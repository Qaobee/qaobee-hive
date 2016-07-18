package com.qaobee.hive.dao.impl;

import com.lowagie.text.DocumentException;
import com.qaobee.hive.dao.PdfDAO;
import com.qaobee.hive.dao.TemplatesDAO;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.tools.MediaReplacedElementFactory;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.file.impl.PathAdjuster;
import org.vertx.java.core.impl.VertxInternal;
import org.vertx.java.core.json.JsonObject;
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
    /**
     * The Templates dao.
     */
    @Inject
    TemplatesDAO templatesDAO;
    @Inject
    private Vertx vertx;
    /**
     * The Pdf config.
     */
    @Inject
    @Named("pdf")
    JsonObject pdfConfig;
    /**
     * The Env.
     */
    @Inject
    @Named("env")
    JsonObject env;

    @Override
    public JsonObject generatePDF(JsonObject data, String template, String filename) throws QaobeeException {
        try {
            final StringBuilder cssStr = new StringBuilder();
            for (final Object c : pdfConfig.getArray("css").toList()) {
                cssStr.append(FileUtils.readFileToString(new File(PathAdjuster.adjust((VertxInternal) vertx, (String) c)), "UTF-8"));
            }
            data.putString("css", cssStr.toString());
            String datadir = System.getProperty("user.home");
            if (env.containsField("OPENSHIFT_DATA_DIR")) {
                datadir = env.getString("OPENSHIFT_DATA_DIR");
            }
            File dir = new File(datadir + "/tmp/");
            if (!dir.exists()) {
                assert dir.mkdirs();
            }
            final File temp = new File(datadir + "/tmp/" + filename + ".pdf");
            if (temp.exists()) {
                assert temp.delete();
            }
            final OutputStream os = new FileOutputStream(temp);
            final ITextRenderer renderer = new ITextRenderer();
            renderer.getSharedContext().setReplacedElementFactory(new MediaReplacedElementFactory(renderer.getSharedContext().getReplacedElementFactory(), dir));
            renderer.setDocumentFromString(templatesDAO.generatePDF(data, template));
            renderer.layout();
            renderer.createPDF(os);
            final JsonObject res = new JsonObject();
            res.putString(PDF, temp.getAbsolutePath());
            return res;
        } catch (XRRuntimeException | DocumentException | IOException e) {
            LOG.error(e.getMessage(), e);
            throw new QaobeeException(ExceptionCodes.INTERNAL_ERROR, e);
        }
    }
}
