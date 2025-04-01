package CustomArrays;

import java.util.Arrays;

public class CustomStack {
    int arr[];
    int DEFAULT_SIZE =10;
    int size =0;

    CustomStack(){
        arr= new int[DEFAULT_SIZE];
    }

    public void push(int num){
        if(isEnoughSpace()){
            arr[size++]=num;
        }else{
            resize();;
            arr[size++]=num;
        }
    }

    public boolean isEnoughSpace(){
        return size<arr.length;
    }

    public int pop(){
        int removed = arr[--size];
        int[] temp = new int[size];
        for(int i =0;i<size;i++){
            temp[i]=arr[i];
        }
        arr=temp;
        return removed;
    }


    public void resize(){
        int[] temp = new int[size*2];
        for(int i =0;i<arr.length;i++){
            temp[i]= arr[i];
        }
        arr=temp;
    }

    @Override
    public String toString() {
        return "CustomStack{" +
                "arr=" + Arrays.toString(arr) +
                ", size=" + size +
                '}';
    }
}
