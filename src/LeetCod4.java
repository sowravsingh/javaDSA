import java.lang.reflect.Array;
import java.util.*;

public class LeetCod4 {


    public static String replaceWords(List<String> dictionary, String sentence) {
        String[] sentenceArray = sentence.split(" ");
        String finalString ="";
        for (String dict : dictionary){
            for(int i =0;i<sentenceArray.length;i++){
                if(sentenceArray[i].startsWith(dict)){
                    sentenceArray[i]=dict;
                }
            }
        }
        for(int i =0;i<sentenceArray.length;i++){
            if(i==0){
                finalString=sentenceArray[i];
            }else{
                finalString=finalString+" "+sentenceArray[i];
            }

        }
        return finalString;
    }

    public static void main(String[] args) {
       int[] difficulty  = {85,47,57};
       int[] profit = {24,66,99};
       int[] worker ={8,2,4,7};
        System.out.println(longestSubarray(worker,4));
    }



    public static int longestSubarray(int[] nums, int limit) {
        int ans =0;
        TreeMap<Integer,Integer> treeMap = new TreeMap<>();
        int i=0;
        int j =0;
        int n = nums.length;

        while(j<n){
            treeMap.put(nums[j],treeMap.getOrDefault(nums[j],0)+1);
            int minVal= treeMap.firstKey();
            int maxVal = treeMap.lastKey();

            int diff = maxVal-minVal;

            while(diff>limit && i<=j){
                int count = treeMap.get(nums[i]);
                if(count==1){
                    treeMap.remove(nums[i]);
                }else{
                    count--;
                    treeMap.put(nums[i],count);
                }

                minVal= treeMap.firstKey();
                maxVal= treeMap.lastKey();
                diff= maxVal-minVal;
                i++;
            }

            System.out.println(" i "+i+" j "+j);

            int len = j-i+1;
             ans= Math.max(ans,len);
            j++;
        }
        return ans;

    }
    public static int numberOfSubarrays(int[] nums, int k) {
        int ans=returnAtMost(nums,k)-returnAtMost(nums,k-1);
        return ans;
    }


    public static int returnAtMost(int[]nums, int k){
        for(int i =0;i<nums.length;i++){
            nums[i]= nums[i]%2==0?0:1;
        }

        int i =0,j=0,ans =0;
        int n =nums.length;
        int cnt =0;
        while(j<n){
            cnt+=nums[j];

            while(cnt>k && i<=j){
                cnt-=nums[i];
                i++;
            }

            ans+=j-i+1;
            j++;
        }
        return ans;
    }



    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int totalCustomers =0;
        for(int i =0;i<grumpy.length;i++){
            if(grumpy[i]==0){
                totalCustomers=totalCustomers+customers[i];
            }
        }

        int additionalCustomers= 0;
        for(int i =0;i<grumpy.length;i++){
            int start =i;
            int end =(start+minutes)-1;
            end = end>=grumpy.length ?grumpy.length-1:end;
            int grumpedCustomers =0;
            System.out.println("start "+start+" end "+end);
            for(int j =start;j<=end;j++){
                if(grumpy[j]==1){
                    grumpedCustomers=grumpedCustomers+customers[j];
                }
            }
            additionalCustomers=Math.max(additionalCustomers,grumpedCustomers);

        }

        return totalCustomers+additionalCustomers;
    }

    public int largestRectangleArea(int[] heights) {
        int maxArea =0;
        int[] left = new int[heights.length];
        int[] right = new int[heights.length];

        Stack<Integer> stack = new Stack<>();
        for(int i =0;i<heights.length;i++){
            if(stack.isEmpty() && i ==0){
                left[i]= 0;
                stack.push(i);
            }else{
                while (!stack.isEmpty() && heights[stack.peek()]>= heights[i]){
                    stack.pop();
                }
                left[i]= stack.isEmpty()?0:stack.peek()+1;
                stack.push(i);
            }

        }
        System.out.println(Arrays.toString(left));
        while(!stack.isEmpty()){
            stack.pop();
        }

        for(int i =heights.length-1;i>=0;i--){
            if(stack.isEmpty()){
                right[i] = i;
                stack.push(i);
            }else{
                while(!stack.isEmpty() && heights[stack.peek()]>=heights[i]){
                    stack.pop();
                }
                right[i]=stack.isEmpty()?heights.length-1:stack.peek()-1;
                stack.push(i);
            }
        }

        System.out.println(Arrays.toString(right));
        for(int i =0;i<heights.length;i++){
            int area = heights[i]*((right[i]-left[i])+1);
            maxArea =Math.max(maxArea,area);
        }
        return maxArea;
    }

    public int maxDistance(int[] position, int m) {
        int ans =0;
        Arrays.sort(position);
        int start =1;
        int end = (position[position.length-1]-position[0]);

        while(start<=end){
            int mid = start+(end-start)/2;
            if(canPlace(position,m,mid)){
                ans =mid;
                start= mid+1;
            }else{
                end = mid-1;
            }

        }
        return ans;
    }

    public boolean canPlace(int[] position, int m, int diff){
        int totalBalls =1;
        int last = position[0];
        for(int i =1;i<position.length;i++){
            if(position[i]-last<=diff){
                totalBalls++;
                last= position[i];
            }

            if(totalBalls==m){
                return true;
            }
        }

        return false;
    }

    public int minDays(int[] bloomDay, int m, int k) {
        long totalFlowers = m*k;
        int minDays =0;
        if(bloomDay.length<totalFlowers){
            return -1;
        }

        int start =1 ;
        int end = Arrays.stream(bloomDay).max().getAsInt();
        while(start<=end){
            int mid = start+(end-start)/2;
            if(bookiesCanMade(bloomDay,m,k,mid)){
                minDays=mid;
                end =mid-1;
            }else{
                start=mid+1;
            }
        }

        return minDays;
    }


    public boolean  bookiesCanMade(int[] bloomDay,int m ,int k,int day){
        int con =0;
        int totalBookies =0;
        for(int i=0;i<bloomDay.length;i++){
            if(bloomDay[i]<=day){
                con++;
            }else{
                con=0;
            }

            if(con ==k){
                totalBookies++;
                con =0;
            }

        }

        return totalBookies==m;
    }

    public static int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        Map<Integer,Integer> profitVSDifficultyMap = new HashMap<>();
        int totalProfit =0;
        for(int i=0;i<profit.length;i++){
            if(profitVSDifficultyMap.get(profit[i])!=null){
                int minDifficultyRequired = Math.min(profitVSDifficultyMap.get(profit[i]),difficulty[i]);
                profitVSDifficultyMap.put(profit[i],minDifficultyRequired);
            }else{
                profitVSDifficultyMap.put(profit[i],difficulty[i]);
            }

        }

        Arrays.sort(profit);
        System.out.println(Arrays.toString(difficulty));
        for(int work:worker){
            for(int i = profit.length-1;i>=0;i--){
                if(profitVSDifficultyMap.get(profit[i])<=work){
                    totalProfit=totalProfit+profit[i];
                }
            }

        }
        return totalProfit;
    }


    public static int binarySearch(int target,int[] arr){

        int start =0;
        int end = arr.length-1;

        while(start <= end ){
            int mid = (start+end)/2;
            if(target == arr[mid]){
                return arr[mid];
            }
            else if(target >arr[mid]){
                start = mid+1;
            }
            else if(target <arr[mid]){
                end = mid-1;
            }

        }

        if(end<0){
            return -1;
        }
        return arr[end];
    }


    public static  int count=0;
    public static int subarraysDivByK(int[] nums, int k) {


        subArrayRecurrsion(nums,0,0,k);
        int finalCount =count;
        count=0;
        return finalCount;

    }

    public static void subArrayRecurrsion(int[] array, int start,int end,int k ){
        if(end ==array.length){
            return;
        }else if (start>end){
            subArrayRecurrsion(array, 0,end+1,k);
        }else{
            addToSubArrayList(array,start,end,k);
            subArrayRecurrsion(array,start+1,end,k);
        }
    }


    public static void addToSubArrayList(int[] array,int start,int end,int k ){
        int sum =0;
        for(int i =start;i<=end;i++){
            sum =sum+array[i];
        }
        if(sum%k ==0){
            count++;
        }
    }


    public int minMovesToSeat(int[] seats, int[] students) {
        Arrays.sort(seats);
        Arrays.sort(students);

        int count =0;
        for(int i =0;i<seats.length;i++){
            if(students[i]<seats[i]){
                count =count+(seats[i]-students[i]);
            }else if (students[i]>seats[i]){
                count = count+(students[i]-seats[i]);
            }

        }

        return count;

    }


    public int minIncrementForUnique(int[] nums) {
        Arrays.sort(nums);

        int count =0;
        int maxNum =nums[0];
        for(int i =1;i<nums.length;i++){
            if(nums[i]==maxNum){
                count++;
                maxNum++;
            }else if(nums[i]<maxNum){
                count =count+(maxNum-nums[i])+1;
                maxNum++;

            }else{
                maxNum= nums[i];
            }
        }

        return count;
    }

}
