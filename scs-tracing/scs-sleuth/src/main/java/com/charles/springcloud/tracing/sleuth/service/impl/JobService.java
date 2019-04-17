package com.charles.springcloud.tracing.sleuth.service.impl;

import com.charles.springcloud.tracing.base.service.RemoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import static com.charles.springcloud.tracing.base.constants.ServiceNames.SERVICE_1;

@Service
public class JobService {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobService.class);
    @Value("${spring.application.name}")
    private String applicationName;
    private final RemoteService remoteService;

    @Autowired
    public JobService(@Qualifier("sleuthRemoteServiceImpl") RemoteService remoteService) {
        this.remoteService = remoteService;
    }

    @Async
    public void asyncMethod() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        LOGGER.info("Async Method");
    }

    @Scheduled(cron = "0/10 * * * * ?")
    public void scheduledWork() throws InterruptedException {
        if (SERVICE_1.equals(applicationName + "0")) {
            LOGGER.info("************* Start some work from the scheduled task *************");
            asyncMethod();
            remoteService.callService3Async();
            LOGGER.info("******************* End work from scheduled task *******************");
        }
    }
}
