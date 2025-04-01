package LeetcodeProblems;

import java.util.*;

public class LeetCodeProblems4 {


    public static void main(String[] args) {

        LeetCodeProblems4 ll4 = new LeetCodeProblems4();
        int[] nums ={5,3,8,2,6,1,4,6};

        int[][] arr= {{0,7},{3,5},{5,2},{3,0},{1,6}};
        //System.out.println(Math.ceil((float) a/b));
      // System.out.println(ll4.maxTwoEvents(arr));
       // System.out.println(ll4.maximumBeauty(nums,29));

     //   System.out.println((char)97);
       System.out.println(Arrays.toString(ll4.leftmostBuildingQueries(nums,arr)));
    }


    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        int[] nextGreaterIndex = new int[heights.length];
        int[] result = new int[queries.length];
        Arrays.fill(nextGreaterIndex,-1);

        Stack<Integer> stack = new Stack<>();
        for (int i =heights.length-1;i>=0;i--){

            while (!stack.isEmpty() && heights[stack.peek()]<=heights[i]){
                stack.pop();
            }


            if (!stack.isEmpty()){
                nextGreaterIndex[i]=stack.peek();
            }

            stack.push(i);

        }

        System.out.println(Arrays.toString(nextGreaterIndex));

        int index =0;
        for (int[] query :queries){

            if(query[0] == query[1]){
                result[index]=query[0];
                index++;
                continue;
            }


            int bigIndex = Math.max(query[0],query[1]);
            int smallIndex = Math.min(query[0],query[1]);


            if(heights[bigIndex] >= heights[smallIndex]){
                result[index]=bigIndex;
                index++;
                continue;
            }


            if(nextGreaterIndex[bigIndex] != -1 &&  heights[nextGreaterIndex[bigIndex]]>=heights[smallIndex]){
                result[index]=nextGreaterIndex[bigIndex];
            }else if(nextGreaterIndex[smallIndex] != -1 &&  nextGreaterIndex[smallIndex]>=bigIndex){
                System.out.println("came here for index "+index);
                result[index]=nextGreaterIndex[smallIndex];
            }else {
                result[index]=-1;
            }



            index++;

        }


        return result;
    }


    public String repeatLimitedString(String s, int repeatLimit) {

        int[][] freqArray= new int[26][2];
        for (int i =0;i<s.length();i++){
            char ch= s.charAt(i);
            freqArray[ch-'a'][0]=ch-'a';
            freqArray[ch-'a'][1]=  freqArray[ch-'a'][1]+1;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((int[] a, int[] b) ->{
            if (b[0]==a[0]){
                return b[1]-a[1];
            }else {
                return b[0]-a[0];
            }
        });


        for (int[] arr :freqArray){
            if (arr[1]!=0){
                pq.offer(arr);
            }
        }

        StringBuffer sb = new StringBuffer();
        while (!pq.isEmpty()){
            int[] arr = pq.poll();
           // System.out.println(Arrays.toString(arr));
            int freq = arr[1];
            char ch =  (char) (arr[0]+97);
            if (freq-repeatLimit>0){
                for (int i =0;i<repeatLimit;i++){
                    sb.append(ch);
                }

                while (!pq.isEmpty() && pq.peek()[1]==0){
                    pq.poll();
                }

                if (!pq.isEmpty()){
                    int[] nextArr= pq.poll();
                    sb.append((char) (nextArr[0]+97));
                    nextArr[1]= nextArr[1]-1;
                    int[] reducedArr = {arr[0],freq-repeatLimit};
                    pq.offer(reducedArr);
                    pq.offer(nextArr);
                }



            }else {
                for (int i =0;i<freq;i++){
                    sb.append(ch);
                }
            }


        }

        return sb.toString();
    }
    public long continuousSubarrays2(int[] nums) {
        int n = nums.length;
        int left = 0;
        int rangeMin = Integer.MAX_VALUE;
        int rangeMax = Integer.MIN_VALUE;

        long count = 0;
        long winSize;
        int right;

        for (right = 0; right < n; ++right) {
            rangeMin = Math.min(rangeMin, nums[right]);
            rangeMax = Math.max(rangeMax, nums[right]);

            if (rangeMax - rangeMin > 2) {
                System.out.println("came here");
                winSize = right - left;
                count += (winSize * (winSize + 1)) / 2;

                left = right;
                // Expand current window to as left as possible
                rangeMin = nums[right];
                rangeMax = nums[right];
                while (left > 0 && Math.abs(nums[right] - nums[left - 1]) <= 2) {
                    left--;
                    rangeMin = Math.min(rangeMin, nums[left]);
                    rangeMax = Math.max(rangeMax, nums[left]);
                }
                // Subtract overcounted subarrays
                if (left < right) {
                    winSize = right - left;
                    count -= (winSize * (winSize + 1)) / 2;
                }
            }
        }
     //   System.out.println(count);
        // Add subarrays from the last window
        winSize = right - left;
        count += (winSize * (winSize + 1)) / 2;

        return count;
    }

    public long continuousSubarrays(int[] nums) {
       long ans =0;

       int max= Integer.MIN_VALUE;
       int min =Integer.MAX_VALUE;
       int start =0;
       int end =0;
       while (end<nums.length){
           max = Math.max(max,nums[end]);
           min =Math.min(min,nums[end]);

           if(max-min>2){
               System.out.println("came here");
               long length = end-start;
               long subArrays = ((length)*(length+1))/2;
               ans=ans+subArrays;

               start=end;
               max= nums[end];
               min = nums[end];
               while (start>0 && Math.abs(nums[end]- nums[start-1])<=2){
                   start--;
                   max = Math.max(max,nums[start]);
                   min =Math.min(min,nums[start]);

               }


               if(start<end){
                   length = end-start;
                   subArrays = ((length)*(length+1))/2;
                   ans=ans-subArrays;
               }

           }

           end++;
       }

     //   System.out.println(ans);
        long length = end-start;
        long subArrays = ((length)*(length+1))/2;
        ans=ans+subArrays;

       return ans;

    }



    public long findScore(int[] nums) {
        boolean[] isVisited = new boolean[nums.length];

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) ->{
            if (a[0]!=b[0]){
                return a[0]-b[0];
            }else {
                return a[1]-b[1];
            }
        });

        for (int i =0;i<nums.length;i++){
            int[] arr = {nums[i],i};
            pq.offer(arr);
        }

        long sum=0;
        while (!pq.isEmpty()){
            int[] arr = pq.poll();
            if (!isVisited[arr[1]]) {
                sum = sum+arr[0];
                isVisited[arr[1]]=true;
                if(arr[1]+1!=nums.length){
                    isVisited[arr[1]+1]=true;
                }
                if(arr[1]-1>=0){
                    isVisited[arr[1]-1]=true;
                }
            }


        }

        return sum;
    }

    public long pickGifts(int[] gifts, int k) {

        Long sum =0L;

        PriorityQueue<Integer> pq = new PriorityQueue<>(( a, b) ->{
            return b-a;
        });


        for (int  num : gifts){
            pq.offer(num);
        }


        while (k>0){
            if (!pq.isEmpty()){
                int num = pq.poll();
                pq.offer((int) Math.floor(Math.sqrt(num)));
            }
            k--;
        }


        while (!pq.isEmpty()){
            sum=sum+pq.poll();
        }
        return sum;


    }

    public int maximumBeauty(int[] nums, int k) {

        Arrays.sort(nums);
        int maxLen =0;

        if (nums.length==1){
            return 1;
        }


        for (int i =0;i<nums.length;i++){
            int maxNumber = nums[i]+(2*k);
           // System.out.println(maxNumber);
            int endIndex = getLEastIndex(i,nums.length-1,nums,maxNumber);
            System.out.println(endIndex);
            maxLen = Math.max(maxLen,(endIndex-i)+1);
        }

        return maxLen;

    }


    public int getLEastIndex(int start , int end , int[] arr, int target){

        while(start<=end){
            int mid = (start+end)/2;

            if(arr[mid]>target){
                end = mid-1;
            }else{
                start= mid+1;
            }
        }

        return end;
    }


    Map<String,Integer> strVsCntMap = new HashMap<>();
    public int maximumLength(String s) {

        char prevChar = s.charAt(0);
        strVsCntMap.put(String.valueOf(prevChar),1);
        int start =0;

        for (int i =1;i<s.length();i++){
            char currentChar=  s.charAt(i);
            strVsCntMap.put(String.valueOf(currentChar),strVsCntMap.getOrDefault(String.valueOf(currentChar),0)+1);

            if (currentChar == prevChar){
                shrinkString(start,i,s);
            }else {
                prevChar=currentChar;
                start=i;
            }

        }

        int maxLen =-1;
        for (String str :strVsCntMap.keySet()){
            if (strVsCntMap.get(str)>=3){
                maxLen= Math.max(maxLen,str.length());
            }
        }

       return maxLen;

    }

    public void shrinkString(int start, int end, String s){

        while (start<end){
            String newString =s.substring(start,end+1);
            strVsCntMap.put(newString,strVsCntMap.getOrDefault(newString,0)+1);
            start++;
        }
    }

    public int minimumSize(int[] nums, int maxOperations) {
        int start =1;
        int end = Arrays.stream(nums).max().getAsInt();

        int ans= end;

        while(start<=end){
            int mid = (start+end)/2;
            if (isvalid(nums,maxOperations,mid)){
                ans=mid;
                end = mid-1;
            }else {
                start = mid+1;
            }
        }

        return ans;
    }


    public boolean isvalid(int[] nums , int maxOperations, int mid){

      int max =0;
        for (int num :nums){

            //System.out.println(" before "+num);
           max+= (int) Math.ceil(num/(double) mid)-1;

           // System.out.println(" after "+num);
            if (max>maxOperations){
                return false;
            }
        }
        return true;
    }

    public int maxCount(int[] banned, int n, int maxSum) {
        Set<Integer> set = new HashSet<>();
        for (int num :banned){
            set.add(num);
        }

        int maxCount =0;
        int sum=0;
        if (maxSum==0) return 0;
        for (int i =1;i<=n;i++){

            if (set.contains(i)){
                continue;
            }

            sum= sum+i;
            if (sum>maxSum){
                return maxCount;
            }

            maxCount++;


        }

        return maxCount;
    }


    public int maxTwoEvents(int[][] events) {
        Arrays.sort(events ,(a,b) ->(a[0]-b[0]));

        int[] suffixMax = new int[events.length];
        suffixMax[events.length-1]= events[events.length-1][2];

        for (int i =events.length-2;i>=0;i--){
            suffixMax[i]= Math.max( suffixMax[i+1],events[i][2]);
        }


        int maxEvents = 0;
        for (int i =0;i<events.length;i++){
            int totalEvent =events[i][2];
            int index= getLeastMax(i+1,events.length-1,events,events[i][1]+1);
            if (index!=events.length){
                totalEvent = totalEvent+suffixMax[index];
            }

            maxEvents= Math.max(maxEvents,totalEvent);
        }


        return maxEvents;
    }

    public int getLeastMax(int start, int end, int[][] events, int num){

        while (start<=end){
            int mid = (start+end)/2;

            if (events[mid][0]<num){
                start= mid+1;
            }else{
                end = mid-1;
            }
        }

        return start;
    }
}
