#!/bin/sh 
set -x 

LOCAL_REPO="rr19"

PROJECT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )"
APP_NAME=$( basename -- $PROJECT_DIR )

VERSION=$(cat version.txt)

docker build -t ${LOCAL_REPO}/${APP_NAME}:${VERSION} .
RESULT=$? 

echo "Build scripted finished"

exit $RESULT
# DONE 