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
package com.qaobee.hive.technical.mongo;

import io.vertx.core.json.JsonObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * The Class CriteriaBuilder.
 *
 * @author xavier
 */
public class CriteriaBuilder {

    /**
     * The map.
     */
    private final Map<String, Object> map = new HashMap<>();

    /**
     * Adds the a search criteria.
     *
     * @param key   search key
     * @param value searched value
     * @return a criteria
     */
    public CriteriaBuilder add(final String key, final Object value) {
        map.put(key, value);
        return this;
    }

    /**
     * Adds a criteria with regexp condition.
     *
     * @param key   (String) : search key
     * @param value (String) : searched value
     * @return CriteriaBuilder : a criteria
     */
    public CriteriaBuilder addRegExp(final String key, final String value) {
        JsonObject regexp = new JsonObject().put("$regex", value);
        regexp.put("$options", "i");
        map.put(key, regexp);
        return this;
    }

    /**
     * Gets the map.
     *
     * @return a criteria map
     */
    public Map<String, Object> get() {
        return map;
    }

    /**
     * Between criteria builder.
     *
     * @param floor key of the min
     * @param ceil  key of the max
     * @param value value to test
     * @return a criteria
     */
    public CriteriaBuilder between(final String floor, final String ceil, long value) {
        add(floor, new JsonObject().put("$lt", value));
        add(ceil, new JsonObject().put("$gt", value));
        return this;
    }

    /**
     * Creates a "$in" criteria.
     *
     * @param key    (String) : field to apply the criteria
     * @param values (String...) : values desired
     * @return CriteriaBuilder criteria builder
     */
    public CriteriaBuilder in(final String key, final String... values) {
        add(key, new JsonObject().put("$in", Arrays.asList(values)));
        return this;
    }

}
