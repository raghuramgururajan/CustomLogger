# CustomLogger
Custom Logger for production logs
Custom Logger uses the format below for Logging production files
Timestamp = %d{yyyy-MM-dd HH:mm:ss.SSS z} | RequestId = %reqLogid | UserId = %userId | TransactionId = %transactionId | ServerName = %serverName | Useragent = %useragent | UrlPath = %urlPath | ResponseCode= %responseCode | remoteIp = %remoteIpAddr | referrer = %referrer | Severity=%-5p | ComponentName=%c{1} | MethodName=%M | LineNumber=%L | Message=%m%xEx%n"

The different attributes used in here are
a)Timestamp =Unique timestamp for every log output that contains precision upto microsecond and timezone and this help us in debugging and identifying issues
b)RequestId =Every Request made to api or method will output a unique request Id and this identifier can be something like UUID and this will help us in identifying the request and avoiding any duplicates
c)UserId =The current userId for whom the processing is done and this will be a critical attribute for debugging to identify user flows in a page
d)Transaction Id = Each and every financial transaction made on a Intuit applications will all have a unique transaction id to identify the record and its state
e)ServerName = The server name where the current request is being processed if its payment server it will be something like payments-serv-01
f)UserAgent =The user agent who had made the request like Mozilla/5.0 or Chrome/41.0.2226.0 ,Safari/537.36 etc
g)UrlPath = The unique url path to identify the call if its a checkouts call something like /api/v1/checkout etc 
h)ResponseCode= The response code in case of web applications will be http status code and in the case of scripts will be corresponding custom error codes
i)RemoteIp = The remote Ip address of the client who made the request
h)Referrer = The referrer url that points out if we are redirected from external third party apis to our website
j)Severity =There are different levels of severity that are used in logging the message .The core levels are            
Level	Description
ALL	All levels including custom levels.
DEBUG	Designates fine-grained informational events that are most useful to debug an application.
ERROR	Designates error events that might still allow the application to continue running.
FATAL	Designates very severe error events that will presumably lead the application to abort.
INFO	Designates informational messages that highlight the progress of the application at coarse-grained level.
OFF	The highest possible rank and is intended to turn off logging.
TRACE	Designates finer-grained informational events than the DEBUG.
WARN	Designates potentially harmful situations.

k)ComponentName = The main component for which we are logging information like payments components,checkout component etc
l)MethodName =The method name represents the method for which the logging is being done and this can also represent the script name in the case of shellscripts
m)Linenumber = The line number where a particular message is being logged in the code .This can also represent the place where error or exceptions occurs in case of error messages
n)Message = The message represents the core message that we are logging and it also contains exception stack traces in case of error

Sample logged message looks as below

Timestamp = 2016-11-03 11:57:34.429 PDT | RequestId = 123-456-789 | UserId = abc1@xyz | TransactionId = 111-222-333 | ServerName = payments-wls-01 | Useragent = Mozilla/5.0 | UrlPath = /application/v1.0/accounts | ResponseCode= 200 success | remoteIp = 10.121.22.24 | referrer = www.yahoo.com | Severity=INFO  | ComponentName=LogFormatConverterTest | MethodName=testLogFormat | LineNumber=45 | Message=Sample logging
Timestamp = 2016-11-03 11:57:34.439 PDT | RequestId = 112-223-555 | UserId = abc2@xyz | TransactionId = 333-444-555 | ServerName = payments-wls-01 | Useragent = Mozilla/5.0 | UrlPath = /application/v1.0/accounts | ResponseCode= 200 success | remoteIp = 10.121.22.24 | referrer = www.yahoo.com | Severity=INFO  | ComponentName=LogFormatConverterTest | MethodName=logReplacesPasswordWithAsterix | LineNumber=75 | Message=password=***
Timestamp = 2016-11-03 11:57:34.443 PDT | RequestId = 666-666-777 | UserId = abc4@xyz | TransactionId = 444-222-333 | ServerName = payments-wls-01 | Useragent = Mozilla/5.0 | UrlPath = /application/v1.0/accounts | ResponseCode= 200 success | remoteIp = 10.121.22.24 | referrer = www.yahoo.com | Severity=INFO  | ComponentName=LogFormatConverterTest | MethodName=logReplacesSSNWithAsterix | LineNumber=92 | Message=ssn=***
Timestamp = 2016-11-03 11:57:34.446 PDT | RequestId = 123-444-333 | UserId = abc5@xyz | TransactionId = 555-222-333 | ServerName = payments-wls-01 | Useragent = Mozilla/5.0 | UrlPath = /application/v1.0/accounts | ResponseCode= 200 success | remoteIp = 10.121.22.24 | referrer = www.yahoo.com | Severity=ERROR | ComponentName=LogFormatConverterTest | MethodName=logForExceptionAndError | LineNumber=110 | Message=Exception occuredjava.lang.Exception: test exception

The main classes are all under the package com.customlogger .The project is a maven project and the core format for logging is located under resources log4j2.xml

The code uses log4j for logging and we define our custom pattern for emitting logs in the log4j2.xml

The main verification tests are LogFormatConverterTest and they can be run under the root folder of the project using the command
 mvn clean -Dtest=com.customlogger.LogFormatConverterTest test

