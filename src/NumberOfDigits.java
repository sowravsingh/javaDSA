import java.util.*;

public class NumberOfDigits {
    public static void main(String[] args) {

        int[] arr={1,4,3,2,5};
        int[] result = cyclicSort(arr);
        System.out.println(Arrays.toString(result));
//        for(int i=0;i<result.length;i++){
//            System.out.println(result[i]);
//        }
    }


    public static int countNicePairs(int[] nums) {
        int result =0;
        int mod =1000000007;
         Map<Integer,Integer> resultMap = new HashMap<>();
        for(int i =0 ;i<nums.length;i++){
            int number = nums[i];
            int reversedNumber = 0;
            while(number !=0){
                int n = number%10;
                reversedNumber=reversedNumber*10 +n;
                number=number/10;
            }
            nums[i]= Math.abs(nums[i]-reversedNumber);
        }

        for (int n :nums){
            int count = resultMap.getOrDefault(n,0);
            result=(result%mod)+(count%mod);
            resultMap.put(n,count+1);
        }


        return result%mod;
    }



    public int maxCoins(int[] piles) {
        int resultSum =0;
        Arrays.sort(piles);
        int len =piles.length;
        int n = len/3;
        int count =1;
        for(int i =n;i<len;i++){
            if(count%2!=0){
                resultSum=resultSum+piles[i];
            }
            count++;
        }
        return resultSum;
    }

    public int maxProfit(int[] prices) {
        int profit =0;
        int minDay = prices[0];
        for(int i =1;i<prices.length;i++){
            int dayProfit=prices[i]-minDay;
            if(dayProfit>0){
                profit=profit+dayProfit;
                minDay=prices[i];
            }
            else{
                minDay=prices[i];
            }
        }
        return profit;
    }


    public int countHomogenous(String s) {
        char prev = '?';
        int cnt = 1;
        int sum = 0;

        for (char c : s.toCharArray()) {
            if (c != prev) {
                cnt = 1;
                prev = c;
            }
            sum = (sum + cnt++) % 1000000007;
        }
        return sum;
    }

    public static int[] bubbleSort(int [] arr){
        for(int i=0;i<arr.length;i++){
            for(int j =1;j<arr.length;j++){
                if(arr[j]<arr[j-1]){
                    int temp = arr[j];
                    arr[j]=arr[j-1];
                    arr[j-1]=temp;
                }
            }
        }

        return arr;
    }


    public static int[] selectionSort(int[] arr){
        for(int i=0;i<arr.length;i++){
            int minNumber = arr[i];
            int minIndex=i;
            for(int j=i+1;j<arr.length;j++){
                if(minNumber> arr[j]){
                    minNumber=arr[j];
                    minIndex=j;
                }
            }
            int temp = arr[i];
            arr[i]=minNumber;
            arr[minIndex]=temp;

        }
        return arr;
    }


    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int i=0,j=0;
        List<Integer> resultList = new ArrayList<>();
        while(i<nums1.length && j<nums2.length){
            if(nums1[i]<nums2[j]){
                resultList.add(nums1[i]);
                i++;
            }else{
                resultList.add(nums2[j]);
                j++;
            }
        }

        while(i<nums1.length){
            resultList.add(nums1[i]);
            i++;
        }
        while(j<nums2.length){
            resultList.add(nums2[j]);
            j++;
        }

        int len= resultList.size();
        int mid = len/2;
        if(len%2 ==0){
            double result = (resultList.get(mid)+resultList.get(mid-1))/2;
            return result;
        }
        else{
            double result = resultList.get(mid);
            return result;
        }

    }


    public static int[] insertionSort(int [] arr){
        for(int i =0;i<arr.length-1;i++){
            for(int j = i+1;j>0;j--){
                if(arr[j]<arr[j-1]){
                    int temp = arr[j];
                    arr[j]= arr[j-1];
                    arr[j-1]=temp;
                }else{
                    break;
                }
            }
        }
        return arr;
    }


    public static int[] cyclicSort(int[] arr){

        int i=0;
        while(i<arr.length){
            int correctIndex = arr[i]-1;
            if(arr[i]!= arr[correctIndex]){
                int temp= arr[i];
                arr[i]=arr[correctIndex];
                arr[correctIndex]=temp;
            }else{
                i++;
            }
        }

        return arr;
    }


    public int missingNumber(int[] nums) {
        //Arrays.sort(nums);


        int i=0;
        while(i <nums.length){
            int correct=nums[i];
            if(nums[i] <nums.length && nums[i]!=nums[correct]){
                int temp=nums[i];
                nums[i]=nums[correct];
                nums[correct]=temp;
            }else{
                i++;
            }
        }
        for(int index =0;index<nums.length;index++){
            if(nums[index] !=index){
                return index;
            }
        }
        return nums.length;
    }

}