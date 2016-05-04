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

package com.qaobee.hive.api.v1.commons.utils;

import com.mongodb.BasicDBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.business.model.sandbox.effective.SB_Person;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.constantes.Constantes;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.CriteriaBuilder;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.tools.Messages;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import io.netty.handler.codec.http.HttpHeaders;
import org.apache.commons.io.FileUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import javax.inject.Inject;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;

/**
 * The type Asset verticle.
 */
@DeployableVerticle
public class AssetVerticle extends AbstractGuiceVerticle {
    public static final String ADD = "asset.add";
    public static final String GET = "asset.get";
    private static final Logger LOG = LoggerFactory.getLogger(AssetVerticle.class);
    private static final String COLLECTION_FIELD = "collection";
    private static final String FILENAME_FIELD = "filename";
    private static final String UID_FIELD = "uid";
    @Inject
    private MongoDB mongo;
    @Inject
    private Utils utils;

    /**
     * Start void.
     */
    @Override
    public void start() {
        super.start();
        LOG.debug(this.getClass().getName() + " started");
        /**
         * Add an asset
         *
         * <pre>{
         *  uid User id
         *  token
         *  filename
         *  collection
         *  field
         *  contentType
         *  }</pre>
         */
        vertx.eventBus().registerHandler(ADD, this::addAssetHandler);
        /**
         * Get an asset
         *
         * <pre>{
         *  id
         *  collection
         *  }</pre>
         */
        vertx.eventBus().registerHandler(GET, this::getAssetHandler);
    }

    private void getAssetHandler(Message<JsonObject> message) {
        JsonObject resp = new JsonObject();
        try {
            utils.testMandatoryParams(message.body().toMap(), COLLECTION_FIELD, "id");
            GridFS img = new GridFS(mongo.getDb(), "Assets");

            if ("SB_Person".equals(message.body().getString(COLLECTION_FIELD)) || "User".equals(message.body().getString(COLLECTION_FIELD))) {
                GridFSDBFile imageForOutput = img.findOne(new ObjectId(message.body().getString("id")));
                if (imageForOutput != null && imageForOutput.getChunkSize() > 0) {
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    imageForOutput.writeTo(bos);
                    byte[] asset = bos.toByteArray();
                    resp.putString(HttpHeaders.Names.CONTENT_LENGTH, Integer.toString(asset.length));
                    resp.putBinary("asset", asset);
                } else {
                    byte[] asset = FileUtils.readFileToByteArray(new File("web/user.png"));
                    resp.putString(HttpHeaders.Names.CONTENT_LENGTH, Integer.toString(asset.length));
                    resp.putBinary("asset", asset);
                }
                message.reply(resp);
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            resp.putNumber(Constantes.STATUS_CODE, ExceptionCodes.INTERNAL_ERROR.getCode());
            resp.putString(Constantes.MESSAGE, e.getMessage());
            message.reply(resp);
        } catch (final IllegalArgumentException e) {
            LOG.error(e.getMessage(), e);
            resp.putNumber(Constantes.STATUS_CODE, ExceptionCodes.INVALID_PARAMETER.getCode());
            resp.putString(Constantes.MESSAGE, e.getMessage());
            message.reply(resp);
        }
    }

    private void addAssetHandler(Message<JsonObject> message) {
        JsonObject resp = new JsonObject();
        try {
            utils.testMandatoryParams(message.body().toMap(), UID_FIELD, AbstractGuiceVerticle.TOKEN, FILENAME_FIELD, COLLECTION_FIELD, "field", "contentType");
            final JsonArray res = mongo.findByCriterias(new CriteriaBuilder().add("account.token", message.body().getString(AbstractGuiceVerticle.TOKEN)).get(), null, null, 0, 0, User.class);

            if (res.size() != 1) {
                FileUtils.deleteQuietly(new File(message.body().getString(FILENAME_FIELD)));
                resp.putNumber(Constantes.STATUS_CODE, ExceptionCodes.NOT_LOGGED.getCode()).putString(Constantes.MESSAGE, Messages.getString("not.logged", message.body().getString("locale")));
            } else {
                GridFS img = new GridFS(mongo.getDb(), "Assets");
                GridFSInputFile gfsFile = img.createFile(FileUtils.readFileToByteArray(new File(message.body().getString(FILENAME_FIELD))));
                gfsFile.setFilename(message.body().getString(UID_FIELD));
                BasicDBObject meta = new BasicDBObject();
                meta.append(UID_FIELD, message.body().getString(UID_FIELD));
                gfsFile.setMetaData(meta);
                gfsFile.setContentType(message.body().getString(CONTENT_TYPE));
                gfsFile.save();
                JsonObject personToSave = new JsonObject()
                        .putString("_id", message.body().getString(UID_FIELD))
                        .putString(message.body().getString("field"), gfsFile.getId().toString());
                if ("SB_Person".equals(message.body().getString(COLLECTION_FIELD))) {
                    mongo.getById(message.body().getString(UID_FIELD), SB_Person.class);
                    mongo.update(personToSave, SB_Person.class);
                } else {
                    mongo.getById(message.body().getString(UID_FIELD), User.class);
                    mongo.update(personToSave, User.class);
                }

                resp.putNumber(Constantes.STATUS_CODE, 200);
                resp.putString(Constantes.MESSAGE, personToSave.encode());
                if (vertx.fileSystem().existsSync(message.body().getString(FILENAME_FIELD))) {
                    vertx.fileSystem().deleteSync(message.body().getString(FILENAME_FIELD));
                }
                message.reply(resp);
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            resp.putNumber(Constantes.STATUS_CODE, ExceptionCodes.INTERNAL_ERROR.getCode());
            resp.putString(Constantes.MESSAGE, e.getMessage());
            if (vertx.fileSystem().existsSync(message.body().getString(FILENAME_FIELD))) {
                vertx.fileSystem().deleteSync(message.body().getString(FILENAME_FIELD));
            }
            message.reply(resp);
        } catch (final IllegalArgumentException e) {
            LOG.error(e.getMessage(), e);
            resp.putNumber(Constantes.STATUS_CODE, ExceptionCodes.INVALID_PARAMETER.getCode());
            resp.putString(Constantes.MESSAGE, e.getMessage());
            if (vertx.fileSystem().existsSync(message.body().getString(FILENAME_FIELD))) {
                vertx.fileSystem().deleteSync(message.body().getString(FILENAME_FIELD));
            }
            message.reply(resp);
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            resp.putNumber(Constantes.STATUS_CODE, ExceptionCodes.DATA_ERROR.getCode());
            resp.putString(Constantes.MESSAGE, e.getMessage());
            if (vertx.fileSystem().existsSync(message.body().getString(FILENAME_FIELD))) {
                vertx.fileSystem().deleteSync(message.body().getString(FILENAME_FIELD));
            }
            message.reply(resp);
        }
    }
}
