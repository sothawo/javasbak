#!/usr/bin/env bash

cd kafkabase
docker build -t javasbak-kafkabase .
cd -

cd zookeeper
docker build -t javasbak-zookeeper .
cd -

cd kafkabroker
docker build -t javasbak-kafkabroker .
cd -
