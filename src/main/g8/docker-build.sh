#!/bin/sh 
set -x

LOCAL_REPO="dave"

PROJECT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )"
APP_NAME=$( basename -- $PROJECT_DIR )

VERSION_FILE="version.txt"
if [ ! -f "${PROJECT_DIR}/${VERSION_FILE}" ]
then 
   echo "Creating version file ..."
   $PROJECT_DIR/generateVersion
   echo "Version file created."
fi 
VERSION=$(cat version.txt)

docker build -t ${LOCAL_REPO}/${APP_NAME}:${VERSION} .
RESULT=$? 

echo "Build scripted finished"

exit $RESULT
# DONE 
