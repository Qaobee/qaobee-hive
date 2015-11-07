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
 * INJECTION stats  (only for developpment)
 * V1.0
 * 
 * This script creates documents for collections :
 * - Stats
 * 
 * AUTHOR : Christophe kervella pour QaoBee
 */
//////////////////////////////////////////////////////////

db.getCollection('SB_Stats').remove({"eventId":"e254897f-cf3a-48b8-bed5-a4d4664ab4a4"});

/* 1 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e6a"),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "holder",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 69,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "1ce4591d-74a8-46e9-af80-d633f9344d27"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305531429),
    "value" : "1"
});

/* 2 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e6b"),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "positionType",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 70,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "1ce4591d-74a8-46e9-af80-d633f9344d27"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305531430),
    "value" : "right-wingman"
});

/* 3 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e6c"),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "holder",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 71,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305542895),
    "value" : "1"
});

/* 4 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e6d"),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "positionType",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 72,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305542896),
    "value" : "pivot"
});

/* 5 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e6e"),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "holder",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 73,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305551302),
    "value" : "1"
});

/* 6 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e6f"),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "positionType",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 74,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305551302),
    "value" : "right-backcourt"
});

/* 7 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e70"),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "holder",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 75,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305555919),
    "value" : "1"
});

/* 8 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e71"),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "positionType",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 76,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305555919),
    "value" : "center-backcourt"
});

/* 9 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e72"),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "holder",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 77,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305558932),
    "value" : "1"
});

/* 10 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e73"),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "positionType",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 78,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305558932),
    "value" : "goalkeeper"
});

/* 11 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e74"),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "holder",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 79,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305567020),
    "value" : "1"
});

/* 12 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e75"),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "positionType",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 80,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305567020),
    "value" : "left-backcourt"
});

/* 13 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e76"),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "holder",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 81,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305570065),
    "value" : "1"
});

/* 14 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e77"),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "positionType",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 82,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305570065),
    "value" : "left-wingman"
});

db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e6a"),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "substitue",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 69,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305570065),
    "value" : "1"
});

db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e6a"),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "substitue",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 69,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "46bea3c9-a3c0-4f4e-91fc-0bd2797b48df"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305570065),
    "value" : "1"
});

db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e6a"),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "substitue",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 69,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5a1c12af-0fc4-4eb8-a342-216e51bbada8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305570065),
    "value" : "1"
});

db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e6a"),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "substitue",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 69,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "07cfa115-6a8e-42b3-8a08-16ce1d7dbd89"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305570065),
    "value" : "1"
});

db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e6a"),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "substitue",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 69,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305570065),
    "value" : "1"
});

/* 15 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e78"),
    "activityId" : "ACT-HAND",
    "chrono" : 24,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 83,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305601516),
    "value" : "bottom-left"
});

/* 16 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e79"),
    "activityId" : "ACT-HAND",
    "chrono" : 25,
    "code" : "shootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 84,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305602615),
    "value" : "RUP"
});

/* 17 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e7a"),
    "activityId" : "ACT-HAND",
    "chrono" : 26,
    "code" : "stopOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 85,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305603571),
    "value" : "1"
});

/* 18 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e7b"),
    "activityId" : "ACT-HAND",
    "chrono" : 26,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 86,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305603528),
    "value" : "0"
});

/* 19 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e7c"),
    "activityId" : "ACT-HAND",
    "chrono" : 40,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 87,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305617692),
    "value" : "top-right"
});

/* 20 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e7d"),
    "activityId" : "ACT-HAND",
    "chrono" : 41,
    "code" : "shootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 88,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305618946),
    "value" : "LMIDDLE"
});

/* 21 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e7e"),
    "activityId" : "ACT-HAND",
    "chrono" : 42,
    "code" : "goalScored",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 89,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305619933),
    "value" : "1"
});

/* 22 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e7f"),
    "activityId" : "ACT-HAND",
    "chrono" : 42,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 90,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305619933),
    "value" : "16"
});

/* 23 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e80"),
    "activityId" : "ACT-HAND",
    "chrono" : 52,
    "code" : "neutralization",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 91,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305629394),
    "value" : "1"
});

/* 24 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e81"),
    "activityId" : "ACT-HAND",
    "chrono" : 52,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 92,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305629393),
    "value" : "10"
});

/* 25 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e82"),
    "activityId" : "ACT-HAND",
    "chrono" : 53,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 93,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305630831),
    "value" : "0"
});

/* 26 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e83"),
    "activityId" : "ACT-HAND",
    "chrono" : 64,
    "code" : "neutralization",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 94,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305641611),
    "value" : "1"
});

/* 27 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e84"),
    "activityId" : "ACT-HAND",
    "chrono" : 64,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 95,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305641611),
    "value" : "11"
});

/* 28 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e85"),
    "activityId" : "ACT-HAND",
    "chrono" : 65,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 96,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305642430),
    "value" : "0"
});

/* 29 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e86"),
    "activityId" : "ACT-HAND",
    "chrono" : 68,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 97,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305645555),
    "value" : "bottom-center"
});

/* 30 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e87"),
    "activityId" : "ACT-HAND",
    "chrono" : 70,
    "code" : "shootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 98,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305647473),
    "value" : "RDOWN"
});

/* 31 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e88"),
    "activityId" : "ACT-HAND",
    "chrono" : 71,
    "code" : "stopOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 99,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305648298),
    "value" : "1"
});

/* 32 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e89"),
    "activityId" : "ACT-HAND",
    "chrono" : 71,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 100,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305648266),
    "value" : "6"
});

/* 33 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e8a"),
    "activityId" : "ACT-HAND",
    "chrono" : 76,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 101,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305654045),
    "value" : "bottom-center"
});

/* 34 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e8b"),
    "activityId" : "ACT-HAND",
    "chrono" : 80,
    "code" : "shootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 102,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305657575),
    "value" : "LMIDDLE"
});

/* 35 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e8c"),
    "activityId" : "ACT-HAND",
    "chrono" : 81,
    "code" : "goalScored",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 103,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305658561),
    "value" : "1"
});

/* 36 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e8d"),
    "activityId" : "ACT-HAND",
    "chrono" : 81,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 104,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305658563),
    "value" : "10"
});

/* 37 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e8e"),
    "activityId" : "ACT-HAND",
    "chrono" : 95,
    "code" : "neutralization",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 105,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305673125),
    "value" : "1"
});

/* 38 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e8f"),
    "activityId" : "ACT-HAND",
    "chrono" : 95,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 106,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305673125),
    "value" : "14"
});

/* 39 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e90"),
    "activityId" : "ACT-HAND",
    "chrono" : 97,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 107,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305674539),
    "value" : "2"
});

/* 40 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e91"),
    "activityId" : "ACT-HAND",
    "chrono" : 104,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 108,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305681928),
    "value" : "bottom-center"
});

/* 41 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e92"),
    "activityId" : "ACT-HAND",
    "chrono" : 106,
    "code" : "pole",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 109,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305683441),
    "value" : "top-pole"
});

/* 42 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e93"),
    "activityId" : "ACT-HAND",
    "chrono" : 136,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 110,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305714082),
    "value" : "bottom-center"
});

/* 43 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e94"),
    "activityId" : "ACT-HAND",
    "chrono" : 139,
    "code" : "pole",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 111,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305716678),
    "value" : "left-pole"
});

/* 44 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e95"),
    "activityId" : "ACT-HAND",
    "chrono" : 144,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 112,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305722269),
    "value" : "0"
});

/* 45 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e96"),
    "activityId" : "ACT-HAND",
    "chrono" : 147,
    "code" : "passOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 113,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305724797),
    "value" : "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"
});

/* 46 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e97"),
    "activityId" : "ACT-HAND",
    "chrono" : 151,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 114,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305728541),
    "value" : "corner-left"
});

/* 47 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e98"),
    "activityId" : "ACT-HAND",
    "chrono" : 153,
    "code" : "shootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 115,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305730912),
    "value" : "LUP"
});

/* 48 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e99"),
    "activityId" : "ACT-HAND",
    "chrono" : 154,
    "code" : "goalScored",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 116,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305732021),
    "value" : "1"
});

/* 49 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e9a"),
    "activityId" : "ACT-HAND",
    "chrono" : 154,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 117,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305732022),
    "value" : "9"
});

/* 50 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e9b"),
    "activityId" : "ACT-HAND",
    "chrono" : 176,
    "code" : "neutralization",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 118,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305753670),
    "value" : "1"
});

/* 1 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e9c"),
    "activityId" : "ACT-HAND",
    "chrono" : 176,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 119,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305753671),
    "value" : "22"
});

/* 2 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e9d"),
    "activityId" : "ACT-HAND",
    "chrono" : 177,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 120,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305754977),
    "value" : "0"
});

/* 3 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e9e"),
    "activityId" : "ACT-HAND",
    "chrono" : 187,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 121,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305764751),
    "value" : "top-left"
});

/* 4 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697e9f"),
    "activityId" : "ACT-HAND",
    "chrono" : 188,
    "code" : "shootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 122,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305765888),
    "value" : "LMIDDLE"
});

/* 5 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ea0"),
    "activityId" : "ACT-HAND",
    "chrono" : 189,
    "code" : "stopOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 123,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305766844),
    "value" : "1"
});

/* 6 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ea1"),
    "activityId" : "ACT-HAND",
    "chrono" : 189,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 124,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305766809),
    "value" : "0"
});

/* 7 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ea2"),
    "activityId" : "ACT-HAND",
    "chrono" : 195,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 125,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305772748),
    "value" : "0"
});

/* 8 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ea3"),
    "activityId" : "ACT-HAND",
    "chrono" : 197,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 126,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305774533),
    "value" : "top-left"
});

/* 9 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ea4"),
    "activityId" : "ACT-HAND",
    "chrono" : 198,
    "code" : "shootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 127,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305775477),
    "value" : "LMIDDLE"
});

/* 10 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ea5"),
    "activityId" : "ACT-HAND",
    "chrono" : 199,
    "code" : "goalconceded",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 128,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305776499),
    "value" : "1"
});

/* 11 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ea6"),
    "activityId" : "ACT-HAND",
    "chrono" : 199,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 129,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305776499),
    "value" : "4"
});

/* 12 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ea7"),
    "activityId" : "ACT-HAND",
    "chrono" : 212,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 130,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305789470),
    "value" : "top-left"
});

/* 13 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ea8"),
    "activityId" : "ACT-HAND",
    "chrono" : 213,
    "code" : "shootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 131,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305791411),
    "value" : "LUP"
});

/* 14 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ea9"),
    "activityId" : "ACT-HAND",
    "chrono" : 214,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 133,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305792434),
    "value" : "15"
});

/* 15 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697eaa"),
    "activityId" : "ACT-HAND",
    "chrono" : 215,
    "code" : "stopOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 132,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305792476),
    "value" : "1"
});

/* 16 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697eab"),
    "activityId" : "ACT-HAND",
    "chrono" : 215,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 134,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305792496),
    "value" : "1"
});

/* 17 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697eac"),
    "activityId" : "ACT-HAND",
    "chrono" : 219,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 135,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305796605),
    "value" : "bottom-center"
});

/* 18 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ead"),
    "activityId" : "ACT-HAND",
    "chrono" : 221,
    "code" : "pole",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 136,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305799318),
    "value" : "right-pole"
});

/* 19 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697eae"),
    "activityId" : "ACT-HAND",
    "chrono" : 236,
    "code" : "neutralization",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 137,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305814088),
    "value" : "1"
});

/* 20 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697eaf"),
    "activityId" : "ACT-HAND",
    "chrono" : 236,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 138,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305814089),
    "value" : "21"
});

/* 21 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697eb0"),
    "activityId" : "ACT-HAND",
    "chrono" : 237,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 139,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305815384),
    "value" : "0"
});

/* 22 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697eb1"),
    "activityId" : "ACT-HAND",
    "chrono" : 252,
    "code" : "duelLoose",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 140,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305830318),
    "value" : "1"
});

/* 23 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697eb2"),
    "activityId" : "ACT-HAND",
    "chrono" : 252,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 141,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305830320),
    "value" : "14"
});

/* 24 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697eb3"),
    "activityId" : "ACT-HAND",
    "chrono" : 267,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 142,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305844634),
    "value" : "top-center"
});

/* 25 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697eb4"),
    "activityId" : "ACT-HAND",
    "chrono" : 268,
    "code" : "shootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 143,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305845653),
    "value" : "LUP"
});

/* 26 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697eb5"),
    "activityId" : "ACT-HAND",
    "chrono" : 268,
    "code" : "goalconceded",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 144,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305846523),
    "value" : "1"
});

/* 27 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697eb6"),
    "activityId" : "ACT-HAND",
    "chrono" : 268,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 145,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305846524),
    "value" : "16"
});

/* 28 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697eb7"),
    "activityId" : "ACT-HAND",
    "chrono" : 278,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 146,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305855818),
    "value" : "0"
});

/* 29 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697eb8"),
    "activityId" : "ACT-HAND",
    "chrono" : 279,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 147,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305857415),
    "value" : "top-center"
});

/* 30 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697eb9"),
    "activityId" : "ACT-HAND",
    "chrono" : 282,
    "code" : "shootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 148,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305860455),
    "value" : "CDOWN"
});

/* 31 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697eba"),
    "activityId" : "ACT-HAND",
    "chrono" : 284,
    "code" : "goalconceded",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 149,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305861800),
    "value" : "1"
});

/* 32 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ebb"),
    "activityId" : "ACT-HAND",
    "chrono" : 284,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 150,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305861799),
    "value" : "6"
});

/* 33 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ebc"),
    "activityId" : "ACT-HAND",
    "chrono" : 289,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 151,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305866597),
    "value" : "top-center"
});

/* 34 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ebd"),
    "activityId" : "ACT-HAND",
    "chrono" : 291,
    "code" : "shootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 152,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305869509),
    "value" : "RDOWN"
});

/* 35 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ebe"),
    "activityId" : "ACT-HAND",
    "chrono" : 292,
    "code" : "goalScored",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 153,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305870355),
    "value" : "1"
});

/* 36 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ebf"),
    "activityId" : "ACT-HAND",
    "chrono" : 292,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 154,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305870355),
    "value" : "8"
});

/* 37 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ec0"),
    "activityId" : "ACT-HAND",
    "chrono" : 303,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 155,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305880659),
    "value" : "11"
});

/* 38 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ec1"),
    "activityId" : "ACT-HAND",
    "chrono" : 308,
    "code" : "looseball",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 156,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305885807),
    "value" : "1"
});

/* 39 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ec2"),
    "activityId" : "ACT-HAND",
    "chrono" : 308,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 157,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305885808),
    "value" : "5"
});

/* 40 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ec3"),
    "activityId" : "ACT-HAND",
    "chrono" : 325,
    "code" : "interceptionOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 158,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305903213),
    "value" : "1"
});

/* 41 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ec4"),
    "activityId" : "ACT-HAND",
    "chrono" : 325,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 159,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305903216),
    "value" : "17"
});

/* 42 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ec5"),
    "activityId" : "ACT-HAND",
    "chrono" : 331,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 160,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305908785),
    "value" : "top-center"
});

/* 43 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ec6"),
    "activityId" : "ACT-HAND",
    "chrono" : 332,
    "code" : "shootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 161,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305910248),
    "value" : "LMIDDLE"
});

/* 44 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ec7"),
    "activityId" : "ACT-HAND",
    "chrono" : 336,
    "code" : "goalScored",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 162,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305913883),
    "value" : "1"
});

/* 45 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ec8"),
    "activityId" : "ACT-HAND",
    "chrono" : 336,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 163,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305913883),
    "value" : "11"
});

/* 46 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ec9"),
    "activityId" : "ACT-HAND",
    "chrono" : 347,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 164,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305924923),
    "value" : "bottom-center"
});

/* 47 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697eca"),
    "activityId" : "ACT-HAND",
    "chrono" : 348,
    "code" : "shootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 165,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305926034),
    "value" : "LDOWN"
});

/* 48 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ecb"),
    "activityId" : "ACT-HAND",
    "chrono" : 349,
    "code" : "goalconceded",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 166,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305927331),
    "value" : "1"
});

/* 49 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ecc"),
    "activityId" : "ACT-HAND",
    "chrono" : 349,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 167,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305927329),
    "value" : "13"
});

/* 50 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ecd"),
    "activityId" : "ACT-HAND",
    "chrono" : 356,
    "code" : "forceAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 168,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305934616),
    "value" : "1"
});

/* 1 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ece"),
    "activityId" : "ACT-HAND",
    "chrono" : 356,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 169,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305934617),
    "value" : "7"
});

/* 2 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ecf"),
    "activityId" : "ACT-HAND",
    "chrono" : 364,
    "code" : "neutralization",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 170,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305942428),
    "value" : "1"
});

/* 3 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ed0"),
    "activityId" : "ACT-HAND",
    "chrono" : 364,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 171,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305942428),
    "value" : "7"
});

/* 4 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ed1"),
    "activityId" : "ACT-HAND",
    "chrono" : 366,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 172,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305943979),
    "value" : "0"
});

/* 5 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ed2"),
    "activityId" : "ACT-HAND",
    "chrono" : 374,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 173,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305952522),
    "value" : "bottom-left"
});

/* 6 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ed3"),
    "activityId" : "ACT-HAND",
    "chrono" : 375,
    "code" : "shootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 174,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305953607),
    "value" : "LDOWN"
});

/* 7 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ed4"),
    "activityId" : "ACT-HAND",
    "chrono" : 376,
    "code" : "stopOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 175,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305954508),
    "value" : "1"
});

/* 8 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ed5"),
    "activityId" : "ACT-HAND",
    "chrono" : 376,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 176,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305954478),
    "value" : "10"
});

/* 9 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ed6"),
    "activityId" : "ACT-HAND",
    "chrono" : 397,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 177,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305975664),
    "value" : "bottom-center"
});

/* 10 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ed7"),
    "activityId" : "ACT-HAND",
    "chrono" : 399,
    "code" : "outside",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 178,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305977221),
    "value" : "outside-right"
});

/* 11 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ed8"),
    "activityId" : "ACT-HAND",
    "chrono" : 399,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 179,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305977221),
    "value" : "23"
});

/* 12 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ed9"),
    "activityId" : "ACT-HAND",
    "chrono" : 409,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 180,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305987131),
    "value" : "top-center"
});

/* 13 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697eda"),
    "activityId" : "ACT-HAND",
    "chrono" : 410,
    "code" : "shootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 181,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305988299),
    "value" : "RUP"
});

/* 14 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697edb"),
    "activityId" : "ACT-HAND",
    "chrono" : 411,
    "code" : "stopOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 182,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305989164),
    "value" : "1"
});

/* 15 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697edc"),
    "activityId" : "ACT-HAND",
    "chrono" : 411,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 183,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446305989129),
    "value" : "12"
});

/* 16 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697edd"),
    "activityId" : "ACT-HAND",
    "chrono" : 430,
    "code" : "substitue",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 184,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306008436),
    "value" : "1"
});

/* 17 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ede"),
    "activityId" : "ACT-HAND",
    "chrono" : 430,
    "code" : "playTime",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 185,
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306008437),
    "value" : "430"
});

/* 18 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697edf"),
    "activityId" : "ACT-HAND",
    "chrono" : 432,
    "code" : "holder",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 186,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306010613),
    "value" : "1"
});

/* 19 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ee0"),
    "activityId" : "ACT-HAND",
    "chrono" : 432,
    "code" : "positionType",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 187,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306010613),
    "value" : "left-wingman"
});

/* 20 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ee1"),
    "activityId" : "ACT-HAND",
    "chrono" : 436,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 188,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306014470),
    "value" : "25"
});

/* 21 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ee2"),
    "activityId" : "ACT-HAND",
    "chrono" : 440,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 189,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306018059),
    "value" : "0"
});

/* 22 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ee3"),
    "activityId" : "ACT-HAND",
    "chrono" : 444,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 190,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306022075),
    "value" : "corner-left"
});

/* 23 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ee4"),
    "activityId" : "ACT-HAND",
    "chrono" : 445,
    "code" : "shootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 191,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306023561),
    "value" : "LUP"
});

/* 24 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ee5"),
    "activityId" : "ACT-HAND",
    "chrono" : 446,
    "code" : "stopOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 192,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306024423),
    "value" : "1"
});

/* 25 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ee6"),
    "activityId" : "ACT-HAND",
    "chrono" : 446,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 193,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306024385),
    "value" : "0"
});

/* 26 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ee7"),
    "activityId" : "ACT-HAND",
    "chrono" : 457,
    "code" : "interceptionOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 194,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306035538),
    "value" : "1"
});

/* 27 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ee8"),
    "activityId" : "ACT-HAND",
    "chrono" : 457,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 195,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306035538),
    "value" : "11"
});

/* 28 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ee9"),
    "activityId" : "ACT-HAND",
    "chrono" : 463,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 196,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306041179),
    "value" : "corner-left"
});

/* 29 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697eea"),
    "activityId" : "ACT-HAND",
    "chrono" : 464,
    "code" : "pole",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 197,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306042819),
    "value" : "right-pole"
});

/* 30 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697eeb"),
    "activityId" : "ACT-HAND",
    "chrono" : 467,
    "code" : "passOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 198,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306045682),
    "value" : "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"
});

/* 31 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697eec"),
    "activityId" : "ACT-HAND",
    "chrono" : 474,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 199,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306051966),
    "value" : "corner-left"
});

/* 32 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697eed"),
    "activityId" : "ACT-HAND",
    "chrono" : 475,
    "code" : "shootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 200,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306053331),
    "value" : "RMIDDLE"
});

/* 33 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697eee"),
    "activityId" : "ACT-HAND",
    "chrono" : 477,
    "code" : "goalScored",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 201,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306055093),
    "value" : "1"
});

/* 34 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697eef"),
    "activityId" : "ACT-HAND",
    "chrono" : 477,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 202,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306055093),
    "value" : "20"
});

/* 35 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ef0"),
    "activityId" : "ACT-HAND",
    "chrono" : 482,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 203,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306060422),
    "value" : "bottom-left"
});

/* 36 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ef1"),
    "activityId" : "ACT-HAND",
    "chrono" : 483,
    "code" : "pole",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 204,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306061805),
    "value" : "left-pole"
});

/* 37 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ef2"),
    "activityId" : "ACT-HAND",
    "chrono" : 507,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 205,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306085130),
    "value" : "bottom-left"
});

/* 38 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ef3"),
    "activityId" : "ACT-HAND",
    "chrono" : 508,
    "code" : "shootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 206,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306086326),
    "value" : "RMIDDLE"
});

/* 39 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ef4"),
    "activityId" : "ACT-HAND",
    "chrono" : 509,
    "code" : "goalconceded",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 207,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306087188),
    "value" : "1"
});

/* 40 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ef5"),
    "activityId" : "ACT-HAND",
    "chrono" : 509,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 208,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306087188),
    "value" : "32"
});

/* 41 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ef6"),
    "activityId" : "ACT-HAND",
    "chrono" : 513,
    "code" : "timeoutThem",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 209,
    "owner" :  ["Ploemeur Atlantique Hb"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306091225),
    "value" : "1"
});

/* 42 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ef7"),
    "activityId" : "ACT-HAND",
    "chrono" : 533,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 210,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306181558),
    "value" : "20"
});

/* 43 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ef8"),
    "activityId" : "ACT-HAND",
    "chrono" : 535,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 211,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306184140),
    "value" : "top-left"
});

/* 44 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697ef9"),
    "activityId" : "ACT-HAND",
    "chrono" : 536,
    "code" : "shootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 212,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306185159),
    "value" : "LDOWN"
});

/* 45 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697efa"),
    "activityId" : "ACT-HAND",
    "chrono" : 537,
    "code" : "stopOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 213,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306186016),
    "value" : "1"
});

/* 46 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697efb"),
    "activityId" : "ACT-HAND",
    "chrono" : 537,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 214,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306185970),
    "value" : "0"
});

/* 47 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697efc"),
    "activityId" : "ACT-HAND",
    "chrono" : 549,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 215,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306197794),
    "value" : "corner-left"
});

/* 48 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697efd"),
    "activityId" : "ACT-HAND",
    "chrono" : 551,
    "code" : "outside",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 216,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306199374),
    "value" : "outside-right"
});

/* 49 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697efe"),
    "activityId" : "ACT-HAND",
    "chrono" : 551,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 217,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306199374),
    "value" : "14"
});

/* 50 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697eff"),
    "activityId" : "ACT-HAND",
    "chrono" : 561,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 218,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306209928),
    "value" : "top-center"
});

/* 1 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697f00"),
    "activityId" : "ACT-HAND",
    "chrono" : 562,
    "code" : "shootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 219,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306211246),
    "value" : "CDOWN"
});

/* 2 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697f01"),
    "activityId" : "ACT-HAND",
    "chrono" : 564,
    "code" : "goalconceded",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 220,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306212843),
    "value" : "1"
});

/* 3 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697f02"),
    "activityId" : "ACT-HAND",
    "chrono" : 564,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 221,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306212843),
    "value" : "13"
});

/* 4 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697f03"),
    "activityId" : "ACT-HAND",
    "chrono" : 580,
    "code" : "substitue",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 222,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306229021),
    "value" : "1"
});

/* 5 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697f04"),
    "activityId" : "ACT-HAND",
    "chrono" : 580,
    "code" : "playTime",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 223,
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306229021),
    "value" : "580"
});

/* 6 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697f05"),
    "activityId" : "ACT-HAND",
    "chrono" : 583,
    "code" : "holder",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 224,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306232357),
    "value" : "1"
});

/* 7 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697f06"),
    "activityId" : "ACT-HAND",
    "chrono" : 583,
    "code" : "positionType",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 225,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306232358),
    "value" : "center-backcourt"
});

/* 8 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697f07"),
    "activityId" : "ACT-HAND",
    "chrono" : 588,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 226,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5a1c12af-0fc4-4eb8-a342-216e51bbada8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306236941),
    "value" : "top-center"
});

/* 9 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697f08"),
    "activityId" : "ACT-HAND",
    "chrono" : 590,
    "code" : "outside",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 227,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5a1c12af-0fc4-4eb8-a342-216e51bbada8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306238453),
    "value" : "outside-left"
});

/* 10 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697f09"),
    "activityId" : "ACT-HAND",
    "chrono" : 590,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 228,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306238453),
    "value" : "26"
});

/* 11 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697f0a"),
    "activityId" : "ACT-HAND",
    "chrono" : 595,
    "code" : "neutralization",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 229,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306243740),
    "value" : "1"
});

/* 12 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb8e4b09c0c51697f0b"),
    "activityId" : "ACT-HAND",
    "chrono" : 595,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 230,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306243741),
    "value" : "5"
});

/* 13 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f0c"),
    "activityId" : "ACT-HAND",
    "chrono" : 598,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 231,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306246896),
    "value" : "0"
});

/* 14 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f0d"),
    "activityId" : "ACT-HAND",
    "chrono" : 612,
    "code" : "neutralization",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 232,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306260638),
    "value" : "1"
});

/* 15 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f0e"),
    "activityId" : "ACT-HAND",
    "chrono" : 612,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 233,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306260636),
    "value" : "14"
});

/* 16 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f0f"),
    "activityId" : "ACT-HAND",
    "chrono" : 613,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 234,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306261517),
    "value" : "0"
});

/* 17 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f10"),
    "activityId" : "ACT-HAND",
    "chrono" : 624,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 235,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306273288),
    "value" : "top-right"
});

/* 18 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f11"),
    "activityId" : "ACT-HAND",
    "chrono" : 625,
    "code" : "shootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 236,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306274363),
    "value" : "CDOWN"
});

/* 19 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f12"),
    "activityId" : "ACT-HAND",
    "chrono" : 626,
    "code" : "stopOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 237,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306275449),
    "value" : "1"
});

/* 20 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f13"),
    "activityId" : "ACT-HAND",
    "chrono" : 626,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 238,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306275412),
    "value" : "0"
});

/* 21 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f14"),
    "activityId" : "ACT-HAND",
    "chrono" : 639,
    "code" : "looseball",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 239,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5a1c12af-0fc4-4eb8-a342-216e51bbada8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306288251),
    "value" : "1"
});

/* 22 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f15"),
    "activityId" : "ACT-HAND",
    "chrono" : 639,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 240,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306288254),
    "value" : "12"
});

/* 23 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f16"),
    "activityId" : "ACT-HAND",
    "chrono" : 661,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 241,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306310385),
    "value" : "bottom-left"
});

/* 24 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f17"),
    "activityId" : "ACT-HAND",
    "chrono" : 664,
    "code" : "pole",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 242,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306312892),
    "value" : "left-pole"
});

/* 25 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f18"),
    "activityId" : "ACT-HAND",
    "chrono" : 692,
    "code" : "substitue",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 243,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5a1c12af-0fc4-4eb8-a342-216e51bbada8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306340966),
    "value" : "1"
});

/* 26 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f19"),
    "activityId" : "ACT-HAND",
    "chrono" : 692,
    "code" : "playTime",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 244,
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306340966),
    "value" : "692"
});

/* 27 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f1a"),
    "activityId" : "ACT-HAND",
    "chrono" : 694,
    "code" : "holder",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 245,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5a1c12af-0fc4-4eb8-a342-216e51bbada8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306343551),
    "value" : "1"
});

/* 28 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f1b"),
    "activityId" : "ACT-HAND",
    "chrono" : 694,
    "code" : "positionType",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 246,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5a1c12af-0fc4-4eb8-a342-216e51bbada8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306343552),
    "value" : "right-wingman"
});

/* 29 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f1c"),
    "activityId" : "ACT-HAND",
    "chrono" : 698,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 247,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5a1c12af-0fc4-4eb8-a342-216e51bbada8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306347441),
    "value" : "corner-right"
});

/* 30 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f1d"),
    "activityId" : "ACT-HAND",
    "chrono" : 701,
    "code" : "pole",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 248,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5a1c12af-0fc4-4eb8-a342-216e51bbada8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306350427),
    "value" : "left-pole"
});

/* 31 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f1e"),
    "activityId" : "ACT-HAND",
    "chrono" : 708,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 249,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306357167),
    "value" : "top-right"
});

/* 32 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f1f"),
    "activityId" : "ACT-HAND",
    "chrono" : 709,
    "code" : "shootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 250,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306358061),
    "value" : "CDOWN"
});

/* 33 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f20"),
    "activityId" : "ACT-HAND",
    "chrono" : 710,
    "code" : "goalconceded",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 251,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306359106),
    "value" : "1"
});

/* 34 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f21"),
    "activityId" : "ACT-HAND",
    "chrono" : 710,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 252,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306359106),
    "value" : "71"
});

/* 35 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f22"),
    "activityId" : "ACT-HAND",
    "chrono" : 714,
    "code" : "forceAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 253,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306362921),
    "value" : "1"
});

/* 36 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f23"),
    "activityId" : "ACT-HAND",
    "chrono" : 714,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 254,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306362921),
    "value" : "4"
});

/* 37 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f24"),
    "activityId" : "ACT-HAND",
    "chrono" : 725,
    "code" : "duelLoose",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 255,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306373929),
    "value" : "1"
});

/* 38 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f25"),
    "activityId" : "ACT-HAND",
    "chrono" : 725,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 256,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306373929),
    "value" : "11"
});

/* 39 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f26"),
    "activityId" : "ACT-HAND",
    "chrono" : 727,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 257,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306376232),
    "value" : "top-right"
});

/* 40 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f27"),
    "activityId" : "ACT-HAND",
    "chrono" : 728,
    "code" : "shootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 258,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306377184),
    "value" : "LDOWN"
});

/* 41 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f28"),
    "activityId" : "ACT-HAND",
    "chrono" : 730,
    "code" : "goalconceded",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 259,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306378731),
    "value" : "1"
});

/* 42 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f29"),
    "activityId" : "ACT-HAND",
    "chrono" : 730,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 260,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306378731),
    "value" : "5"
});

/* 43 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f2a"),
    "activityId" : "ACT-HAND",
    "chrono" : 743,
    "code" : "marcher",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 261,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5a1c12af-0fc4-4eb8-a342-216e51bbada8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306392355),
    "value" : "1"
});

/* 44 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f2b"),
    "activityId" : "ACT-HAND",
    "chrono" : 743,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 262,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306392355),
    "value" : "13"
});

/* 45 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f2c"),
    "activityId" : "ACT-HAND",
    "chrono" : 764,
    "code" : "yellowCard",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 263,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5a1c12af-0fc4-4eb8-a342-216e51bbada8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306413385),
    "value" : "1"
});

/* 46 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f2d"),
    "activityId" : "ACT-HAND",
    "chrono" : 764,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 264,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306413382),
    "value" : "21"
});

/* 47 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f2e"),
    "activityId" : "ACT-HAND",
    "chrono" : 777,
    "code" : "neutralization",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 265,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306426239),
    "value" : "1"
});

/* 48 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f2f"),
    "activityId" : "ACT-HAND",
    "chrono" : 777,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 266,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306426241),
    "value" : "13"
});

/* 49 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f30"),
    "activityId" : "ACT-HAND",
    "chrono" : 817,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 267,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306466431),
    "value" : "top-center"
});

/* 50 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f31"),
    "activityId" : "ACT-HAND",
    "chrono" : 819,
    "code" : "shootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 268,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306468685),
    "value" : "LUP"
});

/* 1 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f32"),
    "activityId" : "ACT-HAND",
    "chrono" : 821,
    "code" : "stopOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 269,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306469808),
    "value" : "1"
});

/* 2 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f33"),
    "activityId" : "ACT-HAND",
    "chrono" : 821,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 270,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306469764),
    "value" : "44"
});

/* 3 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f34"),
    "activityId" : "ACT-HAND",
    "chrono" : 821,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 271,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306469838),
    "value" : "0"
});

/* 4 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f35"),
    "activityId" : "ACT-HAND",
    "chrono" : 858,
    "code" : "interceptionOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 272,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306506841),
    "value" : "1"
});

/* 5 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f36"),
    "activityId" : "ACT-HAND",
    "chrono" : 858,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 273,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306506841),
    "value" : "37"
});

/* 6 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f37"),
    "activityId" : "ACT-HAND",
    "chrono" : 893,
    "code" : "substitue",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 274,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306542859),
    "value" : "1"
});

/* 7 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f38"),
    "activityId" : "ACT-HAND",
    "chrono" : 893,
    "code" : "playTime",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 275,
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306542859),
    "value" : "310"
});

/* 8 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f39"),
    "activityId" : "ACT-HAND",
    "chrono" : 896,
    "code" : "holder",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 276,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306545121),
    "value" : "1"
});

/* 9 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f3a"),
    "activityId" : "ACT-HAND",
    "chrono" : 896,
    "code" : "positionType",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 277,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306545121),
    "value" : "center-backcourt"
});

/* 10 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f3b"),
    "activityId" : "ACT-HAND",
    "chrono" : 899,
    "code" : "substitue",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 278,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306548782),
    "value" : "1"
});

/* 11 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f3c"),
    "activityId" : "ACT-HAND",
    "chrono" : 899,
    "code" : "playTime",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 279,
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306548782),
    "value" : "3"
});

/* 12 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f3d"),
    "activityId" : "ACT-HAND",
    "chrono" : 901,
    "code" : "holder",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 280,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306550799),
    "value" : "1"
});

/* 13 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f3e"),
    "activityId" : "ACT-HAND",
    "chrono" : 901,
    "code" : "positionType",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 281,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306550800),
    "value" : "center-backcourt"
});

/* 14 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f3f"),
    "activityId" : "ACT-HAND",
    "chrono" : 909,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 282,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306558335),
    "value" : "bottom-right"
});

/* 15 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f40"),
    "activityId" : "ACT-HAND",
    "chrono" : 910,
    "code" : "shootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 283,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306559464),
    "value" : "LMIDDLE"
});

/* 16 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f41"),
    "activityId" : "ACT-HAND",
    "chrono" : 911,
    "code" : "goalScored",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 284,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306560458),
    "value" : "1"
});

/* 17 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f42"),
    "activityId" : "ACT-HAND",
    "chrono" : 911,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 285,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306560458),
    "value" : "53"
});

/* 18 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f43"),
    "activityId" : "ACT-HAND",
    "chrono" : 931,
    "code" : "interceptionOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 286,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306580657),
    "value" : "1"
});

/* 19 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f44"),
    "activityId" : "ACT-HAND",
    "chrono" : 931,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 287,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306580658),
    "value" : "20"
});

/* 20 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f45"),
    "activityId" : "ACT-HAND",
    "chrono" : 934,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 288,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306583800),
    "value" : "top-center"
});

/* 21 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f46"),
    "activityId" : "ACT-HAND",
    "chrono" : 936,
    "code" : "shootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 289,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306585371),
    "value" : "RUP"
});

/* 22 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f47"),
    "activityId" : "ACT-HAND",
    "chrono" : 937,
    "code" : "goalScored",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 290,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306586222),
    "value" : "1"
});

/* 23 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f48"),
    "activityId" : "ACT-HAND",
    "chrono" : 937,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 291,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306586222),
    "value" : "6"
});

/* 24 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f49"),
    "activityId" : "ACT-HAND",
    "chrono" : 950,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 292,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306599500),
    "value" : "bottom-center"
});

/* 25 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f4a"),
    "activityId" : "ACT-HAND",
    "chrono" : 951,
    "code" : "outside",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 293,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306600898),
    "value" : "outside-right"
});

/* 26 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f4b"),
    "activityId" : "ACT-HAND",
    "chrono" : 951,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 294,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306600899),
    "value" : "14"
});

/* 27 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f4c"),
    "activityId" : "ACT-HAND",
    "chrono" : 979,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 295,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "07cfa115-6a8e-42b3-8a08-16ce1d7dbd89"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306628192),
    "value" : "top-right"
});

/* 28 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f4d"),
    "activityId" : "ACT-HAND",
    "chrono" : 982,
    "code" : "outside",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 296,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "07cfa115-6a8e-42b3-8a08-16ce1d7dbd89"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306631420),
    "value" : "outside-left"
});

/* 29 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f4e"),
    "activityId" : "ACT-HAND",
    "chrono" : 982,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 297,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306631420),
    "value" : "30"
});

/* 30 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f4f"),
    "activityId" : "ACT-HAND",
    "chrono" : 993,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 298,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306642712),
    "value" : "top-center"
});

/* 31 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f50"),
    "activityId" : "ACT-HAND",
    "chrono" : 995,
    "code" : "shootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 299,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306644231),
    "value" : "LDOWN"
});

/* 32 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f51"),
    "activityId" : "ACT-HAND",
    "chrono" : 996,
    "code" : "goalconceded",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 300,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306645713),
    "value" : "1"
});

/* 33 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f52"),
    "activityId" : "ACT-HAND",
    "chrono" : 996,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 301,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306645714),
    "value" : "14"
});

/* 34 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f53"),
    "activityId" : "ACT-HAND",
    "chrono" : 1022,
    "code" : "substitue",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 302,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "07cfa115-6a8e-42b3-8a08-16ce1d7dbd89"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306671581),
    "value" : "1"
});

/* 35 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f54"),
    "activityId" : "ACT-HAND",
    "chrono" : 1022,
    "code" : "playTime",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 303,
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306671581),
    "value" : "1022"
});

/* 36 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f55"),
    "activityId" : "ACT-HAND",
    "chrono" : 1024,
    "code" : "holder",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 304,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "07cfa115-6a8e-42b3-8a08-16ce1d7dbd89"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306673755),
    "value" : "1"
});

/* 37 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f56"),
    "activityId" : "ACT-HAND",
    "chrono" : 1024,
    "code" : "positionType",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 305,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "07cfa115-6a8e-42b3-8a08-16ce1d7dbd89"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306673755),
    "value" : "pivot"
});

/* 38 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f57"),
    "activityId" : "ACT-HAND",
    "chrono" : 1028,
    "code" : "penaltyObtained",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 306,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "46bea3c9-a3c0-4f4e-91fc-0bd2797b48df"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306677625),
    "value" : "1"
});

/* 39 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f58"),
    "activityId" : "ACT-HAND",
    "chrono" : 1040,
    "code" : "substitue",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 307,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "46bea3c9-a3c0-4f4e-91fc-0bd2797b48df"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306689503),
    "value" : "1"
});

/* 40 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f59"),
    "activityId" : "ACT-HAND",
    "chrono" : 1040,
    "code" : "playTime",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 308,
    "owner" :  ["46bea3c9-a3c0-4f4e-91fc-0bd2797b48df"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306689503),
    "value" : "608"
});

/* 41 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f5a"),
    "activityId" : "ACT-HAND",
    "chrono" : 1042,
    "code" : "holder",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 309,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "46bea3c9-a3c0-4f4e-91fc-0bd2797b48df"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306691975),
    "value" : "1"
});

/* 42 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f5b"),
    "activityId" : "ACT-HAND",
    "chrono" : 1042,
    "code" : "positionType",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 310,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "46bea3c9-a3c0-4f4e-91fc-0bd2797b48df"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306691975),
    "value" : "left-wingman"
});

/* 43 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f5c"),
    "activityId" : "ACT-HAND",
    "chrono" : 1044,
    "code" : "passOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 311,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "46bea3c9-a3c0-4f4e-91fc-0bd2797b48df"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306693264),
    "value" : "ce18d73e-dedf-43e5-8e75-16e0375be349"
});

/* 44 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f5d"),
    "activityId" : "ACT-HAND",
    "chrono" : 1046,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 312,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306695127),
    "value" : "top-center"
});

/* 45 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f5e"),
    "activityId" : "ACT-HAND",
    "chrono" : 1047,
    "code" : "shootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 313,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306696637),
    "value" : "LMIDDLE"
});

/* 46 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f5f"),
    "activityId" : "ACT-HAND",
    "chrono" : 1048,
    "code" : "stopOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 314,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306697926),
    "value" : "1"
});

/* 47 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f60"),
    "activityId" : "ACT-HAND",
    "chrono" : 1048,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 315,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306697872),
    "value" : "52"
});

/* 48 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f61"),
    "activityId" : "ACT-HAND",
    "chrono" : 1048,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 316,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306697954),
    "value" : "0"
});

/* 49 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f62"),
    "activityId" : "ACT-HAND",
    "chrono" : 1054,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 317,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306703285),
    "value" : "corner-right"
});

/* 50 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f63"),
    "activityId" : "ACT-HAND",
    "chrono" : 1055,
    "code" : "shootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 318,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306704610),
    "value" : "RUP"
});

/* 1 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f64"),
    "activityId" : "ACT-HAND",
    "chrono" : 1056,
    "code" : "stopOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 319,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306705687),
    "value" : "1"
});

/* 2 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f65"),
    "activityId" : "ACT-HAND",
    "chrono" : 1056,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 320,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306705650),
    "value" : "0"
});

/* 3 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f66"),
    "activityId" : "ACT-HAND",
    "chrono" : 1089,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 321,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306738463),
    "value" : "bottom-left"
});

/* 4 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f67"),
    "activityId" : "ACT-HAND",
    "chrono" : 1090,
    "code" : "shootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 322,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306739642),
    "value" : "CDOWN"
});

/* 5 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f68"),
    "activityId" : "ACT-HAND",
    "chrono" : 1091,
    "code" : "goalScored",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 323,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306741093),
    "value" : "1"
});

/* 6 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f69"),
    "activityId" : "ACT-HAND",
    "chrono" : 1091,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 324,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306741092),
    "value" : "35"
});

/* 7 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f6a"),
    "activityId" : "ACT-HAND",
    "chrono" : 1096,
    "code" : "timeoutUs",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 325,
    "owner" :  ["Ploemeur Atlantique Hb"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306745784),
    "value" : "1"
});

/* 8 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f6b"),
    "activityId" : "ACT-HAND",
    "chrono" : 1125,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 326,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306804794),
    "value" : "0"
});

/* 9 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f6c"),
    "activityId" : "ACT-HAND",
    "chrono" : 1127,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 327,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306807275),
    "value" : "0"
});

/* 10 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f6d"),
    "activityId" : "ACT-HAND",
    "chrono" : 1133,
    "code" : "substitue",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 328,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306813453),
    "value" : "1"
});

/* 11 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f6e"),
    "activityId" : "ACT-HAND",
    "chrono" : 1133,
    "code" : "playTime",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 329,
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306813453),
    "value" : "1133"
});

/* 12 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f6f"),
    "activityId" : "ACT-HAND",
    "chrono" : 1136,
    "code" : "holder",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 330,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306816202),
    "value" : "1"
});

/* 13 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f70"),
    "activityId" : "ACT-HAND",
    "chrono" : 1136,
    "code" : "positionType",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 331,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306816202),
    "value" : "goalkeeper"
});

/* 14 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f71"),
    "activityId" : "ACT-HAND",
    "chrono" : 1145,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 332,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306825461),
    "value" : "0"
});

/* 15 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f72"),
    "activityId" : "ACT-HAND",
    "chrono" : 1151,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 333,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306831396),
    "value" : "0"
});

/* 16 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f73"),
    "activityId" : "ACT-HAND",
    "chrono" : 1154,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 334,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306833825),
    "value" : "top-center"
});

/* 17 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f74"),
    "activityId" : "ACT-HAND",
    "chrono" : 1155,
    "code" : "shootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 335,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306835159),
    "value" : "LUP"
});

/* 18 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f75"),
    "activityId" : "ACT-HAND",
    "chrono" : 1156,
    "code" : "stopOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 336,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306836414),
    "value" : "1"
});

/* 19 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f76"),
    "activityId" : "ACT-HAND",
    "chrono" : 1156,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 337,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306836380),
    "value" : "4"
});

/* 20 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f77"),
    "activityId" : "ACT-HAND",
    "chrono" : 1173,
    "code" : "looseball",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 338,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306853045),
    "value" : "1"
});

/* 21 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f78"),
    "activityId" : "ACT-HAND",
    "chrono" : 1173,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 339,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306853045),
    "value" : "16"
});

/* 22 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f79"),
    "activityId" : "ACT-HAND",
    "chrono" : 1195,
    "code" : "neutralization",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 340,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306875237),
    "value" : "1"
});

/* 23 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f7a"),
    "activityId" : "ACT-HAND",
    "chrono" : 1195,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 341,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306875237),
    "value" : "22"
});

/* 24 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f7b"),
    "activityId" : "ACT-HAND",
    "chrono" : 1200,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 342,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306880313),
    "value" : "0"
});

/* 25 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f7c"),
    "activityId" : "ACT-HAND",
    "chrono" : 1204,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 343,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306884173),
    "value" : "bottom-center"
});

/* 26 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f7d"),
    "activityId" : "ACT-HAND",
    "chrono" : 1205,
    "code" : "shootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 344,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306885446),
    "value" : "LMIDDLE"
});

/* 27 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f7e"),
    "activityId" : "ACT-HAND",
    "chrono" : 1207,
    "code" : "stopOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 345,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306886660),
    "value" : "1"
});

/* 28 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f7f"),
    "activityId" : "ACT-HAND",
    "chrono" : 1207,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 346,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306886641),
    "value" : "7"
});

/* 29 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f80"),
    "activityId" : "ACT-HAND",
    "chrono" : 1235,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 347,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "07cfa115-6a8e-42b3-8a08-16ce1d7dbd89"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306914870),
    "value" : "corner-right"
});

/* 30 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f81"),
    "activityId" : "ACT-HAND",
    "chrono" : 1236,
    "code" : "shootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 348,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "07cfa115-6a8e-42b3-8a08-16ce1d7dbd89"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306916017),
    "value" : "CDOWN"
});

/* 31 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f82"),
    "activityId" : "ACT-HAND",
    "chrono" : 1237,
    "code" : "goalScored",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 349,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "07cfa115-6a8e-42b3-8a08-16ce1d7dbd89"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306917323),
    "value" : "1"
});

/* 32 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f83"),
    "activityId" : "ACT-HAND",
    "chrono" : 1237,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 350,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306917324),
    "value" : "30"
});

/* 33 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f84"),
    "activityId" : "ACT-HAND",
    "chrono" : 1255,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 351,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306935546),
    "value" : "bottom-center"
});

/* 34 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f85"),
    "activityId" : "ACT-HAND",
    "chrono" : 1257,
    "code" : "outside",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 352,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306936894),
    "value" : "outside-top"
});

/* 35 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f86"),
    "activityId" : "ACT-HAND",
    "chrono" : 1257,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 353,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306936894),
    "value" : "20"
});

/* 36 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f87"),
    "activityId" : "ACT-HAND",
    "chrono" : 1258,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 354,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306938438),
    "value" : "1"
});

/* 37 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f88"),
    "activityId" : "ACT-HAND",
    "chrono" : 1294,
    "code" : "duelLoose",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 355,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306974793),
    "value" : "1"
});

/* 38 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f89"),
    "activityId" : "ACT-HAND",
    "chrono" : 1294,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 356,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306974793),
    "value" : "36"
});

/* 39 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f8a"),
    "activityId" : "ACT-HAND",
    "chrono" : 1296,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 357,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306977352),
    "value" : "top-left"
});

/* 40 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f8b"),
    "activityId" : "ACT-HAND",
    "chrono" : 1297,
    "code" : "shootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 358,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306978283),
    "value" : "LDOWN"
});

/* 41 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f8c"),
    "activityId" : "ACT-HAND",
    "chrono" : 1298,
    "code" : "stopOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 359,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306979156),
    "value" : "1"
});

/* 42 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f8d"),
    "activityId" : "ACT-HAND",
    "chrono" : 1298,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 360,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306979110),
    "value" : "0"
});

/* 43 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f8e"),
    "activityId" : "ACT-HAND",
    "chrono" : 1303,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 361,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306984330),
    "value" : "5"
});

/* 44 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f8f"),
    "activityId" : "ACT-HAND",
    "chrono" : 1305,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 362,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306986099),
    "value" : "top-left"
});

/* 45 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f90"),
    "activityId" : "ACT-HAND",
    "chrono" : 1306,
    "code" : "shootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 363,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306987150),
    "value" : "LDOWN"
});

/* 46 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f91"),
    "activityId" : "ACT-HAND",
    "chrono" : 1307,
    "code" : "goalconceded",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 364,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306987878),
    "value" : "1"
});

/* 47 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f92"),
    "activityId" : "ACT-HAND",
    "chrono" : 1307,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 365,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446306987880),
    "value" : "4"
});

/* 48 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f93"),
    "activityId" : "ACT-HAND",
    "chrono" : 1319,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 366,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307000119),
    "value" : "top-center"
});

/* 49 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f94"),
    "activityId" : "ACT-HAND",
    "chrono" : 1320,
    "code" : "shootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 367,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307001290),
    "value" : "RMIDDLE"
});

/* 50 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f95"),
    "activityId" : "ACT-HAND",
    "chrono" : 1321,
    "code" : "goalScored",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 368,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307002375),
    "value" : "1"
});

/* 1 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f96"),
    "activityId" : "ACT-HAND",
    "chrono" : 1321,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 369,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307002375),
    "value" : "14"
});

/* 2 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f97"),
    "activityId" : "ACT-HAND",
    "chrono" : 1413,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 370,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307316170),
    "value" : "92"
});

/* 3 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f98"),
    "activityId" : "ACT-HAND",
    "chrono" : 1418,
    "code" : "passOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 371,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "46bea3c9-a3c0-4f4e-91fc-0bd2797b48df"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307320793),
    "value" : "46bea3c9-a3c0-4f4e-91fc-0bd2797b48df"
});

/* 4 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f99"),
    "activityId" : "ACT-HAND",
    "chrono" : 1420,
    "code" : "passOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 372,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "46bea3c9-a3c0-4f4e-91fc-0bd2797b48df"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307322907),
    "value" : "cc5d449a-d466-400c-b73f-955c676103ed"
});

/* 5 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f9a"),
    "activityId" : "ACT-HAND",
    "chrono" : 1422,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 373,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307324977),
    "value" : "bottom-left"
});

/* 6 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f9b"),
    "activityId" : "ACT-HAND",
    "chrono" : 1423,
    "code" : "shootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 374,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307326389),
    "value" : "RMIDDLE"
});

/* 7 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f9c"),
    "activityId" : "ACT-HAND",
    "chrono" : 1424,
    "code" : "goalScored",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 375,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307327349),
    "value" : "1"
});

/* 8 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f9d"),
    "activityId" : "ACT-HAND",
    "chrono" : 1424,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 376,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307327349),
    "value" : "11"
});

/* 9 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f9e"),
    "activityId" : "ACT-HAND",
    "chrono" : 1436,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 377,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307338831),
    "value" : "0"
});

/* 10 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697f9f"),
    "activityId" : "ACT-HAND",
    "chrono" : 1449,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 378,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307351768),
    "value" : "top-left"
});

/* 11 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fa0"),
    "activityId" : "ACT-HAND",
    "chrono" : 1450,
    "code" : "shootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 379,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307352884),
    "value" : "LMIDDLE"
});

/* 12 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fa1"),
    "activityId" : "ACT-HAND",
    "chrono" : 1457,
    "code" : "goalScored",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 380,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307359825),
    "value" : "1"
});

/* 13 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fa2"),
    "activityId" : "ACT-HAND",
    "chrono" : 1457,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 381,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307359824),
    "value" : "21"
});

/* 14 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fa3"),
    "activityId" : "ACT-HAND",
    "chrono" : 1460,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 382,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307362768),
    "value" : "3"
});

/* 15 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fa4"),
    "activityId" : "ACT-HAND",
    "chrono" : 1463,
    "code" : "passOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 383,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307366435),
    "value" : "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"
});

/* 16 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fa5"),
    "activityId" : "ACT-HAND",
    "chrono" : 1466,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 384,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307368856),
    "value" : "bottom-center"
});

/* 17 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fa6"),
    "activityId" : "ACT-HAND",
    "chrono" : 1467,
    "code" : "shootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 385,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307369896),
    "value" : "LUP"
});

/* 18 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fa7"),
    "activityId" : "ACT-HAND",
    "chrono" : 1468,
    "code" : "goalScored",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 386,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307370760),
    "value" : "1"
});

/* 19 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fa8"),
    "activityId" : "ACT-HAND",
    "chrono" : 1468,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 387,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307370760),
    "value" : "8"
});

/* 20 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fa9"),
    "activityId" : "ACT-HAND",
    "chrono" : 1495,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 388,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307397816),
    "value" : "0"
});

/* 21 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697faa"),
    "activityId" : "ACT-HAND",
    "chrono" : 1497,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 389,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307400582),
    "value" : "top-left"
});

/* 22 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fab"),
    "activityId" : "ACT-HAND",
    "chrono" : 1499,
    "code" : "shootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 390,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307402481),
    "value" : "RDOWN"
});

/* 23 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fac"),
    "activityId" : "ACT-HAND",
    "chrono" : 1500,
    "code" : "goalScored",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 391,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307403535),
    "value" : "1"
});

/* 24 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fad"),
    "activityId" : "ACT-HAND",
    "chrono" : 1500,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 392,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307403534),
    "value" : "5"
});

/* 25 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fae"),
    "activityId" : "ACT-HAND",
    "chrono" : 1501,
    "code" : "substitue",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 393,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307518394),
    "value" : "1"
});

/* 26 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697faf"),
    "activityId" : "ACT-HAND",
    "chrono" : 1501,
    "code" : "playTime",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 394,
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307518395),
    "value" : "1501"
});

/* 27 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fb0"),
    "activityId" : "ACT-HAND",
    "chrono" : 1501,
    "code" : "holder",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 395,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307520734),
    "value" : "1"
});

/* 28 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fb1"),
    "activityId" : "ACT-HAND",
    "chrono" : 1501,
    "code" : "positionType",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 396,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307520734),
    "value" : "right-backcourt"
});

/* 29 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fb2"),
    "activityId" : "ACT-HAND",
    "chrono" : 1501,
    "code" : "holder",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 397,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307526721),
    "value" : "1"
});

/* 30 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fb3"),
    "activityId" : "ACT-HAND",
    "chrono" : 1501,
    "code" : "positionType",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 398,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307526721),
    "value" : "left-backcourt"
});

/* 31 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fb4"),
    "activityId" : "ACT-HAND",
    "chrono" : 1501,
    "code" : "substitue",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 399,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307533904),
    "value" : "1"
});

/* 32 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fb5"),
    "activityId" : "ACT-HAND",
    "chrono" : 1501,
    "code" : "playTime",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 400,
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307533904),
    "value" : "459"
});

/* 33 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fb6"),
    "activityId" : "ACT-HAND",
    "chrono" : 1501,
    "code" : "substitue",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 401,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307535923),
    "value" : "1"
});

/* 34 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fb7"),
    "activityId" : "ACT-HAND",
    "chrono" : 1501,
    "code" : "playTime",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 402,
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307535923),
    "value" : "605"
});

/* 35 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fb8"),
    "activityId" : "ACT-HAND",
    "chrono" : 1501,
    "code" : "holder",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 403,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307541826),
    "value" : "1"
});

/* 36 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fb9"),
    "activityId" : "ACT-HAND",
    "chrono" : 1501,
    "code" : "positionType",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 404,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307541827),
    "value" : "left-wingman"
});

/* 37 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fba"),
    "activityId" : "ACT-HAND",
    "chrono" : 1501,
    "code" : "substitue",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 405,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307546974),
    "value" : "1"
});

/* 38 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fbb"),
    "activityId" : "ACT-HAND",
    "chrono" : 1501,
    "code" : "playTime",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 406,
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307546974),
    "value" : "807"
});

/* 39 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fbc"),
    "activityId" : "ACT-HAND",
    "chrono" : 1501,
    "code" : "substitue",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 407,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307549773),
    "value" : "1"
});

/* 40 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fbd"),
    "activityId" : "ACT-HAND",
    "chrono" : 1501,
    "code" : "playTime",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 408,
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307549773),
    "value" : "459"
});

/* 41 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fbe"),
    "activityId" : "ACT-HAND",
    "chrono" : 1501,
    "code" : "holder",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 409,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307556392),
    "value" : "1"
});

/* 42 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fbf"),
    "activityId" : "ACT-HAND",
    "chrono" : 1501,
    "code" : "positionType",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 410,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307556392),
    "value" : "right-wingman"
});

/* 43 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fc0"),
    "activityId" : "ACT-HAND",
    "chrono" : 1520,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 411,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307587248),
    "value" : "19"
});

/* 44 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fc1"),
    "activityId" : "ACT-HAND",
    "chrono" : 1520,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 412,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307587984),
    "value" : "0"
});

/* 45 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fc2"),
    "activityId" : "ACT-HAND",
    "chrono" : 1524,
    "code" : "exclusionTempoObtained",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 413,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307591779),
    "value" : "1"
});

/* 46 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fc3"),
    "activityId" : "ACT-HAND",
    "chrono" : 1524,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 414,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307591771),
    "value" : "4"
});

/* 47 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fc4"),
    "activityId" : "ACT-HAND",
    "chrono" : 1529,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 415,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307596590),
    "value" : "top-center"
});

/* 48 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fc5"),
    "activityId" : "ACT-HAND",
    "chrono" : 1530,
    "code" : "shootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 416,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307597677),
    "value" : "RDOWN"
});

/* 49 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fc6"),
    "activityId" : "ACT-HAND",
    "chrono" : 1532,
    "code" : "goalconceded",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 417,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307599120),
    "value" : "1"
});

/* 50 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fc7"),
    "activityId" : "ACT-HAND",
    "chrono" : 1532,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 418,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307599119),
    "value" : "8"
});

/* 1 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fc8"),
    "activityId" : "ACT-HAND",
    "chrono" : 1563,
    "code" : "passOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 419,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307630313),
    "value" : "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"
});

/* 2 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fc9"),
    "activityId" : "ACT-HAND",
    "chrono" : 1565,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 420,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307632329),
    "value" : "top-left"
});

/* 3 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fca"),
    "activityId" : "ACT-HAND",
    "chrono" : 1567,
    "code" : "shootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 421,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307634264),
    "value" : "RDOWN"
});

/* 4 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fcb"),
    "activityId" : "ACT-HAND",
    "chrono" : 1568,
    "code" : "goalScored",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 422,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307635187),
    "value" : "1"
});

/* 5 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fcc"),
    "activityId" : "ACT-HAND",
    "chrono" : 1568,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 423,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307635186),
    "value" : "36"
});

/* 6 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fcd"),
    "activityId" : "ACT-HAND",
    "chrono" : 1584,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 424,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307652201),
    "value" : "corner-left"
});

/* 7 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fce"),
    "activityId" : "ACT-HAND",
    "chrono" : 1586,
    "code" : "shootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 425,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307653265),
    "value" : "LDOWN"
});

/* 8 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fcf"),
    "activityId" : "ACT-HAND",
    "chrono" : 1587,
    "code" : "stopOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 426,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307654412),
    "value" : "1"
});

/* 9 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fd0"),
    "activityId" : "ACT-HAND",
    "chrono" : 1587,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 427,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307654381),
    "value" : "0"
});

/* 10 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fd1"),
    "activityId" : "ACT-HAND",
    "chrono" : 1602,
    "code" : "looseball",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 428,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307669281),
    "value" : "1"
});

/* 11 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fd2"),
    "activityId" : "ACT-HAND",
    "chrono" : 1602,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 429,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307669281),
    "value" : "15"
});

/* 12 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fd3"),
    "activityId" : "ACT-HAND",
    "chrono" : 1617,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 430,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307685199),
    "value" : "bottom-center"
});

/* 13 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fd4"),
    "activityId" : "ACT-HAND",
    "chrono" : 1619,
    "code" : "shootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 431,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307686483),
    "value" : "RUP"
});

/* 14 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fd5"),
    "activityId" : "ACT-HAND",
    "chrono" : 1620,
    "code" : "stopOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 432,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307687434),
    "value" : "1"
});

/* 15 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fd6"),
    "activityId" : "ACT-HAND",
    "chrono" : 1620,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 433,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307687406),
    "value" : "0"
});

/* 16 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fd7"),
    "activityId" : "ACT-HAND",
    "chrono" : 1632,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 434,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307699996),
    "value" : "corner-left"
});

/* 17 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fd8"),
    "activityId" : "ACT-HAND",
    "chrono" : 1634,
    "code" : "shootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 435,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307702073),
    "value" : "RMIDDLE"
});

/* 18 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fd9"),
    "activityId" : "ACT-HAND",
    "chrono" : 1635,
    "code" : "stopOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 436,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307703080),
    "value" : "1"
});

/* 19 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fda"),
    "activityId" : "ACT-HAND",
    "chrono" : 1635,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 437,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307703056),
    "value" : "0"
});

/* 20 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fdb"),
    "activityId" : "ACT-HAND",
    "chrono" : 1641,
    "code" : "forceDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 438,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307709068),
    "value" : "1"
});

/* 21 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fdc"),
    "activityId" : "ACT-HAND",
    "chrono" : 1641,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 439,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307709069),
    "value" : "6"
});

/* 22 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fdd"),
    "activityId" : "ACT-HAND",
    "chrono" : 1669,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 440,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307736985),
    "value" : "top-right"
});

/* 23 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fde"),
    "activityId" : "ACT-HAND",
    "chrono" : 1670,
    "code" : "shootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 441,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307738069),
    "value" : "CDOWN"
});

/* 24 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fdf"),
    "activityId" : "ACT-HAND",
    "chrono" : 1671,
    "code" : "goalScored",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 442,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307739251),
    "value" : "1"
});

/* 25 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fe0"),
    "activityId" : "ACT-HAND",
    "chrono" : 1671,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 443,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307739249),
    "value" : "30"
});

/* 26 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fe1"),
    "activityId" : "ACT-HAND",
    "chrono" : 1678,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 444,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307745439),
    "value" : "0"
});

/* 27 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fe2"),
    "activityId" : "ACT-HAND",
    "chrono" : 1684,
    "code" : "exclusionTempoObtained",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 445,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307751904),
    "value" : "1"
});

/* 28 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fe3"),
    "activityId" : "ACT-HAND",
    "chrono" : 1687,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 446,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307754423),
    "value" : "0"
});

/* 29 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fe4"),
    "activityId" : "ACT-HAND",
    "chrono" : 1695,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 447,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307762699),
    "value" : "bottom-center"
});

/* 30 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fe5"),
    "activityId" : "ACT-HAND",
    "chrono" : 1697,
    "code" : "shootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 448,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307764810),
    "value" : "RUP"
});

/* 31 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fe6"),
    "activityId" : "ACT-HAND",
    "chrono" : 1698,
    "code" : "goalconceded",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 449,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307765826),
    "value" : "1"
});

/* 32 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fe7"),
    "activityId" : "ACT-HAND",
    "chrono" : 1698,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 450,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307765828),
    "value" : "11"
});

/* 33 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fe8"),
    "activityId" : "ACT-HAND",
    "chrono" : 1729,
    "code" : "looseball",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 451,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307797029),
    "value" : "1"
});

/* 34 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fe9"),
    "activityId" : "ACT-HAND",
    "chrono" : 1729,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 452,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307797034),
    "value" : "31"
});

/* 35 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fea"),
    "activityId" : "ACT-HAND",
    "chrono" : 1766,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 453,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307836506),
    "value" : "bottom-right"
});

/* 36 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697feb"),
    "activityId" : "ACT-HAND",
    "chrono" : 1767,
    "code" : "shootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 454,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307837554),
    "value" : "LDOWN"
});

/* 37 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fec"),
    "activityId" : "ACT-HAND",
    "chrono" : 1769,
    "code" : "goalconceded",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 455,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307838875),
    "value" : "1"
});

/* 38 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fed"),
    "activityId" : "ACT-HAND",
    "chrono" : 1769,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 456,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307838875),
    "value" : "40"
});

/* 39 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fee"),
    "activityId" : "ACT-HAND",
    "chrono" : 1803,
    "code" : "looseball",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 457,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307873444),
    "value" : "1"
});

/* 40 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fef"),
    "activityId" : "ACT-HAND",
    "chrono" : 1803,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 458,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307873444),
    "value" : "34"
});

/* 41 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697ff0"),
    "activityId" : "ACT-HAND",
    "chrono" : 1811,
    "code" : "neutralization",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 459,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307881467),
    "value" : "1"
});

/* 42 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697ff1"),
    "activityId" : "ACT-HAND",
    "chrono" : 1811,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 460,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307881471),
    "value" : "8"
});

/* 43 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697ff2"),
    "activityId" : "ACT-HAND",
    "chrono" : 1812,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 461,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307882676),
    "value" : "0"
});

/* 44 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697ff3"),
    "activityId" : "ACT-HAND",
    "chrono" : 1825,
    "code" : "neutralization",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 462,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307895920),
    "value" : "1"
});

/* 45 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697ff4"),
    "activityId" : "ACT-HAND",
    "chrono" : 1825,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 463,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307895921),
    "value" : "13"
});

/* 46 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697ff5"),
    "activityId" : "ACT-HAND",
    "chrono" : 1826,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 464,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307896770),
    "value" : "0"
});

/* 47 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697ff6"),
    "activityId" : "ACT-HAND",
    "chrono" : 1832,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 465,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307902079),
    "value" : "bottom-right"
});

/* 48 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697ff7"),
    "activityId" : "ACT-HAND",
    "chrono" : 1833,
    "code" : "shootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 466,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307903671),
    "value" : "RDOWN"
});

/* 49 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697ff8"),
    "activityId" : "ACT-HAND",
    "chrono" : 1834,
    "code" : "stopOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 467,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307904722),
    "value" : "1"
});

/* 50 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697ff9"),
    "activityId" : "ACT-HAND",
    "chrono" : 1834,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 468,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307904703),
    "value" : "8"
});

/* 1 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697ffa"),
    "activityId" : "ACT-HAND",
    "chrono" : 1842,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 469,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307912268),
    "value" : "top-left"
});

/* 2 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697ffb"),
    "activityId" : "ACT-HAND",
    "chrono" : 1843,
    "code" : "shootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 470,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307913378),
    "value" : "LUP"
});

/* 3 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697ffc"),
    "activityId" : "ACT-HAND",
    "chrono" : 1844,
    "code" : "goalScored",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 471,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307914378),
    "value" : "1"
});

/* 4 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697ffd"),
    "activityId" : "ACT-HAND",
    "chrono" : 1844,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 472,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307914378),
    "value" : "10"
});

/* 5 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697ffe"),
    "activityId" : "ACT-HAND",
    "chrono" : 1872,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 473,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307942582),
    "value" : "top-right"
});

/* 6 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51697fff"),
    "activityId" : "ACT-HAND",
    "chrono" : 1873,
    "code" : "shootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 474,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307943850),
    "value" : "RMIDDLE"
});

/* 7 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51698000"),
    "activityId" : "ACT-HAND",
    "chrono" : 1874,
    "code" : "stopOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 475,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307944769),
    "value" : "1"
});

/* 8 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51698001"),
    "activityId" : "ACT-HAND",
    "chrono" : 1874,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 476,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307944739),
    "value" : "30"
});

/* 9 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51698002"),
    "activityId" : "ACT-HAND",
    "chrono" : 1883,
    "code" : "looseball",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 477,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307953665),
    "value" : "1"
});

/* 10 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51698003"),
    "activityId" : "ACT-HAND",
    "chrono" : 1883,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 478,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307953665),
    "value" : "9"
});

/* 11 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51698004"),
    "activityId" : "ACT-HAND",
    "chrono" : 1904,
    "code" : "neutralization",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 479,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307974291),
    "value" : "1"
});

/* 12 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51698005"),
    "activityId" : "ACT-HAND",
    "chrono" : 1904,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 480,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307974290),
    "value" : "21"
});

/* 13 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51698006"),
    "activityId" : "ACT-HAND",
    "chrono" : 1914,
    "code" : "marcher",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 481,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307984158),
    "value" : "1"
});

/* 14 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51698007"),
    "activityId" : "ACT-HAND",
    "chrono" : 1914,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 482,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307984158),
    "value" : "10"
});

/* 15 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51698008"),
    "activityId" : "ACT-HAND",
    "chrono" : 1929,
    "code" : "neutralization",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 483,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307999414),
    "value" : "1"
});

/* 16 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51698009"),
    "activityId" : "ACT-HAND",
    "chrono" : 1929,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 484,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446307999415),
    "value" : "15"
});

/* 17 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c5169800a"),
    "activityId" : "ACT-HAND",
    "chrono" : 1931,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 485,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308001471),
    "value" : "0"
});

/* 18 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c5169800b"),
    "activityId" : "ACT-HAND",
    "chrono" : 1939,
    "code" : "interceptionOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 486,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "46bea3c9-a3c0-4f4e-91fc-0bd2797b48df"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308009755),
    "value" : "1"
});

/* 19 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c5169800c"),
    "activityId" : "ACT-HAND",
    "chrono" : 1939,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 487,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308009760),
    "value" : "8"
});

/* 20 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c5169800d"),
    "activityId" : "ACT-HAND",
    "chrono" : 1946,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 488,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "46bea3c9-a3c0-4f4e-91fc-0bd2797b48df"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308017127),
    "value" : "bottom-center"
});

/* 21 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c5169800e"),
    "activityId" : "ACT-HAND",
    "chrono" : 1948,
    "code" : "pole",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 489,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "46bea3c9-a3c0-4f4e-91fc-0bd2797b48df"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308018515),
    "value" : "top-pole"
});

/* 22 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c5169800f"),
    "activityId" : "ACT-HAND",
    "chrono" : 1954,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 490,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308024347),
    "value" : "15"
});

/* 23 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51698010"),
    "activityId" : "ACT-HAND",
    "chrono" : 1967,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 491,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308037798),
    "value" : "top-center"
});

/* 24 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51698011"),
    "activityId" : "ACT-HAND",
    "chrono" : 1968,
    "code" : "shootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 492,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308038885),
    "value" : "RDOWN"
});

/* 25 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51698012"),
    "activityId" : "ACT-HAND",
    "chrono" : 1969,
    "code" : "goalconceded",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 493,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308039851),
    "value" : "1"
});

/* 26 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51698013"),
    "activityId" : "ACT-HAND",
    "chrono" : 1969,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 494,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308039851),
    "value" : "15"
});

/* 27 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51698014"),
    "activityId" : "ACT-HAND",
    "chrono" : 1980,
    "code" : "substitue",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 495,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "46bea3c9-a3c0-4f4e-91fc-0bd2797b48df"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308050341),
    "value" : "1"
});

/* 28 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51698015"),
    "activityId" : "ACT-HAND",
    "chrono" : 1980,
    "code" : "playTime",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 496,
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308050341),
    "value" : "956"
});

/* 29 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51698016"),
    "activityId" : "ACT-HAND",
    "chrono" : 1983,
    "code" : "holder",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 497,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "46bea3c9-a3c0-4f4e-91fc-0bd2797b48df"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308053890),
    "value" : "1"
});

/* 30 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51698017"),
    "activityId" : "ACT-HAND",
    "chrono" : 1983,
    "code" : "positionType",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 498,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "46bea3c9-a3c0-4f4e-91fc-0bd2797b48df"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308053890),
    "value" : "pivot"
});

/* 31 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51698018"),
    "activityId" : "ACT-HAND",
    "chrono" : 1987,
    "code" : "shift",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 499,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308057624),
    "value" : "1"
});

/* 32 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51698019"),
    "activityId" : "ACT-HAND",
    "chrono" : 1988,
    "code" : "passOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 500,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308059012),
    "value" : "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"
});

/* 33 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c5169801a"),
    "activityId" : "ACT-HAND",
    "chrono" : 1990,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 501,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308060634),
    "value" : "corner-left"
});

/* 34 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c5169801b"),
    "activityId" : "ACT-HAND",
    "chrono" : 1991,
    "code" : "shootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 502,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308061619),
    "value" : "LMIDDLE"
});

/* 35 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c5169801c"),
    "activityId" : "ACT-HAND",
    "chrono" : 1992,
    "code" : "goalScored",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 503,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308062538),
    "value" : "1"
});

/* 36 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c5169801d"),
    "activityId" : "ACT-HAND",
    "chrono" : 1992,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 504,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308062538),
    "value" : "23"
});

/* 37 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c5169801e"),
    "activityId" : "ACT-HAND",
    "chrono" : 2003,
    "code" : "interceptionOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 505,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308073408),
    "value" : "1"
});

/* 38 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c5169801f"),
    "activityId" : "ACT-HAND",
    "chrono" : 2003,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 506,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308073409),
    "value" : "11"
});

/* 39 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51698020"),
    "activityId" : "ACT-HAND",
    "chrono" : 2011,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 507,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308081895),
    "value" : "bottom-left"
});

/* 40 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51698021"),
    "activityId" : "ACT-HAND",
    "chrono" : 2012,
    "code" : "pole",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 508,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308083247),
    "value" : "left-pole"
});

/* 41 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51698022"),
    "activityId" : "ACT-HAND",
    "chrono" : 2021,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 509,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308091831),
    "value" : "18"
});

/* 42 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51698023"),
    "activityId" : "ACT-HAND",
    "chrono" : 2057,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 510,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308128998),
    "value" : "36"
});

/* 43 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffb9e4b09c0c51698024"),
    "activityId" : "ACT-HAND",
    "chrono" : 2059,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 511,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308131509),
    "value" : "bottom-center"
});

/* 44 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698025"),
    "activityId" : "ACT-HAND",
    "chrono" : 2060,
    "code" : "shootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 512,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308132500),
    "value" : "LMIDDLE"
});

/* 45 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698026"),
    "activityId" : "ACT-HAND",
    "chrono" : 2062,
    "code" : "goalScored",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 513,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308133561),
    "value" : "1"
});

/* 46 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698027"),
    "activityId" : "ACT-HAND",
    "chrono" : 2062,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 514,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308133564),
    "value" : "5"
});

/* 47 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698028"),
    "activityId" : "ACT-HAND",
    "chrono" : 2073,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 515,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308145471),
    "value" : "top-center"
});

/* 48 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698029"),
    "activityId" : "ACT-HAND",
    "chrono" : 2075,
    "code" : "outside",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 516,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308146695),
    "value" : "outside-right"
});

/* 49 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169802a"),
    "activityId" : "ACT-HAND",
    "chrono" : 2075,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 517,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308146694),
    "value" : "13"
});

/* 50 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169802b"),
    "activityId" : "ACT-HAND",
    "chrono" : 2099,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 518,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308170868),
    "value" : "top-center"
});

/* 1 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169802c"),
    "activityId" : "ACT-HAND",
    "chrono" : 2100,
    "code" : "shootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 519,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308171927),
    "value" : "LMIDDLE"
});

/* 2 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169802d"),
    "activityId" : "ACT-HAND",
    "chrono" : 2101,
    "code" : "goalScored",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 520,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308172817),
    "value" : "1"
});

/* 3 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169802e"),
    "activityId" : "ACT-HAND",
    "chrono" : 2101,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 521,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308172817),
    "value" : "26"
});

/* 4 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169802f"),
    "activityId" : "ACT-HAND",
    "chrono" : 2109,
    "code" : "forceDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 522,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308181156),
    "value" : "1"
});

/* 5 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698030"),
    "activityId" : "ACT-HAND",
    "chrono" : 2109,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 523,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308181155),
    "value" : "8"
});

/* 6 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698031"),
    "activityId" : "ACT-HAND",
    "chrono" : 2118,
    "code" : "substitue",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 524,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308189613),
    "value" : "1"
});

/* 7 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698032"),
    "activityId" : "ACT-HAND",
    "chrono" : 2118,
    "code" : "playTime",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 525,
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308189613),
    "value" : "617"
});

/* 8 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698033"),
    "activityId" : "ACT-HAND",
    "chrono" : 2120,
    "code" : "holder",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 526,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308191825),
    "value" : "1"
});

/* 9 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698034"),
    "activityId" : "ACT-HAND",
    "chrono" : 2120,
    "code" : "positionType",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 527,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308191826),
    "value" : "left-backcourt"
});

/* 10 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698035"),
    "activityId" : "ACT-HAND",
    "chrono" : 2130,
    "code" : "substitue",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 528,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308201924),
    "value" : "1"
});

/* 11 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698036"),
    "activityId" : "ACT-HAND",
    "chrono" : 2130,
    "code" : "playTime",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 529,
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308201924),
    "value" : "629"
});

/* 12 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698037"),
    "activityId" : "ACT-HAND",
    "chrono" : 2134,
    "code" : "holder",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 530,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308206100),
    "value" : "1"
});

/* 13 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698038"),
    "activityId" : "ACT-HAND",
    "chrono" : 2134,
    "code" : "positionType",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 531,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308206100),
    "value" : "left-wingman"
});

/* 14 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698039"),
    "activityId" : "ACT-HAND",
    "chrono" : 2137,
    "code" : "looseball",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 532,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308209533),
    "value" : "1"
});

/* 15 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169803a"),
    "activityId" : "ACT-HAND",
    "chrono" : 2137,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 533,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308209534),
    "value" : "28"
});

/* 16 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169803b"),
    "activityId" : "ACT-HAND",
    "chrono" : 2140,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 534,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308211779),
    "value" : "top-left"
});

/* 17 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169803c"),
    "activityId" : "ACT-HAND",
    "chrono" : 2141,
    "code" : "shootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 535,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308212880),
    "value" : "RDOWN"
});

/* 18 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169803d"),
    "activityId" : "ACT-HAND",
    "chrono" : 2142,
    "code" : "goalconceded",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 536,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308213977),
    "value" : "1"
});

/* 19 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169803e"),
    "activityId" : "ACT-HAND",
    "chrono" : 2142,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 537,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308213979),
    "value" : "4"
});

/* 20 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169803f"),
    "activityId" : "ACT-HAND",
    "chrono" : 2151,
    "code" : "passDec",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 538,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308222981),
    "value" : "1"
});

/* 21 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698040"),
    "activityId" : "ACT-HAND",
    "chrono" : 2153,
    "code" : "passOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 539,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308224787),
    "value" : "ce18d73e-dedf-43e5-8e75-16e0375be349"
});

/* 22 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698041"),
    "activityId" : "ACT-HAND",
    "chrono" : 2154,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 540,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308226365),
    "value" : "corner-left"
});

/* 23 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698042"),
    "activityId" : "ACT-HAND",
    "chrono" : 2155,
    "code" : "shootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 541,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308227423),
    "value" : "RUP"
});

/* 24 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698043"),
    "activityId" : "ACT-HAND",
    "chrono" : 2156,
    "code" : "goalScored",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 542,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308228422),
    "value" : "1"
});

/* 25 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698044"),
    "activityId" : "ACT-HAND",
    "chrono" : 2156,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 543,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308228422),
    "value" : "14"
});

/* 26 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698045"),
    "activityId" : "ACT-HAND",
    "chrono" : 2174,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 544,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308246105),
    "value" : "top-right"
});

/* 27 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698046"),
    "activityId" : "ACT-HAND",
    "chrono" : 2175,
    "code" : "shootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 545,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308247341),
    "value" : "LDOWN"
});

/* 28 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698047"),
    "activityId" : "ACT-HAND",
    "chrono" : 2176,
    "code" : "goalconceded",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 546,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308248483),
    "value" : "1"
});

/* 29 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698048"),
    "activityId" : "ACT-HAND",
    "chrono" : 2176,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 547,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308248485),
    "value" : "20"
});

/* 30 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698049"),
    "activityId" : "ACT-HAND",
    "chrono" : 2194,
    "code" : "shift",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 548,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308266391),
    "value" : "1"
});

/* 31 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169804a"),
    "activityId" : "ACT-HAND",
    "chrono" : 2196,
    "code" : "passOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 549,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308267997),
    "value" : "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"
});

/* 32 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169804b"),
    "activityId" : "ACT-HAND",
    "chrono" : 2198,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 550,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308269860),
    "value" : "top-center"
});

/* 33 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169804c"),
    "activityId" : "ACT-HAND",
    "chrono" : 2199,
    "code" : "outside",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 551,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308271353),
    "value" : "outside-top"
});

/* 34 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169804d"),
    "activityId" : "ACT-HAND",
    "chrono" : 2199,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 552,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308271353),
    "value" : "23"
});

/* 35 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169804e"),
    "activityId" : "ACT-HAND",
    "chrono" : 2212,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 553,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308284142),
    "value" : "top-center"
});

/* 36 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169804f"),
    "activityId" : "ACT-HAND",
    "chrono" : 2213,
    "code" : "shootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 554,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308285270),
    "value" : "RUP"
});

/* 37 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698050"),
    "activityId" : "ACT-HAND",
    "chrono" : 2215,
    "code" : "goalconceded",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 555,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308286792),
    "value" : "1"
});

/* 38 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698051"),
    "activityId" : "ACT-HAND",
    "chrono" : 2215,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 556,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308286792),
    "value" : "16"
});

/* 39 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698052"),
    "activityId" : "ACT-HAND",
    "chrono" : 2238,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 557,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308309885),
    "value" : "top-center"
});

/* 40 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698053"),
    "activityId" : "ACT-HAND",
    "chrono" : 2239,
    "code" : "shootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 558,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308310890),
    "value" : "RMIDDLE"
});

/* 41 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698054"),
    "activityId" : "ACT-HAND",
    "chrono" : 2240,
    "code" : "stopOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 559,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308311947),
    "value" : "1"
});

/* 42 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698055"),
    "activityId" : "ACT-HAND",
    "chrono" : 2240,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 560,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308311912),
    "value" : "0"
});

/* 43 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698056"),
    "activityId" : "ACT-HAND",
    "chrono" : 2275,
    "code" : "neutralization",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 561,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308348571),
    "value" : "1"
});

/* 44 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698057"),
    "activityId" : "ACT-HAND",
    "chrono" : 2275,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 562,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308348571),
    "value" : "35"
});

/* 45 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698058"),
    "activityId" : "ACT-HAND",
    "chrono" : 2290,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 563,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308363869),
    "value" : "0"
});

/* 46 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698059"),
    "activityId" : "ACT-HAND",
    "chrono" : 2294,
    "code" : "forceDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 564,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308367429),
    "value" : "1"
});

/* 47 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169805a"),
    "activityId" : "ACT-HAND",
    "chrono" : 2294,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 565,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308367429),
    "value" : "3"
});

/* 48 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169805b"),
    "activityId" : "ACT-HAND",
    "chrono" : 2311,
    "code" : "shift",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 566,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308384498),
    "value" : "1"
});

/* 49 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169805c"),
    "activityId" : "ACT-HAND",
    "chrono" : 2312,
    "code" : "passOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 567,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308385709),
    "value" : "ce18d73e-dedf-43e5-8e75-16e0375be349"
});

/* 50 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169805d"),
    "activityId" : "ACT-HAND",
    "chrono" : 2314,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 568,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308387894),
    "value" : "corner-left"
});

/* 1 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169805e"),
    "activityId" : "ACT-HAND",
    "chrono" : 2315,
    "code" : "shootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 569,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308388939),
    "value" : "LUP"
});

/* 2 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169805f"),
    "activityId" : "ACT-HAND",
    "chrono" : 2318,
    "code" : "goalScored",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 570,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308391423),
    "value" : "1"
});

/* 3 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698060"),
    "activityId" : "ACT-HAND",
    "chrono" : 2318,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 571,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308391422),
    "value" : "24"
});

/* 4 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698061"),
    "activityId" : "ACT-HAND",
    "chrono" : 2331,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 572,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308404869),
    "value" : "corner-right"
});

/* 5 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698062"),
    "activityId" : "ACT-HAND",
    "chrono" : 2333,
    "code" : "shootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 573,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308406201),
    "value" : "RUP"
});

/* 6 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698063"),
    "activityId" : "ACT-HAND",
    "chrono" : 2334,
    "code" : "stopOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 574,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308407280),
    "value" : "1"
});

/* 7 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698064"),
    "activityId" : "ACT-HAND",
    "chrono" : 2334,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 575,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308407254),
    "value" : "0"
});

/* 8 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698065"),
    "activityId" : "ACT-HAND",
    "chrono" : 2340,
    "code" : "substitue",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 576,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308413980),
    "value" : "1"
});

/* 9 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698066"),
    "activityId" : "ACT-HAND",
    "chrono" : 2340,
    "code" : "playTime",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 577,
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308413980),
    "value" : "1439"
});

/* 10 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698067"),
    "activityId" : "ACT-HAND",
    "chrono" : 2343,
    "code" : "holder",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 578,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308416687),
    "value" : "1"
});

/* 11 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698068"),
    "activityId" : "ACT-HAND",
    "chrono" : 2343,
    "code" : "positionType",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 579,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308416688),
    "value" : "center-backcourt"
});

/* 12 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698069"),
    "activityId" : "ACT-HAND",
    "chrono" : 2356,
    "code" : "penaltyObtained",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 580,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308429128),
    "value" : "1"
});

/* 13 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169806a"),
    "activityId" : "ACT-HAND",
    "chrono" : 2358,
    "code" : "passOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 581,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308431288),
    "value" : "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"
});

/* 14 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169806b"),
    "activityId" : "ACT-HAND",
    "chrono" : 2359,
    "code" : "exclusionTempoObtained",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 582,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308432858),
    "value" : "1"
});

/* 15 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169806c"),
    "activityId" : "ACT-HAND",
    "chrono" : 2374,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 583,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308447169),
    "value" : "top-center"
});

/* 16 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169806d"),
    "activityId" : "ACT-HAND",
    "chrono" : 2375,
    "code" : "shootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 584,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308449119),
    "value" : "CDOWN"
});

/* 17 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169806e"),
    "activityId" : "ACT-HAND",
    "chrono" : 2377,
    "code" : "stopOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 585,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308450277),
    "value" : "1"
});

/* 18 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169806f"),
    "activityId" : "ACT-HAND",
    "chrono" : 2377,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 586,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308450231),
    "value" : "43"
});

/* 19 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698070"),
    "activityId" : "ACT-HAND",
    "chrono" : 2377,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 587,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308450306),
    "value" : "0"
});

/* 20 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698071"),
    "activityId" : "ACT-HAND",
    "chrono" : 2402,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 588,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308475387),
    "value" : "bottom-right"
});

/* 21 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698072"),
    "activityId" : "ACT-HAND",
    "chrono" : 2403,
    "code" : "outside",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 589,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308476599),
    "value" : "outside-right"
});

/* 22 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698073"),
    "activityId" : "ACT-HAND",
    "chrono" : 2403,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 590,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308476599),
    "value" : "26"
});

/* 23 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698074"),
    "activityId" : "ACT-HAND",
    "chrono" : 2411,
    "code" : "looseball",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 591,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308485159),
    "value" : "1"
});

/* 24 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698075"),
    "activityId" : "ACT-HAND",
    "chrono" : 2411,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 592,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308485160),
    "value" : "8"
});

/* 25 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698076"),
    "activityId" : "ACT-HAND",
    "chrono" : 2431,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 593,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308504626),
    "value" : "bottom-right"
});

/* 26 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698077"),
    "activityId" : "ACT-HAND",
    "chrono" : 2432,
    "code" : "shootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 594,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308505713),
    "value" : "RDOWN"
});

/* 27 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698078"),
    "activityId" : "ACT-HAND",
    "chrono" : 2433,
    "code" : "stopOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 595,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308506548),
    "value" : "1"
});

/* 28 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698079"),
    "activityId" : "ACT-HAND",
    "chrono" : 2433,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 596,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308506522),
    "value" : "0"
});

/* 29 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169807a"),
    "activityId" : "ACT-HAND",
    "chrono" : 2442,
    "code" : "looseball",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 597,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308515644),
    "value" : "1"
});

/* 30 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169807b"),
    "activityId" : "ACT-HAND",
    "chrono" : 2442,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 598,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308515644),
    "value" : "9"
});

/* 31 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169807c"),
    "activityId" : "ACT-HAND",
    "chrono" : 2459,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 599,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308532874),
    "value" : "top-right"
});

/* 32 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169807d"),
    "activityId" : "ACT-HAND",
    "chrono" : 2462,
    "code" : "shootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 600,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308536156),
    "value" : "CDOWN"
});

/* 33 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169807e"),
    "activityId" : "ACT-HAND",
    "chrono" : 2463,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 602,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308537277),
    "value" : "0"
});

/* 34 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169807f"),
    "activityId" : "ACT-HAND",
    "chrono" : 2464,
    "code" : "stopOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 601,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308537308),
    "value" : "1"
});

/* 35 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698080"),
    "activityId" : "ACT-HAND",
    "chrono" : 2466,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 603,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308539679),
    "value" : "2"
});

/* 36 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698081"),
    "activityId" : "ACT-HAND",
    "chrono" : 2468,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 604,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308541584),
    "value" : "top-left"
});

/* 37 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698082"),
    "activityId" : "ACT-HAND",
    "chrono" : 2469,
    "code" : "shootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 605,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308542571),
    "value" : "LDOWN"
});

/* 38 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698083"),
    "activityId" : "ACT-HAND",
    "chrono" : 2470,
    "code" : "stopOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 606,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308543431),
    "value" : "1"
});

/* 39 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698084"),
    "activityId" : "ACT-HAND",
    "chrono" : 2470,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 607,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308543397),
    "value" : "0"
});

/* 40 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698085"),
    "activityId" : "ACT-HAND",
    "chrono" : 2471,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 608,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308544446),
    "value" : "0"
});

/* 41 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698086"),
    "activityId" : "ACT-HAND",
    "chrono" : 2473,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 609,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308546829),
    "value" : "corner-left"
});

/* 42 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698087"),
    "activityId" : "ACT-HAND",
    "chrono" : 2477,
    "code" : "shootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 610,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308550911),
    "value" : "LUP"
});

/* 43 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698088"),
    "activityId" : "ACT-HAND",
    "chrono" : 2478,
    "code" : "stopOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 611,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308552134),
    "value" : "1"
});

/* 44 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698089"),
    "activityId" : "ACT-HAND",
    "chrono" : 2478,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 612,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308552075),
    "value" : "0"
});

/* 45 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169808a"),
    "activityId" : "ACT-HAND",
    "chrono" : 2482,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 613,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308555953),
    "value" : "top-center"
});

/* 46 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169808b"),
    "activityId" : "ACT-HAND",
    "chrono" : 2483,
    "code" : "shootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 614,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308556967),
    "value" : "LUP"
});

/* 47 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169808c"),
    "activityId" : "ACT-HAND",
    "chrono" : 2484,
    "code" : "goalScored",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 615,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308557853),
    "value" : "1"
});

/* 48 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169808d"),
    "activityId" : "ACT-HAND",
    "chrono" : 2484,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 616,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308557853),
    "value" : "5"
});

/* 49 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169808e"),
    "activityId" : "ACT-HAND",
    "chrono" : 2511,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 617,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308584606),
    "value" : "top-left"
});

/* 50 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169808f"),
    "activityId" : "ACT-HAND",
    "chrono" : 2512,
    "code" : "shootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 618,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308585737),
    "value" : "RUP"
});

/* 1 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698090"),
    "activityId" : "ACT-HAND",
    "chrono" : 2513,
    "code" : "stopOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 619,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308586679),
    "value" : "1"
});

/* 2 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698091"),
    "activityId" : "ACT-HAND",
    "chrono" : 2513,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 620,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308586645),
    "value" : "29"
});

/* 3 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698092"),
    "activityId" : "ACT-HAND",
    "chrono" : 2514,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 621,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308588161),
    "value" : "0"
});

/* 4 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698093"),
    "activityId" : "ACT-HAND",
    "chrono" : 2516,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 622,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308589752),
    "value" : "top-left"
});

/* 5 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698094"),
    "activityId" : "ACT-HAND",
    "chrono" : 2517,
    "code" : "shootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 623,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308590787),
    "value" : "CUP"
});

/* 6 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698095"),
    "activityId" : "ACT-HAND",
    "chrono" : 2518,
    "code" : "goalconceded",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 624,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308591692),
    "value" : "1"
});

/* 7 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698096"),
    "activityId" : "ACT-HAND",
    "chrono" : 2518,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 625,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308591692),
    "value" : "4"
});

/* 8 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698097"),
    "activityId" : "ACT-HAND",
    "chrono" : 2539,
    "code" : "substitue",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 626,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308613306),
    "value" : "1"
});

/* 9 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698098"),
    "activityId" : "ACT-HAND",
    "chrono" : 2539,
    "code" : "playTime",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 627,
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308613307),
    "value" : "196"
});

/* 10 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698099"),
    "activityId" : "ACT-HAND",
    "chrono" : 2541,
    "code" : "holder",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 628,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308615225),
    "value" : "1"
});

/* 11 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169809a"),
    "activityId" : "ACT-HAND",
    "chrono" : 2541,
    "code" : "positionType",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 629,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308615225),
    "value" : "center-backcourt"
});

/* 12 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169809b"),
    "activityId" : "ACT-HAND",
    "chrono" : 2546,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 630,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308620031),
    "value" : "top-right"
});

/* 13 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169809c"),
    "activityId" : "ACT-HAND",
    "chrono" : 2547,
    "code" : "shootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 631,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308621095),
    "value" : "LDOWN"
});

/* 14 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169809d"),
    "activityId" : "ACT-HAND",
    "chrono" : 2548,
    "code" : "goalScored",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 632,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308622159),
    "value" : "1"
});

/* 15 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169809e"),
    "activityId" : "ACT-HAND",
    "chrono" : 2548,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 633,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308622159),
    "value" : "30"
});

/* 16 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c5169809f"),
    "activityId" : "ACT-HAND",
    "chrono" : 2562,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 634,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308636347),
    "value" : "0"
});

/* 17 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980a0"),
    "activityId" : "ACT-HAND",
    "chrono" : 2565,
    "code" : "shift",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 635,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308639123),
    "value" : "1"
});

/* 18 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980a1"),
    "activityId" : "ACT-HAND",
    "chrono" : 2567,
    "code" : "passOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 636,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308640650),
    "value" : "ce18d73e-dedf-43e5-8e75-16e0375be349"
});

/* 19 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980a2"),
    "activityId" : "ACT-HAND",
    "chrono" : 2568,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 637,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308641975),
    "value" : "corner-left"
});

/* 20 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980a3"),
    "activityId" : "ACT-HAND",
    "chrono" : 2570,
    "code" : "shootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 638,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308643518),
    "value" : "RUP"
});

/* 21 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980a4"),
    "activityId" : "ACT-HAND",
    "chrono" : 2571,
    "code" : "goalScored",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 639,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308644469),
    "value" : "1"
});

/* 22 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980a5"),
    "activityId" : "ACT-HAND",
    "chrono" : 2571,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 640,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308644469),
    "value" : "8"
});

/* 23 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980a6"),
    "activityId" : "ACT-HAND",
    "chrono" : 2580,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 641,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308653684),
    "value" : "bottom-right"
});

/* 24 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980a7"),
    "activityId" : "ACT-HAND",
    "chrono" : 2581,
    "code" : "pole",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 642,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308654849),
    "value" : "top-pole"
});

/* 25 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980a8"),
    "activityId" : "ACT-HAND",
    "chrono" : 2595,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 643,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308668629),
    "value" : "0"
});

/* 26 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980a9"),
    "activityId" : "ACT-HAND",
    "chrono" : 2598,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 644,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308672267),
    "value" : "top-left"
});

/* 27 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980aa"),
    "activityId" : "ACT-HAND",
    "chrono" : 2600,
    "code" : "outside",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 645,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308673509),
    "value" : "outside-right"
});

/* 28 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980ab"),
    "activityId" : "ACT-HAND",
    "chrono" : 2600,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 646,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308673509),
    "value" : "5"
});

/* 29 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980ac"),
    "activityId" : "ACT-HAND",
    "chrono" : 2630,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 647,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308703800),
    "value" : "bottom-center"
});

/* 30 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980ad"),
    "activityId" : "ACT-HAND",
    "chrono" : 2631,
    "code" : "outside",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 648,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308705024),
    "value" : "outside-top"
});

/* 31 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980ae"),
    "activityId" : "ACT-HAND",
    "chrono" : 2631,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 649,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308705024),
    "value" : "31"
});

/* 32 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980af"),
    "activityId" : "ACT-HAND",
    "chrono" : 2632,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 650,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308706447),
    "value" : "1"
});

/* 33 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980b0"),
    "activityId" : "ACT-HAND",
    "chrono" : 2635,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 651,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308708752),
    "value" : "top-center"
});

/* 34 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980b1"),
    "activityId" : "ACT-HAND",
    "chrono" : 2636,
    "code" : "shootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 652,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308709775),
    "value" : "RDOWN"
});

/* 35 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980b2"),
    "activityId" : "ACT-HAND",
    "chrono" : 2637,
    "code" : "goalconceded",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 653,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308710865),
    "value" : "1"
});

/* 36 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980b3"),
    "activityId" : "ACT-HAND",
    "chrono" : 2637,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 654,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308710865),
    "value" : "4"
});

/* 37 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980b4"),
    "activityId" : "ACT-HAND",
    "chrono" : 2669,
    "code" : "substitue",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 655,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308743477),
    "value" : "1"
});

/* 38 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980b5"),
    "activityId" : "ACT-HAND",
    "chrono" : 2669,
    "code" : "playTime",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 656,
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308743477),
    "value" : "1168"
});

/* 39 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980b6"),
    "activityId" : "ACT-HAND",
    "chrono" : 2672,
    "code" : "holder",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 657,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308745655),
    "value" : "1"
});

/* 40 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980b7"),
    "activityId" : "ACT-HAND",
    "chrono" : 2672,
    "code" : "positionType",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 658,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308745655),
    "value" : "right-backcourt"
});

/* 41 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980b8"),
    "activityId" : "ACT-HAND",
    "chrono" : 2674,
    "code" : "holder",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 659,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308748177),
    "value" : "1"
});

/* 42 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980b9"),
    "activityId" : "ACT-HAND",
    "chrono" : 2674,
    "code" : "positionType",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 660,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308748177),
    "value" : "left-backcourt"
});

/* 43 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980ba"),
    "activityId" : "ACT-HAND",
    "chrono" : 2677,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 661,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308750807),
    "value" : "bottom-left"
});

/* 44 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980bb"),
    "activityId" : "ACT-HAND",
    "chrono" : 2678,
    "code" : "outside",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 662,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308751842),
    "value" : "outside-left"
});

/* 45 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980bc"),
    "activityId" : "ACT-HAND",
    "chrono" : 2678,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 663,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308751841),
    "value" : "41"
});

/* 46 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980bd"),
    "activityId" : "ACT-HAND",
    "chrono" : 2688,
    "code" : "forceDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 664,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308761929),
    "value" : "1"
});

/* 47 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980be"),
    "activityId" : "ACT-HAND",
    "chrono" : 2688,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 665,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308761929),
    "value" : "10"
});

/* 48 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980bf"),
    "activityId" : "ACT-HAND",
    "chrono" : 2704,
    "code" : "substitue",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 666,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308778521),
    "value" : "1"
});

/* 49 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980c0"),
    "activityId" : "ACT-HAND",
    "chrono" : 2704,
    "code" : "playTime",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 667,
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308778521),
    "value" : "721"
});

/* 50 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980c1"),
    "activityId" : "ACT-HAND",
    "chrono" : 2707,
    "code" : "holder",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 668,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308781151),
    "value" : "1"
});

/* 1 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980c2"),
    "activityId" : "ACT-HAND",
    "chrono" : 2707,
    "code" : "positionType",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 669,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308781151),
    "value" : "pivot"
});

/* 2 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980c3"),
    "activityId" : "ACT-HAND",
    "chrono" : 2709,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 670,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "46bea3c9-a3c0-4f4e-91fc-0bd2797b48df"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308783291),
    "value" : "top-center"
});

/* 3 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980c4"),
    "activityId" : "ACT-HAND",
    "chrono" : 2710,
    "code" : "outside",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 671,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "46bea3c9-a3c0-4f4e-91fc-0bd2797b48df"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308784393),
    "value" : "outside-right"
});

/* 4 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980c5"),
    "activityId" : "ACT-HAND",
    "chrono" : 2710,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 672,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308784394),
    "value" : "22"
});

/* 5 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980c6"),
    "activityId" : "ACT-HAND",
    "chrono" : 2724,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 673,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308798040),
    "value" : "top-left"
});

/* 6 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980c7"),
    "activityId" : "ACT-HAND",
    "chrono" : 2726,
    "code" : "shootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 674,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308800192),
    "value" : "LDOWN"
});

/* 7 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980c8"),
    "activityId" : "ACT-HAND",
    "chrono" : 2727,
    "code" : "goalconceded",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 675,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308801121),
    "value" : "1"
});

/* 8 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980c9"),
    "activityId" : "ACT-HAND",
    "chrono" : 2727,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 676,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308801120),
    "value" : "17"
});

/* 9 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980ca"),
    "activityId" : "ACT-HAND",
    "chrono" : 2737,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 677,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308810732),
    "value" : "top-left"
});

/* 10 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980cb"),
    "activityId" : "ACT-HAND",
    "chrono" : 2738,
    "code" : "shootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 678,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308812060),
    "value" : "LDOWN"
});

/* 11 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980cc"),
    "activityId" : "ACT-HAND",
    "chrono" : 2739,
    "code" : "goalScored",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 679,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308812878),
    "value" : "1"
});

/* 12 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980cd"),
    "activityId" : "ACT-HAND",
    "chrono" : 2739,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 680,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308812877),
    "value" : "12"
});

/* 13 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980ce"),
    "activityId" : "ACT-HAND",
    "chrono" : 2745,
    "code" : "duelLoose",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 681,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308819379),
    "value" : "1"
});

/* 14 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980cf"),
    "activityId" : "ACT-HAND",
    "chrono" : 2745,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 682,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308819380),
    "value" : "6"
});

/* 15 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980d0"),
    "activityId" : "ACT-HAND",
    "chrono" : 2755,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 683,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308828697),
    "value" : "bottom-center"
});

/* 16 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980d1"),
    "activityId" : "ACT-HAND",
    "chrono" : 2756,
    "code" : "outside",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 684,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308829795),
    "value" : "outside-right"
});

/* 17 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980d2"),
    "activityId" : "ACT-HAND",
    "chrono" : 2756,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 685,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308829794),
    "value" : "11"
});

/* 18 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980d3"),
    "activityId" : "ACT-HAND",
    "chrono" : 2761,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 686,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308835170),
    "value" : "top-left"
});

/* 19 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980d4"),
    "activityId" : "ACT-HAND",
    "chrono" : 2762,
    "code" : "shootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 687,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308836146),
    "value" : "LUP"
});

/* 20 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980d5"),
    "activityId" : "ACT-HAND",
    "chrono" : 2763,
    "code" : "goalScored",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 688,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308836942),
    "value" : "1"
});

/* 21 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980d6"),
    "activityId" : "ACT-HAND",
    "chrono" : 2763,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 689,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308836942),
    "value" : "7"
});

/* 22 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980d7"),
    "activityId" : "ACT-HAND",
    "chrono" : 2789,
    "code" : "forceDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 690,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308863661),
    "value" : "1"
});

/* 23 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980d8"),
    "activityId" : "ACT-HAND",
    "chrono" : 2789,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 691,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308863662),
    "value" : "26"
});

/* 24 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980d9"),
    "activityId" : "ACT-HAND",
    "chrono" : 2802,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 692,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308876108),
    "value" : "0"
});

/* 25 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980da"),
    "activityId" : "ACT-HAND",
    "chrono" : 2805,
    "code" : "exclusionTempoObtained",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 693,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308879162),
    "value" : "1"
});

/* 26 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980db"),
    "activityId" : "ACT-HAND",
    "chrono" : 2805,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 694,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308879164),
    "value" : "3"
});

/* 27 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980dc"),
    "activityId" : "ACT-HAND",
    "chrono" : 2841,
    "code" : "neutralization",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 695,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "1ce4591d-74a8-46e9-af80-d633f9344d27"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308915039),
    "value" : "1"
});

/* 28 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980dd"),
    "activityId" : "ACT-HAND",
    "chrono" : 2841,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 696,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308915038),
    "value" : "36"
});

/* 29 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980de"),
    "activityId" : "ACT-HAND",
    "chrono" : 2847,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 697,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308921154),
    "value" : "0"
});

/* 30 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980df"),
    "activityId" : "ACT-HAND",
    "chrono" : 2849,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 698,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308923482),
    "value" : "bottom-left"
});

/* 31 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980e0"),
    "activityId" : "ACT-HAND",
    "chrono" : 2850,
    "code" : "shootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 699,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308924461),
    "value" : "RDOWN"
});

/* 32 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980e1"),
    "activityId" : "ACT-HAND",
    "chrono" : 2851,
    "code" : "stopOk",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 700,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308925304),
    "value" : "1"
});

/* 33 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980e2"),
    "activityId" : "ACT-HAND",
    "chrono" : 2851,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 701,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308925281),
    "value" : "4"
});

/* 34 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980e3"),
    "activityId" : "ACT-HAND",
    "chrono" : 2869,
    "code" : "penaltyObtained",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 702,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "1ce4591d-74a8-46e9-af80-d633f9344d27"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308943111),
    "value" : "1"
});

/* 35 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980e4"),
    "activityId" : "ACT-HAND",
    "chrono" : 2881,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 703,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308958911),
    "value" : "9"
});

/* 36 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980e5"),
    "activityId" : "ACT-HAND",
    "chrono" : 2881,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 704,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308959705),
    "value" : "0"
});

/* 37 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980e6"),
    "activityId" : "ACT-HAND",
    "chrono" : 2884,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 705,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308962392),
    "value" : "top-center"
});

/* 38 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980e7"),
    "activityId" : "ACT-HAND",
    "chrono" : 2885,
    "code" : "pole",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 706,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308963632),
    "value" : "top-pole"
});

/* 39 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980e8"),
    "activityId" : "ACT-HAND",
    "chrono" : 2904,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 707,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308982732),
    "value" : "top-center"
});

/* 40 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980e9"),
    "activityId" : "ACT-HAND",
    "chrono" : 2906,
    "code" : "shootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 708,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308984112),
    "value" : "LMIDDLE"
});

/* 41 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980ea"),
    "activityId" : "ACT-HAND",
    "chrono" : 2907,
    "code" : "goalScored",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 709,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308985659),
    "value" : "1"
});

/* 42 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980eb"),
    "activityId" : "ACT-HAND",
    "chrono" : 2907,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 710,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308985659),
    "value" : "25"
});

/* 43 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980ec"),
    "activityId" : "ACT-HAND",
    "chrono" : 2913,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 711,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308991435),
    "value" : "top-center"
});

/* 44 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980ed"),
    "activityId" : "ACT-HAND",
    "chrono" : 2914,
    "code" : "shootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 712,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308992504),
    "value" : "LMIDDLE"
});

/* 45 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980ee"),
    "activityId" : "ACT-HAND",
    "chrono" : 2917,
    "code" : "goalconceded",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 713,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308995522),
    "value" : "1"
});

/* 46 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980ef"),
    "activityId" : "ACT-HAND",
    "chrono" : 2917,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 714,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446308995522),
    "value" : "9"
});

/* 47 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980f0"),
    "activityId" : "ACT-HAND",
    "chrono" : 2922,
    "code" : "originShootAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 715,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446309000743),
    "value" : "corner-left"
});

/* 48 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980f1"),
    "activityId" : "ACT-HAND",
    "chrono" : 2924,
    "code" : "outside",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 716,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446309001954),
    "value" : "outside-right"
});

/* 49 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980f2"),
    "activityId" : "ACT-HAND",
    "chrono" : 2924,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 717,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446309001957),
    "value" : "7"
});

/* 50 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980f3"),
    "activityId" : "ACT-HAND",
    "chrono" : 2944,
    "code" : "forceDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 718,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446309022130),
    "value" : "1"
});

/* 1 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980f4"),
    "activityId" : "ACT-HAND",
    "chrono" : 2944,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 719,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446309022131),
    "value" : "20"
});

/* 2 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980f5"),
    "activityId" : "ACT-HAND",
    "chrono" : 2958,
    "code" : "forceAtt",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 720,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446309035878),
    "value" : "1"
});

/* 3 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980f6"),
    "activityId" : "ACT-HAND",
    "chrono" : 2958,
    "code" : "timeAttack",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 721,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446309035875),
    "value" : "14"
});

/* 4 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980f7"),
    "activityId" : "ACT-HAND",
    "chrono" : 2966,
    "code" : "contre",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 722,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "46bea3c9-a3c0-4f4e-91fc-0bd2797b48df"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446309044155),
    "value" : "1"
});

/* 5 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980f8"),
    "activityId" : "ACT-HAND",
    "chrono" : 2966,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 723,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446309044156),
    "value" : "8"
});

/* 6 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980f9"),
    "activityId" : "ACT-HAND",
    "chrono" : 2987,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 728,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446309065063),
    "value" : "6"
});

/* 7 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980fa"),
    "activityId" : "ACT-HAND",
    "chrono" : 2987,
    "code" : "goalconceded",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 727,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446309065063),
    "value" : "1"
});

/* 8 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980fb"),
    "activityId" : "ACT-HAND",
    "chrono" : 2985,
    "code" : "shootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 726,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446309062915),
    "value" : "CUP"
});

/* 9 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980fc"),
    "activityId" : "ACT-HAND",
    "chrono" : 2983,
    "code" : "originShootDef",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 725,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446309061872),
    "value" : "top-left"
});

/* 10 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980fd"),
    "activityId" : "ACT-HAND",
    "chrono" : 2981,
    "code" : "timeDefense",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 724,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446309059518),
    "value" : "0"
});

/* 11 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980fe"),
    "activityId" : "ACT-HAND",
    "chrono" : 2987,
    "code" : "playTime",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 729,
    "owner" :  ["5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446313909226),
    "value" : "2987"
});

/* 12 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c516980ff"),
    "activityId" : "ACT-HAND",
    "chrono" : 2987,
    "code" : "playTime",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 730,
    "owner" :  ["07cfa115-6a8e-42b3-8a08-16ce1d7dbd89"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446313909275),
    "value" : "2987"
});

/* 13 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698100"),
    "activityId" : "ACT-HAND",
    "chrono" : 2987,
    "code" : "playTime",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 731,
    "owner" :  ["1ce4591d-74a8-46e9-af80-d633f9344d27"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446313909295),
    "value" : "2987"
});

/* 14 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698101"),
    "activityId" : "ACT-HAND",
    "chrono" : 2987,
    "code" : "playTime",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 732,
    "owner" :  ["26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446313909311),
    "value" : "2987"
});

/* 15 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698102"),
    "activityId" : "ACT-HAND",
    "chrono" : 2987,
    "code" : "playTime",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 733,
    "owner" :  ["cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446313909327),
    "value" : "2987"
});

/* 16 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698103"),
    "activityId" : "ACT-HAND",
    "chrono" : 2987,
    "code" : "playTime",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 734,
    "owner" :  ["c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446313909341),
    "value" : "2987"
});

/* 17 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698104"),
    "activityId" : "ACT-HAND",
    "chrono" : 2987,
    "code" : "playTime",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 735,
    "owner" :  ["5a1c12af-0fc4-4eb8-a342-216e51bbada8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446313909360),
    "value" : "2987"
});

/* 18 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698105"),
    "activityId" : "ACT-HAND",
    "chrono" : 2987,
    "code" : "playTime",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 736,
    "owner" :  ["941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446313909374),
    "value" : "2987"
});

/* 19 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698106"),
    "activityId" : "ACT-HAND",
    "chrono" : 2987,
    "code" : "playTime",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 737,
    "owner" :  ["cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446313909390),
    "value" : "2987"
});

/* 20 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698107"),
    "activityId" : "ACT-HAND",
    "chrono" : 2987,
    "code" : "playTime",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 738,
    "owner" :  ["ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446313909407),
    "value" : "2987"
});

/* 21 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698108"),
    "activityId" : "ACT-HAND",
    "chrono" : 2987,
    "code" : "playTime",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 739,
    "owner" :  ["43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446313909422),
    "value" : "2987"
});

/* 22 */
db.SB_Stats.insert({
    "_id" : ObjectId("5634ffbae4b09c0c51698109"),
    "activityId" : "ACT-HAND",
    "chrono" : 2987,
    "code" : "playTime",
    "eventId" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "mRowid" : 740,
    "owner" :  ["46bea3c9-a3c0-4f4e-91fc-0bd2797b48df"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "timer" : NumberLong(1446313909436),
    "value" : "2987"
});