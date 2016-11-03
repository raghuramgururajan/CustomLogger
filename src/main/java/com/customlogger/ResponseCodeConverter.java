package com.customlogger; /**
 * Created by raghuram gururajan on 10/29/16.
 */
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;
/**
 * Plugin for converting referrer url for display in logs
 */
@Plugin(name="ResponseCodeConverter", category = "Converter")
@ConverterKeys({"responseCode"})
public class ResponseCodeConverter extends LogEventPatternConverter {
    protected ResponseCodeConverter(String name, String style) {
        super(name, style);
    }


    public static ResponseCodeConverter newInstance(String[] options) {
        return new ResponseCodeConverter("responseCode","responseCode");
    }

    @Override
    public void format(LogEvent event, StringBuilder toAppendTo) {

        toAppendTo.append(getResponseCode());

    }

    public String getResponseCode() {
        String responseCode = LogDataGenerator.getInstance().getResponseCode();
        return responseCode;
    }



}
