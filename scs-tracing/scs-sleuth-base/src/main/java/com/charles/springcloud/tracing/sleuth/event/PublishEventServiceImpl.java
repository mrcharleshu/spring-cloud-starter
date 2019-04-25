package com.charles.springcloud.tracing.sleuth.event;

import com.charles.springcloud.tracing.sleuth.event.po.IntegerEvent;
import com.charles.springcloud.tracing.sleuth.event.po.StringEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class PublishEventServiceImpl implements PublishEventService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PublishEventServiceImpl.class);
    private final ApplicationContext applicationContext;

    @Autowired
    public PublishEventServiceImpl(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void testSimpleEvent() {
        LOGGER.info("We now send simple event");
        StringEvent stringEvent = new StringEvent(applicationContext);
        stringEvent.setValue("simple event method");
        applicationContext.publishEvent(stringEvent);
    }

    @Override
    public void testEventWithReturnValue() {
        LOGGER.info("We now send event with return value");
        IntegerEvent integerValue = new IntegerEvent(applicationContext);
        integerValue.setValue(3);
        applicationContext.publishEvent(integerValue);
    }
}
