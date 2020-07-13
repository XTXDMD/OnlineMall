package com.zhy.project.mall.controller.admin;

import com.google.gson.Gson;
import com.zhy.project.mall.model.Admin;
import com.zhy.project.mall.model.Result;
import com.zhy.project.mall.model.bo.AdminPwdBO;
import com.zhy.project.mall.model.bo.AdminSearchBO;
import com.zhy.project.mall.model.bo.AdminUpdateInfoBO;
import com.zhy.project.mall.model.bo.AdminLoginBO;
import com.zhy.project.mall.model.vo.AdminInfoVO;
import com.zhy.project.mall.model.vo.AdminLoginVO;
import com.zhy.project.mall.service.AdminService;
import com.zhy.project.mall.service.AdminServiceImpl;
import com.zhy.project.mall.utils.HttpUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/api/admin/admin/*")
public class AdminServlet extends HttpServlet {
    private AdminService adminService = new AdminServiceImpl();
    private Gson gson = new Gson();
    private String idToUpdate;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求Url
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/admin", "");
        if ("/login".equals(action)) {
            try {
                login(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if ("/logoutAdmin".equals(action)) {
            logoutAdmin(request, response);


        } else if ("/updateAdminss".equals(action)) {
            try {
                updateAdminss(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else if ("/addAdminss".equals(action)) {
            try {
                addAdminss(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else if ("/getSearchAdmins".equals(action)) {
            try {
                getSearchAdmins(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if ("/changePwd".equals(action)) {
            try {
                changePwd(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    private void logoutAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.getWriter().println(gson.toJson(Result.ok("已退出登录")));
    }

    private void getSearchAdmins(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String requestBody = HttpUtils.getRequestBody(request);
        AdminSearchBO searchBO = gson.fromJson(requestBody, AdminSearchBO.class);
        List<Admin> admins = adminService.getSearchAdmins(searchBO);
        if (admins != null) {
            response.getWriter().println(gson.toJson(Result.ok(admins)));
        }
    }

    private void changePwd(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String requestBody = HttpUtils.getRequestBody(request);
        AdminPwdBO pwdBO = gson.fromJson(requestBody, AdminPwdBO.class);
        if (pwdBO.getNewPwd().equals(pwdBO.getConfirmPwd())) {
            adminService.changePwd(pwdBO);
            response.getWriter().println(gson.toJson(Result.ok()));
        } else {
            response.getWriter().println(gson.toJson(Result.error("两次输入的新密码不一致")));
        }
    }

    /**
     * 管理员登陆，获取前端传过来的json字符串
     *
     * @param request
     * @param response
     */
    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String requestBody = HttpUtils.getRequestBody(request);
        AdminLoginBO LoginBO = gson.fromJson(requestBody, AdminLoginBO.class);
        Admin login = adminService.login(LoginBO);
        Result result;
        if (login != null) {
            AdminLoginVO adminLoginVO = new AdminLoginVO();
            adminLoginVO.setName(login.getNickname());
            adminLoginVO.setToken(login.getNickname());
            result = Result.ok(adminLoginVO);
            request.getSession().setAttribute("admin", login);
        } else {
            result = Result.error("用户名或密码错误！");
        }
        response.getWriter().println(gson.toJson(result));

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI().toString();
        String action = requestURI.replace("/api/admin/admin", "");
        if ("/allAdmins".equals(action)) {
            try {
                allAdmins(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        if ("/deleteAdmins".equals(action)) {
            try {
                deleteAdmins(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if ("/getAdminsInfo".equals(action)) {
            try {
                getAdminsInfo(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 新增管理员
     *
     * @param request
     * @param response
     * @throws IOException
     */

    private void addAdminss(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String requestBody = HttpUtils.getRequestBody(request);
        AdminUpdateInfoBO addInfo = gson.fromJson(requestBody, AdminUpdateInfoBO.class);
        int stutas = adminService.addAdminss(addInfo);
        if (stutas > 0) {
            response.getWriter().println(gson.toJson(Result.ok()));
        }
    }


    /**
     * 通过id修改管理员信息
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws SQLException
     */
    private void updateAdminss(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String requestBody = HttpUtils.getRequestBody(request);
        AdminUpdateInfoBO updateInfo = gson.fromJson(requestBody, AdminUpdateInfoBO.class);
        int status = adminService.updateAdminss(idToUpdate, updateInfo);
        if (status > 0) {
            response.getWriter().println(gson.toJson(Result.ok()));
        }
    }

    /**
     * 通过id查询管理员信息
     *
     * @param request
     * @param response
     * @throws SQLException
     * @throws IOException
     */
    private void getAdminsInfo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String id = request.getParameter("id");
        this.idToUpdate = id;
        Admin adminInfo = adminService.getAdminsInfo(id);
        if (adminInfo != null) {
            AdminInfoVO infoVO = new AdminInfoVO();
            infoVO.setEmail(adminInfo.getEmail());
            infoVO.setNickname(adminInfo.getNickname());
            infoVO.setPwd(adminInfo.getPwd());
            response.getWriter().println(gson.toJson(Result.ok(infoVO)));
        } else {
            response.getWriter().println(gson.toJson(Result.error("管理员信息不存在！")));
        }
    }

    /**
     * 通过id删除管理员信息
     *
     * @param request
     * @param response
     * @throws SQLException
     * @throws IOException
     */
    private void deleteAdmins(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String id = request.getParameter("id");
        int status = adminService.deleteAdmins(id);
        if (status > 0) {
            response.getWriter().println(gson.toJson(Result.ok()));
        }
    }


    private void allAdmins(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        List<Admin> admins = adminService.allAdmins();
        Result result = new Result();
        result.setCode(0);
        result.setData(admins);
        response.getWriter().println(gson.toJson(result));
    }
}
