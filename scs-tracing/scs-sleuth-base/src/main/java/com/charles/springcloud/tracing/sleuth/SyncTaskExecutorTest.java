package com.charles.springcloud.tracing.sleuth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.SyncTaskExecutor;

@Slf4j
public class SyncTaskExecutorTest {

    public static void main(String[] args) {
        SyncTaskExecutor executor = new SyncTaskExecutor();

        log.info("Hello");

        executor.execute(new Runnable() {
            @Override
            public void run() {
                log.info("World");
            }
        });

        log.info("Charles");
    }
}
