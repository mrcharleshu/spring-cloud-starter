package com.charles.springcloud.tracing.service.impl;

import com.charles.springcloud.tracing.service.RemoteService;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class HttpClientServiceImpl implements RemoteService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientServiceImpl.class);
    private static final String SERVICE_2_API_URL = "http://localhost:8102/foo";
    private static final String SERVICE_3_API_URL = "http://localhost:8103/bar";
    private static final String SERVICE_4_API_URL = "http://localhost:8104/tar";
    private final CloseableHttpClient httpClient;

    public HttpClientServiceImpl(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    private String doGet(String url) {
        HttpGet httpGet = new HttpGet(url);
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String callService2() {
        return doGet(SERVICE_2_API_URL);
    }

    @Override
    public String callService3() {
        return doGet(SERVICE_3_API_URL);
    }

    @Async
    @Override
    public void callService3Async() {
        LOGGER.info("call service3 async ...");
        callService3();
    }

    @Override
    public String callService4() {
        return doGet(SERVICE_4_API_URL);
    }
}
