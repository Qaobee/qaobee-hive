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
package com.qaobee.hive.technical.vertx;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.SerializationUtils;

import com.qaobee.hive.business.model.sandbox.effective.Person;

/**
 * The Class RequestWrapper.
 *
 * @author Xavier.Marin
 */
public class RequestWrapper implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3895794648152512848L;

	/** The path. */
	private List<String> path;

	/** The headers. */
	private Map<String, List<String>> headers;

	/** The params. */
	private Map<String, List<String>> params;

	/** The body. */
	private String body;

	/** The method. */
	private String method;

	/** The locale. */
	private String locale;

	/** the current person *. */
	private Person person;

	/**
	 * Gets the body.
	 *
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * Gets the headers.
	 *
	 * @return the headers
	 */
	public Map<String, List<String>> getHeaders() {
		return headers;
	}

	/**
	 * Gets the locale.
	 *
	 * @return the locale
	 */
	public String getLocale() {
		return locale;
	}

	/**
	 * Gets the method.
	 *
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * Gets the params.
	 *
	 * @return the params
	 */
	public Map<String, List<String>> getParams() {
		return params;
	}

	/**
	 * Gets the path.
	 *
	 * @return the path
	 */
	public List<String> getPath() {
		return path;
	}

	/**
	 * Sets the body.
	 *
	 * @param body
	 *            the new body
	 */
	public void setBody(final String body) {
		this.body = body;
	}

	/**
	 * Sets the headers.
	 *
	 * @param headers
	 *            the new headers
	 */
	public void setHeaders(final Map<String, List<String>> headers) {
		this.headers = headers;
	}

	/**
	 * Sets the locale.
	 *
	 * @param locale
	 *            the new locale
	 */
	public void setLocale(final String locale) {
		this.locale = locale;
	}

	/**
	 * Sets the method.
	 *
	 * @param method
	 *            the new method
	 */
	public void setMethod(final String method) {
		this.method = method;
	}

	/**
	 * Sets the params.
	 *
	 * @param params
	 *            the new params
	 */
	public void setParams(final Map<String, List<String>> params) {
		this.params = params;
	}

	/**
	 * Sets the path.
	 *
	 * @param path
	 *            the new path
	 */
	public void setPath(final List<String> path) {
		this.path = path;
	}

	/**
	 * Gets the current Person *.
	 *
	 * @return the current Person
	 */
	public Person getPerson() {
		return person;
	}

	/**
	 * Sets the current Person *.
	 *
	 * @param person
	 *            the new person
	 */
	public void setPerson(final Person person) {
		this.person = person;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#clone()
	 */
	@Override
	public RequestWrapper clone() throws CloneNotSupportedException {
		return (RequestWrapper) SerializationUtils.clone(this);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RequestWrapper [path=" + path + ", headers=" + headers + ", params=" + params + ", body=" + body + ", method=" + method + ", locale=" + locale + ", person=" + person + "]";
	}

}
