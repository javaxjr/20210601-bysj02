package com.tjetc.test;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;

public class ShiWu {
    public static void main(String[] args) {

        Class<SqlSessionFactory> sessionFactory = SqlSessionFactory.class;

        try {
            SqlSessionFactory factory = sessionFactory.newInstance();

            Configuration configuration = factory.getConfiguration();



        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        double d=3.2;
        int i = (int) d;
        System.out.println("i = " + i);

        d=Math.ceil(d);
        System.out.println("d = " + d);

        d=Math.abs(d);
        System.out.println("d = " + d);

    }




}
