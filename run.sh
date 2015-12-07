#!/usr/bin/env bash
LOCALHOST=$(ifconfig eno1 | grep inet | grep -v inet6 | awk '{print $2}' | cut -d':' -f2)
echo $LOCALHOST
docker run -t --rm -i \
-e OPENSHIFT_MONGODB_DB_HOST=$LOCALHOST \
-e OPENSHIFT_MONGODB_DB_PORT=27017 \
-e OPENSHIFT_MONGODB_DB_PASSWORD=hive \
-e OPENSHIFT_MONGODB_DB_USERNAME=hive \
-p 8080:8080 \
 qaobee-hive:0.0.1