package com.youxiu326.controller;

import com.alibaba.fastjson.JSONObject;
import com.youxiu326.service.AccountService;
import com.youxiu326.utils.WechatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/")
@Controller
public class IndexController {

    @Autowired
    private AccountService accountService;

    @Value("${wechat.appID}")
    private String appID;

    @Value("${wechat.appsecret}")
    private String appsecret;

    /**
     * 附近洗车点
     * @param request
     * @param response
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/washCart")
    public String washCart(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        return "center";
    }

    /**
     * 充值
     * @param request
     * @param response
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/recharge")
    public String recharge(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        return "center";
    }

    /**
     * 个人中心
     * @param request
     * @param response
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/center")
    public String center(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        return "center";
    }

    /**
     * 狂赚佣金
     * @param request
     * @param response
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/money")
    public String money(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        return "money";
    }

    /**
     * 历史记录
     * @param request
     * @param response
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/history")
    public String history(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

        return "history";
    }

    /**
     * 提现
     * @param request
     * @param response
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/withdraw")
    public String withdraw(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

        return "withdraw";
    }

    @RequestMapping("/page/{url}")
    public String page(@PathVariable("url") String url){
        return url;
    }

    /**
     * 1.网页授权重定向
     */
    @RequestMapping("/web/forward")
    public String webForward(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        System.out.println("========狂赚佣金==========");
        String scene_str = "oprfLww9bi1hskkH0s5PSlL0BkYA";
        // 获取了永久二维码
        String qrCode = accountService.getQRcode(scene_str);

        String redirectUri = "http://test.youxiu326.xin/web/getOpenId";
        String state = "abc123";

        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
        url = url.replace("APPID", appID)
                .replace("REDIRECT_URI",redirectUri)
                .replace("SCOPE", "snsapi_base ") //无需授权，只查用户openId (snsapi_userinfo )
                .replace("STATE", state);

//        return "forward:/hello";
//        return "redirect:/hello";
        return "redirect:"+url;
    }

    /**
     * 2.网页授权获得用户openid
     */
    @RequestMapping("/web/getOpenId")
    public @ResponseBody Map webGetOpenId(String code){

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        url = url.replace("APPID", appID)
                .replace("SECRET",appsecret)
                .replace("CODE", code);
        String str = WechatUtil.get(url);
        Map map = JSONObject.parseObject(str, Map.class);

        // 通过网页授权获得用户微信openid
        String openid = (String) map.get("openid");
        System.out.println("通过网页授权获得了用户微信openId");
        return map;
    }


}
