package com.charles.springcloud.stream.consumer;

import com.charles.springcloud.stream.Log;
import com.charles.springcloud.stream.channel.InputChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class ReceiverService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReceiverService.class);

    @StreamListener(InputChannel.INPUT_1)
    public void receiveInput1(Message<String> message) {
        LOGGER.info("{} receive: {}", InputChannel.INPUT_1, message.getPayload());
    }

    @StreamListener(InputChannel.INPUT_2)
    public void receiveInput2(@Payload Log log) {
        LOGGER.info("{} receive: {}", InputChannel.INPUT_2, log);
    }
}
