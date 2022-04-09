package me.study.java.java;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@DisplayName("Async 테스트")
public class AsyncTest {

    @Test
    @DisplayName("Http Client Async 테스트")
    void httpClientAsync() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Callable<String> callable1 = () -> {
            System.out.println("callable1 start");
            HttpResponse<String> httpResponse = getHttpClient().send(getHttpRequest(), HttpResponse.BodyHandlers.ofString());
            return "callable1 end: " + httpResponse.body();
        };
        Callable<String> callable2 = () -> {
            System.out.println("callable2 start");
            HttpResponse<String> httpResponse = getHttpClient().send(getHttpRequest(), HttpResponse.BodyHandlers.ofString());
            return "callable2 end: " + httpResponse.body();
        };
        Callable<String> callable3 = () -> {
            System.out.println("callable3 start");
            HttpResponse<String> httpResponse = getHttpClient().send(getHttpRequest(), HttpResponse.BodyHandlers.ofString());
            return "callable3 end: " + httpResponse.body();
        };
        Callable<String> callable4 = () -> {
            System.out.println("callable4 start");
            HttpResponse<String> httpResponse = getHttpClient().send(getHttpRequest(), HttpResponse.BodyHandlers.ofString());
            return "callable4 end: " + httpResponse.body();
        };
        Callable<String> callable5 = () -> {
            System.out.println("callable5 start");
            HttpResponse<String> httpResponse = getHttpClient().send(getHttpRequest(), HttpResponse.BodyHandlers.ofString());
            return "callable5 end: " + httpResponse.body();
        };

        long beforeTime = System.currentTimeMillis();

        Future<String> future1 = executorService.submit(callable1);
        Future<String> future2 = executorService.submit(callable2);
        Future<String> future3 = executorService.submit(callable3);
        Future<String> future4 = executorService.submit(callable4);
        Future<String> future5 = executorService.submit(callable5);

        System.out.println(future1.get());
        System.out.println(future2.get());
        System.out.println(future3.get());
        System.out.println(future4.get());
        System.out.println(future5.get());

        long afterTime = System.currentTimeMillis();
        long diffTime = afterTime - beforeTime;
        System.out.println("실행 시간: " + diffTime + "ms");
    }

    private HttpClient getHttpClient() {
        return HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(20))
                .build();
    }

    private HttpRequest getHttpRequest() {
        return HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8081/hello"))
                .build();
    }
}
