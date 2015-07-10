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

package com.qaobee.hive.business.model.sandbox.competition;

import java.util.List;

import com.qaobee.hive.business.model.transversal.Audit;
import com.qaobee.hive.business.model.transversal.Tag;

/**
 * The Class Championship.
 *
 * @author Nada Vujanic-Maquin
 * @version V1.0
 */

public class SB_Championship {

	// Declaration des variables

	/** Internal identifier. */
	private String _id;

	/** The label. */
	private String label;
	
	/** 
	 * List labels
	 */
	private List<Tag> labels;
	
	/** 
	 * audit CRUD object 
	 */
	private Audit audit;

	// Getters and Setters

	/**
	 * Returns the internal identifier of the championship.
	 * 
	 * @return String : internal identifier
	 */
	public String get_id() {
		return _id;
	}

	/**
	 * Defines the internal identifier of the championship.
	 * 
	 * @param _id
	 *            (String) : identifier
	 */
	public void set_id(String _id) {
		this._id = _id;
	}

	/**
	 * Returns the label of the championship.
	 *
	 * @return String : label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Defines the label of the championship.
	 *
	 * @param label
	 *            (String) : label
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the labels
	 */
	public List<Tag> getLabels() {
		return labels;
	}

	/**
	 * @param labels the labels to set
	 */
	public void setLabels(List<Tag> labels) {
		this.labels = labels;
	}

	/**
	 * @return the audit
	 */
	public Audit getAudit() {
		return audit;
	}

	/**
	 * @param audit the audit to set
	 */
	public void setAudit(Audit audit) {
		this.audit = audit;
	}

}
