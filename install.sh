#!/bin/bash
# Build, install and start the app (mobile-install alternative)

set -e

echo "Building app..."
bazel build //app/src/main:app

echo "Installing app..."
adb install -r bazel-bin/app/src/main/app.apk

echo "Starting app..."
adb shell am start -n com.amary.my.music/.MainActivity

echo "✅ App installed and started successfully!"
