package com.charles.sleuth.service.impl;

import com.charles.sleuth.service.RemoteService;
import com.charles.sleuth.service.SimpleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Service
public class SimpleServiceImpl implements SimpleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleServiceImpl.class);
    private final RemoteService remoteService;
    @Value("${spring.application.name}")
    private String applicationName;
    private static final String SERVICE_1_NAME = "service-0";

    @Autowired
    public SimpleServiceImpl(RemoteService remoteService) {
        this.remoteService = remoteService;
    }

    @Scheduled(cron = "0/10 * * * * ?")
    public void scheduledWork() throws InterruptedException {
        if (SERVICE_1_NAME.equals(applicationName)) {
            LOGGER.info("************* Start some work from the scheduled task *************");
            asyncMethod();
            remoteService.callService3Async();
            asyncMethod();
            remoteService.callService3Async();
            LOGGER.info("******************* End work from scheduled task *******************");
        }
    }

    @Override
    public void testParallelStream() {
        Arrays.asList("Tom", "Jerry", "Mary", "Colin").parallelStream().forEach(LOGGER::info);
    }

    @Async
    public void asyncMethod() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        LOGGER.info("Async Method");
    }

    @Async
    @Override
    public void testAsync1() throws InterruptedException {
        LOGGER.info("Start Async Method");
        asyncMethod();
        LOGGER.info("End Async Method");
    }

    @Async
    protected void print(String str) {
        LOGGER.info("Simple print string: {}", str);
    }

    @Override
    public void testAsync2() {
        Arrays.asList("Tom", "Jerry", "Mary", "Colin").forEach(this::print);
    }

}
