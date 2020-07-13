package com.zhy.project.mall.service;

import com.zhy.project.mall.dao.UserDao;
import com.zhy.project.mall.dao.UserDaoImpl;
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

public class UserServiceImpl implements UserService{
    UserDao userDao = new UserDaoImpl();
    @Override
    public List<UserInfoVO> allUser() throws SQLException {
        List<UserInfoVO> users = userDao.allUser();
        return users;
    }

    @Override
    public List<UserInfoVO> searchUser(String keyWord) throws SQLException {
        List<UserInfoVO> users = userDao.searchUser(keyWord);
        return users;
    }

    @Override
    public void deleteUser(String id) throws SQLException {
        int idToDelete = Integer.parseInt(id);
        userDao.deleteUser(idToDelete);
    }

    @Override
    public User login(UserLoginBO userLoginBO) throws SQLException {
        User user = new User();
        user.setEmail(userLoginBO.getEmail());
        user.setPwd(userLoginBO.getPwd());
        return userDao.login(user);
    }

    @Override
    public UserLoginVO signup(UserSignupBO signupBO) throws SQLException {
        User user = new User();
        user.setEmail(signupBO.getEmail());
        user.setPwd(signupBO.getPwd());
        user.setNickname(signupBO.getNickname());
        user.setRecipient(signupBO.getRecipient());
        user.setAddress(signupBO.getAddress());
        user.setPhone(signupBO.getPhone());
        userDao.signup(user);
        UserLoginVO loginVO = new UserLoginVO(0,signupBO.getNickname(),signupBO.getNickname());
        return loginVO;
    }

    @Override
    public UserDataVO data(String token) throws SQLException {
        User user = userDao.data(token);
        UserDataVO dataVO = new UserDataVO(0,user.getId() + "",user.getEmail(),user.getNickname(),user.getRecipient(),user.getAddress(),user.getPhone());
        return dataVO;
    }

    @Override
    public void updateUserData(UserUpdateBO updateBO) throws SQLException {
        User user = new User(updateBO.getId(),updateBO.getNickname(),updateBO.getRecipient(),updateBO.getAddress(),updateBO.getPhone());
        userDao.updateUserData(user);
    }

    @Override
    public String getPwdById(Integer id) throws SQLException {
        return userDao.getPwdById(id);
    }

    @Override
    public void updatePwd(UserUpdatePwdBO updatePwdBO) throws SQLException {
        userDao.updatePwd(updatePwdBO.getId(),updatePwdBO.getNewPwd());
    }
}
