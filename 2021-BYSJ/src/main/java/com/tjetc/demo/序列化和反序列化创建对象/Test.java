package com.tjetc.demo.序列化和反序列化创建对象;

import java.io.*;
import java.util.UUID;

public class Test {
    public static void main(String[] args) {
        User user = new User();
        user.setId(UUID.randomUUID().toString().replaceAll("_","-"));
        user.setUsername("dfdfd");
        user.setPassword("454545");

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:/upload/user.txt"));
            //创建一个对象输出流
            oos.writeObject(user.toString());

            oos.close();

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:/upload/user.txt"));
            Object object = ois.readObject();

            User user1 = (User) object;
            System.out.println("user1 = " + user1);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
