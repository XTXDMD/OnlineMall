package com.zhy.project.mall.model.vo;

public class StateVO {
    private Integer id;
    private String name;

    public StateVO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public StateVO(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
