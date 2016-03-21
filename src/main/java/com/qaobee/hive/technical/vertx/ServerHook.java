package com.qaobee.hive.technical.vertx;

import com.qaobee.hive.technical.tools.Params;
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

    public boolean handleSocketCreated(SockJSSocket sock) {
        // You can do things in here like check the Origin of the request
        String origin = sock.headers().get("origin") + "/";
        LOG.info("Origin is " + origin + " / " + Params.getString("site.url"));
        return !(origin == null || !origin.equals(Params.getString("site.url")));
    }

    @Override
    public void handleSocketClosed(SockJSSocket sock) {
        LOG.info("handleSocketClosed, sock = " + sock);
    }

    @Override
    public boolean handleSendOrPub(SockJSSocket sock, boolean send, JsonObject msg, String address) {
        LOG.info("handleSendOrPub, sock = " + sock + ", send = " + send + ", address = " + address);
        LOG.info(msg.encode());
        return true;
    }

    @Override
    public boolean handlePreRegister(SockJSSocket sock, String address) {
        LOG.info("handlePreRegister, sock = " + sock + ", address = " + address);
        return true;
    }

    @Override
    public void handlePostRegister(SockJSSocket sock, String address) {
        LOG.info("handlePostRegister, sock = " + sock + ", address = " + address);
    }

    @Override
    public boolean handleUnregister(SockJSSocket sock, String address) {
        LOG.info("handleUnregister, sock = " + sock + ", address = " + address);
        return true;
    }

    @Override
    public boolean handleAuthorise(JsonObject message, String sessionID, Handler<AsyncResult<Boolean>> handler) {
        return false;
    }
}
