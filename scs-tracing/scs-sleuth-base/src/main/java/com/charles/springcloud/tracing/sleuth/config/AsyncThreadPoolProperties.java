package com.charles.springcloud.tracing.sleuth.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("spring.async.threadpool")
public class AsyncThreadPoolProperties {
    private static final String THREAD_NAME_PREFIX = "AsyncExecutor-";
    private Integer corePoolSize = 8;
    private Integer maxPoolSize = 40;
    private Integer queueCapacity = 200;
    private String threadNamePrefix = THREAD_NAME_PREFIX;

    public Integer getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(Integer corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public Integer getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(Integer maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public Integer getQueueCapacity() {
        return queueCapacity;
    }

    public void setQueueCapacity(Integer queueCapacity) {
        this.queueCapacity = queueCapacity;
    }

    public String getThreadNamePrefix() {
        return threadNamePrefix;
    }

    public void setThreadNamePrefix(String threadNamePrefix) {
        this.threadNamePrefix = threadNamePrefix;
    }

    @Override
    public String toString() {
        return "AsyncThreadPoolProperties{" +
                "corePoolSize=" + corePoolSize +
                ", maxPoolSize=" + maxPoolSize +
                ", queueCapacity=" + queueCapacity +
                ", threadNamePrefix='" + threadNamePrefix + '\'' +
                '}';
    }
}
