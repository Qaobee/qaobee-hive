/*************************************************************************
 * Qaobee
 * __________________
 * <p/>
 * [2015] Qaobee
 * All Rights Reserved.
 * <p/>
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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.qaobee.hive.business.model.commons.settings.Activity;
import com.qaobee.hive.business.model.commons.settings.CategoryAge;
import com.qaobee.hive.business.model.commons.settings.Season;
import com.qaobee.hive.business.model.transversal.Audit;
import com.qaobee.hive.business.model.transversal.Tag;

import java.util.ArrayList;
import java.util.List;

/**
 * Bean that describes a championship.
 *
 * @author cke
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChampionShip {

    /**
     * Internal identifier
     */
    private String _id; // NOSONAR
    /**
     * Activity
     */
    private Activity activity;
    /**
     * Age category
     */
    private CategoryAge categoryAge;
    /**
     * Code
     */
    private String code;
    /**
     * Label
     */
    private String label;
    /**
     * List of tags for determinate championShip
     */
    private List<Tag> tags;
    /**
     * List of journey for a championShip
     */
    private List<ChampionShipJourney> journeys;
    /**
     * Season
     */
    private Season season;
    /**
     * Audit CRUD
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
     * Returns the championship activity.
     *
     * @return Activity : activity
     */
    public Activity getActivity() {
        return activity;
    }

    /**
     * Defines the championship activity.
     *
     * @param activity (Activity) : activity
     */
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    /**
     * Returns the championship age category.
     *
     * @return CategoryAge : age category
     */
    public CategoryAge getCategoryAge() {
        return categoryAge;
    }

    /**
     * Defines the championship age category.
     *
     * @param categoryAge (CategoryAge) : age category
     */
    public void setCategoryAge(CategoryAge categoryAge) {
        this.categoryAge = categoryAge;
    }

    /**
     * Returns the championship code.
     *
     * @return String : code
     */
    public String getCode() {
        return code;
    }

    /**
     * Defines the championship code.
     *
     * @param code (String) : code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Returns the championship label.
     *
     * @return String : label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Defines the championship label.
     *
     * @param label (String) : label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Retuns the list of tags.
     *
     * @return List(Tag) : list
     */
    public List<Tag> getTags() {
        return tags;
    }

    /**
     * Defines the list of tags.
     *
     * @param tags (List(Tag)) : list
     */
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    /**
     * Adds a tag to the list.
     *
     * @param tag (Tag) : tag to add
     */
    public void addTag(Tag tag) {
        if (tags == null) {
            tags = new ArrayList<>();
        }
        tags.add(tag);
    }

    /**
     * Returns the list of journeys.
     *
     * @return List(ChampionShipJourney) : list
     */
    public List<ChampionShipJourney> getJourneys() {
        return journeys;
    }

    /**
     * Defines the list of journeys.
     *
     * @param journeys (List(ChampionShipJourney)) : list
     */
    public void setJourneys(List<ChampionShipJourney> journeys) {
        this.journeys = journeys;
    }

    /**
     * Adds a journey to the list.
     *
     * @param journey (ChampionShipJourney) : journey
     */
    public void addJourney(ChampionShipJourney journey) {
        if (journeys == null) {
            journeys = new ArrayList<>();
        }
        journeys.add(journey);
    }

    /**
     * Returns the season.
     *
     * @return Season : season
     */
    public Season getSeason() {
        return season;
    }

    /**
     * Defines the season.
     *
     * @param season (Season) : season
     */
    public void setSeason(Season season) {
        this.season = season;
    }

    /**
     * Returns the audit.
     *
     * @return Audit :  audit
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
