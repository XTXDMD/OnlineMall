package com.zhy.project.mall.dao;

import com.zhy.project.mall.model.Comments;
import com.zhy.project.mall.model.Orders;
import com.zhy.project.mall.model.Spec;
import com.zhy.project.mall.model.User;
import com.zhy.project.mall.model.bo.CartBO;
import com.zhy.project.mall.model.bo.PageOrderBO;
import com.zhy.project.mall.model.vo.*;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao {
    List<PageOrderInfoVO> ordersByPage(PageOrderBO orderBO) throws SQLException;

    int getTotalCounts(PageOrderBO orderBO) throws SQLException;

    OrderInfoVO order(int idToGet) throws SQLException;

    List<SpecVO> getSpecsByGoodsId(int goodsId) throws SQLException;

    int getOrderStateById(int idToGet) throws SQLException;

    String getSpecNameById(int specId) throws SQLException;

    void changeOrder(Orders orders) throws SQLException;

    void deleteOrder(int idToDelete) throws SQLException;

    List<UserOrdersVO> getOrderByState(int stateId, String token) throws SQLException;

    GoodsVO getGoodById(int goodsId) throws SQLException;

    Spec getSpecById(Integer goodsDetailId) throws SQLException;

    void settleAccounts(CartBO cartBO) throws SQLException;

    void pay(int idToPay) throws SQLException;

    void confirmReceive(int idToConfirm) throws SQLException;

    void sendComment(Comments comments) throws SQLException;

    void hasComment(int orderId) throws SQLException;

    int getUserIdByNickname(String token) throws SQLException;

    User getUserByNickname(String token) throws SQLException;

    void tallyDown(Integer id, Integer num) throws SQLException;

    void addOrder(Orders orders) throws SQLException;

    Orders getOrderById(Integer id) throws SQLException;
}
