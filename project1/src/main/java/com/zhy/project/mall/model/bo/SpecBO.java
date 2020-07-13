package com.zhy.project.mall.model.bo;

public class SpecBO {
    private Integer id;
    private String specName;
    private Integer stockNum;
    private Double unitPrice;
    private Integer goodsId;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public SpecBO(String specName, Integer stockNum, Double unitPrice) {
        this.specName = specName;
        this.stockNum = stockNum;
        this.unitPrice = unitPrice;
    }

    public SpecBO(Integer id, String specName, Integer stockNum, Double unitPrice) {
        this.id = id;
        this.specName = specName;
        this.stockNum = stockNum;
        this.unitPrice = unitPrice;
    }

    public String getSpecName() {
        return specName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
