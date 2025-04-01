package Multithreading;

public class SleepJoinExample {


    public static void main(String[] args) throws InterruptedException {
        Runnable r1 = () ->{
            for (int i =0 ;i<3;i++){
                System.out.println(" executing thread "+Thread.currentThread().getName() +" for "+i+" time.");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        };


        Thread t1 = new Thread(r1,"first Thread");
        Thread t2 = new Thread(r1,"second Thread");
        t1.start();

        // wait executing of t2 only for 3 sec so after 2 iterations of t1 t2 will start
        t1.join(3000);
        t2.start();
    }
}



