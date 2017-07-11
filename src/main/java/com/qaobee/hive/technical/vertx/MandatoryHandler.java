package com.qaobee.hive.technical.vertx;

import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.utils.Utils;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * The type Mandatory handler.
 */
public class MandatoryHandler {
    private static final Logger LOG = LoggerFactory.getLogger(MandatoryHandler.class);
    @Inject
    private Utils utils;
    /**
     * Test.
     *
     * @param context the context
     * @param fields  the fields
     */
    public void testBodyParams(RoutingContext context, String... fields) {
        try {
            utils.testMandatoryParams(context, fields);
            context.next();
        } catch (QaobeeException e) {
            LOG.warn(e.getMessage(), e);
            utils.handleError(context, e);
        }
    }

    /**
     * Test.
     *
     * @param context the context
     * @param fields  the fields
     */
    public void testRequesParams(RoutingContext context, String... fields) {
        try {
            utils.testMandatoryParams(context.request().params(), context, fields);
            context.next();
        } catch (QaobeeException e) {
            LOG.warn(e.getMessage(), e);
            utils.handleError(context, e);
        }
    }

    /**
     * Test reques headers.
     *
     * @param context the context
     * @param fields  the fields
     */
    public void testRequesHeaders(RoutingContext context, String... fields) {
        try {
            utils.testMandatoryParams(context.request().headers(), context, fields);
            context.next();
        } catch (QaobeeException e) {
            LOG.warn(e.getMessage(), e);
            utils.handleError(context, e);
        }
    }
}
