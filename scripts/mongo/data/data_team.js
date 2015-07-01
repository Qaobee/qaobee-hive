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
db.Team.remove({});

/*
 * Create the collection with test data
 */
//"_id" : ObjectId().valueOf(),
db.Team.insert({
    "_id" : "552d5e08644a77b3a20afdfe",
    "label" : "Team A",
    "sandBoxCfgId" : "5591bb9c127472938a6444a3",
    "categoryAge" : {
        "code" : "sen",
        "label" : "Senior",
        "ageMax" : NumberInt(34),
        "ageMin" : NumberInt(20),
        "genre" : "gender.male"
    },
    "championship" : {
        "id" : "1", 
        "label" : "Honor",
    },                      
    "listStaffMember" : [
         {
           "personId" : "54160977d5bd065a1bb1e563",
           "role" : {
               "code" : "coach",
               "label" : "Coach"
           }
         }
    ] 
});

//"_id" : ObjectId().valueOf(),
db.Team.insert({
    "_id" : "552d5e08644a77b3a20afdff",
    "label" : "Team B",
    "sandBoxCfgId" : "5591bb9c127472938a6444a3",
    "categoryAge" : {
        "code" : "sen",
        "label" : "Senior",
        "ageMax" : NumberInt(34),
        "ageMin" : NumberInt(20),
        "genre" : "gender.male"
    },
    "championship" : {
        "id" : "2", 
        "label" : "Exellence",
    },                     
    "listStaffMember" : [
        {
          "personId" : "541d3136f61fbf69868c121d",
          "role" : {
              "code" : "coach",
               "label" : "Coach"
           }
        }
   ] 
});
