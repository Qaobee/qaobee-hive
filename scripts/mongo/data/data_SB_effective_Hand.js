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
 * INJECTION SB_Effective
 * V1.2
 * 
 * This script creates documents for collections :
 * - Person
 * 
 * AUTHOR : Christophe kervella pour QaoBee
 */
//////////////////////////////////////////////////////////
/* 
 * Vidage de la collection SB_Effective (Club Dunkerque handball sen)
 */
db.SB_Effective.remove({ "_id" : "550b31f925da07681592db22"});

/** ************************************************************* */
/*
 * Alimentation SB_Effective (Club Dunkerque Handball)
 */
/** ************************************************************* */
db.SB_Effective.insert({
    "_id" : "550b31f925da07681592db22",
    "sandBoxCfgId" : "5591bb9c127472938a6444a3",
    "categoryAge" : {
        "code" : "sen",
        "label" : "Senior Gars",
        "ageMax" : NumberInt(150),
        "ageMin" : NumberInt(18),
        "genre" : "Homme",
        "order" : NumberInt(1)
    },
    "members" : [{ "personId": "541d2c5fb3f78c0317eea2be","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "541d3136f61fbf69868c1214","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "541d3136f61fbf69868c1215","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "541d3136f61fbf69868c1216","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "541d3136f61fbf69868c1217","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "541d3136f61fbf69868c1218","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "541d3136f61fbf69868c1219","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "541d3136f61fbf69868c121a","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "541d3136f61fbf69868c121b","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "541d3136f61fbf69868c121c","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "5509f71adb8f8b6e2f51f4d4","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "5509f71adb8f8b6e2f51f4d5","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "5509f71adb8f8b6e2f51f4d6","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "5509f71adb8f8b6e2f51f4d7","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "550a02acdb8f8b6e2f51f4da","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "54160977d5bd065a1bb1e563","role" : {"code": "coach", "label": "Coach"}},
                 { "personId": "54160977d5bd065a1bb1e564","role" : {"code": "acoach", "label": "Coach Adjoint"}}
    ]
});

//////////////////////////////////////////////////////////
/* 
 * Vidage de la collection SB_Effective (Club Cesson handball sen)
 */
db.SB_Effective.remove({ "_id" : "550b31f925da07681592db23"});

/** ************************************************************* */
/*
 * Alimentation SB_Effective (Club Cesson Handball)
 */
/** ************************************************************* */
db.SB_Effective.insert({
    "_id" : "550b31f925da07681592db23",
    "sandBoxCfgId" : "558b0fc0bd2e39cdab651e21",
    "categoryAge" : {
        "code" : "sen",
        "label" : "Senior Gars",
        "ageMax" : NumberInt(150),
        "ageMin" : NumberInt(18),
        "genre" : "Homme",
        "order" : NumberInt(1)
    },
    "members" : [{ "personId": "550a05dadb8f8b6e2f51f4db","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "550a05e3db8f8b6e2f51f4dc","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "550a05e9db8f8b6e2f51f4dd","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "550a05f7db8f8b6e2f51f4de","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "550a0600db8f8b6e2f51f4df","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "550a0606db8f8b6e2f51f4e0","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "550a060ddb8f8b6e2f51f4e1","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "550a0614db8f8b6e2f51f4e2","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "550a061bdb8f8b6e2f51f4e3","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "550a0620db8f8b6e2f51f4e4","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "550a0620db8f8b6e2f51f4e5","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "550a0620db8f8b6e2f51f4e6","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "550a0620db8f8b6e2f51f4e7","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "550a0620db8f8b6e2f51f4e8","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "5509ef1fdb8f8b6e2f51f4ce","role" : {"code": "coach", "label": "Coach"}},
                 { "personId": "5509ef1fdb8f8b6e2f51f4cf","role" : {"code": "acoach", "label": "Coach Adjoint"}}
    ]
});