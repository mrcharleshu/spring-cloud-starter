package com.charles.springcloud.tracing;

import com.charles.springcloud.tracing.service.RemoteService;
import com.charles.springcloud.tracing.sleuth.service.impl.SleuthHttpClientServiceImpl;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SleuthHttpClientApplication {

    @Bean
    public CloseableHttpClient httpClient() {
        final int DEFAULT_TIMEOUT_MILLIS = 3000;
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(DEFAULT_TIMEOUT_MILLIS)
                .setSocketTimeout(DEFAULT_TIMEOUT_MILLIS)
                .build();
        // return TracingHttpClientBuilder.create(tracing).setDefaultRequestConfig(requestConfig).build();
        return HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
    }

    @Bean
    public RemoteService remoteService(CloseableHttpClient httpClient) {
        return new SleuthHttpClientServiceImpl(httpClient);
    }

    public static void main(String[] args) {
        SpringApplication.run(SleuthHttpClientApplication.class, args);
    }
}
