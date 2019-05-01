#!/usr/bin/env bash




git pull
./gradlew -Pprod clean bootJar


ps -ef | grep evaluate-0.0.1-SNAPSHOT.jar | grep -v grep | cut -c 9-15 | xargs kill -s 9



nohup java -jar -Dspring.profiles.active=prod,swagger build/libs/*.jar >> logs.txt &

tail -f logs.txt
