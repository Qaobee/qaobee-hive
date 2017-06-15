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

import com.qaobee.hive.dao.AssetDAO;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;

/**
 * The type Asset verticle.
 */
@DeployableVerticle
public class AssetVerticle extends AbstractGuiceVerticle {
    private static final Logger LOG = LoggerFactory.getLogger(AssetVerticle.class);
    /**
     * The constant ADD.
     */
    public static final String ADD = "asset.add";
    /**
     * The constant GET.
     */
    public static final String GET = "asset.get";

    private static final String COLLECTION_FIELD = "collection";
    private static final String FILENAME_FIELD = "filename";
    private static final String UID_FIELD = "uid";

    @Inject
    private Utils utils;
    @Inject
    private AssetDAO assetDAO;

    @Override
    public void start() {
        super.start();
        if (LOG.isDebugEnabled()) {
            LOG.debug(this.getClass().getName() + " started");
        }
        vertx.eventBus().consumer(ADD, this::addAsset);
        vertx.eventBus().consumer(GET, this::getAssetHandler);
    }

    /**
     * Get an asset
     * <p>
     * <pre>{
     *  id
     *  collection
     *  }</pre>
     * </p>
     */
    private void getAssetHandler(Message<JsonObject> message) {
        JsonObject resp = new JsonObject();
        try {
            utils.testMandatoryParams(message.body(), COLLECTION_FIELD, "id");
            replyJsonObjectJ(message, assetDAO.getAsset(message.body().getString(COLLECTION_FIELD), message.body().getString("id")));
        } catch (final QaobeeException e) {
            LOG.error(e.getMessage(), e);
            resp.put(Constants.STATUS_CODE, e.getCode().getCode())
                    .put(Constants.MESSAGE, e.getMessage());
            message.reply(resp);
        }
    }

    /**
     * Add an asset
     * <p>
     * <pre>{
     *  uid User id
     *  token
     *  filename
     *  collection
     *  field
     *  contentType
     *  }</pre>
     * </p>
     */
    private void addAsset(Message<JsonObject> message) {
        try {
            utils.testMandatoryParams(message.body(), UID_FIELD, Constants.TOKEN, FILENAME_FIELD, COLLECTION_FIELD, "field", CONTENT_TYPE, "locale");
            replyJsonObjectJ(message, assetDAO.addAsset(
                    message.body().getString(UID_FIELD),
                    message.body().getString(Constants.TOKEN),
                    message.body().getString(FILENAME_FIELD),
                    message.body().getString(COLLECTION_FIELD),
                    message.body().getString("field"),
                    message.body().getString(CONTENT_TYPE),
                    message.body().getString("locale")
            ));
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            if (vertx.fileSystem().existsBlocking(message.body().getString(FILENAME_FIELD))) {
                vertx.fileSystem().deleteBlocking(message.body().getString(FILENAME_FIELD));
            }
            utils.sendErrorJ(message, e);
        }
    }
}
