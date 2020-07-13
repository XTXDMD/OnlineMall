package com.zhy.project.mall.model.vo;

import com.zhy.project.mall.model.Goods;
import com.zhy.project.mall.model.Spec;
import com.zhy.project.mall.model.bo.GoodsBO;
import com.zhy.project.mall.model.bo.SpecBO;

import java.util.List;

public class GoodsInfoVO {
    private Goods goods;
    private List<SpecVO> specs;
    private Integer unitPrice = 0;

    public GoodsInfoVO(List<SpecVO> specs, Goods goods) {
        this.specs = specs;
        this.goods = goods;
    }

    public GoodsInfoVO() {
    }

    public List<SpecVO> getSpecs() {
        return specs;
    }

    public void setSpecs(List<SpecVO> specs) {
        this.specs = specs;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
}
