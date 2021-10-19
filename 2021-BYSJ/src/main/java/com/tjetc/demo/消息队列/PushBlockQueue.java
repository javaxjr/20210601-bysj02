package com.tjetc.demo.消息队列;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class PushBlockQueue extends LinkedBlockingQueue<Object> {

    private static ExecutorService es = Executors.newFixedThreadPool(10);

    private static PushBlockQueue pbg = new PushBlockQueue();

    private boolean flag = false;

    private PushBlockQueue(){}

    public static PushBlockQueue getInstance(){
        return pbg;
    }

    /**
     * 队列监听启动
     */
    public void start(){
        if (!this.flag){
            flag=true;
        }else {
            throw new IllegalArgumentException("队列已启动，不可重负启动");
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
               while (flag){
                   try {
                       //从队列中取消息
                       Object obj = take();
                       //线程池派出线程来消费取出的消息
                       es.execute(new PushBlockQueueHandler(obj));
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }

               }
            }
        }).start();
    }

    /*
    * 停止队列监听
    * */
    public void stop(){
        this.flag=false;
    }


}
