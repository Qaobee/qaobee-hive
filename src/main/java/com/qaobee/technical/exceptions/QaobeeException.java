/*************************************************************************
 * 
 * Qaobee
 * __________________
 * 
 * [2014] Qaobee
 * All Rights Reserved.
 * 
 * NOTICE:  All information contained here is, and remains
 * the property of Qaobee and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * here are proprietary to Qaobee and its suppliers and may 
 * be covered by U.S. and Foreign Patents, patents in process,
 * and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Qaobee.
 */
package com.qaobee.technical.exceptions;

/**
 * The Class QaobeeException.
 *
 * @author xavier
 */
public class QaobeeException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3702929307917485614L;

	/** The code. */
	private ExceptionCodes code;

	/** The json context. */
	private String jsonContext = "";

	/** The timestamp. */
	private long timestamp = System.currentTimeMillis();

	/** The report. */
	private boolean report = false;

	/** The error. */
	private boolean error = true;

	/**
	 * Instantiates a new qaobee exception.
	 *
	 * @param report
	 *            the report
	 * @param jsonContext
	 *            the json context
	 * @param code
	 *            the code
	 * @param message
	 *            the message
	 */
	public QaobeeException(final boolean report, final String jsonContext, final ExceptionCodes code, final String message) {
		super(message);
		setJsonContext(jsonContext);
		setCode(code);
		setReport(report);
	}

	/**
	 * Instantiates a new qaobee exception.
	 *
	 * @param code
	 *            the code
	 * @param message
	 *            the message
	 */
	public QaobeeException(final ExceptionCodes code, final String message) {
		super(message);
		setCode(code);
	}

	/**
	 * Instantiates a new qaobee exception.
	 *
	 * @param jsonContext
	 *            the json context
	 * @param code
	 *            the code
	 * @param message
	 *            the message
	 */
	public QaobeeException(final String jsonContext, final ExceptionCodes code, final String message) {
		super(message);
		setJsonContext(jsonContext);
		setCode(code);
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

	/**
	 * Sets the code.
	 *
	 * @param code
	 *            the new code
	 */
	public void setCode(final ExceptionCodes code) {
		this.code = code;
	}

	/**
	 * Sets the error.
	 *
	 * @param error
	 *            the new error
	 */
	public void setError(final boolean error) {
		this.error = error;
	}

	/**
	 * Sets the json context.
	 *
	 * @param jsonContext
	 *            the new json context
	 */
	public void setJsonContext(final String jsonContext) {
		this.jsonContext = jsonContext;
	}

	/**
	 * Sets the report.
	 *
	 * @param report
	 *            the new report
	 */
	public void setReport(final boolean report) {
		this.report = report;
	}

	/**
	 * Sets the timestamp.
	 *
	 * @param timestamp
	 *            the new timestamp
	 */
	public void setTimestamp(final long timestamp) {
		this.timestamp = timestamp;
	}
}
