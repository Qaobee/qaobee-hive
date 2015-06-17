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
 * Vidage de la collection Person Cesson handball
 */
db.Person.remove({ "$and" : [ { "listLicenses.structureId" : "541168295971d35c1f2d1b5f"} , 
                              { "listLicenses.listHistoLicense" : 
                                { "$elemMatch" : { "seasonCode" : "SAI-2014" , "categoryAgeCode" : "sen"}}}
]});

/*******************************************************************************
 * Alimentation Person Club A Handball
 * *************************************************************
 */

db.Person.insert({
    "_id" : "550a05dadb8f8b6e2f51f4db",
    "name" : "Batinovic",
    "firstname" : "Damir",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(556236000000),
    "birthcity" : "Zagreb",
    "birthcountry" : {"_id" : "CNTR-191-HR-HRV" , "codeOSCE" : NumberInt(191) , "alpha2" : "HR" , "alpha3" : "HRV" , "label" : "settings.Country.HR.name"},
    "nationality" : {"_id" : "CNTR-191-HR-HRV" , "codeOSCE" : NumberInt(191) , "alpha2" : "HR" , "alpha3" : "HRV" , "label" : "settings.Country.HR.name"},
    "job" : "Handballeur",
    "address" : {
        "formatedAddress" : "",
        "place" : "3, allée de Champagné",
        "zipcode" : "35510",
        "city" : "CESSON-SEVIGNE",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 01",
        "office" : "",
        "cellphone" : "07 06 00 00 01",
        "email" : "batinovic@toto.com"
    },

    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : "95",
        "height" : "193",
        "squadnumber" : "33",
        "positionType" : "center-backcourt",
        "laterality" : "righthanded",
    },
    "physicalFolder" : [ {
        key : "explosive",
        value : NumberInt(4)
    }, {
        key : "speed",
        value : NumberInt(4)
    }, {
        key : "traction",
        value : NumberInt(3)
    }, {
        key : "verticaljump",
        value : NumberInt(5)
    }, {
        key : "endurance",
        value : NumberInt(4)
    }, {
        key : "equilibrium",
        value : NumberInt(5)
    }, {
        key : "strenght",
        value : NumberInt(4)
    }, {
        key : "nimbleness",
        value : NumberInt(4)
    } ],
    "technicalFolder" : [ {
        key : "pass",
        value : NumberInt(5)
    }, {
        key : "center",
        value : NumberInt(5)
    }, {
        key : "corner",
        value : NumberInt(4)
    }, {
        key : "freekick",
        value : NumberInt(5)
    }, {
        key : "penalty",
        value : NumberInt(4)
    }, {
        key : "shoot",
        value : NumberInt(5)
    }, {
        key : "dribble",
        value : NumberInt(5)
    }, {
        key : "tacle",
        value : NumberInt(5)
    } ],
    "mentalFolder" : [ {
        key : "aggressiveness",
        value : NumberInt(5)
    }, {
        key : "anticipation",
        value : NumberInt(5)
    }, {
        key : "concentration",
        value : NumberInt(4)
    }, {
        key : "altruism",
        value : NumberInt(4)
    }, {
        key : "courage",
        value : NumberInt(4)
    }, {
        key : "creativity",
        value : NumberInt(5)
    }, {
        key : "motivation",
        value : NumberInt(5)
    }, {
        key : "self-control",
        value : NumberInt(4)
    }, {
        key : "leadership",
        value : NumberInt(5)
    }, {
        key : "professionalism",
        value : NumberInt(4)
    } ],
    "medicalFolder" : null,
    "listLicenses" : [ {
        "numLicense" : "05290751000001",
        "structureId" : "541168295971d35c1f2d1b5f",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "A",
                "label" : "Licence A",
                "order" : NumberInt(1)
            }
        }, {
            "dateQualification" : NumberLong(1376776800000),
            "dateInscription" : NumberLong(1376776800000),
            "seasonCode" : "SAI-2013",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "A",
                "label" : "Licence A",
                "order" : NumberInt(1)
            }
        } ]
    } ]
});

db.Person.insert({
    "_id" : "550a05e3db8f8b6e2f51f4dc",
    "name" : "Bonnefoi",
    "firstname" : "Kevin",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(691714800000),
    "birthcity" : "Saint-Raphaël",
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
    "job" : "Handballeur",
    "address" : {
        "formatedAddress" : "",
        "place" : "3, allée de Champagné",
        "zipcode" : "35510",
        "city" : "CESSON-SEVIGNE",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 02",
        "office" : "",
        "cellphone" : "07 06 00 00 02",
        "email" : "bonnefoi@toto.com"
    },

    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : "108",
        "height" : "191",
        "squadnumber" : "16",
        "positionType" : "goalkeeper",
        "laterality" : "righthanded",
    },
    "physicalFolder" : [ {
        key : "explosive",
        value : NumberInt(4)
    }, {
        key : "speed",
        value : NumberInt(4)
    }, {
        key : "traction",
        value : NumberInt(3)
    }, {
        key : "verticaljump",
        value : NumberInt(5)
    }, {
        key : "endurance",
        value : NumberInt(4)
    }, {
        key : "equilibrium",
        value : NumberInt(5)
    }, {
        key : "strenght",
        value : NumberInt(4)
    }, {
        key : "nimbleness",
        value : NumberInt(4)
    } ],
    "technicalFolder" : [ {
        key : "pass",
        value : NumberInt(5)
    }, {
        key : "center",
        value : NumberInt(5)
    }, {
        key : "corner",
        value : NumberInt(4)
    }, {
        key : "freekick",
        value : NumberInt(5)
    }, {
        key : "penalty",
        value : NumberInt(4)
    }, {
        key : "shoot",
        value : NumberInt(5)
    }, {
        key : "dribble",
        value : NumberInt(5)
    }, {
        key : "tacle",
        value : NumberInt(5)
    } ],
    "mentalFolder" : [ {
        key : "aggressiveness",
        value : NumberInt(5)
    }, {
        key : "anticipation",
        value : NumberInt(5)
    }, {
        key : "concentration",
        value : NumberInt(4)
    }, {
        key : "altruism",
        value : NumberInt(4)
    }, {
        key : "courage",
        value : NumberInt(4)
    }, {
        key : "creativity",
        value : NumberInt(5)
    }, {
        key : "motivation",
        value : NumberInt(5)
    }, {
        key : "self-control",
        value : NumberInt(4)
    }, {
        key : "leadership",
        value : NumberInt(5)
    }, {
        key : "professionalism",
        value : NumberInt(4)
    } ],
    "medicalFolder" : null,
    "listLicenses" : [ {
        "numLicense" : "05290751000002",
        "structureId" : "541168295971d35c1f2d1b5f",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "A",
                "label" : "Licence A",
                "order" : NumberInt(1)
            }
        }, {
            "dateQualification" : NumberLong(1376776800000),
            "dateInscription" : NumberLong(1376776800000),
            "seasonCode" : "SAI-2013",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "A",
                "label" : "Licence A",
                "order" : NumberInt(1)
            }
        } ]
    } ]
});

db.Person.insert({
    "_id" : "550a05e9db8f8b6e2f51f4dd",
    "name" : "Briffe",
    "firstname" : "Romain",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(601426800000),
    "birthcity" : "Rennes",
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
    "job" : "Handballeur",
    "address" : {
        "formatedAddress" : "",
        "place" : "3, allée de Champagné",
        "zipcode" : "35510",
        "city" : "CESSON-SEVIGNE",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 03",
        "office" : "",
        "cellphone" : "07 06 00 00 03",
        "email" : "briffe@toto.com"
    },

    "status" : {
        "availability" : {
            "value" : "unavailable",
            "cause" : "injured"
        },
        "stateForm" : "middling",
        "weight" : "83",
        "height" : "189",
        "squadnumber" : "13",
        "positionType" : "left-backcourt",
        "laterality" : "righthanded",
    },
    "physicalFolder" : [ {
        key : "explosive",
        value : NumberInt(4)
    }, {
        key : "speed",
        value : NumberInt(4)
    }, {
        key : "traction",
        value : NumberInt(3)
    }, {
        key : "verticaljump",
        value : NumberInt(5)
    }, {
        key : "endurance",
        value : NumberInt(4)
    }, {
        key : "equilibrium",
        value : NumberInt(5)
    }, {
        key : "strenght",
        value : NumberInt(4)
    }, {
        key : "nimbleness",
        value : NumberInt(4)
    } ],
    "technicalFolder" : [ {
        key : "pass",
        value : NumberInt(5)
    }, {
        key : "center",
        value : NumberInt(5)
    }, {
        key : "corner",
        value : NumberInt(4)
    }, {
        key : "freekick",
        value : NumberInt(5)
    }, {
        key : "penalty",
        value : NumberInt(4)
    }, {
        key : "shoot",
        value : NumberInt(5)
    }, {
        key : "dribble",
        value : NumberInt(5)
    }, {
        key : "tacle",
        value : NumberInt(5)
    } ],
    "mentalFolder" : [ {
        key : "aggressiveness",
        value : NumberInt(5)
    }, {
        key : "anticipation",
        value : NumberInt(5)
    }, {
        key : "concentration",
        value : NumberInt(4)
    }, {
        key : "altruism",
        value : NumberInt(4)
    }, {
        key : "courage",
        value : NumberInt(4)
    }, {
        key : "creativity",
        value : NumberInt(5)
    }, {
        key : "motivation",
        value : NumberInt(5)
    }, {
        key : "self-control",
        value : NumberInt(4)
    }, {
        key : "leadership",
        value : NumberInt(5)
    }, {
        key : "professionalism",
        value : NumberInt(4)
    } ],
    "medicalFolder" : null,
    "listLicenses" : [ {
        "numLicense" : "05290751000003",
        "structureId" : "541168295971d35c1f2d1b5f",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "A",
                "label" : "Licence A",
                "order" : NumberInt(1)
            }
        }, {
            "dateQualification" : NumberLong(1376776800000),
            "dateInscription" : NumberLong(1376776800000),
            "seasonCode" : "SAI-2013",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "A",
                "label" : "Licence A",
                "order" : NumberInt(1)
            }
        } ]
    } ]
});

db.Person.insert({
    "_id" : "550a05f7db8f8b6e2f51f4de",
    "name" : "Derbier",
    "firstname" : "Maxime",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(523663200000),
    "birthcity" : "Rennes",
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
    "job" : "Handballeur",
    "address" : {
        "formatedAddress" : "",
        "place" : "3, allée de Champagné",
        "zipcode" : "35510",
        "city" : "CESSON-SEVIGNE",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 04",
        "office" : "",
        "cellphone" : "07 06 00 00 04",
        "email" : "derbier@toto.com"
    },

    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : "77",
        "height" : "179",
        "squadnumber" : "3",
        "positionType" : "right-wingman",
        "laterality" : "lefthanded",
    },
    "physicalFolder" : [ {
        key : "explosive",
        value : NumberInt(4)
    }, {
        key : "speed",
        value : NumberInt(4)
    }, {
        key : "traction",
        value : NumberInt(3)
    }, {
        key : "verticaljump",
        value : NumberInt(5)
    }, {
        key : "endurance",
        value : NumberInt(4)
    }, {
        key : "equilibrium",
        value : NumberInt(5)
    }, {
        key : "strenght",
        value : NumberInt(4)
    }, {
        key : "nimbleness",
        value : NumberInt(4)
    } ],
    "technicalFolder" : [ {
        key : "pass",
        value : NumberInt(5)
    }, {
        key : "center",
        value : NumberInt(5)
    }, {
        key : "corner",
        value : NumberInt(4)
    }, {
        key : "freekick",
        value : NumberInt(5)
    }, {
        key : "penalty",
        value : NumberInt(4)
    }, {
        key : "shoot",
        value : NumberInt(5)
    }, {
        key : "dribble",
        value : NumberInt(5)
    }, {
        key : "tacle",
        value : NumberInt(5)
    } ],
    "mentalFolder" : [ {
        key : "aggressiveness",
        value : NumberInt(5)
    }, {
        key : "anticipation",
        value : NumberInt(5)
    }, {
        key : "concentration",
        value : NumberInt(4)
    }, {
        key : "altruism",
        value : NumberInt(4)
    }, {
        key : "courage",
        value : NumberInt(4)
    }, {
        key : "creativity",
        value : NumberInt(5)
    }, {
        key : "motivation",
        value : NumberInt(5)
    }, {
        key : "self-control",
        value : NumberInt(4)
    }, {
        key : "leadership",
        value : NumberInt(5)
    }, {
        key : "professionalism",
        value : NumberInt(4)
    } ],
    "medicalFolder" : null,
    "listLicenses" : [ {
        "numLicense" : "05290751000004",
        "structureId" : "541168295971d35c1f2d1b5f",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "A",
                "label" : "Licence A",
                "order" : NumberInt(1)
            }
        }, {
            "dateQualification" : NumberLong(1376776800000),
            "dateInscription" : NumberLong(1376776800000),
            "seasonCode" : "SAI-2013",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "A",
                "label" : "Licence A",
                "order" : NumberInt(1)
            }
        } ]
    } ]
});

db.Person.insert({
    "_id" : "550a0600db8f8b6e2f51f4df",
    "name" : "Dore",
    "firstname" : "Benoir",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(444870000000),
    "birthcity" : "Rennes",
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
    "job" : "Handballeur",
    "address" : {
        "formatedAddress" : "",
        "place" : "3, allée de Champagné",
        "zipcode" : "35510",
        "city" : "CESSON-SEVIGNE",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 05",
        "office" : "",
        "cellphone" : "07 06 00 00 05",
        "email" : "dore@toto.com"
    },

    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : "82",
        "height" : "183",
        "squadnumber" : "35",
        "positionType" : "left-wingman",
        "laterality" : "righthanded",
    },
    "physicalFolder" : [ {
        key : "explosive",
        value : NumberInt(4)
    }, {
        key : "speed",
        value : NumberInt(4)
    }, {
        key : "traction",
        value : NumberInt(3)
    }, {
        key : "verticaljump",
        value : NumberInt(5)
    }, {
        key : "endurance",
        value : NumberInt(4)
    }, {
        key : "equilibrium",
        value : NumberInt(5)
    }, {
        key : "strenght",
        value : NumberInt(4)
    }, {
        key : "nimbleness",
        value : NumberInt(4)
    } ],
    "technicalFolder" : [ {
        key : "pass",
        value : NumberInt(5)
    }, {
        key : "center",
        value : NumberInt(5)
    }, {
        key : "corner",
        value : NumberInt(4)
    }, {
        key : "freekick",
        value : NumberInt(5)
    }, {
        key : "penalty",
        value : NumberInt(4)
    }, {
        key : "shoot",
        value : NumberInt(5)
    }, {
        key : "dribble",
        value : NumberInt(5)
    }, {
        key : "tacle",
        value : NumberInt(5)
    } ],
    "mentalFolder" : [ {
        key : "aggressiveness",
        value : NumberInt(5)
    }, {
        key : "anticipation",
        value : NumberInt(5)
    }, {
        key : "concentration",
        value : NumberInt(4)
    }, {
        key : "altruism",
        value : NumberInt(4)
    }, {
        key : "courage",
        value : NumberInt(4)
    }, {
        key : "creativity",
        value : NumberInt(5)
    }, {
        key : "motivation",
        value : NumberInt(5)
    }, {
        key : "self-control",
        value : NumberInt(4)
    }, {
        key : "leadership",
        value : NumberInt(5)
    }, {
        key : "professionalism",
        value : NumberInt(4)
    } ],
    "medicalFolder" : null,
    "listLicenses" : [ {
        "numLicense" : "05290751000005",
        "structureId" : "541168295971d35c1f2d1b5f",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "A",
                "label" : "Licence A",
                "order" : NumberInt(1)
            }
        } ]
    } ]
});

db.Person.insert({
    "_id" : "550a0606db8f8b6e2f51f4e0",
    "name" : "Guillo",
    "firstname" : "Romaric",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(686271600000),
    "birthcity" : "Rennes",
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
    "job" : "Handballeur",
    "address" : {
        "formatedAddress" : "",
        "place" : "3, allée de Champagné",
        "zipcode" : "35510",
        "city" : "CESSON-SEVIGNE",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 06",
        "office" : "",
        "cellphone" : "07 06 00 00 06",
        "email" : "guillo@toto.com"
    },

    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : "106",
        "height" : "207",
        "squadnumber" : "56",
        "positionType" : "pivot",
        "laterality" : "righthanded",
    },
    "physicalFolder" : [ {
        key : "explosive",
        value : NumberInt(4)
    }, {
        key : "speed",
        value : NumberInt(4)
    }, {
        key : "traction",
        value : NumberInt(3)
    }, {
        key : "verticaljump",
        value : NumberInt(5)
    }, {
        key : "endurance",
        value : NumberInt(4)
    }, {
        key : "equilibrium",
        value : NumberInt(5)
    }, {
        key : "strenght",
        value : NumberInt(4)
    }, {
        key : "nimbleness",
        value : NumberInt(4)
    } ],
    "technicalFolder" : [ {
        key : "pass",
        value : NumberInt(5)
    }, {
        key : "center",
        value : NumberInt(5)
    }, {
        key : "corner",
        value : NumberInt(4)
    }, {
        key : "freekick",
        value : NumberInt(5)
    }, {
        key : "penalty",
        value : NumberInt(4)
    }, {
        key : "shoot",
        value : NumberInt(5)
    }, {
        key : "dribble",
        value : NumberInt(5)
    }, {
        key : "tacle",
        value : NumberInt(5)
    } ],
    "mentalFolder" : [ {
        key : "aggressiveness",
        value : NumberInt(5)
    }, {
        key : "anticipation",
        value : NumberInt(5)
    }, {
        key : "concentration",
        value : NumberInt(4)
    }, {
        key : "altruism",
        value : NumberInt(4)
    }, {
        key : "courage",
        value : NumberInt(4)
    }, {
        key : "creativity",
        value : NumberInt(5)
    }, {
        key : "motivation",
        value : NumberInt(5)
    }, {
        key : "self-control",
        value : NumberInt(4)
    }, {
        key : "leadership",
        value : NumberInt(5)
    }, {
        key : "professionalism",
        value : NumberInt(4)
    } ],
    "medicalFolder" : null,
    "listLicenses" : [ {
        "numLicense" : "05290751000006",
        "structureId" : "541168295971d35c1f2d1b5f",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "A",
                "label" : "Licence A",
                "order" : NumberInt(1)
            }
        } ]
    } ]
});

db.Person.insert({
    "_id" : "550a060ddb8f8b6e2f51f4e1",
    "name" : "Hochet",
    "firstname" : "Sylvain",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(564706800000),
    "birthcity" : "Rennes",
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
    "job" : "Handballeur",
    "address" : {
        "formatedAddress" : "",
        "place" : "3, allée de Champagné",
        "zipcode" : "35510",
        "city" : "CESSON-SEVIGNE",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 07",
        "office" : "",
        "cellphone" : "07 06 00 00 07",
        "email" : "hochet@toto.com"
    },

    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : "87",
        "height" : "185",
        "squadnumber" : "11",
        "positionType" : "left-wingman",
        "laterality" : "righthanded",
    },
    "physicalFolder" : [ {
        key : "explosive",
        value : NumberInt(4)
    }, {
        key : "speed",
        value : NumberInt(4)
    }, {
        key : "traction",
        value : NumberInt(3)
    }, {
        key : "verticaljump",
        value : NumberInt(5)
    }, {
        key : "endurance",
        value : NumberInt(4)
    }, {
        key : "equilibrium",
        value : NumberInt(5)
    }, {
        key : "strenght",
        value : NumberInt(4)
    }, {
        key : "nimbleness",
        value : NumberInt(4)
    } ],
    "technicalFolder" : [ {
        key : "pass",
        value : NumberInt(5)
    }, {
        key : "center",
        value : NumberInt(5)
    }, {
        key : "corner",
        value : NumberInt(4)
    }, {
        key : "freekick",
        value : NumberInt(5)
    }, {
        key : "penalty",
        value : NumberInt(4)
    }, {
        key : "shoot",
        value : NumberInt(5)
    }, {
        key : "dribble",
        value : NumberInt(5)
    }, {
        key : "tacle",
        value : NumberInt(5)
    } ],
    "mentalFolder" : [ {
        key : "aggressiveness",
        value : NumberInt(5)
    }, {
        key : "anticipation",
        value : NumberInt(5)
    }, {
        key : "concentration",
        value : NumberInt(4)
    }, {
        key : "altruism",
        value : NumberInt(4)
    }, {
        key : "courage",
        value : NumberInt(4)
    }, {
        key : "creativity",
        value : NumberInt(5)
    }, {
        key : "motivation",
        value : NumberInt(5)
    }, {
        key : "self-control",
        value : NumberInt(4)
    }, {
        key : "leadership",
        value : NumberInt(5)
    }, {
        key : "professionalism",
        value : NumberInt(4)
    } ],
    "medicalFolder" : null,
    "listLicenses" : [ {
        "numLicense" : "05290751000007",
        "structureId" : "541168295971d35c1f2d1b5f",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "A",
                "label" : "Licence A",
                "order" : NumberInt(1)
            }
        } ]
    } ]
});

db.Person.insert({
    "_id" : "550a0614db8f8b6e2f51f4e2",
    "name" : "Lanfranchi",
    "firstname" : "Mathieu",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(401580000000),
    "birthcity" : "Rennes",
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
    "job" : "Handballeur",
    "address" : {
        "formatedAddress" : "",
        "place" : "3, allée de Champagné",
        "zipcode" : "35510",
        "city" : "CESSON-SEVIGNE",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 08",
        "office" : "",
        "cellphone" : "07 06 00 00 08",
        "email" : "lanfranchi@toto.com"
    },

    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : "93",
        "height" : "178",
        "squadnumber" : "23",
        "positionType" : "pivot",
        "laterality" : "righthanded",
    },
    "physicalFolder" : [ {
        key : "explosive",
        value : NumberInt(4)
    }, {
        key : "speed",
        value : NumberInt(4)
    }, {
        key : "traction",
        value : NumberInt(3)
    }, {
        key : "verticaljump",
        value : NumberInt(5)
    }, {
        key : "endurance",
        value : NumberInt(4)
    }, {
        key : "equilibrium",
        value : NumberInt(5)
    }, {
        key : "strenght",
        value : NumberInt(4)
    }, {
        key : "nimbleness",
        value : NumberInt(4)
    } ],
    "technicalFolder" : [ {
        key : "pass",
        value : NumberInt(5)
    }, {
        key : "center",
        value : NumberInt(5)
    }, {
        key : "corner",
        value : NumberInt(4)
    }, {
        key : "freekick",
        value : NumberInt(5)
    }, {
        key : "penalty",
        value : NumberInt(4)
    }, {
        key : "shoot",
        value : NumberInt(5)
    }, {
        key : "dribble",
        value : NumberInt(5)
    }, {
        key : "tacle",
        value : NumberInt(5)
    } ],
    "mentalFolder" : [ {
        key : "aggressiveness",
        value : NumberInt(5)
    }, {
        key : "anticipation",
        value : NumberInt(5)
    }, {
        key : "concentration",
        value : NumberInt(4)
    }, {
        key : "altruism",
        value : NumberInt(4)
    }, {
        key : "courage",
        value : NumberInt(4)
    }, {
        key : "creativity",
        value : NumberInt(5)
    }, {
        key : "motivation",
        value : NumberInt(5)
    }, {
        key : "self-control",
        value : NumberInt(4)
    }, {
        key : "leadership",
        value : NumberInt(5)
    }, {
        key : "professionalism",
        value : NumberInt(4)
    } ],
    "medicalFolder" : null,
    "listLicenses" : [ {
        "numLicense" : "05290751000008",
        "structureId" : "541168295971d35c1f2d1b5f",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "A",
                "label" : "Licence A",
                "order" : NumberInt(1)
            }
        } ]
    } ]
});

db.Person.insert({
    "_id" : "550a061bdb8f8b6e2f51f4e3",
    "name" : "Le boulaire",
    "firstname" : "Léo",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(712274400000),
    "birthcity" : "Rennes",
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
    "job" : "Handballeur",
    "address" : {
        "formatedAddress" : "",
        "place" : "3, allée de Champagné",
        "zipcode" : "35510",
        "city" : "CESSON-SEVIGNE",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 09",
        "office" : "",
        "cellphone" : "07 06 00 00 09",
        "email" : "leboulaire@toto.com"
    },

    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : "85",
        "height" : "180",
        "squadnumber" : "15",
        "positionType" : "right-wingman",
        "laterality" : "lefthanded",
    },
    "physicalFolder" : [ {
        key : "explosive",
        value : NumberInt(4)
    }, {
        key : "speed",
        value : NumberInt(4)
    }, {
        key : "traction",
        value : NumberInt(3)
    }, {
        key : "verticaljump",
        value : NumberInt(5)
    }, {
        key : "endurance",
        value : NumberInt(4)
    }, {
        key : "equilibrium",
        value : NumberInt(5)
    }, {
        key : "strenght",
        value : NumberInt(4)
    }, {
        key : "nimbleness",
        value : NumberInt(4)
    } ],
    "technicalFolder" : [ {
        key : "pass",
        value : NumberInt(5)
    }, {
        key : "center",
        value : NumberInt(5)
    }, {
        key : "corner",
        value : NumberInt(4)
    }, {
        key : "freekick",
        value : NumberInt(5)
    }, {
        key : "penalty",
        value : NumberInt(4)
    }, {
        key : "shoot",
        value : NumberInt(5)
    }, {
        key : "dribble",
        value : NumberInt(5)
    }, {
        key : "tacle",
        value : NumberInt(5)
    } ],
    "mentalFolder" : [ {
        key : "aggressiveness",
        value : NumberInt(5)
    }, {
        key : "anticipation",
        value : NumberInt(5)
    }, {
        key : "concentration",
        value : NumberInt(4)
    }, {
        key : "altruism",
        value : NumberInt(4)
    }, {
        key : "courage",
        value : NumberInt(4)
    }, {
        key : "creativity",
        value : NumberInt(5)
    }, {
        key : "motivation",
        value : NumberInt(5)
    }, {
        key : "self-control",
        value : NumberInt(4)
    }, {
        key : "leadership",
        value : NumberInt(5)
    }, {
        key : "professionalism",
        value : NumberInt(4)
    } ],
    "medicalFolder" : null,
    "listLicenses" : [ {
        "numLicense" : "05290751000009",
        "structureId" : "541168295971d35c1f2d1b5f",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "A",
                "label" : "Licence A",
                "order" : NumberInt(1)
            }
        } ]
    } ]
});

db.Person.insert({
    "_id" : "550a0620db8f8b6e2f51f4e4",
    "name" : "Redei",
    "firstname" : "Istvan",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(430437600000),
    "birthcity" : "Budapest",
    "birthcountry" : {"_id" : "CNTR-348-HU-HUN" , "codeOSCE" : NumberInt(348) , "alpha2" : "HU" , "alpha3" : "HUN" , "label" : "settings.Country.HU.name"},
    "nationality" : {"_id" : "CNTR-348-HU-HUN" , "codeOSCE" : NumberInt(348) , "alpha2" : "HU" , "alpha3" : "HUN" , "label" : "settings.Country.HU.name"},
    "job" : "Handballeur",
    "address" : {
        "formatedAddress" : "",
        "place" : "3, allée de Champagné",
        "zipcode" : "35510",
        "city" : "CESSON-SEVIGNE",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 10",
        "office" : "",
        "cellphone" : "07 06 00 00 10",
        "email" : "redei@toto.com"
    },

    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : "101",
        "height" : "197",
        "squadnumber" : "19",
        "positionType" : "right-backcourt",
        "laterality" : "lefthanded",
    },
    "physicalFolder" : [ {
        key : "explosive",
        value : NumberInt(4)
    }, {
        key : "speed",
        value : NumberInt(4)
    }, {
        key : "traction",
        value : NumberInt(3)
    }, {
        key : "verticaljump",
        value : NumberInt(5)
    }, {
        key : "endurance",
        value : NumberInt(4)
    }, {
        key : "equilibrium",
        value : NumberInt(5)
    }, {
        key : "strenght",
        value : NumberInt(4)
    }, {
        key : "nimbleness",
        value : NumberInt(4)
    } ],
    "technicalFolder" : [ {
        key : "pass",
        value : NumberInt(5)
    }, {
        key : "center",
        value : NumberInt(5)
    }, {
        key : "corner",
        value : NumberInt(4)
    }, {
        key : "freekick",
        value : NumberInt(5)
    }, {
        key : "penalty",
        value : NumberInt(4)
    }, {
        key : "shoot",
        value : NumberInt(5)
    }, {
        key : "dribble",
        value : NumberInt(5)
    }, {
        key : "tacle",
        value : NumberInt(5)
    } ],
    "mentalFolder" : [ {
        key : "aggressiveness",
        value : NumberInt(5)
    }, {
        key : "anticipation",
        value : NumberInt(5)
    }, {
        key : "concentration",
        value : NumberInt(4)
    }, {
        key : "altruism",
        value : NumberInt(4)
    }, {
        key : "courage",
        value : NumberInt(4)
    }, {
        key : "creativity",
        value : NumberInt(5)
    }, {
        key : "motivation",
        value : NumberInt(5)
    }, {
        key : "self-control",
        value : NumberInt(4)
    }, {
        key : "leadership",
        value : NumberInt(5)
    }, {
        key : "professionalism",
        value : NumberInt(4)
    } ],
    "medicalFolder" : null,
    "listLicenses" : [ {
        "numLicense" : "05290751000010",
        "structureId" : "541168295971d35c1f2d1b5f",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "A",
                "label" : "Licence A",
                "order" : NumberInt(1)
            }
        } ]
    } ]
});


db.Person.insert({
    "_id" : "550a0620db8f8b6e2f51f4e5",
    "name" : "Robin",
    "firstname" : "Mickaël",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(486079200000),
    "birthcity" : "Strasbourg",
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
    "job" : "Handballeur",
    "address" : {
        "formatedAddress" : "",
        "place" : "3, allée de Champagné",
        "zipcode" : "35510",
        "city" : "CESSON-SEVIGNE",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 10",
        "office" : "",
        "cellphone" : "07 06 00 00 10",
        "email" : "robin@toto.com"
    },

    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : "87",
        "height" : "190",
        "squadnumber" : "4",
        "positionType" : "goalkeeper",
        "laterality" : "righthanded",
    },
    "physicalFolder" : [ {
        key : "explosive",
        value : NumberInt(4)
    }, {
        key : "speed",
        value : NumberInt(4)
    }, {
        key : "traction",
        value : NumberInt(3)
    }, {
        key : "verticaljump",
        value : NumberInt(5)
    }, {
        key : "endurance",
        value : NumberInt(4)
    }, {
        key : "equilibrium",
        value : NumberInt(5)
    }, {
        key : "strenght",
        value : NumberInt(4)
    }, {
        key : "nimbleness",
        value : NumberInt(4)
    } ],
    "technicalFolder" : [ {
        key : "pass",
        value : NumberInt(5)
    }, {
        key : "center",
        value : NumberInt(5)
    }, {
        key : "corner",
        value : NumberInt(4)
    }, {
        key : "freekick",
        value : NumberInt(5)
    }, {
        key : "penalty",
        value : NumberInt(4)
    }, {
        key : "shoot",
        value : NumberInt(5)
    }, {
        key : "dribble",
        value : NumberInt(5)
    }, {
        key : "tacle",
        value : NumberInt(5)
    } ],
    "mentalFolder" : [ {
        key : "aggressiveness",
        value : NumberInt(5)
    }, {
        key : "anticipation",
        value : NumberInt(5)
    }, {
        key : "concentration",
        value : NumberInt(4)
    }, {
        key : "altruism",
        value : NumberInt(4)
    }, {
        key : "courage",
        value : NumberInt(4)
    }, {
        key : "creativity",
        value : NumberInt(5)
    }, {
        key : "motivation",
        value : NumberInt(5)
    }, {
        key : "self-control",
        value : NumberInt(4)
    }, {
        key : "leadership",
        value : NumberInt(5)
    }, {
        key : "professionalism",
        value : NumberInt(4)
    } ],
    "medicalFolder" : null,
    "listLicenses" : [ {
        "numLicense" : "05290751000010",
        "structureId" : "541168295971d35c1f2d1b5f",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "A",
                "label" : "Licence A",
                "order" : NumberInt(1)
            }
        } ]
    } ]
});


db.Person.insert({
    "_id" : "550a0620db8f8b6e2f51f4e6",
    "name" : "Skatar",
    "firstname" : "mikele",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(504745200000),
    "birthcity" : "Rome",
    "birthcountry" : {"_id" : "CNTR-380-IT-ITA" , "codeOSCE" : NumberInt(380) , "alpha2" : "IT" , "alpha3" : "ITA" , "label" : "settings.Country.IT.name"},
    "nationality" : {"_id" : "CNTR-380-IT-ITA" , "codeOSCE" : NumberInt(380) , "alpha2" : "IT" , "alpha3" : "ITA" , "label" : "settings.Country.IT.name"},
    "job" : "Handballeur",
    "address" : {
        "formatedAddress" : "",
        "place" : "3, allée de Champagné",
        "zipcode" : "35510",
        "city" : "CESSON-SEVIGNE",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 10",
        "office" : "",
        "cellphone" : "07 06 00 00 10",
        "email" : "skatar@toto.com"
    },

    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : "94",
        "height" : "190",
        "squadnumber" : "17",
        "positionType" : "right-backcourt",
        "laterality" : "lefthanded",
    },
    "physicalFolder" : [ {
        key : "explosive",
        value : NumberInt(4)
    }, {
        key : "speed",
        value : NumberInt(4)
    }, {
        key : "traction",
        value : NumberInt(3)
    }, {
        key : "verticaljump",
        value : NumberInt(5)
    }, {
        key : "endurance",
        value : NumberInt(4)
    }, {
        key : "equilibrium",
        value : NumberInt(5)
    }, {
        key : "strenght",
        value : NumberInt(4)
    }, {
        key : "nimbleness",
        value : NumberInt(4)
    } ],
    "technicalFolder" : [ {
        key : "pass",
        value : NumberInt(5)
    }, {
        key : "center",
        value : NumberInt(5)
    }, {
        key : "corner",
        value : NumberInt(4)
    }, {
        key : "freekick",
        value : NumberInt(5)
    }, {
        key : "penalty",
        value : NumberInt(4)
    }, {
        key : "shoot",
        value : NumberInt(5)
    }, {
        key : "dribble",
        value : NumberInt(5)
    }, {
        key : "tacle",
        value : NumberInt(5)
    } ],
    "mentalFolder" : [ {
        key : "aggressiveness",
        value : NumberInt(5)
    }, {
        key : "anticipation",
        value : NumberInt(5)
    }, {
        key : "concentration",
        value : NumberInt(4)
    }, {
        key : "altruism",
        value : NumberInt(4)
    }, {
        key : "courage",
        value : NumberInt(4)
    }, {
        key : "creativity",
        value : NumberInt(5)
    }, {
        key : "motivation",
        value : NumberInt(5)
    }, {
        key : "self-control",
        value : NumberInt(4)
    }, {
        key : "leadership",
        value : NumberInt(5)
    }, {
        key : "professionalism",
        value : NumberInt(4)
    } ],
    "medicalFolder" : null,
    "listLicenses" : [ {
        "numLicense" : "05290751000010",
        "structureId" : "541168295971d35c1f2d1b5f",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "A",
                "label" : "Licence A",
                "order" : NumberInt(1)
            }
        } ]
    } ]
});


db.Person.insert({
    "_id" : "550a0620db8f8b6e2f51f4e7",
    "name" : "Suty",
    "firstname" : "Jérémy",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(529714800000),
    "birthcity" : "Rennes",
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
    "job" : "Handballeur",
    "address" : {
        "formatedAddress" : "",
        "place" : "3, allée de Champagné",
        "zipcode" : "35510",
        "city" : "CESSON-SEVIGNE",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 10",
        "office" : "",
        "cellphone" : "07 06 00 00 10",
        "email" : "suty@toto.com"
    },

    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : "85",
        "height" : "188",
        "squadnumber" : "5",
        "positionType" : "center-backcourt",
        "laterality" : "righthanded",
    },
    "physicalFolder" : [ {
        key : "explosive",
        value : NumberInt(4)
    }, {
        key : "speed",
        value : NumberInt(4)
    }, {
        key : "traction",
        value : NumberInt(3)
    }, {
        key : "verticaljump",
        value : NumberInt(5)
    }, {
        key : "endurance",
        value : NumberInt(4)
    }, {
        key : "equilibrium",
        value : NumberInt(5)
    }, {
        key : "strenght",
        value : NumberInt(4)
    }, {
        key : "nimbleness",
        value : NumberInt(4)
    } ],
    "technicalFolder" : [ {
        key : "pass",
        value : NumberInt(5)
    }, {
        key : "center",
        value : NumberInt(5)
    }, {
        key : "corner",
        value : NumberInt(4)
    }, {
        key : "freekick",
        value : NumberInt(5)
    }, {
        key : "penalty",
        value : NumberInt(4)
    }, {
        key : "shoot",
        value : NumberInt(5)
    }, {
        key : "dribble",
        value : NumberInt(5)
    }, {
        key : "tacle",
        value : NumberInt(5)
    } ],
    "mentalFolder" : [ {
        key : "aggressiveness",
        value : NumberInt(5)
    }, {
        key : "anticipation",
        value : NumberInt(5)
    }, {
        key : "concentration",
        value : NumberInt(4)
    }, {
        key : "altruism",
        value : NumberInt(4)
    }, {
        key : "courage",
        value : NumberInt(4)
    }, {
        key : "creativity",
        value : NumberInt(5)
    }, {
        key : "motivation",
        value : NumberInt(5)
    }, {
        key : "self-control",
        value : NumberInt(4)
    }, {
        key : "leadership",
        value : NumberInt(5)
    }, {
        key : "professionalism",
        value : NumberInt(4)
    } ],
    "medicalFolder" : null,
    "listLicenses" : [ {
        "numLicense" : "05290751000010",
        "structureId" : "541168295971d35c1f2d1b5f",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "A",
                "label" : "Licence A",
                "order" : NumberInt(1)
            }
        } ]
    } ]
});

db.Person.insert({
    "_id" : "550a0620db8f8b6e2f51f4e8",
    "name" : "Celica",
    "firstname" : "Dusko",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(524527200000),
    "birthcity" : "Sarajevo",
    "birthcountry" : {"_id" : "CNTR-70-BA-BIH" , "codeOSCE" : NumberInt(70) , "alpha2" : "BA" , "alpha3" : "BIH" , "label" : "settings.Country.BA.name"},
    "nationality" : {"_id" : "CNTR-70-BA-BIH" , "codeOSCE" : NumberInt(70) , "alpha2" : "BA" , "alpha3" : "BIH" , "label" : "settings.Country.BA.name"},
    "job" : "Handballeur",
    "address" : {
        "formatedAddress" : "",
        "place" : "3, allée de Champagné",
        "zipcode" : "35510",
        "city" : "CESSON-SEVIGNE",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 10",
        "office" : "",
        "cellphone" : "07 06 00 00 10",
        "email" : "celica@toto.com"
    },

    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : "100",
        "height" : "198",
        "squadnumber" : "10",
        "positionType" : "left-backcourt",
        "laterality" : "righthanded",
    },
    "physicalFolder" : [ {
        key : "explosive",
        value : NumberInt(4)
    }, {
        key : "speed",
        value : NumberInt(4)
    }, {
        key : "traction",
        value : NumberInt(3)
    }, {
        key : "verticaljump",
        value : NumberInt(5)
    }, {
        key : "endurance",
        value : NumberInt(4)
    }, {
        key : "equilibrium",
        value : NumberInt(5)
    }, {
        key : "strenght",
        value : NumberInt(4)
    }, {
        key : "nimbleness",
        value : NumberInt(4)
    } ],
    "technicalFolder" : [ {
        key : "pass",
        value : NumberInt(5)
    }, {
        key : "center",
        value : NumberInt(5)
    }, {
        key : "corner",
        value : NumberInt(4)
    }, {
        key : "freekick",
        value : NumberInt(5)
    }, {
        key : "penalty",
        value : NumberInt(4)
    }, {
        key : "shoot",
        value : NumberInt(5)
    }, {
        key : "dribble",
        value : NumberInt(5)
    }, {
        key : "tacle",
        value : NumberInt(5)
    } ],
    "mentalFolder" : [ {
        key : "aggressiveness",
        value : NumberInt(5)
    }, {
        key : "anticipation",
        value : NumberInt(5)
    }, {
        key : "concentration",
        value : NumberInt(4)
    }, {
        key : "altruism",
        value : NumberInt(4)
    }, {
        key : "courage",
        value : NumberInt(4)
    }, {
        key : "creativity",
        value : NumberInt(5)
    }, {
        key : "motivation",
        value : NumberInt(5)
    }, {
        key : "self-control",
        value : NumberInt(4)
    }, {
        key : "leadership",
        value : NumberInt(5)
    }, {
        key : "professionalism",
        value : NumberInt(4)
    } ],
    "medicalFolder" : null,
    "listLicenses" : [ {
        "numLicense" : "05290751000010",
        "structureId" : "541168295971d35c1f2d1b5f",
        "listHistoLicense" : [ {
            "dateQualification" : NumberLong(1408312800000),
            "dateInscription" : NumberLong(1408312800000),
            "seasonCode" : "SAI-2014",
            "categoryAgeCode" : "sen",
            "tarif" : null,
            "typeLicence" : {
                "code" : "A",
                "label" : "Licence A",
                "order" : NumberInt(1)
            }
        } ]
    } ]
});
