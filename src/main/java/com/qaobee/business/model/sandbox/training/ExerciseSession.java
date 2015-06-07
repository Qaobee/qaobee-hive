/*************************************************************************
 * 
 * Qaobee
 * 
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

/**
 * The Class Exercise.
 *
 * @author cke
 */
public class ExerciseSession extends TrainingAbstract {

	/** order number. */
	private int orderNumber;

	/** Exercice */
	private Exercise exercise;

	/**
	 * @return the exercise
	 */
	public Exercise getExercise() {
		return exercise;
	}

	/**
	 * @param exercise
	 *            the exercise to set
	 */
	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}

	/**
	 * Gets the order number.
	 *
	 * @return the order number
	 */
	public final int getOrderNumber() {
		return orderNumber;
	}

	/**
	 * Sets the order number.
	 *
	 * @param orderNumber
	 *            the new order number
	 */
	public final void setOrderNumber(final int orderNumber) {
		this.orderNumber = orderNumber;
	}

}
