package com.charles.sleuth.annotation.impl;

import com.charles.sleuth.annotation.LogTracer;
import com.google.common.collect.Maps;
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

import java.util.Map;
import java.util.Random;

import static com.charles.sleuth.constants.CustomizedMdcKeys.BUSINESS;

@Aspect
@Component
@Order(2)
public class LogTracerAspect {
    private static Logger LOGGER = LoggerFactory.getLogger(LogTracerAspect.class);
    private final Tracer tracer;
    private final Map<String, Span> spanCache = Maps.newConcurrentMap();
    private static final String REENTRANT_KEY = "XC123535624";

    @Autowired
    public LogTracerAspect(Tracer tracer) {
        this.tracer = tracer;
    }

    @Around(value = "@annotation(tracer)")
    public Object process(ProceedingJoinPoint joinPoint, LogTracer tracer) {
        // 如果需要reentrant, 在第二次请求时本条日志的输出的traceId是新生成的
        LOGGER.info("************ one new tracer ************");
        try {
            MDC.put(BUSINESS, tracer.operation());
            this.tracer.getCurrentSpan().setBaggageItem(BUSINESS, tracer.operation());
            if (!tracer.reenterable()) {
                // 正常情况下此处的REENTRANT_KEY应该从方法中获取
                Span lastSpan = spanCache.get(REENTRANT_KEY);
                if (lastSpan != null) {
                    this.tracer.continueSpan(lastSpan);
                }
            }
            Object result = joinPoint.proceed();
            // 方法执行完从result中获取REENTRANT_KEY，如果需要，保存到缓存中
            if (tracer.reenterable()) {
                Span currentSpan = this.tracer.getCurrentSpan();
                Span span = Span.builder()
                        .parent(currentSpan.getSpanId())
                        .spanId(new Random().nextLong())
                        .traceId(currentSpan.getTraceId())
                        .baggage(currentSpan.getBaggage())
                        .build();
                spanCache.put(REENTRANT_KEY, span);
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

}