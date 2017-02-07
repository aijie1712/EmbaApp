package com.emba.embaapp.model;

/**
 * Created by 41369 on 2017/2/7.
 */

public class EmbaLoginModel {
    /**
     * account : aaa
     * message : 登录验证成功！
     * password : 123456
     * remAccount : true
     * remPassword : true
     * sessionId : 712AA8D4506B42F93EE052E5EB3BC0CA
     * status : success
     * url : serviceNeeds
     */

    public static final String SUCCESS = "success";

    private String account;
    private String message;
    private String password;
    private boolean remAccount;
    private boolean remPassword;
    private String sessionId;
    private String status;
    private String url;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRemAccount() {
        return remAccount;
    }

    public void setRemAccount(boolean remAccount) {
        this.remAccount = remAccount;
    }

    public boolean isRemPassword() {
        return remPassword;
    }

    public void setRemPassword(boolean remPassword) {
        this.remPassword = remPassword;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
