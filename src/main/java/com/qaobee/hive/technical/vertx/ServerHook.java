package com.qaobee.hive.technical.vertx;

import org.slf4j.Logger;
import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.Handler;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.sockjs.EventBusBridgeHook;
import org.vertx.java.core.sockjs.SockJSSocket;

/**
 * The type Server hook.
 */
public class ServerHook implements EventBusBridgeHook {
    /**
     * The Logger.
     */
    private Logger logger;

    /**
     * Instantiates a new Server hook.
     *
     * @param logger the logger
     */
    public ServerHook(Logger logger) {
        this.logger = logger;
    }

    /**
     * Handle socket created boolean.
     *
     * @param sock the sock
     * @return the boolean
     */
    @Override
    public boolean handleSocketCreated(SockJSSocket sock) {
        // You can do things in here like check the Origin of the request
        String origin = sock.headers().get("origin");
        logger.info("Origin is " + origin);
        return !(origin == null || !origin.equals("http://localhost:3000"));
    }

    /**
     * The socket has been closed
     *
     * @param sock The socket
     */
    @Override
    public void handleSocketClosed(SockJSSocket sock) {
        logger.info("handleSocketClosed, sock = " + sock);
    }

    /**
     * Client is sending or publishing on the socket
     *
     * @param sock    The sock
     * @param send    if true it's a send else it's a publish
     * @param msg     The message
     * @param address The address the message is being sent/published to
     * @return true To allow the send/publish to occur, false otherwise
     */
    @Override
    public boolean handleSendOrPub(SockJSSocket sock, boolean send, JsonObject msg, String address) {
        logger.info("handleSendOrPub, sock = " + sock + ", send = " + send + ", address = " + address);
        logger.info(msg.encode());
        return true;
    }

    /**
     * Client is registering a handler
     *
     * @param sock    The socket
     * @param address The address
     * @return true to let the registration occur, false otherwise
     */
    @Override
    public boolean handlePreRegister(SockJSSocket sock, String address) {
        logger.info("handlePreRegister, sock = " + sock + ", address = " + address);
        return true;
    }

    @Override
    public void handlePostRegister(SockJSSocket sock, String address) {
        logger.info("handlePostRegister, sock = " + sock + ", address = " + address);
    }

    /**
     * Client is unregistering a handler
     *
     * @param sock    The socket
     * @param address The address
     */
    @Override
    public boolean handleUnregister(SockJSSocket sock, String address) {
        logger.info("handleUnregister, sock = " + sock + ", address = " + address);
        return true;
    }

    @Override
    public boolean handleAuthorise(JsonObject message, String sessionID, Handler<AsyncResult<Boolean>> handler) {
        return false;
    }
}
