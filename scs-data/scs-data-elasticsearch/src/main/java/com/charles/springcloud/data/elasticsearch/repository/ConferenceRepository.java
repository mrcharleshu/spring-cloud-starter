package com.charles.springcloud.data.elasticsearch.repository;

import com.charles.springcloud.data.elasticsearch.document.Conference;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConferenceRepository extends ElasticsearchRepository<Conference, String> {
}