### SpringCloudPractice
- scs-eureka:服务注册中心
- scs-supplier-one:服务提供者1
- scs-supplier-two:服务提供者2
- scs-consumer-ribbon:服务消费者-Ribbon
- scs-consumer-feign:服务消费者-Feign

### 服务之间调用
目前，在Spring cloud 中服务之间通过restful方式调用有两种方式，从实践上看，采用feign的方式更优雅（feign内部也使用了ribbon做负载均衡）。 
- restTemplate+Ribbon 
- feign

### SpringCloud服务简介
[^_^]: ![](http://img.blog.csdn.net/20161122094129037)
![](/static/scs.jpg)

- Eureka：实际上在整个过程中维护者每个服务的生命周期。每一个服务都要被注册到Eureka服务器上，这里被注册到Eureka的服务又称为Client。Eureka通过心跳来确定服务是否正常。Eureka只做请求转发。同时Eureka是支持集群的
- Zuul：类似于网关，反向代理。为外部请求提供统一入口。 
- Ribbon/Feign：可以理解为调用服务的客户端。 
- Hystrix：断路器，服务调用通常是深层的，一个底层服务通常为多个上层服务提供服务，那么如果底层服务失败则会造成大面积失败，Hystrix就是就调用失败后触发定义好的处理方法，从而更友好的解决出错。也是微服务的容错机制。

### 运行前先设置本地DNS，在/etc/hosts文件中设置
```
127.0.0.1	localhost	master
127.0.0.1	localhost	slave-01
127.0.0.1	localhost	slave-02
```

### 各个服务端口分配
|服务|端口范围|
|:--------|:--------|
|api-gateway|8080|
|eureka-cluster|8081~8083|
|scs-supplier-db|9020~9023|
|scs-supplier-simple|8091~8096|
|scs-consumer-ribbon|9001~9003|
|scs-consumer-feign|9010~9012|
