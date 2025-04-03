package LeetcodeProblems;

import java.util.Arrays;

public class LeetCodeProblem7 {

    public static void main(String[] args) {

        LeetCodeProblem7 ll = new LeetCodeProblem7();
        int[][] questions={{1,1},{2,2},{3,3},{4,4},{5,5}};
        long[] dp = new long[questions.length];
        int[] arr ={12,6,1,2,7};
        Arrays.fill(dp,-1);
        System.out.println(ll.maximumTripletValue(arr));

    }


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

            maxResult= Math.max(maxResult,(long) (prefixMax[i-1] - nums[1]) * suffixMax[i+1]);
        }

        return  maxResult;

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
