package Multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolsExample {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        Runnable r1 = () ->{
           for (int i =0;i<4;i++){
               System.out.println("currently Executing thread is "+Thread.currentThread().getName());
               try {
                   Thread.sleep(2);
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
           }
        };



        executor.execute(r1);
        executor.shutdown();
    }
}
