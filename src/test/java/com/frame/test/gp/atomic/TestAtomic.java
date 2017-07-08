package com.frame.test.gp.atomic;/**
 * Created by Administrator on 2017/7/3.
 */

/**
 * @author Administrator
 * @CREATE 2017/7/3 11:47
 */
public class TestAtomic {
    public static void main(String[] args){
        ThreadDemo td=new ThreadDemo();
        //开启二十个线程完成自加操作
        for (int i = 0; i < 20; i++) {
            new Thread(td).start();
        }
    }
  /**  比如 对于
   *  int num=0;
   *  i=num++;这个操作
   *  在进行num++这个操作的时候，先从主存中获取num的值，然后再进++操作
   *  很明显这是两个步骤，在获取num的值之后，另一个线程可能已经更改了num的值，这时候加的时候明显有问题
   *  CAS算法如下：
            *  获取主存值 V=0
            *  执行之后，在查看一个主存的值 此时如果还是 A=0(没有线程修改)
            *  根据num++的操作，此时B应该更新到值为0+1=1
            *  V=0
            *  A=0
            *  B=1
            *  因为V==A 此时把B的值刷新到主存中V=1;
   *  ************************************************
           *  如果在一个线程读取之后，另一个线程修改了值，即A=1
            *  V=0
            *  A=1
            *  B=1
            *  V!=A,则不会把B的值刷新到主存中
   */
}
