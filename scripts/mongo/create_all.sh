#!/bin/sh

echo '****************************************************'
echo 'Script de creation des collections dans la base Hive'
echo '****************************************************'

pathFile=`find . -name create_all.sh`
echo $pathFile
pathDir=`expr match "$pathFile" '\(.*\)\/create_all.sh'`
echo "Change directory to : $pathDir"
cd $pathDir
echo `pwd`

echo `pwd`
mongo hive --eval "use hive"
mongo hive --eval "db.getCollectionNames().forEach(function(c) { if (c.indexOf(\"system.\") == -1) db[c].drop(); })"
mongo hive **/*.js
mongo ${db} *.js
mongo hive --eval "db.printCollectionStats()" | grep '\(ns\|count\)'


echo '*************************************'
echo 'Tous les scripts JS ont été traités !'
echo '*************************************'
