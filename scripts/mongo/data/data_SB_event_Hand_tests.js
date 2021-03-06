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
 * Vidage de la collection SB_Event pour la sandbox de Cesson-Sevigne
 */
db.SB_Event.remove({ "owner.sandboxId" : "558b0efebd2e39cdab651e1f"});

 
/***************************************************************
 * Alimentation SB_Event Cesson Handball
 * *************************************************************
 */
db.SB_Event.insert({"_id" : "55847ed0d040353767a48e68",
    "activityId" : "ACT-HAND",
    "label" : "Amical",
    "owner" : {
        "sandboxId" : "558b0efebd2e39cdab651e1f", 
        "effectiveId" : "550b31f925da07681592db23", 
        "teamId" : "552d5e08644a77b3a20afdfe"
    },
    "startDate" : NumberLong(1440693000000), 
    "endDate" : NumberLong(1440700200000),
    "address" : {
        "formatedAddress" : "Brest Arena, Boulevard de Plymouth, Brest, France",
        "lat" : 48.387501,
        "lng" : -4.519812000000002,
        "place" : "Boulevard de Plymouth",
        "city" : "Brest",
        "country" : "France",
        "zipcode" : "29200"
    },
    "link" : {"type" : "friendlyGame"},
    "participants" : {
        "teamHome": {"id":"55e7619a427aacaa7148056a", "label":"PSG Handball"}, 
        "teamVisitor": {"id":"552d5e08644a77b3a20afdfe", "label":"CRMHB Cesson-Sévigné"},
        "infrastructure":"Brest Arena"}
});

db.SB_Event.insert({"_id" : "55847ed0d040353767a48e69",
    "activityId" : "ACT-HAND",
    "label" : "Amical",
    "owner" : {
        "sandboxId" : "558b0efebd2e39cdab651e1f", 
        "effectiveId" : "550b31f925da07681592db23", 
        "teamId" : "552d5e08644a77b3a20afdfe"
    },
    "startDate" : NumberLong(1440959400000), 
    "endDate" : NumberLong(1440966600000),
    "link" : {"linkId" : "AAAA", "type" : "friendlyGame"},
    "participants" : {
        "teamHome": {"id":"552d5e08644a77b3a20afdfe", "label":"CRMHB Cesson-Sévigné"}, 
        "teamVisitor": {"id":"55e76161427aacaa71480569", "label":"Nantes HBC"},
        "infrastructure":"Brest Arena"}
});

db.SB_Event.insert({"_id" : "55847ed0d040353767a48e70",
    "activityId" : "ACT-HAND",
    "label" : "Championnat : Journée 1",
    "owner" : {
        "sandboxId" : "558b0efebd2e39cdab651e1f", 
        "effectiveId" : "550b31f925da07681592db23", 
        "teamId" : "552d5e08644a77b3a20afdfe"
    },
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
        "teamHome": {"id":"55e76b04427aacaa7148056c", "label":"CHAMBERY SAVOIE HB"}, 
        "teamVisitor": {"id":"552d5e08644a77b3a20afdfe", "label":"CRMHB Cesson-Sévigné"},
        "infrastructure":"Le phare"}
});

db.SB_Event.insert({"_id" : "55847ed0d040353767a48e71",
    "activityId" : "ACT-HAND",
    "label" : "Coupe de France",
    "owner" : {
        "sandboxId" : "558b0efebd2e39cdab651e1f", 
        "effectiveId" : "550b31f925da07681592db23", 
        "teamId" : "552d5e08644a77b3a20afdfe"
    },
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
    "link" : {"type" : "cup"},
    "participants" : { 
        "teamHome": {"id":"552d5e08644a77b3a20afdfe", "label":"CRMHB Cesson-Sévigné"},
        "teamVisitor": {"id":"55e76b26427aacaa7148056d", "label":"USDK Dunkerque"},
        "infrastructure":"Brest Arena"}
});

db.SB_Event.insert({"_id" : "55847ed0d040353767a48e75",
    "activityId" : "ACT-HAND",
    "label" : "Entraînement",
    "owner" : {
        "sandboxId" : "558b0efebd2e39cdab651e1f", 
        "effectiveId" : "550b31f925da07681592db23", 
        "teamId" : "552d5e08644a77b3a20afdfe"
    },
    "startDate" : NumberLong(1442856600000), 
    "endDate" : NumberLong(1442863800000),
    "address" : {
        "formatedAddress" : "Brest Arena, Boulevard de Plymouth, Brest, France",
        "lat" : 48.387501,
        "lng" : -4.519812000000002,
        "place" : "Boulevard de Plymouth",
        "city" : "Brest",
        "country" : "France",
        "zipcode" : "29200"
    },
    "link" : {"type" : "training"},
    "participants" : {
        "teamHome": {"id":"552d5e08644a77b3a20afdfe", "label":"CRMHB Cesson-Sévigné"},
        "infrastructure":"Brest Arena"}
});

db.SB_Event.insert({"_id" : "55847ed0d040353767a48e72",
    "activityId" : "ACT-HAND",
    "label" : "Championnat : Journée 2",
    "owner" : {
        "sandboxId" : "558b0efebd2e39cdab651e1f", 
        "effectiveId" : "550b31f925da07681592db23", 
        "teamId" : "552d5e08644a77b3a20afdfe"
    },
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
        "teamHome": {"id":"55e76b44427aacaa7148056e", "label":"US CRETEIL HANDBALL"},
        "teamVisitor": {"id":"552d5e08644a77b3a20afdfe", "label":"CRMHB Cesson-Sévigné"},
        "infrastructure":"Robert Oubron"}
});

db.SB_Event.insert({"_id" : "55847ed0d040353767a48e73",
    "activityId" : "ACT-HAND",
    "label" : "Championnat : Journée 3",
    "owner" : {
        "sandboxId" : "558b0efebd2e39cdab651e1f", 
        "effectiveId" : "550b31f925da07681592db23", 
        "teamId" : "552d5e08644a77b3a20afdfe"
    },
    "startDate" : NumberLong(1443895200000), 
    "endDate" : NumberLong(1443902400000),
    "link" : {"linkId" : "AAAA", "type" : "championship"},
    "participants" : {
        "teamHome": {"id":"552d5e08644a77b3a20afdfe", "label":"CRMHB Cesson-Sévigné"},
        "teamVisitor": {"id":"55e7619a427aacaa7148056a", "label":"PSG Handball"},
        "infrastructure":"Le liberte Rennes"}
});

db.SB_Event.insert({"_id" : "55847ed0d040353767a48e74",
    "activityId" : "ACT-HAND",
    "label" : "Amical",
    "owner" : {
        "sandboxId" : "558b0efebd2e39cdab651e1f", 
        "effectiveId" : "550b31f925da07681592db23", 
        "teamId" : "552d5e08644a77b3a20afdfe"
    },
    "startDate" : NumberLong(1444655715000), 
    "endDate" : NumberLong(1444662915000),
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
        "teamHome": {"id":"55e76b64427aacaa7148056f", "label":"Toulouse HB"},
        "teamVisitor": {"id":"552d5e08644a77b3a20afdfe", "label":"CRMHB Cesson-Sévigné"},
        "infrastructure":"Brest Arena"}
});
