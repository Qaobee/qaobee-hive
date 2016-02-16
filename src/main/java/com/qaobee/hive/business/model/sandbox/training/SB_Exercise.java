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

import java.io.File;

/**
 * The Class Exercise.
 *
 * @author Mohamed EL MARZGIOUI
 */
public class SB_Exercise extends TrainingAbstract { // NOSONAR

	/** Objective. */
	private String objective;

	/** setting Up. */
	private String settingUp;

	/** Execution's variables . */
	private String executionVariables;

	/** File . */
	private File file;

	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * @param file
	 *            the file to set
	 */
	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * @return the objective
	 */
	public String getObjective() {
		return objective;
	}

	/**
	 * @param objective
	 *            the objective to set
	 */
	public void setObjective(String objective) {
		this.objective = objective;
	}

	/**
	 * @return the settingUp
	 */
	public String getSettingUp() {
		return settingUp;
	}

	/**
	 * @param settingUp
	 *            the settingUp to set
	 */
	public void setSettingUp(String settingUp) {
		this.settingUp = settingUp;
	}

	/**
	 * @return the executionVariables
	 */
	public String getExecutionVariables() {
		return executionVariables;
	}

	/**
	 * @param executionVariables
	 *            the executionVariables to set
	 */
	public void setExecutionVariables(String executionVariables) {
		this.executionVariables = executionVariables;
	}

	/**
	 * @return the instructions
	 */
	public String getInstructions() {
		return instructions;
	}

	/**
	 * @param instructions
	 *            the instructions to set
	 */
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	/**
	 * @return the criteriaSuccess
	 */
	public String getCriteriaSuccess() {
		return criteriaSuccess;
	}

	/**
	 * @param criteriaSuccess
	 *            the criteriaSuccess to set
	 */
	public void setCriteriaSuccess(String criteriaSuccess) {
		this.criteriaSuccess = criteriaSuccess;
	}

	/** instructions. */
	private String instructions;

	/** criteria of success. */
	private String criteriaSuccess;

}
