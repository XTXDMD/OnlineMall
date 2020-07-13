package com.zhy.project.mall.model.bo;

/**
 * 用于接收订单分页查询参数bo;
 */


public class PageOrderBO {
    private Integer state;

    private Integer currentPage;

    private Integer pagesize;

    private String moneyLimit1;

    private String moneyLimit2;

    private String goods;

    private String address;

    private String name;

    private String id;

    public PageOrderBO() {
    }

    public PageOrderBO(Integer state, Integer currentPage, Integer pagesize, String moneyLimit1, String moneyLimit2, String goods, String address, String name, String id) {
        this.state = state;
        this.currentPage = currentPage;
        this.pagesize = pagesize;
        this.moneyLimit1 = moneyLimit1;
        this.moneyLimit2 = moneyLimit2;
        this.goods = goods;
        this.address = address;
        this.name = name;
        this.id = id;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPagesize() {
        return pagesize;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

    public String getMoneyLimit1() {
        return moneyLimit1;
    }

    public void setMoneyLimit1(String moneyLimit1) {
        this.moneyLimit1 = moneyLimit1;
    }

    public String getMoneyLimit2() {
        return moneyLimit2;
    }

    public void setMoneyLimit2(String moneyLimit2) {
        this.moneyLimit2 = moneyLimit2;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
