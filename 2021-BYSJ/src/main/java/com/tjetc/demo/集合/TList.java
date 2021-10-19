package com.tjetc.demo.集合;

import com.tjetc.domain.Admin;

import java.util.*;

public class TList {

    public static void main(String[] args) {

        Admin admin = new Admin();
        admin.setId(1);
        admin.setPassword(UUID.randomUUID().toString());

        List<Admin> list = Arrays.asList(admin);

        List arrList = new ArrayList(list);
        arrList.add(admin);

        ListIterator<Admin> iterator = list.listIterator();
        while (iterator.hasNext()){
            Admin next = iterator.next();
            System.out.println("next = " + next.getPassword());
        }

        Iterator iterator1 = arrList.iterator();
        while (iterator1.hasNext()){
            Admin admin1 = (Admin)(iterator1.next());

            System.out.println("iterator1 = " + admin1.getPassword());
        }

        MatchType[] values = MatchType.values();
        for (MatchType value : values) {
            System.out.println("value = " + value);
        }
        MatchType eq = MatchType.valueOf("EQ");
        System.out.println("eq = " + eq);

    }
}
