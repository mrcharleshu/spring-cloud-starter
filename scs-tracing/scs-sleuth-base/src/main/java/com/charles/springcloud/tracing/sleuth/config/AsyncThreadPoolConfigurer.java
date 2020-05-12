package com.charles.springcloud.tracing.sleuth.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cloud.sleuth.instrument.async.LazyTraceExecutor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.ProxyAsyncConfiguration;

import java.util.concurrent.Executor;

/**
 * 接口实现初始化方法和销毁前操作主要有下面３种方法：
 * 第一种是：通过@PostConstruct 和 @PreDestroy 方法 实现初始化和销毁bean之前进行的操作;
 * 第二种是：通过 在xml中定义init-method 和  destory-method方法;
 * 第三种是：通过bean实现InitializingBean和 DisposableBean接口;
 * <p></p>
 * InitializingBean的执行在ApplicationContextAware后面
 */
@EnableAsync
@Configuration
@AutoConfigureAfter(value = AsyncThreadPoolConfiguration.class)
@AutoConfigureBefore(value = ProxyAsyncConfiguration.class)
@ConditionalOnBean(LazyTraceExecutor.class)
public class AsyncThreadPoolConfigurer implements AsyncConfigurer, InitializingBean, DisposableBean {
    private static final Logger log = LoggerFactory.getLogger(AsyncThreadPoolConfigurer.class);
    private final LazyTraceExecutor executor;

    public AsyncThreadPoolConfigurer(LazyTraceExecutor executor) {
        log.info("Initializing AsyncThreadPoolConfigurer...");
        this.executor = executor;
    }

    @Override
    public Executor getAsyncExecutor() {
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (ex, method, params) -> {
            log.warn("Throwable: {}", ex.getMessage());
            log.warn("Method: {}", method.getName());
            log.warn("Object: {}", params);
        };
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("Initializing AsyncThreadExecutorConfig...");
    }

    @Override
    public void destroy() throws Exception {
        log.info("Destroying AsyncThreadExecutorConfig...");
    }
}