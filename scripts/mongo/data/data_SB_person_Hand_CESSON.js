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
 * INJECTION SB_Person
 * V1.2
 * 
 * This script creates documents for collections :
 * - SB_Person
 * 
 * AUTHOR : Christophe kervella pour QaoBee
 */
//////////////////////////////////////////////////////////
/* 
 * Vidage de la collection SB_Person Cesson handball
 */
db.SB_Person.remove({ "sandboxId" : "558b0efebd2e39cdab651e1f"});

/*******************************************************************************
 * Alimentation SB_Person Club A Handball
 * *************************************************************
 */

db.SB_Person.insert({
    "_id" : "550a05dadb8f8b6e2f51f4db",
    "sandboxId" : "558b0efebd2e39cdab651e1f",
    "name" : "Batinovic",
    "firstname" : "Damir",
    "avatar" : null,
    "gender" : "Homme",
    "birthdate" : NumberLong(556236000000),
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
        "weight" : NumberInt(95),
        "height" : NumberInt(193),
        "squadnumber" : NumberInt(33),
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
    "birthdate" : NumberLong(691714800000),
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
        "weight" :  NumberInt(108),
        "height" :  NumberInt(191),
        "squadnumber" : NumberInt(16),
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
    "birthdate" : NumberLong(601426800000),
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
        "weight" :  NumberInt(83),
        "height" :  NumberInt(189),
        "squadnumber" : NumberInt(13),
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
    "birthdate" : NumberLong(523663200000),
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
        "weight" :  NumberInt(77),
        "height" :  NumberInt(179),
        "squadnumber" : NumberInt(3),
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
    "birthdate" : NumberLong(444870000000),
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
        "weight" :  NumberInt(82),
        "height" :  NumberInt(183),
        "squadnumber" : NumberInt(35),
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
    "birthdate" : NumberLong(686271600000),
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
        "weight" :  NumberInt(106),
        "height" :  NumberInt(207),
        "squadnumber" : NumberInt(56),
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
    "birthdate" : NumberLong(564706800000),
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
        "weight" :  NumberInt(87),
        "height" :  NumberInt(185),
        "squadnumber" : NumberInt(11),
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
    "birthdate" : NumberLong(401580000000),
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
        "weight" :  NumberInt(93),
        "height" :  NumberInt(178),
        "squadnumber" : NumberInt(23),
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
    "birthdate" : NumberLong(712274400000),
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
        "weight" :  NumberInt(85),
        "height" :  NumberInt(180),
        "squadnumber" : NumberInt(15),
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
    "birthdate" : NumberLong(430437600000),
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
        "weight" :  NumberInt(101),
        "height" :  NumberInt(197),
        "squadnumber" : NumberInt(19),
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
    "birthdate" : NumberLong(486079200000),
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
        "weight" :  NumberInt(87),
        "height" :  NumberInt(190),
        "squadnumber" : NumberInt(4),
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
    "birthdate" : NumberLong(504745200000),
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
        "weight" :  NumberInt(94),
        "height" :  NumberInt(190),
        "squadnumber" : NumberInt(17),
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
    "birthdate" : NumberLong(529714800000),
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
        "weight" :  NumberInt(85),
        "height" :  NumberInt(188),
        "squadnumber" : NumberInt(5),
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
    "birthdate" : NumberLong(524527200000),
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
        "weight" :  NumberInt(100),
        "height" :  NumberInt(198),
        "squadnumber" : NumberInt(10),
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
        "squadnumber" : NumberInt(10),
        "weight" : NumberInt(84),
        "height" : NumberInt(81),
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
    "birthdate" : NumberLong(110415600000),
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
        "squadnumber" : NumberInt(10),
        "weight" : NumberInt(84),
        "height" : NumberInt(81),
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
    "birthdate" : NumberLong(425944800000),
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
        "squadnumber" : NumberInt(10),
        "weight" : NumberInt(84),
        "height" : NumberInt(81),
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
    "birthdate" : NumberLong(741564000000),
    "gender" : "Homme",
    "nationality" : "France",
    "birthCity" : "Brest, France",
    "avatar" : null
});
