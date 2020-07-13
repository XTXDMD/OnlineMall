package com.zhy.project.mall.model.vo;

public class AdminInfoVO {
    private String email;
    private String pwd;
    private String nickname;

    public AdminInfoVO(String email, String pwd, String nickname) {
        this.email = email;
        this.pwd = pwd;
        this.nickname = nickname;
    }

    public AdminInfoVO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
