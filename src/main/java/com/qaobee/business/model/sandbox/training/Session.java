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
package com.qaobee.business.model.sandbox.training;

import java.util.List;

/**
 * The Class Session.
 *
 * @author cke
 */
public class Session extends TrainingAbstract {

	/** List exerciseSession. */
	private List<ExerciseSession> exerciseSessionList;

	/**
	 * @return the exerciseSessionList
	 */
	public List<ExerciseSession> getExerciseSessionList() {
		return exerciseSessionList;
	}

	/**
	 * @param exerciseSessionList
	 *            the exerciseSessionList to set
	 */
	public void setExerciseSessionList(List<ExerciseSession> exerciseSessionList) {
		this.exerciseSessionList = exerciseSessionList;
	}

}
