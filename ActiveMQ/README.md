# ActiveMQ

## 标准协议

- JMS(Java Message Service)
- AMQP() 不同语言之间的消息中间件
- MQTT 物联网消息中间件

两种消息传递域：

- 点对点(point-to-point)
- 发布/订阅(publish/subscribe)


## 安装

- 地址：[官网](http://activemq.apache.org/components/classic/download/)
- 解压：`tar -zxvf apacheactiveMQ.tar.gz`
- 启动：
    - 普通启动：`sh activemq start`
    - 启动并指定日志文件：`sh activemq start >
/tmp/activemqlog`
- 启动检查：`netstat -an|grep 61616`
- 端口：8161(访问管理页面),61616(对外暴露接口，应用程序访问)，默认帐号密码 admin/admin
- 重启：`sh activemq restart`
- 关闭：`sh activemq stop`
- windows 使用参考：[Windows下安装ActiveMQ](https://blog.csdn.net/j080624/article/details/79983797)

ActiveMQ 和 RabbitMQ 都会使用 5672 端口，修改 ActiveMQ 的端口为 5673 -> `apache-activemq-5.14.4\conf\activemq.xml`


![JMS 的体系结构](https://img2018.cnblogs.com/blog/993791/201904/993791-20190413012045848-1170188609.jpg)

## 项目说明

1. HelloWorld：消息收发的 Demo