package com.zhy.project.mall.model.bo;

public class AdminSearchBO {
    private String email;
    private String nickname;

    public AdminSearchBO(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }

    public AdminSearchBO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
