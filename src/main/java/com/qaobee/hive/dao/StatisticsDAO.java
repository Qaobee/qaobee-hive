/*
 *   __________________
 *    Qaobee
 *    __________________
 *
 *    Copyright (c) 2015.  Qaobee
 *    All Rights Reserved.
 *
 *    NOTICE: All information contained here is, and remains
 *    the property of Qaobee and its suppliers,
 *    if any. The intellectual and technical concepts contained
 *    here are proprietary to Qaobee and its suppliers and may
 *    be covered by U.S. and Foreign Patents, patents in process,
 *    and are protected by trade secret or copyright law.
 *    Dissemination of this information or reproduction of this material
 *    is strictly forbidden unless prior written permission is obtained
 *    from Qaobee.
 */

package com.qaobee.hive.dao;


import com.qaobee.hive.technical.exceptions.QaobeeException;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
 * The interface Statistics dao.
 */
public interface StatisticsDAO {
    /**
     * Add bulk json object.
     *
     * @param stats the stats
     * @return the json object
     */
    JsonObject addBulk(JsonArray stats);

    /**
     * Gets list for event.
     *
     * @param eventId the event id
     * @return the list for event
     */
    JsonArray getListForEvent(String eventId);

    /**
     * Add stat json object.
     *
     * @param stat the stat
     * @return the json object
     * @throws QaobeeException the qaobee exception
     */
    JsonObject addStat(JsonObject stat) throws QaobeeException;

    /**
     * Gets list detail value.
     *
     * @param listIndicators the list indicators
     * @param listOwners     the list owners
     * @param startDate      the start date
     * @param endDate        the end date
     * @param values         the values
     * @param limit          the limit
     * @return the list detail value
     * @throws QaobeeException the qaobee exception
     */
    JsonArray getListDetailValue(JsonArray listIndicators, JsonArray listOwners, Long startDate, Long endDate, JsonArray values, int limit) throws QaobeeException;

    /**
     * Gets stats grouped by.
     *
     * @param listIndicators the list indicators
     * @param listOwners     the list owners
     * @param startDate      the start date
     * @param endDate        the end date
     * @param aggregate      the aggregate
     * @param value          the value
     * @param shootSeqId     the shoot seq id
     * @param groupBy        the group by
     * @param sortedBy       the sorted by
     * @param limit          the limit
     * @return the stats grouped by
     * @throws QaobeeException the qaobee exception
     */
    JsonArray getStatsGroupedBy(JsonArray listIndicators, JsonArray listOwners, Long startDate, // NOSONAR
                                Long endDate, String aggregate, JsonArray value, JsonArray shootSeqId,
                                JsonArray groupBy, JsonArray sortedBy, Integer limit) throws QaobeeException;
}
