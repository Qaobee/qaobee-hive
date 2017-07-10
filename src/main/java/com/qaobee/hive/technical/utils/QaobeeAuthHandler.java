package com.qaobee.hive.technical.utils;

import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.tools.Messages;
import com.qaobee.hive.technical.utils.guice.MongoClientCustom;
import com.qaobee.hive.technical.vertx.QaobeeUser;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.AuthHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.protocol.HTTP;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;

public class QaobeeAuthHandler implements AuthHandler {
    @Inject
    private Utils utils;
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
        RequestWrapper request = utils.wrapRequest(context);
        String token = getToken(request);
        if (StringUtils.isBlank(token)) {
            handle401(context, Messages.getString(NOT_LOGGED_KEY, request.getLocale()));
        } else {
            mongoClient.findOne(DBCollections.USER, new JsonObject().put("account.token", token), new JsonObject(), result -> {
                if (result.succeeded() && result.result() != null) {
                    testSession(result.result(), request, context);
                } else {
                    handle401(context, Messages.getString(NOT_LOGGED_KEY, request.getLocale()));
                }
            });
        }
    }

    private void testSession(JsonObject jsonUser, RequestWrapper request, RoutingContext context) {
        // we take the first one (should be only one)
        final com.qaobee.hive.business.model.commons.users.User user = Json.decodeValue(jsonUser.encode(), com.qaobee.hive.business.model.commons.users.User.class);
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
        mongo.upsert(jsonUser, DBCollections.USER).done(id -> {
            if (user.getAccount().getTokenRenewDate() == 0) {
                handle401(context, Messages.getString(SESSION_EXPIRED, request.getLocale()));
            } else {
                context.setUser(new QaobeeUser(jsonUser));
                context.next();
            }
        }).fail(e->handle401(context, Messages.getString(NOT_LOGGED_KEY, request.getLocale())));
    }

    private static void handle401(RoutingContext context, String message) {
        context.response().putHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON)
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

    private static String getToken(RequestWrapper request) {
        if (request.getHeaders() != null && request.getHeaders().containsKey(Constants.TOKEN)) {
            return request.getHeaders().get(Constants.TOKEN).get(0);
        }
        if (request.getParams() != null && request.getParams().containsKey(Constants.TOKEN)) {
            return request.getParams().get(Constants.TOKEN).get(0);
        }
        return null;
    }
}
