package com.zhy.project.mall.dao;

import com.zhy.project.mall.model.Admin;

import java.sql.SQLException;
import java.util.List;

public interface AdminDao {
    Admin login(Admin admin) throws SQLException;

    List<Admin> allAdmins() throws SQLException;

    int deleteAdmins(String id) throws SQLException;

    Admin getAdminsInfo(String id) throws SQLException;

    int updateAdminss(String id, Admin updateInfo) throws SQLException;

    int addAdminss(Admin addInfo) throws SQLException;

    List<Admin> getSearchAdmins(Admin adminSearch) throws SQLException;

    void changePwd(String email,String newPwd) throws SQLException;
}
