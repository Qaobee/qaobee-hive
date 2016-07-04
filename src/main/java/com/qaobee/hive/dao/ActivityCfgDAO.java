package com.qaobee.hive.dao;

import com.qaobee.hive.technical.exceptions.QaobeeException;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

/**
 * Created by xavier on 04/07/16.
 */
public interface ActivityCfgDAO {
    /**
     * Gets activity cfg params.
     *
     * @param activityId the activity id
     * @param countryId  the country id
     * @param dateRef    the date ref
     * @param paramField the param field
     * @return the activity cfg params
     * @throws QaobeeException the qaobee exception
     */
    JsonArray getActivityCfgParams(String activityId, String countryId, Long dateRef, String paramField) throws QaobeeException;

    /**
     * Gets activity cfg.
     *
     * @param activityId the activity id
     * @param countryId  the country id
     * @param dateRef    the date ref
     * @return the activity cfg
     * @throws QaobeeException the qaobee exception
     */
    JsonObject getActivityCfg(String activityId, String countryId, Long dateRef) throws QaobeeException;
}
