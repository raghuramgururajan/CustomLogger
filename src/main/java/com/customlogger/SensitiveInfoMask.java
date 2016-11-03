package com.customlogger; /**
 * Created by raghuram gururajan on 10/29/16.
 */

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.impl.MutableLogEvent;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;

/**
 * Plugin for masking sensitive info like password,ssn etc for display in logs
 */
@Plugin(name = "SensitiveInfoMaskConverter", category = "Converter")
@ConverterKeys({"m"})
public class SensitiveInfoMask extends LogEventPatternConverter {
    protected SensitiveInfoMask(String name, String style) {
        super(name, style);
    }


    public static SensitiveInfoMask newInstance(String[] options) {
        return new SensitiveInfoMask("m", "m");
    }

    @Override
    public void format(LogEvent event, StringBuilder toAppendTo) {
        String message = ((MutableLogEvent) event).getFormattedMessage();
        if (message != null && message.contains("password")) {
            toAppendTo.append(message.replaceAll("password=[^&]*", "password=***"));
        } else if (message != null && message.contains("ssn")) {
            toAppendTo.append(message.replaceAll("ssn=[^&]*", "ssn=***"));
        } else {
            toAppendTo.append(message);
        }


    }


}
