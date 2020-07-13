package com.zhy.project.mall.model.bo;

public class AdminLoginBO {
    private String email;
    private String pwd;

    public AdminLoginBO(String email, String pwd) {
        this.email = email;
        this.pwd = pwd;
    }

    public AdminLoginBO(){}

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

    @Override
    public String toString() {
        return "AdminLoginBO{" +
                "email='" + email + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
