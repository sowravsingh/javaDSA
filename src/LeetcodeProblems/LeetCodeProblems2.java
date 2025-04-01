package LeetcodeProblems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Stream;

public class LeetCodeProblems2 {

    public static void main(String[] args) {
        LeetCodeProblems2 ll = new LeetCodeProblems2();
        int[] nums ={18,9,9,12,12,18};
        int[][] guards ={{0,0},{1,1},{2,3}};
        int[][] matrix ={{0,1},{1,0}};
        System.out.println(ll.maxEqualRowsAfterFlips(matrix));
//        int ageOfCrop=10;
//        int ActualTotalAge=15;
//        int NormalTotalAge=103;
//        ageOfCrop=Math.round((ageOfCrop/ActualTotalAge)*NormalTotalAge);
     //   System.out.println(ageOfCrop);

    }


    public char[][] rotateTheBox(char[][] box) {
        char[][] resultArr =new char[box[0].length][box.length];
        int m = box.length;
        for (int i =0;i<resultArr.length;i++){
            for (int j =0;j<resultArr[0].length;j++){
                resultArr[i][j] = box[m-1-j][i];
            }
        }


        for (int col =0;col<resultArr[0].length;col++){
            int emptyCell = resultArr.length-1;
            for (int row = resultArr.length-1;row>=0;row--){
                if (resultArr[row][col] =='*'){
                    emptyCell= row-1;
                }else if (resultArr[row][col] =='#'){

                    if (emptyCell== row){
                        emptyCell=emptyCell-1;
                    }else {
                        resultArr[emptyCell][col]= '#';
                        resultArr[row][col]='.';
                        emptyCell--;
                        while (emptyCell>=row && resultArr[emptyCell][col] !='.'){
                            emptyCell--;
                        }
                    }

                }


            }
        }



        return resultArr;
    }



    public int maxEqualRowsAfterFlips(int[][] matrix) {
        HashMap<String,Integer> patternMap = new HashMap<>();

        for (int[] row : matrix){
            StringBuilder normalSb =new StringBuilder();
            StringBuilder complimentedSb = new StringBuilder();
            for (int cell : row){
                normalSb.append(cell);
                complimentedSb.append(cell==0?1:0);
            }

            patternMap.put(normalSb.toString(),patternMap.getOrDefault(normalSb.toString(),0)+1);
            patternMap.put(complimentedSb.toString(),patternMap.getOrDefault(complimentedSb.toString(),0)+1);
        }

        int maxRows=0;
        for (String pattern : patternMap.keySet()){
            maxRows= Math.max(maxRows,patternMap.get(pattern));
        }

        return maxRows;
    }


    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {

        int[][] gridBoard = new int[m][n];
        int totalUnguardedCells =0;

        for (int[] wall : walls){
            gridBoard[wall[0]][wall[1]] =3;
        }

        for (int[]guard :guards){
            gridBoard[guard[0]][guard[1]] =2;
        }


        for (int[] guard :guards){
            int row = guard[0];
            int col = guard[1];
        //    System.out.println( "guard row is "+ row+ " col is "+col);



            // making all it's left cells as guarded
            col = guard[1]-1;
            while (col>=0 && gridBoard[row][col] !=2 && gridBoard[row][col] !=3){
                gridBoard[row][col]=1;
                col--;
            }

            // making all it's  cells as guarded
            col = guard[1]+1;
            while (col<n &&  gridBoard[row][col] !=2 && gridBoard[row][col] !=3){
                gridBoard[row][col]=1;
                col++;
            }


            // making all it's top cells as guarded
            row = guard[0]-1;
            col =guard[1];
            while (row>=0 &&  gridBoard[row][col] !=2 && gridBoard[row][col] !=3){
                gridBoard[row][col]=1;
                row--;
            }

            // making all it's down cells as guarded
            row = guard[0]+1;

            while (row<m &&  gridBoard[row][col] !=2 && gridBoard[row][col] !=3){
                gridBoard[row][col]=1;
                row++;
            }

        }


        for (int[] row : gridBoard){
            //System.out.println(Arrays.toString(row));
            for (int cel :row){
                if (cel==0){
                    totalUnguardedCells++;
                }
            }
        }

        return totalUnguardedCells;
    }



    public int takeCharacters(String s, int k) {
        Map<Character,Integer> frequencyMap= new HashMap<>();
        for (char c:s.toCharArray() ){
            frequencyMap.put(c,frequencyMap.getOrDefault(c,0)+1);
        }

        if (!isCharactersMatchingK(k,frequencyMap)){
            return -1;
        }

        int minimumMinutes = s.length();
        int start=0;
        int end =0;
        while (end<s.length()){
            frequencyMap.put(s.charAt(end),frequencyMap.getOrDefault(s.charAt(end),0)-1);

            if (isCharactersMatchingK(k,frequencyMap)){
                minimumMinutes=Math.min(minimumMinutes,(s.length()-(end-start+1)));
            }else {
                while (start<=end){
                    frequencyMap.put(s.charAt(start),frequencyMap.getOrDefault(s.charAt(start),0)+1);
                    start++;
                    if (isCharactersMatchingK(k,frequencyMap)) {
                        minimumMinutes = Math.min(minimumMinutes,(s.length()-(end-start+1)));
                        break;
                    }
                }
            }

            end++;
        }

        return minimumMinutes;
    }

    public boolean isCharactersMatchingK(int k ,  Map<Character,Integer> frequencyMap){

        if (frequencyMap.getOrDefault('a',0)<k || frequencyMap.getOrDefault('b',0)<k || frequencyMap.getOrDefault('c',0)<k ){
            return false;
        }
        return true;
    }

    public long maximumSubarraySum(int[] nums, int k) {
        Map<Integer,Integer> numberVsMap = new HashMap<>();

        long maxSum = 0;
        int start =0;
        long currrentSum =0;
        for (int i= 0;i<nums.length;i++){
            int index=numberVsMap.getOrDefault(nums[i],-1);
           while (index>=start || i-start+1 >k){
               currrentSum-=nums[start++];
           }



            currrentSum=currrentSum+nums[i];

            if (i-start+1==k){
                maxSum= Math.max(maxSum,currrentSum);
            }
            numberVsMap.put(nums[i],i );


        }

        return maxSum;

    }

    public int shortestSubarray(int[] nums, int k) {
        PriorityQueue<Long[]> pq = new PriorityQueue<>((x,y) ->{
            return Long.compare(x[0],y[0]);
        });
        int minShortLength=Integer.MAX_VALUE;
        Long prefixSum =0L;

        for (int i=0;i<nums.length;i++){
            prefixSum=prefixSum+ nums[i];
            if (prefixSum>= k){
                minShortLength= Math.min(minShortLength,i+1);
            }


            while ( !pq.isEmpty() && prefixSum-pq.peek()[0]>=k){
               Long[] arr = pq.poll();
                System.out.println(Arrays.toString(arr));
                minShortLength= (int) Math.min(minShortLength,(i-arr[1]));

            }
            Long[] arr = { prefixSum, (long) i};
            pq.offer(arr);

        }



        return minShortLength==Integer.MAX_VALUE?-1:minShortLength;

    }

    public int[] resultsArray(int[] nums, int k) {
        int[] resultArray = new int[nums.length-k+1];
        for (int i =0;i<=nums.length-k;i++){

            int index = i+1;
            int prevElement = nums[i];
            while(index<i+k){
                if (nums[index]!= prevElement+1){
                    prevElement=-1;
                    break;
                }

                prevElement= nums[index];

                index++;
            }

            resultArray[i]=prevElement;
        }


       return resultArray;
    }



    public int findLengthOfShortestSubarray(int[] arr) {
        int start =0;
        int end = arr.length-1;  //6


        // find the increasing order from 0
        while (start<arr.length-1 && arr[start]<=arr[start+1]){
            start++;
        }

        System.out.println(start);
        int shortLength = end - start;

        while(start<end){
            while (start>0 && arr[end]<arr[start]){
                start--;
            }

            if (start==0 && arr[start]>arr[end]){
                shortLength= Math.min(shortLength,end-start);
            }else{
                shortLength= Math.min(shortLength,end-start-1);
            }
            System.out.println("start is "+start+ "  end is "+end);
//            System.out.println("*****");
            end--;
            if (arr[end]>arr[end+1]){
                break;
            }
        }


        return shortLength;
    }




    public long countFairPairs(int[] nums, int lower, int upper) {
        int n = nums.length;
        Arrays.sort(nums);

        long ans = 0;
        for (int i = 0; i < n - 1; ++i) {
            int lb = lowerBound(nums, i + 1, n, lower - nums[i]);
            int ub = upperBound(nums, i + 1, n, upper - nums[i]);
            ans += (ub - lb);
        }
        return ans;
    }

    private int lowerBound(int[] nums, int start, int end, int target) {
        while (start < end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] < target) start = mid + 1;
            else end = mid;
        }
        return start;
    }

    private int upperBound(int[] nums, int start, int end, int target) {
        while (start < end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] <= target) start = mid + 1;
            else end = mid;
        }
        return start;
    }

    private long countLess(int[] nums, int sum) {
        long res = 0;
        for (int i = 0, j = nums.length - 1; i < j; ++i) {
            while (i < j && nums[i] + nums[j] > sum)
                --j;
            res += j - i;
        }
        return res;
    }


    public int[] maximumBeauty(int[][] items, int[] queries) {
        int[] result = new int[queries.length];
        Arrays.sort(items, (a, b) -> {
            int primaryComparison = Integer.compare(a[0], b[0]);
            return (primaryComparison != 0) ? primaryComparison : Integer.compare(b[1], a[1]);
        });
        int maxElement = items[0][1];

        for (int i =0;i< items.length;i++){
            items[i][1] = Math.max(maxElement,items[i][1]);
            maxElement=Math.max(maxElement,items[i][1]);
        }

//        for (int[] item :items){
//            System.out.println(Arrays.toString(item));
//        }


        for(int i=0; i<queries.length;i++){
            int index= getIndex(queries[i],items);
            result[i]=index==-1?0:items[index][1];

        }
        return result;
    }

    public int getIndex(int num, int[][] nums){
        int start=0;
        int end = nums.length-1;

        while(start<=end){
//            System.out.println(start);
//            System.out.println(end);
//            System.out.println("***********");
            int mid = (start+end)/2;
            if(nums[mid][0]==num){
                return mid;
            }
            if (nums[mid][0]>num){
                end = mid-1;
            }else {
                start=mid+1;
            }

        }

        //System.out.println("-------------------------"+end);
        return end;

    }

    public boolean primeSubOperation(int[] nums) {

        boolean[] isPrime = new boolean[1001];
        for (int i = 0; i < 1001; i++) {
            if (i <= 1) {
                isPrime[i] = false; // 0 and 1 are not prime
                continue;
            }

            boolean prime = true; // Assume i is prime initially
            for (int j = 2; j * j <= i; j++) {
                if (i % j == 0) { // If i is divisible by j, it's not prime
                    prime = false;
                    break;
                }
            }
            isPrime[i] = prime; // Set isPrime[i] to true if prime, false if not
        }



        int index=nums[0]-1;
        while (index>=0 &&!isPrime[index] ){
            index--;
        }
        index= index<0?0:index;
        nums[0]= nums[0]- index;

        System.out.println(nums[0]);

        for (int i =1;i<nums.length;i++){
            index= nums[i]-1;
            while (index>=0){
                if(isPrime[index]  && nums[i]-index>nums[i-1]){
                    break;
                }

                index--;
            }
            index= index<0?0:index;
            System.out.println("for nums[i] "+nums[i]+" nearest prime is "+index);
            if(!(nums[i]-index<=nums[i-1])){
                nums[i]= nums[i]- index;
            }

            System.out.println(nums[i]);
            if(nums[i]<=nums[i-1]){
                return false;
            }

        }

        return true;
    }

    public boolean canSortArray(int[] nums) {
        for (int i =0;i<nums.length-1;i++){
            if(nums[i]>nums[i+1]){
                if (Integer.bitCount(nums[i]) != Integer.bitCount(nums[i+1])){
                    return false;
                }
                int temp = nums[i];
                nums[i]=nums[i+1];
                nums[i+1]=temp;


                for (int j =i-1;j>=0;j--){
                    if (nums[j]>nums[j+1]){
                        if (Integer.bitCount(nums[j]) != Integer.bitCount(nums[j+1])){
                            return false;
                        }
                        int temp2 = nums[j];
                        nums[j]=nums[j+1];
                        nums[j+1]=temp2;

                    }else {
                        break;
                    }
                }

            }
        }



        return true;
    }




}
