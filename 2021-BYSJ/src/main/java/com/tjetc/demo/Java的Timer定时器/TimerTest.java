package com.tjetc.demo.Java的Timer定时器;

import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {
    static String str="HH:mm:ss";
    static SimpleDateFormat dateFormat=new SimpleDateFormat(str);

    public static void main(String[] args) {
        Timer timer=new Timer();
        String now1 = dateFormat.format(System.currentTimeMillis());
        System.out.println(now1);
        //延迟3秒后执行
        timer.schedule(new MyTask(),3000);
    }
}

class MyTask extends TimerTask{

    @Override
    public void run() {

        System.out.println("该起床了");
        String now2 = TimerTest.dateFormat.format(System.currentTimeMillis());
        System.out.println(now2);
    }
}

