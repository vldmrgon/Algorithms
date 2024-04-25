package com.my.pro;

import java.util.concurrent.*;
import java.util.List;

import lombok.SneakyThrows;

public class Async {

    public static final int DELAY_SERVICE_A_IN_SECOND = 1;
    public static final int DELAY_SERVICE_B_IN_SECOND = 2;
    public static final int DELAY_SERVICE_C_IN_SECOND = 3;

    private static final List<RunTask> runners = List.of(
            new SyncStartTasks(),
            new ExecutorStartTasks(),
            new CompletableFutureStartTasks()
    );

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        for (RunTask runner : runners) {
            runner.printResult();
        }





    }

    public interface RunTask {
        String getTypeRunning();

        void startTask() throws ExecutionException, InterruptedException;

        default void printResult() throws ExecutionException, InterruptedException {
            long start = System.nanoTime();
            startTask();
            long end = System.nanoTime();
            double executionTimeInSeconds = (end - start) / 1_000_000_000.0;
            System.out.println("Method: " + getTypeRunning() + " Execution time in seconds: " + executionTimeInSeconds);
        }
    }

    public static class CompletableFutureStartTasks implements RunTask {

        @Override
        public String getTypeRunning() {
            return "Completable Future";
        }

        @Override
        public void startTask() throws ExecutionException, InterruptedException {
//            CompletableFuture<String> future1 = CompletableFuture.supplyAsync(Async::requestToServiceA);
//            CompletableFuture<String> future2 = CompletableFuture.supplyAsync(Async::requestToServiceB);
//            CompletableFuture<String> future3 = CompletableFuture.supplyAsync(Async::requestToServiceC);
//
//            CompletableFuture<Void> allResults = CompletableFuture.allOf(future1, future2, future3);
//            allResults.get();
            CompletableFuture<String> order = CompletableFuture.supplyAsync(Async::requestToServiceA);
            CompletableFuture<String> rating = CompletableFuture.supplyAsync(Async::requestToServiceB);

            CompletableFuture<String> completableFuture = order.thenCombine(rating, (x, y) -> {
                return x + " " + y;
            });
            String s = completableFuture.get();
        }
    }

    public static class ExecutorStartTasks implements RunTask {

        @Override
        public String getTypeRunning() {
            return "Start task via Executors";
        }

        @SneakyThrows
        @Override
        public void startTask() {
            ExecutorService executorService = Executors.newFixedThreadPool(3);

            Future<String> future1 = executorService.submit(Async::requestToServiceA);
            Future<String> future2 = executorService.submit(Async::requestToServiceB);
            Future<String> future3 = executorService.submit(Async::requestToServiceC);

            String s1 = future1.get();
            String s2 = future2.get();
            String s3 = future3.get();
            executorService.shutdown();
        }
    }

    public static class SyncStartTasks implements RunTask {

        @Override
        public String getTypeRunning() {
            return "Synchronous starting tacks";
        }

        @Override
        public void startTask() {
            String s1 = requestToServiceA();
            String s2 = requestToServiceB();
            String s3 = requestToServiceC();
        }
    }

    public static String requestToServiceA() {
        try {
            TimeUnit.SECONDS.sleep(DELAY_SERVICE_A_IN_SECOND);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName());
        return "Response from service A";
    }

    public static String requestToServiceB() {
        try {
            TimeUnit.SECONDS.sleep(DELAY_SERVICE_B_IN_SECOND);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName());
        return "Response from service B";
    }

    public static String requestToServiceC() {
        try {
            TimeUnit.SECONDS.sleep(DELAY_SERVICE_C_IN_SECOND);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName());
        return "Response from service C";
    }
}