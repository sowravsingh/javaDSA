package Multithreading.CCMultiThreading.Locks;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReadWriteLock;

public class ReadWriteLockExample {

    int a =20;
    // static Semaphore lock= new Semaphore(2);

    public void producer(ReadWriteLock lock){
        System.out.println(" came to produce data ");

        try {
            lock.writeLock().lock();
            System.out.println(" acquired a write lock for thread "+Thread.currentThread().getName()+" and sleeping for 20 secs");
            Thread.sleep(20000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            lock.writeLock().unlock();
            System.out.println(" removing write lock from thread "+Thread.currentThread().getName()+" so others can acquire lock");
        }

    }


    public void consume(ReadWriteLock lock){
        System.out.println(" came to consume data ");

        try {
            lock.readLock().lock();
            System.out.println(" acquired a read lock for thread "+Thread.currentThread().getName()+" and sleeping for 20 secs");
            Thread.sleep(20000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            lock.readLock().unlock();
            System.out.println(" removing read lock from thread "+Thread.currentThread().getName()+" so others can acquire lock");
        }
    }
}
