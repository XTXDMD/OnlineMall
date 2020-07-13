package com.zhy.project.mall.service;

import com.zhy.project.mall.model.bo.*;
import com.zhy.project.mall.model.vo.OrderInfoVO;
import com.zhy.project.mall.model.vo.PageOrdersVO;
import com.zhy.project.mall.model.vo.UserOrdersVO;

import java.sql.SQLException;
import java.util.List;

public interface OrderService {
    PageOrdersVO ordersByPage(PageOrderBO orderBO) throws SQLException;

    OrderInfoVO order(String id) throws SQLException;

    int getOrderStateById(String orderId) throws SQLException;

    void changeOrder(ChangeOrderBO changeOrderBO) throws SQLException;

    void deleteOrder(String id) throws SQLException;

    List<UserOrdersVO> getOrderByState(String stateId, String token) throws SQLException;

    int settleAccounts(List<CartBO> cartBOS) throws SQLException;

    void pay(String id) throws SQLException;

    void confirmReceive(String id) throws SQLException;

    void sendComment(UserCommentBO commentBO) throws SQLException;

    int addOrder(AddOrderBO orderBO) throws SQLException;
}
