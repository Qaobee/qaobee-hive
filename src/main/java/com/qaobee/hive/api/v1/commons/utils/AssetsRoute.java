package com.qaobee.hive.api.v1.commons.utils;

import com.qaobee.hive.services.AssetsService;
import com.qaobee.hive.technical.annotations.VertxRoute;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.vertx.AbstractRoute;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.File;

/**
 * The type Param route.
 */
@VertxRoute(rootPath = "/file")
public class AssetsRoute extends AbstractRoute {
    private static final Logger LOG = LoggerFactory.getLogger(AssetsRoute.class);

    private static final String COLLECTION = "collection";
    @Inject
    private AssetsService assetsService;

    @Override
    public Router init() {
        Router router = Router.router(vertx);
        router.get("/:collection/:id").handler(c -> mandatoryHandler.testRequestParams(c, COLLECTION, "id"));
        router.get("/:collection/:id").handler(this::getAssetHandler);

        router.post("/:collection/:field/:uid").handler(authHandler);
        router.post("/:collection/:field/:uid").handler(c -> mandatoryHandler.testRequestParams(c, COLLECTION, "field", "uid"));
        router.post("/:collection/:field/:uid").handler(c -> mandatoryHandler.testRequestHeaders(c, "Accept-Language"));
        router.post("/:collection/:field/:uid").handler(this::assetUploadHandler);
        return router;
    }

    /**
     * @apiDescription Put an asset in a collection
     * @api {post} /file/:collection/:field/:uid
     * @apiName Post asset
     * @apiGroup Main
     * @apiParam {String} collection collection
     * @apiparam {String} field field
     * @apiparam {String} token token
     * @apiparam {String} locale locale
     * @apiparam {String} uid document id
     */
    private void assetUploadHandler(RoutingContext context) {
        // We first pause the request so we don't receive any data between now and when the file is opened
        String datadir = System.getProperty("user.home");
        if (StringUtils.isNotBlank(System.getenv("OPENSHIFT_DATA_DIR"))) {
            datadir = System.getenv("OPENSHIFT_DATA_DIR");
        }
        final File dir = new File(datadir + "/upload");
        if (!dir.exists()) {
            boolean res = dir.mkdirs();
            LOG.debug("Creating " + dir.getAbsolutePath() + " result : " + res);
        }
        context.fileUploads().forEach(upload -> handleUpload(upload, dir, context));
    }


    /**
     * @apiDescription Get an asset from a collection
     * @api {get} /file/:collection/:id  Get an asset from a collection
     * @apiVersion 0.1.0
     * @apiName Get asset
     * @apiGroup Main
     * @apiPermission all
     * @apiParam {String} collection Mandatory The collection.
     * @apiParam {String} id Mandatory The Asset-ID.
     */
    private void getAssetHandler(RoutingContext context) {
        assetsService.getAsset(context.request().getParam(COLLECTION), context.request().getParam("id"), event -> {
            if (event.succeeded()) {
                context.response().putHeader(HTTP.CONTENT_LEN, event.result().getString(HTTP.CONTENT_LEN))
                        .putHeader(HTTP.CONTENT_TYPE, "application/image")
                        .end(Buffer.buffer(event.result().getBinary("asset")));
            } else {
                utils.handleError(context, (QaobeeException) event.cause());
            }
        });
    }


    private void handleUpload(FileUpload upload, File dir, RoutingContext context) {
        final String destFileName = dir.getAbsolutePath() + File.separator + context.request().getParam("uid") + "." + FilenameUtils.getExtension(upload.fileName());
        if (vertx.fileSystem().existsBlocking(destFileName)) {
            vertx.fileSystem().deleteBlocking(destFileName);
        }
        vertx.fileSystem().copy(upload.uploadedFileName(), destFileName, res ->
                assetsService.addAsset(
                        context.request().getParam("uid"),
                        destFileName,
                        context.request().getParam(COLLECTION),
                        context.request().getParam("field"),
                        upload.contentType(),
                        getLocale(context),
                        message -> {
                            if (message.succeeded()) {
                                handleResponse(context, message.result());
                            } else {
                                utils.handleError(context, new QaobeeException(ExceptionCodes.DATA_ERROR, message.cause().getMessage()));
                            }
                        })
        );
    }
}
