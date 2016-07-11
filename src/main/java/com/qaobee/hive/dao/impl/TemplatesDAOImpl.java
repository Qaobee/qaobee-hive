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

package com.qaobee.hive.dao.impl;

import com.qaobee.hive.dao.TemplatesDAO;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.json.JsonObject;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Templates dao.
 */
public class TemplatesDAOImpl implements TemplatesDAO {
    private static final Logger LOG = LoggerFactory.getLogger(TemplatesDAOImpl.class);

    /**
     * The constant DATA.
     */
    public static final String DATA = "data";
    /**
     * The constant TEMPLATE.
     */
    public static final String TEMPLATE = "template";
    private Configuration cfg;

    /**
     * Instantiates a new Templates dao.
     *
     * @param cfg the cfg
     */
    public TemplatesDAOImpl(Configuration cfg) {
        this.cfg = cfg;
    }

    @Override
    public JsonObject generatePDFHandler(JsonObject body) throws QaobeeException {
        final Map<String, Object> input = new HashMap<>();
        final JsonObject res = new JsonObject();
        try {
            if (!body.containsField(DATA) || !body.containsField(TEMPLATE)) {
                throw new QaobeeException(ExceptionCodes.MANDATORY_FIELD, "wrong json format");
            }
            input.putAll(body.getObject(DATA).toMap());
            final Writer writer = new StringWriter();
            final Template template = cfg.getTemplate(body.getString(TEMPLATE));
            template.process(input, writer);
            final String resTpl = writer.toString();
            res.putString("result", resTpl);
        } catch (IOException | TemplateException e) {
            LOG.error(e.getMessage(), e);
            throw new QaobeeException(ExceptionCodes.INTERNAL_ERROR, e);
        }
        return res;
    }
}
