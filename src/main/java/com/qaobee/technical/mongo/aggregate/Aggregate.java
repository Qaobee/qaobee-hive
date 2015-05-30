package com.qaobee.technical.mongo.aggregate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
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
	 * @param stageName
	 * @param listFields
	 */
	public Aggregate(Map<String, Object> Attrs) {

		pipeline = new ArrayList<DBObject>();

		for (Object object : fields) {

		}

	}

	/**
	 * Add field in the stage
	 * 
	 * @param field
	 * @param value
	 */
	public void addField(final String field, final String value) {
		fields.add(new BasicDBObject(field, value));
	}

	/**
	 * @return the stage
	 */
	public final DBObject getStage() {
		stage = new BasicDBObject(this.typeStage, fields);
		return stage;
	}
}
