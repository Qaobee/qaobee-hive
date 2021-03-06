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

db.Season.insert({
    "_id" :"559a9294889089a442f3d495",
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
    "_id" : "559a9294889089a442f3d496",
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
    "_id" : "559a9294889089a442f3d497",
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
    "_id" : "559a9294889089a442f3d498",
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
    "_id" : "559a9294889089a442f3d499",
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
    "_id" : "558b0ceaf9285df5b7553fc6",
    "code" : "SAI-2015",
    "label" : "SAISON 2015-2016",
    /* Start : 01/07/2015*/
    "startDate" : NumberLong(1435701600000),
    /* End : 30/06/2016*/
    "endDate" : NumberLong(1467237600000),
    "activityId" : "ACT-HAND",
    "countryId" : "CNTR-250-FR-FRA"
});

db.Season.insert({
    "_id" : "577a85eeeb4488b5f6000c84",
    "code" : "SAI-2016",
    "label" : "SAISON 2016-2017",
    /* Start : 01/07/2016*/
    "startDate" : NumberLong(1467324000000),
    /* End : 30/06/2017*/
    "endDate" : NumberLong(1498773600000),
    "activityId" : "ACT-HAND",
    "countryId" : "CNTR-250-FR-FRA"
});

db.Season.insert({
    "_id" : "591ff63921031a7b45752371",
    "code" : "SAI-2017",
    "label" : "SAISON 2017-2018",
    "startDate" : NumberLong(1498860001000),
    "endDate" : NumberLong(1530395999000),
    "activityId" : "ACT-HAND",
    "countryId" : "CNTR-250-FR-FRA"
});

db.Season.insert({
    "_id" : "5b3be57c4d1099219abf1128",
    "code" : "SAI-2018",
    "label" : "SAISON 2018-2019",
    "startDate" : NumberLong(1530396001000),
    "endDate" : NumberLong(1561931999000),
    "activityId" : "ACT-HAND",
    "countryId" : "CNTR-250-FR-FRA"
});
// ////////////////////////////////////////////////////////
