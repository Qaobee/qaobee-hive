/*************************************************************************
 * 
 * Qaobee
 * __________________
 * 
 * [2014] Qaobee
 * All Rights Reserved.
 * 
 * NOTICE: All information contained here is, and remains
 * the property of Qaobee and its suppliers,
 * if any. The intellectual and technical concepts contained
 * here are proprietary to Qaobee and its suppliers and may
 * be covered by U.S. and Foreign Patents, patents in process,
 * and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Qaobee.
 */
package com.qaobee.hive.business.model.sandbox.training;

import java.util.List;

import com.qaobee.hive.business.model.commons.settings.CategoryAge;
import com.qaobee.hive.business.model.commons.users.User;
import com.qaobee.hive.business.model.commons.users.communication.network.Comment;
import com.qaobee.hive.business.model.commons.users.communication.network.Sharing;
import com.qaobee.hive.business.model.transversal.Audit;
import com.qaobee.hive.business.model.transversal.Tag;

/**
 * The Class TrainingAbstract.
 */
public class TrainingAbstract {

	/** The _id. */
	private String _id;

	/** label. */
	private String label;

	/** History. */
	private User author;

	/** Create date. */
	private long datCreate;

	/** Age category. */
	private CategoryAge categoryAge;

	/** Duration of session. */
	private long duration;

	/** Status. */
	private WorkflowStatusEnum wkflStatus;

	/** Comment's list. */
	private List<Comment> commentList;

	/** Sharing. */
	private Sharing sharing;

	/** History. */
	private List<History> historyList;
	
	/**
     * The list of tags for object.
     */
    private List<Tag> tags;
	
	/** audit CRUD object */
	private Audit audit;

	/**
	 * Instantiates a new education abstract.
	 */
	public TrainingAbstract() {
		super();
	}

	/**
	 * Gets the _id.
	 *
	 * @return the _id
	 */
	public String get_id() {
		return _id;
	}

	/**
	 * Sets the _id.
	 *
	 * @param _id
	 *            the _id to set
	 */
	public void set_id(final String _id) {
		this._id = _id;
	}

	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Sets the label.
	 *
	 * @param label
	 *            the new label
	 */
	public void setLabel(final String label) {
		this.label = label;
	}

	/**
	 * @return the categoryAge
	 */
	public CategoryAge getCategoryAge() {
		return categoryAge;
	}

	/**
	 * Sets the age category.
	 *
	 * @param categoryAge
	 *            the new age category
	 */
	public void setCategoryAge(final CategoryAge categoryAge) {
		this.categoryAge = categoryAge;
	}

	/**
	 * Gets the duration of session.
	 *
	 * @return the duration of session
	 */
	public long getDuration() {
		return duration;
	}

	/**
	 * Sets the duration of session.
	 *
	 * @param duration
	 *            the new duration of session
	 */
	public void setDuration(final long duration) {
		this.duration = duration;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public WorkflowStatusEnum getWkflStatus() {
		return wkflStatus;
	}

	/**
	 * Sets the status.
	 *
	 * @param wkflStatus
	 *            the new status
	 */
	public void setWkflStatus(final WorkflowStatusEnum wkflStatus) {
		this.wkflStatus = wkflStatus;
	}

	/**
	 * Gets the comment's list.
	 *
	 * @return the comment's list
	 */
	public final List<Comment> getCommentList() {
		return commentList;
	}

	/**
	 * Sets the comment's list.
	 *
	 * @param commentList
	 *            the new comment's list
	 */
	public final void setCommentList(final List<Comment> commentList) {
		this.commentList = commentList;
	}

	/**
	 * Gets the sharing.
	 *
	 * @return the sharing
	 */
	public final Sharing getSharing() {
		return sharing;
	}

	/**
	 * Sets the sharing.
	 *
	 * @param sharing
	 *            the new sharing
	 */
	public final void setSharing(final Sharing sharing) {
		this.sharing = sharing;
	}

	/**
	 * @return the datCreate
	 */
	public long getDatCreate() {
		return datCreate;
	}

	/**
	 * @param datCreate
	 *            the datCreate to set
	 */
	public void setDatCreate(long datCreate) {
		this.datCreate = datCreate;
	}

	/**
	 * @return the historyList
	 */
	public List<History> getHistoryList() {
		return historyList;
	}

	/**
	 * @param historyList
	 *            the historyList to set
	 */
	public void setHistoryList(List<History> historyList) {
		this.historyList = historyList;
	}

	/**
	 * @return the author
	 */
	public User getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(User author) {
		this.author = author;
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

}