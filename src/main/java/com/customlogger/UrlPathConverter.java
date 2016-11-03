package com.customlogger; /**
 * Created by raghuram gururajan on 10/29/16.
 */

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;

/**
 * Plugin for converting  url path for display in logs
 */
@Plugin(name = "UrlPathConverter", category = "Converter")
@ConverterKeys({"urlPath"})
public class UrlPathConverter extends LogEventPatternConverter {
    protected UrlPathConverter(String name, String style) {
        super(name, style);
    }


    public static UrlPathConverter newInstance(String[] options) {
        return new UrlPathConverter("urlPath", "urlPath");
    }

    @Override
    public void format(LogEvent event, StringBuilder toAppendTo) {

        toAppendTo.append(getUrlPath());

    }

    public String getUrlPath() {
        String urlPath = LogDataGenerator.getInstance().getUrlPath();
        return urlPath;
    }


}
