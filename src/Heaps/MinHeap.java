package Heaps;

import Streams.InputStreams;

import java.util.ArrayList;

public class MinHeap {

    ArrayList<Integer> arrList = new ArrayList<>();



    public void insert(int value){
        arrList.add(value);
        upHeap(arrList.size()-1);

    }

    public int parentIndex(int index){
        return (index-1)/2;
    }

    public int leftIndex(int index){
        return 2*(index)+1;
    }

    public int rightIndex(int index){
        return 2*(index)+2;
    }

    public void swap(int firstIndex, int secondIndex){
        int temp = arrList.get(firstIndex);
        arrList.set(firstIndex,arrList.get(secondIndex));
        arrList.set(secondIndex,temp);
    }

    public void upHeap(int index){
        if(index==0){
            return;
        }

        int parentIndex = parentIndex(index);
        if(arrList.get(index)<= arrList.get(parentIndex)){
            swap(index,parentIndex);
            upHeap(parentIndex);
        }

        return;

    }

    public ArrayList<Integer> heapSort(){
       ArrayList<Integer> resultList = new ArrayList<>();

       while (!arrList.isEmpty()){
           resultList.add(remove());
       }

       return resultList;
    }


    public int remove(){

        int minNumber = arrList.get(0);
        int lastNumber =arrList.remove(arrList.size()-1);
        if(!arrList.isEmpty()){
            arrList.set(0,lastNumber);
            downHeap(0);
        }
        return minNumber;
    }


    public void downHeap(int index){
        int swapIndex = index;
        int left= leftIndex(index);
        int right= rightIndex(index);
        if(left<arrList.size() && arrList.get(left)<=arrList.get(swapIndex)){
            swapIndex =left;
        }

        if(right<arrList.size() && arrList.get(right)<=arrList.get(swapIndex)){
            swapIndex= right;
        }

        if(swapIndex !=index){
            swap(index,swapIndex);
            downHeap(swapIndex);
        }

    }

    public void printlist(){
        System.out.println(arrList);
    }



}
