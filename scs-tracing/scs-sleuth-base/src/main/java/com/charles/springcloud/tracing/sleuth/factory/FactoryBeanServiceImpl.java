package com.charles.springcloud.tracing.sleuth.factory;

import com.charles.springcloud.tracing.sleuth.factory.afb.Employee;
import com.charles.springcloud.tracing.sleuth.factory.afb.EmployeeFactoryBean;
import com.charles.springcloud.tracing.sleuth.factory.fb.CustomProxyFactoryBean;
import com.charles.springcloud.tracing.sleuth.factory.fb.ProxyTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * getBean执行的是AbstractBeanFactory中的doGetBean方法
 * @see org.springframework.beans.factory.support.AbstractBeanFactory#doGetBean(String, Class, Object[], boolean)
 * @see org.springframework.beans.factory.support.FactoryBeanRegistrySupport#getObjectFromFactoryBean(FactoryBean, String, boolean)
 * @see org.springframework.beans.factory.support.FactoryBeanRegistrySupport#doGetObjectFromFactoryBean(FactoryBean, String)
 */
@Service
public class FactoryBeanServiceImpl implements FactoryBeanService, ApplicationContextAware {
    private static final Logger LOGGER = LoggerFactory.getLogger(FactoryRunner.class);
    private ApplicationContext context;

    @Override
    public void test() throws Exception {
        LOGGER.info(">>>>>>>>>>>>>>>>>> CustomProxyFactoryBean <<<<<<<<<<<<<<<<<");
        ProxyTestService proxyTestService = (ProxyTestService) context.getBean("proxyTextService");
        proxyTestService.print();
        // add '&' to get factory bean itself
        CustomProxyFactoryBean customProxyFactoryBean = (CustomProxyFactoryBean) context.getBean("&proxyTextService");
        LOGGER.info("print object: {}", customProxyFactoryBean.getObject().toString());
        LOGGER.info("print object type: {}", customProxyFactoryBean.getObjectType().toString());
        LOGGER.info("print bean: {}", customProxyFactoryBean.toString());
        LOGGER.info(">>>>>>>>>>>>>>>>>> EmployeeFactoryBean <<<<<<<<<<<<<<<<<");
        Employee manager = (Employee) context.getBean("manager");
        Employee director = (Employee) context.getBean("director");
        LOGGER.info(manager.toString());
        LOGGER.info(director.toString());
        EmployeeFactoryBean factory = (EmployeeFactoryBean) context.getBean("&director");
        LOGGER.info(factory.getDesignation());
        LOGGER.info(factory.getObjectType().toString());
        LOGGER.info(factory.getObject().toString());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
