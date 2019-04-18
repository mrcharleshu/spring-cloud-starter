package com.charles.springcloud.tracing.service.callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.concurrent.SuccessCallback;

public class AsyncSuccessCallback implements SuccessCallback<String> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncSuccessCallback.class);

    @Override
    public void onSuccess(String result) {
        LOGGER.info("ListenableFuture callback success, result = {}", result);
    }
}
