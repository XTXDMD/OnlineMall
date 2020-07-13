package com.zhy.project.mall.service;

import com.zhy.project.mall.dao.OrderDao;
import com.zhy.project.mall.dao.OrderDaoImpl;
import com.zhy.project.mall.model.*;
import com.zhy.project.mall.model.bo.*;
import com.zhy.project.mall.model.vo.*;
import com.zhy.project.mall.utils.DruidUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    OrderDao orderDao = new OrderDaoImpl();
    @Override
    public PageOrdersVO ordersByPage(PageOrderBO orderBO) throws SQLException {
        int totalCounts = orderDao.getTotalCounts(orderBO);
        List<PageOrderInfoVO> orderInfoVOS = orderDao.ordersByPage(orderBO);
        PageOrdersVO pageOrdersVO = new PageOrdersVO(totalCounts,orderInfoVOS);
        return pageOrdersVO;
    }

    @Override
    public OrderInfoVO order(String id) throws SQLException {
        int idToGet = Integer.parseInt(id);
        OrderInfoVO infoVO = orderDao.order(idToGet);
        int goodsId = infoVO.getGoodsId();
        List<SpecVO> specs = orderDao.getSpecsByGoodsId(goodsId);
        infoVO.setSpec(specs);
        infoVO.setCurSpec(new SpecVO(infoVO.getGoodsDetailId()));
        return infoVO;
    }

    @Override
    public int getOrderStateById(String orderId) throws SQLException {
        int idToGet = Integer.parseInt(orderId);
        return orderDao.getOrderStateById(idToGet);
    }

    @Override
    public void changeOrder(ChangeOrderBO changeOrderBO) throws SQLException {
        int specId = changeOrderBO.getSpec();
        String specName = orderDao.getSpecNameById(specId);
        Orders orders = new Orders(Integer.parseInt(changeOrderBO.getId()),specName,changeOrderBO.getSpec(),changeOrderBO.getNum(),changeOrderBO.getState());
        orderDao.changeOrder(orders);
    }

    @Override
    public void deleteOrder(String id) throws SQLException {
        int idToDelete = Integer.parseInt(id);
        orderDao.deleteOrder(idToDelete);
    }

    @Override
    public List<UserOrdersVO> getOrderByState(String stateId, String token) throws SQLException {
        int idToGet = Integer.parseInt(stateId);
        List<UserOrdersVO> ordersVO = orderDao.getOrderByState(idToGet,token);
        //遍历 orderVO将goodsVO对象填充进去
        for (UserOrdersVO userOrdersVO : ordersVO) {
            int goodsId = userOrdersVO.getGoodsId();
            userOrdersVO.setGoodsVO(orderDao.getGoodById(goodsId));
            Spec spec = orderDao.getSpecById(userOrdersVO.getGoodsDetailId());
            if((spec != null) && (userOrdersVO.getGoodsVO() != null)){
                userOrdersVO.getGoodsVO().setSpec(spec.getSpecName());
                userOrdersVO.getGoodsVO().setGoodsDetailId(userOrdersVO.getGoodsDetailId());
                userOrdersVO.getGoodsVO().setUnitPrice(spec.getUnitPrice());
            }
        }
        return ordersVO;
    }

    @Override
    public int settleAccounts(List<CartBO> cartBOS) throws SQLException {
        for (CartBO cartBO : cartBOS) {
            orderDao.settleAccounts(cartBO);
            Orders orders = orderDao.getOrderById(cartBO.getId());
            Spec spec = orderDao.getSpecById(orders.getGoodsDetailId());
            if(cartBO.getGoodsNum() > spec.getStockNum()){
                return 0;
            }
            orderDao.tallyDown(spec.getId(),cartBO.getGoodsNum());
        }
        return 1;
    }

    @Override
    public void pay(String id) throws SQLException {
        int idToPay = Integer.parseInt(id);
        orderDao.pay(idToPay);
    }

    @Override
    public void confirmReceive(String id) throws SQLException {
        int idToConfirm = Integer.parseInt(id);
        orderDao.confirmReceive(idToConfirm);
    }

    @Override
    public void sendComment(UserCommentBO commentBO) throws SQLException {
        Comments comments = new Comments(commentBO.getToken(),commentBO.getOrderId(),commentBO.getGoodsId(),commentBO.getGoodsDetailId(),commentBO.getContent(),commentBO.getScore());
        int userId = orderDao.getUserIdByNickname(commentBO.getToken());
        String specName = orderDao.getSpecNameById(comments.getGoodsDetailId());
        comments.setUserId(userId);
        comments.setSpecName(specName);
        comments.setCreatetime(new Date());
        orderDao.sendComment(comments);
        int orderId = comments.getOrderId();
        //修改订单评论状态
        orderDao.hasComment(orderId);

    }

    @Override
    public int addOrder(AddOrderBO orderBO) throws SQLException {
        User user = orderDao.getUserByNickname(orderBO.getToken());
        Spec spec = orderDao.getSpecById(orderBO.getGoodsDetailId());
        if(spec.getStockNum() < orderBO.getNum()){
            return 0;
        }
        GoodsVO goods = orderDao.getGoodById(spec.getGoodsId());
        Orders orders = new Orders(user.getId() + "",orderBO.getToken(),user.getEmail(),user.getAddress(),user.getPhone(),goods.getName(),goods.getId(),spec.getSpecName(),orderBO.getGoodsDetailId(),orderBO.getNum(),orderBO.getAmount(),orderBO.getState());
        Connection connection = null;
        /**
         * 下单操作添加事务逻辑
         */
        try{
            connection = DruidUtils.getConnection(true);
            connection.setAutoCommit(false);
            orderDao.addOrder(orders);
            //若已付款则相依规格商品数量 —1
            if(orders.getStateId() == 1){
                orderDao.tallyDown(spec.getId(),orders.getNum());
            }
            connection.commit();
        }catch (SQLException e){
            e.printStackTrace();
            try{
                if(connection != null){
                    connection.rollback();
                }
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }finally {
            try{
                if(connection != null){
                    connection.close();
                }
                DruidUtils.releseConnection();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        return 1;
    }

}
