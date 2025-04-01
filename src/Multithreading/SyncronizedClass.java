package Multithreading;

public class SyncronizedClass {

    public static void main(String[] args) throws InterruptedException {
        BricksCount bd = new BricksCount();

        Runnable r1 =() ->{
            for (int i =0;i<10000;i+=50){
                bd.updateBrickCount();
            }
        };

        Runnable r2 =() ->{
            for (int i =0;i<10000;i+=50){
                bd.updateBrickCount();
            }
        };

        Runnable r3 =() ->{
            for (int i =0;i<10000;i+=50){
                bd.updateBrickCount();
            }
        };


        Thread t1= new Thread(r1);
        Thread t2= new Thread(r2);
        Thread t3= new Thread(r3);

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println(bd.bricksCount);
        System.out.println(bd.nonSyncronizedCount);
    }


}

class  BricksCount{
    int bricksCount=0;
    int nonSyncronizedCount=0;
    public  void  updateBrickCount(){
        synchronized (this) {
            bricksCount+=50;
        }

        nonSyncronizedCount+=50;
    }
}