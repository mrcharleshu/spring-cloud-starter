package com.charles.springcloud.data.elasticsearch.document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@Document(indexName = "log-tracer-2019.05.09", type = "doc", shards = 5, replicas = 1)
public class LogTracer {
    @Id
    private String id;
    @Field(type = FieldType.Date)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate logDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss.SSS")
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime logTime;
    private String logLevel;
    private String service;
    private String traceId;
    private String spanId;
    private String parentId;
    @Field(type = FieldType.Long)
    private Long pid;
    private String thread;
    private String className;
    private String invokeMethod;
    @Field(type = FieldType.Long)
    private Long invokeLine;
    private String tag;
    private String action;
    private String step;
    private String subStep;
    @Field(type = FieldType.Long)
    private Long timestamp;
    private String businessNo;
    private String tenantNo;
    private String companyCode;
    private String desc;
    @Field(type = FieldType.Long)
    private Long status;

    public LogTracer() {
    }

    public LogTracer(String id, LocalDate logDate, LocalTime logTime, String logLevel,
            String service, String traceId, String spanId, String parentId,
            Long pid, String thread, String className, String invokeMethod, Long invokeLine,
            String tag, String action, String step, String subStep,
            Long timestamp, String businessNo, String tenantNo, String companyCode, String desc, Long status) {
        this.id = id;
        this.logDate = logDate;
        this.logTime = logTime;
        this.logLevel = logLevel;
        this.service = service;
        this.traceId = traceId;
        this.spanId = spanId;
        this.parentId = parentId;
        this.pid = pid;
        this.thread = thread;
        this.className = className;
        this.invokeMethod = invokeMethod;
        this.invokeLine = invokeLine;
        this.tag = tag;
        this.action = action;
        this.step = step;
        this.subStep = subStep;
        this.timestamp = timestamp;
        this.businessNo = businessNo;
        this.tenantNo = tenantNo;
        this.companyCode = companyCode;
        this.desc = desc;
        this.status = status;
    }
}