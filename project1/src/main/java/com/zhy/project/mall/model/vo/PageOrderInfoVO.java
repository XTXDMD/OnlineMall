package com.zhy.project.mall.model.vo;


import com.zhy.project.mall.model.enumaration.OrderState;

/**
 * 后台订单分页查询，显示具体订单信息
 */
public class PageOrderInfoVO {
    private Integer id;
    private Integer userId;
    private Integer goodsDetailId;
    private String goods;
    private String spec;
    private Integer goodsNum;
    private Double amount;
    private Integer stateId;
    private String state;
    private PageOrderInfoUserVO user = new PageOrderInfoUserVO();

    public PageOrderInfoVO() {
    }

    public PageOrderInfoVO(Integer id, Integer userId, Integer goodsDetailId, String goods, String spec, Integer goodsNum, Double amount, Integer stateId, String state, PageOrderInfoUserVO user) {
        this.id = id;
        this.userId = userId;
        this.goodsDetailId = goodsDetailId;
        this.goods = goods;
        this.spec = spec;
        this.goodsNum = goodsNum;
        this.amount = amount;
        this.stateId = stateId;
        this.state = state;
        this.user = user;
    }
    //增加四个set方法供 dbutils框架调用 完成对user对象的赋值
    public void setNickname(String nickname) {
        user.setNickname(nickname);
    }

    public void setName(String name) {
        user.setName(name);
    }

    public void setAddress(String address){
        user.setAddress(address);
    }

    public void setPhone(String phone) {
        user.setPhone(phone);
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

    public Integer getGoodsDetailId() {
        return goodsDetailId;
    }

    public void setGoodsDetailId(Integer goodsDetailId) {
        this.goodsDetailId = goodsDetailId;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getStateId() {
        return stateId;
    }

    /**
     * dbutils调用set函数给stateId赋值的同时，完成对state的赋值;
     *
     * @param stateId
     */
    public void setStateId(Integer stateId) {
        if (stateId.equals(OrderState.UN_PAID.getCode())) {
            this.setState(OrderState.UN_PAID.getValue());
        }
        if (stateId.equals(OrderState.UN_SHIPED.getCode())) {
            this.setState(OrderState.UN_SHIPED.getValue());
        }
        if (stateId.equals(OrderState.DELIVERED.getCode())) {
            this.setState(OrderState.DELIVERED.getValue());
        }
        if (stateId.equals(OrderState.RECEIVER.getCode())) {
            this.setState(OrderState.RECEIVER.getValue());
        }
        this.stateId = stateId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public PageOrderInfoUserVO getUser() {
        return user;
    }

    public void setUser(PageOrderInfoUserVO user) {
        this.user = user;
    }
}
