package com.tjetc.demo.集合;

import com.tjetc.domain.Admin;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class TCollection {
    public static void main(String[] args) {
        Collection collection = new ArrayDeque();

        Admin admin = new Admin();
        admin.setId(1);
        admin.setPassword("12234887");
        collection.add(admin);
        collection.add(admin);
        collection.add(admin);
        collection.add(admin);
        collection.add(admin);
        collection.add(admin);
        collection.add(admin);
        collection.add(admin);
        collection.add(admin);
        collection.add(admin);

        Iterator iterator = collection.iterator();

        while (iterator.hasNext()){
            Admin next = (Admin) iterator.next();
            System.out.println("next = " + next.toString());
        }
        if (iterator.hasNext()){

        }

    }


}
