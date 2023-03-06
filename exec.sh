#!/bin/bash

mvn clean compile
mvn install

docker compose -f ./docker/docker-compose.yml up
