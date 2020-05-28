#!/bin/bash

REPOSITORY=/home/ubuntu/app/goonmeonity_test2
PROJECT_NAME=goonmeonity_backend
echo "> build 파일 복사"

cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> 현재 구동중인 애플리케이션 pid 확인"

CURRENT_PID=$(pgrep -f goonmeonity-application-1.0*.jar | awk '{print  $1}')
echo "현재 구동중인 애플리케이션 pid:" $CURRENT_PID

if [  -z "$CURRENT_PID" ]; then
        echo "> 현재 구동 중인 애플리케이션이 없으므로 종료하지 않습니다."
else
        echo "> kill -15 $CURRENT_PID"
        kill -15 $CURRENT_PID
        sleep 5
fi

echo "> 새애플리케이션 배포"

JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1 )

echo "> JAR NAME : $JAR_NAME"

echo "> $JAR_NAME 에 실행권한 추가"

sudo chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"

sudo nohup java -jar -Dspring.config.location=classpath://application.properties  $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &

sudo nohup java -jar \
    -Dspring.config.location=classpath:/application.properties, classpath:/application-real.properties,/home/ec2-user/app/application-real-db.properties \
    -Dspring.profiles.active=real \
    $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &
