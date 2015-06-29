#!/bin/sh

echo '****************************************************'
echo 'Script de creation des collections dans la base Hive'
echo '****************************************************'

#pathFile=`find . -name create_all.sh`
#echo $pathFile
#pathDir=`expr match "$pathFile" '\(.*\)\/create_all.sh'`
#pathDir=`expr "$pathFile" '\(.*\)\/create_all.sh'`
#echo "Change directory to : $pathDir"
#cd $pathDir

echo `pwd`

mongo hive --eval "db.dropDatabase()"
mongo hive **/*.js
mongo hive --eval "db.printCollectionStats()" | grep '\(ns\|count\)'


echo '*************************************'
echo 'Tous les scripts JS ont été traités !'
echo '*************************************'
