## 安装es，修改配置文件
分别去掉一下注释，并将对应的值修改为如下：
```
cluster.name: CollectorDBCluster
node.name: skywalking
network.host: 0.0.0.0
http.port: 9200

thread_pool.bulk.queue_size: 1000
# bootstrap.memory_lock: false
# bootstrap.system_call_filter: false
```
启动elasticsearch

## 基础包准备：

- [apache-skywalking-apm-incubating-6.0.0-GA.tar](https://github.com/apache/incubator-skywalking/releases)

解压后启动bin目录下的start.sh

## 服务配置
### IDEA配置VM options
`-javaagent:/Users/Charles/Downloads/apache-skywalking-apm-incubating/agent/skywalking-agent.jar -Dskywalking.collector.backend_service=localhost:11800 -Dskywalking.agent.service_name=service-1`

### maven启动
在项目下新建`.mvn/jvm.config`
```
-javaagent:/Users/Charles/Downloads/apache-skywalking-apm-incubating/agent/skywalking-agent.jar
-Dskywalking.collector.backend_service=localhost:11800
-Dskywalking.agent.service_name=service-1`
```

## APM后台地址查看
http://localhost:8080