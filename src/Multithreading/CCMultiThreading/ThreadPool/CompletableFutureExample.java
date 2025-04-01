package Multithreading.CCMultiThreading.ThreadPool;

import java.util.concurrent.*;

public class CompletableFutureExample {

    public static void main(String[] args) {

        ThreadPoolExecutor tp  = new ThreadPoolExecutor(1,2,2, TimeUnit.MINUTES,new ArrayBlockingQueue<>(2),new customThreadFactory(),new ThreadPoolExecutor.AbortPolicy());

        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try{
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(" this task was executing by thread " + Thread.currentThread().getName());
            return "sowrav";
        },tp);

//        CompletableFuture.runAsync(() ->{
//            System.out.println(" inside runnable method with thread "+Thread.currentThread().getName() );
//        },tp);

        CompletableFuture<String> stringCompletableFuture1 = stringCompletableFuture.thenApply((String str) -> {
            System.out.println(" first then was executed");
            return str + " singh";
        });


//        stringCompletableFuture.thenApply((String str) ->{
//            System.out.println("second this was execute ");
//            return "singh "+str;
//        });

        CompletableFuture<String> stringCompletableFuture2 = stringCompletableFuture1.thenCompose((String str) -> {
            System.out.println(" compose  was executed");
            return CompletableFuture.supplyAsync(() -> {
                String s = str + " raj";
                return s;
            });
        });

        stringCompletableFuture2.thenAccept((str) ->{
            System.out.println(" this is the end of the chain and strng is "+str);
        });

        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            return 10;
        });

        CompletableFuture<String> stringCompletableFuture3 = integerCompletableFuture.thenCombine(stringCompletableFuture2, (Integer a, String b) -> {
            return a + " " + b;
        });


        System.out.println(" currently running thread is "+Thread.currentThread().getName());

        try {
            System.out.println(stringCompletableFuture3.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        System.out.println(" completed executing first thread ");
        tp.shutdown();
    }
}

class  customThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable runnable) {
        Thread th = new Thread(runnable);
        th.setName(" custom thread :");
        return th;
    }
}
