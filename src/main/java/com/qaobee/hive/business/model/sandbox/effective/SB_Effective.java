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

package com.qaobee.hive.business.model.sandbox.effective;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.qaobee.hive.business.model.commons.settings.CategoryAge;
import com.qaobee.hive.business.model.transversal.Audit;
import com.qaobee.hive.business.model.transversal.Member;

import java.util.ArrayList;
import java.util.List;

/**
 * Bean that describes a SandBox Effective.
 *
 * @author jerome
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SB_Effective { // NOSONAR

    /**
     * Internal identifier.
     */
    private String _id; // NOSONAR
    /**
     * Sandbox's id
     */
    private String sandboxId;
    
    /**
     * Age category.
     */
    private CategoryAge categoryAge;
    /**
     * List of persons that composed the current group
     */
    private List<Member> members;
    /**
     * Label of the effective
     */
    private String label;
    /**
     * audit CRUD object
     */
    private Audit audit;

    /**
     * Return the internal identifier.
     *
     * @return the _id (String) : ID
     */
    public final String get_id() { // NOSONAR
        return _id;
    }

    /**
     * Defines the internal identifier.
     *
     * @param _id (String) : ID
     */
    public final void set_id(String _id) { // NOSONAR
        this._id = _id;
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
     * Returns the list of members.
     *
     * @return List(Member) : list
     */
    public List<Member> getMembers() {
        return members;
    }

    /**
     * Defines the list of members.
     *
     * @param members (List(Member)) : list
     */
    public void setMembers(List<Member> members) {
        this.members = members;
    }

    /**
     * Adds a member to the effective list.
     *
     * @param member (Member) : member
     */
    public void addMember(Member member) {
        if (members == null) {
            members = new ArrayList<>();
        }
        members.add(member);
    }

    /**
     * Returns the sandbox ID.
     *
     * @return String : ID
     */
    public String getSandboxId() {
        return sandboxId;
    }

    /**
     * Defines the sandbox ID.
     *
     * @param sandBoxId (String) : ID
     */
    public void setSandboxId(String sandBoxId) {
        this.sandboxId = sandBoxId;
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
