//////////////////////////////////////////////////////////
/* 
 * SCRIPT MONGO DB
 * INJECTION Cycle
 * V1.0
 * 
 * AUTHOR : Mohamed EL MARZGIOUI for QaoBee
 */
//////////////////////////////////////////////////////////
/* 
 * emptying of collections
 */
   db.Theme.remove({"structureId" : "541168295971d35c1f2d1b60"});

/*
 * Data tests of cycle
 */

/* 0 */
  
   db.Theme.insert({
       "_id" : "4654de62-ab15-4fcd-84cd-33689f0edeb6",
       "subThemesList" : [ 
           {
               "label" : "NewsubTheme",
               "description" : ""
           }
       ],
       "author" : {
           "_id" : "54160977d5bd065a1bb1e565",
           "name" : "Hidalgo",
           "firstname" : "Michel",
           "avatar" : null,
           "contact" : {
               "home" : "04 04 00 00 01",
               "office" : "",
               "cellphone" : "07 06 00 00 01",
               "email" : "Hidalgo@toto.com",
               "fax" : null
           }
       },
       "activityId" : "ACT-FOOT",
       "structureId" : "541168295971d35c1f2d1b60",
       "label" : "NewTheme 1",
       "description" : "RAS"
   });
   
   db.Theme.insert({
       "_id" : "e3bcb9a9-2b1f-4bcb-a95d-a14b93954634",
       "subThemesList" : [ 
           {
               "label" : "NewsubTheme",
               "description" : ""
           }
       ],
       "author" : {
           "_id" : "54160977d5bd065a1bb1e565",
           "name" : "Hidalgo",
           "firstname" : "Michel",
           "avatar" : null,
           "contact" : {
               "home" : "04 04 00 00 01",
               "office" : "",
               "cellphone" : "07 06 00 00 01",
               "email" : "Hidalgo@toto.com",
               "fax" : null
           }
       },
       "activityId" : "ACT-FOOT",
       "structureId" : "541168295971d35c1f2d1b60",
       "label" : "NewTheme 2",
       "description" : "RAS"
   });
   
   db.Theme.insert({
       "_id" : "e3bcb9a9-2b1f-4bcb-a95d-a14b93954633",
       "subThemesList" : [ 
           {
               "label" : "NewsubTheme",
               "description" : ""
           }
       ],
       "author" : {
           "_id" : "54160977d5bd065a1bb1e565",
           "name" : "Hidalgo",
           "firstname" : "Michel",
           "avatar" : null,
           "contact" : {
               "home" : "04 04 00 00 01",
               "office" : "",
               "cellphone" : "07 06 00 00 01",
               "email" : "Hidalgo@toto.com",
               "fax" : null
           }
       },
       "activityId" : "ACT-FOOT",
       "structureId" : "541168295971d35c1f2d1b60",
       "label" : "NewTheme 3",
       "description" : "RAS"
   });
   
   db.Theme.insert({
       "_id" : "dc6ad62a-3750-4976-9203-97649dfdf8aa",
       "label" : "RR",
       "subThemesList" : [ 
           {
               "label" : "RRR"
           }
       ],
       "author" : {
           "_id" : "54160977d5bd065a1bb1e565",
           "name" : "Hidalgo",
           "firstname" : "Michel",
           "avatar" : null,
           "contact" : {
               "home" : "04 04 00 00 01",
               "office" : "",
               "cellphone" : "07 06 00 00 01",
               "email" : "Hidalgo@toto.com",
               "fax" : null
           }
       },
       "activityId" : "ACT-FOOT",
       "structureId" : "541168295971d35c1f2d1b60"
   });
   
   db.Theme.insert({
       "_id" : "6fe8307e-b1e1-4694-9fd1-aae1ec7da9e4",
       "activityId" : "ACT-FOOT",
       "label" : "RIR",
       "subThemesList" : [ 
           {
               "label" : "RIRIR"
           }
       ],
       "author" : {
           "_id" : "54160977d5bd065a1bb1e565",
           "name" : "Hidalgo",
           "firstname" : "Michel",
           "avatar" : null,
           "contact" : {
               "home" : "04 04 00 00 01",
               "office" : "",
               "cellphone" : "07 06 00 00 01",
               "email" : "Hidalgo@toto.com",
               "fax" : null
           }
       },
       "structureId" : "541168295971d35c1f2d1b60"
   });
   