package com.charles.springcloud.tracing.sleuth.event.po;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

public class IntegerEvent extends ApplicationContextEvent {
    private Integer value;

    /**
     * Create a new ContextStartedEvent.
     * @param source the {@code ApplicationContext} that the event is raised for
     *               (must not be {@code null})
     */
    public IntegerEvent(ApplicationContext source) {
        super(source);
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
