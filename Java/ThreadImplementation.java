package Java;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyThread extends Thread {
    public String name;
    public Counter counter;

    MyThread(String name, Counter counter) {
        this.name = name;
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            counter.increment();
            System.out.println(counter.getCount() + " : " + name);
        }
    }
}

class Counter {
    int count;
    Lock readLock;
    Lock writeLock;

    public Counter() {
        this.count = 0;
        readLock = new ReentrantReadWriteLock().readLock();
        writeLock = new ReentrantReadWriteLock().writeLock();
    }

    public void increment() {
        if (writeLock.tryLock()) {
            try {
                count++;
            } finally {
                writeLock.unlock();
            }
        } else {
            System.out.println("Unable to increment");
        }
    }

    public int getCount() {
        if (readLock.tryLock()) {
            try {
                return count;
            } finally {
                readLock.unlock();
            }
        } else {
            System.out.println("Unable to get count");
            return 0;
        }
    }
}

public class ThreadImplementation {
    public static void main(String[] args) {
        Counter counter = new Counter();
        MyThread t2 = new MyThread("Thread2", counter);
        MyThread t1 = new MyThread("Thread1", counter);
        t1.start();
        t2.start();
    }
}
