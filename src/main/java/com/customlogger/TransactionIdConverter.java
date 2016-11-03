package com.customlogger; /**
 * Created by raghuram gururajan on 10/29/16.
 */

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.impl.MutableLogEvent;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;

/**
 * Plugin for converting transaction id for display in logs
 */
@Plugin(name = "TransactionIdConverter", category = "Converter")
@ConverterKeys({"transactionId"})
public class TransactionIdConverter extends LogEventPatternConverter {
    protected TransactionIdConverter(String name, String style) {
        super(name, style);
    }


    public static TransactionIdConverter newInstance(String[] options) {
        return new TransactionIdConverter("transactionId", "transactionId");
    }

    @Override
    public void format(LogEvent event, StringBuilder toAppendTo) {
        String message = ((MutableLogEvent) event).getFormattedMessage();

        toAppendTo.append(getTransactionId());

    }

    protected String getTransactionId() {

        String transactionId = LogDataGenerator.getInstance().getTransactionId();

        return transactionId;
    }


}
