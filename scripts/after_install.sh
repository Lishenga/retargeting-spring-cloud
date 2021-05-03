#!/bin/bash

sudo chown -R ubuntu:ubuntu /home/ubuntu

cd /home/ubuntu/retargeting-spring-cloud
export DOCKER_CLIENT_TIMEOUT=300
export COMPOSE_HTTP_TIMEOUT=300    
sudo docker-compose -f docker-compose.yml up -d --build