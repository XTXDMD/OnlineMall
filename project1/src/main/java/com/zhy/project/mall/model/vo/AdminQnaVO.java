package com.zhy.project.mall.model.vo;

import java.util.Date;

/**
 * 用于后台显示问答
 */

public class AdminQnaVO {
    private Integer id;
    private Integer userId;
    private Integer goodsId;
    private String content;
    private String replyContent;
    private Integer state;
    private Date createtime;
    private GoodsVO goods = new GoodsVO();
    private UserVO user = new UserVO();

    public void setUserName(String userName){
        user.setName(userName);
    }

    public void setGoodsName(String goodsName){
        goods.setName(goodsName);
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

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public GoodsVO getGoods() {
        return goods;
    }

    public void setGoods(GoodsVO goods) {
        this.goods = goods;
    }

    public UserVO getUser() {
        return user;
    }

    public void setUser(UserVO user) {
        this.user = user;
    }
}
