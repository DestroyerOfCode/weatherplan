#!/bin/zsh

set -e

# Change to the directory ~/development/weatherplan
cd ~/development/weatherplan

# Run the first command
./gradlew clean build -x test

# Change to the directory ~/development/weatherplan/
cd ~/development/weatherplan/

# Run the second command
./gradlew smssender:bootBuildImage homeweather:bootBuildImage apigateway:bootBuildImage openweather:bootBuildImage --parallel

# Change to the directory ~/development/weatherplan/setup
cd ~/development/weatherplan/setup

# Run docker compose down
docker compose -f docker-compose.test.yml down
docker network rm weather-network

# Run docker compose up -d
docker compose -f docker-compose.test.yml --env-file .env.test -f docker-compose.yml --env-file .env --env-file .env.test up -d

docker ps
