package Multithreading.CCMultiThreading;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class InterviewQuestion {

    public static void main(String[] args) {


        ReentrantLock r1 = new ReentrantLock();
        Condition c1 = r1.newCondition();
        PrintNumbers pr1 = new PrintNumbers();
        PrintNumbers pr2 = new PrintNumbers();
        Thread t1 =  new Thread(()  ->{
            pr1.printEvenNumber(r1,c1);
        });

        Thread t2 =  new Thread(()  ->{

            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            pr1.printOddNumber(r1,c1);
        });

        t1.start();
        t2.start();



    }




}

 class PrintNumbers{

    int i =0;
    public  void printEvenNumber(ReentrantLock lock,Condition condition){

        lock.lock();
        while (i%2==0 && i<=20){
            System.out.println(i);
            i++;
            try {
                condition.signal();
                condition.await();
            }catch (RuntimeException | InterruptedException e){

            }

        }
        condition.signal();
        lock.unlock();


    }

     public  void printOddNumber(ReentrantLock lock,Condition condition){
         lock.lock();
        while (i%2!=0 && i<=20){
             System.out.println(i);
             i++;
             try {
                 condition.signal();
                 condition.await();
             }catch (RuntimeException | InterruptedException e){

             }

        }

         condition.signal();
         lock.unlock();

     }

}

