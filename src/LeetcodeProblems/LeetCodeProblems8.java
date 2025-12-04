package LeetcodeProblems;

import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;

public class LeetCodeProblems8 {
    public static void main(String[] args) {
        LeetCodeProblems8 ll = new LeetCodeProblems8();
        int[] nums ={10,10,10};
        int[] wrokers={0,10,10,10,10};
        ll.solve(nums,3);

    }


    public int[] solve(int[] A, int B) {
        if (B >A.length){
            B= B-A.length;
        }

        reverse(A,0,A.length-1);

        reverse(A,0,B-1);
        if(B != A.length){
            reverse(A,B,A.length-1);
        }

        System.out.println(Arrays.toString(A));
        return A;

    }

    public void reverse(int[] arr , int start , int end){

        while(start<=end){
            int temp = arr[start];
            arr[start]=arr[end];
            arr[end]=temp;
            start++;
            end--;
        }
    }



    int[][]dp;
    public int minTimeToReach(int[][] moveTime) {
        if (moveTime.length==0){
            return 0;
        }



        dp = new int[moveTime.length][moveTime[0].length];
        for (int[] row:dp){
            Arrays.fill(row,-1);
        }

        return minTime(moveTime,0,0,0);


    }


    public int minTime(int[][] num , int row, int col, int time){

        if (dp[row][col] !=-1){
            return dp[row][col];
        }
        if(num[row][col] > time){
            time= num[row][col];
        }

       int downTime =  minTime(num,row+1,col,time+1);
       int rightTime = minTime(num,row, col+1,time+1);
       int upTime= minTime(num,row-1,col,time+1);
       int  leftTime =  minTime(num,row, col-1,time+1);

      return time;

    }

    public boolean isValid(int row , int col, int[][] num){
        if(row==num.length || col == num[0].length || row <0 || col<0){
            return false;
        }

        return true;
    }

    public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {

        int count =0;
        PriorityQueue<Integer> workersPq = new PriorityQueue<>();
        PriorityQueue<Integer> tasksPq = new PriorityQueue<>();


        for (int num : workers){
            workersPq.offer(num);
        }

        for (int num : tasks){
            tasksPq.offer(num);
        }

        while (!workersPq.isEmpty()){
           int worker =  workersPq.poll();
           if (tasksPq.peek()<= worker){
               tasksPq.poll();
               count++;
           }else {
               if (pills !=0 && worker+strength >=tasksPq.peek()){
                   pills--;
                   count++;
                   tasksPq.poll();
               }
           }

        }

        System.out.println(count);
        return count;
    }


    public int findNumbers(int[] nums) {
        int count =0;

        for (int num : nums){
           int digits = (int) Math.log10(num) +1;

           if (digits%2 ==0){
               count++;
           }
        }

        System.out.println(count);
        return count;
    }


    public long countSubarrays2(int[] nums, int k) {

        int maxElement = nums[0];
        for (int num :nums){
            maxElement= Math.max(maxElement,num);
        }

        long result =0;
        int count =0;
        int left =0;
        int right =0;

        System.out.println(maxElement);
        while (right<nums.length){
            if (nums[right] == maxElement){
                count++;
            }
        //    System.out.println(" right os at "+right);

            if (count==k){
                result = result+(nums.length-right);
            }

            while (count==k ){
                System.out.println(left);

                if (nums[left]==maxElement){
                    count--;
                }else {
                    result = result+(nums.length-right);
                }

                left++;
            }

            right++;
        }

        System.out.println(result);
        return result;
    }

    public long countSubarrays(int[] nums, long k) {

                int sum =0;
                int len =0;
                int left =0;
                int cnt =0;
                int right =0;
                int prevRight =-1;

                while (right<nums.length){
                    sum = sum+nums[right];
                    len++;

                    while (sum*len >=k){
                        sum= sum-nums[left];
                        len--;

                        left++;
                    }


                    if (right>=left){
                        int n = right-left+1;
                        cnt = cnt+ (n *(n+1))/2;

                        if (prevRight >=left){
                            n = prevRight-left+1;
                            cnt = cnt- (n *(n+1))/2;
                        }
                        prevRight =right;
                    }

                    right++;

                }

                System.out.println(cnt);
                return cnt;
    }
}
