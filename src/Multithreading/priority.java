package Multithreading;

public class priority {

    public static void main(String[] args) {

        Runnable r1 = () ->{
            System.out.println("currently Executing "+Thread.currentThread().getName());
        };


        Thread t1 = new Thread(r1,"first thread");
        Thread t2 = new Thread(r1,"second Thread");

        System.out.println("before setting "+t2.getPriority());

        t2.setPriority(7);
        t1.start();
        t2.start();
    }
}


