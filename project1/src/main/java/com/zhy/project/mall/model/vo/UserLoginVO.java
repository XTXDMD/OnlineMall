package com.zhy.project.mall.model.vo;

public class UserLoginVO {
    private Integer code;
    private String token;
    private String name;

    public UserLoginVO() {
    }

    public UserLoginVO(Integer code, String token, String name) {
        this.code = code;
        this.token = token;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

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
