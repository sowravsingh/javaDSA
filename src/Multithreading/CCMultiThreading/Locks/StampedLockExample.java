package Multithreading.CCMultiThreading.Locks;

import java.util.concurrent.locks.StampedLock;

public class StampedLockExample {

    int a =30;

    public void consumer(StampedLock lock){

        System.out.println(" came to consume");
        long stamp = lock.readLock();
        System.out.println("acquired a write  lock");
        try{
            Thread.sleep(8000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        lock.unlockRead(stamp);
        System.out.println("released a write  lock");

    }

    public void consumer2(StampedLock lock){
        long stamp =lock.tryOptimisticRead();
        System.out.println("tried a read  lock ");
        try{
            Thread.sleep(20000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (lock.validate(stamp)){
            System.out.println("stamp was not changed so no one acquired lock");
        }else {
            System.out.println(" mean time some one acquired lock and released so some thing might updated ");
        }

    }
}
