//////////////////////////////////////////////////////////
/* 
 * SCRIPT MONGO DB 
 * INJECTION Effective
 * V1.2
 * 
 * This script creates documents for collections :
 * - Person
 * 
 * AUTHOR : Christophe kervella pour QaoBee
 */
//////////////////////////////////////////////////////////
/* 
 * Vidage de la collection Effective (Club Dunkerque handball sen)
 */
db.Effective.remove({ "structureId" : "541168295971d35c1f2d1b5e",
                      "seasonCode" : "SAI-2014" , 
                      "categoryAge.code" : "sen"
});

/** ************************************************************* */
/*
 * Alimentation Effective (Club Dunkerque Handball)
 */
/** ************************************************************* */
db.Effective.insert({
    "_id" : "550b31f925da07681592db22",
    "structureId" : "541168295971d35c1f2d1b5e",
    "seasonCode" : "SAI-2014" , 
    "categoryAge" : {
        "code" : "sen",
        "label" : "Senior",
        "ageMax" : NumberInt(34),
        "ageMin" : NumberInt(20),
        "genre" : "gender.male"
    },
    "members" : ["541d2c5fb3f78c0317eea2be", "541d3136f61fbf69868c1214", "541d3136f61fbf69868c1215",
                 "541d3136f61fbf69868c1216", "541d3136f61fbf69868c1217", "541d3136f61fbf69868c1218",
                 "541d3136f61fbf69868c1219", "541d3136f61fbf69868c121a", "541d3136f61fbf69868c121b",
                 "541d3136f61fbf69868c121c", "5509f71adb8f8b6e2f51f4d4", "5509f71adb8f8b6e2f51f4d5",
                 "5509f71adb8f8b6e2f51f4d6", "5509f71adb8f8b6e2f51f4d7", "550a02acdb8f8b6e2f51f4da"
    ]
});

//////////////////////////////////////////////////////////
/* 
 * Vidage de la collection Effective (Club Cesson handball sen)
 */
db.Effective.remove({ "structureId" : "541168295971d35c1f2d1b5f",
                      "seasonCode" : "SAI-2014" , 
                      "categoryAge.code" : "sen"
});

/** ************************************************************* */
/*
 * Alimentation Effective (Club Dunkerque Handball)
 */
/** ************************************************************* */
db.Effective.insert({
    "_id" : "550b31f925da07681592db23",
    "structureId" : "541168295971d35c1f2d1b5f",
    "seasonCode" : "SAI-2014" , 
    "categoryAge" : {
        "code" : "sen",
        "label" : "Senior",
        "ageMax" : NumberInt(34),
        "ageMin" : NumberInt(20),
        "genre" : "gender.male"
    },
    "members" : ["550a05dadb8f8b6e2f51f4db", "550a05e3db8f8b6e2f51f4dc", "550a05e9db8f8b6e2f51f4dd",
                 "550a05f7db8f8b6e2f51f4de", "550a0600db8f8b6e2f51f4df", "550a0606db8f8b6e2f51f4e0",
                 "550a060ddb8f8b6e2f51f4e1", "550a0614db8f8b6e2f51f4e2", "550a061bdb8f8b6e2f51f4e3",
                 "550a0620db8f8b6e2f51f4e4", "550a0620db8f8b6e2f51f4e5", "550a0620db8f8b6e2f51f4e6",
                 "550a0620db8f8b6e2f51f4e7", "550a0620db8f8b6e2f51f4e8"
    ]
});