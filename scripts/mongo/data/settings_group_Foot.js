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
 * INJECTION Group
 * V1.0
 * 
 * AUTHOR : Christophe kervella pour QaoBee
 */
//////////////////////////////////////////////////////////
/* 
 * Vidage des collections
 */
db.Group.remove({});

/*
 * Alimentation group and group history : Groupe for category sen and Club Demo Football
 */

db.Group.insert(
{
    "_id" : "54fc59546d15ac75ae230be2",
    /* Start : 10/10/2014*/
    "startDate" : NumberLong(1412892000000),
    /* End : 31/12/2199*/
    "endDate" : NumberLong(7258028400000),
    "structureId" : "541168295971d35c1f2d1b60" ,
    "label" : "Groupe SEN A",
    "categoryAge" : {
        "code" : "sen",
        "label" : "Senior",
        "ageMax" : NumberInt(34),
        "ageMin" : NumberInt(20),
        "genre" : "Masculin"
    },
    "levelGame" : 
    {
        "code" : "nv1",
        "label" : "Elite",
        "order" : NumberInt(1)
    },
    "members" : ["541d3136f61fbf69868c1223","541d3136f61fbf69868c121f","541d3136f61fbf69868c1220",
                 "541d3136f61fbf69868c1221","541d3136f61fbf69868c1226","541d3136f61fbf69868c1228",
                 "541d3136f61fbf69868c122a","541d3136f61fbf69868c122c","541d3136f61fbf69868c1230",
                 "541d3136f61fbf69868c1222","541d3136f61fbf69868c122f"
    ]
});

db.Group.insert(
{
    "_id" : "54fc59546d15ac75ae230be3",
    /* Start : 01/01/1900*/
    "startDate" : NumberLong(-2208992400000),
    /* End : 09/10/2014*/
    "endDate" : NumberLong(1412805600000),
    "structureId" : "541168295971d35c1f2d1b60" ,
    "label" : "Groupe SEN A",
    "categoryAge" : {
        "code" : "sen",
        "label" : "Senior",
        "ageMax" : NumberInt(34),
        "ageMin" : NumberInt(20),
        "genre" : "Masculin"
    },
    "levelGame" : 
    {
        "code" : "nv1",
        "label" : "Elite",
        "order" : NumberInt(1)
    },
    "members" : ["541d3136f61fbf69868c121e","541d3136f61fbf69868c121f","541d3136f61fbf69868c1220",
                 "541d3136f61fbf69868c1221","541d3136f61fbf69868c1229"
    ]
});

db.Group.insert(
{
    "_id" : "54fc59546d15ac75ae230be4",
    /* Start : 01/01/1900*/
    "startDate" : NumberLong(-2208992400000),
    /* End : 31/12/2199*/
    "endDate" : NumberLong(7258028400000),
    "structureId" : "541168295971d35c1f2d1b60" ,
    "label" : "Groupe SEN B",
    "categoryAge" : {
        "code" : "sen",
        "label" : "Senior",
        "ageMax" : NumberInt(34),
        "ageMin" : NumberInt(20),
        "genre" : "Masculin"
    },
    "levelGame" : 
    {
        "code" : "nv3",
        "label" : "Régional",
        "order" : NumberInt(3)
    },
    "members" : ["541d3136f61fbf69868c121e","541d3136f61fbf69868c1231","541d3136f61fbf69868c122e",
                 "541d3136f61fbf69868c122d","541d3136f61fbf69868c122b","541d3136f61fbf69868c1227",
                 "541d3136f61fbf69868c1225","541d3136f61fbf69868c1224","541d3136f61fbf69868c121d",
                 "541d3136f61fbf69868c1229","541d3136f61fbf69868c1232"
    ]
});