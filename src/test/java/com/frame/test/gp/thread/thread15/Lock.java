package com.frame.test.gp.thread.thread15;

/**
 * @author Administrator
 * @CREATE 2017/7/23 16:19
 */
public class Lock {
    private boolean isLocked = false;

    private Thread lockingThread = null;

    public synchronized void lock() throws InterruptedException {
        while (isLocked) {
            wait();
        }
        isLocked = true;
        lockingThread = Thread.currentThread();
    }

    public synchronized void unlock() {
        if (this.lockingThread != Thread.currentThread()) {
            throw new IllegalMonitorStateException("call thread has not locked this lock");
        }
        isLocked = false;
        lockingThread = null;
        notify();
    }
}
