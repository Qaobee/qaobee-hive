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
package com.qaobee.hive.business.model.sandbox.training;

/**
 * The Enum WorkflowStatusEnum.
 */
public enum WorkflowStatusEnum {

	/** In progress status. */
	INPROGRESS("education.status.inprogress"),

	/** submitted status. */
	SUBMITTED("education.status.submitted"),

	/** Validate status. */
	VALIDATED("education.status.validated"),

	/** Cancelled status. */
	CANCELLED("education.status.cancelled");

	/** workflowStatus. */
	private String workflowStatus;

	/**
	 * Constructor.
	 *
	 * @param workflowStatus
	 *            (String) : workflowStatus in i18n
	 */
	WorkflowStatusEnum(final String workflowStatus) {
		this.workflowStatus = workflowStatus;
	}

	/**
	 * Gets the workflowStatus.
	 *
	 * @return the workflowStatus
	 */
	public String getWorkflowStatus() {
		return workflowStatus;
	}

}
