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
 * Vidage de la collection Person
 */
db.Person.remove({"_id" : {"$in" : ["a0ef9c2d-6864-4a20-84ba-b66a666d2bf4", "54160977d5bd065a1bb1e563", "54160977d5bd065a1bb1e564",
                           "54160977d5bd065a1bb1e565", "54160977d5bd065a1bb1e566","54160977d5bd065a1bb1e568", "5509ef1fdb8f8b6e2f51f4ce",
                           "5509ef1fdb8f8b6e2f51f4cf"]}});

/*
 * Alimentation collection Person : USERS
 */

// ladmin : login = admin / mdp = adminqaobee29
db.Person.insert({
    "_id" : "a0ef9c2d-6864-4a20-84ba-b66a666d2bf4",
    "name" : "Qaobee",
    "firstname" : "Administrateur",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(1398290400000),
    "birthcity" : "Brest",
    "birthcountry" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "alpha2" : "FR" , "alpha3" : "FRA" , "label" : "settings.Country.FR.name"},
    "nationality" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "alpha2" : "FR" , "alpha3" : "FRA" , "label" : "settings.Country.FR.name"},
    "job" : "Ecrivain",
    "address" : {
        "formatedAddress" : "",
        "place" : "20, rue Cuirassé Bretagne",
        "zipcode" : "29200",
        "city" : "BREST",
        "country" : "France"
    },
    "contact" : {
        "home" : null,
        "office" : "0298038835",
        "cellphone" : null,
        "email" : "contact@qaobee.com"
    },
    "account" : {
        "_id" : null,
        "activationCode" : "1649f6f5dd3246beb5fd58142b7e3784",
        "activationPasswd" : null,
        "active" : true,
        "expirationDate" : 0,
        "firstConnexion" : true,
        "login" : "admin",
        "passwd" : null,
        "password" : "meqEKvIdHMOMIpdD0V6YSEHhouA=",
        "salt" : "nQ8pt5v+O5E=",
        
        "token" : "52f2a199-56e1-4454-bf03-7d9f7f475155",
        "tokenRenewDate" : NumberLong(1412799534801),
        "habilitations" : [ {
            "_id" : "70cd4981-d2b5-4dbc-95cf-87704e18449e",
            "key" : "admin_qaobee",
            "description" : "<p>Administrateur <strong>QaoBee</strong></p>"
        } ],
        "notifications" : null,
        "listPlan" : [ {
            "paymentId" : "9578c234-00d8-4cad-b33e-9c2425f432c9",
            "levelPlan" : "DISCOVERY",
            "amountPaid" : 5,
            "paidDate" : NumberLong(1412799496416),
            "startPeriodDate" : 0,
            "endPeriodDate" : 0,
            "status" : "paid",
            "periodicity" : null,
            "structure" : null,
            "activity" : null
        } ]
    }
});

//DEBUT USER CLUB Dunkerque Handball
db.Person.insert({
    "_id" : "54160977d5bd065a1bb1e563",
    "name" : "Casal",
    "firstname" : "Patrick",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(39740400000),
    "birthcity" : "Saint-Joseph",
    "birthcountry" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "alpha2" : "FR" , "alpha3" : "FRA" , "label" : "settings.Country.FR.name"},
    "nationality" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "alpha2" : "FR" , "alpha3" : "FRA" , "label" : "settings.Country.FR.name"},
    "job" : "Coach",
    "address" : {
        "formatedAddress" : "",
        "place" : " Stades de Flandres, Avenue de Rosendaël",
        "zipcode" : "59240",
        "city" : " DUNKERQUE",
        "country" : "France"
    },
    "contact" : {
        "home" : null,
        "office" : "03 28 66 91 52",
        "cellphone" : "06 30 35 38 19",
        "email" : "Casal@Casal.com"
    },
    "account" : {
        "activationCode" : "6d23f95b647d47d9ab35f92bc12819dc",
        "activationPasswd" : null,
        "active" : true,
        "expirationDate" : NumberLong(0),
        "firstConnexion" : false,
        "login" : "usdk1",
        "passwd" : null,
        "password" : "xHvS+ICtNg/1rrrj49WT+A/y9MA=",
        "salt" : "CPAaJ8ydx6I=",
        "timestamp" : NumberLong(1406738914448),
        "token" : null,
        "tokenRenewDate" : NumberLong(0),
        "habilitations" : null,
        "notifications" : null,
        "listPlan" : [ {
            "paymentId" : "paymentId001",
            "levelPlan" : "PREMIUM",
            "amountPaid" : NumberLong(6),
            "paidDate" : NumberLong(1408312800000),
            "startPeriodDate" : NumberLong(1408312800000),
            "endPeriodDate" : NumberLong(1435615200000),
            "status" : "paid",
            "periodicity" : "monthly",
            "structure" : {
                "_id" : "541168295971d35c1f2d1b5e",
                "label" : "Dunkerque Handball",
                "acronym" : "USDK",
                "codeActivity" : "ACT-HAND",
                "addressStr" : {
                    "place" : " Stades de Flandres, Avenue de Rosendaël",
                    "zipcode" : "59240",
                    "city" : " DUNKERQUE",
                    "country" : "France"
                },
                "contactStr" : {
                    "home" : null,
                    "office" : "03 28 66 91 52",
                    "cellphone" : "06 30 35 38 19",
                    "fax" : "",
                    "email" : "melanie.lefebvre@usdk.fr"
                },
                //Mettre l'id user coach pour l'instant
                "correspondent" : null,
                "country" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "alpha2" : "FR" , "alpha3" : "FRA" , "label" : "settings.Country.FR.name"},
                "avatar" : null
            },
            "activity" : {
                "_id" : "ACT-HAND",
                "code" : "ACT-HAND",
                "label" : "admin.settings.activity.handball.label",
                "activated" : true,
                "activityType" : "TEAM_SPORT"
            }
        } ]
    }
});

db.Person.insert({
    "_id" : "54160977d5bd065a1bb1e564",
    "name" : "Calbry",
    "firstname" : "Arnaud",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(140655600000),
    "birthcity" : "Dieppe",
    "birthcountry" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "alpha2" : "FR" , "alpha3" : "FRA" , "label" : "settings.Country.FR.name"},
    "nationality" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "alpha2" : "FR" , "alpha3" : "FRA" , "label" : "settings.Country.FR.name"},
    "job" : "Coach-Adjoint",
    "address" : {
        "formatedAddress" : "",
        "place" : " Stades de Flandres, Avenue de Rosendaël",
        "zipcode" : "59240",
        "city" : " DUNKERQUE",
        "country" : "France"
    },
    "contact" : {
        "home" : null,
        "office" : "03 28 66 91 52",
        "cellphone" : "06 30 35 38 19",
        "email" : "Calbry@Calbry.com"
    },
    "account" : {
        "activationCode" : "6d23f95b647d47d9ab35f92bc12819dc",
        "activationPasswd" : null,
        "active" : true,
        "expirationDate" : NumberLong(0),
        "firstConnexion" : false,
        "login" : "usdk2",
        "passwd" : null,
        "password" : "xHvS+ICtNg/1rrrj49WT+A/y9MA=",
        "salt" : "CPAaJ8ydx6I=",
        "timestamp" : NumberLong(1406738914448),
        "token" : null,
        "tokenRenewDate" : NumberLong(0),
        "habilitations" : null,
        "notifications" : null,
        "listPlan" : [ {
            "paymentId" : "paymentId002",
            "levelPlan" : "DISCOVERY",
            "amountPaid" : NumberLong(6),
            "paidDate" : NumberLong(1408312800000),
            "startPeriodDate" : NumberLong(1408312800000),
            "endPeriodDate" : NumberLong(1435615200000),
            "status" : "paid",
            "periodicity" : "monthly",
            "structure" : {
                "_id" : "541168295971d35c1f2d1b5e",
                "label" : "Dunkerque Handball",
                "acronym" : "USDK",
                "codeActivity" : "ACT-HAND",
                "addressStr" : {
                    "place" : " Stades de Flandres, Avenue de Rosendaël",
                    "zipcode" : "59240",
                    "city" : " DUNKERQUE",
                    "country" : "France"
                },
                "contactStr" : {
                    "home" : null,
                    "office" : "03 28 66 91 52",
                    "cellphone" : "06 30 35 38 19",
                    "fax" : "",
                    "email" : "melanie.lefebvre@usdk.fr"
                },
                //Mettre l'id user coach pour l'instant
                "correspondent" : null,
                "country" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "alpha2" : "FR" , "alpha3" : "FRA" , "label" : "settings.Country.FR.name"},
                "avatar" : null
            },
            "activity" : {
                "_id" : "ACT-HAND",
                "code" : "ACT-HAND",
                "label" : "admin.settings.activity.handball.label",
                "activated" : true,
                "activityType" : "TEAM_SPORT"
            }
        } ]
    }
});

//FIN USER CLUB Dunkerque Handball


// DEBUT USER CLUB CESSON RENNES METROPOLE HB
db.Person.insert({
    "_id" : "5509ef1fdb8f8b6e2f51f4ce",
    "name" : "Sylla",
    "firstname" : "Yerime",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(-21434400000),
    "birthcity" : "Villemomble",
    "birthcountry" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "alpha2" : "FR" , "alpha3" : "FRA" , "label" : "settings.Country.FR.name"},
    "nationality" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "alpha2" : "FR" , "alpha3" : "FRA" , "label" : "settings.Country.FR.name"},
    "job" : "Manager General",
    "address" : {
        "formatedAddress" : "",
        "place" : "3, allée de Champagné",
        "zipcode" : "35510",
        "city" : "CESSON-SEVIGNE",
        "country" : "France"
    },
    "contact" : {
        "home" : null,
        "office" : "02 23 45 07 19",
        "cellphone" : "06 69 97 68 39",
        "email" : "yerimesylla@sylla.com"
    },
    "account" : {
        "activationCode" : "6d23f95b647d47d9ab35f92bc12819dc",
        "activationPasswd" : null,
        "active" : true,
        "expirationDate" : NumberLong(0),
        "firstConnexion" : false,
        "login" : "crmhb1",
        "passwd" : null,
        "password" : "xHvS+ICtNg/1rrrj49WT+A/y9MA=",
        "salt" : "CPAaJ8ydx6I=",
        "timestamp" : NumberLong(1406738914448),
        "token" : null,
        "tokenRenewDate" : NumberLong(0),
        "habilitations" : null,
        "notifications" : null,
        "listPlan" : [ {
            "paymentId" : "paymentId001",
            "levelPlan" : "PREMIUM",
            "amountPaid" : NumberLong(6),
            "paidDate" : NumberLong(1408312800000),
            "startPeriodDate" : NumberLong(1408312800000),
            "endPeriodDate" : NumberLong(1435615200000),
            "status" : "paid",
            "periodicity" : "monthly",
            "structure" : {
                "_id" : "541168295971d35c1f2d1b5f",
                "label" : "CESSON RENNES METROPOLE HB",
                "acronym" : "CRMBH",
                "codeActivity" : "ACT-HAND",
                "addressStr" : {
                    "place" : "3, allée de Champagné",
                    "zipcode" : "35510",
                    "city" : "CESSON-SEVIGNE",
                    "country" : "France"
                },
                "contactStr" : {
                    "home" : null,
                    "office" : "02 23 45 07 19",
                    "cellphone" : "06 69 97 68 39",
                    "fax" : "",
                    "email" : "sandrine@cesson-handball.com"
                },
                //Mettre l'id user coach pour l'instant
                "correspondent" : null,
                "country" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "alpha2" : "FR" , "alpha3" : "FRA" , "label" : "settings.Country.FR.name"},
                "avatar" : null
            },
            "activity" : {
                "_id" : "ACT-HAND",
                "code" : "ACT-HAND",
                "label" : "admin.settings.activity.handball.label",
                "activated" : true,
                "activityType" : "TEAM_SPORT"
            }
        } ]
    }
});

db.Person.insert({
    "_id" : "5509ef1fdb8f8b6e2f51f4cf",
    "name" : "Oskarsson",
    "firstname" : "Ragnar",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(272325600000),
    "birthcity" : "Reykjavik",
    "birthcountry" : {"_id" : "CNTR-352-IS-ISL" , "codeOSCE" : NumberInt(352) , "alpha2" : "IS" , "alpha3" : "ISL" , "label" : "settings.Country.IS.name"},
    "nationality" : {"_id" : "CNTR-352-IS-ISL" , "codeOSCE" : NumberInt(352) , "alpha2" : "IS" , "alpha3" : "ISL" , "label" : "settings.Country.IS.name"},
    "job" : "Coach-Adjoint",
    "address" : {
        "formatedAddress" : "",
        "place" : "3, allée de Champagné",
        "zipcode" : "35510",
        "city" : "CESSON-SEVIGNE",
        "country" : "France"
    },
    "contact" : {
        "home" : null,
        "office" : "02 23 45 07 19",
        "cellphone" : "06 69 97 68 39",
        "email" : "Oskarsson@Oskarsson.com"
    },
    "account" : {
        "activationCode" : "6d23f95b647d47d9ab35f92bc12819dc",
        "activationPasswd" : null,
        "active" : true,
        "expirationDate" : NumberLong(0),
        "firstConnexion" : false,
        "login" : "crmhb2",
        "passwd" : null,
        "password" : "xHvS+ICtNg/1rrrj49WT+A/y9MA=",
        "salt" : "CPAaJ8ydx6I=",
        "timestamp" : NumberLong(1406738914448),
        "token" : null,
        "tokenRenewDate" : NumberLong(0),
        "habilitations" : null,
        "notifications" : null,
        "listPlan" : [ {
            "paymentId" : "paymentId002",
            "levelPlan" : "DISCOVERY",
            "amountPaid" : NumberLong(6),
            "paidDate" : NumberLong(1408312800000),
            "startPeriodDate" : NumberLong(1408312800000),
            "endPeriodDate" : NumberLong(1435615200000),
            "status" : "paid",
            "periodicity" : "monthly",
            "structure" : {
                "_id" : "541168295971d35c1f2d1b5f",
                "label" : "CESSON RENNES METROPOLE HB",
                "acronym" : "CRMBH",
                "codeActivity" : "ACT-HAND",
                "addressStr" : {
                    "place" : "3, allée de Champagné",
                    "zipcode" : "35510",
                    "city" : "CESSON-SEVIGNE",
                    "country" : "France"
                },
                "contactStr" : {
                    "home" : null,
                    "office" : "02 23 45 07 19",
                    "cellphone" : "06 69 97 68 39",
                    "fax" : "",
                    "email" : "sandrine@cesson-handball.com"
                },
                //Mettre l'id user coach pour l'instant
                "correspondent" : null,
                "country" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "alpha2" : "FR" , "alpha3" : "FRA" , "label" : "settings.Country.FR.name"},
                "avatar" : null
            },
            "activity" : {
                "_id" : "ACT-HAND",
                "code" : "ACT-HAND",
                "label" : "admin.settings.activity.handball.label",
                "activated" : true,
                "activityType" : "TEAM_SPORT"
            }
        } ]
    }
});

//FIN USER CESSON RENNES METROPOLE HB

//DEBUT USER Club Demo football
db.Person.insert({
    "_id" : "54160977d5bd065a1bb1e565",
    "name" : "Hidalgo",
    "firstname" : "Michel",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(-1160701200000),
    "birthcity" : "Marseille",
    "birthcountry" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "alpha2" : "FR" , "alpha3" : "FRA" , "label" : "settings.Country.FR.name"},
    "nationality" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "alpha2" : "FR" , "alpha3" : "FRA" , "label" : "settings.Country.FR.name"},
    "job" : "Ecrivain",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "31000",
        "city" : "Toulouse",
        "country" : "France"
    },
    "contact" : {
        "home" : "04 04 00 00 01",
        "office" : "",
        "cellphone" : "07 06 00 00 01",
        "email" : "Hidalgo@toto.com"
    },
    "account" : {
        "activationCode" : "6d23f95b647d47d9ab35f92bc12819dc",
        "activationPasswd" : null,
        "active" : true,
        "expirationDate" : NumberLong(0),
        "firstConnexion" : false,
        "login" : "ccaft",
        "passwd" : null,
        "password" : "xHvS+ICtNg/1rrrj49WT+A/y9MA=",
        "salt" : "CPAaJ8ydx6I=",
        "timestamp" : NumberLong(1406738914448),
        "token" : null,
        "tokenRenewDate" : NumberLong(0),
        "habilitations" : null,
        "notifications" : null,
        "listPlan" : [ {
            "paymentId" : "paymentId003",
            "levelPlan" : "TEAM_PLUS",
            "amountPaid" : NumberLong(6),
            "paidDate" : NumberLong(1408312800000),
            "startPeriodDate" : NumberLong(1408312800000),
            "endPeriodDate" : NumberLong(1435615200000),
            "status" : "paid",
            "periodicity" : "monthly",
            "structure" : {
                "_id" : "541168295971d35c1f2d1b60",
                "label" : "Club Demo football",
                "acronym" : "CAFB",
                "codeActivity" : "ACT-FOOT",
                "addressStr" : {
                    "place" : "1 Rue Jean Jaurès",
                    "zipcode" : "Brest",
                    "city" : "29200",
                    "country" : "France"
                },
                "contactStr" : {
                    "home" : "02 98 00 00 00",
                    "office" : "02 98 01 01 01",
                    "cellphone" : "06 06 06 06 06",
                    "email" : "0529000@football-france.eu"
                },
                "correspondent" : null,
                "country" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "alpha2" : "FR" , "alpha3" : "FRA" , "label" : "settings.Country.FR.name"},
                "avatar" : null
            },
            "activity" : {
                "_id" : "ACT-FOOT",
                "code" : "ACT-FOOT",
                "label" : "admin.settings.activity.football.label",
                "activated" : true,
                "activityType" : "TEAM_SPORT"
            }
        } ]
    }
});

db.Person.insert({
    "_id" : "54160977d5bd065a1bb1e568",
    "name" : "Michel",
    "firstname" : "Henry",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(-699930000000),
    "birthcity" : "Aix-en-Provence",
    "birthcountry" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "alpha2" : "FR" , "alpha3" : "FRA" , "label" : "settings.Country.FR.name"},
    "nationality" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "alpha2" : "FR" , "alpha3" : "FRA" , "label" : "settings.Country.FR.name"},
    "job" : "Ecrivain",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "13100",
        "city" : "Aix-en-Provence",
        "country" : "France"
    },
    "contact" : {
        "home" : "04 04 00 00 01",
        "office" : "",
        "cellphone" : "07 06 00 00 01",
        "email" : "Michel@toto.com"
    },
    "account" : {
        "activationCode" : "6d23f95b647d47d9ab35f92bc12819dc",
        "activationPasswd" : null,
        "active" : true,
        "expirationDate" : NumberLong(0),
        "firstConnexion" : false,
        "login" : "ccahm",
        "passwd" : null,
        "password" : "xHvS+ICtNg/1rrrj49WT+A/y9MA=",
        "salt" : "CPAaJ8ydx6I=",
        "timestamp" : NumberLong(1406738914448),
        "token" : null,
        "tokenRenewDate" : NumberLong(0),
        "habilitations" : null,
        "notifications" : null,
        "listPlan" : [ {
            "paymentId" : "paymentId003",
            "levelPlan" : "TEAM_PLUS",
            "amountPaid" : NumberLong(6),
            "paidDate" : NumberLong(1408312800000),
            "startPeriodDate" : NumberLong(1408312800000),
            "endPeriodDate" : NumberLong(1435615200000),
            "status" : "paid",
            "periodicity" : "monthly",
            "structure" : {
                "_id" : "541168295971d35c1f2d1b60",
                "label" : "Club Demo football",
                "acronym" : "CAFB",
                "codeActivity" : "ACT-FOOT",
                "addressStr" : {
                    "place" : "1 Rue Jean Jaurès",
                    "zipcode" : "Brest",
                    "city" : "29200",
                    "country" : "France"
                },
                "contactStr" : {
                    "home" : "02 98 00 00 00",
                    "office" : "02 98 01 01 01",
                    "cellphone" : "06 06 06 06 06",
                    "email" : "0529000@football-france.eu"
                },
                "correspondent" : null,
                "country" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "alpha2" : "FR" , "alpha3" : "FRA" , "label" : "settings.Country.FR.name"},
                "avatar" : null
            },
            "activity" : {
                "_id" : "ACT-FOOT",
                "code" : "ACT-FOOT",
                "label" : "admin.settings.activity.football.label",
                "activated" : true,
                "activityType" : "TEAM_SPORT"
            }
        } ]
    }
});

db.Person.insert({
    "_id" : "54160977d5bd065a1bb1e566",
    "name" : "Jacquet",
    "firstname" : "Aimé",
    "avatar" : null,
    "gender" : "gender.male",
    "birthdate" : NumberLong(-886640400000),
    "birthcity" : "Bordeaux",
    "birthcountry" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "alpha2" : "FR" , "alpha3" : "FRA" , "label" : "settings.Country.FR.name"},
    "nationality" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "alpha2" : "FR" , "alpha3" : "FRA" , "label" : "settings.Country.FR.name"},
    "job" : "Ecrivain",
    "address" : {
        "formatedAddress" : "",
        "place" : "1 Rue du Stade",
        "zipcode" : "33000",
        "city" : "Bordeaux",
        "country" : "France"
    },
    "contact" : {
        "home" : "05 04 00 00 01",
        "office" : "",
        "cellphone" : "07 06 00 00 01",
        "email" : "jacquet@toto.com"
    },
    "account" : {
        "activationCode" : "6d23f95b647d47d9ab35f92bc12819dc",
        "activationPasswd" : null,
        "active" : true,
        "expirationDate" : NumberLong(0),
        "firstConnexion" : false,
        "login" : "ccaaj",
        "passwd" : null,
        "password" : "xHvS+ICtNg/1rrrj49WT+A/y9MA=",
        "salt" : "CPAaJ8ydx6I=",
        "timestamp" : NumberLong(1406738914448),
        "token" : null,
        "tokenRenewDate" : NumberLong(0),
        "habilitations" : null,
        "notifications" : null,
        "listPlan" : [ {
            "paymentId" : "paymentId004",
            "levelPlan" : "PREMIUM",
            "amountPaid" : NumberLong(6),
            "paidDate" : NumberLong(1408312800000),
            "startPeriodDate" : NumberLong(1408312800000),
            "endPeriodDate" : NumberLong(1435615200000),
            "status" : "paid",
            "periodicity" : "monthly",
            "structure" : {
                "_id" : "541168295971d35c1f2d1b60",
                "label" : "Club Demo football",
                "acronym" : "CAFB",
                "codeActivity" : "ACT-FOOT",
                "addressStr" : {
                    "place" : "1 Rue Jean Jaurès",
                    "zipcode" : "Brest",
                    "city" : "29200",
                    "country" : "France"
                },
                "contactStr" : {
                    "home" : "02 98 00 00 00",
                    "office" : "02 98 01 01 01",
                    "cellphone" : "06 06 06 06 06",
                    "email" : "0529000@football-france.eu"
                },
                "correspondent" : null,
                "country" : {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "alpha2" : "FR" , "alpha3" : "FRA" , "label" : "settings.Country.FR.name"},
                "avatar" : null
            },
            "activity" : {
                "_id" : "ACT-FOOT",
                "code" : "ACT-FOOT",
                "label" : "admin.settings.activity.football.label",
                "activated" : true,
                "activityType" : "TEAM_SPORT"
            }
        } ]
    }
});

//FIN USER Club Demo football