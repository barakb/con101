package com.gigaspaces;

/**
 * Hello world!
 */
public class Deadlock {

    private final Object resource1 = new Object();
    private final Object resource2 = new Object();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello World!");
        final Deadlock deadlock = new Deadlock();
        new Thread(){
            @Override
            public void run() {
                while(true){
                    deadlock.goo();
                }
            }
        }.start();

        while(true){
            deadlock.foo();
        }

    }

    public void goo(){
        synchronized (resource1){
            synchronized (resource2){
                System.out.println("goo have both resources");
            }
        }
    }

    public void foo(){
        synchronized (resource1){
            synchronized (resource2){
                System.out.println("foo have both resources");
            }
        }
    }
}
