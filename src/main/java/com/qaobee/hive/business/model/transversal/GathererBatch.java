/*************************************************************************
 * 
 * Qaobee
 * __________________
 * 
 * [2015] Qaobee
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
package com.qaobee.hive.business.model.transversal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Bean that describes data from Gatherer Batch.
 * @author jerome
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GathererBatch {

	/** Date processing batch */
	protected String batchProcessDate;
	/** Action made by processing batch */
	protected String batchProcessAction;
	
	/**
	 * Returns date for processing batch.
	 * @return String : date in YYYY-MM-DD format.
	 */
	public String getBatchProcessDate() {
		return batchProcessDate;
	}
	
	/**
	 * Defines date for processing batch.
	 * @param batchProcessDate (String)  :date in YYYY-MM-DD format
	 */
	public void setBatchProcessDate(String batchProcessDate) {
		this.batchProcessDate = batchProcessDate;
	}
	
	/**
	 * Returns action made by processing batch.
	 * @return String : action (CREATED, UPDATED, NO_CHANGE)
	 */
	public String getBatchProcessAction() {
		return batchProcessAction;
	}
	
	/**
	 * Defines action made by processing batch.
	 * @param batchProcessAction (String) : action (CREATED, UPDATED, NO_CHANGE)
	 */
	public void setBatchProcessAction(String batchProcessAction) {
		this.batchProcessAction = batchProcessAction;
	}
	
}
