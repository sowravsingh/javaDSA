package SortingAlgos;

import java.util.Arrays;

public class SelectionSort {

    // best time complexity o(n*n)
    // worst time complexity o(n*n)
    // for every index fetch smallest element in remaining array and put that element in the current index .so even it is already sorted also it takes n*n time

    public static void main(String[] args) {
        SelectionSort sc = new SelectionSort();
        int[] nums ={5,4,3,21};
        System.out.println(Arrays.toString(sc.selectionSort(nums)));
    }

    public int[] selectionSort(int[] arr){

        for (int i =0;i<arr.length-1;i++){

            int minIndex =i;
            for (int j =i+1;j<arr.length;j++){
                if (arr[j]<arr[minIndex]){
                    minIndex=j;
                }
            }

            if (i != minIndex){
                int temp = arr[i];
                arr[i]= arr[minIndex];
                arr[minIndex]=temp;
            }
        }

        return arr;
    }
}
