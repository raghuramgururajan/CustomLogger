package com.customlogger;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.*;
import org.junit.*;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.*;

/**
 * Created by raghuram gururajan on 10/30/16.
 */
public class LogFormatConverterTest {

    StringAppender appender = null;

    @Before
    public void setUp() throws Exception {
        //Set up the data for logging
        LogDataGenerator.getInstance().setApplicationVer("1.0");
        LogDataGenerator.getInstance().setRequestId("1244-123-344");
        LogDataGenerator.getInstance().setServerName("payments-wls-01");
        LogDataGenerator.getInstance().setTransactionId("12222345");
        LogDataGenerator.getInstance().setUserId("abc@xyz");
        LogDataGenerator.getInstance().setUserAgent("Mozilla/5.0");
        LogDataGenerator.getInstance().setResponseCode("200 success");
        LogDataGenerator.getInstance().setReferrer("www.yahoo.com");
        LogDataGenerator.getInstance().setRemoteIpAddr("10.121.22.24");
        LogDataGenerator.getInstance().setUrlPath("/application/v1.0/accounts");
        // Create a String Appender to capture log output
        appender = StringAppender.createStringAppender("Timestamp = %d{yyyy-MM-dd HH:mm:ss.SSS z} | RequestId = %reqLogid | UserId = %userId | TransactionId = %transactionId | ServerName = %serverName | Useragent = %useragent | UrlPath = %urlPath | ResponseCode= %responseCode | remoteIp = %remoteIpAddr | referrer = %referrer | Severity=%-5p | ComponentName=%c{1} | MethodName=%M | LineNumber=%L | Message=%m%xEx%n");
    }

    @Test
    public void testLogFormat() {
        // Get the RootLogger
        Logger logger = LogManager.getLogger(LogFormatConverterTest.class);
        LogDataGenerator.getInstance().setRequestId("123-456-789");
        LogDataGenerator.getInstance().setTransactionId("111-222-333");
        appender.start();

        // Create a String Appender to capture log output
        appender.addToLogger(logger.getName(), Level.INFO);
        logger.info("Sample logging");
        assertTrue(appender.getOutput().contains("Timestamp"));
        assertTrue(appender.getOutput().contains("RequestId = 123-456-789"));
        assertTrue(appender.getOutput().contains("UserId = abc@xyz"));
        assertTrue(appender.getOutput().contains("ServerName = payments-wls-01"));
        assertTrue(appender.getOutput().contains("ServerName = payments-wls-01"));
        assertTrue(appender.getOutput().contains("TransactionId = 111-222-333"));
        assertTrue(appender.getOutput().contains("ServerName = payments-wls-01"));
        assertTrue(appender.getOutput().contains("Useragent = Mozilla/5.0"));
        assertTrue(appender.getOutput().contains("UrlPath = /application/v1.0/accounts"));
        assertTrue(appender.getOutput().contains("ResponseCode= 200 success"));
        assertTrue(appender.getOutput().contains("remoteIp = 10.121.22.24"));
        assertTrue(appender.getOutput().contains("referrer = www.yahoo.com"));
        assertTrue(appender.getOutput().contains("Severity=INFO"));
        assertTrue(appender.getOutput().contains("Message=Sample logging"));
        assertTrue(appender.getOutput().contains("ComponentName=LogFormatConverterTest"));
        assertTrue(appender.getOutput().contains("MethodName=testLogFormat"));
    }

    @Test
    public void logReplacesPasswordWithAsterix() throws Exception {
        // Get the Logger instance
        Logger logger = LogManager.getLogger(LogFormatConverterTest.class);
        LogDataGenerator.getInstance().setRequestId("112-223-555");
        LogDataGenerator.getInstance().setTransactionId("333-444-555");
        appender.start();

        appender.addToLogger(logger.getName(), Level.INFO);

        // Log to the string appender
        logger.info("password=temp123");

        assertTrue(appender.getOutput().contains("password=***"));
        assertTrue(appender.getOutput().contains("MethodName=logReplacesPasswordWithAsterix"));
    }

    @Test
    public void logReplacesSSNWithAsterix() throws Exception {
        // Get the Logger instance
        Logger logger = LogManager.getLogger(LogFormatConverterTest.class);
        LogDataGenerator.getInstance().setRequestId("666-666-777");
        LogDataGenerator.getInstance().setTransactionId("444-222-333");
        appender.start();

        appender.addToLogger(logger.getName(), Level.INFO);

        // Log to the string appender
        logger.info("ssn=123-456-78");
        assertTrue(appender.getOutput().contains("ssn=***"));
        assertTrue(appender.getOutput().contains("MethodName=logReplacesSSNWithAsterix"));
        assertTrue(appender.getOutput().contains("LineNumber=92"));
    }

    @Test
    public void logForExceptionAndError() {
        // Get the Logger instance
        Logger logger = LogManager.getLogger(LogFormatConverterTest.class);
        LogDataGenerator.getInstance().setRequestId("123-444-333");
        LogDataGenerator.getInstance().setTransactionId("555-222-333");
        appender.addToLogger(logger.getName(), Level.ERROR);
        appender.start();
        try {
            //Throw an exception and verify the message
            throw new Exception("test exception");
        } catch (Exception e) {
            logger.error("Exception occured" + e);
        }

        assertTrue(appender.getOutput().contains("Severity=ERROR"));
        assertTrue(appender.getOutput().contains("Message=Exception occuredjava.lang.Exception: test exception"));
        assertTrue(appender.getOutput().contains("MethodName=logForExceptionAndError"));

    }


    @After
    public void tearDown() throws Exception {
        appender.removeFromLogger(LogManager.getRootLogger().getName());

    }

}