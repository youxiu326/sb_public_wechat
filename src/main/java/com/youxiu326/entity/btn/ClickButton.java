package com.youxiu326.entity.btn;

/**
 * click类型的按钮
 */
public class ClickButton extends Button{
    // click类型按钮的唯一标识符
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
