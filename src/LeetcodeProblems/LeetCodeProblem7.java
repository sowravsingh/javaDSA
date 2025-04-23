package LeetcodeProblems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LeetCodeProblem7 {

    public static void main(String[] args) {

        LeetCodeProblem7 ll = new LeetCodeProblem7();
        int[][] questions={{1,1},{2,2},{3,3},{4,4},{5,5}};
        long[] dp = new long[questions.length];
        int[] arr ={4,5,6,4,4};
        Arrays.fill(dp,-1);
        System.out.println(ll.countLargestGroup(13));

    }


    public int countLargestGroup(int n) {
        Map<Integer,Integer> sumVsFrequency= new HashMap<>();

        int maxFreq = Integer.MIN_VALUE;
        for (int i =1;i<=n ;i++){
            int k=i;
            int rem =0;
            while (k>0){
                rem = rem+ k%10;
                k = k/10;
            }
            sumVsFrequency.put(rem,sumVsFrequency.getOrDefault(rem,0)+1);
            maxFreq=Math.max(maxFreq,sumVsFrequency.get(rem));
        }

        int largeGroup = 0;

        for (int sum :sumVsFrequency.keySet()){
           if (sumVsFrequency.get(sum) == maxFreq)
               largeGroup++;
        }

        return largeGroup;

    }



    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int totalSubArrays =0;
        int start =0;
        int product =1;
        for (int i =0;i<nums.length;i++){

            product= product*nums[i];
        }


        return totalSubArrays;
    }



    public String shortestBeautifulSubstring(String s, int k) {

        int left =0;
        int minLen = Integer.MAX_VALUE;
        String resultString ="";

        for (int i =0;i<s.length();i++){
            if (s.charAt(i) == '1'){
                k--;
            }

            if (k ==0){
                if (minLen > (i-left+1)){
                    resultString= s.substring(left,i+1);
                    minLen= resultString.length();
                } else if (minLen == (i-left+1)) {
                    if (lexicographicallyComparison(s.substring(left,i+1),resultString)){
                        resultString= s.substring(left,i+1);
                    }
                }
            }

            while (k==0){
                if(s.charAt(left) =='1'){
                    k++;
                }

                left++;

                if (k ==0){
                    if (minLen > (i-left+1)){
                        resultString= s.substring(left,i+1);
                        minLen= resultString.length();
                    }else if (minLen == (i-left+1)) {
                        if (lexicographicallyComparison(s.substring(left,i+1),resultString)){
                            resultString= s.substring(left,i+1);
                        }

                    }
                }
            }

        }


        return resultString;
    }


    public boolean lexicographicallyComparison(String s1, String s2){

        int i =0;
        while (i<s1.length()){
            if (s1.charAt(i) > s2.charAt(i)){
                return false;
            }

            if (s1.charAt(i) < s2.charAt(i)){
                return true;
            }
            i++;
        }
        return false;

    }



    public int characterReplacement(String s, int k) {
        HashMap<Character, Integer> freqs = new HashMap<>();
        int res = 0, i = 0, maxFreq = 0;

        for (int j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            freqs.put(c, freqs.getOrDefault(c, 0) + 1);
            maxFreq = Math.max(maxFreq, freqs.get(c));


           // System.out.println(" max freq at "+c +" is "+maxFreq);
            if ((j - i + 1) - maxFreq > k) {
                char left = s.charAt(i);
                freqs.put(left, freqs.get(left) - 1);
                i++;
            }

           /// System.out.println(" j - i + 1 "+(j - i + 1));
            res = Math.max(res, j - i + 1);
        }

        return res;

    }

    public long numberOfPowerfulInt(long start, long finish, int limit, String s) {
        Long result = 0L;

        StringBuffer sb = new StringBuffer(s);
        while (Integer.parseInt(sb.toString()) <=finish){
            for (int i =1 ;i<=limit;i++){
               sb.insert(0,i);
            }
        }

        return result;

    }

    public int countGoodNumbers(long n) {

        int mod = 1000000007;
        long evenDigits =0L;
        long oddDigits =0L;

        if(n%2 ==0){
            evenDigits = n/2;
            oddDigits= n/2;
        }else {
            evenDigits= n/2+1;
            oddDigits=n/2;
        }


        double oddCount = Math.pow(4, oddDigits);

        double evenCount = Math.pow(5,evenDigits);


        return (int)((oddCount * evenCount) % mod);

      //  return 100;
    }

//    public int countGoodTriplets(int[] arr, int a, int b, int c) {
//
//    }

    // leetcode problem 2874
    public long maximumTripletValue(int[] nums) {
        int[]prefixMax =new int[nums.length];
        int[]suffixMax = new int[nums.length];
        Long maxResult = 0L;

        prefixMax[0]= nums[0];
        suffixMax[nums.length-1]= nums[nums.length-1];

        for (int i =1;i<nums.length;i++){
            prefixMax[i]=Math.max(prefixMax[i-1],nums[i]);
        }

        for (int i = nums.length-2;i>=0;i--){
            suffixMax[i]= Math.max(suffixMax[i+1],nums[i]);
        }

        for (int i =1;i<nums.length-1;i++){
            maxResult= Math.max(maxResult,(Long.valueOf(prefixMax[i-1])-Long.valueOf(nums[i]))*Long.valueOf(suffixMax[i+1]));
        }

        return  maxResult;

    }


    // leetcode problem 416
    public boolean canPartition(int[] nums) {
       int totalSum =0;

       for (int num : nums){
           totalSum=totalSum+num;
       }

       if (totalSum%2 !=0){
           return false;
       }
        int target = totalSum/2;
        System.out.println(target);

        int[][] dp= new int[nums.length][target+1];



        return getTarget(nums , target, 0,  dp);


    }


    public boolean getTarget(int[] arr , int target, int index , int[][] dp){

        if (target==0){
            return true;
        }


        if (index == arr.length){
            return false;
        }


        if (dp[index][target] !=0){
            return dp[index][target] ==1 ?true:false;
        }



        boolean notPick = getTarget(arr,target,index+1,dp);

        boolean pick = false;

        if (arr[index] <= target){
            pick = getTarget(arr,target-arr[index],index+1,dp);
        }

        boolean result = pick || notPick;

        dp[index][target] = result ?1 :2;

        return result;
    }


    public int minimumOperations(int[] nums) {
        Map<Integer,Integer> freqMap = new HashMap<>();

        for (int num : nums){
            freqMap.put(num, freqMap.getOrDefault(num,0)+1);
        }

        int count =0;
        int index=0;
        boolean isUnique = false;
        while(!isUnique || !freqMap.isEmpty()){
            isUnique= isUnique(freqMap);
            if (isUnique){
                return count;
            }else {
                for (int i =0;i<3;i++){
                    if (index < nums.length && freqMap.get(nums[index])!=null){
                        freqMap.put(nums[index], freqMap.get(nums[index])-1);
                    }else {
                        break;
                    }

                    index++;
                }
                count++;
            }

        }

        return count;
    }


    public boolean isUnique(Map<Integer,Integer> freqMap){

        for (Integer num : freqMap.keySet()){
            if (freqMap.get(num) >1){
                return false;
            }
        }
        return true;
    }




    public long mostPoints(int[][] questions, int index) {
        System.out.println(index);

        if (index>= questions.length){
            return 0;
        }

        //pick
        long s1 = questions[index][0]+mostPoints(questions,index+questions[index][1]+1);

        //not pick
        long s2 = mostPoints(questions,index+1);
        return Math.max(s1,s2);
    }

    public long mostPointsDp(int[][] questions, int index,long[] dp) {
        System.out.println(index);

        if (index>= questions.length){
            return 0;
        }
        if (dp[index]!=-1){
            return dp[index];
        }

        //pick
        long s1 = questions[index][0]+mostPoints(questions,index+questions[index][1]+1);

        //not pick
        long s2 = mostPoints(questions,index+1);
        return dp[index]=Math.max(s1,s2);
    }

    public static long mostPointsTab(int[][] questions) {
        long[] dp = new long[questions.length];
        Arrays.fill(dp,-1);


        for (int i = questions.length - 1; i >= 0; i--) {
            //pick
            long s1 = questions[i][0];
            if (i + questions[i][1] + 1 < questions.length) {
                s1 = dp[i + questions[i][1] + 1];
            }

            //not pick
            long s2 = Long.MIN_VALUE;
            if (i + 1 < questions.length) {
                s2 = dp[i + 1];
            }
            dp[i] = Math.max(s1, s2);
        }
        return dp[0];
    }

}
