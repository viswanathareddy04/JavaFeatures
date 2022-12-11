package com.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalExample {

    private static final ExecutorService executorService = Executors.newFixedThreadPool(10);
    private static final ThreadLocal<SimpleDateFormat>  simpleDateFormatThreadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            executorService.submit( () -> {
                String birthdate = getBirthdate(new Date());
                System.out.println(birthdate);
            });
            simpleDateFormatThreadLocal.remove();
            
        }
    }

    private static String getBirthdate(Date date) {
        final SimpleDateFormat df = simpleDateFormatThreadLocal.get();
        return df.format(date);

    }
}
