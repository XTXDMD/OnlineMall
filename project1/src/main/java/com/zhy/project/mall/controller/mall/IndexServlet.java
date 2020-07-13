package com.zhy.project.mall.controller.mall;

import com.google.gson.Gson;
import com.zhy.project.mall.model.Result;
import com.zhy.project.mall.model.Type;
import com.zhy.project.mall.model.vo.TypeGoodsVO;
import com.zhy.project.mall.service.GoodsService;
import com.zhy.project.mall.service.GoodsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/api/mall/index/*")
public class IndexServlet extends HttpServlet {
    GoodsService goodsService = new GoodsServiceImpl();
    Gson gson = new Gson();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求Url
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/mall/index", "");
        if("/getType".equals(action)){
            try {
                getType(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if("/getGoodsByType".equals(action)){
            try {
                getGoodsByType(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void getGoodsByType(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String typeId = request.getParameter("typeId");
        List<TypeGoodsVO> goodsVO = goodsService.getGoodsByType(typeId);
        response.getWriter().println(gson.toJson(Result.ok(goodsVO)));
    }

    private void getType(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        List<Type> types = goodsService.getType();
        response.getWriter().println(gson.toJson(Result.ok(types)));
    }
}
