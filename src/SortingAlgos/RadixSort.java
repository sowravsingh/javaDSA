package SortingAlgos;

import java.util.Arrays;

public class RadixSort {

    // radix sort is sorting numbers digit wise starting from ones digit to max digit in max element.
    // best and worst time complexity will be o (d*(n+k))
    // d represents number of digits in max element and k represents number of digits basically 10 ;



    public static void main(String[] args) {
        int[] nums ={170, 45, 75, 90, 802, 24, 2, 66};
        RadixSort radixSort = new RadixSort();
        System.out.println(Arrays.toString(radixSort.radixSort(nums)));
    }


    public int[] radixSort(int[] nums){

        int maxElement = Arrays.stream(nums).max().getAsInt();

        for (int exp = 1;maxElement/exp>0 ;exp=exp*10){
            int[] output = new int[nums.length];
            int[] count = new int[10];

            for (int i = 0;i<nums.length;i++){
               int number = (nums[i]/exp)%10;
               count[number]++;
            }

            for (int i =1;i<count.length;i++){
                count[i]= count[i]+count[i-1];
            }

            for (int i= nums.length-1;i>=0;i--){
                output[count[(nums[i]/exp)%10]-1] = nums[i];
                count[(nums[i]/exp)%10]--;
            }

            nums= output.clone();


        }

        return nums;

    }
}
