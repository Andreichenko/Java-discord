#!/bin/bash

# Start the first process
nginx&
status=$?
if [ $status -ne 0 ]; then
  echo "Failed to start nginx: $status"
  exit $status
fi

# Start the second process
java -jar -Dspring.profiles.active=default /app.jar&
status=$?
if [ $status -ne 0 ]; then
  echo "Failed to start bot: $status"
  exit $status
fi

while sleep 60; do
  echo -n ""
done
