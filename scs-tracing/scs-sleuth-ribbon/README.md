## 启动命令
```
mvn spring-boot:run -Pservice-1
mvn spring-boot:run -Pservice-2
mvn spring-boot:run -Pservice-3
mvn spring-boot:run -Pservice-4
```

## 脚本run.sh
```
# 启动
.run.sh
# 停止
ps -ef | grep run.sh | awk '{print $2}' | xargs kill -9
```

## Ribbon中的ServerList实现类注意
### 有两个注意的类可以实现ServerList接口，分别是
- DomainExtractingServerList
- ConfigurationBasedServerList

### 实例化入口
`RibbonClientConfiguration`实例化的是`ConfigurationBasedServerList`
`EurekaRibbonClientConfiguration`实例化的是`DomainExtractingServerList`

### 注意
需要用eureka拉取服务端实例列表的情况下要保持`spring-cloud-netflix-eureka-client`依赖项
在`spring-cloud-netflix-core`前面
