/*
 * Copyright (c) 2008-2016, GigaSpaces Technologies, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gigaspaces;

/**
 * Created by Barak Bar Orion
 * on 5/28/17.
 *
 * @since 12.1
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(3);
        Main.supply("Value1", queue);
        Main.supply("Value2", queue);

        while(true){
            String value = queue.get();
            System.out.println("value is " + value);
            Thread.sleep(1000);
        }


    }

    private static void supply(final String value1, final BlockingQueue<String> queue) {
        new Thread(){
            @Override
            public void run() {
                while(true) {
                    try {
                        queue.add(value1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
