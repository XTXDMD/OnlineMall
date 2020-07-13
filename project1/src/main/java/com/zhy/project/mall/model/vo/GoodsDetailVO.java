package com.zhy.project.mall.model.vo;

import java.util.List;


/**
 * 用于前端显示商品详情信息
 */
public class GoodsDetailVO {
    private String img;
    private String name;
    private String desc;
    private Integer typeId;
    private List<SpecVO> specs;
    private Integer unitPrice = 0;

    public GoodsDetailVO() {
    }

    public GoodsDetailVO(String img, String name, String desc, Integer typeId) {
        this.img = img;
        this.name = name;
        this.desc = desc;
        this.typeId = typeId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public List<SpecVO> getSpecs() {
        return specs;
    }

    public void setSpecs(List<SpecVO> specs) {
        this.specs = specs;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }
}
