package com.learn;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * ExecutorService 的关闭
 *
 * shutdown 方法：平滑的关闭 ExecutorService，
 * 当此方法被调用时，ExecutorService 停止接收新的任务并且等待已经提交的任务（包含提交正在执行和提交未执行）执行完成。当所有提交任务执行完毕，线程池即被关闭。
 *
 * awaitTermination 方法：接收 timeout 和 TimeUnit 两个参数，用于设定超时时间及单位。
 * 当等待超过设定时间时，会监测 ExecutorService 是否已经关闭，若关闭则返回 true，否则返回 false。一般情况下会和 shutdown 方法组合使用。
 *
 */

// 普通任务处理类
class Task implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ">普通任务");
    }
}

// 长时间任务处理类
class LongTask implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ">长时间任务");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
        }
    }
}

public class Main20230310 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(4);
        service.execute(new Task());
        service.execute(new Task());
        service.execute(new LongTask());
        service.execute(new Task());

        service.shutdown();

        while (!service.awaitTermination(1, TimeUnit.SECONDS)) {
            System.out.println("线程池没有关闭");
        }

        System.out.println("线程池已经关闭");
    }
}
