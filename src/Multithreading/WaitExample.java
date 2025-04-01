package Multithreading;

public class WaitExample {


    public static void main(String[] args) {
        wait wt = new wait();
        Runnable r1 = () ->{
            try {
                wt.withdraw(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };


        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                wt.deposit(2000);
            }
        };

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
        System.out.println(wt.totalAmount);
    }
}


class wait{
    int totalAmount =0;

    public synchronized void withdraw(int amount ) throws InterruptedException {
        if(amount>totalAmount){
            System.out.println(" no sufficient balance to withdraw amount "+amount);
            wait();
        }

        System.out.println(" wait is over got sufficient balance "+totalAmount);
        totalAmount-=amount;
    }

    public synchronized  void deposit(int amount){
        System.out.println("depositing amount");
        totalAmount+=amount;
        notify();
    }




}
