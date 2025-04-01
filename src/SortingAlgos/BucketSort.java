package SortingAlgos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BucketSort {



    //best case : o (n) if  in each bucket only one element was in each bucket or all the elements in bucket are already sorted
    // wost case : o(n*n) as bubble sort took worst case n*n for sorting.

    public static void main(String[] args) {
        BucketSort bucketSort = new BucketSort();
        float[] arr = {0.44f,0.12f,0.67f};
        System.out.println(Arrays.toString( bucketSort.bucketSort(arr)));
    }

    public float[] bucketSort(float[] arr){

        int n = arr.length;
        ArrayList<Float>[] arrayLists = new ArrayList[n];


        for (int i =0;i<n;i++){
            ArrayList<Float> list = new ArrayList<>();
            arrayLists[i] = list;
        }

        for (float num : arr){

            int index =(int) num*n;
            arrayLists[index].add(num);
        }

        for (int i =0;i<n;i++){
            bubbleSort(arrayLists[i]);
        }


        int index =0;
        for (List<Float> list:arrayLists){
            if (!list.isEmpty()){
                for (float num : list){
                    arr[index]=num;
                    index++;
                }
            }
        }

        return arr;
    }


    public void bubbleSort(ArrayList<Float> list){
        for (int i =0;i<list.size();i++){
            boolean isChanged = false;
            for (int j = i+1;j<list.size();j++){
                if (list.get(i)>list.get(j)){
                    isChanged=true;
                    float temp = list.get(i);
                    list.set(i,list.get(j));
                    list.set(j,temp);
                }
            }


            if (!isChanged){
                return;
            }
        }
    }
}
