package com.charles.springcloud.tracing.service.callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.concurrent.FailureCallback;

public class AsyncFailureCallback implements FailureCallback {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncFailureCallback.class);

    @Override
    public void onFailure(Throwable ex) {
        LOGGER.info("ListenableFuture callback failed, result = {}", ex.getMessage());
    }
}
