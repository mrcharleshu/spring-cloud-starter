package com.charles.springcloud.tracing.sleuth.service.impl;

import com.charles.springcloud.tracing.service.RemoteService;
import com.charles.springcloud.tracing.service.impl.RibbonServiceImpl;
import com.charles.springcloud.tracing.sleuth.annotation.LogActionStepTracer;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.client.RestTemplate;

public class SleuthRibbonServiceImpl extends RibbonServiceImpl implements RemoteService {

    public SleuthRibbonServiceImpl(RestTemplate restTemplate) {
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
