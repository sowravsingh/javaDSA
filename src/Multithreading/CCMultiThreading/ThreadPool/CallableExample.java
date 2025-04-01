package Multithreading.CCMultiThreading.ThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CallableExample {

    public static void main(String[] args) {
        ThreadPoolExecutor tp = new ThreadPoolExecutor(1,2,2, TimeUnit.MINUTES,new ArrayBlockingQueue<>(2), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        Future<String> submit = tp.submit(() -> {
            return "sowrav";
        });

        Future<String> submit2 = tp.submit(() -> {
            return "sowrav";
        });

        List<String> list = new ArrayList<>();
        tp.submit(() ->{
            list.add("teja");
        },list);

        System.out.println(list);
        try {

            System.out.println(submit.get());
            System.out.println(submit2.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        tp.shutdown();
    }
}
