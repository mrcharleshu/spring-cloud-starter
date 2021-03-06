package com.charles.springcloud.tracing.sleuth.service.impl;

import com.charles.springcloud.tracing.service.RemoteService;
import com.charles.springcloud.tracing.service.impl.HttpClientServiceImpl;
import com.charles.springcloud.tracing.sleuth.annotation.LogActionStepTracer;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.scheduling.annotation.Async;

public class SleuthHttpClientServiceImpl extends HttpClientServiceImpl implements RemoteService {

    public SleuthHttpClientServiceImpl(CloseableHttpClient httpClient) {
        super(httpClient);
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
