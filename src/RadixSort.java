import java.util.Arrays;

public class RadixSort {

    public static int[] radixSort(int[] arr){
        int maxNumber = Arrays.stream(arr).max().getAsInt();
        System.out.println(maxNumber);
        for(int i =1 ;maxNumber/i>0;i =i*10){
            arr= countSort(arr,i);

        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr ={49,2,34,4,46,23};
        radixSort(arr);
    }

    public static int[] countSort(int[] arr , int exp){
        int [] count = new int[10];
        int[] output= new int[arr.length];
        Arrays.fill(count,0);
        for (int i =0;i<arr.length;i++){
            count[(arr[i]/exp)%10]++;
        }
        System.out.println("Before counitng numbers "+Arrays.toString(count));

        for(int i =1;i<10;i++){
            count[i]= count[i]+count[i-1];
        }
        System.out.println("after counitng numbers "+Arrays.toString(count));

        for (int i =arr.length-1;i>=0;i--){
            output[count[(arr[i]/exp)%10]-1] =arr[i];
            count[(arr[i]/exp)%10]--;
        }

        System.out.println("final output array :: "+Arrays.toString(output));

        System.out.println("************************************");
        return output;
    }



}
