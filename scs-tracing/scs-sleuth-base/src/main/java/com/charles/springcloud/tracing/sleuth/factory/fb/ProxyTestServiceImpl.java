package com.charles.springcloud.tracing.sleuth.factory.fb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProxyTestServiceImpl implements ProxyTestService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProxyTestServiceImpl.class);

    @Override
    public void print() {
        LOGGER.info("Hello world, print proxied");
    }
}
