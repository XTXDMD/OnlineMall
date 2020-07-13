package com.zhy.project.mall.service;

import com.zhy.project.mall.dao.GoodsDao;
import com.zhy.project.mall.dao.GoodsDaoImpl;
import com.zhy.project.mall.model.*;
import com.zhy.project.mall.model.bo.*;
import com.zhy.project.mall.model.vo.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoodsServiceImpl implements GoodsService{
    GoodsDao goodsDao = new GoodsDaoImpl();
    @Override
    public List<Type> getType() throws SQLException {
        return goodsDao.getType();
    }

    @Override
    public List<TypeGoodsVO> getGoodsByType(String typeId) throws SQLException {
        return goodsDao.getGoodsByType(typeId);
    }

    @Override
    public void addGoods(GoodsBO goodsBO) throws SQLException {
        List<SpecBO> specList = goodsBO.getSpecList();
        Map<String,Object> params = getPriceAndStock(specList);
        double price =(double) params.get("price");
        int stockNum = (int) params.get("stockNum");
        Goods goods = new Goods(null,goodsBO.getImg(),goodsBO.getName(),price,goodsBO.getTypeId(),stockNum,goodsBO.getDesc());
        goodsDao.addGoods(goods);
        int lastInsertId = goodsDao.getLastInsertId();
        for (SpecBO specBO : specList) {
            goodsDao.addSpec(new Spec(null,specBO.getSpecName(),specBO.getStockNum(),specBO.getUnitPrice(),lastInsertId));
        }
    }

    @Override
    public void updateGoods(GoodsBO goodsBO) throws SQLException {
        List<SpecBO> specList = goodsBO.getSpecList();
        Map<String,Object> params = getPriceAndStock(specList);
        double price =(double) params.get("price");
        int stockNum = (int) params.get("stockNum");
        Goods goods = new Goods(goodsBO.getId(),goodsBO.getImg(),goodsBO.getName(),price,goodsBO.getTypeId(),stockNum,goodsBO.getDesc());
        for (SpecBO specBO : specList) {
            goodsDao.updateSpec(specBO);
        }
        goodsDao.updateGoods(goods);
    }

    @Override
    public GoodsInfoVO getGoodsInfo(String id) throws SQLException {
        int idToGet = Integer.parseInt(id);
        GoodsInfoVO infoVO = new GoodsInfoVO();
        infoVO.setSpecs(goodsDao.getSpecsById(idToGet));
        infoVO.setGoods(goodsDao.getGoodsById(idToGet));
        return infoVO;
    }

    @Override
    public void deleteSpec(SpecBO specBO) throws SQLException {
        String specName = specBO.getSpecName();
        int goodsId = specBO.getGoodsId();
        goodsDao.deleteSpec(goodsId,specName);
    }

    @Override
    public int addSpec(SpecBO specBO) throws SQLException {
        Spec spec = new Spec(null,specBO.getSpecName(),specBO.getStockNum(),specBO.getUnitPrice(),specBO.getGoodsId());
        int status = goodsDao.getSpecByName(specBO.getSpecName());
        if(status == 1){
            goodsDao.addSpec(spec);
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public void deleteGoods(String id) throws SQLException {
        int idToDelete = Integer.parseInt(id);
        goodsDao.deleteGoods(idToDelete);
    }

    @Override
    public void addType(TypeBO typeBO) throws SQLException {
        String typeName = typeBO.getName();
        goodsDao.addType(typeName);
    }

    @Override
    public void deleteType(String typeId) throws SQLException {
        int typeIdToDelete = Integer.parseInt(typeId);
        goodsDao.deleteType(typeIdToDelete);
    }

    @Override
    public List<AdminQnaVO> noReplyMsg() throws SQLException {
        return goodsDao.noReplyMsg();
    }

    @Override
    public List<AdminQnaVO> repliedMsg() throws SQLException {
        return goodsDao.repliedMsg();
    }

    @Override
    public void reply(ReplyBO replyBO) throws SQLException {
        goodsDao.reply(replyBO.getId(),replyBO.getContent());
    }

    @Override
    public List<UserSearchGoodsVO> searchGoods(String keyword) throws SQLException {
        return goodsDao.searchGoods(keyword);
    }

    @Override
    public GoodsDetailVO getGoodsDetailInfo(String id) throws SQLException {
        int idToGet = Integer.parseInt(id);
        Goods goods = goodsDao.getGoodsById(idToGet);
        GoodsDetailVO detailVO = new GoodsDetailVO(goods.getImg(),goods.getName(),goods.getDesc(),goods.getTypeId());
        detailVO.setSpecs(goodsDao.getSpecsById(idToGet));
        return detailVO;
    }

    @Override
    public List<GoodsMsgVO> getGoodsMsg(String id) throws SQLException {
        int idToGet = Integer.parseInt(id);
        return goodsDao.getGoodsMsg(idToGet);
    }

    @Override
    public GoodsCommentListVO getGoodsComment(String id) throws SQLException {
        int idToGet = Integer.parseInt(id);
        GoodsCommentListVO commentListVO = new GoodsCommentListVO();
        commentListVO.setCommentList(goodsDao.getGoodsComment(idToGet));
        commentListVO.setRate(getRate(commentListVO));
        return commentListVO;
    }

    @Override
    public void askGoodsMsg(AskGoodMsgBO goodMsgBO) throws SQLException {
        Qna qna = new Qna(goodMsgBO.getToken(),goodMsgBO.getMsg(),goodMsgBO.getGoodsId());
        goodsDao.askGoodsMsg(qna);
    }

    /**
     * 通过所有用户给出的score求出好评率
     * @param commentListVO
     * @return
     */
    private Integer getRate(GoodsCommentListVO commentListVO) {
        int rate = 0;
        int scoreSum = 0;
        for (GoodsCommentVO goodsCommentVO : commentListVO.getCommentList()) {
            scoreSum = scoreSum + goodsCommentVO.getScore();
        }
        if(scoreSum == 0){
            return 0;
        }
        rate = scoreSum / commentListVO.getCommentList().size();
        return rate;
    }


    /**通过 specList 获取价格和库存；
     * @param specList
     * @return
     */
    private Map<String, Object> getPriceAndStock(List<SpecBO> specList) {
        double price = specList.get(0).getUnitPrice();
        int stockNum = specList.get(0).getStockNum();
        for(int i = 1; i < specList.size(); i++){
            if(price > specList.get(i).getUnitPrice()){
                price = specList.get(i).getUnitPrice();
            }
            if(stockNum > specList.get(i).getStockNum()){
                price = specList.get(i).getStockNum();
            }
        }
        Map<String,Object> params = new HashMap<>();
        params.put("price",price);
        params.put("stockNum",stockNum);
        return params;
    }
}
