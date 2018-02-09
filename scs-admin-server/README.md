### SpringBoot Admin ([参考](http://blog.csdn.net/xiao_jun_0820/article/details/77983334?locationnum=9&fps=1))
spring cloud提供了hystrix dashbord用来监控hystrix，但是既然springboot admin提供了单个应用的那么多好用的管理功能和监控，那么就整合在一起方便管理和查看吧。

###
springboot admin提供了spring-boot-admin-server-ui-hystrix用来整合hystrix。

除了eureka依赖和admin server依赖外，添加了admin server hystrix ui依赖，还有mail依赖用来发送监控报警

1. 启动类需要添加@EnableAdminServer注解,

2. 本例是采用springboot admin整合eureka的方案自动发现所有的注册过的服务,所以添加了@EnableDiscoveryClient注解。

3. 本例还激活了邮件通知服务，所以pom中添加了mail starter,然后在yml中配置邮件服务器，

- spring.boot.admin.notify.main.to是要接收报警的邮箱，
- 然后igore-changes的默认值我修改了一下从任何状态到UP状态的的状态变更都不要发送邮件通知，
- 默认的只忽略了从UNKNOWN变为UP，我想从DOWN变成UP也不要发邮件了，只在不正常的时候提示就够了。
- 然后指定了defaultZone为自己启动的eureka server的地址。
- admin 的routes endpoints在原来默认的基础上增加了hystrix.stream端点路由