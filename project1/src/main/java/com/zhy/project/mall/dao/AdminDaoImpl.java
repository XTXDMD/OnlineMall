package com.zhy.project.mall.dao;

import com.mysql.cj.util.StringUtils;
import com.zhy.project.mall.model.Admin;
import com.zhy.project.mall.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminDaoImpl implements AdminDao {
    @Override
    public Admin login(Admin admin) throws SQLException{
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        Admin query = queryRunner.query("select * from admin where email = ? and pwd = ?",
                new BeanHandler<>(Admin.class),
                admin.getEmail(),
                admin.getPwd());
        return query;
    }

    @Override
    public List<Admin> allAdmins() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        List<Admin> admins = queryRunner.query("select * from admin",
                new BeanListHandler<Admin>(Admin.class));
        return admins;
    }

    @Override
    public int deleteAdmins(String id) throws SQLException {
        int idToDelet = Integer.parseInt(id);
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        int status = queryRunner.execute("delete from admin where id = ?",idToDelet);
        return status;
    }

    @Override
    public Admin getAdminsInfo(String id) throws SQLException {
        int idToGet = Integer.parseInt(id);
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        Admin adminInfo = queryRunner.query("select * from admin where id = ?",
                new BeanHandler<>(Admin.class),
                idToGet);
        return adminInfo;
    }

    @Override
    public int updateAdminss(String id, Admin updateInfo) throws SQLException {
        int idToUpdate = Integer.parseInt(id);
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        int status = queryRunner.update("UPDATE admin SET email = ?,nickname = ?, pwd = ? WHERE id = ?",
                updateInfo.getEmail(),updateInfo.getNickname(),updateInfo.getPwd(),idToUpdate);
        return status;
    }

    @Override
    public int addAdminss(Admin addInfo) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        int status = queryRunner.execute("INSERT INTO admin (email,nickname,pwd) VALUES (?,?,?)",
                addInfo.getEmail(),addInfo.getNickname(),addInfo.getPwd());
        return status;
    }

    @Override
    public List<Admin> getSearchAdmins(Admin adminSearch) throws SQLException {
        Map<String,Object> result = getDynamicSql(adminSearch);
        String sql = (String)result.get("sql");
        List<String> params = (List<String>) result.get("params");
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        List<Admin> admins = queryRunner.query(sql,new BeanListHandler<Admin>(Admin.class),params.toArray());
        return admins;
    }

    @Override
    public void changePwd(String nickname,String newPwd) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        queryRunner.update("UPDATE admin SET pwd = ? where nickname = ?",newPwd,nickname);
    }

    private Map<String, Object> getDynamicSql(Admin adminSearch) {
        String base = "select * from admin where 1 = 1 ";
        List<String> params = new ArrayList<>();
        if(!StringUtils.isNullOrEmpty(adminSearch.getEmail())){
            base = base + "and email like ?";
            params.add("%" + adminSearch.getEmail() + "%");
        }
        if(!StringUtils.isNullOrEmpty(adminSearch.getNickname())){
            base = base + " and nickname like ?";
            params.add("%" + adminSearch.getNickname() + "%");
        }
        Map<String,Object>  map = new HashMap<>();
        map.put("sql",base);
        map.put("params",params);
        return map;
    }
}
