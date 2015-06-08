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