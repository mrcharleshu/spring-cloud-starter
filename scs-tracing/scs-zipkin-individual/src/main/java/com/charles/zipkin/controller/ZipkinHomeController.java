package com.charles.zipkin.controller;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/")
public class ZipkinHomeController {
    // private static final Logger LOGGER = Logger.getLogger(HomeController.class.getName());
    private static final Logger LOGGER = LoggerFactory.getLogger(ZipkinHomeController.class);
    private static final String SERVICE_2_API_URL = "http://localhost:9096/foo";
    private static final String SERVICE_3_API_URL = "http://localhost:9097/bar";
    private static final String SERVICE_4_API_URL = "http://localhost:9098/tar";
    private Random random = new Random();
    private final OkHttpClient client;

    @Autowired
    public ZipkinHomeController(OkHttpClient client) {
        this.client = client;
    }

    private int sleep() throws InterruptedException {
        int sleep = random.nextInt(100);
        TimeUnit.MILLISECONDS.sleep(sleep);
        return sleep;
    }

    private String getResponse(Response response) {
        Optional body = Optional.ofNullable(response.body());
        return body.isPresent() ? body.toString() : "";
    }

    private String doRequest(String url) throws IOException {
        Request request = new Request.Builder().url(url).get().build();
        Response response = client.newCall(request).execute();
        return getResponse(response);
    }

    /**
     * service-1
     */
    @RequestMapping("start")
    public String start() throws InterruptedException, IOException {
        LOGGER.info("start");
        int sleep = sleep();
        String response = doRequest(SERVICE_2_API_URL);
        return " [service1 sleep " + sleep + " ms]" + response;
    }

    /**
     * service-2
     */
    @RequestMapping("foo")
    public String foo() throws InterruptedException, IOException {
        LOGGER.info("foo");
        int sleep = sleep();
        String response1 = doRequest(SERVICE_3_API_URL);
        String response2 = doRequest(SERVICE_4_API_URL);
        return " [service2 sleep " + sleep + " ms]" + response1 + response2;
    }

    /**
     * service-3
     */
    @RequestMapping("bar")
    public String bar() throws InterruptedException {
        LOGGER.info("bar");
        int sleep = sleep();
        return " [service3 sleep " + sleep + " ms]";
    }

    /**
     * service-4
     */
    @RequestMapping("tar")
    public String tar() throws InterruptedException {
        LOGGER.info("tar");
        int sleep = sleep();
        return " [service4 sleep " + sleep + " ms]";
    }
}
