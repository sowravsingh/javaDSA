package Streams;

import java.util.Arrays;

public class CountSort {


    public static int[] countSort(int[] arr){
        int maxNumber = Arrays.stream(arr).max().getAsInt();
        int[] count= new int[maxNumber+1];
        Arrays.fill(count,0);
        for (int num :arr){
            count[num]++;
        }

        int index=0;
        for (int i=0;i<count.length;i++){
            if(count[i]==0){
                continue ;
            }
            int cnt = count[i];
            while(cnt!=0){
                arr[index]=i;
                index++;
                cnt--;
            }
        }

        return arr;
    }


    public static void main(String[] args) {
        int[] arr ={34,65,2,1,7,2};
        System.out.println(Arrays.toString(countSort(arr)));
    }
}
