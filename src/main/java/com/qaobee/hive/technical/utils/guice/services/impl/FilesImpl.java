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

package com.qaobee.hive.technical.utils.guice.services.impl;

import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import com.qaobee.hive.technical.mongo.MongoDB;
import com.qaobee.hive.technical.utils.guice.services.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.ByteArrayOutputStream;

/**
 * The type Files.
 */
public class FilesImpl implements Files {

	private static final Logger LOG = LoggerFactory.getLogger(FilesImpl.class);
	@Inject private MongoDB db;

	@Override public byte[] get(String name) {
		try {
			GridFS gfsPhoto = new GridFS(db.getDb(), "img");
			GridFSDBFile imageForOutput = gfsPhoto.findOne(name);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			imageForOutput.writeTo(bos);
			return bos.toByteArray();
		} catch (Exception e) {
			LOG.warn(e.getMessage(), e);
		}
		return new byte[] {};
	}

	@Override public void put(byte[] data, String name) {
		GridFS gfsImages = new GridFS(db.getDb(), "img");
		GridFSInputFile gfsFile = gfsImages.createFile(data);
		gfsFile.setFilename(name);
		gfsFile.save();
	}
}
