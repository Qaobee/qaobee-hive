package com.qaobee.hive.technical.vertx;

import com.qaobee.hive.dao.Utils;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import com.qaobee.hive.technical.tools.Messages;
import io.vertx.ext.web.RoutingContext;

import javax.inject.Inject;

/**
 * The type Role handler.
 */
public class RoleHandler {
    /**
     * The Utils.
     */
    @Inject
    protected Utils utils;

    /**
     * Has role.
     *
     * @param context the context
     * @param roles   the roles
     */
    public void hasRole(RoutingContext context, String roles) {
        context.user().isAuthorised(roles, res -> {
            if (res.succeeded() && res.result()) {
                context.next();
            } else {
                utils.handleError(context, new QaobeeException(ExceptionCodes.NOT_ADMIN,
                        Messages.getString("not.admin", context.request().getHeader("Accept-Language"))
                ));
            }
        });
    }
}
