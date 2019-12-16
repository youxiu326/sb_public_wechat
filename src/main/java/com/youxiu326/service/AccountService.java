package com.youxiu326.service;

import com.alibaba.fastjson.JSONObject;
import com.youxiu326.utils.WechatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AccountService {

    @Autowired
    private AccessTokenService accessTokenService;

    /**
     * 获得永久二维码
     * @param scene_str
     * @return
     */
    public String getQRcode(String scene_str){

        String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";
        url = url.replace("TOKEN",accessTokenService.getAccessToken());

        Map data = new HashMap();
        data.put("action_name","QR_LIMIT_STR_SCENE");
        Map scene = new HashMap();
        Map sceneObj = new HashMap();
        sceneObj.put("scene_str",scene_str);
        scene.put("scene",sceneObj);
        data.put("action_info",scene);
        String result = WechatUtil.post(url, JSONObject.toJSONString(data));
        Map<String,Object> map =JSONObject.parseObject(result,Map.class);
        // 获得用于创建永久二维码的ticket
        String ticket = (String) map.get("ticket");
        // 根据该地址自行生成需要的二维码图片
        String qrcodeUrl = (String) map.get("url");
        System.out.println(qrcodeUrl);
        return qrcodeUrl;
    }


    /**
     * 获取用户的基本信息
     * @param openid 用户openID
     * @return
     */
    public Map<String,Object> getUserInfo(String openid) {
        String url="https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        url = url.replace("ACCESS_TOKEN", accessTokenService.getAccessToken()).replace("OPENID", openid);
        String result = WechatUtil.get(url);
        Map<String,Object> map = JSONObject.parseObject(result,Map.class);
        return map;
    }


}
