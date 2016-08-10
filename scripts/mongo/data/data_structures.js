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
 * PARAMETRAGE STRUCTURE
 * V1.0
 * 
 * This script creates documents for collections :
 * - Structure
 * - StructureCfg
 * 
 * AUTHOR : Nada Vujanic-Maquin pour QaoBee
 */
//////////////////////////////////////////////////////////


/* 
 * Structure : une structure est quasi immuable, cet objet bougera peu dans le temps
 */
// db.Structure.remove({});

// Extract value of String from ObjectId - field transformation from Object to String
db.Structure.insert({
    "_id" : "541168295971d35c1f2d1b5e",
    "label" : "Dunkerque Handball",
    "acronym" : "USDK",
    "activity" : {
        "_id" : "ACT-HAND",
        "code" : "ACT-HAND",
        "label" : "admin.settings.activity.handball.label",
        "enable" : true,
        "activityType" : "TEAM_SPORT"
    },
    "address" : {
        "place" : " Stades de Flandres, Avenue de Rosendaël",
        "zipcode" : "59240",
        "city" : " DUNKERQUE",
        "country" : "France"
    },
    "contact" : {
        "home" : null,
        "office" : "03 28 66 91 52",
        "cellphone" : "06 30 35 38 19",
        "fax" : "",
        "email" : "melanie.lefebvre@usdk.fr"
    },
    "country" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "avatar" : null
});

db.Structure.insert({
    "_id" : "541168295971d35c1f2d1b5f",
    "label" : "CESSON RENNES METROPOLE HB",
    "acronym" : "CRMBH",
    "activity" : {
        "_id" : "ACT-HAND",
        "code" : "ACT-HAND",
        "label" : "admin.settings.activity.handball.label",
        "enable" : true,
        "activityType" : "TEAM_SPORT"
    },
    "address" : {
        "place" : "3, allée de Champagné",
        "zipcode" : "35510",
        "city" : "CESSON-SEVIGNE",
        "country" : "France"
    },
    "contact" : {
        "home" : null,
        "office" : "02 23 45 07 19",
        "cellphone" : "06 69 97 68 39",
        "fax" : "",
        "email" : "sandrine@cesson-handball.com"
    },
    "country" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "avatar" : null
});
