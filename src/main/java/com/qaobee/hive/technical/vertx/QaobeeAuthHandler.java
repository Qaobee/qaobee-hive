package com.qaobee.hive.technical.vertx;

import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.services.MongoDB;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.tools.Messages;
import com.qaobee.hive.technical.utils.MongoClientCustom;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.AuthHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.protocol.HTTP;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

public class QaobeeAuthHandler implements AuthHandler {
    @Inject
    private MongoClientCustom mongoClient;
    @Inject
    private MongoDB mongo;

    private final Set<String> authorities = new HashSet<>();
    private static final String NOT_LOGGED_KEY = "not.logged";
    private static final String SESSION_EXPIRED = "session.expired";
    private static final String ACCOUNT_FIELD = "account";

    /**
     * Something has happened, so handle it.
     *
     * @param context the context to handle
     */
    @Override
    public void handle(RoutingContext context) {
        String token = getToken(context.request());
        String locale = context.request().getHeader("Accept-Language");
        if (StringUtils.isBlank(token)) {
            handle401(context, Messages.getString(NOT_LOGGED_KEY, locale));
        } else {
            mongoClient.findOne(DBCollections.USER, new JsonObject().put("account.token", token), new JsonObject(), result -> {
                if (result.succeeded() && result.result() != null) {
                    testSession(result.result(), context, locale);
                } else {
                    handle401(context, Messages.getString(NOT_LOGGED_KEY, locale));
                }
            });
        }
    }

    private void testSession(JsonObject jsonUser, RoutingContext context, String locale) {
        // we take the first one (should be only one)
        final User user = Json.decodeValue(jsonUser.encode(), User.class);
        if (Constants.DEFAULT_SESSION_TIMEOUT < System.currentTimeMillis() - user.getAccount().getTokenRenewDate()) {
            jsonUser.getJsonObject(ACCOUNT_FIELD).put("token", "");
            user.getAccount().setToken(null);
            jsonUser.getJsonObject(ACCOUNT_FIELD).put("tokenRenewDate", 0L);
            user.getAccount().setTokenRenewDate(0L);
        } else {
            long connectionTime = System.currentTimeMillis();
            jsonUser.getJsonObject(ACCOUNT_FIELD).put("tokenRenewDate", connectionTime);
            user.getAccount().setTokenRenewDate(connectionTime);
        }
        mongo.upsert(jsonUser, DBCollections.USER, upsertRes -> {
            if(upsertRes.succeeded()) {
                if (user.getAccount().getTokenRenewDate() == 0) {
                    handle401(context, Messages.getString(SESSION_EXPIRED, locale));
                } else {
                    context.setUser(new QaobeeUser(jsonUser));
                    context.next();
                }
            } else {
                handle401(context, Messages.getString(NOT_LOGGED_KEY, locale));
            }
        });
    }

    private static void handle401(RoutingContext context, String message) {
        context.response().putHeader(HTTP.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON)
                .setStatusCode(401)
                .end(new JsonObject()
                        .put(Constants.STATUS, false)
                        .put("message", message)
                        .put("code", ExceptionCodes.NOT_LOGGED.name()).encode()
                );
    }

    @Override
    public AuthHandler addAuthority(String authority) {
        authorities.add(authority);
        return this;
    }

    @Override
    public AuthHandler addAuthorities(Set<String> authorities) {
        this.authorities.addAll(authorities);
        return this;
    }

    private static String getToken(HttpServerRequest request) {
        if (request.headers().contains(Constants.TOKEN)) {
            return request.getHeader(Constants.TOKEN);
        }
        if (request.params().contains(Constants.TOKEN)) {
            return request.getParam(Constants.TOKEN);
        }
        return null;
    }
}
