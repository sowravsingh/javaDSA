package Multithreading.CCMultiThreading;

public class MultiThreadingExample2 {

    public static void main(String[] args) {
        System.out.println(" started executing main thread");

        Thread t1 = new Thread( () ->{
            try{
                System.out.println(" sleeping for some time ");
                Thread.sleep(20000);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        t1.setDaemon(true);
        t1.start();
//        try {
//            t1.join();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        System.out.println(" completed executing main thread");

    }
}
