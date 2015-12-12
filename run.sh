#!/usr/bin/env bash
LOCALHOST=$(docker run --rm --net=host alpine ip route get 8.8.8.8 | awk '{ print $7;  }')
echo $LOCALHOST
docker run -t --rm -i \
-e OPENSHIFT_MONGODB_DB_HOST=mongo \
-e OPENSHIFT_MONGODB_DB_PORT=27017 \
-e OPENSHIFT_MONGODB_DB_PASSWORD=hive \
-e OPENSHIFT_MONGODB_DB_USERNAME=hive \
--add-host=mongo:$LOCALHOST \
-p 8080:8080 \
qaobee-hive:0.0.1