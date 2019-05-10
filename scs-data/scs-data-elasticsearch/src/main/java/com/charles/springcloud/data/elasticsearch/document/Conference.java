package com.charles.springcloud.data.elasticsearch.document;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.util.List;

@Data
@Builder
@Document(indexName = "conference-index", type = "geo-class-point-type", shards = 1, replicas = 0, refreshInterval = "-1")
public class Conference {
    @Id
    private String id;
    private String name;
    @Field(type = FieldType.Date)
    private String date;
    @Field(type = FieldType.Integer)
    private Integer duration;
    private GeoPoint location;
    private List<String> keywords;

    // do not remove it
    public Conference() {
    }

    // do not remove it - work around for lombok generated constructor for all params
    public Conference(String id, String name, String date, Integer duration, GeoPoint location, List<String> keywords) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.duration = duration;
        this.location = location;
        this.keywords = keywords;
    }
}