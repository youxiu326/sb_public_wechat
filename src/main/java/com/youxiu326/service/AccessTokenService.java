package com.youxiu326.service;

import com.alibaba.fastjson.JSONObject;
import com.youxiu326.entity.wechat.AccessToken;
import com.youxiu326.utils.WechatUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class AccessTokenService {


    @Value("${wechat.appID}")
    private String appID;

    @Value("${wechat.appsecret}")
    private String appsecret;

    // 用于存储token
    private static AccessToken at;

    /**
     * 获取accessToken的地址
     */
    private static final String GET_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    /**
     * 向处暴露的获取token的方法
     */
    public String getAccessToken() {
        // return "28_7Y7Dp12i4pidTHjXZxLJJDSmPPte4PN9J-tXuYeYmd87KOFnszq85xIFk-ovNNg_0xZziPLcWHcMpwFp-1ihj5MpZ3_R_6dFsMntJyB0vPmz49s9oS8C1MQY4ZXy3SX5eRJgqb54PODC1Jx4QQFiAGAAAP";
        if(at==null||at.isExpired()) {
            getToken();
        }
        return at.getAccessToken();
    }

    private void getToken(){
        String url = GET_TOKEN_URL.replace("APPID",appID).replace("APPSECRET",appsecret);
        String tokenStr = WechatUtil.get(url);
        System.out.println(tokenStr);
        Map<String,Object> map =JSONObject.parseObject(tokenStr,Map.class);
        String access_token = (String) map.get("access_token");
        Integer expires_in = (Integer) map.get("expires_in");
        at = new AccessToken(access_token,expires_in);
    }



}
