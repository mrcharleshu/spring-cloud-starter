package com.charles.springcloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
@SpringBootApplication
public class SayHelloApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(SayHelloApplication.class);

    @RequestMapping(value = "/greeting")
    public String greet() {
        LOGGER.info("Access /greeting");
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> greetings = Arrays.asList("Hi there", "Greetings", "Salutations");
        Random rand = new Random();
        int randomNum = rand.nextInt(greetings.size());
        return greetings.get(randomNum);
    }

    @RequestMapping(value = "/")
    public String home() {
        LOGGER.info("Access /");
        return "Hi!";
    }

    public static void main(String[] args) {
        SpringApplication.run(SayHelloApplication.class, args);
    }
}