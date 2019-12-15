package com.youxiu326.controller;

import com.alibaba.fastjson.JSONObject;
import com.youxiu326.entity.btn.Button;
import com.youxiu326.entity.btn.ClickButton;
import com.youxiu326.entity.btn.ViewButton;
import com.youxiu326.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/menu")
@Controller
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/create")
    public @ResponseBody  String createMenu(){

        Button menu = new Button();

        ViewButton washCarBtn = new ViewButton();
        washCarBtn.setName("附近洗车点");
        washCarBtn.setType("view");
        washCarBtn.setUrl("http://test.youxiu326.xin/washCar");
        menu.getButton().add(washCarBtn);

        ViewButton rechargeBtn = new ViewButton();
        rechargeBtn.setName("充值");
        rechargeBtn.setType("view");
        rechargeBtn.setUrl("http://test.youxiu326.xin/recharge");
        menu.getButton().add(rechargeBtn);

        Button myBtn = new Button();
        myBtn.setName("我");

        ViewButton centerBtn = new ViewButton();
        centerBtn.setName("个人中心");
        centerBtn.setType("view");
        centerBtn.setUrl("http://test.youxiu326.xin/center");
        ViewButton moneyBtn = new ViewButton();
        moneyBtn.setName("狂赚佣金");
        moneyBtn.setType("view");
        moneyBtn.setUrl("http://test.youxiu326.xin/money");
        myBtn.getSub_button().add(centerBtn);
        myBtn.getSub_button().add(moneyBtn);
        menu.getButton().add(myBtn);
        menuService.createMenu(menu);
        return JSONObject.toJSONString(menu);
    }

}
