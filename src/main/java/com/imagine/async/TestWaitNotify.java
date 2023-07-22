package com.imagine.async;

import lombok.extern.slf4j.Slf4j;

/**
 * @author imagine
 * @date 2023/7/22/0022 - 15:57
 * obj.wait() 让进入 object 监视器的线程到 waitSet 等待
 * obj.notify() 在 object 上正在 waitSet 等待的线程中挑一个唤醒
 * obj.notifyAll() 让 object 上正在 waitSet 等待的线程全部唤醒
 *
 * wait() 方法会释放对象的锁，进入 WaitSet 等待区，从而让其他线程就机会获取对象的锁。无限制等待，直到notify 为止
 *
 * wait(long n) 有时限的等待, 到 n 毫秒后结束等待，或是被 notify
 *
 *
 * 1. sleep 是 Thread 方法，而 wait 是 Object 的方法
 * 2. sleep 不需要强制和 synchronized 配合使用，但 wait 需要和 synchronized 一起用
 * 3. sleep 在睡眠的同时，不会释放对象锁的，但 wait 在等待的时候会释放对象锁
 * 4. 它们状态 TIMED_WAITING
 */
@Slf4j(topic = "c.TestWaitNotify")
public class TestWaitNotify {
    final static Object obj = new Object();
    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (obj) {
                log.debug("执行....");
                try {
                    obj.wait(); // 让线程在obj上一直等待下去
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("其它代码....");
            }
        }).start();
        new Thread(() -> {
            synchronized (obj) {
                log.debug("执行....");
                try {
                    obj.wait(); // 让线程在obj上一直等待下去
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("其它代码....");
            }
        }).start();
// 主线程两秒后执行
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.debug("唤醒 obj 上其它线程");
        synchronized (obj) {
            obj.notify(); // 唤醒obj上一个线程
// obj.notifyAll(); // 唤醒obj上所有等待线程
        }
    }
}
