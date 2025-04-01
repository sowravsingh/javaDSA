package Multithreading;

public class DeadLock {


    public static void main(String[] args) {
        String str1 = "firstString";
        String str2= "secondString";

        Runnable r1 = new Runnable() {
            @Override
            public void run() {

                synchronized (str1){
                    System.out.println("printing "+str1+" from "+Thread.currentThread().getName());
                    synchronized (str2){
                        System.out.println("printing "+str2+" from "+Thread.currentThread().getName());
                    }
                }
            }
        };

        Runnable r2 = new Runnable() {
            @Override
            public void run() {

                synchronized (str2){
                    System.out.println("printing "+str2+" from "+Thread.currentThread().getName());
                    synchronized (str1){
                        System.out.println("printing "+str1+" from "+Thread.currentThread().getName());
                    }
                }
            }
        };

        Thread t1 = new Thread(r1,"thread1");
        Thread t2 = new Thread(r2,"Thread2");

        t1.start();
        t2.start();
        System.out.println("completed Executing ");
    }
}
