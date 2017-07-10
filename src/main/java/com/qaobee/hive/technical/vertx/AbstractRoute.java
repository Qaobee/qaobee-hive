package com.qaobee.hive.technical.vertx;

import com.qaobee.hive.technical.annotations.VertxRoute;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.exceptions.QaobeeSvcException;
import com.qaobee.hive.technical.utils.Utils;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.AuthHandler;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;

/**
 * The type Abstract route.
 */
public abstract class AbstractRoute implements VertxRoute.Route {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractRoute.class);
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
     * Handle error.
     *
     * @param context the context
     * @param e       the e
     */
    protected void handleError(RoutingContext context, QaobeeSvcException e) {
        LOG.error(e.getMessage(), e);
        context.response().putHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON)
                .setStatusCode(e.getCode().getCode())
                .end(new JsonObject()
                        .put(Constants.STATUS, false)
                        .put("message", e.getMessage())
                        .put("code", e.getCode().name()).encode()
                );
    }

    /**
     * Handle error.
     *
     * @param context the context
     * @param e       the e
     */
    protected void handleError(RoutingContext context, QaobeeException e) {
        context.response().putHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON)
                .setStatusCode(e.getCode().getCode())
                .end(new JsonObject()
                        .put(Constants.STATUS, false)
                        .put("message", e.getMessage())
                        .put("code", e.getCode().name()).encode()
                );
    }

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
                handleError(context, (QaobeeSvcException) event.cause());
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
                handleError(context, (QaobeeSvcException) event.cause());
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
                .putHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON)
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
                .putHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON)
                .setStatusCode(200)
                .end(result.encode());
    }
}
