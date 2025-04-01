package Multithreading.CCMultiThreading.Locks;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

public class Main {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Semaphore semaphoreLock =new Semaphore(2);
        ReadWriteLock readWriteLock= new ReentrantReadWriteLock();
        StampedLock stampedLock = new StampedLock();

        ReentrantLockExample r1 = new ReentrantLockExample();
        ReentrantLockExample r2 = new ReentrantLockExample();

        SemaphoreLockExample s1 = new SemaphoreLockExample();
        SemaphoreLockExample s2 = new SemaphoreLockExample();

        ReadWriteLockExample rr1= new ReadWriteLockExample();
        ReadWriteLockExample rr2= new ReadWriteLockExample();

        StampedLockExample ss1= new StampedLockExample();
        StampedLockExample ss2 = new StampedLockExample();


        Thread t1 = new Thread( () ->{
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            ss1.consumer(stampedLock);
        });

        Thread t2 = new Thread( () ->{
            ss2.consumer2(stampedLock);
        });

//        Thread t3 = new Thread( () ->{
//            rr1.consume(readWriteLock);
//        });

        t1.start();
        t2.start();
       // t3.start();

    }
}
