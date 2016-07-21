/*
 *   __________________
 *    Qaobee
 *    __________________
 *
 *    Copyright (c) 2015.  Qaobee
 *    All Rights Reserved.
 *
 *    NOTICE: All information contained here is, and remains
 *    the property of Qaobee and its suppliers,
 *    if any. The intellectual and technical concepts contained
 *    here are proprietary to Qaobee and its suppliers and may
 *    be covered by U.S. and Foreign Patents, patents in process,
 *    and are protected by trade secret or copyright law.
 *    Dissemination of this information or reproduction of this material
 *    is strictly forbidden unless prior written permission is obtained
 *    from Qaobee.
 */

package com.qaobee.hive.business.model.commons.referencial;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.qaobee.hive.business.model.commons.settings.Activity;
import com.qaobee.hive.business.model.commons.settings.CategoryAge;
import com.qaobee.hive.business.model.transversal.Audit;

import java.util.ArrayList;
import java.util.List;

/**
 * Bean that describes a team.
 *
 * @author cke
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Team {

    /**
     * Internal identifier
     */
    private String _id; // NOSONAR
    /**
     * Label
     */
    private String label;
    /**
     * Activity of the Team
     */
    private Activity activity;
    /**
     * Structure of the Team
     */
    private Structure structure;
    /**
     * Age category of the Team
     */
    private CategoryAge categoryAge;
    /**
     * List of Seasons
     */
    private List<TeamSeason> teamSeasons;
    /**
     * audit CRUD object
     */
    private Audit audit;

    /**
     * Returns the internal identifier.
     *
     * @return String : ID
     */
    public String get_id() { // NOSONAR
        return _id;
    }

    /**
     * Defines the internal identifier.
     *
     * @param _id (String) : ID
     */
    public void set_id(String _id) { // NOSONAR
        this._id = _id;
    }

    /**
     * Returns the label.
     *
     * @return String : label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Defines the label.
     *
     * @param label (String) : label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Returns the activity.
     *
     * @return Activity : activity
     */
    public Activity getActivity() {
        return activity;
    }

    /**
     * Defines the activity.
     *
     * @param activity (Activity) : activity
     */
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    /**
     * Returns the structure.
     *
     * @return Structure : structure
     */
    public Structure getStructure() {
        return structure;
    }

    /**
     * Defines the structure.
     *
     * @param structure (Structure) : structure
     */
    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    /**
     * Returns the age category.
     *
     * @return CategoryAge : age category
     */
    public CategoryAge getCategoryAge() {
        return categoryAge;
    }

    /**
     * Defines the age category.
     *
     * @param categoryAge (CategoryAge) : age category
     */
    public void setCategoryAge(CategoryAge categoryAge) {
        this.categoryAge = categoryAge;
    }

    /**
     * Returns the list of seasons.
     *
     * @return List(TeamSeason) : list
     */
    public List<TeamSeason> getTeamSeasons() {
        return teamSeasons;
    }

    /**
     * Defines the list of seasons.
     *
     * @param teamSeasons (List(TeamSeason)) : list
     */
    public void setTeamSeasons(List<TeamSeason> teamSeasons) {
        this.teamSeasons = teamSeasons;
    }

    /**
     * Adds a season to the list.
     *
     * @param teamSeason (TeamSeason) : season
     */
    public void addTeamSeason(TeamSeason teamSeason) {
        if (teamSeasons == null) {
            teamSeasons = new ArrayList<>();
        }
        teamSeasons.add(teamSeason);
    }

    /**
     * Returns the audit.
     *
     * @return Audit : audit
     */
    public Audit getAudit() {
        return audit;
    }

    /**
     * Defines the audit.
     *
     * @param audit (Audit) : audit
     */
    public void setAudit(Audit audit) {
        this.audit = audit;
    }

}
