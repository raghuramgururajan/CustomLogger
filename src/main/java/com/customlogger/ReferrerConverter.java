package com.customlogger;
/**
 * Created by raghuram gururajan on 10/29/16.
 */

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;

/**
 * Plugin for converting referrer url for display in logs
 */
@Plugin(name = "ReferrerConverter", category = "Converter")
@ConverterKeys({"referrer"})
public class ReferrerConverter extends LogEventPatternConverter {
    protected ReferrerConverter(String name, String style) {
        super(name, style);
    }


    public static ReferrerConverter newInstance(String[] options) {
        return new ReferrerConverter("referrer", "referrer");
    }

    @Override
    public void format(LogEvent event, StringBuilder toAppendTo) {

        toAppendTo.append(getReferrer());

    }

    public String getReferrer() {
        String refferer = LogDataGenerator.getInstance().getReferrer();
        return refferer;
    }


}
