#!/usr/bin/env bash
vertx runzip hive-0.1.zip -instances 1 -conf /home/qaobee/$ENV-conf.json \
-Dorg.vertx.logger-delegate-factory-class-name=org.vertx.java.core.logging.impl.SLF4JLogDelegateFactory \
-XX:PermSize=128m -XX:MaxPermSize=256m -Xms512m -Xmx1g -XX:+UseParallelGC