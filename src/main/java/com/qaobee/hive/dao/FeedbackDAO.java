package com.qaobee.hive.dao;

import com.qaobee.hive.technical.exceptions.QaobeeException;
import org.vertx.java.core.json.JsonObject;

/**
 * The interface Feedback dao.
 */
public interface FeedbackDAO {
    /**
     * Send feedback.
     *
     * @param data the data
     * @throws QaobeeException the qaobee exception
     */
    void sendFeedback(JsonObject data) throws QaobeeException;
}
