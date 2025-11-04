#!/bin/bash
echo "🛑 Stopping any running Java processes..."
pkill -9 java 2>/dev/null

echo "🧹 Cleaning build..."
./gradlew clean

echo "🚀 Starting application..."
./gradlew bootRun
