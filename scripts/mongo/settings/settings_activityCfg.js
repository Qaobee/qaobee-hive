//////////////////////////////////////////////////////////
/* 
 * SCRIPT MONGO DB
 * PARAMETRAGE Activity SETTINGS
 * V1.0
 * 
 * This script creates documents for collections :
 * 
 * - ActivityCfg
 * 
 * AUTHOR : QaoBee
 */
//////////////////////////////////////////////////////////


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
    "listPositionType" : [ {
        "code" : "forward",
        "label" : "Attaquant",
        "order" : NumberInt(1)
    }, {
        "code" : "midfielder",
        "label" : "Milieu",
        "order" : NumberInt(2)
    }, {
        "code" : "defender",
        "label" : "Défenseur",
        "order" : NumberInt(3)
    }, {
        "code" : "goalkeeper",
        "label" : "Gardien",
        "order" : NumberInt(4)
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
    "listRoleSandbox" : [ {
        "code" : "admin",
        "label" : "Administrateur",
        "order" : NumberInt(1)
    }, {
        "code" : "member",
        "label" : "Membre",
        "order" : NumberInt(2)
    } ],
    "listRoleStr" : [ {
        "code" : "player",
        "label" : "Joueur",
        "order" : NumberInt(1)
    }, {
        "code" : "coach",
        "label" : "Coach",
        "order" : NumberInt(2)
    }, {
        "code" : "acoach",
        "label" : "Coach Adjoint",
        "order" : NumberInt(3)
    }, {
        "code" : "physiotherapist",
        "label" : "Kinésithérapeute",
        "order" : NumberInt(7)
    }, {
        "code" : "other",
        "label" : "Autre",
        "order" : NumberInt(8)
    } ],
    "listCategoryAge" : [ {
        "code" : "vet",
        "label" : "Vétérant",
        "ageMax" : NumberInt(150),
        "ageMin" : NumberInt(35),
        "genre" : "Homme",
        "order" : NumberInt(1)
    }, {
        "code" : "sen",
        "label" : "Senior",
        "ageMax" : NumberInt(34),
        "ageMin" : NumberInt(20),
        "genre" : "Homme",
        "order" : NumberInt(2)
    }, {
        "code" : "senF",
        "label" : "Senior Féminin",
        "ageMax" : NumberInt(34),
        "ageMin" : NumberInt(19),
        "genre" : "Femme",
        "order" : NumberInt(3)
    }, {
        "code" : "u19",
        "label" : "U19",
        "ageMax" : NumberInt(18),
        "ageMin" : NumberInt(17),
        "genre" : "Homme",
        "order" : NumberInt(4)
    }, {
        "code" : "u17",
        "label" : "U17",
        "ageMax" : NumberInt(16),
        "ageMin" : NumberInt(15),
        "genre" : "Homme",
        "order" : NumberInt(5)
    }, {
        "code" : "u15",
        "label" : "U15",
        "ageMax" : NumberInt(14),
        "ageMin" : NumberInt(13),
        "genre" : "Homme",
        "order" : NumberInt(6)
    }, {
        "code" : "u13",
        "label" : "U13",
        "ageMax" : NumberInt(12),
        "ageMin" : NumberInt(11),
        "genre" : "Homme",
        "order" : NumberInt(7)
    }, {
        "code" : "u11",
        "label" : "U11",
        "ageMax" : NumberInt(10),
        "ageMin" : NumberInt(9),
        "genre" : "Homme",
        "order" : NumberInt(8)
    } ],
    "listAvailabityStatus" : [ {
        "code" : "available",
        "label" : "Disponible",
        "order" : NumberInt(1)
    }, {
        "code" : "wounded",
        "label" : "Blessé",
        "order" : NumberInt(2)
    }, {
        "code" : "suspended",
        "label" : "Suspendu",
        "order" : NumberInt(3)
    }, {
        "code" : "absent",
        "label" : "Incertain",
        "order" : NumberInt(4)
    } ],
    "listShapeConditionStatus" : [ {
        "code" : "vg",
        "label" : "Très bon",
        "order" : NumberInt(1)
    }, {
        "code" : "g",
        "label" : "Bon",
        "order" : NumberInt(2)
    }, {
        "code" : "M",
        "label" : "Moyen",
        "order" : NumberInt(3)
    }, {
        "code" : "F",
        "label" : "Faible",
        "order" : NumberInt(4)
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
    "listLaterality" : [ {
        "code" : "lateralityR",
        "label" : "Droitier",
        "order" : NumberInt(1)
    }, {
        "code" : "lateralityL",
        "label" : "Gaucher",
        "order" : NumberInt(2)
    }, {
        "code" : "lateralityA",
        "label" : "Ambidextre",
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
    } ],
    "listEventType" : [ {
        "code" : "championship",
        "label" : "Championnat",
        "order" : NumberInt(1)
    }, {
        "code" : "cup",
        "label" : "Coupe",
        "order" : NumberInt(2)
    }, {
        "code" : "friendlyGame",
        "label" : "Match amical",
        "order" : NumberInt(3)
    }],
    "parametersGame": {
        "nbMaxPlayers" : 16,
        "nbMinPlayers" : 7,
        "nbPeriod" : 2,
        "periodDuration" : 2700,
        "nbTimeout" : 0,
        "timeoutDuration" : 0,
        "yellowCardMax": 2,
        "exclusionTempo": 0,
        "halfTimeDuration": 600
    },
    "parametersSignup" : {
    	"players" : [{
    		"positionType" : "goalkeeper",
    		"quantity" : NumberInt(2)
    	}, {
    		"positionType" : "defender",
    		"quantity" : NumberInt(5)
    	}, {
    		"positionType" : "midfielder",
    		"quantity" : NumberInt(5)
    	}, {
    		"positionType" : "forward",
    		"quantity" : NumberInt(5)
    	}]
    }
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
    "listPositionType" : [ {
        "code" : "left-wingman",
        "label" : "Ailier Gauche",
        "order" : NumberInt(1)
    }, {
        "code" : "left-backcourt",
        "label" : "Arrière Gauche",
        "order" : NumberInt(2)
    }, {
        "code" : "pivot",
        "label" : "Pivot",
        "order" : NumberInt(3)
    }, {
        "code" : "center-backcourt",
        "label" : "Demi-centre",
        "order" : NumberInt(4)
    }, {
        "code" : "right-backcourt",
        "label" : "Arrière Droit",
        "order" : NumberInt(5)
    }, {
        "code" : "right-wingman",
        "label" : "Ailier Droit",
        "order" : NumberInt(6)
    }, {
        "code" : "goalkeeper",
        "label" : "Gardien",
        "order" : NumberInt(7)
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
        "code" : "player",
        "label" : "Joueur",
        "order" : NumberInt(1)
    }, {
        "code" : "coach",
        "label" : "Coach",
        "order" : NumberInt(2)
    }, {
        "code" : "acoach",
        "label" : "Coach Adjoint",
        "order" : NumberInt(3)
    }, {
        "code" : "physiotherapist",
        "label" : "Kinésithérapeute",
        "order" : NumberInt(7)
    }, {
        "code" : "other",
        "label" : "Autre",
        "order" : NumberInt(8)
    } ],
    "listRoleSandbox" : [ {
        "code" : "admin",
        "label" : "Administrateur",
        "order" : NumberInt(1)
    }, {
        "code" : "member",
        "label" : "Membre",
        "order" : NumberInt(2)
    } ],
    "listCategoryAge" : [ {
        "code" : "senG",
        "label" : "Senior Gars",
        "ageMax" : 150,
        "ageMin" : 18,
        "genre" : "Homme",
        "order" : 1
    }, 
    {
        "code" : "senF",
        "label" : "Senior Filles",
        "ageMax" : 150,
        "ageMin" : 18,
        "genre" : "Femme",
        "order" : 2
    }, 
    {
        "code" : "u18G",
        "label" : "- 18 Gars",
        "ageMax" : 18,
        "ageMin" : 15,
        "genre" : "Homme",
        "order" : 3
    }, 
    {
        "code" : "u18F",
        "label" : "- 18 Fille",
        "ageMax" : 18,
        "ageMin" : 15,
        "genre" : "Femme",
        "order" : 4
    }, 
    {
        "code" : "u17G",
        "label" : "- 17 Gars",
        "ageMax" : 17,
        "ageMin" : 15,
        "genre" : "Homme",
        "order" : 5
    }, 
    {
        "code" : "u17F",
        "label" : "- 17 Filles",
        "ageMax" : 17,
        "ageMin" : 15,
        "genre" : "Femme",
        "order" : 6
    },
    {
        "code" : "u16",
        "label" : "- 16 Gars",
        "ageMax" : 15,
        "ageMin" : 15,
        "genre" : "Homme",
        "order" : 7
    }, 
    {
        "code" : "u16F",
        "label" : "- 16 Fille",
        "ageMax" : 15,
        "ageMin" : 14,
        "genre" : "Femme",
        "order" : 8
    }, 
    {
        "code" : "u15G",
        "label" : "- 15 Gars",
        "ageMax" : 14,
        "ageMin" : 14,
        "genre" : "Homme",
        "order" : 9
    },
   {
        "code" : "u15F",
        "label" : "- 15 Filles",
        "ageMax" : 14,
        "ageMin" : 14,
        "genre" : "Femme",
        "order" : 10
    }, 
    {
        "code" : "u14G",
        "label" : "- 14 Gars",
        "ageMax" : 13,
        "ageMin" : 12,
        "genre" : "Homme",
        "order" : 11
    }, 
    {
        "code" : "u14F",
        "label" : "- 14 Fille",
        "ageMax" : 13,
        "ageMin" : 12,
        "genre" : "Femme",
        "order" : 12
    },
    {
        "code" : "u13G",
        "label" : "- 13 Gars",
        "ageMax" : 12,
        "ageMin" : 11,
        "genre" : "Homme",
        "order" : 13
    }, 
    {
        "code" : "u13F",
        "label" : "- 13 Fille",
        "ageMax" : 12,
        "ageMin" : 11,
        "genre" : "Femme",
        "order" : 14
    }, 
    {
        "code" : "u12G",
        "label" : "- 12 Gars",
        "ageMax" : 11,
        "ageMin" : 10,
        "genre" : "Homme",
        "order" : 15
    }, 
    {
        "code" : "u12F",
        "label" : "- 12 Fille",
        "ageMax" : 11,
        "ageMin" : 10,
        "genre" : "Femme",
        "order" : 16
    },
    {
        "code" : "u11G",
        "label" : "- 11 Gars",
        "ageMax" : 11,
        "ageMin" : 10,
        "genre" : "Homme",
        "order" : 17
    }, 
    {
        "code" : "u11F",
        "label" : "- 11 Fille",
        "ageMax" : 10,
        "ageMin" : 9,
        "genre" : "Femme",
        "order" : 18
    }, 
    {
        "code" : "u10",
        "label" : "- 10",
        "ageMax" : 9,
        "ageMin" : 9,
        "genre" : "Mixte",
        "order" : 19
    }, 
    {
        "code" : "u09",
        "label" : "- 9",
        "ageMax" : 8,
        "ageMin" : 7,
        "genre" : "Mixte",
        "order" : 20
    } ],
    "listAvailabityStatus" : [ {
        "code" : "available",
        "label" : "Disponible",
        "order" : NumberInt(1)
    }, {
        "code" : "wounded",
        "label" : "Blessé",
        "order" : NumberInt(2)
    }, {
        "code" : "suspended",
        "label" : "Suspendu",
        "order" : NumberInt(3)
    }, {
        "code" : "absent",
        "label" : "Incertain",
        "order" : NumberInt(4)
    } ],
    "listShapeConditionStatus" : [ {
        "code" : "vg",
        "label" : "Très bon",
        "order" : NumberInt(1)
    }, {
        "code" : "g",
        "label" : "Bon",
        "order" : NumberInt(2)
    }, {
        "code" : "M",
        "label" : "Moyen",
        "order" : NumberInt(3)
    }, {
        "code" : "F",
        "label" : "Faible",
        "order" : NumberInt(4)
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
    "listLaterality" : [ {
        "code" : "lateralityR",
        "label" : "Droitier",
        "order" : NumberInt(1)
    }, {
        "code" : "lateralityL",
        "label" : "Gaucher",
        "order" : NumberInt(2)
    }, {
        "code" : "lateralityA",
        "label" : "Ambidextre",
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
    } ],
    "listEventType" : [ {
        "code" : "championship",
        "label" : "Championnat",
        "order" : NumberInt(1)
    }, {
        "code" : "cup",
        "label" : "Coupe",
        "order" : NumberInt(2)
    }, {
        "code" : "friendlyGame",
        "label" : "Match amical",
        "order" : NumberInt(3)
    }],
    "parametersGame": {
        "nbMaxPlayers" : 12,
        "nbMinPlayers" : 4,
        "nbPeriod" : 2,
        "periodDuration" : 1800,
        "nbTimeout" : 3,
        "timeoutDuration" : 60,
        "yellowCardMax": 1,
        "exclusionTempo": 3,
        "halfTimeDuration": 600
    },
    "parametersSignup" : {
    	"players" : [{
    		"positionType" : "goalkeeper",
    		"quantity" : NumberInt(2)
    	}, {
    		"positionType" : "left-wingman",
    		"quantity" : NumberInt(2)
    	}, {
    		"positionType" : "right-wingman",
    		"quantity" : NumberInt(2)
    	}, {
    		"positionType" : "pivot",
    		"quantity" : NumberInt(1)
    	}, {
    		"positionType" : "center-backcourt",
    		"quantity" : NumberInt(1)
    	}, {
    		"positionType" : "left-backcourt",
    		"quantity" : NumberInt(2)
    	}, {
    		"positionType" : "right-backcourt",
    		"quantity" : NumberInt(2)
    	}]
    }
}); 
