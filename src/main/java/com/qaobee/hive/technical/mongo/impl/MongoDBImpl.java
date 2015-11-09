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
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.MongoDB;
import org.vertx.java.core.json.EncodeException;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import javax.net.ssl.SSLSocketFactory;
import java.net.UnknownHostException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

/**
 * The Class MongoDB.
 *
 * @author xavier
 */
public class MongoDBImpl implements MongoDB {

    /**
     * The Constant LOG.
     */
    protected static final Logger LOG = Logger.getLogger(MongoDBImpl.class.getName());

    /**
     * The db.
     */
    private DB db;

    /**
     * The mongo.
     */
    private MongoClient mongo;

    /**
     * The config.
     */
    private JsonObject config;

    /**
     * The write concern.
     */
    private WriteConcern writeConcern;

    /**
     * The mongo initialized.
     */
    private static AtomicBoolean mongoInitialized = new AtomicBoolean(false);

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
        final String password = getOptionalStringConfig("password", null);
        final ReadPreference readPreference = ReadPreference.valueOf(getOptionalStringConfig("read_preference", "primary"));
        final int poolSize = getOptionalIntConfig("pool_size", 10);
        final int socketTimeout = getOptionalIntConfig("socket_timeout", 60000);
        final boolean useSSL = getOptionalBooleanConfig("use_ssl", false);
        final JsonArray seedsProperty = config.getArray("seeds");
        final MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
        builder.connectionsPerHost(poolSize);
        builder.socketTimeout(socketTimeout);
        builder.readPreference(readPreference);
        if (useSSL) {
            builder.socketFactory(SSLSocketFactory.getDefault());
        }
        if (seedsProperty == null) {
            final ServerAddress address = new ServerAddress(host, port);
            if (username != null && password != null) {
                final MongoCredential credential = MongoCredential.createMongoCRCredential(username, dbName, password.toCharArray());
                mongo = new MongoClient(address, Arrays.asList(credential), builder.build());
            } else {
                mongo = new MongoClient(address, builder.build());
            }
        } else {
            final List<ServerAddress> seeds = makeSeeds(seedsProperty);
            if (username != null && password != null) {
                final MongoCredential credential = MongoCredential.createMongoCRCredential(username, dbName, password.toCharArray());
                mongo = new MongoClient(seeds, Arrays.asList(credential), builder.build());
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
    public WriteConcern getWriteConcern() {
        if (writeConcern == null) {
            writeConcern = db.getWriteConcern();
        }
        return writeConcern;
    }

    /**
     * Save string.
     *
     * @param o object to save
     * @return id string
     * @throws EncodeException can't encode
     * @throws EncodeException can't encode
     */
    @Override
    public String save(final Object o) throws EncodeException, QaobeeException {
        return save(new JsonObject(Json.encode(o)), o.getClass());
    }

    /**
     * Update string.
     *
     * @param document   the document
     * @param collection the collection
     * @return the string
     * @throws MongoException the mongo exception
     */
    @Override
    public String update(final JsonObject document, final Class<?> collection) throws MongoException {
        final DBCollection coll = db.getCollection(collection.getSimpleName());
        JsonObject q = new JsonObject();
        JsonObject set = new JsonObject();
        q.putString("_id", document.getString("_id"));
        document.removeField("_id");
        set.putObject("$set", document);
        WriteResult res = coll.update(new BasicDBObject(q.toMap()), new BasicDBObject(set.toMap()));

        return (String) res.getUpsertedId();
    }
    
    /**
     * Update string.
     *
     * @param document   the document
     * @param collection the collection
     * @return the string
     * @throws MongoException the mongo exception
     */
    @Override
    public String update(final JsonObject document, final String collection) throws MongoException {
        final DBCollection coll = db.getCollection(collection);
        JsonObject q = new JsonObject();
        JsonObject set = new JsonObject();
        q.putString("_id", document.getString("_id"));
        document.removeField("_id");
        set.putObject("$set", document);
        WriteResult res = coll.update(new BasicDBObject(q.toMap()), new BasicDBObject(set.toMap()));

        return (String) res.getUpsertedId(); 
         
    }

    /**
     * Update string.
     *
     * @param query      the query
     * @param set        the set
     * @param collection the collection
     * @return the string
     * @throws MongoException the mongo exception
     */
    @Override
    public String update(JsonObject query, JsonObject set, Class<?> collection) throws MongoException {
        final DBCollection coll = db.getCollection(collection.getSimpleName());
        WriteResult res = coll.update(new BasicDBObject(query.toMap()), new BasicDBObject(set.toMap()));
        return (String) res.getUpsertedId();
    }

    /**
     * Saves a document in a colection.
     *
     * @param document   object to save
     * @param collection target
     * @return id string
     * @throws QaobeeException can't save
     * @throws QaobeeException can't save
     */
    public String save(final JsonObject document, final Class<?> collection) throws QaobeeException, MongoException {
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
        if (res.getN() > 0) {
            if (genID != null) {
                return genID;
            } else {
                return (String) res.getUpsertedId();
            }
        } else {
            throw new QaobeeException(ExceptionCodes.MONGO_ERROR, "Can't save " + document.encode());
        }
    }
    
    /**
     * Saves a document in a colection.
     *
     * @param document   object to save
     * @param collection target
     * @return id string
     * @throws QaobeeException can't save
     * @throws QaobeeException can't save
     */
    public String save(final JsonObject document, String collection) throws QaobeeException, MongoException {
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
        if (res.getN() > 0) {
            if (genID != null) {
                return genID;
            } else {
                return (String) res.getUpsertedId();
            }
        } else {
            throw new QaobeeException(ExceptionCodes.MONGO_ERROR, "Can't save " + document.encode());
        }
    }

    /**
     * Get a document by id.
     *
     * @param id         the id
     * @param collection the collection
     * @return the document
     * @throws QaobeeException not found
     */
    @Override
    @SuppressWarnings("unchecked")
    public JsonObject getById(final String id, final Class<?> collection) throws QaobeeException {
        final DBCursor res = db.getCollection(collection.getSimpleName()).find(new BasicDBObject("_id", id));
        if (res.count() != 1) {
            throw new QaobeeException(ExceptionCodes.MONGO_ERROR, id + " not found in " + collection);
        } else {
            return new JsonObject(res.next().toMap());
        }
    }
    
    /**
     * Get a document by id.
     *
     * @param id         the id
     * @param collection the collection
     * @return the document
     * @throws QaobeeException not found
     */
    @Override
    @SuppressWarnings("unchecked")
    public JsonObject getById(final String id, final String collection) throws QaobeeException {
        final DBCursor res = db.getCollection(collection).find(new BasicDBObject("_id", id));
        if (res.count() != 1) {
            throw new QaobeeException(ExceptionCodes.MONGO_ERROR, id + " not found in " + collection);
        } else {
            return new JsonObject(res.next().toMap());
        }
    }

    /**
     * Get a document by id.
     *
     * @param id         the id
     * @param collection the collection
     * @param minimal    fields to retrieve
     * @return the document
     * @throws QaobeeException not found
     */
    @Override
    @SuppressWarnings("unchecked")
    public JsonObject getById(final String id, final Class<?> collection, final List<String> minimal) throws QaobeeException {
        final DBCursor res = db.getCollection(collection.getSimpleName()).find(new BasicDBObject("_id", id), new BasicDBObject(getMinimal(minimal)));
        if (res.count() != 1) {
            throw new QaobeeException(ExceptionCodes.MONGO_ERROR, id + " not found in " + collection);
        } else {
            return new JsonObject(res.next().toMap());
        }
    }

    /**
     * Gets the minimal.
     *
     * @param minimal minimal list of fields to retrieve
     * @return a map
     */
    @Override
    public Map<String, Boolean> getMinimal(final List<String> minimal) {
        final Map<String, Boolean> map = new HashMap<String, Boolean>();
        for (final String key : minimal) {
            map.put(key, Boolean.TRUE);
        }
        return map;
    }

    /**
     * Remove a document from a collection.
     *
     * @param id         document id
     * @param collection the collection
     */
    @Override
    public void deleteById(final String id, final Class<?> collection) {
        final DBCollection coll = db.getCollection(collection.getSimpleName());
        coll.remove(new BasicDBObject("_id", id));
    }

    @Override
    @SuppressWarnings("unchecked")
    public JsonArray findByCriterias(final Map<String, Object> criteria, final List<String> fields, final String sort, final int order, final int limit, final Class<?> collection) {
        final DBCollection coll = db.getCollection(collection.getSimpleName());
        DBObject query = new BasicDBObject();
        if (criteria != null) {
            final BasicDBList and = new BasicDBList();
            for (final String k : criteria.keySet()) {
                if (criteria.get(k) instanceof String) {

                    String param = (String) criteria.get(k);
                    if (param.startsWith("//")) {
                        param = param.substring(2);
                        and.add(new BasicDBObject(k, java.util.regex.Pattern.compile(param)));
                    } else {
                        and.add(new BasicDBObject(k, criteria.get(k)));
                    }
                } else {
                    and.add(new BasicDBObject(k, criteria.get(k)));
                }
            }
            query = new BasicDBObject("$and", and);
        }

        LOG.info("findByCriterias -> Query : " + query.toString());

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

    /**
     * Find all documents with minimal fields and a sort order.
     *
     * @param fields     fields to include
     * @param sort       sort field
     * @param order      sort order
     * @param limit      limit
     * @param collection collection
     * @return an array
     */
    @Override
    public JsonArray findAll(List<String> fields, String sort, int order, int limit, Class<?> collection) {
        return findByCriterias(null, fields, sort, order, limit, collection);
    }

    /**
     * Find all documents by in clause with a sort order.
     *
     * @param in         fields to include
     * @param sort       sort field
     * @param order      sort order
     * @param limit      limit
     * @param collection collection
     * @return an array
     */
    @Override
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

    /**
     * Aggregate json array.
     *
     * @param field      the field
     * @param pipeline   the pipeline
     * @param collection the collection
     * @return the json array
     */
// TODO : JRO : implémente cette partie pour éviter de trimbaler des DBObjects
    @Override
    public JsonArray aggregate(String field, List<DBObject> pipeline, Class<?> collection) {
        JsonArray res = new JsonArray();
        Iterator<DBObject> it = db.getCollection(collection.getSimpleName()).aggregate(pipeline).results().iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (next instanceof BasicDBList) {
                res.addArray(new JsonObject(((BasicDBObject) next).toMap()).asArray());
            } else {
                res.addObject(new JsonObject(((BasicDBObject) next).toMap()));
            }
        }
        return res;
    }
    
    /**
     * Aggregate json array.
     *
     * @param field      the field
     * @param pipeline   the pipeline
     * @param collection the collection
     * @return the json array
     */
// TODO : JRO : implémente cette partie pour éviter de trimbaler des DBObjects
    @Override
    public JsonArray aggregate(String field, List<DBObject> pipeline, String collection) {
        JsonArray res = new JsonArray();
        Iterator<DBObject> it = db.getCollection(collection).aggregate(pipeline).results().iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (next instanceof BasicDBList) {
                res.addArray(new JsonObject(((BasicDBObject) next).toMap()).asArray());
            } else {
                res.addObject(new JsonObject(((BasicDBObject) next).toMap()));
            }
        }
        return res;
    }

    /**
     * Gets db.
     *
     * @return the db
     */
    @Override
    public DB getDb() {
        return db;
    }

}
