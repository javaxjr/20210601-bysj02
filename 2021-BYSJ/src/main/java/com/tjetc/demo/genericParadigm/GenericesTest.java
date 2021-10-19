package com.tjetc.demo.genericParadigm;

import com.tjetc.domain.Admin;

import java.util.ArrayList;
import java.util.List;

/*
* generic paradigm:泛型
*
* 泛型也是可以限制你要使用的参数类型的，
* 比如下面的例子中，list集合中，我只想加入数字类型的数据，当然也是可以的，只要让类中的T继承Number就行了
*
* */
//public class GenericesTest<T extends Number> {
public class GenericesTest<T> {
    /*
    * 泛型方法
    * */
    private List<T> list  = new ArrayList<T>();

    public void add(T t){
        list.add(t);
    }

    public static void main(String[] args) {
        GenericesTest genericesTest = new GenericesTest();
        String t = "this is test";
        Integer t2 = 10000;
        genericesTest.add(t);  //上面已经限制了要使用的参数类型
        genericesTest.add(t2);
        System.out.println(genericesTest.list.get(0));
        System.out.println(genericesTest.list.get(1));

        /*try {
            Class<?> admin = Class.forName("Admin");
            Admin instance = (Admin) admin.newInstance();
            System.out.println("admin = " + admin);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }*/

    }

}
