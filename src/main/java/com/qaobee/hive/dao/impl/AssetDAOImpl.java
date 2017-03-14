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

import com.mongodb.BasicDBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import com.qaobee.hive.dao.AssetDAO;
import com.qaobee.hive.technical.constantes.DBCollections;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.CriteriaBuilder;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.tools.Messages;
import io.netty.handler.codec.http.HttpHeaders;
import org.apache.commons.io.FileUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import javax.inject.Inject;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;


/**
 * The type Asset dao.
 */
public class AssetDAOImpl implements AssetDAO {
    private static final Logger LOG = LoggerFactory.getLogger(AssetDAOImpl.class);
    private static final String COLLECTION = "User";
    private static final String UID_FIELD = "uid";
    private static final String MESS_NOT_FOUND = "Not found";
    private final MongoDB mongo;
    private final Vertx vertx;

    /**
     * Instantiates a new Asset dao.
     *
     * @param mongo the mongo
     * @param vertx the vertx
     */
    @Inject
    public AssetDAOImpl(MongoDB mongo, Vertx vertx) {
        this.mongo = mongo;
        this.vertx = vertx;
    }

    @Override
    public JsonObject addAsset(String userId, String token, String filename, String collection, String field, String contentType, String locale) throws QaobeeException {
        final JsonArray res = mongo.findByCriterias(new CriteriaBuilder().add("account.token", token).get(), null, null, 0, 0, COLLECTION);
        if (res.size() != 1) {
            FileUtils.deleteQuietly(new File(filename));
            throw new QaobeeException(ExceptionCodes.NOT_LOGGED, Messages.getString("not.logged", locale));
        }
        GridFS img = new GridFS(mongo.getDb(), DBCollections.ASSETS);
        GridFSInputFile gfsFile;
        try {
            gfsFile = img.createFile(FileUtils.readFileToByteArray(new File(filename)));

            gfsFile.setFilename(userId);
            BasicDBObject meta = new BasicDBObject();
            meta.append(UID_FIELD, userId);
            gfsFile.setMetaData(meta);
            gfsFile.setContentType(contentType);
            gfsFile.save();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            throw new QaobeeException(ExceptionCodes.INTERNAL_ERROR, e.getMessage());
        }
        JsonObject personToSave = new JsonObject()
                .putString("_id", userId)
                .putString(field, gfsFile.getId().toString());
        if ("SB_Person".equals(collection)) {
            mongo.getById(userId, DBCollections.PERSON);
            mongo.update(personToSave, DBCollections.PERSON);
        } else {
            mongo.getById(userId, DBCollections.USER);
            mongo.update(personToSave, DBCollections.USER);
        }
        if (vertx.fileSystem().existsSync(filename)) {
            vertx.fileSystem().deleteSync(filename);
        }
        return personToSave;
    }

    @Override
    public JsonObject getAsset(String collection, String id) throws QaobeeException {
        GridFS img = new GridFS(mongo.getDb(), DBCollections.ASSETS);
        try {
            if (DBCollections.PERSON.equals(collection) || DBCollections.USER.equals(collection)) {
                GridFSDBFile imageForOutput = img.findOne(new ObjectId(id));
                if (imageForOutput != null && imageForOutput.getChunkSize() > 0) {
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        imageForOutput.writeTo(bos);
                    byte[] asset = bos.toByteArray();
                    return new JsonObject()
                            .putString(HttpHeaders.Names.CONTENT_LENGTH, Integer.toString(asset.length))
                            .putBinary("asset", asset);
                } else {
                    throw new QaobeeException(ExceptionCodes.INVALID_PARAMETER, MESS_NOT_FOUND);
                }
            } else {
                throw new QaobeeException(ExceptionCodes.INVALID_PARAMETER, MESS_NOT_FOUND);
            }
        }catch(IllegalArgumentException e) {
            LOG.error(e.getMessage(), e);
            throw new QaobeeException(ExceptionCodes.INVALID_PARAMETER, MESS_NOT_FOUND);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            throw new QaobeeException(ExceptionCodes.INTERNAL_ERROR, e.getMessage());
        }
    }
}
