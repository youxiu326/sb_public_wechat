package com.youxiu326.service;

import com.alibaba.fastjson.JSONObject;
import com.youxiu326.entity.btn.Button;
import com.youxiu326.utils.WechatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MenuService {


    private static final String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    @Autowired
    private AccessTokenService accessTokenService;

    /**
     * 创建菜单
     * @param menu
     */
    public void createMenu(Button menu){

        String newUrl = url.replace("ACCESS_TOKEN",accessTokenService.getAccessToken());
        System.out.println(JSONObject.toJSONString(menu));
        String result = WechatUtil.post(newUrl, JSONObject.toJSONString(menu));
        System.out.println("创建菜单返回结果："+result);
    }

}
