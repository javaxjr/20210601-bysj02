package com.test;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

import java.util.concurrent.Future;

/**
 * @Async的使用：实现线程的作用
 *
 *
*/
public class AsyncTest {

    @Async("defaultAsyncExecutor")
    public static void  testAsyncVoid(){

        try {
            System.out.println("@Async注解实现线程");
            //让程序暂停100秒，相当于执行一个很耗时的任务
            System.out.println("异常执行打印字符串");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Async
    public static Future<String> testAsyncReturn() {

        System.out.println("Execute method asynchronously - " + Thread.currentThread().getName());

        try {
            Thread.sleep(5000);
            return new AsyncResult<String>("hello world!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }



}
