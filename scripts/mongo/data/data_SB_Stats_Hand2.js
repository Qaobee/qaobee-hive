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

db.getCollection('SB_Stats').remove({"eventId":"59bb6da2-b9d2-4453-856d-52a0fb98eac1"});

/* 1 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169823d").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 119,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446911672294),
    "value" : 0
});

/* 5 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360b5").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : false,
    "chrono" : 77,
    "code" : "yellowCard",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 112,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a", 
        "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"
    ],
    "phaseSeqId" : "407426cc-0ad5-41b9-a562-87b82ad46524",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446911672294),
    "value" : "1"
});

/* 2 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169823e").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 120,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446911898962),
    "value" : "goalkeeper"
});

/* 3 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169823f").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 121,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446911921162),
    "value" : "left-wingman"
});

/* 4 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698240").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 122,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912039019),
    "value" : "left-backcourt"
});

/* 5 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698241").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 123,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c0bf7706-f039-46c3-86b2-460930696890"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912046460),
    "value" : "right-wingman"
});

/* 6 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698242").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 124,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912050443),
    "value" : "center-backcourt"
});

/* 7 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698243").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 125,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912055800),
    "value" : "pivot"
});

/* 8 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698244").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 126,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912062651),
    "value" : "right-backcourt"
});

/* 9 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698245").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "substitue",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 127,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "1ce4591d-74a8-46e9-af80-d633f9344d27"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912450096),
    "value" : "1"
});

/* 10 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698246").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "substitue",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 128,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912450233),
    "value" : "1"
});

/* 11 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698247").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "holder",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 129,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912450251),
    "value" : "1"
});

/* 12 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698248").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "holder",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 130,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912450268),
    "value" : "1"
});

/* 13 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698249").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "substitue",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 131,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912450288),
    "value" : "1"
});

/* 14 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169824a").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "holder",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 132,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912450308),
    "value" : "1"
});

/* 15 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169824b").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "holder",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 133,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c0bf7706-f039-46c3-86b2-460930696890"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912450328),
    "value" : "1"
});

/* 16 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169824c").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "holder",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 134,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912450348),
    "value" : "1"
});

/* 17 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169824d").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "substitue",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 135,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912450364),
    "value" : "1"
});

/* 18 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169824e").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "holder",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 136,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912450380),
    "value" : "1"
});

/* 19 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169824f").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "holder",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 137,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912450398),
    "value" : "1"
});

/* 20 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698250").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "substitue",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 138,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "d31d3550-479a-4ee9-8304-84a132202d89"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912450415),
    "value" : "1"
});

/* 21 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698251").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 28,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 139,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912540376),
    "value" : "0"
});

/* 22 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698252").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 29,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 140,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912541254),
    "value" : "1"
});

/* 23 */
var shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698253").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 31,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 141,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446912543410),
    "value" : "BACKLEFT6"
});

/* 24 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698254").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 34,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 142,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446912546133),
    "value" : "LDOWN"
});

/* 25 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698255").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 35,
    "code" : "stopOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 143,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446912547136),
    "value" : "1"
});

/* 26 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698256").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 35,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 144,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912547097),
    "value" : "6"
});

/* 27 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698257").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 60,
    "code" : "looseball",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 145,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912572204),
    "value" : "1"
});

/* 28 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698258").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 60,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 146,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912572203),
    "value" : "25"
});

/* 29 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698259").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 62,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 147,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446912574664),
    "value" : "BACKLEFT6"
});

/* 30 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169825a").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 64,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 148,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446912576409),
    "value" : "LDOWN"
});

/* 31 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169825b").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 65,
    "code" : "goalConceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 149,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446912577593),
    "value" : "1"
});

/* 32 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169825c").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 65,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 150,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : "565974fd033dce10edafcc33",
    "timer" : NumberLong(1446912577593),
    "value" : "5"
});

/* 33 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169825d").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 87,
    "code" : "passDec",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 151,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912599217),
    "value" : "1"
});

/* 34 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169825e").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 93,
    "code" : "passOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 152,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912605600),
    "value" : "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"
});

/* 35 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169825f").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 94,
    "code" : "duelWon",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 153,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912606989),
    "value" : "1"
});

/* 36 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698260").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 96,
    "code" : "passOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 154,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912608540),
    "value" : "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"
});

/* 37 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698261").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 98,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 155,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912610402),
    "value" : "CENTER6"
});

/* 38 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698262").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 99,
    "code" : "impactShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 156,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912611418),
    "value" : "RDOWN"
});

/* 39 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698263").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 100,
    "code" : "goalScored",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 157,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912612345),
    "value" : "1"
});

/* 40 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698264").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 100,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 158,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912612345),
    "value" : "35"
});

/* 41 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698265").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 130,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 159,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446912642793),
    "value" : "CENTER9"
});

/* 42 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698266").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 132,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 160,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446912644222),
    "value" : "LDOWN"
});

/* 43 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698267").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 133,
    "code" : "stopOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 161,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446912645234),
    "value" : "1"
});

/* 44 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698268").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 133,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 162,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912645211),
    "value" : "33"
});

/* 45 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698269").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 168,
    "code" : "zone",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 163,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912680374),
    "value" : "1"
});

/* 46 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169826a").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 168,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 164,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912680378),
    "value" : "35"
});

/* 47 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169826b").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 188,
    "code" : "neutralization",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 165,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912700730),
    "value" : "1"
});

/* 48 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169826c").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 188,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 166,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912700730),
    "value" : "20"
});

/* 49 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169826d").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 189,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 167,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912701909),
    "value" : "1"
});

/* 50 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169826e").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 206,
    "code" : "badPosition",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 168,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c0bf7706-f039-46c3-86b2-460930696890"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912718402),
    "value" : "1"
});

/* 1 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169826f").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 206,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 169,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912718401),
    "value" : "17"
});

/* 2 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698270").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 208,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 170,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446912720487),
    "value" : "LWING"
});

/* 3 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698271").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 209,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 171,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446912721561),
    "value" : "RDOWN"
});

/* 4 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698272").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 210,
    "code" : "goalConceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 172,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446912722476),
    "value" : "1"
});

/* 5 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698273").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 210,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 173,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912722475),
    "value" : "4"
});

/* 6 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698274").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 215,
    "code" : "looseball",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 174,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c0bf7706-f039-46c3-86b2-460930696890"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912727997),
    "value" : "1"
});

/* 7 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698275").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 215,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 175,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912727997),
    "value" : "5"
});

/* 8 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698276").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 219,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 176,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446912731408),
    "value" : "BACKLEFT6"
});

/* 9 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698277").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 221,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 177,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446912733316),
    "value" : "RDOWN"
});

/* 10 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698278").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 223,
    "code" : "goalConceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 178,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446912735529),
    "value" : "1"
});

/* 11 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698279").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 223,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 179,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912735532),
    "value" : "8"
});

/* 12 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169827a").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 259,
    "code" : "shift",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 180,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912772579),
    "value" : "1"
});

/* 13 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169827b").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 260,
    "code" : "passOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 181,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912773960),
    "value" : "cc5d449a-d466-400c-b73f-955c676103ed"
});

/* 14 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169827c").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 262,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 182,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446912776391),
    "value" : "BACKLEFT9"
});

/* 15 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169827d").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 264,
    "code" : "impactShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 183,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446912777717),
    "value" : "RDOWN"
});

/* 16 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169827e").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 265,
    "code" : "goalScored",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 184,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446912779071),
    "value" : "1"
});

/* 17 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169827f").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 265,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 185,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912779070),
    "value" : "42"
});

/* 18 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698280").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 284,
    "code" : "interceptionOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 186,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912798147),
    "value" : "1"
});

/* 19 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698281").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 284,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 187,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912798147),
    "value" : "19"
});

/* 20 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698282").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 290,
    "code" : "passDec",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 188,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912803844),
    "value" : "1"
});

/* 21 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698283").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 291,
    "code" : "passOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 189,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912805003),
    "value" : "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"
});

/* 22 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698284").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 292,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 190,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446912806433),
    "value" : "BACKLEFT6"
});

/* 23 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698285").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 294,
    "code" : "impactShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 191,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446912808187),
    "value" : "RDOWN"
});

/* 24 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698286").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 295,
    "code" : "goalScored",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 192,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446912809206),
    "value" : "1"
});

/* 25 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698287").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 295,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 193,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912809206),
    "value" : "11"
});

/* 26 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698288").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 318,
    "code" : "duelLoose",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 194,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912831855),
    "value" : "1"
});

/* 27 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698289").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 318,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 195,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912831855),
    "value" : "23"
});

/* 28 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169828a").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 320,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 196,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446912834151),
    "value" : "CENTER6"
});

/* 29 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169828b").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 322,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 197,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446912836117),
    "value" : "RMIDDLE"
});

/* 30 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169828c").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 323,
    "code" : "goalConceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 198,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446912837324),
    "value" : "1"
});

/* 31 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169828d").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 323,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 199,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912837324),
    "value" : "5"
});

/* 32 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169828e").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 338,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 200,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446912852006),
    "value" : "LWING"
});

/* 33 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169828f").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 339,
    "code" : "impactShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 201,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446912853310),
    "value" : "HC"
});

/* 34 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698290").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 339,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 202,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912853310),
    "value" : "16"
});

/* 35 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698291").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 354,
    "code" : "neutralization",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 203,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912867624),
    "value" : "1"
});

/* 36 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698292").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 354,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 204,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912867623),
    "value" : "15"
});

/* 37 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698293").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 366,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 205,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912879743),
    "value" : "0"
});

/* 38 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698294").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 368,
    "code" : "neutralization",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 206,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912882181),
    "value" : "1"
});

/* 39 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698295").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 368,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 207,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912882181),
    "value" : "2"
});

/* 40 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698296").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 369,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 208,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912882965),
    "value" : "0"
});

/* 41 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698297").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 374,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 209,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446912888467),
    "value" : "CENTER6"
});

/* 42 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698298").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 375,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 210,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446912889575),
    "value" : "LDOWN"
});

/* 43 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698299").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 377,
    "code" : "goalConceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 211,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446912890667),
    "value" : "1"
});

/* 44 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169829a").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 377,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 212,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912890666),
    "value" : "8"
});

/* 45 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169829b").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 384,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 213,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446912897973),
    "value" : "CENTER6"
});

/* 46 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169829c").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 386,
    "code" : "impactShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 214,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446912900360),
    "value" : "POST"
});

/* 47 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169829d").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 421,
    "code" : "passOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 215,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912934681),
    "value" : "5f82c510-2c89-46b0-b87d-d3b59e748615"
});

/* 48 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169829e").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 424,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 216,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912938049),
    "value" : "47"
});

/* 49 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169829f").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 428,
    "code" : "interceptionKo",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 217,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912942404),
    "value" : "1"
});

/* 50 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982a0").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 428,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 218,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912942403),
    "value" : "4"
});

/* 1 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982a1").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 433,
    "code" : "interceptionOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 219,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912947392),
    "value" : "1"
});

/* 2 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982a2").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 433,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 220,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912947389),
    "value" : "5"
});

/* 3 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982a3").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 444,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 221,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446912958114),
    "value" : "BACKRIGHT9"
});

/* 4 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982a4").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 446,
    "code" : "impactShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 222,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446912959858),
    "value" : "LUP"
});

/* 5 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982a5").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 447,
    "code" : "stopOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 223,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446912960969),
    "value" : "1"
});

/* 6 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982a6").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 447,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 224,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912960916),
    "value" : "14"
});

/* 7 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982a7").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 447,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 225,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912960987),
    "value" : "0"
});

/* 8 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982a8").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 482,
    "code" : "neutralization",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 226,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912997193),
    "value" : "1"
});

/* 9 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982a9").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 482,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 227,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912997196),
    "value" : "35"
});

/* 10 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982aa").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 501,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 228,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913016334),
    "value" : "CENTER9"
});

/* 11 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982ab").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 503,
    "code" : "impactShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 229,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913017583),
    "value" : "POST"
});

/* 12 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982ac").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 519,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 230,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913034363),
    "value" : "0"
});

/* 13 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982ad").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 522,
    "code" : "neutralization",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 231,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913037054),
    "value" : "1"
});

/* 14 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982ae").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 522,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 232,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913037053),
    "value" : "2"
});

/* 15 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982af").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 525,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 233,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913040271),
    "value" : "0"
});

/* 16 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982b0").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 535,
    "code" : "badPosition",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 234,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913050389),
    "value" : "1"
});

/* 17 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982b1").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 535,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 235,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913050390),
    "value" : "10"
});

/* 18 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982b2").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 537,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 236,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913051920),
    "value" : "BACKRIGHT6"
});

/* 19 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982b3").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 538,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 237,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913052903),
    "value" : "LUP"
});

/* 20 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982b4").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 539,
    "code" : "goalConceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 238,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913053903),
    "value" : "1"
});

/* 21 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982b5").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 539,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 239,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913053903),
    "value" : "3"
});

/* 22 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982b6").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 546,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 240,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913061248),
    "value" : "LWING"
});

/* 23 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982b7").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 548,
    "code" : "impactShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 241,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913063453),
    "value" : "LDOWN"
});

/* 24 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982b8").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 550,
    "code" : "stopOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 242,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913064778),
    "value" : "1"
});

/* 25 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982b9").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 550,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 243,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913064731),
    "value" : "0"
});

/* 26 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982ba").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 570,
    "code" : "neutralization",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 244,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913085178),
    "value" : "1"
});

/* 27 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982bb").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 570,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 245,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913085179),
    "value" : "20"
});

/* 28 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982bc").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 572,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 246,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913086788),
    "value" : "0"
});

/* 29 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982bd").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 574,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 247,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913088508),
    "value" : "BACKLEFT9"
});

/* 30 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982be").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 575,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 248,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913089760),
    "value" : "POST"
});

/* 31 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982bf").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 589,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 249,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913104066),
    "value" : "substitue"
});

/* 32 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982c0").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 589,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 250,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913104144),
    "value" : "right-backcourt"
});

/* 33 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982c1").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 600,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 251,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913114831),
    "value" : 600
});

/* 34 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982c2").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 603,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 252,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913117524),
    "value" : "left-backcourt"
});

/* 35 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982c3").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 609,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 253,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913123624),
    "value" : "right-backcourt"
});

/* 36 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982c4").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 616,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 254,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c0bf7706-f039-46c3-86b2-460930696890"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913130620),
    "value" : "substitue"
});

/* 37 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982c5").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 616,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 255,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "1ce4591d-74a8-46e9-af80-d633f9344d27"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913130660),
    "value" : "right-wingman"
});

/* 38 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982c6").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 622,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 256,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913136955),
    "value" : "50"
});

/* 39 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982c7").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 626,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 257,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "1ce4591d-74a8-46e9-af80-d633f9344d27"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913140863),
    "value" : "RWING"
});

/* 40 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982c8").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 628,
    "code" : "impactShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 258,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "1ce4591d-74a8-46e9-af80-d633f9344d27"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913142749),
    "value" : "LUP"
});

/* 41 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982c9").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 629,
    "code" : "stopOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 259,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913143934),
    "value" : "1"
});

/* 42 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982ca").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 629,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 260,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913143885),
    "value" : "7"
});

/* 43 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982cb").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 629,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 261,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913143961),
    "value" : "0"
});

/* 44 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982cc").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 636,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 262,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913150550),
    "value" : "BACKLEFT6"
});

/* 45 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982cd").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 637,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 263,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913151945),
    "value" : "LUP"
});

/* 46 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982ce").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 638,
    "code" : "goalConceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 264,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913152956),
    "value" : "1"
});

/* 47 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982cf").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 638,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 265,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913152956),
    "value" : "9"
});

/* 48 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982d0").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 664,
    "code" : "looseball",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 266,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "1ce4591d-74a8-46e9-af80-d633f9344d27"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913179107),
    "value" : "1"
});

/* 49 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982d1").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 664,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 267,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913179107),
    "value" : "26"
});

/* 50 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982d2").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 668,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 268,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913183374),
    "value" : "CENTER6"
});

/* 1 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982d3").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 670,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 269,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913184600),
    "value" : "LDOWN"
});

/* 2 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982d4").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 671,
    "code" : "goalConceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 270,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913185683),
    "value" : "1"
});

/* 3 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982d5").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 671,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 271,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913185683),
    "value" : "7"
});

/* 4 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982d6").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 688,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 272,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913203428),
    "value" : "CENTER6"
});

/* 5 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982d7").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 692,
    "code" : "impactShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 273,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913207426),
    "value" : "CDOWN"
});

/* 6 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982d8").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 693,
    "code" : "goalScored",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 274,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913208381),
    "value" : "1"
});

/* 7 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982d9").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 693,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 275,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913208384),
    "value" : "22"
});

/* 8 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982da").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 708,
    "code" : "timeoutUs",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 276,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "Avenir Du Ponant 1"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913223138),
    "value" : "1"
});

/* 9 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982db").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 717,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 277,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913293990),
    "value" : "0"
});

/* 10 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982dc").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 719,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 278,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913295916),
    "value" : "0"
});

/* 11 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982dd").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 722,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 279,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913298354),
    "value" : "BACKLEFT6"
});

/* 12 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982de").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 723,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 280,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913299937),
    "value" : "HC"
});

/* 13 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982df").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 723,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 281,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913299937),
    "value" : "4"
});

/* 14 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982e0").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 737,
    "code" : "looseball",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 282,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913313893),
    "value" : "1"
});

/* 15 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982e1").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 737,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 283,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913313891),
    "value" : "14"
});

/* 16 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982e2").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 743,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 284,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913319327),
    "value" : "0"
});

/* 17 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982e3").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 753,
    "code" : "forceAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 285,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913329783),
    "value" : "1"
});

/* 18 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982e4").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 753,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 286,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913329782),
    "value" : "10"
});

/* 19 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982e5").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 783,
    "code" : "neutralization",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 287,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913359106),
    "value" : "1"
});

/* 20 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982e6").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 783,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 288,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913359106),
    "value" : "30"
});

/* 21 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982e7").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 800,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 289,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913376111),
    "value" : "0"
});

/* 22 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982e8").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 819,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 290,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913395472),
    "value" : "BACKLEFT9"
});

/* 23 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982e9").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 820,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 291,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913396598),
    "value" : "RMIDDLE"
});

/* 24 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982ea").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 822,
    "code" : "stopOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 292,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913398176),
    "value" : "1"
});

/* 25 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982eb").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 822,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 293,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913398131),
    "value" : "22"
});

/* 26 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982ec").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 839,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 294,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913415285),
    "value" : "CENTER9"
});

/* 27 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982ed").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 840,
    "code" : "impactShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 295,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913416269),
    "value" : "LDOWN"
});

/* 28 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982ee").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 841,
    "code" : "stopOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 296,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913417472),
    "value" : "1"
});

/* 29 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982ef").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 841,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 297,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913417419),
    "value" : "19"
});

/* 30 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982f0").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 841,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 298,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913417500),
    "value" : "0"
});

/* 31 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982f1").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 842,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 299,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913418416),
    "value" : "0"
});

/* 32 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982f2").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 862,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 300,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913438452),
    "value" : "substitue"
});

/* 33 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982f3").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 862,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 301,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913438515),
    "value" : "left-wingman"
});

/* 34 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982f4").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 872,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 302,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913448391),
    "value" : "LWING"
});

/* 35 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982f5").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 873,
    "code" : "impactShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 303,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913449679),
    "value" : "RDOWN"
});

/* 36 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982f6").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 874,
    "code" : "stopOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 304,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913450903),
    "value" : "1"
});

/* 37 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982f7").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 874,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 305,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913450862),
    "value" : "0"
});

/* 38 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982f8").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 895,
    "code" : "neutralization",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 306,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913471894),
    "value" : "1"
});

/* 39 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982f9").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 895,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 307,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913471895),
    "value" : "21"
});

/* 40 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982fa").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 896,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 308,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913472573),
    "value" : "1"
});

/* 41 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982fb").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 899,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 309,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913475819),
    "value" : "CENTER6"
});

/* 42 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982fc").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 901,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 310,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913477715),
    "value" : "LUP"
});

/* 43 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982fd").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 902,
    "code" : "goalConceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 311,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913478746),
    "value" : "1"
});

/* 44 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982fe").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 902,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 312,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913478744),
    "value" : "6"
});

/* 45 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982ff").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 915,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 313,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913492122),
    "value" : "substitue"
});

/* 46 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698300").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 916,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 314,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "d31d3550-479a-4ee9-8304-84a132202d89"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913492169),
    "value" : "pivot"
});

/* 47 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698301").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 934,
    "code" : "exclusionTempoObtained",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 315,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913510240),
    "value" : "1"
});

/* 48 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698302").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 943,
    "code" : "passOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 316,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913519320),
    "value" : "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"
});

/* 49 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698303").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 945,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 317,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913521977),
    "value" : "LWING"
});

/* 50 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698304").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 947,
    "code" : "impactShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 318,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913523395),
    "value" : "HC"
});

/* 1 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698305").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 947,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 319,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913523394),
    "value" : "45"
});

/* 2 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698306").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 955,
    "code" : "gameSystem",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 320,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "562ba2d4b70fd108e375395e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913531395),
    "value" : "1-5"
});

/* 3 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698307").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 961,
    "code" : "neutralization",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 321,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "1ce4591d-74a8-46e9-af80-d633f9344d27"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913537341),
    "value" : "1"
});

/* 4 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698308").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 961,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 322,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913537342),
    "value" : "14"
});

/* 5 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698309").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 979,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 323,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913556028),
    "value" : "BACKRIGHT6"
});

/* 6 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169830a").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 980,
    "code" : "impactShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 324,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913556997),
    "value" : "LDOWN"
});

/* 7 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169830b").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 982,
    "code" : "stopOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 325,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913558281),
    "value" : "1"
});

/* 8 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169830c").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 982,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 326,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913558244),
    "value" : "0"
});

/* 9 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169830d").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 990,
    "code" : "badPosition",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 327,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913566489),
    "value" : "1"
});

/* 10 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169830e").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 990,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 328,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913566488),
    "value" : "8"
});

/* 11 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169830f").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 992,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 329,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913568874),
    "value" : "BACKLEFT6"
});

/* 12 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698310").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 993,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 330,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913569929),
    "value" : "RDOWN"
});

/* 13 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698311").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 994,
    "code" : "goalConceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 331,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913570902),
    "value" : "1"
});

/* 14 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698312").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 994,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 332,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913570902),
    "value" : "4"
});

/* 15 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698313").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1021,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 333,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913597707),
    "value" : "CENTER9"
});

/* 16 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698314").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1022,
    "code" : "impactShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 334,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913599149),
    "value" : "POST"
});

/* 17 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698315").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1045,
    "code" : "passOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 335,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913621225),
    "value" : "d31d3550-479a-4ee9-8304-84a132202d89"
});

/* 18 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698316").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1045,
    "code" : "duelWon",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 336,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "d31d3550-479a-4ee9-8304-84a132202d89"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913621758),
    "value" : "1"
});

/* 19 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698317").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1046,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 337,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913622984),
    "value" : "0"
});

/* 20 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698318").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1050,
    "code" : "interceptionOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 338,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "d31d3550-479a-4ee9-8304-84a132202d89"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913627085),
    "value" : "1"
});

/* 21 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698319").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1050,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 339,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913627085),
    "value" : "4"
});

/* 22 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169831a").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1056,
    "code" : "looseball",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 340,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913632486),
    "value" : "1"
});

/* 23 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169831b").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1056,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 341,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913632488),
    "value" : "6"
});

/* 24 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169831c").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1069,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 342,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913645749),
    "value" : "substitue"
});

/* 25 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169831d").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1069,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 343,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913645793),
    "value" : "right-backcourt"
});

/* 26 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169831e").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1129,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 344,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913706938),
    "value" : "CENTER9"
});

/* 27 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169831f").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1130,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 345,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913708304),
    "value" : "HC"
});

/* 28 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698320").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1130,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 346,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913708304),
    "value" : "74"
});

/* 29 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698321").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1154,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 347,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913732324),
    "value" : "24"
});

/* 30 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698322").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1156,
    "code" : "neutralization",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 348,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913733899),
    "value" : "1"
});

/* 31 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698323").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1156,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 349,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913733899),
    "value" : "2"
});

/* 32 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698324").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1170,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 350,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913748366),
    "value" : 1170
});

/* 33 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698325").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1173,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 351,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "d31d3550-479a-4ee9-8304-84a132202d89"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913750610),
    "value" : "center-backcourt"
});

/* 34 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698326").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1175,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 352,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913753362),
    "value" : "pivot"
});

/* 35 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698327").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1179,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 353,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913757340),
    "value" : "0"
});

/* 36 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698328").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1192,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 354,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913770108),
    "value" : "CENTER6"
});

/* 37 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698329").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1193,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 355,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913771114),
    "value" : "POST"
});

/* 38 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169832a").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1196,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 356,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913773657),
    "value" : "CENTER6"
});

/* 39 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169832b").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1197,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 357,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913774994),
    "value" : "LDOWN"
});

/* 40 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169832c").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1199,
    "code" : "goalConceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 358,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913776512),
    "value" : "1"
});

/* 41 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169832d").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1199,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 359,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913776512),
    "value" : "20"
});

/* 42 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169832e").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1227,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 360,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913851065),
    "value" : "0"
});

/* 43 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169832f").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1229,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 361,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913853144),
    "value" : "CENTER6"
});

/* 44 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698330").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1230,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 362,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913854355),
    "value" : "CDOWN"
});

/* 45 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698331").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1231,
    "code" : "goalConceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 363,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913855429),
    "value" : "1"
});

/* 46 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698332").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1231,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 364,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913855429),
    "value" : "4"
});

/* 47 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698333").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1267,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 365,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913891102),
    "value" : "CENTER9"
});

/* 48 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698334").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1268,
    "code" : "impactShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 366,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913892101),
    "value" : "RUP"
});

/* 49 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698335").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1269,
    "code" : "goalScored",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 367,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913893122),
    "value" : "1"
});

/* 50 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698336").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1269,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 368,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913893122),
    "value" : "38"
});

/* 1 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698337").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1291,
    "code" : "neutralization",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 369,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913915188),
    "value" : "1"
});

/* 2 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698338").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1291,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 370,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913915184),
    "value" : "22"
});

/* 3 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698339").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1293,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 371,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913917064),
    "value" : "0"
});

/* 4 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169833a").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1294,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 372,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913918624),
    "value" : "CENTER6"
});

/* 5 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169833b").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1295,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 373,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913919730),
    "value" : "LDOWN"
});

/* 6 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169833c").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1296,
    "code" : "stopOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 374,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913920825),
    "value" : "1"
});

/* 7 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169833d").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1296,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 375,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913920793),
    "value" : "3"
});

/* 8 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169833e").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1328,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 376,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913952541),
    "value" : "BACKLEFT6"
});

/* 9 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169833f").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1329,
    "code" : "impactShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 377,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913953493),
    "value" : "RDOWN"
});

/* 10 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698340").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1330,
    "code" : "goalScored",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 378,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446913954565),
    "value" : "1"
});

/* 11 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698341").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1330,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 379,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913954568),
    "value" : "34"
});

/* 12 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698342").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1349,
    "code" : "neutralization",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 380,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913973079),
    "value" : "1"
});

/* 13 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698343").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1349,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 381,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913973080),
    "value" : "19"
});

/* 14 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698344").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1350,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 382,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913974087),
    "value" : "0"
});

/* 15 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698345").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1356,
    "code" : "neutralization",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 383,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913980311),
    "value" : "1"
});

/* 16 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698346").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1356,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 384,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913980310),
    "value" : "6"
});

/* 17 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698347").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1357,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 385,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913981100),
    "value" : "1"
});

/* 18 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698348").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1385,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 386,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914018752),
    "value" : 523
});

/* 19 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698349").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1385,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 387,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914028873),
    "value" : 210
});

/* 20 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169834a").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1385,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 388,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914032343),
    "value" : "pivot"
});

/* 21 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169834b").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1385,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 389,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914035335),
    "value" : "left-wingman"
});

/* 22 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169834c").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1385,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 390,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914081183),
    "value" : "28"
});

/* 23 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169834d").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1386,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 391,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914083132),
    "value" : "0"
});

/* 24 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169834e").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1392,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 392,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446914088446),
    "value" : "BACKLEFT6"
});

/* 25 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169834f").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1393,
    "code" : "impactShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 393,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446914089525),
    "value" : "RMIDDLE"
});

/* 26 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698350").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1394,
    "code" : "goalScored",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 394,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446914090659),
    "value" : "1"
});

/* 27 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698351").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1394,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 395,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914090659),
    "value" : "7"
});

/* 28 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698352").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1408,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 396,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914104936),
    "value" : "0"
});

/* 29 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698353").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1425,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 397,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446914121904),
    "value" : "LWING"
});

/* 30 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698354").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1426,
    "code" : "impactShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 398,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446914123134),
    "value" : "RMIDDLE"
});

/* 31 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698355").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1427,
    "code" : "goalScored",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 399,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446914124229),
    "value" : "1"
});

/* 32 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698356").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1427,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 400,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914124229),
    "value" : "19"
});

/* 33 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698357").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1454,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 401,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446914150264),
    "value" : "CENTER6"
});

/* 34 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698358").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1455,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 402,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446914151566),
    "value" : "HC"
});

/* 35 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698359").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1455,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 403,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914151568),
    "value" : "27"
});

/* 36 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169835a").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1478,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 404,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446914175113),
    "value" : "LWING"
});

/* 37 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169835b").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1479,
    "code" : "impactShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 405,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446914176246),
    "value" : "RDOWN"
});

/* 38 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169835c").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1481,
    "code" : "goalScored",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 406,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446914177288),
    "value" : "1"
});

/* 39 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169835d").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1481,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 407,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914177288),
    "value" : "26"
});

/* 40 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169835e").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1493,
    "code" : "badPosition",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 408,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914190126),
    "value" : "1"
});

/* 41 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169835f").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1493,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 409,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914190126),
    "value" : "12"
});

/* 42 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698360").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1495,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 410,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446914192024),
    "value" : "BACKRIGHT6"
});

/* 43 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698361").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1496,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 411,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446914193166),
    "value" : "RDOWN"
});

/* 44 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698362").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1497,
    "code" : "goalConceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 412,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446914194144),
    "value" : "1"
});

/* 45 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698363").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1497,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 413,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914194144),
    "value" : "4"
});

/* 46 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698364").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1498,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 414,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914953685),
    "value" : 1498
});

/* 47 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698365").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1498,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 415,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914956392),
    "value" : "substitue"
});

/* 48 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698366").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1498,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 416,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914956440),
    "value" : "right-backcourt"
});

/* 49 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698367").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1498,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 417,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "1ce4591d-74a8-46e9-af80-d633f9344d27"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914974436),
    "value" : "substitue"
});

/* 50 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698368").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1498,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 418,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c0bf7706-f039-46c3-86b2-460930696890"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914974479),
    "value" : "right-wingman"
});

/* 1 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698369").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1498,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 419,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "d31d3550-479a-4ee9-8304-84a132202d89"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915010796),
    "value" : "substitue"
});

/* 2 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169836a").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1498,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 420,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915010948),
    "value" : "center-backcourt"
});

/* 3 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169836b").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1501,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 421,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915031170),
    "value" : "CENTER6"
});

/* 4 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169836c").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1503,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 422,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915033055),
    "value" : "CDOWN"
});

/* 5 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169836d").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1505,
    "code" : "goalConceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 423,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915034508),
    "value" : "1"
});

/* 6 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169836e").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1505,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 424,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915034508),
    "value" : "6"
});

/* 7 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169836f").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1524,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 425,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915054350),
    "value" : "BACKRIGHT6"
});

/* 8 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698370").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1526,
    "code" : "impactShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 426,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915055445),
    "value" : "LDOWN"
});

/* 9 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698371").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1527,
    "code" : "stopOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 427,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915057011),
    "value" : "1"
});

/* 10 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698372").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1527,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 428,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915056950),
    "value" : "22"
});

/* 11 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698373").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1527,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 429,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915057037),
    "value" : "0"
});

/* 12 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698374").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1545,
    "code" : "neutralization",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 430,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915075143),
    "value" : "1"
});

/* 13 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698375").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1545,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 431,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915075142),
    "value" : "18"
});

/* 14 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698376").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1549,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 432,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915078825),
    "value" : "0"
});

/* 15 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698377").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1551,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 433,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915080676),
    "value" : "CENTER6"
});

/* 16 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698378").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1553,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 434,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915082687),
    "value" : "LDOWN"
});

/* 17 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698379").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1554,
    "code" : "goalConceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 435,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915084105),
    "value" : "1"
});

/* 18 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169837a").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1554,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 436,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915084105),
    "value" : "5"
});

/* 19 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169837b").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1568,
    "code" : "looseball",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 437,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c0bf7706-f039-46c3-86b2-460930696890"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915098273),
    "value" : "1"
});

/* 20 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169837c").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1568,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 438,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915098273),
    "value" : "14"
});

/* 21 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169837d").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1575,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 439,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915104860),
    "value" : "BACKLEFT6"
});

/* 22 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169837e").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1576,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 440,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915106047),
    "value" : "RDOWN"
});

/* 23 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169837f").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1577,
    "code" : "goalConceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 441,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915107166),
    "value" : "1"
});

/* 24 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698380").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1577,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 442,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915107166),
    "value" : "9"
});

/* 25 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698381").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1606,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 443,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915135931),
    "value" : "29"
});

/* 26 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698382").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1615,
    "code" : "interceptionOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 444,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915145192),
    "value" : "1"
});

/* 27 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698383").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1615,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 445,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915145192),
    "value" : "9"
});

/* 28 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698384").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1622,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 446,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915151983),
    "value" : "BACKLEFT6"
});

/* 29 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698385").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1626,
    "code" : "impactShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 447,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915156209),
    "value" : "RUP"
});

/* 30 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698386").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1627,
    "code" : "goalScored",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 448,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915157358),
    "value" : "1"
});

/* 31 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698387").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1627,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 449,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915157358),
    "value" : "12"
});

/* 32 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698388").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1648,
    "code" : "duelLoose",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 450,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915178304),
    "value" : "1"
});

/* 33 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698389").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1648,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 451,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915178303),
    "value" : "20"
});

/* 34 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169838a").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1650,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 452,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915180287),
    "value" : "BACKRIGHT6"
});

/* 35 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169838b").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1651,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 453,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915181404),
    "value" : "RDOWN"
});

/* 36 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169838c").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1652,
    "code" : "goalConceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 454,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915182446),
    "value" : "1"
});

/* 37 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169838d").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1652,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 455,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915182446),
    "value" : "4"
});

/* 38 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169838e").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1672,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 456,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915202125),
    "value" : "BACKLEFT6"
});

/* 39 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169838f").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1674,
    "code" : "impactShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 457,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915203634),
    "value" : "POST"
});

/* 40 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698390").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1687,
    "code" : "passOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 458,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915216596),
    "value" : "cc5d449a-d466-400c-b73f-955c676103ed"
});

/* 41 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698391").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1689,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 459,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915218900),
    "value" : "36"
});

/* 42 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698392").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1691,
    "code" : "duelLoose",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 460,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915220781),
    "value" : "1"
});

/* 43 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698393").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1691,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 461,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915220781),
    "value" : "2"
});

/* 44 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698394").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1695,
    "code" : "duelLoose",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 462,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915225353),
    "value" : "1"
});

/* 45 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698395").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1695,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 463,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915225353),
    "value" : "4"
});

/* 46 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698396").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1697,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 464,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915227467),
    "value" : "BACKLEFT6"
});

/* 47 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698397").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1698,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 465,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915228419),
    "value" : "POST"
});

/* 48 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698398").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1701,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 466,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915231336),
    "value" : "BACKLEFT6"
});

/* 49 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698399").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1704,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 467,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915233675),
    "value" : "LMIDDLE"
});

/* 50 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169839a").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1705,
    "code" : "goalConceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 468,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915234988),
    "value" : "1"
});

/* 1 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169839b").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1705,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 469,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915234987),
    "value" : "10"
});

/* 2 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169839c").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1714,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 470,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c0bf7706-f039-46c3-86b2-460930696890"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915243685),
    "value" : "RWING"
});

/* 3 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169839d").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1715,
    "code" : "impactShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 471,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c0bf7706-f039-46c3-86b2-460930696890"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915244876),
    "value" : "HC"
});

/* 4 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169839e").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1715,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 472,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915244876),
    "value" : "10"
});

/* 5 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169839f").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1736,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 473,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915265701),
    "value" : "CENTER9"
});

/* 6 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983a0").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1737,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 474,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915266873),
    "value" : "RUP"
});

/* 7 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983a1").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1738,
    "code" : "goalConceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 475,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915267890),
    "value" : "1"
});

/* 8 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983a2").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1738,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 476,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915267891),
    "value" : "23"
});

/* 9 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983a3").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1757,
    "code" : "looseball",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 477,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915287163),
    "value" : "1"
});

/* 10 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983a4").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1757,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 478,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915287163),
    "value" : "19"
});

/* 11 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983a5").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1768,
    "code" : "contre",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 479,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915297563),
    "value" : "1"
});

/* 12 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983a6").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1768,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 480,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915297563),
    "value" : "11"
});

/* 13 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983a7").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1770,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 481,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915299549),
    "value" : "0"
});

/* 14 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983a8").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1797,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 482,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915326752),
    "value" : "0"
});

/* 15 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983a9").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1823,
    "code" : "shift",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 483,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915353189),
    "value" : "1"
});

/* 16 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983aa").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1826,
    "code" : "passOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 484,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915355600),
    "value" : "ce18d73e-dedf-43e5-8e75-16e0375be349"
});

/* 17 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983ab").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1828,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 485,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915358442),
    "value" : "LWING"
});

/* 18 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983ac").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1830,
    "code" : "impactShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 486,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915359649),
    "value" : "RUP"
});

/* 19 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983ad").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1831,
    "code" : "goalScored",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 487,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915360816),
    "value" : "1"
});

/* 20 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983ae").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1831,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 488,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915360816),
    "value" : "34"
});

/* 21 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983af").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1844,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 489,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915373809),
    "value" : 346
});

/* 22 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983b0").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1846,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 490,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915376210),
    "value" : 1846
});

/* 23 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983b1").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1851,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 491,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "d31d3550-479a-4ee9-8304-84a132202d89"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915381045),
    "value" : "center-backcourt"
});

/* 24 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983b2").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1853,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 492,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915383339),
    "value" : "pivot"
});

/* 25 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983b3").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1870,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 493,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915399850),
    "value" : "BACKRIGHT9"
});

/* 26 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983b4").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1871,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 494,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915400989),
    "value" : "RDOWN"
});

/* 27 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983b5").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1872,
    "code" : "stopOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 495,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915402282),
    "value" : "1"
});

/* 28 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983b6").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1872,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 496,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915402219),
    "value" : "0"
});

/* 29 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983b7").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1882,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 497,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915412264),
    "value" : "CENTER6"
});

/* 30 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983b8").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1883,
    "code" : "impactShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 498,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915413228),
    "value" : "LUP"
});

/* 31 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983b9").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1884,
    "code" : "goalScored",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 499,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915414233),
    "value" : "1"
});

/* 32 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983ba").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1884,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 500,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915414234),
    "value" : "12"
});

/* 33 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983bb").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1908,
    "code" : "duelLoose",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 501,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915437715),
    "value" : "1"
});

/* 34 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983bc").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1908,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 502,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915437716),
    "value" : "24"
});

/* 35 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983bd").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1909,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 503,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915439309),
    "value" : "BACKLEFT6"
});

/* 36 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983be").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1910,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 504,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915440351),
    "value" : "LDOWN"
});

/* 37 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983bf").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1911,
    "code" : "goalConceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 505,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915441390),
    "value" : "1"
});

/* 38 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983c0").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1911,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 506,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915441390),
    "value" : "3"
});

/* 39 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983c1").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1933,
    "code" : "looseball",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 507,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915462843),
    "value" : "1"
});

/* 40 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983c2").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1933,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 508,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915462844),
    "value" : "22"
});

/* 41 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983c3").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1937,
    "code" : "interceptionOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 509,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915467474),
    "value" : "1"
});

/* 42 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983c4").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1937,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 510,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915467476),
    "value" : "4"
});

/* 43 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983c5").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1941,
    "code" : "looseball",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 511,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915470642),
    "value" : "1"
});

/* 44 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983c6").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1941,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 512,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915470642),
    "value" : "4"
});

/* 45 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983c7").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1956,
    "code" : "neutralization",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 513,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915485835),
    "value" : "1"
});

/* 46 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983c8").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1956,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 514,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915485835),
    "value" : "15"
});

/* 47 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983c9").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1957,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 515,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915486622),
    "value" : "0"
});

/* 48 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983ca").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1962,
    "code" : "neutralization",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 516,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915492544),
    "value" : "1"
});

/* 49 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983cb").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1962,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 517,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915492544),
    "value" : "5"
});

/* 50 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983cc").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1964,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 518,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915493798),
    "value" : "1"
});

/* 1 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983cd").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1970,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 519,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915500327),
    "value" : "CENTER6"
});

/* 2 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983ce").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1971,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 520,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915501532),
    "value" : "POST"
});

/* 3 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983cf").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1978,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 521,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915508100),
    "value" : "BACKLEFT6"
});

/* 4 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983d0").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1979,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 522,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915509252),
    "value" : "LUP"
});

/* 5 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983d1").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1980,
    "code" : "goalConceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 523,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915510262),
    "value" : "1"
});

/* 6 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983d2").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1980,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 524,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915510262),
    "value" : "16"
});

/* 7 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983d3").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1991,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 525,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915521498),
    "value" : "BACKLEFT6"
});

/* 8 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983d4").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1992,
    "code" : "impactShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 526,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915522600),
    "value" : "LUP"
});

/* 9 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983d5").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1993,
    "code" : "goalScored",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 527,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915523590),
    "value" : "1"
});

/* 10 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983d6").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 1993,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 528,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915523590),
    "value" : "13"
});

/* 11 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983d7").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2004,
    "code" : "neutralization",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 529,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "d31d3550-479a-4ee9-8304-84a132202d89"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915534477),
    "value" : "1"
});

/* 12 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983d8").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2004,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 530,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915534477),
    "value" : "10"
});

/* 13 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983d9").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2006,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 531,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915535716),
    "value" : "0"
});

/* 14 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983da").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2020,
    "code" : "neutralization",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 532,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915549929),
    "value" : "1"
});

/* 15 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983db").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2020,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 533,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915549930),
    "value" : "14"
});

/* 16 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983dc").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2021,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 534,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915550816),
    "value" : "0"
});

/* 17 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983dd").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2036,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 535,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915566558),
    "value" : "BACKLEFT6"
});

/* 18 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983de").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2038,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 536,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915567770),
    "value" : "CDOWN"
});

/* 19 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983df").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2039,
    "code" : "stopOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 537,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915569061),
    "value" : "1"
});

/* 20 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983e0").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2039,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 538,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915569037),
    "value" : "18"
});

/* 21 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983e1").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2041,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 539,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915570942),
    "value" : "2"
});

/* 22 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983e2").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2045,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 540,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915575642),
    "value" : "CENTER6"
});

/* 23 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983e3").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2049,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 541,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915579543),
    "value" : "HC"
});

/* 24 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983e4").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2049,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 542,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915579542),
    "value" : "8"
});

/* 25 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983e5").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2086,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 543,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915620848),
    "value" : "CENTER6"
});

/* 26 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983e6").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2087,
    "code" : "impactShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 544,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915622070),
    "value" : "POST"
});

/* 27 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983e7").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2091,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 545,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915626083),
    "value" : "0"
});

/* 28 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983e8").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2116,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 546,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915650689),
    "value" : "BACKRIGHT9"
});

/* 29 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983e9").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2118,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 547,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915652217),
    "value" : "HC"
});

/* 30 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983ea").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2118,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 548,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915652217),
    "value" : "27"
});

/* 31 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983eb").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2124,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 549,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915658637),
    "value" : "BACKLEFT6"
});

/* 32 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983ec").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2125,
    "code" : "impactShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 550,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915659849),
    "value" : "LMIDDLE"
});

/* 33 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983ed").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2126,
    "code" : "goalScored",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 551,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915660880),
    "value" : "1"
});

/* 34 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983ee").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2126,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 552,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915660880),
    "value" : "8"
});

/* 35 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983ef").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2147,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 553,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915681888),
    "value" : "BACKRIGHT6"
});

/* 36 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983f0").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2148,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 554,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915683156),
    "value" : "HC"
});

/* 37 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983f1").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2148,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 555,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915683156),
    "value" : "22"
});

/* 38 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983f2").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2184,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 556,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915719121),
    "value" : "substitue"
});

/* 39 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983f3").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2185,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 557,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915719290),
    "value" : "goalkeeper"
});

/* 40 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983f4").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2191,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 558,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915726114),
    "value" : "substitue"
});

/* 41 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983f5").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2191,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 559,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915726170),
    "value" : "right-backcourt"
});

/* 42 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983f6").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2194,
    "code" : "looseball",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 560,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915729014),
    "value" : "1"
});

/* 43 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983f7").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2194,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 561,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915729014),
    "value" : "45"
});

/* 44 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983f8").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2216,
    "code" : "interceptionOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 562,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915750427),
    "value" : "1"
});

/* 45 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983f9").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2216,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 563,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915750427),
    "value" : "22"
});

/* 46 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983fa").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2221,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 564,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915755791),
    "value" : "BACKLEFT6"
});

/* 47 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983fb").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2222,
    "code" : "impactShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 565,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915756979),
    "value" : "LUP"
});

/* 48 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983fc").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2224,
    "code" : "stopOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 566,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915758381),
    "value" : "1"
});

/* 49 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983fd").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2224,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 567,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915758320),
    "value" : "8"
});

/* 50 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983fe").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2224,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 568,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915758405),
    "value" : "0"
});

/* 1 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983ff").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2243,
    "code" : "neutralization",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 569,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915777739),
    "value" : "1"
});

/* 2 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698400").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2243,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 570,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915777739),
    "value" : "19"
});

/* 3 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698401").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2256,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 571,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915790901),
    "value" : "0"
});

/* 4 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698402").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2258,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 572,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915792829),
    "value" : "CENTER6"
});

/* 5 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698403").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2259,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 573,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915794157),
    "value" : "LMIDDLE"
});

/* 6 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698404").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2260,
    "code" : "stopOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 574,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915795230),
    "value" : "1"
});

/* 7 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698405").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2260,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 575,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915795171),
    "value" : "0"
});

/* 8 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698406").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2265,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 576,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915800228),
    "value" : "BACKLEFT6"
});

/* 9 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698407").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2267,
    "code" : "impactShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 577,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915801287),
    "value" : "LMIDDLE"
});

/* 10 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698408").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2268,
    "code" : "goalScored",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 578,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915802441),
    "value" : "1"
});

/* 11 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698409").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2268,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 579,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915802441),
    "value" : "7"
});

/* 12 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169840a").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2319,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 580,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915970400),
    "value" : "6"
});

/* 13 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169840b").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2328,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 581,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c0bf7706-f039-46c3-86b2-460930696890"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915979216),
    "value" : "substitue"
});

/* 14 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169840c").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2328,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 582,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "1ce4591d-74a8-46e9-af80-d633f9344d27"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915979257),
    "value" : "right-wingman"
});

/* 15 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169840d").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2330,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 583,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "1ce4591d-74a8-46e9-af80-d633f9344d27"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915982002),
    "value" : "RWING"
});

/* 16 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169840e").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2332,
    "code" : "impactShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 584,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "1ce4591d-74a8-46e9-af80-d633f9344d27"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915983812),
    "value" : "HC"
});

/* 17 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169840f").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2332,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 585,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915983814),
    "value" : "13"
});

/* 18 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698410").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2340,
    "code" : "duelLoose",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 586,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915991343),
    "value" : "1"
});

/* 19 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698411").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2340,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 587,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915991342),
    "value" : "8"
});

/* 20 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698412").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2342,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 588,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915993595),
    "value" : "CENTER6"
});

/* 21 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698413").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2343,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 589,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915994686),
    "value" : "LDOWN"
});

/* 22 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698414").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2344,
    "code" : "goalConceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 590,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446915995844),
    "value" : "1"
});

/* 23 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698415").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2344,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 591,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915995843),
    "value" : "4"
});

/* 24 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698416").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2359,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 592,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916038078),
    "value" : "substitue"
});

/* 25 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698417").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2359,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 593,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916038235),
    "value" : "right-backcourt"
});

/* 26 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698418").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2386,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 594,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916072426),
    "value" : "0"
});

/* 27 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698419").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2387,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 595,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916073036),
    "value" : "1"
});

/* 28 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169841a").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2390,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 596,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916076017),
    "value" : "BACKLEFT6"
});

/* 29 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169841b").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2391,
    "code" : "impactShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 597,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916077142),
    "value" : "RMIDDLE"
});

/* 30 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169841c").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2392,
    "code" : "goalScored",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 598,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916078066),
    "value" : "1"
});

/* 31 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169841d").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2392,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 599,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916078068),
    "value" : "5"
});

/* 32 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169841e").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2421,
    "code" : "badPosition",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 600,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "d31d3550-479a-4ee9-8304-84a132202d89"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916106846),
    "value" : "1"
});

/* 33 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169841f").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2421,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 601,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916106847),
    "value" : "29"
});

/* 34 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698420").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2423,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 602,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916108842),
    "value" : "BACKRIGHT6"
});

/* 35 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698421").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2424,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 603,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916110156),
    "value" : "RDOWN"
});

/* 36 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698422").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2425,
    "code" : "goalConceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 604,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916111310),
    "value" : "1"
});

/* 37 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698423").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2425,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 605,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916111310),
    "value" : "4"
});

/* 38 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698424").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2439,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 606,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "d31d3550-479a-4ee9-8304-84a132202d89"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916125717),
    "value" : "substitue"
});

/* 39 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698425").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2439,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 607,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916125774),
    "value" : "center-backcourt"
});

/* 40 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698426").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2446,
    "code" : "penaltyObtained",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 608,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916132125),
    "value" : "1"
});

/* 41 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698427").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2455,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 609,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916141063),
    "value" : "CENTER6"
});

/* 42 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698428").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2456,
    "code" : "impactShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 610,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916142233),
    "value" : "LUP"
});

/* 43 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698429").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2457,
    "code" : "goalScored",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 611,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916143465),
    "value" : "1"
});

/* 44 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169842a").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2457,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 612,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916143465),
    "value" : "32"
});

/* 45 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169842b").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2481,
    "code" : "badPosition",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 613,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "1ce4591d-74a8-46e9-af80-d633f9344d27"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916167293),
    "value" : "1"
});

/* 46 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169842c").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2481,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 614,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916167295),
    "value" : "24"
});

/* 47 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169842d").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2491,
    "code" : "badPosition",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 615,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916177386),
    "value" : "1"
});

/* 48 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169842e").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2491,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 616,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916177386),
    "value" : "10"
});

/* 49 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169842f").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2493,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 617,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916179232),
    "value" : "BACKLEFT6"
});

/* 50 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698430").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2495,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 618,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916181630),
    "value" : "LDOWN"
});

/* 1 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698431").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2496,
    "code" : "goalConceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 619,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916182702),
    "value" : "1"
});

/* 2 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698432").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2496,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 620,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916182702),
    "value" : "5"
});

/* 3 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698433").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2532,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 621,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916218166),
    "value" : "CENTER9"
});

/* 4 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698434").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2534,
    "code" : "impactShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 622,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916220246),
    "value" : "LDOWN"
});

/* 5 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698435").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2535,
    "code" : "stopOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 623,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916221798),
    "value" : "1"
});

/* 6 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698436").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2535,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 624,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916221743),
    "value" : "38"
});

/* 7 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698437").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2535,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 625,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916221819),
    "value" : "0"
});

/* 8 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698438").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2555,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 626,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916241805),
    "value" : "0"
});

/* 9 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698439").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2586,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 627,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916290803),
    "value" : "30"
});

/* 10 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169843a").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2600,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 628,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916305677),
    "value" : "BACKRIGHT9"
});

/* 11 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169843b").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2603,
    "code" : "impactShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 629,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916308262),
    "value" : "HC"
});

/* 12 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169843c").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2603,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 630,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916308262),
    "value" : "17"
});

/* 13 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169843d").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2610,
    "code" : "neutralization",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 631,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916315629),
    "value" : "1"
});

/* 14 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169843e").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2610,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 632,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916315629),
    "value" : "7"
});

/* 15 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169843f").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2611,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 633,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916316506),
    "value" : "0"
});

/* 16 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698440").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2623,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 634,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916328210),
    "value" : "CENTER9"
});

/* 17 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698441").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2624,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 635,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916329634),
    "value" : "HC"
});

/* 18 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698442").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2624,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 636,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916329634),
    "value" : "13"
});

/* 19 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698443").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2643,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 637,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "1ce4591d-74a8-46e9-af80-d633f9344d27"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916348843),
    "value" : "BACKRIGHT6"
});

/* 20 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698444").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2646,
    "code" : "impactShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 638,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "1ce4591d-74a8-46e9-af80-d633f9344d27"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916350933),
    "value" : "LUP"
});

/* 21 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698445").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2647,
    "code" : "goalScored",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 639,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "1ce4591d-74a8-46e9-af80-d633f9344d27"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916351982),
    "value" : "1"
});

/* 22 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698446").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2647,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 640,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916351981),
    "value" : "23"
});

/* 23 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698447").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2680,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 641,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916385648),
    "value" : "BACKRIGHT6"
});

/* 24 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698448").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2688,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 642,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916393638),
    "value" : "RMIDDLE"
});

/* 25 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698449").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2689,
    "code" : "stopOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 643,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916394785),
    "value" : "1"
});

/* 26 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169844a").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2689,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 644,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916394746),
    "value" : "42"
});

/* 27 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169844b").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2696,
    "code" : "looseball",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 645,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916401327),
    "value" : "1"
});

/* 28 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169844c").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2696,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 646,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916401327),
    "value" : "7"
});

/* 29 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169844d").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2711,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 647,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916416338),
    "value" : "substitue"
});

/* 30 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169844e").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2711,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 648,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916416376),
    "value" : "left-backcourt"
});

/* 31 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169844f").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2715,
    "code" : "exclusionTempoObtained",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 649,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916419977),
    "value" : "1"
});

/* 32 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698450").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2715,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 650,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916419973),
    "value" : "19"
});

/* 33 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698451").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2718,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 651,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916423761),
    "value" : "CENTER6"
});

/* 34 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698452").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2719,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 652,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916424722),
    "value" : "LDOWN"
});

/* 35 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698453").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2721,
    "code" : "goalConceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 653,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916426354),
    "value" : "1"
});

/* 36 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698454").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2721,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 654,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916426353),
    "value" : "6"
});

/* 37 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698455").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2748,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 655,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916453281),
    "value" : "CENTER9"
});

/* 38 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698456").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2749,
    "code" : "impactShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 656,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916454602),
    "value" : "HC"
});

/* 39 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698457").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2749,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 657,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916454603),
    "value" : "28"
});

/* 40 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698458").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2759,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 658,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916464435),
    "value" : "CENTER9"
});

/* 41 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698459").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2760,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 659,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916465425),
    "value" : "POST"
});

/* 42 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169845a").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2772,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 660,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916477419),
    "value" : "CENTER6"
});

/* 43 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169845b").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2773,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 661,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916478382),
    "value" : "CDOWN"
});

/* 44 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169845c").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2774,
    "code" : "goalConceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 662,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916479440),
    "value" : "1"
});

/* 45 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169845d").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2774,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 663,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916479439),
    "value" : "25"
});

/* 46 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169845e").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2781,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 664,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916485967),
    "value" : "CENTER9"
});

/* 47 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169845f").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2782,
    "code" : "impactShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 665,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916487062),
    "value" : "RDOWN"
});

/* 48 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698460").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2783,
    "code" : "goalScored",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 666,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916488679),
    "value" : "1"
});

/* 49 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698461").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2783,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 667,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916488679),
    "value" : "9"
});

/* 50 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698462").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2790,
    "code" : "neutralization",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 668,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916495893),
    "value" : "1"
});

/* 1 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698463").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2790,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 669,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916495893),
    "value" : "7"
});

/* 2 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698464").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2791,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 670,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916496635),
    "value" : "0"
});

/* 3 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698465").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2808,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 671,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916513302),
    "value" : "substitue"
});

/* 4 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698466").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2808,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 672,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916513356),
    "value" : "left-wingman"
});

/* 5 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698467").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2811,
    "code" : "neutralization",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 673,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916516612),
    "value" : "1"
});

/* 6 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698468").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2811,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 674,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916516612),
    "value" : "20"
});

/* 7 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698469").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2855,
    "code" : "looseball",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 675,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916561481),
    "value" : "1"
});

/* 8 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169846a").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2855,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 676,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916561481),
    "value" : "44"
});

/* 9 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169846b").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2870,
    "code" : "exclusionTempoObtained",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 677,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916577268),
    "value" : "1"
});

/* 10 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169846c").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2870,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 678,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916577268),
    "value" : "15"
});

/* 11 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169846d").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2880,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 679,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916629436),
    "value" : "9"
});

/* 12 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169846e").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2880,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 680,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916630281),
    "value" : "0"
});

/* 13 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169846f").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2889,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 681,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916638760),
    "value" : "CENTER9"
});

/* 14 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698470").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2890,
    "code" : "impactShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 682,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916640050),
    "value" : "LDOWN"
});

/* 15 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698471").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2893,
    "code" : "stopOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 683,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916642543),
    "value" : "1"
});

/* 16 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698472").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2893,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 684,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916642490),
    "value" : "0"
});

/* 17 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698473").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2901,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 685,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916650688),
    "value" : "0"
});

/* 18 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698474").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2913,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 686,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916663186),
    "value" : "BACKRIGHT9"
});

/* 19 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698475").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2914,
    "code" : "impactShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 687,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916664220),
    "value" : "LDOWN"
});

/* 20 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698476").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2915,
    "code" : "goalScored",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 688,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916665165),
    "value" : "1"
});

/* 21 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698477").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2915,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 689,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916665167),
    "value" : "14"
});

/* 22 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698478").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2931,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 690,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916681054),
    "value" : "BACKRIGHT9"
});

/* 23 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698479").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2932,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 691,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916682086),
    "value" : "LMIDDLE"
});

/* 24 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169847a").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2933,
    "code" : "stopOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 692,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916683261),
    "value" : "1"
});

/* 25 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169847b").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2933,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 693,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916683216),
    "value" : "18"
});

/* 26 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169847c").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2950,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 694,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916699734),
    "value" : "BACKLEFT6"
});

/* 27 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169847d").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2951,
    "code" : "impactShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 695,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916700696),
    "value" : "LUP"
});

/* 28 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169847e").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2952,
    "code" : "goalScored",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 696,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916701609),
    "value" : "1"
});

/* 29 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169847f").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2952,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 697,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916701608),
    "value" : "19"
});

/* 30 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698480").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2968,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 698,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916717720),
    "value" : "RWING"
});

/* 31 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698481").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2969,
    "code" : "impactShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 699,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916719078),
    "value" : "RDOWN"
});

/* 32 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698482").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2970,
    "code" : "goalConceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 700,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916720482),
    "value" : "1"
});

/* 33 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698483").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2970,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 701,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916720482),
    "value" : "18"
});

/* 34 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698484").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2991,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 702,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916770366),
    "value" : "20"
});

/* 35 */
shootSeqId = new ObjectId().valueOf();
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698485").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2993,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 703,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916772873),
    "value" : "CENTER9"
});

/* 36 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698486").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2995,
    "code" : "impactShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 704,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916774856),
    "value" : "LUP"
});

/* 37 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698487").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2996,
    "code" : "stopOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 705,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : shootSeqId,
    "timer" : NumberLong(1446916776049),
    "value" : "1"
});

/* 38 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698488").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2996,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 706,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916775985),
    "value" : "5"
});

/* 39 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698489").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 2996,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 707,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916776071),
    "value" : "0"
});

/* 40 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169848a").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 3001,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 708,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916809221),
    "value" : "4"
});

/* 41 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169848b").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 3001,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 709,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916809253),
    "value" : 290
});

/* 42 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169848c").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 3001,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 710,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916809276),
    "value" : 816
});

/* 43 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169848d").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 3001,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 711,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c0bf7706-f039-46c3-86b2-460930696890"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916809294),
    "value" : 673
});

/* 44 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169848e").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 3001,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 712,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916809310),
    "value" : 290
});

/* 45 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169848f").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 3001,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 713,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916809329),
    "value" : 193
});

/* 46 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698490").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 3001,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 714,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916809345),
    "value" : 1148
});

/* 47 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698491").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 3001,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 715,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "1ce4591d-74a8-46e9-af80-d633f9344d27"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916809362),
    "value" : 673
});

/* 48 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698492").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 3001,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 716,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916809376),
    "value" : 642
});

/* 49 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698493").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 3001,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 717,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916809391),
    "value" : 642
});

/* 50 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698494").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 3001,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 718,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "d31d3550-479a-4ee9-8304-84a132202d89"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916809407),
    "value" : 562
});

/* 1 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698495").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 3001,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 719,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916809422),
    "value" : 193
});

/* 2 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698496").valueOf(),
    "activityId" : "ACT-HAND",
    "chrono" : 3001,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 720,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "59bb6da2-b9d2-4453-856d-52a0fb98eac1", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916809437),
    "value" : 562
});


/* V0.44 *//* V0.44 *//* V0.44 *//* V0.44 *//* V0.44 *//* V0.44 *//* V0.44 *//* V0.44 *//* V0.44 *//* V0.44 *//* V0.44 *//* V0.44 */
/* V0.44 *//* V0.44 *//* V0.44 *//* V0.44 *//* V0.44 *//* V0.44 *//* V0.44 *//* V0.44 *//* V0.44 *//* V0.44 *//* V0.44 *//* V0.44 */
/* V0.44 *//* V0.44 *//* V0.44 *//* V0.44 *//* V0.44 *//* V0.44 *//* V0.44 *//* V0.44 *//* V0.44 *//* V0.44 *//* V0.44 *//* V0.44 */
/* V0.44 *//* V0.44 *//* V0.44 *//* V0.44 *//* V0.44 *//* V0.44 *//* V0.44 *//* V0.44 *//* V0.44 *//* V0.44 *//* V0.44 *//* V0.44 */


/* 28 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb3609a").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : true,
    "chrono" : 6,
    "code" : "timeAttack",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 85,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a"
    ],
    "phaseSeqId" : "997183a3-a65b-490f-a8c9-15e7c3324c1d",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1448491870237),
    "value" : "0"
});

/* 29 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb3609b").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : true,
    "chrono" : 9,
    "code" : "passOk",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 86,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a", 
        "5f82c510-2c89-46b0-b87d-d3b59e748615"
    ],
    "phaseSeqId" : "9b983eff-511b-40bd-8d75-eced2399b2ed",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1448491872695),
    "value" : "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"
});

/* 30 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb3609c").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : true,
    "chrono" : 9,
    "code" : "passOk",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 87,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a", 
        "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"
    ],
    "phaseSeqId" : "9b983eff-511b-40bd-8d75-eced2399b2ed",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1448491873159),
    "value" : "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"
});

/* 31 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb3609d").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : true,
    "chrono" : 11,
    "code" : "passOk",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 88,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a", 
        "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"
    ],
    "phaseSeqId" : "9b983eff-511b-40bd-8d75-eced2399b2ed",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1448491875283),
    "value" : "5f82c510-2c89-46b0-b87d-d3b59e748615"
});

/* 32 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb3609e").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : true,
    "chrono" : 17,
    "code" : "originShootAtt",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 89,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a", 
        "5f82c510-2c89-46b0-b87d-d3b59e748615"
    ],
    "phaseSeqId" : "9b983eff-511b-40bd-8d75-eced2399b2ed",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : "d4edc59d-bd5b-469d-a649-bcd6922f6a28",
    "timer" : NumberLong(1448491880914),
    "value" : "BACKRIGHT9"
});

/* 33 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb3609f").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : true,
    "chrono" : 18,
    "code" : "impactShootAtt",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 90,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a", 
        "5f82c510-2c89-46b0-b87d-d3b59e748615"
    ],
    "phaseSeqId" : "9b983eff-511b-40bd-8d75-eced2399b2ed",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : "d4edc59d-bd5b-469d-a649-bcd6922f6a28",
    "timer" : NumberLong(1448491882233),
    "value" : "RMIDDLE"
});

/* 34 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360a0").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : false,
    "chrono" : 19,
    "code" : "stopOk",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 91,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a", 
        "5f82c510-2c89-46b0-b87d-d3b59e748615"
    ],
    "phaseSeqId" : "53dadaf9-6902-4630-80f0-5de237c4e1d3",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : "d4edc59d-bd5b-469d-a649-bcd6922f6a28",
    "timer" : NumberLong(1448491883443),
    "value" : "1"
});

/* 35 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360a1").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : false,
    "chrono" : 19,
    "code" : "timeDefense",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 92,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a"
    ],
    "phaseSeqId" : "53dadaf9-6902-4630-80f0-5de237c4e1d3",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : "d4edc59d-bd5b-469d-a649-bcd6922f6a28",
    "timer" : NumberLong(1448491883417),
    "value" : "0"
});

/* 36 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360a2").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : true,
    "chrono" : 25,
    "code" : "timeAttack",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 93,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a"
    ],
    "phaseSeqId" : "f5bc4fad-f873-4590-8471-9f467ccdbc91",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1448491889237),
    "value" : "0"
});

/* 37 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360a3").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : true,
    "chrono" : 27,
    "code" : "passOk",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 94,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a", 
        "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"
    ],
    "phaseSeqId" : "935428c7-9798-46a9-9387-29416b2142f5",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1448491891187),
    "value" : "07cfa115-6a8e-42b3-8a08-16ce1d7dbd89"
});

/* 38 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360a4").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : true,
    "chrono" : 30,
    "code" : "timeAttack",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 95,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a"
    ],
    "phaseSeqId" : "935428c7-9798-46a9-9387-29416b2142f5",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1448491898325),
    "value" : "5"
});

/* 39 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360a5").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : true,
    "chrono" : 32,
    "code" : "passOk",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 96,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a", 
        "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"
    ],
    "phaseSeqId" : "a3dc8dc4-1950-4ddc-aa8b-fca2fca618df",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1448491900479),
    "value" : "07cfa115-6a8e-42b3-8a08-16ce1d7dbd89"
});

/* 40 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360a6").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : true,
    "chrono" : 32,
    "code" : "passOk",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 97,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a", 
        "07cfa115-6a8e-42b3-8a08-16ce1d7dbd89"
    ],
    "phaseSeqId" : "a3dc8dc4-1950-4ddc-aa8b-fca2fca618df",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1448491901099),
    "value" : "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"
});

/* 41 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360a7").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : true,
    "chrono" : 33,
    "code" : "passOk",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 98,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a", 
        "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"
    ],
    "phaseSeqId" : "a3dc8dc4-1950-4ddc-aa8b-fca2fca618df",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1448491901592),
    "value" : "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"
});

/* 42 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360a8").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : true,
    "chrono" : 34,
    "code" : "passOk",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 99,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a", 
        "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"
    ],
    "phaseSeqId" : "a3dc8dc4-1950-4ddc-aa8b-fca2fca618df",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1448491902829),
    "value" : "5f82c510-2c89-46b0-b87d-d3b59e748615"
});

/* 43 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360a9").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : true,
    "chrono" : 39,
    "code" : "duelWon",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 100,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a", 
        "5f82c510-2c89-46b0-b87d-d3b59e748615"
    ],
    "phaseSeqId" : "a3dc8dc4-1950-4ddc-aa8b-fca2fca618df",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1448491907962),
    "value" : "1"
});

/* 44 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360aa").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : true,
    "chrono" : 42,
    "code" : "originShootAtt",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 101,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a", 
        "5f82c510-2c89-46b0-b87d-d3b59e748615"
    ],
    "phaseSeqId" : "a3dc8dc4-1950-4ddc-aa8b-fca2fca618df",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : "9e4c2618-907b-4db3-a954-0de9f7cc6965",
    "timer" : NumberLong(1448491910832),
    "value" : "BACKLEFT9"
});

/* 45 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360ab").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : true,
    "chrono" : 44,
    "code" : "impactShootAtt",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 102,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a", 
        "5f82c510-2c89-46b0-b87d-d3b59e748615"
    ],
    "phaseSeqId" : "a3dc8dc4-1950-4ddc-aa8b-fca2fca618df",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : "9e4c2618-907b-4db3-a954-0de9f7cc6965",
    "timer" : NumberLong(1448491912397),
    "value" : "LDOWN"
});

/* 46 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360ac").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : true,
    "chrono" : 45,
    "code" : "goalScored",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 103,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a", 
        "5f82c510-2c89-46b0-b87d-d3b59e748615"
    ],
    "phaseSeqId" : "a3dc8dc4-1950-4ddc-aa8b-fca2fca618df",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : "9e4c2618-907b-4db3-a954-0de9f7cc6965",
    "timer" : NumberLong(1448491913430),
    "value" : "1"
});

/* 47 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360ad").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : true,
    "chrono" : 45,
    "code" : "timeAttack",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 104,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a"
    ],
    "phaseSeqId" : "a3dc8dc4-1950-4ddc-aa8b-fca2fca618df",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : "9e4c2618-907b-4db3-a954-0de9f7cc6965",
    "timer" : NumberLong(1448491913430),
    "value" : "15"
});

/* 48 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360ae").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : false,
    "chrono" : 55,
    "code" : "duelLoose",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 105,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a", 
        "07cfa115-6a8e-42b3-8a08-16ce1d7dbd89"
    ],
    "phaseSeqId" : "96977246-318e-4ff6-b6e0-b24af79a5488",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1448491924113),
    "value" : "1"
});

/* 49 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360af").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : false,
    "chrono" : 55,
    "code" : "timeDefense",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 106,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a"
    ],
    "phaseSeqId" : "96977246-318e-4ff6-b6e0-b24af79a5488",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1448491924114),
    "value" : "10"
});

/* 50 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360b0").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : true,
    "chrono" : 59,
    "code" : "timeAttack",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 107,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a"
    ],
    "phaseSeqId" : "c1e56ba6-35ee-4258-94d5-1113e1e3726f",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1448491927744),
    "value" : "0"
});

/* 1 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360b1").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : true,
    "chrono" : 67,
    "code" : "originShootAtt",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 108,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a", 
        "5f82c510-2c89-46b0-b87d-d3b59e748615"
    ],
    "phaseSeqId" : "c31f3f0a-59a8-4a62-80e1-80fa899e5c6c",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : "7dd146bf-f956-43b2-9036-237c33dcd3b7",
    "timer" : NumberLong(1448491935995),
    "value" : "CENTER9"
});

/* 2 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360b2").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : true,
    "chrono" : 68,
    "code" : "impactShootAtt",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 109,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a", 
        "5f82c510-2c89-46b0-b87d-d3b59e748615"
    ],
    "phaseSeqId" : "c31f3f0a-59a8-4a62-80e1-80fa899e5c6c",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : "7dd146bf-f956-43b2-9036-237c33dcd3b7",
    "timer" : NumberLong(1448491937112),
    "value" : "RUP"
});

/* 3 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360b3").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : false,
    "chrono" : 69,
    "code" : "goalScored",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 110,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a", 
        "5f82c510-2c89-46b0-b87d-d3b59e748615"
    ],
    "phaseSeqId" : "c31f3f0a-59a8-4a62-80e1-80fa899e5c6c",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : "7dd146bf-f956-43b2-9036-237c33dcd3b7",
    "timer" : NumberLong(1448491938329),
    "value" : "1"
});

/* 4 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360b4").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : true,
    "chrono" : 69,
    "code" : "timeAttack",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 111,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a"
    ],
    "phaseSeqId" : "c31f3f0a-59a8-4a62-80e1-80fa899e5c6c",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : "7dd146bf-f956-43b2-9036-237c33dcd3b7",
    "timer" : NumberLong(1448491938327),
    "value" : "10"
});

/* 5 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360b5").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : false,
    "chrono" : 77,
    "code" : "yellowCard",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 112,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a", 
        "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"
    ],
    "phaseSeqId" : "407426cc-0ad5-41b9-a562-87b82ad46524",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1448491945912),
    "value" : "1"
});

/* 6 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360b6").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : false,
    "chrono" : 77,
    "code" : "timeDefense",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 113,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a"
    ],
    "phaseSeqId" : "407426cc-0ad5-41b9-a562-87b82ad46524",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1448491945915),
    "value" : "7"
});

/* 7 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360b7").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : false,
    "chrono" : 82,
    "code" : "timeDefense",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 114,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a"
    ],
    "phaseSeqId" : "03e862ae-35dd-4049-997b-ed480cfeb4bc",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1448491951341),
    "value" : "5"
});

/* 8 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360b8").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : true,
    "chrono" : 85,
    "code" : "passOk",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 115,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a", 
        "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"
    ],
    "phaseSeqId" : "a6b2d6b4-f351-4cac-8374-0f89f2ba1528",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1448491954059),
    "value" : "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"
});

/* 9 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360b9").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : true,
    "chrono" : 86,
    "code" : "passOk",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 116,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a", 
        "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"
    ],
    "phaseSeqId" : "a6b2d6b4-f351-4cac-8374-0f89f2ba1528",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1448491954720),
    "value" : "5f82c510-2c89-46b0-b87d-d3b59e748615"
});

/* 10 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360ba").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : true,
    "chrono" : 91,
    "code" : "originShootAtt",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 117,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a", 
        "5f82c510-2c89-46b0-b87d-d3b59e748615"
    ],
    "phaseSeqId" : "a6b2d6b4-f351-4cac-8374-0f89f2ba1528",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : "53f44839-7da8-4266-a7b5-fabca7581976",
    "timer" : NumberLong(1448491959741),
    "value" : "BACKLEFT9"
});

/* 11 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360bb").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : true,
    "chrono" : 92,
    "code" : "impactShootAtt",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 118,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a", 
        "5f82c510-2c89-46b0-b87d-d3b59e748615"
    ],
    "phaseSeqId" : "a6b2d6b4-f351-4cac-8374-0f89f2ba1528",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : "53f44839-7da8-4266-a7b5-fabca7581976",
    "timer" : NumberLong(1448491961388),
    "value" : "RUP"
});

/* 12 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360bc").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : true,
    "chrono" : 94,
    "code" : "goalScored",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 119,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a", 
        "5f82c510-2c89-46b0-b87d-d3b59e748615"
    ],
    "phaseSeqId" : "a6b2d6b4-f351-4cac-8374-0f89f2ba1528",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : "53f44839-7da8-4266-a7b5-fabca7581976",
    "timer" : NumberLong(1448491963165),
    "value" : "1"
});

/* 13 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360bd").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : true,
    "chrono" : 94,
    "code" : "timeAttack",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 120,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a"
    ],
    "phaseSeqId" : "a6b2d6b4-f351-4cac-8374-0f89f2ba1528",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : "53f44839-7da8-4266-a7b5-fabca7581976",
    "timer" : NumberLong(1448491963167),
    "value" : "11"
});

/* 14 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360be").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : false,
    "chrono" : 102,
    "code" : "yellowCard",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 121,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a", 
        "07cfa115-6a8e-42b3-8a08-16ce1d7dbd89"
    ],
    "phaseSeqId" : "3c459ee5-1441-49ba-b5eb-55ab8f045822",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1448491971015),
    "value" : "1"
});

/* 15 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360bf").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : false,
    "chrono" : 102,
    "code" : "timeDefense",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 122,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a"
    ],
    "phaseSeqId" : "3c459ee5-1441-49ba-b5eb-55ab8f045822",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1448491971015),
    "value" : "8"
});

/* 16 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360c0").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : true,
    "chrono" : 105,
    "code" : "timeAttack",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 123,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a"
    ],
    "phaseSeqId" : "031dabc9-b64d-45d9-841f-d905a8e27e59",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1448491973923),
    "value" : "0"
});

/* 17 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360c1").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : true,
    "chrono" : 108,
    "code" : "passOk",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 124,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a", 
        "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"
    ],
    "phaseSeqId" : "55869dba-baf7-41bf-98be-1ee408a21eed",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1448491976510),
    "value" : "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"
});

/* 18 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360c2").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : true,
    "chrono" : 108,
    "code" : "passOk",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 125,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a", 
        "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"
    ],
    "phaseSeqId" : "55869dba-baf7-41bf-98be-1ee408a21eed",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1448491977101),
    "value" : "5f82c510-2c89-46b0-b87d-d3b59e748615"
});

/* 19 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360c3").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : true,
    "chrono" : 113,
    "code" : "originShootAtt",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 126,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a", 
        "5f82c510-2c89-46b0-b87d-d3b59e748615"
    ],
    "phaseSeqId" : "55869dba-baf7-41bf-98be-1ee408a21eed",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : "08dfa8ea-6987-4c41-8780-1e86cb6a5cba",
    "timer" : NumberLong(1448491982353),
    "value" : "CENTER6"
});

/* 20 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360c4").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : true,
    "chrono" : 115,
    "code" : "impactShootAtt",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 127,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a", 
        "5f82c510-2c89-46b0-b87d-d3b59e748615"
    ],
    "phaseSeqId" : "55869dba-baf7-41bf-98be-1ee408a21eed",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : "08dfa8ea-6987-4c41-8780-1e86cb6a5cba",
    "timer" : NumberLong(1448491983824),
    "value" : "CDOWN"
});

/* 21 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360c5").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : true,
    "chrono" : 117,
    "code" : "goalScored",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 128,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a", 
        "5f82c510-2c89-46b0-b87d-d3b59e748615"
    ],
    "phaseSeqId" : "55869dba-baf7-41bf-98be-1ee408a21eed",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : "08dfa8ea-6987-4c41-8780-1e86cb6a5cba",
    "timer" : NumberLong(1448491985486),
    "value" : "1"
});

/* 22 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360c6").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : true,
    "chrono" : 117,
    "code" : "timeAttack",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 129,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a"
    ],
    "phaseSeqId" : "55869dba-baf7-41bf-98be-1ee408a21eed",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : "08dfa8ea-6987-4c41-8780-1e86cb6a5cba",
    "timer" : NumberLong(1448491985487),
    "value" : "12"
});

/* 23 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360c7").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : false,
    "chrono" : 129,
    "code" : "neutralization",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 130,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a", 
        "cc5d449a-d466-400c-b73f-955c676103ed"
    ],
    "phaseSeqId" : "70b4cb60-abae-4360-86cd-fc23d1f311e9",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1448491997512),
    "value" : "1"
});

/* 24 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360c8").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : false,
    "chrono" : 129,
    "code" : "timeDefense",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 131,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a"
    ],
    "phaseSeqId" : "70b4cb60-abae-4360-86cd-fc23d1f311e9",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1448491997515),
    "value" : "12"
});

/* 25 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360c9").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : true,
    "chrono" : 138,
    "code" : "penaltyObtained",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 132,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a", 
        "5f82c510-2c89-46b0-b87d-d3b59e748615"
    ],
    "phaseSeqId" : "2d7af06b-e5a2-40d1-a719-302cfbac15b4",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1448492006771),
    "value" : "1"
});

/* 26 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360ca").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : true,
    "chrono" : 142,
    "code" : "yellowCard",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 133,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a", 
        "5f82c510-2c89-46b0-b87d-d3b59e748615"
    ],
    "phaseSeqId" : "2d7af06b-e5a2-40d1-a719-302cfbac15b4",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1448492010579),
    "value" : "5f82c510-2c89-46b0-b87d-d3b59e748615"
});

/* 27 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360cb").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : true,
    "chrono" : 144,
    "code" : "originShootAtt",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 134,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a", 
        "5f82c510-2c89-46b0-b87d-d3b59e748615"
    ],
    "phaseSeqId" : "2d7af06b-e5a2-40d1-a719-302cfbac15b4",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : "7b63171b-b66a-4ed8-b80a-5f30a6872418",
    "timer" : NumberLong(1448492012488),
    "value" : "PENALTY"
});

/* 28 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360cc").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : true,
    "chrono" : 146,
    "code" : "impactShootAtt",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 135,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a", 
        "5f82c510-2c89-46b0-b87d-d3b59e748615"
    ],
    "phaseSeqId" : "2d7af06b-e5a2-40d1-a719-302cfbac15b4",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : "7b63171b-b66a-4ed8-b80a-5f30a6872418",
    "timer" : NumberLong(1448492014429),
    "value" : "LMIDDLE"
});

/* 29 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360cd").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : true,
    "chrono" : 146,
    "code" : "goalScored",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 136,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a", 
        "5f82c510-2c89-46b0-b87d-d3b59e748615"
    ],
    "phaseSeqId" : "2d7af06b-e5a2-40d1-a719-302cfbac15b4",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : "7b63171b-b66a-4ed8-b80a-5f30a6872418",
    "timer" : NumberLong(1448492015409),
    "value" : "1"
});

/* 30 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360ce").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : true,
    "chrono" : 146,
    "code" : "timeAttack",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 137,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a"
    ],
    "phaseSeqId" : "2d7af06b-e5a2-40d1-a719-302cfbac15b4",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "shootSeqId" : "7b63171b-b66a-4ed8-b80a-5f30a6872418",
    "timer" : NumberLong(1448492015411),
    "value" : "17"
});

/* 31 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360cf").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : false,
    "chrono" : 151,
    "code" : "timeDefense",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 138,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a"
    ],
    "phaseSeqId" : "a0723098-479b-4642-997a-8057c092d332",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1448492044876),
    "value" : "20"
});

/* 32 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360d0").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : false,
    "chrono" : 3001,
    "code" : "playTime",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 139,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a", 
        "07cfa115-6a8e-42b3-8a08-16ce1d7dbd89"
    ],
    "phaseSeqId" : "a0723098-479b-4642-997a-8057c092d332",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1448492045037),
    "value" : 1510
});

/* 33 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0ee4b0972ccfb360d1").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : false,
    "chrono" : 3001,
    "code" : "playTime",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 140,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a", 
        "26baf31a-f153-41b0-9e1d-c32cb9e859dd"
    ],
    "phaseSeqId" : "a0723098-479b-4642-997a-8057c092d332",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1448492045055),
    "value" : 1510
});

/* 34 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0fe4b0972ccfb360d2").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : false,
    "chrono" : 3001,
    "code" : "playTime",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 141,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a", 
        "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"
    ],
    "phaseSeqId" : "a0723098-479b-4642-997a-8057c092d332",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1448492045070),
    "value" : 1510
});

/* 35 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0fe4b0972ccfb360d3").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : false,
    "chrono" : 3001,
    "code" : "playTime",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 142,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a", 
        "5f82c510-2c89-46b0-b87d-d3b59e748615"
    ],
    "phaseSeqId" : "a0723098-479b-4642-997a-8057c092d332",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1448492045089),
    "value" : 1510
});

/* 36 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0fe4b0972ccfb360d4").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : false,
    "chrono" : 3001,
    "code" : "playTime",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 143,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a", 
        "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"
    ],
    "phaseSeqId" : "a0723098-479b-4642-997a-8057c092d332",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1448492045103),
    "value" : 1510
});

/* 37 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0fe4b0972ccfb360d5").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : false,
    "chrono" : 3001,
    "code" : "playTime",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 144,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a", 
        "cc5d449a-d466-400c-b73f-955c676103ed"
    ],
    "phaseSeqId" : "a0723098-479b-4642-997a-8057c092d332",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1448492045122),
    "value" : 1510
});

/* 38 */
db.SB_Stats.insert({
    "_id" : ObjectId("56563c0fe4b0972ccfb360d6").valueOf(),
    "activityId" : "ACT-HAND",
    "attack" : false,
    "chrono" : 3001,
    "code" : "playTime",
    "eventId" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "mRowid" : 145,
    "owner" : [ 
        "561ec20b409937a6b439d4e9", 
        "561ec4d0409937a6b439d4ea", "35d65151-2fe5-48e1-a219-8534412b6bca", 
        "937918db-848e-4a6d-8feb-a7ba6bd60f5a", 
        "ce18d73e-dedf-43e5-8e75-16e0375be349"
    ],
    "phaseSeqId" : "a0723098-479b-4642-997a-8057c092d332",
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1448492045135),
    "value" : 1510
});