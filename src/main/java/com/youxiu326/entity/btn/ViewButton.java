package com.youxiu326.entity.btn;

/**
 * view类型的按钮
 */
public class ViewButton extends Button {

    // view类型按钮的点击跳转链接
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
