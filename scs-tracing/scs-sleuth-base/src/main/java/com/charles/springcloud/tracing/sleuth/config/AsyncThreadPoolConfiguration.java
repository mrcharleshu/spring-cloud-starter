package com.charles.springcloud.tracing.sleuth.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.sleuth.instrument.async.LazyTraceExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Slf4j
@Configuration
@EnableConfigurationProperties(AsyncThreadPoolProperties.class)
@ConditionalOnProperty(prefix = "spring.async", name = "enabled", havingValue = "true")
public class AsyncThreadPoolConfiguration {

    private final BeanFactory beanFactory;

    public AsyncThreadPoolConfiguration(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Bean
    public LazyTraceExecutor asyncThreadPoolExecutor(AsyncThreadPoolProperties properties) {
        log.info("Initializing ThreadPoolTaskExecutor: {}", properties);
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(properties.getCorePoolSize());
        executor.setMaxPoolSize(properties.getMaxPoolSize());
        executor.setQueueCapacity(properties.getQueueCapacity());
        executor.setThreadNamePrefix(properties.getThreadNamePrefix());
        // executor.setRejectedExecutionHandler(new CallerRunsPolicy());
        //由调用线程处理该任务
        // 丢弃任务并抛出RejectedExecutionException异常
        // executor.setRejectedExecutionHandler(new AbortPolicyWithReport(properties.getThreadNamePrefix()));
        // executor.initialize(); // 作为一个Bean初始化后，initialize方法会在afterPropertiesSet中被调用
        // return executor;
        executor.initialize();
        return new LazyTraceExecutor(beanFactory, executor);
    }
}
