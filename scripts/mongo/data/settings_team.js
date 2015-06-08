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
    "structureId" : "541168295971d35c1f2d1b60",
    "categoryAge" : {
        "code" : "sen",
        "label" : "Senior Gars"
    }    
});

//"_id" : ObjectId().valueOf(),
db.Team.insert({
    "_id" : "552d5e08644a77b3a20afdff",
    "label" : "Team B",
    "structureId" : "541168295971d35c1f2d1b60",
    "categoryAge" : {
        "code" : "sen",
        "label" : "Senior Gars"
    }    
});
