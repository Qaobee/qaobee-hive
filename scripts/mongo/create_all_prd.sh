#!/bin/bash

red='\e[0;31m'
green='\e[0;32m'
NC='\e[0m' # No Color
db=' -u admin -p F-9ATqk2yWzs --verbose localhost:51081/hive '

function pause(){
   read -p "$*"
}


echo -e "${red}PRODUCTION !!! Don't forget rhc port-forward -a hive${NC}"
pause 'Press [Enter] key to continue...'

echo -e "${green}****************************************************"
echo -e "Script de creation des collections dans la base Hive"
echo -e "****************************************************${NC}"

pathFile=`find . -name create_all.sh`
echo $pathFile
pathDir=`expr match "$pathFile" '\(.*\)\/create_all.sh'`
echo "Change directory to : $pathDir"
cd $pathDir
echo `pwd`

#mongo ${db} --eval "db.dropDatabase()"
mongo ${db} *.js
mongo ${db} **/*.js

mongo ${db} --eval "db.printCollectionStats()" | grep '\(ns\|count\)'

echo -e "${green}*************************************"
echo -e "Tous les scripts JS ont été traités !"
echo -e "*************************************${NC}"
