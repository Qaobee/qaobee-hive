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

import com.qaobee.hive.business.model.commons.users.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Class RequestWrapper.
 *
 * @author Xavier.Marin
 */
public class RequestWrapper {

    private List<String> path;
    private Map<String, List<String>> headers;
    private Map<String, List<String>> params;
    private String body;
    private String method;
    private String locale;
    private User user;

    /**
     * Gets the body.
     *
     * @return the body
     */
    public String getBody() {
        return body;
    }

    /**
     * Sets the body.
     *
     * @param body the new body
     */
    public void setBody(final String body) {
        this.body = body;
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
     * Sets the headers.
     *
     * @param headers the new headers
     */
    public void setHeaders(final Map<String, List<String>> headers) {
        this.headers = headers;
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
     * Sets the locale.
     *
     * @param locale the new locale
     */
    public void setLocale(final String locale) {
        this.locale = locale;
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
     * Sets the method.
     *
     * @param method the new method
     */
    public void setMethod(final String method) {
        this.method = method;
    }

    /**
     * Gets the params.
     *
     * @return the params
     */
    public Map<String, List<String>> getParams() {
        if (params == null) {
            params = new HashMap<>();
        }
        return params;
    }

    /**
     * Sets the params.
     *
     * @param params the new params
     */
    public void setParams(final Map<String, List<String>> params) {
        this.params = params;
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
     * Sets the path.
     *
     * @param path the new path
     */
    public void setPath(final List<String> path) {
        this.path = path;
    }

    /**
     * Gets the current user *.
     *
     * @return the current user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the current user *.
     *
     * @param user the new user
     */
    public void setUser(final User user) {
        this.user = user;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "RequestWrapper [path=" + path + ", headers=" + headers + ", params=" + params + ", body=" + body + ", method=" + method + ", locale=" + locale + ", user=" + user + "]";
    }

}
