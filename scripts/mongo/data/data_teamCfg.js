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
 * V1.0
 * 
 * This script creates documents for the collection :
 * - TeamCfg
 * 
 * AUTHOR : Nada Vujanic-Maquin for QaoBee
 */
//////////////////////////////////////////////////////////

/*
 * Empty the collection
 */
db.TeamCfg.remove({});

/*
 * Create the collection with test data
 */

// Team A : season 2013 - 2014
// "_id" : ObjectId().valueOf(),
db.TeamCfg.insert({
    "_id" : "55314cb86c63f45f6c196e29",
    "teamId" : "552d5e08644a77b3a20afdfe",
    "levelGame" : {
        "code" : "nv1",
        "label" : "Elite",
        "order" : NumberInt(1)
    },
    "season" : {
        "_id" : "551fae2bb65f105cf3eb432e",
        "code" : "SAI-2013",
        "label" : "2013 - 2014",
        /* Start : 01/07/2013*/
        "startDate" : NumberLong(1372629600000),
        /* End : 31/05/2014*/
        "endDate" : NumberLong(1401573600000),
        "activityId" : "ACT-FOOT",
        "countryId" : "CNTR-250-FR-FRA"
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
         },
         {
           "personId" : "54160977d5bd065a1bb1e564",
           "role" : {
               "code" : "acoach",
               "label" : "Coach Adjoint"
           }
         }
    ],
    "isActive" : "true"    
});

// Team A : season 2014 - 2015
// "_id" : ObjectId().valueOf(),
db.TeamCfg.insert({
    "_id" : "55314cb86c63f45f6c196e2a",
    "teamId" : "552d5e08644a77b3a20afdfe",
    "levelGame" : {
        "code" : "nv1",
        "label" : "Elite",
        "order" : NumberInt(1)
    },
    "season" : {
        "_id" : "551fae2bb65f105cf3eb432f",
        "code" : "SAI-2014",
        "label" : "2014 - 2015",
        /* Start : 01/06/2014*/
        "startDate" : NumberLong(1401573600000),
        /* End : 30/06/2015*/
        "endDate" : NumberLong(1435615200000),
        "activityId" : "ACT-FOOT",
        "countryId" : "CNTR-250-FR-FRA"
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
    ],
    "isActive" : "true"    
});

// Team B : season 2014 - 2015
// "_id" : ObjectId().valueOf(),
db.TeamCfg.insert({
    "_id" : "55314cb86c63f45f6c196e2b",
    "teamId" : "552d5e08644a77b3a20afdff",
    "levelGame" : {
        "code" : "nv3",
        "label" : "Régional",
        "order" : NumberInt(3)
    },
    "season" : {
        "_id" : "551fae2bb65f105cf3eb432f",
        "code" : "SAI-2014",
        "label" : "2014 - 2015",
        /* Start : 01/06/2014*/
        "startDate" : NumberLong(1401573600000),
        /* End : 30/06/2015*/
        "endDate" : NumberLong(1435615200000),
        "activityId" : "ACT-FOOT",
        "countryId" : "CNTR-250-FR-FRA"
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
    ],
    "isActive" : "true"    
});

// Team B : season 2015 - 2016
// "_id" : ObjectId().valueOf(),
db.TeamCfg.insert({
    "_id" : "55314cb86c63f45f6c196e2c",
    "teamId" : "552d5e08644a77b3a20afdff",
    "levelGame" : {
        "code" : "nv3",
        "label" : "Régional",
        "order" : NumberInt(3)
    },
    "season" : {
        "_id" : "551fae2bb65f105cf3eb4330",
        "code" : "SAI-2015",
        "label" : "2015 - 2016",
        /* Start : 01/07/2015*/
        "startDate" : NumberLong(1435701600000),
        /* End : 30/06/2016*/
        "endDate" : NumberLong(1467237600000),
        "activityId" : "ACT-FOOT",
        "countryId" : "CNTR-250-FR-FRA"
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
    ],
    "isActive" : "false"    
});