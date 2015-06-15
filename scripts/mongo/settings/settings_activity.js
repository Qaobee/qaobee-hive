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
 * PARAMETRAGE Activity SETTINGS
 * V1.0
 * 
 * This script creates documents for collections :
 * - Activity
 * - Season
 * - ActivityCfg
 * 
 * AUTHOR : QaoBee
 */
//////////////////////////////////////////////////////////

/* 
 * Activity : une activite est immuable, cet objet ne bougera pas dans le temps
 */
db.Activity.remove({});

db.Activity.insert({
    "_id" : "ACT-FOOT",
    "code" : "ACT-FOOT",
    "label" : "admin.settings.activity.football.label",
    "activated" : true,
    "activityType" : "TEAM_SPORT"
});

db.Activity.insert({
    "_id" : "ACT-HAND",
    "code" : "ACT-HAND",
    "label" : "admin.settings.activity.handball.label",
    "activated" : true,
    "activityType" : "TEAM_SPORT"
});

db.Activity.insert({
    "_id" : "ACT-FENC",
    "code" : "ACT-FENC",
    "label" : "admin.settings.activity.fencing.label",
    "activated" : false,
    "activityType" : "TEAM_SPORT"
});
// ////////////////////////////////////////////////////////

/*
 * Season : liste des saisons pour une activite
 */
db.Season.remove({});
var _id = new ObjectId().valueOf();
db.Season.insert({
    "_id" : _id,
    "code" : "SAI-2013",
    "label" : "SAISON 2013-2014",
    /* Start : 01/07/2013*/
    "startDate" : NumberLong(1372629600000),
    /* End : 31/05/2014*/
    "endDate" : NumberLong(1401573600000),
    "activityId" : "ACT-FOOT",
    "countryId" : "CNTR-250-FR-FRA"
});

_id = new ObjectId().valueOf();
db.Season.insert({
    "_id" : _id,
    "code" : "SAI-2014",
    "label" : "SAISON 2014-2015",
    /* Start : 01/06/2014*/
    "startDate" : NumberLong(1401573600000),
    /* End : 30/06/2015*/
    "endDate" : NumberLong(1435615200000),
    "activityId" : "ACT-FOOT",
    "countryId" : "CNTR-250-FR-FRA"
});

_id = new ObjectId().valueOf();
db.Season.insert({
    "_id" : _id,
    "code" : "SAI-2015",
    "label" : "SAISON 2015-2016",
    /* Start : 01/07/2015*/
    "startDate" : NumberLong(1435701600000),
    /* End : 30/06/2016*/
    "endDate" : NumberLong(1467237600000),
    "activityId" : "ACT-FOOT",
    "countryId" : "CNTR-250-FR-FRA"
});

_id = new ObjectId().valueOf();
db.Season.insert({
    "_id" : _id,
    "code" : "SAI-2013",
    "label" : "SAISON 2013-2014",
    /* Start : 01/07/2013*/
    "startDate" : NumberLong(1372629600000),
    /* End : 30/06/2014*/
    "endDate" : NumberLong(1404079200000),
    "activityId" : "ACT-HAND",
    "countryId" : "CNTR-250-FR-FRA"
});

_id = new ObjectId().valueOf();
db.Season.insert({
    "_id" : _id,
    "code" : "SAI-2014",
    "label" : "SAISON 2014-2015",
    /* Start : 01/07/2014*/
    "startDate" : NumberLong(1404165600000),
    /* End : 30/06/2015*/
    "endDate" : NumberLong(1435615200000),
    "activityId" : "ACT-HAND",
    "countryId" : "CNTR-250-FR-FRA"
});

_id = new ObjectId().valueOf();
db.Season.insert({
    "_id" : _id,
    "code" : "SAI-2015",
    "label" : "SAISON 2015-2016",
    /* Start : 01/07/2015*/
    "startDate" : NumberLong(1435701600000),
    /* End : 30/06/2016*/
    "endDate" : NumberLong(1467237600000),
    "activityId" : "ACT-HAND",
    "countryId" : "CNTR-250-FR-FRA"
});
// ////////////////////////////////////////////////////////

/*
 * Activity configuration : grace a ce document on peut gerer les modifications
 * d'une activite d'une saison a l'autre, et par pays
 */

db.ActivityCfg.remove({});

// /// FOOT CONFIGURATION /////
_id = new ObjectId().valueOf();
db.ActivityCfg.insert({
    "_id" : _id,
    "activityId" : "ACT-FOOT",
    "countryId" : "CNTR-250-FR-FRA",
    /* Start : 01/01/1900*/
    "startDate" : NumberLong(-2208992400000),
    /* End : 31/12/2199*/
    "endDate" : NumberLong(7258028400000),
    "caracteristicsPerson" : [{
        "positionType" : ["forward", "midfielder", "defender"],
        "physicalFolder" : [
            { key:"explosive", value: NumberInt(3)},
            { key:"speed", value: NumberInt(3)},
            { key:"traction", value: NumberInt(3)},
            { key:"verticaljump", value: NumberInt(3)},
            { key:"endurance", value: NumberInt(3)},
            { key:"equilibrium", value: NumberInt(3)},
            { key:"strenght", value: NumberInt(3)},
            { key:"nimbleness", value: NumberInt(3)}
        ],
        "technicalFolder" : [
            { key:"pass", value: NumberInt(3)},
            { key:"center", value: NumberInt(3)},
            { key:"corner", value: NumberInt(3)},
            { key:"freekick", value: NumberInt(3)},
            { key:"penalty", value: NumberInt(3)},
            { key:"shoot", value: NumberInt(3)},
            { key:"dribble", value: NumberInt(3)},
            { key:"tacle", value: NumberInt(3)}
        ],
        "mentalFolder" : [
            { key:"aggressiveness", value: NumberInt(3)},
            { key:"anticipation", value: NumberInt(3)},
            { key:"concentration", value: NumberInt(3)},
            { key:"altruism", value: NumberInt(3)},
            { key:"courage", value: NumberInt(3)},
            { key:"creativity", value: NumberInt(3)},
            { key:"motivation", value: NumberInt(3)},
            { key:"self-control", value: NumberInt(3)},
            { key:"leadership", value: NumberInt(3)},
            { key:"professionalism", value: NumberInt(3)}
        ]
    },
        {
            "positionType" : ["goalkeeper"],
            "physicalFolder" : [
                { key:"explosive", value: NumberInt(3)},
                { key:"speed", value: NumberInt(3)},
                { key:"traction", value: NumberInt(3)},
                { key:"verticaljump", value: NumberInt(3)},
                { key:"endurance", value: NumberInt(3)},
                { key:"equilibrium", value: NumberInt(3)},
                { key:"strenght", value: NumberInt(3)},
                { key:"nimbleness", value: NumberInt(3)}
            ],
            "technicalFolder" : [
                { key:"pass", value: NumberInt(3)},
                { key:"center", value: NumberInt(3)},
                { key:"corner", value: NumberInt(3)},
                { key:"freekick", value: NumberInt(3)},
                { key:"penalty", value: NumberInt(3)},
                { key:"shoot", value: NumberInt(3)},
                { key:"dribble", value: NumberInt(3)},
                { key:"tacle", value: NumberInt(3)}
            ],
            "mentalFolder" : [
                { key:"aggressiveness", value: NumberInt(3)},
                { key:"anticipation", value: NumberInt(3)},
                { key:"concentration", value: NumberInt(3)},
                { key:"altruism", value: NumberInt(3)},
                { key:"courage", value: NumberInt(3)},
                { key:"creativity", value: NumberInt(3)},
                { key:"motivation", value: NumberInt(3)},
                { key:"self-control", value: NumberInt(3)},
                { key:"leadership", value: NumberInt(3)},
                { key:"professionalism", value: NumberInt(3)}
            ]
        }],
    "listRubricSheet" : [ {
        "code" : "SHEET-DAS",
        "label" : "Tableau de bord",
        "isDefault" : true,
        "order" : NumberInt(1),
        "indicatorFamily" : "IND-DASHBOARD"
    }, {
        "code" : "SHEET-TEC",
        "label" : "Technique",
        "isDefault" : false,
        "order" : NumberInt(2),
        "indicatorFamily" : "IND-TECHNICAL"
    }, {
        "code" : "SHEET-PHY",
        "label" : "Physique",
        "isDefault" : false,
        "order" : NumberInt(3),
        "indicatorFamily" : "IND-PHYSICAL"
    }, {
        "code" : "SHEET-MEN",
        "label" : "Mental",
        "isDefault" : false,
        "order" : NumberInt(4),
        "indicatorFamily" : "IND-MENTAL"
    },{
        "code" : "SHEET-ADM",
        "label" : "Administratif",
        "isDefault" : false,
        "order" : NumberInt(5),
        "indicatorFamily" : "IND-ADMIN"
    }],
    "listLevelGame" : [ {
        "code" : "nv1",
        "label" : "Elite",
        "order" : NumberInt(1)
    }, {
        "code" : "nv2",
        "label" : "National",
        "order" : NumberInt(2)
    }, {
        "code" : "nv3",
        "label" : "Régional",
        "order" : NumberInt(3)
    }, {
        "code" : "nv4",
        "label" : "Départemental",
        "order" : NumberInt(4)
    }],
    "listRoleStr" : [ {
        "code" : "president",
        "label" : "Président",
        "order" : NumberInt(1)
    }, {
        "code" : "treasurer",
        "label" : "Trésorier",
        "order" : NumberInt(2)
    }, {
        "code" : "secretary",
        "label" : "Secrétaire",
        "order" : NumberInt(3)
    }, {
        "code" : "coach",
        "label" : "Coach",
        "order" : NumberInt(4)
    }, {
        "code" : "acoach",
        "label" : "Coach Adjoint",
        "order" : NumberInt(5)
    }, {
        "code" : "physiotherapist",
        "label" : "Kinésithérapeute",
        "order" : NumberInt(6)
    }, {
        "code" : "member",
        "label" : "Membre",
        "order" : NumberInt(7)
    } ],
    "listCategoryAge" : [ {
        "code" : "vet",
        "label" : "Vétérant",
        "ageMax" : NumberInt(150),
        "ageMin" : NumberInt(35),
        "genre" : "Masculin",
        "order" : NumberInt(1)
    }, {
        "code" : "sen",
        "label" : "Senior",
        "ageMax" : NumberInt(34),
        "ageMin" : NumberInt(20),
        "genre" : "Masculin",
        "order" : NumberInt(2)
    }, {
        "code" : "senF",
        "label" : "Senior Féminin",
        "ageMax" : NumberInt(34),
        "ageMin" : NumberInt(19),
        "genre" : "Féminin",
        "order" : NumberInt(3)
    }, {
        "code" : "u19",
        "label" : "U19",
        "ageMax" : NumberInt(18),
        "ageMin" : NumberInt(17),
        "genre" : "Masculin",
        "order" : NumberInt(4)
    }, {
        "code" : "u17",
        "label" : "U17",
        "ageMax" : NumberInt(16),
        "ageMin" : NumberInt(15),
        "genre" : "Masculin",
        "order" : NumberInt(5)
    }, {
        "code" : "u15",
        "label" : "U15",
        "ageMax" : NumberInt(14),
        "ageMin" : NumberInt(13),
        "genre" : "Masculin",
        "order" : NumberInt(6)
    }, {
        "code" : "u13",
        "label" : "U13",
        "ageMax" : NumberInt(12),
        "ageMin" : NumberInt(11),
        "genre" : "Masculin",
        "order" : NumberInt(7)
    }, {
        "code" : "u11",
        "label" : "U11",
        "ageMax" : NumberInt(10),
        "ageMin" : NumberInt(9),
        "genre" : "Masculin",
        "order" : NumberInt(8)
    } ],
    "listGender" : [ {
        "code" : "male",
        "label" : "Masculin",
        "order" : NumberInt(1)
    }, {
        "code" : "female",
        "label" : "Féminin",
        "order" : NumberInt(2)
    }, {
        "code" : "mixte",
        "label" : "Mixte",
        "order" : NumberInt(3)
    } ],
    "listTypeLicense" : [ {
        "code" : "A",
        "label" : "Dirigeant",
        "order" : NumberInt(1)
    }, {
        "code" : "B",
        "label" : "Joueur Libre",
        "order" : NumberInt(2)
    }, {
        "code" : "C",
        "label" : "Joueur futsal",
        "order" : NumberInt(3)
    }, {
        "code" : "D",
        "label" : "Joueur Entreprise",
        "order" : NumberInt(4)
    }, {
        "code" : "E",
        "label" : "Joueur Loisir",
        "order" : NumberInt(5)
    } ]
});


// /// HAND CONFIGURATION /////

_id = new ObjectId().valueOf();
db.ActivityCfg.insert({
    "_id" : _id,
    "activityId" : "ACT-HAND",
    "countryId" : "CNTR-250-FR-FRA",
    /* Start : 01/01/1900*/
    "startDate" : NumberLong(-2208992400000),
    /* End : 31/12/2199*/
    "endDate" : NumberLong(7258028400000),
    "caracteristicsPerson" : [{
        "positionType" : ["pivot", "center-backcourt", "left-backcourt", "right-backcourt", "left-wingman", "right-wingman"],
        "physicalFolder" : [
            { key:"explosive", value: NumberInt(3)},
            { key:"speed", value: NumberInt(3)},
            { key:"traction", value: NumberInt(3)},
            { key:"verticaljump", value: NumberInt(3)},
            { key:"endurance", value: NumberInt(3)},
            { key:"equilibrium", value: NumberInt(3)},
            { key:"strenght", value: NumberInt(3)},
            { key:"nimbleness", value: NumberInt(3)}
        ],
        "technicalFolder" : [
            { key:"pass", value: NumberInt(3)},
            { key:"center", value: NumberInt(3)},
            { key:"corner", value: NumberInt(3)},
            { key:"freekick", value: NumberInt(3)},
            { key:"penalty", value: NumberInt(3)},
            { key:"shoot", value: NumberInt(3)},
            { key:"dribble", value: NumberInt(3)},
            { key:"tacle", value: NumberInt(3)}
        ],
        "mentalFolder" : [
            { key:"aggressiveness", value: NumberInt(3)},
            { key:"anticipation", value: NumberInt(3)},
            { key:"concentration", value: NumberInt(3)},
            { key:"altruism", value: NumberInt(3)},
            { key:"courage", value: NumberInt(3)},
            { key:"creativity", value: NumberInt(3)},
            { key:"motivation", value: NumberInt(3)},
            { key:"self-control", value: NumberInt(3)},
            { key:"leadership", value: NumberInt(3)},
            { key:"professionalism", value: NumberInt(3)}
        ]
    },
        {
            "positionType" : ["goalkeeper"],
            "physicalFolder" : [
                { key:"explosive", value: NumberInt(3)},
                { key:"speed", value: NumberInt(3)},
                { key:"traction", value: NumberInt(3)},
                { key:"verticaljump", value: NumberInt(3)},
                { key:"endurance", value: NumberInt(3)},
                { key:"equilibrium", value: NumberInt(3)},
                { key:"strenght", value: NumberInt(3)},
                { key:"nimbleness", value: NumberInt(3)}
            ],
            "technicalFolder" : [
                { key:"pass", value: NumberInt(3)},
                { key:"center", value: NumberInt(3)},
                { key:"corner", value: NumberInt(3)},
                { key:"freekick", value: NumberInt(3)},
                { key:"penalty", value: NumberInt(3)},
                { key:"shoot", value: NumberInt(3)},
                { key:"dribble", value: NumberInt(3)},
                { key:"tacle", value: NumberInt(3)}
            ],
            "mentalFolder" : [
                { key:"aggressiveness", value: NumberInt(3)},
                { key:"anticipation", value: NumberInt(3)},
                { key:"concentration", value: NumberInt(3)},
                { key:"altruism", value: NumberInt(3)},
                { key:"courage", value: NumberInt(3)},
                { key:"creativity", value: NumberInt(3)},
                { key:"motivation", value: NumberInt(3)},
                { key:"self-control", value: NumberInt(3)},
                { key:"leadership", value: NumberInt(3)},
                { key:"professionalism", value: NumberInt(3)}
            ]
        }],
    "listRubricSheet" : [ {
        "code" : "SHEET-DAS",
        "label" : "Tableau de bord",
        "isDefault" : true,
        "order" : NumberInt(1),
        "indicatorFamily" : "IND-DASHBOARD"
    }, {
        "code" : "SHEET-TEC",
        "label" : "Technique",
        "isDefault" : false,
        "order" : NumberInt(2),
        "indicatorFamily" : "IND-TECHNICAL"
    }, {
        "code" : "SHEET-PHY",
        "label" : "Physique",
        "isDefault" : false,
        "order" : NumberInt(3),
        "indicatorFamily" : "IND-PHYSICAL"
    }, {
        "code" : "SHEET-MEN",
        "label" : "Mental",
        "isDefault" : false,
        "order" : NumberInt(4),
        "indicatorFamily" : "IND-MENTAL"
    },{
        "code" : "SHEET-ADM",
        "label" : "Administratif",
        "isDefault" : false,
        "order" : NumberInt(5),
        "indicatorFamily" : "IND-ADMIN"
    }],
    "listLevelGame" : [ {
        "code" : "nv1",
        "label" : "Elite",
        "order" : NumberInt(1)
    }, {
        "code" : "nv2",
        "label" : "National",
        "order" : NumberInt(2)
    }, {
        "code" : "nv3",
        "label" : "Régional",
        "order" : NumberInt(3)
    }, {
        "code" : "nv4",
        "label" : "Départemental",
        "order" : NumberInt(4)
    } ],
    "listRoleStr" : [ {
        "code" : "president",
        "label" : "Président",
        "order" : NumberInt(1)
    }, {
        "code" : "treasurer",
        "label" : "Trésorier",
        "order" : NumberInt(2)
    }, {
        "code" : "secretary",
        "label" : "Secrétaire",
        "order" : NumberInt(3)
    }, {
        "code" : "coach",
        "label" : "Coach",
        "order" : NumberInt(4)
    }, {
        "code" : "acoach",
        "label" : "Coach Adjoint",
        "order" : NumberInt(5)
    }, {
        "code" : "physiotherapist",
        "label" : "Kinésithérapeute",
        "order" : NumberInt(6)
    }, {
        "code" : "member",
        "label" : "Membre",
        "order" : NumberInt(7)
    } ],
    "listCategoryAge" : [ {
        "code" : "sen",
        "label" : "Senior Gars",
        "ageMax" : NumberInt(150),
        "ageMin" : NumberInt(18),
        "genre" : "Masculin",
        "order" : NumberInt(1)
    }, {
        "code" : "senF",
        "label" : "Senior Féminin",
        "ageMax" : NumberInt(150),
        "ageMin" : NumberInt(18),
        "genre" : "Féminin",
        "order" : NumberInt(2)
    }, {
        "code" : "u18",
        "label" : "Moins de 18 Gars",
        "ageMax" : NumberInt(17),
        "ageMin" : NumberInt(17),
        "genre" : "Masculin",
        "order" : NumberInt(3)
    }, {
        "code" : "u18F",
        "label" : "Moins de 18 Fille",
        "ageMax" : NumberInt(17),
        "ageMin" : NumberInt(16),
        "genre" : "Féminin",
        "order" : NumberInt(4)
    }, {
        "code" : "u17",
        "label" : "Moins de 17 Gars",
        "ageMax" : NumberInt(16),
        "ageMin" : NumberInt(16),
        "genre" : "Masculin",
        "order" : NumberInt(5)
    }, {
        "code" : "u16",
        "label" : "Moins de 16 Gars",
        "ageMax" : NumberInt(15),
        "ageMin" : NumberInt(15),
        "genre" : "Masculin",
        "order" : NumberInt(6)
    }, {
        "code" : "u16F",
        "label" : "Moins de 16 Fille",
        "ageMax" : NumberInt(15),
        "ageMin" : NumberInt(14),
        "genre" : "Féminin",
        "order" : NumberInt(7)
    }, {
        "code" : "u15",
        "label" : "Moins de 15 Gars",
        "ageMax" : NumberInt(14),
        "ageMin" : NumberInt(14),
        "genre" : "Masculin",
        "order" : NumberInt(8)
    }, {
        "code" : "u14",
        "label" : "Moins de 14 Gars",
        "ageMax" : NumberInt(13),
        "ageMin" : NumberInt(12),
        "genre" : "Masculin",
        "order" : NumberInt(9)
    }, {
        "code" : "u14F",
        "label" : "Moins de 14 Fille",
        "ageMax" : NumberInt(13),
        "ageMin" : NumberInt(12),
        "genre" : "Féminin",
        "order" : NumberInt(10)
    }, {
        "code" : "u12",
        "label" : "Moins de 12 Gars",
        "ageMax" : NumberInt(11),
        "ageMin" : NumberInt(10),
        "genre" : "Masculin",
        "order" : NumberInt(11)
    }, {
        "code" : "u12F",
        "label" : "Moins de 12 Fille",
        "ageMax" : NumberInt(11),
        "ageMin" : NumberInt(10),
        "genre" : "Féminin",
        "order" : NumberInt(12)
    }, {
        "code" : "u10",
        "label" : "Moins de 10",
        "ageMax" : NumberInt(9),
        "ageMin" : NumberInt(9),
        "genre" : "Mixte",
        "order" : NumberInt(13)
    }, {
        "code" : "u09",
        "label" : "Moins de 9",
        "ageMax" : NumberInt(8),
        "ageMin" : NumberInt(7),
        "genre" : "Mixte",
        "order" : NumberInt(14)
    } ],
    "listGender" : [ {
        "code" : "male",
        "label" : "Masculin",
        "order" : NumberInt(1)
    }, {
        "code" : "female",
        "label" : "Féminin",
        "order" : NumberInt(2)
    }, {
        "code" : "mixte",
        "label" : "Mixte",
        "order" : NumberInt(3)
    } ],
    "listTypeLicense" : [ {
        "code" : "A",
        "label" : "Licence A",
        "order" : NumberInt(1)
    }, {
        "code" : "B",
        "label" : "Licence B",
        "order" : NumberInt(2)
    }, {
        "code" : "C",
        "label" : "Licence C",
        "order" : NumberInt(3)
    }, {
        "code" : "E",
        "label" : "Licence E",
        "order" : NumberInt(4)
    }, {
        "code" : "BE",
        "label" : "Licence BE",
        "order" : NumberInt(5)
    }, {
        "code" : "CE",
        "label" : "Licence CE",
        "order" : NumberInt(6)
    } ]
}); 
