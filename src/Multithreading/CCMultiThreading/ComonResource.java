package Multithreading.CCMultiThreading;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class ComonResource {

    Queue<Integer> queue = new LinkedList<>();

    public synchronized void produceData(int i){
        System.out.println(" came from producer to add data "+i);
        while (queue.size() ==1){
            System.out.println(" size is full so waiting ");
            try {
                //Thread.sleep(10000);
               wait();
            }catch (Exception e){
                // handle exceptions
            }
        }

        queue.offer(i);
        System.out.println(" offered data and notifying if any thread is waiting to consume data.");
        notifyAll();

    }

    public synchronized void consumeData(){
        System.out.println(" came to consume data ");
        while (queue.isEmpty()){
            System.out.println(" size is empty  so waiting ");
            try {
                wait();
            }catch (Exception e){
                // handle exceptions
            }
        }

        queue.poll();
        System.out.println(" consumed data and notifying if any thread is waiting to add data .");
        notifyAll();

    }





}
