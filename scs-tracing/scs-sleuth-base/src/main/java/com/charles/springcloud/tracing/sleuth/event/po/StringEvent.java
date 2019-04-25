package com.charles.springcloud.tracing.sleuth.event.po;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

public class StringEvent extends ApplicationContextEvent {
    private String value;

    /**
     * Create a new ContextStartedEvent.
     * @param source the {@code ApplicationContext} that the event is raised for
     *               (must not be {@code null})
     */
    public StringEvent(ApplicationContext source) {
        super(source);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
