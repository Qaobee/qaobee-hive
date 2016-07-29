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

package com.qaobee.hive.dao;

import com.qaobee.hive.technical.exceptions.QaobeeException;
import org.vertx.java.core.json.JsonObject;

/**
 * The interface Templates dao.
 */
public interface TemplatesDAO {
    /**
     * Generate pdf handler json object.
     *
     * @param body the body
     * @return the json object
     * @throws QaobeeException the qaobee exception
     */
    String generatePDF(JsonObject body) throws QaobeeException;

    /**
     * Generate pdf json object.
     *
     * @param data     the data
     * @param template the template
     * @return the json object
     * @throws QaobeeException the qaobee exception
     */
    String generatePDF(JsonObject data, String template) throws QaobeeException;

    /**
     * Generate mail json object.
     *
     * @param body the body
     * @return the json object
     * @throws QaobeeException the qaobee exception
     */
    JsonObject generateMail(JsonObject body) throws QaobeeException;

    /**
     * Generate mail json object.
     *
     * @param data     the data
     * @param template the template
     * @return the json object
     * @throws QaobeeException the qaobee exception
     */
    JsonObject generateMail(JsonObject data, String template) throws QaobeeException;
}
