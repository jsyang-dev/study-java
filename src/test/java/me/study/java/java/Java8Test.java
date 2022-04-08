package me.study.java.java;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Java 8 기능")
public class Java8Test {

    @Test
    @DisplayName("Runnable 테스트")
    void runnable() {
        Runnable runnable = () -> {
            String result = "runnable: " + LocalTime.now();
            System.out.println(result);
        };
        Thread thread1 = new Thread(runnable);
        thread1.start();
    }

    @Test
    @DisplayName("Callable 테스트")
    void callable() throws ExecutionException, InterruptedException {
        Callable<String> callable1 = () -> "callable1: " + LocalTime.now();
        FutureTask<String> futureTask = new FutureTask<>(callable1);
        Thread thread2 = new Thread(futureTask);
        thread2.start();
        System.out.println(futureTask.get());

        Callable<String> callable2 = () -> "callable2: " + LocalTime.now();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(callable2);
        System.out.println(future.get());
    }

    @Test
    @DisplayName("ExecutorService 테스트")
    void executorService() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Callable<String> callable1 = () -> {
            System.out.println("callable1 start");
            Thread.sleep(1000);
            return "callable1 result";
        };
        Callable<String> callable2 = () -> {
            System.out.println("callable2 start");
            Thread.sleep(1000);
            return "callable2 result";
        };
        Callable<String> callable3 = () -> {
            System.out.println("callable3 start");
            Thread.sleep(1000);
            return "callable3 result";
        };

        long beforeTime = System.currentTimeMillis();

        Future<String> future1 = executorService.submit(callable1);
        Future<String> future2 = executorService.submit(callable2);
        Future<String> future3 = executorService.submit(callable3);

        System.out.println(future1.get());
        System.out.println(future2.get());
        System.out.println(future3.get());

        long afterTime = System.currentTimeMillis();
        long diffTime = afterTime - beforeTime;
        System.out.println("실행 시간: " + diffTime + "ms");
    }

    @Test
    @DisplayName("Stream API 테스트")
    void stream() {
        int sum1 = Stream.iterate(1, n -> n + 1)
                .limit(5)
                .skip(2)
                .peek(System.out::println)
                .mapToInt(it -> it)
                .sum();

        assertThat(sum1).isEqualTo(12);

        Integer sum2 = Stream.of(1, 2, 3)
                .reduce(0, Integer::sum);

        assertThat(sum2).isEqualTo(6);

        int max = new Random().ints(5)
                .filter(it -> it > 0)
                .peek(System.out::println)
                .max()
                .orElse(0);

        assertThat(max).isGreaterThan(0);

        List<String> collect = Pattern.compile(",")
                .splitAsStream("a,a,b,c,d")
                .distinct()
                .peek(System.out::println)
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        assertThat(collect).containsExactly("A", "B", "C", "D");

        List<List<Integer>> lists = List.of(List.of(5, 1, 3), List.of(4, 2));
        String joining = lists.stream()
                .flatMap(Collection::stream)
                .sorted()
                .peek(System.out::println)
                .map(String::valueOf)
                .collect(Collectors.joining(", ", "<", ">"));

        assertThat(joining).isEqualTo("<1, 2, 3, 4, 5>");
    }
}
