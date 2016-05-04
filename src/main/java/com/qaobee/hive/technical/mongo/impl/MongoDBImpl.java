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
package com.qaobee.hive.technical.mongo.impl;

import com.mongodb.*;
import com.mongodb.util.JSONSerializers;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import javax.net.ssl.SSLSocketFactory;
import java.net.UnknownHostException;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * The Class MongoDB.
 *
 * @author xavier
 */
public class MongoDBImpl implements MongoDB {

    protected static final Logger LOG = Logger.getLogger(MongoDBImpl.class.getName());

    private DB db;
    private JsonObject config;
    private WriteConcern writeConcern;

    /**
     * Instantiates a new Mongo dB impl.
     *
     * @param config the config
     * @throws UnknownHostException the unknown host exception
     */
    public MongoDBImpl(JsonObject config) throws UnknownHostException {
        this.config = config;
        String host = getOptionalStringConfig("host", "localhost");
        writeConcern = WriteConcern.valueOf(getOptionalStringConfig("writeConcern", ""));
        final int port = getOptionalIntConfig("port", 27017);
        final String dbName = getOptionalStringConfig("db_name", "default_db");
        final String username = getOptionalStringConfig("username", null);
        final String password = getOptionalStringConfig("password", null); // NOSONAR
        final ReadPreference readPreference = ReadPreference.valueOf(getOptionalStringConfig("read_preference", "primary"));
        final int poolSize = getOptionalIntConfig("pool_size", 10);
        final int socketTimeout = getOptionalIntConfig("socket_timeout", 60000);
        final boolean useSSL = getOptionalBooleanConfig("use_ssl", false);
        final JsonArray seedsProperty = config.getArray("seeds");
        final MongoClientOptions.Builder builder = new MongoClientOptions.Builder()
                .connectionsPerHost(poolSize)
                .socketTimeout(socketTimeout)
                .readPreference(readPreference);
        if (useSSL) {
            builder.socketFactory(SSLSocketFactory.getDefault());
        }
        MongoClient mongo;
        if (seedsProperty == null) {
            final ServerAddress address = new ServerAddress(host, port);
            if (username != null && password != null) {
                final MongoCredential credential = MongoCredential.createMongoCRCredential(username, dbName, password.toCharArray());
                mongo = new MongoClient(address, Collections.singletonList(credential), builder.build());
            } else {
                mongo = new MongoClient(address, builder.build());
            }
        } else {
            final List<ServerAddress> seeds = makeSeeds(seedsProperty);
            if (username != null && password != null) {
                final MongoCredential credential = MongoCredential.createMongoCRCredential(username, dbName, password.toCharArray());
                mongo = new MongoClient(seeds, Collections.singletonList(credential), builder.build());
            } else {
                mongo = new MongoClient(seeds, builder.build());
            }

        }

        db = mongo.getDB(dbName);
    }

    /**
     * Gets the optional boolean config.
     *
     * @param fieldName the field name
     * @param defValue  the def value
     * @return the optional boolean config
     */
    private boolean getOptionalBooleanConfig(final String fieldName, final boolean defValue) {
        return config.containsField(fieldName) ? config.getBoolean(fieldName) : defValue;
    }

    /**
     * Gets the optional int config.
     *
     * @param fieldName the field name
     * @param defValue  the def value
     * @return the optional int config
     */
    private int getOptionalIntConfig(final String fieldName, final int defValue) {
        return config.containsField(fieldName) ? config.getInteger(fieldName) : defValue;
    }

    /**
     * Gets the optional string config.
     *
     * @param fieldName the field name
     * @param defValue  the def value
     * @return the optional string config
     */
    private String getOptionalStringConfig(final String fieldName, final String defValue) {
        return config.containsField(fieldName) ? config.getString(fieldName) : defValue;
    }

    /**
     * Make seeds.
     *
     * @param seedsProperty the seeds property
     * @return the list
     * @throws UnknownHostException the unknown host exception
     */
    private List<ServerAddress> makeSeeds(final JsonArray seedsProperty) throws UnknownHostException {
        final List<ServerAddress> seeds = new ArrayList<>();
        for (final Object elem : seedsProperty) {
            final JsonObject address = (JsonObject) elem;
            final String host = address.getString("host");
            final int port = address.getInteger("port");
            seeds.add(new ServerAddress(host, port));
        }
        return seeds;
    }

    /**
     * Gets the write concern.
     *
     * @return the writeConcern
     */
    private WriteConcern getWriteConcern() {
        if (writeConcern == null) {
            writeConcern = db.getWriteConcern();
        }
        return writeConcern;
    }

    @Override
    public String save(final Object o) throws QaobeeException {
        return save(new JsonObject(Json.encode(o)), o.getClass());
    }

    @Override
    public String update(final JsonObject document, final Class<?> collection) {
        return update(document, collection.getSimpleName());
    }

    @Override
    public String update(final JsonObject document, final String collection) {
        final DBCollection coll = db.getCollection(collection);
        JsonObject q = new JsonObject().putString("_id", document.getString("_id"));
        document.removeField("_id");
        JsonObject set = new JsonObject().putObject("$set", document);
        coll.update(new BasicDBObject(q.toMap()), new BasicDBObject(set.toMap()));
        return q.getString("_id");
    }

    @Override
    public String update(JsonObject query, JsonObject set, Class<?> collection) {
        final DBCollection coll = db.getCollection(collection.getSimpleName());
        WriteResult res = coll.update(new BasicDBObject(query.toMap()), new BasicDBObject(set.toMap()));
        return (String) res.getUpsertedId();
    }

    @Override
    public String save(final JsonObject document, final Class<?> collection) throws QaobeeException {
        final DBCollection coll = db.getCollection(collection.getSimpleName());
        String genID;
        if (document.getField("_id") == null) {
            genID = UUID.randomUUID().toString();
            document.putString("_id", genID);
        } else {
            genID = document.getField("_id");
        }
        final DBObject obj = new BasicDBObject(document.toMap());
        final WriteResult res = coll.save(obj, getWriteConcern());
        return getUpsertedId(res, genID, document);
    }

    private String getUpsertedId(WriteResult res, String genID, JsonObject document) throws QaobeeException {
        if (res.getN() > 0) {
            if (genID != null) {
                return genID;
            } else {
                return (String) res.getUpsertedId();
            }
        } else {
            throw new QaobeeException(ExceptionCodes.DATA_ERROR, "Can't save " + document.encode());
        }
    }

    @Override
    public String save(final JsonObject document, String collection) throws QaobeeException {
        final DBCollection coll = db.getCollection(collection);
        String genID;
        if (document.getField("_id") == null) {
            genID = UUID.randomUUID().toString();
            document.putString("_id", genID);
        } else {
            genID = document.getField("_id");
        }
        final DBObject obj = new BasicDBObject(document.toMap());
        final WriteResult res = coll.save(obj, getWriteConcern());
        return getUpsertedId(res, genID, document);
    }

    @Override
    @SuppressWarnings("unchecked")
    public JsonObject getById(final String id, final Class<?> collection) throws QaobeeException {
        final DBCursor res = db.getCollection(collection.getSimpleName()).find(new BasicDBObject("_id", id));
        if (res.count() != 1) {
            throw new QaobeeException(ExceptionCodes.DATA_ERROR, id + " not found in " + collection.getSimpleName());
        } else {
            return new JsonObject(res.next().toMap());
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public JsonObject getById(final String id, final String collection) throws QaobeeException {
        final DBCursor res = db.getCollection(collection).find(new BasicDBObject("_id", id));
        if (res.count() != 1) {
            throw new QaobeeException(ExceptionCodes.DATA_ERROR, id + " not found in " + collection);
        } else {
            return new JsonObject(res.next().toMap());
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public JsonObject getById(final String id, final Class<?> collection, final List<String> minimal) throws QaobeeException {
        final DBCursor res = db.getCollection(collection.getSimpleName()).find(new BasicDBObject("_id", id), new BasicDBObject(getMinimal(minimal)));
        if (res.count() != 1) {
            throw new QaobeeException(ExceptionCodes.DATA_ERROR, id + " not found in " + collection);
        } else {
            return new JsonObject(res.next().toMap());
        }
    }

    @Override
    public Map<String, Boolean> getMinimal(final List<String> minimal) {
        final Map<String, Boolean> map = new HashMap<>();
        for (final String key : minimal) {
            map.put(key, Boolean.TRUE);
        }
        return map;
    }

    @Override
    public void deleteById(final String id, final Class<?> collection) {
        final DBCollection coll = db.getCollection(collection.getSimpleName());
        coll.remove(new BasicDBObject("_id", id));
    }

    @Override
    @SuppressWarnings("unchecked")
    public JsonArray findByCriterias(final Map<String, Object> criteria, final List<String> fields, final String sort, final int order, final int limit,
                                     final Class<?> collection) {
        final DBCollection coll = db.getCollection(collection.getSimpleName());
        DBObject query = new BasicDBObject();
        if (criteria != null) {
            final BasicDBList and = new BasicDBList();
            for (String k : criteria.keySet()) {
                if (criteria.get(k) instanceof String && ((String) criteria.get(k)).startsWith("//")) {
                    and.add(new BasicDBObject(k, Pattern.compile(((String) criteria.get(k)).substring(2))));
                } else {
                    and.add(new BasicDBObject(k, criteria.get(k)));
                }
            }
            query = new BasicDBObject("$and", and);
        }

        DBCursor res;
        if (fields != null) {
            res = coll.find(query, new BasicDBObject(getMinimal(fields)));
        } else {
            res = coll.find(query);
        }
        if (limit > 0) {
            res = res.limit(limit);
        }
        if (sort != null) {
            res = res.sort(new BasicDBObject(sort, order));
        }
        final JsonArray json = new JsonArray();
        while (res.hasNext()) {
            json.add(new JsonObject(res.next().toMap()));
        }
        return json;
    }

    @Override
    public JsonArray findAll(List<String> fields, String sort, int order, int limit, Class<?> collection) {
        return findByCriterias(null, fields, sort, order, limit, collection);
    }

    @Override
    @SuppressWarnings("unchecked")
    public JsonArray findByInClause(List<String> in, String sort, int order, int limit, Class<?> collection) {
        final DBCollection coll = db.getCollection(collection.getSimpleName());
        final BasicDBList docIds = new BasicDBList();
        docIds.addAll(in);
        final DBObject inClause = new BasicDBObject("$in", docIds);
        DBObject query = new BasicDBObject("$in", inClause);
        DBCursor res = coll.find(query);
        if (limit > 0) {
            res = res.limit(limit);
        }
        if (sort != null) {
            res = res.sort(new BasicDBObject(sort, order));
        }
        final JsonArray json = new JsonArray();
        while (res.hasNext()) {
            json.add(new JsonObject(res.next().toMap()));
        }
        return json;
    }

    @Override
    @SuppressWarnings("unchecked")
    public JsonArray aggregate(String field, List<DBObject> pipeline, Class<?> collection) throws QaobeeException {
        return aggregate(field, pipeline, collection.getSimpleName());
    }

    @Override
    @SuppressWarnings("unchecked")
    public JsonArray aggregate(String field, List<DBObject> pipeline, String collection) throws QaobeeException {
        JsonArray res = new JsonArray();
        for (DBObject next : db.getCollection(collection).aggregate(pipeline).results()) {
            if (next instanceof BasicDBList) {
                ListIterator<Object> it = ((BasicDBList) next).listIterator();
                JsonArray jar = new JsonArray();
                while (it.hasNext()) {
                    jar.add(convertBsonToJson((DBObject) it.next()));
                }
                res.addArray(jar);
            } else {
                res.addObject(convertBsonToJson(next));
            }
        }
        return res;
    }

    @Override
    public DB getDb() {
        return db;
    }

    private static JsonObject convertBsonToJson(DBObject dbObject) throws QaobeeException {
        if (dbObject == null) {
            throw new QaobeeException(ExceptionCodes.JSON_EXCEPTION, "Cannot convert null to JsonObject");
        }
        // Create JSON string from DBObject
        String serialize = JSONSerializers.getStrict().serialize(dbObject);
        // Convert to JsonObject
        HashMap<String, Object> jsonMap = Json.decodeValue(serialize, HashMap.class);
        return new JsonObject(jsonMap);
    }

}
