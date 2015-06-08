package com.qaobee.hive.business.model.sandbox.training;

import java.io.File;

/**
 * The Class Exercise.
 *
 * @author Mohamed EL MARZGIOUI
 */
public class Exercise extends TrainingAbstract {

	/** Theme */
	private Theme theme;
	/** Subtheme */
	private SubTheme subTheme;
	/** Objective. */
	private String objective;

	/** setting Up. */
	private String settingUp;

	/** Execution's variables . */
	private String executionVariables;

	/** File . */
	private File file;

	/**
	 * @return the theme
	 */
	public Theme getTheme() {
		return theme;
	}

	/**
	 * @param theme
	 *            the theme to set
	 */
	public void setTheme(Theme theme) {
		this.theme = theme;
	}

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
	 * @return the subTheme
	 */
	public SubTheme getSubTheme() {
		return subTheme;
	}

	/**
	 * @param subTheme
	 *            the subTheme to set
	 */
	public void setSubTheme(SubTheme subTheme) {
		this.subTheme = subTheme;
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
