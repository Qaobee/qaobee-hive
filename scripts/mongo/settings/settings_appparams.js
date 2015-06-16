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
 * INJECTION App parameters
 * V1.0
 * 
 * AUTHOR : Christophe kervella pour QaoBee
 */
//////////////////////////////////////////////////////////

/* 
 * Vidage de la collection AppParams
 */
db.AppParams.remove({});

var _id = new ObjectId().valueOf();
db.AppParams.insert({
    "_id": _id,
    "countryId" : "CNTR-250-FR-FRA",
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
    } ]
});