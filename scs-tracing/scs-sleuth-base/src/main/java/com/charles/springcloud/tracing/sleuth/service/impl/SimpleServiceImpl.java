package com.charles.springcloud.tracing.sleuth.service.impl;

import com.charles.springcloud.tracing.service.AsyncService;
import com.charles.springcloud.tracing.service.callback.AsyncFailureCallback;
import com.charles.springcloud.tracing.service.callback.AsyncSuccessCallback;
import com.charles.springcloud.tracing.sleuth.annotation.LogActionStepTracer;
import com.charles.springcloud.tracing.sleuth.service.SimpleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.cloud.sleuth.annotation.SpanTag;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class SimpleServiceImpl implements SimpleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleServiceImpl.class);
    private final AsyncService asyncService;

    @Autowired
    public SimpleServiceImpl(AsyncService asyncService) {
        this.asyncService = asyncService;
    }

    @NewSpan(name = "testNewSpanMethod")
    @Override
    public void testNewSpanMethod(@SpanTag("testTag") String testTag) {
        LOGGER.info("This is a new span, testTag = {}", testTag);
    }

    @LogActionStepTracer(step = "testAsync1")
    @Override
    public void testAsync1() throws InterruptedException {
        ListenableFuture<String> future = asyncService.getListenableFuture();
        future.addCallback(new AsyncSuccessCallback(), new AsyncFailureCallback());
    }

    @LogActionStepTracer(step = "testAsync2")
    @Override
    public void testAsync2() throws InterruptedException {
        for (String s : TEST_NAMES) {
            asyncService.simpleAsyncPrint(s);
        }
    }

    @Async
    @LogActionStepTracer(step = "testAsync3")
    @Override
    public void testAsync3() throws ExecutionException, InterruptedException {
        // Start the clock
        long start = System.currentTimeMillis();
        // Kick of multiple, asynchronous lookups
        CompletableFuture<String> result1 = asyncService.getCompletableFuture();
        CompletableFuture<String> result2 = asyncService.getCompletableFuture();
        CompletableFuture<String> result3 = asyncService.getCompletableFuture();
        // Wait until they are all done
        CompletableFuture.allOf(result1, result2, result3).join();
        // Print results, including elapsed time
        LOGGER.info("Elapsed time: " + (System.currentTimeMillis() - start));
        LOGGER.info("--> " + result1.get());
        LOGGER.info("--> " + result2.get());
        LOGGER.info("--> " + result3.get());
    }

    @LogActionStepTracer(step = "testParallelStream")
    @Override
    public void testParallelStream() {
        TEST_NAMES.parallelStream().forEach(LOGGER::info);
    }
}
