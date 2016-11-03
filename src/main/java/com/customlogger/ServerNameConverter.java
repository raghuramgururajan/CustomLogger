package com.customlogger; /**
 * Created by raghuram gururajan on 10/29/16.
 */

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.impl.MutableLogEvent;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;

/**
 * Plugin for converting servername  for display in logs
 */
@Plugin(name = "ServerNameConverter", category = "Converter")
@ConverterKeys({"serverName"})
public class ServerNameConverter extends LogEventPatternConverter {
    protected ServerNameConverter(String name, String style) {
        super(name, style);
    }


    public static ServerNameConverter newInstance(String[] options) {
        return new ServerNameConverter("serverName", "serverName");
    }

    @Override
    public void format(LogEvent event, StringBuilder toAppendTo) {
        String message = ((MutableLogEvent) event).getFormattedMessage();

        toAppendTo.append(getServerName());

    }

    protected String getServerName() {

        String serverName = LogDataGenerator.getInstance().getServerName();

        return serverName;
    }


}
