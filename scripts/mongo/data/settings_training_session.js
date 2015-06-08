//////////////////////////////////////////////////////////
/* 
 * SCRIPT MONGO DB
 * INJECTION Session
 * V1.0
 * 
 * AUTHOR : Mohamed EL MARZGIOUI for QaoBee
 */
//////////////////////////////////////////////////////////
/* 
 * emptying of collections
 */
   db.Session.remove({"structureId" : "541168295971d35c1f2d1b60"});

/*
 * Data tests of session
 */

/* 0 */
   db.Session.insert({
       "_id" : "4d456aaa-85c9-4de8-9e56-96ba29b80221",
       "activityId" : "ACT-FOOT",
       "structureId" : "541168295971d35c1f2d1b60",
       "categoryAge" : {
           "code" : "sen",
           "label" : "Senior Gars",
           "ageMax" : 150,
           "ageMin" : 18,
           "genre" : "Masculin",
           "order" : 1
       },
       "author" : {
           "_id" : "54160977d5bd065a1bb1e565",
           "name" : "Hidalgo",
           "firstname" : "Michel"
       },
       "historyList" : [ 
           {
               "author" : {
                   "_id" : "54160977d5bd065a1bb1e565",
                   "name" : "Hidalgo",
                   "firstname" : "Michel"
               },
               "historyType" : "creation",
               "date" : "2015-04-22T20:50:13.487Z"
           }
       ],
       "exerciseSessionList" : [ 
           {
               "_id" : "b7d1c1c3-378f-44fc-825f-c21ef3296751",
               "activityId" : "ACT-FOOT",
               "structureId" : "541168295971d35c1f2d1b60",
               "categoryAge" : {
                   "code" : "sen",
                   "label" : "Senior Gars",
                   "ageMax" : 150,
                   "ageMin" : 18,
                   "genre" : "Masculin",
                   "order" : 1,
                   "listStaffMember" : [ 
                       {
                           "personId" : "54160977d5bd065a1bb1e565",
                           "role" : {
                               "code" : "coach",
                               "label" : "Coach"
                           }
                       }, 
                       {
                           "personId" : "54160977d5bd065a1bb1e568",
                           "role" : {
                               "code" : "acoach",
                               "label" : "Coach Adjoint"
                           }
                       }
                   ]
               },
               "author" : {
                   "_id" : "54160977d5bd065a1bb1e565",
                   "name" : "Hidalgo",
                   "firstname" : "Michel"
               },
               "file" : {},
               "historyList" : [ 
                   {
                       "author" : {
                           "_id" : "54160977d5bd065a1bb1e565",
                           "name" : "Hidalgo",
                           "firstname" : "Michel"
                       },
                       "historyType" : "creation",
                       "date" : "2015-04-22T20:19:34.247Z"
                   }
               ],
               "label" : "Exo 2",
               "duration" : "20",
               "theme" : {
                   "label" : "NewTheme 2"
               },
               "description" : "Jeu d'échauffement",
               "settingUp" : "Trois chasseurs, le reste les lapins",
               "instructions" : "Si le chasseur touche le lapin, le lapin se met en position gainage",
               "criteriaSuccess" : "Mise en place d'une organisation des chasseurs",
               "objectif" : "RAS",
               "variablesExecution" : "RAS",
               "observation" : "Observations",
               "dateCreate" : "2015-04-22T20:19:34.247Z"
           }, 
           {
               "_id" : "b7d1c1c3-378f-44fc-825f-c21ef3296751",
               "activityId" : "ACT-FOOT",
               "structureId" : "541168295971d35c1f2d1b60",
               "categoryAge" : {
                   "code" : "sen",
                   "label" : "Senior Gars",
                   "ageMax" : 150,
                   "ageMin" : 18,
                   "genre" : "Masculin",
                   "order" : 1,
                   "listStaffMember" : [ 
                       {
                           "personId" : "54160977d5bd065a1bb1e565",
                           "role" : {
                               "code" : "coach",
                               "label" : "Coach"
                           }
                       }, 
                       {
                           "personId" : "54160977d5bd065a1bb1e568",
                           "role" : {
                               "code" : "acoach",
                               "label" : "Coach Adjoint"
                           }
                       }
                   ]
               },
               "author" : {
                   "_id" : "54160977d5bd065a1bb1e565",
                   "name" : "Hidalgo",
                   "firstname" : "Michel"
               },
               "file" : {},
               "historyList" : [ 
                   {
                       "author" : {
                           "_id" : "54160977d5bd065a1bb1e565",
                           "name" : "Hidalgo",
                           "firstname" : "Michel"
                       },
                       "historyType" : "creation",
                       "date" : "2015-04-22T20:19:34.247Z"
                   }
               ],
               "label" : "Exo 2",
               "duration" : "20",
               "theme" : {
                   "label" : "NewTheme 2"
               },
               "description" : "Jeu d'échauffement",
               "settingUp" : "Trois chasseurs, le reste les lapins",
               "instructions" : "Si le chasseur touche le lapin, le lapin se met en position gainage",
               "criteriaSuccess" : "Mise en place d'une organisation des chasseurs",
               "objectif" : "RAS",
               "variablesExecution" : "RAS",
               "observation" : "Observations",
               "dateCreate" : "2015-04-22T20:19:34.247Z"
           }
       ],
       "label" : "ATT 0-6",
       "duration" : "90",
       "theme" : {
           "label" : "New Theme"
       },
       "subTheme" : {
           "label" : "sous thème"
       },
       "description" : "Description de la description",
       "observation" : "une nouvelle observation",
       "dateCreate" : "2015-04-22T20:50:13.487Z"
   });

   db.Session.insert({
       "_id" : "e7bebc2c-918f-4a33-b46a-1770aedb9b32",
       "categoryAge" : {
           "code" : "sen",
           "label" : "Senior Gars",
           "ageMax" : 150,
           "ageMin" : 18,
           "genre" : "Masculin",
           "order" : 1
       },
       "author" : {
           "_id" : "54160977d5bd065a1bb1e565",
           "name" : "Hidalgo",
           "firstname" : "Michel"
       },
       "theme" : {
           "label" : "RR"
       },
       "subTheme" : {
           "label" : "RRR"
       },
       "historyList" : [ 
           {
               "author" : {
                   "_id" : "54160977d5bd065a1bb1e565",
                   "name" : "Hidalgo",
                   "firstname" : "Michel"
               },
               "historyType" : "creation",
               "date" : "2015-05-02T10:46:46.733Z"
           }
       ],
       "exerciseSessionList" : [ 
           {
               "_id" : "9b832208-5958-43c8-9e1f-53fd63762b6c",
               "subTheme" : {
                   "label" : "RRR"
               },
               "categoryAge" : {
                   "code" : "sen",
                   "label" : "Senior Gars",
                   "ageMax" : 150,
                   "ageMin" : 18,
                   "genre" : "Masculin",
                   "order" : 1,
                   "listStaffMember" : [ 
                       {
                           "personId" : "54160977d5bd065a1bb1e565",
                           "role" : {
                               "code" : "coach",
                               "label" : "Coach"
                           }
                       }, 
                       {
                           "personId" : "54160977d5bd065a1bb1e568",
                           "role" : {
                               "code" : "acoach",
                               "label" : "Coach Adjoint"
                           }
                       }
                   ]
               },
               "theme" : {
                   "label" : "RR"
               },
               "author" : {
                   "_id" : "54160977d5bd065a1bb1e565",
                   "name" : "Hidalgo",
                   "firstname" : "Michel"
               },
               "file" : {},
               "historyList" : [ 
                   {
                       "author" : {
                           "_id" : "54160977d5bd065a1bb1e565",
                           "name" : "Hidalgo",
                           "firstname" : "Michel"
                       },
                       "historyType" : "creation",
                       "date" : "2015-05-01T07:16:16.447Z"
                   }
               ],
               "activityId" : "ACT-FOOT",
               "structureId" : "541168295971d35c1f2d1b60",
               "label" : "RR",
               "description" : "eer ere er er",
               "settingUp" : "KJkd dkdjfkdf",
               "variablesExecution" : "sdfsdkfsd sdkf",
               "instructions" : "sdfksdjfsd",
               "criteriaSuccess" : "fffffffffff ff ff f",
               "objectif" : "ddf dfdf",
               "duration" : "15",
               "dateCreate" : "2015-05-01T07:16:16.447Z"
           }, 
           {
               "_id" : "9b832208-5958-43c8-9e1f-53fd63762b6c",
               "subTheme" : {
                   "label" : "RRR"
               },
               "categoryAge" : {
                   "code" : "sen",
                   "label" : "Senior Gars",
                   "ageMax" : 150,
                   "ageMin" : 18,
                   "genre" : "Masculin",
                   "order" : 1,
                   "listStaffMember" : [ 
                       {
                           "personId" : "54160977d5bd065a1bb1e565",
                           "role" : {
                               "code" : "coach",
                               "label" : "Coach"
                           }
                       }, 
                       {
                           "personId" : "54160977d5bd065a1bb1e568",
                           "role" : {
                               "code" : "acoach",
                               "label" : "Coach Adjoint"
                           }
                       }
                   ]
               },
               "theme" : {
                   "label" : "RR"
               },
               "author" : {
                   "_id" : "54160977d5bd065a1bb1e565",
                   "name" : "Hidalgo",
                   "firstname" : "Michel"
               },
               "file" : {},
               "historyList" : [ 
                   {
                       "author" : {
                           "_id" : "54160977d5bd065a1bb1e565",
                           "name" : "Hidalgo",
                           "firstname" : "Michel"
                       },
                       "historyType" : "creation",
                       "date" : "2015-05-01T07:16:16.447Z"
                   }
               ],
               "activityId" : "ACT-FOOT",
               "structureId" : "541168295971d35c1f2d1b60",
               "label" : "RR",
               "description" : "eer ere er er",
               "settingUp" : "KJkd dkdjfkdf",
               "variablesExecution" : "sdfsdkfsd sdkf",
               "instructions" : "sdfksdjfsd",
               "criteriaSuccess" : "fffffffffff ff ff f",
               "objectif" : "ddf dfdf",
               "duration" : "15",
               "dateCreate" : "2015-05-01T07:16:16.447Z"
           }, 
           {
               "_id" : "9b832208-5958-43c8-9e1f-53fd63762b6c",
               "subTheme" : {
                   "label" : "RRR"
               },
               "categoryAge" : {
                   "code" : "sen",
                   "label" : "Senior Gars",
                   "ageMax" : 150,
                   "ageMin" : 18,
                   "genre" : "Masculin",
                   "order" : 1,
                   "listStaffMember" : [ 
                       {
                           "personId" : "54160977d5bd065a1bb1e565",
                           "role" : {
                               "code" : "coach",
                               "label" : "Coach"
                           }
                       }, 
                       {
                           "personId" : "54160977d5bd065a1bb1e568",
                           "role" : {
                               "code" : "acoach",
                               "label" : "Coach Adjoint"
                           }
                       }
                   ]
               },
               "theme" : {
                   "label" : "RR"
               },
               "author" : {
                   "_id" : "54160977d5bd065a1bb1e565",
                   "name" : "Hidalgo",
                   "firstname" : "Michel"
               },
               "file" : {},
               "historyList" : [ 
                   {
                       "author" : {
                           "_id" : "54160977d5bd065a1bb1e565",
                           "name" : "Hidalgo",
                           "firstname" : "Michel"
                       },
                       "historyType" : "creation",
                       "date" : "2015-05-01T07:16:16.447Z"
                   }
               ],
               "activityId" : "ACT-FOOT",
               "structureId" : "541168295971d35c1f2d1b60",
               "label" : "RR",
               "description" : "eer ere er er",
               "settingUp" : "KJkd dkdjfkdf",
               "variablesExecution" : "sdfsdkfsd sdkf",
               "instructions" : "sdfksdjfsd",
               "criteriaSuccess" : "fffffffffff ff ff f",
               "objectif" : "ddf dfdf",
               "duration" : "15",
               "dateCreate" : "2015-05-01T07:16:16.447Z"
           }
       ],
       "activityId" : "ACT-FOOT",
       "structureId" : "541168295971d35c1f2d1b60",
       "label" : "Test",
       "duration" : "90",
       "description" : "sdfsdlfkj",
       "observation" : "RAS",
       "dateCreate" : "2015-05-02T10:46:46.733Z"
   });