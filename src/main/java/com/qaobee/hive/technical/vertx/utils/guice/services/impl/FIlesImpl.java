package com.qaobee.hive.technical.vertx.utils.guice.services.impl;

import java.io.ByteArrayOutputStream;

import javax.inject.Inject;

import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.vertx.utils.guice.services.Files;

/**
 * Created by xavier on 09/11/14.
 */
public class FIlesImpl implements Files {
	@Inject
	private MongoDB db;

	@Override
	public byte[] get(String name) {
		try {
			GridFS gfsPhoto = new GridFS(db.getDb(), "img");
			GridFSDBFile imageForOutput = gfsPhoto.findOne(name);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			imageForOutput.writeTo(bos);
			return bos.toByteArray();
		} catch (Exception exp) {
		}
		return null;
	}

	@Override
	public void put(byte[] data, String name) {
		GridFS gfsImages = new GridFS(db.getDb(), "img");
		GridFSInputFile gfsFile = gfsImages.createFile(data);
		gfsFile.setFilename(name);
		gfsFile.save();
	}
}
