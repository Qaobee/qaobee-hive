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

package com.qaobee.hive.dao.impl;

import com.mongodb.async.client.gridfs.AsyncInputStream;
import com.mongodb.async.client.gridfs.GridFSBucket;
import com.mongodb.async.client.gridfs.GridFSBuckets;
import com.mongodb.async.client.gridfs.GridFSDownloadStream;
import com.mongodb.async.client.gridfs.helpers.AsyncStreamHelper;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import com.qaobee.hive.dao.AssetDAO;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.CriteriaBuilder;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.tools.Messages;
import com.qaobee.hive.technical.utils.guice.MongoClientCustom;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import org.apache.commons.io.FileUtils;
import org.apache.http.protocol.HTTP;
import org.bson.Document;
import org.jdeferred.Deferred;
import org.jdeferred.Promise;
import org.jdeferred.impl.DeferredObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;


/**
 * The type Asset dao.
 */
public class AssetDAOImpl implements AssetDAO {
    private static final Logger LOG = LoggerFactory.getLogger(AssetDAOImpl.class);
    private static final String COLLECTION = "User";
    private static final String UID_FIELD = "uid";
    private static final String MESS_NOT_FOUND = "Not found";
    @Inject
    private MongoDB mongo;
    @Inject
    private Vertx vertx;
    @Inject
    private MongoClientCustom mongoClient;
    @Inject
    @Named("mongo.db")
    private JsonObject dbConfig;


    @Override
    public Promise<JsonObject, QaobeeException, Integer> addAsset(String userId, String token, String filename, String collection, String field, String contentType, String locale) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        mongo.findByCriterias(new CriteriaBuilder().add("account.token", token).get(), null, null, 0, 0, COLLECTION).done(
                res -> {
                    if (res.size() != 1) {
                        vertx.fileSystem().deleteBlocking(filename);
                        deferred.reject(new QaobeeException(ExceptionCodes.NOT_LOGGED, Messages.getString("not.logged", locale)));
                    }
                    // Create some custom options
                    GridFSUploadOptions options = new GridFSUploadOptions()
                            .chunkSizeBytes(1024)
                            .metadata(new Document("type", contentType).append(UID_FIELD, userId));

                    try {
                        GridFSBucket gridFSBucket = GridFSBuckets.create(mongoClient.getDB().getDatabase(dbConfig.getString("db_name")), DBCollections.ASSETS);
                        final AsyncInputStream streamToUploadFrom = AsyncStreamHelper.toAsyncInputStream(FileUtils.readFileToByteArray(new File(filename)));

                        gridFSBucket.uploadFromStream(userId, streamToUploadFrom, options, (result, e) -> {
                            streamToUploadFrom.close((closeRes, er) -> {
                                if (er != null) {
                                    LOG.error(er.getMessage(), er);
                                    deferred.reject(new QaobeeException(ExceptionCodes.INTERNAL_ERROR, er.getMessage()));
                                }
                            });
                            if (e != null) {
                                LOG.error(e.getMessage(), e);
                                deferred.reject(new QaobeeException(ExceptionCodes.INTERNAL_ERROR, e.getMessage()));
                            } else {
                                JsonObject personToSave = new JsonObject()
                                        .put("_id", userId)
                                        .put(field, result.toHexString());
                                if ("SB_Person".equals(collection)) {
                                    mongo.getById(userId, DBCollections.PERSON);
                                    mongo.upsert(personToSave, DBCollections.PERSON);
                                } else {
                                    mongo.getById(userId, DBCollections.USER);
                                    mongo.upsert(personToSave, DBCollections.USER);
                                }
                                if (vertx.fileSystem().existsBlocking(filename)) {
                                    vertx.fileSystem().deleteBlocking(filename);
                                }
                                deferred.resolve(personToSave);
                            }
                        });
                    } catch (IOException e) {
                        LOG.error(e.getMessage(), e);
                        deferred.reject(new QaobeeException(ExceptionCodes.INTERNAL_ERROR, e.getMessage()));
                    }

                }
        ).fail(deferred::reject);
        return deferred.promise();
    }

    @Override
    public Promise<JsonObject, QaobeeException, Integer> getAsset(String collection, String id) {
        Deferred<JsonObject, QaobeeException, Integer> deferred = new DeferredObject<>();
        GridFSBucket gridFSBucket = GridFSBuckets.create(mongoClient.getDB().getDatabase(dbConfig.getString("db_name")), DBCollections.ASSETS);
        if (DBCollections.PERSON.equals(collection) || DBCollections.USER.equals(collection)) {
            final ByteBuffer dstByteBuffer = ByteBuffer.allocate(1024 * 1024);
            final GridFSDownloadStream downloadStream = gridFSBucket.openDownloadStream(id);
            downloadStream.read(dstByteBuffer, (result, e) -> {
                if (e != null) {
                    LOG.error(e.getMessage(), e);
                    deferred.reject(new QaobeeException(ExceptionCodes.INVALID_PARAMETER, MESS_NOT_FOUND));
                } else {
                    dstByteBuffer.flip();
                    byte[] bytes = new byte[result];
                    dstByteBuffer.get(bytes);
                    downloadStream.close((result1, er) -> {
                        if (er != null) {
                            LOG.error(er.getMessage(), er);
                            deferred.reject(new QaobeeException(ExceptionCodes.INVALID_PARAMETER, MESS_NOT_FOUND));
                        }
                    });
                    deferred.resolve(new JsonObject()
                            .put(HTTP.CONTENT_LEN, Integer.toString(bytes.length))
                            .put("asset", bytes));
                }
            });
        } else {
            deferred.reject(new QaobeeException(ExceptionCodes.INVALID_PARAMETER, MESS_NOT_FOUND));
        }
        return deferred.promise();
    }
}
