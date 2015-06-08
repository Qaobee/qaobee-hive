package com.qaobee.hive.technical.vertx.utils.guice.services;

/**
 * Created by xavier on 09/11/14.
 */
public interface Files {
    public byte[] get(String name);
    public void put(byte[] data, String name);
}
