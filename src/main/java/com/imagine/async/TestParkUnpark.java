package com.imagine.async;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * @author imagine
 * @date 2023/7/22/0022 - 17:04
 */
@Slf4j(topic = "c.TestParkUnpark")
public class TestParkUnpark {
    public static void main(String[] args) throws InterruptedException {
//        先 park 再 unpark
//        Thread t1 = new Thread(() -> {
//            log.debug("start...");
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            log.debug("park...");
//            LockSupport.park();
//            log.debug("resume...");
//        },"t1");
//        t1.start();
//        Thread.sleep(2000);
//        log.debug("unpark...");
//        LockSupport.unpark(t1);

//        18:42:52.585 c.TestParkUnpark [t1] - start...
//        18:42:53.589 c.TestParkUnpark [t1] - park...
//        18:42:54.583 c.TestParkUnpark [main] - unpark...
//        18:42:54.583 c.TestParkUnpark [t1] - resume...
//**********************************************************************************************************************
        //先 unpark 再 park
        Thread t1 = new Thread(() -> {
            log.debug("start...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.debug("park...");
            LockSupport.park();
            log.debug("resume...");
        }, "t1");
        t1.start();
        Thread.sleep(1000);
        log.debug("unpark...");
        LockSupport.unpark(t1);


    }
}
