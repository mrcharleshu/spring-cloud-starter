package com.charles.springcloud.tracing.base.service;

public interface RemoteService {
    String callService2();

    String callService3();

    void callService3Async();

    String callService4();
}
