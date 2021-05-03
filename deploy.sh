#!/bin/bash

cd api-gateway
mvn spring-boot:build-image -DskipTests
cd ..
cd config-server
mvn spring-boot:build-image -DskipTests
cd ..
cd naming_server
mvn spring-boot:build-image -DskipTests
cd ..
sudo docker-compose -f docker-compose.yml up -d 
