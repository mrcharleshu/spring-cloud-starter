package com.charles.skywalking.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RemoteServiceImpl implements RemoteService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RemoteServiceImpl.class);
    private static final String SERVICE_2_API_URL = "http://SERVICE-2/foo";
    private static final String SERVICE_3_API_URL = "http://SERVICE-3/bar";
    private static final String SERVICE_4_API_URL = "http://SERVICE-4/tar";
    private final RestTemplate restTemplate;

    @Autowired
    public RemoteServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String callService2() {
        return restTemplate.getForEntity(SERVICE_2_API_URL, String.class).getBody();
    }

    @Override
    public String callService3() {
        return restTemplate.getForEntity(SERVICE_3_API_URL, String.class).getBody();
    }

    @Async
    @Override
    public void callService3Async() {
        LOGGER.info("call service3 async ...");
        callService3();
    }

    @Override
    public String callService4() {
        return restTemplate.getForEntity(SERVICE_4_API_URL, String.class).getBody();
    }
}
