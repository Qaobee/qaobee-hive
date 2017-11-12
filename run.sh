#!/usr/bin/env bash
LOCALHOST=$(docker run --rm --net=host alpine ip route get 8.8.8.8 | awk '{ print $7;  }')
echo ${LOCALHOST}
docker run -ti \
-e OPENSHIFT_MONGODB_DB_HOST=mongo \
-e OPENSHIFT_MONGODB_DB_PORT=27017 \
-e OPENSHIFT_MONGODB_DB_PASSWORD=hive \
-e OPENSHIFT_MONGODB_DB_USERNAME=hive \
-e OPENSHIFT_DATA_DIR=/opt/hive-data \
--add-host=mongo:${LOCALHOST} \
-p 8888:8888 \
registry.gitlab.com/qaobee/qaobee-hive:latest
# qaobee-hive:latest
