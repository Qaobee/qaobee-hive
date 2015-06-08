#!/bin/bash
rm -fr node_modules
rm -fr src/main/resources/web/js/libs/*
npm install
bower install
grunt
