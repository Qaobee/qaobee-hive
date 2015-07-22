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
 * INJECTION CHAMPIONSHIP
 * V1.1
 * 
 * This script creates documents for the collection :
 * - Championship
 * 
 * AUTHOR : Jérôme ROUE for QaoBee
 */
//////////////////////////////////////////////////////////

/*
 * Empty the collection
 */
db.Championship.remove({});

db.Championship.insert({
    "_id" : "559ebfb499f07aa6f04dec76",
    "label" : "Championnat du bout du monde",
    "levelGame" : {
    	"code" : "R",
    	"label" : "regional"
    },
    "subLevelGame" : "Honneur régionale",
    "pool" : "Poule A",
    "instance" : "Ligue de Bretagne",
    "activityId" : "ACT-HAND",
    "categoryAge" : {
        "code" : "sen",
        "label" : "Senior",
        "ageMax" : NumberInt(34),
        "ageMin" : NumberInt(20),
        "genre" : "gender.male"
    },
    "seasonCode" : "SAI-2014",
    "participants" : [ {
        "id" : "ID-TEAM-CHAMBERY",
        "name" : "CHAMBERY SAVOIE HB",
        "structureId" : "CHAMBERYSAVOIEHB",
        "type" : "team"
    }, 
    {
        "id" : "ID-TEAM-CESSON",
        "name" : "CRMHB Cesson-Sévigné",
        "structureId" : "541168295971d35c1f2d1b5e",
        "type" : "team"
    },
    {
        "id" : "ID-TEAM-DUNKERQUE",
        "name" : "USDK Dunkerque",
        "structureId" : "541168295971d35c1f2d1b5f",
        "type" : "team"
    },
    {
        "id" : "ID-TEAM-CRETEIL",
        "label" : "US CRETEIL HANDBALL",
        "structureId" : "USCRETEILHANDBALL",
        "type" : "team"
    }],
    "journeys" : [ 
        {
	    	"label" : "Championnat : Journée 20",
	    	"startDate" : NumberLong(1428518700000), 
	    	"endDate" : NumberLong(1428525900000),
	    	"matchs" : [
	    	    /* FDM 1 : CHEMBERY - CESSON*/
	    	    {
	    		"code" : "fdm1",
	    		"dateTime" : "J20 14h00",
	    		"address" : { "place" : "Le phare - 800 Avenue du Grand Arietaz", "zipcode" : "73000", "city" : "Chambéry", "country" : "France" },
	    		"participants" : [{"id" : "ID-TEAM-CHAMBERY", "name" : "CHAMBERY SAVOIE HB", "structureId" : "CHAMBERYSAVOIEHB", "type":"teamHome"}, 
	    	                      {"id" : "ID-TEAM-CESSON", "name" : "CRMHB Cesson-Sévigné", "structureId" : "541168295971d35c1f2d1b5e", "type":"teamVisitor"},
	    	                      {"id" : "ID-PHARE-CHAMBERY", "name" : "Le phare", "structureId" : "CHAMBERYSAVOIEHB", "type":"infrastructure"}],
	    	    "result" : {"scoreHome" : NumberInt(12), "scoreVisitor" : NumberInt(34)}
	    	    },
	    	    /* FDM 2 : DUNKERQUE - CRETEIL*/
	    	    {
	    		"code" : "fdm2",
	    		"dateTime" : "J20 16h00",
	    		"address" : { "place" : "Le phare - 800 Avenue du Grand Arietaz", "zipcode" : "73000", "city" : "Chambéry", "country" : "France" },
	    		"participants" : [{"id" : "ID-TEAM-DUNKERQUE", "name" : "USDK Dunkerque", "structureId" : "541168295971d35c1f2d1b5f", "type" : "teamHome"},
	    		                  {"id" : "ID-TEAM-CRETEIL", "label" : "US CRETEIL HANDBALL", "structureId" : "USCRETEILHANDBALL", "type" : "teamVisitor"},
	    	                      {"id" : "ID-PHARE-CHAMBERY", "name" : "Le phare", "structureId" : "CHAMBERYSAVOIEHB", "type":"infrastructure"}],
	    	    "result" : {"scoreHome" : NumberInt(30), "scoreVisitor" : NumberInt(28)}
	    	    }
	    	],
	    	"ranking" : [
	    	    {
	    	    	"place" : NumberInt(4), 
	    	        "id" : "ID-TEAM-CHAMBERY", "name" : "CHAMBERY SAVOIE HB", "structureId" : "CHAMBERYSAVOIEHB",
	    	        "nbPlayed" : NumberInt(1), "nbWon" : NumberInt(0), "nbLost" : NumberInt(1), "nbDraw" : NumberInt(0), 
	        	    "sumScored" : NumberInt(12), "sumConceded" : NumberInt(34), "goalAverage" : NumberInt(12-34),
	        	    "points" : NumberInt(1)
	    	    }, 
	    	    {
	    	    	"place" : NumberInt(1),
	    	        "id" : "ID-TEAM-CESSON", "name" : "CRMHB Cesson-Sévigné", "structureId" : "541168295971d35c1f2d1b5e",
	    	        "nbPlayed" : NumberInt(1), "nbWon" : NumberInt(1), "nbLost" : NumberInt(0), "nbDraw" : NumberInt(0), 
	        	    "sumScored" : NumberInt(34), "sumConceded" : NumberInt(12), "goalAverage" : NumberInt(34-12),
	        	    "points" : NumberInt(3)
	    	    },
	    	    {
	    	    	"place" : NumberInt(2),
	    	        "id" : "ID-TEAM-DUNKERQUE", "name" : "USDK Dunkerque", "structureId" : "541168295971d35c1f2d1b5f",
	    	        "nbPlayed" : NumberInt(1), "nbWon" : NumberInt(1), "nbLost" : NumberInt(0), "nbDraw" : NumberInt(0), 
	        	    "sumScored" : NumberInt(30), "sumConceded" : NumberInt(28), "goalAverage" : NumberInt(30-28),
	        	    "points" : NumberInt(3)
	    	    },
	    	    {
	    	    	"place" : NumberInt(3),
	    	        "id" : "ID-TEAM-CRETEIL", "label" : "US CRETEIL HANDBALL", "structureId" : "USCRETEILHANDBALL",
	    	        "nbPlayed" : NumberInt(1), "nbWon" : NumberInt(0), "nbLost" : NumberInt(1), "nbDraw" : NumberInt(0), 
	        	    "sumScored" : NumberInt(28), "sumConceded" : NumberInt(30), "goalAverage" : NumberInt(28-30),
	        	    "points" : NumberInt(1)
	    	    }
	    	]
        },
        {
	    	"label" : "Championnat : Journée 21",
	    	"startDate" : NumberLong(1429123500000), 
	    	"endDate" : NumberLong(1429130700000),
	    	"matchs" : [
	    	    /* FDM 3 : CHEMBERY - DUNKERQUE*/
	    	    {
	    		"code" : "fdm3",
	    		"dateTime" : "J21 14h00",
	    		"address" : { "place" : "Le phare - 800 Avenue du Grand Arietaz", "zipcode" : "73000", "city" : "Chambéry", "country" : "France" },
	    		"participants" : [{"id" : "ID-TEAM-CHAMBERY", "name" : "CHAMBERY SAVOIE HB", "structureId" : "CHAMBERYSAVOIEHB", "type":"teamHome"}, 
	    		                  {"id" : "ID-TEAM-DUNKERQUE", "name" : "USDK Dunkerque", "structureId" : "541168295971d35c1f2d1b5f", "type" : "teamVisitor"},
	    	                      {"id" : "ID-PHARE-CHAMBERY", "name" : "Le phare", "structureId" : "CHAMBERYSAVOIEHB", "type":"infrastructure"}],
	    	    "result" : {"scoreHome" : NumberInt(16), "scoreVisitor" : NumberInt(20)}
	    	    },
	    	    /* FDM 4 : CRETEIL - CESSON*/
	    	    {
	    		"code" : "fdm4",
	    		"dateTime" : "J21 16h00",
	    		"address" : { "place" : "Le phare - 800 Avenue du Grand Arietaz", "zipcode" : "73000", "city" : "Chambéry", "country" : "France" },
	    		"participants" : [{"id" : "ID-TEAM-CESSON", "name" : "CRMHB Cesson-Sévigné", "structureId" : "541168295971d35c1f2d1b5e", "type":"teamHome"},
	    		                  {"id" : "ID-TEAM-CRETEIL", "label" : "US CRETEIL HANDBALL", "structureId" : "USCRETEILHANDBALL", "type" : "teamVisitor"},
	    	                      {"id" : "ID-PHARE-CHAMBERY", "name" : "Le phare", "structureId" : "CHAMBERYSAVOIEHB", "type":"infrastructure"}],
	    	    "result" : {"scoreHome" : NumberInt(12), "scoreVisitor" : NumberInt(12)}
	    	    }
	    	],
	    	"ranking" : [
	    	    {
	    	    	"place" : NumberInt(4), 
	    	        "id" : "ID-TEAM-CHAMBERY", "name" : "CHAMBERY SAVOIE HB", "structureId" : "CHAMBERYSAVOIEHB",
	    	        "nbPlayed" : NumberInt(2), "nbWon" : NumberInt(0), "nbLost" : NumberInt(2), "nbDraw" : NumberInt(0), 
	        	    "sumScored" : NumberInt(12+16), "sumConceded" : NumberInt(34+20), "goalAverage" : NumberInt(12-34+16-20),
	        	    "points" : NumberInt(1+1)
	    	    }, 
	    	    {
	    	    	"place" : NumberInt(2),
	    	        "id" : "ID-TEAM-CESSON", "name" : "CRMHB Cesson-Sévigné", "structureId" : "541168295971d35c1f2d1b5e",
	    	        "nbPlayed" : NumberInt(2), "nbWon" : NumberInt(1), "nbLost" : NumberInt(0), "nbDraw" : NumberInt(1),
	        	    "sumScored" : NumberInt(34+12), "sumConceded" : NumberInt(12+12), "goalAverage" : NumberInt(34-12+12-12),
	        	    "points" : NumberInt(3+2)
	    	    },
	    	    {
	    	    	"place" : NumberInt(1),
	    	        "id" : "ID-TEAM-DUNKERQUE", "name" : "USDK Dunkerque", "structureId" : "541168295971d35c1f2d1b5f",
	    	        "nbPlayed" : NumberInt(2), "nbWon" : NumberInt(2), "nbLost" : NumberInt(0), "nbDraw" : NumberInt(0), 
	        	    "sumScored" : NumberInt(30+20), "sumConceded" : NumberInt(28+16), "goalAverage" : NumberInt(30-28+20-16),
	        	    "points" : NumberInt(3+3)
	    	    },
	    	    {
	    	    	"place" : NumberInt(3),
	    	        "id" : "ID-TEAM-CRETEIL", "label" : "US CRETEIL HANDBALL", "structureId" : "USCRETEILHANDBALL",
	    	        "nbPlayed" : NumberInt(2), "nbWon" : NumberInt(0), "nbLost" : NumberInt(1), "nbDraw" : NumberInt(1), 
	        	    "sumScored" : NumberInt(28+12), "sumConceded" : NumberInt(30+12), "goalAverage" : NumberInt(28-30+12-12),
	        	    "points" : NumberInt(1+2)
	    	    }
	    	]
        },
        {
	    	"label" : "Championnat : Journée 22",
	    	"startDate" : NumberLong(1429123500000), 
	    	"endDate" : NumberLong(1429130700000),
	    	"matchs" : [
	    	    /* FDM 5 : CHEMBERY - CRETEIL*/
	    	    {
	    		"code" : "fdm5",
	    		"dateTime" : "J22 14h00",
	    		"address" : { "place" : "Le phare - 800 Avenue du Grand Arietaz", "zipcode" : "73000", "city" : "Chambéry", "country" : "France" },
	    		"participants" : [{"id" : "ID-TEAM-CRETEIL", "label" : "US CRETEIL HANDBALL", "structureId" : "USCRETEILHANDBALL", "type" : "teamHome"},
	    		                  {"id" : "ID-TEAM-CHAMBERY", "name" : "CHAMBERY SAVOIE HB", "structureId" : "CHAMBERYSAVOIEHB", "type":"teamVisitor"},
	    	                      {"id" : "ID-PHARE-CHAMBERY", "name" : "Le phare", "structureId" : "CHAMBERYSAVOIEHB", "type":"infrastructure"}],
	    	    "result" : {"scoreHome" : NumberInt(14), "scoreVisitor" : NumberInt(28)}
	    	    },
	    	    /* FDM 6 : DUNKERQUE - CESSON*/
	    	    {
	    		"code" : "fdm6",
	    		"dateTime" : "J22 16h00",
	    		"address" : { "place" : "Le phare - 800 Avenue du Grand Arietaz", "zipcode" : "73000", "city" : "Chambéry", "country" : "France" },
	    		"participants" : [{"id" : "ID-TEAM-DUNKERQUE", "name" : "USDK Dunkerque", "structureId" : "541168295971d35c1f2d1b5f", "type" : "teamHome"},
	    		                  {"id" : "ID-TEAM-CESSON", "name" : "CRMHB Cesson-Sévigné", "structureId" : "541168295971d35c1f2d1b5e", "type":"teamVisitor"},
	    	                      {"id" : "ID-PHARE-CHAMBERY", "name" : "Le phare", "structureId" : "CHAMBERYSAVOIEHB", "type":"infrastructure"}],
	    	    "result" : {"scoreHome" : NumberInt(17), "scoreVisitor" : NumberInt(13)}
	    	    }
	    	],
	    	"ranking" : [
	    	    {
	    	    	"place" : NumberInt(4), 
	    	        "id" : "ID-TEAM-CHAMBERY", "name" : "CHAMBERY SAVOIE HB", "structureId" : "CHAMBERYSAVOIEHB",
	    	        "nbPlayed" : NumberInt(3), "nbWon" : NumberInt(0), "nbLost" : NumberInt(3), "nbDraw" : NumberInt(0), 
	        	    "sumScored" : NumberInt(12+16+28), "sumConceded" : NumberInt(34+20+14), "goalAverage" : NumberInt(12-34+16-20+28-14),
	        	    "points" : NumberInt(1+1+1)
	    	    }, 
	    	    {
	    	    	"place" : NumberInt(2),
	    	        "id" : "ID-TEAM-CESSON", "name" : "CRMHB Cesson-Sévigné", "structureId" : "541168295971d35c1f2d1b5e",
	    	        "nbPlayed" : NumberInt(3), "nbWon" : NumberInt(1), "nbLost" : NumberInt(1), "nbDraw" : NumberInt(1),
	        	    "sumScored" : NumberInt(34+12+13), "sumConceded" : NumberInt(12+12+17), "goalAverage" : NumberInt(34-12+12-12+13-17),
	        	    "points" : NumberInt(3+2+1)
	    	    },
	    	    {
	    	    	"place" : NumberInt(1),
	    	        "id" : "ID-TEAM-DUNKERQUE", "name" : "USDK Dunkerque", "structureId" : "541168295971d35c1f2d1b5f",
	    	        "nbPlayed" : NumberInt(3), "nbWon" : NumberInt(3), "nbLost" : NumberInt(0), "nbDraw" : NumberInt(0), 
	        	    "sumScored" : NumberInt(30+20+17), "sumConceded" : NumberInt(28+16+13), "goalAverage" : NumberInt(30-28+20-16+17-13),
	        	    "points" : NumberInt(3+3+3)
	    	    },
	    	    {
	    	    	"place" : NumberInt(3),
	    	        "id" : "ID-TEAM-CRETEIL", "label" : "US CRETEIL HANDBALL", "structureId" : "USCRETEILHANDBALL",
	    	        "nbPlayed" : NumberInt(3), "nbWon" : NumberInt(0), "nbLost" : NumberInt(2), "nbDraw" : NumberInt(1), 
	        	    "sumScored" : NumberInt(28+12+14), "sumConceded" : NumberInt(30+12+28), "goalAverage" : NumberInt(28-30+12-12+14-28),
	        	    "points" : NumberInt(1+2+1)
	    	    }
	    	]
        }
    
    ]
});


db.Championship.insert({
    "_id" : "55aff929be4d1e107c60f690",
    "label" : "Championnat de la crepe",
    "levelGame" : {
    	"code" : "R",
    	"label" : "regional"
    },
    "subLevelGame" : "Honneur régionale",
    "pool" : "Poule B",
    "instance" : "Ligue de Bretagne",
    "activityId" : "ACT-HAND",
    "categoryAge" : {
        "code" : "sen",
        "label" : "Senior",
        "ageMax" : NumberInt(50),
        "ageMin" : NumberInt(35),
        "genre" : "gender.male"
    },
    "seasonCode" : "SAI-2014",
    "participants" : [ {
        "id" : "ID-TEAM-CHAMBERY",
        "name" : "CHAMBERY SAVOIE HB",
        "structureId" : "CHAMBERYSAVOIEHB",
        "type" : "team"
    }, 
    {
        "id" : "ID-TEAM-CESSON",
        "name" : "CRMHB Cesson-Sévigné",
        "structureId" : "541168295971d35c1f2d1b5e",
        "type" : "team"
    },
    {
        "id" : "ID-TEAM-DUNKERQUE",
        "name" : "USDK Dunkerque",
        "structureId" : "541168295971d35c1f2d1b5f",
        "type" : "team"
    },
    {
        "id" : "ID-TEAM-CRETEIL",
        "label" : "US CRETEIL HANDBALL",
        "structureId" : "USCRETEILHANDBALL",
        "type" : "team"
    }],
    "journeys" : [ 
        {
	    	"label" : "Championnat : Journée 10",
	    	"startDate" : NumberLong(1428518700000), 
	    	"endDate" : NumberLong(1428525900000),
	    	"matchs" : [
	    	    /* FDM 1 : CHEMBERY - CESSON*/
	    	    {
	    		"code" : "fdm1",
	    		"dateTime" : "J10 14h00",
	    		"address" : { "place" : "Le phare - 800 Avenue du Grand Arietaz", "zipcode" : "73000", "city" : "Chambéry", "country" : "France" },
	    		"participants" : [{"id" : "ID-TEAM-CHAMBERY", "name" : "CHAMBERY SAVOIE HB", "structureId" : "CHAMBERYSAVOIEHB", "type":"teamHome"}, 
	    	                      {"id" : "ID-TEAM-CESSON", "name" : "CRMHB Cesson-Sévigné", "structureId" : "541168295971d35c1f2d1b5e", "type":"teamVisitor"},
	    	                      {"id" : "ID-PHARE-CHAMBERY", "name" : "Le phare", "structureId" : "CHAMBERYSAVOIEHB", "type":"infrastructure"}],
	    	    "result" : {"scoreHome" : NumberInt(12), "scoreVisitor" : NumberInt(34)}
	    	    },
	    	    /* FDM 2 : DUNKERQUE - CRETEIL*/
	    	    {
	    		"code" : "fdm2",
	    		"dateTime" : "J10 16h00",
	    		"address" : { "place" : "Le phare - 800 Avenue du Grand Arietaz", "zipcode" : "73000", "city" : "Chambéry", "country" : "France" },
	    		"participants" : [{"id" : "ID-TEAM-DUNKERQUE", "name" : "USDK Dunkerque", "structureId" : "541168295971d35c1f2d1b5f", "type" : "teamHome"},
	    		                  {"id" : "ID-TEAM-CRETEIL", "label" : "US CRETEIL HANDBALL", "structureId" : "USCRETEILHANDBALL", "type" : "teamVisitor"},
	    	                      {"id" : "ID-PHARE-CHAMBERY", "name" : "Le phare", "structureId" : "CHAMBERYSAVOIEHB", "type":"infrastructure"}],
	    	    "result" : {"scoreHome" : NumberInt(30), "scoreVisitor" : NumberInt(28)}
	    	    }
	    	],
	    	"ranking" : [
	    	    {
	    	    	"place" : NumberInt(4), 
	    	        "id" : "ID-TEAM-CHAMBERY", "name" : "CHAMBERY SAVOIE HB", "structureId" : "CHAMBERYSAVOIEHB",
	    	        "nbPlayed" : NumberInt(1), "nbWon" : NumberInt(0), "nbLost" : NumberInt(1), "nbDraw" : NumberInt(0), 
	        	    "sumScored" : NumberInt(12), "sumConceded" : NumberInt(34), "goalAverage" : NumberInt(12-34),
	        	    "points" : NumberInt(1)
	    	    }, 
	    	    {
	    	    	"place" : NumberInt(1),
	    	        "id" : "ID-TEAM-CESSON", "name" : "CRMHB Cesson-Sévigné", "structureId" : "541168295971d35c1f2d1b5e",
	    	        "nbPlayed" : NumberInt(1), "nbWon" : NumberInt(1), "nbLost" : NumberInt(0), "nbDraw" : NumberInt(0), 
	        	    "sumScored" : NumberInt(34), "sumConceded" : NumberInt(12), "goalAverage" : NumberInt(34-12),
	        	    "points" : NumberInt(3)
	    	    },
	    	    {
	    	    	"place" : NumberInt(2),
	    	        "id" : "ID-TEAM-DUNKERQUE", "name" : "USDK Dunkerque", "structureId" : "541168295971d35c1f2d1b5f",
	    	        "nbPlayed" : NumberInt(1), "nbWon" : NumberInt(1), "nbLost" : NumberInt(0), "nbDraw" : NumberInt(0), 
	        	    "sumScored" : NumberInt(30), "sumConceded" : NumberInt(28), "goalAverage" : NumberInt(30-28),
	        	    "points" : NumberInt(3)
	    	    },
	    	    {
	    	    	"place" : NumberInt(3),
	    	        "id" : "ID-TEAM-CRETEIL", "label" : "US CRETEIL HANDBALL", "structureId" : "USCRETEILHANDBALL",
	    	        "nbPlayed" : NumberInt(1), "nbWon" : NumberInt(0), "nbLost" : NumberInt(1), "nbDraw" : NumberInt(0), 
	        	    "sumScored" : NumberInt(28), "sumConceded" : NumberInt(30), "goalAverage" : NumberInt(28-30),
	        	    "points" : NumberInt(1)
	    	    }
	    	]
        },
        {
	    	"label" : "Championnat : Journée 11",
	    	"startDate" : NumberLong(1429123500000), 
	    	"endDate" : NumberLong(1429130700000),
	    	"matchs" : [
	    	    /* FDM 3 : CHEMBERY - DUNKERQUE*/
	    	    {
	    		"code" : "fdm3",
	    		"dateTime" : "J11 14h00",
	    		"address" : { "place" : "Le phare - 800 Avenue du Grand Arietaz", "zipcode" : "73000", "city" : "Chambéry", "country" : "France" },
	    		"participants" : [{"id" : "ID-TEAM-CHAMBERY", "name" : "CHAMBERY SAVOIE HB", "structureId" : "CHAMBERYSAVOIEHB", "type":"teamHome"}, 
	    		                  {"id" : "ID-TEAM-DUNKERQUE", "name" : "USDK Dunkerque", "structureId" : "541168295971d35c1f2d1b5f", "type" : "teamVisitor"},
	    	                      {"id" : "ID-PHARE-CHAMBERY", "name" : "Le phare", "structureId" : "CHAMBERYSAVOIEHB", "type":"infrastructure"}],
	    	    "result" : {"scoreHome" : NumberInt(16), "scoreVisitor" : NumberInt(20)}
	    	    },
	    	    /* FDM 4 : CRETEIL - CESSON*/
	    	    {
	    		"code" : "fdm4",
	    		"dateTime" : "J11 16h00",
	    		"address" : { "place" : "Le phare - 800 Avenue du Grand Arietaz", "zipcode" : "73000", "city" : "Chambéry", "country" : "France" },
	    		"participants" : [{"id" : "ID-TEAM-CESSON", "name" : "CRMHB Cesson-Sévigné", "structureId" : "541168295971d35c1f2d1b5e", "type":"teamHome"},
	    		                  {"id" : "ID-TEAM-CRETEIL", "label" : "US CRETEIL HANDBALL", "structureId" : "USCRETEILHANDBALL", "type" : "teamVisitor"},
	    	                      {"id" : "ID-PHARE-CHAMBERY", "name" : "Le phare", "structureId" : "CHAMBERYSAVOIEHB", "type":"infrastructure"}],
	    	    "result" : {"scoreHome" : NumberInt(12), "scoreVisitor" : NumberInt(12)}
	    	    }
	    	],
	    	"ranking" : [
	    	    {
	    	    	"place" : NumberInt(4), 
	    	        "id" : "ID-TEAM-CHAMBERY", "name" : "CHAMBERY SAVOIE HB", "structureId" : "CHAMBERYSAVOIEHB",
	    	        "nbPlayed" : NumberInt(2), "nbWon" : NumberInt(0), "nbLost" : NumberInt(2), "nbDraw" : NumberInt(0), 
	        	    "sumScored" : NumberInt(12+16), "sumConceded" : NumberInt(34+20), "goalAverage" : NumberInt(12-34+16-20),
	        	    "points" : NumberInt(1+1)
	    	    }, 
	    	    {
	    	    	"place" : NumberInt(2),
	    	        "id" : "ID-TEAM-CESSON", "name" : "CRMHB Cesson-Sévigné", "structureId" : "541168295971d35c1f2d1b5e",
	    	        "nbPlayed" : NumberInt(2), "nbWon" : NumberInt(1), "nbLost" : NumberInt(0), "nbDraw" : NumberInt(1),
	        	    "sumScored" : NumberInt(34+12), "sumConceded" : NumberInt(12+12), "goalAverage" : NumberInt(34-12+12-12),
	        	    "points" : NumberInt(3+2)
	    	    },
	    	    {
	    	    	"place" : NumberInt(1),
	    	        "id" : "ID-TEAM-DUNKERQUE", "name" : "USDK Dunkerque", "structureId" : "541168295971d35c1f2d1b5f",
	    	        "nbPlayed" : NumberInt(2), "nbWon" : NumberInt(2), "nbLost" : NumberInt(0), "nbDraw" : NumberInt(0), 
	        	    "sumScored" : NumberInt(30+20), "sumConceded" : NumberInt(28+16), "goalAverage" : NumberInt(30-28+20-16),
	        	    "points" : NumberInt(3+3)
	    	    },
	    	    {
	    	    	"place" : NumberInt(3),
	    	        "id" : "ID-TEAM-CRETEIL", "label" : "US CRETEIL HANDBALL", "structureId" : "USCRETEILHANDBALL",
	    	        "nbPlayed" : NumberInt(2), "nbWon" : NumberInt(0), "nbLost" : NumberInt(1), "nbDraw" : NumberInt(1), 
	        	    "sumScored" : NumberInt(28+12), "sumConceded" : NumberInt(30+12), "goalAverage" : NumberInt(28-30+12-12),
	        	    "points" : NumberInt(1+2)
	    	    }
	    	]
        },
        {
	    	"label" : "Championnat : Journée 12",
	    	"startDate" : NumberLong(1429123500000), 
	    	"endDate" : NumberLong(1429130700000),
	    	"matchs" : [
	    	    /* FDM 5 : CHEMBERY - CRETEIL*/
	    	    {
	    		"code" : "fdm5",
	    		"dateTime" : "J12 14h00",
	    		"address" : { "place" : "Le phare - 800 Avenue du Grand Arietaz", "zipcode" : "73000", "city" : "Chambéry", "country" : "France" },
	    		"participants" : [{"id" : "ID-TEAM-CRETEIL", "label" : "US CRETEIL HANDBALL", "structureId" : "USCRETEILHANDBALL", "type" : "teamHome"},
	    		                  {"id" : "ID-TEAM-CHAMBERY", "name" : "CHAMBERY SAVOIE HB", "structureId" : "CHAMBERYSAVOIEHB", "type":"teamVisitor"},
	    	                      {"id" : "ID-PHARE-CHAMBERY", "name" : "Le phare", "structureId" : "CHAMBERYSAVOIEHB", "type":"infrastructure"}],
	    	    "result" : {"scoreHome" : NumberInt(14), "scoreVisitor" : NumberInt(28)}
	    	    },
	    	    /* FDM 6 : DUNKERQUE - CESSON*/
	    	    {
	    		"code" : "fdm6",
	    		"dateTime" : "J12 16h00",
	    		"address" : { "place" : "Le phare - 800 Avenue du Grand Arietaz", "zipcode" : "73000", "city" : "Chambéry", "country" : "France" },
	    		"participants" : [{"id" : "ID-TEAM-DUNKERQUE", "name" : "USDK Dunkerque", "structureId" : "541168295971d35c1f2d1b5f", "type" : "teamHome"},
	    		                  {"id" : "ID-TEAM-CESSON", "name" : "CRMHB Cesson-Sévigné", "structureId" : "541168295971d35c1f2d1b5e", "type":"teamVisitor"},
	    	                      {"id" : "ID-PHARE-CHAMBERY", "name" : "Le phare", "structureId" : "CHAMBERYSAVOIEHB", "type":"infrastructure"}],
	    	    "result" : {"scoreHome" : NumberInt(17), "scoreVisitor" : NumberInt(13)}
	    	    }
	    	],
	    	"ranking" : [
	    	    {
	    	    	"place" : NumberInt(4), 
	    	        "id" : "ID-TEAM-CHAMBERY", "name" : "CHAMBERY SAVOIE HB", "structureId" : "CHAMBERYSAVOIEHB",
	    	        "nbPlayed" : NumberInt(3), "nbWon" : NumberInt(0), "nbLost" : NumberInt(3), "nbDraw" : NumberInt(0), 
	        	    "sumScored" : NumberInt(12+16+28), "sumConceded" : NumberInt(34+20+14), "goalAverage" : NumberInt(12-34+16-20+28-14),
	        	    "points" : NumberInt(1+1+1)
	    	    }, 
	    	    {
	    	    	"place" : NumberInt(2),
	    	        "id" : "ID-TEAM-CESSON", "name" : "CRMHB Cesson-Sévigné", "structureId" : "541168295971d35c1f2d1b5e",
	    	        "nbPlayed" : NumberInt(3), "nbWon" : NumberInt(1), "nbLost" : NumberInt(1), "nbDraw" : NumberInt(1),
	        	    "sumScored" : NumberInt(34+12+13), "sumConceded" : NumberInt(12+12+17), "goalAverage" : NumberInt(34-12+12-12+13-17),
	        	    "points" : NumberInt(3+2+1)
	    	    },
	    	    {
	    	    	"place" : NumberInt(1),
	    	        "id" : "ID-TEAM-DUNKERQUE", "name" : "USDK Dunkerque", "structureId" : "541168295971d35c1f2d1b5f",
	    	        "nbPlayed" : NumberInt(3), "nbWon" : NumberInt(3), "nbLost" : NumberInt(0), "nbDraw" : NumberInt(0), 
	        	    "sumScored" : NumberInt(30+20+17), "sumConceded" : NumberInt(28+16+13), "goalAverage" : NumberInt(30-28+20-16+17-13),
	        	    "points" : NumberInt(3+3+3)
	    	    },
	    	    {
	    	    	"place" : NumberInt(3),
	    	        "id" : "ID-TEAM-CRETEIL", "label" : "US CRETEIL HANDBALL", "structureId" : "USCRETEILHANDBALL",
	    	        "nbPlayed" : NumberInt(3), "nbWon" : NumberInt(0), "nbLost" : NumberInt(2), "nbDraw" : NumberInt(1), 
	        	    "sumScored" : NumberInt(28+12+14), "sumConceded" : NumberInt(30+12+28), "goalAverage" : NumberInt(28-30+12-12+14-28),
	        	    "points" : NumberInt(1+2+1)
	    	    }
	    	]
        }
    
    ]
});
