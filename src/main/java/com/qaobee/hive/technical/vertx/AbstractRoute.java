package com.qaobee.hive.technical.vertx;

import com.qaobee.hive.technical.annotations.VertxRoute;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.utils.Utils;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.AuthHandler;
import org.apache.http.protocol.HTTP;

import javax.inject.Inject;

/**
 * The type Abstract route.
 */
public abstract class AbstractRoute implements VertxRoute.Route {
    /**
     * The Vertx.
     */
    @Inject
    protected Vertx vertx;
    /**
     * The Utils.
     */
    @Inject
    protected Utils utils;
    /**
     * The Auth handler.
     */
    @Inject
    protected AuthHandler authHandler;
    /**
     * The Mandatory handler.
     */
    @Inject
    protected MandatoryHandler mandatoryHandler;


    /**
     * Handle response handler.
     *
     * @param context the context
     * @return the handler
     */
    protected Handler<AsyncResult<JsonObject>> handleResponse(RoutingContext context) {
        return event -> {
            if (event.succeeded()) {
                handleResponse(context, event.result());
            } else {
                utils.handleError(context, (QaobeeException) event.cause());
            }
        };
    }

    /**
     * Handle response array handler.
     *
     * @param context the context
     * @return the handler
     */
    protected Handler<AsyncResult<JsonArray>> handleResponseArray(RoutingContext context) {
        return event -> {
            if (event.succeeded()) {
                handleResponse(context, event.result());
            } else {
                utils.handleError(context, (QaobeeException) event.cause());
            }
        };
    }


    /**
     * Handle response.
     *
     * @param context the context
     * @param result  the result
     */
    protected void handleResponse(RoutingContext context, JsonObject result) {
        context.response()
                .putHeader(HTTP.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON)
                .setStatusCode(200)
                .end(result.encode());
    }

    /**
     * Handle response.
     *
     * @param context the context
     * @param result  the result
     */
    protected void handleResponse(RoutingContext context, JsonArray result) {
        context.response()
                .putHeader(HTTP.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON)
                .setStatusCode(200)
                .end(result.encode());
    }

    /**
     * Handle status.
     *
     * @param b       the b
     * @param context the context
     */
    protected void handleStatus(boolean b, RoutingContext context) {
        handleResponse(context, new JsonObject().put(Constants.STATUS, b));
    }

    /**
     * Gets locale.
     *
     * @param context the context
     * @return the locale
     */
    protected String getLocale(RoutingContext context) {
        return context.request().getHeader("Accept-Language");
    }
}
