package com.charles.sleuth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Service
public class SimpleServiceImpl implements SimpleService {
    // private final Tracer tracer;
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleServiceImpl.class);

    // @Autowired
    // public SimpleServiceImpl(Tracer tracer) {
    //     this.tracer = tracer;
    // }

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

    private void print(String str) {
        LOGGER.info("Simple print string: {}", str);
    }

    @Async
    @Override
    public void testAsync2() {
        Arrays.asList("Tom", "Jerry", "Mary", "Colin").forEach(this::print);
    }

    // private void testTracerAddTag() {
    //     tracer.addTag("operator", "Charles");
    // }

}
