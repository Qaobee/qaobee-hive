/*************************************************************************
 * 
 * Qaobee
 * __________________
 * 
 * [2014] Qaobee
 * All Rights Reserved.
 * 
 * NOTICE:  All information contained here is, and remains
 * the property of Qaobee and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * here are proprietary to Qaobee and its suppliers and may 
 * be covered by U.S. and Foreign Patents, patents in process,
 * and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Qaobee.
 */
package com.qaobee.technical.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * The Interface Deployable.
 *
 * @author Xavier Marin
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DeployableVerticle {
	
	/**
	 * Config.
	 *
	 * @return the config name
	 */
	String config() default "";
}
