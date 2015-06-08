//////////////////////////////////////////////////////////
/* 
 * SCRIPT MONGO DB
 * INJECTION Indicator stats  (only for developpment)
 * V1.0
 * 
 * This script creates documents for collections :
 * - Stats
 * 
 * AUTHOR : Christophe kervella pour QaoBee
 */
//////////////////////////////////////////////////////////


db.Stats.insert({activityId: "ACT-HAND",
    chrono: 24,
    code: "passDec",
    eventId: "6a125e56-f92c-4729-8877-53b556906ecc",
    owner: "550a0600db8f8b6e2f51f4df",
    producter: ["54160977d5bd065a1bb1e565"],
    structureId: "541168295971d35c1f2d1b5f",
    timer: 1433696958907,
    value: 1
});
    

/* 
 * Vidage des Stats "pour les matchs"
 */
db.Stats.remove(
  {"code" : "goalscored"}
);

db.Stats.remove(
  {"code" : "goalconceded"}
);

db.Stats.remove(
  {"code" : "holder"}
);

db.Stats.remove(
  {"code" : "substitue"}
);

db.Stats.remove(
  {"code" : "yellowCard"}
);

db.Stats.remove(
  {"code" : "redCard"}
);

db.Stats.remove(
  {"code" : "playtime"}
);

db.Stats.remove(
  {"code" : "audience"}
);

//////////////////////////////////////////////////////////
/*
 * Stats match M1
 */
//////////////////////////////////////////////////////////
_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "audience",
    "timer" : NumberLong(1402927200000), //06/16/2014 16:00
    "owner" : "M1",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M1", "value" : NumberInt(44172)
});

//////////////////////////////////////////////////////////
/*
 * Stats match M2
 */
//////////////////////////////////////////////////////////
_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "audience",
    "timer" : NumberLong(1403359200000), //06/21/2014 16:00
    "owner" : "M2",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M2", "value" : NumberInt(30034)
});

//////////////////////////////////////////////////////////
/*
 * Stats match M3
 */
//////////////////////////////////////////////////////////
_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "audience",
    "timer" : NumberLong(1403618400000), //06/24/2014 16:00
    "owner" : "M3",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M3", "value" : NumberInt(28000)
});

//////////////////////////////////////////////////////////
/*
 * Stats match M4
 */
//////////////////////////////////////////////////////////
_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "audience",
    "timer" : NumberLong(1403968500000), //06/28/2014 17:15
    "owner" : "M4",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M4", "value" : NumberInt(37000)
});

//////////////////////////////////////////////////////////
/*
 * Stats match M5
 */
//////////////////////////////////////////////////////////
_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "audience",
    "timer" : NumberLong(1404498600000), //07/04/2014 20:30
    "owner" : "M5",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M5", "value" : NumberInt(37000)
});

//////////////////////////////////////////////////////////
/*
 * Stats match M6
 */
//////////////////////////////////////////////////////////
_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "audience",
    "timer" : NumberLong(1404844200000), //07/08/2014 20:30
    "owner" : "M6",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M6", "value" : NumberInt(63000)
});

//////////////////////////////////////////////////////////
/*
 * Stats match M7
 */
//////////////////////////////////////////////////////////
_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "audience",
    "timer" : NumberLong(1405017000000), //07/10/2014 20:30
    "owner" : "M7",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M7", "value" : NumberInt(28000)
});

//////////////////////////////////////////////////////////
/*
 * Stats Jean-Luc Ettori
 */
//////////////////////////////////////////////////////////
_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "goalconceded",
    "timer" : NumberLong(1402927215000), //06/16/2014 16:00:15
    "owner" : "541d3136f61fbf69868c121f",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M1", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "goalconceded",
    "timer" : NumberLong(1402932120000), //06/16/2014 17:22:00
    "owner" : "541d3136f61fbf69868c121f",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M1", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "goalconceded",
    "timer" : NumberLong(1402933080000), //06/16/2014 17:38:00
    "owner" : "541d3136f61fbf69868c121f",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M1", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1402927200000), //06/16/2014 16:00
    "owner" : "541d3136f61fbf69868c121f",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M1", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1402927200000), //06/16/2014 16:00
    "owner" : "541d3136f61fbf69868c121f",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M1", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "goalconceded",
    "timer" : NumberLong(1403364600000), //06/21/2014 17:30:00
    "owner" : "541d3136f61fbf69868c121f",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M2", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1403359200000), //06/21/2014 16:00
    "owner" : "541d3136f61fbf69868c121f",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M2", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1403359200000), //06/21/2014 16:00
    "owner" : "541d3136f61fbf69868c121f",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M2", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "goalconceded",
    "timer" : NumberLong(1403624340000), //06/24/2014 17:39:00
    "owner" : "541d3136f61fbf69868c121f",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M3", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1403618400000), //06/24/2014 16:00
    "owner" : "541d3136f61fbf69868c121f",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M3", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1403618400000), //06/24/2014 16:00
    "owner" : "541d3136f61fbf69868c121f",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M3", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1403968500000), //06/28/2014 17:15
    "owner" : "541d3136f61fbf69868c121f",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M4", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1403968500000), //06/28/2014 17:15
    "owner" : "541d3136f61fbf69868c121f",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M4", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "goalconceded",
    "timer" : NumberLong(1404504000000), //07/04/2014 22:00
    "owner" : "541d3136f61fbf69868c121f",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M5", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1404498600000), //07/04/2014 20:30
    "owner" : "541d3136f61fbf69868c121f",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M5", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1404498600000), //07/04/2014 20:30
    "owner" : "541d3136f61fbf69868c121f",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M5", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "goalconceded",
    "timer" : NumberLong(1404845220000), //07/04/2014 20:47
    "owner" : "541d3136f61fbf69868c121f",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M6", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "goalconceded",
    "timer" : NumberLong(1404506820000), //07/04/2014 22:47
    "owner" : "541d3136f61fbf69868c121f",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M6", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "goalconceded",
    "timer" : NumberLong(1404507180000), //07/04/2014 22:53
    "owner" : "541d3136f61fbf69868c121f",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M6", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1404844200000), //07/08/2014 20:30
    "owner" : "541d3136f61fbf69868c121f",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M6", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1404844200000), //07/08/2014 20:30
    "owner" : "541d3136f61fbf69868c121f",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M6", "value" : NumberInt(90)
});
//////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////
/*
 * Stats Michel Platini
 */
//////////////////////////////////////////////////////////
_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1402927200000), //06/16/2014 16:00
    "owner" : "541d3136f61fbf69868c1228",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M1", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1402927200000), //06/16/2014 16:00
    "owner" : "541d3136f61fbf69868c1228",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M1", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "goalscored",
    "timer" : NumberLong(1403361780000), //06/21/2014 16:43
    "owner" : "541d3136f61fbf69868c1228",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M2", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1403359200000), //06/21/2014 16:00
    "owner" : "541d3136f61fbf69868c1228",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M2", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1403359200000), //06/21/2014 16:00
    "owner" : "541d3136f61fbf69868c1228",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M2", "value" : NumberInt(80)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1403618400000), //06/24/2014 16:00
    "owner" : "541d3136f61fbf69868c1228",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M3", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1403618400000), //06/24/2014 16:00
    "owner" : "541d3136f61fbf69868c1228",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M3", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1404498600000), //07/04/2014 20:30
    "owner" : "541d3136f61fbf69868c1228",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M5", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1404498600000), //07/04/2014 20:30
    "owner" : "541d3136f61fbf69868c1228",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M5", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1404844200000), //07/08/2014 20:30
    "owner" : "541d3136f61fbf69868c1228",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M6", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1404844200000), //07/08/2014 20:30
    "owner" : "541d3136f61fbf69868c1228",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M6", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "goalscored",
    "timer" : NumberLong(1404845760000), //07/08/2014 20:56
    "owner" : "541d3136f61fbf69868c1228",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M6", "value" : NumberInt(1)
});
//////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////
/*
 * Stats Marius Tresor
 */
//////////////////////////////////////////////////////////
_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1402927200000), //06/16/2014 16:00
    "owner" : "541d3136f61fbf69868c1226",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M1", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1402927200000), //06/16/2014 16:00
    "owner" : "541d3136f61fbf69868c1226",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M1", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1403359200000), //06/21/2014 16:00
    "owner" : "541d3136f61fbf69868c1226",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M2", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1403359200000), //06/21/2014 16:00
    "owner" : "541d3136f61fbf69868c1226",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M2", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1403618400000), //06/24/2014 16:00
    "owner" : "541d3136f61fbf69868c1226",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M3", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1403618400000), //06/24/2014 16:00
    "owner" : "541d3136f61fbf69868c1226",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M3", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1403968500000), //06/28/2014 17:15
    "owner" : "541d3136f61fbf69868c1226",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M4", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1403968500000), //06/28/2014 17:15
    "owner" : "541d3136f61fbf69868c1226",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M4", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1404498600000), //07/04/2014 20:30
    "owner" : "541d3136f61fbf69868c1226",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M5", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1404498600000), //07/04/2014 20:30
    "owner" : "541d3136f61fbf69868c1226",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M5", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1404844200000), //07/08/2014 20:30
    "owner" : "541d3136f61fbf69868c1226",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M6", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1404844200000), //07/08/2014 20:30
    "owner" : "541d3136f61fbf69868c1226",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M6", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "goalscored",
    "timer" : NumberLong(1404851520000), //07/08/2014 22:32
    "owner" : "541d3136f61fbf69868c1226",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M6", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1405017000000), //07/10/2014 20:30
    "owner" : "541d3136f61fbf69868c1226",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M7", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1405017000000), //07/10/2014 20:30
    "owner" : "541d3136f61fbf69868c1226",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M7", "value" : NumberInt(90)
});
//////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////
/*
 * Stats Alain Giresse
 */
//////////////////////////////////////////////////////////
_id = new ObjectId().valueOf();
    db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1402927200000), //06/16/2014 16:00
    "owner" : "541d3136f61fbf69868c122a",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M1", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1402927200000), //06/16/2014 16:00
    "owner" : "541d3136f61fbf69868c122a",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M1", "value":NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1403359200000), //06/21/2014 16:00
    "owner" : "541d3136f61fbf69868c122a",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M2", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1403359200000), //06/21/2014 16:00
    "owner" : "541d3136f61fbf69868c122a",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M2", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1403618400000), //06/24/2014 16:00
    "owner" : "541d3136f61fbf69868c122a",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M3", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1403618400000), //06/24/2014 16:00
    "owner" : "541d3136f61fbf69868c122a",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M3", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1403968500000), //06/28/2014 17:15
    "owner" : "541d3136f61fbf69868c122a",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M4", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1403968500000), //06/28/2014 17:15
    "owner" : "541d3136f61fbf69868c122a",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M4", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "goalscored",
    "timer" : NumberLong(1404500580000), //07/04/2014 21:03
    "owner" : "541d3136f61fbf69868c122a",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M5", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "goalscored",
    "timer" : NumberLong(1404504300000), //07/04/2014 22:05
    "owner" : "541d3136f61fbf69868c122a",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M5", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1404498600000), //07/04/2014 20:30
    "owner" : "541d3136f61fbf69868c122a",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M5", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1404498600000), //07/04/2014 20:30
    "owner" : "541d3136f61fbf69868c122a",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M5", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1404844200000), //07/08/2014 20:30
    "owner" : "541d3136f61fbf69868c122a",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M6", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1404844200000), //07/08/2014 20:30
    "owner" : "541d3136f61fbf69868c122a",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M6", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "goalscored",
    "timer" : NumberLong(1404851880000), //07/08/2014 22:38
    "owner" : "541d3136f61fbf69868c122a",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M6", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "yellowCard",
    "timer" : NumberLong(1404844200000), //07/08/2014 20:30
    "owner" : "541d3136f61fbf69868c122a",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M6", "value" : NumberInt(1)
});
//////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////
/*
 * Stats Jean-François Larios
 */
//////////////////////////////////////////////////////////
_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1402927200000), //06/16/2014 16:00
    "owner" : "541d3136f61fbf69868c122b",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M1", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1402927200000), //06/16/2014 16:00
    "owner" : "541d3136f61fbf69868c122b",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M1", "value" : NumberInt(73)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1405017000000), //07/10/2014 20:30
    "owner" : "541d3136f61fbf69868c122b",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M7", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1405017000000), //07/10/2014 20:30
    "owner" : "541d3136f61fbf69868c122b",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M7", "value" : NumberInt(90)
});
//////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////
/*
 * Stats Gérard Soler
 */
//////////////////////////////////////////////////////////
_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "goalscored",
    "timer" : NumberLong(1402928760000), //06/16/2014 16:26
    "owner" : "541d3136f61fbf69868c1232",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M1", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1402927200000), //06/16/2014 16:00
    "owner" : "541d3136f61fbf69868c1232",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M1", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1402927200000), //06/16/2014 16:00
    "owner" : "541d3136f61fbf69868c1232",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M1", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1403359200000), //06/21/2014 16:00
    "owner" : "541d3136f61fbf69868c1232",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M2", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1403359200000), //06/21/2014 16:00
    "owner" : "541d3136f61fbf69868c1232",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M2", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1403618400000), //06/24/2014 16:00
    "owner" : "541d3136f61fbf69868c1232",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M3", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1403618400000), //06/24/2014 16:00
    "owner" : "541d3136f61fbf69868c1232",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M3", "value" : NumberInt(87)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1403968500000), //06/28/2014 17:15
    "owner" : "541d3136f61fbf69868c1232",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M4", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1403968500000), //06/28/2014 17:15
    "owner" : "541d3136f61fbf69868c1232",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M4", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1404498600000), //07/04/2014 20:30
    "owner" : "541d3136f61fbf69868c1232",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M5", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1404498600000), //07/04/2014 20:30
    "owner" : "541d3136f61fbf69868c1232",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M5", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1405017000000), //07/10/2014 20:30
    "owner" : "541d3136f61fbf69868c1232",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M7", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1405017000000), //07/10/2014 20:30
    "owner" : "541d3136f61fbf69868c1232",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M7", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "yellowCard",
    "timer" : NumberLong(1405017000000), //07/10/2014 20:30
    "owner" : "541d3136f61fbf69868c1232",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M2", "value" : NumberInt(1)
});
//////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////
/*
 * Stats Dominique Rocheteau
 */
//////////////////////////////////////////////////////////
_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1402927200000), //06/16/2014 16:00
    "owner" : "541d3136f61fbf69868c1230",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M1", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1402927200000), //06/16/2014 16:00
    "owner" : "541d3136f61fbf69868c1230",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M1", "value" : NumberInt(71)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "substitue",
    "timer" : NumberLong(1403968500000), //06/28/2014 17:15
    "owner" : "541d3136f61fbf69868c1230",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M4", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1403968500000), //06/28/2014 17:15
    "owner" : "541d3136f61fbf69868c1230",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M4", "value" : NumberInt(75)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "goalscored",
    "timer" : NumberLong(1404502320000), //07/04/2014 21:32
    "owner" : "541d3136f61fbf69868c1230",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M5", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "goalscored",
    "timer" : NumberLong(1404503580000), //07/04/2014 21:53
    "owner" : "541d3136f61fbf69868c1230",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M5", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1404498600000), //07/04/2014 20:30
    "owner" : "541d3136f61fbf69868c1230",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M5", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1404498600000), //07/04/2014 20:30
    "owner" : "541d3136f61fbf69868c1230",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M5", "value" : NumberInt(83)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1404844200000), //07/08/2014 20:30
    "owner" : "541d3136f61fbf69868c1230",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M6", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1404844200000), //07/08/2014 20:30
    "owner" : "541d3136f61fbf69868c1230",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M6", "value" : NumberInt(90)
});
//////////////////////////////////////////////////////////
/*
 * Stats Manuel Amoros
 */
//////////////////////////////////////////////////////////
_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1402927200000), //06/16/2014 16:00
    "owner" : "541d3136f61fbf69868c1220",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M1", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1402927200000), //06/16/2014 16:00
    "owner" : "541d3136f61fbf69868c1220",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M1", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1403359200000), //06/21/2014 16:00
    "owner" : "541d3136f61fbf69868c1220",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M2", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1403359200000), //06/21/2014 16:00
    "owner" : "541d3136f61fbf69868c1220",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M2", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "yellowCard",
    "timer" : NumberLong(1403359200000), //06/21/2014 16:00
    "owner" : "541d3136f61fbf69868c1220",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M2", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1403618400000), //06/24/2014 16:00
    "owner" : "541d3136f61fbf69868c1220",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M3", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1403618400000), //06/24/2014 16:00
    "owner" : "541d3136f61fbf69868c1220",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M3", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "yellowCard",
    "timer" : NumberLong(1403618400000), //06/24/2014 16:00
    "owner" : "541d3136f61fbf69868c1220",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M3", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1404498600000), //07/04/2014 20:30
    "owner" : "541d3136f61fbf69868c1220",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M5", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1404498600000), //07/04/2014 20:30
    "owner" : "541d3136f61fbf69868c1220",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M5", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1404844200000), //07/08/2014 20:30
    "owner" : "541d3136f61fbf69868c1220",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M6", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1404844200000), //07/08/2014 20:30
    "owner" : "541d3136f61fbf69868c1220",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M6", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1405017000000), //07/10/2014 20:30
    "owner" : "541d3136f61fbf69868c1220",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M7", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1405017000000), //07/10/2014 20:30
    "owner" : "541d3136f61fbf69868c1220",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M7", "value" : NumberInt(90)
});
//////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////
/*
 * Stats Patrick Battiston
 */
//////////////////////////////////////////////////////////
_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1402927200000), //06/16/2014 16:00
    "owner" : "541d3136f61fbf69868c1221",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M1", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1402927200000), //06/16/2014 16:00
    "owner" : "541d3136f61fbf69868c1221",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M1", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1403968500000), //06/28/2014 17:15
    "owner" : "541d3136f61fbf69868c1221",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M4", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1403968500000), //06/28/2014 17:15
    "owner" : "541d3136f61fbf69868c1221",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M4", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "substitue",
    "timer" : NumberLong(1404844200000), //07/08/2014 20:30
    "owner" : "541d3136f61fbf69868c1221",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M6", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1404844200000), //07/08/2014 20:30
    "owner" : "541d3136f61fbf69868c1221",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M6", "value" : NumberInt(10)
});
//////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////
/*
 * Stats Maxime Bossis
 */
//////////////////////////////////////////////////////////
_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1402927200000), //06/16/2014 16:00
    "owner" : "541d3136f61fbf69868c1222",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M1", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1402927200000), //06/16/2014 16:00
    "owner" : "541d3136f61fbf69868c1222",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M1", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1403359200000), //06/21/2014 16:00
    "owner" : "541d3136f61fbf69868c1222",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M2", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1403359200000), //06/21/2014 16:00
    "owner" : "541d3136f61fbf69868c1222",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M2", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "goalscored",
    "timer" : NumberLong(1403365500000), //06/21/2014 17:45
    "owner" : "541d3136f61fbf69868c1222",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M2", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1403618400000), //06/24/2014 16:00
    "owner" : "541d3136f61fbf69868c1222",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M3", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1403618400000), //06/24/2014 16:00
    "owner" : "541d3136f61fbf69868c1222",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M3", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1403968500000), //06/28/2014 17:15
    "owner" : "541d3136f61fbf69868c1222",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M4", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1403968500000), //06/28/2014 17:15
    "owner" : "541d3136f61fbf69868c1222",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M4", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1404498600000), //07/04/2014 20:30
    "owner" : "541d3136f61fbf69868c1222",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M5", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1404498600000), //07/04/2014 20:30
    "owner" : "541d3136f61fbf69868c1222",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M5", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1404844200000), //07/08/2014 20:30
    "owner" : "541d3136f61fbf69868c1222",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M6", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1404844200000), //07/08/2014 20:30
    "owner" : "541d3136f61fbf69868c1222",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M6", "value" : NumberInt(90)
});
//////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////
/*
 * Stats Christian Lopez
 */
//////////////////////////////////////////////////////////
_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1402927200000), //06/16/2014 16:00
    "owner" : "541d3136f61fbf69868c1224",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M1", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1402927200000), //06/16/2014 16:00
    "owner" : "541d3136f61fbf69868c1224",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M1", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "substitue",
    "timer" : NumberLong(1403359200000), //06/21/2014 16:00
    "owner" : "541d3136f61fbf69868c1224",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M2", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1403359200000), //06/21/2014 16:00
    "owner" : "541d3136f61fbf69868c1224",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M2", "value" : NumberInt(30)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "substitue",
    "timer" : NumberLong(1404844200000), //07/08/2014 20:30
    "owner" : "541d3136f61fbf69868c1224",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M6", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1404844200000), //07/08/2014 20:30
    "owner" : "541d3136f61fbf69868c1224",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M6", "value" : NumberInt(30)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "substitue",
    "timer" : NumberLong(1405017000000), //07/10/2014 20:30
    "owner" : "541d3136f61fbf69868c1224",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M7", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1405017000000), //07/10/2014 20:30
    "owner" : "541d3136f61fbf69868c1224",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M7", "value" : NumberInt(26)
});
//////////////////////////////////////////////////////////
/*
 * Stats Didier Six
 */
////////////////////////////////////////////////////////
_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "substitue",
    "timer" : NumberLong(1402927200000), //06/16/2014 16:00
    "owner" : "541d3136f61fbf69868c1231",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M1", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1402927200000), //06/16/2014 16:00
    "owner" : "541d3136f61fbf69868c1231",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M1", "value" : NumberInt(19)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1403359200000), //06/21/2014 16:00
    "owner" : "541d3136f61fbf69868c1231",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M2", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1403359200000), //06/21/2014 16:00
    "owner" : "541d3136f61fbf69868c1231",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M2", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "goalscored",
    "timer" : NumberLong(1403362980000), //06/21/2014 17:03
    "owner" : "541d3136f61fbf69868c1231",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M2", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "goalscored",
    "timer" : NumberLong(1403623260000), //06/24/2014 17:21
    "owner" : "541d3136f61fbf69868c1231",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M3", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1403618400000), //06/24/2014 16:00
    "owner" : "541d3136f61fbf69868c1231",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M3", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1403618400000), //06/24/2014 16:00
    "owner" : "541d3136f61fbf69868c1231",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M3", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1403968500000), //06/28/2014 17:15
    "owner" : "541d3136f61fbf69868c1231",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M4", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1403968500000), //06/28/2014 17:15
    "owner" : "541d3136f61fbf69868c1231",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M4", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "substitue",
    "timer" : NumberLong(1404498600000), //07/04/2014 20:30
    "owner" : "541d3136f61fbf69868c1231",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M5", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1404498600000), //07/04/2014 20:30
    "owner" : "541d3136f61fbf69868c1231",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M5", "value" : NumberInt(27)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1404844200000), //07/08/2014 20:30
    "owner" : "541d3136f61fbf69868c1231",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M6", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1404844200000), //07/08/2014 20:30
    "owner" : "541d3136f61fbf69868c1231",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M6", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "substitue",
    "timer" : NumberLong(1405017000000), //07/10/2014 20:30
    "owner" : "541d3136f61fbf69868c1231",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M7", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1405017000000), //07/10/2014 20:30
    "owner" : "541d3136f61fbf69868c1231",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M7", "value" : NumberInt(10)
});
//////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////
/*
 * Stats Jean Tigana
 */
////////////////////////////////////////////////////////
_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "substitue",
    "timer" : NumberLong(1402927200000), //06/16/2014 16:00
    "owner" : "541d3136f61fbf69868c122c",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M1", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1402927200000), //06/16/2014 16:00
    "owner" : "541d3136f61fbf69868c122c",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M1", "value" : NumberInt(17)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1403968500000), //06/28/2014 17:15
    "owner" : "541d3136f61fbf69868c122c",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M4", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1403968500000), //06/28/2014 17:15
    "owner" : "541d3136f61fbf69868c122c",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M4", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "yellowCard",
    "timer" : NumberLong(1404498600000), //07/04/2014 20:30
    "owner" : "541d3136f61fbf69868c122c",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M5", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1404498600000), //07/04/2014 20:30
    "owner" : "541d3136f61fbf69868c122c",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M5", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1404498600000), //07/04/2014 20:30
    "owner" : "541d3136f61fbf69868c122c",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M5", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1404844200000), //07/08/2014 20:30
    "owner" : "541d3136f61fbf69868c122c",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M6", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1404844200000), //07/08/2014 20:30
    "owner" : "541d3136f61fbf69868c122c",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M6", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1405017000000), //07/10/2014 20:30
    "owner" : "541d3136f61fbf69868c122c",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M7", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1405017000000), //07/10/2014 20:30
    "owner" : "541d3136f61fbf69868c122c",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M7", "value" : NumberInt(80)
});
//////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////
/*
 * Stats Bernard Lacombe
 */
//////////////////////////////////////////////////////////
_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1403359200000), //06/21/2014 16:00
    "owner" : "541d3136f61fbf69868c122f",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M2", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1403359200000), //06/21/2014 16:00
    "owner" : "541d3136f61fbf69868c122f",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M2", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1403618400000), //06/24/2014 16:00
    "owner" : "541d3136f61fbf69868c122f",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M3", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1403618400000), //06/24/2014 16:00
    "owner" : "541d3136f61fbf69868c122f",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M3", "value" : NumberInt(70)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1403968500000), //06/28/2014 17:15
    "owner" : "541d3136f61fbf69868c122f",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M4", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1403968500000), //06/28/2014 17:15
    "owner" : "541d3136f61fbf69868c122f",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M4", "value" : NumberInt(15)
});
//////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////
/*
 * Stats Gérard Janvion
 */
//////////////////////////////////////////////////////////
_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1403359200000), //06/21/2014 16:00
    "owner" : "541d3136f61fbf69868c1223",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M2", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1403359200000), //06/21/2014 16:00
    "owner" : "541d3136f61fbf69868c1223",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M2", "value" : NumberInt(60)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1403618400000), //06/24/2014 16:00
    "owner" : "541d3136f61fbf69868c1223",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M3", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1403618400000), //06/24/2014 16:00
    "owner" : "541d3136f61fbf69868c1223",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M3", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1403968500000), //06/28/2014 17:15
    "owner" : "541d3136f61fbf69868c1223",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M4", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1403968500000), //06/28/2014 17:15
    "owner" : "541d3136f61fbf69868c1223",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M4", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1404498600000), //07/04/2014 20:30
    "owner" : "541d3136f61fbf69868c1223",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M5", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1404498600000), //07/04/2014 20:30
    "owner" : "541d3136f61fbf69868c1223",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M5", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1404844200000), //07/08/2014 20:30
    "owner" : "541d3136f61fbf69868c1223",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M6", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1404844200000), //07/08/2014 20:30
    "owner" : "541d3136f61fbf69868c1223",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M6", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1405017000000), //07/10/2014 20:30
    "owner" : "541d3136f61fbf69868c1223",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M7", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1405017000000), //07/10/2014 20:30
    "owner" : "541d3136f61fbf69868c1223",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M7", "value" : NumberInt(64)
});
//////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////
/*
 * Stats Bernard Genghini
 */
//////////////////////////////////////////////////////////
_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "goalscored",
    "timer" : NumberLong(1403361060000), //06/21/2014 16:31
    "owner" : "541d3136f61fbf69868c1227",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M2", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1403359200000), //06/21/2014 16:00
    "owner" : "541d3136f61fbf69868c1227",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M2", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1403359200000), //06/21/2014 16:00
    "owner" : "541d3136f61fbf69868c1227",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M2", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1403618400000), //06/24/2014 16:00
    "owner" : "541d3136f61fbf69868c1227",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M3", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1403618400000), //06/24/2014 16:00
    "owner" : "541d3136f61fbf69868c1227",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M3", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "goalscored",
    "timer" : NumberLong(1403970840000), //06/28/2014 17:54
    "owner" : "541d3136f61fbf69868c1227",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M4", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1403968500000), //06/28/2014 17:15
    "owner" : "541d3136f61fbf69868c1227",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M4", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1403968500000), //06/28/2014 17:15
    "owner" : "541d3136f61fbf69868c1227",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M4", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1404498600000), //07/04/2014 20:30
    "owner" : "541d3136f61fbf69868c1227",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M5", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1404498600000), //07/04/2014 20:30
    "owner" : "541d3136f61fbf69868c1227",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M5", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1404844200000), //07/08/2014 20:30
    "owner" : "541d3136f61fbf69868c1227",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M6", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1404844200000), //07/08/2014 20:30
    "owner" : "541d3136f61fbf69868c1227",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M6", "value" : NumberInt(50)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "yellowCard",
    "timer" : NumberLong(1404844200000), //07/08/2014 20:30
    "owner" : "541d3136f61fbf69868c1227",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M6", "value" : NumberInt(1)
});
//////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////
/*
 * Stats René Girard
 */
//////////////////////////////////////////////////////////
_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "substitue",
    "timer" : NumberLong(1402927200000), //06/16/2014 16:00
    "owner" : "541d3136f61fbf69868c1229",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M2", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1402927200000), //06/16/2014 16:00
    "owner" : "541d3136f61fbf69868c1229",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M2", "value" : NumberInt(10)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "substitue",
    "timer" : NumberLong(1403618400000), //06/24/2014 16:00
    "owner" : "541d3136f61fbf69868c1229",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M3", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1403618400000), //06/24/2014 16:00
    "owner" : "541d3136f61fbf69868c1229",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M3", "value" : NumberInt(3)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "substitue",
    "timer" : NumberLong(1403968500000), //06/28/2014 17:15
    "owner" : "541d3136f61fbf69868c1229",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M4", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1403968500000), //06/28/2014 17:15
    "owner" : "541d3136f61fbf69868c1229",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M4", "value" : NumberInt(6)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1405017000000), //07/10/2014 20:30
    "owner" : "541d3136f61fbf69868c1229",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M7", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1405017000000), //07/10/2014 20:30
    "owner" : "541d3136f61fbf69868c1229",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M7", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "goalscored",
    "timer" : NumberLong(1405017780000), //07/10/2014 20:43
    "owner" : "541d3136f61fbf69868c1229",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M7", "value" : NumberInt(1)
});

//////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////
/*
 * Stats Alain Couriol
 */
//////////////////////////////////////////////////////////
_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "substitue",
    "timer" : NumberLong(1403618400000), //06/24/2014 16:00
    "owner" : "541d3136f61fbf69868c122e",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M3", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1403618400000), //06/24/2014 16:00
    "owner" : "541d3136f61fbf69868c122e",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M3", "value" : NumberInt(20)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "substitue",
    "timer" : NumberLong(1404498600000), //07/04/2014 20:30
    "owner" : "541d3136f61fbf69868c122e",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M5", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1404498600000), //07/04/2014 20:30
    "owner" : "541d3136f61fbf69868c122e",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M5", "value" : NumberInt(7)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1405017000000), //07/10/2014 20:30
    "owner" : "541d3136f61fbf69868c122e",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M7", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1405017000000), //07/10/2014 20:30
    "owner" : "541d3136f61fbf69868c122e",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M7", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "goalscored",
    "timer" : NumberLong(1405022280000), //07/10/2014 21:58
    "owner" : "541d3136f61fbf69868c122e",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M7", "value" : NumberInt(1)
});

//////////////////////////////////////////////////////////

////////////////////////////////////////////////////////
/*
 * Stats Jean Castaneda
 */
//////////////////////////////////////////////////////////
_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1405017000000), //07/10/2014 20:30
    "owner" : "541d3136f61fbf69868c121e",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M7", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1405017000000), //07/10/2014 20:30
    "owner" : "541d3136f61fbf69868c121e",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M7", "value" : NumberInt(90)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "goalconceded",
    "timer" : NumberLong(1405019460000), //07/04/2014 21:11
    "owner" : "541d3136f61fbf69868c121e",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M7", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "goalconceded",
    "timer" : NumberLong(1405019640000), //07/04/2014 21:14
    "owner" : "541d3136f61fbf69868c121e",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M7", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "goalconceded",
    "timer" : NumberLong(1405020660000), //07/04/2014 21:31
    "owner" : "541d3136f61fbf69868c121e",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M7", "value" : NumberInt(1)
});
//////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////
/*
 * Stats Bruno Bellone
 */
//////////////////////////////////////////////////////////
_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1405017000000), //07/10/2014 20:30
    "owner" : "541d3136f61fbf69868c122d",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M7", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1405017000000), //07/10/2014 20:30
    "owner" : "541d3136f61fbf69868c122d",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M7", "value" : NumberInt(90)
});
//////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////
/*
 * Stats Philippe Mahut
 */
//////////////////////////////////////////////////////////
_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "holder",
    "timer" : NumberLong(1405017000000), //07/10/2014 20:30
    "owner" : "541d3136f61fbf69868c1225",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M7", "value" : NumberInt(1)
});

_id = new ObjectId().valueOf();
db.Stats.insert({
    "_id" : _id,
    "code" : "playtime",
    "timer" : NumberLong(1405017000000), //07/10/2014 20:30
    "owner" : "541d3136f61fbf69868c1225",
    "producter" : ["54160977d5bd065a1bb1e565"],
    "structureId" : "541168295971d35c1f2d1b60",
    "activityId" : "ACT-FOOT",
    "eventId" : "M7", "value" : NumberInt(90)
});
//////////////////////////////////////////////////////////