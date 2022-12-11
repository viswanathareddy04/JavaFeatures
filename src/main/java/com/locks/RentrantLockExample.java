package com.locks;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class RentrantLockExample {
    private final ReentrantLock reentrantLock = new ReentrantLock();

    private static final ExecutorService executorService = Executors.newFixedThreadPool(20);
    private final int count =0;

    public void getCount(){
        reentrantLock.lock();
        try{
            System.out.println("******************************************************");
            System.out.println("getHoldCount: " + reentrantLock.getHoldCount());
            System.out.println("getQueueLength: " + reentrantLock.getQueueLength());
            System.out.println("isLocked: " + reentrantLock.isLocked());
            System.out.println("tryLock: "  +reentrantLock.tryLock());
            Thread.sleep(1000);
            System.out.println("******************************************************");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) {
        final RentrantLockExample rentrantLockExample = new RentrantLockExample();

        for (int i = 0; i < 100; i++) {
            executorService.submit( () -> {
               rentrantLockExample.getCount();

            });
        }
    }
}
