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
    "label": "CKE A",
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