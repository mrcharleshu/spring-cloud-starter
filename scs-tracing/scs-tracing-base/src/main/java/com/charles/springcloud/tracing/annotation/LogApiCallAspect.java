package com.charles.springcloud.tracing.annotation;

import com.charles.springcloud.tracing.constants.AspectOrder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(AspectOrder.LOG_API_CALL_ORDER)
public class LogApiCallAspect {
    private static Logger LOGGER = LoggerFactory.getLogger(LogApiCallAspect.class);

    @Around(value = "@annotation(call)")
    public Object process(ProceedingJoinPoint joinPoint, LogApiCall call) {
        LOGGER.info("[{}]执行调用", call.action());
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            LOGGER.info("[{}]调用结束", call.action());
        }
    }
}