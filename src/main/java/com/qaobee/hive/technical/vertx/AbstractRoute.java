package com.qaobee.hive.technical.vertx;

import com.qaobee.hive.technical.annotations.VertxRoute;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.dao.Utils;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
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
    @Inject
    protected RoleHandler roleHandler;
    /**
     * The Mandatory handler.
     */
    @Inject
    protected MandatoryHandler mandatoryHandler;


    /**
     * Handle response handler.
     *
     * @param context the context
     *
     * @return the handler
     */
    protected Handler<AsyncResult<JsonObject>> handleResponse(RoutingContext context) {
        return event -> {
            if (event.succeeded()) {
                handleResponse(context, event.result());
            } else {
                if(event.cause() instanceof QaobeeException) {
                    utils.handleError(context, event.cause());
                } else {
                    utils.handleError(context, new QaobeeException(ExceptionCodes.INTERNAL_ERROR, event.cause().getMessage()));
                }
            }
        };
    }

    /**
     * Handle response array handler.
     *
     * @param context the context
     *
     * @return the handler
     */
    protected Handler<AsyncResult<JsonArray>> handleResponseArray(RoutingContext context) {
        return event -> {
            if (event.succeeded()) {
                handleResponse(context, event.result());
            } else {
                utils.handleError(context, event.cause());
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
     *
     * @return the locale
     */
    protected String getLocale(RoutingContext context) {
        return context.request().getHeader("Accept-Language").split(",")[0];
    }

    /**
     * Add route.
     *
     * @param router   the router
     * @param path     the path
     * @param method   the method
     * @param handlers the handlers
     */
    @SafeVarargs
    protected final void addRoute(Router router, String path, HttpMethod method, Handler<RoutingContext>... handlers) {
        for(Handler<RoutingContext> h : handlers) {
            router.route(method, path).handler(h);
        }
    }
}
