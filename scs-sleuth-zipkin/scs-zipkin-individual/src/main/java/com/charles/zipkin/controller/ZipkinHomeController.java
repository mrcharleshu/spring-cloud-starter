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
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/")
public class ZipkinHomeController {
    // private static final Logger LOGGER = Logger.getLogger(HomeController.class.getName());
    private static final Logger LOGGER = LoggerFactory.getLogger(ZipkinHomeController.class);
    private static final String SERVICE_2_API_URL = "http://localhost:9092/foo";
    private static final String SERVICE_3_API_URL = "http://localhost:9093/bar";
    private static final String SERVICE_4_API_URL = "http://localhost:9094/tar";
    private Random random = new Random();
    private final OkHttpClient client;

    @Autowired
    public ZipkinHomeController(OkHttpClient client) {
        this.client = client;
    }

    /**
     * service-1
     */
    @RequestMapping("start")
    public String start() throws InterruptedException, IOException {
        LOGGER.info("start");
        int sleep = random.nextInt(100);
        TimeUnit.MILLISECONDS.sleep(sleep);
        Request request = new Request.Builder().url(SERVICE_2_API_URL).get().build();
        Response response = client.newCall(request).execute();
        return " [service1 sleep " + sleep + " ms]" + response.body().toString();
    }

    /**
     * service-2
     * @return
     * @throws InterruptedException
     * @throws IOException
     */
    @RequestMapping("foo")
    public String foo() throws InterruptedException, IOException {
        LOGGER.info("foo");
        int sleep = random.nextInt(100);
        TimeUnit.MILLISECONDS.sleep(sleep);
        Request request = new Request.Builder().url(SERVICE_3_API_URL).get().build();  //service3
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        request = new Request.Builder().url(SERVICE_4_API_URL).get().build();  //service4
        response = client.newCall(request).execute();
        result += response.body().string();
        return " [service2 sleep " + sleep + " ms]" + result;
    }

    /**
     * service-3
     * @return
     * @throws InterruptedException
     */
    @RequestMapping("bar")
    public String bar() throws InterruptedException {
        LOGGER.info("bar");
        int sleep = random.nextInt(100);
        TimeUnit.MILLISECONDS.sleep(sleep);
        return " [service3 sleep " + sleep + " ms]";
    }

    /**
     * service-4
     */
    @RequestMapping("tar")
    public String tar() throws InterruptedException {
        LOGGER.info("tar");
        int sleep = random.nextInt(100);
        TimeUnit.MILLISECONDS.sleep(sleep);
        return " [service4 sleep " + sleep + " ms]";
    }
}
