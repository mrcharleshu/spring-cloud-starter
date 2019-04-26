package com.charles.springcloud.tracing.sleuth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.instrument.async.LazyTraceExecutor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 接口实现初始化方法和销毁前操作主要有下面３种方法：
 * 第一种是：通过@PostConstruct 和 @PreDestroy 方法 实现初始化和销毁bean之前进行的操作;
 * 第二种是：通过 在xml中定义init-method 和  destory-method方法;
 * 第三种是：通过bean实现InitializingBean和 DisposableBean接口;
 * <p></p>
 * InitializingBean的执行在ApplicationContextAware后面
 */
@Configuration
@EnableAsync
public class AsyncThreadConfig extends AsyncConfigurerSupport
        implements BeanNameAware, ApplicationContextAware, InitializingBean, DisposableBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncThreadConfig.class);
    private final BeanFactory beanFactory;

    @Autowired
    public AsyncThreadConfig(BeanFactory beanFactory) {
        LOGGER.info("Constructor");
        this.beanFactory = beanFactory;
    }

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(10);
        threadPoolTaskExecutor.setMaxPoolSize(15);
        threadPoolTaskExecutor.setQueueCapacity(50);
        threadPoolTaskExecutor.setThreadNamePrefix("AsyncExecutor-");
        threadPoolTaskExecutor.initialize();
        return new LazyTraceExecutor(beanFactory, threadPoolTaskExecutor);
    }

    @Override
    public void setBeanName(String name) {
        LOGGER.info("beanName = {}", name);
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        LOGGER.info("[setApplicationContext] applicationName = {}, displayName = {}",
                context.getApplicationName(), context.getDisplayName());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LOGGER.info("Initializing method [implements InitializingBean interface]...");
    }

    @Override
    public void destroy() throws Exception {
        LOGGER.info("Destroying method [implements DisposableBean interface]...");
    }
}