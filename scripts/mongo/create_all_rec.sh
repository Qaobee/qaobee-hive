#!/bin/bash

red='\e[0;31m'
green='\e[0;32m'
NC='\e[0m' # No Color
db=' -u hive -p qaobee2016 --verbose 51.254.217.60:27017/hive '

function pause(){
   read -p "$*"
}


echo -e "${red}PRODUCTION !!! Are you sure?${NC}"
pause 'Press [Enter] key to continue...'

echo -e "${green}****************************************************"
echo -e "Script de creation des collections dans la base Hive"
echo -e "****************************************************${NC}"

pathFile=`find . -name create_all.sh`
echo ${pathFile}
pathDir=`expr match "$pathFile" '\(.*\)\/create_all.sh'`
echo "Change directory to : $pathDir"
cd ${pathDir}
echo `pwd`

mongo hive --eval "db.getCollectionNames().forEach(function(c) { if (c.indexOf(\"system.\") == -1) db[c].drop(); })"
mongo ${db} **/*.js

mongo ${db} --eval "db.printCollectionStats()" | grep '\(ns\|count\)'

echo -e "${green}*************************************"
echo -e "Tous les scripts JS ont été traités !"
echo -e "*************************************${NC}"
