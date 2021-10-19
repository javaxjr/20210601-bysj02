package com.tjetc.demo.消息队列;

import java.util.Scanner;

public class MQTest {
    public static void main(String[] args) {
        //获取消息队列的单例，并监听队列监听器
        PushBlockQueue.getInstance().start();
        //循环向队列写入数据
        /*
        * 生产者---生产消息----》入队列----监听器---通知消费者---》消费
        * */
        Scanner scanner = new Scanner(System.in);
        try {
            while (true){
                String content=scanner.nextLine();
                if (content.trim().equals("stop")){
                    System.exit(1);
                }
                PushBlockQueue.getInstance().put(content);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
