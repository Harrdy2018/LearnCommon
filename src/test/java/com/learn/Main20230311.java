package com.learn;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class MyTask implements Runnable {

    final String username;

    public MyTask(String username) {
        this.username = username;
    }

    @Override
    public void run() {
        try (UserContext userContext = new UserContext(this.username)) {
            new Task1().process();
            new Task2().process();
            new Task3().process();
        }
    }
}

class Task1 {
    public void process() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        System.out.printf("[%s] check user %s...\n", Thread.currentThread().getName(), UserContext.getCurrentUser());
    }
}

class Task2 {
    public void process() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        System.out.printf("[%s] %s registered ok.\n", Thread.currentThread().getName(), UserContext.getCurrentUser());
    }
}

class Task3 {
    public void process() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        System.out.printf("[%s] work of %s has done.\n", Thread.currentThread().getName(), UserContext.getCurrentUser());
    }
}



class UserContext implements AutoCloseable {
    /**
     * 实际上，可以把ThreadLocal看成一个全局Map<Thread, Object>：每个线程获取ThreadLocal变量时，总是使用Thread自身作为key：
     */
    private static final ThreadLocal<String> userThreadLocal = new ThreadLocal<>();

    public UserContext(String name) {
        userThreadLocal.set(name);
        System.out.printf("[%s] init user %s...\n", Thread.currentThread().getName(), UserContext.getCurrentUser());
    }

    public static String getCurrentUser() {
        return userThreadLocal.get();
    }

    /**
     * 最后，特别注意ThreadLocal一定要在finally中清除也可以实现AutoCloseable：
     * 这是因为当前线程执行完相关代码后，很可能会被重新放入线程池中，如果ThreadLocal没有被清除，该线程执行其他代码时，会把上一次的状态带进去。
     */
    @Override
    public void close() {
        System.out.printf("[%s] cleanup for user %s...\n", Thread.currentThread().getName(), UserContext.getCurrentUser());
        userThreadLocal.remove();
    }
}


public class Main20230311 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(3);
        String[] users = new String[] { "Bob", "Alice", "Tim", "Mike", "Lily", "Jack", "Bush" };
        for (String user : users) {
            es.submit(new MyTask(user));
        }

        es.shutdown();
        while (!es.awaitTermination(3, TimeUnit.SECONDS)) {
            System.out.println("线程池没有关闭");
        }

        System.out.println("线程池已经关闭");
    }
}
