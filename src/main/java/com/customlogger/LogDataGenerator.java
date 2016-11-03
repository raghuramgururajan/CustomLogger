package com.customlogger;

import java.util.UUID;

/**
 * Created by raghuram gururajan on 10/29/16.
 */
public class LogDataGenerator {

    private static LogDataGenerator requestLogDataGenerator = new LogDataGenerator();

    private LogDataGenerator() {

    }
    public static LogDataGenerator getInstance() {
        return requestLogDataGenerator;
    }
    private String requestId = "";
    private String userAgent = "";
    private String remoteIpAddr = "";
    private String applicationVer= "";
    private String responseCode = "";
    private String urlPath = "";
    private String referrer = "";

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    private String transactionId = "";
    private String userId = "";
    private String serverName  = "";

    public String getRemoteIpAddr() {
        return remoteIpAddr;
    }

    public void setRemoteIpAddr(String remoteIpAddr) {
        this.remoteIpAddr = remoteIpAddr;
    }

    public String getApplicationVer() {
        return applicationVer;
    }

    public void setApplicationVer(String applicationVer) {
        this.applicationVer = applicationVer;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public  String getRequestId() {
        if (requestId == null || requestId.length() == 0) {
            return UUID.randomUUID().toString();
        }
       return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }


}
