package com.zhy.project.mall.controller.mall;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.zhy.project.mall.model.Result;
import com.zhy.project.mall.model.User;
import com.zhy.project.mall.model.bo.AddOrderBO;
import com.zhy.project.mall.model.bo.CartBO;
import com.zhy.project.mall.model.bo.CartListBO;
import com.zhy.project.mall.model.bo.UserCommentBO;
import com.zhy.project.mall.model.vo.UserOrdersVO;
import com.zhy.project.mall.service.OrderService;
import com.zhy.project.mall.service.OrderServiceImpl;
import com.zhy.project.mall.utils.HttpUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/api/mall/order/*")
public class OrdersServlet extends HttpServlet {
    OrderService orderService = new OrderServiceImpl();
    Gson gson = new Gson();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求Url
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/mall/order", "");
        if("/settleAccounts".equals(action)){
            try {
                settleAccounts(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if("/sendComment".equals(action)){
            try {
                sendComment(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if("/addOrder".equals(action)){
            try {
                addOrder(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void addOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String requestBody = HttpUtils.getRequestBody(request);
        AddOrderBO orderBO = gson.fromJson(requestBody,AddOrderBO.class);
        int status = orderService.addOrder(orderBO);
        if(status == 0){
            response.getWriter().println(gson.toJson(Result.error("库存不足！！！")));
        }else {
            response.getWriter().println(gson.toJson(Result.ok()));
        }

    }

    private void sendComment(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String requestBody = HttpUtils.getRequestBody(request);
        UserCommentBO commentBO = gson.fromJson(requestBody,UserCommentBO.class);
        orderService.sendComment(commentBO);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    private void settleAccounts(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String requestBody = HttpUtils.getRequestBody(request);
        //将json Array 转化为CartBO对象List
        JsonElement jsonElement = new JsonParser().parse(requestBody);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        List<CartBO> cartBOS = new ArrayList<CartBO>();
        JsonArray cartList = jsonObject.getAsJsonArray("cartList");
        for (JsonElement element : cartList) {
            CartBO cartBO = gson.fromJson(element,CartBO.class);
            cartBOS.add(cartBO);
        }
        int status = orderService.settleAccounts(cartBOS);
        if(status == 0){
            response.getWriter().println(gson.toJson(Result.error("库存不足！！！")));
        }else {
            response.getWriter().println(gson.toJson(Result.ok()));
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求Url
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/mall/order", "");
        if ("/getOrderByState".equals(action)) {
            try {
                getOrderByState(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if("/pay".equals(action)){
            try {
                pay(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        else if("/confirmReceive".equals(action)){
            try {
                confirmReceive(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }else if("/deleteOrder".equals(action)){
            try {
                deleteOrder(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String id = request.getParameter("id");
        orderService.deleteOrder(id);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    private void confirmReceive(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String id = request.getParameter("id");
        orderService.confirmReceive(id);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    private void pay(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String id = request.getParameter("id");
        orderService.pay(id);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    private void getOrderByState(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String stateId = request.getParameter("state");
        String token = request.getParameter("token");
        List<UserOrdersVO> ordersVOS = orderService.getOrderByState(stateId,token);
        response.getWriter().println(gson.toJson(Result.ok(ordersVOS)));
    }
}
