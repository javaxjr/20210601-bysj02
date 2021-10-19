package com.tjetc.demo.消息队列;

public class PushBlockQueueHandler implements Runnable {

    //消费对象
    private Object obj;

    public PushBlockQueueHandler(Object obj) {

        this.obj=obj;
    }

    //消费线程
    @Override
    public void run() {

        doBusiness();

    }

    //消费行为
    private void doBusiness() {

        //获取线程名
        System.out.println(Thread.currentThread().getName()+"-收到消息：" + obj);
    }
}
