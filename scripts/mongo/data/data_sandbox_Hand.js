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
 * INJECTION SandBoxCfg
 * V1.0
 *
 * This script creates documents for collections :
 * - SandBoxCfg
 * - SandBox
 *
 * AUTHOR : xavier MARIN pour Qaobee
 */
//////////////////////////////////////////////////////////
/*
 * Vidage de la collection SandBoxCfg handball
 */
db.SandBoxCfg.remove({"sandbox.owner": {"$in": ["55847ed0d040353767a48e70", "55847ed0d040353767a48e71", "55847ed0d040353767a48e72", "55847ed0d040353767a48e73"]}});
/*
 * Vidage de la collection SandBox handball
 */
db.SandBox.remove({"owner": {"$in": ["55847ed0d040353767a48e70", "55847ed0d040353767a48e71", "55847ed0d040353767a48e72", "55847ed0d040353767a48e73"]}});


/***************************************************************
 * Alimentation SandBox Cesson Handball
 * *************************************************************
 */
db.SandBox.insert({
    "_id": "558b0efebd2e39cdab651e1f",
    "activityId": "ACT-HAND",
    "structureId": "541168295971d35c1f2d1b5f",
    "members": [],
    "owner": "5509ef1fdb8f8b6e2f51f4ce"
});


/***************************************************************
 * Alimentation SandBoxCfg Cesson Handball
 * *************************************************************
 */
db.SandBoxCfg.insert({
    "_id": "558b0fc0bd2e39cdab651e21",
    "sandbox": {
        "_id": "558b0efebd2e39cdab651e1f",
        "activityId": "ACT-HAND",
        "structureId": "541168295971d35c1f2d1b5f",
        "members": [],
        "owner": "5509ef1fdb8f8b6e2f51f4ce"
    },
    "members": [],
    "season": {
        "_id": "558b0ceaf9285df5b7553fc6",
        "code": "SAI-2015",
        "label": "SAISON 2015-2016",
        "startDate": 1435701600000,
        "endDate": 1467237600000,
        "activityId": "ACT-HAND",
        "countryId": "CNTR-250-FR-FRA"
    },
    "structure": {
        "_id": "541168295971d35c1f2d1b5f",
        "label": "CESSON RENNES METROPOLE HB",
        "acronym": "CRMBH",
        "activity": {
            "_id": "ACT-HAND",
            "code": "ACT-HAND",
            "label": "admin.settings.activity.handball.label",
            "activated": true,
            "activityType": "TEAM_SPORT"
        },
        "address": {
            "place": "3, allée de Champagné",
            "zipcode": "35510",
            "city": "CESSON-SEVIGNE",
            "country": "France"
        },
        "contact": {
            "home": null,
            "office": "02 23 45 07 19",
            "cellphone": "06 69 97 68 39",
            "fax": "",
            "email": "sandrine@cesson-handball.com"
        },
        "country": {
            "_id": "CNTR-250-FR-FRA",
            "codeOSCE": 250,
            "alpha2": "FR",
            "alpha3": "FRA",
            "label": "settings.Country.FR.name"
        },
        "avatar": null
    },
    "teams": []
});