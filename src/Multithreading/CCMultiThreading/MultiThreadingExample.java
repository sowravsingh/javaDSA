package Multithreading.CCMultiThreading;

public class MultiThreadingExample {
    public static void main(String[] args) {
        ComonResource resource = new ComonResource();

        resource.produceData(1);

        Thread t1 = new Thread( () ->{
            resource.produceData(1);
        });


        Thread t2  = new Thread(() ->{
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
           resource.consumeData();
        });




        t1.start();
//        try {
//            Thread.sleep(2000);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        t1.suspend();
//
//        try {
//            Thread.sleep(30000);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        t1.resume();

         t2.start();
    }
}
