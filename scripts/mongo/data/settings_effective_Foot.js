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
 * Vidage de la collection Effective (Club Demo Football sen)
 */
db.Effective.remove({ "structureId" : "541168295971d35c1f2d1b60",
                      "seasonCode" : "SAI-2014" , 
                      "categoryAge.code" : "sen"
});

/** ************************************************************* */
/*
 * Alimentation Effective (Club Demo Football sen)
 */
/** ************************************************************* */
db.Effective.insert({
    "_id" : new ObjectId().valueOf(),
    "structureId" : "541168295971d35c1f2d1b60",
    "seasonCode" : "SAI-2014" , 
    "categoryAge" : {
        "code" : "sen",
        "label" : "Senior",
        "ageMax" : NumberInt(34),
        "ageMin" : NumberInt(20),
        "genre" : "gender.male"
    },
    "members" : ["541d3136f61fbf69868c121d","541d3136f61fbf69868c121e","541d3136f61fbf69868c1220",
                 "541d3136f61fbf69868c121f","541d3136f61fbf69868c1221","541d3136f61fbf69868c1222",
                 "541d3136f61fbf69868c1223","541d3136f61fbf69868c1224","541d3136f61fbf69868c1225",
                 "541d3136f61fbf69868c1226","541d3136f61fbf69868c1227","541d3136f61fbf69868c1228",
                 "541d3136f61fbf69868c1229","541d3136f61fbf69868c122a","541d3136f61fbf69868c122b",
                 "541d3136f61fbf69868c122c","541d3136f61fbf69868c122d","541d3136f61fbf69868c122e",
                 "541d3136f61fbf69868c122f","541d3136f61fbf69868c1230","541d3136f61fbf69868c1231",
                 "541d3136f61fbf69868c1232"
    ]
});

/** ************************************************************* */
/*
 * Alimentation Effective (Club Demo Football sen)
 */
/** ************************************************************* */
db.Effective.insert({
    "_id" : new ObjectId().valueOf(),
    "structureId" : "541168295971d35c1f2d1b60",
    "seasonCode" : "SAI-2014" , 
    "categoryAge" : {
        "code" : "u19",
        "label" : "U19",
        "ageMax" : NumberInt(18),
        "ageMin" : NumberInt(17),
        "genre" : "Masculin"
    },
    "members" : ["54c606cdb39d53f0fb9477b2","54c60742b39d53f0fb9477b3","54c60777b39d53f0fb9477b4",
                 "54c60777b39d53f0fb9477b5","54c60777b39d53f0fb9477b6","54c60777b39d53f0fb9477b7",
                 "54c60777b39d53f0fb9477b8","54c60777b39d53f0fb9477b9","54c609fbb39d53f0fb9477b5",
                 "54c60c4cb39d53f0fb9477b6","54c60cb1b39d53f0fb9477b7","54c60cfbb39d53f0fb9477b8",
                 "54c60d55b39d53f0fb9477b9","54c60dbcb39d53f0fb9477bb","54c60e31b39d53f0fb9477bc",
                 "54c60e85b39d53f0fb9477bd","54c60ee3b39d53f0fb9477be","54c60f88b39d53f0fb9477bf",
                 "54c6102cb39d53f0fb9477c0","54c61087b39d53f0fb9477c1","54c610e7b39d53f0fb9477c2",
                 "54c6112fb39d53f0fb9477c3"
    ]
});