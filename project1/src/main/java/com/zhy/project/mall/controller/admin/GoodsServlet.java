package com.zhy.project.mall.controller.admin;

import com.google.gson.Gson;
import com.zhy.project.mall.model.Result;
import com.zhy.project.mall.model.Type;
import com.zhy.project.mall.model.bo.GoodsBO;
import com.zhy.project.mall.model.bo.ReplyBO;
import com.zhy.project.mall.model.bo.SpecBO;
import com.zhy.project.mall.model.bo.TypeBO;
import com.zhy.project.mall.model.vo.AdminQnaVO;
import com.zhy.project.mall.model.vo.GoodsInfoVO;
import com.zhy.project.mall.model.vo.TypeGoodsVO;
import com.zhy.project.mall.service.GoodsService;
import com.zhy.project.mall.service.GoodsServiceImpl;
import com.zhy.project.mall.utils.FileUploadUtils;
import com.zhy.project.mall.utils.HttpUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/api/admin/goods/*")
public class GoodsServlet extends HttpServlet {
    private GoodsService goodsService = new GoodsServiceImpl();
    private Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求Url
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/goods", "");
        if ("/imgUpload".equals(action)) {
            imgUpload(request,response);
        }else if ("/addGoods".equals(action)) {
            try {
                addGoods(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if ("/deleteSpec".equals(action)) {
            try {
                deleteSpec(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }else if ("/addSpec".equals(action)) {
            try {
                addSpec(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }else if ("/updateGoods".equals(action)) {
            try {
                updateGoods(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if ("/addType".equals(action)) {
            try {
                addType(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if ("/reply".equals(action)) {
            try {
                reply(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void reply(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String requstBody = HttpUtils.getRequestBody(request);
        ReplyBO replyBO = gson.fromJson(requstBody,ReplyBO.class);
        goodsService.reply(replyBO);
        response.getWriter().println(gson.toJson(Result.ok("回复成功！")));
    }

    private void addType(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String requestBody = HttpUtils.getRequestBody(request);
        TypeBO typeBO = gson.fromJson(requestBody, TypeBO.class);
        goodsService.addType(typeBO);
        response.getWriter().println(gson.toJson(Result.ok()));
    }


    private void imgUpload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,Object> map = FileUploadUtils.parseRequest(request);
        String domain = (String) getServletContext().getAttribute("domain");
        String file = (String) map.get("file");
        response.getWriter().println(gson.toJson(Result.ok(domain + file)));
    }

    private void addGoods(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String requestBody = HttpUtils.getRequestBody(request);
        GoodsBO goodsBO = gson.fromJson(requestBody,GoodsBO.class);
        goodsService.addGoods(goodsBO);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    private void deleteSpec(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String requestBody = HttpUtils.getRequestBody(request);
        SpecBO specBO = gson.fromJson(requestBody,SpecBO.class);
        goodsService.deleteSpec(specBO);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    private void addSpec(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String requesBody = HttpUtils.getRequestBody(request);
        SpecBO specBO = gson.fromJson(requesBody,SpecBO.class);
        int status = goodsService.addSpec(specBO);
        if(status == 1){
            response.getWriter().println(gson.toJson(Result.ok(specBO)));
        }else{
            response.getWriter().println(gson.toJson(Result.error("规格重复")));
        }
    }

    private void updateGoods(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String requestBody = HttpUtils.getRequestBody(request);
        GoodsBO goodsBO = gson.fromJson(requestBody,GoodsBO.class);
        goodsService.updateGoods(goodsBO);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求Url
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/goods", "");
        if ("/getType".equals(action)) {
            try {
                getType(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if ("/getGoodsByType".equals(action)) {
            try {
                getGoodsByType(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if ("/getGoodsInfo".equals(action)) {
            try {
                getGoodsInfo(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if ("/deleteGoods".equals(action)) {
            try {
                deleteGoods(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if ("/deleteType".equals(action)) {
            try {
                deleteType(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if ("/noReplyMsg".equals(action)) {
            try {
                noReplyMsg(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if ("/repliedMsg".equals(action)) {
            try {
                repliedMsg(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void repliedMsg(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        List<AdminQnaVO> adminQnaVO = goodsService.repliedMsg();
        response.getWriter().println(gson.toJson(Result.ok(adminQnaVO)));
    }

    private void noReplyMsg(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        List<AdminQnaVO> adminQnaVO = goodsService.noReplyMsg();
        response.getWriter().println(gson.toJson(Result.ok(adminQnaVO)));
    }

    private void deleteType(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String typeId = request.getParameter("typeId");
        goodsService.deleteType(typeId);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    private void deleteGoods(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String id = request.getParameter("id");
        goodsService.deleteGoods(id);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    private void getGoodsInfo(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String id = request.getParameter("id");
        GoodsInfoVO goodsInfoVO = goodsService.getGoodsInfo(id);
        response.getWriter().println(gson.toJson(Result.ok(goodsInfoVO)));
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
