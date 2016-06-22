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
            "levelPlan" : "FREEMIUM",
            "amountPaid" : 9,
            "paidDate" : 0,
            "startPeriodDate" : NumberLong(1466590012943),
            "endPeriodDate" : 0,
            "status" : "open",
            "periodicity" : "monthly",
            "activity": {
                "_id": "ACT-FOOT",
                "code": "ACT-FOOT",
                "label": "admin.settings.activity.football.label",
                "enable": true,
                "activityType": "TEAM_SPORT"
            },
            "paymentURL" : "https://www.payplug.com/pay/test/cnoSU0Pc8bURVwS0TF5yF",
            "cardId" : null,
            "cardInfo" : null,
            "shippingList" : null,
            "paiementURL" : "https://www.payplug.com/pay/test/cnoSU0Pc8bURVwS0TF5yF"
        }],
        "habilitations" : null,
        "mobileToken" : null
    },
    "timestamp" : NumberLong(1444854317672),
    "gender" : "gender.male",
    "effectiveDefault" : null
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
            "levelPlan" : "FREEMIUM",
            "amountPaid" : 9,
            "paidDate" : 0,
            "startPeriodDate" : NumberLong(1466590012943),
            "endPeriodDate" : 0,
            "status" : "open",
            "periodicity" : "monthly",
            "activity": {
                "_id": "ACT-FOOT",
                "code": "ACT-FOOT",
                "label": "admin.settings.activity.football.label",
                "enable": true,
                "activityType": "TEAM_SPORT"
            },
            "paymentURL" : "https://www.payplug.com/pay/test/cnoSU0Pc8bURVwS0TF5yF",
            "cardId" : null,
            "cardInfo" : null,
            "shippingList" : null,
            "paiementURL" : "https://www.payplug.com/pay/test/cnoSU0Pc8bURVwS0TF5yF"
        }],
        "habilitations" : null,
        "mobileToken" : null
    },
    "timestamp" : NumberLong(1444854317672),
    "gender" : "gender.male",
    "effectiveDefault" : null
});

//FIN USER Club Demo football
