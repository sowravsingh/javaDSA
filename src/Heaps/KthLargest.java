package Heaps;

import java.util.PriorityQueue;

public class KthLargest {

    PriorityQueue<Integer> pq;
    int k;
    public KthLargest(int k, int[] nums) {
        this.k=k;
        pq=new PriorityQueue<>();
        for (int num:nums){
            if(pq.size()<k){
                pq.offer(num);
            }
            else{
                if(pq.peek()<num){
                    pq.poll();
                    pq.offer(num);
                }
            }

        }

        System.out.println(pq);
    }

    public int add(int val) {
        if(pq.size()<k){
            pq.add(val);
            return pq.peek();
        }else{
            if(pq.peek()<val){
                pq.poll();
                pq.offer(val);
            }
            return pq.peek();
        }
    }
}
