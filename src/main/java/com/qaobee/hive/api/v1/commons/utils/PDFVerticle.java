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

import com.qaobee.hive.dao.PdfDAO;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonObject;

import javax.inject.Inject;

/**
 * The Class PDFVerticle.
 *
 * @author Xavier MARIN
 */
@DeployableVerticle()
public class PDFVerticle extends AbstractGuiceVerticle {
    private static final Logger LOG = LoggerFactory.getLogger(PDFVerticle.class);
    public static final String GENERATE_PDF = "internal.pdf.generate";
    public static final String DATA = "data";
    public static final String TEMPLATE = "template";
    public static final String FILE_NAME = "filename";
    public static final String PDF = "pdf";
    public static final String CONTENT_TYPE = "application/pdf";
    @Inject
    private Utils utils;
    @Inject
    private PdfDAO pdfDAO;

    @Override
    public void start() {
        super.start();
        LOG.debug(this.getClass().getName() + " started");
        vertx.eventBus().registerHandler(GENERATE_PDF, this::generatePDF);
    }

    private void generatePDF(Message<JsonObject> message) {
        try {
            utils.testMandatoryParams(message.body().toMap(), DATA, TEMPLATE, FILE_NAME);
            message.reply(pdfDAO.generatePDF(message.body().getObject(DATA), message.body().getString(TEMPLATE), message.body().getString(FILE_NAME)));
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendErrorJ(message,e );
        }
    }
}
