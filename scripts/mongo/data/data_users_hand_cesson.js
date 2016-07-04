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

// DEBUT USER CLUB CESSON RENNES METROPOLE HB

/*
 * Vidage de la collection Person
 */
db.User.remove({"_id": "5509ef1fdb8f8b6e2f51f4ce"});

/*
 * Alimentation collection USER
 */
db.User.insert({
    "_id": "5509ef1fdb8f8b6e2f51f4ce",
    "name": "Sylla",
    "firstname": "Yerime",
    "avatar": null,
    "gender": "gender.male",
    "birthdate": -21434400000,
    "nationality": {"_id": "CNTR-250-FR-FRA", "codeOSCE": 250, "label": "France", "local": "fr"},
    "effectiveDefault":"550b31f925da07681592db23",
    "country": {
        "_id": "CNTR-250-FR-FRA",
        "codeOSCE": 250,
        "label": "France",
        "local": "fr"
    },
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
        "expirationDate": 0,
        "firstConnexion": false,
        "login": "crmhb1",
        "passwd": null,
        "password": "xHvS+ICtNg/1rrrj49WT+A/y9MA=",
        "salt": "CPAaJ8ydx6I=",
        "timestamp": 1406738914448,
        "token": null,
        "tokenRenewDate": 0,
        "habilitations": null,

        "listPlan": [{
            "paymentId": "paymentId001",
            "levelPlan" : "FREEMIUM",
            "amountPaid" : 9,
            "paidDate" : 0,
            "startPeriodDate": 1466590012943,
            "endPeriodDate" : 0,
            "status" : "open",
            "periodicity" : "monthly",
            "activity": {
                "_id": "ACT-HAND",
                "code": "ACT-HAND",
                "label": "admin.settings.activity.handball.label",
                "enable": true,
                "activityType": "TEAM_SPORT"
            },
            "paymentURL" : "https://www.payplug.com/pay/test/cnoSU0Pc8bURVwS0TF5yF",
            "cardId" : null,
            "cardInfo" : null,
            "shippingList" : null,
            "paiementURL" : "https://www.payplug.com/pay/test/cnoSU0Pc8bURVwS0TF5yF"
        }],
        "mobileToken" : null
    },
    "timestamp": 1444854317672
});

/*
 * Vidage de la collection SB_SandBox cesson handball
 */
db.SB_SandBox.remove({"_id": "558b0efebd2e39cdab651e1f"});


/*
 * Alimentation SB_SandBox Cesson Handball
 */
db.SB_SandBox.insert({
    "_id": "558b0efebd2e39cdab651e1f",
    "activityId": "ACT-HAND",
    "owner": "5509ef1fdb8f8b6e2f51f4ce",
    "sandboxCfgId": "558b0fc0bd2e39cdab651e21"
});

/*
 * Vidage de la collection SB_SandBoxCfg cesson handball
 */
db.SB_SandBoxCfg.remove({"_id": "558b0fc0bd2e39cdab651e21"});

db.SB_SandBoxCfg.insert({
    "_id": "558b0fc0bd2e39cdab651e21",
    "activity": {
        "_id": "ACT-HAND",
        "code": "ACT-HAND",
        "label": "admin.settings.activity.handball.label",
        "enable": true,
        "activityType": "TEAM_SPORT"
    },
    "sandbox": {
        "_id": "558b0efebd2e39cdab651e1f",
        "activityId": "ACT-HAND",
        "owner": "5509ef1fdb8f8b6e2f51f4ce"
    },
    "members": [],
    "season": {
        "_id": "558b0ceaf9285df5b7553fc6",
        "code": "SAI-2015",
        "label": "SAISON 2015-2016",
        "startDate": 1435701600000,
        "endDate": 1467237600000,
        "activityId": "ACT-HAND",
        "countryId": "CNTR-250-FR-FRA"
    },
    "structure": {
        "_id": "541168295971d35c1f2d1b5f",
        "label": "CESSON RENNES METROPOLE HB",
        "acronym": "CRMBH",
        "activity": {
            "_id": "ACT-HAND",
            "code": "ACT-HAND",
            "label": "admin.settings.activity.handball.label",
            "activated": true,
            "activityType": "TEAM_SPORT"
        },
        "address": {
            "place": "3, allée de Champagné",
            "zipcode": "35510",
            "city": "CESSON-SEVIGNE",
            "country": "France"
        },
        "contact": {
            "home": null,
            "office": "02 23 45 07 19",
            "cellphone": "06 69 97 68 39",
            "fax": "",
            "email": "sandrine@cesson-handball.com"
        },
        "country": {"_id": "CNTR-250-FR-FRA", "codeOSCE": 250, "label": "France", "local": "fr"},
        "avatar": null
    },
    "teams": []
});

/*
 * Vidage de la collection SB_Person
 */
db.SB_Person.remove({"sandboxId":"558b0efebd2e39cdab651e1f"});

/*
 * Alimentation SB_Person
 */

db.SB_Person.insert({
    "_id" : "550a05dadb8f8b6e2f51f4db",
    "sandboxId" : "558b0efebd2e39cdab651e1f",
    "name" : "Batinovic",
    "firstname" : "Damir",
    "avatar" : null,
    "gender" : "Homme",
    "birthdate": 556236000000,
    "birthcity" : "Zagreb, Croatie",
    "nationality" : "Croatie",
    "address" : {
        "formatedAddress" : "3 Allée de Champagne, Cesson-Sévigné, France",
        "lat" : 48.0899268000000006,
        "lng" : -1.6145389000000001,
        "place" : "3 Rue de Champagne",
        "city" : "Chantepie",
        "country" : "France",
        "zipcode" : "35135"
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
        "weight": 95,
        "height": 193,
        "squadnumber": 33,
        "positionType" : "center-backcourt",
        "laterality" : "Droitier"
    }
});

db.SB_Person.insert({
    "_id" : "550a05e3db8f8b6e2f51f4dc",
    "sandboxId" : "558b0efebd2e39cdab651e1f",
    "name" : "Bonnefoi",
    "firstname" : "Kevin",
    "avatar" : null,
    "gender" : "Homme",
    "birthdate": 691714800000,
    "birthcity" : "Saint-Raphaël, France",
    "nationality" : "France",
    "address" : {
        "formatedAddress" : "3 Allée de Champagne, Cesson-Sévigné, France",
        "lat" : 48.0899268000000006,
        "lng" : -1.6145389000000001,
        "place" : "3 Rue de Champagne",
        "city" : "Chantepie",
        "country" : "France",
        "zipcode" : "35135"
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
        "weight": 108,
        "height": 191,
        "squadnumber": 16,
        "positionType" : "goalkeeper",
        "laterality" : "Droitier"
    }
});

db.SB_Person.insert({
    "_id" : "550a05e9db8f8b6e2f51f4dd",
    "sandboxId" : "558b0efebd2e39cdab651e1f",
    "name" : "Briffe",
    "firstname" : "Romain",
    "avatar" : null,
    "gender" : "Homme",
    "birthdate": 601426800000,
    "birthcity" : "Rennes, France",
    "nationality" : "France",
    "address" : {
        "formatedAddress" : "3 Allée de Champagne, Cesson-Sévigné, France",
        "lat" : 48.0899268000000006,
        "lng" : -1.6145389000000001,
        "place" : "3 Rue de Champagne",
        "city" : "Chantepie",
        "country" : "France",
        "zipcode" : "35135"
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
        "weight": 83,
        "height": 189,
        "squadnumber": 13,
        "positionType" : "left-backcourt",
        "laterality" : "Droitier"
    }
});

db.SB_Person.insert({
    "_id" : "550a05f7db8f8b6e2f51f4de",
    "sandboxId" : "558b0efebd2e39cdab651e1f",
    "name" : "Derbier",
    "firstname" : "Maxime",
    "avatar" : null,
    "gender" : "Homme",
    "birthdate": 523663200000,
    "birthcity" : "Rennes, France",
    "nationality" : "France",
   "address" : {
        "formatedAddress" : "3 Allée de Champagne, Cesson-Sévigné, France",
        "lat" : 48.0899268000000006,
        "lng" : -1.6145389000000001,
        "place" : "3 Rue de Champagne",
        "city" : "Chantepie",
        "country" : "France",
        "zipcode" : "35135"
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
        "weight": 77,
        "height": 179,
        "squadnumber": 3,
        "positionType" : "right-wingman",
        "laterality" : "Gaucher"
    }
});

db.SB_Person.insert({
    "_id" : "550a0600db8f8b6e2f51f4df",
    "sandboxId" : "558b0efebd2e39cdab651e1f",
    "name" : "Dore",
    "firstname" : "Benoir",
    "avatar" : null,
    "gender" : "Homme",
    "birthdate": 444870000000,
    "birthcity" : "Rennes, France",
    "nationality" : "France",
    "address" : {
        "formatedAddress" : "3 Allée de Champagne, Cesson-Sévigné, France",
        "lat" : 48.0899268000000006,
        "lng" : -1.6145389000000001,
        "place" : "3 Rue de Champagne",
        "city" : "Chantepie",
        "country" : "France",
        "zipcode" : "35135"
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
        "weight": 82,
        "height": 183,
        "squadnumber": 35,
        "positionType" : "left-wingman",
        "laterality" : "Droitier"
    }
});

db.SB_Person.insert({
    "_id" : "550a0606db8f8b6e2f51f4e0",
    "sandboxId" : "558b0efebd2e39cdab651e1f",
    "name" : "Guillo",
    "firstname" : "Romaric",
    "avatar" : null,
    "gender" : "Homme",
    "birthdate": 686271600000,
    "birthcity" : "Rennes, France",
    "nationality" : "France",
    "address" : {
        "formatedAddress" : "3 Allée de Champagne, Cesson-Sévigné, France",
        "lat" : 48.0899268000000006,
        "lng" : -1.6145389000000001,
        "place" : "3 Rue de Champagne",
        "city" : "Chantepie",
        "country" : "France",
        "zipcode" : "35135"
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
        "weight": 106,
        "height": 207,
        "squadnumber": 56,
        "positionType" : "pivot",
        "laterality" : "Droitier"
    }
});

db.SB_Person.insert({
    "_id" : "550a060ddb8f8b6e2f51f4e1",
    "sandboxId" : "558b0efebd2e39cdab651e1f",
    "name" : "Hochet",
    "firstname" : "Sylvain",
    "avatar" : null,
    "gender" : "Homme",
    "birthdate": 564706800000,
    "birthcity" : "Rennes, France",
    "nationality" : "France",
    "address" : {
        "formatedAddress" : "3 Allée de Champagne, Cesson-Sévigné, France",
        "lat" : 48.0899268000000006,
        "lng" : -1.6145389000000001,
        "place" : "3 Rue de Champagne",
        "city" : "Chantepie",
        "country" : "France",
        "zipcode" : "35135"
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
        "weight": 87,
        "height": 185,
        "squadnumber": 11,
        "positionType" : "left-wingman",
        "laterality" : "Droitier"
    }
});

db.SB_Person.insert({
    "_id" : "550a0614db8f8b6e2f51f4e2",
    "sandboxId" : "558b0efebd2e39cdab651e1f",
    "name" : "Lanfranchi",
    "firstname" : "Mathieu",
    "avatar" : null,
    "gender" : "Homme",
    "birthdate": 401580000000,
    "birthcity" : "Rennes, France",
    "nationality" : "France",
    "address" : {
        "formatedAddress" : "3 Allée de Champagne, Cesson-Sévigné, France",
        "lat" : 48.0899268000000006,
        "lng" : -1.6145389000000001,
        "place" : "3 Rue de Champagne",
        "city" : "Chantepie",
        "country" : "France",
        "zipcode" : "35135"
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
        "weight": 93,
        "height": 178,
        "squadnumber": 23,
        "positionType" : "pivot",
        "laterality" : "Droitier"
    }
});

db.SB_Person.insert({
    "_id" : "550a061bdb8f8b6e2f51f4e3",
    "sandboxId" : "558b0efebd2e39cdab651e1f",
    "name" : "Le boulaire",
    "firstname" : "Léo",
    "avatar" : null,
    "gender" : "Homme",
    "birthdate": 712274400000,
    "birthcity" : "Rennes, France",
    "nationality" : "France",
    "address" : {
        "formatedAddress" : "3 Allée de Champagne, Cesson-Sévigné, France",
        "lat" : 48.0899268000000006,
        "lng" : -1.6145389000000001,
        "place" : "3 Rue de Champagne",
        "city" : "Chantepie",
        "country" : "France",
        "zipcode" : "35135"
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
        "weight": 85,
        "height": 180,
        "squadnumber": 15,
        "positionType" : "right-wingman",
        "laterality" : "Gaucher"
    }
});

db.SB_Person.insert({
    "_id" : "550a0620db8f8b6e2f51f4e4",
    "sandboxId" : "558b0efebd2e39cdab651e1f",
    "name" : "Redei",
    "firstname" : "Istvan",
    "avatar" : null,
    "gender" : "Homme",
    "birthdate": 430437600000,
    "birthcity" : "Budapest, Hongrie",
    "nationality" : "Hongrie",
    "address" : {
        "formatedAddress" : "3 Allée de Champagne, Cesson-Sévigné, France",
        "lat" : 48.0899268000000006,
        "lng" : -1.6145389000000001,
        "place" : "3 Rue de Champagne",
        "city" : "Chantepie",
        "country" : "France",
        "zipcode" : "35135"
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
        "weight": 101,
        "height": 197,
        "squadnumber": 19,
        "positionType" : "right-backcourt",
        "laterality" : "Gaucher"
    }
});

db.SB_Person.insert({
    "_id" : "550a0620db8f8b6e2f51f4e5",
    "sandboxId" : "558b0efebd2e39cdab651e1f",
    "name" : "Robin",
    "firstname" : "Mickaël",
    "avatar" : null,
    "gender" : "Homme",
    "birthdate": 486079200000,
    "birthcity" : "Strasbourg, France",
    "nationality" : "France",
    "address" : {
        "formatedAddress" : "3 Allée de Champagne, Cesson-Sévigné, France",
        "lat" : 48.0899268000000006,
        "lng" : -1.6145389000000001,
        "place" : "3 Rue de Champagne",
        "city" : "Chantepie",
        "country" : "France",
        "zipcode" : "35135"
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
        "weight": 87,
        "height": 190,
        "squadnumber": 4,
        "positionType" : "goalkeeper",
        "laterality" : "Droitier"
    }
});

db.SB_Person.insert({
    "_id" : "550a0620db8f8b6e2f51f4e6",
    "sandboxId" : "558b0efebd2e39cdab651e1f",
    "name" : "Skatar",
    "firstname" : "mikele",
    "avatar" : null,
    "gender" : "Homme",
    "birthdate": 504745200000,
    "birthcity" : "Rome, Italie",
    "nationality" : "Italie",
    "address" : {
        "formatedAddress" : "3 Allée de Champagne, Cesson-Sévigné, France",
        "lat" : 48.0899268000000006,
        "lng" : -1.6145389000000001,
        "place" : "3 Rue de Champagne",
        "city" : "Chantepie",
        "country" : "France",
        "zipcode" : "35135"
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
        "weight": 94,
        "height": 190,
        "squadnumber": 17,
        "positionType" : "right-backcourt",
        "laterality" : "Gaucher"
    }
});

db.SB_Person.insert({
    "_id" : "550a0620db8f8b6e2f51f4e7",
    "sandboxId" : "558b0efebd2e39cdab651e1f",
    "name" : "Suty",
    "firstname" : "Jérémy",
    "avatar" : null,
    "gender" : "Homme",
    "birthdate": 529714800000,
    "birthcity" : "Rennes, France",
    "nationality" : "France",
    "address" : {
        "formatedAddress" : "3 Allée de Champagne, Cesson-Sévigné, France",
        "lat" : 48.0899268000000006,
        "lng" : -1.6145389000000001,
        "place" : "3 Rue de Champagne",
        "city" : "Chantepie",
        "country" : "France",
        "zipcode" : "35135"
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
        "weight": 85,
        "height": 188,
        "squadnumber": 5,
        "positionType" : "center-backcourt",
        "laterality" : "Droitier"
    }
});

db.SB_Person.insert({
    "_id" : "550a0620db8f8b6e2f51f4e8",
    "sandboxId" : "558b0efebd2e39cdab651e1f",
    "name" : "Celica",
    "firstname" : "Dusko",
    "avatar" : null,
    "gender" : "Homme",
    "birthdate": 524527200000,
    "birthcity" : "Sarajevo, Fédération de Bosnie-et-Herzégovine, Bosnie-Herzégovine",
    "nationality" : "Bosnie-Herzégovine",
    "address" : {
        "formatedAddress" : "3 Allée de Champagne, Cesson-Sévigné, France",
        "lat" : 48.0899268000000006,
        "lng" : -1.6145389000000001,
        "place" : "3 Rue de Champagne",
        "city" : "Chantepie",
        "country" : "France",
        "zipcode" : "35135"
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
        "weight": 100,
        "height": 198,
        "squadnumber": 10,
        "positionType" : "left-backcourt",
        "laterality" : "Droitier"
    }
});

db.SB_Person.insert({
    "_id" : "7cf258dc-8126-4718-9912-5721abc69aac",
    "sandboxId" : "558b0efebd2e39cdab651e1f",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber": 10,
        "weight": 84,
        "height": 81,
        "laterality" : "Droitier",
        "stateForm" : "good",
        "positionType" : "center-backcourt"
    },
    "address" : {
        "formatedAddress" : "3 Allée de Champagne, Cesson-Sévigné, France",
        "lat" : 48.0899268000000006,
        "lng" : -1.6145389000000001,
        "place" : "3 Rue de Champagne",
        "city" : "Chantepie",
        "country" : "France",
        "zipcode" : "35135"
    },
    "contact" : {
        "email" : "chris1.kervella@gmele.com",
        "home" : "0298020202",
        "cellphone" : "0707070707"
    },
    "firstname" : "Chris1",
    "name" : "Kervella",
    "birthdate": 110415600000,
    "gender" : "Homme",
    "nationality" : "France",
    "birthCity" : "Brest, France",
    "avatar" : null
});

db.SB_Person.insert({
    "_id" : "7cf258dc-8126-4718-9912-5721abc69aad",
    "sandboxId" : "558b0efebd2e39cdab651e1f",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber": 10,
        "weight": 84,
        "height": 81,
        "laterality" : "Droitier",
        "stateForm" : "good",
        "positionType" : "center-backcourt"
    },
    "address" : {
        "formatedAddress" : "3 Allée de Champagne, Cesson-Sévigné, France",
        "lat" : 48.0899268000000006,
        "lng" : -1.6145389000000001,
        "place" : "3 Rue de Champagne",
        "city" : "Chantepie",
        "country" : "France",
        "zipcode" : "35135"
    },
    "contact" : {
        "email" : "chris2.kervella@gmele.com",
        "home" : "0298020202",
        "cellphone" : "0707070707"
    },
    "firstname" : "Chris2",
    "name" : "Kervella",
    "birthdate": 425944800000,
    "gender" : "Homme",
    "nationality" : "France",
    "birthCity" : "Brest, France",
    "avatar" : null
});

db.SB_Person.insert({
    "_id" : "7cf258dc-8126-4718-9912-5721abc69aae",
    "sandboxId" : "558b0efebd2e39cdab651e1f",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber": 10,
        "weight": 84,
        "height": 81,
        "laterality" : "Droitier",
        "stateForm" : "good",
        "positionType" : "center-backcourt"
    },
    "address" : {
        "formatedAddress" : "3 Allée de Champagne, Cesson-Sévigné, France",
        "lat" : 48.0899268000000006,
        "lng" : -1.6145389000000001,
        "place" : "3 Rue de Champagne",
        "city" : "Chantepie",
        "country" : "France",
        "zipcode" : "35135"
    },
    "contact" : {
        "email" : "chris3.kervella@gmele.com",
        "home" : "0298020202",
        "cellphone" : "0707070707"
    },
    "firstname" : "Chris3",
    "name" : "Kervella",
    "birthdate": 741564000000,
    "gender" : "Homme",
    "nationality" : "France",
    "birthCity" : "Brest, France",
    "avatar" : null
});

/*
 * Vidage de la collection SB_Effective
 */
db.SB_Effective.remove({ "_id" : "550b31f925da07681592db23"});

/*
 * Alimentation SB_Effective
 */
db.SB_Effective.insert({
    "_id" : "550b31f925da07681592db23",
    "sandBoxCfgId" : "558b0fc0bd2e39cdab651e21",
    "label": "Cesson A",
    "categoryAge" : {
        "code" : "sen",
        "label" : "Senior Gars",
        "ageMax": 150,
        "ageMin": 18,
        "genre" : "Homme",
        "order": 1
    },
    "members" : [{ "personId": "550a05dadb8f8b6e2f51f4db","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "550a05e3db8f8b6e2f51f4dc","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "550a05e9db8f8b6e2f51f4dd","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "550a05f7db8f8b6e2f51f4de","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "550a0600db8f8b6e2f51f4df","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "550a0606db8f8b6e2f51f4e0","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "550a060ddb8f8b6e2f51f4e1","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "550a0614db8f8b6e2f51f4e2","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "550a061bdb8f8b6e2f51f4e3","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "550a0620db8f8b6e2f51f4e4","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "550a0620db8f8b6e2f51f4e5","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "550a0620db8f8b6e2f51f4e6","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "550a0620db8f8b6e2f51f4e7","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "550a0620db8f8b6e2f51f4e8","role" : {"code": "player", "label": "Joueur"}},
                 { "personId": "5509ef1fdb8f8b6e2f51f4ce","role" : {"code": "coach", "label": "Coach"}},
                 { "personId": "5509ef1fdb8f8b6e2f51f4cf","role" : {"code": "acoach", "label": "Coach Adjoint"}}
    ]
});

/*
 * Vidage de la collection SB_Team
 */
db.SB_Team.remove({"sandboxId" : "558b0efebd2e39cdab651e1f"});

/*
 * Alimentation SB_Team
 */
db.SB_Team.insert({
    "_id" : "552d5e08644a77b3a20afdfe",
    "label" : "Cesson-Sevigne A",
    "sandboxId" : "558b0efebd2e39cdab651e1f",
    "effectiveId" : "550b31f925da07681592db23",
    "enable" : true,
    "adversary": false
});

db.SB_Team.insert({
    "_id" : "55e76161427aacaa71480569",
    "label" : "Nantes HBC",
    "sandboxId" : "558b0efebd2e39cdab651e1f",
    "effectiveId" : "550b31f925da07681592db23",
    "linkTeamId" : ["552d5e08644a77b3a20afdfe"],
    "enable" : true,
    "adversary": true
});

db.SB_Team.insert({
    "_id" : "55e7619a427aacaa7148056a",
    "label" : "PSG Handball",
    "sandboxId" : "558b0efebd2e39cdab651e1f",
    "effectiveId" : "550b31f925da07681592db23",
    "linkTeamId" : ["552d5e08644a77b3a20afdfe"],
    "enable" : true,
    "adversary": true
});

db.SB_Team.insert({
    "_id" : "55e768c0427aacaa7148056b",
    "label" : "AIX En Provence HB",
    "sandboxId" : "558b0efebd2e39cdab651e1f",
    "effectiveId" : "550b31f925da07681592db23",
    "enable" : true,
    "adversary": true
});

db.SB_Team.insert({
    "_id" : "55e76b04427aacaa7148056c",
    "label" : "CHAMBERY SAVOIE HB",
    "sandboxId" : "558b0efebd2e39cdab651e1f",
    "effectiveId" : "550b31f925da07681592db23",
    "linkTeamId" : ["552d5e08644a77b3a20afdfe"],
    "enable" : true,
    "adversary": true
});

db.SB_Team.insert({
    "_id" : "55e76b26427aacaa7148056d",
    "label" : "USDK Dunkerque",
    "sandboxId" : "558b0efebd2e39cdab651e1f",
    "effectiveId" : "550b31f925da07681592db23",
    "linkTeamId" : ["552d5e08644a77b3a20afdfe"],
    "enable" : true,
    "adversary": true
});

db.SB_Team.insert({
    "_id" : "55e76b44427aacaa7148056e",
    "label" : "US CRETEIL HANDBALL",
    "sandboxId" : "558b0efebd2e39cdab651e1f",
    "effectiveId" : "550b31f925da07681592db23",
    "linkTeamId" : ["552d5e08644a77b3a20afdfe"],
    "enable" : true,
    "adversary": true
});

db.SB_Team.insert({
    "_id" : "55e76b64427aacaa7148056f",
    "label" : "Toulouse HB",
    "sandboxId" : "558b0efebd2e39cdab651e1f",
    "effectiveId" : "550b31f925da07681592db23",
    "linkTeamId" : ["552d5e08644a77b3a20afdfe"],
    "enable" : true,
    "adversary": true
});


//FIN USER CESSON RENNES METROPOLE HB
