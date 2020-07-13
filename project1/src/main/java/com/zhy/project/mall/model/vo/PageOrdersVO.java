package com.zhy.project.mall.model.vo;


import java.util.List;

/**
 * 台管理系统订单分页查询信息
 */
public class PageOrdersVO {
    private Integer total;
    private List<PageOrderInfoVO> orders;

    public PageOrdersVO() {
    }

    public PageOrdersVO(Integer total, List<PageOrderInfoVO> orders) {
        this.total = total;
        this.orders = orders;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<PageOrderInfoVO> getOrders() {
        return orders;
    }

    public void setOrders(List<PageOrderInfoVO> orders) {
        this.orders = orders;
    }
}
