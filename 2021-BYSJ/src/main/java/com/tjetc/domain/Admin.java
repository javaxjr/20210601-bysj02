package com.tjetc.domain;
/*
* 后台管理员表
* */
public class Admin extends People{

    private String permission;//Permission setting 权限设置
    // LOCATION:超级管理员访问   IDENTITY:普通管理员访问

    public Admin() {
    }

    @Override
    public String toString() {
        return "Admin{" +
                "permission='" + permission + '\'' +
                '}';
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
