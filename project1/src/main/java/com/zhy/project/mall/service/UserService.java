package com.zhy.project.mall.service;

import com.zhy.project.mall.model.User;
import com.zhy.project.mall.model.bo.UserLoginBO;
import com.zhy.project.mall.model.bo.UserSignupBO;
import com.zhy.project.mall.model.bo.UserUpdateBO;
import com.zhy.project.mall.model.bo.UserUpdatePwdBO;
import com.zhy.project.mall.model.vo.UserDataVO;
import com.zhy.project.mall.model.vo.UserInfoVO;
import com.zhy.project.mall.model.vo.UserLoginVO;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    List<UserInfoVO> allUser() throws SQLException;

    List<UserInfoVO> searchUser(String keyWord) throws SQLException;

    void deleteUser(String id) throws SQLException;

    User login(UserLoginBO userLoginBO) throws SQLException;

    UserLoginVO signup(UserSignupBO signupBO) throws SQLException;

    UserDataVO data(String token) throws SQLException;

    void updateUserData(UserUpdateBO updateBO) throws SQLException;

    String getPwdById(Integer id) throws SQLException;

    void updatePwd(UserUpdatePwdBO updatePwdBO) throws SQLException;
}
