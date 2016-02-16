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

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.qaobee.hive.business.model.transversal.Audit;
import com.qaobee.hive.business.model.transversal.Tag;

/**
 * Bean that describes a SandBox championship.
 * @author Nada Vujanic-Maquin
 * @version V1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SB_Championship { // NOSONAR

	/** Internal identifier */
	private String _id; // NOSONAR
	/** Label */
	private String label;
	/** List labels */
	private List<Tag> labels;
	/** audit CRUD object */
	private Audit audit;

	/**
	 * Returns the internal identifier of the championship.
	 * @return String : internal identifier
	 */
	public String get_id() { // NOSONAR
		return _id;
	}

	/**
	 * Defines the internal identifier of the championship.
	 * @param _id (String) : identifier
	 */
	public void set_id(String _id) { // NOSONAR
		this._id = _id;
	}

	/**
	 * Returns the label of the championship.
	 * @return String : label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Defines the label of the championship.
	 * @param label (String) : label
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Returns the list of labels.
	 * @return List(Tag) : list
	 */
	public List<Tag> getLabels() {
		return labels;
	}

	/**
	 * Defines the list of labels.
	 * @param labels (List(Tag)) : list
	 */
	public void setLabels(List<Tag> labels) {
		this.labels = labels;
	}

	/**
	 * Adds a label.
	 * @param label (Tag) : label
	 */
	public void addLabel(Tag label) {
		if(labels==null) {
			labels = new ArrayList<>();
		}
		labels.add(label);
	}
	
	/**
	 * Returns the audit trail.
	 * @return Audit : audit
	 */
	public Audit getAudit() {
		return audit;
	}

	/**
	 * Defines the audit trail.
	 * @param audit (Audit) : audit
	 */
	public void setAudit(Audit audit) {
		this.audit = audit;
	}

}
