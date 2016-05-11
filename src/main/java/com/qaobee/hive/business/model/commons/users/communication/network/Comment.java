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

/**
 * The object used to draw comment exchanges on an artifact. An artifact can be an exercise, a meeting, a match, a team composition etc ...
 *
 * @author cke
 */

public class Comment {

    /**
     * The _id.
     */
    private String _id; // NOSONAR

    /**
     * Author Id.
     */
    private String authorId;

    /**
     * Create date.
     */
    private long datCreate;

    /**
     * content.
     */
    private String content;

    /**
     * content max size.
     */
    private int contentMaxsize;

    /**
     * Comment's status.
     */
    private CommentStatusEnum commentStatus;

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
     * Gets the content.
     *
     * @return the content
     */
    public final String getContent() {
        return content;
    }

    /**
     * Sets the content.
     *
     * @param content the new content
     */
    public final void setContent(final String content) {
        this.content = content;
    }

    /**
     * Gets the content maxsize.
     *
     * @return the content maxsize
     */
    public final int getContentMaxsize() {
        return contentMaxsize;
    }

    /**
     * Sets the content maxsize.
     *
     * @param contentMaxsize the new content maxsize
     */
    public final void setContentMaxsize(final int contentMaxsize) {
        this.contentMaxsize = contentMaxsize;
    }

    /**
     * Gets the comment's status.
     *
     * @return the comment's status
     */
    public final CommentStatusEnum getCommentStatus() {
        return commentStatus;
    }

    /**
     * Sets the comment's status.
     *
     * @param commentStatus the new comment's status
     */
    public final void setCommentStatus(final CommentStatusEnum commentStatus) {
        this.commentStatus = commentStatus;
    }

    /**
     * @return the authorId
     */
    public String getAuthorId() {
        return authorId;
    }

    /**
     * @param authorId the authorId to set
     */
    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

}
