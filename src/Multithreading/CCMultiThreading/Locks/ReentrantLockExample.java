package Multithreading.CCMultiThreading.Locks;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {

    int a =20;
//    static ReentrantLock lock= new ReentrantLock();

    public void producer(ReentrantLock lock){
        System.out.println(" came to produce data ");

        try {
            lock.lock();
            System.out.println(" acquired a reentrant lock and sleeping for 20 secs");
            Thread.sleep(20000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
            System.out.println(" removing lock so others can acquire lock");
        }

    }


    public void consume(ReentrantLock lock){
        System.out.println(" came to consume data ");

        try {
            lock.lock();
            System.out.println(" acquired a reentrant lock and sleeping for 20 secs");
            Thread.sleep(20000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
            System.out.println(" removing lock so others can acquire lock");
        }
    }

}
