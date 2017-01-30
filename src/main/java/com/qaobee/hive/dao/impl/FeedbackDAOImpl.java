/*
 *   __________________
 *    Qaobee
 *    __________________
 *
 *    Copyright (c) 2015.  Qaobee
 *    All Rights Reserved.
 *
 *    NOTICE: All information contained here is, and remains
 *    the property of Qaobee and its suppliers,
 *    if any. The intellectual and technical concepts contained
 *    here are proprietary to Qaobee and its suppliers and may
 *    be covered by U.S. and Foreign Patents, patents in process,
 *    and are protected by trade secret or copyright law.
 *    Dissemination of this information or reproduction of this material
 *    is strictly forbidden unless prior written permission is obtained
 *    from Qaobee.
 */

package com.qaobee.hive.dao.impl;

import com.qaobee.hive.dao.FeedbackDAO;
import com.qaobee.hive.technical.exceptions.ExceptionCodes;
import com.qaobee.hive.technical.exceptions.QaobeeException;
import net.rcarz.jiraclient.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.json.JsonObject;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Feedback dao.
 */
public class FeedbackDAOImpl implements FeedbackDAO {
    private static final Logger LOG = LoggerFactory.getLogger(FeedbackDAOImpl.class);
    private static final String PROJECT_FIELD = "project";
    @Inject
    @Named("jira")
    private JsonObject config;

    @Override
    public void sendFeedback(JsonObject data) throws QaobeeException {
        BasicCredentials creds = new BasicCredentials(config.getString("user"), config.getString("passwd"));
        JiraClient jira = new JiraClient(config.getString("url"), creds);
        try {
            File temp = null;
            if(data.containsField("img")) {
                byte[] img = Base64.decodeBase64(data.getString("img").replace("data:image/png;base64,", ""));
                temp = File.createTempFile("temp-file-name", ".png");
                FileUtils.writeByteArrayToFile(temp, img);
            }
            String title = "Anonymous Feedback";
            if (data.containsField("meta") && data.getObject("meta").containsField("user")) {
                title = data.getObject("meta").getObject("user").getString("firstname") + " " + data.getObject("meta").getObject("user").getString("name");
            }
            List<String> labels =  new ArrayList<>();
            labels.add("feedback");
            String project = config.getString(PROJECT_FIELD);
            if(data.containsField(PROJECT_FIELD)) {
                project = data.getString(PROJECT_FIELD);
            }
            List<String> versions =  new ArrayList<>();
            if(data.containsField("version")) {
                versions.add(data.getString("version"));
            }
            Issue newIssue = jira.createIssue(project, "Bug")
                    .field(Field.SUMMARY, "[" + title + "] " + data.getString("note"))
                    .field(Field.DESCRIPTION, data.getString("description", data.getString("note")) + "\n" +  data.getString("url") + "\n" + data.getObject("browser").encodePrettily())
                    .field(Field.LABELS,labels)
                    .field(Field.ISSUE_TYPE,data.getString("type", "feedback"))
              //      .field(Field.VERSIONS, versions)
                    .execute();
            if(temp !=null) {
                newIssue.addAttachment(temp);
                FileUtils.deleteQuietly(temp);
            }
        } catch (JiraException | IOException e) {
            LOG.error(e.getMessage(), e);
            throw new QaobeeException(ExceptionCodes.INTERNAL_ERROR, e.getMessage());
        }
    }
}
