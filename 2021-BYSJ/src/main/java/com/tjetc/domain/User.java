package com.tjetc.domain;

/*
* 用户表
* */
public class User extends People{
    private String grade;//用户等级
    private String payment;//支付密码
    private String address;//住址

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "grade='" + grade + '\'' +
                ", payment='" + payment + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
