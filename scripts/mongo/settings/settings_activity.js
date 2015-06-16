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

//////////////////////////////////////////////////////////
/* 
 * SCRIPT MONGO DB
 * PARAMETRAGE Activity SETTINGS
 * V1.0
 * 
 * This script creates documents for collections :
 * - Activity
 * - Season
 * - ActivityCfg
 * 
 * AUTHOR : QaoBee
 */
//////////////////////////////////////////////////////////

/* 
 * Activity : une activite est immuable, cet objet ne bougera pas dans le temps
 */
db.Activity.remove({});

db.Activity.insert({
    "_id" : "ACT-FOOT",
    "code" : "ACT-FOOT",
    "label" : "admin.settings.activity.football.label",
    "enable" : true,
    "activityType" : "TEAM_SPORT"
});

db.Activity.insert({
    "_id" : "ACT-HAND",
    "code" : "ACT-HAND",
    "label" : "admin.settings.activity.handball.label",
    "enable" : true,
    "activityType" : "TEAM_SPORT"
});

db.Activity.insert({
    "_id" : "ACT-FENC",
    "code" : "ACT-FENC",
    "label" : "admin.settings.activity.fencing.label",
    "enable" : false,
    "activityType" : "TEAM_SPORT"
});
// ////////////////////////////////////////////////////////
