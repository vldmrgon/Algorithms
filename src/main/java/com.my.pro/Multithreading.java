package com.my.pro;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

public class Multithreading {

    @AllArgsConstructor
    static class Racer implements Runnable {
        private String name;

        @SneakyThrows
        @Override
        public void run() {
            Thread.sleep(100);
            System.out.println(name);
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        Thread thread1 = new Thread(new Racer("1"));
        Thread thread2 = new Thread(new Racer("2"));
        Thread thread3 = new Thread(new Racer("3"));
        Thread thread4 = new Thread(new Racer("4"));
        Thread thread5 = new Thread(new Racer("5"));

        thread5.start();
        thread3.start();
        thread1.start();

        thread3.join();
        thread2.start();

        thread1.join();
        thread4.start();

        thread2.join();
        thread4.join();
        thread5.join();
    }
}