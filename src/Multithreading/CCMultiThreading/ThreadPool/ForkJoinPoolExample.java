package Multithreading.CCMultiThreading.ThreadPool;

import java.util.concurrent.*;

public class ForkJoinPoolExample {

    public static void main(String[] args) {
        ForkJoinPool fp = new ForkJoinPool();
        ForkJoinTask<Integer> submit = fp.submit(new SubmissionTask(8));
        try {
            System.out.println(submit.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        ForkJoinTask<Void> sowrav = fp.submit(new RecursionActionExample("tej"));
        try {
            sowrav.get();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}


class RecursionActionExample extends RecursiveAction{


    String str ="";
    public RecursionActionExample(String str) {
        this.str=str;
    }

    @Override
    protected void compute() {
        if (str.length()==1){
            System.out.println("executing by "+Thread.currentThread().getName());
            System.out.println(str.charAt(0));
        }else {
            int length = str.length();
            int mid =length/2;
            RecursionActionExample r1 = new RecursionActionExample(str.substring(0,mid));
            RecursionActionExample r2 = new RecursionActionExample(str.substring(mid));
            r1.fork();
            r2.fork();
        }
    }
}



class SubmissionTask extends RecursiveTask<Integer>{

    int a ;


    public SubmissionTask(int a) {
        this.a = a;
    }

    @Override
    protected Integer compute() {
        int sum =0;
        if (a<=4){
            for (int i =0;i<a;i++){
                sum=sum+1;

            }
           return sum;
        }

        int mid = a/2;
        SubmissionTask sb1 = new SubmissionTask(mid);
        SubmissionTask sb2 = new SubmissionTask(mid);
        sb1.fork();
        sb2.fork();


        int result1 = sb1.join();
        int result2= sb2.join();

        sum = result1+result2;

        return sum;
    }
}