package com.tjetc;

import javax.xml.crypto.Data;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TestDateTimer {
    public static  int time = 30*60;
    public static Calendar c;
    public static long entTime;
    public static Date date;
    public static long startTime;
    public static long midTime;


    public static void main(String[] args) {
        c = Calendar.getInstance();
        c.set(2017,4,17,0,0,0);//月份:1-12月
        // c.set(Calendar.YEAR, 2017);

        // c.set(Calendar.MONTH, 4);

        // c.set(Calendar.DAY_OF_MONTH, 17);

        // c.set(Calendar.HOUR_OF_DAY, 0);

        // c.set(Calendar.MINUTE, 0);

        // c.set(Calendar.SECOND, 0);

        entTime=c.getTimeInMillis();
        date=new Date();
        startTime = date.getTime();
        midTime = (entTime-startTime)/1000;

        //time3();
        time1();
        //time2();
    }

    //方式三
    private static void time3(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                midTime--;
                long hh=midTime/60/60%60;
                long mm = midTime/60/60;
                long ss = midTime/60;
                System.out.println("还剩"+hh+"小时"+mm+"分钟"+ss+"秒");
            }
        },0,1000);
    }

    //方式2
    private static void time2() {
        while (midTime > 0) {
            midTime--;

            long hh = midTime / 60 / 60 % 60;

            long mm = midTime / 60 % 60;

            long ss = midTime % 60;

            System.out.println("还剩" + hh + "小时" + mm + "分钟" + ss + "秒");

            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();

            }

        }
    }

    /**

     * 方式一： 给定时长倒计时

     */

    private static void time1() {
        while (time > 0) {
            time--;

            try {
                Thread.sleep(1000);

                int hh = time / 60 / 60 % 60;

                int mm = time / 60 % 60;

                int ss = time % 60;

                System.out.println("还剩" + hh + ":" + mm + ":" + ss);

            } catch (InterruptedException e) {
                e.printStackTrace();

            }

        }

    }

}

