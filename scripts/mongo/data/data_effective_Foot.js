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
 * INJECTION Effective
 * V1.2
 * 
 * This script creates documents for collections :
 * - Person
 * 
 * AUTHOR : Christophe kervella pour QaoBee s
 */
//////////////////////////////////////////////////////////
/* 
 * Vidage de la collection Effective (France A sen)
 */
db.Effective.remove({ "_id" : "559d2a2518e3cb71c60d964a"});

/** ************************************************************* */
/*
 * Alimentation Effective (France A sen)
 */
/** ************************************************************* */
db.Effective.insert({
    "_id" : "559d2a2518e3cb71c60d964a",
    "sandBoxCfgId" : "559d268318e3cb71c60d9649",
    "categoryAge" : {
        "code" : "sen",
        "label" : "Senior",
        "ageMax" : NumberInt(34),
        "ageMin" : NumberInt(20),
        "genre" : "gender.male"
    },
    "members" : [{ "personId": "541d3136f61fbf69868c121d","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "541d3136f61fbf69868c121e","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "541d3136f61fbf69868c1220","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "541d3136f61fbf69868c121f","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "541d3136f61fbf69868c1221","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "541d3136f61fbf69868c1222","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "541d3136f61fbf69868c1223","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "541d3136f61fbf69868c1224","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "541d3136f61fbf69868c1225","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "541d3136f61fbf69868c1226","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "541d3136f61fbf69868c1227","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "541d3136f61fbf69868c1228","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "541d3136f61fbf69868c1229","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "541d3136f61fbf69868c122a","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "541d3136f61fbf69868c122b","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "541d3136f61fbf69868c122c","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "541d3136f61fbf69868c122d","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "541d3136f61fbf69868c122e","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "541d3136f61fbf69868c122f","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "541d3136f61fbf69868c1230","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "541d3136f61fbf69868c1231","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "541d3136f61fbf69868c1232","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "54160977d5bd065a1bb1e565","role" : {"code": "coach", "label": "Coach"}},
                 { "personId": "54160977d5bd065a1bb1e568","role" : {"code": "acoach", "label": "Coach Adjoint"}}
    ]
});

/** ************************************************************* */

/* 
 * Vidage de la collection Effective (France A sen)
 */
db.Effective.remove({ "_id" : "559d2abf18e3cb71c60d964b"});

/** ************************************************************* */
/*
 * Alimentation Effective (France A u19)
 */
/** ************************************************************* */

db.Effective.insert({
    "_id" : "559d2abf18e3cb71c60d964b",
    "sandBoxCfgId" : "559d268318e3cb71c60d9649", 
    "categoryAge" : {
        "code" : "u19",
        "label" : "U19",
        "ageMax" : NumberInt(18),
        "ageMin" : NumberInt(17),
        "genre" : "Masculin"
    },
    "members" : [{ "personId": "54c606cdb39d53f0fb9477b2","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "54c60742b39d53f0fb9477b3","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "54c60777b39d53f0fb9477b4","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "54c60777b39d53f0fb9477b5","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "54c60777b39d53f0fb9477b6","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "54c60777b39d53f0fb9477b7","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "54c60777b39d53f0fb9477b8","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "54c60777b39d53f0fb9477b9","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "54c609fbb39d53f0fb9477b5","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "54c60c4cb39d53f0fb9477b6","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "54c60cb1b39d53f0fb9477b7","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "54c60cfbb39d53f0fb9477b8","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "54c60d55b39d53f0fb9477b9","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "54c60dbcb39d53f0fb9477bb","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "54c60e31b39d53f0fb9477bc","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "54c60e85b39d53f0fb9477bd","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "54c60ee3b39d53f0fb9477be","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "54c60f88b39d53f0fb9477bf","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "54c6102cb39d53f0fb9477c0","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "54c61087b39d53f0fb9477c1","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "54c610e7b39d53f0fb9477c2","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "54c6112fb39d53f0fb9477c3","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "54160977d5bd065a1bb1e566","role" : {"code": "coach", "label": "Coach"}}
    ]
});