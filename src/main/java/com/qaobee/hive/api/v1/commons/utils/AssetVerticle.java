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
import com.qaobee.hive.business.model.sandbox.effective.Person;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.mongo.CriteriaBuilder;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.tools.Messages;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import org.apache.commons.io.FileUtils;
import org.bson.types.ObjectId;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import javax.inject.Inject;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;


/**
 * The type Asset verticle.
 */
@DeployableVerticle
public class AssetVerticle extends AbstractGuiceVerticle {
    /**
     * The Constant ADD.
     */
    public static final String ADD = "asset.add";
    /**
     * The constant GET.
     */
    public static final String GET = "asset.get";
    /**
     * The Mongo.
     */
    @Inject
    private MongoDB mongo;
    /**
     * The Utils.
     */
    @Inject
    private Utils utils;

    /**
     * Start void.
     */
    @Override
    public void start() {
        super.start();
        container.logger().debug(this.getClass().getName() + " started");
        /*
         * TODO
		 */
        final Handler<Message<JsonObject>> addAssetHandler = new Handler<Message<JsonObject>>() {
            @Override
            public void handle(final Message<JsonObject> message) {
                JsonObject resp = new JsonObject();
                try {
                    utils.testMandatoryParams(message.body().toMap(), "uid", "token", "filename", "collection", "field", "contentType");
                    final JsonArray res = mongo.findByCriterias(new CriteriaBuilder().add("account.token", message.body().getString("token")).get(), null, null, 0, 0, Person.class);
                    if (res.size() != 1) {
                        FileUtils.deleteQuietly(new File(message.body().getString("filename")));

                        resp.putNumber("statusCode", ExceptionCodes.NOT_LOGGED.getCode());
                        resp.putString("message", Messages.getString("not.logged", message.body().getString("locale")));

                    } else {
                        GridFS img = new GridFS(mongo.getDb(), "Assets");
                        container.logger().info(mongo.getDb().getMongo().getConnectPoint());

                        GridFSInputFile gfsFile = img.createFile(FileUtils.readFileToByteArray(new File(message.body().getString("filename"))));
                        gfsFile.setFilename(message.body().getString("uid"));
                        BasicDBObject meta = new BasicDBObject();
                        meta.append("uid", message.body().getString("uid"));
                        gfsFile.setMetaData(meta);
                        gfsFile.setContentType(message.body().getString("contentType"));
                        gfsFile.save();
                        if (message.body().getString("collection").equals(Person.class.getSimpleName())) {
                            JsonObject personToSave = new JsonObject();
                            personToSave.putString("_id", message.body().getString("uid"));
                            personToSave.putString(message.body().getString("field"), gfsFile.getId().toString());
                            mongo.update(personToSave, Person.class);
                            resp.putNumber("statusCode", 200);
                            resp.putString("message", personToSave.encode());
                        }
                        FileUtils.deleteQuietly(new File(message.body().getString("filename")));
                        message.reply(resp);
                    }
                } catch (IOException e) {
                    resp.putNumber("statusCode", ExceptionCodes.INTERNAL_ERROR.getCode());
                    resp.putString("message", e.getMessage());
                    FileUtils.deleteQuietly(new File(message.body().getString("filename")));
                    message.reply(resp);
                } catch (final IllegalArgumentException e) {
                    container.logger().error(e.getMessage(), e);
                    resp.putNumber("statusCode", ExceptionCodes.INVALID_PARAMETER.getCode());
                    resp.putString("message", e.getMessage());
                    FileUtils.deleteQuietly(new File(message.body().getString("filename")));
                    message.reply(resp);
                }
            }
        };
         /*
         * TODO
		 */
        final Handler<Message<JsonObject>> getAssetHandler = new Handler<Message<JsonObject>>() {
            @Override
            public void handle(final Message<JsonObject> message) {
                JsonObject resp = new JsonObject();
                try {
                    utils.testMandatoryParams(message.body().toMap(), "collection", "id");
                    GridFS img = new GridFS(mongo.getDb(), "Assets");

                    if (message.body().getString("collection").equals(Person.class.getSimpleName())) {
                        GridFSDBFile imageForOutput = img.findOne(new ObjectId(message.body().getString("id")));
                        if (imageForOutput != null && imageForOutput.getChunkSize() > 0) {
                            ByteArrayOutputStream bos = new ByteArrayOutputStream();
                            imageForOutput.writeTo(bos);
                            byte[] asset = bos.toByteArray();
                            resp.putString("Content-Length", Integer.toString(asset.length));
                            resp.putBinary("asset", asset);
                        } else {
                            byte[] asset = FileUtils.readFileToByteArray(new File("web/imgs/user.png"));
                            resp.putString("Content-Length", Integer.toString(asset.length));
                            resp.putBinary("asset", asset);
                        }
                        message.reply(resp);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    resp.putNumber("statusCode", ExceptionCodes.INTERNAL_ERROR.getCode());
                    resp.putString("message", e.getMessage());
                    message.reply(resp);
                } catch (final IllegalArgumentException e) {
                    container.logger().error(e.getMessage(), e);
                    resp.putNumber("statusCode", ExceptionCodes.INVALID_PARAMETER.getCode());
                    resp.putString("message", e.getMessage());
                    message.reply(resp);
                }
            }
        };
        // Handlers declaration
        vertx.eventBus().registerHandler(GET, getAssetHandler);
        vertx.eventBus().registerHandler(ADD, addAssetHandler);
    }
}
