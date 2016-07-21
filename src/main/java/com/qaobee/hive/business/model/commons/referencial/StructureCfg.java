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
import com.qaobee.hive.business.model.commons.settings.Season;
import com.qaobee.hive.business.model.transversal.Audit;

import java.util.ArrayList;
import java.util.List;

/**
 * Bean that describes the structure configuration.
 *
 * @author cke
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StructureCfg {

    /**
     * Internal identifier
     */
    private String _id; // NOSONAR
    /**
     * Structure
     */
    private Structure structure;
    /**
     * Season
     */
    private Season season;
    /**
     * List of teams
     */
    private List<Team> teams;
    /**
     * List of infraStructures
     */
    private List<InfraStructure> infraStructures;
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
     * Returns the list of teams.
     *
     * @return List(Team) : list
     */
    public List<Team> getTeams() {
        return teams;
    }

    /**
     * Defines the list of teams.
     *
     * @param teams (List(Team)) : list
     */
    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    /**
     * Adds a team to the list.
     *
     * @param team (Team) : team
     */
    public void addTeam(Team team) {
        if (teams == null) {
            teams = new ArrayList<>();
        }
        teams.add(team);
    }

    /**
     * Returns the list of infrastructures.
     *
     * @return List(InfraStructure) : list
     */
    public List<InfraStructure> getInfraStructures() {
        return infraStructures;
    }

    /**
     * Defines the list of infrastructures.
     *
     * @param infraStructures (List(InfraStructure)) : list
     */
    public void setInfraStructures(List<InfraStructure> infraStructures) {
        this.infraStructures = infraStructures;
    }

    /**
     * Adds an infrastructure to the list.
     *
     * @param infraStructure (InfraStructure) : infra
     */
    public void addInfraStructure(InfraStructure infraStructure) {
        if (infraStructures == null) {
            infraStructures = new ArrayList<>();
        }
        infraStructures.add(infraStructure);
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
