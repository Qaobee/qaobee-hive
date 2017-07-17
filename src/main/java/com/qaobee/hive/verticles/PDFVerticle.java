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

package com.qaobee.hive.verticles;

import com.qaobee.hive.dao.PdfDAO;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * The Class PDFVerticle.
 *
 * @author Xavier MARIN
 */
@DeployableVerticle
public class PDFVerticle extends AbstractGuiceVerticle {
    private static final Logger LOG = LoggerFactory.getLogger(PDFVerticle.class);
    /**
     * The constant GENERATE_PDF.
     */
    public static final String GENERATE_PDF = "internal.pdf.generate";
    /**
     * The constant DATA.
     */
    public static final String DATA = "data";
    /**
     * The constant TEMPLATE.
     */
    public static final String TEMPLATE = "template";
    /**
     * The constant FILE_NAME.
     */
    public static final String FILE_NAME = "filename";
    /**
     * The constant PDF.
     */
    public static final String PDF = "pdf";
    /**
     * The constant CONTENT_TYPE.
     */
    public static final String CONTENT_TYPE = "application/pdf";

    @Inject
    private PdfDAO pdfDAO;

    @Override
    public void start(Future<Void> startFuture) {
        inject(this).add(GENERATE_PDF, this::generatePDF).register(startFuture);
    }

    private void generatePDF(Message<JsonObject> message) {
        try {
            utils.testMandatoryParams(message.body(), DATA, TEMPLATE, FILE_NAME);
            pdfDAO.generatePDF(message.body().getJsonObject(DATA), message.body().getString(TEMPLATE), message.body().getString(FILE_NAME), handleJson(message));
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendErrorJ(message,e );
        }
    }
}
