package Multithreading;

public class InterruptedExample {


    public static void main(String[] args) {
        Interrupt r1 = new Interrupt();
        Thread t1 = new Thread(r1);
        t1.start();

       //t1.interrupt();
        try{
            t1.interrupt();
        }catch(Exception e){System.out.println("Exception handled "+e);}




    }
}


class Interrupt implements Runnable{

    @Override
    public void run() {
        for (int i =0;i<5;i++){
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }

            if (Thread.currentThread().isInterrupted()){
                System.out.println("thread is interrupted");
            }else {
                System.out.println(" printing "+i);

            }
        }
    }
}


//class TestInterruptingThread1 extends Thread{
//    public void run(){
//        try{
//            Thread.sleep(1000);
//            System.out.println("task");
//        }catch(InterruptedException e){
//            throw new RuntimeException("Thread interrupted..."+e);
//        }
//
//    }
//
//    public static void main(String args[]){
//        TestInterruptingThread1 t1=new TestInterruptingThread1();
//        t1.start();
//        try{
//            t1.interrupt();
//        }catch(Exception e){System.out.println("Exception handled "+e);}
//
//    }
//}
