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
    "_id" : "ACT-ARCHERY",
    "code" : "ACT-ARCHERY",
    "label" : "commons.settings.activity.archery",
    "enable" : false,
    "activityType" : "INDIVIDUAL_SPORT"
});

db.Activity.insert({
    "_id" : "ACT-BADMIN",
    "code" : "ACT-BADMIN",
    "label" : "commons.settings.activity.badminton",
    "enable" : false,
    "activityType" : "INDIVIDUAL_SPORT"
});

db.Activity.insert({
    "_id" : "ACT-BASEBALL",
    "code" : "ACT-BASEBALL",
    "label" : "commons.settings.activity.baseball",
    "enable" : false,
    "activityType" : "TEAM_SPORT"
});

db.Activity.insert({
    "_id" : "ACT-BASKET",
    "code" : "ACT-BASKET",
    "label" : "commons.settings.activity.basketball",
    "enable" : false,
    "activityType" : "TEAM_SPORT"
});

db.Activity.insert({
    "_id" : "ACT-BOBSLEIGH",
    "code" : "ACT-BOBSLEIGH",
    "label" : "commons.settings.activity.bobsleigh",
    "enable" : false,
    "activityType" : "TEAM_SPORT"
});

db.Activity.insert({
    "_id" : "ACT-BOXING",
    "code" : "ACT-BOXING",
    "label" : "commons.settings.activity.boxing",
    "enable" : false,
    "activityType" : "INDIVIDUAL_SPORT"
});

db.Activity.insert({
    "_id" : "ACT-COMBINED",
    "code" : "ACT-COMBINED",
    "label" : "commons.settings.activity.combined",
    "enable" : false,
    "activityType" : "INDIVIDUAL_SPORT"
});

db.Activity.insert({
    "_id" : "ACT-CURLING",
    "code" : "ACT-CURLING",
    "label" : "commons.settings.activity.curling",
    "enable" : false,
    "activityType" : "TEAM_SPORT"
});

db.Activity.insert({
    "_id" : "ACT-CYCLING",
    "code" : "ACT-CYCLING",
    "label" : "commons.settings.activity.cycling",
    "enable" : false,
    "activityType" : "INDIVIDUAL_SPORT"
});

db.Activity.insert({
    "_id" : "ACT-DANCE",
    "code" : "ACT-DANCE",
    "label" : "commons.settings.activity.dance",
    "enable" : false,
    "activityType" : "INDIVIDUAL_SPORT"
});

db.Activity.insert({
    "_id" : "ACT-FENCING",
    "code" : "ACT-FENCING",
    "label" : "commons.settings.activity.fencing",
    "enable" : false,
    "activityType" : "INDIVIDUAL_SPORT"
});

db.Activity.insert({
    "_id" : "ACT-FIGHT",
    "code" : "ACT-FIGHT",
    "label" : "commons.settings.activity.fight",
    "enable" : false,
    "activityType" : "INDIVIDUAL_SPORT"
});

db.Activity.insert({
    "_id" : "ACT-FOOT",
    "code" : "ACT-FOOT",
    "label" : "commons.settings.activity.football",
    "enable" : true,
    "activityType" : "TEAM_SPORT"
});

db.Activity.insert({
    "_id" : "ACT-HAND",
    "code" : "ACT-HAND",
    "label" : "commons.settings.activity.handball",
    "enable" : true,
    "activityType" : "TEAM_SPORT"
});

db.Activity.insert({
    "_id" : "ACT-HOCKEY",
    "code" : "ACT-HOCKEY",
    "label" : "commons.settings.activity.hockey",
    "enable" : false,
    "activityType" : "TEAM_SPORT"
});

db.Activity.insert({
    "_id" : "ACT-HORSING",
    "code" : "ACT-HORSING",
    "label" : "commons.settings.activity.horsing",
    "enable" : false,
    "activityType" : "INDIVIDUAL_SPORT"
});

db.Activity.insert({
    "_id" : "ACT-JUDO",
    "code" : "ACT-JUDO",
    "label" : "commons.settings.activity.judo",
    "enable" : false,
    "activityType" : "INDIVIDUAL_SPORT"
});

db.Activity.insert({
    "_id" : "ACT-LUGE",
    "code" : "ACT-LUGE",
    "label" : "commons.settings.activity.luge",
    "enable" : false,
    "activityType" : "INDIVIDUAL_SPORT"
});

db.Activity.insert({
    "_id" : "ACT-PING",
    "code" : "ACT-PING",
    "label" : "commons.settings.activity.ping",
    "enable" : false,
    "activityType" : "INDIVIDUAL_SPORT"
});

db.Activity.insert({
    "_id" : "ACT-ROWING",
    "code" : "ACT-ROWING",
    "label" : "commons.settings.activity.rowing",
    "enable" : false,
    "activityType" : "TEAM_SPORT"
});

db.Activity.insert({
    "_id" : "ACT-RUGBY",
    "code" : "ACT-RUGBY",
    "label" : "commons.settings.activity.rugbyball",
    "enable" : false,
    "activityType" : "TEAM_SPORT"
});

db.Activity.insert({
    "_id" : "ACT-SHOOTING",
    "code" : "ACT-SHOOTING",
    "label" : "commons.settings.activity.shooting",
    "enable" : false,
    "activityType" : "INDIVIDUAL_SPORT"
});

db.Activity.insert({
    "_id" : "ACT-SPRINT",
    "code" : "ACT-SPRINT",
    "label" : "commons.settings.activity.sprint",
    "enable" : false,
    "activityType" : "INDIVIDUAL_SPORT"
});

db.Activity.insert({
    "_id" : "ACT-SWIM",
    "code" : "ACT-SWIM",
    "label" : "commons.settings.activity.swim",
    "enable" : false,
    "activityType" : "INDIVIDUAL_SPORT"
});

db.Activity.insert({
    "_id" : "ACT-TENNIS",
    "code" : "ACT-TENNIS",
    "label" : "commons.settings.activity.tennis",
    "enable" : false,
    "activityType" : "INDIVIDUAL_SPORT"
});

db.Activity.insert({
    "_id" : "ACT-VOLLEY",
    "code" : "ACT-VOLLEY",
    "label" : "commons.settings.activity.volleyball",
    "enable" : false,
    "activityType" : "TEAM_SPORT"
});

// ////////////////////////////////////////////////////////
