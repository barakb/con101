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

import java.util.ArrayList;

/**
 * Created by Barak Bar Orion
 * on 5/28/17.
 *
 * @since 12.1
 */
public class ArrayBlockingQueue<T> implements BlockingQueue<T> {
    private final int max;
    private ArrayList<T> queue;

    public ArrayBlockingQueue(final int max) {
        this.max = max;
        queue = new ArrayList<T>();
    }

    public synchronized T get() throws InterruptedException {
        try {
            while (queue.isEmpty()) {
                wait();
            }
            System.out.println("Queue removing while size is " + queue.size());
            return queue.remove(0);
        }finally {
            notifyAll();
        }
    }

    public synchronized void add(T t) throws InterruptedException {
        while(isFull()){
            wait();
        }
        queue.add(t);
        notifyAll();
    }

    private boolean isFull(){
        return max <= queue.size();
    }
}
