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


