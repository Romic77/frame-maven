package com.frame.test.gp.thread;/**
 * Created by Administrator on 2017/7/3.
 */

/**
 * @author Administrator
 * @CREATE 2017/7/3 23:22
 */
public class ThreadLocalExample {
    public static class MyRunnable implements Runnable{
        private ThreadLocal<Integer> threadLocal=new ThreadLocal<Integer>();

        public void run() {
            threadLocal.set((int)(Math.random()*100D));
            try {
                Thread.sleep(2000);
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println(threadLocal.get());
        }
    }
    public static void main(String[] args) throws InterruptedException {
        MyRunnable sharedRunnableInstance=new MyRunnable();

        Thread thread1=new Thread(sharedRunnableInstance);
        Thread thread2=new Thread(sharedRunnableInstance);
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
