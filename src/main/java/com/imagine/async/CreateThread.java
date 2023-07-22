package com.imagine.async;

/**
 * @author imagine
 * @date 2023/7/19/0019 - 17:26
 */

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 *
 */
@Slf4j(topic = "c.Test1")
public class CreateThread {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Thread thread = new Thread(){
            @Override
            public void run() {
                log.debug("thread");
            }
        };
        thread.start();


        Runnable run = new Runnable() {
            @Override
            public void run() {
                log.debug("runnable");

            }
        };

        Thread runnable = new Thread(run);
        runnable.start();


        FutureTask<String> task = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "task";
            }
        });


        Thread taskThread = new Thread(task);

        taskThread.start();
        String str = task.get();
        log.debug(str);

    }
}
