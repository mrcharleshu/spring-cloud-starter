package com.charles.springcloud.stream.producer.service;

import com.charles.springcloud.stream.channel.OutputChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SenderServiceImpl implements SenderService {
    private static final String PROPERTY_TAGS = "TAGS";
    private final OutputChannel outputChannel;

    @Autowired
    public SenderServiceImpl(OutputChannel outputChannel) {
        this.outputChannel = outputChannel;
    }

    @Override
    public boolean sendString(String msg) {
        return outputChannel.output1().send(MessageBuilder.withPayload(msg).build());
    }

    @Override
    public <T> boolean sendObject(T object) {
        Message message = MessageBuilder.withPayload(object)
                .setHeader(PROPERTY_TAGS, "testTagInHeader")
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build();
        return outputChannel.output2().send(message);
    }

    @Override
    public boolean sendWithTags(String msg, String tag) {
        Message message = MessageBuilder.createMessage(msg, new MessageHeaders(
                Stream.of(tag).collect(Collectors.toMap(str -> PROPERTY_TAGS, String::toString))));
        return outputChannel.output1().send(message);
    }
}
