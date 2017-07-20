package com.qaobee.hive.technical.vertx;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.AbstractUser;
import io.vertx.ext.auth.AuthProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class QaobeeUser extends AbstractUser {
    private static final Logger LOG = LoggerFactory.getLogger(QaobeeUser.class);
    private JsonObject principal;

    public QaobeeUser(JsonObject principal) {
        this.principal = principal;
    }

    @Override
    protected void doIsPermitted(String permissionOrRole, Handler<AsyncResult<Boolean>> resultHandler) {
        if (permissionOrRole != null) {
            doHasRole(permissionOrRole, resultHandler);
        }
    }

    @Override
    public JsonObject principal() {
        return principal;
    }

    /**
     * Set the auth provider for the User. This is typically used to reattach a detached User with an AuthProvider, e.g.
     * after it has been deserialized.
     *
     * @param authProvider the AuthProvider - this must be the same type of AuthProvider that originally created the User
     */
    @Override
    public void setAuthProvider(AuthProvider authProvider) {
        // nothing
    }

    private void doHasRole(String role, Handler<AsyncResult<Boolean>> resultHandler) {
        try {
            Optional<JsonArray> roles = Optional.ofNullable(principal.getJsonObject("account").getJsonArray("habilitations", new JsonArray()));
            final boolean[] found = {false};
            roles.orElseGet(JsonArray::new).forEach(r-> {
                if(((JsonObject)r).getString("key").equals(role)) {
                    resultHandler.handle(Future.succeededFuture(true));
                    found[0] = true;
                }
            });
            if(!found[0]) {
                resultHandler.handle(Future.succeededFuture(false));
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            resultHandler.handle(Future.failedFuture(e));
        }
    }

    @Override
    public String toString() {
        return principal.toString();
    }

}
