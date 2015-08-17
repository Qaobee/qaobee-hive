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
 * Vidage de la collection SB_Person (Club Dunkerque handball sen)
 */
db.SB_Person.remove({ "sandboxId" : "5591bb5e127472938a6444a2"});

/*******************************************************************************
 * Alimentation SB_Person Club A Handball
 * *************************************************************
 */

db.SB_Person.insert({
    "_id" : "541d2c5fb3f78c0317eea2be",
    "sandboxId" : "5591bb5e127472938a6444a2",
    "name" : "Gerard",
    "firstname" : "Vincent",
    "avatar" : null,
    "gender" : "Homme",
    "birthdate" : NumberLong(535071600000),
    "birthcity" : "Woippy",
    "nationality" : "France",
    "address" : {
        "formatedAddress" : "59240 Avenue de Rosendaël Jacques Collache, Dunkerque, France",
        "lat" : 51.0370455999999990,
        "lng" : 2.3964314000000000,
        "place" : "Avenue de Rosendaël Jacques Collache",
        "city" : "Dunkerque",
        "country" : "France",
        "zipcode" : "59240"
    },
    "contact" : {
        "home" : "05 04 00 00 01",
        "office" : "",
        "cellphone" : "07 06 00 00 01",
        "email" : "Nom01@toto.com"
    },

    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : NumberInt(97),
        "height" : NumberInt(188),
        "squadnumber" : NumberInt(1),
        "positionType" : "goalkeeper",
        "laterality" : "Droitier"
    }
});

db.SB_Person.insert({
    "_id" : "541d3136f61fbf69868c1214",
    "sandboxId" : "5591bb5e127472938a6444a2",
    "name" : "Annotel",
    "firstname" : "William",
    "avatar" : null,
    "gender" : "Homme",
    "birthdate" : NumberLong(417736800000),
    "birthcity" : "Lagny/Marne",
    "nationality" : "France",
    "address" : {
        "formatedAddress" : "59240 Avenue de Rosendaël Jacques Collache, Dunkerque, France",
        "lat" : 51.0370455999999990,
        "lng" : 2.3964314000000000,
        "place" : "Avenue de Rosendaël Jacques Collache",
        "city" : "Dunkerque",
        "country" : "France",
        "zipcode" : "59240"
    },
    "contact" : {
        "home" : "05 04 00 00 02",
        "office" : "",
        "cellphone" : "07 06 00 00 02",
        "email" : "Nom02@toto.com"
    },

    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : NumberInt(93),
        "height" : NumberInt(195),
        "squadnumber" : NumberInt(16),
        "positionType" : "goalkeeper",
        "laterality" : "Droitier"
    }
});

db.SB_Person.insert({
    "_id" : "541d3136f61fbf69868c1215",
    "sandboxId" : "5591bb5e127472938a6444a2",
    "name" : "Butto",
    "firstname" : "Baptiste",
    "avatar" : null,
    "gender" : "Homme",
    "birthdate" : NumberLong(540169200000),
    "birthcity" : "Algrange",
    "nationality" : "France",
    "address" : {
        "formatedAddress" : "59240 Avenue de Rosendaël Jacques Collache, Dunkerque, France",
        "lat" : 51.0370455999999990,
        "lng" : 2.3964314000000000,
        "place" : "Avenue de Rosendaël Jacques Collache",
        "city" : "Dunkerque",
        "country" : "France",
        "zipcode" : "59240"
    },
    "contact" : {
        "home" : "05 04 00 00 03",
        "office" : "",
        "cellphone" : "07 06 00 00 03",
        "email" : "Nom03@toto.com"
    },

    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : NumberInt(77),
        "height" : NumberInt(183),
        "squadnumber" : NumberInt(57),
        "positionType" : "left-wingman",
        "laterality" : "Droitier"
    }
});

db.SB_Person.insert({
    "_id" : "541d3136f61fbf69868c1216",
    "sandboxId" : "5591bb5e127472938a6444a2",
    "name" : "Touati",
    "firstname" : "Jaleleddine",
    "avatar" : null,
    "gender" : "Homme",
    "birthdate" : NumberLong(395272800000),
    "birthcity" : "Tunis",
    "nationality" : "Tunisie",
    "address" : {
        "formatedAddress" : "59240 Avenue de Rosendaël Jacques Collache, Dunkerque, France",
        "lat" : 51.0370455999999990,
        "lng" : 2.3964314000000000,
        "place" : "Avenue de Rosendaël Jacques Collache",
        "city" : "Dunkerque",
        "country" : "France",
        "zipcode" : "59240"
    },
    "contact" : {
        "home" : "05 04 00 00 04",
        "office" : "",
        "cellphone" : "07 06 00 00 04",
        "email" : "toutati@toto.com"
    },

    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : NumberInt(78),
        "height" : NumberInt(179),
        "squadnumber" : NumberInt(7),
        "positionType" : "right-wingman",
        "laterality" : "Gaucher"
    }
});

db.SB_Person.insert({
    "_id" : "541d3136f61fbf69868c1217",
    "sandboxId" : "5591bb5e127472938a6444a2",
    "name" : "Emonet",
    "firstname" : "Julien",
    "avatar" : null,
    "gender" : "Homme",
    "birthdate" : NumberLong(694479600000),
    "birthcity" : "Sartrouville",
    "nationality" : "France",
    "address" : {
        "formatedAddress" : "59240 Avenue de Rosendaël Jacques Collache, Dunkerque, France",
        "lat" : 51.0370455999999990,
        "lng" : 2.3964314000000000,
        "place" : "Avenue de Rosendaël Jacques Collache",
        "city" : "Dunkerque",
        "country" : "France",
        "zipcode" : "59240"
    },
    "contact" : {
        "home" : "05 04 00 00 05",
        "office" : "",
        "cellphone" : "07 06 00 00 05",
        "email" : "emonet@toto.com"
    },

    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : NumberInt(90),
        "height" : NumberInt(182),
        "squadnumber" : NumberInt(17),
        "positionType" : "left-wingman",
        "laterality" : "Droitier"
    }
});

db.SB_Person.insert({
    "_id" : "541d3136f61fbf69868c1218",
    "sandboxId" : "5591bb5e127472938a6444a2",
    "name" : "Afgour",
    "firstname" : "Benjamin",
    "avatar" : null,
    "gender" : "Homme",
    "birthdate" : NumberLong(670456800000),
    "birthcity" : "Rethel",
    "nationality" : "France",
    "address" : {
        "formatedAddress" : "59240 Avenue de Rosendaël Jacques Collache, Dunkerque, France",
        "lat" : 51.0370455999999990,
        "lng" : 2.3964314000000000,
        "place" : "Avenue de Rosendaël Jacques Collache",
        "city" : "Dunkerque",
        "country" : "France",
        "zipcode" : "59240"
    },
    "contact" : {
        "home" : "05 04 00 00 06",
        "office" : "",
        "cellphone" : "07 06 00 00 06",
        "email" : "afgour@toto.com"
    },

    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : NumberInt(105),
        "height" : NumberInt(195),
        "squadnumber" : NumberInt(3),
        "positionType" : "pivot",
        "laterality" : "Droitier"
    }
});

db.SB_Person.insert({
    "_id" : "541d3136f61fbf69868c1219",
    "sandboxId" : "5591bb5e127472938a6444a2",
    "name" : "Mokrani",
    "firstname" : "Mohamed",
    "avatar" : null,
    "gender" : "Homme",
    "birthdate" : NumberLong(349743600000),
    "birthcity" : "Ivry sur Seine",
    "nationality" : "Algérie",
    "address" : {
        "formatedAddress" : "59240 Avenue de Rosendaël Jacques Collache, Dunkerque, France",
        "lat" : 51.0370455999999990,
        "lng" : 2.3964314000000000,
        "place" : "Avenue de Rosendaël Jacques Collache",
        "city" : "Dunkerque",
        "country" : "France",
        "zipcode" : "59240"
    },
    "contact" : {
        "home" : "05 04 00 00 07",
        "office" : "",
        "cellphone" : "07 06 00 00 07",
        "email" : "mokrani@toto.com"
    },

    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : NumberInt(94),
        "height" : NumberInt(186),
        "squadnumber" : NumberInt(46),
        "positionType" : "pivot",
        "laterality" : "Droitier"
    }
});

db.SB_Person.insert({
    "_id" : "541d3136f61fbf69868c121a",
    "sandboxId" : "5591bb5e127472938a6444a2",
    "name" : "Causse",
    "firstname" : "Théophile",
    "avatar" : null,
    "gender" : "Homme",
    "birthdate" : NumberLong(718844400000),
    "birthcity" : "Port-au-Prince",
    "nationality" : "France",
    "address" : {
        "formatedAddress" : "59240 Avenue de Rosendaël Jacques Collache, Dunkerque, France",
        "lat" : 51.0370455999999990,
        "lng" : 2.3964314000000000,
        "place" : "Avenue de Rosendaël Jacques Collache",
        "city" : "Dunkerque",
        "country" : "France",
        "zipcode" : "59240"
    },
    "contact" : {
        "home" : "05 04 00 00 08",
        "office" : "",
        "cellphone" : "07 06 00 00 08",
        "email" : "causse@toto.com"
    },

    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : NumberInt(72),
        "height" : NumberInt(180),
        "squadnumber" : NumberInt(9),
        "positionType" : "right-wingman",
        "laterality" : "Gaucher"
    }
});

db.SB_Person.insert({
    "_id" : "541d3136f61fbf69868c121b",
    "sandboxId" : "5591bb5e127472938a6444a2",
    "name" : "Grocaut",
    "firstname" : "Mickael",
    "avatar" : null,
    "gender" : "Homme",
    "birthdate" : NumberLong(325548000000),
    "birthcity" : "Hénin-Beaumont",
    "nationality" : "France",
    "address" : {
        "formatedAddress" : "59240 Avenue de Rosendaël Jacques Collache, Dunkerque, France",
        "lat" : 51.0370455999999990,
        "lng" : 2.3964314000000000,
        "place" : "Avenue de Rosendaël Jacques Collache",
        "city" : "Dunkerque",
        "country" : "France",
        "zipcode" : "59240"
    },
    "contact" : {
        "home" : "05 04 00 00 09",
        "office" : "",
        "cellphone" : "07 06 00 00 09",
        "email" : "grocaut@toto.com"
    },

    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : NumberInt(103),
        "height" : NumberInt(194),
        "squadnumber" : NumberInt(21),
        "positionType" : "pivot",
        "laterality" : "Droitier"
    }
});

db.SB_Person.insert({
    "_id" : "541d3136f61fbf69868c121c",
    "sandboxId" : "5591bb5e127472938a6444a2",
    "name" : "Nagy",
    "firstname" : "Kornel",
    "avatar" : null,
    "gender" : "Homme",
    "birthdate" : NumberLong(532911600000),
    "birthcity" : "Püspökladany",
    "nationality" : "Hongrie",
    "address" : {
        "formatedAddress" : "59240 Avenue de Rosendaël Jacques Collache, Dunkerque, France",
        "lat" : 51.0370455999999990,
        "lng" : 2.3964314000000000,
        "place" : "Avenue de Rosendaël Jacques Collache",
        "city" : "Dunkerque",
        "country" : "France",
        "zipcode" : "59240"
    },
    "contact" : {
        "home" : "05 04 00 00 10",
        "office" : "",
        "cellphone" : "07 06 00 00 10",
        "email" : "nagy@toto.com"
    },

    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : NumberInt(100),
        "height" : NumberInt(195),
        "squadnumber" : NumberInt(6),
        "positionType" : "left-backcourt",
        "laterality" : "Gaucher"
    }
});

db.SB_Person.insert({
    "_id" : "5509f71adb8f8b6e2f51f4d4",
    "sandboxId" : "5591bb5e127472938a6444a2",
    "name" : "Pejovic",
    "firstname" : "Zarko",
    "avatar" : null,
    "gender" : "Homme",
    "birthdate" : NumberLong(506991600000),
    "birthcity" : "Mojkovac",
    "nationality" : "Moldovie",
    "address" : {
        "formatedAddress" : "59240 Avenue de Rosendaël Jacques Collache, Dunkerque, France",
        "lat" : 51.0370455999999990,
        "lng" : 2.3964314000000000,
        "place" : "Avenue de Rosendaël Jacques Collache",
        "city" : "Dunkerque",
        "country" : "France",
        "zipcode" : "59240"
    },
    "contact" : {
        "home" : "05 04 00 00 10",
        "office" : "",
        "cellphone" : "07 06 00 00 10",
        "email" : "pejovic@toto.com"
    },

    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : NumberInt(94),
        "height" : NumberInt(190),
        "squadnumber" : NumberInt(19),
        "positionType" : "center-backcourt",
        "laterality" : "Droitier"
    }
});

db.SB_Person.insert({
    "_id" : "5509f71adb8f8b6e2f51f4d5",
    "sandboxId" : "5591bb5e127472938a6444a2",
    "name" : "Guillard",
    "firstname" : "Romain",
    "avatar" : null,
    "gender" : "Homme",
    "birthdate" : NumberLong(506041200000),
    "birthcity" : "Suresnes",
    "nationality" : "France",
    "address" : {
        "formatedAddress" : "59240 Avenue de Rosendaël Jacques Collache, Dunkerque, France",
        "lat" : 51.0370455999999990,
        "lng" : 2.3964314000000000,
        "place" : "Avenue de Rosendaël Jacques Collache",
        "city" : "Dunkerque",
        "country" : "France",
        "zipcode" : "59240"
    },
    "contact" : {
        "home" : "05 04 00 00 10",
        "office" : "",
        "cellphone" : "07 06 00 00 10",
        "email" : "guillard@toto.com"
    },

    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : NumberInt(100),
        "height" : NumberInt(190),
        "squadnumber" : NumberInt(19),
        "positionType" : "center-backcourt",
        "laterality" : "Droitier"
    }
});

db.SB_Person.insert({
    "_id" : "5509f71adb8f8b6e2f51f4d6",
    "sandboxId" : "5591bb5e127472938a6444a2",
    "name" : "Lamon",
    "firstname" : "Bastien",
    "avatar" : null,
    "gender" : "Homme",
    "birthdate" : NumberLong(348620400000),
    "birthcity" : "Roubaix",
    "nationality" : "France",
    "address" : {
        "formatedAddress" : "59240 Avenue de Rosendaël Jacques Collache, Dunkerque, France",
        "lat" : 51.0370455999999990,
        "lng" : 2.3964314000000000,
        "place" : "Avenue de Rosendaël Jacques Collache",
        "city" : "Dunkerque",
        "country" : "France",
        "zipcode" : "59240"
    },
    "contact" : {
        "home" : "05 04 00 00 10",
        "office" : "",
        "cellphone" : "07 06 00 00 10",
        "email" : "lamon@toto.com"
    },

    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : NumberInt(80),
        "height" : NumberInt(186),
        "squadnumber" : NumberInt(4),
        "positionType" : "center-backcourt",
        "laterality" : "Droitier"
    }
});

db.SB_Person.insert({
    "_id" : "5509f71adb8f8b6e2f51f4d7",
    "sandboxId" : "5591bb5e127472938a6444a2",
    "name" : "Paczkowski",
    "firstname" : "Pawel",
    "avatar" : null,
    "gender" : "Homme",
    "birthdate" : NumberLong(740008800000),
    "birthcity" : "Swiecie",
    "nationality" : "Pologne",
    "address" : {
        "formatedAddress" : "59240 Avenue de Rosendaël Jacques Collache, Dunkerque, France",
        "lat" : 51.0370455999999990,
        "lng" : 2.3964314000000000,
        "place" : "Avenue de Rosendaël Jacques Collache",
        "city" : "Dunkerque",
        "country" : "France",
        "zipcode" : "59240"
    },
    "contact" : {
        "home" : "05 04 00 00 10",
        "office" : "",
        "cellphone" : "07 06 00 00 10",
        "email" : "paczkowski@toto.com"
    },

    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : NumberInt(93),
        "height" : NumberInt(195),
        "squadnumber" : NumberInt(32),
        "positionType" : "right-backcourt",
        "laterality" : "Gaucher"
    }
});

db.SB_Person.insert({
    "_id" : "550a02acdb8f8b6e2f51f4da",
    "sandboxId" : "5591bb5e127472938a6444a2",
    "name" : "Soudry",
    "firstname" : "Pierre",
    "avatar" : null,
    "gender" : "Homme",
    "birthdate" : NumberLong(575420400000),
    "birthcity" : "Amiens",
    "nationality" : "France",
    "address" : {
        "formatedAddress" : "59240 Avenue de Rosendaël Jacques Collache, Dunkerque, France",
        "lat" : 51.0370455999999990,
        "lng" : 2.3964314000000000,
        "place" : "Avenue de Rosendaël Jacques Collache",
        "city" : "Dunkerque",
        "country" : "France",
        "zipcode" : "59240"
    },
    "contact" : {
        "home" : "05 04 00 00 10",
        "office" : "",
        "cellphone" : "07 06 00 00 10",
        "email" : "soudry@toto.com"
    },

    "status" : {
        "availability" : {
            "value" : "available",
            "cause" : "available"
        },
        "stateForm" : "middling",
        "weight" : NumberInt(81),
        "height" : NumberInt(183),
        "squadnumber" : NumberInt(19),
        "positionType" : "right-backcourt",
        "laterality" : "Gaucher"
    }
});
