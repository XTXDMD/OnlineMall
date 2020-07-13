package com.zhy.project.mall.controller.mall;

import com.google.gson.Gson;
import com.zhy.project.mall.model.Result;
import com.zhy.project.mall.model.Type;
import com.zhy.project.mall.model.User;
import com.zhy.project.mall.model.bo.UserLoginBO;
import com.zhy.project.mall.model.bo.UserSignupBO;
import com.zhy.project.mall.model.bo.UserUpdateBO;
import com.zhy.project.mall.model.bo.UserUpdatePwdBO;
import com.zhy.project.mall.model.vo.TypeGoodsVO;
import com.zhy.project.mall.model.vo.UserDataVO;
import com.zhy.project.mall.model.vo.UserLoginVO;
import com.zhy.project.mall.service.GoodsService;
import com.zhy.project.mall.service.GoodsServiceImpl;
import com.zhy.project.mall.service.UserService;
import com.zhy.project.mall.service.UserServiceImpl;
import com.zhy.project.mall.utils.HttpUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/api/mall/user/*")
public class UserServlet extends HttpServlet {
    Gson gson = new Gson();
    UserService userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求Url
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/mall/user", "");
        if ("/login".equals(action)) {
            try {
                login(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if ("/logoutClient".equals(action)) {
            logoutClient(request, response);

        } else if ("/signup".equals(action)) {
            try {
                signup(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if ("/updateUserData".equals(action)) {
            try {
                updateUserData(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if ("/updatePwd".equals(action)) {
            try {
                updatePwd(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void logoutClient(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    private void updatePwd(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String requestBody = HttpUtils.getRequestBody(request);
        UserUpdatePwdBO updatePwdBO = gson.fromJson(requestBody, UserUpdatePwdBO.class);
        if (isOldPwdCorrect(updatePwdBO.getId(), updatePwdBO.getOldPwd())) {
            userService.updatePwd(updatePwdBO);
            response.getWriter().println(gson.toJson(Result.ok()));
        } else {
            response.getWriter().println(gson.toJson(Result.error("旧密码不正确")));
        }

    }

    private boolean isOldPwdCorrect(Integer id, String oldPwd) throws SQLException {
        String pwd = userService.getPwdById(id);
        if (pwd.equals(oldPwd)) {
            return true;
        } else {
            return false;
        }
    }

    private void updateUserData(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String requestBody = HttpUtils.getRequestBody(request);
        UserUpdateBO updateBO = gson.fromJson(requestBody, UserUpdateBO.class);
        userService.updateUserData(updateBO);
        response.getWriter().println(gson.toJson(Result.ok("修改成功")));
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String requestBody = HttpUtils.getRequestBody(request);
        UserLoginBO userLoginBO = gson.fromJson(requestBody, UserLoginBO.class);
        User login = userService.login(userLoginBO);
        UserLoginVO loginVO = new UserLoginVO();
        if (login != null) {
            loginVO.setCode(0);
            loginVO.setName(login.getNickname());
            loginVO.setToken(login.getNickname());
            request.getSession().setAttribute("user", login);
            response.getWriter().println(gson.toJson(Result.ok(loginVO)));
        } else {
            response.getWriter().println(gson.toJson(Result.error("用户名或密码错误！！！")));
        }


    }

    private void signup(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String requestBody = HttpUtils.getRequestBody(request);
        UserSignupBO signupBO = gson.fromJson(requestBody, UserSignupBO.class);
        UserLoginVO loginVO = userService.signup(signupBO);
        response.getWriter().println(gson.toJson(Result.ok(loginVO)));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求Url
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/mall/user", "");
        if ("/data".equals(action)) {
            try {
                data(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void data(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String token = request.getParameter("token");
        UserDataVO dataVO = userService.data(token);
        response.getWriter().println(gson.toJson(Result.ok(dataVO)));
    }


}
