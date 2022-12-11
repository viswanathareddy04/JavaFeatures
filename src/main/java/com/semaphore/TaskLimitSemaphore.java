package com.semaphore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

// Throttle task submission
public class TaskLimitSemaphore {

    private final ExecutorService executor;
    private final Semaphore semaphore;

    public TaskLimitSemaphore(ExecutorService executor, int limit) {
        this.executor = executor;
        this.semaphore = new Semaphore(limit);
    }

    public <T> Future<T> submit(final Callable<T> task) throws InterruptedException {

        semaphore.acquire();
        System.out.println("semaphore.acquire()...");

        return executor.submit(() -> {
            try {
                return task.call();
            } finally {
                semaphore.release();
                System.out.println("semaphore.release()...");
            }
        });

    }

    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = Executors.newCachedThreadPool();

        // Only 2 tasks are able to run concurrently
        TaskLimitSemaphore obj = new TaskLimitSemaphore(executor, 2);

        for (int i = 0; i < 10; i++) {

            int finalI = i;
            obj.submit(() -> {
                System.out.println(getCurrentDateTime() + " : task:"+ finalI +"is running!");
                Thread.sleep(2000);
                System.out.println(getCurrentDateTime() + " : task:"+ finalI +" is done!");
                return finalI;
            });
        }

        executor.shutdown();
    }

    private static String getCurrentDateTime() {
        return sdf.format(new Date());
    }
}