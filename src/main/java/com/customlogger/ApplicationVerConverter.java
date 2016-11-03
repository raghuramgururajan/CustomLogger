package com.customlogger; /**
 * Created by raghuram gururajan on 10/29/16.
 */

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;

/**
 * Converter for matching logs for application version
 */
@Plugin(name = "ApplicationVerConverter", category = "Converter")
@ConverterKeys({"applicationVer"})
public class ApplicationVerConverter extends LogEventPatternConverter {
    protected ApplicationVerConverter(String name, String style) {
        super(name, style);
    }


    public static ApplicationVerConverter newInstance(String[] options) {
        return new ApplicationVerConverter("applicationVer", "applicationVer");
    }

    @Override
    public void format(LogEvent event, StringBuilder toAppendTo) {

        toAppendTo.append(getApplicationVer());

    }

    public String getApplicationVer() {
        String applicationVer = LogDataGenerator.getInstance().getApplicationVer();

        return applicationVer;
    }


}
