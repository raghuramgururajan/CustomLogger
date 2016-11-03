package com.customlogger; /**
 * Created by raghuram gururajan on 10/29/16.
 */

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;

@Plugin(name = "UserIdConverter", category = "Converter")
@ConverterKeys({"userId"})
/**
 * Plugin for converting userid for display in logs
 */
public class UserIdConverter extends LogEventPatternConverter {
    protected UserIdConverter(String name, String style) {
        super(name, style);
    }


    public static UserIdConverter newInstance(String[] options) {
        return new UserIdConverter("userId", "userId");
    }

    @Override
    public void format(LogEvent event, StringBuilder toAppendTo) {
        String message = event.getMessage().getFormat();

        toAppendTo.append(getUserId());

    }

    protected String getUserId() {

        String userId = LogDataGenerator.getInstance().getUserId();

        return userId;
    }


}
