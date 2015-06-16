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
