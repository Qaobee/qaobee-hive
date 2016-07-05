package com.qaobee.hive.dao;

import com.qaobee.hive.technical.exceptions.QaobeeException;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

public interface StructureDAO {
    JsonObject update(JsonObject structure) throws QaobeeException;

    JsonArray getListOfStructures(String activity, JsonObject address) throws QaobeeException;

    JsonObject getStructure(String id) throws QaobeeException;

    JsonObject addStructure(JsonObject structure) throws QaobeeException;
}
