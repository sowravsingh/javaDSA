package SortingAlgos;

import java.util.Arrays;

public class HeapSort {



    // first construct a maxheap (i.e keeping maximum element at top)
    // now swap the top element with end element so that for every iteration largest element comes to end one by one
    // while swapping try again to build max heap for the array excluding the last swapped index.


    // construct max heap it takes o(logn) because we assuming the given array as complete binary tree so max height from top to bottom takes logn time.
    // we are doing this for every element so it is o(nlogn)

    // best and worst remains same because even it is sorted we will traverse from top to bottom

    public static void main(String[] args) {

        int[] nums ={9,4,3,8,10,2,5};
        HeapSort heapSort = new HeapSort();
        heapSort.heapSort(nums);
    }



    public void heapify(int[] arr , int index, int n){

        int left = (2*index) +1;
        int right = (2*index)+2;

        int largest = index;

        if (left<n && arr[left]>arr[largest]){
            largest=left;
        }

        if (right<n && arr[right]>arr[largest]){
            largest=right;
        }

        if (largest!=index){

            int temp= arr[index];
            arr[index]=arr[largest];
            arr[largest]= temp;

            heapify(arr,largest,n);
        }
    }



    public void heapSort(int[] nums){

        int n = nums.length;
        for (int i =(n/2)-1 ;i>=0;i--){
            heapify(nums,i,n);
        }


        for (int i =n-1;i>=0;i--){
            int temp =nums[0];
            nums[0]=nums[i];
            nums[i]=temp;

            heapify(nums,0,i);
        }

        System.out.println(Arrays.toString(nums));
    }
}
