/*************************************************************************
 * 
 * Qaobee
 * __________________
 * 
 * [2015] Qaobee
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
package com.qaobee.hive.business.commons.settings.impl;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.impl.Json;

import com.qaobee.hive.business.commons.settings.ActivityBusiness;
import com.qaobee.hive.business.model.commons.settings.Activity;
import com.qaobee.hive.business.model.commons.settings.Country;
import com.qaobee.hive.technical.mongo.MongoDB;

/**
 * @author jerome
 *
 */
public class ActivityBusinessImpl implements ActivityBusiness {

	private static Map<String, Activity> mapActivity = null;
	
	@Inject
	private MongoDB mongo;
	
	@Override
	public Activity getActivityFromId(String id) {
		if(mapActivity==null) {
			getActivities();
		}
		if(mapActivity==null) {
			return null;
		}
		return mapActivity.get(id);
	}

	
	private void getActivities() {
		JsonArray resultJson = mongo.findAll(null, null, -1, 0, Activity.class);
		if(resultJson != null && resultJson.size() > 0) {
			Activity activity;
			mapActivity = new HashMap<>();
			for(int i=0; i<resultJson.size();i++) {
				activity = Json.decodeValue(resultJson.get(i).toString(), Activity.class);
				mapActivity.put(activity.get_id(), activity);
			}
		}
	}
}
