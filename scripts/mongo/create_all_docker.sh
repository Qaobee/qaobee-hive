#!/bin/sh

echo '****************************************************'
echo 'Script de creation des collections dans la base Hive'
echo '    ---> Docker edition                             '
echo '****************************************************'

export db="hive -u hive -p hive --verbose --host $1 --port $2"
echo ' '
echo '1) Suppression de toutes les collections'
mongo ${db} --eval "db.getCollectionNames().forEach(function(c) { if (c.indexOf(\"system.\") == -1) db[c].drop(); })"
mongo ${db} --eval "db.printCollectionStats()" | grep '\("ns"\|"count"\)'

echo ' '
echo '2) Import des fichiers *.js'
mongo ${db} **/*.js

echo ' '
echo '3) Verifications'
mongo ${db} --eval "db.printCollectionStats()" | grep '\("ns"\|"count"\)'


echo '*************************************'
echo 'Tous les scripts JS ont été traités !'
echo '*************************************'
