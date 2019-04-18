package com.charles.springcloud.tracing.service.impl;

import com.charles.springcloud.tracing.service.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
public class AsyncServiceImpl implements AsyncService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncServiceImpl.class);

    @Async
    @Override
    public ListenableFuture<String> getListenableFuture() throws InterruptedException {
        LOGGER.info("Before Method: getListenableFuture");
        TimeUnit.SECONDS.sleep(10);
        LOGGER.info("After Method: getListenableFuture");
        return AsyncResult.forValue("ListenableFuture result value");
    }

    @Async
    @Override
    public CompletableFuture<String> getCompletableFuture() throws InterruptedException {
        LOGGER.info("Before Method: getCompletableFuture");
        // https://spring.io/guides/gs/async-method/
        TimeUnit.SECONDS.sleep(10);
        LOGGER.info("After Method: getCompletableFuture");
        // return new AsyncResult<>("hello world !!!!");
        return CompletableFuture.completedFuture("hello world !!!!");
    }

    @Async
    @Override
    public void simpleAsyncPrint(String str) throws InterruptedException {
        TimeUnit.SECONDS.sleep(10);
        LOGGER.info("Simple print string: {}", str);
    }
}
