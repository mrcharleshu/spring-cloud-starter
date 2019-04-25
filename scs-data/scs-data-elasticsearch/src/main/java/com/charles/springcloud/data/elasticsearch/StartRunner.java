package com.charles.springcloud.data.elasticsearch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class StartRunner implements CommandLineRunner, Ordered {
    private static final Logger LOGGER = LoggerFactory.getLogger(StartRunner.class);
    private final ElasticsearchOperations operations;
    private final ConferenceRepository conferenceRepository;

    @Autowired
    public StartRunner(ConferenceRepository conferenceRepository, ElasticsearchOperations operations) {
        this.conferenceRepository = conferenceRepository;
        this.operations = operations;
    }

    @Override
    public void run(String... args) throws Exception {
        // Remove all documents
        // conferenceRepository.deleteAll();
        LOGGER.info("all records count: {}", conferenceRepository.count());
        insertSampleData();
        LOGGER.info("all records count: {}", conferenceRepository.count());
        insertSampleData();
        LOGGER.info("all records count: {}", conferenceRepository.count());
    }

    private void insertSampleData() {
        operations.refresh(Conference.class);

        // Save data sample
        conferenceRepository.save(Conference.builder().date("2014-11-06").name("Spring eXchange 2014 - London")
                .keywords(Arrays.asList("java", "spring")).location(new GeoPoint(51.500152D, -0.126236D)).build());
        conferenceRepository.save(Conference.builder().date("2014-12-07").name("Scala eXchange 2014 - London")
                .keywords(Arrays.asList("scala", "play", "java")).location(new GeoPoint(51.500152D, -0.126236D)).build());
        conferenceRepository.save(Conference.builder().date("2014-11-20").name("Elasticsearch 2014 - Berlin")
                .keywords(Arrays.asList("java", "elasticsearch", "kibana")).location(new GeoPoint(52.5234051D, 13.4113999))
                .build());
        conferenceRepository.save(Conference.builder().date("2014-11-12").name("AWS London 2014")
                .keywords(Arrays.asList("cloud", "aws")).location(new GeoPoint(51.500152D, -0.126236D)).build());
        conferenceRepository.save(Conference.builder().date("2014-10-04").name("JDD14 - Cracow")
                .keywords(Arrays.asList("java", "spring")).location(new GeoPoint(50.0646501D, 19.9449799)).build());
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
