//////////////////////////////////////////////////////////
/* 
 * SCRIPT MONGO DB 
 * INJECTION Person
 * V1.2
 * 
 * This script creates documents for collections :
 * - Person
 * 
 * AUTHOR : Christophe kervella pour QaoBee
 */
//////////////////////////////////////////////////////////
/* 
 * Vidage de la collection Person  (Club Demo Football U19)
 */
db.Person.remove({ "$and" : [ { "listLicenses.structureId" : "541168295971d35c1f2d1b60"} , 
                              { "listLicenses.listHistoLicense" : 
                                { "$elemMatch" : { "seasonCode" : "SAI-2014" , "categoryAgeCode" : "u19"}}}
]});

/** ************************************************************* */
/*
 * Alimentation Person Club A Football U19
 */
/** ************************************************************* */
db.Person.insert({
    "_id" : "54c606cdb39d53f0fb9477b2",
    "name" : "Barthez",
    "firstname" : "Fabien",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(804290400000),
    "birthcity" : "Paris",
    "birthcountry" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : NumberInt(250),
        "alpha2" : "FR",
        "alpha3" : "FRA",
        "label" : "settings.Country.FR.name"
    },
    "nationality" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : NumberInt(250),
        "alpha2" : "FR",
        "alpha3" : "FRA",
        "label" : "settings.Country.FR.name"
    },
    "job" : "Pizzaiolo",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "00980",
        "city" : "Monaco",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 10 01",
        "office" : "",
        "cellphone" : "07 06 00 10 01",
        "email" : "Barthez@toto.com"
    },
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "poor",
        "weight" : "78",
        "height" : "178",
        "squadnumber" : "16",
        "positionType" : "goalkeeper",
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
        "value" : 5
    }, {
        "key" : "verticaljump",
        "value" : 1
    }, {
        "key" : "endurance",
        "value" : 1
    }, {
        "key" : "equilibrium",
        "value" : 4
    }, {
        "key" : "strenght",
        "value" : 3
    }, {
        "key" : "nimbleness",
        "value" : 4
    } ],
    "technicalFolder" : [ {
        "key" : "pass",
        "value" : 3
    }, {
        "key" : "center",
        "value" : 5
    }, {
        "key" : "corner",
        "value" : 4
    }, {
        "key" : "freekick",
        "value" : 3
    }, {
        "key" : "penalty",
        "value" : 5
    }, {
        "key" : "shoot",
        "value" : 5
    }, {
        "key" : "dribble",
        "value" : 3
    }, {
        "key" : "tacle",
        "value" : 3
    } ],
    "mentalFolder" : [ {
        "key" : "aggressiveness",
        "value" : 4
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
        "value" : 4
    }, {
        "key" : "creativity",
        "value" : 1
    }, {
        "key" : "motivation",
        "value" : 2
    }, {
        "key" : "self-control",
        "value" : 4
    }, {
        "key" : "leadership",
        "value" : 2
    }, {
        "key" : "professionalism",
        "value" : 5
    } ],
    "medicalFolder" : null,
    "listLicenses" : [ {
        "numLicense" : "05290771001001",
        "structureId" : "541168295971d35c1f2d1b60",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "u19",
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

db.Person.insert({
    "_id" : "54c60742b39d53f0fb9477b3",
    "name" : "Charbonnier",
    "firstname" : "Lionel",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(804290400000),
    "birthcity" : "Saint-Etienne",
    "birthcountry" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : NumberInt(250),
        "alpha2" : "FR",
        "alpha3" : "FRA",
        "label" : "settings.Country.FR.name"
    },
    "nationality" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : NumberInt(250),
        "alpha2" : "FR",
        "alpha3" : "FRA",
        "label" : "settings.Country.FR.name"
    },
    "job" : "Maçon",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "42000",
        "city" : "Saint-Etienne",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 10 02",
        "office" : "",
        "cellphone" : "07 06 00 10 02",
        "email" : "Charbonnier@toto.com"
    },
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "excellent",
        "weight" : "90",
        "height" : "190",
        "squadnumber" : "22",
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
        "numLicense" : "05290771001002",
        "structureId" : "541168295971d35c1f2d1b60",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "u19",
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

db.Person.insert({
    "_id" : "54c60777b39d53f0fb9477b4",
    "name" : "Lama",
    "firstname" : "Bernard",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(804290400000),
    "birthcity" : "Monaco",
    "birthcountry" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : NumberInt(250),
        "alpha2" : "FR",
        "alpha3" : "FRA",
        "label" : "settings.Country.FR.name"
    },
    "nationality" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : NumberInt(250),
        "alpha2" : "FR",
        "alpha3" : "FRA",
        "label" : "settings.Country.FR.name"
    },
    "job" : "coiffeur",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "00980",
        "city" : "Monaco",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 10 03",
        "office" : "",
        "cellphone" : "07 06 00 10 03",
        "email" : "lama@toto.com"
    },
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "good",
        "weight" : "73",
        "height" : "173",
        "squadnumber" : "1",
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
        "numLicense" : "05290771001003",
        "structureId" : "541168295971d35c1f2d1b60",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "u19",
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

db.Person.insert({
    "_id" : "54c60777b39d53f0fb9477b5",
    "name" : "Blanc",
    "firstname" : "Laurent",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(804290400000),
    "birthcity" : "Monaco",
    "birthcountry" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : NumberInt(250),
        "alpha2" : "FR",
        "alpha3" : "FRA",
        "label" : "settings.Country.FR.name"
    },
    "nationality" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : NumberInt(250),
        "alpha2" : "FR",
        "alpha3" : "FRA",
        "label" : "settings.Country.FR.name"
    },
    "job" : "Plongeur",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "00980",
        "city" : "Monaco",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 10 04",
        "office" : "",
        "cellphone" : "07 06 00 10 04",
        "email" : "blanc@toto.com"
    },
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "excellent",
        "weight" : "72",
        "height" : "172",
        "squadnumber" : "5",
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
        "numLicense" : "05290771001004",
        "structureId" : "541168295971d35c1f2d1b60",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "u19",
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

db.Person.insert({
    "_id" : "54c60777b39d53f0fb9477b6",
    "name" : "Candela",
    "firstname" : "Vincent",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(804290400000),
    "birthcity" : "Saint-Etienne",
    "birthcountry" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : NumberInt(250),
        "alpha2" : "FR",
        "alpha3" : "FRA",
        "label" : "settings.Country.FR.name"
    },
    "nationality" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : NumberInt(250),
        "alpha2" : "FR",
        "alpha3" : "FRA",
        "label" : "settings.Country.FR.name"
    },
    "job" : "Dentiste",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "42000",
        "city" : "Saint-Etienne",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 10 05",
        "office" : "",
        "cellphone" : "07 06 00 10 05",
        "email" : "candela@toto.com"
    },
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "excellent",
        "weight" : "82",
        "height" : "182",
        "squadnumber" : "2",
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
        "numLicense" : "05290771001005",
        "structureId" : "541168295971d35c1f2d1b60",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "u19",
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

db.Person.insert({
    "_id" : "54c60777b39d53f0fb9477b7",
    "name" : "Desailly",
    "firstname" : "Marcel",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(804290400000),
    "birthcity" : "Nantes",
    "birthcountry" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : NumberInt(250),
        "alpha2" : "FR",
        "alpha3" : "FRA",
        "label" : "settings.Country.FR.name"
    },
    "nationality" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : NumberInt(250),
        "alpha2" : "FR",
        "alpha3" : "FRA",
        "label" : "settings.Country.FR.name"
    },
    "job" : "ferrailleur",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "44000",
        "city" : "Nantes",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 10 06",
        "office" : "",
        "cellphone" : "07 06 00 10 06",
        "email" : "desailly@toto.com"
    },
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "good",
        "weight" : "86",
        "height" : "186",
        "squadnumber" : "8",
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
        "numLicense" : "05290771001006",
        "structureId" : "541168295971d35c1f2d1b60",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "u19",
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

db.Person.insert({
    "_id" : "54c60777b39d53f0fb9477b8",
    "name" : "Leboeuf",
    "firstname" : "Franck",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(804290400000),
    "birthcity" : "Fort-de-France",
    "birthcountry" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : NumberInt(250),
        "alpha2" : "FR",
        "alpha3" : "FRA",
        "label" : "settings.Country.FR.name"
    },
    "nationality" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : NumberInt(250),
        "alpha2" : "FR",
        "alpha3" : "FRA",
        "label" : "settings.Country.FR.name"
    },
    "job" : "Vigile",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "42000",
        "city" : "Saint-Etienne",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 10 07",
        "office" : "",
        "cellphone" : "07 06 00 10 07",
        "email" : "leboeuf@toto.com"
    },
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : "72",
        "height" : "172",
        "squadnumber" : "18",
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
        "numLicense" : "05290771001007",
        "structureId" : "541168295971d35c1f2d1b60",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "u19",
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

db.Person.insert({
    "_id" : "54c60777b39d53f0fb9477b9",
    "name" : "Lizarazu",
    "firstname" : "Bixente",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(804290400000),
    "birthcity" : "Toulouse",
    "birthcountry" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : NumberInt(250),
        "alpha2" : "FR",
        "alpha3" : "FRA",
        "label" : "settings.Country.FR.name"
    },
    "nationality" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : NumberInt(250),
        "alpha2" : "FR",
        "alpha3" : "FRA",
        "label" : "settings.Country.FR.name"
    },
    "job" : "Maçon",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "31000",
        "city" : "Toulouse",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 10 08",
        "office" : "",
        "cellphone" : "07 06 00 10 08",
        "email" : "bixente@toto.com"
    },
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : "77",
        "height" : "177",
        "squadnumber" : "3",
        "positionType" : "defender",
        "laterality" : "lefthanded",
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
        "numLicense" : "05290771001008",
        "structureId" : "541168295971d35c1f2d1b60",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "u19",
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

db.Person.insert({
    "_id" : "54c609fbb39d53f0fb9477b5",
    "name" : "Thuram",
    "firstname" : "Lilian",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(804290400000),
    "birthcity" : "Saint-Etienne",
    "birthcountry" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : NumberInt(250),
        "alpha2" : "FR",
        "alpha3" : "FRA",
        "label" : "settings.Country.FR.name"
    },
    "nationality" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : NumberInt(250),
        "alpha2" : "FR",
        "alpha3" : "FRA",
        "label" : "settings.Country.FR.name"
    },
    "job" : "Assureur",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "42000",
        "city" : "Saint-Etienne",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 10 09",
        "office" : "",
        "cellphone" : "07 06 00 10 09",
        "email" : "thuram@toto.com"
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
        "squadnumber" : "15",
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
        "numLicense" : "05290771001009",
        "structureId" : "541168295971d35c1f2d1b60",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "u19",
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

db.Person.insert({
    "_id" : "54c60c4cb39d53f0fb9477b6",
    "name" : "Boghossian",
    "firstname" : "Alain",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(804290400000),
    "birthcity" : "Bordeaux",
    "birthcountry" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : NumberInt(250),
        "alpha2" : "FR",
        "alpha3" : "FRA",
        "label" : "settings.Country.FR.name"
    },
    "nationality" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : NumberInt(250),
        "alpha2" : "FR",
        "alpha3" : "FRA",
        "label" : "settings.Country.FR.name"
    },
    "job" : "Boulanger",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "33000",
        "city" : "Bordeaux",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 10 10",
        "office" : "",
        "cellphone" : "07 06 00 10 10",
        "email" : "boghossian@toto.com"
    },
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "excellent",
        "weight" : "82",
        "height" : "182",
        "squadnumber" : "14",
        "positionType" : "midfielder",
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
        "numLicense" : "05290771001010",
        "structureId" : "541168295971d35c1f2d1b60",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "u19",
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

db.Person.insert({
    "_id" : "54c60cb1b39d53f0fb9477b7",
    "name" : "Deschamps",
    "firstname" : "Didier",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(804290400000),
    "birthcity" : "Saint-Etienne",
    "birthcountry" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : NumberInt(250),
        "alpha2" : "FR",
        "alpha3" : "FRA",
        "label" : "settings.Country.FR.name"
    },
    "nationality" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : NumberInt(250),
        "alpha2" : "FR",
        "alpha3" : "FRA",
        "label" : "settings.Country.FR.name"
    },
    "job" : "VRP",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "42000",
        "city" : "Saint-Etienne",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 10 11",
        "office" : "",
        "cellphone" : "07 06 00 10 11",
        "email" : "deschamps@toto.com"
    },
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "good",
        "weight" : "79",
        "height" : "179",
        "squadnumber" : "7",
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
        "numLicense" : "05290771001011",
        "structureId" : "541168295971d35c1f2d1b60",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "u19",
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

db.Person.insert({
    "_id" : "54c60cfbb39d53f0fb9477b8",
    "name" : "Djorkaeff",
    "firstname" : "Youri",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(804290400000),
    "birthcity" : "Saint-Etienne",
    "birthcountry" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : NumberInt(250),
        "alpha2" : "FR",
        "alpha3" : "FRA",
        "label" : "settings.Country.FR.name"
    },
    "nationality" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : NumberInt(250),
        "alpha2" : "FR",
        "alpha3" : "FRA",
        "label" : "settings.Country.FR.name"
    },
    "job" : "Plombier",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "42000",
        "city" : "Saint-Etienne",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 10 12",
        "office" : "",
        "cellphone" : "07 06 00 10 12",
        "email" : "djorkaeff@toto.com"
    },
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "excellent",
        "weight" : "77",
        "height" : "177",
        "squadnumber" : "6",
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
        "numLicense" : "05290771001012",
        "structureId" : "541168295971d35c1f2d1b60",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "u19",
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
db.Person.insert({
    "_id" : "54c60d55b39d53f0fb9477b9",
    "name" : "Karembeu",
    "firstname" : "Christian",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(804290400000),
    "birthcity" : "Bordeaux",
    "birthcountry" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : NumberInt(250),
        "alpha2" : "FR",
        "alpha3" : "FRA",
        "label" : "settings.Country.FR.name"
    },
    "nationality" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : NumberInt(250),
        "alpha2" : "FR",
        "alpha3" : "FRA",
        "label" : "settings.Country.FR.name"
    },
    "job" : "Taxi",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "33000",
        "city" : "Bordeaux",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 10 13",
        "office" : "",
        "cellphone" : "07 06 00 10 13",
        "email" : "karembeu@toto.com"
    },
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : "78",
        "height" : "178",
        "squadnumber" : "19",
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
        "numLicense" : "05290771001013",
        "structureId" : "541168295971d35c1f2d1b60",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "u19",
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

db.Person.insert({
    "_id" : "54c60dbcb39d53f0fb9477bb",
    "name" : "Petit",
    "firstname" : "Emmanuel",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(804290400000),
    "birthcity" : "Bordeaux",
    "birthcountry" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : NumberInt(250),
        "alpha2" : "FR",
        "alpha3" : "FRA",
        "label" : "settings.Country.FR.name"
    },
    "nationality" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : NumberInt(250),
        "alpha2" : "FR",
        "alpha3" : "FRA",
        "label" : "settings.Country.FR.name"
    },
    "job" : "Couvreur",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "33000",
        "city" : "Bordeaux",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 10 14",
        "office" : "",
        "cellphone" : "07 06 00 10 14",
        "email" : "petit@toto.com"
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
        "squadnumber" : "17",
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
        "numLicense" : "05290771001014",
        "structureId" : "541168295971d35c1f2d1b60",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "u19",
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

db.Person.insert({
    "_id" : "54c60e31b39d53f0fb9477bc",
    "name" : "Pires",
    "firstname" : "Robert",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(804290400000),
    "birthcity" : "Saint-Etienne",
    "birthcountry" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : NumberInt(250),
        "alpha2" : "FR",
        "alpha3" : "FRA",
        "label" : "settings.Country.FR.name"
    },
    "nationality" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : NumberInt(250),
        "alpha2" : "FR",
        "alpha3" : "FRA",
        "label" : "settings.Country.FR.name"
    },
    "job" : "Animateur",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "42000",
        "city" : "Saint-Etienne",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 10 15",
        "office" : "",
        "cellphone" : "07 06 00 10 15",
        "email" : "pires@toto.com"
    },
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : "80",
        "height" : "180",
        "squadnumber" : "11",
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
        "numLicense" : "05290771001015",
        "structureId" : "541168295971d35c1f2d1b60",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "u19",
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

db.Person.insert({
    "_id" : "54c60e85b39d53f0fb9477bd",
    "name" : "Viera",
    "firstname" : "Patrick",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(804290400000),
    "birthcity" : "Bamako",
    "birthcountry" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : NumberInt(250),
        "alpha2" : "FR",
        "alpha3" : "FRA",
        "label" : "settings.Country.FR.name"
    },
    "nationality" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : NumberInt(250),
        "alpha2" : "FR",
        "alpha3" : "FRA",
        "label" : "settings.Country.FR.name"
    },
    "job" : "Platrier",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "33000",
        "city" : "Bordeaux",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 10 16",
        "office" : "",
        "cellphone" : "07 06 00 10 16",
        "email" : "viera@toto.com"
    },
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "excellent",
        "weight" : "68",
        "height" : "168",
        "squadnumber" : "4",
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
        "numLicense" : "05290771001016",
        "structureId" : "541168295971d35c1f2d1b60",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "u19",
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

db.Person.insert({
    "_id" : "54c60ee3b39d53f0fb9477be",
    "name" : "Zidane",
    "firstname" : "Zinédine",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(804290400000),
    "birthcity" : "Monaco",
    "birthcountry" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : NumberInt(250),
        "alpha2" : "FR",
        "alpha3" : "FRA",
        "label" : "settings.Country.FR.name"
    },
    "nationality" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : NumberInt(250),
        "alpha2" : "FR",
        "alpha3" : "FRA",
        "label" : "settings.Country.FR.name"
    },
    "job" : "Flambeur",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "00980",
        "city" : "Monaco",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 10 17",
        "office" : "",
        "cellphone" : "07 06 00 10 17",
        "email" : "zidane@toto.com"
    },
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "good",
        "weight" : "75",
        "height" : "175",
        "squadnumber" : "10",
        "positionType" : "midfielder",
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
        "numLicense" : "05290771001017",
        "structureId" : "541168295971d35c1f2d1b60",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "u19",
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

db.Person.insert({
    "_id" : "54c60f88b39d53f0fb9477bf",
    "name" : "Diomède",
    "firstname" : "Bernard",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(804290400000),
    "birthcity" : "Monaco",
    "birthcountry" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : NumberInt(250),
        "alpha2" : "FR",
        "alpha3" : "FRA",
        "label" : "settings.Country.FR.name"
    },
    "nationality" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : NumberInt(250),
        "alpha2" : "FR",
        "alpha3" : "FRA",
        "label" : "settings.Country.FR.name"
    },
    "job" : "Mécanicien",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "00980",
        "city" : "Monaco",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 10 18",
        "office" : "",
        "cellphone" : "07 06 00 10 18",
        "email" : "diomede@toto.com"
    },
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "poor",
        "weight" : "74",
        "height" : "174",
        "squadnumber" : "13",
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
        "numLicense" : "05290771001018",
        "structureId" : "541168295971d35c1f2d1b60",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "u19",
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
    } ],
});

db.Person.insert({
    "_id" : "54c6102cb39d53f0fb9477c0",
    "name" : "Dugarry",
    "firstname" : "Christophe",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(804290400000),
    "birthcity" : "Bordeaux",
    "birthcountry" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : NumberInt(250),
        "alpha2" : "FR",
        "alpha3" : "FRA",
        "label" : "settings.Country.FR.name"
    },
    "nationality" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : NumberInt(250),
        "alpha2" : "FR",
        "alpha3" : "FRA",
        "label" : "settings.Country.FR.name"
    },
    "job" : "Garagiste",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "33000",
        "city" : "Bordeaux",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 10 19",
        "office" : "",
        "cellphone" : "07 06 00 10 19",
        "email" : "dugarry@toto.com"
    },
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "good",
        "weight" : "74",
        "height" : "174",
        "squadnumber" : "21",
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
        "numLicense" : "05290771001019",
        "structureId" : "541168295971d35c1f2d1b60",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
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

db.Person.insert({
    "_id" : "54c61087b39d53f0fb9477c1",
    "name" : "Guivarc'h",
    "firstname" : "Stéphane",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(804290400000),
    "birthcity" : "Paris",
    "birthcountry" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : NumberInt(250),
        "alpha2" : "FR",
        "alpha3" : "FRA",
        "label" : "settings.Country.FR.name"
    },
    "nationality" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : NumberInt(250),
        "alpha2" : "FR",
        "alpha3" : "FRA",
        "label" : "settings.Country.FR.name"
    },
    "job" : "Soudeur",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "75000",
        "city" : "Paris",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 10 20",
        "office" : "",
        "cellphone" : "07 06 00 10 20",
        "email" : "guivarch@toto.com"
    },
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "good",
        "weight" : "77",
        "height" : "177",
        "squadnumber" : "9",
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
        "numLicense" : "05290771001020",
        "structureId" : "541168295971d35c1f2d1b60",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
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

db.Person.insert({
    "_id" : "54c610e7b39d53f0fb9477c2",
    "name" : "Henry",
    "firstname" : "Thierry",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(804290400000),
    "birthcity" : "Paris",
    "birthcountry" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : NumberInt(250),
        "alpha2" : "FR",
        "alpha3" : "FRA",
        "label" : "settings.Country.FR.name"
    },
    "nationality" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : NumberInt(250),
        "alpha2" : "FR",
        "alpha3" : "FRA",
        "label" : "settings.Country.FR.name"
    },
    "job" : "Ecrivain",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "70629",
        "city" : "Stuttgart",
        "country" : "Allemagne"
    },
    "contact" : {
        "home" : "05 04 00 10 21",
        "office" : "",
        "cellphone" : "07 06 00 10 21",
        "email" : "henry@toto.com"
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
        "squadnumber" : "12",
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
        "numLicense" : "05290771001021",
        "structureId" : "541168295971d35c1f2d1b60",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
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

db.Person.insert({
    "_id" : "54c6112fb39d53f0fb9477c3",
    "name" : "Trezeguet",
    "firstname" : "David",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(804290400000),
    "birthcity" : "Toulouse",
    "birthcountry" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : NumberInt(250),
        "alpha2" : "FR",
        "alpha3" : "FRA",
        "label" : "settings.Country.FR.name"
    },
    "nationality" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : NumberInt(250),
        "alpha2" : "FR",
        "alpha3" : "FRA",
        "label" : "settings.Country.FR.name"
    },
    "job" : "VRP",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "31000",
        "city" : "Toulouse",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 10 22",
        "office" : "",
        "cellphone" : "07 06 00 10 22",
        "email" : "trezeguet@toto.com"
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
        "numLicense" : "05290771001020",
        "structureId" : "541168295971d35c1f2d1b60",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
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