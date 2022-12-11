package com.multithreading;

public class MyThread  extends  Thread{
    @Override
    public void  run(){
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Thread: "+Thread.currentThread().getName());
        System.out.println("Thread: "+Thread.currentThread().threadId());
    }

}
