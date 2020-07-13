package com.zhy.project.mall.service;

import com.zhy.project.mall.model.Admin;
import com.zhy.project.mall.model.bo.AdminLoginBO;
import com.zhy.project.mall.model.bo.AdminPwdBO;
import com.zhy.project.mall.model.bo.AdminSearchBO;
import com.zhy.project.mall.model.bo.AdminUpdateInfoBO;

import java.sql.SQLException;
import java.util.List;

public interface AdminService {
    Admin login(AdminLoginBO loginBO) throws SQLException;

    List<Admin> allAdmins() throws SQLException;

    int deleteAdmins(String id) throws SQLException;

    Admin getAdminsInfo(String id) throws SQLException;

    int updateAdminss(String id, AdminUpdateInfoBO updateInfo) throws SQLException;

    int addAdminss(AdminUpdateInfoBO addInfo) throws SQLException;

    List<Admin> getSearchAdmins(AdminSearchBO searchBO) throws SQLException;

    void changePwd(AdminPwdBO pwdBO) throws SQLException;
}
