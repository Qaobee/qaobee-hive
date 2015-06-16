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
 * INJECTION Indicator configuration
 * V1.0
 * 
 * AUTHOR : Christophe kervella pour QaoBee
 */
//////////////////////////////////////////////////////////

/* 
 * Vidage de la collection IndicateurCfg
 */
db.IndicatorCfg.remove({"activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA"});


/* *******************
 * Characteristics of person
 ******************* */
//Int Value
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "hightPerson",     "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "PERS-CHARACTERISTIC", "listScreen" : [ "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "value","type" : "INT"} ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "weightPerson",    "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "PERS-CHARACTERISTIC", "listScreen" : [ "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "value","type" : "INT"} ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "attendance",      "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "PERS-CHARACTERISTIC", "listScreen" : [ "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "value","type" : "INT"} ], "listValues" : [ 0, 1 ]});

//String Value
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "laterality",      "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "PERS-CHARACTERISTIC", "listScreen" : [ "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "value", "type" : "STRING"} ],"listValues" : [ "lefthanded", "righthanded", "ambidextrous" ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "stateForm",       "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "PERS-CHARACTERISTIC", "listScreen" : [ "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "value", "type" : "STRING"} ],"listValues" : [ "poor", "middling", "good", "excellent" ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "positionType",    "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "PERS-CHARACTERISTIC", "listScreen" : [ "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "value", "type" : "STRING"} ],"listValues" : [ "center-backcourt", "right-backcourt", "left-backcourt", "right-wingman", "left-wingman", "goalkeeper", "pivot" ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "unavailability",  "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "PERS-CHARACTERISTIC", "listScreen" : [ "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "value", "type" : "STRING"} ],"listValues" : [ "available", "injured", "suspended", "absent" ]});

/* *******************
 * stat event person
 ******************* */
//Int Value
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "2minutes",        "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "PERS-SANCTION", "listScreen" : [ "COLLECTE", "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "INT"} ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "yellowCard",      "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "PERS-SANCTION", "listScreen" : [ "COLLECTE", "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "INT"} ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "redCard",         "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "PERS-SANCTION", "listScreen" : [ "COLLECTE", "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "INT"} ]});

db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "playtime",        "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "PERS-STANDING", "listScreen" : [ "COLLECTE", "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "INT"} ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "substitue",       "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "PERS-STANDING", "listScreen" : [ "COLLECTE", "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "INT"} ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "holder",          "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "PERS-STANDING", "listScreen" : [ "COLLECTE", "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "INT"} ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "injured",         "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "PERS-STANDING", "listScreen" : [ "COLLECTE", "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "INT"} ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "eventNotePerson", "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "PERS-STANDING", "listScreen" : [ "COLLECTE", "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "INT"} ]});

db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "neutralization",  "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "PERS-ACT-DEF-POS", "listScreen" : [ "COLLECTE", "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "INT"} ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "forceDef",        "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "PERS-ACT-DEF-POS", "listScreen" : [ "COLLECTE", "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "INT"} ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "contre",          "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "PERS-ACT-DEF-POS", "listScreen" : [ "COLLECTE", "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "INT"} ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "interceptionOk",  "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "PERS-ACT-DEF-POS", "listScreen" : [ "COLLECTE", "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "INT"} ]});

db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "interceptionKo",  "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "PERS-ACT-DEF-NEG", "listScreen" : [ "COLLECTE", "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "INT"} ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "duelLoose"  ,     "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "PERS-ACT-DEF-NEG", "listScreen" : [ "COLLECTE", "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "INT"} ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "tooLate",         "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "PERS-ACT-DEF-NEG", "listScreen" : [ "COLLECTE", "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "INT"} ]});

db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "shootAtt",        "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "PERS-ACT-OFF-NEG", "listScreen" : [ "COLLECTE", "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "STRING"} ],"listValues" : [ "HC", "POST", "LUP", "LMIDDLE", "LDOWN", "CUP", "CMIDDLE", "CDOWN", "RUP", "RMIDDLE", "RDOWN" ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "forceAtt",        "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "PERS-ACT-OFF-NEG", "listScreen" : [ "COLLECTE", "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "INT"} ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "marcher",         "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "PERS-ACT-OFF-NEG", "listScreen" : [ "COLLECTE", "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "INT"} ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "doubleDribble",   "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "PERS-ACT-OFF-NEG", "listScreen" : [ "COLLECTE", "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "INT"} ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "looseball",       "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "PERS-ACT-OFF-NEG", "listScreen" : [ "COLLECTE", "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "INT"} ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "foot",            "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "PERS-ACT-OFF-NEG", "listScreen" : [ "COLLECTE", "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "INT"} ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "zone",            "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "PERS-ACT-OFF-NEG", "listScreen" : [ "COLLECTE", "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "INT"} ]});

db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "counterAttack",   "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "PERS-ACT-OFF-POS", "listScreen" : [ "COLLECTE", "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "INT"} ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "penaltyObtained", "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "PERS-ACT-OFF-POS", "listScreen" : [ "COLLECTE", "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "INT"} ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "2minutesObtained", "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "PERS-ACT-OFF-POS", "listScreen" : [ "COLLECTE", "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "INT"} ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "shift",           "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "PERS-ACT-OFF-POS", "listScreen" : [ "COLLECTE", "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "INT"} ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "duelWon",         "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "PERS-ACT-OFF-POS", "listScreen" : [ "COLLECTE", "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "INT"} ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "passDec",         "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "PERS-ACT-OFF-POS", "listScreen" : [ "COLLECTE", "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "INT"} ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "passOk",          "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "PERS-ACT-OFF-POS", "listScreen" : [ "COLLECTE", "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "INT"} ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "goalScored",      "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "PERS-ACT-OFF-POS", "listScreen" : [ "COLLECTE", "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "STRING"} ],"listValues" : [ "HC", "POST", "LUP", "LMIDDLE", "LDOWN", "CUP", "CMIDDLE", "CDOWN", "RUP", "RMIDDLE", "RDOWN" ]});

//String value
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "stopOk",          "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "PERS-ACT-DEF-POS", "listScreen" : [ "COLLECTE", "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "STRING"} ],"listValues" : [ "LUP", "LMIDDLE", "LDOWN", "CUP", "CMIDDLE", "CDOWN", "RUP", "RMIDDLE", "RDOWN" ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "goalConceded",    "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "PERS-ACT-DEF-NEG", "listScreen" : [ "COLLECTE", "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "STRING"} ],"listValues" : [ "LUP", "LMIDDLE", "LDOWN", "CUP", "CMIDDLE", "CDOWN", "RUP", "RMIDDLE", "RDOWN" ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "originShootDef",  "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "PERS-ACT-DEF-POS", "listScreen" : [ "COLLECTE", "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "STRING"} ],"listValues" : [ "LWING", "RWING", "BACKLEFT9", "BACKLEFT6", "CENTER9", "CENTER6", "BACKRIGHT9", "BACKRIGHT6"]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "goalScored",      "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "PERS-ACT-OFF-POS", "listScreen" : [ "COLLECTE", "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "STRING"} ],"listValues" : [ "HC", "POST", "LUP", "LMIDDLE", "LDOWN", "CUP", "CMIDDLE", "CDOWN", "RUP", "RMIDDLE", "RDOWN" ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "originShootAtt",  "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "PERS-ACT-OFF-POS", "listScreen" : [ "COLLECTE", "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "STRING"} ],"listValues" : [ "LWING", "RWING", "BACKLEFT9", "BACKLEFT6", "CENTER9", "CENTER6", "BACKRIGHT9", "BACKRIGHT6"]});

/* *******************
 * stat event team
 ******************* */
//Int Value
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "defenseSysUs",      "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "TEAM-INFO-GAME", "listScreen" : [ "COLLECTE", "CPT-TEAMSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "STRING"} ], "listValues" : [ "0-6", "1-5", "2-4", "3-3", "1-2-3" ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "defenseSystemThem", "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "TEAM-INFO-GAME", "listScreen" : [ "COLLECTE", "CPT-TEAMSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "STRING"} ], "listValues" : [ "0-6", "1-5", "2-4", "3-3", "1-2-3" ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "timeAttack",        "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "TEAM-INFO-GAME", "listScreen" : [ "COLLECTE", "CPT-TEAMSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "INT"} ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "timeDefense",       "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "TEAM-INFO-GAME", "listScreen" : [ "COLLECTE", "CPT-TEAMSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "INT"} ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "timeoutUs",         "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "TEAM-INFO-GAME", "listScreen" : [ "COLLECTE", "CPT-TEAMSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "INT"} ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "timeoutThem",       "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA", "indicatorType" : "TEAM-INFO-GAME", "listScreen" : [ "COLLECTE", "CPT-TEAMSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "INT"} ]});

/* *******************
 * stat event
 ******************* */
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "audience",        "activityId" : "ACT-HAND","countryId" : "CNTR-250-FR-FRA","listScreen" : [ "CPT-EVENTSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "INT"} ]});
