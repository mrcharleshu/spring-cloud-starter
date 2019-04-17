package com.charles.springcloud.tracing.sleuth.service.impl;

import com.charles.springcloud.tracing.base.service.RemoteService;
import com.charles.springcloud.tracing.base.service.impl.RemoteServiceImpl;
import com.charles.springcloud.tracing.sleuth.annotation.LogActionStepTracer;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SleuthRemoteServiceImpl extends RemoteServiceImpl implements RemoteService {

    public SleuthRemoteServiceImpl(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @LogActionStepTracer(step = "callService2")
    @Override
    public String callService2() {
        return super.callService2();
    }

    @LogActionStepTracer(step = "callService3")
    @Override
    public String callService3() {
        return super.callService3();
    }

    @LogActionStepTracer(step = "callService3Async")
    @Async
    @Override
    public void callService3Async() {
        super.callService3Async();
    }

    @LogActionStepTracer(step = "callService4")
    @Override
    public String callService4() {
        return super.callService4();
    }
}
