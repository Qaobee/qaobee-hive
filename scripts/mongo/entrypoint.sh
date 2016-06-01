#!/bin/bash
export PATH=/opt/mongodb/bin:$PATH
set -m

mongodb_cmd="mongod"
cmd="$mongodb_cmd"
if [ "$AUTH" == "yes" ]; then
    cmd="$cmd --auth"
fi

if [ "$JOURNALING" == "no" ]; then
    cmd="$cmd --nojournal"
fi

if [ "$OPLOG_SIZE" != "" ]; then
    cmd="$cmd --oplogSize $OPLOG_SIZE"
fi

$cmd --smallfiles &

if [ ! -f /data/db/.mongodb_password_set ]; then
    /home/mongo/set_mongodb_password.sh
fi

fg
