package com.zhy.project.mall.model.vo;

public class SpecVO {
    private Integer id;
    private String specName;
    private String stockNum;
    private Double unitPrice;

    public SpecVO() {
    }

    public SpecVO(Integer id, String specName, Double unitPrice) {
        this.id = id;
        this.specName = specName;
        this.unitPrice = unitPrice;
    }

    public String getStockNum() {
        return stockNum;
    }

    public void setStockNum(String stockNum) {
        this.stockNum = stockNum;
    }

    public SpecVO(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
