package com.youxiu326.controller;

import com.youxiu326.utils.WechatUtil;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@RequestMapping("/")
@Controller
public class CheckController {

    @Value("${wechat.token}")
    public String token;

    /**
     * 验证服务器地址的有效性
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/check")
    public void check(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        PrintWriter out = response.getWriter();
        if (WechatUtil.checkSignature(signature, timestamp, nonce,token)){
            out.print(echostr);
            out.flush();
            out.close();
        }
    }

    @PostMapping("check")
    public void message(HttpServletRequest request,HttpServletResponse response) throws IOException, DocumentException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Map<String, String> map = WechatUtil.xmlToMap(request);
        //发送方帐号 (主体不一样 需要转换)
        String fromUserName = map.get("FromUserName");//FromUserName
        // 开发者微信号
        String toUserName = map.get("ToUserName");//ToUserName
        String msgType = map.get("MsgType");
        String event = map.get("Event");
        String content = map.get("Content");

        // 用户已经关注后再扫描的二维码
        if ("event".equals(msgType) && "SCAN".equals(event)) {
            String eventKey = map.get("EventKey");
            System.out.println("已经关注->扫描用户:"+ fromUserName+"  ,业务员:"+ eventKey);
            // 取得了业务员openId
            // 此时将关注者用户fromUserName OpenId 与 业务员OpenId关联

         // 用户通过扫描二维码关注后
        }else if("event".equals(msgType) && "subscribe".equals(event)){
            String eventKey = map.get("EventKey");
            System.out.println("未关注->扫描用户:"+ fromUserName+"  ,业务员:"+ eventKey);
        }else {
            System.out.println("====其他类型=====");
        }

    }
}
