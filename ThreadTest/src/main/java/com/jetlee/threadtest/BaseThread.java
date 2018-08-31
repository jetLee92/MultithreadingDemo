package com.jetlee.threadtest;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

public class BaseThread {

    public static void main(String[] args) {
        thread();
        runnable();
        threadFactory();

        // Executor是超接口，ExecutorService是Executor的子接口,创建的线程都会返回ThreadPoolExecutor
        executorCache();
        executorFixed();
    }

    private static void thread() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                System.out.println("这是继承Thread的用法");
            }
        };
        thread.start();
    }

    private static void runnable() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("这是Runnable线程用法");
            }
        });
        thread.start();
    }

    /**
     * 线程工厂
     */
    private static void threadFactory() {
        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable);
            }
        };

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("这是ThreadFactory线程用法");
            }
        };

        Thread thread1 = threadFactory.newThread(runnable);
        thread1.start();
        Thread thread2 = threadFactory.newThread(runnable);
        thread2.start();
    }

    private static void executorCache() {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("这是缓存线程池newCachedThreadPool用法");
            }
        };

        // 创建一个有缓存的线程池，初始值为0。线程开始就创建一个，用完了一分钟了可以复用，一分钟后自动回收。
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(runnable);
        executorService.execute(runnable);
        executorService.execute(runnable);
    }

    private static void executorFixed() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("这是线程池newFixedThreadPool用法");
            }
        };
        // 手动创建限定大小的线程池，最后要手动的shutdown
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            executorService.execute(runnable);
        }
        executorService.shutdown();
    }


}
