package com.imagine.async;

import lombok.extern.slf4j.Slf4j;

/**
 * @author imagine
 * @date 2023/7/19/0019 - 17:56
 *
 * Thread.sleep 使当前执行的线程在指定的毫秒数内休眠(暂时停止执行)
 * thread.interrupt 打断调用该方法的线程对象,如果该线程是在sleep会抛出异常，
 *                  如果不是在睡眠，得调用Thread.currentThread().isInterrupted();获取打断标记
 * LockSupport.park();打断park线程,如果打断标记已经是 true, 则 park 会失效,可以使用 Thread.interrupted() 清除打断状态
 * Thread.yield 1.调用yield会让当前线程从Running进入Runnable就绪状态，然后调度执行其它线程
 *              2.具体地实现依赖于操作系统的任务调度器
 * thread.setPriority 设置优先级
 */
@Slf4j(topic = "c.CommonMethod")
public class CommonMethod {
    private static int count = 0;
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                log.debug("start sleep......");
                count = 10;
                //sleep 使当前执行的线程在指定的毫秒数内休眠(暂时停止执行)
                Thread.sleep(1000);
                log.debug("end sleep!");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        //启动线程
        thread.start();
        //等到thread这个线程对象的线程结束再执行使用了这个方法的那个线程

        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.debug("count：{}",count);

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //获取线程状态
        log.debug("t1 state：{}",thread.getState());
        //打断调用该方法的线程
//        thread.interrupt();
    }
}
