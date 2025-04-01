package Multithreading;

public class Normal {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("hi");
        Thread t1 = new Thread("tejaThread");
        t1.run();
        //t1.start();
        //Thread.sleep(5000);
        System.out.println(Thread.activeCount());
        Thread[] threads = new Thread[Thread.activeCount()];
        Thread.enumerate(threads);  // Populates the array with all active threads
        for (Thread t : threads) {
            System.out.println("Thread name: " + t.getName());
        }
        System.out.println("printing hi after 5 sec");

    }
}
