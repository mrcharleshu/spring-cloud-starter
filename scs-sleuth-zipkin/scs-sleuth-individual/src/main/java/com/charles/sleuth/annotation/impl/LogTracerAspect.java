package com.charles.sleuth.annotation.impl;

import com.charles.sleuth.annotation.LogActionStepTracer;
import com.charles.sleuth.annotation.LogActionTracer;
import com.charles.sleuth.service.CacheService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import static com.charles.sleuth.constants.CustomizedMdcKeys.ACTION;
import static com.charles.sleuth.constants.CustomizedMdcKeys.STEP;

@Aspect
@Component
@Order(1)
public class LogTracerAspect {
    private static Logger LOGGER = LoggerFactory.getLogger(LogTracerAspect.class);
    private final Tracer tracer;
    public static final String REENTRANT_KEY = "XC123535624";
    private final CacheService cacheService;

    @Autowired
    public LogTracerAspect(Tracer tracer, CacheService cacheService) {
        this.tracer = tracer;
        this.cacheService = cacheService;
    }

    @Around(value = "@annotation(tracer)")
    public Object process(ProceedingJoinPoint joinPoint, LogActionTracer tracer) {
        // 如果需要reentrant, 在第二次请求时本条日志的输出的traceId是新生成的
        LOGGER.info("************ one new tracer ************");
        try {
            MDC.put(ACTION, tracer.action());
            this.tracer.getCurrentSpan().setBaggageItem(ACTION, tracer.action());
            Object result = joinPoint.proceed();
            // 方法执行完从result中获取REENTRANT_KEY，如果需要，保存到缓存中
            if (tracer.continued()) {
                Span currentSpan = this.tracer.getCurrentSpan();
                cacheService.cache(REENTRANT_KEY, currentSpan);
            }
            LOGGER.info("result = {}", result);
            return result;
        } catch (Throwable e) {
            LOGGER.error("MDC标识添加异常 ", e);
            throw new RuntimeException("MDC标识添加异常");
        } finally {
            MDC.clear();
        }
    }

    @Around(value = "@annotation(tracer)")
    public Object process(ProceedingJoinPoint joinPoint, LogActionStepTracer tracer) {
        // 如果需要reentrant, 在第二次请求时本条日志的输出的traceId是新生成的
        try {
            MDC.put(STEP, tracer.step());
            return joinPoint.proceed();
        } catch (Throwable e) {
            LOGGER.error("MDC标识添加异常 ", e);
            throw new RuntimeException("MDC标识添加异常");
        } finally {
            MDC.remove(STEP);
        }
    }
}