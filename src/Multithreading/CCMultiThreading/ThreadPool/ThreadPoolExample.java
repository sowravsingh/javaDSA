package Multithreading.CCMultiThreading.ThreadPool;

import java.util.concurrent.*;

public class ThreadPoolExample {


    public static void main(String[] args) {
        ThreadPoolExecutor tp = new ThreadPoolExecutor(2,4,100, TimeUnit.MINUTES,new ArrayBlockingQueue<>(2),new CustomThreadFactory(),new CustomRejectionHandler());
        //ExecutorService tp = Executors.newFixedThreadPool(2);

        for (int i =1;i<=7;i++){

            tp.submit(() ->{
                System.out.println(" task "+ " was picked by thread "+Thread.currentThread().getName());
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        System.out.println(" before shut down");
        tp.shutdown();
        System.out.println("after shutdown");

    }
}


class CustomThreadFactory implements ThreadFactory{

    @Override
    public Thread newThread(Runnable runnable) {
        Thread th =new Thread(runnable);
        System.out.println("creating new thread  using custom thread factory");
        th.setName("custom Thread");
        return th;
    }
}

class CustomRejectionHandler implements RejectedExecutionHandler{

    @Override
    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
        System.out.println("rejecting task "+runnable.toString());
    }
}
