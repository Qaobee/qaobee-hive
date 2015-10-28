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
 * AUTHOR : Christophe Kervella for QaoBee
 */
//////////////////////////////////////////////////////////

/*
 * Empty the collection
 */
db.SB_Team.remove({"sandboxId" : "558b0efebd2e39cdab651e1f"});

/*
 * Cesson-Sevigne Team
 */
db.SB_Team.insert({
    "_id" : "552d5e08644a77b3a20afdfe",
    "label" : "Cesson-Sevigne A",
    "sandboxId" : "558b0efebd2e39cdab651e1f", 
    "effectiveId" : "550b31f925da07681592db23",
    "enable" : true,
    "adversary": false
});

db.SB_Team.insert({
    "_id" : "55e76161427aacaa71480569",
    "label" : "Nantes HBC",
    "sandboxId" : "558b0efebd2e39cdab651e1f", 
    "effectiveId" : "550b31f925da07681592db23",
    "linkTeamId" : ["552d5e08644a77b3a20afdfe"],
    "enable" : true,
    "adversary": true
});

db.SB_Team.insert({
    "_id" : "55e7619a427aacaa7148056a",
    "label" : "PSG Handball",
    "sandboxId" : "558b0efebd2e39cdab651e1f", 
    "effectiveId" : "550b31f925da07681592db23",
    "linkTeamId" : ["552d5e08644a77b3a20afdfe"],
    "enable" : true,
    "adversary": true
});


db.SB_Team.insert({
    "_id" : "55e768c0427aacaa7148056b",
    "label" : "AIX En Provence HB",
    "sandboxId" : "558b0efebd2e39cdab651e1f", 
    "effectiveId" : "550b31f925da07681592db23",
    "enable" : true,
    "adversary": true
});

db.SB_Team.insert({
    "_id" : "55e76b04427aacaa7148056c",
    "label" : "CHAMBERY SAVOIE HB",
    "sandboxId" : "558b0efebd2e39cdab651e1f", 
    "effectiveId" : "550b31f925da07681592db23",
    "linkTeamId" : ["552d5e08644a77b3a20afdfe"],
    "enable" : true,
    "adversary": true
});

db.SB_Team.insert({
    "_id" : "55e76b26427aacaa7148056d",
    "label" : "USDK Dunkerque",
    "sandboxId" : "558b0efebd2e39cdab651e1f", 
    "effectiveId" : "550b31f925da07681592db23",
    "linkTeamId" : ["552d5e08644a77b3a20afdfe"],
    "enable" : true,
    "adversary": true
});

db.SB_Team.insert({
    "_id" : "55e76b44427aacaa7148056e",
    "label" : "US CRETEIL HANDBALL",
    "sandboxId" : "558b0efebd2e39cdab651e1f", 
    "effectiveId" : "550b31f925da07681592db23",
    "linkTeamId" : ["552d5e08644a77b3a20afdfe"],
    "enable" : true,
    "adversary": true
});

db.SB_Team.insert({
    "_id" : "55e76b64427aacaa7148056f",
    "label" : "Toulouse HB",
    "sandboxId" : "558b0efebd2e39cdab651e1f", 
    "effectiveId" : "550b31f925da07681592db23",
    "linkTeamId" : ["552d5e08644a77b3a20afdfe"],
    "enable" : true,
    "adversary": true
});




/*
 * Dunkerque Team
 */
db.SB_Team.remove({"sandboxId" : "5591bb5e127472938a6444a2"});
db.SB_Team.insert({
    "_id" : "552d5e08644a77b3a20afdff",
    "label" : "Dunkerque A",
    "sandboxId" : "5591bb5e127472938a6444a2", 
    "effectiveId" : "550b31f925da07681592db22",
    "enable" : true,
    "adversary": false
});
