package com.charles.springcloud.tracing.sleuth.event;

import com.charles.springcloud.tracing.sleuth.event.po.IntegerEvent;
import com.charles.springcloud.tracing.sleuth.event.po.StringEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class IntegerEventListener implements ApplicationListener<IntegerEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(IntegerEventListener.class);
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(IntegerEvent event) {
        LOGGER.info("from MyEventListener :" + event);
        LOGGER.info("event type: " + event.getClass());
    }

    @EventListener
    public void someMethod(ApplicationEvent event) {
        LOGGER.info("event :" + event);
        StringEvent stringEvent = new StringEvent(applicationContext);
        stringEvent.setValue("success");
        // return stringEvent;
    }
}
