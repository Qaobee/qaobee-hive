package com.qaobee.hive.technical.mongo;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.List;

/**
 * The type Criteria option.
 */
@DataObject
public class CriteriaOption {
    /**
     * The Options.
     */
    JsonObject options = new JsonObject();

    /**
     * Instantiates a new Criteria option.
     */
    public CriteriaOption() {
        // empty
    }

    /**
     * Instantiates a new Criteria option.
     *
     * @param options the options
     */
    public CriteriaOption(JsonObject options) {
        this.options = options;
    }

    /**
     * With sort criteria option.
     *
     * @param sort the sort
     * @return the criteria option
     */
    public CriteriaOption withSort(String sort) {
        options.put("sort", sort);
        return this;
    }

    /**
     * With fields criteria option.
     *
     * @param fields the fields
     * @return the criteria option
     */
    public CriteriaOption withFields(List<String> fields) {
        options.put("fields", new JsonArray(fields));
        return this;
    }

    /**
     * With order criteria option.
     *
     * @param order the order
     * @return the criteria option
     */
    public CriteriaOption withOrder(int order) {
        options.put("order", order);
        return this;
    }

    /**
     * With limit criteria option.
     *
     * @param limit the limit
     * @return the criteria option
     */
    public CriteriaOption withLimit(int limit) {
        options.put("limit", limit);
        return this;
    }

    /**
     * To json json object.
     *
     * @return the json object
     */
    public JsonObject toJson() {
        return options;
    }
}
