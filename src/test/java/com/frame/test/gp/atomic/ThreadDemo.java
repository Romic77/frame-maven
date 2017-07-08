package com.frame.test.gp.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Administrator
 * @CREATE 2017/7/3 10:30
 */
public class ThreadDemo implements Runnable{
    //定义一个AtomicInteger的初始值为0的变量atoint
    private AtomicInteger atoint=new AtomicInteger(0);

    /**
     *@author: chenqi
     *@date: 2017-07-03 11:41:09
     *@description:
    */
    public void run() {
        System.out.println("start atoint:"+atoint.get());
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getNum());
    }


    public int getNum(){
        //每次返回的为当前值的下一个值
        return atoint.incrementAndGet();
    }

}
