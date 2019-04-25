package com.charles.springcloud.data.elasticsearch;

import org.elasticsearch.client.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories
public class ElasticsearchConfiguration {

    // 不启动本地elasticsearch的时候实例化
    //@Bean
    //public NodeClientFactoryBean client() {
    //    NodeClientFactoryBean bean = new NodeClientFactoryBean(true);
    //    bean.setClusterName(UUID.randomUUID().toString());
    //    bean.setEnableHttp(false);
    //    bean.setPathData("target/elasticsearchTestData");
    //    bean.setPathHome("src/test/resources/test-home-dir");
    //    return bean;
    //}

    @Bean
    public ElasticsearchTemplate elasticsearchTemplate(Client client) {
        return new ElasticsearchTemplate(client);
    }
}