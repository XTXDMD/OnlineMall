package com.zhy.project.mall.model.vo;

import com.zhy.project.mall.model.enumaration.OrderState;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于后台编辑订单信息时，显示订单信息；
 */

public class OrderInfoVO {
    private Integer id;

    private Integer amount;

    private Integer num;

    private Integer goodsDetailId;

    private Integer state;

    private String goods;

    private Integer goodsId;

    private List<SpecVO> spec;

    private List<StateVO> states;

    private StateVO curState;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getGoodsDetailId() {
        return goodsDetailId;
    }

    public void setGoodsDetailId(Integer goodsDetailId) {
        this.goodsDetailId = goodsDetailId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {

        //在 dbutils调用setState方法时，完成对states和curstate的赋值

        List<StateVO> states = new ArrayList<>();
        states.add(new StateVO(OrderState.UN_PAID.getCode(),OrderState.UN_PAID.getValue()));
        states.add(new StateVO(OrderState.UN_SHIPED.getCode(),OrderState.UN_SHIPED.getValue()));
        states.add(new StateVO(OrderState.DELIVERED.getCode(),OrderState.DELIVERED.getValue()));
        states.add(new StateVO(OrderState.RECEIVER.getCode(),"已完成订单"));
        this.setStates(states);
        this.state = state;
        this.setCurState(new StateVO(state));
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public List<SpecVO> getSpec() {
        return spec;
    }

    public void setSpec(List<SpecVO> spec) {
        this.spec = spec;
    }

    public List<StateVO> getStates() {
        return states;
    }

    public void setStates(List<StateVO> states) {
        this.states = states;
    }

    public StateVO getCurState() {
        return curState;
    }

    public void setCurState(StateVO curState) {
        this.curState = curState;
    }

    public SpecVO getCurSpec() {
        return curSpec;
    }

    public void setCurSpec(SpecVO curSpec) {
        this.curSpec = curSpec;
    }

    private SpecVO curSpec;

}
