package Multithreading.CCMultiThreading.Locks;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class SemaphoreLockExample {

    int a =20;
   // static Semaphore lock= new Semaphore(2);

    public void producer(Semaphore lock){
        System.out.println(" came to produce data ");

        try {
            lock.acquire();
            System.out.println(" acquired a sempahore lock and sleeping for 20 secs");
            Thread.sleep(20000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            lock.release();
            System.out.println(" removing lock so others can acquire lock");
        }

    }


    public void consume(Semaphore lock){
        System.out.println(" came to consume data ");

        try {
            lock.acquire();
            System.out.println(" acquired a semaphore lock and sleeping for 20 secs");
            Thread.sleep(20000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            lock.release();
            System.out.println(" removing lock so others can acquire lock");
        }
    }
}
