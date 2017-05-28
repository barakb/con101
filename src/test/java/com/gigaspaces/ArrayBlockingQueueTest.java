package com.gigaspaces;

import static org.junit.Assert.*;

/**
 * Created by Barak Bar Orion
 * on 5/28/17.
 */
public class ArrayBlockingQueueTest {

    @org.junit.Test(timeout = 3000)
    public void test() throws Exception {
        final BlockingQueue<Integer>  queue = new ArrayBlockingQueue<Integer>(10);
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    queue.add(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        int value = queue.get();
        assertEquals(10, value);


    }


}