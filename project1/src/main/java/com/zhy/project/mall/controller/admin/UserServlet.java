package com.zhy.project.mall.controller.admin;

import com.google.gson.Gson;
import com.zhy.project.mall.model.Result;
import com.zhy.project.mall.model.vo.UserInfoVO;
import com.zhy.project.mall.service.UserService;
import com.zhy.project.mall.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/api/admin/user/*")
public class UserServlet extends HttpServlet {
    private Gson gson = new Gson();
    private UserService userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求Url
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/user", "");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求Url
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/user", "");
        if ("/allUser".equals(action)) {
            try {
                allUser(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if("/searchUser".equals(action)){
            try {
                searchUser(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if("/deleteUser".equals(action)){
            try {
                deleteUser(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String id = request.getParameter("id");
        userService.deleteUser(id);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    private void searchUser(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String keyWord = request.getParameter("word");
        List<UserInfoVO> userInfoVOList = userService.searchUser(keyWord);
        response.getWriter().println(gson.toJson(Result.ok(userInfoVOList)));

    }

    private void allUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        List<UserInfoVO> userInfoVOList = userService.allUser();
        response.getWriter().println(gson.toJson(Result.ok(userInfoVOList)));
    }
}
