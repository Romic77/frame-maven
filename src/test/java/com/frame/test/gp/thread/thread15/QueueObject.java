package com.frame.test.gp.thread.thread15;

/**
 * @author Administrator
 * @CREATE 2017/7/23 18:01
 */
public class QueueObject {
    private boolean isNotified = false;

    public synchronized void doWait() throws InterruptedException {
        while (!isNotified) {
            this.wait();
        }
        this.isNotified = false;
    }

    public synchronized void doNotify() {
        this.isNotified = true;
        this.notify();
    }

    public boolean equals(Object o) {
        return this == o;
    }
}
