package com.frame.test.gp.thread;

import org.junit.Ignore;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2017/6/27.
 */
@Ignore
public class ThreadTest {
    /**
     * ++i，先使用变量，再进行变量自增1
     * i++，先进行变量自增1，再使用变量
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        int i=0;
        int a =(i++);

        int j=0;
        int b =(++j);
        System.out.println(a+"："+b);

        char c ='a';
        System.out.println("a"+c+c);
    }
}

