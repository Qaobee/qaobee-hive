package com.qaobee.hive.technical.utils;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.AuthProvider;
import io.vertx.ext.auth.User;

public class RequestUser implements User {
    /**
     * Is the user authorised to
     *
     * @param authority     the authority - what this really means is determined by the specific implementation. It might
     *                      represent a permission to access a resource e.g. `printers:printer34` or it might represent
     *                      authority to a role in a roles based model, e.g. `role:admin`.
     * @param resultHandler handler that will be called with an {@link AsyncResult} containing the value
     *                      `true` if the they has the authority or `false` otherwise.
     * @return the User to enable fluent use
     */
    @Override
    public User isAuthorised(String authority, Handler<AsyncResult<Boolean>> resultHandler) {
        return null;
    }

    /**
     * The User object will cache any authorities that it knows it has to avoid hitting the
     * underlying auth provider each time.  Use this method if you want to clear this cache.
     *
     * @return the User to enable fluent use
     */
    @Override
    public User clearCache() {
        return null;
    }

    /**
     * Get the underlying principal for the User. What this actually returns depends on the implementation.
     * For a simple user/password based auth, it's likely to contain a JSON object with the following structure:
     * <pre>
     *   {
     *     "username", "tim"
     *   }
     * </pre>
     *
     * @return JSON representation of the Principal
     */
    @Override
    public JsonObject principal() {
        return null;
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
}
