package com.charles.springcloud.tracing.sleuth.event.po;

import org.springframework.context.ApplicationEvent;

public class IntegerEvent extends ApplicationEvent {
    private Integer value;

    /**
     * Create a new ApplicationEvent.
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public IntegerEvent(Object source) {
        super(source);
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
