package com.qaobee.hive.api.v1.sandbox.share;

import com.qaobee.hive.api.v1.Module;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.business.model.sandbox.config.SB_SandBox;
import com.qaobee.hive.business.model.sandbox.config.SB_SandBoxCfg;
import com.qaobee.hive.technical.annotations.Rule;
import com.qaobee.hive.technical.constantes.Constants;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.mongo.CriteriaBuilder;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.utils.Utils;
import com.qaobee.hive.technical.utils.guice.AbstractGuiceVerticle;
import com.qaobee.hive.technical.vertx.RequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import javax.inject.Inject;

/**
 * The type Sb share verticle.
 */
public class SB_ShareVerticle extends AbstractGuiceVerticle { // NOSONAR
    private static final Logger LOG = LoggerFactory.getLogger(SB_ShareVerticle.class);

    /**
     * The constant GET_FRIEND_LIST.
     */
    public static final String GET_FRIEND_LIST = Module.VERSION + ".sandbox.share.list";
    /**
     * The constant ADD_FRIEND.
     */
    public static final String ADD_FRIEND = Module.VERSION + ".sandbox.share.add";
    /**
     * The constant DEL_FRIEND.
     */
    public static final String DEL_FRIEND = Module.VERSION + ".sandbox.share.del";
    /**
     * The constant GET.
     */
    public static final String GET = Module.VERSION + ".sandbox.share.get";

    /**
     * The constant PARAM_SANBOXID.
     */
    public static final String PARAM_SANBOXID = "sandboxId";
    /**
     * The constant PARAM_USERID.
     */
    public static final String PARAM_USERID = "userId";

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
                .registerHandler(GET_FRIEND_LIST, this::getFriendList)
                .registerHandler(ADD_FRIEND, this::addFriend)
                .registerHandler(DEL_FRIEND, this::delFriend)
                .registerHandler(GET, this::getShare);
    }
    /**
     * @apiDescription Get an enriched SB_SandBoxCfg
     * @api {post} /api/1/sandbox/share/get Get an enriched SB_SandBoxCfg
     * @apiParam {String} sandboxId Targeted sandbox
     * @apiName getShare
     * @apiGroup Share API
     * @apiSuccess {Object} sandbox Enriched sandbox;
     */
    @Rule(address = GET, method = Constants.GET, logged = true, mandatoryParams = {PARAM_SANBOXID}, scope = Rule.Param.REQUEST)
    private void getShare(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        JsonObject request = new JsonObject(req.getBody());
        try {
            JsonObject sandbox = mongo.getById(request.getString(PARAM_SANBOXID), SB_SandBox.class);
            String sandboxCfgId = sandbox.getString("sandboxCfgId");
            sandbox.putObject("sandboxCfg", getEnrichedSandboxCfg(sandboxCfgId));
            message.reply(sandbox.encode());
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }

    /**
     * @apiDescription Remove a member to a SB_SandBoxCfg
     * @api {post} /api/1/sandbox/share/del Remove a member to a SB_SandBoxCfg
     * @apiParam {String} userId User id to add as a member
     * @apiParam {String} sandboxId Targeted sandbox
     * @apiName delFriend
     * @apiGroup Share API
     * @apiSuccess {Object} sandbox Enriched sandbox;
     */
    @Rule(address = DEL_FRIEND, method = Constants.POST, logged = true, mandatoryParams = {PARAM_SANBOXID, PARAM_USERID}, scope = Rule.Param.BODY)
    private void delFriend(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        JsonObject request = new JsonObject(req.getBody());
        try {
            JsonObject sandbox = mongo.getById(request.getString(PARAM_SANBOXID), SB_SandBox.class);
            String sandboxCfgId = sandbox.getString("sandboxCfgId");
            JsonObject sandboxCfg = mongo.getById(sandboxCfgId, SB_SandBoxCfg.class);
            JsonArray members = new JsonArray();
            sandboxCfg.getArray("members").forEach(m -> {
                if(!(m).equals(request.getString(PARAM_USERID))) {
                    members.add(m);
                }
            });
            sandboxCfg.putArray("members", members);
            mongo.update(sandboxCfg, SB_SandBoxCfg.class);
            sandbox.putObject("sandboxCfg", getEnrichedSandboxCfg(sandboxCfgId));
            message.reply(sandbox.encode());
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }

    /**
     * @apiDescription Add a member to a SB_SandBoxCfg
     * @api {post} /api/1/sandbox/share/add Add a member to a SB_SandBoxCfg
     * @apiParam {String} userId User id to add as a member
     * @apiParam {String} sandboxId Targeted sandbox
     * @apiName addFriend
     * @apiGroup Share API
     * @apiSuccess {Object} sandbox Enriched sandbox;
     */
    @Rule(address = ADD_FRIEND, method = Constants.POST, logged = true, mandatoryParams = {PARAM_SANBOXID, PARAM_USERID}, scope = Rule.Param.BODY)
    private void addFriend(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        JsonObject request = new JsonObject(req.getBody());
        try {
            JsonObject sandbox = mongo.getById(request.getString(PARAM_SANBOXID), SB_SandBox.class);
            String sandboxCfgId = sandbox.getString("sandboxCfgId");
            JsonObject sandboxCfg = mongo.getById(sandboxCfgId, SB_SandBoxCfg.class);
            sandboxCfg.getArray("members").add(request.getString(PARAM_USERID));
            mongo.update(sandboxCfg, SB_SandBoxCfg.class);
            sandbox.putObject("sandboxCfg", getEnrichedSandboxCfg(sandboxCfgId));
            message.reply(sandbox.encode());
        } catch (QaobeeException e) {
            LOG.error(e.getMessage(), e);
            utils.sendError(message, e);
        }
    }

    /**
     * @apiDescription Get list of enriched sandboxes for the current user
     * @api {get} /api/1/sandbox/share/list Get list of enriched sandboxes for the current user
     * @apiName getFriendList
     * @apiGroup Share API
     * @apiSuccess {Array} sandboxes list of enriched sandboxes;
     */
    @Rule(address = GET_FRIEND_LIST, method = Constants.GET, logged = true)
    private void getFriendList(Message<String> message) {
        final RequestWrapper req = Json.decodeValue(message.body(), RequestWrapper.class);
        JsonArray sandboxes = mongo.findByCriterias(new CriteriaBuilder().add("owner", req.getUser().get_id()).get(),
                null, null, -1, 0, SB_SandBox.class);
        if (sandboxes == null || sandboxes.size() == 0) {
            message.reply("[]");
            return;
        }
        JsonArray result = new JsonArray();
        sandboxes.forEach(s -> {
            try {
                ((JsonObject) s).putObject("sandboxCfg", getEnrichedSandboxCfg(((JsonObject) s).getString("sandboxCfgId")));
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
        sandboxCfg.getArray("members").forEach(m -> {
            try {
                members.add(mongo.getById((String) m, User.class));
            } catch (QaobeeException e) {
                LOG.error(e.getMessage(), e);
            }
        });
        sandboxCfg.putArray("members", members);
        return sandboxCfg;
    }
}
