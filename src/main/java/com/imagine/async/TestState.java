package com.imagine.async;

import lombok.extern.slf4j.Slf4j;

/**
 * @author imagine
 * @date 2023/7/20/0020 - 14:35
 */
@Slf4j(topic = "c.TestState")
public class TestState {
    public static void main(String[] args) {
        //new
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                log.debug("running");
            }
        };
        //runnable
        Thread t2 = new Thread("t2") {
            @Override
            public void run() {
                while (true){

                }
            }
        };
        t2.start();
        //terminated
        Thread t3 = new Thread("t3") {
            @Override
            public void run() {
                log.debug("running");
            }
        };
        t3.start();

        //获取锁成功 进入sleep->time-waiting
        Thread t4 = new Thread("t4") {
            @Override
            public void run() {
                synchronized (TestState.class){
                    try {
                        Thread.sleep(10000000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };

        t4.start();

        //等待t2执行完,waiting
        Thread t5 = new Thread("t5") {
            @Override
            public void run() {
                try {
                    t2.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        t5.start();

        //获取锁失败,blocked
        Thread t6 = new Thread("t6") {
            @Override
            public void run() {
                synchronized (TestState.class){
                    try {
                        Thread.sleep(10000000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };

        t6.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        log.debug("t1 state {}",t1.getState());
        log.debug("t2 state {}",t2.getState());
        log.debug("t3 state {}",t3.getState());
        log.debug("t4 state {}",t4.getState());
        log.debug("t5 state {}",t5.getState());
        log.debug("t6 state {}",t6.getState());



    }
}
