package com.zhy.project.mall.service;

import com.zhy.project.mall.dao.AdminDao;
import com.zhy.project.mall.dao.AdminDaoImpl;
import com.zhy.project.mall.model.Admin;
import com.zhy.project.mall.model.bo.AdminLoginBO;
import com.zhy.project.mall.model.bo.AdminPwdBO;
import com.zhy.project.mall.model.bo.AdminSearchBO;
import com.zhy.project.mall.model.bo.AdminUpdateInfoBO;

import java.sql.SQLException;
import java.util.List;

public class AdminServiceImpl implements AdminService{
    private AdminDao adminDao = new AdminDaoImpl();

    @Override
    public Admin login(AdminLoginBO loginBO) throws SQLException {
        Admin admin = new Admin();
        admin.setEmail(loginBO.getEmail());
        admin.setPwd(loginBO.getPwd());
        return adminDao.login(admin);
    }

    @Override
    public List<Admin> allAdmins() throws SQLException {
        return adminDao.allAdmins();
    }

    @Override
    public int deleteAdmins(String id) throws SQLException {
        return adminDao.deleteAdmins(id);
    }

    @Override
    public Admin getAdminsInfo(String id) throws SQLException {
        return adminDao.getAdminsInfo(id);
    }

    @Override
    public int updateAdminss(String id, AdminUpdateInfoBO updateInfo) throws SQLException {
        Admin adminUpdate = new Admin();
        adminUpdate.setEmail(updateInfo.getEmail());
        adminUpdate.setNickname(updateInfo.getNickname());
        adminUpdate.setPwd(updateInfo.getPwd());
        return adminDao.updateAdminss(id,adminUpdate);
    }

    @Override
    public int addAdminss(AdminUpdateInfoBO addInfo) throws SQLException {
        Admin newAdmin = new Admin();
        newAdmin.setEmail(addInfo.getEmail());
        newAdmin.setNickname(addInfo.getNickname());
        newAdmin.setPwd(addInfo.getPwd());
        return adminDao.addAdminss(newAdmin);
    }

    @Override
    public List<Admin> getSearchAdmins(AdminSearchBO searchBO) throws SQLException {
        Admin adminSearch = new Admin();
        adminSearch.setEmail(searchBO.getEmail());
        adminSearch.setNickname(searchBO.getNickname());
        return adminDao.getSearchAdmins(adminSearch);
    }

    @Override
    public void changePwd(AdminPwdBO pwdBO) throws SQLException {
        String nickname = pwdBO.getAdminToken();
        String newPwd = pwdBO.getNewPwd();
        adminDao.changePwd(nickname,newPwd);
    }
}
