package com.charles.springcloud.data.elasticsearch;

import com.charles.springcloud.data.elasticsearch.document.Conference;
import com.charles.springcloud.data.elasticsearch.document.LogTracer;
import com.charles.springcloud.data.elasticsearch.repository.ConferenceRepository;
import com.charles.springcloud.data.elasticsearch.service.LogTracerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class StartRunner implements CommandLineRunner, Ordered {
    private static final Logger LOGGER = LoggerFactory.getLogger(StartRunner.class);
    private final ElasticsearchOperations operations;
    private final ConferenceRepository conferenceRepository;
    private final LogTracerService logTracerService;

    @Autowired
    public StartRunner(ElasticsearchOperations operations,
            ConferenceRepository conferenceRepository, LogTracerService logTracerService) {
        this.operations = operations;
        this.conferenceRepository = conferenceRepository;
        this.logTracerService = logTracerService;
    }

    @Override
    public void run(String... args) throws Exception {
        runLogTracer();
        // runConference();
    }

    private void runLogTracer() {
        LOGGER.info("all records count: {}", logTracerService.count());
        // Iterable<LogTracer> logTracers = logTracerRepository.findAll();
        LOGGER.info("find by condition");
        List<LogTracer> logTracers = logTracerService.findByBusinessNo("A000044");
        logTracers.forEach(System.out::println);
        LOGGER.info("find by page request");
        Page<LogTracer> page = logTracerService.search("A000056");
        LOGGER.info("after paging query, result = {}", page.toString());
        page.forEach(System.out::println);
    }

    private void runConference() {
        LOGGER.info("all records count: {}", conferenceRepository.count());
        insertConferenceSampleData();
        LOGGER.info("all records count: {}", conferenceRepository.count());
        // insertConferenceSampleData();
        // LOGGER.info("all records count: {}", conferenceRepository.count());
    }

    private void insertConferenceSampleData() {
        operations.refresh(Conference.class);

        // Save data sample
        conferenceRepository.save(Conference.builder().date("2014-11-06").name("Spring eXchange 2014 - London").duration(45)
                .keywords(Arrays.asList("java", "spring")).location(new GeoPoint(51.500152D, -0.126236D)).build());
        conferenceRepository.save(Conference.builder().date("2014-12-07").name("Scala eXchange 2014 - London").duration(30)
                .keywords(Arrays.asList("scala", "play", "java")).location(new GeoPoint(51.500152D, -0.126236D)).build());
        conferenceRepository.save(Conference.builder().date("2014-11-20").name("Elasticsearch 2014 - Berlin").duration(45)
                .keywords(Arrays.asList("java", "elasticsearch", "kibana")).location(new GeoPoint(52.5234051D, 13.4113999))
                .build());
        conferenceRepository.save(Conference.builder().date("2014-11-12").name("AWS London 2014").duration(60)
                .keywords(Arrays.asList("cloud", "aws")).location(new GeoPoint(51.500152D, -0.126236D)).build());
        conferenceRepository.save(Conference.builder().date("2014-10-04").name("JDD14 - Cracow").duration(90)
                .keywords(Arrays.asList("java", "spring")).location(new GeoPoint(50.0646501D, 19.9449799)).build());
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
