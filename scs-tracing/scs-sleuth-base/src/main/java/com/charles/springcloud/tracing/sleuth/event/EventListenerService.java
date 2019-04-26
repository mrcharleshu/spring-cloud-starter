package com.charles.springcloud.tracing.sleuth.event;

import com.charles.springcloud.tracing.sleuth.event.po.StringEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EventListenerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventListenerService.class);

    @Async
    @EventListener
    public void stringEventListener(StringEvent event) {
        LOGGER.info("anther event subscriber, {}", event.getValue());
    }
}
