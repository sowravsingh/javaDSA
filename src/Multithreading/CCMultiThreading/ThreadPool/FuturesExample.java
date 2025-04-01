package Multithreading.CCMultiThreading.ThreadPool;

import java.util.concurrent.*;

public class FuturesExample {

    public static void main(String[] args) {
        ThreadPoolExecutor tp = new ThreadPoolExecutor(1,1,4, TimeUnit.MINUTES,new ArrayBlockingQueue<>(2), Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());

        Future<?> futureObject =tp.submit(() ->{
           try {
               Thread.sleep(7000);
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }
            System.out.println(" execution of thread was completed");
        });


        try{
            System.out.println( futureObject.get(2,TimeUnit.SECONDS));

        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            System.out.println(" exception occured while fetching data ");
        }

        try{
            System.out.println( futureObject.get());

        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        System.out.println(futureObject.isDone());

        tp.shutdown();

    }
}
