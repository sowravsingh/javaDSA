package SortingAlgos;

import java.util.Arrays;

public class MergeSort {


    //best time complexity :: o(nlogn)
    // worst time complexity :: o(nlogn)

    // divide the array into 2 parts . keep on dividing until array has only single element and then sort them separately and again merge them.
    // no matter whether it is already sorted or not we keep on dividing the array until it becomes single element and then we are merging back so best and worst remains same.


    public static void main(String[] args) {
        MergeSort ms = new MergeSort();
        int[] arr = {5,4,31,2,1};
        System.out.println(Arrays.toString(ms.mergeSort(arr)));
    }

    public int[] mergeSort(int[] nums){

        if (nums.length <=1){
            return nums;
        }
        int mid = nums.length/2;

        int[] leftArray = Arrays.copyOfRange(nums,0,mid);
        int[] rightArray = Arrays.copyOfRange(nums,mid,nums.length);

        nums= merge(mergeSort(leftArray),mergeSort(rightArray));


        return nums;
    }


    public  int[] merge(int[] leftArray,int[] rightArray){
        int[] resultArray = new int[leftArray.length+rightArray.length];

        int left = 0;
        int right =0;

        int index =0;
        while (left<leftArray.length && right<rightArray.length){
            if (leftArray[left]<rightArray[right]){
                resultArray[index] =leftArray[left];
                left++;
                index++;
            }else {
                resultArray[index] =rightArray[right];
                right++;
                index++;
            }

        }


        while (left<leftArray.length){
            resultArray[index] =leftArray[left];
            left++;
            index++;
        }

        while (right<rightArray.length){
            resultArray[index] =rightArray[right];
            right++;
            index++;
        }

        return resultArray;
    }
}
