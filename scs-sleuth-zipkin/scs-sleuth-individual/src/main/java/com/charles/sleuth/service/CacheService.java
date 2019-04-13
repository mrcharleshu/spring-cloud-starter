package com.charles.sleuth.service;

import org.springframework.cloud.sleuth.Span;

public interface CacheService {

    void cache(String key, Span span);

    Span get(String key);
}
