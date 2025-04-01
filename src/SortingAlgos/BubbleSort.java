package SortingAlgos;

import java.util.Arrays;

public class BubbleSort {



    // best time complexity :: o(n)
    // worst time complexity :: o(n*n)
    // idea is to at every one round largest element moves to last position then in next round second largest will move to second large index total rounds will be n;

    public static void main(String[] args) {
        BubbleSort bb = new BubbleSort();
        int[] arr={5,4,3,2,1};
        System.out.println(Arrays.toString(bb.bubbleSort(arr)));
    }

    public int[] bubbleSort(int[] arr){

        for (int i =0;i<arr.length;i++){
            boolean isChanged = false;
            for (int j =0;j<arr.length-1-i;j++){
                if (arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1] =temp;
                    isChanged=true;
                }
            }
            //System.out.println(Arrays.toString(arr));

            if (!isChanged){
                System.out.println(" array is already sorted so returning at "+i);
                break;
            }
        }

        return arr;

    }
}