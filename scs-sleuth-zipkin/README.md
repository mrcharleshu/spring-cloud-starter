[TOC]

## scs-sleuth-individual
### 启动eureka服务
http://localhost:8761
### 启动四个服务
```
./exec.sh sleuth run
```

> **想看到高亮的命令行日志输出可以运行下面命令**
> 
> ```
> services=(1 2 3 4);for i in ${services[*]};do mvn spring-boot:run -Pservice-${i} &;sleep 10s;done;
> ```

### 运行测试命令
```
curl http://localhost:9091/start
```
### 关闭四个服务
```
./exec.sh sleuth stop
```

## scs-zipkin-individual
### 启动zipkin服务
http://127.0.0.1:9411
### 启动四个服务
```
./exec.sh zipkin run
```
### 运行测试命令
```
curl http://localhost:9095/start
```
### 关闭四个服务
```
./exec.sh zipkin stop
```