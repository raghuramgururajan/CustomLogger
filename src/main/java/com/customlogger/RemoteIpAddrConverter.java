package com.customlogger; /**
 * Created by raghuram gururajan on 10/29/16.
 */
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;
/**
 * Plugin for converting remote ip addr for display in logs
 */
@Plugin(name="RemoteIpAddrConverter", category = "Converter")
@ConverterKeys({"remoteIpAddr"})
public class RemoteIpAddrConverter extends LogEventPatternConverter {
    protected RemoteIpAddrConverter(String name, String style) {
        super(name, style);
    }


    public static RemoteIpAddrConverter newInstance(String[] options) {
        return new RemoteIpAddrConverter("remoteIpAddr","remoteIpAddr");
    }

    @Override
    public void format(LogEvent event, StringBuilder toAppendTo) {

        toAppendTo.append(getRemoteIpAddr());

    }

    public String getRemoteIpAddr() {
        String remoteIp = LogDataGenerator.getInstance().getRemoteIpAddr();

        return remoteIp;
    }




}
