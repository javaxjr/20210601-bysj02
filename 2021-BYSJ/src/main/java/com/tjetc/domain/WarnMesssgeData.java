package com.tjetc.domain;

/*
* 发送邮件时  相关信息的类
* */

public class WarnMesssgeData {
    private String subject;  //邮件标题
    private String content; //邮件内容
    private String fileName; //文件名称
    private String address;  //文件地址

    public WarnMesssgeData() {
    }


    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public String getFileName() {
        return fileName;
    }

    public String getAddress() {
        return address;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
