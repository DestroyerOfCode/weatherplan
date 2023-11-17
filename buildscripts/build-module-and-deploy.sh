#!/bin/zsh

# Enable automatic exit on non-zero status
set -e

if [[ $# -ne 1 ]]; then
  echo "Usage: $0 <module_name>"
  exit 1
fi

module_name=$1
project_dir=~/development/weatherplan

# Change to the project directory
cd $project_dir

# Run Gradle commands with single quotes
./gradlew "${module_name}"':clean' "$module_name"':build' -x=test
./gradlew "$module_name"':bootBuildImage'

# Change to the setup directory
cd $project_dir/setup/dev

# Run Docker Compose
docker compose down
docker compose up -d

