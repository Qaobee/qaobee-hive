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

package com.qaobee.hive.technical.mongo.aggregate;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The type Aggregate.
 * @author cke
 */
public class Aggregate {

	/** pipeline aggregation */
	private List<DBObject> pipeline;

	/**
	 * list of field for the stage
     */
	BasicDBList fields;

	/** the stage */
	DBObject stage;

	/** stage type */
	String typeStage;

	/**
	 * Constructor
	 *
	 * @param Attrs the attrs
     */
	public Aggregate(Map<String, Object> Attrs) {

		pipeline = new ArrayList<>();

		for (Object object : fields) {

		}

	}

	/**
	 * Add field in the stage
	 *
	 * @param field the field
	 * @param value the value
     */
	public void addField(final String field, final String value) {
		fields.add(new BasicDBObject(field, value));
	}

	/**
	 * Gets stage.
	 *
	 * @return the stage
     */
	public final DBObject getStage() {
		stage = new BasicDBObject(this.typeStage, fields);
		return stage;
	}
}
