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
 * Vidage de la collection Person
 */
db.User.remove({
    "_id": {
        "$in": ["a0ef9c2d-6864-4a20-84ba-b66a666d2bf4", "54160977d5bd065a1bb1e563", "54160977d5bd065a1bb1e564",
            "54160977d5bd065a1bb1e565", "54160977d5bd065a1bb1e566", "54160977d5bd065a1bb1e568", "5509ef1fdb8f8b6e2f51f4ce",
            "5509ef1fdb8f8b6e2f51f4cf"]
    }
});

/*
 * Alimentation collection Person : USERS
 */

// ladmin : login = admin / mdp = adminqaobee29
db.User.insert({
    "_id": "a0ef9c2d-6864-4a20-84ba-b66a666d2bf4",
    "name": "Qaobee",
    "firstname": "Administrateur",
    "avatar": null,
    "gender": "gender.male",
    "birthdate": NumberLong(1398290400000),
    "nationality": {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},

    "address": {
        "formatedAddress": "",
        "place": "20, rue Cuirassé Bretagne",
        "zipcode": "29200",
        "city": "BREST",
        "country": "France"
    },
    "contact": {
        "home": null,
        "office": "0298038835",
        "cellphone": null,
        "email": "contact@qaobee.com"
    },
    "account": {
        "_id": null,
        "activationCode": "1649f6f5dd3246beb5fd58142b7e3784",
        "activationPasswd": null,
        "active": true,
        "expirationDate": 0,
        "firstConnexion": true,
        "login": "admin",
        "passwd": null,
        "password": "meqEKvIdHMOMIpdD0V6YSEHhouA=",
        "salt": "nQ8pt5v+O5E=",

        "token": "52f2a199-56e1-4454-bf03-7d9f7f475155",
        "tokenRenewDate": NumberLong(1412799534801),
        "habilitations": [{
            "_id": "70cd4981-d2b5-4dbc-95cf-87704e18449e",
            "key": "admin_qaobee",
            "description": "<p>Administrateur <strong>QaoBee</strong></p>"
        }],

        "listPlan": [{
            "paymentId": "9578c234-00d8-4cad-b33e-9c2425f432c9",
            "levelPlan": "PREMIUM",
            "amountPaid": 5,
            "paidDate": NumberLong(1412799496416),
            "startPeriodDate": 0,
            "endPeriodDate": 0,
            "status": "paid",
            "periodicity": null,
            "structure": null,
            "activity": null
        }]
    }
});

//DEBUT USER CLUB Dunkerque Handball
db.User.insert({
    "_id": "54160977d5bd065a1bb1e563",
    "name": "Casal",
    "firstname": "Patrick",
    "avatar": null,
    "gender": "gender.male",
    "birthdate": NumberLong(39740400000),
    "nationality": {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},

    "address": {
        "formatedAddress": "",
        "place": " Stades de Flandres, Avenue de Rosendaël",
        "zipcode": "59240",
        "city": " DUNKERQUE",
        "country": "France"
    },
    "contact": {
        "home": null,
        "office": "03 28 66 91 52",
        "cellphone": "06 30 35 38 19",
        "email": "Casal@Casal.com"
    },
    "account": {
        "activationCode": "6d23f95b647d47d9ab35f92bc12819dc",
        "activationPasswd": null,
        "active": true,
        "expirationDate": NumberLong(0),
        "firstConnexion": false,
        "login": "usdk1",
        "passwd": null,
        "password": "xHvS+ICtNg/1rrrj49WT+A/y9MA=",
        "salt": "CPAaJ8ydx6I=",
        "timestamp": NumberLong(1406738914448),
        "token": null,
        "tokenRenewDate": NumberLong(0),
        "habilitations": null,

        "listPlan": [{
            "paymentId": "paymentId001",
            "levelPlan": "PREMIUM",
            "amountPaid": NumberLong(6),
            "paidDate": NumberLong(1408312800000),
            "startPeriodDate": NumberLong(1408312800000),
            "endPeriodDate": NumberLong(1435615200000),
            "status": "paid",
            "periodicity": "monthly",
            "activity": {
                "_id": "ACT-HAND",
                "code": "ACT-HAND",
                "label": "admin.settings.activity.handball.label",
                "enable": true,
                "activityType": "TEAM_SPORT"
            }
        }]
    }
});

db.User.insert({
    "_id": "54160977d5bd065a1bb1e564",
    "name": "Calbry",
    "firstname": "Arnaud",
    "avatar": null,
    "gender": "gender.male",
    "birthdate": NumberLong(140655600000),
    "nationality": {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},

    "address": {
        "formatedAddress": "",
        "place": " Stades de Flandres, Avenue de Rosendaël",
        "zipcode": "59240",
        "city": " DUNKERQUE",
        "country": "France"
    },
    "contact": {
        "home": null,
        "office": "03 28 66 91 52",
        "cellphone": "06 30 35 38 19",
        "email": "Calbry@Calbry.com"
    },
    "account": {
        "activationCode": "6d23f95b647d47d9ab35f92bc12819dc",
        "activationPasswd": null,
        "active": false,
        "expirationDate": NumberLong(0),
        "firstConnexion": false,
        "login": "usdk2",
        "passwd": null,
        "password": "xHvS+ICtNg/1rrrj49WT+A/y9MA=",
        "salt": "CPAaJ8ydx6I=",
        "timestamp": NumberLong(1406738914448),
        "token": null,
        "tokenRenewDate": NumberLong(0),
        "habilitations": null,

        "listPlan": [{
            "paymentId": "paymentId002",
            "levelPlan": "PREMIUM",
            "amountPaid": NumberLong(6),
            "paidDate": NumberLong(1408312800000),
            "startPeriodDate": NumberLong(1408312800000),
            "endPeriodDate": NumberLong(1435615200000),
            "status": "paid",
            "periodicity": "monthly",
            "activity": {
                "_id": "ACT-HAND",
                "code": "ACT-HAND",
                "label": "admin.settings.activity.handball.label",
                "enable": true,
                "activityType": "TEAM_SPORT"
            }
        }]
    }
});

//FIN USER CLUB Dunkerque Handball


// DEBUT USER CLUB CESSON RENNES METROPOLE HB
db.User.insert({
    "_id": "5509ef1fdb8f8b6e2f51f4ce",
    "name": "Sylla",
    "firstname": "Yerime",
    "avatar": null,
    "gender": "gender.male",
    "birthdate": NumberLong(-21434400000),
    "nationality": {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},
    "address": {
        "formatedAddress": "",
        "place": "3, allée de Champagné",
        "zipcode": "35510",
        "city": "CESSON-SEVIGNE",
        "country": "France"
    },
    "contact": {
        "home": null,
        "office": "02 23 45 07 19",
        "cellphone": "06 69 97 68 39",
        "email": "yerimesylla@sylla.com"
    },
    "account": {
        "activationCode": "6d23f95b647d47d9ab35f92bc12819dc",
        "activationPasswd": null,
        "active": true,
        "expirationDate": NumberLong(0),
        "firstConnexion": false,
        "login": "crmhb1",
        "passwd": null,
        "password": "xHvS+ICtNg/1rrrj49WT+A/y9MA=",
        "salt": "CPAaJ8ydx6I=",
        "timestamp": NumberLong(1406738914448),
        "token": null,
        "tokenRenewDate": NumberLong(0),
        "habilitations": null,

        "listPlan": [{
            "paymentId": "paymentId001",
            "levelPlan": "PREMIUM",
            "amountPaid": NumberLong(6),
            "paidDate": NumberLong(1408312800000),
            "startPeriodDate": NumberLong(1408312800000),
            "endPeriodDate": NumberLong(1435615200000),
            "status": "paid",
            "periodicity": "monthly",
            "activity": {
                "_id": "ACT-HAND",
                "code": "ACT-HAND",
                "label": "admin.settings.activity.handball.label",
                "enable": true,
                "activityType": "TEAM_SPORT"
            }
        }]
    }
});

db.User.insert({
    "_id": "5509ef1fdb8f8b6e2f51f4cf",
    "name": "Oskarsson",
    "firstname": "Ragnar",
    "avatar": null,
    "gender": "gender.male",
    "birthdate": NumberLong(272325600000),
    "nationality": {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},

    "address": {
        "formatedAddress": "",
        "place": "3, allée de Champagné",
        "zipcode": "35510",
        "city": "CESSON-SEVIGNE",
        "country": "France"
    },
    "contact": {
        "home": null,
        "office": "02 23 45 07 19",
        "cellphone": "06 69 97 68 39",
        "email": "Oskarsson@Oskarsson.com"
    },
    "account": {
        "activationCode": "6d23f95b647d47d9ab35f92bc12819dc",
        "activationPasswd": null,
        "active": true,
        "expirationDate": NumberLong(0),
        "firstConnexion": false,
        "login": "crmhb2",
        "passwd": null,
        "password": "xHvS+ICtNg/1rrrj49WT+A/y9MA=",
        "salt": "CPAaJ8ydx6I=",
        "timestamp": NumberLong(1406738914448),
        "token": null,
        "tokenRenewDate": NumberLong(0),
        "habilitations": null,

        "listPlan": [{
            "paymentId": "paymentId002",
            "levelPlan": "PREMIUM",
            "amountPaid": NumberLong(6),
            "paidDate": NumberLong(1408312800000),
            "startPeriodDate": NumberLong(1408312800000),
            "endPeriodDate": NumberLong(1435615200000),
            "status": "paid",
            "periodicity": "monthly",
            "activity": {
                "_id": "ACT-HAND",
                "code": "ACT-HAND",
                "label": "admin.settings.activity.handball.label",
                "enable": true,
                "activityType": "TEAM_SPORT"
            }
        }]
    }
});

//FIN USER CESSON RENNES METROPOLE HB

//DEBUT USER Club Demo football
db.User.insert({
    "_id": "54160977d5bd065a1bb1e565",
    "name": "Hidalgo",
    "firstname": "Michel",
    "avatar": null,
    "gender": "gender.male",
    "birthdate": NumberLong(-1160701200000),
    "nationality": {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},

    "address": {
        "formatedAddress": "",
        "place": "1 Rue du Stade",
        "zipcode": "31000",
        "city": "Toulouse",
        "country": "France"
    },
    "contact": {
        "home": "04 04 00 00 01",
        "office": "",
        "cellphone": "07 06 00 00 01",
        "email": "Hidalgo@toto.com"
    },
    "account": {
        "activationCode": "6d23f95b647d47d9ab35f92bc12819dc",
        "activationPasswd": null,
        "active": true,
        "expirationDate": NumberLong(0),
        "firstConnexion": false,
        "login": "ccaft",
        "passwd": null,
        "password": "xHvS+ICtNg/1rrrj49WT+A/y9MA=",
        "salt": "CPAaJ8ydx6I=",
        "timestamp": NumberLong(1406738914448),
        "token": null,
        "tokenRenewDate": NumberLong(0),
        "habilitations": null,

        "listPlan": [{
            "paymentId": "paymentId003",
            "levelPlan": "PREMIUM",
            "amountPaid": NumberLong(6),
            "paidDate": NumberLong(1408312800000),
            "startPeriodDate": NumberLong(1408312800000),
            "endPeriodDate": NumberLong(1435615200000),
            "status": "paid",
            "periodicity": "monthly",
            "activity": {
                "_id": "ACT-FOOT",
                "code": "ACT-FOOT",
                "label": "admin.settings.activity.football.label",
                "enable": true,
                "activityType": "TEAM_SPORT"
            }
        }]
    }
});

db.User.insert({
    "_id": "54160977d5bd065a1bb1e568",
    "name": "Michel",
    "firstname": "Henry",
    "avatar": null,
    "gender": "gender.male",
    "birthdate": NumberLong(-699930000000),
    "nationality": {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},

    "address": {
        "formatedAddress": "",
        "place": "1 Rue du Stade",
        "zipcode": "13100",
        "city": "Aix-en-Provence",
        "country": "France"
    },
    "contact": {
        "home": "04 04 00 00 01",
        "office": "",
        "cellphone": "07 06 00 00 01",
        "email": "Michel@toto.com"
    },
    "account": {
        "activationCode": "6d23f95b647d47d9ab35f92bc12819dc",
        "activationPasswd": null,
        "active": true,
        "expirationDate": NumberLong(0),
        "firstConnexion": false,
        "login": "ccahm",
        "passwd": null,
        "password": "xHvS+ICtNg/1rrrj49WT+A/y9MA=",
        "salt": "CPAaJ8ydx6I=",
        "timestamp": NumberLong(1406738914448),
        "token": null,
        "tokenRenewDate": NumberLong(0),
        "habilitations": null,

        "listPlan": [{
            "paymentId": "paymentId003",
            "levelPlan": "PREMIUM",
            "amountPaid": NumberLong(6),
            "paidDate": NumberLong(1408312800000),
            "startPeriodDate": NumberLong(1408312800000),
            "endPeriodDate": NumberLong(1435615200000),
            "status": "paid",
            "periodicity": "monthly",
            "activity": {
                "_id": "ACT-FOOT",
                "code": "ACT-FOOT",
                "label": "admin.settings.activity.football.label",
                "enable": true,
                "activityType": "TEAM_SPORT"
            }
        }]
    }
});

db.User.insert({
    "_id": "54160977d5bd065a1bb1e566",
    "name": "Jacquet",
    "firstname": "Aimé",
    "avatar": null,
    "gender": "gender.male",
    "birthdate": NumberLong(-886640400000),
    "nationality": {"_id" : "CNTR-250-FR-FRA" , "codeOSCE" : NumberInt(250) , "label" : "France" , "local" : "fr"},

    "address": {
        "formatedAddress": "",
        "place": "1 Rue du Stade",
        "zipcode": "33000",
        "city": "Bordeaux",
        "country": "France"
    },
    "contact": {
        "home": "05 04 00 00 01",
        "office": "",
        "cellphone": "07 06 00 00 01",
        "email": "jacquet@toto.com"
    },
    "account": {
        "activationCode": "6d23f95b647d47d9ab35f92bc12819dc",
        "activationPasswd": null,
        "active": true,
        "expirationDate": NumberLong(0),
        "firstConnexion": false,
        "login": "ccaaj",
        "passwd": null,
        "password": "xHvS+ICtNg/1rrrj49WT+A/y9MA=",
        "salt": "CPAaJ8ydx6I=",
        "timestamp": NumberLong(1406738914448),
        "token": null,
        "tokenRenewDate": NumberLong(0),
        "habilitations": null,

        "listPlan": [{
            "paymentId": "paymentId004",
            "levelPlan": "PREMIUM",
            "amountPaid": NumberLong(6),
            "paidDate": NumberLong(1408312800000),
            "startPeriodDate": NumberLong(1408312800000),
            "endPeriodDate": NumberLong(1435615200000),
            "status": "paid",
            "periodicity": "monthly",
            "activity": {
                "_id": "ACT-FOOT",
                "code": "ACT-FOOT",
                "label": "admin.settings.activity.football.label",
                "enable": true,
                "activityType": "TEAM_SPORT"
            }
        }]
    }
});

//FIN USER Club Demo football