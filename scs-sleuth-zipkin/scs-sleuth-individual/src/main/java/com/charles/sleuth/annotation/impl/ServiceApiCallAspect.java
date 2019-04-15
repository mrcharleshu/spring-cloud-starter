package com.charles.sleuth.annotation.impl;

import com.charles.sleuth.annotation.ServiceApiCall;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class ServiceApiCallAspect {
    private static Logger LOGGER = LoggerFactory.getLogger(ServiceApiCallAspect.class);

    @Around(value = "@annotation(call)")
    public Object process(ProceedingJoinPoint joinPoint, ServiceApiCall call) {
        LOGGER.info("[{}]执行调用", call.service());
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            LOGGER.info("[{}]调用结束", call.service());
        }
    }
}