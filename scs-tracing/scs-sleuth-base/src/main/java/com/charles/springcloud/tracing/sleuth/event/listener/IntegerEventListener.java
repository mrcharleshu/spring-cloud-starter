package com.charles.springcloud.tracing.sleuth.event.listener;

import com.charles.springcloud.tracing.sleuth.event.po.IntegerEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class IntegerEventListener implements ApplicationListener<IntegerEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(IntegerEventListener.class);

    @Override
    public void onApplicationEvent(IntegerEvent event) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.info("from IntegerEventListener :" + event);
        LOGGER.info("event type: " + event.getClass());
    }
}
