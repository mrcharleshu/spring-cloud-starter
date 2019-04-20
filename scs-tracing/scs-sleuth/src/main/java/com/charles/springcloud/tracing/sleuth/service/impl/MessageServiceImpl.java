package com.charles.springcloud.tracing.sleuth.service.impl;

import com.charles.springcloud.stream.Log;
import com.charles.springcloud.stream.channel.InputChannel;
import com.charles.springcloud.stream.channel.OutputChannel;
import com.charles.springcloud.tracing.service.RemoteService;
import com.charles.springcloud.tracing.sleuth.service.MessageService;
import com.charles.springcloud.tracing.sleuth.service.SimpleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;

@Service
public class MessageServiceImpl implements MessageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageServiceImpl.class);
    private static final Random random = new Random();
    private final SimpleService simpleService;
    private final RemoteService remoteService;
    private final OutputChannel outputChannel;

    @Autowired
    public MessageServiceImpl(SimpleService simpleService,
            @Qualifier(value = "sleuthRemoteServiceImpl") RemoteService remoteService,
            OutputChannel outputChannel) {
        this.remoteService = remoteService;
        this.simpleService = simpleService;
        this.outputChannel = outputChannel;
    }

    @StreamListener(InputChannel.INPUT_1)
    public void receive(Message<String> message) throws InterruptedException, ExecutionException {
        for (Map.Entry<String, Object> entry : message.getHeaders().entrySet()) {
            LOGGER.info("header key = {}, value = {}", entry.getKey(), entry.getValue());
        }
        LOGGER.info(message.getPayload());
        simpleService.testAsync1();
        simpleService.testAsync2();
        simpleService.testAsync3();
        remoteService.callService2();
    }

    private String getSendResultMessage(boolean sendResult) {
        String message = sendResult ? "发送成功" : "发送失败";
        LOGGER.info(message);
        return message;
    }

    @Override
    public String sendString(String content) {
        boolean isSendSuccess = outputChannel.output1().send(
                MessageBuilder.withPayload(content).build());
        return getSendResultMessage(isSendSuccess);
    }

    @Override
    public String sendObject(String content) {
        boolean isSendSuccess = outputChannel.output2().send(
                MessageBuilder.withPayload(new Log(random.nextInt(), content)).build());
        return getSendResultMessage(isSendSuccess);
    }
}
