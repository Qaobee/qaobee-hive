package com.qaobee.hive.services;

import com.qaobee.hive.services.impl.CRMServiceImpl;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

/**
 * The interface Crm dao.
 */
@ProxyGen
@VertxGen
@FunctionalInterface
public interface CRMService {

    /**
     * Create crm service.
     *
     * @param vertx the vertx
     *
     * @return the crm service
     */
    static CRMService create(Vertx vertx) {
        return new CRMServiceImpl(vertx);
    }

    /**
     * Create proxy crm service.
     *
     * @param vertx   the vertx
     * @param address the address
     *
     * @return the crm service
     */
    static CRMService createProxy(Vertx vertx, String address) {
        return new CRMServiceVertxEBProxy(vertx, address);
    }

    /**
     * Register user.
     *
     * @param user       the user
     * @param firstLogin the first login
     */
    void registerUser(JsonObject user, boolean firstLogin);
}
