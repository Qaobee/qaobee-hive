package com.qaobee.hive.dao;

import com.qaobee.hive.technical.exceptions.QaobeeException;
import org.vertx.java.core.json.JsonObject;

/**
 * The interface Pdf dao.
 */
public interface PdfDAO {
    /**
     * Generate pdf json object.
     *
     * @param data     the data
     * @param template the template
     * @param filename the filename
     * @return the json object
     * @throws QaobeeException the qaobee exception
     */
    JsonObject generatePDF(JsonObject data, String template, String filename) throws QaobeeException;
}
