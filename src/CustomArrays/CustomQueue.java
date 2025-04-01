package CustomArrays;

import java.util.ArrayList;
import java.util.List;

public class CustomQueue {
    int[] arr;
    int DEFAULT_SIZE =10;
    int front =0;
    int end =0;
    int size=0;


    CustomQueue(){
        arr = new int[DEFAULT_SIZE];
    }

    public void insert(int num){
        if(isEnoughSpace()){
            arr[end++]=num;
            size++;
        }else{

        }

    }

    public int remove(){
        int removed = arr[front++];
        return removed;
    }

    public void display(){
        for(int i = front;i<end;i++){
            System.out.print(arr[i] + " -> ");
        }
        System.out.println("END");
    }
    public boolean isEnoughSpace(){
        return  size<arr.length;
    }






}
