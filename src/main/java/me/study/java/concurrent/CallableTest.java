package me.study.java.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<String> helloFuture = executorService.submit(() -> {
            Thread.sleep(2000L);
            return "Callable";
        });

        System.out.println("Hello");
        System.out.println("before: " + helloFuture.isDone());
        System.out.println(helloFuture.get());
        System.out.println("after: " + helloFuture.isDone());

        executorService.shutdown();
    }
}
