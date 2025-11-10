#!/bin/bash

# SonarQube Analysis Script for Candy Land Game
# This script runs SonarQube analysis with proper configuration

set -e

# Check if required environment variables are set
if [ -z "$SONAR_TOKEN" ]; then
    echo "❌ Error: SONAR_TOKEN environment variable is not set"
    echo "Please set your SonarQube authentication token:"
    echo "export SONAR_TOKEN=your_token_here"
    exit 1
fi

if [ -z "$SONAR_HOST_URL" ]; then
    echo "❌ Error: SONAR_HOST_URL environment variable is not set"
    echo "Please set your SonarQube Server URL:"
    echo "export SONAR_HOST_URL=https://your-sonarqube-server.com"
    exit 1
fi

echo "🔍 Running SonarQube analysis..."
echo "📍 Server: $SONAR_HOST_URL"
echo "🎯 Project Key: candy-land-java-game"

# Run the analysis
mvn clean verify sonar:sonar \
  -Dsonar.projectKey=candy-land-java-game \
  -Dsonar.host.url="$SONAR_HOST_URL" \
  -Dsonar.login="$SONAR_TOKEN" \
  -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml

echo "✅ SonarQube analysis completed successfully!"
echo "📊 View results at: $SONAR_HOST_URL/dashboard?id=candy-land-java-game"