package com.charles.springcloud.stream.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface InputChannel {
    String INPUT_1 = "input_1";
    String INPUT_2 = "input_2";

    @Input(INPUT_1)
    SubscribableChannel kafkaInput1();

    @Input(INPUT_2)
    SubscribableChannel kafkaInput2();
}