package com.zhy.project.mall.model.vo;

/**
 * 后台订单分页查询，显示具体订单用户信息
 */

public class PageOrderInfoUserVO {
    private String nickname;
    private String name;
    private String address;
    private String phone;

    public PageOrderInfoUserVO() {
    }

    public PageOrderInfoUserVO(String nickname, String name, String address, String phone) {
        this.nickname = nickname;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
