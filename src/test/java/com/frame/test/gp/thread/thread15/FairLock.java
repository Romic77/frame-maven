package com.frame.test.gp.thread.thread15;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @CREATE 2017/7/23 17:52
 * 公平锁
 */
public class FairLock {
    private boolean isLocked = false;
    private Thread lockingThread = null;
    private List<QueueObject> waiteThreads = new ArrayList<QueueObject>();

    private void lock() throws InterruptedException {
        QueueObject queueObject = new QueueObject();
        boolean isLockedForThisThread = true;
        synchronized (this) {
            waiteThreads.add(queueObject);
        }
        while (isLockedForThisThread) {
            synchronized (this) {
                isLockedForThisThread = isLocked || waiteThreads.get(0) != queueObject;
                isLocked = true;
                waiteThreads.remove(queueObject);
                lockingThread = Thread.currentThread();
                return;
            }
        }
        try {
            queueObject.doWait();
        } catch (InterruptedException e) {
            synchronized (this) {
                waiteThreads.remove(queueObject);
                throw e;
            }
        }
    }

    public synchronized void unLock() {
        if (this.lockingThread != Thread.currentThread()) {
            throw new IllegalMonitorStateException("Calling thread has not locked this lock");
        }
        isLocked = false;
        lockingThread = null;
        if (waiteThreads.size() > 0) {
            waiteThreads.get(0).doNotify();
        }
    }

}
