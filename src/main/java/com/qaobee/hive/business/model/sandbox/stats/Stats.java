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
package com.qaobee.hive.business.model.sandbox.stats;

import java.util.List;

/**
 * The Class Stats.
 * 
 * @author cke
 * @updated by Jerome
 */
public class Stats {

	/** The id. */
	private String _id;

	/** The code. */
	private String code;
	
	/** sharding key */
	private String chrono;

	/** Timer. */
	private long timer;

	/** owner */
	private String owner;

	/** producter */
	private List<String> producter;

	/** sandbox ID */
	private String sandBoxId;

	/** activity ID */
	private String activityId;

	/** event identifier */
	private String eventId;

	/** value */
	private String value;

	/**
	 * @return the _id
	 */
	public final String get_id() {
		return _id;
	}

	/**
	 * @param _id
	 *            the _id to set
	 */
	public final void set_id(String _id) {
		this._id = _id;
	}

	/**
	 * @return the code
	 */
	public final String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public final void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * @return the chrono
	 */
	public String getChrono() {
		return chrono;
	}

	/**
	 * @param chrono the chrono to set
	 */
	public void setChrono(String chrono) {
		this.chrono = chrono;
	}

	/**
	 * @return the timer
	 */
	public final long getTimer() {
		return timer;
	}

	/**
	 * @param timer
	 *            the timer to set
	 */
	public final void setTimer(long timer) {
		this.timer = timer;
	}

	/**
	 * @return the owner
	 */
	public final String getOwner() {
		return owner;
	}

	/**
	 * @param owner
	 *            the owner to set
	 */
	public final void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * @return the sandBoxId
	 */
	public String getSandBoxId() {
		return sandBoxId;
	}

	/**
	 * @param sandBoxId the sandBoxId to set
	 */
	public void setSandBoxId(String sandBoxId) {
		this.sandBoxId = sandBoxId;
	}

	/**
	 * @return the activityId
	 */
	public final String getActivityId() {
		return activityId;
	}

	/**
	 * @param activityId
	 *            the activityId to set
	 */
	public final void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	/**
	 * @return the value
	 */
	public final String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public final void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the eventId
	 */
	public final String getEventId() {
		return eventId;
	}

	/**
	 * @param eventId
	 *            the eventId to set
	 */
	public final void setEventId(String eventId) {
		this.eventId = eventId;
	}

	/**
	 * @return the producter
	 */
	public final List<String> getProducter() {
		return producter;
	}

	/**
	 * @param producter
	 *            the producter to set
	 */
	public final void setProducter(List<String> producter) {
		this.producter = producter;
	}
}