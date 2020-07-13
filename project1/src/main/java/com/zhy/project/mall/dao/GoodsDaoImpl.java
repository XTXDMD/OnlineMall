package com.zhy.project.mall.dao;

import com.zhy.project.mall.model.*;
import com.zhy.project.mall.model.bo.SpecBO;
import com.zhy.project.mall.model.vo.*;
import com.zhy.project.mall.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class GoodsDaoImpl implements GoodsDao{
    @Override
    public List<Type> getType() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        List<Type> types = queryRunner.query("SELECT * FROM type",new BeanListHandler<Type>(Type.class));
        return types;
    }

    @Override
    public List<TypeGoodsVO> getGoodsByType(String typeId) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        int idToSearch = Integer.parseInt(typeId);
        List<TypeGoodsVO> goodsVO = null;
        if(idToSearch == -1){
            goodsVO = queryRunner.query("SELECT id,img,name,price,typeId,stockNum FROM goods",new BeanListHandler<TypeGoodsVO>(TypeGoodsVO.class));
        }else {
            goodsVO = queryRunner.query("SELECT id,img,name,price,typeId,stockNum FROM goods WHERE typeId = ?",new BeanListHandler<TypeGoodsVO>(TypeGoodsVO.class),idToSearch);
        }
        return goodsVO;
    }

    @Override
    public void addGoods(Goods goods) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        queryRunner.update("INSERT INTO goods VALUES(null,?,?,?,?,?,?)",goods.getImg(),goods.getName(),goods.getPrice(),goods.getTypeId(),goods.getStockNum(),goods.getDesc());
    }

    @Override
    public int getLastInsertId() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        BigInteger lastInsertId = queryRunner.query("SELECT last_insert_id()",new ScalarHandler<>());
        return lastInsertId.intValue();
    }

    @Override
    public void addSpec(Spec spec) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        queryRunner.update("INSERT INTO spec VALUES(null,?,?,?,?)",spec.getSpecName(),spec.getStockNum(),spec.getUnitPrice(),spec.getGoodsId());
    }

    @Override
    public void updateGoods(Goods goods) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        queryRunner.update("UPDATE goods SET img = ?, name = ?, price = ?, typeId = ?, stockNum = ?, `desc` = ? WHERE id = ?",goods.getImg(),goods.getName(),goods.getPrice(),goods.getTypeId(),goods.getStockNum(),goods.getDesc(),goods.getId());
    }



    @Override
    public Goods getGoodsById(int id) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        Goods goods = queryRunner.query("SELECT * FROM goods WHERE id = ?",new BeanHandler<Goods>(Goods.class),id);
        return goods;
    }

    @Override
    public List<SpecVO> getSpecsById(int id) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        List<SpecVO> specs = queryRunner.query("SELECT * FROM spec WHERE goodsId = ?",new BeanListHandler<SpecVO>(SpecVO.class),id);
        return specs;
    }

    @Override
    public int getSpecByName(String specName) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        Spec spec = queryRunner.query("SELECT * FROM spec WHERE specName = ?",new BeanHandler<Spec>(Spec.class),specName);
        if(spec == null){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public void deleteGoods(int idToDelete) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        queryRunner.update("DELETE FROM goods WHERE id = ?",idToDelete);
    }

    @Override
    public void addType(String typeName) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        queryRunner.update("INSERT INTO type VALUES(null,?)",typeName);
    }

    @Override
    public void deleteType(int typeIdToDelete) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        queryRunner.update("DELETE FROM type WHERE id = ?",typeIdToDelete);
    }

    @Override
    public List<AdminQnaVO> noReplyMsg() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        List<AdminQnaVO> adminQnaVO = queryRunner.query("SELECT * FROM qna WHERE state = 1",new BeanListHandler<AdminQnaVO>(AdminQnaVO.class));
        return adminQnaVO;
    }

    @Override
    public List<AdminQnaVO> repliedMsg() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        List<AdminQnaVO> adminQnaVO = queryRunner.query("SELECT * FROM qna WHERE state = 0",new BeanListHandler<AdminQnaVO>(AdminQnaVO.class));
        return adminQnaVO;
    }



    @Override
    public List<UserSearchGoodsVO> searchGoods(String keyword) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        keyword = "%" + keyword + "%";
        List<UserSearchGoodsVO> goodsVOList = queryRunner.query("SELECT * FROM goods WHERE name like ? ",new BeanListHandler<UserSearchGoodsVO>(UserSearchGoodsVO.class),keyword);
        return goodsVOList;
    }

    @Override
    public void reply(Integer id, String content) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        queryRunner.update("UPDATE qna SET replyContent = ?,replytime = ? ,state = 0 WHERE id = ?",content,new Date(),id);

    }

    @Override
    public List<GoodsMsgVO> getGoodsMsg(int idToGet) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        List<GoodsMsgVO> msgVOS = queryRunner.query("SELECT goodsId as id, content, userName as asker, createtime as time,replyContent,replytime FROM qna WHERE goodsId = ?",new BeanListHandler<GoodsMsgVO>(GoodsMsgVO.class),idToGet);
        return msgVOS;
    }

    @Override
    public List<GoodsCommentVO> getGoodsComment(int idToGet) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        List<GoodsCommentVO> commentVOS = queryRunner.query("SELECT username,score,goodsDetailId as id,specName,content as comment,createtime as time,userId FROM comments WHERE goodsId = ?",new BeanListHandler<GoodsCommentVO>(GoodsCommentVO.class),idToGet);
        return commentVOS;
    }

    @Override
    public void askGoodsMsg(Qna qna) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        queryRunner.update("INSERT INTO qna(userName,content,goodsId,state,createtime) VALUES(?,?,?,?,?)",qna.getUserName(),qna.getContent(),qna.getGoodsId(),1,new Date());
    }

    @Override
    public void updateSpec(SpecBO specBO) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        queryRunner.update("UPDATE spec SET specName = ?,stockNum = ?,unitPrice = ? WHERE id = ?",specBO.getSpecName(),specBO.getStockNum(),specBO.getUnitPrice(),specBO.getId());
    }

    @Override
    public void deleteSpec(int goodsId, String specName) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        queryRunner.update("DELETE FROM spec WHERE goodsId = ? and specName = ?",goodsId,specName);
    }

}
