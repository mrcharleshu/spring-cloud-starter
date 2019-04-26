package com.charles.springcloud.tracing.sleuth.factory;

import com.charles.springcloud.tracing.sleuth.factory.afb.beanprocessor.CustomBeanPostProcessor;
import com.charles.springcloud.tracing.sleuth.factory.afb.EmployeeFactoryBean;
import com.charles.springcloud.tracing.sleuth.factory.afb.beanprocessor.EmployeeService;
import com.charles.springcloud.tracing.sleuth.factory.afb.beanprocessor.EmployeeServiceImpl;
import com.charles.springcloud.tracing.sleuth.factory.fb.CustomProxyFactoryBean;
import com.charles.springcloud.tracing.sleuth.factory.fb.ProxyTestService;
import com.charles.springcloud.tracing.sleuth.factory.fb.ProxyTestServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyFactoryConfiguration {

    @Bean
    public CustomProxyFactoryBean proxyTextService() {
        ProxyTestService service = new ProxyTestServiceImpl();
        return new CustomProxyFactoryBean(service);
    }

    @Bean
    public EmployeeFactoryBean manager() {
        return new EmployeeFactoryBean("Manager");
    }

    @Bean
    public EmployeeFactoryBean director() {
        return new EmployeeFactoryBean("Director");
    }

    @Bean
    public CustomBeanPostProcessor customProxyFactoryBean() {
        return new CustomBeanPostProcessor();
    }

    @Bean
    public EmployeeService employeeService() {
        return new EmployeeServiceImpl();
    }
}
