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
db.IndicatorCfg.remove({"activityId" : "ACT-FOOT","countryId" : "CNTR-250-FR-FRA"});


/* *******************
 * Characteristics of person
 ******************* */
//Int Value
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "hightPerson",     "activityId" : "ACT-FOOT","countryId" : "CNTR-250-FR-FRA","listScreen" : [ "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "value","type" : "INT"} ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "weightPerson",    "activityId" : "ACT-FOOT","countryId" : "CNTR-250-FR-FRA","listScreen" : [ "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "value","type" : "INT"} ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "attendance",      "activityId" : "ACT-FOOT","countryId" : "CNTR-250-FR-FRA","listScreen" : [ "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "INT"} ], "listValues" : [ 0, 1 ]});

//String Value
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "laterality",      "activityId" : "ACT-FOOT","countryId" : "CNTR-250-FR-FRA","listScreen" : [ "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "value", "type" : "STRING"} ],"listValues" : [ "lefthanded", "righthanded", "ambidextrous" ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "stateForm",       "activityId" : "ACT-FOOT","countryId" : "CNTR-250-FR-FRA","listScreen" : [ "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "value", "type" : "STRING"} ],"listValues" : [ "poor", "middling", "good", "excellent" ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "positionType",    "activityId" : "ACT-FOOT","countryId" : "CNTR-250-FR-FRA","listScreen" : [ "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "value", "type" : "STRING"} ],"listValues" : [ "defender", "forward", "goalkeeper", "midfielder" ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "unavailability",  "activityId" : "ACT-FOOT","countryId" : "CNTR-250-FR-FRA","listScreen" : [ "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "value", "type" : "STRING"} ],"listValues" : [ "available", "injured", "suspended", "absent" ]});

/* *******************
 * stat event person
 ******************* */
//Int Value
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "goalscored",      "activityId" : "ACT-FOOT","countryId" : "CNTR-250-FR-FRA","listScreen" : [ "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "INT"} ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "goalconceded",    "activityId" : "ACT-FOOT","countryId" : "CNTR-250-FR-FRA","listScreen" : [ "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "INT"} ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "yellowCard",      "activityId" : "ACT-FOOT","countryId" : "CNTR-250-FR-FRA","listScreen" : [ "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "INT"} ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "redCard",         "activityId" : "ACT-FOOT","countryId" : "CNTR-250-FR-FRA","listScreen" : [ "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "INT"} ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "playtime",        "activityId" : "ACT-FOOT","countryId" : "CNTR-250-FR-FRA","listScreen" : [ "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "INT"} ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "substitue",       "activityId" : "ACT-FOOT","countryId" : "CNTR-250-FR-FRA","listScreen" : [ "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "INT"} ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "holder",          "activityId" : "ACT-FOOT","countryId" : "CNTR-250-FR-FRA","listScreen" : [ "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "INT"} ]});
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "eventNotePerson", "activityId" : "ACT-FOOT","countryId" : "CNTR-250-FR-FRA","listScreen" : [ "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "INT"} ]});

/* *******************
 * stat event
 ******************* */
db.IndicatorCfg.insert({"_id" : new ObjectId().valueOf(),"code" : "audience",        "activityId" : "ACT-FOOT","countryId" : "CNTR-250-FR-FRA","listScreen" : [ "EFF-PLAYERSHEET" ],"description" : null,"listField" : [ {"name" : "code","type" : "STRING"}, {"name" : "timer","type" : "LONG"}, {"name" : "owner","type" : "STRING"}, {"name" : "producter","type" : "ARRAY"}, {"name" : "structureId","type" : "STRING"}, {"name" : "activityId","type" : "STRING"}, {"name" : "chunkKey","type" : "LONG"}, {"name" : "eventId","type" : "STRING"}, {"name" : "chrono","type" : "LONG"},{"name" : "value","type" : "INT"} ]});

