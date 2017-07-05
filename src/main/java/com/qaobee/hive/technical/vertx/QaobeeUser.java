package com.qaobee.hive.technical.vertx;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.AbstractUser;
import io.vertx.ext.auth.AuthProvider;

public class QaobeeUser extends AbstractUser {
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

    }

    private void doHasRole(String role, Handler<AsyncResult<Boolean>> resultHandler) {
        try {
            JsonArray roles = principal.getJsonObject("account").getJsonArray("habilitations", new JsonArray());
            resultHandler.handle(Future.succeededFuture(roles.contains(role)));
        } catch (Throwable e) {
            resultHandler.handle(Future.failedFuture(e));
        }
    }

    @Override
    public String toString() {
        return principal.toString();
    }

}
