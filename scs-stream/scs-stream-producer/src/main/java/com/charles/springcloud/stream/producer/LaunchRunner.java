package com.charles.springcloud.stream.producer;

import com.charles.springcloud.stream.Log;
import com.charles.springcloud.stream.producer.service.SenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LaunchRunner implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(LaunchRunner.class);
    private final SenderService senderService;

    @Autowired
    public LaunchRunner(SenderService senderService) {
        this.senderService = senderService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = 5;
        for (int index = 1; index <= count; index++) {
            String msgContent = "msg-" + index;
            if (index % 3 == 0) {
                LOGGER.info("CustomRunner result = {},", senderService.sendString(msgContent));
            } else if (index % 3 == 1) {
                LOGGER.info("CustomRunner result = {},", senderService.sendWithTags(msgContent, "tagStr"));
            } else {
                LOGGER.info("CustomRunner result = {},", senderService.sendObject(new Log(index, "foo")));
            }
        }
    }
}