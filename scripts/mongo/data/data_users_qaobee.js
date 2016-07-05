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
 * Vidage de la collection User qaobee
 */

db.User.remove({
    "_id": {
        "$in": ["a0ef9c2d-6864-4a20-84ba-b66a666d2bf4",
                "b50b3325-fdbd-41bf-bda4-81c827982001"
               ]
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
            "levelPlan": "ADMIN",
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

/*
 * Alimentation collection USER : CKE
 */
db.User.insert({
    "_id" : "b50b3325-fdbd-41bf-bda4-81c827982001",
    "avatar" : null,
    "name" : "Kervella",
    "firstname" : "Christophe",
    "address" : {
        "formatedAddress" : "45 Allée Parc ar Groas, Plouzané, France",
        "lat" : 48.3771823000000012,
        "lng" : -4.6230722000000002,
        "place" : "45 Allée Parc ar Groas",
        "city" : "Plouzané",
        "country" : "France",
        "zipcode" : "29280"
    },
    "birthdate" : NumberLong(110415600000),
    "contact" : {
        "home" : "0781635773",
        "office" : null,
        "cellphone" : null,
        "email" : "christophe.kervella@qaobee.com",
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
        "activationCode" : "9443356eb2e6462596243ee8a58d3f56",
        "activationPasswd" : null,
        "active" : true,
        "expirationDate" : 0,
        "firstConnexion" : false,
        "login" : "cke-qaobee",
        "passwd" : null,
        "password" : "QrHqnRQwrJ3zwCw7dalpfH5rkoE=",
        "salt" : "MkgZzvYT+d8=",
        "timestamp" : 0,
        "token" : null,
        "tokenRenewDate" : 0,
        "listPlan" : [
            {
                "paymentId" : "d9248a81-2e4c-49f6-85d4-afc1599c5669",
                "levelPlan" : "FREEMIUM",
                "amountPaid" : 9,
                "paidDate" : 0,
                "startPeriodDate" : NumberLong(1466590012943),
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
            }
        ],
        "habilitations" : null,
        "mobileToken" : null
    },
    "timestamp" : NumberLong(1444854317672),
    "gender" : "gender.male",
    "sandboxDefault" : "561ec20b409937a6b439d4e9"
});

/*
 * Vidage de la collection SB_SandBox CKE
 */
db.SB_SandBox.remove({"_id": "561ec20b409937a6b439d4e9"});


/***************************************************************
 * Alimentation SB_SandBox CKE
 * *************************************************************
 */
db.SB_SandBox.insert({
    "_id": "561ec20b409937a6b439d4e9",
    "activity": {
        "_id": "ACT-HAND",
        "code": "ACT-HAND",
        "label": "admin.settings.activity.handball.label",
        "enable": true,
        "activityType": "TEAM_SPORT"
    },
    "owner": "b50b3325-fdbd-41bf-bda4-81c827982001",
    "members": [],
    "structure": {
        "_id": "541168295971d35c1f2d1b5f",
        "label": "CLUB CKE HB",
        "acronym": "CKEHB",
        "activity": {
            "_id": "ACT-HAND",
            "code": "ACT-HAND",
            "label": "admin.settings.activity.handball.label",
            "activated": true,
            "activityType": "TEAM_SPORT"
        },
        "country": {"_id": "CNTR-250-FR-FRA", "codeOSCE": NumberInt(250), "label": "France", "local": "fr"},
        "avatar": null
    },
    "effectiveDefault" : "561ec4d0409937a6b439d4ea"
});

//////////////////////////////////////////////////////////
/*
 * Vidage de la collection SB_Person (CKE)
 */
db.SB_Person.remove({"sandboxId":"561ec20b409937a6b439d4e9"});


/* 1 */
db.SB_Person.insert({
    "_id" : "5f82c510-2c89-46b0-b87d-d3b59e748615",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 11,
        "weight" : 60,
        "height" : 175,
        "laterality" : "Droitier",
        "stateForm" : "good",
        "positionType" : "left-backcourt"
    },
    "address" : {
        "formatedAddress" : "45 Allée Parc ar Groas, Plouzané, France",
        "lat" : 48.3771823000000012,
        "lng" : -4.6230722000000002,
        "place" : "45 Allée Parc ar Groas",
        "city" : "Plouzané",
        "country" : "France",
        "zipcode" : "29280"
    },
    "contact" : {},
    "firstname" : "Tristan",
    "name" : "Kervella",
    "birthdate" : NumberLong(1014332400000),
    "gender" : "Homme",
    "nationality" : "France",
    "birthCity" : "Plouzané, France",
    "sandboxId" : "561ec20b409937a6b439d4e9"
});

/* 2 */
db.SB_Person.insert({
    "_id" : "d31d3550-479a-4ee9-8304-84a132202d89",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 10,
        "weight" : 55,
        "height" : 155,
        "laterality" : "Droitier",
        "stateForm" : "good",
        "positionType" : "pivot"
    },
    "address" : {
        "formatedAddress" : "8 Hameau de Ker Yan, Locmaria-Plouzané, France",
        "lat" : 48.3736687999999972,
        "lng" : -4.6507980000000000,
        "place" : "8 Hameau de Ker Yan",
        "city" : "Locmaria-Plouzané",
        "country" : "France",
        "zipcode" : "29280"
    },
    "contact" : {
        "cellphone" : "0698997229",
        "home" : "0298489077",
        "email" : "patrick.fleury@laposte.net"
    },
    "firstname" : "Thibaut",
    "name" : "Fleury",
    "birthdate" : NumberLong(1015023600000),
    "nationality" : "France",
    "birthCity" : "Brest, France",
    "gender" : "Homme",
    "sandboxId" : "561ec20b409937a6b439d4e9"
});

/* 3 */
db.SB_Person.insert({
    "_id" : "88afb9c2-eb10-482c-938d-34089405e65f",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 9,
        "weight" : 50,
        "height" : 155,
        "laterality" : "Droitier",
        "stateForm" : "good",
        "positionType" : "left-wingman"
    },
    "address" : {
        "formatedAddress" : "8 Rue Courlis, Locmaria-Plouzané, France",
        "lat" : 48.3698181000000034,
        "lng" : -4.6586223999999996,
        "place" : "8 Rue Courlis",
        "city" : "Locmaria-Plouzané",
        "country" : "France",
        "zipcode" : "29280"
    },
    "contact" : {
        "email" : "katdomclo@gmail.com",
        "home" : "0298382324"
    },
    "firstname" : "Victor",
    "name" : "Clorennec",
    "gender" : "Homme",
    "birthdate" : NumberLong(1034546400000),
    "nationality" : "France",
    "birthCity" : "Senlis, France",
    "sandboxId" : "561ec20b409937a6b439d4e9"
});

/* 4 */
db.SB_Person.insert({
    "_id" : "c0bf7706-f039-46c3-86b2-460930696890",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 8,
        "weight" : 60,
        "height" : 165,
        "laterality" : "Gaucher",
        "stateForm" : "good",
        "positionType" : "right-wingman"
    },
    "address" : {
        "formatedAddress" : "3 Route de Kerfily, Locmaria-Plouzané, France",
        "lat" : 48.3749360999999993,
        "lng" : -4.6458358000000004,
        "place" : "3 Route de Kerfily",
        "city" : "Locmaria-Plouzané",
        "country" : "France",
        "zipcode" : "29280"
    },
    "contact" : {
        "home" : "0298485392",
        "email" : "gbergot@laposte.net"
    },
    "firstname" : "Mathys",
    "name" : "Bergot",
    "gender" : "Homme",
    "birthdate" : NumberLong(1037314800000),
    "nationality" : "France",
    "birthCity" : "Brest, France",
    "sandboxId" : "561ec20b409937a6b439d4e9"
});

/* 5 */
db.SB_Person.insert({
    "_id" : "26baf31a-f153-41b0-9e1d-c32cb9e859dd",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 12,
        "weight" : 65,
        "height" : 175,
        "laterality" : "Droitier",
        "stateForm" : "good",
        "positionType" : "goalkeeper"
    },
    "address" : {
        "formatedAddress" : "1 Rue Duperré, Brest, France",
        "lat" : 48.3961918999999980,
        "lng" : -4.4837622000000001,
        "place" : "1 Rue Duperré",
        "city" : "Brest",
        "country" : "France",
        "zipcode" : "29200"
    },
    "contact" : {
        "home" : "0298430448"
    },
    "firstname" : "Ewenn",
    "name" : "Scoarnec",
    "birthdate" : NumberLong(1017183600000),
    "nationality" : "France",
    "birthCity" : "Bordeaux, France",
    "sandboxId" : "561ec20b409937a6b439d4e9"
});

/* 6 */
db.SB_Person.insert({
    "_id" : "8915da91-832f-419f-b5e6-a92d6828fd31",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 6,
        "weight" : 65,
        "height" : 178,
        "laterality" : "Droitier",
        "stateForm" : "good",
        "positionType" : "right-backcourt"
    },
    "address" : {
        "formatedAddress" : "34 Rue Albert Louppe, Brest, France",
        "lat" : 48.4034297999999978,
        "lng" : -4.4710770000000002,
        "place" : "34 Rue Albert Louppe",
        "city" : "Brest",
        "country" : "France",
        "zipcode" : "29200"
    },
    "contact" : {
        "cellphone" : "0611857269",
        "home" : "0256294305"
    },
    "firstname" : "Clement",
    "name" : "Pellen",
    "gender" : "Homme",
    "birthdate" : NumberLong(1019340000000),
    "nationality" : "France",
    "birthCity" : "Brest, France",
    "sandboxId" : "561ec20b409937a6b439d4e9"
});

/* 7 */
db.SB_Person.insert({
    "_id" : "941f9d48-45e8-4b2f-b0ce-d33900a92fb8",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 5,
        "weight" : 65,
        "height" : 164,
        "laterality" : "Droitier",
        "stateForm" : "good",
        "positionType" : "center-backcourt"
    },
    "address" : {
        "formatedAddress" : "275 Rue des Iris, Plouzané, France",
        "lat" : 48.3700189999999992,
        "lng" : -4.5799702000000000,
        "place" : "275 Rue des Iris",
        "city" : "Plouzané",
        "country" : "France",
        "zipcode" : "29280"
    },
    "contact" : {
        "email" : "frank.charrault@hotmail.fr",
        "home" : "0298037320",
        "cellphone" : "0760970956"
    },
    "firstname" : "Hugo",
    "name" : "Charrault",
    "gender" : "Homme",
    "birthdate" : NumberLong(1003269600000),
    "nationality" : "France",
    "birthCity" : "Brest, France",
    "sandboxId" : "561ec20b409937a6b439d4e9"
});

/* 8 */
db.SB_Person.insert({
    "_id" : "c857c124-79c0-4b6e-8406-f89a26b8426f",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 1,
        "weight" : 67,
        "height" : 177,
        "laterality" : "Droitier",
        "stateForm" : "good",
        "positionType" : "goalkeeper"
    },
    "address" : {
        "formatedAddress" : "275 Rue des Iris, Plouzané, France",
        "lat" : 48.3700189999999992,
        "lng" : -4.5799702000000000,
        "place" : "275 Rue des Iris",
        "city" : "Plouzané",
        "country" : "France",
        "zipcode" : "29280"
    },
    "contact" : {
        "cellphone" : "0760970956",
        "home" : "0298037320",
        "email" : "frank.charrault@hotmail.fr"
    },
    "firstname" : "Tom",
    "name" : "Charrault",
    "gender" : "Homme",
    "birthdate" : NumberLong(1034805600000),
    "nationality" : "France",
    "birthCity" : "Brest, France",
    "sandboxId" : "561ec20b409937a6b439d4e9"
});

/* 9 */
db.SB_Person.insert({
    "_id" : "ce18d73e-dedf-43e5-8e75-16e0375be349",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 4,
        "weight" : 65,
        "height" : 173,
        "laterality" : "Droitier",
        "stateForm" : "good",
        "positionType" : "left-backcourt"
    },
    "address" : {
        "formatedAddress" : "14 Allée du Thym, Plouzané, France",
        "lat" : 48.3754877000000008,
        "lng" : -4.5923733999999996,
        "place" : "14 Allée du Thym",
        "city" : "Plouzané",
        "country" : "France",
        "zipcode" : "29280"
    },
    "contact" : {
        "cellphone" : "0642718984",
        "home" : "0298050815",
        "email" : "gaelle.espinosa@free.fr"
    },
    "firstname" : "Florian",
    "name" : "Espinosa",
    "birthdate" : NumberLong(984438000000),
    "gender" : "Homme",
    "nationality" : "France",
    "birthCity" : "Brest, France",
    "sandboxId" : "561ec20b409937a6b439d4e9"
});

/* 10 */
db.SB_Person.insert({
    "_id" : "5f5f6267-7f09-48bf-9557-3f52bcd6be8f",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 3,
        "weight" : 57,
        "height" : 163,
        "laterality" : "Droitier",
        "stateForm" : "good",
        "positionType" : "pivot"
    },
    "address" : {
        "formatedAddress" : "6 Impasse du Valy, Locmaria-Plouzané, France",
        "lat" : 48.3615472999999980,
        "lng" : -4.6754585000000004,
        "place" : "6 Impasse du Valy",
        "city" : "Locmaria-Plouzané",
        "country" : "France",
        "zipcode" : "29280"
    },
    "contact" : {
        "home" : "0298382183",
        "cellphone" : "0781163147",
        "email" : "n.e.predon@aliceadsl.fr"
    },
    "firstname" : "Mathias",
    "name" : "Predon",
    "birthdate" : NumberLong(1017529200000),
    "nationality" : "France",
    "birthCity" : "Brest, France",
    "gender" : "Homme",
    "sandboxId" : "561ec20b409937a6b439d4e9"
});

/* 11 */
db.SB_Person.insert({
    "_id" : "1ce4591d-74a8-46e9-af80-d633f9344d27",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 2,
        "weight" : 63,
        "height" : 170,
        "laterality" : "Droitier",
        "stateForm" : "good",
        "positionType" : "right-wingman"
    },
    "address" : {
        "formatedAddress" : "225 Pen ar Ménez, Locmaria-Plouzané, France",
        "lat" : 48.3595380000000006,
        "lng" : -4.6379733999999999,
        "place" : "225 Route de Pen ar Ménez",
        "city" : "Locmaria-Plouzané",
        "country" : "France",
        "zipcode" : "29280"
    },
    "contact" : {
        "email" : "nathalie.l_hopital@bbox.fr",
        "cellphone" : "0659377230",
        "home" : "0983932414"
    },
    "firstname" : "Elvin",
    "name" : "L'hopital",
    "gender" : "Homme",
    "birthdate" : NumberLong(1006038000000),
    "nationality" : "France",
    "birthCity" : "Brest, France",
    "sandboxId" : "561ec20b409937a6b439d4e9"
});

/* 12 */
db.SB_Person.insert({
    "_id" : "4d24e5f6-db65-4c2a-9a62-a0309a4c15e0",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 14,
        "weight" : 70,
        "height" : 182,
        "laterality" : "Droitier",
        "stateForm" : "good",
        "positionType" : "left-backcourt"
    },
    "address" : {
        "formatedAddress" : "Languiforc'h, Locmaria-Plouzané, France",
        "lat" : 48.3483510000000010,
        "lng" : -4.6322349999999997,
        "city" : "Locmaria-Plouzané",
        "country" : "France",
        "zipcode" : "29280"
    },
    "contact" : {
        "email" : "bourge.solene@neuf.fr",
        "home" : "0298436498",
        "cellphone" : "0607568090"
    },
    "firstname" : "Vassily",
    "name" : "Lhomer",
    "gender" : "Homme",
    "birthdate" : NumberLong(991087200000),
    "nationality" : "France",
    "birthCity" : "Brest, France",
    "sandboxId" : "561ec20b409937a6b439d4e9"
});

/* 13 */
db.SB_Person.insert({
    "_id" : "7da59c29-b0c2-440f-8720-a22ed0770950",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 15,
        "weight" : 70,
        "height" : 178,
        "laterality" : "Droitier",
        "stateForm" : "good",
        "positionType" : "left-backcourt"
    },
    "address" : {
        "formatedAddress" : "33 Rue Raoul Dufy, Brest, France",
        "lat" : 48.4082823999999974,
        "lng" : -4.4512372999999998,
        "place" : "33 Rue Raoul Dufy",
        "city" : "Brest",
        "country" : "France",
        "zipcode" : "29200"
    },
    "contact" : {
        "cellphone" : "0662428314"
    },
    "firstname" : "Elouan",
    "name" : "Lesven",
    "birthdate" : NumberLong(1005260400000),
    "nationality" : "France",
    "birthCity" : "La Rochelle, France",
    "gender" : "Homme",
    "sandboxId" : "561ec20b409937a6b439d4e9"
});

/* 14 */
db.SB_Person.insert({
    "_id" : "bdb180eb-9fc9-4648-b83e-394d2e4743fc",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 16,
        "weight" : 70,
        "height" : 173,
        "laterality" : "Droitier",
        "stateForm" : "good",
        "positionType" : "goalkeeper"
    },
    "address" : {
        "formatedAddress" : "65 Rue de Brest, Plouzané, France",
        "lat" : 48.3717307999999875,
        "lng" : -4.5810651000000000,
        "place" : "65 Rue de Brest",
        "city" : "Plouzané",
        "country" : "France",
        "zipcode" : "29280"
    },
    "contact" : {
        "email" : "steph.29200@free.fr",
        "cellphone" : "0781204882",
        "home" : "0951978298"
    },
    "firstname" : "Samuel",
    "name" : "Morisset",
    "gender" : "Homme",
    "birthdate" : NumberLong(997221600000),
    "nationality" : "France",
    "birthCity" : "Brest, France",
    "sandboxId" : "561ec20b409937a6b439d4e9"
});

/* 15 */
db.SB_Person.insert({
    "_id" : "46bea3c9-a3c0-4f4e-91fc-0bd2797b48df",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 19,
        "weight" : 70,
        "height" : 173,
        "laterality" : "Droitier",
        "stateForm" : "good",
        "positionType" : "center-backcourt"
    },
    "address" : {
        "formatedAddress" : "6 impasse marie curie, Guilers, France",
        "lat" : 48.426340,
        "lng" : -4.565373,
        "place" : "6 impasse marie curie",
        "city" : "Guilers",
        "country" : "France",
        "zipcode" : "29820"
    },
    "contact" : {
        "email" : "nonoderrien@orange.fr",
        "cellphone" : "0607882976",
        "home" : "0298074029"
    },
    "firstname" : "Mathieu",
    "name" : "Derrien",
    "gender" : "Homme",
    "birthdate" : NumberLong(1018735200000),
    "nationality" : "France",
    "birthCity" : "Brest, France",
    "sandboxId" : "561ec20b409937a6b439d4e9"
});

/* 16 */
db.SB_Person.insert({
    "_id" : "cb1ad24e-6195-4c11-854f-4d7a68a43c6e",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 17,
        "weight" : 70,
        "height" : 173,
        "laterality" : "Droitier",
        "stateForm" : "good",
        "positionType" : "left-wingman"
    },
    "address" : {
        "formatedAddress" : "200 rue Django Reinhardt, Brest, France",
        "lat" : 48.4156331,
        "lng" : -4.5057746,
        "place" : "200 rue Django Reinhardt",
        "city" : "Brest",
        "country" : "France",
        "zipcode" : "29200"
    },
    "contact" : {
        "email" : "fclvz@free.fr",
        "cellphone" : "0651616148",
        "home" : "0298035698"
    },
    "firstname" : "Hugo",
    "name" : "Calvez",
    "gender" : "Homme",
    "birthdate" : NumberLong(985129200000),
    "nationality" : "France",
    "birthCity" : "Brest, France",
    "sandboxId" : "561ec20b409937a6b439d4e9"
});

/* 17 */
db.SB_Person.insert({
    "_id" : "07cfa115-6a8e-42b3-8a08-16ce1d7dbd89",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 18,
        "weight" : 70,
        "height" : 173,
        "laterality" : "Gaucher",
        "stateForm" : "good",
        "positionType" : "right-wingman"
    },
    "address" : {
        "formatedAddress" : "16 rue Notre Dame, Bourg-Blanc, France",
        "lat" : 48.5001999,
        "lng" : -4.5065255,
        "place" : "16 rue Notre Dame",
        "city" : "Bourg-Blanc",
        "country" : "France",
        "zipcode" : "29860"
    },
    "contact" : {
        "email" : "annekermarrec@live.fr",
        "cellphone" : "0650571471",
        "home" : "0298421788"
    },
    "firstname" : "Allan",
    "name" : "Kermarrec",
    "gender" : "Homme",
    "birthdate" : NumberLong(1019685600000),
    "nationality" : "France",
    "birthCity" : "Brest, France",
    "sandboxId" : "561ec20b409937a6b439d4e9"
});

/* 18 */
db.SB_Person.insert({
    "_id" : "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 20,
        "weight" : 70,
        "height" : 173,
        "laterality" : "Droitier",
        "stateForm" : "good",
        "positionType" : "pivot"
    },
    "address" : {
        "formatedAddress" : "29 rue Pierre Talcoat, Brest, France",
        "lat" : 48.4261371,
        "lng" : -4.5065255,
        "place" : "29 rue Pierre Talcoat",
        "city" : "Brest",
        "country" : "France",
        "zipcode" : "29200"
    },
    "contact" : {
        "email" : "alain.moine@yahoo.fr",
        "cellphone" : "0663200117",
        "home" : "0230018283"
    },
    "firstname" : "Alex",
    "name" : "Moine",
    "gender" : "Homme",
    "birthdate" : NumberLong(1005174000000),
    "nationality" : "France",
    "birthCity" : "Brest, France",
    "sandboxId" : "561ec20b409937a6b439d4e9"
});

/* 19 */
db.SB_Person.insert({
    "_id" : "cc5d449a-d466-400c-b73f-955c676103ed",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 21,
        "weight" : 70,
        "height" : 173,
        "laterality" : "Droitier",
        "stateForm" : "good",
        "positionType" : "left-backcourt"
    },
    "address" : {
        "formatedAddress" : "9 Rue Jean Louis Delmotte, Brest, France",
        "lat" : 48.4293424,
        "lng" : -4.4952663,
        "place" : "9 Rue Jean Louis Delmotte",
        "city" : "Brest",
        "country" : "France",
        "zipcode" : "29200"
    },
    "contact" : {
        "email" : "lcperson@free.fr",
        "cellphone" : "0660059239",
        "home" : "0973593269"
    },
    "firstname" : "Clément",
    "name" : "Person",
    "gender" : "Homme",
    "birthdate" : NumberLong(993420000000),
    "nationality" : "France",
    "birthCity" : "Brest, France",
    "sandboxId" : "561ec20b409937a6b439d4e9"
});

/* 20 */
db.SB_Person.insert({
    "_id" : "5a1c12af-0fc4-4eb8-a342-216e51bbada8",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 22,
        "weight" : 70,
        "height" : 173,
        "laterality" : "Droitier",
        "stateForm" : "good",
        "positionType" : "center-backcourt"
    },
    "address" : {
        "formatedAddress" : "30 Rue de la Paix, Saint-Renan, France",
        "lat" : 48.4273124,
        "lng" : -4.6222088,
        "place" : "30 Rue de la Paix",
        "city" : "Saint-Renan",
        "country" : "France",
        "zipcode" : "29200"
    },
    "contact" : {
        "email" : "lcperson@free.fr",
        "cellphone" : "0662497695"
    },
    "firstname" : "Malo",
    "name" : "Lurton",
    "gender" : "Homme",
    "birthdate" : NumberLong(978303600000),
    "nationality" : "France",
    "birthCity" : "Brest, France",
    "sandboxId" : "561ec20b409937a6b439d4e9"
});

/* 21 */
db.SB_Person.insert({
    "_id" : "563d9bcd6f16be326d78b70d",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 23,
        "weight" : 70,
        "height" : 173,
        "laterality" : "Droitier",
        "stateForm" : "good",
        "positionType" : "goalkeeper"
    },
    "address" : {
        "formatedAddress" : "1 Rue Duguesclin, Milizac, France",
        "lat" : 48.4613236,
        "lng" : -4.5744474,
        "place" : "1 Rue Duguesclin",
        "city" : "Milizac",
        "country" : "France",
        "zipcode" : "29290"
    },
    "contact" : {
        "email" : "sandesnoues@gmail.com",
        "cellphone" : "0621733109"
    },
    "firstname" : "Kelian",
    "name" : "Desnoues",
    "gender" : "Homme",
    "birthdate" : NumberLong(981932400000),
    "nationality" : "France",
    "birthCity" : "Brest, France",
    "sandboxId" : "561ec20b409937a6b439d4e9"
});

/* 22 */
db.SB_Person.insert({
    "_id" : "563d9f2e6f16be326d78b70e",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 24,
        "weight" : 70,
        "height" : 173,
        "laterality" : "Droitier",
        "stateForm" : "good",
        "positionType" : "left-backcourt"
    },
    "address" : {
        "formatedAddress" : "480 Chemin du Hildy, Brest, France",
        "lat" : 48.3664717,
        "lng" : -4.5379593,
        "place" : "480 Chemin du Hildy",
        "city" : "Brest",
        "country" : "France",
        "zipcode" : "29200"
    },
    "contact" : {
        "email" : "enrique_mum_gouhey@hotmail.com",
        "cellphone" : "0683336329"
    },
    "firstname" : "Esteban",
    "name" : "Gouhey",
    "gender" : "Homme",
    "birthdate" : NumberLong(1042066800000),
    "nationality" : "France",
    "birthCity" : "Brest, France",
    "sandboxId" : "561ec20b409937a6b439d4e9"
});

//////////////////////////////////////////////////////////
/*
 * Vidage de la collection SB_Effective (CKE)
 */
db.SB_Effective.remove({ "_id" : "561ec4d0409937a6b439d4ea"});

/** ************************************************************* */
/*
 * Alimentation SB_Effective (CKE)
 */
/** ************************************************************* */
db.SB_Effective.insert({
    "_id" : "561ec4d0409937a6b439d4ea",
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "label" : "CKE A",
    "categoryAge" : {
        "code" : "sen",
        "label" : "Senior Gars",
        "ageMax" : 150,
        "ageMin" : 18,
        "genre" : "Homme",
        "order" : 1
    },
    "members" : [
        {
            "personId" : "5f82c510-2c89-46b0-b87d-d3b59e748615",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "d31d3550-479a-4ee9-8304-84a132202d89",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "88afb9c2-eb10-482c-938d-34089405e65f",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "c0bf7706-f039-46c3-86b2-460930696890",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "26baf31a-f153-41b0-9e1d-c32cb9e859dd",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "8915da91-832f-419f-b5e6-a92d6828fd31",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "941f9d48-45e8-4b2f-b0ce-d33900a92fb8",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "c857c124-79c0-4b6e-8406-f89a26b8426f",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "ce18d73e-dedf-43e5-8e75-16e0375be349",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "5f5f6267-7f09-48bf-9557-3f52bcd6be8f",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "1ce4591d-74a8-46e9-af80-d633f9344d27",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "4d24e5f6-db65-4c2a-9a62-a0309a4c15e0",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "7da59c29-b0c2-440f-8720-a22ed0770950",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "bdb180eb-9fc9-4648-b83e-394d2e4743fc",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "46bea3c9-a3c0-4f4e-91fc-0bd2797b48df",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "cb1ad24e-6195-4c11-854f-4d7a68a43c6e",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "07cfa115-6a8e-42b3-8a08-16ce1d7dbd89",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "cc5d449a-d466-400c-b73f-955c676103ed",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "5a1c12af-0fc4-4eb8-a342-216e51bbada8",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "563d9bcd6f16be326d78b70d",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "563d9f2e6f16be326d78b70e",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        }
    ]
});

/** ************************************************************* */

//////////////////////////////////////////////////////////
/*
 * Vidage de la SB_Team (CKE)
 */
db.SB_Team.remove({"sandboxId" : "561ec20b409937a6b439d4e9"});

/*
 * Alimentation SB_Team (CKE)
 */
db.SB_Team.insert({
    "_id" : "937918db-848e-4a6d-8feb-a7ba6bd60f5a",
    "label" : "Avenir Du Ponant 1",
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "effectiveId" : "561ec4d0409937a6b439d4ea",
    "enable" : true,
    "adversary" : false
});

db.SB_Team.insert({
    "_id" : "5a931b17-63c5-4b1a-be6e-2e5ed8521059",
    "label" : "Ploemeur Atlantique Hb",
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "effectiveId" : "561ec4d0409937a6b439d4ea",
    "linkTeamId" : ["937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "enable" : true,
    "adversary": true
});

db.SB_Team.insert({
    "_id" : "562ba2a5b70fd108e375395c",
    "label" : "Guingamp Hb",
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "effectiveId" : "561ec4d0409937a6b439d4ea",
    "linkTeamId" : ["937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "enable" : true,
    "adversary": true
});

db.SB_Team.insert({
    "_id" : "562ba2a5b70fd108e375395d",
    "label" : "Hb Pays De Vannes",
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "effectiveId" : "561ec4d0409937a6b439d4ea",
    "linkTeamId" : ["937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "enable" : true,
    "adversary": true
});

db.SB_Team.insert({
    "_id" : "562ba2d4b70fd108e375395e",
    "label" : "Golfe 56",
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "effectiveId" : "561ec4d0409937a6b439d4ea",
    "linkTeamId" : ["937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "enable" : true,
    "adversary": true
});

db.SB_Team.insert({
    "_id" : "562ba2e3b70fd108e375395f",
    "label" : "Cesson Rennes Métropole Hb",
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "effectiveId" : "561ec4d0409937a6b439d4ea",
    "linkTeamId" : ["937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "enable" : true,
    "adversary": true
});

db.SB_Team.insert({
    "_id" : "562ba300b70fd108e3753960",
    "label" : "Cercle Paul Bert Rennes Hb",
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "effectiveId" : "561ec4d0409937a6b439d4ea",
    "linkTeamId" : ["937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "enable" : true,
    "adversary": true
});

db.SB_Team.insert({
    "_id" : "562ba318b70fd108e3753961",
    "label" : "Rennes Métropole Hb",
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "effectiveId" : "561ec4d0409937a6b439d4ea",
    "linkTeamId" : ["937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "enable" : true,
    "adversary": true
});

db.SB_Team.insert({
    "_id" : "562ba335b70fd108e3753962",
    "label" : "Stiren Languidic",
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "effectiveId" : "561ec4d0409937a6b439d4ea",
    "linkTeamId" : ["937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "enable" : true,
    "adversary": true
});

db.SB_Team.insert({
    "_id" : "562ba34db70fd108e3753963",
    "label" : "Concarneau Hb",
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "effectiveId" : "561ec4d0409937a6b439d4ea",
    "linkTeamId" : ["937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "enable" : true,
    "adversary": true
});

db.SB_Team.insert({
    "_id" : "562ba368b70fd108e3753964",
    "label" : "Lesneven Hb",
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "effectiveId" : "561ec4d0409937a6b439d4ea",
    "linkTeamId" : ["937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "enable" : true,
    "adversary": true
});

db.SB_Team.insert({
    "_id" : "562ba37eb70fd108e3753965",
    "label" : "Hbc Plerin",
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "effectiveId" : "561ec4d0409937a6b439d4ea",
    "linkTeamId" : ["937918db-848e-4a6d-8feb-a7ba6bd60f5a"],
    "enable" : true,
    "adversary": true
});

/*
 * Alimentation collection SB_Team : CKE
 */

db.SB_Event.remove({ "owner.sandboxId" : "561ec20b409937a6b439d4e9"});

db.SB_Event.insert({"_id" : "e254897f-cf3a-48b8-bed5-a4d4664ab4a4",
    "activityId" : "ACT-HAND",
    "owner" : {
        "sandboxId" : "561ec20b409937a6b439d4e9",
        "effectiveId" : "561ec4d0409937a6b439d4ea",
        "teamId" : "937918db-848e-4a6d-8feb-a7ba6bd60f5a"
    },
    "address" : {
        "formatedAddress" : "Guilers, France",
        "lat" : 48.4248430000000027,
        "lng" : -4.5582410000000007,
        "city" : "Guilers",
        "country" : "France"
    },
    "link" : {
        "type" : "championship"
    },
    "label" : "Journée 5",
    "startDate" : NumberLong(1446305400000),
    "participants" : {
        "teamHome" : {
            "id" : "937918db-848e-4a6d-8feb-a7ba6bd60f5a",
            "label" : "Locmaria Hb 1"
        },
        "teamVisitor" : {
            "id" : "5a931b17-63c5-4b1a-be6e-2e5ed8521059",
            "label" : "Ploemeur Atlantique Hb"
        }
    }
});

db.SB_Event.insert({
    "_id" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
    "activityId" : "ACT-HAND",
    "owner" : {
        "sandboxId" : "561ec20b409937a6b439d4e9",
        "effectiveId" : "561ec4d0409937a6b439d4ea",
        "teamId" : "937918db-848e-4a6d-8feb-a7ba6bd60f5a"
    },
    "address" : {
        "formatedAddress" : "Avenue Paul Cézanne, Vannes, France",
        "lat" : 47.6741354999999984,
        "lng" : -2.7696065000000001,
        "place" : "Avenue Paul Cézanne",
        "city" : "Vannes",
        "country" : "France",
        "zipcode" : "56000"
    },
    "link" : {
        "type" : "championship"
    },
    "label" : "Journée 6",
    "startDate" : NumberLong(1446912000000),
    "participants" : {
        "teamVisitor" : {
            "id" : "937918db-848e-4a6d-8feb-a7ba6bd60f5a",
            "label" : "Avenir Du Ponant 1"
        },
        "teamHome" : {
            "id" : "562ba2d4b70fd108e375395e",
            "label" : "Golfe 56"
        }
    }
});

db.SB_Event.insert({
    "_id" : "0ca48ff3-ea32-4ea6-bad5-e8619d495f91",
    "activityId" : "ACT-HAND",
    "owner" : {
        "sandboxId" : "561ec20b409937a6b439d4e9",
        "effectiveId" : "561ec4d0409937a6b439d4ea",
        "teamId" : "937918db-848e-4a6d-8feb-a7ba6bd60f5a"
    },
    "address" : {
        "formatedAddress" : "Route de Pen ar Ménez, Locmaria-Plouzané, France",
        "lat" : 48.3665238999999971,
        "lng" : -4.6422292999999986,
        "place" : "undefinedRoute de Pen ar Ménez",
        "city" : "Locmaria-Plouzané",
        "country" : "France",
        "zipcode" : "29280"
    },
    "link" : {
        "type" : "championship"
    },
    "label" : "Journée 7",
    "startDate" : NumberLong(1447518600000),
    "participants" : {
        "teamHome" : {
            "id" : "937918db-848e-4a6d-8feb-a7ba6bd60f5a",
            "label" : "Avenir Du Ponant 1"
        },
        "teamVisitor" : {
            "id" : "562ba2e3b70fd108e375395f",
            "label" : "Cesson Rennes Métropole Hb"
        }
    }
});

db.SB_Event.insert({
    "_id" : "311bb254-dc16-40e0-a86d-b74b9469c039",
    "activityId" : "ACT-HAND",
    "owner" : {
        "sandboxId" : "561ec20b409937a6b439d4e9",
        "effectiveId" : "561ec4d0409937a6b439d4ea",
        "teamId" : "937918db-848e-4a6d-8feb-a7ba6bd60f5a"
    },
    "address" : {
        "formatedAddress" : "Rue Saint-Vincent de Paul, Brest, France",
        "lat" : 47.1395479999999978,
        "lng" : -1.3374013000000000,
        "place" : "undefinedRue Saint-Vincent",
        "city" : "Le Pallet",
        "country" : "France",
        "zipcode" : "44330"
    },
    "link" : {
        "type" : "championship"
    },
    "label" : "Journée 8",
    "startDate" : NumberLong(1448123400000),
    "participants" : {
        "teamHome" : {
            "id" : "937918db-848e-4a6d-8feb-a7ba6bd60f5a",
            "label" : "Avenir Du Ponant 1"
        },
        "teamVisitor" : {
            "id" : "562ba300b70fd108e3753960",
            "label" : "Cercle Paul Bert Rennes Hb"
        }
    }
});

db.SB_Event.insert({
    "_id" : "5c551ebf-b4ca-45ed-b351-75af2c5838d8",
    "activityId" : "ACT-HAND",
    "owner" : {
        "sandboxId" : "561ec20b409937a6b439d4e9",
        "effectiveId" : "561ec4d0409937a6b439d4ea",
        "teamId" : "937918db-848e-4a6d-8feb-a7ba6bd60f5a"
    },
    "address" : {
        "formatedAddress" : "Le Got, Route de Brest, Le Folgoët, France",
        "lat" : 48.5563788999999986,
        "lng" : -4.3416321000000000,
        "place" : "undefinedRoute de Brest",
        "city" : "Le Folgoët",
        "country" : "France",
        "zipcode" : "29260"
    },
    "link" : {
        "type" : "championship"
    },
    "label" : "Journée 9",
    "startDate" : NumberLong(1449337500000),
    "participants" : {
        "teamVisitor" : {
            "id" : "937918db-848e-4a6d-8feb-a7ba6bd60f5a",
            "label" : "Avenir Du Ponant 1"
        },
        "teamHome" : {
            "id" : "562ba368b70fd108e3753964",
            "label" : "Lesneven Hb"
        }
    }
});

db.SB_Event.insert({
    "_id" : "35d65151-2fe5-48e1-a219-8534412b6bca",
    "activityId" : "ACT-HAND",
    "owner" : {
        "sandboxId" : "561ec20b409937a6b439d4e9",
        "effectiveId" : "561ec4d0409937a6b439d4ea",
        "teamId" : "937918db-848e-4a6d-8feb-a7ba6bd60f5a"
    },
    "address" : {},
    "link" : {
        "type" : "championship"
    },
    "label" : "Journée 10",
    "startDate" : NumberLong(1449934200000),
    "participants" : {
        "teamHome" : {
            "id" : "937918db-848e-4a6d-8feb-a7ba6bd60f5a",
            "label" : "Avenir Du Ponant 1"
        },
        "teamVisitor" : {
            "id" : "562ba335b70fd108e3753962",
            "label" : "Stiren Languidic"
        }
    }
});

/*
 * Alimentation collection SB_Collecte : CKE
 */
db.SB_Collecte.insert({"_id" : "56410c65446d5807c2732e9d",
    "status" : "done",
    "eventRef" : {
        "_id" : "59bb6da2-b9d2-4453-856d-52a0fb98eac1",
        "activityId" : "ACT-HAND",
        "owner" : {
            "sandboxId" : "561ec20b409937a6b439d4e9",
            "effectiveId" : "561ec4d0409937a6b439d4ea",
            "teamId" : "937918db-848e-4a6d-8feb-a7ba6bd60f5a"
        },
        "address" : {
            "formatedAddress" : "Avenue Paul Cézanne, Vannes, France",
            "lat" : 47.6741354999999984,
            "lng" : -2.7696065000000001,
            "place" : "Avenue Paul Cézanne",
            "city" : "Vannes",
            "country" : "France",
            "zipcode" : "56000"
        },
        "link" : {
            "type" : "championship"
        },
        "label" : "Journée 6",
        "startDate" : NumberLong(1446912000000),
        "participants" : {
            "teamVisitor" : {
                "id" : "937918db-848e-4a6d-8feb-a7ba6bd60f5a",
                "label" : "Avenir Du Ponant 1"
            },
            "teamHome" : {
                "id" : "562ba2d4b70fd108e375395e",
                "label" : "Golfe 56"
            }
        }
    },
    "startDate" : NumberLong(1446912000000),
    "endDate" : NumberLong(1446916810000),
    "players" : [
        "d31d3550-479a-4ee9-8304-84a132202d89",
        "c0bf7706-f039-46c3-86b2-460930696890",
        "26baf31a-f153-41b0-9e1d-c32cb9e859dd",
        "941f9d48-45e8-4b2f-b0ce-d33900a92fb8",
        "c857c124-79c0-4b6e-8406-f89a26b8426f",
        "ce18d73e-dedf-43e5-8e75-16e0375be349",
        "1ce4591d-74a8-46e9-af80-d633f9344d27",
        "7da59c29-b0c2-440f-8720-a22ed0770950",
        "cb1ad24e-6195-4c11-854f-4d7a68a43c6e",
        "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd",
        "5f82c510-2c89-46b0-b87d-d3b59e748615",
        "cc5d449a-d466-400c-b73f-955c676103ed"
    ],
    "observers" : [{
        "userId" : "b50b3325-fdbd-41bf-bda4-81c827982001",
        "indicators" : ["all"]
    }],
    "parametersGame": {
        "nbMaxPlayers" : 12,
        "nbMinPlayers" : 4,
        "nbPeriod" : 2,
        "periodDuration" : 1500,
        "nbTimeout" : 3,
        "timeoutDuration" : 60,
        "yellowCardMax": 1,
        "exclusionTempo": 3,
        "halfTimeDuration": 600
    }
});

/*
 * Alimentation collection SB_Collecte : CKE
 */
db.SB_Collecte.insert({"_id" : "565e0f0dbcda594d193e24db",
    "status" : "done",
    "eventRef" : {
        "_id" : "35d65151-2fe5-48e1-a219-8534412b6bca",
        "activityId" : "ACT-HAND",
        "owner" : {
            "sandboxId" : "561ec20b409937a6b439d4e9",
            "effectiveId" : "561ec4d0409937a6b439d4ea",
            "teamId" : "937918db-848e-4a6d-8feb-a7ba6bd60f5a"
        },
        "address" : {},
        "link" : {
            "type" : "championship"
        },
        "label" : "Journée 10",
        "startDate" : NumberLong(1449934200000),
        "participants" : {
            "teamHome" : {
                "id" : "937918db-848e-4a6d-8feb-a7ba6bd60f5a",
                "label" : "Avenir Du Ponant 1"
            },
            "teamVisitor" : {
                "id" : "562ba335b70fd108e3753962",
                "label" : "Stiren Languidic"
            }
        }
    },
    "startDate" : NumberLong(1448491800000),
    "endDate" : NumberLong(1448492500000),
    "players" : [
        "d31d3550-479a-4ee9-8304-84a132202d89",
        "c0bf7706-f039-46c3-86b2-460930696890",
        "26baf31a-f153-41b0-9e1d-c32cb9e859dd",
        "941f9d48-45e8-4b2f-b0ce-d33900a92fb8",
        "c857c124-79c0-4b6e-8406-f89a26b8426f",
        "ce18d73e-dedf-43e5-8e75-16e0375be349",
        "1ce4591d-74a8-46e9-af80-d633f9344d27",
        "7da59c29-b0c2-440f-8720-a22ed0770950",
        "cb1ad24e-6195-4c11-854f-4d7a68a43c6e",
        "43e62ae5-2a92-4e1a-9b9a-d1a399c096bd",
        "5f82c510-2c89-46b0-b87d-d3b59e748615",
        "cc5d449a-d466-400c-b73f-955c676103ed"
    ],
    "observers" : [{
        "userId" : "b50b3325-fdbd-41bf-bda4-81c827982001",
        "indicators" : ["all"]
    }],
    "parametersGame": {
        "nbMaxPlayers" : 12,
        "nbMinPlayers" : 4,
        "nbPeriod" : 2,
        "periodDuration" : 1500,
        "nbTimeout" : 3,
        "timeoutDuration" : 60,
        "yellowCardMax": 1,
        "exclusionTempo": 3,
        "halfTimeDuration": 600
    }
});



/*
 * Vidage de la collection User qaobee
 */

db.User.remove({"_id": "b50b3325-fdbd-41bf-bda4-81c827982003"});

/*
 * Alimentation collection USER : FIS
 */
db.User.insert({
    "_id" : "b50b3325-fdbd-41bf-bda4-81c827982003",
    "avatar" : null,
    "name" : "Isoard",
    "firstname" : "Florent",
    "address" : null,
    "birthdate" : 0,
    "contact" : {
        "home" : null,
        "office" : null,
        "cellphone" : null,
        "email" : "florent.isoard@qaobee.com",
        "fax" : null,
        "webSite" : null,
        "facebook" : null,
        "googlePlus" : null,
        "twitter" : null
    },
    "country" : null,
    "nationality" : null,
    "account" : {
        "_id" : null,
        "activationCode" : "9443356eb2e6462596243ee8a58d3f56",
        "activationPasswd" : null,
        "active" : true,
        "expirationDate" : 0,
        "firstConnexion" : false,
        "login" : "fis-qaobee",
        "passwd" : null,
        "password" : "QrHqnRQwrJ3zwCw7dalpfH5rkoE=",
        "salt" : "MkgZzvYT+d8=",
        "timestamp" : 0,
        "token" : null,
        "tokenRenewDate" : 0,
        "listPlan" : [
            {
                "paymentId" : "d9248a81-2e4c-49f6-85d4-afc1599c5669",
                "levelPlan" : "FREEMIUM",
                "amountPaid" : 9,
                "paidDate" : 0,
                "startPeriodDate" : NumberLong(1466590012943),
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
            }
        ],
        "habilitations" : null,
        "mobileToken" : null
    },
    "timestamp" : NumberLong(1444854317672),
    "gender" : "gender.male",
    "sandboxDefault" : "562026b8758f1cc6a4753307"
});

/*
 * Vidage de la collection SB_SandBox FIS
 */
db.SB_SandBox.remove({"_id": "562026b8758f1cc6a4753307"});

/***************************************************************
 * Alimentation SB_SandBox FIS
 * *************************************************************
 */
db.SB_SandBox.insert({
    "_id": "562026b8758f1cc6a4753307",
    "activity": {
        "_id": "ACT-HAND",
        "code": "ACT-HAND",
        "label": "admin.settings.activity.handball.label",
        "enable": true,
        "activityType": "TEAM_SPORT"
    },
    "owner": "b50b3325-fdbd-41bf-bda4-81c827982003",
    "structure": {
        "_id": "5620281c758f1cc6a475330c",
        "label": "CLUB FIS HB",
        "acronym": "FISHB",
        "activity": {
            "_id": "ACT-HAND",
            "code": "ACT-HAND",
            "label": "admin.settings.activity.handball.label",
            "activated": true,
            "activityType": "TEAM_SPORT"
        },
        "country": {"_id": "CNTR-250-FR-FRA", "codeOSCE": NumberInt(250), "label": "France", "local": "fr"},
        "avatar": null
    },
    "members": [],
    "effectiveDefault" : "56202720758f1cc6a4753309"
});

/*
 * Vidage de la collection SB_Person FIS
 */
db.SB_Person.remove({"sandboxId":"562026b8758f1cc6a4753307"});

/* 1 */
db.SB_Person.insert({
    "_id" : "56608a20c630d9b391c0c4e0",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 11,
        "weight" : 60,
        "height" : 175,
        "laterality" : "Droitier",
        "stateForm" : "good",
        "positionType" : "left-backcourt"
    },
    "address" : {
        "formatedAddress" : "45 Allée Parc ar Groas, Plouzané, France",
        "lat" : 48.3771823000000012,
        "lng" : -4.6230722000000002,
        "place" : "45 Allée Parc ar Groas",
        "city" : "Plouzané",
        "country" : "France",
        "zipcode" : "29280"
    },
    "contact" : {},
    "firstname" : "Tristan",
    "name" : "Kervella",
    "birthdate" : NumberLong(1014332400000),
    "gender" : "Homme",
    "nationality" : "France",
    "birthCity" : "Plouzané, France",
    "sandboxId" : "562026b8758f1cc6a4753307"
});

/* 2 */
db.SB_Person.insert({
    "_id" : "56608a6ac630d9b391c0c4e1",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 10,
        "weight" : 55,
        "height" : 155,
        "laterality" : "Droitier",
        "stateForm" : "good",
        "positionType" : "pivot"
    },
    "address" : {
        "formatedAddress" : "8 Hameau de Ker Yan, Locmaria-Plouzané, France",
        "lat" : 48.3736687999999972,
        "lng" : -4.6507980000000000,
        "place" : "8 Hameau de Ker Yan",
        "city" : "Locmaria-Plouzané",
        "country" : "France",
        "zipcode" : "29280"
    },
    "contact" : {
        "cellphone" : "0698997229",
        "home" : "0298489077",
        "email" : "patrick.fleury@laposte.net"
    },
    "firstname" : "Thibaut",
    "name" : "Fleury",
    "birthdate" : NumberLong(1015023600000),
    "nationality" : "France",
    "birthCity" : "Brest, France",
    "gender" : "Homme",
    "sandboxId" : "562026b8758f1cc6a4753307"
});

/* 3 */
db.SB_Person.insert({
    "_id" : "56608a72c630d9b391c0c4e2",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 9,
        "weight" : 50,
        "height" : 155,
        "laterality" : "Droitier",
        "stateForm" : "good",
        "positionType" : "left-wingman"
    },
    "address" : {
        "formatedAddress" : "8 Rue Courlis, Locmaria-Plouzané, France",
        "lat" : 48.3698181000000034,
        "lng" : -4.6586223999999996,
        "place" : "8 Rue Courlis",
        "city" : "Locmaria-Plouzané",
        "country" : "France",
        "zipcode" : "29280"
    },
    "contact" : {
        "email" : "katdomclo@gmail.com",
        "home" : "0298382324"
    },
    "firstname" : "Victor",
    "name" : "Clorennec",
    "gender" : "Homme",
    "birthdate" : NumberLong(1034546400000),
    "nationality" : "France",
    "birthCity" : "Senlis, France",
    "sandboxId" : "562026b8758f1cc6a4753307"
});

/* 4 */
db.SB_Person.insert({
    "_id" : "56608a7ec630d9b391c0c4e3",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 8,
        "weight" : 60,
        "height" : 165,
        "laterality" : "Gaucher",
        "stateForm" : "good",
        "positionType" : "right-wingman"
    },
    "address" : {
        "formatedAddress" : "3 Route de Kerfily, Locmaria-Plouzané, France",
        "lat" : 48.3749360999999993,
        "lng" : -4.6458358000000004,
        "place" : "3 Route de Kerfily",
        "city" : "Locmaria-Plouzané",
        "country" : "France",
        "zipcode" : "29280"
    },
    "contact" : {
        "home" : "0298485392",
        "email" : "gbergot@laposte.net"
    },
    "firstname" : "Mathys",
    "name" : "Bergot",
    "gender" : "Homme",
    "birthdate" : NumberLong(1037314800000),
    "nationality" : "France",
    "birthCity" : "Brest, France",
    "sandboxId" : "562026b8758f1cc6a4753307"
});

/* 5 */
db.SB_Person.insert({
    "_id" : "56608a88c630d9b391c0c4e4",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 12,
        "weight" : 65,
        "height" : 175,
        "laterality" : "Droitier",
        "stateForm" : "good",
        "positionType" : "goalkeeper"
    },
    "address" : {
        "formatedAddress" : "1 Rue Duperré, Brest, France",
        "lat" : 48.3961918999999980,
        "lng" : -4.4837622000000001,
        "place" : "1 Rue Duperré",
        "city" : "Brest",
        "country" : "France",
        "zipcode" : "29200"
    },
    "contact" : {
        "home" : "0298430448"
    },
    "firstname" : "Ewenn",
    "name" : "Scoarnec",
    "birthdate" : NumberLong(1017183600000),
    "nationality" : "France",
    "birthCity" : "Bordeaux, France",
    "sandboxId" : "562026b8758f1cc6a4753307"
});

/* 6 */
db.SB_Person.insert({
    "_id" : "56608a88c630d9b391c0c4e5",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 6,
        "weight" : 65,
        "height" : 178,
        "laterality" : "Droitier",
        "stateForm" : "good",
        "positionType" : "right-backcourt"
    },
    "address" : {
        "formatedAddress" : "34 Rue Albert Louppe, Brest, France",
        "lat" : 48.4034297999999978,
        "lng" : -4.4710770000000002,
        "place" : "34 Rue Albert Louppe",
        "city" : "Brest",
        "country" : "France",
        "zipcode" : "29200"
    },
    "contact" : {
        "cellphone" : "0611857269",
        "home" : "0256294305"
    },
    "firstname" : "Clement",
    "name" : "Pellen",
    "gender" : "Homme",
    "birthdate" : NumberLong(1019340000000),
    "nationality" : "France",
    "birthCity" : "Brest, France",
    "sandboxId" : "562026b8758f1cc6a4753307"
});

/* 7 */
db.SB_Person.insert({
    "_id" : "56608a9cc630d9b391c0c4e6",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 5,
        "weight" : 65,
        "height" : 164,
        "laterality" : "Droitier",
        "stateForm" : "good",
        "positionType" : "center-backcourt"
    },
    "address" : {
        "formatedAddress" : "275 Rue des Iris, Plouzané, France",
        "lat" : 48.3700189999999992,
        "lng" : -4.5799702000000000,
        "place" : "275 Rue des Iris",
        "city" : "Plouzané",
        "country" : "France",
        "zipcode" : "29280"
    },
    "contact" : {
        "email" : "frank.charrault@hotmail.fr",
        "home" : "0298037320",
        "cellphone" : "0760970956"
    },
    "firstname" : "Hugo",
    "name" : "Charrault",
    "gender" : "Homme",
    "birthdate" : NumberLong(1003269600000),
    "nationality" : "France",
    "birthCity" : "Brest, France",
    "sandboxId" : "562026b8758f1cc6a4753307"
});

/* 8 */
db.SB_Person.insert({
    "_id" : "56608a9cc630d9b391c0c4e7",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 1,
        "weight" : 67,
        "height" : 177,
        "laterality" : "Droitier",
        "stateForm" : "good",
        "positionType" : "goalkeeper"
    },
    "address" : {
        "formatedAddress" : "275 Rue des Iris, Plouzané, France",
        "lat" : 48.3700189999999992,
        "lng" : -4.5799702000000000,
        "place" : "275 Rue des Iris",
        "city" : "Plouzané",
        "country" : "France",
        "zipcode" : "29280"
    },
    "contact" : {
        "cellphone" : "0760970956",
        "home" : "0298037320",
        "email" : "frank.charrault@hotmail.fr"
    },
    "firstname" : "Tom",
    "name" : "Charrault",
    "gender" : "Homme",
    "birthdate" : NumberLong(1034805600000),
    "nationality" : "France",
    "birthCity" : "Brest, France",
    "sandboxId" : "562026b8758f1cc6a4753307"
});

/* 9 */
db.SB_Person.insert({
    "_id" : "56608a9cc630d9b391c0c4e8",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 4,
        "weight" : 65,
        "height" : 173,
        "laterality" : "Droitier",
        "stateForm" : "good",
        "positionType" : "left-backcourt"
    },
    "address" : {
        "formatedAddress" : "14 Allée du Thym, Plouzané, France",
        "lat" : 48.3754877000000008,
        "lng" : -4.5923733999999996,
        "place" : "14 Allée du Thym",
        "city" : "Plouzané",
        "country" : "France",
        "zipcode" : "29280"
    },
    "contact" : {
        "cellphone" : "0642718984",
        "home" : "0298050815",
        "email" : "gaelle.espinosa@free.fr"
    },
    "firstname" : "Florian",
    "name" : "Espinosa",
    "birthdate" : NumberLong(984438000000),
    "gender" : "Homme",
    "nationality" : "France",
    "birthCity" : "Brest, France",
    "sandboxId" : "562026b8758f1cc6a4753307"
});

/* 10 */
db.SB_Person.insert({
    "_id" : "56608ab7c630d9b391c0c4e9",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 3,
        "weight" : 57,
        "height" : 163,
        "laterality" : "Droitier",
        "stateForm" : "good",
        "positionType" : "pivot"
    },
    "address" : {
        "formatedAddress" : "6 Impasse du Valy, Locmaria-Plouzané, France",
        "lat" : 48.3615472999999980,
        "lng" : -4.6754585000000004,
        "place" : "6 Impasse du Valy",
        "city" : "Locmaria-Plouzané",
        "country" : "France",
        "zipcode" : "29280"
    },
    "contact" : {
        "home" : "0298382183",
        "cellphone" : "0781163147",
        "email" : "n.e.predon@aliceadsl.fr"
    },
    "firstname" : "Mathias",
    "name" : "Predon",
    "birthdate" : NumberLong(1017529200000),
    "nationality" : "France",
    "birthCity" : "Brest, France",
    "gender" : "Homme",
    "sandboxId" : "562026b8758f1cc6a4753307"
});

/* 11 */
db.SB_Person.insert({
    "_id" : "56608ac7c630d9b391c0c4ea",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 2,
        "weight" : 63,
        "height" : 170,
        "laterality" : "Droitier",
        "stateForm" : "good",
        "positionType" : "right-wingman"
    },
    "address" : {
        "formatedAddress" : "225 Pen ar Ménez, Locmaria-Plouzané, France",
        "lat" : 48.3595380000000006,
        "lng" : -4.6379733999999999,
        "place" : "225 Route de Pen ar Ménez",
        "city" : "Locmaria-Plouzané",
        "country" : "France",
        "zipcode" : "29280"
    },
    "contact" : {
        "email" : "nathalie.l_hopital@bbox.fr",
        "cellphone" : "0659377230",
        "home" : "0983932414"
    },
    "firstname" : "Elvin",
    "name" : "L'hopital",
    "gender" : "Homme",
    "birthdate" : NumberLong(1006038000000),
    "nationality" : "France",
    "birthCity" : "Brest, France",
    "sandboxId" : "562026b8758f1cc6a4753307"
});

/* 12 */
db.SB_Person.insert({
    "_id" : "56608ad3c630d9b391c0c4eb",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 14,
        "weight" : 70,
        "height" : 182,
        "laterality" : "Droitier",
        "stateForm" : "good",
        "positionType" : "left-backcourt"
    },
    "address" : {
        "formatedAddress" : "Languiforc'h, Locmaria-Plouzané, France",
        "lat" : 48.3483510000000010,
        "lng" : -4.6322349999999997,
        "city" : "Locmaria-Plouzané",
        "country" : "France",
        "zipcode" : "29280"
    },
    "contact" : {
        "email" : "bourge.solene@neuf.fr",
        "home" : "0298436498",
        "cellphone" : "0607568090"
    },
    "firstname" : "Vassily",
    "name" : "Lhomer",
    "gender" : "Homme",
    "birthdate" : NumberLong(991087200000),
    "nationality" : "France",
    "birthCity" : "Brest, France",
    "sandboxId" : "562026b8758f1cc6a4753307"
});

//////////////////////////////////////////////////////////
/*
 * Vidage de la collection SB_Effective (FIS)
 */
db.SB_Effective.remove({ "_id" : "56202720758f1cc6a4753309"});

/** ************************************************************* */
/*
 * Alimentation SB_Effective (FIS)
 */
/** ************************************************************* */
db.SB_Effective.insert({
    "_id" : "56202720758f1cc6a4753309",
    "sandboxId" : "562026b8758f1cc6a4753307",
    "label": "FIS A",
    "categoryAge" : {
        "code" : "sen",
        "label" : "Senior Gars",
        "ageMax" : NumberInt(150),
        "ageMin" : NumberInt(18),
        "genre" : "Homme",
        "order" : NumberInt(1)
    },
    "members" : [
        {
            "personId" : "56608a20c630d9b391c0c4e0",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "56608a6ac630d9b391c0c4e1",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "56608a72c630d9b391c0c4e2",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "56608a7ec630d9b391c0c4e3",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "56608a88c630d9b391c0c4e4",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "56608a88c630d9b391c0c4e5",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "56608a9cc630d9b391c0c4e6",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "56608a9cc630d9b391c0c4e7",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "56608a9cc630d9b391c0c4e8",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "56608ab7c630d9b391c0c4e9",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "56608ac7c630d9b391c0c4ea",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        },
        {
            "personId" : "56608ad3c630d9b391c0c4eb",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        }
    ]
});

//////////////////////////////////////////////////////////
/*
 * Vidage de la collection SB_Team (FIS)
 */
db.SB_Team.remove({"sandboxId" : "562026b8758f1cc6a4753307"});

/** ************************************************************* */
/*
 * Alimentation SB_Team (FIS)
 */
/** ************************************************************* */
/* 1 */
db.SB_Team.insert({
    "_id" : "b5892558-6324-4c35-80c0-4dc118ab7cf0",
    "sandboxId" : "562026b8758f1cc6a4753307",
    "effectiveId" : "56202720758f1cc6a4753309",
    "adversary" : false,
    "enable" : true,
    "label" : "Sénior A"
});

/* 2 */
db.SB_Team.insert({
    "_id" : "a66df019-ea7a-47e8-9eec-c67fd399dbb9",
    "adversary" : true,
    "effectiveId" : "56202720758f1cc6a4753309",
    "enable" : true,
    "label" : "Rennes",
    "linkTeamId" : [
        "b5892558-6324-4c35-80c0-4dc118ab7cf0"
    ],
    "sandboxId" : "562026b8758f1cc6a4753307"
});

/* 3 */
db.SB_Team.insert({
    "_id" : "72df19c9-9b4d-4d5a-85da-6fb2fc70b996",
    "sandboxId" : "562026b8758f1cc6a4753307",
    "effectiveId" : "56202720758f1cc6a4753309",
    "adversary" : false,
    "enable" : true,
    "label" : "Senior Gars 2"
});

/* 4 */
db.SB_Team.insert({
    "_id" : "43571820-41e4-4231-bebf-5a17c659cd6e",
    "adversary" : true,
    "effectiveId" : "56202720758f1cc6a4753309",
    "enable" : true,
    "label" : "Paris",
    "linkTeamId" : [
        "b5892558-6324-4c35-80c0-4dc118ab7cf0",
        "72df19c9-9b4d-4d5a-85da-6fb2fc70b996"
    ],
    "sandboxId" : "562026b8758f1cc6a4753307"
});

/* 5 */
db.SB_Team.insert({
    "_id" : "5556abd0-9fc5-4eab-ad6f-5a3cf7c1df4c",
    "adversary" : true,
    "checked" : true,
    "effectiveId" : "56202720758f1cc6a4753309",
    "enable" : true,
    "label" : "Chambéry",
    "linkTeamId" : [
        "b5892558-6324-4c35-80c0-4dc118ab7cf0",
        "72df19c9-9b4d-4d5a-85da-6fb2fc70b996"
    ],
    "modified" : true,
    "sandboxId" : "562026b8758f1cc6a4753307"
});

//////////////////////////////////////////////////////////
/*
 * Vidage de la collection SB_Event (FIS)
 */
db.SB_Event.remove({"owner.sandboxId" : "562026b8758f1cc6a4753307"});

/** ************************************************************* */
/*
 * Alimentation SB_Event (FIS)
 */
/** ************************************************************* */
db.SB_Event.insert({
    "_id" : "5660c53ac630d9b391c0c4ec",
    "activityId" : "ACT-HAND",
    "owner" : {
        "sandboxId" : "562026b8758f1cc6a4753307",
        "effectiveId" : "56202720758f1cc6a4753309",
        "teamId" : "b5892558-6324-4c35-80c0-4dc118ab7cf0"
    },
    "address" : {},
    "link" : {
        "type" : "championship"
    },
    "label" : "Journée 10",
    "startDate" : NumberLong(1449934200000),
    "participants" : {
        "teamHome" : {
            "id" : "b5892558-6324-4c35-80c0-4dc118ab7cf0",
            "label" : "Sénior A"
        },
        "teamVisitor" : {
            "id" : "43571820-41e4-4231-bebf-5a17c659cd6e",
            "label" : "Paris"
        }
    }
});

db.SB_Event.insert({
    "_id" : "5660c695c630d9b391c0c4ed",
    "activityId" : "ACT-HAND",
    "owner" : {
        "sandboxId" : "562026b8758f1cc6a4753307",
        "effectiveId" : "56202720758f1cc6a4753309",
        "teamId" : "b5892558-6324-4c35-80c0-4dc118ab7cf0"
    },
    "address" : {},
    "link" : {
        "type" : "championship"
    },
    "label" : "Journée 11",
    "startDate" : NumberLong(1450539000000),
    "participants" : {
        "teamHome" : {
            "id" : "b5892558-6324-4c35-80c0-4dc118ab7cf0",
            "label" : "Sénior A"
        },
        "teamVisitor" : {
            "id" : "a66df019-ea7a-47e8-9eec-c67fd399dbb9",
            "label" : "Rennes"
        }
    }
});

db.SB_Event.insert({
    "_id" : "5660c6a1c630d9b391c0c4ee",
    "activityId" : "ACT-HAND",
    "owner" : {
        "sandboxId" : "562026b8758f1cc6a4753307",
        "effectiveId" : "56202720758f1cc6a4753309",
        "teamId" : "b5892558-6324-4c35-80c0-4dc118ab7cf0"
    },
    "address" : {},
    "link" : {
        "type" : "cup"
    },
    "label" : "Coupe de France",
    "startDate" : NumberLong(1452353400000),
    "participants" : {
        "teamHome" : {
            "id" : "b5892558-6324-4c35-80c0-4dc118ab7cf0",
            "label" : "Sénior A"
        },
        "teamVisitor" : {
            "id" : "5556abd0-9fc5-4eab-ad6f-5a3cf7c1df4c",
            "label" : "Chambéry"
        }
    }
});



