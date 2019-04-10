package com.charles.zipkin.config;

import com.github.kristofa.brave.Brave;
import com.github.kristofa.brave.EmptySpanCollectorMetricsHandler;
import com.github.kristofa.brave.Sampler;
import com.github.kristofa.brave.SpanCollector;
import com.github.kristofa.brave.http.DefaultSpanNameProvider;
import com.github.kristofa.brave.http.HttpSpanCollector;
import com.github.kristofa.brave.okhttp.BraveOkHttpRequestResponseInterceptor;
import com.github.kristofa.brave.servlet.BraveServletFilter;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZipkinConfig {
    @Autowired
    private ZipkinProperties properties;

    @Bean
    public SpanCollector spanCollector() {
        HttpSpanCollector.Config config = HttpSpanCollector.Config.builder()
                .connectTimeout(properties.getConnectTimeout())
                .readTimeout(properties.getReadTimeout())
                .compressionEnabled(properties.isCompressionEnabled())
                .flushInterval(properties.getFlushInterval())
                .build();
        return HttpSpanCollector.create(properties.getUrl(), config, new EmptySpanCollectorMetricsHandler());
    }

    @Bean
    public Brave brave(SpanCollector spanCollector) {
        Brave.Builder builder = new Brave.Builder(properties.getServiceName());  //指定state
        builder.spanCollector(spanCollector);
        builder.traceSampler(Sampler.ALWAYS_SAMPLE);
        return builder.build();
    }

    @Bean
    public BraveServletFilter braveServletFilter(Brave brave) {
        return new BraveServletFilter(
                brave.serverRequestInterceptor(),
                brave.serverResponseInterceptor(),
                new DefaultSpanNameProvider()
        );
    }

    @Bean
    public OkHttpClient okHttpClient(Brave brave) {
        Interceptor interceptor = new BraveOkHttpRequestResponseInterceptor(
                brave.clientRequestInterceptor(),
                brave.clientResponseInterceptor(),
                new DefaultSpanNameProvider()
        );
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }
}
