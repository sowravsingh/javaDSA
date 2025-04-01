package SortingAlgos;

import java.util.Arrays;

public class QuickSort {

    // choose a pivot (best to pick mid one as pivot)
    // sort the array such that u are at a position where all the elements less than pivot on left side and all the elements greater than pivot are on right
    // again do the same for sub array  left of pivot and same for sub array right of pivot.

    // worst time complexity o(n*n)
    // best time complexity o(nlogn)



    public static void main(String[] args) {
        int[] nums ={5,4,3,2,1};
        QuickSort quickSort = new QuickSort();
        quickSort.quickSort(nums,0,nums.length-1);
        System.out.println(Arrays.toString(nums));
    }


    public void quickSort(int[] nums, int start, int end){

        if (start >= end){
            return;
        }

        int s =start;
        int e =end;


        int mid= (s+e)/2;
        int pivot = nums[mid];

        while (s<=e){

            while (nums[s]<pivot){
                s++;
            }

            while (nums[e]>pivot){
                e--;
            }

            if (s<=e){
                int temp = nums[s];
                nums[s]= nums[e];
                nums[e]=temp;
                s++;
                e--;
            }

        }




        quickSort(nums,start,e);
        quickSort(nums,s,end);
        return ;
    }
}
