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
 * INJECTION SB_Event
 * V1.0
 * 
 * This script creates documents for collections :
 * - SB_Event
 * 
 * AUTHOR : Christophe kervella pour Qaobee
 */
//////////////////////////////////////////////////////////

/* 
 * Vidage de la collection SB_Event handball
 */
db.SB_Event.remove({ "_id" : { "$in" : [ "55847ed0d040353767a48e70", "55847ed0d040353767a48e71", "55847ed0d040353767a48e72", "55847ed0d040353767a48e73"]}});

 
/***************************************************************
 * Alimentation SB_Event Cesson Handball
 * *************************************************************
 */

db.SB_Event.insert({"_id" : "55847ed0d040353767a48e70",
    "activityId" : "ACT-HAND",
    "label" : "Championnat : Journée 1",
    "owner" : ["ID-TEAM-CHAMBERY", "552d5e08644a77b3a20afdfe", "ID-PHARE-CHAMBERY", "558b0efebd2e39cdab651e1f", "550b31f925da07681592db23"],
    "startDate" : NumberLong(1442080800000), 
    "endDate" : NumberLong(1442088000000),
    "address" : {
        "formatedAddress" : "LE PHARE - CHAMBERY METROPOLE, 800 Avenue du Grand Arietaz, Chambéry, France",
        "lat" : 45.583892,
        "lng" : 5.899586999999997,
        "place" : "800 Avenue du Grand Arietaz",
        "city" : "Chambéry",
        "country" : "France",
        "zipcode" : "73000"
    },
    "link" : {"linkId" : "AAAA", "type" : "championship"},
    "participants" : {
        "teamHome":"CHAMBERY SAVOIE HB", 
        "teamVisitor":"CRMHB Cesson-Sévigné",
        "infrastructure":"Le phare"}
});

db.SB_Event.insert({"_id" : "55847ed0d040353767a48e71",
    "activityId" : "ACT-HAND",
    "label" : "Championnat : Journée 2",
    "owner" : ["552d5e08644a77b3a20afdfe", "ID-TEAM-DUNKERQUE", "ID-ARENA-BREST", "558b0efebd2e39cdab651e1f", "550b31f925da07681592db23"],
    "startDate" : NumberLong(1442685600000), 
    "endDate" : NumberLong(1442692800000),
    "address" : {
        "formatedAddress" : "Brest Arena, Boulevard de Plymouth, Brest, France",
        "lat" : 48.387501,
        "lng" : -4.519812000000002,
        "place" : "Boulevard de Plymouth",
        "city" : "Brest",
        "country" : "France",
        "zipcode" : "29200"
    },
    "link" : {"linkId" : "AAAA", "type" : "championship"},
    "participants" : {
        "teamHome":"CRMHB Cesson-Sévigné", 
        "teamVisitor":"USDK Dunkerque",
        "infrastructure":"Brest Arena"}
});

db.SB_Event.insert({"_id" : "55847ed0d040353767a48e72",
    "activityId" : "ACT-HAND",
    "label" : "Championnat : Journée 3",
    "owner" : ["ID-TEAM-CRETEIL", "552d5e08644a77b3a20afdfe", "ID-OUBRON-CRETEIL", "558b0efebd2e39cdab651e1f","550b31f925da07681592db23"],
    "startDate" : NumberLong(1443290400000), 
    "endDate" : NumberLong(1443297600000),
    "address" : {
        "formatedAddress" : "Palais des sports Robert-Oubron, Rue Pasteur Vallery Radot, Créteil, France",
        "lat" : 48.787513,
        "lng" : 2.441669000000047,
        "place" : "5 Rue Pasteur Vallery Radot",
        "city" : "Créteil",
        "country" : "France",
        "zipcode" : "94000"
    },
    "link" : {"linkId" : "AAAA", "type" : "championship"},
    "participants" : {
        "teamHome":"US CRETEIL HANDBALL", 
        "teamVisitor":"CRMHB Cesson-Sévigné",
        "infrastructure":"Robert Oubron"}
});

db.SB_Event.insert({"_id" : "55847ed0d040353767a48e73",
    "activityId" : "ACT-HAND",
    "label" : "Championnat : Journée 4",
    "owner" : ["552d5e08644a77b3a20afdfe", "ID-TEAM-RAPHAEL", "ID-LIBERTE-RENNES", "558b0efebd2e39cdab651e1f", "550b31f925da07681592db23"],
    "startDate" : NumberLong(1443895200000), 
    "endDate" : NumberLong(1443902400000),
    "link" : {"linkId" : "AAAA", "type" : "championship"},
    "participants" : {
        "teamHome":"CRMHB Cesson-Sévigné", 
        "teamVisitor":"SAINT RAPHAEL VHB",
        "infrastructure":"Le liberte Rennes"}
});