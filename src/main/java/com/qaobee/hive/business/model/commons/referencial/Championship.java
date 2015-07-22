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
import com.qaobee.hive.business.model.commons.settings.Season;
import com.qaobee.hive.business.model.transversal.Audit;
import com.qaobee.hive.business.model.transversal.Tag;

/**
 * @author cke
 *
 */
public class Championship {
	
	/**
     * The _id.
     */
    private String _id;
    
    /**
     * activity of the ChampionShip.
     */
    private Activity activity;

    
    /**
     * Category Age of the ChampionShip
     */
    private CategoryAge categoryAge;
    
    /**
     * The code.
     */
    private String code;
    
    /**
     * The label of 
     */
    private String label;
    
    /**
     * The list of tags for determinate championShip.
     */
    private List<Tag> tags;
    
    /**
     * The list of journey for a championShip.
     */
    private List<ChampionShipJourney> journeys;
    
    /** 
     * Season. 
     */
	private Season season;
	
	/** 
	 * audit CRUD object 
	 */
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
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
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
	 * @return the tags
	 */
	public List<Tag> getTags() {
		return tags;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	/**
	 * @return the journeys
	 */
	public List<ChampionShipJourney> getJourneys() {
		return journeys;
	}

	/**
	 * @param journeys the journeys to set
	 */
	public void setJourneys(List<ChampionShipJourney> journeys) {
		this.journeys = journeys;
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
