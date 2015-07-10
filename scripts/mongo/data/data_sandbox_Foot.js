/*
 * __________________
 *   Qaobee
 * __________________
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
 * INJECTION SB_SandBoxCfg
 * V1.0
 *
 * This script creates documents for collections :
 * - SB_SandBoxCfg
 * - SB_SandBox
 *
 * AUTHOR : xavier MARIN pour Qaobee
 */
//////////////////////////////////////////////////////////
/*
 * Vidage de la collection SB_SandBoxCfg France A
 */
db.SB_SandBoxCfg.remove({"_id": "559d268318e3cb71c60d9649"});
/*
 * Vidage de la collection SB_SandBox France A
 */
db.SB_SandBox.remove({"_id": "559d266a18e3cb71c60d9648"});


/***************************************************************
 * Alimentation SandBox France A
 * *************************************************************
 */
db.SB_SandBox.insert({
    "_id": "559d266a18e3cb71c60d9648",
    "activityId": "ACT-FOOT",
    "owner": "54160977d5bd065a1bb1e565",
    "sandboxCfgId": "559d268318e3cb71c60d9649"
});


/***************************************************************
 * Alimentation SB_SandBoxCfg France A
 * *************************************************************
 */
db.SB_SandBoxCfg.insert({
    "_id": "559d268318e3cb71c60d9649",
    "activity": {
        "_id" : "ACT-FOOT",
        "code" : "ACT-FOOT",
        "label" : "admin.settings.activity.football.label",
        "enable" : true,
        "activityType" : "TEAM_SPORT"
    },
    "sandbox": {
        "_id": "559d266a18e3cb71c60d9648",
        "activityId": "ACT-FOOT",
        "owner": "54160977d5bd065a1bb1e565",
        "sandboxCfgId": "559d268318e3cb71c60d9649"
    },
    "members": [
        {
            "personId": "54160977d5bd065a1bb1e568",

            "role": {
                "code": "acoach",
                "label": "Coach Adjoint"
            }
        }
    ],
    "season": {
        "_id" : "559a9294889089a442f3d497",
        "code" : "SAI-2015",
        "label" : "SAISON 2015-2016",
        /* Start : 01/07/2015*/
        "startDate" : NumberLong(1435701600000),
        /* End : 30/06/2016*/
        "endDate" : NumberLong(1467237600000),
        "activityId" : "ACT-FOOT",
        "countryId" : "CNTR-250-FR-FRA"
    },
    "structure": {
        "_id" : "541168295971d35c1f2d1b60",
        "label" : "France A",
        "acronym" : "CAFB",
        "activity" : {
            "_id" : "ACT-FOOT",
            "code" : "ACT-FOOT",
            "label" : "admin.settings.activity.football.label",
            "activated" : true,
            "activityType" : "TEAM_SPORT"
        },
        "address" : {
            "place" : "1 Rue Jean Jaurès",
            "zipcode" : "Brest",
            "city" : "29200",
            "country" : "France"
        },
        "contact" : {
            "home" : "02 98 00 00 00",
            "office" : "02 98 01 01 01",
            "cellphone" : "06 06 06 06 06",
            "fax" : "",
            "email" : "0529000@football-france.eu"
        },
        "country" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
        "avatar" : null
    }
});