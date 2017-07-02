/*
 *   __________________
 *    Qaobee
 *    __________________
 *
 *    Copyright (c) 2015.  Qaobee
 *    All Rights Reserved.
 *
 *    NOTICE: All information contained here is, and remains
 *    the property of Qaobee and its suppliers,
 *    if any. The intellectual and technical concepts contained
 *    here are proprietary to Qaobee and its suppliers and may
 *    be covered by U.S. and Foreign Patents, patents in process,
 *    and are protected by trade secret or copyright law.
 *    Dissemination of this information or reproduction of this material
 *    is strictly forbidden unless prior written permission is obtained
 *    from Qaobee.
 */

package com.qaobee.hive.dao.impl;

import com.lowagie.text.DocumentException;
import com.qaobee.hive.dao.PdfDAO;
import com.qaobee.hive.dao.TemplatesDAO;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.tools.MediaReplacedElementFactory;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.jdeferred.Deferred;
import org.jdeferred.Promise;
import org.jdeferred.impl.DeferredObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.util.XRRuntimeException;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * The type Pdf dao.
 */
public class PdfDAOImpl implements PdfDAO {
    private static final Logger LOG = LoggerFactory.getLogger(PdfDAOImpl.class);
    private static final String PDF = "pdf";
    @Inject
    private TemplatesDAO templatesDAO;
    @Inject
    private Vertx vertx;
    @Inject
    @Named("pdf")
    private JsonObject pdfConfig;

    @Override
    public Promise<JsonObject, QaobeeException, Integer> generatePDF(JsonObject data, String template, String filename) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        vertx.executeBlocking(bl -> {
            OutputStream os = null;
            try {// NOSONAR
                final StringBuilder cssStr = new StringBuilder();
                pdfConfig.getJsonArray("css").getList().forEach(c -> cssStr.append(vertx.fileSystem().readFileBlocking((String) c).toString()));
                data.put("css", cssStr.toString());
                String datadir = System.getProperty("user.home");
                if (StringUtils.isNotBlank(System.getenv("OPENSHIFT_DATA_DIR"))) {
                    datadir = System.getenv("OPENSHIFT_DATA_DIR");
                }
                File dir = new File(datadir + "/tmp");
                if (!dir.exists()) {
                    assert dir.mkdirs();
                }
                final File temp = new File(datadir + "/tmp/" + filename + ".pdf");
                vertx.fileSystem().deleteBlocking(temp.getAbsolutePath());
                os = new FileOutputStream(temp);
                final ITextRenderer renderer = new ITextRenderer();
                renderer.getSharedContext().setReplacedElementFactory(new MediaReplacedElementFactory(renderer.getSharedContext().getReplacedElementFactory(), dir));
                renderer.setDocumentFromString(templatesDAO.generatePDF(data, template));
                renderer.layout();
                renderer.createPDF(os);
                final JsonObject res = new JsonObject();
                res.put(PDF, temp.getAbsolutePath());
                IOUtils.closeQuietly(os);
                bl.complete(res);
            } catch (XRRuntimeException | DocumentException | IOException e) {
                LOG.error(e.getMessage(), e);
                bl.fail(new QaobeeException(ExceptionCodes.INTERNAL_ERROR, e));
            } catch (QaobeeException e) {
                LOG.error(e.getMessage(), e);
                bl.fail(e);
            } finally {
                if (os != null) {
                    IOUtils.closeQuietly(os);
                }
            }
        }, ar -> {
            if (ar.succeeded()) {
                deferred.resolve((JsonObject) ar.result());
            } else {
                deferred.reject((QaobeeException) ar.cause());
            }
        });
        return deferred.promise();
    }
}
