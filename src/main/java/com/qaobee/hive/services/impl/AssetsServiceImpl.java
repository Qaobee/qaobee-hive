package com.qaobee.hive.services.impl;

import com.mongodb.async.client.gridfs.AsyncInputStream;
import com.mongodb.async.client.gridfs.GridFSBucket;
import com.mongodb.async.client.gridfs.GridFSBuckets;
import com.mongodb.async.client.gridfs.GridFSDownloadStream;
import com.mongodb.async.client.gridfs.helpers.AsyncStreamHelper;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import com.qaobee.hive.services.AssetsService;
import com.qaobee.hive.technical.annotations.ProxyService;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeSvcException;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.utils.guice.MongoClientCustom;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import org.apache.commons.io.FileUtils;
import org.apache.http.protocol.HTTP;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

@ProxyService(address = AssetsService.ADDRESS, iface = AssetsService.class)
public class AssetsServiceImpl implements AssetsService {
    private static final Logger LOG = LoggerFactory.getLogger(AssetsService.class);
    private static final String MESS_NOT_FOUND = "Not found";
    @Inject
    private MongoDB mongo;
    @Inject
    private MongoClientCustom mongoClient;
    @Inject
    @Named("mongo.db")
    private JsonObject dbConfig;

    private Vertx vertx;

    public AssetsServiceImpl(Vertx vertx) {
        super();
        this.vertx = vertx;
    }

    @Override
    public void addAsset(String userId, String filename, String collection, String field, String contentType, String locale, Handler<AsyncResult<JsonObject>> resultHandler) {
        GridFSUploadOptions options = new GridFSUploadOptions()
                .chunkSizeBytes(1024)
                .metadata(new Document("type", contentType).append("uid", userId));

        try {
            GridFSBucket gridFSBucket = GridFSBuckets.create(mongoClient.getDB().getDatabase(dbConfig.getString("db_name")), DBCollections.ASSETS);
            final AsyncInputStream streamToUploadFrom = AsyncStreamHelper.toAsyncInputStream(FileUtils.readFileToByteArray(new File(filename)));

            gridFSBucket.uploadFromStream(userId, streamToUploadFrom, options, (result, e) -> {
                streamToUploadFrom.close((closeRes, er) -> {
                    if (er != null) {
                        LOG.error(er.getMessage(), er);
                        resultHandler.handle(Future.failedFuture(new QaobeeSvcException(ExceptionCodes.INTERNAL_ERROR, er.getMessage())));
                    }
                });
                if (e != null) {
                    LOG.error(e.getMessage(), e);
                    resultHandler.handle(Future.failedFuture(new QaobeeSvcException(ExceptionCodes.INTERNAL_ERROR, e.getMessage())));
                } else {
                    if (DBCollections.PERSON.equals(collection) || DBCollections.USER.equals(collection)) {
                        mongo.getById(userId, collection).done(p -> {
                            p.put(field, result.toHexString());
                            mongo.upsert(p, collection)
                                    .done(r -> resultHandler.handle(Future.succeededFuture(p)))
                                    .fail(ex -> resultHandler.handle(Future.failedFuture(new QaobeeSvcException(ex))));
                        }).fail(ex -> resultHandler.handle(Future.failedFuture(new QaobeeSvcException(ex))));
                    } else {
                        resultHandler.handle(Future.failedFuture(new QaobeeSvcException(ExceptionCodes.INVALID_PARAMETER, MESS_NOT_FOUND)));
                    }
                    if (vertx.fileSystem().existsBlocking(filename)) {
                        vertx.fileSystem().deleteBlocking(filename);
                    }
                }
            });
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            resultHandler.handle(Future.failedFuture(new QaobeeSvcException(ExceptionCodes.INTERNAL_ERROR, e.getMessage())));
        }
    }

    @Override
    public void getAsset(String collection, String id, Handler<AsyncResult<JsonObject>> resultHandler) {
        GridFSBucket gridFSBucket = GridFSBuckets.create(mongoClient.getDB().getDatabase(dbConfig.getString("db_name")), DBCollections.ASSETS);
        if (DBCollections.PERSON.equals(collection) || DBCollections.USER.equals(collection)) {
            final ByteBuffer dstByteBuffer = ByteBuffer.allocate(1024 * 1024);
            try {
                final GridFSDownloadStream downloadStream = gridFSBucket.openDownloadStream(new ObjectId(id));
                downloadStream.read(dstByteBuffer, (result, e) -> {
                    if (e != null) {
                        LOG.error(e.getMessage(), e);
                        resultHandler.handle(Future.failedFuture(new QaobeeSvcException(ExceptionCodes.INVALID_PARAMETER, MESS_NOT_FOUND)));
                    } else {
                        dstByteBuffer.flip();
                        byte[] bytes = new byte[result];
                        dstByteBuffer.get(bytes);
                        downloadStream.close((result1, er) -> {
                            if (er != null) {
                                LOG.error(er.getMessage(), er);
                                resultHandler.handle(Future.failedFuture(new QaobeeSvcException(ExceptionCodes.INVALID_PARAMETER, MESS_NOT_FOUND)));
                            }
                        });
                        resultHandler.handle(Future.succeededFuture(new JsonObject()
                                .put(HTTP.CONTENT_LEN, Integer.toString(bytes.length))
                                .put("asset", bytes)));
                    }
                });
            } catch (IllegalArgumentException e) {
                LOG.error(e.getMessage(), e);
                resultHandler.handle(Future.failedFuture(new QaobeeSvcException(ExceptionCodes.INVALID_PARAMETER, MESS_NOT_FOUND)));
            }
        } else {
            resultHandler.handle(Future.failedFuture(new QaobeeSvcException(ExceptionCodes.INVALID_PARAMETER, MESS_NOT_FOUND)));
        }
    }
}
