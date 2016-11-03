package com.customlogger; /**
 * Created by raghuram gururajan on 10/29/16.
 */

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;

/**
 * Plugin for converting useragent for display in logs
 */
@Plugin(name = "UserAgentConverter", category = "Converter")
@ConverterKeys({"useragent"})
public class UserAgentConverter extends LogEventPatternConverter {
    protected UserAgentConverter(String name, String style) {
        super(name, style);
    }


    public static UserAgentConverter newInstance(String[] options) {
        return new UserAgentConverter("useragent", "useragent");
    }

    @Override
    public void format(LogEvent event, StringBuilder toAppendTo) {

        toAppendTo.append(getUserAgent());


    }

    protected String getRequestId() {

        String requestId = LogDataGenerator.getInstance().getRequestId();

        return requestId;
    }

    protected String getUserAgent() {

        String userAgent = LogDataGenerator.getInstance().getUserAgent();

        return userAgent;
    }


}
