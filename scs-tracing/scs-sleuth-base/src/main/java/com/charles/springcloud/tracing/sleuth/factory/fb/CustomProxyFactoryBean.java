package com.charles.springcloud.tracing.sleuth.factory.fb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理一个类，拦截该类的所有方法，在方法的调用前后进行日志的输出
 * 其他示例：https://spring.io/blog/2011/08/09/what-s-a-factorybean
 * 其他示例：https://howtodoinjava.com/spring-core/how-to-create-beans-using-spring-factorybean/
 * 其他示例：https://blog.csdn.net/u013185616/article/details/52335864
 */
public class CustomProxyFactoryBean implements FactoryBean<Object>, InitializingBean, DisposableBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomProxyFactoryBean.class);
    private Object target;
    private Object proxy;

    public CustomProxyFactoryBean(Object target) {
        this.target = target;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        proxy = Proxy.newProxyInstance(this.getClass().getClassLoader(),
                target.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        LOGGER.info("invoke method [{}] start", method.getName());
                        Object result = method.invoke(target, args);
                        LOGGER.info("invoke method [{}] finished", method.getName());
                        return result;
                    }
                });
        LOGGER.info("afterPropertiesSet......");
    }

    @Override
    public Object getObject() throws Exception {
        LOGGER.info("[CustomProxyFactoryBean]#[getObject]");
        return proxy;
    }

    @Override
    public Class<?> getObjectType() {
        return proxy == null ? Object.class : proxy.getClass();
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public void destroy() throws Exception {
        LOGGER.info("destroy......");
    }
}