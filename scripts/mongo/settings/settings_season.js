//////////////////////////////////////////////////////////
/* 
 * SCRIPT MONGO DB
 * PARAMETRAGE Activity SETTINGS
 * V1.0
 * 
 * This script creates documents for collections :
 * - Season
 * 
 * AUTHOR : QaoBee
 */
//////////////////////////////////////////////////////////
/*
 * Season : liste des saisons pour une activite
 */
db.Season.remove({});
var _id = new ObjectId().valueOf();
db.Season.insert({
    "_id" : _id,
    "code" : "SAI-2013",
    "label" : "SAISON 2013-2014",
    /* Start : 01/07/2013*/
    "startDate" : NumberLong(1372629600000),
    /* End : 31/05/2014*/
    "endDate" : NumberLong(1401573600000),
    "activityId" : "ACT-FOOT",
    "countryId" : "CNTR-250-FR-FRA"
});

_id = new ObjectId().valueOf();
db.Season.insert({
    "_id" : _id,
    "code" : "SAI-2014",
    "label" : "SAISON 2014-2015",
    /* Start : 01/06/2014*/
    "startDate" : NumberLong(1401573600000),
    /* End : 30/06/2015*/
    "endDate" : NumberLong(1435615200000),
    "activityId" : "ACT-FOOT",
    "countryId" : "CNTR-250-FR-FRA"
});

_id = new ObjectId().valueOf();
db.Season.insert({
    "_id" : _id,
    "code" : "SAI-2015",
    "label" : "SAISON 2015-2016",
    /* Start : 01/07/2015*/
    "startDate" : NumberLong(1435701600000),
    /* End : 30/06/2016*/
    "endDate" : NumberLong(1467237600000),
    "activityId" : "ACT-FOOT",
    "countryId" : "CNTR-250-FR-FRA"
});

_id = new ObjectId().valueOf();
db.Season.insert({
    "_id" : _id,
    "code" : "SAI-2013",
    "label" : "SAISON 2013-2014",
    /* Start : 01/07/2013*/
    "startDate" : NumberLong(1372629600000),
    /* End : 30/06/2014*/
    "endDate" : NumberLong(1404079200000),
    "activityId" : "ACT-HAND",
    "countryId" : "CNTR-250-FR-FRA"
});

_id = new ObjectId().valueOf();
db.Season.insert({
    "_id" : _id,
    "code" : "SAI-2014",
    "label" : "SAISON 2014-2015",
    /* Start : 01/07/2014*/
    "startDate" : NumberLong(1404165600000),
    /* End : 30/06/2015*/
    "endDate" : NumberLong(1435615200000),
    "activityId" : "ACT-HAND",
    "countryId" : "CNTR-250-FR-FRA"
});

_id = new ObjectId().valueOf();
db.Season.insert({
    "_id" : _id,
    "code" : "SAI-2015",
    "label" : "SAISON 2015-2016",
    /* Start : 01/07/2015*/
    "startDate" : NumberLong(1435701600000),
    /* End : 30/06/2016*/
    "endDate" : NumberLong(1467237600000),
    "activityId" : "ACT-HAND",
    "countryId" : "CNTR-250-FR-FRA"
});
// ////////////////////////////////////////////////////////