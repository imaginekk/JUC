package com.imagine.async;


import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import java.io.IOException;

/**
 * @author imagine
 * @date 2023/7/22/0022 - 10:42
 */
@Slf4j(topic = "c.TestBiased")
public class TestBiased {
    public static void main(String[] args) throws IOException, InterruptedException {
        Dog d = new Dog();
        ClassLayout classLayout = ClassLayout.parseInstance(d);
        log.debug(classLayout.toPrintable());

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (d){
                    log.debug("hhh");
                    log.debug(classLayout.toPrintable());
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (d){
                    log.debug("hhh");
                    log.debug(classLayout.toPrintable());
                }
            }
        }).start();


    }
}
class Dog {}