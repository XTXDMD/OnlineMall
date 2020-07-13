package com.zhy.project.mall.model.vo;

import java.util.List;

/**
 * 用于前台显示商品评价列表信息
 */
public class GoodsCommentListVO {
    private List<GoodsCommentVO> commentList;
    private Integer rate;

    public List<GoodsCommentVO> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<GoodsCommentVO> commentList) {
        this.commentList = commentList;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
}
