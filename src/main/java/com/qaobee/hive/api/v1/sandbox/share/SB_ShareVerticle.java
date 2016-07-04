package com.qaobee.hive.api.v1.sandbox.share;

import com.mongodb.BasicDBObject;
import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.api.v1.commons.communication.NotificationsVerticle;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.business.model.sandbox.config.SB_SandBox;
import com.qaobee.hive.business.model.sandbox.config.SB_SandBoxCfg;
import com.qaobee.hive.technical.annotations.DeployableVerticle;
import com.qaobee.hive.technical.annotations.Rule;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.CriteriaBuilder;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.tools.Messages;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.DecodeException;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import javax.inject.Inject;
import java.util.Arrays;

/**
 * The type Sb share verticle.
 */
@DeployableVerticle
public class SB_ShareVerticle extends AbstractGuiceVerticle { // NOSONAR
    private static final Logger LOG = LoggerFactory.getLogger(SB_ShareVerticle.class);

    /**
     * The constant GET_SANDBOX_LIST.
     */
    public static final String GET_SANDBOX_LIST = Module.VERSION + ".share.sandbox.list";
    /**
     * The constant ADD_TO_SANDBOX.
     */
    public static final String ADD_TO_SANDBOX = Module.VERSION + ".share.sandbox.add";
    /**
     * The constant REMOVE_FROM_SANDBOX.
     */
    public static final String REMOVE_FROM_SANDBOX = Module.VERSION + ".share.sandbox.del";
    /**
     * The constant GET_SANDOX_SHARING.
     */
    public static final String GET_SANDOX_SHARING = Module.VERSION + ".share.sandbox.get";

    /**
     * The constant PARAM_SANBOXID.
     */
    public static final String PARAM_SANBOXID = "sandboxId";
    /**
     * The constant PARAM_USERID.
     */
    public static final String PARAM_USERID = "userId";
    public static final String PARAM_ROLE_CODE = "role_code";
    public static final String PARAM_ROLE_LABEL = "role_label";

    private static final String INTERNAL_SHARE_NOTIFICATION = "internal.sandbox.share";
    private static final String FIELD_ID = "_id";
    private static final String FIELD_LOCALE = "locale";
    private static final String FIELD_OWNER = "owner";
    private static final String FIELD_SBCFG_ID = "sandboxCfgId";
    private static final String FIELD_SBCFG = "sandboxCfg";
    private static final String FIELD_MEMBERS = "members";
    private static final String FIELD_FIRSTNAME = "firstname";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_AVATAR = "avatar";
    private static final String FIELD_CONTACT = "contact";
    private static final String FIELD_COUNTRY = "country";
    private static final String FIELD_ROOT = "root";
    private static final String FIELD_UID = "uid";
    /**
     * The Utils.
     */
    @Inject
    protected Utils utils;
    @Inject
    private MongoDB mongo;

    @Override
    public void start() {
        super.start();
        LOG.debug(this.getClass().getName() + " started");
        vertx.eventBus()
                .registerHandler(GET_SANDBOX_LIST, this::getListOfSharedSandboxes)
                .registerHandler(ADD_TO_SANDBOX, this::addUserToSandbox)
                .registerHandler(REMOVE_FROM_SANDBOX, this::removeUserFromSandbox)
                .registerHandler(GET_SANDOX_SHARING, this::getSandboxSharing)
                .registerHandler(INTERNAL_SHARE_NOTIFICATION, this::internalShareNotification);
    }

    private void internalShareNotification(Message<JsonObject> message) {
        JsonObject notification = new JsonObject()
                .putString("id", message.body().getString(PARAM_USERID))
                .putString("target", User.class.getSimpleName())
                .putObject("notification", new JsonObject()
                        .putString("content", Messages.getString(message.body().getString(FIELD_ROOT) + ".content", message.body().getString(FIELD_LOCALE)))
                        .putString("title", Messages.getString(message.body().getString(FIELD_ROOT) + ".title", message.body().getString(FIELD_LOCALE)))
                        .putString("senderId", message.body().getString(FIELD_UID))
                );
        vertx.eventBus().send(NotificationsVerticle.NOTIFY, notification);
    }

    /**
     * @apiDescription Get an enriched SB_SandBoxCfg
     * @api {post} /api/1/share/sandbox/get Get an enriched SB_SandBoxCfg
     * @apiParam {String} sandboxId Targeted sandbox
     * @apiName getSandboxSharing
     * @apiGroup Share API
     * @apiSuccess {Object} sandbox Enriched sandbox;
     */
    @Rule(address = GET_SANDOX_SHARING, method = Constants.GET, logged = true, mandatoryParams = {PARAM_SANBOXID}, scope = Rule.Param.REQUEST)
    private void getSandboxSharing(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        try {
            JsonObject sandbox = mongo.getById(req.getParams().get(PARAM_SANBOXID).get(0), SB_SandBox.class);
            String sandboxCfgId = sandbox.getString(FIELD_SBCFG_ID);
            sandbox.putObject(FIELD_SBCFG, getEnrichedSandboxCfg(sandboxCfgId));
            message.reply(sandbox.encode());
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        } catch (DecodeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, new QaobeeException(ExceptionCodes.JSON_EXCEPTION, e));
        }
    }

    /**
     * @apiDescription Remove a member to a SB_SandBoxCfg
     * @api {post} /api/1/share/sandbox/del Remove a member to a SB_SandBoxCfg
     * @apiParam {String} userId User id to add as a member
     * @apiParam {String} sandboxId Targeted sandbox
     * @apiName removeUserFromSandbox
     * @apiGroup Share API
     * @apiSuccess {Object} sandbox Enriched sandbox;
     */
    @Rule(address = REMOVE_FROM_SANDBOX, method = Constants.POST, logged = true, mandatoryParams = {PARAM_SANBOXID, PARAM_USERID}, scope = Rule.Param.BODY)
    private void removeUserFromSandbox(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        JsonObject request = new JsonObject(req.getBody());
        try {
            JsonObject sandbox = mongo.getById(request.getString(PARAM_SANBOXID), SB_SandBox.class);
            String sandboxCfgId = sandbox.getString(FIELD_SBCFG_ID);
            JsonObject sandboxCfg = mongo.getById(sandboxCfgId, SB_SandBoxCfg.class);
            JsonArray members = new JsonArray();
            sandboxCfg.getArray(FIELD_MEMBERS).forEach(m -> {
                if(!((JsonObject) m).getString("personId").equals(request.getString(PARAM_USERID))) {
                    members.add(m);
                }
            });
            sandboxCfg.putArray(FIELD_MEMBERS, members);
            vertx.eventBus().send(INTERNAL_SHARE_NOTIFICATION, new JsonObject()
                    .putString(PARAM_USERID, request.getString(PARAM_USERID))
                    .putString(FIELD_ROOT, "notification.sandbox.del")
                    .putString(FIELD_LOCALE, req.getLocale())
                    .putString(FIELD_UID, req.getUser().get_id())
            );
            mongo.update(sandboxCfg, SB_SandBoxCfg.class);
            sandbox.putObject(FIELD_SBCFG, getEnrichedSandboxCfg(sandboxCfgId));
            message.reply(sandbox.encode());
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }

    /**
     * @apiDescription Add a member to a SB_SandBoxCfg
     * @api {post} /api/1/share/sandbox/add Add a member to a SB_SandBoxCfg
     * @apiParam {String} userId User id to add as a member
     * @apiParam {String} sandboxId Targeted sandbox
     * @apiParam {String} role_code Role code
     * @apiParam {String} role_label Role label
     * @apiName addUserToSandbox
     * @apiGroup Share API
     * @apiSuccess {Object} sandbox Enriched sandbox;
     */
    @Rule(address = ADD_TO_SANDBOX, method = Constants.POST, logged = true, mandatoryParams = {PARAM_SANBOXID, PARAM_USERID, PARAM_ROLE_CODE, PARAM_ROLE_LABEL}, scope = Rule.Param.BODY)
    private void addUserToSandbox(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        JsonObject request = new JsonObject(req.getBody());
        try {
            JsonObject sandbox = mongo.getById(request.getString(PARAM_SANBOXID), SB_SandBox.class);
            String sandboxCfgId = sandbox.getString(FIELD_SBCFG_ID);
            JsonObject sandboxCfg = mongo.getById(sandboxCfgId, SB_SandBoxCfg.class);
            sandboxCfg.getArray(FIELD_MEMBERS).add(
                    new JsonObject()
                            .putString("personId", request.getString(PARAM_USERID))
                            .putObject("role", new JsonObject()
                                    .putString("code", request.getString(PARAM_ROLE_CODE))
                                    .putString("label", request.getString(PARAM_ROLE_LABEL))
                            )
            );
            mongo.update(sandboxCfg, SB_SandBoxCfg.class);
            vertx.eventBus().send(INTERNAL_SHARE_NOTIFICATION, new JsonObject()
                    .putString(PARAM_USERID, request.getString(PARAM_USERID))
                    .putString(FIELD_ROOT, "notification.sandbox.add")
                    .putString(FIELD_LOCALE, req.getLocale())
                    .putString(FIELD_UID, req.getUser().get_id())
            );
            sandbox.putObject(FIELD_SBCFG, getEnrichedSandboxCfg(sandboxCfgId));
            message.reply(sandbox.encode());
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }

    /**
     * @apiDescription Get list of enriched sandboxes for the current user
     * @api {get} /api/1/share/sandbox/list Get list of enriched sandboxes for the current user
     * @apiName getListOfSharedSandboxes
     * @apiGroup Share API
     * @apiSuccess {Array} sandboxes list of enriched sandboxes;
     */
    @Rule(address = GET_SANDBOX_LIST, method = Constants.GET, logged = true)
    private void getListOfSharedSandboxes(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);

        JsonArray result = new JsonArray();
        JsonArray sandboxes = mongo.findByCriterias(new CriteriaBuilder().add(FIELD_OWNER, req.getUser().get_id()).get(),
                null, null, -1, 0, SB_SandBox.class);
        BasicDBObject elemMatch = new BasicDBObject();
        elemMatch.put("personId", req.getUser().get_id());
        BasicDBObject array = new BasicDBObject();
        array.put("$elemMatch", elemMatch);
        BasicDBObject query = new BasicDBObject();
        query.put("members", array);

        mongo.getDb().getCollection(SB_SandBoxCfg.class.getSimpleName()).find(query).forEach(sbCfg -> {
            JsonObject sandboxCfg = new JsonObject(sbCfg.toString());
            JsonArray members = new JsonArray();
            sandboxCfg.getArray(FIELD_MEMBERS).forEach(m -> {
                try {
                    members.add(mongo.getById(((JsonObject) m).getString("personId"), User.class, Arrays.asList(FIELD_ID, FIELD_NAME, FIELD_AVATAR, FIELD_FIRSTNAME, FIELD_CONTACT, FIELD_COUNTRY)));
                } catch (QaobeeException e) {
                    LOG.error(e.getMessage(), e);
                }
            });
            sandboxCfg.putArray(FIELD_MEMBERS, members);
            mongo.findByCriterias(new CriteriaBuilder().add(FIELD_SBCFG_ID, sbCfg.get(FIELD_ID)).get(), null, null, -1, 0, SB_SandBox.class).forEach(sb -> {
                        try {
                            ((JsonObject) sb).putObject(FIELD_OWNER, mongo.getById(((JsonObject) sb).getString(FIELD_OWNER), User.class, Arrays.asList(FIELD_ID, FIELD_NAME, FIELD_AVATAR, FIELD_FIRSTNAME, FIELD_CONTACT, FIELD_COUNTRY)));
                        } catch (QaobeeException e) {
                            LOG.error(e.getMessage(), e);
                        }
                        ((JsonObject) sb).putObject(FIELD_SBCFG, sandboxCfg);
                        result.add(sb);
                    }
            );
        });

        sandboxes.forEach(s -> {
            try {
                ((JsonObject) s).putObject(FIELD_OWNER, mongo.getById(((JsonObject) s).getString(FIELD_OWNER), User.class, Arrays.asList(FIELD_ID, FIELD_NAME, FIELD_AVATAR, FIELD_FIRSTNAME, FIELD_CONTACT, FIELD_COUNTRY)));
                ((JsonObject) s).putObject(FIELD_SBCFG, getEnrichedSandboxCfg(((JsonObject) s).getString(FIELD_SBCFG_ID)));
            } catch (QaobeeException e) {
                LOG.error(e.getMessage(), e);
            }
            result.add(s);
        });
        message.reply(result.encode());
    }

    private JsonObject getEnrichedSandboxCfg(String sandboxCfgId) throws QaobeeException {
        JsonObject sandboxCfg = mongo.getById(sandboxCfgId, SB_SandBoxCfg.class);
        JsonArray members = new JsonArray();
        sandboxCfg.getArray(FIELD_MEMBERS).forEach(m -> {
            try {
                members.add(mongo.getById(((JsonObject) m).getString("personId"), User.class, Arrays.asList(FIELD_ID, FIELD_NAME, FIELD_AVATAR, FIELD_FIRSTNAME, FIELD_CONTACT, FIELD_COUNTRY)));
            } catch (QaobeeException e) {
                LOG.error(e.getMessage(), e);
            }
        });
        sandboxCfg.putArray(FIELD_MEMBERS, members);
        return sandboxCfg;
    }
}
