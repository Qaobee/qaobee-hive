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
package com.qaobee.hive.business.model.commons.referencial;

import java.util.List;

import com.qaobee.hive.business.model.commons.settings.Activity;
import com.qaobee.hive.business.model.commons.settings.CategoryAge;
import com.qaobee.hive.business.model.transversal.Audit;

/**
 * @author cke
 *
 */
public class Team {
	
	// Declaration des variables
    /**
     * The _id.
     */
    private String _id;

    /**
     * The label.
     */
    private String label;

    /**
     * activity code of the Team.
     */
    private Activity activity;
    
    /**
     * Structure of the Team
     */
    private Structure structure;
    
    /**
     * CategoryAge of the Team
     */
    private CategoryAge categoryAge;
    
    /**
     * The seasons of the Team
     */
    private List<TeamSeason> teamSeasons;
    
    /** audit CRUD object */
	private Audit audit;

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
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the activity
	 */
	public Activity getActivity() {
		return activity;
	}

	/**
	 * @param activity the activity to set
	 */
	public void setActivity(Activity activity) {
		this.activity = activity;
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

	/**
	 * @return the categoryAge
	 */
	public CategoryAge getCategoryAge() {
		return categoryAge;
	}

	/**
	 * @param categoryAge the categoryAge to set
	 */
	public void setCategoryAge(CategoryAge categoryAge) {
		this.categoryAge = categoryAge;
	}

	/**
	 * @return the teamSeasons
	 */
	public List<TeamSeason> getTeamSeasons() {
		return teamSeasons;
	}

	/**
	 * @param teamSeasons the teamSeasons to set
	 */
	public void setTeamSeasons(List<TeamSeason> teamSeasons) {
		this.teamSeasons = teamSeasons;
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
