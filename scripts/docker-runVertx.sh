#!/usr/bin/env bash
echo "-------------------- $ENV"
java -XX:PermSize=128m -XX:MaxPermSize=256m -Xms512m -Xmx1g -XX:+UseParallelGC -Djava.security.egd=file:/dev/urandom \
-jar qaobee-hive-0.1-fat.jar instances 5