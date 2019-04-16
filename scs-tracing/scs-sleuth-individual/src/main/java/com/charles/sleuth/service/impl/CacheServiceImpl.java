package com.charles.sleuth.service.impl;

import com.charles.sleuth.constants.ServiceNames;
import com.charles.sleuth.service.CacheService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.sleuth.Span;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CacheServiceImpl implements CacheService {
    private static final Map<String, Span> spanCache = Maps.newConcurrentMap();
    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public void cache(String key, Span span) {
        spanCache.put(key, span);
    }

    @Override
    public Span get(String key) {
        if (ServiceNames.SERVICE_1.equals(applicationName)) {
            Span span = spanCache.get(key);
            spanCache.remove(key);
            System.out.println(spanCache.keySet());
            return span;
        }
        return null;
    }
}
