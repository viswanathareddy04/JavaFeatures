package com.locks;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class RentrantLockExample {
    private final ReentrantLock reentrantLock = new ReentrantLock();

    private final Semaphore semaphore = new Semaphore(3);
    private static final ExecutorService executorService = Executors.newFixedThreadPool(10);
    private int count =0;

    public int getCount(){
        reentrantLock.lock();
        try{
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " gets Count: " + count);
            Thread.sleep(1000);
            return count++;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) {
        final RentrantLockExample rentrantLockExample = new RentrantLockExample();

        for (int i = 0; i < 10; i++) {
            executorService.submit( () -> {
               rentrantLockExample.getCount();

            });
        }
    }
}
