package com.charles.springcloud.data.elasticsearch.repository;

import com.charles.springcloud.data.elasticsearch.document.LogTracer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogTracerRepository extends ElasticsearchRepository<LogTracer, String> {
    List<LogTracer> findByBusinessNo(String businessNo);
}