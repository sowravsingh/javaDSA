package SortingAlgos;

import java.util.Arrays;

public class CountSort {


    // find max element and create a count array of size maxElement+1;
    // now maintain how many times a number repeated in main array and store that count in count array with index as that number;
    // so start from small index (i.e small number ) so add that index to result array as how many times it was repeated add that many times that index to result array
    // not for big numbers because we have to create count array of size maxElement so if maxElement is big so size of array increases.

    // best and worst time complexity is o(n+k)

    public static void main(String[] args) {
        int[] arr ={5,3,2,19,7};
        CountSort countSort= new CountSort();
        System.out.println(Arrays.toString(countSort.countSort(arr)));
    }

    public int[] countSort(int[] arr){

        int[] resultArray = new int[arr.length];
        // find max element
        int maxElement = Arrays.stream(arr).max().getAsInt();

        int[] countArr = new int[maxElement+1];

        for (int num : arr){
            countArr[num]++;
        }


        int index =0;
        for (int i =0;i<countArr.length;i++){
            if (countArr[i]>=1){
                int count = countArr[i];
                while (count>0){
                    resultArray[index]=i;
                    count--;
                    index++;
                }
            }
        }


        return resultArray;
    }
}
