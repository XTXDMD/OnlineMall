package com.zhy.project.mall.model.vo;


import java.util.Date;

/**
 * 用于显示前台用户订单信息
 */
public class UserOrdersVO {
    private Integer id;
    private Integer goodsId;
    private Integer state;
    private Integer goodsNum;
    private Integer amount;
    private Integer goodsDetailId;
    private Date createtime;
    private boolean hasComment;
    private GoodsVO goods;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getGoodsDetailId() {
        return goodsDetailId;
    }

    public void setGoodsDetailId(Integer goodsDetailId) {
        this.goodsDetailId = goodsDetailId;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public boolean isHasComment() {
        return hasComment;
    }

    public void setHasComment(boolean hasComment) {
        this.hasComment = hasComment;
    }

    public GoodsVO getGoodsVO() {
        return goods;
    }

    public void setGoodsVO(GoodsVO goodsVO) {
        this.goods = goodsVO;
    }
}
