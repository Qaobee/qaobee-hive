/*
 *  __________________
 *  Qaobee
 *  __________________
 *
 *  Copyright (c) 2015.  Qaobee
 *  All Rights Reserved.
 *
 *  NOTICE: All information contained here is, and remains
 *  the property of Qaobee and its suppliers,
 *  if any. The intellectual and technical concepts contained
 *  here are proprietary to Qaobee and its suppliers and may
 *  be covered by U.S. and Foreign Patents, patents in process,
 *  and are protected by trade secret or copyright law.
 *  Dissemination of this information or reproduction of this material
 *  is strictly forbidden unless prior written permission is obtained
 *  from Qaobee.
 */

package com.qaobee.hive.technical.vertx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.Handler;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.sockjs.EventBusBridgeHook;
import org.vertx.java.core.sockjs.SockJSSocket;

/**
 * The type Server hook.
 */
public class ServerHook implements EventBusBridgeHook {

    private static final Logger LOG = LoggerFactory.getLogger(ServerHook.class);
    private String siteUrl;

    /**
     * Instantiates a new Server hook.
     *
     * @param siteUrl the site url
     */
    public ServerHook(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    @Override
    public boolean handleSocketCreated(SockJSSocket sock) {
        // You can do things in here like check the Origin of the request
        String origin = sock.headers().get("origin") + "/";
        LOG.info("Origin is " + origin + " / " + siteUrl);
        return siteUrl.equals(origin);
    }

    @Override
    public void handleSocketClosed(SockJSSocket sock) {
        LOG.info("handleSocketClosed, sock = " + sock);
    }

    @Override
    public boolean handleSendOrPub(SockJSSocket sock, boolean send, JsonObject msg, String address) {
        LOG.info(String.format("handleSendOrPub, sock = %s, send = %s, address = %s", sock, send, address));
        return true;
    }

    @Override
    public boolean handlePreRegister(SockJSSocket sock, String address) {
        LOG.info(String.format("handlePreRegister, sock = %s, address = %s", sock, address));
        return true;
    }

    @Override
    public void handlePostRegister(SockJSSocket sock, String address) {
        LOG.info(String.format("handlePostRegister, sock = %s, address = %s", sock, address));
    }

    @Override
    public boolean handleUnregister(SockJSSocket sock, String address) {
        LOG.info(String.format("handleUnregister, sock = %s, address = %s", sock, address));
        return true;
    }

    @Override
    public boolean handleAuthorise(JsonObject message, String sessionID, Handler<AsyncResult<Boolean>> handler) {
        return false;
    }
}
