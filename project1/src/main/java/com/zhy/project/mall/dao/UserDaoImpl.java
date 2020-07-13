package com.zhy.project.mall.dao;

import com.zhy.project.mall.model.User;
import com.zhy.project.mall.model.vo.UserInfoVO;
import com.zhy.project.mall.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao{
    @Override
    public List<UserInfoVO> allUser() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        List<UserInfoVO> users = queryRunner.query("SELECT * FROM user",new BeanListHandler<UserInfoVO>(UserInfoVO.class));
        return users;
    }

    @Override
    public List<UserInfoVO> searchUser(String keyWord) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String nickname = "%" + keyWord + "%";
        List<UserInfoVO> users = queryRunner.query("SELECT * FROM user WHERE nickname LIKE ?",new BeanListHandler<UserInfoVO>(UserInfoVO.class),nickname);
        return users;
    }

    @Override
    public void deleteUser(int idToDelete) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        queryRunner.update("DELETE FROM user WHERE id = ?",idToDelete);
    }

    @Override
    public User login(User user) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        User query = queryRunner.query("select * from user where email = ? and pwd = ?",
                new BeanHandler<>(User.class),
                user.getEmail(),
                user.getPwd());
        return query;
    }

    @Override
    public void signup(User user) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        queryRunner.update("INSERT INTO user VALUES(null,?,?,?,?,?,?)",user.getEmail(),user.getNickname(),user.getPwd(),user.getRecipient(),user.getAddress(),user.getPhone());
    }

    @Override
    public User data(String token) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        User query = queryRunner.query("SELECT * FROM user WHERE nickname = ?", new BeanHandler<>(User.class), token);
        return query;
    }

    @Override
    public void updateUserData(User user) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        queryRunner.update("UPDATE user SET nickname = ?, recipient = ?, address = ?, phone = ? WHERE id = ?",user.getNickname(),user.getRecipient(),user.getAddress(),user.getPhone(),user.getId());
    }

    @Override
    public String getPwdById(Integer id) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String pwd = queryRunner.query("SELECT pwd FROM user WHERE id = ?", new ScalarHandler<String>(), id);
        return pwd;
    }

    @Override
    public void updatePwd(Integer id, String newPwd) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        queryRunner.update("UPDATE user SET pwd = ? WHERE id = ?",newPwd,id);
    }
}
