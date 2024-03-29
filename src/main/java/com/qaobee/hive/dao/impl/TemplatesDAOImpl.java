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
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private final Configuration cfgMail;
    private final Configuration cfgPDF;

    /**
     * Instantiates a new Templates dao.
     *
     * @param cfgMail the mail cfg
     * @param cfgPDF  the pdf config
     */
    public TemplatesDAOImpl(Configuration cfgMail, Configuration cfgPDF) {
        this.cfgMail = cfgMail;
        this.cfgPDF = cfgPDF;
    }

    @Override
    @SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
    public String generatePDF(JsonObject body) throws QaobeeException {
        if (!body.containsKey(DATA) || !body.containsKey(TEMPLATE)) {
            throw new QaobeeException(ExceptionCodes.MANDATORY_FIELD, "wrong json format");
        }
        return generatePDF(body.getJsonObject(DATA), body.getString(TEMPLATE));
    }

    @Override
    @SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
    public String generatePDF(JsonObject data, String template) throws QaobeeException {
        try {
            StringWriter out = new StringWriter();
            Template tpls = cfgPDF.getTemplate(template);
            tpls.process(data.getMap(), out);
            return out.getBuffer().toString();
        } catch (IOException | TemplateException e) {
            LOG.error(e.getMessage(), e);
            throw new QaobeeException(ExceptionCodes.INTERNAL_ERROR, e);
        }
    }

    @Override
    @SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
    public JsonObject generateMail(JsonObject body) throws QaobeeException {
        if (!body.containsKey(DATA) || !body.containsKey(TEMPLATE)) {
            throw new QaobeeException(ExceptionCodes.MANDATORY_FIELD, "wrong json format");
        }
        return generateMail(body.getJsonObject(DATA), body.getString(TEMPLATE));
    }

    @Override
    @SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
    public JsonObject generateMail(JsonObject data, String template) throws QaobeeException {
        try {
            final JsonObject res = new JsonObject();
            final Map<String, Object> input = new HashMap<>();
            input.putAll(data.getMap());
            final Writer writer = new StringWriter();
            final Template tmpl = cfgMail.getTemplate(template);
            tmpl.process(input, writer);
            final String resTpl = writer.toString();
            res.put("result", resTpl);
            return res;
        } catch (IOException | TemplateException e) {
            LOG.error(e.getMessage(), e);
            throw new QaobeeException(ExceptionCodes.INTERNAL_ERROR, e);
        }
    }
}
