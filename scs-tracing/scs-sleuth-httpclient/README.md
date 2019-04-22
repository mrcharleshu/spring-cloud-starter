 ## Apache HTTP Client的使用注意
 
 - Spring-Cloud-Sleuth 1.x.x 适配 SpringBoot 1.x.x
 - 1.x.x的版本对于Apache HTTP Client没有默认的trace拦截器实现
 - SpringBoot 2.x.x 搭配 Spring-Cloud-Sleuth 2.x.x 有默认实现
    
    
    TracingHttpClientBuilder.create(tracing).setDefaultRequestConfig(requestConfig).build();