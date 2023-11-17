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
cd ~/development/weatherplan/setup/dev/

# Run docker compose down
docker compose down

# Run docker compose up -d
docker compose up -d

docker ps
