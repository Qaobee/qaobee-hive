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
db.User.remove({
    "_id": {
        "$in": ["5509ef1fdb8f8b6e2f51f4ce",
                "62b1870e-f1e4-4bcc-a389-ed8b22f564f7"
               ]
    }
});

/*
 * Alimentation collection USER
 */
db.User.insert({
    "_id" : "62b1870e-f1e4-4bcc-a389-ed8b22f564f7",
    "avatar" : null,
    "name" : "Onesta",
    "firstname" : "Claude",
    "address" : null,
    "birthdate" : 8.28e+07,
    "contact" : {
        "home" : "0202020202",
        "office" : null,
        "cellphone" : null,
        "email" : "ch.kervella@gmail.com",
        "fax" : null,
        "webSite" : null,
        "facebook" : null,
        "googlePlus" : null,
        "twitter" : null
    },
    "country" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : 250,
        "label" : "France",
        "local" : "fr"
    },
    "nationality" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : 250,
        "label" : "France",
        "local" : "fr"
    },
    "account" : {
        "_id" : null,
        "activationCode" : "6069e3d533314b019ded95f944586daa",
        "activationPasswd" : null,
        "active" : true,
        "expirationDate" : 0,
        "firstConnexion" : false,
        "login" : "onesta",
        "passwd" : null,
        "password" : "0ju49rez6FasAbRsJ9mHnjYPXyg=",
        "salt" : "PsMza+ryehM=",
        "timestamp" : 0,
        "token" : "f983ce61-1163-4d72-a943-d37ddd8ab4c7",
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
    "timestamp": NumberLong(1444854317672),
    "sandboxDefault" : "4c504098-e704-4e3e-8f7b-d1ea97beb681"
});

/*
 * Alimentation SB_SandBox France Handball
 */
db.SB_SandBox.insert({
    "_id" : "68d8945f-097b-4310-8499-7d282eed3354",
    "activityId" : "ACT-HAND",
    "owner" : "62b1870e-f1e4-4bcc-a389-ed8b22f564f7",
    "members": null,
    "structure": {
        "_id": "577fbf60b9cb07ea3649d6ec",
        "label": "EQUIPE DE FRANCE",
        "acronym": "EDF HB",
        "activity": {
            "_id": "ACT-HAND",
            "code": "ACT-HAND",
            "label": "admin.settings.activity.handball.label",
            "activated": true,
            "activityType": "TEAM_SPORT"
        },
        "address": {
            "place": "rue bidon",
            "zipcode": "75510",
            "city": "PARIS",
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
    "effectiveDefault":"4c504098-e704-4e3e-8f7b-d1ea97beb681",
});

/*
 * Vidage de la collection SB_Person
 */
db.SB_Person.remove({"sandboxId":"68d8945f-097b-4310-8499-7d282eed3354"});

/* 2 */
db.SB_Person.insert(
{
    "_id" : "0871a8a7-d0ea-4ff9-b450-b9d2451fc631",
    "address" : {
        "place" : "45 Allée Parc ar Groas",
        "zipcode" : "29280",
        "city" : "Montpellier",
        "country" : "France",
        "lat" : 43.6107689999999977,
        "lng" : 3.8767160000000001,
        "formatedAddress" : "Montpellier, France"
    },
    "avatar" : null,
    "birthCity" : "Woippy, France",
    "birthcountry" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : 250,
        "label" : "France",
        "local" : "fr"
    },
    "birthdate" : NumberLong(535071600000),
    "firstname" : "Vincent",
    "gender" : "Homme",
    "name" : "Gerard",
    "nationality" : "France",
    "sandboxId" : "68d8945f-097b-4310-8499-7d282eed3354",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 12,
        "weight" : 97,
        "height" : 188,
        "laterality" : "Droitier",
        "stateForm" : "good",
        "positionType" : "goalkeeper"
    }
});

/* 3 */
db.SB_Person.insert(
{
    "_id" : "32974e34-1af5-490e-abeb-224704571bc8",
    "address" : {
        "place" : "45 Allée Parc ar Groas",
        "zipcode" : "29280",
        "city" : "Paris",
        "country" : "France",
        "lat" : 48.8566140000000004,
        "lng" : 2.3522219000000000,
        "formatedAddress" : "Paris, France"
    },
    "avatar" : null,
    "birthCity" : "Mulhouse, France",
    "birthcountry" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : 250,
        "label" : "France",
        "local" : "fr"
    },
    "birthdate" : NumberLong(216601200000),
    "firstname" : "Thierry",
    "gender" : "Homme",
    "name" : "Omeyer",
    "nationality" : "France",
    "sandboxId" : "68d8945f-097b-4310-8499-7d282eed3354",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 16,
        "weight" : 93,
        "height" : 192,
        "laterality" : "Droitier",
        "stateForm" : "good",
        "positionType" : "goalkeeper"
    }
});

/* 4 */
db.SB_Person.insert(
{
    "_id" : "4305c274-0d70-4d72-83c2-2f5b724d45ab",
    "address" : {
        "place" : "45 Allée Parc ar Groas",
        "zipcode" : "29280",
        "city" : "Nantes",
        "country" : "France",
        "lat" : 47.2183709999999977,
        "lng" : -1.5536209999999999,
        "formatedAddress" : "Nantes, France"
    },
    "avatar" : null,
    "birthCity" : "Nîmes, France",
    "birthcountry" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : 250,
        "label" : "France",
        "local" : "fr"
    },
    "birthdate" : NumberLong(708732000000),
    "firstname" : "Théo",
    "gender" : "Homme",
    "name" : "Derot",
    "nationality" : "France",
    "sandboxId" : "68d8945f-097b-4310-8499-7d282eed3354",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 25,
        "weight" : 98,
        "height" : 193,
        "laterality" : "Droitier",
        "stateForm" : "good",
        "positionType" : "left-backcourt"
    }
});

/* 5 */
db.SB_Person.insert(
{
    "_id" : "621aea66-79dc-45f6-a59e-74dfef82f160",
    "address" : {
        "place" : "45 Allée Parc ar Groas",
        "zipcode" : "29280",
        "city" : "Paris",
        "country" : "France",
        "lat" : 48.8566140000000004,
        "lng" : 2.3522219000000000,
        "formatedAddress" : "Paris, France"
    },
    "avatar" : null,
    "birthCity" : "Serbie, Niš",
    "birthcountry" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : 250,
        "label" : "France",
        "local" : "fr"
    },
    "birthdate" : NumberLong(450482400000),
    "firstname" : "Nicolas",
    "gender" : "Homme",
    "name" : "Karabatic",
    "nationality" : "Serbie",
    "sandboxId" : "68d8945f-097b-4310-8499-7d282eed3354",
    "status" : {
        "squadnumber" : "13",
        "positionType" : "left-backcourt",
        "laterality" : "lefthanded",
        "stateForm" : "good",
        "weight" : "107",
        "height" : "196",
        "availability" : {
            "value" : "available",
            "cause" : "available"
        }
    }
});

/* 6 */
db.SB_Person.insert(
{
    "_id" : "86c9441c-fb08-47eb-bae1-dccd04286b02",
    "address" : {
        "place" : "45 Allée Parc ar Groas",
        "zipcode" : "29280",
        "city" : "Montpellier",
        "country" : "France",
        "lat" : 43.6107689999999977,
        "lng" : 3.8767160000000001,
        "formatedAddress" : "Montpellier, France"
    },
    "avatar" : null,
    "birthCity" : "Perpignan, France",
    "birthcountry" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : 250,
        "label" : "France",
        "local" : "fr"
    },
    "birthdate" : NumberLong(836172000000),
    "firstname" : "Ludovic",
    "gender" : "Homme",
    "name" : "Fabregas",
    "nationality" : "France",
    "sandboxId" : "68d8945f-097b-4310-8499-7d282eed3354",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 23,
        "weight" : 100,
        "height" : 198,
        "laterality" : "Droitier",
        "stateForm" : "good",
        "positionType" : "pivot"
    }
});

/* 7 */
db.SB_Person.insert(
{
    "_id" : "a9320ebe-5d03-45e8-a36d-f95189791ade",
    "address" : {
        "place" : "45 Allée Parc ar Groas",
        "zipcode" : "29280",
        "city" : "Paris",
        "country" : "France",
        "lat" : 48.8566140000000004,
        "lng" : 2.3522219000000000,
        "formatedAddress" : "Paris, France"
    },
    "avatar" : null,
    "birthCity" : "Strasbourg, France",
    "birthcountry" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : 250,
        "label" : "France",
        "local" : "fr"
    },
    "birthdate" : NumberLong(577404000000),
    "firstname" : "Luka",
    "gender" : "Homme",
    "name" : "Karabatic",
    "nationality" : "France",
    "sandboxId" : "68d8945f-097b-4310-8499-7d282eed3354",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 22,
        "weight" : 107,
        "height" : 202,
        "laterality" : "Droitier",
        "stateForm" : "good",
        "positionType" : "pivot"
    }
});

/* 8 */
db.SB_Person.insert(
{
    "_id" : "d4ba928f-67cd-4a41-bea2-8fbdc3fedeb0",
    "address" : {
        "place" : "45 Allée Parc ar Groas",
        "zipcode" : "29280",
        "city" : "Montpellier",
        "country" : "France",
        "lat" : 43.6107689999999977,
        "lng" : 3.8767160000000001,
        "formatedAddress" : "Montpellier, France"
    },
    "avatar" : null,
    "birthCity" : "Apt, France",
    "birthcountry" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : 250,
        "label" : "France",
        "local" : "fr"
    },
    "birthdate" : NumberLong(381020400000),
    "firstname" : "Michaël",
    "gender" : "Homme",
    "name" : "Guigou",
    "nationality" : "France",
    "sandboxId" : "68d8945f-097b-4310-8499-7d282eed3354",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 21,
        "weight" : 78,
        "height" : 179,
        "laterality" : "Droitier",
        "stateForm" : "good",
        "positionType" : "left-wingman"
    }
});

/* 9 */
db.SB_Person.insert(
{
    "_id" : "da360af7-a0a4-4d70-b7eb-b2c370449e3c",
    "address" : {
        "place" : "45 Allée Parc ar Groas",
        "zipcode" : "29280",
        "city" : "Montpellier",
        "country" : "France",
        "lat" : 43.6107689999999977,
        "lng" : 3.8767160000000001,
        "formatedAddress" : "Montpellier, France"
    },
    "avatar" : null,
    "birthCity" : "Versailles, France",
    "birthcountry" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : 250,
        "label" : "France",
        "local" : "fr"
    },
    "birthdate" : NumberLong(652658400000),
    "firstname" : "Valentin",
    "gender" : "Homme",
    "name" : "Porte",
    "nationality" : "France",
    "sandboxId" : "68d8945f-097b-4310-8499-7d282eed3354",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 28,
        "weight" : 96,
        "height" : 190,
        "laterality" : "Gaucher",
        "stateForm" : "good",
        "positionType" : "right-backcourt"
    }
});

/* 10 */
db.SB_Person.insert(
{
    "_id" : "e321f325-d374-4696-9011-8224a0881fb0",
    "address" : {
        "place" : "45 Allée Parc ar Groas",
        "zipcode" : "29280",
        "city" : "Paris",
        "country" : "France",
        "lat" : 48.8566140000000004,
        "lng" : 2.3522219000000000,
        "formatedAddress" : "Paris, France"
    },
    "avatar" : null,
    "birthCity" : "Saint-Denis, La Réunion",
    "birthcountry" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : 250,
        "label" : "France",
        "local" : "fr"
    },
    "birthdate" : NumberLong(314146800000),
    "firstname" : "Daniel",
    "gender" : "Homme",
    "name" : "Narcisse",
    "nationality" : "France",
    "sandboxId" : "68d8945f-097b-4310-8499-7d282eed3354",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 8,
        "weight" : 93,
        "height" : 189,
        "laterality" : "Droitier",
        "stateForm" : "good",
        "positionType" : "center-backcourt"
    }
});

/* 11 */
db.SB_Person.insert(
{
    "_id" : "ebeabe95-05fd-496a-8abb-49906a91bda3",
    "address" : {
        "place" : "45 Allée Parc ar Groas",
        "zipcode" : "29280",
        "city" : "Flensbourg",
        "country" : "Allemagne",
        "lat" : 54.7937431000000004,
        "lng" : 9.4469963999999997,
        "formatedAddress" : "Flensbourg, Allemagne"
    },
    "avatar" : null,
    "birthCity" : "Paris",
    "birthcountry" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : 250,
        "label" : "France",
        "local" : "fr"
    },
    "birthdate" : NumberLong(674863200000),
    "contact" : {
        "home" : ""
    },
    "firstname" : "Kentin",
    "gender" : "Homme",
    "name" : "Mahé",
    "nationality" : "France",
    "sandboxId" : "68d8945f-097b-4310-8499-7d282eed3354",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 14,
        "weight" : 83,
        "height" : 186,
        "laterality" : "Droitier",
        "stateForm" : "good",
        "positionType" : "center-backcourt"
    }
});

/* 12 */
db.SB_Person.insert(
{
    "_id" : "ecb66bfa-b766-42f0-8427-8353392fc6d6",
    "address" : {
        "place" : "45 Allée Parc ar Groas",
        "zipcode" : "29280",
        "city" : "Paris",
        "country" : "France",
        "lat" : 48.8566140000000004,
        "lng" : 2.3522219000000000,
        "formatedAddress" : "Paris, France"
    },
    "avatar" : null,
    "birthCity" : "Ivry-sur-Seine, France",
    "birthcountry" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : 250,
        "label" : "France",
        "local" : "fr"
    },
    "birthdate" : NumberLong(463269600000),
    "firstname" : "Luc",
    "gender" : "Homme",
    "name" : "Abalo",
    "nationality" : "France",
    "sandboxId" : "68d8945f-097b-4310-8499-7d282eed3354",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 19,
        "weight" : 86,
        "height" : 182,
        "laterality" : "Gaucher",
        "stateForm" : "good",
        "positionType" : "right-wingman"
    }
});

/* 13 */
db.SB_Person.insert(
{
    "_id" : "f8c6f3f5-3ec7-4a5e-a8e1-024711a323f6",
    "address" : {
        "place" : "45 Allée Parc ar Groas",
        "zipcode" : "29280",
        "city" : "Paris",
        "country" : "France",
        "lat" : 48.8566140000000004,
        "lng" : 2.3522219000000000,
        "formatedAddress" : "Paris, France"
    },
    "avatar" : null,
    "birthCity" : "Béziers, France",
    "birthcountry" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : 250,
        "label" : "France",
        "local" : "fr"
    },
    "birthdate" : NumberLong(520898400000),
    "firstname" : "Samuel",
    "gender" : "Homme",
    "name" : "Honrubia",
    "nationality" : "France",
    "sandboxId" : "68d8945f-097b-4310-8499-7d282eed3354",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 11,
        "weight" : 75,
        "height" : 180,
        "laterality" : "Droitier",
        "stateForm" : "good",
        "positionType" : "left-wingman"
    }
});

/* 14 */
db.SB_Person.insert(
{
    "_id" : "fbb25f0e-bb74-4ff4-8193-d94abd8fee42",
    "address" : {
        "place" : "45 Allée Parc ar Groas",
        "zipcode" : "29280",
        "city" : "Paris",
        "country" : "France",
        "lat" : 48.8566140000000004,
        "lng" : 2.3522219000000000,
        "formatedAddress" : "Paris, France"
    },
    "avatar" : null,
    "birthCity" : "Le Chesnay, France",
    "birthcountry" : {
        "_id" : "CNTR-250-FR-FRA",
        "codeOSCE" : 250,
        "label" : "France",
        "local" : "fr"
    },
    "birthdate" : NumberLong(853628400000),
    "firstname" : "Benoît",
    "gender" : "Homme",
    "name" : "Kounkoud",
    "nationality" : "France",
    "sandboxId" : "68d8945f-097b-4310-8499-7d282eed3354",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 29,
        "weight" : 88,
        "height" : 189,
        "laterality" : "Gaucher",
        "stateForm" : "good",
        "positionType" : "right-backcourt"
    }
});

/* 15 */
db.SB_Person.insert(
{
    "_id" : "eecb84ea-5a73-4774-b0b1-3a1e7ffb2b14",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 6,
        "weight" : 86,
        "height" : 187,
        "laterality" : "Droitier",
        "stateForm" : "good",
        "positionType" : "left-backcourt"
    },
    "address" : {
        "formatedAddress" : "Balingen, Allemagne",
        "lat" : 48.2748205999999982,
        "lng" : 8.8495297999999991,
        "city" : "Balingen",
        "country" : "Allemagne"
    },
    "contact" : {},
    "firstname" : "Olivier",
    "name" : "Nyokas",
    "birthdate" : NumberLong(520293600000),
    "gender" : "Homme",
    "nationality" : "France",
    "birthCity" : "Montfermeil, France",
    "avatar" : null,
    "sandboxId" : "68d8945f-097b-4310-8499-7d282eed3354"
});

/* 16 */
db.SB_Person.insert(
{
    "_id" : "827089ed-499d-4cce-88ad-41374968c953",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 20,
        "weight" : 110,
        "height" : 192,
        "laterality" : "Droitier",
        "stateForm" : "good",
        "positionType" : "pivot"
    },
    "address" : {
        "formatedAddress" : "Barcelone, Espagne",
        "lat" : 41.3850638999999987,
        "lng" : 2.1734035000000000,
        "city" : "Barcelone",
        "country" : "Espagne"
    },
    "contact" : {},
    "firstname" : "Cédric",
    "name" : "Sorhaindo",
    "birthdate" : NumberLong(455407200000),
    "gender" : "Homme",
    "nationality" : "France",
    "birthCity" : "La Trinité, Martinique",
    "avatar" : null,
    "sandboxId" : "68d8945f-097b-4310-8499-7d282eed3354"
});

/* 17 */
db.SB_Person.insert(
{
    "_id" : "fc46c0d3-2fb1-44e8-b07a-48d7adb953e9",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 5,
        "weight" : 94,
        "height" : 195,
        "laterality" : "Gaucher",
        "stateForm" : "good",
        "positionType" : "right-backcourt"
    },
    "address" : {
        "formatedAddress" : "Créteil, France",
        "lat" : 48.7903670000000034,
        "lng" : 2.4555720000000001,
        "city" : "Créteil",
        "country" : "France"
    },
    "contact" : {},
    "firstname" : "Nedim",
    "name" : "Remili",
    "birthdate" : NumberLong(806018400000),
    "gender" : "Homme",
    "nationality" : "France",
    "birthCity" : "Créteil, France",
    "avatar" : null,
    "sandboxId" : "68d8945f-097b-4310-8499-7d282eed3354"
});

/* 18 */
db.SB_Person.insert(
{
    "_id" : "ba7e9639-1009-4aa2-982f-330d7b04faaf",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 27,
        "weight" : 102,
        "height" : 202,
        "laterality" : "Gaucher",
        "stateForm" : "good",
        "positionType" : "right-backcourt"
    },
    "address" : {
        "formatedAddress" : "Saint-Raphaël, France",
        "lat" : 43.4251900000000006,
        "lng" : 6.7683700000000000,
        "city" : "Saint-Raphaël",
        "country" : "France"
    },
    "contact" : {},
    "firstname" : "Adrien",
    "name" : "Dipanda",
    "birthdate" : NumberLong(578613600000),
    "gender" : "Homme",
    "nationality" : "France",
    "birthCity" : "Dijon, France",
    "avatar" : null,
    "sandboxId" : "68d8945f-097b-4310-8499-7d282eed3354"
});

/*
 * Alimentation SB_Effective
 */
db.SB_Effective.insert({
    "_id" : "4c504098-e704-4e3e-8f7b-d1ea97beb681",
    "sandboxId" : "68d8945f-097b-4310-8499-7d282eed3354",
    "label": "France A",
    "categoryAge" : {
        "code" : "sen",
        "label" : "Senior Gars",
        "ageMax": 150,
        "ageMin": 18,
        "genre" : "Homme",
        "order": 1
    },
    "members" : [
        {
            "personId" : "32974e34-1af5-490e-abeb-224704571bc8",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "a9320ebe-5d03-45e8-a36d-f95189791ade",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "621aea66-79dc-45f6-a59e-74dfef82f160",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "f8c6f3f5-3ec7-4a5e-a8e1-024711a323f6",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "ebeabe95-05fd-496a-8abb-49906a91bda3",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "86c9441c-fb08-47eb-bae1-dccd04286b02",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "4305c274-0d70-4d72-83c2-2f5b724d45ab",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "da360af7-a0a4-4d70-b7eb-b2c370449e3c",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "0871a8a7-d0ea-4ff9-b450-b9d2451fc631",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "fbb25f0e-bb74-4ff4-8193-d94abd8fee42",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "d4ba928f-67cd-4a41-bea2-8fbdc3fedeb0",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "ecb66bfa-b766-42f0-8427-8353392fc6d6",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "e321f325-d374-4696-9011-8224a0881fb0",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "eecb84ea-5a73-4774-b0b1-3a1e7ffb2b14",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "827089ed-499d-4cce-88ad-41374968c953",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "fc46c0d3-2fb1-44e8-b07a-48d7adb953e9",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "ba7e9639-1009-4aa2-982f-330d7b04faaf",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        }
    ]
});

/*
 * Vidage de la collection SB_Team
 */
db.SB_Team.remove({"sandboxId" : "68d8945f-097b-4310-8499-7d282eed3354"});

/*
 * Alimentation SB_Team
 */
db.SB_Team.insert({
    "_id" : "ce0ef71d-2526-48b9-bf2d-72cc100954ac",
    "label" : "France A",
    "sandboxId" : "68d8945f-097b-4310-8499-7d282eed3354",
    "effectiveId" : "4c504098-e704-4e3e-8f7b-d1ea97beb681",
    "enable" : true,
    "adversary": false
});

db.SB_Team.insert({
    "_id" : "bc82ffd3-0430-412e-8596-6f023b50d241",
    "label" : "Serbie",
    "sandboxId" : "68d8945f-097b-4310-8499-7d282eed3354",
    "effectiveId" : "4c504098-e704-4e3e-8f7b-d1ea97beb681",
    "linkTeamId" : ["ce0ef71d-2526-48b9-bf2d-72cc100954ac"],
    "enable" : true,
    "adversary": true
});

db.SB_Team.insert({
    "_id" : "8195d7d8-9667-4a79-9f2d-9993d4014638",
    "label" : "Pologne",
    "sandboxId" : "68d8945f-097b-4310-8499-7d282eed3354",
    "effectiveId" : "4c504098-e704-4e3e-8f7b-d1ea97beb681",
    "linkTeamId" : ["ce0ef71d-2526-48b9-bf2d-72cc100954ac"],
    "enable" : true,
    "adversary": true
});

db.SB_Team.insert({
    "_id" : "47fbc170-eb60-40ee-bff0-89175558228b",
    "label" : "Croatie",
    "sandboxId" : "68d8945f-097b-4310-8499-7d282eed3354",
    "effectiveId" : "4c504098-e704-4e3e-8f7b-d1ea97beb681",
    "linkTeamId" : ["ce0ef71d-2526-48b9-bf2d-72cc100954ac"],
    "enable" : true,
    "adversary": true
});

/****************************************************************/


db.User.insert({
    "_id": "5509ef1fdb8f8b6e2f51f4ce",
    "name": "Sylla",
    "firstname": "Yerime",
    "avatar": null,
    "gender": "gender.male",
    "birthdate": -21434400000,
    "nationality": {"_id": "CNTR-250-FR-FRA", "codeOSCE": 250, "label": "France", "local": "fr"},
    "sandboxDefault":"558b0efebd2e39cdab651e1f",
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
    "members": null,
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
    "effectiveDefault":"550b31f925da07681592db23",
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
    "sandboxId" : "558b0efebd2e39cdab651e1f",
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
