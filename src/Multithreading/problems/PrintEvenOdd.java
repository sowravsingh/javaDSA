package Multithreading.problems;

public class PrintEvenOdd {
    public int num =0;
    public int max = 50;


    public synchronized void printOdd(){
        try {
            while (num<=max && num%2 !=0){
                System.out.println(" printing odd number "+num+" from "+Thread.currentThread().getName());
                num++;
                notify();
                wait();
            }
            notifyAll();
        }catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

    public synchronized void printEven(){
        try {
            while (num<=max && num%2 ==0){
                System.out.println(" printing even number "+num+" from "+Thread.currentThread().getName());
                num++;
                notify();
                wait();
            }
            notifyAll();
        }catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        PrintEvenOdd printEvenOdd = new PrintEvenOdd();
        System.out.println(" start time is "+System.currentTimeMillis());
        Thread t1 = new Thread(() ->{
           printEvenOdd.printEven();
        });

        Thread t2 = new Thread(() ->{
            printEvenOdd.printOdd();
        });

        t1.start();;
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(" end time is "+System.currentTimeMillis());


    }
}
