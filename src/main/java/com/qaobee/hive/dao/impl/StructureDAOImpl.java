package com.qaobee.hive.dao.impl;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.qaobee.hive.business.commons.settings.CountryBusiness;
import com.qaobee.hive.business.model.commons.referencial.Structure;
import com.qaobee.hive.business.model.commons.settings.Country;
import com.qaobee.hive.dao.StructureDAO;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

public class StructureDAOImpl implements StructureDAO {

    @Inject
    private MongoDB mongo;
    @Inject
    private CountryBusiness countryBusiness;

    @Override
    public JsonObject update(JsonObject structure) throws QaobeeException {
        mongo.save(structure, Structure.class);
        return structure;
    }

    @Override
    public JsonArray getListOfStructures(String activity, JsonObject address) throws QaobeeException {
        Country country = countryBusiness.getCountryFromAlpha2(address.getString("countryAlpha2", "FR"));
        if (country == null) {
            throw new QaobeeException(ExceptionCodes.DATA_ERROR, "No Country defined for (" + address.getString("countryAlpha2") + ")");
        }
        // Aggregat section
        DBObject match;
        BasicDBObject dbObjectParent;
        // $MACTH section
        dbObjectParent = new BasicDBObject();
        // Activity ID
        dbObjectParent.put("activity._id", activity);
        // Country ID
        dbObjectParent.put("country._id", country.get_id());
        // City OR Zipcode
        BasicDBList dbList = new BasicDBList();
        dbList.add(new BasicDBObject("address.city", address.getString("city").toUpperCase()));
        dbList.add(new BasicDBObject("address.zipcode", address.getString("zipcode")));
        dbObjectParent.put("$or", dbList.toArray());
        match = new BasicDBObject("$match", dbObjectParent);
        // Pipeline
        List<DBObject> pipelineAggregation = Collections.singletonList(match);
       return mongo.aggregate("_id", pipelineAggregation, Structure.class);
    }

    @Override
    public JsonObject getStructure(String id) throws QaobeeException {
        return mongo.getById(id, Structure.class);
    }

    @Override
    public JsonObject addStructure(JsonObject structure) throws QaobeeException {
        final String id = mongo.save(structure, Structure.class);
        structure.putString("_id", id);
        return structure;
    }
}
