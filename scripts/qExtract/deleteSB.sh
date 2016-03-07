#!/bin/bash

echo '****************************************'
echo 'Script de suppression de SandBox de Hive'
echo '****************************************'

###### CONSTANTS
RED='\e[31m'
LIGHT_RED='\e[91m'
RESET='\e[39m'

WORKINGDIR='delete'

###### FUNCTIONS 

function help
{
	echo "Utilisation du script "
	echo "-l --login         Login de l'utilisateur"
	echo "-i --userId        Identifiant de l'utilisateur"
	echo "-d --db            Database"
	echo "-o --output        Fichier de sortie"
	echo "-f --force-delete  Suppression sans confirmation"
	echo "-h --help          Aide"
	exit
}


###### MAIN
db=hive
fileoutput=delete.js
forceDelete=0

while [ "$1" != "" ]; do
    case $1 in
        -l | --login )          shift
                                login=$1
                                echo 'login : ' ${login}
                                ;;
        -i | --userId )         shift
        						userId=$1
        						echo 'userId : ' ${userId}
                                ;;
        -d | --database )       shift
        						db=$1
        						echo 'database : ' ${db}
        						;;
        -o | --output ) 		shift
        						fileoutput=$i
        						echo 'file Output : ' ${fileoutput}
        						;;
        -f | --force-delete )	forceDelete=1
        						echo 'Force Delete : ON'
        						;;	
        -h | --help )           help
                                exit
                                ;;
        * )                     exit 1
    esac
    shift
done

if [[ ( -z "$login" ) && ( -z "$userId" ) ]]
then
	echo -e "${RED}Paramètres manquants : login ou id obligatoire ${RESET}"
	help
fi

if [ ! -d ${WORKINGDIR} ]
then
	mkdir ${WORKINGDIR}
	echo "Répertoire '${WORKINGDIR}' créé"
else
	echo "Répertoire '${WORKINGDIR}' présent"
	rm ${WORKINGDIR}/*.json
fi


### Récup user
if [[ ! -z "$login" ]]
then
	mongo ${db} --quiet --eval "db.User.find({'account.login': '${login}'}).forEach(function(myDoc) { print(myDoc._id)})" > ${WORKINGDIR}/user.json.temp
	sed -e 's/.*/db.User.remove({"_id" : "&"});/' ${WORKINGDIR}/user.json.temp > ${WORKINGDIR}/01_user.json
	rm ${WORKINGDIR}/user.json.temp
	userId=$(mongo ${db} --quiet --eval "db.User.find({'account.login': '${login}'}, {'_id' : 1}).forEach( function(myDoc) { print(myDoc._id)})")
	echo 'userId : ' ${userId}
else 
	mongo ${db} --quiet --eval "db.User.find({'_id': '${userId}'}).forEach(function(myDoc) { print(myDoc._id)})" > ${WORKINGDIR}/user.json.temp
	sed -e 's/.*/db.User.remove({"_id" : "&"});/' ${WORKINGDIR}/user.json.temp > ${WORKINGDIR}/01_user.json
	rm ${WORKINGDIR}/user.json.temp
	userId=$(mongo ${db} --quiet --eval "db.User.find({'_id': '${userId}'}, {'_id' : 1}).forEach( function(myDoc) { print(myDoc._id)})")
	echo 'userId : ' ${userId}
fi

if [[ -z "$userId" ]]
then
	echo -e "${RED}Aucune donnée trouvée${RESET}"
	exit 1
fi

### Récup SandBox
mongo ${db} --quiet --eval "db.SB_SandBox.find({'owner' : '${userId}'}).forEach(function(myDoc) { print(myDoc._id)})" > ${WORKINGDIR}/SB_SandBox.json.temp
sed -e 's/.*/db.SB_SandBox.remove({"_id" : "&"});/' ${WORKINGDIR}/SB_SandBox.json.temp > ${WORKINGDIR}/02_SB_SandBox.json
rm ${WORKINGDIR}/SB_SandBox.json.temp
sbId=$(mongo ${db} --quiet --eval "db.SB_SandBox.find({'owner' : '${userId}'}, {'_id' : 1}).forEach( function(myDoc) { print(myDoc._id)})")
echo 'sbId   : ' ${sbId}

### Récup SandBox Config
mongo ${db} --quiet --eval "db.SB_SandBoxCfg.find({'sandbox._id': '${sbId}'}).forEach(function(myDoc) { print(myDoc._id)})" > ${WORKINGDIR}/SB_SandBoxCfg.json.temp
sed -e 's/.*/db.SB_SandBoxCfg.remove({"_id" : "&"});/' ${WORKINGDIR}/SB_SandBoxCfg.json.temp > ${WORKINGDIR}/03_SB_SandBoxCfg.json
rm ${WORKINGDIR}/SB_SandBoxCfg.json.temp
sbCfgId=$(mongo ${db} --quiet --eval "db.SB_SandBoxCfg.find({'sandbox._id': '${sbId}'}, {'_id' : 1}).forEach( function(myDoc) { print(myDoc._id)})")
echo 'sbCfgId: ' ${sbCfgId}

### Récup SandBox Person
mongo ${db} --quiet --eval "db.SB_Person.find({'sandboxId': '${sbId}'}).forEach(function(myDoc) { print(myDoc._id)})" > ${WORKINGDIR}/SB_Person.json.temp
sed -e 's/.*/db.SB_Person.remove({"_id" : "&"});/' ${WORKINGDIR}/SB_Person.json.temp > ${WORKINGDIR}/04_SB_Person.json
rm ${WORKINGDIR}/SB_Person.json.temp


### Récup SandBox Effective
mongo ${db} --quiet --eval "db.SB_Effective.find({'sandBoxCfgId': '${sbCfgId}'}).forEach(function(myDoc) { print(myDoc._id)})" > ${WORKINGDIR}/SB_Effective.json.temp
sed -e 's/.*/db.SB_Effective.remove({"_id" : "&"});/' ${WORKINGDIR}/SB_Effective.json.temp > ${WORKINGDIR}/05_SB_Effective.json
rm ${WORKINGDIR}/SB_Effective.json.temp

### Récup SandBox Team
mongo ${db} --quiet --eval "db.SB_Team.find({'sandboxId': '${sbId}'}).forEach(function(myDoc) { print(myDoc._id)})" > ${WORKINGDIR}/SB_Team.json.temp
sed -e 's/.*/db.SB_Team.remove({"_id" : "&"});/' ${WORKINGDIR}/SB_Team.json.temp > ${WORKINGDIR}/06_SB_Team.json
rm ${WORKINGDIR}/SB_Team.json.temp

### Récup SandBox Event
mongo ${db} --quiet --eval "db.SB_Event.find({'owner.sandboxId': '${sbId}'}).forEach(function(myDoc) { print(myDoc._id)})" > ${WORKINGDIR}/SB_Event.json.temp
sed -e 's/.*/db.SB_Event.remove({"_id" : "&"});/' ${WORKINGDIR}/SB_Event.json.temp > ${WORKINGDIR}/07_SB_Event.json
rm ${WORKINGDIR}/SB_Event.json.temp
mongo ${db} --quiet --eval "db.SB_Event.find({'owner.sandboxId': '${sbId}'}, {'_id' : 1}).forEach( function(myDoc) { print(myDoc._id)})" > ${WORKINGDIR}/SB_Event.txt

### Récup des SandBox Stats à partir de la liste des Events
if [ -f ${WORKINGDIR}/SB_Stats.json ]
then
	rm ${WORKINGDIR}/SB_Stats.json
fi
for line in $(cat ${WORKINGDIR}/SB_Event.txt); 
do 
	mongo ${db} --quiet --eval "db.SB_Stats.find({'eventId': '${line}'}).forEach(function(myDoc) { print(myDoc._id)})" >> ${WORKINGDIR}/SB_Stats.json.temp
done
if [ -f  ${WORKINGDIR}/SB_Stats.json.temp ]
then
	sed -e 's/.*/db.SB_Stats.remove({"_id" : "&"});/' ${WORKINGDIR}/SB_Stats.json.temp > ${WORKINGDIR}/08_SB_Stats.json
	rm ${WORKINGDIR}/SB_Stats.json.temp
fi

### Récup des SandBox Collecte à partir de la liste des Events
if [ -f ${WORKINGDIR}/SB_Collecte.json ]
then
	rm ${WORKINGDIR}/SB_Collecte.json
fi
for line in $(cat ${WORKINGDIR}/SB_Event.txt); 
do 
	mongo ${db} --quiet --eval "db.SB_Collecte.find({'eventRef._id': '${line}'}).forEach(function(myDoc) { print(myDoc._id)})" >> ${WORKINGDIR}/SB_Collecte.json.temp
done
if [ -f ${WORKINGDIR}/SB_Collecte.json.temp ]
then
	sed -e 's/.*/db.SB_Collecte.remove({"_id" : "&"});/' ${WORKINGDIR}/SB_Collecte.json.temp > ${WORKINGDIR}/09_SB_Collecte.json
	rm ${WORKINGDIR}/SB_Collecte.json.temp
fi

### Récup des SandBox Cycle
mongo ${db} --quiet --eval "db.SB_Cycle.find({'author._id': '${userId}'}).forEach(function(myDoc) { print(myDoc._id)})" > ${WORKINGDIR}/SB_Cycle.json.temp
sed -e 's/.*/db.SB_Person.remove({"_id" : "&"});/' ${WORKINGDIR}/SB_Cycle.json.temp > ${WORKINGDIR}/10_SB_Cycle.json
rm ${WORKINGDIR}/SB_Cycle.json.temp

### Récup des SandBox Exercise
mongo ${db} --quiet --eval "db.SB_Exercise.find({'author._id': '${userId}'}).forEach(function(myDoc) { print(myDoc._id)})" > ${WORKINGDIR}/SB_Exercise.json.temp
sed -e 's/.*/db.SB_Exercise.remove({"_id" : "&"});/' ${WORKINGDIR}/SB_Exercise.json.temp > ${WORKINGDIR}/11_SB_Exercise.json
rm ${WORKINGDIR}/SB_Exercise.json.temp

### Récup des SandBox Session
mongo ${db} --quiet --eval "db.SB_Session.find({'author._id': '${userId}'}).forEach(function(myDoc) { print(myDoc._id)})" > ${WORKINGDIR}/SB_Session.json.temp
sed -e 's/.*/db.SB_Session.remove({"_id" : "&"});/' ${WORKINGDIR}/SB_Session.json.temp > ${WORKINGDIR}/12_SB_Session.json
rm ${WORKINGDIR}/SB_Session.json.temp

for	 fichierJSon in `ls ${WORKINGDIR}/*.json`
do
	echo "`echo ${fichierJSon} | sed -e 's/^.*[0-9]\{2\}_\(.*\).json/\1/'`: `cat ${fichierJSon} | wc -l`"
done 


### Concat des fichiers
cat ${WORKINGDIR}/*.json > ${fileoutput}
echo "Nombre de requetes :  `cat ${fileoutput} | wc -l`"

if [[ $forceDelete == 0 ]]
then
	echo ""
	echo -e "${RED}Supprimer ce user et sandbox ? (o/n) ${RESET}"
	read validSuppr
	
	if [[ ( "$validSuppr" == "o" ) || ( "$validSuppr" == "O" ) ]]
	then
		mongo ${db} ${fileoutput}
		echo "Suppression effectuée !"
	else
		echo "ABANBON"
	fi
else
	echo ""
	echo -e "${RED} Suppression des données en cours${RESET}"
	mongo ${db} ${fileoutput}
	echo "Suppression effectuée !"
fi

