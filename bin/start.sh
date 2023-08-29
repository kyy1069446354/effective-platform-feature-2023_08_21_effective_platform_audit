#!/bin/bash

cd `dirname $0`
cd ..
CURRENT_PATH=$(pwd)

JAR=$(find $CURRENT_PATH -maxdepth 1 -name "*.jar")
AppName=$(basename "$JAR")
PID=$(ps -ef | grep $AppName | grep -v grep | awk '{ print $2 }')
JVM_OPTS="-Dname=$AppName -Xms256M -Xmx512M -XX:+HeapDumpOnOutOfMemoryError -Dspring.profile.active=prod"

case "$1" in
    start)
        if [ ! -z "$PID" ]; then
            echo "$AppName 已经启动，进程号: $PID"
        else
            echo -n -e "启动 $AppName ... $PID \n"
        cd $CURRENT_PATH
        nohup java -jar $JVM_OPTS $JAR >/dev/null 2>error.console &
            if [ "$?" = "0" ]; then
                echo "启动完成，请执行 tail -f logs/info.log 命令，查看日志确保成功"
            else
                echo "启动失败"
            fi
        fi
        ;;
    stop)
        if [ -z "$PID" ]; then
            echo "$AppName 没有在运行，无需关闭"
        else
            echo "关闭 $AppName ..."
              kill $PID
            if [ "$?" = "0" ]; then
                echo "服务已关闭"
            else
                echo "服务关闭失败"
            fi
        fi
        ;;
    restart)
        ${0} stop
        echo "sleep 2s"
        sleep 2s
        ${0} start
        ;;
    kill)
        if [ -z "$PID" ]; then
            echo "$AppName 没有在运行，无需关闭"
        else
            echo "强制关闭 $AppName ..."
              kill -9 $PID
            if [ "$?" = "0" ]; then
                echo "服务已关闭"
            else
                echo "服务关闭失败"
            fi
        fi
        ;;
    status)
        if [ ! -z "$PID" ]; then
            echo "$AppName 正在运行"
        else
            echo "$AppName 未在运行"
        fi
        ;;
  *)
    echo "Usage: ./start.sh {start|stop|restart|status|kill}" >&2
        exit 1
esac