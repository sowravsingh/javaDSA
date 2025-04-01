package Multithreading.CCMultiThreading;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicVariables {
    AtomicInteger counter = new AtomicInteger(0);

    public  void increment(){
        counter.addAndGet(1);
    }

    public int getCounter(){
        return counter.get();
    }
}

class main{
    public static void main(String[] args) {
        AtomicVariables at = new AtomicVariables();

        Thread t1 = new Thread(() ->{
            for (int i = 0;i<200;i++){
                at.increment();
            }
        });

        Thread t2 = new Thread(() ->{
            for (int i = 0;i<200;i++){
                at.increment();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println(at.getCounter());
    }
}
