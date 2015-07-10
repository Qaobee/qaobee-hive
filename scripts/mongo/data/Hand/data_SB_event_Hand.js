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
    "categoryAge" : { "code" : "sen", "label" : "Senior", "ageMax" : NumberInt(34), "ageMin" : NumberInt(20), "genre" : "gender.male"},
    "seasonCode" : "SAI-2014", 
    "label" : "Championnat : Journée 1",
    "owner" : ["ID-TEAM-CHAMBERY", "552d5e08644a77b3a20afdfe", "ID-PHARE-CHAMBERY"],
    "startDate" : NumberLong(1442080800000), "endDate" : NumberLong(1442088000000),
    "address" : { "place" : "Le phare - 800 Avenue du Grand Arietaz", "zipcode" : "73000", "city" : "Chambéry", "country" : "France" },
    "link" : {"linkId" : "AAAA", "type" : "championship"},
    "participants" : [{"id" : "ID-TEAM-CHAMBERY", "name" : "CHAMBERY SAVOIE HB", "structureId" : "CHAMBERYSAVOIEHB", "type":"teamHome"}, 
                      {"id" : "552d5e08644a77b3a20afdfe", "name" : "CRMHB Cesson-Sévigné", "structureId" : "541168295971d35c1f2d1b5e", "type":"teamVisitor"},
                      {"id" : "ID-PHARE-CHAMBERY", "name" : "Le phare", "structureId" : "CHAMBERYSAVOIEHB", "type":"infrastructure"}]
});

db.SB_Event.insert({"_id" : "55847ed0d040353767a48e71",
    "activityId" : "ACT-HAND",
    "categoryAge" : { "code" : "sen", "label" : "Senior", "ageMax" : NumberInt(34), "ageMin" : NumberInt(20), "genre" : "gender.male"},
    "seasonCode" : "SAI-2014", 
    "label" : "Championnat : Journée 2",
    "owner" : ["552d5e08644a77b3a20afdfe", "ID-TEAM-DUNKERQUE", "ID-ARENA-BREST", "558b0efebd2e39cdab651e1f"],
    "startDate" : NumberLong(1442685600000), "endDate" : NumberLong(1442692800000),
    "address" : { "place" : "Brest Arena - 140 Boulevard de Plymouth", "zipcode" : "29200", "city" : "Brest", "country" : "France" },
    "link" : {"linkId" : "AAAA", "type" : "championship"},
    "participants" : [{"id" : "552d5e08644a77b3a20afdfe", "name" : "CRMHB Cesson-Sévigné", "structureId" : "541168295971d35c1f2d1b5e", "type":"teamHome"}, 
                      {"id" : "ID-TEAM-DUNKERQUE", "name" : "USDK Dunkerque", "structureId" : "541168295971d35c1f2d1b5f", "type":"teamVisitor"}, 
                      {"id" : "ID-ARENA-BREST", "name" : "Brest Arena", "structureId" : "541168295971d35c1f2d1b5e", "type":"infrastructure"}]
});

db.SB_Event.insert({"_id" : "55847ed0d040353767a48e72",
    "activityId" : "ACT-HAND",
    "categoryAge" : { "code" : "sen", "label" : "Senior", "ageMax" : NumberInt(34), "ageMin" : NumberInt(20), "genre" : "gender.male"},
    "seasonCode" : "SAI-2014", 
    "label" : "Championnat : Journée 3",
    "owner" : ["ID-TEAM-CRETEIL", "552d5e08644a77b3a20afdfe", "ID-OUBRON-CRETEIL", "558b0efebd2e39cdab651e1f"],
    "startDate" : NumberLong(1443290400000), "endDate" : NumberLong(1443297600000),
    "address" : { "place" : "Robert Oubron - Rue Pasteur Vallery Radot", "zipcode" : "94000", "city" : "Creteil", "country" : "France" },
    "link" : {"linkId" : "AAAA", "type" : "championship"},
    "participants" : [{"id" : "ID-TEAM-CRETEIL", "label" : "US CRETEIL HANDBALL", "structureId" : "USCRETEILHANDBALL", "type":"teamHome"}, 
                      {"id" : "552d5e08644a77b3a20afdfe", "label" : "CRMHB Cesson-Sévigné", "structureId" : "541168295971d35c1f2d1b5e", "type":"teamVisitor"},
                      {"id" : "ID-OUBRON-CRETEIL", "label" : "Robert Oubron", "structureId" : "USCRETEILHANDBALL", "type":"infrastructure"}]
});

db.SB_Event.insert({"_id" : "55847ed0d040353767a48e73",
    "activityId" : "ACT-HAND",
    "categoryAge" : { "code" : "sen", "label" : "Senior", "ageMax" : NumberInt(34), "ageMin" : NumberInt(20), "genre" : "gender.male"},
    "seasonCode" : "SAI-2014", 
    "label" : "Championnat : Journée 4",
    "owner" : ["552d5e08644a77b3a20afdfe", "ID-TEAM-RAPHAEL", "ID-LIBERTE-RENNES", "558b0efebd2e39cdab651e1f"],
    "startDate" : NumberLong(1443895200000), "endDate" : NumberLong(1443902400000),
    "address" : { "place" : "Le liberté, Esplanade Charles de Gaulle", "zipcode" : "35000", "city" : "Rennes", "country" : "France" },
    "link" : {"linkId" : "AAAA", "type" : "championship"},
    "participants" : [{"id" : "552d5e08644a77b3a20afdfe", "label" : "CRMHB Cesson-Sévigné", "structureId" : "541168295971d35c1f2d1b5e", "type":"teamHome"}, 
                      {"id" : "ID-TEAM-RAPHAEL", "label" : "SAINT RAPHAEL VHB", "structureId" : "SAINTRAPHAELVHB", "type":"teamVisitor"},
                      {"id" : "ID-LIBERTE-RENNES", "label" : "Le liberte Rennes", "structureId" : "541168295971d35c1f2d1b5e", "type":"infrastructure"}]
});