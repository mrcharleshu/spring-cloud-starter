### 自定义Eureka的Instance ID
在Spring Cloud中，服务的Instance ID的默认值是
`${spring.cloud.client.hostname}:${spring.application.name}:${spring.application.instance_id:${server.port}}`
也就是机器主机名:应用名称:应用端口 。
因此在Eureka Server首页中看到的服务的信息类似如下：192.168.31.134:say-hello:8081。如果想要自定义这部分的信息怎么办？

示例：
```
eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:9000/eureka/
  instance:
    preferIpAddress: true
    instance-id: ${spring.cloud.client.ipAddress}:${server.port} # 将Instance ID设置成IP:端口的形式
```

### Eureka集群的配置
- http://blog.csdn.net/tianyaleixiaowu/article/details/78184793
- https://www.cnblogs.com/wangjing666/p/6972742.html
