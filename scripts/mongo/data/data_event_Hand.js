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
 * INJECTION Event
 * V1.0
 * 
 * This script creates documents for collections :
 * - Event
 * 
 * AUTHOR : Christophe kervella pour Qaobee
 */
//////////////////////////////////////////////////////////

/* 
 * Vidage de la collection Event handball
 */
db.Event.remove({ "activityId" : "ACT-HAND", 
                   "seasonCode" : "SAI-2014" ,
                   "categoryAge.code" : "sen"});

 
/***************************************************************
 * Alimentation Event Cesson Handball
 * *************************************************************
 */

db.Event.insert({"_id" : new ObjectId().valueOf(),
    "activityId" : "ACT-HAND",
    "categoryAge" : { "code" : "sen", "label" : "Senior", "ageMax" : NumberInt(34), "ageMin" : NumberInt(20), "genre" : "gender.male"},
    "seasonCode" : "SAI-2014", 
    "label" : "Championnat : Journée 20",
    "owner" : ["ID-TEAM-CHAMBERY", "ID-TEAM-CESSON", "ID-PHARE-CHAMBERY"],
    "startDate" : NumberLong(1428518700000), "endDate" : NumberLong(1428525900000),
    "address" : { "place" : "Le phare - 800 Avenue du Grand Arietaz", "zipcode" : "73000", "city" : "Chambéry", "country" : "France" },
    "link" : {"linkId" : "AAAA", "type" : "championship"},
    "participants" : [{"id" : "ID-TEAM-CHAMBERY", "name" : "CHAMBERY SAVOIE HB", "structureId" : "CHAMBERYSAVOIEHB", "type":"teamHome"}, 
                      {"id" : "ID-TEAM-CESSON", "name" : "CRMHB Cesson-Sévigné", "structureId" : "541168295971d35c1f2d1b5e", "type":"teamVisitor"},
                      {"id" : "ID-PHARE-CHAMBERY", "name" : "Le phare", "structureId" : "CHAMBERYSAVOIEHB", "type":"infrastructure"}]
});

db.Event.insert({"_id" : new ObjectId().valueOf(),
    "activityId" : "ACT-HAND",
    "categoryAge" : { "code" : "sen", "label" : "Senior", "ageMax" : NumberInt(34), "ageMin" : NumberInt(20), "genre" : "gender.male"},
    "seasonCode" : "SAI-2014", 
    "label" : "Championnat : Journée 21",
    "owner" : ["ID-TEAM-CESSON", "ID-TEAM-DUNKERQUE", "ID-ARENA-BREST"],
    "startDate" : NumberLong(1429123500000), "endDate" : NumberLong(1429130700000),
    "address" : { "place" : "Brest Arena - 140 Boulevard de Plymouth", "zipcode" : "29200", "city" : "Brest", "country" : "France" },
    "link" : {"linkId" : "AAAA", "type" : "championship"},
    "participants" : [{"id" : "ID-TEAM-CESSON", "name" : "CRMHB Cesson-Sévigné", "structureId" : "541168295971d35c1f2d1b5e", "type":"teamHome"}, 
                      {"id" : "ID-TEAM-DUNKERQUE", "name" : "USDK Dunkerque", "structureId" : "541168295971d35c1f2d1b5f", "type":"teamVisitor"}, 
                      {"id" : "ID-ARENA-BREST", "name" : "Brest Arena", "structureId" : "541168295971d35c1f2d1b5e", "type":"infrastructure"}]
});

db.Event.insert({"_id" : new ObjectId().valueOf(),
    "activityId" : "ACT-HAND",
    "categoryAge" : { "code" : "sen", "label" : "Senior", "ageMax" : NumberInt(34), "ageMin" : NumberInt(20), "genre" : "gender.male"},
    "seasonCode" : "SAI-2014", 
    "label" : "Championnat : Journée 22",
    "owner" : ["ID-TEAM-CRETEIL", "ID-TEAM-CESSON", "ID-OUBRON-CRETEIL"],
    "startDate" : NumberLong(1430935200000), "endDate" : NumberLong(1430942400000),
    "address" : { "place" : "Robert Oubron - Rue Pasteur Vallery Radot", "zipcode" : "94000", "city" : "Creteil", "country" : "France" },
    "link" : {"linkId" : "AAAA", "type" : "championship"},
    "participants" : [{"id" : "ID-TEAM-CRETEIL", "label" : "US CRETEIL HANDBALL", "structureId" : "USCRETEILHANDBALL", "type":"teamHome"}, 
                      {"id" : "ID-TEAM-CESSON", "label" : "CRMHB Cesson-Sévigné", "structureId" : "541168295971d35c1f2d1b5e", "type":"teamVisitor"},
                      {"id" : "ID-OUBRON-CRETEIL", "label" : "Robert Oubron", "structureId" : "USCRETEILHANDBALL", "type":"infrastructure"}]
});

db.Event.insert({"_id" : new ObjectId().valueOf(),
    "activityId" : "ACT-HAND",
    "categoryAge" : { "code" : "sen", "label" : "Senior", "ageMax" : NumberInt(34), "ageMin" : NumberInt(20), "genre" : "gender.male"},
    "seasonCode" : "SAI-2014", 
    "label" : "Championnat : Journée 23",
    "owner" : ["ID-TEAM-CESSON", "ID-TEAM-RAPHAEL", "ID-LIBERTE-RENNES"],
    "startDate" : NumberLong(1431541800000), "endDate" : NumberLong(1431549000000),
    "address" : { "place" : "Le liberté, Esplanade Charles de Gaulle", "zipcode" : "35000", "city" : "Rennes", "country" : "France" },
    "link" : {"linkId" : "AAAA", "type" : "championship"},
    "participants" : [{"id" : "ID-TEAM-CESSON", "label" : "CRMHB Cesson-Sévigné", "structureId" : "541168295971d35c1f2d1b5e", "type":"teamHome"}, 
                      {"id" : "ID-TEAM-RAPHAEL", "label" : "SAINT RAPHAEL VHB", "structureId" : "SAINTRAPHAELVHB", "type":"teamVisitor"},
                      {"id" : "ID-LIBERTE-RENNES", "label" : "Le liberte Rennes", "structureId" : "541168295971d35c1f2d1b5e", "type":"infrastructure"}]
});