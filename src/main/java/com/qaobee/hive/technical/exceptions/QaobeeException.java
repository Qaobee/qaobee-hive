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
package com.qaobee.hive.technical.exceptions;

/**
 * The Class QaobeeException.
 *
 * @author xavier
 */
public class QaobeeException extends Exception {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 3702929307917485614L;

    /**
     * The code.
     */
    private ExceptionCodes code; // NOSONAR

    /**
     * The json context.
     */
    private String jsonContext = ""; // NOSONAR

    /**
     * The timestamp.
     */
    private long timestamp = System.currentTimeMillis(); // NOSONAR

    /**
     * The report.
     */
    private boolean report; // NOSONAR

    /**
     * The error.
     */
    private boolean error = true; // NOSONAR

    /**
     * Instantiates a new qaobee exception.
     *
     * @param report      the report
     * @param jsonContext the json context
     * @param code        the code
     * @param message     the message
     */
    public QaobeeException(final boolean report, final String jsonContext, final ExceptionCodes code, final String message) {
        super(message);
        this.jsonContext = jsonContext;
        this.code = code;
        this.report = report;
    }

    /**
     * Instantiates a new qaobee exception.
     *
     * @param code    the code
     * @param message the message
     */
    public QaobeeException(final ExceptionCodes code, final String message) {
        super(message);
        this.code = code;
    }

    /**
     * Instantiates a new qaobee exception.
     *
     * @param jsonContext the json context
     * @param code        the code
     * @param message     the message
     */
    public QaobeeException(final String jsonContext, final ExceptionCodes code, final String message) {
        super(message);
        this.jsonContext = jsonContext;
        this.code = code;
    }

    /**
     * Instantiates a new Qaobee exception.
     *
     * @param code    the code
     * @param message the message
     * @param e       the e
     */
    public QaobeeException(ExceptionCodes code, String message, Exception e) {
        super(message + " : " + e.getMessage());
        this.code = code;
    }

    /**
     * Instantiates a new Qaobee exception.
     *
     * @param code the code
     * @param e    the e
     */
    public QaobeeException(ExceptionCodes code, Exception e) {
        super(e.getMessage());
        this.code = code;
    }

    /**
     * Gets the code.
     *
     * @return the code
     */
    public ExceptionCodes getCode() {
        return code;
    }

    /**
     * Gets the json context.
     *
     * @return the json context
     */
    public String getJsonContext() {
        return jsonContext;
    }

    /**
     * Gets the timestamp.
     *
     * @return the timestamp
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Checks if is the error.
     *
     * @return the error
     */
    public boolean isError() {
        return error;
    }

    /**
     * Checks if is the report.
     *
     * @return the report
     */
    public boolean isReport() {
        return report;
    }
}
