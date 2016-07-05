package com.qaobee.hive.dao;

import com.qaobee.hive.technical.exceptions.QaobeeException;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

/**
 * The interface Championship dao.
 */
public interface ChampionshipDAO {
    /**
     * Update championship json object.
     *
     * @param championship the championship
     * @return the json object
     * @throws QaobeeException the qaobee exception
     */
    JsonObject updateChampionship(JsonObject championship) throws QaobeeException;

    /**
     * Add championship json object.
     *
     * @param championship the championship
     * @return the json object
     * @throws QaobeeException the qaobee exception
     */
    JsonObject addChampionship(JsonObject championship) throws QaobeeException;

    /**
     * Gets championship.
     *
     * @param id the id
     * @return the championship
     * @throws QaobeeException the qaobee exception
     */
    JsonObject getChampionship(String id) throws QaobeeException;

    /**
     * Gets list championships.
     *
     * @param params the params
     * @return the list championships
     * @throws QaobeeException the qaobee exception
     */
    JsonArray getListChampionships(JsonObject params) throws QaobeeException;
}
