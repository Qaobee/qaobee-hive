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
 * INJECTION SB_SB_Person
 * V1.2
 * 
 * This script creates documents for collections :
 * - SB_SB_Person
 * 
 * AUTHOR : Christophe kervella pour QaoBee
 */
//////////////////////////////////////////////////////////
/* 
 * Vidage de la collection SB_SB_Person (Club Demo Football sen)
 */
db.SB_SB_Person.remove({ "$and" : [ { "listLicenses.structureId" : "541168295971d35c1f2d1b60"} , 
                              { "listLicenses.listHistoLicense" : 
                                { "$elemMatch" : { "seasonCode" : "SAI-2014" , "categoryAgeCode" : "sen"}}}
]});

/** ************************************************************* */
/*
 * Alimentation SB_SB_Person Club A Football
 */
/** ************************************************************* */
db.SB_Person.insert({
    "_id" : "541d3136f61fbf69868c121d",
    "name" : "Baratelli",
    "firstname" : "Dominique",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(-694832400000),
    "birthcity" : "Paris",
    "birthcountry" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "nationality" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "job" : "Pizzaiolo",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "75000",
        "city" : "Paris",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 01",
        "office" : "",
        "cellphone" : "07 06 00 00 01",
        "email" : "Baratelli@toto.com"
    },
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "poor",
        "weight" : "78",
        "height" : "178",
        "squadnumber" : "1",
        "positionType" : "goalkeeper",
        "laterality" : "righthanded",
    },
    "listLicenses" : [ {
        "numLicense" : "05290771000001",
        "structureId" : "541168295971d35c1f2d1b60",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "B",
                "label" : "Joueur Libre",
                "order" : NumberInt(2)
            }
        }, {
            "dateQualification" : NumberLong(1376776800000),
            "dateInscription" : NumberLong(1376776800000),
            "seasonCode" : "SAI-2013",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "B",
                "label" : "Joueur Libre",
                "order" : NumberInt(2)
            }
        } ]
    } ]
});

db.SB_Person.insert({
    "_id" : "541d3136f61fbf69868c121e",
    "name" : "Castaneda",
    "firstname" : "Jean",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(-403491600000),
    "birthcity" : "Saint-Etienne",
    "birthcountry" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "nationality" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "job" : "Maçon",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "42000",
        "city" : "Saint-Etienne",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 02",
        "office" : "",
        "cellphone" : "07 06 00 00 02",
        "email" : "castaneda@toto.com"
    },
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "excellent",
        "weight" : "90",
        "height" : "190",
        "squadnumber" : "21",
        "positionType" : "goalkeeper",
        "laterality" : "righthanded",
    },
    "physicalFolder" : [ {
        "key" : "explosive",
        "value" : 5
    }, {
        "key" : "speed",
        "value" : 1
    }, {
        "key" : "traction",
        "value" : 1
    }, {
        "key" : "verticaljump",
        "value" : 4
    }, {
        "key" : "endurance",
        "value" : 1
    }, {
        "key" : "equilibrium",
        "value" : 1
    }, {
        "key" : "strenght",
        "value" : 1
    }, {
        "key" : "nimbleness",
        "value" : 1
    } ],
    "technicalFolder" : [ {
        "key" : "pass",
        "value" : 3
    }, {
        "key" : "center",
        "value" : 4
    }, {
        "key" : "corner",
        "value" : 1
    }, {
        "key" : "freekick",
        "value" : 3
    }, {
        "key" : "penalty",
        "value" : 2
    }, {
        "key" : "shoot",
        "value" : 1
    }, {
        "key" : "dribble",
        "value" : 4
    }, {
        "key" : "tacle",
        "value" : 2
    } ],
    "mentalFolder" : [ {
        "key" : "aggressiveness",
        "value" : 5
    }, {
        "key" : "anticipation",
        "value" : 3
    }, {
        "key" : "concentration",
        "value" : 2
    }, {
        "key" : "altruism",
        "value" : 1
    }, {
        "key" : "courage",
        "value" : 4
    }, {
        "key" : "creativity",
        "value" : 1
    }, {
        "key" : "motivation",
        "value" : 3
    }, {
        "key" : "self-control",
        "value" : 1
    }, {
        "key" : "leadership",
        "value" : 2
    }, {
        "key" : "professionalism",
        "value" : 3
    } ],
    "medicalFolder" : null,
    "listLicenses" : [ {
        "numLicense" : "05290771000002",
        "structureId" : "541168295971d35c1f2d1b60",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "B",
                "label" : "Joueur Libre",
                "order" : NumberInt(2)
            }
        }, {
            "dateQualification" : NumberLong(1376776800000),
            "dateInscription" : NumberLong(1376776800000),
            "seasonCode" : "SAI-2013",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "B",
                "label" : "Joueur Libre",
                "order" : NumberInt(2)
            }
        } ]
    } ]
});

db.SB_Person.insert({
    "_id" : "541d3136f61fbf69868c1220",
    "name" : "Amoros",
    "firstname" : "Manuel",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(-249786000000),
    "birthcity" : "Monaco",
    "birthcountry" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "nationality" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "job" : "Plongeur",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "00980",
        "city" : "Monaco",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 04",
        "office" : "",
        "cellphone" : "07 06 00 00 04",
        "email" : "amoros@toto.com"
    },
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "excellent",
        "weight" : "72",
        "height" : "172",
        "squadnumber" : "2",
        "positionType" : "defender",
        "laterality" : "righthanded",
    },
    "physicalFolder" : [ {
        "key" : "explosive",
        "value" : 4
    }, {
        "key" : "speed",
        "value" : 5
    }, {
        "key" : "traction",
        "value" : 5
    }, {
        "key" : "verticaljump",
        "value" : 3
    }, {
        "key" : "endurance",
        "value" : 2
    }, {
        "key" : "equilibrium",
        "value" : 1
    }, {
        "key" : "strenght",
        "value" : 4
    }, {
        "key" : "nimbleness",
        "value" : 1
    } ],
    "technicalFolder" : [ {
        "key" : "pass",
        "value" : 5
    }, {
        "key" : "center",
        "value" : 1
    }, {
        "key" : "corner",
        "value" : 1
    }, {
        "key" : "freekick",
        "value" : 4
    }, {
        "key" : "penalty",
        "value" : 4
    }, {
        "key" : "shoot",
        "value" : 2
    }, {
        "key" : "dribble",
        "value" : 1
    }, {
        "key" : "tacle",
        "value" : 2
    } ],
    "mentalFolder" : [ {
        "key" : "aggressiveness",
        "value" : 2
    }, {
        "key" : "anticipation",
        "value" : 1
    }, {
        "key" : "concentration",
        "value" : 3
    }, {
        "key" : "altruism",
        "value" : 2
    }, {
        "key" : "courage",
        "value" : 3
    }, {
        "key" : "creativity",
        "value" : 3
    }, {
        "key" : "motivation",
        "value" : 3
    }, {
        "key" : "self-control",
        "value" : 2
    }, {
        "key" : "leadership",
        "value" : 2
    }, {
        "key" : "professionalism",
        "value" : 3
    } ],
    "medicalFolder" : null,
    "listLicenses" : [ {
        "numLicense" : "05290771000004",
        "structureId" : "541168295971d35c1f2d1b60",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "B",
                "label" : "Joueur Libre",
                "order" : NumberInt(2)
            }
        }, {
            "dateQualification" : NumberLong(1376776800000),
            "dateInscription" : NumberLong(1376776800000),
            "seasonCode" : "SAI-2013",
            "categoryAgeCode" : "u19",
            "tarif" : null,
            "typeLicence" : {
                "code" : "B",
                "label" : "Joueur Libre",
                "order" : NumberInt(2)
            }
        } ]
    } ]
});

db.SB_Person.insert({
    "_id" : "541d3136f61fbf69868c121f",
    "name" : "Ettori",
    "firstname" : "Jean-Luc",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(-455335200000),
    "birthcity" : "Monaco",
    "birthcountry" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "nationality" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "job" : "coiffeur",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "00980",
        "city" : "Monaco",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 03",
        "office" : "",
        "cellphone" : "07 06 00 00 03",
        "email" : "ettori@toto.com"
    },
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "good",
        "weight" : "73",
        "height" : "173",
        "squadnumber" : "22",
        "positionType" : "goalkeeper",
        "laterality" : "righthanded",
    },
    "physicalFolder" : [ {
        "key" : "explosive",
        "value" : 5
    }, {
        "key" : "speed",
        "value" : 3
    }, {
        "key" : "traction",
        "value" : 2
    }, {
        "key" : "verticaljump",
        "value" : 3
    }, {
        "key" : "endurance",
        "value" : 2
    }, {
        "key" : "equilibrium",
        "value" : 3
    }, {
        "key" : "strenght",
        "value" : 4
    }, {
        "key" : "nimbleness",
        "value" : 1
    } ],
    "technicalFolder" : [ {
        "key" : "pass",
        "value" : 5
    }, {
        "key" : "center",
        "value" : 2
    }, {
        "key" : "corner",
        "value" : 2
    }, {
        "key" : "freekick",
        "value" : 4
    }, {
        "key" : "penalty",
        "value" : 2
    }, {
        "key" : "shoot",
        "value" : 3
    }, {
        "key" : "dribble",
        "value" : 4
    }, {
        "key" : "tacle",
        "value" : 2
    } ],
    "mentalFolder" : [ {
        "key" : "aggressiveness",
        "value" : 3
    }, {
        "key" : "anticipation",
        "value" : 2
    }, {
        "key" : "concentration",
        "value" : 3
    }, {
        "key" : "altruism",
        "value" : 3
    }, {
        "key" : "courage",
        "value" : 3
    }, {
        "key" : "creativity",
        "value" : 1
    }, {
        "key" : "motivation",
        "value" : 2
    }, {
        "key" : "self-control",
        "value" : 1
    }, {
        "key" : "leadership",
        "value" : 2
    }, {
        "key" : "professionalism",
        "value" : 3
    } ],
    "medicalFolder" : null,
    "listLicenses" : [ {
        "numLicense" : "05290771000003",
        "structureId" : "541168295971d35c1f2d1b60",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "B",
                "label" : "Joueur Libre",
                "order" : NumberInt(2)
            }
        }, {
            "dateQualification" : NumberLong(1376776800000),
            "dateInscription" : NumberLong(1376776800000),
            "seasonCode" : "SAI-2013",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "B",
                "label" : "Joueur Libre",
                "order" : NumberInt(2)
            }
        } ]
    } ]
});

db.SB_Person.insert({
    "_id" : "541d3136f61fbf69868c1221",
    "name" : "Battiston",
    "firstname" : "Patrick",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(-404182800000),
    "birthcity" : "Saint-Etienne",
    "birthcountry" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "nationality" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "job" : "Dentiste",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "42000",
        "city" : "Saint-Etienne",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 05",
        "office" : "",
        "cellphone" : "07 06 00 00 05",
        "email" : "battiston@toto.com"
    },
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "excellent",
        "weight" : "82",
        "height" : "182",
        "squadnumber" : "3",
        "positionType" : "defender",
        "laterality" : "righthanded",
    },
    "physicalFolder" : [ {
        "key" : "explosive",
        "value" : 2
    }, {
        "key" : "speed",
        "value" : 4
    }, {
        "key" : "traction",
        "value" : 2
    }, {
        "key" : "verticaljump",
        "value" : 5
    }, {
        "key" : "endurance",
        "value" : 1
    }, {
        "key" : "equilibrium",
        "value" : 4
    }, {
        "key" : "strenght",
        "value" : 4
    }, {
        "key" : "nimbleness",
        "value" : 5
    } ],
    "technicalFolder" : [ {
        "key" : "pass",
        "value" : 5
    }, {
        "key" : "center",
        "value" : 4
    }, {
        "key" : "corner",
        "value" : 5
    }, {
        "key" : "freekick",
        "value" : 3
    }, {
        "key" : "penalty",
        "value" : 3
    }, {
        "key" : "shoot",
        "value" : 1
    }, {
        "key" : "dribble",
        "value" : 4
    }, {
        "key" : "tacle",
        "value" : 3
    } ],
    "mentalFolder" : [ {
        "key" : "aggressiveness",
        "value" : 3
    }, {
        "key" : "anticipation",
        "value" : 4
    }, {
        "key" : "concentration",
        "value" : 3
    }, {
        "key" : "altruism",
        "value" : 5
    }, {
        "key" : "courage",
        "value" : 1
    }, {
        "key" : "creativity",
        "value" : 1
    }, {
        "key" : "motivation",
        "value" : 5
    }, {
        "key" : "self-control",
        "value" : 3
    }, {
        "key" : "leadership",
        "value" : 3
    }, {
        "key" : "professionalism",
        "value" : 3
    } ],
    "medicalFolder" : null,
    "listLicenses" : [ {
        "numLicense" : "05290771000005",
        "structureId" : "541168295971d35c1f2d1b60",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "B",
                "label" : "Joueur Libre",
                "order" : NumberInt(2)
            }
        }, {
            "dateQualification" : NumberLong(1376776800000),
            "dateInscription" : NumberLong(1376776800000),
            "seasonCode" : "SAI-2013",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "B",
                "label" : "Joueur Libre",
                "order" : NumberInt(2)
            }
        } ]
    } ]
});

db.SB_Person.insert({
    "_id" : "541d3136f61fbf69868c1222",
    "name" : "Bossis",
    "firstname" : "Maxime",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(-458186400000),
    "birthcity" : "Nantes",
    "birthcountry" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "nationality" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "job" : "ferrailleur",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "44000",
        "city" : "Nantes",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 06",
        "office" : "",
        "cellphone" : "07 06 00 00 06",
        "email" : "bossis@toto.com"
    },
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "good",
        "weight" : "86",
        "height" : "186",
        "squadnumber" : "4",
        "positionType" : "defender",
        "laterality" : "righthanded",
    },
    "physicalFolder" : [ {
        "key" : "explosive",
        "value" : 1
    }, {
        "key" : "speed",
        "value" : 3
    }, {
        "key" : "traction",
        "value" : 1
    }, {
        "key" : "verticaljump",
        "value" : 2
    }, {
        "key" : "endurance",
        "value" : 1
    }, {
        "key" : "equilibrium",
        "value" : 5
    }, {
        "key" : "strenght",
        "value" : 1
    }, {
        "key" : "nimbleness",
        "value" : 2
    } ],
    "technicalFolder" : [ {
        "key" : "pass",
        "value" : 2
    }, {
        "key" : "center",
        "value" : 4
    }, {
        "key" : "corner",
        "value" : 1
    }, {
        "key" : "freekick",
        "value" : 2
    }, {
        "key" : "penalty",
        "value" : 1
    }, {
        "key" : "shoot",
        "value" : 2
    }, {
        "key" : "dribble",
        "value" : 3
    }, {
        "key" : "tacle",
        "value" : 2
    } ],
    "mentalFolder" : [ {
        "key" : "aggressiveness",
        "value" : 5
    }, {
        "key" : "anticipation",
        "value" : 1
    }, {
        "key" : "concentration",
        "value" : 4
    }, {
        "key" : "altruism",
        "value" : 5
    }, {
        "key" : "courage",
        "value" : 1
    }, {
        "key" : "creativity",
        "value" : 5
    }, {
        "key" : "motivation",
        "value" : 4
    }, {
        "key" : "self-control",
        "value" : 4
    }, {
        "key" : "leadership",
        "value" : 5
    }, {
        "key" : "professionalism",
        "value" : 4
    } ],
    "medicalFolder" : null,
    "listLicenses" : [ {
        "numLicense" : "05290771000006",
        "structureId" : "541168295971d35c1f2d1b60",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "B",
                "label" : "Joueur Libre",
                "order" : NumberInt(2)
            }
        }, {
            "dateQualification" : NumberLong(1376776800000),
            "dateInscription" : NumberLong(1376776800000),
            "seasonCode" : "SAI-2013",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "B",
                "label" : "Joueur Libre",
                "order" : NumberInt(2)
            }
        } ]
    } ]
});

db.SB_Person.insert({
    "_id" : "541d3136f61fbf69868c1223",
    "name" : "Janvion",
    "firstname" : "Gérard",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(-517975200000),
    "birthcity" : "Fort-de-France",
    "birthcountry" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "nationality" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "job" : "Vigile",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "42000",
        "city" : "Saint-Etienne",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 07",
        "office" : "",
        "cellphone" : "07 06 00 00 07",
        "email" : "janvion@toto.com"
    },
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : "72",
        "height" : "172",
        "squadnumber" : "5",
        "positionType" : "defender",
        "laterality" : "lefthanded",
    },
    "physicalFolder" : [ {
        "key" : "explosive",
        "value" : 1
    }, {
        "key" : "speed",
        "value" : 5
    }, {
        "key" : "traction",
        "value" : 2
    }, {
        "key" : "verticaljump",
        "value" : 4
    }, {
        "key" : "endurance",
        "value" : 5
    }, {
        "key" : "equilibrium",
        "value" : 2
    }, {
        "key" : "strenght",
        "value" : 5
    }, {
        "key" : "nimbleness",
        "value" : 1
    } ],
    "technicalFolder" : [ {
        "key" : "pass",
        "value" : 3
    }, {
        "key" : "center",
        "value" : 5
    }, {
        "key" : "corner",
        "value" : 5
    }, {
        "key" : "freekick",
        "value" : 4
    }, {
        "key" : "penalty",
        "value" : 1
    }, {
        "key" : "shoot",
        "value" : 1
    }, {
        "key" : "dribble",
        "value" : 3
    }, {
        "key" : "tacle",
        "value" : 4
    } ],
    "mentalFolder" : [ {
        "key" : "aggressiveness",
        "value" : 4
    }, {
        "key" : "anticipation",
        "value" : 3
    }, {
        "key" : "concentration",
        "value" : 5
    }, {
        "key" : "altruism",
        "value" : 2
    }, {
        "key" : "courage",
        "value" : 4
    }, {
        "key" : "creativity",
        "value" : 2
    }, {
        "key" : "motivation",
        "value" : 1
    }, {
        "key" : "self-control",
        "value" : 2
    }, {
        "key" : "leadership",
        "value" : 3
    }, {
        "key" : "professionalism",
        "value" : 1
    } ],
    "medicalFolder" : null,
    "listLicenses" : [ {
        "numLicense" : "05290771000007",
        "structureId" : "541168295971d35c1f2d1b60",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "B",
                "label" : "Joueur Libre",
                "order" : NumberInt(2)
            }
        }, {
            "dateQualification" : NumberLong(1376776800000),
            "dateInscription" : NumberLong(1376776800000),
            "seasonCode" : "SAI-2013",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "B",
                "label" : "Joueur Libre",
                "order" : NumberInt(2)
            }
        } ]
    } ]
});

db.SB_Person.insert({
    "_id" : "541d3136f61fbf69868c1224",
    "name" : "Lopez",
    "firstname" : "Christian",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(-530154000000),
    "birthcity" : "Toulouse",
    "birthcountry" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "nationality" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "job" : "Maçon",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "31000",
        "city" : "Toulouse",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 08",
        "office" : "",
        "cellphone" : "07 06 00 00 08",
        "email" : "lopez@toto.com"
    },
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : "77",
        "height" : "177",
        "squadnumber" : "6",
        "positionType" : "defender",
        "laterality" : "righthanded",
    },
    "physicalFolder" : [ {
        "key" : "explosive",
        "value" : 3
    }, {
        "key" : "speed",
        "value" : 1
    }, {
        "key" : "traction",
        "value" : 2
    }, {
        "key" : "verticaljump",
        "value" : 5
    }, {
        "key" : "endurance",
        "value" : 1
    }, {
        "key" : "equilibrium",
        "value" : 3
    }, {
        "key" : "strenght",
        "value" : 1
    }, {
        "key" : "nimbleness",
        "value" : 1
    } ],
    "technicalFolder" : [ {
        "key" : "pass",
        "value" : 5
    }, {
        "key" : "center",
        "value" : 5
    }, {
        "key" : "corner",
        "value" : 1
    }, {
        "key" : "freekick",
        "value" : 1
    }, {
        "key" : "penalty",
        "value" : 2
    }, {
        "key" : "shoot",
        "value" : 2
    }, {
        "key" : "dribble",
        "value" : 5
    }, {
        "key" : "tacle",
        "value" : 1
    } ],
    "mentalFolder" : [ {
        "key" : "aggressiveness",
        "value" : 3
    }, {
        "key" : "anticipation",
        "value" : 4
    }, {
        "key" : "concentration",
        "value" : 1
    }, {
        "key" : "altruism",
        "value" : 3
    }, {
        "key" : "courage",
        "value" : 2
    }, {
        "key" : "creativity",
        "value" : 3
    }, {
        "key" : "motivation",
        "value" : 2
    }, {
        "key" : "self-control",
        "value" : 4
    }, {
        "key" : "leadership",
        "value" : 5
    }, {
        "key" : "professionalism",
        "value" : 4
    } ],
    "medicalFolder" : null,
    "listLicenses" : [ {
        "numLicense" : "05290771000008",
        "structureId" : "541168295971d35c1f2d1b60",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "B",
                "label" : "Joueur Libre",
                "order" : NumberInt(2)
            }
        }, {
            "dateQualification" : NumberLong(1376776800000),
            "dateInscription" : NumberLong(1376776800000),
            "seasonCode" : "SAI-2013",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "B",
                "label" : "Joueur Libre",
                "order" : NumberInt(2)
            }
        } ]
    } ]
});

db.SB_Person.insert({
    "_id" : "541d3136f61fbf69868c1225",
    "name" : "Mahut",
    "firstname" : "Philippe",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(-436410000000),
    "birthcity" : "Saint-Etienne",
    "birthcountry" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "nationality" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "job" : "Assureur",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "42000",
        "city" : "Saint-Etienne",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 09",
        "office" : "",
        "cellphone" : "07 06 00 00 09",
        "email" : "mahut@toto.com"
    },
    "status" : {
        "availability" : {
            "value" : "unavailable",
            "cause" : "injured",
            "endDate" : NumberLong(1435615200000)
        },
        "stateForm" : "good",
        "weight" : "84",
        "height" : "184",
        "squadnumber" : "7",
        "positionType" : "defender",
        "laterality" : "righthanded",
    },
    "physicalFolder" : [ {
        "key" : "explosive",
        "value" : 3
    }, {
        "key" : "speed",
        "value" : 2
    }, {
        "key" : "traction",
        "value" : 1
    }, {
        "key" : "verticaljump",
        "value" : 2
    }, {
        "key" : "endurance",
        "value" : 4
    }, {
        "key" : "equilibrium",
        "value" : 1
    }, {
        "key" : "strenght",
        "value" : 2
    }, {
        "key" : "nimbleness",
        "value" : 3
    } ],
    "technicalFolder" : [ {
        "key" : "pass",
        "value" : 4
    }, {
        "key" : "center",
        "value" : 5
    }, {
        "key" : "corner",
        "value" : 4
    }, {
        "key" : "freekick",
        "value" : 5
    }, {
        "key" : "penalty",
        "value" : 4
    }, {
        "key" : "shoot",
        "value" : 2
    }, {
        "key" : "dribble",
        "value" : 5
    }, {
        "key" : "tacle",
        "value" : 5
    } ],
    "mentalFolder" : [ {
        "key" : "aggressiveness",
        "value" : 5
    }, {
        "key" : "anticipation",
        "value" : 4
    }, {
        "key" : "concentration",
        "value" : 4
    }, {
        "key" : "altruism",
        "value" : 5
    }, {
        "key" : "courage",
        "value" : 3
    }, {
        "key" : "creativity",
        "value" : 5
    }, {
        "key" : "motivation",
        "value" : 1
    }, {
        "key" : "self-control",
        "value" : 1
    }, {
        "key" : "leadership",
        "value" : 5
    }, {
        "key" : "professionalism",
        "value" : 2
    } ],
    "medicalFolder" : null,
    "listLicenses" : [ {
        "numLicense" : "05290771000009",
        "structureId" : "541168295971d35c1f2d1b60",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "B",
                "label" : "Joueur Libre",
                "order" : NumberInt(2)
            }
        }, {
            "dateQualification" : NumberLong(1376776800000),
            "dateInscription" : NumberLong(1376776800000),
            "seasonCode" : "SAI-2013",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "B",
                "label" : "Joueur Libre",
                "order" : NumberInt(2)
            }
        } ]
    } ]
});

db.SB_Person.insert({
    "_id" : "541d3136f61fbf69868c1226",
    "name" : "Tresor",
    "firstname" : "Marius",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(-629946000000),
    "birthcity" : "Bordeaux",
    "birthcountry" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "nationality" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "job" : "Boulanger",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "33000",
        "city" : "Bordeaux",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 10",
        "office" : "",
        "cellphone" : "07 06 00 00 10",
        "email" : "tresor@toto.com"
    },
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "excellent",
        "weight" : "82",
        "height" : "182",
        "squadnumber" : "8",
        "positionType" : "defender",
        "laterality" : "righthanded",
    },
    "physicalFolder" : [ {
        "key" : "explosive",
        "value" : 2
    }, {
        "key" : "speed",
        "value" : 4
    }, {
        "key" : "traction",
        "value" : 3
    }, {
        "key" : "verticaljump",
        "value" : 3
    }, {
        "key" : "endurance",
        "value" : 4
    }, {
        "key" : "equilibrium",
        "value" : 1
    }, {
        "key" : "strenght",
        "value" : 5
    }, {
        "key" : "nimbleness",
        "value" : 2
    } ],
    "technicalFolder" : [ {
        "key" : "pass",
        "value" : 1
    }, {
        "key" : "center",
        "value" : 1
    }, {
        "key" : "corner",
        "value" : 3
    }, {
        "key" : "freekick",
        "value" : 4
    }, {
        "key" : "penalty",
        "value" : 4
    }, {
        "key" : "shoot",
        "value" : 3
    }, {
        "key" : "dribble",
        "value" : 3
    }, {
        "key" : "tacle",
        "value" : 2
    } ],
    "mentalFolder" : [ {
        "key" : "aggressiveness",
        "value" : 1
    }, {
        "key" : "anticipation",
        "value" : 5
    }, {
        "key" : "concentration",
        "value" : 5
    }, {
        "key" : "altruism",
        "value" : 4
    }, {
        "key" : "courage",
        "value" : 3
    }, {
        "key" : "creativity",
        "value" : 4
    }, {
        "key" : "motivation",
        "value" : 4
    }, {
        "key" : "self-control",
        "value" : 4
    }, {
        "key" : "leadership",
        "value" : 1
    }, {
        "key" : "professionalism",
        "value" : 5
    } ],
    "medicalFolder" : null,
    "listLicenses" : [ {
        "numLicense" : "05290771000010",
        "structureId" : "541168295971d35c1f2d1b60",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "B",
                "label" : "Joueur Libre",
                "order" : NumberInt(2)
            }
        }, {
            "dateQualification" : NumberLong(1376776800000),
            "dateInscription" : NumberLong(1376776800000),
            "seasonCode" : "SAI-2013",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "B",
                "label" : "Joueur Libre",
                "order" : NumberInt(2)
            }
        } ]
    } ]
});

db.SB_Person.insert({
    "_id" : "541d3136f61fbf69868c1227",
    "name" : "Genghini",
    "firstname" : "Bernard",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(-377226000000),
    "birthcity" : "Saint-Etienne",
    "birthcountry" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "nationality" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "job" : "VRP",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "42000",
        "city" : "Saint-Etienne",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 11",
        "office" : "",
        "cellphone" : "07 06 00 00 11",
        "email" : "genghini@toto.com"
    },
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "good",
        "weight" : "79",
        "height" : "179",
        "squadnumber" : "9",
        "positionType" : "midfielder",
        "laterality" : "righthanded",
    },
    "physicalFolder" : [ {
        "key" : "explosive",
        "value" : 3
    }, {
        "key" : "speed",
        "value" : 3
    }, {
        "key" : "traction",
        "value" : 2
    }, {
        "key" : "verticaljump",
        "value" : 3
    }, {
        "key" : "endurance",
        "value" : 3
    }, {
        "key" : "equilibrium",
        "value" : 2
    }, {
        "key" : "strenght",
        "value" : 5
    }, {
        "key" : "nimbleness",
        "value" : 2
    } ],
    "technicalFolder" : [ {
        "key" : "pass",
        "value" : 1
    }, {
        "key" : "center",
        "value" : 5
    }, {
        "key" : "corner",
        "value" : 3
    }, {
        "key" : "freekick",
        "value" : 1
    }, {
        "key" : "penalty",
        "value" : 3
    }, {
        "key" : "shoot",
        "value" : 1
    }, {
        "key" : "dribble",
        "value" : 2
    }, {
        "key" : "tacle",
        "value" : 4
    } ],
    "mentalFolder" : [ {
        "key" : "aggressiveness",
        "value" : 5
    }, {
        "key" : "anticipation",
        "value" : 3
    }, {
        "key" : "concentration",
        "value" : 5
    }, {
        "key" : "altruism",
        "value" : 5
    }, {
        "key" : "courage",
        "value" : 2
    }, {
        "key" : "creativity",
        "value" : 5
    }, {
        "key" : "motivation",
        "value" : 4
    }, {
        "key" : "self-control",
        "value" : 1
    }, {
        "key" : "leadership",
        "value" : 5
    }, {
        "key" : "professionalism",
        "value" : 4
    } ],
    "medicalFolder" : null,
    "listLicenses" : [ {
        "numLicense" : "05290771000011",
        "structureId" : "541168295971d35c1f2d1b60",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "B",
                "label" : "Joueur Libre",
                "order" : NumberInt(2)
            }
        }, {
            "dateQualification" : NumberLong(1376776800000),
            "dateInscription" : NumberLong(1376776800000),
            "seasonCode" : "SAI-2013",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "B",
                "label" : "Joueur Libre",
                "order" : NumberInt(2)
            }
        } ]
    } ]
});

db.SB_Person.insert({
    "_id" : "541d3136f61fbf69868c1228",
    "name" : "Platini",
    "firstname" : "Michel",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(-458618400000),
    "birthcity" : "Saint-Etienne",
    "birthcountry" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "nationality" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "job" : "Plombier",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "42000",
        "city" : "Saint-Etienne",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 12",
        "office" : "",
        "cellphone" : "07 06 00 00 12",
        "email" : "platini@toto.com"
    },
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "excellent",
        "weight" : "77",
        "height" : "177",
        "squadnumber" : "10",
        "positionType" : "midfielder",
        "laterality" : "righthanded",
    },
    "physicalFolder" : [ {
        "key" : "explosive",
        "value" : 3
    }, {
        "key" : "speed",
        "value" : 3
    }, {
        "key" : "traction",
        "value" : 3
    }, {
        "key" : "verticaljump",
        "value" : 5
    }, {
        "key" : "endurance",
        "value" : 5
    }, {
        "key" : "equilibrium",
        "value" : 1
    }, {
        "key" : "strenght",
        "value" : 5
    }, {
        "key" : "nimbleness",
        "value" : 3
    } ],
    "technicalFolder" : [ {
        "key" : "pass",
        "value" : 3
    }, {
        "key" : "center",
        "value" : 4
    }, {
        "key" : "corner",
        "value" : 2
    }, {
        "key" : "freekick",
        "value" : 3
    }, {
        "key" : "penalty",
        "value" : 1
    }, {
        "key" : "shoot",
        "value" : 1
    }, {
        "key" : "dribble",
        "value" : 1
    }, {
        "key" : "tacle",
        "value" : 2
    } ],
    "mentalFolder" : [ {
        "key" : "aggressiveness",
        "value" : 3
    }, {
        "key" : "anticipation",
        "value" : 1
    }, {
        "key" : "concentration",
        "value" : 4
    }, {
        "key" : "altruism",
        "value" : 3
    }, {
        "key" : "courage",
        "value" : 3
    }, {
        "key" : "creativity",
        "value" : 4
    }, {
        "key" : "motivation",
        "value" : 5
    }, {
        "key" : "self-control",
        "value" : 3
    }, {
        "key" : "leadership",
        "value" : 2
    }, {
        "key" : "professionalism",
        "value" : 2
    } ],
    "medicalFolder" : null,
    "listLicenses" : [ {
        "numLicense" : "05290771000012",
        "structureId" : "541168295971d35c1f2d1b60",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "B",
                "label" : "Joueur Libre",
                "order" : NumberInt(2)
            }
        }, {
            "dateQualification" : NumberLong(1376776800000),
            "dateInscription" : NumberLong(1376776800000),
            "seasonCode" : "SAI-2013",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "B",
                "label" : "Joueur Libre",
                "order" : NumberInt(2)
            }
        } ]
    } ]
});
db.SB_Person.insert({
    "_id" : "541d3136f61fbf69868c1229",
    "name" : "Girard",
    "firstname" : "René",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(-496893600000),
    "birthcity" : "Bordeaux",
    "birthcountry" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "nationality" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "job" : "Taxi",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "33000",
        "city" : "Bordeaux",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 13",
        "office" : "",
        "cellphone" : "07 06 00 00 13",
        "email" : "girard@toto.com"
    },
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : "78",
        "height" : "178",
        "squadnumber" : "11",
        "positionType" : "midfielder",
        "laterality" : "righthanded",
    },
    "physicalFolder" : [ {
        "key" : "explosive",
        "value" : 4
    }, {
        "key" : "speed",
        "value" : 1
    }, {
        "key" : "traction",
        "value" : 2
    }, {
        "key" : "verticaljump",
        "value" : 5
    }, {
        "key" : "endurance",
        "value" : 1
    }, {
        "key" : "equilibrium",
        "value" : 4
    }, {
        "key" : "strenght",
        "value" : 5
    }, {
        "key" : "nimbleness",
        "value" : 4
    } ],
    "technicalFolder" : [ {
        "key" : "pass",
        "value" : 5
    }, {
        "key" : "center",
        "value" : 4
    }, {
        "key" : "corner",
        "value" : 3
    }, {
        "key" : "freekick",
        "value" : 1
    }, {
        "key" : "penalty",
        "value" : 2
    }, {
        "key" : "shoot",
        "value" : 3
    }, {
        "key" : "dribble",
        "value" : 1
    }, {
        "key" : "tacle",
        "value" : 3
    } ],
    "mentalFolder" : [ {
        "key" : "aggressiveness",
        "value" : 5
    }, {
        "key" : "anticipation",
        "value" : 1
    }, {
        "key" : "concentration",
        "value" : 1
    }, {
        "key" : "altruism",
        "value" : 1
    }, {
        "key" : "courage",
        "value" : 2
    }, {
        "key" : "creativity",
        "value" : 4
    }, {
        "key" : "motivation",
        "value" : 5
    }, {
        "key" : "self-control",
        "value" : 1
    }, {
        "key" : "leadership",
        "value" : 5
    }, {
        "key" : "professionalism",
        "value" : 4
    } ],
    "medicalFolder" : null,
    "listLicenses" : [ {
        "numLicense" : "05290771000013",
        "structureId" : "541168295971d35c1f2d1b60",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "B",
                "label" : "Joueur Libre",
                "order" : NumberInt(2)
            }
        }, {
            "dateQualification" : NumberLong(1376776800000),
            "dateInscription" : NumberLong(1376776800000),
            "seasonCode" : "SAI-2013",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "B",
                "label" : "Joueur Libre",
                "order" : NumberInt(2)
            }
        } ]
    } ]
});

db.SB_Person.insert({
    "_id" : "541d3136f61fbf69868c122a",
    "name" : "Giresse",
    "firstname" : "Alain",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(-549597600000),
    "birthcity" : "Bordeaux",
    "birthcountry" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "nationality" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "job" : "Couvreur",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "33000",
        "city" : "Bordeaux",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 14",
        "office" : "",
        "cellphone" : "07 06 00 00 14",
        "email" : "giresse@toto.com"
    },
    "status" : {
        "availability" : {
            "value" : "unavailable",
            "cause" : "absent",
            "endDate" : NumberLong(1435615200000)
        },
        "stateForm" : "middling",
        "weight" : "62",
        "height" : "162",
        "squadnumber" : "12",
        "positionType" : "midfielder",
        "laterality" : "righthanded",
    },
    "physicalFolder" : [ {
        "key" : "explosive",
        "value" : 1
    }, {
        "key" : "speed",
        "value" : 5
    }, {
        "key" : "traction",
        "value" : 4
    }, {
        "key" : "verticaljump",
        "value" : 2
    }, {
        "key" : "endurance",
        "value" : 3
    }, {
        "key" : "equilibrium",
        "value" : 1
    }, {
        "key" : "strenght",
        "value" : 4
    }, {
        "key" : "nimbleness",
        "value" : 4
    } ],
    "technicalFolder" : [ {
        "key" : "pass",
        "value" : 3
    }, {
        "key" : "center",
        "value" : 1
    }, {
        "key" : "corner",
        "value" : 5
    }, {
        "key" : "freekick",
        "value" : 4
    }, {
        "key" : "penalty",
        "value" : 4
    }, {
        "key" : "shoot",
        "value" : 2
    }, {
        "key" : "dribble",
        "value" : 4
    }, {
        "key" : "tacle",
        "value" : 5
    } ],
    "mentalFolder" : [ {
        "key" : "aggressiveness",
        "value" : 2
    }, {
        "key" : "anticipation",
        "value" : 4
    }, {
        "key" : "concentration",
        "value" : 5
    }, {
        "key" : "altruism",
        "value" : 4
    }, {
        "key" : "courage",
        "value" : 1
    }, {
        "key" : "creativity",
        "value" : 1
    }, {
        "key" : "motivation",
        "value" : 5
    }, {
        "key" : "self-control",
        "value" : 5
    }, {
        "key" : "leadership",
        "value" : 4
    }, {
        "key" : "professionalism",
        "value" : 4
    } ],
    "medicalFolder" : null,
    "listLicenses" : [ {
        "numLicense" : "05290771000014",
        "structureId" : "541168295971d35c1f2d1b60",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "B",
                "label" : "Joueur Libre",
                "order" : NumberInt(2)
            }
        }, {
            "dateQualification" : NumberLong(1376776800000),
            "dateInscription" : NumberLong(1376776800000),
            "seasonCode" : "SAI-2013",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "B",
                "label" : "Joueur Libre",
                "order" : NumberInt(2)
            }
        } ]
    } ]
});

db.SB_Person.insert({
    "_id" : "541d3136f61fbf69868c122b",
    "name" : "Larios",
    "firstname" : "Jean-François",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(-421207200000),
    "birthcity" : "Saint-Etienne",
    "birthcountry" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "nationality" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "job" : "Animateur",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "42000",
        "city" : "Saint-Etienne",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 15",
        "office" : "",
        "cellphone" : "07 06 00 00 15",
        "email" : "larios@toto.com"
    },
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : "80",
        "height" : "180",
        "squadnumber" : "13",
        "positionType" : "midfielder",
        "laterality" : "righthanded",
    },
    "physicalFolder" : [ {
        "key" : "explosive",
        "value" : 1
    }, {
        "key" : "speed",
        "value" : 1
    }, {
        "key" : "traction",
        "value" : 5
    }, {
        "key" : "verticaljump",
        "value" : 2
    }, {
        "key" : "endurance",
        "value" : 1
    }, {
        "key" : "equilibrium",
        "value" : 3
    }, {
        "key" : "strenght",
        "value" : 5
    }, {
        "key" : "nimbleness",
        "value" : 4
    } ],
    "technicalFolder" : [ {
        "key" : "pass",
        "value" : 4
    }, {
        "key" : "center",
        "value" : 1
    }, {
        "key" : "corner",
        "value" : 1
    }, {
        "key" : "freekick",
        "value" : 3
    }, {
        "key" : "penalty",
        "value" : 4
    }, {
        "key" : "shoot",
        "value" : 3
    }, {
        "key" : "dribble",
        "value" : 4
    }, {
        "key" : "tacle",
        "value" : 2
    } ],
    "mentalFolder" : [ {
        "key" : "aggressiveness",
        "value" : 1
    }, {
        "key" : "anticipation",
        "value" : 3
    }, {
        "key" : "concentration",
        "value" : 2
    }, {
        "key" : "altruism",
        "value" : 4
    }, {
        "key" : "courage",
        "value" : 5
    }, {
        "key" : "creativity",
        "value" : 4
    }, {
        "key" : "motivation",
        "value" : 2
    }, {
        "key" : "self-control",
        "value" : 3
    }, {
        "key" : "leadership",
        "value" : 5
    }, {
        "key" : "professionalism",
        "value" : 3
    } ],
    "medicalFolder" : null,
    "listLicenses" : [ {
        "numLicense" : "05290771000015",
        "structureId" : "541168295971d35c1f2d1b60",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "B",
                "label" : "Joueur Libre",
                "order" : NumberInt(2)
            }
        }, {
            "dateQualification" : NumberLong(1376776800000),
            "dateInscription" : NumberLong(1376776800000),
            "seasonCode" : "SAI-2013",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "B",
                "label" : "Joueur Libre",
                "order" : NumberInt(2)
            }
        } ]
    } ]
});

db.SB_Person.insert({
    "_id" : "541d3136f61fbf69868c122c",
    "name" : "Tigana",
    "firstname" : "Jean",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(-458445600000),
    "birthcity" : "Bamako",
    "birthcountry" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "nationality" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "job" : "Platrier",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "33000",
        "city" : "Bordeaux",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 16",
        "office" : "",
        "cellphone" : "07 06 00 00 16",
        "email" : "tigana@toto.com"
    },
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "excellent",
        "weight" : "68",
        "height" : "168",
        "squadnumber" : "14",
        "positionType" : "midfielder",
        "laterality" : "righthanded",
    },
    "physicalFolder" : [ {
        "key" : "explosive",
        "value" : 4
    }, {
        "key" : "speed",
        "value" : 4
    }, {
        "key" : "traction",
        "value" : 3
    }, {
        "key" : "verticaljump",
        "value" : 4
    }, {
        "key" : "endurance",
        "value" : 2
    }, {
        "key" : "equilibrium",
        "value" : 4
    }, {
        "key" : "strenght",
        "value" : 3
    }, {
        "key" : "nimbleness",
        "value" : 2
    } ],
    "technicalFolder" : [ {
        "key" : "pass",
        "value" : 4
    }, {
        "key" : "center",
        "value" : 5
    }, {
        "key" : "corner",
        "value" : 2
    }, {
        "key" : "freekick",
        "value" : 3
    }, {
        "key" : "penalty",
        "value" : 1
    }, {
        "key" : "shoot",
        "value" : 1
    }, {
        "key" : "dribble",
        "value" : 5
    }, {
        "key" : "tacle",
        "value" : 2
    } ],
    "mentalFolder" : [ {
        "key" : "aggressiveness",
        "value" : 4
    }, {
        "key" : "anticipation",
        "value" : 2
    }, {
        "key" : "concentration",
        "value" : 4
    }, {
        "key" : "altruism",
        "value" : 3
    }, {
        "key" : "courage",
        "value" : 5
    }, {
        "key" : "creativity",
        "value" : 2
    }, {
        "key" : "motivation",
        "value" : 1
    }, {
        "key" : "self-control",
        "value" : 3
    }, {
        "key" : "leadership",
        "value" : 2
    }, {
        "key" : "professionalism",
        "value" : 5
    } ],
    "medicalFolder" : null,
    "listLicenses" : [ {
        "numLicense" : "05290771000016",
        "structureId" : "541168295971d35c1f2d1b60",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "B",
                "label" : "Joueur Libre",
                "order" : NumberInt(2)
            }
        }, {
            "dateQualification" : NumberLong(1376776800000),
            "dateInscription" : NumberLong(1376776800000),
            "seasonCode" : "SAI-2013",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "B",
                "label" : "Joueur Libre",
                "order" : NumberInt(2)
            }
        } ]
    } ]
});

db.SB_Person.insert({
    "_id" : "541d3136f61fbf69868c122d",
    "name" : "Bellone",
    "firstname" : "Bruno",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(-246157200000),
    "birthcity" : "Monaco",
    "birthcountry" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "nationality" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "job" : "Flambeur",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "00980",
        "city" : "Monaco",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 17",
        "office" : "",
        "cellphone" : "07 06 00 00 17",
        "email" : "bellone@toto.com"
    },
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "good",
        "weight" : "75",
        "height" : "175",
        "squadnumber" : "15",
        "positionType" : "forward",
        "laterality" : "lefthanded",
    },
    "physicalFolder" : [ {
        "key" : "explosive",
        "value" : 4
    }, {
        "key" : "speed",
        "value" : 1
    }, {
        "key" : "traction",
        "value" : 4
    }, {
        "key" : "verticaljump",
        "value" : 4
    }, {
        "key" : "endurance",
        "value" : 3
    }, {
        "key" : "equilibrium",
        "value" : 5
    }, {
        "key" : "strenght",
        "value" : 5
    }, {
        "key" : "nimbleness",
        "value" : 1
    } ],
    "technicalFolder" : [ {
        "key" : "pass",
        "value" : 2
    }, {
        "key" : "center",
        "value" : 2
    }, {
        "key" : "corner",
        "value" : 5
    }, {
        "key" : "freekick",
        "value" : 1
    }, {
        "key" : "penalty",
        "value" : 5
    }, {
        "key" : "shoot",
        "value" : 1
    }, {
        "key" : "dribble",
        "value" : 3
    }, {
        "key" : "tacle",
        "value" : 1
    } ],
    "mentalFolder" : [ {
        "key" : "aggressiveness",
        "value" : 4
    }, {
        "key" : "anticipation",
        "value" : 1
    }, {
        "key" : "concentration",
        "value" : 3
    }, {
        "key" : "altruism",
        "value" : 2
    }, {
        "key" : "courage",
        "value" : 1
    }, {
        "key" : "creativity",
        "value" : 1
    }, {
        "key" : "motivation",
        "value" : 1
    }, {
        "key" : "self-control",
        "value" : 1
    }, {
        "key" : "leadership",
        "value" : 3
    }, {
        "key" : "professionalism",
        "value" : 2
    } ],
    "medicalFolder" : null,
    "listLicenses" : [ {
        "numLicense" : "05290771000017",
        "structureId" : "541168295971d35c1f2d1b60",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "B",
                "label" : "Joueur Libre",
                "order" : NumberInt(2)
            }
        }, {
            "dateQualification" : NumberLong(1376776800000),
            "dateInscription" : NumberLong(1376776800000),
            "seasonCode" : "SAI-2013",
            "categoryAgeCode" : "u19" ,
            "tarif" : null,
            "typeLicence" : {
                "code" : "B",
                "label" : "Joueur Libre",
                "order" : NumberInt(2)
            }
        } ]
    } ]
});

db.SB_Person.insert({
    "_id" : "541d3136f61fbf69868c122e",
    "name" : "Couriol",
    "firstname" : "Alain",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(-353124000000),
    "birthcity" : "Monaco",
    "birthcountry" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "nationality" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "job" : "Mécanicien",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "00980",
        "city" : "Monaco",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 18",
        "office" : "",
        "cellphone" : "07 06 00 00 18",
        "email" : "couriol@toto.com"
    },
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "poor",
        "weight" : "74",
        "height" : "174",
        "squadnumber" : "16",
        "positionType" : "forward",
        "laterality" : "righthanded",
    },
    "physicalFolder" : [ {
        "key" : "explosive",
        "value" : 2
    }, {
        "key" : "speed",
        "value" : 3
    }, {
        "key" : "traction",
        "value" : 5
    }, {
        "key" : "verticaljump",
        "value" : 3
    }, {
        "key" : "endurance",
        "value" : 5
    }, {
        "key" : "equilibrium",
        "value" : 1
    }, {
        "key" : "strenght",
        "value" : 4
    }, {
        "key" : "nimbleness",
        "value" : 4
    } ],
    "technicalFolder" : [ {
        "key" : "pass",
        "value" : 4
    }, {
        "key" : "center",
        "value" : 2
    }, {
        "key" : "corner",
        "value" : 2
    }, {
        "key" : "freekick",
        "value" : 2
    }, {
        "key" : "penalty",
        "value" : 4
    }, {
        "key" : "shoot",
        "value" : 5
    }, {
        "key" : "dribble",
        "value" : 2
    }, {
        "key" : "tacle",
        "value" : 5
    } ],
    "mentalFolder" : [ {
        "key" : "aggressiveness",
        "value" : 5
    }, {
        "key" : "anticipation",
        "value" : 2
    }, {
        "key" : "concentration",
        "value" : 5
    }, {
        "key" : "altruism",
        "value" : 5
    }, {
        "key" : "courage",
        "value" : 3
    }, {
        "key" : "creativity",
        "value" : 2
    }, {
        "key" : "motivation",
        "value" : 1
    }, {
        "key" : "self-control",
        "value" : 3
    }, {
        "key" : "leadership",
        "value" : 2
    }, {
        "key" : "professionalism",
        "value" : 5
    } ],
    "medicalFolder" : null,
    "listLicenses" : [ {
        "numLicense" : "05290771000018",
        "structureId" : "541168295971d35c1f2d1b60",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "B",
                "label" : "Joueur Libre",
                "order" : NumberInt(2)
            }
        }, {
            "dateQualification" : NumberLong(1376776800000),
            "dateInscription" : NumberLong(1376776800000),
            "seasonCode" : "SAI-2013",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "B",
                "label" : "Joueur Libre",
                "order" : NumberInt(2)
            }
        } ]
    } ]
});

db.SB_Person.insert({
    "_id" : "541d3136f61fbf69868c122f",
    "name" : "Lacombe",
    "firstname" : "Bernard",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(-548474400000),
    "birthcity" : "Bordeaux",
    "birthcountry" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "nationality" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "job" : "Garagiste",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "33000",
        "city" : "Bordeaux",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 19",
        "office" : "",
        "cellphone" : "07 06 00 00 19",
        "email" : "lacombe@toto.com"
    },
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "good",
        "weight" : "74",
        "height" : "174",
        "squadnumber" : "17",
        "positionType" : "forward",
        "laterality" : "righthanded",
    },
    "physicalFolder" : [ {
        "key" : "explosive",
        "value" : 3
    }, {
        "key" : "speed",
        "value" : 3
    }, {
        "key" : "traction",
        "value" : 3
    }, {
        "key" : "verticaljump",
        "value" : 4
    }, {
        "key" : "endurance",
        "value" : 5
    }, {
        "key" : "equilibrium",
        "value" : 3
    }, {
        "key" : "strenght",
        "value" : 3
    }, {
        "key" : "nimbleness",
        "value" : 5
    } ],
    "technicalFolder" : [ {
        "key" : "pass",
        "value" : 2
    }, {
        "key" : "center",
        "value" : 4
    }, {
        "key" : "corner",
        "value" : 5
    }, {
        "key" : "freekick",
        "value" : 3
    }, {
        "key" : "penalty",
        "value" : 5
    }, {
        "key" : "shoot",
        "value" : 3
    }, {
        "key" : "dribble",
        "value" : 5
    }, {
        "key" : "tacle",
        "value" : 1
    } ],
    "mentalFolder" : [ {
        "key" : "aggressiveness",
        "value" : 4
    }, {
        "key" : "anticipation",
        "value" : 2
    }, {
        "key" : "concentration",
        "value" : 3
    }, {
        "key" : "altruism",
        "value" : 3
    }, {
        "key" : "courage",
        "value" : 4
    }, {
        "key" : "creativity",
        "value" : 2
    }, {
        "key" : "motivation",
        "value" : 1
    }, {
        "key" : "self-control",
        "value" : 4
    }, {
        "key" : "leadership",
        "value" : 2
    }, {
        "key" : "professionalism",
        "value" : 3
    } ],
    "medicalFolder" : null,
    "listLicenses" : [ {
        "numLicense" : "05290771000019",
        "structureId" : "541168295971d35c1f2d1b60",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "B",
                "label" : "Joueur Libre",
                "order" : NumberInt(2)
            }
        } ]
    } ]
});

db.SB_Person.insert({
    "_id" : "541d3136f61fbf69868c1230",
    "name" : "Rocheteau",
    "firstname" : "Dominique",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(-472266000000),
    "birthcity" : "Paris",
    "birthcountry" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "nationality" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "job" : "Soudeur",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "75000",
        "city" : "Paris",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 20",
        "office" : "",
        "cellphone" : "07 06 00 00 20",
        "email" : "rocheteau@toto.com"
    },
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "good",
        "weight" : "77",
        "height" : "177",
        "squadnumber" : "18",
        "positionType" : "forward",
        "laterality" : "righthanded",
    },
    "physicalFolder" : [ {
        "key" : "explosive",
        "value" : 5
    }, {
        "key" : "speed",
        "value" : 2
    }, {
        "key" : "traction",
        "value" : 3
    }, {
        "key" : "verticaljump",
        "value" : 5
    }, {
        "key" : "endurance",
        "value" : 2
    }, {
        "key" : "equilibrium",
        "value" : 4
    }, {
        "key" : "strenght",
        "value" : 4
    }, {
        "key" : "nimbleness",
        "value" : 3
    } ],
    "technicalFolder" : [ {
        "key" : "pass",
        "value" : 1
    }, {
        "key" : "center",
        "value" : 3
    }, {
        "key" : "corner",
        "value" : 1
    }, {
        "key" : "freekick",
        "value" : 3
    }, {
        "key" : "penalty",
        "value" : 2
    }, {
        "key" : "shoot",
        "value" : 4
    }, {
        "key" : "dribble",
        "value" : 3
    }, {
        "key" : "tacle",
        "value" : 2
    } ],
    "mentalFolder" : [ {
        "key" : "aggressiveness",
        "value" : 5
    }, {
        "key" : "anticipation",
        "value" : 5
    }, {
        "key" : "concentration",
        "value" : 5
    }, {
        "key" : "altruism",
        "value" : 5
    }, {
        "key" : "courage",
        "value" : 4
    }, {
        "key" : "creativity",
        "value" : 1
    }, {
        "key" : "motivation",
        "value" : 3
    }, {
        "key" : "self-control",
        "value" : 5
    }, {
        "key" : "leadership",
        "value" : 2
    }, {
        "key" : "professionalism",
        "value" : 1
    } ],
    "medicalFolder" : null,
    "listLicenses" : [ {
        "numLicense" : "05290771000020",
        "structureId" : "541168295971d35c1f2d1b60",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "B",
                "label" : "Joueur Libre",
                "order" : NumberInt(2)
            }
        } ]
    } ]
});

db.SB_Person.insert({
    "_id" : "541d3136f61fbf69868c1231",
    "name" : "Six",
    "firstname" : "Didier",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(-484884000000),
    "birthcity" : "Paris",
    "birthcountry" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "nationality" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "job" : "Ecrivain",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "70629",
        "city" : "Stuttgart",
        "country" : "Allemagne"
    },
    "contact" : {
        "home" : "05 04 00 00 21",
        "office" : "",
        "cellphone" : "07 06 00 00 21",
        "email" : "six@toto.com"
    },
    "status" : {
        "availability" : {
            "value" : "unavailable",
            "cause" : "suspended",
            "endDate" : NumberLong(1435615200000)
        },
        "stateForm" : "middling",
        "weight" : "80",
        "height" : "180",
        "squadnumber" : "19",
        "positionType" : "forward",
        "laterality" : "righthanded",
    },
    "physicalFolder" : [ {
        "key" : "explosive",
        "value" : 1
    }, {
        "key" : "speed",
        "value" : 3
    }, {
        "key" : "traction",
        "value" : 1
    }, {
        "key" : "verticaljump",
        "value" : 4
    }, {
        "key" : "endurance",
        "value" : 4
    }, {
        "key" : "equilibrium",
        "value" : 1
    }, {
        "key" : "strenght",
        "value" : 4
    }, {
        "key" : "nimbleness",
        "value" : 1
    } ],
    "technicalFolder" : [ {
        "key" : "pass",
        "value" : 2
    }, {
        "key" : "center",
        "value" : 3
    }, {
        "key" : "corner",
        "value" : 3
    }, {
        "key" : "freekick",
        "value" : 2
    }, {
        "key" : "penalty",
        "value" : 3
    }, {
        "key" : "shoot",
        "value" : 4
    }, {
        "key" : "dribble",
        "value" : 3
    }, {
        "key" : "tacle",
        "value" : 2
    } ],
    "mentalFolder" : [ {
        "key" : "aggressiveness",
        "value" : 3
    }, {
        "key" : "anticipation",
        "value" : 5
    }, {
        "key" : "concentration",
        "value" : 3
    }, {
        "key" : "altruism",
        "value" : 4
    }, {
        "key" : "courage",
        "value" : 1
    }, {
        "key" : "creativity",
        "value" : 1
    }, {
        "key" : "motivation",
        "value" : 2
    }, {
        "key" : "self-control",
        "value" : 3
    }, {
        "key" : "leadership",
        "value" : 4
    }, {
        "key" : "professionalism",
        "value" : 5
    } ],
    "medicalFolder" : null,
    "listLicenses" : [ {
        "numLicense" : "05290771000021",
        "structureId" : "541168295971d35c1f2d1b60",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "B",
                "label" : "Joueur Libre",
                "order" : NumberInt(2)
            }
        } ]
    } ]
});

db.SB_Person.insert({
    "_id" : "541d3136f61fbf69868c1232",
    "name" : "Soler",
    "firstname" : "Gerard",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(-497412000000),
    "birthcity" : "Toulouse",
    "birthcountry" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "nationality" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "job" : "VRP",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "31000",
        "city" : "Toulouse",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 22",
        "office" : "",
        "cellphone" : "07 06 00 00 22",
        "email" : "soler@toto.com"
    },
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "good",
        "weight" : "76",
        "height" : "176",
        "squadnumber" : "20",
        "positionType" : "forward",
        "laterality" : "righthanded",
    },
    "physicalFolder" : [ {
        "key" : "explosive",
        "value" : 1
    }, {
        "key" : "speed",
        "value" : 4
    }, {
        "key" : "traction",
        "value" : 4
    }, {
        "key" : "verticaljump",
        "value" : 2
    }, {
        "key" : "endurance",
        "value" : 5
    }, {
        "key" : "equilibrium",
        "value" : 4
    }, {
        "key" : "strenght",
        "value" : 1
    }, {
        "key" : "nimbleness",
        "value" : 3
    } ],
    "technicalFolder" : [ {
        "key" : "pass",
        "value" : 4
    }, {
        "key" : "center",
        "value" : 2
    }, {
        "key" : "corner",
        "value" : 5
    }, {
        "key" : "freekick",
        "value" : 1
    }, {
        "key" : "penalty",
        "value" : 1
    }, {
        "key" : "shoot",
        "value" : 2
    }, {
        "key" : "dribble",
        "value" : 2
    }, {
        "key" : "tacle",
        "value" : 4
    } ],
    "mentalFolder" : [ {
        "key" : "aggressiveness",
        "value" : 2
    }, {
        "key" : "anticipation",
        "value" : 3
    }, {
        "key" : "concentration",
        "value" : 1
    }, {
        "key" : "altruism",
        "value" : 4
    }, {
        "key" : "courage",
        "value" : 1
    }, {
        "key" : "creativity",
        "value" : 5
    }, {
        "key" : "motivation",
        "value" : 5
    }, {
        "key" : "self-control",
        "value" : 2
    }, {
        "key" : "leadership",
        "value" : 3
    }, {
        "key" : "professionalism",
        "value" : 5
    } ],
    "medicalFolder" : null,
    "listLicenses" : [ {
        "numLicense" : "05290771000020",
        "structureId" : "541168295971d35c1f2d1b60",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "B",
                "label" : "Joueur Libre",
                "order" : NumberInt(2)
            }
        } ]
    } ]
});