package com.zhy.project.mall.dao;

import com.mysql.cj.util.StringUtils;
import com.zhy.project.mall.model.Comments;
import com.zhy.project.mall.model.Orders;
import com.zhy.project.mall.model.Spec;
import com.zhy.project.mall.model.User;
import com.zhy.project.mall.model.bo.CartBO;
import com.zhy.project.mall.model.bo.PageOrderBO;
import com.zhy.project.mall.model.vo.*;
import com.zhy.project.mall.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.*;

public class OrderDaoImpl implements OrderDao {
    @Override
    public List<PageOrderInfoVO> ordersByPage(PageOrderBO orderBO) throws SQLException {
        String prefix_sql = "SELECT id,userId,goodsDetailId,goods,spec,num as goodsNum,amount,stateId,nickname,name,address,phone";
        Map<String,Object> sqlResult = getDynamicSql(orderBO);
        String sql = (String) sqlResult.get("sql");
        List<Object> params = (List<Object>) sqlResult.get("params");
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        params.add(orderBO.getPagesize());
        params.add((orderBO.getCurrentPage() - 1) * orderBO.getPagesize());
        List<PageOrderInfoVO> orderInfoVOS = queryRunner.query(prefix_sql + sql + " LIMIT ? OFFSET ?",new BeanListHandler<PageOrderInfoVO>(PageOrderInfoVO.class),params.toArray());
        return orderInfoVOS;
    }

    @Override
    public int getTotalCounts(PageOrderBO orderBO) throws SQLException {
        String prefix_sql = "SELECT COUNT(id) ";
        Map<String,Object> sqlResult = getDynamicSql(orderBO);
        String sql = (String) sqlResult.get("sql");
        List<Object> params = (List<Object>) sqlResult.get("params");
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        Long totalCounts = queryRunner.query(prefix_sql + sql,new ScalarHandler<>(),params.toArray());
        return totalCounts.intValue();
    }

    @Override
    public OrderInfoVO order(int idToGet) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        OrderInfoVO infoVO = queryRunner.query("SELECT id,amount,num,goodsDetailId,stateId as state,goods,goodsId FROM orders WHERE id = ?",new BeanHandler<>(OrderInfoVO.class),idToGet);
        return infoVO;
    }

    @Override
    public List<SpecVO> getSpecsByGoodsId(int goodsId) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        List<SpecVO> specs = queryRunner.query("SELECT * FROM spec WHERE goodsId = ?",new BeanListHandler<SpecVO>(SpecVO.class),goodsId);
        return specs;
    }

    @Override
    public int getOrderStateById(int idToGet) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        BigInteger state = queryRunner.query("SELECT stateId FROM orders WHERE id = ?",new ScalarHandler<>(),idToGet);
        return state.intValue();
    }

    @Override
    public String getSpecNameById(int specId) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        Spec spec = queryRunner.query("SELECT specName FROM spec WHERE id = ?",new BeanHandler<>(Spec.class),specId);
        return spec.getSpecName();
    }

    @Override
    public void changeOrder(Orders orders) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        queryRunner.update("UPDATE orders SET spec = ?, goodsDetailId = ?, num = ?, stateId = ? WHERE id = ?",orders.getSpec(),orders.getGoodsDetailId(),orders.getNum(),orders.getStateId(),orders.getId());
    }

    @Override
    public void deleteOrder(int idToDelete) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        queryRunner.update("DELETE FROM orders WHERE id = ?",idToDelete);
    }

    @Override
    public List<UserOrdersVO> getOrderByState(int stateId, String token) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        List<UserOrdersVO> ordersVOS = null;
        if(stateId == -1){
            ordersVOS = queryRunner.query("SELECT id,goodsId,stateId as state,num as goodsNum,amount,goodsDetailId,createtime,hasComment FROM orders WHERE nickname = ?",new BeanListHandler<UserOrdersVO>(UserOrdersVO.class),token);
        }else{
            ordersVOS = queryRunner.query("SELECT id,goodsId,stateId as state,num as goodsNum,amount,goodsDetailId,createtime,hasComment FROM orders WHERE stateId = ? and nickname = ?",new BeanListHandler<UserOrdersVO>(UserOrdersVO.class),stateId,token);
        }
        return ordersVOS;
    }

    @Override
    public GoodsVO getGoodById(int goodsId) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        GoodsVO goodsVO = queryRunner.query("SELECT id,img,name FROM goods WHERE id = ?",new BeanHandler<GoodsVO>(GoodsVO.class),goodsId);
        return goodsVO;
    }

    @Override
    public Spec getSpecById(Integer goodsDetailId) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        Spec spec = queryRunner.query("SELECT * FROM spec WHERE id = ?",new BeanHandler<>(Spec.class),goodsDetailId);
        return spec;
    }

    @Override
    public void settleAccounts(CartBO cartBO) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        queryRunner.update("UPDATE orders SET num = ?,amount = ?,stateId = 1 WHERE id = ?",cartBO.getGoodsNum(),cartBO.getAmount(),cartBO.getId());
    }

    @Override
    public void pay(int idToPay) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        queryRunner.update("UPDATE orders SET stateId = 2 WHERE id = ?",idToPay);
    }

    @Override
    public void confirmReceive(int idToConfirm) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        queryRunner.update("UPDATE orders SET stateId = 3 WHERE id = ?",idToConfirm);
    }

    /**
     * 将评论写入数据库
     * @param comments
     */
    @Override
    public void sendComment(Comments comments) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        queryRunner.update("INSERT INTO comments VALUES(null,?,?,?,?,?,?,?,?,?)",comments.getUserId()
        ,comments.getUsername(),comments.getOrderId(),comments.getGoodsId(),comments.getGoodsDetailId(),comments.getSpecName(),comments.getContent(),comments.getScore(),comments.getCreatetime());
    }

    @Override
    public void hasComment(int orderId) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        queryRunner.update("UPDATE orders SET hasComment = 1 WHERE id = ?",orderId);
    }

    @Override
    public int getUserIdByNickname(String token) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        Integer userId = queryRunner.query("SELECT id FROM user WHERE nickname = ?",new ScalarHandler<>(),token);
        return userId.intValue();
    }

    @Override
    public User getUserByNickname(String token) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        User user = queryRunner.query("SELECT * FROM user WHERE nickname = ?",new BeanHandler<User>(User.class),token);
        return user;
    }

    @Override
    public void tallyDown(Integer id, Integer num) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        queryRunner.update(DruidUtils.getConnection(true),"UPDATE spec SET stockNum = stockNum - ? WHERE id = ?",num,id);
    }

    @Override
    public void addOrder(Orders orders) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        queryRunner.update(DruidUtils.getConnection(true),"INSERT INTO orders VALUES(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ",orders.getUserId(),orders.getNickname(),
                orders.getName(),orders.getAddress(),orders.getPhone(),orders.getGoods(),orders.getGoodsId(),orders.getSpec(),
                orders.getGoodsDetailId(),orders.getNum(),orders.getAmount(),orders.getStateId(),0,new Date(),new Date());
    }

    @Override
    public Orders getOrderById(Integer id) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        Orders orders = queryRunner.query("SELECT * FROM orders WHERE id = ?",new BeanHandler<Orders>(Orders.class),id);
        return orders;
    }


    /**
     * 获取动态sql
     * @param orderBO
     * @return
     */
    private Map<String, Object> getDynamicSql(PageOrderBO orderBO) {
        String base = " FROM orders WHERE 1 = 1 ";
        List<Object>  params = new ArrayList<>();
        if(orderBO.getState() != -1){
            base = base + " and stateId = ?";
            params.add(orderBO.getState());
        }
        if(!StringUtils.isNullOrEmpty(orderBO.getMoneyLimit1())){
            base = base + " and amount <= ?";
            params.add(Double.parseDouble(orderBO.getMoneyLimit1()));
        }
        if(!StringUtils.isNullOrEmpty(orderBO.getMoneyLimit2())){
            base = base + " and amount >= ?";
            params.add(Double.parseDouble(orderBO.getMoneyLimit2()));
        }
        if(!StringUtils.isNullOrEmpty(orderBO.getGoods())){
            base = base + " and goods like ?";
            params.add("%" + orderBO.getGoods() + "%");
        }
        if(!StringUtils.isNullOrEmpty(orderBO.getAddress())){
            base = base + " and address like ?";
            params.add("%" + orderBO.getAddress() + "%");
        }
        if(!StringUtils.isNullOrEmpty(orderBO.getName())){
            base = base + " and name like ?";
            params.add("%" + orderBO.getName() + "%");
        }
        if(!StringUtils.isNullOrEmpty(orderBO.getId())){
            base = base + " and id = ?";
            params.add(Integer.parseInt(orderBO.getId()));
        }
        Map<String,Object> sqlResult = new HashMap<>();
        sqlResult.put("sql",base);
        sqlResult.put("params",params);
        return sqlResult;
    }
}
