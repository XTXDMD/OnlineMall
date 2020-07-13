package com.zhy.project.mall.controller.mall;

import com.google.gson.Gson;
import com.zhy.project.mall.model.Result;
import com.zhy.project.mall.model.bo.AskGoodMsgBO;
import com.zhy.project.mall.model.vo.*;
import com.zhy.project.mall.service.GoodsService;
import com.zhy.project.mall.service.GoodsServiceImpl;
import com.zhy.project.mall.utils.HttpUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/api/mall/goods/*")
public class GoodsServlet extends HttpServlet {
    GoodsService goodsService = new GoodsServiceImpl();
    Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求Url
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/mall/goods", "");
        if("/askGoodsMsg".equals(action)){
            try {
                askGoodsMsg(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void askGoodsMsg(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String requestBody = HttpUtils.getRequestBody(request);
        AskGoodMsgBO goodMsgBO = gson.fromJson(requestBody,AskGoodMsgBO.class);
        goodsService.askGoodsMsg(goodMsgBO);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求Url
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/mall/goods", "");
        if ("/getGoodsByType".equals(action)) {
            try {
                getGoodsByType(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if("/searchGoods".equals(action)){
            try {
                searchGoods(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if("/getGoodsInfo".equals(action)){
            try {
                getGoodsInfo(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if("/getGoodsMsg".equals(action)){
            try {
                getGoodsMsg(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if("/getGoodsComment".equals(action)){
            try {
                getGoodsComment(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void getGoodsComment(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String id = request.getParameter("goodsId");
        GoodsCommentListVO commentListVO = goodsService.getGoodsComment(id);
        response.getWriter().println(gson.toJson(Result.ok(commentListVO)));
    }

    private void getGoodsMsg(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String id = request.getParameter("id");
        List<GoodsMsgVO> msgVOS = goodsService.getGoodsMsg(id);
        response.getWriter().println(gson.toJson(Result.ok(msgVOS)));
    }

    private void getGoodsInfo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String id = request.getParameter("id");
        GoodsDetailVO detailVO = goodsService.getGoodsDetailInfo(id);
        response.getWriter().println(gson.toJson(Result.ok(detailVO)));
    }

    private void searchGoods(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String keyword = request.getParameter("keyword");
        List<UserSearchGoodsVO> goodsVOList = goodsService.searchGoods(keyword);
        response.getWriter().println(gson.toJson(Result.ok(goodsVOList)));
    }

    private void getGoodsByType(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String typeId = request.getParameter("typeId");
        List<TypeGoodsVO> goodsVO = goodsService.getGoodsByType(typeId);
        response.getWriter().println(gson.toJson(Result.ok(goodsVO)));
    }
}
