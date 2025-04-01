package SortingAlgos;

import java.util.Arrays;

public class InsertionSort {


    // best time complexity o(n)
    // worst time complexity o(n*n)
    // for every index it compares if it is smaller than prev index element if it is small move it until it is smaller than any other element upto current index.


    public static void main(String[] args) {
        InsertionSort insertionSort = new InsertionSort();
        int[] nums ={5,1,4,3,2};
        System.out.println(Arrays.toString(insertionSort.insertionSort(nums)));
    }

    public int[] insertionSort(int[] arr){

        if (arr.length ==1){
            return arr;
        }


        for (int i =1;i<arr.length;i++){
            boolean isChanged = false;

            if (arr[i]<arr[i-1]){
                int temp =arr[i];
                arr[i]=arr[i-1];
                arr[i-1]=temp;

                isChanged =true;
                for (int j =i-1;j>0;j--){
                    if (arr[j]<arr[j-1]){

                        int temp1 =arr[j];
                        arr[j]=arr[j-1];
                        arr[j-1]= temp1;
                    }else {
                        break;
                    }
                }
            }

            if (!isChanged){
                System.out.println(" array is already sorted as no changes happened");
                break;
            }
        }

        return arr;
    }
}
