#!/usr/bin/env bash
docker run --rm --name javasbak-kafkabroker --network javasbak -p 9092:9092 javasbak-kafkabroker --override zookeeper.connect=javasbak-zookeeper:2181
