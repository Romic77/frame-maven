package com.frame.test.gp.thread;/**
 * Created by Administrator on 2017/7/3.
 */

/**
 * @author Administrator
 * @CREATE 2017/7/3 23:06
 */
public class MyThreadDemo {

    //ThreadLocal对象的set()方法设置的值只对当前线程可见,通过ThreadLocal子类的实现，
    // 并覆写initialValue()方法，就可以为ThreadLocal对象指定一个初始化值
    private ThreadLocal threadLocal = new ThreadLocal<String>() {
        @Override protected String initialValue() {
            return "This is the initial value";
        }
    };

    private ThreadLocal<String > myThreadLocal1 = new ThreadLocal<String>();
    public String getValue(){
        myThreadLocal1.set("sb3");
        return myThreadLocal1.get();
    }



    public static void main(String[] args){
        System.out.println(new MyThreadDemo().getValue());
    }
}
