package com.zhy.project.mall.service;

import com.zhy.project.mall.model.Type;
import com.zhy.project.mall.model.bo.*;
import com.zhy.project.mall.model.vo.*;

import java.sql.SQLException;
import java.util.List;

public interface GoodsService {

    List<Type> getType() throws SQLException;

    List<TypeGoodsVO> getGoodsByType(String typeId) throws SQLException;

    void addGoods(GoodsBO goodsBO) throws SQLException;

    void updateGoods(GoodsBO goodsBO) throws SQLException;

    GoodsInfoVO getGoodsInfo(String id) throws SQLException;

    void deleteSpec(SpecBO specBO) throws SQLException;

    int addSpec(SpecBO specBO) throws SQLException;

    void deleteGoods(String id) throws SQLException;

    void addType(TypeBO typeBO) throws SQLException;

    void deleteType(String typeId) throws SQLException;

    List<AdminQnaVO> noReplyMsg() throws SQLException;

    List<AdminQnaVO> repliedMsg() throws SQLException;

    void reply(ReplyBO replyBO) throws SQLException;

    List<UserSearchGoodsVO> searchGoods(String keyword) throws SQLException;

    GoodsDetailVO getGoodsDetailInfo(String id) throws SQLException;

    List<GoodsMsgVO> getGoodsMsg(String id) throws SQLException;

    GoodsCommentListVO getGoodsComment(String id) throws SQLException;

    void askGoodsMsg(AskGoodMsgBO goodMsgBO) throws SQLException;
}
