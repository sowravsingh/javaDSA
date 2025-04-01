package Multithreading.CCMultiThreading;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadLocalExample {

    public static void main(String[] args) {
        ThreadLocal<Integer> th = new ThreadLocal<>();

        th.set(0);

        ExecutorService  fp = Executors.newFixedThreadPool(5);
        fp.submit(() ->{
           th.set(2);
            System.out.println(" local value is "+th.get());
            th.remove();
        });

        for (int i =0;i<5;i++){
            fp.submit(() ->{
                System.out.println(th.get());
            });
        }

        //System.out.println("local value in main thread is "+th.get());

        fp.shutdown();
    }
}
