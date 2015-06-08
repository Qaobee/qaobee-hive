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
package com.qaobee.hive.business.model.sandbox.config;

import com.qaobee.hive.business.model.commons.referencial.Structure;
import com.qaobee.hive.business.model.commons.settings.Season;

/**
 * @author cke
 *
 */
public class SandBoxCfg {
	
	private String _id;
	
	private Structure structure;
	
	private SandBox sandBox;
	
	private Season season;

	/**
	 * @return the _id
	 */
	public String get_id() {
		return _id;
	}

	/**
	 * @param _id the _id to set
	 */
	public void set_id(String _id) {
		this._id = _id;
	}

	/**
	 * @return the season
	 */
	public Season getSeason() {
		return season;
	}

	/**
	 * @param season the season to set
	 */
	public void setSeason(Season season) {
		this.season = season;
	}

	/**
	 * @return the sandBox
	 */
	public SandBox getSandBox() {
		return sandBox;
	}

	/**
	 * @param sandBox the sandBox to set
	 */
	public void setSandBox(SandBox sandBox) {
		this.sandBox = sandBox;
	}

	/**
	 * @return the structure
	 */
	public Structure getStructure() {
		return structure;
	}

	/**
	 * @param structure the structure to set
	 */
	public void setStructure(Structure structure) {
		this.structure = structure;
	}

	

}
