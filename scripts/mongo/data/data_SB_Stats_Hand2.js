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
    "_id" : ObjectId("563f1c6ae4b09c0c5169823d"),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 119,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446911672294),
    "value" : "0"
});

/* 2 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169823e"),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 120,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446911898962),
    "value" : "goalkeeper"
});

/* 3 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169823f"),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 121,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446911921162),
    "value" : "left-wingman"
});

/* 4 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698240"),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 122,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912039019),
    "value" : "left-backcourt"
});

/* 5 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698241"),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 123,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c0bf7706-f039-46c3-86b2-460930696890"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912046460),
    "value" : "right-wingman"
});

/* 6 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698242"),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 124,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912050443),
    "value" : "center-backcourt"
});

/* 7 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698243"),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 125,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912055800),
    "value" : "pivot"
});

/* 8 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698244"),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 126,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912062651),
    "value" : "right-backcourt"
});

/* 9 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698245"),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "substitue",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 127,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "1ce4591d-74a8-46e9-af80-d633f9344d27"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912450096),
    "value" : "1"
});

/* 10 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698246"),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "substitue",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 128,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912450233),
    "value" : "1"
});

/* 11 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698247"),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "holder",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 129,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912450251),
    "value" : "1"
});

/* 12 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698248"),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "holder",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 130,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912450268),
    "value" : "1"
});

/* 13 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698249"),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "substitue",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 131,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912450288),
    "value" : "1"
});

/* 14 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169824a"),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "holder",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 132,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912450308),
    "value" : "1"
});

/* 15 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169824b"),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "holder",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 133,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c0bf7706-f039-46c3-86b2-460930696890"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912450328),
    "value" : "1"
});

/* 16 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169824c"),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "holder",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 134,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912450348),
    "value" : "1"
});

/* 17 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169824d"),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "substitue",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 135,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912450364),
    "value" : "1"
});

/* 18 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169824e"),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "holder",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 136,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912450380),
    "value" : "1"
});

/* 19 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169824f"),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "holder",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 137,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912450398),
    "value" : "1"
});

/* 20 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698250"),
    "activityId" : "ACT-HAND",
    "chrono" : 0,
    "code" : "substitue",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 138,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "d31d3550-479a-4ee9-8304-84a132202d89"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912450415),
    "value" : "1"
});

/* 21 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698251"),
    "activityId" : "ACT-HAND",
    "chrono" : 28,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 139,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912540376),
    "value" : "0"
});

/* 22 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698252"),
    "activityId" : "ACT-HAND",
    "chrono" : 29,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 140,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912541254),
    "value" : "1"
});

/* 23 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698253"),
    "activityId" : "ACT-HAND",
    "chrono" : 31,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 141,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912543410),
    "value" : "top-left"
});

/* 24 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698254"),
    "activityId" : "ACT-HAND",
    "chrono" : 34,
    "code" : "shootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 142,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912546133),
    "value" : "LDOWN"
});

/* 25 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698255"),
    "activityId" : "ACT-HAND",
    "chrono" : 35,
    "code" : "stopOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 143,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912547136),
    "value" : "1"
});

/* 26 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698256"),
    "activityId" : "ACT-HAND",
    "chrono" : 35,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 144,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912547097),
    "value" : "6"
});

/* 27 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698257"),
    "activityId" : "ACT-HAND",
    "chrono" : 60,
    "code" : "looseball",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 145,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912572204),
    "value" : "1"
});

/* 28 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698258"),
    "activityId" : "ACT-HAND",
    "chrono" : 60,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 146,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912572203),
    "value" : "25"
});

/* 29 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698259"),
    "activityId" : "ACT-HAND",
    "chrono" : 62,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 147,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912574664),
    "value" : "top-left"
});

/* 30 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169825a"),
    "activityId" : "ACT-HAND",
    "chrono" : 64,
    "code" : "shootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 148,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912576409),
    "value" : "LDOWN"
});

/* 31 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169825b"),
    "activityId" : "ACT-HAND",
    "chrono" : 65,
    "code" : "goalconceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 149,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912577593),
    "value" : "1"
});

/* 32 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169825c"),
    "activityId" : "ACT-HAND",
    "chrono" : 65,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 150,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912577593),
    "value" : "5"
});

/* 33 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169825d"),
    "activityId" : "ACT-HAND",
    "chrono" : 87,
    "code" : "passDec",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 151,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912599217),
    "value" : "1"
});

/* 34 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169825e"),
    "activityId" : "ACT-HAND",
    "chrono" : 93,
    "code" : "passOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 152,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912605600),
    "value" : "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"
});

/* 35 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169825f"),
    "activityId" : "ACT-HAND",
    "chrono" : 94,
    "code" : "duelWon",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 153,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912606989),
    "value" : "1"
});

/* 36 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698260"),
    "activityId" : "ACT-HAND",
    "chrono" : 96,
    "code" : "passOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 154,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912608540),
    "value" : "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"
});

/* 37 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698261"),
    "activityId" : "ACT-HAND",
    "chrono" : 98,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 155,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912610402),
    "value" : "top-center"
});

/* 38 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698262"),
    "activityId" : "ACT-HAND",
    "chrono" : 99,
    "code" : "shootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 156,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912611418),
    "value" : "RDOWN"
});

/* 39 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698263"),
    "activityId" : "ACT-HAND",
    "chrono" : 100,
    "code" : "goalScored",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 157,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912612345),
    "value" : "1"
});

/* 40 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698264"),
    "activityId" : "ACT-HAND",
    "chrono" : 100,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 158,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912612345),
    "value" : "35"
});

/* 41 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698265"),
    "activityId" : "ACT-HAND",
    "chrono" : 130,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 159,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912642793),
    "value" : "bottom-center"
});

/* 42 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698266"),
    "activityId" : "ACT-HAND",
    "chrono" : 132,
    "code" : "shootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 160,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912644222),
    "value" : "LDOWN"
});

/* 43 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698267"),
    "activityId" : "ACT-HAND",
    "chrono" : 133,
    "code" : "stopOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 161,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912645234),
    "value" : "1"
});

/* 44 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698268"),
    "activityId" : "ACT-HAND",
    "chrono" : 133,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 162,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912645211),
    "value" : "33"
});

/* 45 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698269"),
    "activityId" : "ACT-HAND",
    "chrono" : 168,
    "code" : "zone",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 163,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912680374),
    "value" : "1"
});

/* 46 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169826a"),
    "activityId" : "ACT-HAND",
    "chrono" : 168,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 164,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912680378),
    "value" : "35"
});

/* 47 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169826b"),
    "activityId" : "ACT-HAND",
    "chrono" : 188,
    "code" : "neutralization",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 165,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912700730),
    "value" : "1"
});

/* 48 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169826c"),
    "activityId" : "ACT-HAND",
    "chrono" : 188,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 166,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912700730),
    "value" : "20"
});

/* 49 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169826d"),
    "activityId" : "ACT-HAND",
    "chrono" : 189,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 167,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912701909),
    "value" : "1"
});

/* 50 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169826e"),
    "activityId" : "ACT-HAND",
    "chrono" : 206,
    "code" : "badPosition",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 168,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c0bf7706-f039-46c3-86b2-460930696890"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912718402),
    "value" : "1"
});

/* 1 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169826f"),
    "activityId" : "ACT-HAND",
    "chrono" : 206,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 169,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912718401),
    "value" : "17"
});

/* 2 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698270"),
    "activityId" : "ACT-HAND",
    "chrono" : 208,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 170,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912720487),
    "value" : "corner-left"
});

/* 3 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698271"),
    "activityId" : "ACT-HAND",
    "chrono" : 209,
    "code" : "shootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 171,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912721561),
    "value" : "RDOWN"
});

/* 4 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698272"),
    "activityId" : "ACT-HAND",
    "chrono" : 210,
    "code" : "goalconceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 172,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912722476),
    "value" : "1"
});

/* 5 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698273"),
    "activityId" : "ACT-HAND",
    "chrono" : 210,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 173,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912722475),
    "value" : "4"
});

/* 6 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698274"),
    "activityId" : "ACT-HAND",
    "chrono" : 215,
    "code" : "looseball",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 174,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c0bf7706-f039-46c3-86b2-460930696890"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912727997),
    "value" : "1"
});

/* 7 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698275"),
    "activityId" : "ACT-HAND",
    "chrono" : 215,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 175,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912727997),
    "value" : "5"
});

/* 8 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698276"),
    "activityId" : "ACT-HAND",
    "chrono" : 219,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 176,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912731408),
    "value" : "top-left"
});

/* 9 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698277"),
    "activityId" : "ACT-HAND",
    "chrono" : 221,
    "code" : "shootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 177,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912733316),
    "value" : "RDOWN"
});

/* 10 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698278"),
    "activityId" : "ACT-HAND",
    "chrono" : 223,
    "code" : "goalconceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 178,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912735529),
    "value" : "1"
});

/* 11 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698279"),
    "activityId" : "ACT-HAND",
    "chrono" : 223,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 179,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912735532),
    "value" : "8"
});

/* 12 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169827a"),
    "activityId" : "ACT-HAND",
    "chrono" : 259,
    "code" : "shift",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 180,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912772579),
    "value" : "1"
});

/* 13 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169827b"),
    "activityId" : "ACT-HAND",
    "chrono" : 260,
    "code" : "passOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 181,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912773960),
    "value" : "cc5d449a-d466-400c-b73f-955c676103ed"
});

/* 14 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169827c"),
    "activityId" : "ACT-HAND",
    "chrono" : 262,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 182,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912776391),
    "value" : "bottom-left"
});

/* 15 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169827d"),
    "activityId" : "ACT-HAND",
    "chrono" : 264,
    "code" : "shootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 183,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912777717),
    "value" : "RDOWN"
});

/* 16 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169827e"),
    "activityId" : "ACT-HAND",
    "chrono" : 265,
    "code" : "goalScored",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 184,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912779071),
    "value" : "1"
});

/* 17 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169827f"),
    "activityId" : "ACT-HAND",
    "chrono" : 265,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 185,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912779070),
    "value" : "42"
});

/* 18 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698280"),
    "activityId" : "ACT-HAND",
    "chrono" : 284,
    "code" : "interceptionOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 186,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912798147),
    "value" : "1"
});

/* 19 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698281"),
    "activityId" : "ACT-HAND",
    "chrono" : 284,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 187,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912798147),
    "value" : "19"
});

/* 20 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698282"),
    "activityId" : "ACT-HAND",
    "chrono" : 290,
    "code" : "passDec",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 188,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912803844),
    "value" : "1"
});

/* 21 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698283"),
    "activityId" : "ACT-HAND",
    "chrono" : 291,
    "code" : "passOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 189,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912805003),
    "value" : "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"
});

/* 22 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698284"),
    "activityId" : "ACT-HAND",
    "chrono" : 292,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 190,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912806433),
    "value" : "top-left"
});

/* 23 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698285"),
    "activityId" : "ACT-HAND",
    "chrono" : 294,
    "code" : "shootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 191,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912808187),
    "value" : "RDOWN"
});

/* 24 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698286"),
    "activityId" : "ACT-HAND",
    "chrono" : 295,
    "code" : "goalScored",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 192,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912809206),
    "value" : "1"
});

/* 25 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698287"),
    "activityId" : "ACT-HAND",
    "chrono" : 295,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 193,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912809206),
    "value" : "11"
});

/* 26 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698288"),
    "activityId" : "ACT-HAND",
    "chrono" : 318,
    "code" : "duelLoose",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 194,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912831855),
    "value" : "1"
});

/* 27 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698289"),
    "activityId" : "ACT-HAND",
    "chrono" : 318,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 195,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912831855),
    "value" : "23"
});

/* 28 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169828a"),
    "activityId" : "ACT-HAND",
    "chrono" : 320,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 196,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912834151),
    "value" : "top-center"
});

/* 29 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169828b"),
    "activityId" : "ACT-HAND",
    "chrono" : 322,
    "code" : "shootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 197,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912836117),
    "value" : "RMIDDLE"
});

/* 30 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169828c"),
    "activityId" : "ACT-HAND",
    "chrono" : 323,
    "code" : "goalconceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 198,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912837324),
    "value" : "1"
});

/* 31 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169828d"),
    "activityId" : "ACT-HAND",
    "chrono" : 323,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 199,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912837324),
    "value" : "5"
});

/* 32 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169828e"),
    "activityId" : "ACT-HAND",
    "chrono" : 338,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 200,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912852006),
    "value" : "corner-left"
});

/* 33 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169828f"),
    "activityId" : "ACT-HAND",
    "chrono" : 339,
    "code" : "outside",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 201,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912853310),
    "value" : "outside-right"
});

/* 34 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698290"),
    "activityId" : "ACT-HAND",
    "chrono" : 339,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 202,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912853310),
    "value" : "16"
});

/* 35 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698291"),
    "activityId" : "ACT-HAND",
    "chrono" : 354,
    "code" : "neutralization",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 203,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912867624),
    "value" : "1"
});

/* 36 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698292"),
    "activityId" : "ACT-HAND",
    "chrono" : 354,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 204,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912867623),
    "value" : "15"
});

/* 37 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698293"),
    "activityId" : "ACT-HAND",
    "chrono" : 366,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 205,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912879743),
    "value" : "0"
});

/* 38 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698294"),
    "activityId" : "ACT-HAND",
    "chrono" : 368,
    "code" : "neutralization",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 206,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912882181),
    "value" : "1"
});

/* 39 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698295"),
    "activityId" : "ACT-HAND",
    "chrono" : 368,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 207,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912882181),
    "value" : "2"
});

/* 40 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698296"),
    "activityId" : "ACT-HAND",
    "chrono" : 369,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 208,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912882965),
    "value" : "0"
});

/* 41 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698297"),
    "activityId" : "ACT-HAND",
    "chrono" : 374,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 209,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912888467),
    "value" : "top-center"
});

/* 42 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698298"),
    "activityId" : "ACT-HAND",
    "chrono" : 375,
    "code" : "shootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 210,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912889575),
    "value" : "LDOWN"
});

/* 43 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698299"),
    "activityId" : "ACT-HAND",
    "chrono" : 377,
    "code" : "goalconceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 211,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912890667),
    "value" : "1"
});

/* 44 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169829a"),
    "activityId" : "ACT-HAND",
    "chrono" : 377,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 212,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912890666),
    "value" : "8"
});

/* 45 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169829b"),
    "activityId" : "ACT-HAND",
    "chrono" : 384,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 213,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912897973),
    "value" : "top-center"
});

/* 46 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169829c"),
    "activityId" : "ACT-HAND",
    "chrono" : 386,
    "code" : "pole",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 214,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912900360),
    "value" : "top-pole"
});

/* 47 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169829d"),
    "activityId" : "ACT-HAND",
    "chrono" : 421,
    "code" : "passOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 215,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912934681),
    "value" : "5f82c510-2c89-46b0-b87d-d3b59e748615"
});

/* 48 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169829e"),
    "activityId" : "ACT-HAND",
    "chrono" : 424,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 216,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912938049),
    "value" : "47"
});

/* 49 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169829f"),
    "activityId" : "ACT-HAND",
    "chrono" : 428,
    "code" : "interceptionKo",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 217,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912942404),
    "value" : "1"
});

/* 50 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982a0"),
    "activityId" : "ACT-HAND",
    "chrono" : 428,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 218,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912942403),
    "value" : "4"
});

/* 1 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982a1"),
    "activityId" : "ACT-HAND",
    "chrono" : 433,
    "code" : "interceptionOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 219,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912947392),
    "value" : "1"
});

/* 2 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982a2"),
    "activityId" : "ACT-HAND",
    "chrono" : 433,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 220,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912947389),
    "value" : "5"
});

/* 3 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982a3"),
    "activityId" : "ACT-HAND",
    "chrono" : 444,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 221,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912958114),
    "value" : "bottom-right"
});

/* 4 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982a4"),
    "activityId" : "ACT-HAND",
    "chrono" : 446,
    "code" : "shootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 222,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912959858),
    "value" : "LUP"
});

/* 5 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982a5"),
    "activityId" : "ACT-HAND",
    "chrono" : 447,
    "code" : "stopOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 223,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912960969),
    "value" : "1"
});

/* 6 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982a6"),
    "activityId" : "ACT-HAND",
    "chrono" : 447,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 224,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912960916),
    "value" : "14"
});

/* 7 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982a7"),
    "activityId" : "ACT-HAND",
    "chrono" : 447,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 225,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912960987),
    "value" : "0"
});

/* 8 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982a8"),
    "activityId" : "ACT-HAND",
    "chrono" : 482,
    "code" : "neutralization",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 226,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912997193),
    "value" : "1"
});

/* 9 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982a9"),
    "activityId" : "ACT-HAND",
    "chrono" : 482,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 227,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446912997196),
    "value" : "35"
});

/* 10 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982aa"),
    "activityId" : "ACT-HAND",
    "chrono" : 501,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 228,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913016334),
    "value" : "bottom-center"
});

/* 11 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982ab"),
    "activityId" : "ACT-HAND",
    "chrono" : 503,
    "code" : "pole",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 229,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913017583),
    "value" : "top-pole"
});

/* 12 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982ac"),
    "activityId" : "ACT-HAND",
    "chrono" : 519,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 230,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913034363),
    "value" : "0"
});

/* 13 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982ad"),
    "activityId" : "ACT-HAND",
    "chrono" : 522,
    "code" : "neutralization",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 231,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913037054),
    "value" : "1"
});

/* 14 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982ae"),
    "activityId" : "ACT-HAND",
    "chrono" : 522,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 232,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913037053),
    "value" : "2"
});

/* 15 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982af"),
    "activityId" : "ACT-HAND",
    "chrono" : 525,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 233,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913040271),
    "value" : "0"
});

/* 16 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982b0"),
    "activityId" : "ACT-HAND",
    "chrono" : 535,
    "code" : "badPosition",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 234,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913050389),
    "value" : "1"
});

/* 17 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982b1"),
    "activityId" : "ACT-HAND",
    "chrono" : 535,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 235,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913050390),
    "value" : "10"
});

/* 18 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982b2"),
    "activityId" : "ACT-HAND",
    "chrono" : 537,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 236,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913051920),
    "value" : "top-right"
});

/* 19 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982b3"),
    "activityId" : "ACT-HAND",
    "chrono" : 538,
    "code" : "shootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 237,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913052903),
    "value" : "LUP"
});

/* 20 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982b4"),
    "activityId" : "ACT-HAND",
    "chrono" : 539,
    "code" : "goalconceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 238,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913053903),
    "value" : "1"
});

/* 21 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982b5"),
    "activityId" : "ACT-HAND",
    "chrono" : 539,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 239,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913053903),
    "value" : "3"
});

/* 22 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982b6"),
    "activityId" : "ACT-HAND",
    "chrono" : 546,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 240,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913061248),
    "value" : "corner-left"
});

/* 23 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982b7"),
    "activityId" : "ACT-HAND",
    "chrono" : 548,
    "code" : "shootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 241,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913063453),
    "value" : "LDOWN"
});

/* 24 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982b8"),
    "activityId" : "ACT-HAND",
    "chrono" : 550,
    "code" : "stopOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 242,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913064778),
    "value" : "1"
});

/* 25 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982b9"),
    "activityId" : "ACT-HAND",
    "chrono" : 550,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 243,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913064731),
    "value" : "0"
});

/* 26 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982ba"),
    "activityId" : "ACT-HAND",
    "chrono" : 570,
    "code" : "neutralization",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 244,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913085178),
    "value" : "1"
});

/* 27 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982bb"),
    "activityId" : "ACT-HAND",
    "chrono" : 570,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 245,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913085179),
    "value" : "20"
});

/* 28 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982bc"),
    "activityId" : "ACT-HAND",
    "chrono" : 572,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 246,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913086788),
    "value" : "0"
});

/* 29 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982bd"),
    "activityId" : "ACT-HAND",
    "chrono" : 574,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 247,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913088508),
    "value" : "bottom-left"
});

/* 30 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982be"),
    "activityId" : "ACT-HAND",
    "chrono" : 575,
    "code" : "pole",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 248,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913089760),
    "value" : "top-pole"
});

/* 31 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982bf"),
    "activityId" : "ACT-HAND",
    "chrono" : 589,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 249,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913104066),
    "value" : "substitue"
});

/* 32 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982c0"),
    "activityId" : "ACT-HAND",
    "chrono" : 589,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 250,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913104144),
    "value" : "right-backcourt"
});

/* 33 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982c1"),
    "activityId" : "ACT-HAND",
    "chrono" : 600,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 251,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913114831),
    "value" : "600"
});

/* 34 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982c2"),
    "activityId" : "ACT-HAND",
    "chrono" : 603,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 252,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913117524),
    "value" : "left-backcourt"
});

/* 35 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982c3"),
    "activityId" : "ACT-HAND",
    "chrono" : 609,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 253,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913123624),
    "value" : "right-backcourt"
});

/* 36 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982c4"),
    "activityId" : "ACT-HAND",
    "chrono" : 616,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 254,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c0bf7706-f039-46c3-86b2-460930696890"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913130620),
    "value" : "substitue"
});

/* 37 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982c5"),
    "activityId" : "ACT-HAND",
    "chrono" : 616,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 255,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "1ce4591d-74a8-46e9-af80-d633f9344d27"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913130660),
    "value" : "right-wingman"
});

/* 38 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982c6"),
    "activityId" : "ACT-HAND",
    "chrono" : 622,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 256,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913136955),
    "value" : "50"
});

/* 39 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982c7"),
    "activityId" : "ACT-HAND",
    "chrono" : 626,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 257,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "1ce4591d-74a8-46e9-af80-d633f9344d27"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913140863),
    "value" : "corner-right"
});

/* 40 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982c8"),
    "activityId" : "ACT-HAND",
    "chrono" : 628,
    "code" : "shootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 258,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "1ce4591d-74a8-46e9-af80-d633f9344d27"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913142749),
    "value" : "LUP"
});

/* 41 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982c9"),
    "activityId" : "ACT-HAND",
    "chrono" : 629,
    "code" : "stopOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 259,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913143934),
    "value" : "1"
});

/* 42 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982ca"),
    "activityId" : "ACT-HAND",
    "chrono" : 629,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 260,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913143885),
    "value" : "7"
});

/* 43 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982cb"),
    "activityId" : "ACT-HAND",
    "chrono" : 629,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 261,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913143961),
    "value" : "0"
});

/* 44 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982cc"),
    "activityId" : "ACT-HAND",
    "chrono" : 636,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 262,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913150550),
    "value" : "top-left"
});

/* 45 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982cd"),
    "activityId" : "ACT-HAND",
    "chrono" : 637,
    "code" : "shootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 263,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913151945),
    "value" : "LUP"
});

/* 46 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982ce"),
    "activityId" : "ACT-HAND",
    "chrono" : 638,
    "code" : "goalconceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 264,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913152956),
    "value" : "1"
});

/* 47 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982cf"),
    "activityId" : "ACT-HAND",
    "chrono" : 638,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 265,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913152956),
    "value" : "9"
});

/* 48 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982d0"),
    "activityId" : "ACT-HAND",
    "chrono" : 664,
    "code" : "looseball",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 266,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "1ce4591d-74a8-46e9-af80-d633f9344d27"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913179107),
    "value" : "1"
});

/* 49 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982d1"),
    "activityId" : "ACT-HAND",
    "chrono" : 664,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 267,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913179107),
    "value" : "26"
});

/* 50 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982d2"),
    "activityId" : "ACT-HAND",
    "chrono" : 668,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 268,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913183374),
    "value" : "top-center"
});

/* 1 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982d3"),
    "activityId" : "ACT-HAND",
    "chrono" : 670,
    "code" : "shootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 269,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913184600),
    "value" : "LDOWN"
});

/* 2 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982d4"),
    "activityId" : "ACT-HAND",
    "chrono" : 671,
    "code" : "goalconceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 270,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913185683),
    "value" : "1"
});

/* 3 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982d5"),
    "activityId" : "ACT-HAND",
    "chrono" : 671,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 271,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913185683),
    "value" : "7"
});

/* 4 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982d6"),
    "activityId" : "ACT-HAND",
    "chrono" : 688,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 272,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913203428),
    "value" : "top-center"
});

/* 5 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982d7"),
    "activityId" : "ACT-HAND",
    "chrono" : 692,
    "code" : "shootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 273,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913207426),
    "value" : "CDOWN"
});

/* 6 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982d8"),
    "activityId" : "ACT-HAND",
    "chrono" : 693,
    "code" : "goalScored",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 274,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913208381),
    "value" : "1"
});

/* 7 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982d9"),
    "activityId" : "ACT-HAND",
    "chrono" : 693,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 275,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913208384),
    "value" : "22"
});

/* 8 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982da"),
    "activityId" : "ACT-HAND",
    "chrono" : 708,
    "code" : "timeoutUs",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 276,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "Avenir Du Ponant 1"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913223138),
    "value" : "1"
});

/* 9 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982db"),
    "activityId" : "ACT-HAND",
    "chrono" : 717,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 277,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913293990),
    "value" : "0"
});

/* 10 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982dc"),
    "activityId" : "ACT-HAND",
    "chrono" : 719,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 278,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913295916),
    "value" : "0"
});

/* 11 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982dd"),
    "activityId" : "ACT-HAND",
    "chrono" : 722,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 279,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913298354),
    "value" : "top-left"
});

/* 12 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982de"),
    "activityId" : "ACT-HAND",
    "chrono" : 723,
    "code" : "outside",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 280,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913299937),
    "value" : "outside-left"
});

/* 13 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982df"),
    "activityId" : "ACT-HAND",
    "chrono" : 723,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 281,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913299937),
    "value" : "4"
});

/* 14 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982e0"),
    "activityId" : "ACT-HAND",
    "chrono" : 737,
    "code" : "looseball",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 282,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913313893),
    "value" : "1"
});

/* 15 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982e1"),
    "activityId" : "ACT-HAND",
    "chrono" : 737,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 283,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913313891),
    "value" : "14"
});

/* 16 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982e2"),
    "activityId" : "ACT-HAND",
    "chrono" : 743,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 284,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913319327),
    "value" : "0"
});

/* 17 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982e3"),
    "activityId" : "ACT-HAND",
    "chrono" : 753,
    "code" : "forceAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 285,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913329783),
    "value" : "1"
});

/* 18 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982e4"),
    "activityId" : "ACT-HAND",
    "chrono" : 753,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 286,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913329782),
    "value" : "10"
});

/* 19 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982e5"),
    "activityId" : "ACT-HAND",
    "chrono" : 783,
    "code" : "neutralization",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 287,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913359106),
    "value" : "1"
});

/* 20 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982e6"),
    "activityId" : "ACT-HAND",
    "chrono" : 783,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 288,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913359106),
    "value" : "30"
});

/* 21 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982e7"),
    "activityId" : "ACT-HAND",
    "chrono" : 800,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 289,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913376111),
    "value" : "0"
});

/* 22 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982e8"),
    "activityId" : "ACT-HAND",
    "chrono" : 819,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 290,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913395472),
    "value" : "bottom-left"
});

/* 23 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982e9"),
    "activityId" : "ACT-HAND",
    "chrono" : 820,
    "code" : "shootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 291,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913396598),
    "value" : "RMIDDLE"
});

/* 24 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982ea"),
    "activityId" : "ACT-HAND",
    "chrono" : 822,
    "code" : "stopOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 292,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913398176),
    "value" : "1"
});

/* 25 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982eb"),
    "activityId" : "ACT-HAND",
    "chrono" : 822,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 293,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913398131),
    "value" : "22"
});

/* 26 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982ec"),
    "activityId" : "ACT-HAND",
    "chrono" : 839,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 294,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913415285),
    "value" : "bottom-center"
});

/* 27 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982ed"),
    "activityId" : "ACT-HAND",
    "chrono" : 840,
    "code" : "shootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 295,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913416269),
    "value" : "LDOWN"
});

/* 28 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982ee"),
    "activityId" : "ACT-HAND",
    "chrono" : 841,
    "code" : "stopOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 296,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913417472),
    "value" : "1"
});

/* 29 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982ef"),
    "activityId" : "ACT-HAND",
    "chrono" : 841,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 297,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913417419),
    "value" : "19"
});

/* 30 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982f0"),
    "activityId" : "ACT-HAND",
    "chrono" : 841,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 298,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913417500),
    "value" : "0"
});

/* 31 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982f1"),
    "activityId" : "ACT-HAND",
    "chrono" : 842,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 299,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913418416),
    "value" : "0"
});

/* 32 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982f2"),
    "activityId" : "ACT-HAND",
    "chrono" : 862,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 300,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913438452),
    "value" : "substitue"
});

/* 33 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982f3"),
    "activityId" : "ACT-HAND",
    "chrono" : 862,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 301,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913438515),
    "value" : "left-wingman"
});

/* 34 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982f4"),
    "activityId" : "ACT-HAND",
    "chrono" : 872,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 302,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913448391),
    "value" : "corner-left"
});

/* 35 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982f5"),
    "activityId" : "ACT-HAND",
    "chrono" : 873,
    "code" : "shootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 303,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913449679),
    "value" : "RDOWN"
});

/* 36 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982f6"),
    "activityId" : "ACT-HAND",
    "chrono" : 874,
    "code" : "stopOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 304,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913450903),
    "value" : "1"
});

/* 37 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982f7"),
    "activityId" : "ACT-HAND",
    "chrono" : 874,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 305,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913450862),
    "value" : "0"
});

/* 38 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982f8"),
    "activityId" : "ACT-HAND",
    "chrono" : 895,
    "code" : "neutralization",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 306,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913471894),
    "value" : "1"
});

/* 39 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982f9"),
    "activityId" : "ACT-HAND",
    "chrono" : 895,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 307,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913471895),
    "value" : "21"
});

/* 40 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982fa"),
    "activityId" : "ACT-HAND",
    "chrono" : 896,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 308,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913472573),
    "value" : "1"
});

/* 41 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982fb"),
    "activityId" : "ACT-HAND",
    "chrono" : 899,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 309,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913475819),
    "value" : "top-center"
});

/* 42 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982fc"),
    "activityId" : "ACT-HAND",
    "chrono" : 901,
    "code" : "shootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 310,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913477715),
    "value" : "LUP"
});

/* 43 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982fd"),
    "activityId" : "ACT-HAND",
    "chrono" : 902,
    "code" : "goalconceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 311,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913478746),
    "value" : "1"
});

/* 44 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982fe"),
    "activityId" : "ACT-HAND",
    "chrono" : 902,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 312,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913478744),
    "value" : "6"
});

/* 45 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c516982ff"),
    "activityId" : "ACT-HAND",
    "chrono" : 915,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 313,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913492122),
    "value" : "substitue"
});

/* 46 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698300"),
    "activityId" : "ACT-HAND",
    "chrono" : 916,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 314,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "d31d3550-479a-4ee9-8304-84a132202d89"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913492169),
    "value" : "pivot"
});

/* 47 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698301"),
    "activityId" : "ACT-HAND",
    "chrono" : 934,
    "code" : "exclusionTempoObtained",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 315,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913510240),
    "value" : "1"
});

/* 48 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698302"),
    "activityId" : "ACT-HAND",
    "chrono" : 943,
    "code" : "passOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 316,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913519320),
    "value" : "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"
});

/* 49 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698303"),
    "activityId" : "ACT-HAND",
    "chrono" : 945,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 317,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913521977),
    "value" : "corner-left"
});

/* 50 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698304"),
    "activityId" : "ACT-HAND",
    "chrono" : 947,
    "code" : "outside",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 318,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913523395),
    "value" : "outside-right"
});

/* 1 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698305"),
    "activityId" : "ACT-HAND",
    "chrono" : 947,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 319,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913523394),
    "value" : "45"
});

/* 2 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698306"),
    "activityId" : "ACT-HAND",
    "chrono" : 955,
    "code" : "gameSystem",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 320,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "562ba2d4b70fd108e375395e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913531395),
    "value" : "1-5"
});

/* 3 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698307"),
    "activityId" : "ACT-HAND",
    "chrono" : 961,
    "code" : "neutralization",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 321,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "1ce4591d-74a8-46e9-af80-d633f9344d27"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913537341),
    "value" : "1"
});

/* 4 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698308"),
    "activityId" : "ACT-HAND",
    "chrono" : 961,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 322,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913537342),
    "value" : "14"
});

/* 5 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698309"),
    "activityId" : "ACT-HAND",
    "chrono" : 979,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 323,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913556028),
    "value" : "top-right"
});

/* 6 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169830a"),
    "activityId" : "ACT-HAND",
    "chrono" : 980,
    "code" : "shootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 324,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913556997),
    "value" : "LDOWN"
});

/* 7 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169830b"),
    "activityId" : "ACT-HAND",
    "chrono" : 982,
    "code" : "stopOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 325,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913558281),
    "value" : "1"
});

/* 8 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169830c"),
    "activityId" : "ACT-HAND",
    "chrono" : 982,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 326,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913558244),
    "value" : "0"
});

/* 9 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169830d"),
    "activityId" : "ACT-HAND",
    "chrono" : 990,
    "code" : "badPosition",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 327,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913566489),
    "value" : "1"
});

/* 10 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169830e"),
    "activityId" : "ACT-HAND",
    "chrono" : 990,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 328,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913566488),
    "value" : "8"
});

/* 11 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169830f"),
    "activityId" : "ACT-HAND",
    "chrono" : 992,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 329,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913568874),
    "value" : "top-left"
});

/* 12 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698310"),
    "activityId" : "ACT-HAND",
    "chrono" : 993,
    "code" : "shootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 330,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913569929),
    "value" : "RDOWN"
});

/* 13 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698311"),
    "activityId" : "ACT-HAND",
    "chrono" : 994,
    "code" : "goalconceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 331,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913570902),
    "value" : "1"
});

/* 14 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698312"),
    "activityId" : "ACT-HAND",
    "chrono" : 994,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 332,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913570902),
    "value" : "4"
});

/* 15 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698313"),
    "activityId" : "ACT-HAND",
    "chrono" : 1021,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 333,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913597707),
    "value" : "bottom-center"
});

/* 16 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698314"),
    "activityId" : "ACT-HAND",
    "chrono" : 1022,
    "code" : "pole",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 334,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913599149),
    "value" : "top-pole"
});

/* 17 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698315"),
    "activityId" : "ACT-HAND",
    "chrono" : 1045,
    "code" : "passOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 335,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913621225),
    "value" : "d31d3550-479a-4ee9-8304-84a132202d89"
});

/* 18 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698316"),
    "activityId" : "ACT-HAND",
    "chrono" : 1045,
    "code" : "duelWon",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 336,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "d31d3550-479a-4ee9-8304-84a132202d89"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913621758),
    "value" : "1"
});

/* 19 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698317"),
    "activityId" : "ACT-HAND",
    "chrono" : 1046,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 337,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913622984),
    "value" : "0"
});

/* 20 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698318"),
    "activityId" : "ACT-HAND",
    "chrono" : 1050,
    "code" : "interceptionOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 338,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "d31d3550-479a-4ee9-8304-84a132202d89"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913627085),
    "value" : "1"
});

/* 21 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698319"),
    "activityId" : "ACT-HAND",
    "chrono" : 1050,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 339,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913627085),
    "value" : "4"
});

/* 22 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169831a"),
    "activityId" : "ACT-HAND",
    "chrono" : 1056,
    "code" : "looseball",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 340,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913632486),
    "value" : "1"
});

/* 23 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169831b"),
    "activityId" : "ACT-HAND",
    "chrono" : 1056,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 341,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913632488),
    "value" : "6"
});

/* 24 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169831c"),
    "activityId" : "ACT-HAND",
    "chrono" : 1069,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 342,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913645749),
    "value" : "substitue"
});

/* 25 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169831d"),
    "activityId" : "ACT-HAND",
    "chrono" : 1069,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 343,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913645793),
    "value" : "right-backcourt"
});

/* 26 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169831e"),
    "activityId" : "ACT-HAND",
    "chrono" : 1129,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 344,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913706938),
    "value" : "bottom-center"
});

/* 27 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169831f"),
    "activityId" : "ACT-HAND",
    "chrono" : 1130,
    "code" : "outside",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 345,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913708304),
    "value" : "outside-top"
});

/* 28 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698320"),
    "activityId" : "ACT-HAND",
    "chrono" : 1130,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 346,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913708304),
    "value" : "74"
});

/* 29 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698321"),
    "activityId" : "ACT-HAND",
    "chrono" : 1154,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 347,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913732324),
    "value" : "24"
});

/* 30 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698322"),
    "activityId" : "ACT-HAND",
    "chrono" : 1156,
    "code" : "neutralization",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 348,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913733899),
    "value" : "1"
});

/* 31 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698323"),
    "activityId" : "ACT-HAND",
    "chrono" : 1156,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 349,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913733899),
    "value" : "2"
});

/* 32 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698324"),
    "activityId" : "ACT-HAND",
    "chrono" : 1170,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 350,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913748366),
    "value" : "1170"
});

/* 33 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698325"),
    "activityId" : "ACT-HAND",
    "chrono" : 1173,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 351,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "d31d3550-479a-4ee9-8304-84a132202d89"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913750610),
    "value" : "center-backcourt"
});

/* 34 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698326"),
    "activityId" : "ACT-HAND",
    "chrono" : 1175,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 352,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913753362),
    "value" : "pivot"
});

/* 35 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698327"),
    "activityId" : "ACT-HAND",
    "chrono" : 1179,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 353,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913757340),
    "value" : "0"
});

/* 36 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698328"),
    "activityId" : "ACT-HAND",
    "chrono" : 1192,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 354,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913770108),
    "value" : "top-center"
});

/* 37 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698329"),
    "activityId" : "ACT-HAND",
    "chrono" : 1193,
    "code" : "pole",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 355,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913771114),
    "value" : "left-pole"
});

/* 38 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169832a"),
    "activityId" : "ACT-HAND",
    "chrono" : 1196,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 356,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913773657),
    "value" : "top-center"
});

/* 39 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169832b"),
    "activityId" : "ACT-HAND",
    "chrono" : 1197,
    "code" : "shootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 357,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913774994),
    "value" : "LDOWN"
});

/* 40 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169832c"),
    "activityId" : "ACT-HAND",
    "chrono" : 1199,
    "code" : "goalconceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 358,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913776512),
    "value" : "1"
});

/* 41 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169832d"),
    "activityId" : "ACT-HAND",
    "chrono" : 1199,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 359,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913776512),
    "value" : "20"
});

/* 42 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169832e"),
    "activityId" : "ACT-HAND",
    "chrono" : 1227,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 360,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913851065),
    "value" : "0"
});

/* 43 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169832f"),
    "activityId" : "ACT-HAND",
    "chrono" : 1229,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 361,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913853144),
    "value" : "top-center"
});

/* 44 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698330"),
    "activityId" : "ACT-HAND",
    "chrono" : 1230,
    "code" : "shootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 362,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913854355),
    "value" : "CDOWN"
});

/* 45 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698331"),
    "activityId" : "ACT-HAND",
    "chrono" : 1231,
    "code" : "goalconceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 363,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913855429),
    "value" : "1"
});

/* 46 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698332"),
    "activityId" : "ACT-HAND",
    "chrono" : 1231,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 364,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913855429),
    "value" : "4"
});

/* 47 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698333"),
    "activityId" : "ACT-HAND",
    "chrono" : 1267,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 365,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913891102),
    "value" : "bottom-center"
});

/* 48 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698334"),
    "activityId" : "ACT-HAND",
    "chrono" : 1268,
    "code" : "shootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 366,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913892101),
    "value" : "RUP"
});

/* 49 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698335"),
    "activityId" : "ACT-HAND",
    "chrono" : 1269,
    "code" : "goalScored",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 367,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913893122),
    "value" : "1"
});

/* 50 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698336"),
    "activityId" : "ACT-HAND",
    "chrono" : 1269,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 368,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913893122),
    "value" : "38"
});

/* 1 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698337"),
    "activityId" : "ACT-HAND",
    "chrono" : 1291,
    "code" : "neutralization",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 369,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913915188),
    "value" : "1"
});

/* 2 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698338"),
    "activityId" : "ACT-HAND",
    "chrono" : 1291,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 370,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913915184),
    "value" : "22"
});

/* 3 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698339"),
    "activityId" : "ACT-HAND",
    "chrono" : 1293,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 371,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913917064),
    "value" : "0"
});

/* 4 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169833a"),
    "activityId" : "ACT-HAND",
    "chrono" : 1294,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 372,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913918624),
    "value" : "top-center"
});

/* 5 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169833b"),
    "activityId" : "ACT-HAND",
    "chrono" : 1295,
    "code" : "shootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 373,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913919730),
    "value" : "LDOWN"
});

/* 6 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169833c"),
    "activityId" : "ACT-HAND",
    "chrono" : 1296,
    "code" : "stopOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 374,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913920825),
    "value" : "1"
});

/* 7 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169833d"),
    "activityId" : "ACT-HAND",
    "chrono" : 1296,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 375,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913920793),
    "value" : "3"
});

/* 8 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169833e"),
    "activityId" : "ACT-HAND",
    "chrono" : 1328,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 376,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913952541),
    "value" : "top-left"
});

/* 9 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c5169833f"),
    "activityId" : "ACT-HAND",
    "chrono" : 1329,
    "code" : "shootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 377,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913953493),
    "value" : "RDOWN"
});

/* 10 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698340"),
    "activityId" : "ACT-HAND",
    "chrono" : 1330,
    "code" : "goalScored",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 378,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913954565),
    "value" : "1"
});

/* 11 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698341"),
    "activityId" : "ACT-HAND",
    "chrono" : 1330,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 379,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913954568),
    "value" : "34"
});

/* 12 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6ae4b09c0c51698342"),
    "activityId" : "ACT-HAND",
    "chrono" : 1349,
    "code" : "neutralization",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 380,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913973079),
    "value" : "1"
});

/* 13 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698343"),
    "activityId" : "ACT-HAND",
    "chrono" : 1349,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 381,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913973080),
    "value" : "19"
});

/* 14 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698344"),
    "activityId" : "ACT-HAND",
    "chrono" : 1350,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 382,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913974087),
    "value" : "0"
});

/* 15 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698345"),
    "activityId" : "ACT-HAND",
    "chrono" : 1356,
    "code" : "neutralization",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 383,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913980311),
    "value" : "1"
});

/* 16 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698346"),
    "activityId" : "ACT-HAND",
    "chrono" : 1356,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 384,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913980310),
    "value" : "6"
});

/* 17 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698347"),
    "activityId" : "ACT-HAND",
    "chrono" : 1357,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 385,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446913981100),
    "value" : "1"
});

/* 18 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698348"),
    "activityId" : "ACT-HAND",
    "chrono" : 1385,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 386,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914018752),
    "value" : "523"
});

/* 19 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698349"),
    "activityId" : "ACT-HAND",
    "chrono" : 1385,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 387,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914028873),
    "value" : "210"
});

/* 20 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169834a"),
    "activityId" : "ACT-HAND",
    "chrono" : 1385,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 388,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914032343),
    "value" : "pivot"
});

/* 21 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169834b"),
    "activityId" : "ACT-HAND",
    "chrono" : 1385,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 389,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914035335),
    "value" : "left-wingman"
});

/* 22 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169834c"),
    "activityId" : "ACT-HAND",
    "chrono" : 1385,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 390,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914081183),
    "value" : "28"
});

/* 23 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169834d"),
    "activityId" : "ACT-HAND",
    "chrono" : 1386,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 391,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914083132),
    "value" : "0"
});

/* 24 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169834e"),
    "activityId" : "ACT-HAND",
    "chrono" : 1392,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 392,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914088446),
    "value" : "top-left"
});

/* 25 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169834f"),
    "activityId" : "ACT-HAND",
    "chrono" : 1393,
    "code" : "shootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 393,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914089525),
    "value" : "RMIDDLE"
});

/* 26 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698350"),
    "activityId" : "ACT-HAND",
    "chrono" : 1394,
    "code" : "goalScored",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 394,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914090659),
    "value" : "1"
});

/* 27 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698351"),
    "activityId" : "ACT-HAND",
    "chrono" : 1394,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 395,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914090659),
    "value" : "7"
});

/* 28 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698352"),
    "activityId" : "ACT-HAND",
    "chrono" : 1408,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 396,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914104936),
    "value" : "0"
});

/* 29 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698353"),
    "activityId" : "ACT-HAND",
    "chrono" : 1425,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 397,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914121904),
    "value" : "corner-left"
});

/* 30 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698354"),
    "activityId" : "ACT-HAND",
    "chrono" : 1426,
    "code" : "shootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 398,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914123134),
    "value" : "RMIDDLE"
});

/* 31 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698355"),
    "activityId" : "ACT-HAND",
    "chrono" : 1427,
    "code" : "goalScored",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 399,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914124229),
    "value" : "1"
});

/* 32 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698356"),
    "activityId" : "ACT-HAND",
    "chrono" : 1427,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 400,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914124229),
    "value" : "19"
});

/* 33 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698357"),
    "activityId" : "ACT-HAND",
    "chrono" : 1454,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 401,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914150264),
    "value" : "top-center"
});

/* 34 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698358"),
    "activityId" : "ACT-HAND",
    "chrono" : 1455,
    "code" : "outside",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 402,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914151566),
    "value" : "outside-top"
});

/* 35 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698359"),
    "activityId" : "ACT-HAND",
    "chrono" : 1455,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 403,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914151568),
    "value" : "27"
});

/* 36 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169835a"),
    "activityId" : "ACT-HAND",
    "chrono" : 1478,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 404,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914175113),
    "value" : "corner-left"
});

/* 37 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169835b"),
    "activityId" : "ACT-HAND",
    "chrono" : 1479,
    "code" : "shootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 405,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914176246),
    "value" : "RDOWN"
});

/* 38 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169835c"),
    "activityId" : "ACT-HAND",
    "chrono" : 1481,
    "code" : "goalScored",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 406,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914177288),
    "value" : "1"
});

/* 39 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169835d"),
    "activityId" : "ACT-HAND",
    "chrono" : 1481,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 407,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914177288),
    "value" : "26"
});

/* 40 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169835e"),
    "activityId" : "ACT-HAND",
    "chrono" : 1493,
    "code" : "badPosition",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 408,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914190126),
    "value" : "1"
});

/* 41 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169835f"),
    "activityId" : "ACT-HAND",
    "chrono" : 1493,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 409,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914190126),
    "value" : "12"
});

/* 42 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698360"),
    "activityId" : "ACT-HAND",
    "chrono" : 1495,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 410,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914192024),
    "value" : "top-right"
});

/* 43 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698361"),
    "activityId" : "ACT-HAND",
    "chrono" : 1496,
    "code" : "shootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 411,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914193166),
    "value" : "RDOWN"
});

/* 44 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698362"),
    "activityId" : "ACT-HAND",
    "chrono" : 1497,
    "code" : "goalconceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 412,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914194144),
    "value" : "1"
});

/* 45 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698363"),
    "activityId" : "ACT-HAND",
    "chrono" : 1497,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 413,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914194144),
    "value" : "4"
});

/* 46 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698364"),
    "activityId" : "ACT-HAND",
    "chrono" : 1498,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 414,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914953685),
    "value" : "1498"
});

/* 47 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698365"),
    "activityId" : "ACT-HAND",
    "chrono" : 1498,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 415,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914956392),
    "value" : "substitue"
});

/* 48 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698366"),
    "activityId" : "ACT-HAND",
    "chrono" : 1498,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 416,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914956440),
    "value" : "right-backcourt"
});

/* 49 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698367"),
    "activityId" : "ACT-HAND",
    "chrono" : 1498,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 417,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "1ce4591d-74a8-46e9-af80-d633f9344d27"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914974436),
    "value" : "substitue"
});

/* 50 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698368"),
    "activityId" : "ACT-HAND",
    "chrono" : 1498,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 418,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c0bf7706-f039-46c3-86b2-460930696890"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446914974479),
    "value" : "right-wingman"
});

/* 1 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698369"),
    "activityId" : "ACT-HAND",
    "chrono" : 1498,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 419,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "d31d3550-479a-4ee9-8304-84a132202d89"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915010796),
    "value" : "substitue"
});

/* 2 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169836a"),
    "activityId" : "ACT-HAND",
    "chrono" : 1498,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 420,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915010948),
    "value" : "center-backcourt"
});

/* 3 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169836b"),
    "activityId" : "ACT-HAND",
    "chrono" : 1501,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 421,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915031170),
    "value" : "top-center"
});

/* 4 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169836c"),
    "activityId" : "ACT-HAND",
    "chrono" : 1503,
    "code" : "shootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 422,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915033055),
    "value" : "CDOWN"
});

/* 5 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169836d"),
    "activityId" : "ACT-HAND",
    "chrono" : 1505,
    "code" : "goalconceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 423,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915034508),
    "value" : "1"
});

/* 6 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169836e"),
    "activityId" : "ACT-HAND",
    "chrono" : 1505,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 424,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915034508),
    "value" : "6"
});

/* 7 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169836f"),
    "activityId" : "ACT-HAND",
    "chrono" : 1524,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 425,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915054350),
    "value" : "top-right"
});

/* 8 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698370"),
    "activityId" : "ACT-HAND",
    "chrono" : 1526,
    "code" : "shootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 426,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915055445),
    "value" : "LDOWN"
});

/* 9 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698371"),
    "activityId" : "ACT-HAND",
    "chrono" : 1527,
    "code" : "stopOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 427,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915057011),
    "value" : "1"
});

/* 10 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698372"),
    "activityId" : "ACT-HAND",
    "chrono" : 1527,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 428,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915056950),
    "value" : "22"
});

/* 11 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698373"),
    "activityId" : "ACT-HAND",
    "chrono" : 1527,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 429,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915057037),
    "value" : "0"
});

/* 12 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698374"),
    "activityId" : "ACT-HAND",
    "chrono" : 1545,
    "code" : "neutralization",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 430,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915075143),
    "value" : "1"
});

/* 13 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698375"),
    "activityId" : "ACT-HAND",
    "chrono" : 1545,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 431,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915075142),
    "value" : "18"
});

/* 14 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698376"),
    "activityId" : "ACT-HAND",
    "chrono" : 1549,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 432,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915078825),
    "value" : "0"
});

/* 15 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698377"),
    "activityId" : "ACT-HAND",
    "chrono" : 1551,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 433,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915080676),
    "value" : "top-center"
});

/* 16 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698378"),
    "activityId" : "ACT-HAND",
    "chrono" : 1553,
    "code" : "shootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 434,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915082687),
    "value" : "LDOWN"
});

/* 17 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698379"),
    "activityId" : "ACT-HAND",
    "chrono" : 1554,
    "code" : "goalconceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 435,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915084105),
    "value" : "1"
});

/* 18 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169837a"),
    "activityId" : "ACT-HAND",
    "chrono" : 1554,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 436,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915084105),
    "value" : "5"
});

/* 19 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169837b"),
    "activityId" : "ACT-HAND",
    "chrono" : 1568,
    "code" : "looseball",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 437,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c0bf7706-f039-46c3-86b2-460930696890"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915098273),
    "value" : "1"
});

/* 20 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169837c"),
    "activityId" : "ACT-HAND",
    "chrono" : 1568,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 438,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915098273),
    "value" : "14"
});

/* 21 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169837d"),
    "activityId" : "ACT-HAND",
    "chrono" : 1575,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 439,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915104860),
    "value" : "top-left"
});

/* 22 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169837e"),
    "activityId" : "ACT-HAND",
    "chrono" : 1576,
    "code" : "shootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 440,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915106047),
    "value" : "RDOWN"
});

/* 23 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169837f"),
    "activityId" : "ACT-HAND",
    "chrono" : 1577,
    "code" : "goalconceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 441,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915107166),
    "value" : "1"
});

/* 24 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698380"),
    "activityId" : "ACT-HAND",
    "chrono" : 1577,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 442,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915107166),
    "value" : "9"
});

/* 25 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698381"),
    "activityId" : "ACT-HAND",
    "chrono" : 1606,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 443,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915135931),
    "value" : "29"
});

/* 26 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698382"),
    "activityId" : "ACT-HAND",
    "chrono" : 1615,
    "code" : "interceptionOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 444,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915145192),
    "value" : "1"
});

/* 27 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698383"),
    "activityId" : "ACT-HAND",
    "chrono" : 1615,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 445,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915145192),
    "value" : "9"
});

/* 28 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698384"),
    "activityId" : "ACT-HAND",
    "chrono" : 1622,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 446,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915151983),
    "value" : "top-left"
});

/* 29 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698385"),
    "activityId" : "ACT-HAND",
    "chrono" : 1626,
    "code" : "shootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 447,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915156209),
    "value" : "RUP"
});

/* 30 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698386"),
    "activityId" : "ACT-HAND",
    "chrono" : 1627,
    "code" : "goalScored",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 448,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915157358),
    "value" : "1"
});

/* 31 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698387"),
    "activityId" : "ACT-HAND",
    "chrono" : 1627,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 449,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915157358),
    "value" : "12"
});

/* 32 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698388"),
    "activityId" : "ACT-HAND",
    "chrono" : 1648,
    "code" : "duelLoose",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 450,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915178304),
    "value" : "1"
});

/* 33 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698389"),
    "activityId" : "ACT-HAND",
    "chrono" : 1648,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 451,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915178303),
    "value" : "20"
});

/* 34 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169838a"),
    "activityId" : "ACT-HAND",
    "chrono" : 1650,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 452,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915180287),
    "value" : "top-right"
});

/* 35 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169838b"),
    "activityId" : "ACT-HAND",
    "chrono" : 1651,
    "code" : "shootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 453,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915181404),
    "value" : "RDOWN"
});

/* 36 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169838c"),
    "activityId" : "ACT-HAND",
    "chrono" : 1652,
    "code" : "goalconceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 454,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915182446),
    "value" : "1"
});

/* 37 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169838d"),
    "activityId" : "ACT-HAND",
    "chrono" : 1652,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 455,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915182446),
    "value" : "4"
});

/* 38 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169838e"),
    "activityId" : "ACT-HAND",
    "chrono" : 1672,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 456,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915202125),
    "value" : "top-left"
});

/* 39 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169838f"),
    "activityId" : "ACT-HAND",
    "chrono" : 1674,
    "code" : "pole",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 457,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915203634),
    "value" : "right-pole"
});

/* 40 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698390"),
    "activityId" : "ACT-HAND",
    "chrono" : 1687,
    "code" : "passOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 458,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915216596),
    "value" : "cc5d449a-d466-400c-b73f-955c676103ed"
});

/* 41 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698391"),
    "activityId" : "ACT-HAND",
    "chrono" : 1689,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 459,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915218900),
    "value" : "36"
});

/* 42 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698392"),
    "activityId" : "ACT-HAND",
    "chrono" : 1691,
    "code" : "duelLoose",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 460,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915220781),
    "value" : "1"
});

/* 43 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698393"),
    "activityId" : "ACT-HAND",
    "chrono" : 1691,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 461,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915220781),
    "value" : "2"
});

/* 44 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698394"),
    "activityId" : "ACT-HAND",
    "chrono" : 1695,
    "code" : "duelLoose",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 462,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915225353),
    "value" : "1"
});

/* 45 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698395"),
    "activityId" : "ACT-HAND",
    "chrono" : 1695,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 463,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915225353),
    "value" : "4"
});

/* 46 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698396"),
    "activityId" : "ACT-HAND",
    "chrono" : 1697,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 464,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915227467),
    "value" : "top-left"
});

/* 47 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698397"),
    "activityId" : "ACT-HAND",
    "chrono" : 1698,
    "code" : "pole",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 465,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915228419),
    "value" : "left-pole"
});

/* 48 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698398"),
    "activityId" : "ACT-HAND",
    "chrono" : 1701,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 466,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915231336),
    "value" : "top-left"
});

/* 49 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698399"),
    "activityId" : "ACT-HAND",
    "chrono" : 1704,
    "code" : "shootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 467,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915233675),
    "value" : "LMIDDLE"
});

/* 50 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169839a"),
    "activityId" : "ACT-HAND",
    "chrono" : 1705,
    "code" : "goalconceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 468,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915234988),
    "value" : "1"
});

/* 1 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169839b"),
    "activityId" : "ACT-HAND",
    "chrono" : 1705,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 469,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915234987),
    "value" : "10"
});

/* 2 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169839c"),
    "activityId" : "ACT-HAND",
    "chrono" : 1714,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 470,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c0bf7706-f039-46c3-86b2-460930696890"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915243685),
    "value" : "corner-right"
});

/* 3 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169839d"),
    "activityId" : "ACT-HAND",
    "chrono" : 1715,
    "code" : "outside",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 471,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c0bf7706-f039-46c3-86b2-460930696890"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915244876),
    "value" : "outside-left"
});

/* 4 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169839e"),
    "activityId" : "ACT-HAND",
    "chrono" : 1715,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 472,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915244876),
    "value" : "10"
});

/* 5 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169839f"),
    "activityId" : "ACT-HAND",
    "chrono" : 1736,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 473,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915265701),
    "value" : "bottom-center"
});

/* 6 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983a0"),
    "activityId" : "ACT-HAND",
    "chrono" : 1737,
    "code" : "shootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 474,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915266873),
    "value" : "RUP"
});

/* 7 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983a1"),
    "activityId" : "ACT-HAND",
    "chrono" : 1738,
    "code" : "goalconceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 475,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915267890),
    "value" : "1"
});

/* 8 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983a2"),
    "activityId" : "ACT-HAND",
    "chrono" : 1738,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 476,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915267891),
    "value" : "23"
});

/* 9 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983a3"),
    "activityId" : "ACT-HAND",
    "chrono" : 1757,
    "code" : "looseball",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 477,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915287163),
    "value" : "1"
});

/* 10 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983a4"),
    "activityId" : "ACT-HAND",
    "chrono" : 1757,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 478,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915287163),
    "value" : "19"
});

/* 11 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983a5"),
    "activityId" : "ACT-HAND",
    "chrono" : 1768,
    "code" : "contre",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 479,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915297563),
    "value" : "1"
});

/* 12 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983a6"),
    "activityId" : "ACT-HAND",
    "chrono" : 1768,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 480,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915297563),
    "value" : "11"
});

/* 13 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983a7"),
    "activityId" : "ACT-HAND",
    "chrono" : 1770,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 481,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915299549),
    "value" : "0"
});

/* 14 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983a8"),
    "activityId" : "ACT-HAND",
    "chrono" : 1797,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 482,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915326752),
    "value" : "0"
});

/* 15 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983a9"),
    "activityId" : "ACT-HAND",
    "chrono" : 1823,
    "code" : "shift",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 483,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915353189),
    "value" : "1"
});

/* 16 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983aa"),
    "activityId" : "ACT-HAND",
    "chrono" : 1826,
    "code" : "passOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 484,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915355600),
    "value" : "ce18d73e-dedf-43e5-8e75-16e0375be349"
});

/* 17 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983ab"),
    "activityId" : "ACT-HAND",
    "chrono" : 1828,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 485,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915358442),
    "value" : "corner-left"
});

/* 18 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983ac"),
    "activityId" : "ACT-HAND",
    "chrono" : 1830,
    "code" : "shootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 486,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915359649),
    "value" : "RUP"
});

/* 19 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983ad"),
    "activityId" : "ACT-HAND",
    "chrono" : 1831,
    "code" : "goalScored",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 487,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915360816),
    "value" : "1"
});

/* 20 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983ae"),
    "activityId" : "ACT-HAND",
    "chrono" : 1831,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 488,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915360816),
    "value" : "34"
});

/* 21 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983af"),
    "activityId" : "ACT-HAND",
    "chrono" : 1844,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 489,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915373809),
    "value" : "346"
});

/* 22 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983b0"),
    "activityId" : "ACT-HAND",
    "chrono" : 1846,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 490,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915376210),
    "value" : "1846"
});

/* 23 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983b1"),
    "activityId" : "ACT-HAND",
    "chrono" : 1851,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 491,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "d31d3550-479a-4ee9-8304-84a132202d89"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915381045),
    "value" : "center-backcourt"
});

/* 24 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983b2"),
    "activityId" : "ACT-HAND",
    "chrono" : 1853,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 492,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915383339),
    "value" : "pivot"
});

/* 25 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983b3"),
    "activityId" : "ACT-HAND",
    "chrono" : 1870,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 493,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915399850),
    "value" : "bottom-right"
});

/* 26 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983b4"),
    "activityId" : "ACT-HAND",
    "chrono" : 1871,
    "code" : "shootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 494,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915400989),
    "value" : "RDOWN"
});

/* 27 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983b5"),
    "activityId" : "ACT-HAND",
    "chrono" : 1872,
    "code" : "stopOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 495,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915402282),
    "value" : "1"
});

/* 28 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983b6"),
    "activityId" : "ACT-HAND",
    "chrono" : 1872,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 496,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915402219),
    "value" : "0"
});

/* 29 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983b7"),
    "activityId" : "ACT-HAND",
    "chrono" : 1882,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 497,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915412264),
    "value" : "top-center"
});

/* 30 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983b8"),
    "activityId" : "ACT-HAND",
    "chrono" : 1883,
    "code" : "shootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 498,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915413228),
    "value" : "LUP"
});

/* 31 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983b9"),
    "activityId" : "ACT-HAND",
    "chrono" : 1884,
    "code" : "goalScored",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 499,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915414233),
    "value" : "1"
});

/* 32 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983ba"),
    "activityId" : "ACT-HAND",
    "chrono" : 1884,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 500,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915414234),
    "value" : "12"
});

/* 33 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983bb"),
    "activityId" : "ACT-HAND",
    "chrono" : 1908,
    "code" : "duelLoose",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 501,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915437715),
    "value" : "1"
});

/* 34 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983bc"),
    "activityId" : "ACT-HAND",
    "chrono" : 1908,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 502,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915437716),
    "value" : "24"
});

/* 35 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983bd"),
    "activityId" : "ACT-HAND",
    "chrono" : 1909,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 503,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915439309),
    "value" : "top-left"
});

/* 36 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983be"),
    "activityId" : "ACT-HAND",
    "chrono" : 1910,
    "code" : "shootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 504,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915440351),
    "value" : "LDOWN"
});

/* 37 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983bf"),
    "activityId" : "ACT-HAND",
    "chrono" : 1911,
    "code" : "goalconceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 505,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915441390),
    "value" : "1"
});

/* 38 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983c0"),
    "activityId" : "ACT-HAND",
    "chrono" : 1911,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 506,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915441390),
    "value" : "3"
});

/* 39 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983c1"),
    "activityId" : "ACT-HAND",
    "chrono" : 1933,
    "code" : "looseball",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 507,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915462843),
    "value" : "1"
});

/* 40 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983c2"),
    "activityId" : "ACT-HAND",
    "chrono" : 1933,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 508,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915462844),
    "value" : "22"
});

/* 41 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983c3"),
    "activityId" : "ACT-HAND",
    "chrono" : 1937,
    "code" : "interceptionOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 509,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915467474),
    "value" : "1"
});

/* 42 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983c4"),
    "activityId" : "ACT-HAND",
    "chrono" : 1937,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 510,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915467476),
    "value" : "4"
});

/* 43 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983c5"),
    "activityId" : "ACT-HAND",
    "chrono" : 1941,
    "code" : "looseball",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 511,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915470642),
    "value" : "1"
});

/* 44 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983c6"),
    "activityId" : "ACT-HAND",
    "chrono" : 1941,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 512,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915470642),
    "value" : "4"
});

/* 45 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983c7"),
    "activityId" : "ACT-HAND",
    "chrono" : 1956,
    "code" : "neutralization",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 513,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915485835),
    "value" : "1"
});

/* 46 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983c8"),
    "activityId" : "ACT-HAND",
    "chrono" : 1956,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 514,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915485835),
    "value" : "15"
});

/* 47 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983c9"),
    "activityId" : "ACT-HAND",
    "chrono" : 1957,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 515,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915486622),
    "value" : "0"
});

/* 48 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983ca"),
    "activityId" : "ACT-HAND",
    "chrono" : 1962,
    "code" : "neutralization",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 516,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915492544),
    "value" : "1"
});

/* 49 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983cb"),
    "activityId" : "ACT-HAND",
    "chrono" : 1962,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 517,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915492544),
    "value" : "5"
});

/* 50 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983cc"),
    "activityId" : "ACT-HAND",
    "chrono" : 1964,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 518,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915493798),
    "value" : "1"
});

/* 1 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983cd"),
    "activityId" : "ACT-HAND",
    "chrono" : 1970,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 519,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915500327),
    "value" : "top-center"
});

/* 2 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983ce"),
    "activityId" : "ACT-HAND",
    "chrono" : 1971,
    "code" : "pole",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 520,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915501532),
    "value" : "left-pole"
});

/* 3 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983cf"),
    "activityId" : "ACT-HAND",
    "chrono" : 1978,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 521,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915508100),
    "value" : "top-left"
});

/* 4 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983d0"),
    "activityId" : "ACT-HAND",
    "chrono" : 1979,
    "code" : "shootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 522,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915509252),
    "value" : "LUP"
});

/* 5 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983d1"),
    "activityId" : "ACT-HAND",
    "chrono" : 1980,
    "code" : "goalconceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 523,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915510262),
    "value" : "1"
});

/* 6 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983d2"),
    "activityId" : "ACT-HAND",
    "chrono" : 1980,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 524,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915510262),
    "value" : "16"
});

/* 7 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983d3"),
    "activityId" : "ACT-HAND",
    "chrono" : 1991,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 525,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915521498),
    "value" : "top-left"
});

/* 8 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983d4"),
    "activityId" : "ACT-HAND",
    "chrono" : 1992,
    "code" : "shootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 526,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915522600),
    "value" : "LUP"
});

/* 9 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983d5"),
    "activityId" : "ACT-HAND",
    "chrono" : 1993,
    "code" : "goalScored",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 527,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915523590),
    "value" : "1"
});

/* 10 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983d6"),
    "activityId" : "ACT-HAND",
    "chrono" : 1993,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 528,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915523590),
    "value" : "13"
});

/* 11 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983d7"),
    "activityId" : "ACT-HAND",
    "chrono" : 2004,
    "code" : "neutralization",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 529,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "d31d3550-479a-4ee9-8304-84a132202d89"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915534477),
    "value" : "1"
});

/* 12 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983d8"),
    "activityId" : "ACT-HAND",
    "chrono" : 2004,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 530,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915534477),
    "value" : "10"
});

/* 13 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983d9"),
    "activityId" : "ACT-HAND",
    "chrono" : 2006,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 531,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915535716),
    "value" : "0"
});

/* 14 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983da"),
    "activityId" : "ACT-HAND",
    "chrono" : 2020,
    "code" : "neutralization",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 532,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915549929),
    "value" : "1"
});

/* 15 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983db"),
    "activityId" : "ACT-HAND",
    "chrono" : 2020,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 533,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915549930),
    "value" : "14"
});

/* 16 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983dc"),
    "activityId" : "ACT-HAND",
    "chrono" : 2021,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 534,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915550816),
    "value" : "0"
});

/* 17 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983dd"),
    "activityId" : "ACT-HAND",
    "chrono" : 2036,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 535,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915566558),
    "value" : "top-left"
});

/* 18 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983de"),
    "activityId" : "ACT-HAND",
    "chrono" : 2038,
    "code" : "shootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 536,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915567770),
    "value" : "CDOWN"
});

/* 19 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983df"),
    "activityId" : "ACT-HAND",
    "chrono" : 2039,
    "code" : "stopOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 537,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915569061),
    "value" : "1"
});

/* 20 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983e0"),
    "activityId" : "ACT-HAND",
    "chrono" : 2039,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 538,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915569037),
    "value" : "18"
});

/* 21 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983e1"),
    "activityId" : "ACT-HAND",
    "chrono" : 2041,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 539,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915570942),
    "value" : "2"
});

/* 22 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983e2"),
    "activityId" : "ACT-HAND",
    "chrono" : 2045,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 540,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915575642),
    "value" : "top-center"
});

/* 23 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983e3"),
    "activityId" : "ACT-HAND",
    "chrono" : 2049,
    "code" : "outside",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 541,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915579543),
    "value" : "outside-top"
});

/* 24 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983e4"),
    "activityId" : "ACT-HAND",
    "chrono" : 2049,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 542,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915579542),
    "value" : "8"
});

/* 25 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983e5"),
    "activityId" : "ACT-HAND",
    "chrono" : 2086,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 543,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915620848),
    "value" : "top-center"
});

/* 26 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983e6"),
    "activityId" : "ACT-HAND",
    "chrono" : 2087,
    "code" : "pole",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 544,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915622070),
    "value" : "left-pole"
});

/* 27 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983e7"),
    "activityId" : "ACT-HAND",
    "chrono" : 2091,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 545,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915626083),
    "value" : "0"
});

/* 28 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983e8"),
    "activityId" : "ACT-HAND",
    "chrono" : 2116,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 546,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915650689),
    "value" : "bottom-right"
});

/* 29 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983e9"),
    "activityId" : "ACT-HAND",
    "chrono" : 2118,
    "code" : "outside",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 547,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915652217),
    "value" : "outside-left"
});

/* 30 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983ea"),
    "activityId" : "ACT-HAND",
    "chrono" : 2118,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 548,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915652217),
    "value" : "27"
});

/* 31 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983eb"),
    "activityId" : "ACT-HAND",
    "chrono" : 2124,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 549,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915658637),
    "value" : "top-left"
});

/* 32 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983ec"),
    "activityId" : "ACT-HAND",
    "chrono" : 2125,
    "code" : "shootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 550,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915659849),
    "value" : "LMIDDLE"
});

/* 33 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983ed"),
    "activityId" : "ACT-HAND",
    "chrono" : 2126,
    "code" : "goalScored",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 551,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915660880),
    "value" : "1"
});

/* 34 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983ee"),
    "activityId" : "ACT-HAND",
    "chrono" : 2126,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 552,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915660880),
    "value" : "8"
});

/* 35 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983ef"),
    "activityId" : "ACT-HAND",
    "chrono" : 2147,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 553,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915681888),
    "value" : "top-right"
});

/* 36 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983f0"),
    "activityId" : "ACT-HAND",
    "chrono" : 2148,
    "code" : "outside",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 554,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915683156),
    "value" : "outside-right"
});

/* 37 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983f1"),
    "activityId" : "ACT-HAND",
    "chrono" : 2148,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 555,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915683156),
    "value" : "22"
});

/* 38 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983f2"),
    "activityId" : "ACT-HAND",
    "chrono" : 2184,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 556,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915719121),
    "value" : "substitue"
});

/* 39 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983f3"),
    "activityId" : "ACT-HAND",
    "chrono" : 2185,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 557,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915719290),
    "value" : "goalkeeper"
});

/* 40 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983f4"),
    "activityId" : "ACT-HAND",
    "chrono" : 2191,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 558,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915726114),
    "value" : "substitue"
});

/* 41 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983f5"),
    "activityId" : "ACT-HAND",
    "chrono" : 2191,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 559,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915726170),
    "value" : "right-backcourt"
});

/* 42 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983f6"),
    "activityId" : "ACT-HAND",
    "chrono" : 2194,
    "code" : "looseball",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 560,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915729014),
    "value" : "1"
});

/* 43 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983f7"),
    "activityId" : "ACT-HAND",
    "chrono" : 2194,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 561,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915729014),
    "value" : "45"
});

/* 44 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983f8"),
    "activityId" : "ACT-HAND",
    "chrono" : 2216,
    "code" : "interceptionOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 562,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915750427),
    "value" : "1"
});

/* 45 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983f9"),
    "activityId" : "ACT-HAND",
    "chrono" : 2216,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 563,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915750427),
    "value" : "22"
});

/* 46 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983fa"),
    "activityId" : "ACT-HAND",
    "chrono" : 2221,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 564,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915755791),
    "value" : "top-left"
});

/* 47 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983fb"),
    "activityId" : "ACT-HAND",
    "chrono" : 2222,
    "code" : "shootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 565,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915756979),
    "value" : "LUP"
});

/* 48 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983fc"),
    "activityId" : "ACT-HAND",
    "chrono" : 2224,
    "code" : "stopOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 566,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915758381),
    "value" : "1"
});

/* 49 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983fd"),
    "activityId" : "ACT-HAND",
    "chrono" : 2224,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 567,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915758320),
    "value" : "8"
});

/* 50 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983fe"),
    "activityId" : "ACT-HAND",
    "chrono" : 2224,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 568,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915758405),
    "value" : "0"
});

/* 1 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516983ff"),
    "activityId" : "ACT-HAND",
    "chrono" : 2243,
    "code" : "neutralization",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 569,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915777739),
    "value" : "1"
});

/* 2 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698400"),
    "activityId" : "ACT-HAND",
    "chrono" : 2243,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 570,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915777739),
    "value" : "19"
});

/* 3 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698401"),
    "activityId" : "ACT-HAND",
    "chrono" : 2256,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 571,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915790901),
    "value" : "0"
});

/* 4 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698402"),
    "activityId" : "ACT-HAND",
    "chrono" : 2258,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 572,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915792829),
    "value" : "top-center"
});

/* 5 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698403"),
    "activityId" : "ACT-HAND",
    "chrono" : 2259,
    "code" : "shootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 573,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915794157),
    "value" : "LMIDDLE"
});

/* 6 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698404"),
    "activityId" : "ACT-HAND",
    "chrono" : 2260,
    "code" : "stopOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 574,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915795230),
    "value" : "1"
});

/* 7 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698405"),
    "activityId" : "ACT-HAND",
    "chrono" : 2260,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 575,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915795171),
    "value" : "0"
});

/* 8 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698406"),
    "activityId" : "ACT-HAND",
    "chrono" : 2265,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 576,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915800228),
    "value" : "top-left"
});

/* 9 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698407"),
    "activityId" : "ACT-HAND",
    "chrono" : 2267,
    "code" : "shootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 577,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915801287),
    "value" : "LMIDDLE"
});

/* 10 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698408"),
    "activityId" : "ACT-HAND",
    "chrono" : 2268,
    "code" : "goalScored",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 578,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915802441),
    "value" : "1"
});

/* 11 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698409"),
    "activityId" : "ACT-HAND",
    "chrono" : 2268,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 579,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915802441),
    "value" : "7"
});

/* 12 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169840a"),
    "activityId" : "ACT-HAND",
    "chrono" : 2319,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 580,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915970400),
    "value" : "6"
});

/* 13 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169840b"),
    "activityId" : "ACT-HAND",
    "chrono" : 2328,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 581,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c0bf7706-f039-46c3-86b2-460930696890"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915979216),
    "value" : "substitue"
});

/* 14 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169840c"),
    "activityId" : "ACT-HAND",
    "chrono" : 2328,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 582,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "1ce4591d-74a8-46e9-af80-d633f9344d27"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915979257),
    "value" : "right-wingman"
});

/* 15 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169840d"),
    "activityId" : "ACT-HAND",
    "chrono" : 2330,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 583,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "1ce4591d-74a8-46e9-af80-d633f9344d27"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915982002),
    "value" : "corner-right"
});

/* 16 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169840e"),
    "activityId" : "ACT-HAND",
    "chrono" : 2332,
    "code" : "outside",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 584,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "1ce4591d-74a8-46e9-af80-d633f9344d27"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915983812),
    "value" : "outside-left"
});

/* 17 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169840f"),
    "activityId" : "ACT-HAND",
    "chrono" : 2332,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 585,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915983814),
    "value" : "13"
});

/* 18 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698410"),
    "activityId" : "ACT-HAND",
    "chrono" : 2340,
    "code" : "duelLoose",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 586,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915991343),
    "value" : "1"
});

/* 19 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698411"),
    "activityId" : "ACT-HAND",
    "chrono" : 2340,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 587,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915991342),
    "value" : "8"
});

/* 20 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698412"),
    "activityId" : "ACT-HAND",
    "chrono" : 2342,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 588,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915993595),
    "value" : "top-center"
});

/* 21 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698413"),
    "activityId" : "ACT-HAND",
    "chrono" : 2343,
    "code" : "shootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 589,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915994686),
    "value" : "LDOWN"
});

/* 22 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698414"),
    "activityId" : "ACT-HAND",
    "chrono" : 2344,
    "code" : "goalconceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 590,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915995844),
    "value" : "1"
});

/* 23 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698415"),
    "activityId" : "ACT-HAND",
    "chrono" : 2344,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 591,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446915995843),
    "value" : "4"
});

/* 24 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698416"),
    "activityId" : "ACT-HAND",
    "chrono" : 2359,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 592,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916038078),
    "value" : "substitue"
});

/* 25 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698417"),
    "activityId" : "ACT-HAND",
    "chrono" : 2359,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 593,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916038235),
    "value" : "right-backcourt"
});

/* 26 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698418"),
    "activityId" : "ACT-HAND",
    "chrono" : 2386,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 594,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916072426),
    "value" : "0"
});

/* 27 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698419"),
    "activityId" : "ACT-HAND",
    "chrono" : 2387,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 595,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916073036),
    "value" : "1"
});

/* 28 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169841a"),
    "activityId" : "ACT-HAND",
    "chrono" : 2390,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 596,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916076017),
    "value" : "top-left"
});

/* 29 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169841b"),
    "activityId" : "ACT-HAND",
    "chrono" : 2391,
    "code" : "shootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 597,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916077142),
    "value" : "RMIDDLE"
});

/* 30 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169841c"),
    "activityId" : "ACT-HAND",
    "chrono" : 2392,
    "code" : "goalScored",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 598,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916078066),
    "value" : "1"
});

/* 31 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169841d"),
    "activityId" : "ACT-HAND",
    "chrono" : 2392,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 599,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916078068),
    "value" : "5"
});

/* 32 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169841e"),
    "activityId" : "ACT-HAND",
    "chrono" : 2421,
    "code" : "badPosition",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 600,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "d31d3550-479a-4ee9-8304-84a132202d89"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916106846),
    "value" : "1"
});

/* 33 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169841f"),
    "activityId" : "ACT-HAND",
    "chrono" : 2421,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 601,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916106847),
    "value" : "29"
});

/* 34 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698420"),
    "activityId" : "ACT-HAND",
    "chrono" : 2423,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 602,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916108842),
    "value" : "top-right"
});

/* 35 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698421"),
    "activityId" : "ACT-HAND",
    "chrono" : 2424,
    "code" : "shootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 603,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916110156),
    "value" : "RDOWN"
});

/* 36 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698422"),
    "activityId" : "ACT-HAND",
    "chrono" : 2425,
    "code" : "goalconceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 604,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916111310),
    "value" : "1"
});

/* 37 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698423"),
    "activityId" : "ACT-HAND",
    "chrono" : 2425,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 605,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916111310),
    "value" : "4"
});

/* 38 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698424"),
    "activityId" : "ACT-HAND",
    "chrono" : 2439,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 606,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "d31d3550-479a-4ee9-8304-84a132202d89"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916125717),
    "value" : "substitue"
});

/* 39 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698425"),
    "activityId" : "ACT-HAND",
    "chrono" : 2439,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 607,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916125774),
    "value" : "center-backcourt"
});

/* 40 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698426"),
    "activityId" : "ACT-HAND",
    "chrono" : 2446,
    "code" : "penaltyObtained",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 608,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916132125),
    "value" : "1"
});

/* 41 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698427"),
    "activityId" : "ACT-HAND",
    "chrono" : 2455,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 609,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916141063),
    "value" : "top-center"
});

/* 42 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698428"),
    "activityId" : "ACT-HAND",
    "chrono" : 2456,
    "code" : "shootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 610,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916142233),
    "value" : "LUP"
});

/* 43 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698429"),
    "activityId" : "ACT-HAND",
    "chrono" : 2457,
    "code" : "goalScored",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 611,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916143465),
    "value" : "1"
});

/* 44 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169842a"),
    "activityId" : "ACT-HAND",
    "chrono" : 2457,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 612,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916143465),
    "value" : "32"
});

/* 45 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169842b"),
    "activityId" : "ACT-HAND",
    "chrono" : 2481,
    "code" : "badPosition",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 613,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "1ce4591d-74a8-46e9-af80-d633f9344d27"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916167293),
    "value" : "1"
});

/* 46 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169842c"),
    "activityId" : "ACT-HAND",
    "chrono" : 2481,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 614,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916167295),
    "value" : "24"
});

/* 47 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169842d"),
    "activityId" : "ACT-HAND",
    "chrono" : 2491,
    "code" : "badPosition",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 615,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916177386),
    "value" : "1"
});

/* 48 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169842e"),
    "activityId" : "ACT-HAND",
    "chrono" : 2491,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 616,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916177386),
    "value" : "10"
});

/* 49 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169842f"),
    "activityId" : "ACT-HAND",
    "chrono" : 2493,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 617,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916179232),
    "value" : "top-left"
});

/* 50 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698430"),
    "activityId" : "ACT-HAND",
    "chrono" : 2495,
    "code" : "shootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 618,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916181630),
    "value" : "LDOWN"
});

/* 1 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698431"),
    "activityId" : "ACT-HAND",
    "chrono" : 2496,
    "code" : "goalconceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 619,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916182702),
    "value" : "1"
});

/* 2 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698432"),
    "activityId" : "ACT-HAND",
    "chrono" : 2496,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 620,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916182702),
    "value" : "5"
});

/* 3 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698433"),
    "activityId" : "ACT-HAND",
    "chrono" : 2532,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 621,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916218166),
    "value" : "bottom-center"
});

/* 4 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698434"),
    "activityId" : "ACT-HAND",
    "chrono" : 2534,
    "code" : "shootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 622,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916220246),
    "value" : "LDOWN"
});

/* 5 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698435"),
    "activityId" : "ACT-HAND",
    "chrono" : 2535,
    "code" : "stopOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 623,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916221798),
    "value" : "1"
});

/* 6 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698436"),
    "activityId" : "ACT-HAND",
    "chrono" : 2535,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 624,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916221743),
    "value" : "38"
});

/* 7 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698437"),
    "activityId" : "ACT-HAND",
    "chrono" : 2535,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 625,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916221819),
    "value" : "0"
});

/* 8 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698438"),
    "activityId" : "ACT-HAND",
    "chrono" : 2555,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 626,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916241805),
    "value" : "0"
});

/* 9 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698439"),
    "activityId" : "ACT-HAND",
    "chrono" : 2586,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 627,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916290803),
    "value" : "30"
});

/* 10 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169843a"),
    "activityId" : "ACT-HAND",
    "chrono" : 2600,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 628,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916305677),
    "value" : "bottom-right"
});

/* 11 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169843b"),
    "activityId" : "ACT-HAND",
    "chrono" : 2603,
    "code" : "outside",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 629,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916308262),
    "value" : "outside-left"
});

/* 12 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169843c"),
    "activityId" : "ACT-HAND",
    "chrono" : 2603,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 630,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916308262),
    "value" : "17"
});

/* 13 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169843d"),
    "activityId" : "ACT-HAND",
    "chrono" : 2610,
    "code" : "neutralization",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 631,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916315629),
    "value" : "1"
});

/* 14 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169843e"),
    "activityId" : "ACT-HAND",
    "chrono" : 2610,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 632,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916315629),
    "value" : "7"
});

/* 15 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169843f"),
    "activityId" : "ACT-HAND",
    "chrono" : 2611,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 633,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916316506),
    "value" : "0"
});

/* 16 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698440"),
    "activityId" : "ACT-HAND",
    "chrono" : 2623,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 634,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916328210),
    "value" : "bottom-center"
});

/* 17 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698441"),
    "activityId" : "ACT-HAND",
    "chrono" : 2624,
    "code" : "outside",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 635,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916329634),
    "value" : "outside-top"
});

/* 18 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698442"),
    "activityId" : "ACT-HAND",
    "chrono" : 2624,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 636,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916329634),
    "value" : "13"
});

/* 19 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698443"),
    "activityId" : "ACT-HAND",
    "chrono" : 2643,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 637,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "1ce4591d-74a8-46e9-af80-d633f9344d27"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916348843),
    "value" : "top-right"
});

/* 20 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698444"),
    "activityId" : "ACT-HAND",
    "chrono" : 2646,
    "code" : "shootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 638,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "1ce4591d-74a8-46e9-af80-d633f9344d27"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916350933),
    "value" : "LUP"
});

/* 21 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698445"),
    "activityId" : "ACT-HAND",
    "chrono" : 2647,
    "code" : "goalScored",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 639,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "1ce4591d-74a8-46e9-af80-d633f9344d27"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916351982),
    "value" : "1"
});

/* 22 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698446"),
    "activityId" : "ACT-HAND",
    "chrono" : 2647,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 640,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916351981),
    "value" : "23"
});

/* 23 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698447"),
    "activityId" : "ACT-HAND",
    "chrono" : 2680,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 641,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916385648),
    "value" : "top-right"
});

/* 24 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698448"),
    "activityId" : "ACT-HAND",
    "chrono" : 2688,
    "code" : "shootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 642,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916393638),
    "value" : "RMIDDLE"
});

/* 25 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698449"),
    "activityId" : "ACT-HAND",
    "chrono" : 2689,
    "code" : "stopOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 643,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916394785),
    "value" : "1"
});

/* 26 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169844a"),
    "activityId" : "ACT-HAND",
    "chrono" : 2689,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 644,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916394746),
    "value" : "42"
});

/* 27 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169844b"),
    "activityId" : "ACT-HAND",
    "chrono" : 2696,
    "code" : "looseball",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 645,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916401327),
    "value" : "1"
});

/* 28 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169844c"),
    "activityId" : "ACT-HAND",
    "chrono" : 2696,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 646,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916401327),
    "value" : "7"
});

/* 29 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169844d"),
    "activityId" : "ACT-HAND",
    "chrono" : 2711,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 647,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916416338),
    "value" : "substitue"
});

/* 30 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169844e"),
    "activityId" : "ACT-HAND",
    "chrono" : 2711,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 648,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916416376),
    "value" : "left-backcourt"
});

/* 31 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169844f"),
    "activityId" : "ACT-HAND",
    "chrono" : 2715,
    "code" : "exclusionTempoObtained",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 649,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916419977),
    "value" : "1"
});

/* 32 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698450"),
    "activityId" : "ACT-HAND",
    "chrono" : 2715,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 650,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916419973),
    "value" : "19"
});

/* 33 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698451"),
    "activityId" : "ACT-HAND",
    "chrono" : 2718,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 651,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916423761),
    "value" : "top-center"
});

/* 34 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698452"),
    "activityId" : "ACT-HAND",
    "chrono" : 2719,
    "code" : "shootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 652,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916424722),
    "value" : "LDOWN"
});

/* 35 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698453"),
    "activityId" : "ACT-HAND",
    "chrono" : 2721,
    "code" : "goalconceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 653,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916426354),
    "value" : "1"
});

/* 36 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698454"),
    "activityId" : "ACT-HAND",
    "chrono" : 2721,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 654,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916426353),
    "value" : "6"
});

/* 37 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698455"),
    "activityId" : "ACT-HAND",
    "chrono" : 2748,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 655,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916453281),
    "value" : "bottom-center"
});

/* 38 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698456"),
    "activityId" : "ACT-HAND",
    "chrono" : 2749,
    "code" : "outside",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 656,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916454602),
    "value" : "outside-right"
});

/* 39 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698457"),
    "activityId" : "ACT-HAND",
    "chrono" : 2749,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 657,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916454603),
    "value" : "28"
});

/* 40 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698458"),
    "activityId" : "ACT-HAND",
    "chrono" : 2759,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 658,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916464435),
    "value" : "bottom-center"
});

/* 41 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698459"),
    "activityId" : "ACT-HAND",
    "chrono" : 2760,
    "code" : "pole",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 659,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916465425),
    "value" : "right-pole"
});

/* 42 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169845a"),
    "activityId" : "ACT-HAND",
    "chrono" : 2772,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 660,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916477419),
    "value" : "top-center"
});

/* 43 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169845b"),
    "activityId" : "ACT-HAND",
    "chrono" : 2773,
    "code" : "shootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 661,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916478382),
    "value" : "CDOWN"
});

/* 44 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169845c"),
    "activityId" : "ACT-HAND",
    "chrono" : 2774,
    "code" : "goalconceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 662,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916479440),
    "value" : "1"
});

/* 45 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169845d"),
    "activityId" : "ACT-HAND",
    "chrono" : 2774,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 663,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916479439),
    "value" : "25"
});

/* 46 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169845e"),
    "activityId" : "ACT-HAND",
    "chrono" : 2781,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 664,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916485967),
    "value" : "bottom-center"
});

/* 47 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169845f"),
    "activityId" : "ACT-HAND",
    "chrono" : 2782,
    "code" : "shootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 665,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916487062),
    "value" : "RDOWN"
});

/* 48 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698460"),
    "activityId" : "ACT-HAND",
    "chrono" : 2783,
    "code" : "goalScored",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 666,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916488679),
    "value" : "1"
});

/* 49 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698461"),
    "activityId" : "ACT-HAND",
    "chrono" : 2783,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 667,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916488679),
    "value" : "9"
});

/* 50 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698462"),
    "activityId" : "ACT-HAND",
    "chrono" : 2790,
    "code" : "neutralization",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 668,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916495893),
    "value" : "1"
});

/* 1 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698463"),
    "activityId" : "ACT-HAND",
    "chrono" : 2790,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 669,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916495893),
    "value" : "7"
});

/* 2 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698464"),
    "activityId" : "ACT-HAND",
    "chrono" : 2791,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 670,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916496635),
    "value" : "0"
});

/* 3 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698465"),
    "activityId" : "ACT-HAND",
    "chrono" : 2808,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 671,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916513302),
    "value" : "substitue"
});

/* 4 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698466"),
    "activityId" : "ACT-HAND",
    "chrono" : 2808,
    "code" : "positionType",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 672,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916513356),
    "value" : "left-wingman"
});

/* 5 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698467"),
    "activityId" : "ACT-HAND",
    "chrono" : 2811,
    "code" : "neutralization",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 673,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916516612),
    "value" : "1"
});

/* 6 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698468"),
    "activityId" : "ACT-HAND",
    "chrono" : 2811,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 674,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916516612),
    "value" : "20"
});

/* 7 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698469"),
    "activityId" : "ACT-HAND",
    "chrono" : 2855,
    "code" : "looseball",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 675,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916561481),
    "value" : "1"
});

/* 8 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169846a"),
    "activityId" : "ACT-HAND",
    "chrono" : 2855,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 676,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916561481),
    "value" : "44"
});

/* 9 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169846b"),
    "activityId" : "ACT-HAND",
    "chrono" : 2870,
    "code" : "exclusionTempoObtained",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 677,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916577268),
    "value" : "1"
});

/* 10 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169846c"),
    "activityId" : "ACT-HAND",
    "chrono" : 2870,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 678,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916577268),
    "value" : "15"
});

/* 11 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169846d"),
    "activityId" : "ACT-HAND",
    "chrono" : 2880,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 679,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916629436),
    "value" : "9"
});

/* 12 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169846e"),
    "activityId" : "ACT-HAND",
    "chrono" : 2880,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 680,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916630281),
    "value" : "0"
});

/* 13 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169846f"),
    "activityId" : "ACT-HAND",
    "chrono" : 2889,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 681,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916638760),
    "value" : "bottom-center"
});

/* 14 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698470"),
    "activityId" : "ACT-HAND",
    "chrono" : 2890,
    "code" : "shootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 682,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916640050),
    "value" : "LDOWN"
});

/* 15 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698471"),
    "activityId" : "ACT-HAND",
    "chrono" : 2893,
    "code" : "stopOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 683,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916642543),
    "value" : "1"
});

/* 16 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698472"),
    "activityId" : "ACT-HAND",
    "chrono" : 2893,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 684,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916642490),
    "value" : "0"
});

/* 17 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698473"),
    "activityId" : "ACT-HAND",
    "chrono" : 2901,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 685,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916650688),
    "value" : "0"
});

/* 18 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698474"),
    "activityId" : "ACT-HAND",
    "chrono" : 2913,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 686,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916663186),
    "value" : "bottom-right"
});

/* 19 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698475"),
    "activityId" : "ACT-HAND",
    "chrono" : 2914,
    "code" : "shootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 687,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916664220),
    "value" : "LDOWN"
});

/* 20 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698476"),
    "activityId" : "ACT-HAND",
    "chrono" : 2915,
    "code" : "goalScored",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 688,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916665165),
    "value" : "1"
});

/* 21 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698477"),
    "activityId" : "ACT-HAND",
    "chrono" : 2915,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 689,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916665167),
    "value" : "14"
});

/* 22 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698478"),
    "activityId" : "ACT-HAND",
    "chrono" : 2931,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 690,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916681054),
    "value" : "bottom-right"
});

/* 23 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698479"),
    "activityId" : "ACT-HAND",
    "chrono" : 2932,
    "code" : "shootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 691,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916682086),
    "value" : "LMIDDLE"
});

/* 24 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169847a"),
    "activityId" : "ACT-HAND",
    "chrono" : 2933,
    "code" : "stopOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 692,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916683261),
    "value" : "1"
});

/* 25 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169847b"),
    "activityId" : "ACT-HAND",
    "chrono" : 2933,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 693,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916683216),
    "value" : "18"
});

/* 26 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169847c"),
    "activityId" : "ACT-HAND",
    "chrono" : 2950,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 694,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916699734),
    "value" : "top-left"
});

/* 27 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169847d"),
    "activityId" : "ACT-HAND",
    "chrono" : 2951,
    "code" : "shootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 695,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916700696),
    "value" : "LUP"
});

/* 28 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169847e"),
    "activityId" : "ACT-HAND",
    "chrono" : 2952,
    "code" : "goalScored",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 696,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916701609),
    "value" : "1"
});

/* 29 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169847f"),
    "activityId" : "ACT-HAND",
    "chrono" : 2952,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 697,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916701608),
    "value" : "19"
});

/* 30 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698480"),
    "activityId" : "ACT-HAND",
    "chrono" : 2968,
    "code" : "originShootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 698,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916717720),
    "value" : "corner-right"
});

/* 31 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698481"),
    "activityId" : "ACT-HAND",
    "chrono" : 2969,
    "code" : "shootDef",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 699,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916719078),
    "value" : "RDOWN"
});

/* 32 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698482"),
    "activityId" : "ACT-HAND",
    "chrono" : 2970,
    "code" : "goalconceded",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 700,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916720482),
    "value" : "1"
});

/* 33 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698483"),
    "activityId" : "ACT-HAND",
    "chrono" : 2970,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 701,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916720482),
    "value" : "18"
});

/* 34 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698484"),
    "activityId" : "ACT-HAND",
    "chrono" : 2991,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 702,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916770366),
    "value" : "20"
});

/* 35 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698485"),
    "activityId" : "ACT-HAND",
    "chrono" : 2993,
    "code" : "originShootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 703,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916772873),
    "value" : "bottom-center"
});

/* 36 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698486"),
    "activityId" : "ACT-HAND",
    "chrono" : 2995,
    "code" : "shootAtt",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 704,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916774856),
    "value" : "LUP"
});

/* 37 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698487"),
    "activityId" : "ACT-HAND",
    "chrono" : 2996,
    "code" : "stopOk",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 705,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916776049),
    "value" : "1"
});

/* 38 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698488"),
    "activityId" : "ACT-HAND",
    "chrono" : 2996,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 706,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916775985),
    "value" : "5"
});

/* 39 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698489"),
    "activityId" : "ACT-HAND",
    "chrono" : 2996,
    "code" : "timeAttack",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 707,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916776071),
    "value" : "0"
});

/* 40 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169848a"),
    "activityId" : "ACT-HAND",
    "chrono" : 3001,
    "code" : "timeDefense",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 708,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916809221),
    "value" : "4"
});

/* 41 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169848b"),
    "activityId" : "ACT-HAND",
    "chrono" : 3001,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 709,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "7da59c29-b0c2-440f-8720-a22ed0770950"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916809253),
    "value" : "290"
});

/* 42 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169848c"),
    "activityId" : "ACT-HAND",
    "chrono" : 3001,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 710,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916809276),
    "value" : "816"
});

/* 43 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169848d"),
    "activityId" : "ACT-HAND",
    "chrono" : 3001,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 711,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c0bf7706-f039-46c3-86b2-460930696890"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916809294),
    "value" : "673"
});

/* 44 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169848e"),
    "activityId" : "ACT-HAND",
    "chrono" : 3001,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 712,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916809310),
    "value" : "290"
});

/* 45 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169848f"),
    "activityId" : "ACT-HAND",
    "chrono" : 3001,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 713,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916809329),
    "value" : "193"
});

/* 46 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698490"),
    "activityId" : "ACT-HAND",
    "chrono" : 3001,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 714,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916809345),
    "value" : "1148"
});

/* 47 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698491"),
    "activityId" : "ACT-HAND",
    "chrono" : 3001,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 715,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "1ce4591d-74a8-46e9-af80-d633f9344d27"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916809362),
    "value" : "673"
});

/* 48 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698492"),
    "activityId" : "ACT-HAND",
    "chrono" : 3001,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 716,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "c857c124-79c0-4b6e-8406-f89a26b8426f"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916809376),
    "value" : "642"
});

/* 49 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698493"),
    "activityId" : "ACT-HAND",
    "chrono" : 3001,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 717,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916809391),
    "value" : "642"
});

/* 50 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698494"),
    "activityId" : "ACT-HAND",
    "chrono" : 3001,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 718,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "d31d3550-479a-4ee9-8304-84a132202d89"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916809407),
    "value" : "562"
});

/* 1 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698495"),
    "activityId" : "ACT-HAND",
    "chrono" : 3001,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 719,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "ce18d73e-dedf-43e5-8e75-16e0375be349"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916809422),
    "value" : "193"
});

/* 2 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698496"),
    "activityId" : "ACT-HAND",
    "chrono" : 3001,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 720,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446916809437),
    "value" : "562"
});

/* 3 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698497"),
    "activityId" : "ACT-HAND",
    "chrono" : 3001,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 721,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446976382206),
    "value" : "3001"
});

/* 4 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698498"),
    "activityId" : "ACT-HAND",
    "chrono" : 3001,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 722,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "1ce4591d-74a8-46e9-af80-d633f9344d27"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446976382250),
    "value" : "3001"
});

/* 5 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c51698499"),
    "activityId" : "ACT-HAND",
    "chrono" : 3001,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 727,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446976382351),
    "value" : "3001"
});

/* 6 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169849a"),
    "activityId" : "ACT-HAND",
    "chrono" : 3001,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 726,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446976382334),
    "value" : "3001"
});

/* 7 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169849b"),
    "activityId" : "ACT-HAND",
    "chrono" : 3001,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 725,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446976382316),
    "value" : "3001"
});

/* 8 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169849c"),
    "activityId" : "ACT-HAND",
    "chrono" : 3001,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 724,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446976382297),
    "value" : "3001"
});

/* 9 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169849d"),
    "activityId" : "ACT-HAND",
    "chrono" : 3001,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 723,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446976382272),
    "value" : "3001"
});

/* 10 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169849e"),
    "activityId" : "ACT-HAND",
    "chrono" : 3001,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 728,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cc5d449a-d466-400c-b73f-955c676103ed"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446976612657),
    "value" : "3001"
});

/* 11 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c5169849f"),
    "activityId" : "ACT-HAND",
    "chrono" : 3001,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 729,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "1ce4591d-74a8-46e9-af80-d633f9344d27"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446976612841),
    "value" : "3001"
});

/* 12 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516984a0"),
    "activityId" : "ACT-HAND",
    "chrono" : 3001,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 730,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "26baf31a-f153-41b0-9e1d-c32cb9e859dd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446976612864),
    "value" : "3001"
});

/* 13 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516984a1"),
    "activityId" : "ACT-HAND",
    "chrono" : 3001,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 731,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "5f82c510-2c89-46b0-b87d-d3b59e748615"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446976612886),
    "value" : "3001"
});

/* 14 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516984a2"),
    "activityId" : "ACT-HAND",
    "chrono" : 3001,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 732,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "941f9d48-45e8-4b2f-b0ce-d33900a92fb8"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446976612912),
    "value" : "3001"
});

/* 15 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516984a3"),
    "activityId" : "ACT-HAND",
    "chrono" : 3001,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 733,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "cb1ad24e-6195-4c11-854f-4d7a68a43c6e"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446976612933),
    "value" : "3001"
});

/* 16 */
db.SB_Stats.insert({
    "_id" : ObjectId("563f1c6be4b09c0c516984a4"),
    "activityId" : "ACT-HAND",
    "chrono" : 3001,
    "code" : "playTime",
    "eventId" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "mRowid" : 734,
    "owner" : ["561ec20b409937a6b439d4e9", "561ec4d0409937a6b439d4ea", "937918db-848e-4a6d-8feb-a7ba6bd60f5a", "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd"],
    "producter" : [ 
        "b50b3325-fdbd-41bf-bda4-81c827982001"
    ],
    "timer" : NumberLong(1446976612957),
    "value" : "3001"
});