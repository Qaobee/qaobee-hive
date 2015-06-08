/*************************************************************************
 *
 * Qaobee
 * __________________
 *
 * [2014] Qaobee
 * All Rights Reserved.
 *
 * NOTICE: All information contained here is, and remains
 * the property of Qaobee and its suppliers,
 * if any. The intellectual and technical concepts contained
 * here are proprietary to Qaobee and its suppliers and may
 * be covered by U.S. and Foreign Patents, patents in process,
 * and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Qaobee.
 */
package com.qaobee.hive.business.model.commons.users.account;

import java.util.List;

/**
 * The Class Account.
 *
 * @author cke
 */
public class Account {

    /**
     * The _id.
     */
    private String _id;

    /**
     * The activationcode.
     */
    private String activationCode;

    /**
     * The activation passwd.
     */
    private String activationPasswd;

    /**
     * The active.
     */
    private boolean active;

    /**
     * The expiration date.
     */
    private long expirationDate;

    /**
     * The first connexion.
     */
    private boolean firstConnexion;

    /**
     * The login.
     */
    private String login;

    /**
     * The passwd.
     */
    private String passwd;

    /**
     * The password.
     */
    private byte[] password;

    /**
     * The salt.
     */
    private byte[] salt;

    /**
     * The timestamp.
     */
    private long timestamp;

    /**
     * The token.
     */
    private String token;

    /**
     * The token renew date.
     */
    private long tokenRenewDate;


    /**
     * The list plan.
     */
    private List<Plan> listPlan;

    /**
     * Gets the activation code.
     *
     * @return the activationcode
     */
    public final String getActivationCode() {
        return activationCode;
    }

    /**
     * Sets the activation code.
     *
     * @param activationcode the activationcode to set
     */
    public final void setActivationCode(final String activationcode) {
        activationCode = activationcode;
    }

    /**
     * Gets the activation passwd.
     *
     * @return the activationPasswd
     */
    public final String getActivationPasswd() {
        return activationPasswd;
    }

    /**
     * Sets the activation passwd.
     *
     * @param activationPasswd the activationPasswd to set
     */
    public final void setActivationPasswd(final String activationPasswd) {
        this.activationPasswd = activationPasswd;
    }

    /**
     * Checks if is active.
     *
     * @return the active
     */
    public final boolean isActive() {
        return active;
    }

    /**
     * Sets the active.
     *
     * @param active the active to set
     */
    public final void setActive(final boolean active) {
        this.active = active;
    }

    /**
     * Gets the expiration date.
     *
     * @return the expirationDate
     */
    public final long getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the expiration date.
     *
     * @param expirationDate the expirationDate to set
     */
    public final void setExpirationDate(final long expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * Checks if is first connexion.
     *
     * @return the firstConnexion
     */
    public final boolean isFirstConnexion() {
        return firstConnexion;
    }

    /**
     * Sets the first connexion.
     *
     * @param firstConnexion the firstConnexion to set
     */
    public final void setFirstConnexion(final boolean firstConnexion) {
        this.firstConnexion = firstConnexion;
    }

    /**
     * Gets the login.
     *
     * @return the login
     */
    public final String getLogin() {
        return login;
    }

    /**
     * Sets the login.
     *
     * @param login the login to set
     */
    public final void setLogin(final String login) {
        this.login = login;
    }

    /**
     * Gets the passwd.
     *
     * @return the passwd
     */
    public final String getPasswd() {
        return passwd;
    }

    /**
     * Sets the passwd.
     *
     * @param passwd the passwd to set
     */
    public final void setPasswd(final String passwd) {
        this.passwd = passwd;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public final byte[] getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the password to set
     */
    public final void setPassword(final byte[] password) {
        this.password = password;
    }

    /**
     * Gets the salt.
     *
     * @return the salt
     */
    public final byte[] getSalt() {
        return salt;
    }

    /**
     * Sets the salt.
     *
     * @param salt the salt to set
     */
    public final void setSalt(final byte[] salt) {
        this.salt = salt;
    }

    /**
     * Gets the timestamp.
     *
     * @return the timestamp
     */
    public final long getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp.
     *
     * @param timestamp the timestamp to set
     */
    public final void setTimestamp(final long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Gets the token.
     *
     * @return the token
     */
    public final String getToken() {
        return token;
    }

    /**
     * Sets the token.
     *
     * @param token the token to set
     */
    public final void setToken(final String token) {
        this.token = token;
    }

    /**
     * Gets the token renew date.
     *
     * @return the tokenRenewDate
     */
    public final long getTokenRenewDate() {
        return tokenRenewDate;
    }

    /**
     * Sets the token renew date.
     *
     * @param tokenRenewDate the tokenRenewDate to set
     */
    public final void setTokenRenewDate(final long tokenRenewDate) {
        this.tokenRenewDate = tokenRenewDate;
    }

    /**
     * Gets the _id.
     *
     * @return the _id
     */
    public String get_id() {
        return _id;
    }

    /**
     * Sets the _id.
     *
     * @param _id the _id to set
     */
    public void set_id(final String _id) {
        this._id = _id;
    }

    /**
     * Gets the list plan.
     *
     * @return the listPlan
     */
    public final List<Plan> getListPlan() {
        return listPlan;
    }

    /**
     * Sets the list plan.
     *
     * @param listPlan the listPlan to set
     */
    public final void setListPlan(List<Plan> listPlan) {
        this.listPlan = listPlan;
    }

}
