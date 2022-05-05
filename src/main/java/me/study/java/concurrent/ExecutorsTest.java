package me.study.java.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorsTest {

    public static void main(String[] args) {
        final ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> System.out.println("Thread " + Thread.currentThread().getName()));
        executorService.shutdown();

        final ExecutorService executorService2 = Executors.newFixedThreadPool(2);
        executorService2.submit(getRunnable("Hello"));
        executorService2.submit(getRunnable("The"));
        executorService2.submit(getRunnable("Java"));
        executorService2.submit(getRunnable("Thread"));
        executorService2.shutdown();

        final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.schedule(getRunnable("Hello"), 5, TimeUnit.SECONDS);
        scheduledExecutorService.shutdown();
    }

    private static Runnable getRunnable(String message) {
        return () -> System.out.println(message + " " + Thread.currentThread().getName());
    }
}
