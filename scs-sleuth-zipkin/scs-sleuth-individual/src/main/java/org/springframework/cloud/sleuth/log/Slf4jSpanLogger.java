package org.springframework.cloud.sleuth.log;

import com.charles.sleuth.constants.CustomizedMdcKeys;
import org.slf4j.Logger;
import org.slf4j.MDC;
import org.springframework.cloud.sleuth.Span;

import java.util.regex.Pattern;

public class Slf4jSpanLogger implements SpanLogger {

    private final Logger log;
    private final Pattern nameSkipPattern;

    public Slf4jSpanLogger(String nameSkipPattern) {
        this.nameSkipPattern = Pattern.compile(nameSkipPattern);
        this.log = org.slf4j.LoggerFactory.getLogger(Slf4jSpanLogger.class);
    }

    Slf4jSpanLogger(String nameSkipPattern, Logger log) {
        this.nameSkipPattern = Pattern.compile(nameSkipPattern);
        this.log = log;
    }

    @Override
    public void logStartedSpan(Span parent, Span span) {
        MDC.put(Span.SPAN_ID_NAME, Span.idToHex(span.getSpanId()));
        MDC.put(Span.SPAN_EXPORT_NAME, String.valueOf(span.isExportable()));
        MDC.put(Span.TRACE_ID_NAME, span.traceIdString());
        MDC.put(CustomizedMdcKeys.BUSINESS, span.getBaggageItem(CustomizedMdcKeys.BUSINESS));
        log("Starting span: {}", span);
        if (parent != null) {
            log("With parent: {}", parent);
            MDC.put(Span.PARENT_ID_NAME, Span.idToHex(parent.getSpanId()));
        }
    }

    @Override
    public void logContinuedSpan(Span span) {
        MDC.put(Span.SPAN_ID_NAME, Span.idToHex(span.getSpanId()));
        MDC.put(Span.TRACE_ID_NAME, span.traceIdString());
        MDC.put(Span.SPAN_EXPORT_NAME, String.valueOf(span.isExportable()));
        MDC.put(CustomizedMdcKeys.BUSINESS, span.getBaggageItem(CustomizedMdcKeys.BUSINESS));
        setParentIdIfPresent(span);
        log("Continued span: {}", span);
    }

    private void setParentIdIfPresent(Span span) {
        if (!span.getParents().isEmpty()) {
            MDC.put(Span.PARENT_ID_NAME, Span.idToHex(span.getParents().get(0)));
        }
    }

    @Override
    public void logStoppedSpan(Span parent, Span span) {
        if (span != null) {
            log("Stopped span: {}", span);
        }
        if (span != null && parent != null) {
            log("With parent: {}", parent);
            MDC.put(Span.SPAN_ID_NAME, Span.idToHex(parent.getSpanId()));
            MDC.put(Span.SPAN_EXPORT_NAME, String.valueOf(parent.isExportable()));
            MDC.put(CustomizedMdcKeys.BUSINESS, span.getBaggageItem(CustomizedMdcKeys.BUSINESS));
            setParentIdIfPresent(parent);
        } else {
            MDC.remove(Span.SPAN_ID_NAME);
            MDC.remove(Span.SPAN_EXPORT_NAME);
            MDC.remove(Span.TRACE_ID_NAME);
            MDC.remove(Span.PARENT_ID_NAME);
            MDC.remove(CustomizedMdcKeys.BUSINESS);
        }
    }

    private void log(String text, Span span) {
        if (span != null && this.nameSkipPattern.matcher(span.getName()).matches()) {
            return;
        }
        if (this.log.isTraceEnabled()) {
            this.log.trace(text, span);
        }
    }

}