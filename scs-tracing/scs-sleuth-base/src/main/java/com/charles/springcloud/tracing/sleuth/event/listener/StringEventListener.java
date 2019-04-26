package com.charles.springcloud.tracing.sleuth.event.listener;

import com.charles.springcloud.tracing.sleuth.event.po.StringEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class StringEventListener implements ApplicationListener<StringEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(StringEventListener.class);

    @Override
    public void onApplicationEvent(StringEvent event) {
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.info("from StringEventListener :" + event);
        LOGGER.info("event type: " + event.getClass());
    }
}
