package CustomArrays;

import java.util.Arrays;

public class CustomArrayList {
    int DEFAULT_SIZE=5;
    int[] arr ;
    int size=0;


    CustomArrayList(){
        arr= new int[DEFAULT_SIZE];
    }

    public void add(int num){
        if(isEnoughSpace()){
            arr[size]=num;
            size++;
        }else{
            resize();
            arr[size]=num;
            size++;
        }

    }

    public boolean isEnoughSpace(){
        if(size<arr.length){
            return true;
        }else{
            return false;
        }
    }

    public void resize(){
        int[] temp = new int[arr.length*2];
        for(int i =0;i<arr.length;i++){
            temp[i]=arr[i];
        }
        arr = temp;

    }

    public int get(int index){
        return arr[index];
    }

    public int size(){
        return size;
    }


    public void remove(int index){
        int[] temp = new int[arr.length];
        for(int i =0;i<index;i++){
            temp[i]=arr[i];
        }
        for(int i=index;i<arr.length-1;i++){
            temp[i]=arr[i+1];
        }

        arr=temp;
        size--;

    }

    @Override
    public String toString() {
        return "CustomArrayList{" +
                "size=" + size +
                ", arr=" + Arrays.toString(arr) +
                '}';
    }
}
