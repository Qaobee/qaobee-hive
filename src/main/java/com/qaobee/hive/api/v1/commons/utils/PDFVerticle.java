/*************************************************************************
 * Qaobee
 * __________________
 * <p/>
 * [2014] Qaobee
 * All Rights Reserved.
 * <p/>
 * NOTICE: All information contained here is, and remains
 * the property of Qaobee and its suppliers,
 * if any. The intellectual and technical concepts contained
 * here are proprietary to Qaobee and its suppliers and may
 * be covered by U.S. and Foreign Patents, patents in process,
 * and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Qaobee.
 */
package com.qaobee.hive.api.v1.commons.utils;

import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.tools.MediaReplacedElementFactory;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.FileUtils;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.file.impl.PathAdjuster;
import org.vertx.java.core.impl.VertxInternal;
import org.vertx.java.core.json.JsonObject;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.inject.Inject;
import java.io.*;

/**
 * The Class PDFVerticle.
 *
 * @author Xavier MARIN
 */
@DeployableVerticle(isWorker = true)
public class PDFVerticle extends AbstractGuiceVerticle {

    /**
     * The Constant GENERATE_PDF.
     */
    public static final String GENERATE_PDF = "pdf.generate";
    /**
     * The Constant DATA.
     */
    public static final String DATA = "data";

    /**
     * The Constant TEMPLATE.
     */
    public static final String TEMPLATE = "template";

    /**
     * The Constant FILE_NAME.
     */
    public static final String FILE_NAME = "filename";

    /**
     * The Constant PDF.
     */
    public static final String PDF = "pdf";

    /**
     * The Constant CONTENT_TYPE.
     */
    public static final String CONTENT_TYPE = "application/pdf";

    /**
     * The Constant TEMPLATE_PATH.
     */
    private static final String TEMPLATE_PATH = "pdfTemplates/";
    @Inject
    private Utils utils;
    /**
     * The Cfg.
     */
    Configuration cfg = new Configuration();

    /**
     * Process.
     *
     * @param templatePath le path du template
     * @param params       l'objet d'allimentation
     * @return le template allimenté
     */
    private String process(final String templatePath, final JsonObject params) throws IOException, TemplateException {
        StringWriter out = new StringWriter();
        cfg.setDirectoryForTemplateLoading(new File(PathAdjuster.adjust((VertxInternal) vertx, TEMPLATE_PATH )));
        Template template = cfg.getTemplate(templatePath);
        template.process(params.toMap(), out);
        return out.getBuffer().toString();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.vertx.java.platform.Verticle#start()
     *
     * {template : <template file in src/main/resources/pdfTemplates/>, data : <data>, filename : <File name> } return { pdf : <path to file>}
     */
    @Override
    public void start() {
        super.start();
        container.logger().debug(this.getClass().getName() + " started");
        final Handler<Message<JsonObject>> generatePDFHandler = new Handler<Message<JsonObject>>() {
            @Override
            public void handle(final Message<JsonObject> message) {
                try {
                    if (!message.body().containsField(DATA) || !message.body().containsField(TEMPLATE) || !message.body().containsField(FILE_NAME)) {
                        message.fail(ExceptionCodes.MANDATORY_FIELD.getCode(), "wrong json format");
                        return;
                    }
                    final JsonObject data = message.body().getObject(DATA);
                    // READING CSS

                    final StringBuilder cssStr = new StringBuilder();
                    for (final Object c : container.config().getObject("pdf").getArray("css").toList()) {
                        cssStr.append(FileUtils.readFileToString(new File(PathAdjuster.adjust((VertxInternal) vertx, (String) c))));
                    }
                    data.putString("css", cssStr.toString());
                    final String result = process(message.body().getString(TEMPLATE), data);
                    final File temp = new File(System.getProperty("java.io.tmpdir") + "/" + message.body().getString(FILE_NAME) + ".pdf");
                    if (temp.exists()) {
                        boolean res = temp.delete();
                        container.logger().debug(res);
                    }
                    final OutputStream os = new FileOutputStream(temp);
                    final ITextRenderer renderer = new ITextRenderer();
                    renderer.getSharedContext().setReplacedElementFactory(new MediaReplacedElementFactory(renderer.getSharedContext().getReplacedElementFactory()));
                    renderer.setDocumentFromString(result);
                    renderer.layout();
                    renderer.createPDF(os);
                    final JsonObject res = new JsonObject();
                    res.putString(PDF, temp.getAbsolutePath());
                    message.reply(res);
                } catch (Exception e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendErrorJ(message, ExceptionCodes.INTERNAL_ERROR, e.getMessage());
                }
            }
        };

        // Handlers declaration
        vertx.eventBus().registerHandler(GENERATE_PDF, generatePDFHandler);
    }
}
