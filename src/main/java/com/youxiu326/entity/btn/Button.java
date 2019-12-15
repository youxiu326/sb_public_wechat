package com.youxiu326.entity.btn;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单按钮基类
 */
public class Button {

    private String name;
    private String type;
    private List<Button> sub_button =new ArrayList<>();
    private List<Button> button =new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Button> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<Button> sub_button) {
        this.sub_button = sub_button;
    }

    public List<Button> getButton() {
        return button;
    }

    public void setButton(List<Button> button) {
        this.button = button;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
