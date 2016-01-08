#!/bin/bash
docker run -p 27018:27017 -p 28018:28017 -v /opt/qaobee-hive-db/:/data/db qaobee-hive-db
