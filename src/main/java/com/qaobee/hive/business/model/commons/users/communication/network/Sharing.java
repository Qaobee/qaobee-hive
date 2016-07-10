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
package com.qaobee.hive.business.model.commons.users.communication.network;

import com.qaobee.hive.business.model.commons.users.User;

import java.util.List;

/**
 * The Class Sharing.
 *
 * @author cke
 */
@Deprecated
public class Sharing {

    /**
     * The _id.
     */
    private String _id; // NOSONAR

    /**
     * Sharing's user.
     */
    private User user;

    /**
     * Create date.
     */
    private long datCreate;

    /**
     * Sharing's scope.
     */
    private SharingScopeEnum sharingScope;

    /**
     * Sharing's list Member.
     */
    private List<User> sharingListMembers;

    /**
     * Sharing's circle of friends of author.
     */
    private List<CircleFriend> sharingCircle;

    /**
     * Gets the _id.
     *
     * @return the _id
     */
    public final String get_id() { // NOSONAR
        return _id;
    }

    /**
     * Sets the _id.
     *
     * @param _id the _id to set
     */
    public final void set_id(final String _id) { // NOSONAR
        this._id = _id;
    }

    /**
     * Gets the create date.
     *
     * @return the create date
     */
    public final long getDatCreate() {
        return datCreate;
    }

    /**
     * Sets the create date.
     *
     * @param datCreate the new create date
     */
    public final void setDatCreate(final long datCreate) {
        this.datCreate = datCreate;
    }

    /**
     * Gets the sharing's list Member.
     *
     * @return the sharing's list Member
     */
    public final List<User> getSharingListMembers() {
        return sharingListMembers;
    }

    /**
     * Sets the sharing's list Member.
     *
     * @param sharingListMembers the new sharing's list Member
     */
    public final void setSharingListMembers(final List<User> sharingListMembers) {
        this.sharingListMembers = sharingListMembers;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets the sharing's circle of friends of author.
     *
     * @return the sharing's circle of friends of author
     */
    public final List<CircleFriend> getSharingCircle() {
        return sharingCircle;
    }

    /**
     * Sets the sharing's circle of friends of author.
     *
     * @param sharingCircle the new sharing's circle of friends of author
     */
    public final void setSharingCircle(final List<CircleFriend> sharingCircle) {
        this.sharingCircle = sharingCircle;
    }

    /**
     * Gets the sharing's scope.
     *
     * @return the sharing's scope
     */
    public final SharingScopeEnum getSharingScope() {
        return sharingScope;
    }

    /**
     * Sets the sharing's scope.
     *
     * @param sharingScope the new sharing's scope
     */
    public final void setSharingScope(final SharingScopeEnum sharingScope) {
        this.sharingScope = sharingScope;
    }

}
