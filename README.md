### Spring Cloud Starter
- `scs-eureka`:服务注册中心
- `scs-zuul`:服务网关
- `scs-data`:集成spring-data标准的服务
- `scs-stream`:MQ的标准化后的流处理
- `scs-tracing`:服务跟踪

### 服务之间调用
目前，在Spring cloud 中服务之间通过restful方式调用有两种方式，从实践上看，采用feign的方式更优雅（feign内部也使用了ribbon做负载均衡）。 
- restTemplate+Ribbon 
- feign

### SpringCloud服务简介
[^_^]: ![](http://img.blog.csdn.net/20161122094129037)
![](/static/scs.jpg)

- Eureka：实际上在整个过程中维护者每个服务的生命周期。每一个服务都要被注册到Eureka服务器上，这里被注册到Eureka的服务又称为Client。Eureka通过心跳来确定服务是否正常。Eureka只做请求转发。同时Eureka是支持集群的
- Zuul：类似于网关，反向代理。为外部请求提供统一入口 
- Ribbon: 客户端的负载均衡器
- Feign：是一个使用起来更方便的HTTP客户端
- Hystrix：断路器，服务调用通常是深层的，一个底层服务通常为多个上层服务提供服务，那么如果底层服务失败则会造成大面积失败，Hystrix就是就调用失败后触发定义好的处理方法，从而更友好的解决出错。也是微服务的容错机制

### 运行前先设置本地DNS，在/etc/hosts文件中设置
```
127.0.0.1	localhost	master
127.0.0.1	localhost	slave-01
127.0.0.1	localhost	slave-02
```

### 各个服务端口分配
|服务|端口范围|
|:--------|:--------|
|api-zuul|8080|
|scs-eureka|8081~8083|
|scs-data|9020~9023|
|scs-tracing|8101~8104|
|scs-consumer-ribbon|9001~9003|
|scs-consumer-feign|9010~9012|
