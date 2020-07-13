package com.zhy.project.mall.model.bo;

public class AdminPwdBO {
    private String adminToken;
    private String confirmPwd;
    private String newPwd;
    private String oldPwd;

    public AdminPwdBO(String adminToken, String confirmPwd, String newPwd, String oldPwd) {
        this.adminToken = adminToken;
        this.confirmPwd = confirmPwd;
        this.newPwd = newPwd;
        this.oldPwd = oldPwd;
    }

    public AdminPwdBO() {
    }

    public String getAdminToken() {
        return adminToken;
    }

    public void setAdminToken(String adminToken) {
        this.adminToken = adminToken;
    }

    public String getConfirmPwd() {
        return confirmPwd;
    }

    public void setConfirmPwd(String confirmPwd) {
        this.confirmPwd = confirmPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }
}
