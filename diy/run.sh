#!/bin/sh
ARGS=
PROD_ARGS2=-d

if [ "x$1" = "x" ]; then
	echo 'You must declare environnement arg (PROD/DEV)' 
	exit 1
fi

if [ "x$1" = "xPROD" ]; then
	ARGS="$ARGS $PROD_ARGS" 
fi

echo Copying config files from $1 ENV
cp -f $OPENSHIFT_REPO_DIR/diy/elasticsearch/config/$1/* $OPENSHIFT_REPO_DIR/diy/elasticsearch/config/. 
echo running ES with args : $ARGS
$OPENSHIFT_REPO_DIR/diy/elasticsearch/bin/elasticsearch $ARGS
