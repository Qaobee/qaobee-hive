//////////////////////////////////////////////////////////
/* 
 * SCRIPT MONGO DB
 * PARAMETRAGE STRUCTURE
 * V1.0
 * 
 * This script creates documents for collections :
 * - Structure
 * - StructureCfg
 * 
 * AUTHOR : Nada Vujanic-Maquin pour QaoBee
 */
//////////////////////////////////////////////////////////


/* 
 * Structure : une structure est quasi immuable, cet objet bougera peu dans le temps
 */
db.Structure.remove({});

// Extract value of String from ObjectId - field transformation from Object to String
db.Structure.insert({
    "_id" : "541168295971d35c1f2d1b5e",
    "label" : "Dunkerque Handball",
    "acronym" : "USDK",
    "activity" : {
        "_id" : "ACT-HAND",
        "code" : "ACT-HAND",
        "label" : "admin.settings.activity.handball.label",
        "activated" : true,
        "activityType" : "TEAM_SPORT"
    },
    "address" : {
        "place" : " Stades de Flandres, Avenue de Rosendaël",
        "zipcode" : "59240",
        "city" : " DUNKERQUE",
        "country" : "France"
    },
    "contact" : {
        "home" : null,
        "office" : "03 28 66 91 52",
        "cellphone" : "06 30 35 38 19",
        "fax" : "",
        "email" : "melanie.lefebvre@usdk.fr"
    },
    "country" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "alpha2" : "FR" , "alpha3" : "FRA" , "label" : "settings.Country.FR.name"},
    "avatar" : null
});

db.Structure.insert({
    "_id" : "541168295971d35c1f2d1b5f",
    "label" : "CESSON RENNES METROPOLE HB",
    "acronym" : "CRMBH",
    "activity" : {
        "_id" : "ACT-HAND",
        "code" : "ACT-HAND",
        "label" : "admin.settings.activity.handball.label",
        "activated" : true,
        "activityType" : "TEAM_SPORT"
    },
    "address" : {
        "place" : "3, allée de Champagné",
        "zipcode" : "35510",
        "city" : "CESSON-SEVIGNE",
        "country" : "France"
    },
    "contact" : {
        "home" : null,
        "office" : "02 23 45 07 19",
        "cellphone" : "06 69 97 68 39",
        "fax" : "",
        "email" : "sandrine@cesson-handball.com"
    },
    "country" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "alpha2" : "FR" , "alpha3" : "FRA" , "label" : "settings.Country.FR.name"},
    "avatar" : null
});

db.Structure.insert({
    "_id" : "541168295971d35c1f2d1b60",
    "label" : "Club Demo football",
    "acronym" : "CAFB",
    "activity" : {
        "_id" : "ACT-FOOT",
        "code" : "ACT-FOOT",
        "label" : "admin.settings.activity.football.label",
        "activated" : true,
        "activityType" : "TEAM_SPORT"
    },
    "address" : {
        "place" : "1 Rue Jean Jaurès",
        "zipcode" : "Brest",
        "city" : "29200",
        "country" : "France"
    },
    "contact" : {
        "home" : "02 98 00 00 00",
        "office" : "02 98 01 01 01",
        "cellphone" : "06 06 06 06 06",
        "fax" : "",
        "email" : "0529000@football-france.eu"
    },
    "country" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "alpha2" : "FR" , "alpha3" : "FRA" , "label" : "settings.Country.FR.name"},
    "avatar" : null
});

db.Structure.insert({
    "_id" : "541168295971d35c1f2d1b61",
    "label" : "Club B-football",
    "acronym" : "CBFB",
    "activity" : {
        "_id" : "ACT-FOOT",
        "code" : "ACT-FOOT",
        "label" : "admin.settings.activity.football.label",
        "activated" : true,
        "activityType" : "TEAM_SPORT"
    },
    "address" : {
        "place" : "2 Rue Jean Jaurès",
        "zipcode" : "Brest",
        "city" : "29200",
        "country" : "France"
    },
    "contact" : {
        "home" : "02 98 00 00 00",
        "office" : "02 98 01 01 01",
        "cellphone" : "06 06 06 06 06",
        "fax" : "",
        "email" : "0529000@football-france.eu"
    },
    "country" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "alpha2" : "FR" , "alpha3" : "FRA" , "label" : "settings.Country.FR.name"},
    "avatar" : null
});

//////////////////////////////////////////////////////////

/*
 * Season club configuration : grace a ce document on peut gerer les modifications d'une structure 
 * d'une saison a l'autre, et par catégories d'âge
 */
db.StructureCfg.remove({"structureId" : "541168295971d35c1f2d1b5e", "season" : "SAI-2014"});

///// DEBUT CLUB CONFIGURATION Club Dunkerque Handball, season : 2014-2015/////
id = ObjectId().valueOf();
db.StructureCfg.insert({
    "_id" :  id,
    "structureId" : "541168295971d35c1f2d1b5e" ,
    "season" : {
        "_id" : _id,
        "code" : "SAI-2014",
        "label" : "SAISON 2014-2015",
        /* Start : 01/07/2014*/
        "startDate" : NumberLong(1404165600000),
        /* End : 30/06/2015*/
        "endDate" : NumberLong(1435615200000),
        "activityId" : "ACT-HAND",
        "countryId" : "CNTR-250-FR-FRA"
    } ,
    "listCategoryAge" :
        [{ "code" : "sen",
            "label" : "Senior Gars",
            "ageMax" : NumberInt(150),
            "ageMin" : NumberInt(18),
            "genre" : "Masculin",
            "order" : NumberInt(1),
            "listStaffMember" :
                [
                    { "personId": "54160977d5bd065a1bb1e563",
                        "role" :
                        {
                            "code": "coach",
                            "label": "Coach"
                        }
                    },
                    { "personId": "54160977d5bd065a1bb1e564",

                        "role" :
                        {
                            "code": "acoach",
                            "label": "Coach Adjoint"
                        }
                    }
                ]
        },
            { "code" : "u18G",
                "label" : "Moins de 18 Gars",
                "ageMax" : NumberInt(17),
                "ageMin" : NumberInt(16),
                "genre" : "Masculin",
                "order" : NumberInt(3),
                "listStaffMember" :
                    [
                        { "personId": null,
                            "role" :
                            {
                                "code": "coach",
                                "label": "Coach"
                            }
                        },
                        { "personId": null,

                            "role" :
                            {
                                "code": "acoach",
                                "label": "Coach Adjoint"
                            }
                        }
                    ]
            }
        ],
    "listTeams" :
        [
            { "code" : "sen-team-1",
                "categoryAge" : { "code" : "sen", "label" : "Senior Gars" },
                "label" : "Equipe 1",
                "levelGame" : {
                    "code" : "nv1",
                    "label" : "Elite",
                    "order" : NumberInt(1)
                },
                "listStaffMember" :
                    [
                        { "personId": "54160977d5bd065a1bb1e563",
                        "role" :
                        {
                            "code": "coach",
                            "label": "Coach"
                        }
                        },
                        { "personId": "54160977d5bd065a1bb1e564",
        
                            "role" :
                            {
                                "code": "acoach",
                                "label": "Coach Adjoint"
                            }
                        }
                    ]
            },
            { "code" : "sen-team-2",
                "categoryAge" : { "code" : "sen", "label" : "Senior Gars" },
                "label" : "Equipe 2",
                "levelGame" : {
                    "code" : "nv2",
                    "label" : "National",
                    "order" : NumberInt(2)
                },
                "listStaffMember" :
                    [
                        { "personId": null,
                            "role" :
                            {
                                "code": "coach",
                                "label": "Coach"
                            }
                        }
                    ]
            },
            { "code" : "u18G-team-1",
                "categoryAge" : { "code" : "u18G", "label" : "Moins de 18 Gars" },
                "label" : "Equipe 1",
                "levelGame" : {
                    "code" : "nv2",
                    "label" : "National",
                    "order" : NumberInt(2)
                },
                "listStaffMember" :
                    [
                        { "personId": null,
                            "role" :
                            {
                                "code": "coach",
                                "label": "Coach"
                            }
                        }
                    ]
            }
        ]
});

///// FIN CLUB CONFIGURATION Club Dunkerque Handball, season : 2014-2015/////


db.StructureCfg.remove({"structureId" : "541168295971d35c1f2d1b5f", "season" : "SAI-2014"});

///// DEBUT CLUB CONFIGURATION Club Cesson Sevigne handball, season : 2014-2015/////
id = ObjectId().valueOf();
db.StructureCfg.insert({
    "_id" :  id,
    "structureId" : "541168295971d35c1f2d1b5f" ,
    "season" : {
        "_id" : _id,
        "code" : "SAI-2014",
        "label" : "SAISON 2014-2015",
        /* Start : 01/07/2014*/
        "startDate" : NumberLong(1404165600000),
        /* End : 30/06/2015*/
        "endDate" : NumberLong(1435615200000),
        "activityId" : "ACT-HAND",
        "countryId" : "CNTR-250-FR-FRA"
    } ,
    "listCategoryAge" :
        [{ "code" : "sen",
            "label" : "Senior Gars",
            "ageMax" : NumberInt(150),
            "ageMin" : NumberInt(18),
            "genre" : "Masculin",
            "order" : NumberInt(1),
            "listStaffMember" :
                [
                    { "personId": "5509ef1fdb8f8b6e2f51f4ce",
                        "role" :
                        {
                            "code": "coach",
                            "label": "Coach"
                        }
                    },
                    { "personId": "5509ef1fdb8f8b6e2f51f4cf",

                        "role" :
                        {
                            "code": "acoach",
                            "label": "Coach Adjoint"
                        }
                    }
                ]
        },
            { "code" : "u18G",
                "label" : "Moins de 18 Gars",
                "ageMax" : NumberInt(17),
                "ageMin" : NumberInt(16),
                "genre" : "Masculin",
                "order" : NumberInt(3),
                "listStaffMember" :
                    [
                        { "personId": null,
                            "role" :
                            {
                                "code": "coach",
                                "label": "Coach"
                            }
                        },
                        { "personId": null,

                            "role" :
                            {
                                "code": "acoach",
                                "label": "Coach Adjoint"
                            }
                        }
                    ]
            }
        ],
    "listTeams" :
        [
            { "code" : "sen-team-1",
                "categoryAge" : { "code" : "sen", "label" : "Senior Gars" },
                "label" : "Equipe 1",
                "levelGame" : {
                    "code" : "nv1",
                    "label" : "Elite",
                    "order" : NumberInt(1)
                },
                "listStaffMember" :
                    [
                        { "personId": "5509ef1fdb8f8b6e2f51f4ce",
                        "role" :
                        {
                            "code": "coach",
                            "label": "Coach"
                        }
                        },
                        { "personId": "5509ef1fdb8f8b6e2f51f4cf",
        
                            "role" :
                            {
                                "code": "acoach",
                                "label": "Coach Adjoint"
                            }
                        }
                    ]
            },
            { "code" : "sen-team-2",
                "categoryAge" : { "code" : "sen", "label" : "Senior Gars" },
                "label" : "Equipe 2",
                "levelGame" : {
                    "code" : "nv2",
                    "label" : "National",
                    "order" : NumberInt(2)
                },
                "listStaffMember" :
                    [
                        { "personId": null,
                            "role" :
                            {
                                "code": "coach",
                                "label": "Coach"
                            }
                        }
                    ]
            },
            { "code" : "u18G-team-1",
                "categoryAge" : { "code" : "u18G", "label" : "Moins de 18 Gars" },
                "label" : "Equipe 1",
                "levelGame" : {
                    "code" : "nv2",
                    "label" : "National",
                    "order" : NumberInt(2)
                },
                "listStaffMember" :
                    [
                        { "personId": null,
                            "role" :
                            {
                                "code": "coach",
                                "label": "Coach"
                            }
                        }
                    ]
            }
        ]
});

///// FIN CLUB CONFIGURATION Club Cesson Sevigne handball, season : 2014-2015/////


db.StructureCfg.remove({"structureId" : "541168295971d35c1f2d1b60", "season" : "SAI-2013"});
///// CLUB CONFIGURATION Club A-Football, season : 2013-2014/////
id = ObjectId().valueOf();
db.StructureCfg.insert({
    "_id" :  id,
    "structureId" : "541168295971d35c1f2d1b60" ,
    "season" : {
        "_id" : _id,
        "code" : "SAI-2013",
        "label" : "SAISON 2013-2014",
        /* Start : 01/07/2013*/
        "startDate" : NumberLong(1372629600000),
        /* End : 31/05/2014*/
        "endDate" : NumberLong(1401573600000),
        "activityId" : "ACT-FOOT",
        "countryId" : "CNTR-250-FR-FRA"
    } ,
    "listCategoryAge" :
        [{ "code" : "sen",
            "label" : "Senior Gars",
            "ageMax" : NumberInt(150),
            "ageMin" : NumberInt(18),
            "genre" : "Masculin",
            "order" : NumberInt(1),
            "listStaffMember" :
                [
                    { "personId": "54160977d5bd065a1bb1e565",
                        "role" :
                        {
                            "code": "coach",
                            "label": "Coach"
                        }
                    },
                    { "personId": "54160977d5bd065a1bb1e568",

                        "role" :
                        {
                            "code": "acoach",
                            "label": "Coach Adjoint"
                        }
                    }
                ]
        },
            { "code" : "senF",
                "label" : "Senior Féminin",
                "ageMax" : NumberInt(150),
                "ageMin" : NumberInt(18),
                "genre" : "Féminin",
                "order" : NumberInt(2),
                "listStaffMember" :
                    [
                        { "personId": null,
                            "role" :
                            {
                                "code": "coach",
                                "label": "Coach"
                            }
                        },
                        { "personId": null,

                            "role" :
                            {
                                "code": "acoach",
                                "label": "Coach Adjoint"
                            }
                        }
                    ]
            },
            { "code" : "u17",
                "label" : "U17",
                "ageMax" : NumberInt(16),
                "ageMin" : NumberInt(16),
                "genre" : "Masculin",
                "order" : NumberInt(5),
                "listStaffMember" :
                    [
                        { "personId": null,
                            "role" :
                            {
                                "code": "coach",
                                "label": "Coach"
                            }
                        },
                        { "personId": null,

                            "role" :
                            {
                                "code": "acoach",
                                "label": "Coach Adjoint"
                            }
                        }
                    ]
            },
            { "code" : "u15",
                "label" : "U15",
                "ageMax" : NumberInt(14),
                "ageMin" : NumberInt(14),
                "genre" : "Masculin",
                "order" : NumberInt(8),
                "listStaffMember" :
                    [
                        { "personId": null,
                            "role" :
                            {
                                "code": "coach",
                                "label": "Coach"
                            }
                        },
                        { "personId": null,

                            "role" :
                            {
                                "code": "acoach",
                                "label": "Coach Adjoint"
                            }
                        }
                    ]
            },
            { "code" : "u13",
                "label" : "U13",
                "ageMax" : NumberInt(13),
                "ageMin" : NumberInt(12),
                "genre" : "Masculin",
                "order" : NumberInt(9),
                "listStaffMember" :
                    [
                        { "personId": null,
                            "role" :
                            {
                                "code": "coach",
                                "label": "Coach"
                            }
                        },
                        { "personId": null,

                            "role" :
                            {
                                "code": "acoach",
                                "label": "Coach Adjoint"
                            }
                        }
                    ]
            },
            { "code" : "u11",
                "label" : "U11",
                "ageMax" : NumberInt(11),
                "ageMin" : NumberInt(10),
                "genre" : "Masculin",
                "order" : NumberInt(11),
                "listStaffMember" :
                    [
                        { "personId": null,
                            "role" :
                            {
                                "code": "coach",
                                "label": "Coach"
                            }
                        },
                        { "personId": null,

                            "role" :
                            {
                                "code": "acoach",
                                "label": "Coach Adjoint"
                            }
                        }
                    ]
            }
        ],
    "listTeams" :
        [
            { "code" : "sen-team-1",
                "categoryAge" : { "code" : "sen", "label" : "Senior Gars" },
                "label" : "Equipe 1",
                "levelGame" : {
                    "code" : "nv1",
                    "label" : "Elite",
                    "order" : NumberInt(1)
                },
                "listStaffMember" :
                    [
                        { "personId": "54160977d5bd065a1bb1e565",
                            "role" :
                            {
                                "code": "coach",
                                "label": "Coach"
                            }
                        },
                        { "personId": "54160977d5bd065a1bb1e568",

                            "role" :
                            {
                                "code": "acoach",
                                "label": "Coach Adjoint"
                            }
                        }
                    ]
            },
            { "code" : "sen-team-2",
                "categoryAge" : { "code" : "sen", "label" : "Senior Gars" },
                "label" : "Equipe 2",
                "levelGame" : {
                    "code" : "nv3",
                    "label" : "Régional",
                    "order" : NumberInt(3)
                },
                "listStaffMember" :
                    [
                        { "personId": null,
                            "role" :
                            {
                                "code": "coach",
                                "label": "Coach"
                            }
                        }
                    ]
            },
            { "code" : "senF-team-1",
                "categoryAge" : { "code" : "senF", "label" : "Senior Filles" },
                "label" : "Equipe 1",
                "levelGame" : {
                    "code" : "nv4",
                    "label" : "Départemental",
                    "order" : NumberInt(4)
                },
                "listStaffMember" :
                    [
                        { "personId": null,
                            "role" :
                            {
                                "code": "coach",
                                "label": "Coach"
                            }
                        },
                        { "personId": null,
                            "role" :
                            {
                                "code": "acoach",
                                "label": "Coach Adjoint"
                            }
                        }
                    ]
            },
            { "code" : "u17-team-1",
                "categoryAge" : { "code" : "u17", "label" : "Moins de 17 Gars" },
                "label" : "Equipe 1",
                "levelGame" : {
                    "code" : "nv2",
                    "label" : "National",
                    "order" : NumberInt(2)
                },
                "listStaffMember" :
                    [
                        { "personId": null,
                            "role" :
                            {
                                "code": "coach",
                                "label": "Coach"
                            }
                        }
                    ]
            },
            { "code" : "u15-team-1",
                "categoryAge" : { "code" : "u15", "label" : "Moins de 15 Gars" },
                "label" : "Equipe 1",
                "levelGame" : {
                    "code" : "nv4",
                    "label" : "Départemental",
                    "order" : NumberInt(4)
                },
                "listStaffMember" :
                    [
                        { "personId": null,
                            "role" :
                            {
                                "code": "coach",
                                "label": "Coach"
                            }
                        }
                    ]
            },
            { "code" : "u13-team-1",
                "categoryAge" : { "code" : "u13", "label" : "Moins de 13 Gars" },
                "label" : "Equipe 1",
                "levelGame" : {
                    "code" : "nv4",
                    "label" : "Départemental",
                    "order" : NumberInt(4)
                },
                "listStaffMember" :
                    [
                        { "personId": null,
                            "role" :
                            {
                                "code": "coach",
                                "label": "Coach"
                            }
                        }
                    ]
            },
            { "code" : "u11-team-1",
                "categoryAge" : { "code" : "u11", "label" : "Moins de 11 Gars" },
                "label" : "Equipe 1",
                "levelGame" : {
                    "code" : "nv4",
                    "label" : "Départemental",
                    "order" : NumberInt(4)
                },
                "listStaffMember" :
                    [
                        { "personId": null,
                            "role" :
                            {
                                "code": "coach",
                                "label": "Coach"
                            }
                        }
                    ]
            }
        ]
});

db.StructureCfg.remove({"structureId" : "541168295971d35c1f2d1b60", "season" : "SAI-2014"});
///// CLUB CONFIGURATION Club A-Football, season : 2014-2015/////
id = ObjectId().valueOf();
db.StructureCfg.insert({
    "_id" :  id,
    "structureId" : "541168295971d35c1f2d1b60" ,
    "season" : {
        "_id" : _id,
        "code" : "SAI-2014",
        "label" : "SAISON 2014-2015",
        /* Start : 01/06/2014*/
        "startDate" : NumberLong(1401573600000),
        /* End : 30/06/2015*/
        "endDate" : NumberLong(1435615200000),
        "activityId" : "ACT-FOOT",
        "countryId" : "CNTR-250-FR-FRA"
    } ,
    "listCategoryAge" :
        [{ "code" : "sen",
            "label" : "Senior Gars",
            "ageMax" : NumberInt(150),
            "ageMin" : NumberInt(18),
            "genre" : "Masculin",
            "order" : NumberInt(1),
            "listStaffMember" :
                [
                    { "personId": "54160977d5bd065a1bb1e565",
                        "role" :
                        {
                            "code": "coach",
                            "label": "Coach"
                        }
                    },
                    { "personId": "54160977d5bd065a1bb1e568",

                        "role" :
                        {
                            "code": "acoach",
                            "label": "Coach Adjoint"
                        }
                    }
                ]
        },
            { "code" : "senF",
                "label" : "Senior Féminin",
                "ageMax" : NumberInt(150),
                "ageMin" : NumberInt(18),
                "genre" : "Féminin",
                "order" : NumberInt(2),
                "listStaffMember" :
                    [
                        { "personId": null,
                            "role" :
                            {
                                "code": "coach",
                                "label": "Coach"
                            }
                        },
                        { "personId": null,

                            "role" :
                            {
                                "code": "acoach",
                                "label": "Coach Adjoint"
                            }
                        }
                    ]
            },
            { "code" : "u19",
                "label" : "U19",
                "ageMax" : NumberInt(18),
                "ageMin" : NumberInt(17),
                "genre" : "Masculin",
                "order" : NumberInt(4),
                "listStaffMember" :
                    [
                        { "personId": "54160977d5bd065a1bb1e566",
                            "role" :
                            {
                                "code": "coach",
                                "label": "Coach"
                            }
                        },
                        { "personId": null,

                            "role" :
                            {
                                "code": "acoach",
                                "label": "Coach Adjoint"
                            }
                        }
                    ]
            },
            { "code" : "u17",
                "label" : "U17",
                "ageMax" : NumberInt(16),
                "ageMin" : NumberInt(15),
                "genre" : "Masculin",
                "order" : NumberInt(5),
                "listStaffMember" :
                    [
                        { "personId": null,
                            "role" :
                            {
                                "code": "coach",
                                "label": "Coach"
                            }
                        },
                        { "personId": null,

                            "role" :
                            {
                                "code": "acoach",
                                "label": "Coach Adjoint"
                            }
                        }
                    ]
            },
            { "code" : "u15",
                "label" : "U15",
                "ageMax" : NumberInt(14),
                "ageMin" : NumberInt(13),
                "genre" : "Masculin",
                "order" : NumberInt(6),
                "listStaffMember" :
                    [
                        { "personId": null,
                            "role" :
                            {
                                "code": "coach",
                                "label": "Coach"
                            }
                        },
                        { "personId": null,
                            "role" :
                            {
                                "code": "acoach",
                                "label": "Coach Adjoint"
                            }
                        }
                    ]
            },
            { "code" : "u13",
                "label" : "U13",
                "ageMax" : NumberInt(11),
                "ageMin" : NumberInt(10),
                "genre" : "Masculin",
                "order" : NumberInt(11),
                "listStaffMember" :
                    [
                        { "personId": null,

                            "role" :

                            {
                                "code": "coach",
                                "label": "Coach"
                            }
                        }
                    ]
            },
            { "code" : "u11",
                "label" : "U11",
                "ageMax" : NumberInt(11),
                "ageMin" : NumberInt(10),
                "genre" : "Masculin",
                "order" : NumberInt(12),
                "listStaffMember" :
                    [
                        { "personId": null,
                            "role" :
                            {
                                "code": "coach",
                                "label": "Coach"
                            }
                        }
                    ]
            }
        ],
    "listTeams" :
        [
            { "code" : "sen-team-1",
                "categoryAge" : { "code" : "sen", "label" : "Senior Gars" },
                "label" : "Equipe 1",
                "levelGame" : {
                    "code" : "nv1",
                    "label" : "Elite",
                    "order" : NumberInt(1)
                },
                "listStaffMember" :
                    [
                        { "personId": "54160977d5bd065a1bb1e565",
                            "role" :
                            {
                                "code": "coach",
                                "label": "Coach"
                            }
                        }
                    ]
            },
            { "code" : "sen-team-2",
                "categoryAge" : { "code" : "sen", "label" : "Senior Gars" },
                "label" : "Equipe 2",
                "levelGame" : {
                    "code" : "nv2",
                    "label" : "National",
                    "order" : NumberInt(2)
                },
                "listStaffMember" :
                    [
                        { "personId": "54160977d5bd065a1bb1e568",
                        "role" :
                        {
                            "code": "coach",
                            "label": "Coach"
                        }
                    }
                    ]
            },
            { "code" : "senF-team-1",
                "categoryAge" : { "code" : "senF", "label" : "Senior Filles" },
                "label" : "Equipe 1",
                "levelGame" : {
                    "code" : "nv4",
                    "label" : "Départemental",
                    "order" : NumberInt(4)
                },
                "listStaffMember" :
                    [
                        { "personId": null,
                            "role" :
                            {
                                "code": "coach",
                                "label": "Coach"
                            }
                        },
                        { "personId": null,
                            "role" :
                            {
                                "code": "acoach",
                                "label": "Coach Adjoint"
                            }
                        }
                    ]
            },
            { "code" : "u19-team-1",
                "categoryAge" : { "code" : "u19", "label" : "Moins de 19 Gars" },
                "label" : "Equipe 1",
                "levelGame" : {
                    "code" : "nv2",
                    "label" : "National",
                    "order" : NumberInt(2)
                },
                "listStaffMember" :
                    [
                        { "personId": null,
                            "role" :
                            {
                                "code": "coach",
                                "label": "Coach"
                            }
                        }
                    ]
            },
            { "code" : "u17-team-1",
                "categoryAge" : { "code" : "u17", "label" : "Moins de 17 Gars" },
                "label" : "Equipe 1",
                "levelGame" : {
                    "code" : "nv3",
                    "label" : "Régional",
                    "order" : NumberInt(3)
                },
                "listStaffMember" :
                    [
                        { "personId": null,
                            "role" :
                            {
                                "code": "coach",
                                "label": "Coach"
                            }
                        }
                    ]
            },
            { "code" : "u15-team-1",
                "categoryAge" : { "code" : "u15", "label" : "Moins de 15 Gars" },
                "label" : "Equipe 1",
                "levelGame" : {
                    "code" : "nv4",
                    "label" : "Départemental",
                    "order" : NumberInt(4)
                },
                "listStaffMember" :
                    [
                        { "personId": null,
                            "role" :
                            {
                                "code": "coach",
                                "label": "Coach"
                            }
                        }
                    ]
            },
            { "code" : "u13-team-1",
                "categoryAge" : { "code" : "u13", "label" : "Moins de 13 Gars" },
                "label" : "Equipe 1",
                "levelGame" : {
                    "code" : "nv4",
                    "label" : "Départemental",
                    "order" : NumberInt(4)
                },
                "listStaffMember" :
                    [
                        { "personId": null,
                            "role" :
                            {
                                "code": "coach",
                                "label": "Coach"
                            }
                        }
                    ]
            },
            { "code" : "u11-team-1",
                "categoryAge" : { "code" : "u11", "label" : "Moins de 11 Gars" },
                "label" : "Equipe 1",
                "levelGame" : {
                    "code" : "nv4",
                    "label" : "Départemental",
                    "order" : NumberInt(4)
                },
                "listStaffMember" :
                    [
                        { "personId": null,
                            "role" :
                            {
                                "code": "coach",
                                "label": "Coach"
                            }
                        }
                    ]
            }
        ]
});