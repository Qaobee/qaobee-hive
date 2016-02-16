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
package com.qaobee.hive.business.model.commons.users.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.qaobee.hive.business.model.transversal.Habilitation;

import java.util.List;

/**
 * Bean that describes user account.
 *
 * @author cke
 */
@JsonIgnoreProperties(ignoreUnknown = true) public class Account {

	/**
	 * Internal identifier
	 */
	private String _id; // NOSONAR
	/**
	 * Activation code
	 */
	private String activationCode;
	/**
	 * Activation password
	 */
	private String activationPasswd;
	/**
	 * Is account active ?
	 */
	private boolean active;
	/**
	 * Expiration date
	 */
	private long expirationDate;
	/**
	 * Is the first connection ?
	 */
	private boolean firstConnexion;
	/**
	 * Login
	 */
	private String login;
	/**
	 * Password - String format
	 */
	private String passwd;
	/**
	 * Password - bytes format
	 */
	private byte[] password;
	/**
	 * Salt
	 */
	private byte[] salt;
	/**
	 * Timestamp
	 */
	private long timestamp;
	/**
	 * Token
	 */
	private String token;
	/**
	 * Token renew date
	 */
	private long tokenRenewDate;
	/**
	 * List of plans
	 */
	private List<Plan> listPlan;
	/**
	 * List of habilitations
	 */
	private List<Habilitation> habilitations;
	/**
	 * Mobile token
	 */
	private String mobileToken;

	/**
	 * Returns the activation code.
	 *
	 * @return String : code
	 */
	public String getActivationCode() {
		return activationCode;
	}

	/**
	 * Defines the activation code.
	 *
	 * @param activationcode (String) : code
	 */
	public void setActivationCode(final String activationcode) {
		activationCode = activationcode;
	}

	/**
	 * Returns the activation password.
	 *
	 * @return String : password
	 */
	public String getActivationPasswd() {
		return activationPasswd;
	}

	/**
	 * Defines the activation password.
	 *
	 * @param activationPasswd (String) : password
	 */
	public void setActivationPasswd(final String activationPasswd) {
		this.activationPasswd = activationPasswd;
	}

	/**
	 * Checks if account is active.
	 *
	 * @return boolean : true if active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Defines if the account is active.
	 *
	 * @param active (boolean) : true if active
	 */
	public void setActive(final boolean active) {
		this.active = active;
	}

	/**
	 * Returns the expiration date.
	 *
	 * @return long : expiration date
	 */
	public long getExpirationDate() {
		return expirationDate;
	}

	/**
	 * Defines the expiration date.
	 *
	 * @param expirationDate (long) : expiration date
	 */
	public void setExpirationDate(final long expirationDate) {
		this.expirationDate = expirationDate;
	}

	/**
	 * Checks if it is the first user connection.
	 *
	 * @return boolean : true if first connection
	 */
	public boolean isFirstConnexion() {
		return firstConnexion;
	}

	/**
	 * Defines if it is the first user connection.
	 *
	 * @param firstConnexion (boolean) : true if first connection
	 */
	public void setFirstConnexion(final boolean firstConnexion) {
		this.firstConnexion = firstConnexion;
	}

	/**
	 * Returns the user login.
	 *
	 * @return String : login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Defines the user login.
	 *
	 * @param login (String) : user login
	 */
	public void setLogin(final String login) {
		this.login = login;
	}

	/**
	 * Returns the password in String format.
	 *
	 * @return String: password
	 */
	public String getPasswd() {
		return passwd;
	}

	/**
	 * Defines the password in String format.
	 *
	 * @param passwd (String) : password
	 */
	public void setPasswd(final String passwd) {
		this.passwd = passwd;
	}

	/**
	 * Returns the password in bytes format.
	 *
	 * @return byte[] : password
	 */
	public byte[] getPassword() {
		return password;
	}

	/**
	 * Defines the password in bytes format.
	 *
	 * @param password (byte[]) : password
	 */
	public void setPassword(final byte[] password) {
		this.password = password;
	}

	/**
	 * Returns the salt.
	 *
	 * @return byte[] : salt
	 */
	public byte[] getSalt() {
		return salt;
	}

	/**
	 * Defines the salt.
	 *
	 * @param salt (byte[]) : salt
	 */
	public void setSalt(final byte[] salt) {
		this.salt = salt;
	}

	/**
	 * Returns the timestamp.
	 *
	 * @return long : timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * Defines the timestamp.
	 *
	 * @param timestamp (long) : timestamp
	 */
	public void setTimestamp(final long timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * Returns the token.
	 *
	 * @return String : token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Defines the token.
	 *
	 * @param token (String) : token
	 */
	public void setToken(final String token) {
		this.token = token;
	}

	/**
	 * Returns the token renew date.
	 *
	 * @return long : date
	 */
	public long getTokenRenewDate() {
		return tokenRenewDate;
	}

	/**
	 * Defines the token renew date.
	 *
	 * @param tokenRenewDate (long) : date
	 */
	public void setTokenRenewDate(final long tokenRenewDate) {
		this.tokenRenewDate = tokenRenewDate;
	}

	/**
	 * Returns the identifier.
	 *
	 * @return String : id
	 */
	public String get_id() { // NOSONAR
		return _id;
	}

	/**
	 * Defines the identifier.
	 *
	 * @param _id (String) : id
	 */
	public void set_id(final String _id) { // NOSONAR
		this._id = _id;
	}

	/**
	 * Returns list of plans.
	 *
	 * @return List(Plan) : list
	 */
	public List<Plan> getListPlan() {
		return listPlan;
	}

	/**
	 * Defines the list of plans.
	 *
	 * @param listPlan (List(Plan)) : list
	 */
	public void setListPlan(List<Plan> listPlan) {
		this.listPlan = listPlan;
	}

	/**
	 * Returns the list of habilitations.
	 *
	 * @return List(Habilitation) : habilitations
	 */
	public List<Habilitation> getHabilitations() {
		return habilitations;
	}

	/**
	 * Defines the list of habilitations.
	 *
	 * @param habilitations (List(Habilitation)) : habilitations
	 */
	public void setHabilitations(List<Habilitation> habilitations) {
		this.habilitations = habilitations;
	}

	/**
	 * Returns mobile token.
	 *
	 * @return String : token
	 */
	public String getMobileToken() {
		return mobileToken;
	}

	/**
	 * Defines mobile token.
	 *
	 * @param mobileToken (String) : token
	 */
	public void setMobileToken(String mobileToken) {
		this.mobileToken = mobileToken;
	}
}
