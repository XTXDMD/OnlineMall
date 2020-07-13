package com.zhy.project.mall.dao;

import com.zhy.project.mall.model.User;
import com.zhy.project.mall.model.bo.UserLoginBO;
import com.zhy.project.mall.model.vo.UserInfoVO;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    List<UserInfoVO> allUser() throws SQLException;

    List<UserInfoVO> searchUser(String keyWord) throws SQLException;

    void deleteUser(int idToDelete) throws SQLException;

    User login(User user) throws SQLException;

    void signup(User user) throws SQLException;

    User data(String token) throws SQLException;

    void updateUserData(User user) throws SQLException;

    String getPwdById(Integer id) throws SQLException;

    void updatePwd(Integer id, String newPwd) throws SQLException;
}
