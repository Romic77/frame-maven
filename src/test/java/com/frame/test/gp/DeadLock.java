package com.frame.test.gp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Administrator
 * @CREATE 2017/7/15 22:19
 * http://ifeve.com/deadlock/
 * 死锁
 * 思路是创建两个字符串a和b，再创建两个线程A和B，
 * 让每个线程都用synchronized锁住字符串（A先锁a，再去锁b；B先锁b，再锁a），
 * 如果A锁住a，B锁住b，A就没办法锁住b，B也没办法锁住a，这时就陷入了死锁
 */
public class DeadLock {
    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(DeadLock.class);

    public static String obj1="obj1";
    public static String obj2="obj2";
    public static void main(String[] args) {
          Thread a=new Thread();
    }

    class Lock1 implements Runnable{

        public void run() {
            try {
                System.out.println("Lock1 is running ");
                while (true) {
                    synchronized (DeadLock.obj1) {
                        System.out.println();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

}

