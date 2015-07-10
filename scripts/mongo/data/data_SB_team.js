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
 * INJECTION TEAM
 * V1.1
 * 
 * This script creates documents for the collection :
 * - Team
 * 
 * AUTHOR : Nada Vujanic-Maquin for QaoBee
 */
//////////////////////////////////////////////////////////

/*
 * Empty the collection
 */
db.SB_Team.remove({});

/*
 * Cesson-Sevigne Team
 */
db.SB_Team.insert({
    "_id" : "552d5e08644a77b3a20afdfe",
    "label" : "Cesson-Sevigne A",
    "sandBoxCfgId" : "558b0fc0bd2e39cdab651e21",
    "categoryAge" : {
        "code" : "sen",
        "label" : "Senior",
        "ageMax" : NumberInt(34),
        "ageMin" : NumberInt(20),
        "genre" : "gender.male"
    }
});

/*
 * Dunkerque Team
 */
db.SB_Team.insert({
    "_id" : "552d5e08644a77b3a20afdff",
    "label" : "Dunkerque A",
    "sandBoxCfgId" : "5591bb9c127472938a6444a3",
    "categoryAge" : {
        "code" : "sen",
        "label" : "Senior",
        "ageMax" : NumberInt(34),
        "ageMin" : NumberInt(20),
        "genre" : "gender.male"
    }
});
