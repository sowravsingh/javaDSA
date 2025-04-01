package Multithreading;

public class multThreadingExample {

    public static void main(String[] args) throws InterruptedException {
        //System.out.println(Thread.currentThread().getName());

        //extending thread class
        Employee e = new Employee("employee thread");

        // implementing runnable class
        Manager m = new Manager();
        Thread t1 = new Thread(m,"manager Thread");

        //using anonymous object
        Runnable r =() ->{
            System.out.println("current Thread is "+Thread.currentThread().getName());
        };
        Thread t = new Thread(r,"anonymousObject thread");


        //using anonymous class
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("current Thread is "+Thread.currentThread().getName());
            }
        };
        Thread t2 = new Thread(r1,"Anonymousclass thread");


        e.start();
        e.join();
        //e.sleep(3000);
        t1.start();

//        System.out.println("e state first time"+e.getState());
//        System.out.println("t1 state first time"+t1.getState());
//        System.out.println("e second time "+e.getState());
//        System.out.println("t1 second time "+t1.getState());



//        System.out.println(e.getName());
//        System.out.println(e.getState());
    }
}


class Employee extends Thread{

    public  Employee(String threadName){
        super(threadName);
    }

    @Override
    public void run(){
        for (int i =0;i<5;i++){
            System.out.println("current thread is "+Thread.currentThread().getName());
        }
    }
}


class Manager implements Runnable{


    @Override
    public void run() {
        for (int i =0;i<5;i++){
            System.out.println("current thread is "+Thread.currentThread().getName());
        }

    }
}
