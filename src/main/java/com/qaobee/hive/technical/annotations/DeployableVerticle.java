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
package com.qaobee.hive.technical.annotations;

import org.reflections.Reflections;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Set;

/**
 * The interface Deployable verticle.
 */
@Documented @Retention(RetentionPolicy.RUNTIME) public @interface DeployableVerticle {
	/**
	 * Config string.
	 *
	 * @return the string
	 */
	String config() default "";

	/**
	 * Is worker.
	 *
	 * @return the boolean
	 */
	boolean isWorker() default true;

	/**
	 * The type Verticle loader.
	 */
	class VerticleLoader {
		/**
		 * Scan package.
		 *
		 * @param packageName the package name
		 * @return the set
		 */
		public static Set<Class<?>> scanPackage(String packageName) {
			Reflections reflections = new Reflections(packageName);
			return reflections.getTypesAnnotatedWith(DeployableVerticle.class);
		}
	}
}

