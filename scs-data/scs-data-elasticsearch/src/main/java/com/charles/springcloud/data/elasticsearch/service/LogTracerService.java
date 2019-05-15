package com.charles.springcloud.data.elasticsearch.service;

import com.charles.springcloud.data.elasticsearch.document.LogTracer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LogTracerService {
    Long count();

    List<LogTracer> findByBusinessNo(String businessNo);

    Page<LogTracer> search(String businessNo);
}
