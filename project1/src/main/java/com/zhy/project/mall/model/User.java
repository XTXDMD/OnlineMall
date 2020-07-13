package com.zhy.project.mall.model;

public class User {
    private Integer id;
    private String email;
    private String nickname;
    private String pwd;
    private String recipient;
    private String address;
    private String phone;

    public User(Integer id, String email, String nickname, String pwd, String recipient, String address, String phone) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.pwd = pwd;
        this.recipient = recipient;
        this.address = address;
        this.phone = phone;
    }

    public User(Integer id, String nickname, String recipient, String address, String phone) {
        this.id = id;
        this.nickname = nickname;
        this.recipient = recipient;
        this.address = address;
        this.phone = phone;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
