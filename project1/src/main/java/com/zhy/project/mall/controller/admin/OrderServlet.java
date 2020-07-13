package com.zhy.project.mall.controller.admin;

import com.google.gson.Gson;
import com.zhy.project.mall.model.Result;
import com.zhy.project.mall.model.bo.ChangeOrderBO;
import com.zhy.project.mall.model.bo.PageOrderBO;
import com.zhy.project.mall.model.vo.OrderInfoVO;
import com.zhy.project.mall.model.vo.PageOrdersVO;
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
import java.util.List;

@WebServlet("/api/admin/order/*")
public class OrderServlet extends HttpServlet {
    OrderService orderService = new OrderServiceImpl();
    Gson gson = new Gson();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求Url
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/order", "");
        if("/ordersByPage".equals(action)){
            try {
                ordersByPage(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if("/changeOrder".equals(action)){
            try {
                changeOrder(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * 通过订单id修改订单信息
     * @param request
     * @param response
     */
    private void changeOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String requestBody = HttpUtils.getRequestBody(request);
        ChangeOrderBO changeOrderBO = gson.fromJson(requestBody,ChangeOrderBO.class);
        orderService.changeOrder(changeOrderBO);
        response.getWriter().println(gson.toJson(Result.ok()));
}

    /**
     * 分页显示订单信息
     * @param request
     * @param response
     */
    private void ordersByPage(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String requestBody = HttpUtils.getRequestBody(request);
        PageOrderBO orderBO = gson.fromJson(requestBody,PageOrderBO.class);
        PageOrdersVO orders = orderService.ordersByPage(orderBO);
        response.getWriter().println(gson.toJson(Result.ok(orders)));

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求Url
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/order", "");
        if("/order".equals(action)){
            try {
                order(request,response);
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

    /**
     * 通过id删除订单信息
     * @param request
     * @param response
     */
    private void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String id = request.getParameter("id");
        orderService.deleteOrder(id);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    /**
     * 通过订单id获取订单信息
     * @param request
     * @param response
     * @throws SQLException
     * @throws IOException
     */
    private void order(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String id = request.getParameter("id");
        OrderInfoVO infoVO = orderService.order(id);
        response.getWriter().println(gson.toJson(Result.ok(infoVO)));
    }
}
