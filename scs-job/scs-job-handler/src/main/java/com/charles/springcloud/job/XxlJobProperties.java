package com.charles.springcloud.job;

import org.springframework.boot.context.properties.ConfigurationProperties;

// @Component
@ConfigurationProperties(prefix = "xxl.job")
public class XxlJobProperties {
    private String accessToken;
    private String adminAddresses = "http://127.0.0.1:8082";
    private String executorAppName = "xxl-job-executor-example";
    private String executorIp = "";
    private Integer executorPort = 9998;
    private String executorLogPath = "/opt/data/applogs/xxl-job/jobhandler/";
    private Integer logRetentionDays;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAdminAddresses() {
        return adminAddresses;
    }

    public void setAdminAddresses(String adminAddresses) {
        this.adminAddresses = adminAddresses;
    }

    public String getExecutorAppName() {
        return executorAppName;
    }

    public void setExecutorAppName(String executorAppName) {
        this.executorAppName = executorAppName;
    }

    public String getExecutorIp() {
        return executorIp;
    }

    public void setExecutorIp(String executorIp) {
        this.executorIp = executorIp;
    }

    public Integer getExecutorPort() {
        return executorPort;
    }

    public void setExecutorPort(Integer executorPort) {
        this.executorPort = executorPort;
    }

    public String getExecutorLogPath() {
        return executorLogPath;
    }

    public void setExecutorLogPath(String executorLogPath) {
        this.executorLogPath = executorLogPath;
    }

    public Integer getLogRetentionDays() {
        return logRetentionDays;
    }

    public void setLogRetentionDays(Integer logRetentionDays) {
        this.logRetentionDays = logRetentionDays;
    }
}
