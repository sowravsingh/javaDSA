package LeetcodeProblems;

import java.util.*;

public class LeetCodeProblems1 {


    public static void main(String[] args) {
        LeetCodeProblems1 ll = new LeetCodeProblems1();
        List<List<Integer>> mainList = new ArrayList<>();

        List<Integer> ll1 = Arrays.asList(-5,-4,-3,-2,-1,1);
        List<Integer> ll2 = Arrays.asList(1,2,3,4,5);
//        List<Integer> ll3 = Arrays.asList(1,2,3);
        mainList.add(ll1);
        mainList.add(ll2);
        int[][] nums = {{3,2,4},{2,1,9},{1,1,7}};
        int[] nums2 ={4,8,21,3,8,12,5};


       ll.reverseArray(nums2,3);

    }


    public void reverseArray(int[] nums, int k){

        int index =0;
        while(index<nums.length){
            int lastIndex =Math.min(index+k-1,nums.length-1);
            int mid = (index+lastIndex)/2;

            int minus=0;
            for (int i =index;i<=mid;i++){
                int temp = nums[i];
                nums[i]= nums[lastIndex-minus];
                nums[lastIndex-minus]=temp;
                minus++;
            }

            index= index+k;
        }
        System.out.println(Arrays.toString(nums));
    }



    public int minChanges(String s) {
        int minimumChanges =0;
        for (int i =0;i<s.length();i=i+2){
           char c1 = s.charAt(i);
           char c2 = s.charAt(i+1);
            if (c1!=c2){
                minimumChanges++;
            }
        }

        return minimumChanges;
    }

    public String compressedString(String word) {
        StringBuilder sb = new StringBuilder();
        char prevChar = word.charAt(0);
        int prevValue =1;

        for (int i =1;i<word.length();i++){
            if (prevChar==word.charAt(i)){
                prevValue++;
            }else {
                while (prevValue>9){
                    sb.append(9);
                    sb.append(prevChar);
                    prevValue=prevValue-9;
                }
                sb.append(prevValue);
                sb.append(prevChar);
                prevChar = word.charAt(i);
                prevValue =1;
            }
        }

        while (prevValue>9){
            sb.append(9);
            sb.append(prevChar);
            prevValue=prevValue-9;
        }
        sb.append(prevValue);
        sb.append(prevChar);

        return sb.toString();
    }


    public boolean rotateString(String s, String goal) {

        if (s.length()!=goal.length()){
            return false;
        }
        if (s.endsWith(goal)){
            return true;
        }
        StringBuilder sb = new StringBuilder(s);

        for (int i =0;i<s.length();i++){
            sb = new StringBuilder(sb.substring(1));
            sb.append(s.charAt(i));
            if (sb.toString().equalsIgnoreCase(goal)){
                return true;
            }
        }
        return false;
    }

    public boolean isCircularSentence(String sentence) {
        String[] strArray = sentence.split(" ");
        if (strArray.length==1){
            return strArray[0].charAt(0)==strArray[0].charAt(strArray[0].length()-1);
        }
        char prevChar =strArray[0].charAt(strArray[0].length()-1);
        for (int i =1;i<strArray.length;i++){
            char firstChar = strArray[i].charAt(0);
            if (firstChar!=prevChar){
                return false;
            }
            prevChar= strArray[i].charAt(strArray[i].length()-1);
        }

        return strArray[0].charAt(0)==prevChar;

    }

    public String makeFancyString(String s) {
        StringBuffer sb = new StringBuffer();
        for(int i =0;i<s.length();i++){
            char c = s.charAt(i);
            if (i ==0 || i==1){
                sb.append(c);
            }else{
                if(c != s.charAt(i-1) || c!= s.charAt(i-2)){
                    sb.append(c);
                }
            }

        }

        return sb.toString();
    }

    public int maxMoves(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];

        int maxMoves =0;
        int totalColumns = grid[0].length;

        for (int col = totalColumns-1;col>=0;col--){
            for (int row=0;row< grid.length;row++){
              //  System.out.println(row+ ",, "+col);
                dp[row][col]=returnMaxMoves(grid,dp,row,col,grid[row][col]);

            }
        }

        for (int i =0;i<grid.length;i++){
            maxMoves= Math.max(maxMoves,dp[i][0]);
        }
        return maxMoves-1;
    }


    public int returnMaxMoves(int[][] grid,int[][] dp,int row, int col, int num){
        int maxLen =0;

        if(row+1<grid.length && col+1<grid[0].length){
            if(grid[row+1][col+1]>num){
                maxLen= Math.max(maxLen,dp[row+1][col+1]);
            }
        }

        if(col+1<grid[0].length){
            if(grid[row][col+1]>num){
                maxLen= Math.max(maxLen,dp[row][col+1]);
            }
        }

        if(row-1>=0 && col+1<grid[0].length){
           // System.out.println(row+ ","+col);
            if(grid[row-1][col+1]>num){
                maxLen= Math.max(maxLen,dp[row-1][col+1]);
            }
        }

        return maxLen+1;
    }


    int maxStreak = -1;
    Map<Integer, Integer> rangeMap = new HashMap<>();

    public int longestSquareStreak(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        for (int i = 0; i < nums.length; i++) {
            if (rangeMap.containsKey(nums[i])) {
                continue;
            } else {
                int streakNumber = longestSquareStreakRecur((long) nums[i] * nums[i], set); // Convert to long here
                rangeMap.put(nums[i], streakNumber + 1);
                maxStreak = Math.max(maxStreak, streakNumber + 1);
            }
        }

        return maxStreak == 1 ? -1 : maxStreak;
    }

    public int longestSquareStreakRecur(long num, Set<Integer> set) { // Change num type to long
        if (rangeMap.containsKey((int) num)) { // Cast back to int for the map
            return rangeMap.get((int) num);
        }

        if (set.contains((int) num)) { // Cast back to int for the set check
            int squareStreak = longestSquareStreakRecur(num * num, set); // Use long multiplication here
            rangeMap.put((int) num, squareStreak + 1);
            maxStreak = Math.max(maxStreak, squareStreak + 1);
            return squareStreak + 1;
        }

        return 0;
    }



    int maxCount =0;
    public int maxUniqueSplit(String s) {
        maxUniqueSplitRecurrsion(0,new HashSet<>(),s);
        return maxCount;
    }


    public void maxUniqueSplitRecurrsion(int index,Set<String> subStringSet,String s){
        if (index==s.length()){
            maxCount= Math.max(maxCount,subStringSet.size());
        }


        StringBuilder sb = new StringBuilder();
        for (int i = index;i<s.length();i++){
            sb.append(s.charAt(i));
            if(subStringSet.add(sb.toString())){
                maxUniqueSplitRecurrsion(i+1,subStringSet,s);
                subStringSet.remove(sb.toString());
            }
        }


    }




    public int countMaxOrSubsets(int[] nums) {
        if (nums.length==0){
            return 0;
        }
        int maximumOrValue =0;
        for (int num : nums){
            maximumOrValue=maximumOrValue|num;
        }
        recursionSubset(nums,new ArrayList<>(),0,0,maximumOrValue);
        return totalSubsetsCount;
    }

    int totalSubsetsCount=0;
    public void recursionSubset(int[] nums, List<Integer> list, int i, int OrValue, int maximumOrvValue ){
        if (i == nums.length){
            if (OrValue==maximumOrvValue){
                totalSubsetsCount++;
            }
            return;
        }


        list.add(nums[i]);
        recursionSubset(nums,list,i+1,OrValue|nums[i],maximumOrvValue);
        list.remove(list.size()-1);
        recursionSubset(nums,list,i+1,OrValue,maximumOrvValue);


    }
    public long minimumSteps(String s) {
        Long totalSwaps=0L;
        Long totalZeros =0L;
       for (int i = s.length()-1;i>=0;i--){
           if (s.charAt(i)=='0'){
               totalZeros++;
           }else {
               totalSwaps=totalSwaps+totalZeros;
           }
       }


        return totalSwaps;
    }

    public long maxKelements(int[] nums, int k) {
        Long result =0L;
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer integer, Integer t1) {
                return t1-integer ;
            }
        });

        for (int num :nums){
            pq.offer(num);
        }


        while (k!=0){
            int num=pq.poll();
            result=result+num;
            num= (int) Math.ceil((double)num/3);
            pq.offer(num);
            k--;
        }

        return result;
    }

    public int[] smallestRange(List<List<Integer>> nums) {

        int[] resultArr = new int[2];
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] ints, int[] t1) {
                return ints[0]-t1[0];
            }
        });


        int maxElement= Integer.MIN_VALUE;
        for (int i =0;i<nums.size();i++){
            List<Integer> rowList = nums.get(i);
            int[] minElement= new int[3];
            minElement[0]= rowList.get(0);
            maxElement = Math.max(maxElement,minElement[0]);
            minElement[1]= i;
            minElement[2]= 0;
            pq.offer(minElement);
        }


       int[] minElement =pq.poll();
        int minDiff = Math.abs(maxElement-minElement[0]);
        resultArr[0]= minElement[0];
        resultArr[1]=maxElement;


       while (!pq.isEmpty()){

           int minMaxDiff =Math.abs(maxElement-minElement[0]);

           if (minDiff>minMaxDiff){
               minDiff= minMaxDiff;
               resultArr[0]= minElement[0];
               resultArr[1]= maxElement;
           }


           int row = minElement[1];
           int col = minElement[2];

           List<Integer> rowList = nums.get(row);
           if(col+1== rowList.size()){

               return resultArr;
           }else {
               int[] newMinElement = new int[3];
               newMinElement[0]= rowList.get(col+1);
               maxElement=Math.max(maxElement,newMinElement[0]);
               newMinElement[1]=row;
               newMinElement[2]=col+1;
               pq.offer(newMinElement);
           }


           minElement= pq.poll();
       }

        return resultArr;
    }

    public int minSwaps(String s) {
//        String[] arr = s.split("");
        Stack<String> st = new Stack<>();
        int minSwaps =0;
        int end = s.length()-1;
        for (int i =0;i<s.length();i++){
            if (i == end){
                return minSwaps;
            }


            if (s.charAt(i) =='['){
                st.push("[");
            }else {
                if (st.isEmpty()){
                    while (s.charAt(end)==']'){
                        end--;
                    }
                    minSwaps++;
                    st.push("[");

                }else {
                    st.pop();
                }
            }

        }
        return minSwaps;
    }


}
