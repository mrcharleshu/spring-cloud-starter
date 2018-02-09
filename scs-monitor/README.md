### [Hystrix监控的配置详解](http://blog.csdn.net/jrn1012/article/details/77837744)

### 监控地址
在微服务架构中，hystrix处理容错外，还有实时监控功能，在服务发生调用时，会将每秒请求数、成功请求数等运行指标记录下来。

[Demo地址](https://github.com/bill1012/microservice/tree/master/springcloud-demo)

### 通过接口的监控:hystrix.stream查看监控日志
- 启动scs-consumer-ribbon项目后，访问`http://localhost:9001/helloRibbon`后，
- 再次访问http://localhost:9001/hystrix.stream可以看到界面不断地输出监控日志，监控日志里包含了各种指标（各种指标信息请参考官方文档）。

> 按照同样的步骤操作启动scs-consumer-ribbon项目后，可能发现找不到该页面，这是因为需要做一些如下配置： 
> pom.xml引入hystrix依赖
> 
> ```
> <dependency>
>     <groupId>org.springframework.cloud</groupId>
>     <artifactId>spring-cloud-starter-hystrix</artifactId>
> </dependency>
> ```
> 在启动类上加上@EnableCircuitBreaker注解，再次执行上述操作，界面不断输出监控日志。

### 使用hystrix-board对监控进行图形化展示
上面的日志信息不够直观，借助hystrix-dashboard可对监控进行图形化展示。 
- 在service创建hystrix-dashboard项目 
- 引入hystrix-dashboard依赖
```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-hystrix-dashboard</artifactId>
</dependency>
```
启动类配置`@EnableHystrixDashboard`注解（同时配置@EnableDiscoveryClient向注册中心注册，方便管理）

#### 测试结果 

- 启动hystrix-dashboard后，输入`http://localhost:9000/hystrix`地址，
- 在第一个文本框输入http://localhost:9000/hystrix.stream
- 或者http://localhost:8030/hystrix.stream后点击Monitor Stream则会显示监控结果

> http://localhost:9000/hystrix.stream
> http://localhost:9000/hystrix/monitor?stream=http://localhost:9000/turbine.stream

---
以上的hystrix-dashboard每次只能输入一个监控地址，在微服务架构中往往有很多无法需要监控，该怎么办呢？
可以使用Turbine聚合监控数据，让hystrix-dashboard显示这个聚合数据后的地址。

- 创建一个hystrix-turbine项目，引入如下maven依赖
```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-turbine</artifactId>
</dependency>
```
- 启动类上配置@EnableTurbine注解,配置文件application.yml指明要从收集哪些微服务的监控数据

```
turbine:
  app-config: say-hello,scs-consumer-feign,scs-consumer-ribbon
  cluster-name-expression: "'default'"
```
注意turbine的配置，这里收集scs-consumer-feign,scs-consumer-ribbon的日志 
启动项目hystrix-turbine后，在hystrix-dashboard中输入http://localhost:9000/turbine.stream,则展示聚合结果

