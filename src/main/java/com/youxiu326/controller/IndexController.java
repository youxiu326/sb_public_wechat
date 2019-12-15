package com.youxiu326.controller;

import com.youxiu326.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    /**
     * 附近洗车点
     * @param request
     * @param response
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/washCart")
    public void washCart(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
    }

    /**
     * 充值
     * @param request
     * @param response
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/recharge")
    public void recharge(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
    }

    /**
     * 个人中心
     * @param request
     * @param response
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/center")
    public void center(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
    }

    /**
     * 狂赚佣金
     * @param request
     * @param response
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/money")
    public void money(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        System.out.println("========狂赚佣金==========");
        String scene_str = "oprfLww9bi1hskkH0s5PSlL0BkYA";
        accountService.getQRcode(scene_str);
    }


}
