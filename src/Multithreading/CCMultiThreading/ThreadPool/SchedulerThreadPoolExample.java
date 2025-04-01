package Multithreading.CCMultiThreading.ThreadPool;

import java.util.concurrent.*;

public class SchedulerThreadPoolExample {


    public static void main(String[] args) {
        //ScheduledExecutorService sp = Executors.newScheduledThreadPool(3);
        ThreadLocal<Integer> th = new ThreadLocal<>();
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        ScheduledFuture<?> scheduledFuture = executor.scheduleWithFixedDelay(() -> {
            if(th.get() == null){
                th.set(1);
            }else {
                th.set(th.get()+1);
            }
            System.out.println("started scheduling  " + Thread.currentThread().getName()+" for iteration "+th.get());
            try {
                Thread.sleep(5000);
            }catch (Exception e){

            }

            System.out.println("ended scheduling  " + Thread.currentThread().getName()+" for iteration "+th.get());

        }, 1, 3, TimeUnit.SECONDS);


        System.out.println("came here");
//        try {
//            Thread.sleep(20000);
//            scheduledFuture.cancel(true);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//
//        executor.shutdown();
    }
}
