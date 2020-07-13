package com.zhy.project.mall.model.bo;

import java.util.List;

public class GoodsBO {
    private Integer id;
    private Integer typeId;
    private String name;
    private String img;
    private String desc;
    private List<SpecBO> specList;

    public GoodsBO(Integer typeId, String name, String img, String desc, List<SpecBO> specList) {
        this.typeId = typeId;
        this.name = name;
        this.img = img;
        this.desc = desc;
        this.specList = specList;
    }

    public GoodsBO(Integer id, Integer typeId, String name, String img, String desc, List<SpecBO> specList) {
        this.id = id;
        this.typeId = typeId;
        this.name = name;
        this.img = img;
        this.desc = desc;
        this.specList = specList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GoodsBO() {
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<SpecBO> getSpecList() {
        return specList;
    }

    public void setSpecList(List<SpecBO> specList) {
        this.specList = specList;
    }
}
