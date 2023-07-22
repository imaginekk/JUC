package com.imagine.async;

import lombok.extern.slf4j.Slf4j;

/**
 * @author imagine
 * @date 2023/7/20/0020 - 16:13
 */
@Slf4j(topic = "c.Share")
public class Share {
    static int counter = 0;
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                counter++;
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                counter--;
            }
        }, "t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.debug("{}",counter);
    }
}
