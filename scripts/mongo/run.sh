#!/bin/bash
docker run -d -p 27018:27017 -p 28018:28017 -v /opt/qaobee-hive-db/:/data/db qaobee-hive-db
