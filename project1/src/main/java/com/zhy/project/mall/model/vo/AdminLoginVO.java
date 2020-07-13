package com.zhy.project.mall.model.vo;

public class AdminLoginVO {
    private String token;
    private String name;

    public AdminLoginVO(String token, String name) {
        this.token = token;
        this.name = name;
    }

    public AdminLoginVO() {}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
