package com.customlogger; /**
 * Created by raghuram gururajan on 10/29/16.
 */

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.impl.MutableLogEvent;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;

/**
 * Plugin for converting request log id for display in logs
 */
@Plugin(name = "RequestLogIdConverter", category = "Converter")
@ConverterKeys({"reqLogid"})
public class RequestLogIdConverter extends LogEventPatternConverter {
    protected RequestLogIdConverter(String name, String style) {
        super(name, style);
    }


    public static RequestLogIdConverter newInstance(String[] options) {
        return new RequestLogIdConverter("reqLogid", Thread.currentThread().getName());
    }

    @Override
    public void format(LogEvent event, StringBuilder toAppendTo) {
        String message = ((MutableLogEvent) event).getFormattedMessage();

        toAppendTo.append(getRequestId());

    }

    protected String getRequestId() {

        String requestId = LogDataGenerator.getInstance().getRequestId();

        return requestId;
    }


}
