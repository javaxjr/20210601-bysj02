package com.tjetc.domain;

import com.sun.jersey.spi.resource.Singleton;

/*
 * 后台管理员表
 * */
@Singleton //此注解表示此类为，，单例类（单例模式）
public class Admin extends People {


    private String permission;//Permission setting 权限设置
    // LOCATION:超级管理员访问   IDENTITY:普通管理员访问


    public Admin() {
        System.out.println(" 对象被创建了.............");
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
