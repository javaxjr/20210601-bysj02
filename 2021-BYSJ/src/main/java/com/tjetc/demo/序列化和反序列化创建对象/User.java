package com.tjetc.demo.序列化和反序列化创建对象;


import java.io.Serializable;

/*
* 用户表
* */
public class User implements Serializable {

    private static final long serialVersionUID=1L;
    private String id;
    private String username;//用户名
    private String password;//登录密码

    public User() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
