/*
 *  __________________
 *  Qaobee
 *  __________________
 *
 *  Copyright (c) 2015.  Qaobee
 *  All Rights Reserved.
 *
 *  NOTICE: All information contained here is, and remains
 *  the property of Qaobee and its suppliers,
 *  if any. The intellectual and technical concepts contained
 *  here are proprietary to Qaobee and its suppliers and may
 *  be covered by U.S. and Foreign Patents, patents in process,
 *  and are protected by trade secret or copyright law.
 *  Dissemination of this information or reproduction of this material
 *  is strictly forbidden unless prior written permission is obtained
 *  from Qaobee.
 */
package com.qaobee.hive.api.v1.commons.utils;

import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import freemarker.template.*;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.file.impl.PathAdjuster;
import org.vertx.java.core.impl.VertxInternal;
import org.vertx.java.core.json.JsonObject;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * The Class TemplatesVerticle.
 *
 * @author Xavier MARIN
 */
@DeployableVerticle(isWorker = true)
public class TemplatesVerticle extends AbstractGuiceVerticle {

    /**
     * The Constant DATA.
     */
    public static final String DATA = "data";

    /**
     * The Constant TEMPLATE.
     */
    public static final String TEMPLATE = "template";

    /**
     * The Constant TEMPLATE_PATH.
     */
    private static final String TEMPLATE_PATH = "mailTemplates";

    /**
     * The Constant TEMPLATE_GENERATE.
     */
    public static final String TEMPLATE_GENERATE = "template.generate";

    /**
     * The Utils.
     */
    @Inject
    private Utils utils;

    /**
     * Start void.
     */
    @Override
    public void start() {
        super.start();
        container.logger().debug(this.getClass().getName() + " started");

        final Configuration cfg = new Configuration();

        // Where do we load the templates from:
        try {
            cfg.setDirectoryForTemplateLoading(new File(PathAdjuster.adjust((VertxInternal) vertx, TEMPLATE_PATH)));
        } catch (final IOException e) {
            container.logger().error(e);
        }

        // Some other recommended settings:
        cfg.setIncompatibleImprovements(new Version(2, 3, 20));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        final Handler<Message<JsonObject>> generateHandler = new Handler<Message<JsonObject>>() {
            @Override
            public void handle(final Message<JsonObject> message) {
                final Map<String, Object> input = new HashMap<>();

                try {
                    if (!message.body().containsField(DATA) || !message.body().containsField(TEMPLATE)) {
                        message.fail(ExceptionCodes.MANDATORY_FIELD.getCode(), "wrong json format");
                        return;
                    }
                    final JsonObject data = message.body().getObject(DATA);

                    input.putAll(data.toMap());
                    final Writer writer = new StringWriter();
                    final Template template = cfg.getTemplate(message.body().getString(TEMPLATE));
                    template.process(input, writer);
                    final JsonObject res = new JsonObject();
                    final String resTpl = writer.toString();
                    res.putString("result", resTpl);

                    message.reply(res);
                } catch (IOException | TemplateException e) {
                    container.logger().error(e.getMessage(), e);
                    utils.sendErrorJ(message, ExceptionCodes.INTERNAL_ERROR, e.getMessage());
                }
            }
        };

        // Handlers declaration
        vertx.eventBus().registerHandler(TEMPLATE_GENERATE, generateHandler);
    }
}