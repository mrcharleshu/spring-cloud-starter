package com.charles.springcloud.data.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConferenceRepository extends ElasticsearchRepository<Conference, String> {
}