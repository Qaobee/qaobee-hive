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
package com.qaobee.hive.business.model.sandbox.config;

import com.qaobee.hive.business.model.commons.referencial.Structure;
import com.qaobee.hive.business.model.commons.settings.Season;
import com.qaobee.hive.business.model.transversal.Member;

import java.util.List;

/**
 * The type Sand box cfg.
 *
 * @author cke
 */
public class SB_SandBoxCfg {

    /**
     * The _ id.
     */
    private String _id;

    /**
     * The Structure.
     */
    private Structure structure;

    /**
     * The Sand box.
     */
    private SB_SandBox sandBox;

    /**
     * The Season.
     */
    private Season season;

    /**
     * The Members.
     */
    private List<Member> members;

    /**
     * Gets _ id.
     *
     * @return the _id
     */
    public String get_id() {
        return _id;
    }

    /**
     * Sets _ id.
     *
     * @param _id the _id to set
     */
    public void set_id(String _id) {
        this._id = _id;
    }

    /**
     * Gets season.
     *
     * @return the season
     */
    public Season getSeason() {
        return season;
    }

    /**
     * Sets season.
     *
     * @param season the season to set
     */
    public void setSeason(Season season) {
        this.season = season;
    }

    /**
     * Gets sand box.
     *
     * @return the sandBox
     */
    public SB_SandBox getSandBox() {
        return sandBox;
    }

    /**
     * Sets sand box.
     *
     * @param sandBox the sandBox to set
     */
    public void setSandBox(SB_SandBox sandBox) {
        this.sandBox = sandBox;
    }

    /**
     * Gets structure.
     *
     * @return the structure
     */
    public Structure getStructure() {
        return structure;
    }

    /**
     * Sets structure.
     *
     * @param structure the structure to set
     */
    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    /**
     * Gets members.
     *
     * @return the members
     */
    public List<Member> getMembers() {
        return members;
    }

    /**
     * Sets members.
     *
     * @param members the members to set
     */
    public void setMembers(List<Member> members) {
        this.members = members;
    }


}
