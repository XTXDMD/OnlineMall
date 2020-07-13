package com.zhy.project.mall.dao;

import com.zhy.project.mall.model.*;
import com.zhy.project.mall.model.bo.SpecBO;
import com.zhy.project.mall.model.vo.*;

import java.sql.SQLException;
import java.util.List;

public interface GoodsDao {
    List<Type> getType() throws SQLException;

    List<TypeGoodsVO> getGoodsByType(String typeId) throws SQLException;

    void addGoods(Goods goods) throws SQLException;

    int getLastInsertId() throws SQLException;

    void addSpec(Spec spec) throws SQLException;

    void updateGoods(Goods goods) throws SQLException;

    Goods getGoodsById(int id) throws SQLException;

    List<SpecVO> getSpecsById(int id) throws SQLException;

    void deleteSpec(int goodsId, String specName) throws SQLException;

    int getSpecByName(String specName) throws SQLException;

    void deleteGoods(int idToDelete) throws SQLException;

    void addType(String typeName) throws SQLException;

    void deleteType(int typeIdToDelete) throws SQLException;

    List<AdminQnaVO> noReplyMsg() throws SQLException;

    List<AdminQnaVO> repliedMsg() throws SQLException;

    List<UserSearchGoodsVO> searchGoods(String keyword) throws SQLException;

    void reply(Integer id, String content) throws SQLException;

    List<GoodsMsgVO> getGoodsMsg(int idToGet) throws SQLException;

    List<GoodsCommentVO> getGoodsComment(int idToGet) throws SQLException;

    void askGoodsMsg(Qna qna) throws SQLException;

    void updateSpec(SpecBO specBO) throws SQLException;
}
