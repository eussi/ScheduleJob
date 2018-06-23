# ScheduleJob

##### 项目构建:Maven

该工程用来*定时调度添加的任务*

###### JDK版本:

    jdk 1.7.0_79

##### 功能说明：
    
* 通过界面或者数据库中添加需要调用的类或者spring容器中的类及其方法从而添加调度任务，并添加cron表达式设置调度频率
* 任务可以停止或者开启，任务可以通过更新cron表达式来修改执行频率

##### 运行说明：

1、 使用IntelliJ IDEA开发，导入工程后点击jetty:run或jetty:run-exploded运行

访问路径根据pom.xml中的jetty配置而定，这里访问的路径是：_http://localhost/schedulejob/task/taskList.htm_

2、 部署在linux服务器的tomcat中，方式有多种，介绍下一种部署方式：

目录结构:

> ScheduleJob

>> app

>>> maven打包编译后的项目war包或项目文件夹

>> bin

>>> 运行的脚本

>> middleware

>>> tomcat根目录下内容

注意事项：

* 修改tomcat配置文件server.xml,Host节点下增加如下内容
```
<Context docBase="../../app" path="/ScheduleJob"  reloadable="true" ></Context>
```
_path是访问路径，docBase是要部署的资源路径，注意路径要写到项目根目录的位置_

* bin目录下内容脚本如下：
**startApp.sh**
```
#!/bin/sh
#
#Author: wangxueming
#Date: 2018-06-19
#
###################################
#环境变量及程序执行参数
#需要根据实际环境以及Java程序名称来修改这些参数
###################################
#JDK所在路径,环境中一般已经配置
#JAVA_HOME="/usr/java/jdk1.7.0_79"
 
#执行程序启动所使用的系统用户，考虑到安全，推荐不使用root帐号
#RUNNING_USER=esb
 
#Java应用根目录
APP_HOME=`pwd`/..

#需要启动的shell脚本
APP_SHELL=startup.sh

STOP_SHELL=shutdown.sh
 
###################################
start() {
     chmod 755 $APP_HOME/middleware/bin/*.sh
     echo -n "Starting $APP_SHELL ..."
     echo
     echo "运行时间：`date '+%Y%m%d%H%M%S'`"
     sh $APP_HOME/middleware/bin/$APP_SHELL
}
 
###################################
stop() {
     echo -n "Starting $STOP_SHELL ..."
     echo
     echo "运行时间：`date '+%Y%m%d%H%M%S'`"
     sh $APP_HOME/middleware/bin/$STOP_SHELL
}

###################################
#(函数)打印系统环境参数
###################################
info() {
   echo "System Information:"
   echo "********************************************************"
   echo `uname -a`
   echo
   echo "JAVA_HOME=$JAVA_HOME"
   echo "APP_HOME=$APP_HOME"
   echo "APP_SHELL=$APP_SHELL"
   echo "CURRENT_TIME=`date '+%Y%m%d%H%M%S'`"
   echo "********************************************************"
}
 
###################################
#读取脚本的第一个参数($1)，进行判断
#参数取值范围：{start|info}
#如参数不在指定范围之内，则打印帮助信息
###################################
case "$1" in
   'start')
      start
      ;;
   'stop')
      stop
      ;;
   'info')
     info
     ;;
  *)
     echo "Usage: $0 {start|stop|info}"
     exit 1
     ;;
esac

```
**start.sh**
```
#!/bin/sh
#当前时间，格式20180414173254
current_time=`date '+%Y%m%d%H%M%S'`
#处理逻辑
mv run.log ./log/run.log.$current_time
sh startApp.sh info
echo
echo "开始运行..."
echo
echo "访问地址：http://ip:8619/ScheduleJob/task/taskList.htm"
echo
sh startApp.sh start > run.log
tail -f ../middleware/logs/catalina.out
echo
echo

```
**stop.sh**
```
#!/bin/sh
#当前时间，格式20180414173254
current_time=`date '+%Y%m%d%H%M%S'`
#处理逻辑
mv stop.log ./log/stop.log.$current_time
sh startApp.sh info
echo
echo "正在结束进程..."
sh startApp.sh stop > stop.log
tail -f ../middleware/logs/catalina.out
echo
echo
```
* 运行步骤：

1. 将打包好文件上传服务器
2. 解压缩
3. 进入bin目录，运行start.sh

* 访问路径：

根据配置的路径，访问路径为：_http://ip:prot/ScheduleJob/task/taskList.htm_

* 运行效果

![image](https://github.com/eussi/ScheduleJob/blob/master/src/main/webapp/pic/run.PNG)
