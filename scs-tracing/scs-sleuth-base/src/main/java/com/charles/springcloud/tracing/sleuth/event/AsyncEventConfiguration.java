package com.charles.springcloud.tracing.sleuth.event;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.cloud.sleuth.instrument.async.LazyTraceExecutor;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.ResolvableType;

import java.util.concurrent.Executor;

/**
 * 开启本类可以让Spring的事件监听处理变成异步
 * <p></p>
 * 所有的异步都在multicastEvent中处理，根据executor是同步或者异步来处理
 * @see org.springframework.context.event.SimpleApplicationEventMulticaster#multicastEvent(ApplicationEvent, ResolvableType)
 * 默认是同步处理所有事件，如果需要切换异步，设置applicationEventMulticaster的taskExecutor值
 * @see org.springframework.context.event.SimpleApplicationEventMulticaster#setTaskExecutor(Executor)
 */
@Configuration
public class AsyncEventConfiguration {

    @Bean
    public ApplicationEventMulticaster applicationEventMulticaster(
            BeanFactory beanFactory, LazyTraceExecutor executor) {
        SimpleApplicationEventMulticaster multicaster = new SimpleApplicationEventMulticaster(beanFactory);
        multicaster.setTaskExecutor(executor);
        return multicaster;
    }
}
