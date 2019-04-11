[TOC]

## scs-sleuth-individual
### 启动eureka服务
http://localhost:8761
### 启动四个服务
```
./exec.sh sleuth run
```
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