package com.charles.springcloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
@SpringBootApplication
public class SayHelloApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(SayHelloApplication.class);
    private static final List<String> greetings = Arrays.asList("Hi there", "Greetings", "Salutations");

    @GetMapping(value = "/greeting")
    public String greet(@RequestParam(name = "name") String name) {
        LOGGER.info("Access /greeting ?name={}", name);
        sleep();
        if (StringUtils.isEmpty(name)) {
            Random rand = new Random();
            int randomNum = rand.nextInt(greetings.size());
            return greetings.get(randomNum);
        } else {
            return name;
        }
    }

    private void sleep() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @GetMapping(value = "/")
    public String home() {
        LOGGER.info("Status: Alive");
        return "Hi!";
    }

    public static void main(String[] args) {
        SpringApplication.run(SayHelloApplication.class, args);
    }
}