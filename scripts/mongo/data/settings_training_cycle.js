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
db.Cycle.remove({"_id" : "c3089ddf-b456-4fdc-9484-ea5609adc225"});

/*
 * Data tests of cycle
 */
  
   db.Cycle.insert(
           /* 0 */
           {
               "_id" : "c3089ddf-b456-4fdc-9484-ea5609adc225",
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
                   "label" : "RIR"
               },
               "subTheme" : {
                   "label" : "RIRIR"
               },
               "historyList" : [ 
                   {
                       "author" : {
                           "_id" : "54160977d5bd065a1bb1e565",
                           "name" : "Hidalgo",
                           "firstname" : "Michel"
                       },
                       "historyType" : "creation",
                       "date" : "2015-05-02T11:22:43.886Z"
                   }
               ],
               "sessionCycleList" : [ 
                   {
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
                       "label" : "ATT 1-5",
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
                   }, 
                   {
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
                       "label" : "ATT 1-5",
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
                   }, 
                   {
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
                       "label" : "ATT 1-5",
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
                   }
               ],
               "activityId" : "ACT-FOOT",
               "structureId" : "541168295971d35c1f2d1b60",
               "label" : "test cycle",
               "startDate" : "2015-05-02",
               "endDate" : "2015-06-27",
               "description" : "Description",
               "dateCreate" : "2015-05-02T11:22:43.886Z"
           });