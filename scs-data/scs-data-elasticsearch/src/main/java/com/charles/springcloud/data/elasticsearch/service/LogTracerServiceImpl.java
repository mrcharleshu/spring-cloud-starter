package com.charles.springcloud.data.elasticsearch.service;

import com.charles.springcloud.data.elasticsearch.document.LogTracer;
import com.charles.springcloud.data.elasticsearch.repository.LogTracerRepository;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogTracerServiceImpl implements LogTracerService {
    @Autowired
    private LogTracerRepository logTracerRepository;

    @Override
    public Long count() {
        return logTracerRepository.count();
    }

    @Override
    public List<LogTracer> findByBusinessNo(String businessNo) {
        return logTracerRepository.findByBusinessNo(businessNo);
    }

    @Override
    public Page<LogTracer> search(String businessNo) {
        QueryBuilder queryBuilder = QueryBuilders.queryStringQuery(businessNo);
        Sort sort = Sort.by(Sort.Order.desc("timestamp"));
        PageRequest pageable = PageRequest.of(0, 4, sort);
        return logTracerRepository.search(queryBuilder, pageable);
    }
}
