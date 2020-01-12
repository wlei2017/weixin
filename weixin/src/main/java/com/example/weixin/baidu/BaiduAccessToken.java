package com.example.weixin.baidu;

public class BaiduAccessToken {
    private String accessToken;

    private long expireTime;//到期时间

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public BaiduAccessToken(String accessToken, String expireIn) {
        super();
        this.accessToken = accessToken;
        expireTime = System.currentTimeMillis() +  Integer.parseInt(expireIn) * 1000;
    }

    public boolean isExpire(){
        return System.currentTimeMillis() > expireTime;
    }
}
