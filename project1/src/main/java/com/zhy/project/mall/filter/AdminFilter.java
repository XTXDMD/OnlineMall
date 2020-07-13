package com.zhy.project.mall.filter;

import com.google.gson.Gson;
import com.zhy.project.mall.model.Admin;
import com.zhy.project.mall.model.Result;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/api/admin/*")
public class AdminFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        //将*改为页面系统所在的域（解决跨域无法获取相同session问题）
        response.setHeader("Access-Control-Allow-Origin","http://localhost:8085");
        response.setHeader("Access-Control-Allow-Methods","POST,GET,OPTIONS,PUT,DELETE");
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,Authorization,Content-Type");
        response.setHeader("Access-Control-Allow-Credentials","true");
        response.setContentType("text/html;charset=utf-8");
        String requestURI = request.getRequestURI();
        //判断哪些URI需要拦截
        if(!request.getMethod().equals("OPTIONS")){
            if(auth(requestURI)){
                Admin admin = (Admin) request.getSession().getAttribute("admin");
                if(admin == null){
                    response.getWriter().println(new Gson().toJson(Result.error("该借口需要登陆后访问！！！")));
                    return;
                }
            }
        }
        chain.doFilter(req, resp);
    }

    private boolean auth(String requestURI) {
        if("/api/admin/admin/login".equals(requestURI) || "/api/admin/admin/logoutAdmin".equals(requestURI)){
            return false;
        }
        return true;
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
