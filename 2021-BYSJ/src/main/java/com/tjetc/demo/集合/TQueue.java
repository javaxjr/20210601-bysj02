package com.tjetc.demo.集合;

import com.tjetc.domain.Admin;

import java.util.*;

public class TQueue {
    public static void main(String[] args) {

        //队列----链表
        Queue queue = new LinkedList();

        Admin admin = new Admin();
        admin.setPassword(UUID.randomUUID().toString());
        queue.add(admin);
        queue.add(admin);
        queue.add(admin);

        Iterator iterator = queue.iterator();
        while (iterator.hasNext()){
            Admin next = (Admin) iterator.next();
            System.out.println("admin = " + next.getPassword());
        }

        queue.offer(admin);

        Admin o = (Admin) queue.element();

        System.out.println("o.getPassword() = " + o.getPassword());

        System.out.println("queue.poll() = " + ((Admin)queue.poll()).getPassword());



        Collections collections;

    }




}
