package com.imagine.async;

/**
 * @author imagine
 * @date 2023/7/23/0023 - 8:46
 */
public class TestVisibleness {
    static boolean run = true;
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(()->{
            while(run){
// ....
            }
        });
        t.start();
        Thread.sleep(1000);
        run = false; // 线程t不会如预想的停下来
    }
}
