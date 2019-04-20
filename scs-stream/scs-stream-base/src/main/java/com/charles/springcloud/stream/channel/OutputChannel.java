package com.charles.springcloud.stream.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface OutputChannel {
    String OUTPUT_1 = "output_1";
    String OUTPUT_2 = "output_2";

    @Output(OUTPUT_1)
    MessageChannel output1();

    @Output(OUTPUT_2)
    MessageChannel output2();
}