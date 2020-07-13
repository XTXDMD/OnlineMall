package com.zhy.project.mall.model;

import java.util.Date;

public class Comments {
    private Integer id;
    private Integer userId;
    private String username;
    private Integer orderId;
    private Integer goodsId;
    private Integer goodsDetailId;
    private String specName;
    private String content;
    private Integer score;
    private Date createtime;

    public Comments(String username, String content,Integer goodsId) {
        this.username = username;
        this.goodsId = goodsId;
        this.content = content;
    }

    public Comments(String username, Integer orderId, Integer goodsId, Integer goodsDetailId, String content, Integer score) {
        this.username = username;
        this.orderId = orderId;
        this.goodsId = goodsId;
        this.goodsDetailId = goodsDetailId;
        this.content = content;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getGoodsDetailId() {
        return goodsDetailId;
    }

    public void setGoodsDetailId(Integer goodsDetailId) {
        this.goodsDetailId = goodsDetailId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
