package com.youxiu326.entity.wechat;

/**
 * 微信端用户凭证对象
 */
public class AccessToken {

    private String accessToken;

    private Long expireTime;

    public AccessToken(){}

    public AccessToken(String accessToken, Integer expireTime) {
        this.accessToken = accessToken;
        this.expireTime = System.currentTimeMillis()+expireTime*1000;
    }

    public boolean isExpired(){
        return System.currentTimeMillis()>expireTime;
    }

    public String getAccessToken(){
        return this.accessToken;
    }
}
