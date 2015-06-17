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
 * Vidage de la collection Person (Club Dunkerque handball sen)
 */
db.Person.remove({ "$and" : [ { "listLicenses.structureId" : "541168295971d35c1f2d1b5e"} , 
                              { "listLicenses.listHistoLicense" : 
                                { "$elemMatch" : { "seasonCode" : "SAI-2014" , "categoryAgeCode" : "sen"}}}
]});

/*******************************************************************************
 * Alimentation Person Club A Handball
 * *************************************************************
 */

db.Person.insert({
    "_id" : "541d2c5fb3f78c0317eea2be",
    "name" : "Gerard",
    "firstname" : "Vincent",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(535071600000),
    "birthcity" : "Woippy",
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
        "place" : " Stades de Flandres, Avenue de Rosendaël",
        "zipcode" : "59240",
        "city" : " DUNKERQUE",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 01",
        "office" : "",
        "cellphone" : "07 06 00 00 01",
        "email" : "Nom01@toto.com"
    },

    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : "97",
        "height" : "188",
        "squadnumber" : "1",
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
        "numLicense" : "1159002100001",
        "structureId" : "541168295971d35c1f2d1b5e",
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
    "_id" : "541d3136f61fbf69868c1214",
    "name" : "Annotel",
    "firstname" : "William",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(417736800000),
    "birthcity" : "Lagny/Marne",
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
        "place" : " Stades de Flandres, Avenue de Rosendaël",
        "zipcode" : "59240",
        "city" : " DUNKERQUE",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 02",
        "office" : "",
        "cellphone" : "07 06 00 00 02",
        "email" : "Nom02@toto.com"
    },

    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : "93",
        "height" : "195",
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
        "numLicense" : "1159002103047",
        "structureId" : "541168295971d35c1f2d1b5e",
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
    "_id" : "541d3136f61fbf69868c1215",
    "name" : "Butto",
    "firstname" : "Baptiste",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(540169200000),
    "birthcity" : "Algrange",
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
        "place" : " Stades de Flandres, Avenue de Rosendaël",
        "zipcode" : "59240",
        "city" : " DUNKERQUE",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 03",
        "office" : "",
        "cellphone" : "07 06 00 00 03",
        "email" : "Nom03@toto.com"
    },

    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : "77",
        "height" : "183",
        "squadnumber" : "57",
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
        "numLicense" : "1159002102829",
        "structureId" : "541168295971d35c1f2d1b5e",
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
    "_id" : "541d3136f61fbf69868c1216",
    "name" : "Touati",
    "firstname" : "Jaleleddine",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(395272800000),
    "birthcity" : "Tunis",
    "birthcountry" : {"_id" : "CNTR-788-TN-TUN" , "codeOSCE" : NumberInt(788) , "alpha2" : "TN" , "alpha3" : "TUN" , "label" : "settings.Country.TN.name"},
    "nationality" : {"_id" : "CNTR-788-TN-TUN" , "codeOSCE" : NumberInt(788) , "alpha2" : "TN" , "alpha3" : "TUN" , "label" : "settings.Country.TN.name"},
    "job" : "Handballeur",
    "address" : {
        "formatedAddress" : "",
        "place" : " Stades de Flandres, Avenue de Rosendaël",
        "zipcode" : "59240",
        "city" : " DUNKERQUE",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 04",
        "office" : "",
        "cellphone" : "07 06 00 00 04",
        "email" : "toutati@toto.com"
    },

    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : "78",
        "height" : "179",
        "squadnumber" : "7",
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
        "numLicense" : "1159002100004",
        "structureId" : "541168295971d35c1f2d1b5e",
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
    "_id" : "541d3136f61fbf69868c1217",
    "name" : "Emonet",
    "firstname" : "Julien",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(694479600000),
    "birthcity" : "Sartrouville",
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
        "place" : " Stades de Flandres, Avenue de Rosendaël",
        "zipcode" : "59240",
        "city" : " DUNKERQUE",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 05",
        "office" : "",
        "cellphone" : "07 06 00 00 05",
        "email" : "emonet@toto.com"
    },

    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : "90",
        "height" : "182",
        "squadnumber" : "17",
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
        "numLicense" : "1159002100005",
        "structureId" : "541168295971d35c1f2d1b5e",
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
    "_id" : "541d3136f61fbf69868c1218",
    "name" : "Afgour",
    "firstname" : "Benjamin",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(670456800000),
    "birthcity" : "Rethel",
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
        "place" : " Stades de Flandres, Avenue de Rosendaël",
        "zipcode" : "59240",
        "city" : " DUNKERQUE",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 06",
        "office" : "",
        "cellphone" : "07 06 00 00 06",
        "email" : "afgour@toto.com"
    },

    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : "105",
        "height" : "195",
        "squadnumber" : "3",
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
        "numLicense" : "1159002100006",
        "structureId" : "541168295971d35c1f2d1b5e",
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
    "_id" : "541d3136f61fbf69868c1219",
    "name" : "Mokrani",
    "firstname" : "Mohamed",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(349743600000),
    "birthcity" : "Ivry sur Seine",
    "birthcountry" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : NumberInt(250),
        "alpha2" : "FR",
        "alpha3" : "FRA",
        "label" : "settings.Country.FR.name"
    },
    "nationality" : {"_id" : "CNTR-12-DZ-DZA" , "codeOSCE" : NumberInt(12) , "alpha2" : "DZ" , "alpha3" : "DZA" , "label" : "settings.Country.DZ.name"},
    "job" : "Handballeur",
    "address" : {
        "formatedAddress" : "",
        "place" : " Stades de Flandres, Avenue de Rosendaël",
        "zipcode" : "59240",
        "city" : " DUNKERQUE",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 07",
        "office" : "",
        "cellphone" : "07 06 00 00 07",
        "email" : "mokrani@toto.com"
    },

    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : "94",
        "height" : "186",
        "squadnumber" : "46",
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
        "numLicense" : "1159002100007",
        "structureId" : "541168295971d35c1f2d1b5e",
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
    "_id" : "541d3136f61fbf69868c121a",
    "name" : "Causse",
    "firstname" : "Théophile",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(718844400000),
    "birthcity" : "Port-au-Prince",
    "birthcountry" : {"_id" : "CNTR-332-HT-HTI" , "codeOSCE" : NumberInt(332) , "alpha2" : "HT" , "alpha3" : "HTI" , "label" : "settings.Country.HT.name"},
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
        "place" : " Stades de Flandres, Avenue de Rosendaël",
        "zipcode" : "59240",
        "city" : " DUNKERQUE",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 08",
        "office" : "",
        "cellphone" : "07 06 00 00 08",
        "email" : "causse@toto.com"
    },

    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : "72",
        "height" : "180",
        "squadnumber" : "9",
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
        "numLicense" : "1159002100008",
        "structureId" : "541168295971d35c1f2d1b5e",
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
    "_id" : "541d3136f61fbf69868c121b",
    "name" : "Grocaut",
    "firstname" : "Mickael",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(325548000000),
    "birthcity" : "Hénin-Beaumont",
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
        "place" : " Stades de Flandres, Avenue de Rosendaël",
        "zipcode" : "59240",
        "city" : " DUNKERQUE",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 09",
        "office" : "",
        "cellphone" : "07 06 00 00 09",
        "email" : "grocaut@toto.com"
    },

    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : "103",
        "height" : "194",
        "squadnumber" : "21",
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
        "numLicense" : "1159002100009",
        "structureId" : "541168295971d35c1f2d1b5e",
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
    "_id" : "541d3136f61fbf69868c121c",
    "name" : "Nagy",
    "firstname" : "Kornel",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(532911600000),
    "birthcity" : "Püspökladany",
    "birthcountry" : {"_id" : "CNTR-348-HU-HUN" , "codeOSCE" : NumberInt(348) , "alpha2" : "HU" , "alpha3" : "HUN" , "label" : "settings.Country.HU.name"},
    "nationality" : {"_id" : "CNTR-348-HU-HUN" , "codeOSCE" : NumberInt(348) , "alpha2" : "HU" , "alpha3" : "HUN" , "label" : "settings.Country.HU.name"},
    "job" : "Handballeur",
    "address" : {
        "formatedAddress" : "",
        "place" : " Stades de Flandres, Avenue de Rosendaël",
        "zipcode" : "59240",
        "city" : " DUNKERQUE",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 10",
        "office" : "",
        "cellphone" : "07 06 00 00 10",
        "email" : "nagy@toto.com"
    },

    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : "100",
        "height" : "195",
        "squadnumber" : "6",
        "positionType" : "left-backcourt",
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
        "numLicense" : "1159002100010",
        "structureId" : "541168295971d35c1f2d1b5e",
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
    "_id" : "5509f71adb8f8b6e2f51f4d4",
    "name" : "Pejovic",
    "firstname" : "Zarko",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(506991600000),
    "birthcity" : "Mojkovac",
    "birthcountry" : {"_id" : "CNTR-499-ME-MNE" , "codeOSCE" : NumberInt(499) , "alpha2" : "ME" , "alpha3" : "MNE" , "label" : "settings.Country.ME.name"},
    "nationality" : {"_id" : "CNTR-499-ME-MNE" , "codeOSCE" : NumberInt(499) , "alpha2" : "ME" , "alpha3" : "MNE" , "label" : "settings.Country.ME.name"},
    "job" : "Handballeur",
    "address" : {
        "formatedAddress" : "",
        "place" : " Stades de Flandres, Avenue de Rosendaël",
        "zipcode" : "59240",
        "city" : " DUNKERQUE",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 10",
        "office" : "",
        "cellphone" : "07 06 00 00 10",
        "email" : "pejovic@toto.com"
    },

    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : "94",
        "height" : "190",
        "squadnumber" : "19",
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
        "numLicense" : "1159002100010",
        "structureId" : "541168295971d35c1f2d1b5e",
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
    "_id" : "5509f71adb8f8b6e2f51f4d5",
    "name" : "Guillard",
    "firstname" : "Romain",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(506041200000),
    "birthcity" : "Suresnes",
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
        "place" : " Stades de Flandres, Avenue de Rosendaël",
        "zipcode" : "59240",
        "city" : " DUNKERQUE",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 10",
        "office" : "",
        "cellphone" : "07 06 00 00 10",
        "email" : "guillard@toto.com"
    },

    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : "100",
        "height" : "190",
        "squadnumber" : "19",
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
        "numLicense" : "1159002100010",
        "structureId" : "541168295971d35c1f2d1b5e",
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
    "_id" : "5509f71adb8f8b6e2f51f4d6",
    "name" : "Lamon",
    "firstname" : "Bastien",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(348620400000),
    "birthcity" : "Roubaix",
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
        "place" : " Stades de Flandres, Avenue de Rosendaël",
        "zipcode" : "59240",
        "city" : " DUNKERQUE",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 10",
        "office" : "",
        "cellphone" : "07 06 00 00 10",
        "email" : "lamon@toto.com"
    },

    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : "80",
        "height" : "186",
        "squadnumber" : "4",
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
        "numLicense" : "1159002100010",
        "structureId" : "541168295971d35c1f2d1b5e",
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
    "_id" : "5509f71adb8f8b6e2f51f4d7",
    "name" : "Paczkowski",
    "firstname" : "Pawel",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(740008800000),
    "birthcity" : "Swiecie",
    "birthcountry" : {"_id" : "CNTR-616-PL-POL" , "codeOSCE" : NumberInt(616) , "alpha2" : "PL" , "alpha3" : "POL" , "label" : "settings.Country.PL.name"},
    "nationality" : {"_id" : "CNTR-616-PL-POL" , "codeOSCE" : NumberInt(616) , "alpha2" : "PL" , "alpha3" : "POL" , "label" : "settings.Country.PL.name"},
    "job" : "Handballeur",
    "address" : {
        "formatedAddress" : "",
        "place" : " Stades de Flandres, Avenue de Rosendaël",
        "zipcode" : "59240",
        "city" : " DUNKERQUE",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 10",
        "office" : "",
        "cellphone" : "07 06 00 00 10",
        "email" : "paczkowski@toto.com"
    },

    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : "93",
        "height" : "195",
        "squadnumber" : "32",
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
        "numLicense" : "1159002102916",
        "structureId" : "541168295971d35c1f2d1b5e",
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
    "_id" : "550a02acdb8f8b6e2f51f4da",
    "name" : "Soudry",
    "firstname" : "Pierre",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(575420400000),
    "birthcity" : "Amiens",
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
        "place" : " Stades de Flandres, Avenue de Rosendaël",
        "zipcode" : "59240",
        "city" : " DUNKERQUE",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 10",
        "office" : "",
        "cellphone" : "07 06 00 00 10",
        "email" : "soudry@toto.com"
    },

    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : "81",
        "height" : "183",
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
        "numLicense" : "1159002102916",
        "structureId" : "541168295971d35c1f2d1b5e",
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
