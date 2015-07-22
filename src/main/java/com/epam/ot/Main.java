package com.epam.ot;

import org.apache.log4j.Logger;

import java.io.InputStream;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static final Logger logger = Logger.getLogger(Main.class);
    private static volatile int counterInt;
    private static volatile AtomicInteger counterAtomicInt = new AtomicInteger(0);
    private static volatile int count = 10000;
    private static volatile AtomicInteger decrementCounter = new AtomicInteger(0);

    public static void increment() {
        counterInt++;
        counterAtomicInt.incrementAndGet();
    }

    public static void decrement() {
        count--;
    }

    public static void main(String[] args) throws Exception {
        Runnable runnable = () -> {
            //TODO - try make there gunDao.findById()
            //TODO - change charset to UTF-8
            for (int i = 0; i < 1000; i++) {
                increment();
            }
        };

        Runnable test = () -> {
            while (count > 0) {
                --count;
                decrementCounter.incrementAndGet();
            }
        };
        Thread[] threads = new Thread[15];

        for (int i = 0; i < 10; i++) threads[i] = new Thread(runnable);
        for (int i = 10; i < 15; i++) threads[i] = new Thread(test);
        for (int i = 0; i < 15; i++) threads[i].start();
        for (int i = 0; i < 15; i++) threads[i].join();

        System.out.println("just integer: " + counterInt);
        System.out.println("atomic integer: " + counterAtomicInt);
        //it's just for info
        logger.debug("missed increments: " + (counterAtomicInt.get() - counterInt) + " from " + counterAtomicInt);

        System.out.println("\ndecrement result: " + decrementCounter + " count = " + count);
    }
}