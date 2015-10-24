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
        "$in": ["b50b3325-fdbd-41bf-bda4-81c827982001",
                "b50b3325-fdbd-41bf-bda4-81c827982002",
                "b50b3325-fdbd-41bf-bda4-81c827982003",
                "b50b3325-fdbd-41bf-bda4-81c827982004",
                "b50b3325-fdbd-41bf-bda4-81c827982005",
                "b50b3325-fdbd-41bf-bda4-81c827982006"
               ]
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
    "address" : null,
    "birthdate" : 0,
    "contact" : {
        "home" : null,
        "office" : null,
        "cellphone" : null,
        "email" : "christophe.kervella@qaobee.com",
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
                },
                "paiementURL" : null
            }
        ],
        "habilitations" : null,
        "mobileToken" : null
    },
    "timestamp" : NumberLong(1444854317672),
    "gender" : null,
    "notifications" : null,
    "effectiveDefault" : "561ec4d0409937a6b439d4ea"
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
    "activityId": "ACT-HAND",
    "owner": "b50b3325-fdbd-41bf-bda4-81c827982001",
    "sandboxCfgId": "561ec092409937a6b439d4e8"
});

/*
 * Vidage de la collection SB_SandBoxCfg CKE
 */
db.SB_SandBoxCfg.remove({"_id": "561ec092409937a6b439d4e8"});

/***************************************************************
 * Alimentation SB_SandBoxCfg CKE
 * *************************************************************
 */
db.SB_SandBoxCfg.insert({
    "_id": "561ec092409937a6b439d4e8",
    "activity": {
        "_id": "ACT-HAND",
        "code": "ACT-HAND",
        "label": "admin.settings.activity.handball.label",
        "enable": true,
        "activityType": "TEAM_SPORT"
    },
    "sandbox": {
        "_id": "561ec20b409937a6b439d4e9",
        "activityId": "ACT-HAND",
        "owner": "b50b3325-fdbd-41bf-bda4-81c827982001"
    },
    "members": [
        {
            "personId": "5509ef1fdb8f8b6e2f51f4cf",

            "role": {
                "code": "acoach",
                "label": "Coach Adjoint"
            }
        }
    ],
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
    }
});

//////////////////////////////////////////////////////////
/* 
 * Vidage de la collection SB_Person (CKE)
 */
db.SB_Person.remove({"sandboxId":"561ec20b409937a6b439d4e9"});


/* 1 */
db.SB_Person.insert({
    "_id" : "807f1fc0-6e1b-4b4d-a0d8-7a4a5de8c0dd",
    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "squadnumber" : 11,
        "weight" : 60,
        "height" : 177,
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
    "contact" : {
        "cellphone" : "0781635773",
        "home" : "0298382987",
        "email" : "ch.kervella@gmail.com"
    },
    "firstname" : "Tristan",
    "name" : "Kervella",
    "birthdate" : NumberLong(1038351600000),
    "gender" : "Homme",
    "nationality" : "France",
    "birthCity" : "Brest, France",
    "sandboxId" : "561ec20b409937a6b439d4e9"
});

/* 2 */
db.SB_Person.insert({
    "_id" : "31aa373e-7cde-4c3d-ada6-f48f41dc38d2",
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
    "_id" : "836fff9c-c955-4861-8776-d68999a0abb5",
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
    "_id" : "5a21dfc4-8d93-4b71-b082-1ec7c821dd45",
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
    "_id" : "0ec47086-3266-451c-b452-62cd9091d1c6",
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
    "_id" : "94b05409-1ee4-4c84-af86-34de4d5f6824",
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
    "_id" : "940819ad-9b44-4512-aa4f-86e802e85607",
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
    "_id" : "7d6df586-4476-4bf8-a4cb-324d5421acee",
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
    "_id" : "84f90ac4-528a-41ec-aecf-ced45b3cb1f2",
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
    "_id" : "aa5018a5-7804-438b-9a31-52403ac62359",
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
    "_id" : "5b844a2e-fa1c-4daf-a60a-3c66ebf44b7b",
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
    "sandBoxCfgId" : "561ec092409937a6b439d4e8",
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
            "personId" : "807f1fc0-6e1b-4b4d-a0d8-7a4a5de8c0dd",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        }, 
        {
            "personId" : "31aa373e-7cde-4c3d-ada6-f48f41dc38d2",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        }, 
        {
            "personId" : "836fff9c-c955-4861-8776-d68999a0abb5",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        }, 
        {
            "personId" : "5a21dfc4-8d93-4b71-b082-1ec7c821dd45",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        }, 
        {
            "personId" : "0ec47086-3266-451c-b452-62cd9091d1c6",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        }, 
        {
            "personId" : "94b05409-1ee4-4c84-af86-34de4d5f6824",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        }, 
        {
            "personId" : "940819ad-9b44-4512-aa4f-86e802e85607",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        }, 
        {
            "personId" : "7d6df586-4476-4bf8-a4cb-324d5421acee",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        }, 
        {
            "personId" : "84f90ac4-528a-41ec-aecf-ced45b3cb1f2",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        }, 
        {
            "personId" : "aa5018a5-7804-438b-9a31-52403ac62359",
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
            "personId" : "5b844a2e-fa1c-4daf-a60a-3c66ebf44b7b",
            "role" : {
                "code" : "player",
                "label" : "Joueur"
            }
        }
    ]
});

/** ************************************************************* */
/*
 * Alimentation SB_Team (CKE)
 */
db.SB_Team.insert({
    "_id" : "3c43067f-469f-4369-92f7-82b0be36c1a9",
    "label" : "Avenir Du Ponant 1",
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "effectiveId" : "561ec4d0409937a6b439d4ea",
    "enable" : true,
    "adversary" : false
});

db.SB_Team.insert({
    "_id" : "562ba23db70fd108e375395b",
    "label" : "Ploemeur Atlantique Hb",
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "effectiveId" : "561ec4d0409937a6b439d4ea",
    "linkTeamId" : "3c43067f-469f-4369-92f7-82b0be36c1a9",
    "enable" : true,
    "adversary": true
});

db.SB_Team.insert({
    "_id" : "562ba2a5b70fd108e375395c", 
    "label" : "Guingamp Hb",
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "effectiveId" : "561ec4d0409937a6b439d4ea",
    "linkTeamId" : "3c43067f-469f-4369-92f7-82b0be36c1a9",
    "enable" : true,
    "adversary": true
});

db.SB_Team.insert({
    "_id" : "562ba2a5b70fd108e375395d",
    "label" : "Hb Pays De Vannes",
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "effectiveId" : "561ec4d0409937a6b439d4ea",
    "linkTeamId" : "3c43067f-469f-4369-92f7-82b0be36c1a9",
    "enable" : true,
    "adversary": true
});

db.SB_Team.insert({
    "_id" : "562ba2d4b70fd108e375395e",
    "label" : "Golfe 56",
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "effectiveId" : "561ec4d0409937a6b439d4ea",
    "linkTeamId" : "3c43067f-469f-4369-92f7-82b0be36c1a9",
    "enable" : true,
    "adversary": true
});

db.SB_Team.insert({
    "_id" : "562ba2e3b70fd108e375395f",
    "label" : "Cesson Rennes Métropole Hb",
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "effectiveId" : "561ec4d0409937a6b439d4ea",
    "linkTeamId" : "3c43067f-469f-4369-92f7-82b0be36c1a9",
    "enable" : true,
    "adversary": true
});

db.SB_Team.insert({
    "_id" : "562ba300b70fd108e3753960",
    "label" : "Cercle Paul Bert Rennes Hb",
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "effectiveId" : "561ec4d0409937a6b439d4ea",
    "linkTeamId" : "3c43067f-469f-4369-92f7-82b0be36c1a9",
    "enable" : true,
    "adversary": true
});

db.SB_Team.insert({
    "_id" : "562ba318b70fd108e3753961",
    "label" : "Rennes Métropole Hb",
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "effectiveId" : "561ec4d0409937a6b439d4ea",
    "linkTeamId" : "3c43067f-469f-4369-92f7-82b0be36c1a9",
    "enable" : true,
    "adversary": true
});

db.SB_Team.insert({
    "_id" : "562ba335b70fd108e3753962",
    "label" : "Stiren Languidic",
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "effectiveId" : "561ec4d0409937a6b439d4ea",
    "linkTeamId" : "3c43067f-469f-4369-92f7-82b0be36c1a9",
    "enable" : true,
    "adversary": true
});

db.SB_Team.insert({
    "_id" : "562ba34db70fd108e3753963",
    "label" : "Concarneau Hb",
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "effectiveId" : "561ec4d0409937a6b439d4ea",
    "linkTeamId" : "3c43067f-469f-4369-92f7-82b0be36c1a9",
    "enable" : true,
    "adversary": true
});

db.SB_Team.insert({
    "_id" : "562ba368b70fd108e3753964",
    "label" : "Lesneven Hb",
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "effectiveId" : "561ec4d0409937a6b439d4ea",
    "linkTeamId" : "3c43067f-469f-4369-92f7-82b0be36c1a9",
    "enable" : true,
    "adversary": true
});

db.SB_Team.insert({
    "_id" : "562ba37eb70fd108e3753965",
    "label" : "Hbc Plerin",
    "sandboxId" : "561ec20b409937a6b439d4e9",
    "effectiveId" : "561ec4d0409937a6b439d4ea",
    "linkTeamId" : "3c43067f-469f-4369-92f7-82b0be36c1a9",
    "enable" : true,
    "adversary": true
});

/*
 * Alimentation collection USER : JRO
 */
db.User.insert({
    "_id" : "b50b3325-fdbd-41bf-bda4-81c827982002",
    "avatar" : null,
    "name" : "Roue",
    "firstname" : "Jérôme",
    "address" : null,
    "birthdate" : 0,
    "contact" : {
        "home" : null,
        "office" : null,
        "cellphone" : null,
        "email" : "jerome.roue@qaobee.com",
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
        "login" : "jro-qaobee",
        "passwd" : null,
        "password" : "QrHqnRQwrJ3zwCw7dalpfH5rkoE=",
        "salt" : "MkgZzvYT+d8=",
        "timestamp" : 0,
        "token" : null,
        "tokenRenewDate" : 0,
        "listPlan" : [ 
            {
                "paymentId" : "d9248a81-2e4c-49f6-85d4-afc1599c5669",
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
                },
                "paiementURL" : null
            }
        ],
        "habilitations" : null,
        "mobileToken" : null
    },
    "timestamp" : NumberLong(1444854317672),
    "gender" : null,
    "notifications" : null,
    "effectiveDefault" : "56202514758f1cc6a4753306"
});

/*
 * Vidage de la collection SB_SandBox JRO
 */
db.SB_SandBox.remove({"_id": "56202363758f1cc6a4753304"});

/***************************************************************
 * Alimentation SB_SandBox JRO
 * *************************************************************
 */
db.SB_SandBox.insert({
    "_id": "56202363758f1cc6a4753304",
    "activityId": "ACT-HAND",
    "owner": "b50b3325-fdbd-41bf-bda4-81c827982002",
    "sandboxCfgId": "5620240c758f1cc6a4753305"
});

/*
 * Vidage de la collection SB_SandBoxCfg JRO
 */
db.SB_SandBoxCfg.remove({"_id": "5620240c758f1cc6a4753305"});

/***************************************************************
 * Alimentation SB_SandBoxCfg JRO
 * *************************************************************
 */
db.SB_SandBoxCfg.insert({
    "_id": "5620240c758f1cc6a4753305",
    "activity": {
        "_id": "ACT-HAND",
        "code": "ACT-HAND",
        "label": "admin.settings.activity.handball.label",
        "enable": true,
        "activityType": "TEAM_SPORT"
    },
    "sandbox": {
        "_id": "56202363758f1cc6a4753304",
        "activityId": "ACT-HAND",
        "owner": "b50b3325-fdbd-41bf-bda4-81c827982002"
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
        "label": "CLUB JRO HB",
        "acronym": "JROHB",
        "activity": {
            "_id": "ACT-HAND",
            "code": "ACT-HAND",
            "label": "admin.settings.activity.handball.label",
            "activated": true,
            "activityType": "TEAM_SPORT"
        },
        "country": {"_id": "CNTR-250-FR-FRA", "codeOSCE": NumberInt(250), "label": "France", "local": "fr"},
        "avatar": null
    }
});

//////////////////////////////////////////////////////////
/* 
 * Vidage de la collection SB_Effective (JRO)
 */
db.SB_Effective.remove({ "_id" : "56202514758f1cc6a4753306"});

/** ************************************************************* */
/*
 * Alimentation SB_Effective (JRO)
 */
/** ************************************************************* */
db.SB_Effective.insert({
    "_id" : "56202514758f1cc6a4753306",
    "sandBoxCfgId" : "5620240c758f1cc6a4753305",
    "label": "JRO A",
    "categoryAge" : {
        "code" : "sen",
        "label" : "Senior Gars",
        "ageMax" : NumberInt(150),
        "ageMin" : NumberInt(18),
        "genre" : "Homme",
        "order" : NumberInt(1)
    },
    "members" : []
});


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
                },
                "paiementURL" : null
            }
        ],
        "habilitations" : null,
        "mobileToken" : null
    },
    "timestamp" : NumberLong(1444854317672),
    "gender" : null,
    "notifications" : null,
    "effectiveDefault" : "56202720758f1cc6a4753309"
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
    "activityId": "ACT-HAND",
    "owner": "b50b3325-fdbd-41bf-bda4-81c827982003",
    "sandboxCfgId": "562026e7758f1cc6a4753308"
});

/*
 * Vidage de la collection SB_SandBoxCfg FIS
 */
db.SB_SandBoxCfg.remove({"_id": "562026e7758f1cc6a4753308"});

/***************************************************************
 * Alimentation SB_SandBoxCfg FIS
 * *************************************************************
 */
db.SB_SandBoxCfg.insert({
    "_id": "562026e7758f1cc6a4753308",
    "activity": {
        "_id": "ACT-HAND",
        "code": "ACT-HAND",
        "label": "admin.settings.activity.handball.label",
        "enable": true,
        "activityType": "TEAM_SPORT"
    },
    "sandbox": {
        "_id": "562026b8758f1cc6a4753307",
        "activityId": "ACT-HAND",
        "owner": "b50b3325-fdbd-41bf-bda4-81c827982003"
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
    }
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
    "sandBoxCfgId" : "562026e7758f1cc6a4753308",
    "label": "FIS A",
    "categoryAge" : {
        "code" : "sen",
        "label" : "Senior Gars",
        "ageMax" : NumberInt(150),
        "ageMin" : NumberInt(18),
        "genre" : "Homme",
        "order" : NumberInt(1)
    },
    "members" : []
});

/*
 * Alimentation collection USER : XMA
 */
db.User.insert({
    "_id" : "b50b3325-fdbd-41bf-bda4-81c827982004",
    "avatar" : null,
    "name" : "Marin",
    "firstname" : "Xavier",
    "address" : null,
    "birthdate" : 0,
    "contact" : {
        "home" : null,
        "office" : null,
        "cellphone" : null,
        "email" : "xavier.marin@qaobee.com",
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
        "login" : "xma-qaobee",
        "passwd" : null,
        "password" : "QrHqnRQwrJ3zwCw7dalpfH5rkoE=",
        "salt" : "MkgZzvYT+d8=",
        "timestamp" : 0,
        "token" : null,
        "tokenRenewDate" : 0,
        "listPlan" : [ 
            {
                "paymentId" : "d9248a81-2e4c-49f6-85d4-afc1599c5669",
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
                },
                "paiementURL" : null
            }
        ],
        "habilitations" : null,
        "mobileToken" : null
    },
    "timestamp" : NumberLong(1444854317672),
    "gender" : null,
    "notifications" : null,
    "effectiveDefault" : "5620284d758f1cc6a475330d"
});

/*
 * Vidage de la collection SB_SandBox XMA
 */
db.SB_SandBox.remove({"_id": "56202805758f1cc6a475330a"});

/***************************************************************
 * Alimentation SB_SandBox XMA
 * *************************************************************
 */
db.SB_SandBox.insert({
    "_id": "56202805758f1cc6a475330a",
    "activityId": "ACT-HAND",
    "owner": "b50b3325-fdbd-41bf-bda4-81c827982004",
    "sandboxCfgId": "5620281c758f1cc6a475330b"
});

/*
 * Vidage de la collection SB_SandBoxCfg XMA
 */
db.SB_SandBoxCfg.remove({"_id": "5620281c758f1cc6a475330b"});

/***************************************************************
 * Alimentation SB_SandBoxCfg XMA
 * *************************************************************
 */
db.SB_SandBoxCfg.insert({
    "_id": "5620281c758f1cc6a475330b",
    "activity": {
        "_id": "ACT-HAND",
        "code": "ACT-HAND",
        "label": "admin.settings.activity.handball.label",
        "enable": true,
        "activityType": "TEAM_SPORT"
    },
    "sandbox": {
        "_id": "56202805758f1cc6a475330a",
        "activityId": "ACT-HAND",
        "owner": "b50b3325-fdbd-41bf-bda4-81c827982004"
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
        "_id": "5620281c758f1cc6a475330c",
        "label": "CLUB XMA HB",
        "acronym": "XMAHB",
        "activity": {
            "_id": "ACT-HAND",
            "code": "ACT-HAND",
            "label": "admin.settings.activity.handball.label",
            "activated": true,
            "activityType": "TEAM_SPORT"
        },
        "country": {"_id": "CNTR-250-FR-FRA", "codeOSCE": NumberInt(250), "label": "France", "local": "fr"},
        "avatar": null
    }
});

//////////////////////////////////////////////////////////
/* 
 * Vidage de la collection SB_Effective (XMA)
 */
db.SB_Effective.remove({ "_id" : "5620284d758f1cc6a475330d"});

/** ************************************************************* */
/*
 * Alimentation SB_Effective (XMA)
 */
/** ************************************************************* */
db.SB_Effective.insert({
    "_id" : "5620284d758f1cc6a475330d",
    "sandBoxCfgId" : "5620281c758f1cc6a475330b",
    "label": "XMA A",
    "categoryAge" : {
        "code" : "sen",
        "label" : "Senior Gars",
        "ageMax" : NumberInt(150),
        "ageMin" : NumberInt(18),
        "genre" : "Homme",
        "order" : NumberInt(1)
    },
    "members" : []
});

/*
 * Alimentation collection USER : PPE
 */
db.User.insert({
    "_id" : "b50b3325-fdbd-41bf-bda4-81c827982005",
    "avatar" : null,
    "name" : "Pelerin",
    "firstname" : "Pascal",
    "address" : null,
    "birthdate" : 0,
    "contact" : {
        "home" : null,
        "office" : null,
        "cellphone" : null,
        "email" : "pascal.pelerin@qaobee.com",
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
        "login" : "ppe-qaobee",
        "passwd" : null,
        "password" : "QrHqnRQwrJ3zwCw7dalpfH5rkoE=",
        "salt" : "MkgZzvYT+d8=",
        "timestamp" : 0,
        "token" : null,
        "tokenRenewDate" : 0,
        "listPlan" : [ 
            {
                "paymentId" : "d9248a81-2e4c-49f6-85d4-afc1599c5669",
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
                },
                "paiementURL" : null
            }
        ],
        "habilitations" : null,
        "mobileToken" : null
    },
    "timestamp" : NumberLong(1444854317672),
    "gender" : null,
    "notifications" : null,
    "effectiveDefault" : "5620290f758f1cc6a4753320"
});

/*
 * Vidage de la collection SB_SandBox PPE
 */
db.SB_SandBox.remove({"_id": "562028bd758f1cc6a475330e"});

/***************************************************************
 * Alimentation SB_SandBox PPE
 * *************************************************************
 */
db.SB_SandBox.insert({
    "_id": "562028bd758f1cc6a475330e",
    "activityId": "ACT-HAND",
    "owner": "b50b3325-fdbd-41bf-bda4-81c827982005",
    "sandboxCfgId": "562028d9758f1cc6a475330f"
});

/*
 * Vidage de la collection SB_SandBoxCfg PPE
 */
db.SB_SandBoxCfg.remove({"_id": "562028d9758f1cc6a475330f"});

/***************************************************************
 * Alimentation SB_SandBoxCfg PPE
 * *************************************************************
 */
db.SB_SandBoxCfg.insert({
    "_id": "562028d9758f1cc6a475330f",
    "activity": {
        "_id": "ACT-HAND",
        "code": "ACT-HAND",
        "label": "admin.settings.activity.handball.label",
        "enable": true,
        "activityType": "TEAM_SPORT"
    },
    "sandbox": {
        "_id": "562028bd758f1cc6a475330e",
        "activityId": "ACT-HAND",
        "owner": "b50b3325-fdbd-41bf-bda4-81c827982005"
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
        "_id": "5620281c758f1cc6a475330c",
        "label": "CLUB PPE HB",
        "acronym": "PPEHB",
        "activity": {
            "_id": "ACT-HAND",
            "code": "ACT-HAND",
            "label": "admin.settings.activity.handball.label",
            "activated": true,
            "activityType": "TEAM_SPORT"
        },
        "country": {"_id": "CNTR-250-FR-FRA", "codeOSCE": NumberInt(250), "label": "France", "local": "fr"},
        "avatar": null
    }
});

//////////////////////////////////////////////////////////
/* 
 * Vidage de la collection SB_Effective (PPE)
 */
db.SB_Effective.remove({ "_id" : "5620290f758f1cc6a4753320"});

/** ************************************************************* */
/*
 * Alimentation SB_Effective (PPE)
 */
/** ************************************************************* */
db.SB_Effective.insert({
    "_id" : "5620290f758f1cc6a4753320",
    "sandBoxCfgId" : "562028d9758f1cc6a475330f",
    "label": "PPE A",
    "categoryAge" : {
        "code" : "sen",
        "label" : "Senior Gars",
        "ageMax" : NumberInt(150),
        "ageMin" : NumberInt(18),
        "genre" : "Homme",
        "order" : NumberInt(1)
    },
    "members" : []
});

/*
 * Alimentation collection USER : MGA
 */
db.User.insert({
    "_id" : "b50b3325-fdbd-41bf-bda4-81c827982006",
    "avatar" : null,
    "name" : "Garzuel",
    "firstname" : "Mathieu",
    "address" : null,
    "birthdate" : 0,
    "contact" : {
        "home" : null,
        "office" : null,
        "cellphone" : null,
        "email" : "mathieu.garzuel@qaobee.com",
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
        "login" : "mga-qaobee",
        "passwd" : null,
        "password" : "QrHqnRQwrJ3zwCw7dalpfH5rkoE=",
        "salt" : "MkgZzvYT+d8=",
        "timestamp" : 0,
        "token" : null,
        "tokenRenewDate" : 0,
        "listPlan" : [ 
            {
                "paymentId" : "d9248a81-2e4c-49f6-85d4-afc1599c5669",
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
                },
                "paiementURL" : null
            }
        ],
        "habilitations" : null,
        "mobileToken" : null
    },
    "timestamp" : NumberLong(1444854317672),
    "gender" : null,
    "notifications" : null,
    "effectiveDefault" : "56202974758f1cc6a4753323"
});

/*
 * Vidage de la collection SB_SandBox MGA
 */
db.SB_SandBox.remove({"_id": "5620294b758f1cc6a4753321"});

/***************************************************************
 * Alimentation SB_SandBox MGA
 * *************************************************************
 */
db.SB_SandBox.insert({
    "_id": "5620294b758f1cc6a4753321",
    "activityId": "ACT-HAND",
    "owner": "b50b3325-fdbd-41bf-bda4-81c827982006",
    "sandboxCfgId": "5620295f758f1cc6a4753322"
});

/*
 * Vidage de la collection SB_SandBoxCfg MGA
 */
db.SB_SandBoxCfg.remove({"_id": "5620295f758f1cc6a4753322"});

/***************************************************************
 * Alimentation SB_SandBoxCfg MGA
 * *************************************************************
 */
db.SB_SandBoxCfg.insert({
    "_id": "5620295f758f1cc6a4753322",
    "activity": {
        "_id": "ACT-HAND",
        "code": "ACT-HAND",
        "label": "admin.settings.activity.handball.label",
        "enable": true,
        "activityType": "TEAM_SPORT"
    },
    "sandbox": {
        "_id": "5620294b758f1cc6a4753321",
        "activityId": "ACT-HAND",
        "owner": "b50b3325-fdbd-41bf-bda4-81c827982006"
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
        "_id": "5620281c758f1cc6a475330c",
        "label": "CLUB MGA HB",
        "acronym": "MGAHB",
        "activity": {
            "_id": "ACT-HAND",
            "code": "ACT-HAND",
            "label": "admin.settings.activity.handball.label",
            "activated": true,
            "activityType": "TEAM_SPORT"
        },
        "country": {"_id": "CNTR-250-FR-FRA", "codeOSCE": NumberInt(250), "label": "France", "local": "fr"},
        "avatar": null
    }
});

//////////////////////////////////////////////////////////
/* 
 * Vidage de la collection SB_Effective (MGA)
 */
db.SB_Effective.remove({ "_id" : "56202974758f1cc6a4753323"});

/** ************************************************************* */
/*
 * Alimentation SB_Effective (MGA)
 */
/** ************************************************************* */
db.SB_Effective.insert({
    "_id" : "56202974758f1cc6a4753323",
    "sandBoxCfgId" : "562028d9758f1cc6a475330f",
    "label": "MGA A",
    "categoryAge" : {
        "code" : "sen",
        "label" : "Senior Gars",
        "ageMax" : NumberInt(150),
        "ageMin" : NumberInt(18),
        "genre" : "Homme",
        "order" : NumberInt(1)
    },
    "members" : []
});