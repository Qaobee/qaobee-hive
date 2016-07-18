package com.qaobee.hive.dao;

import com.qaobee.hive.technical.exceptions.QaobeeException;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

/**
 * The interface Effective dao.
 */
public interface EffectiveDAO {
    /**
     * Add json object.
     *
     * @param effective the effective
     * @return the json object
     * @throws QaobeeException the qaobee exception
     */
    JsonObject add(JsonObject effective) throws QaobeeException;

    /**
     * Update json object.
     *
     * @param effective the effective
     * @return the json object
     */
    JsonObject update(JsonObject effective);

    /**
     * Gets effective list.
     *
     * @param sandboxId       the sandbox id
     * @param categoryAgeCode the category age code
     * @return the effective list
     * @throws QaobeeException the qaobee exception
     */
    JsonArray getEffectiveList(String sandboxId, String categoryAgeCode) throws QaobeeException;

    /**
     * Gets effective.
     *
     * @param id the id
     * @return the effective
     * @throws QaobeeException the qaobee exception
     */
    JsonObject getEffective(String id) throws QaobeeException;
}
