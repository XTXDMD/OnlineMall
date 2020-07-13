package com.zhy.project.mall.model.bo;


/**
 * 用于显示后台修改订单信息的请求参数
 */
public class ChangeOrderBO {
    private String id;

    private Integer state;

    private Integer spec;

    private Integer num;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getSpec() {
        return spec;
    }

    public void setSpec(Integer spec) {
        this.spec = spec;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
