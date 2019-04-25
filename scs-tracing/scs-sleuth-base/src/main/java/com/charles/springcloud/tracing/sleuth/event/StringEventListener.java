package com.charles.springcloud.tracing.sleuth.event;

import com.charles.springcloud.tracing.sleuth.event.po.StringEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StringEventListener implements ApplicationListener<StringEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(StringEventListener.class);

    @Override
    public void onApplicationEvent(StringEvent event) {
        LOGGER.info("from MyEventListener :" + event);
        LOGGER.info("event type: " + event.getClass());
    }

    @EventListener
    public void someMethod(ApplicationEvent event) {
        LOGGER.info("event :" + event);
    }
}
