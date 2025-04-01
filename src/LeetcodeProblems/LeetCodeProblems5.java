package LeetcodeProblems;

import java.util.*;

public class LeetCodeProblems5 {

    public static void main(String[] args) {
        LeetCodeProblems5 ll = new LeetCodeProblems5();
       // int[][] nums={{0,2,1,0},{4,0,0,3},{1,0,0,4},{0,3,2,0}};
        int[] nums ={3,2,1};

        System.out.println(ll.longestMonotonicSubarray(nums));
    }

    public int longestMonotonicSubarray(int[] nums) {

        int n = nums.length;
        int left=0;
        int maxLen = 1;
        int prevNum = nums[0];
        for (int i =1;i <nums.length;i++){
            if (prevNum>=nums[i]){
                maxLen =Math.max(((i-1)-left)+1,maxLen);
                left=i;
            }
            prevNum= nums[i];
        }
        if (left != nums.length-1){
            maxLen =Math.max((n-left),maxLen);
        }


         left=0;
         prevNum = nums[0];
        for (int i =1;i <nums.length;i++){
            if (prevNum<=nums[i]){
                maxLen =Math.max(((i-1)-left)+1,maxLen);
                left=i;
            }
            prevNum= nums[i];
        }

        if (left != nums.length-1){
            maxLen =Math.max((n-left),maxLen);
        }


        return maxLen;
    }


    Boolean [][] visited;
    public int findMaxFish(int[][] grid) {

        int maxSum =0;
        for (int row =0;row<grid.length;row++){
            for (int col =0 ;col<grid[0].length;col++){
                if (grid[row][col] ==0){
                    continue;
                }
                visited= new Boolean[grid.length][grid[0].length];
                int sum = returnMaxLen(grid,row,col);
                maxSum=Math.max(sum,maxSum);
            }
        }

        return maxSum;
    }

    public int returnMaxLen(int[][]grid, int row, int col){

        if (row== grid.length || row<0 || col<0 || col == grid[0].length){
            return 0;
        }
        if (visited[row][col] || grid[row][col]==0) {
            return 0;
        }

        visited[row][col]=true;
        int sum = grid[row][col];
        sum = sum+returnMaxLen(grid, row+1,col);
        sum = sum+returnMaxLen(grid, row,col+1);
        sum = sum+returnMaxLen(grid, row,col-1);
        sum = sum+returnMaxLen(grid, row-1,col);

        return sum;

    }


    public List<Integer> eventualSafeNodes(int[][] graph) {

        List<Integer> indexList = new ArrayList<>();

        for (int i =0;i<graph.length;i++){
            int[] row =graph[i];
            if (row.length ==0){
                indexList.add(i);
            }
        }


        for (int i =0;i<graph.length;i++){
            int[] row =graph[i];

            if (row.length==0){
                continue;
            }
            boolean notAEdge=false;
            for (int num : row){
                if (!indexList.contains(num)){
                    notAEdge=true;
                }
            }

            if (!notAEdge){
                indexList.add(i);
            }
        }

        Collections.sort(indexList);

        return indexList;
    }



    public int countServers(int[][] grid) {
        int[] colCount = new int[grid[0].length];
        int[] rowCount = new int[grid.length];

        int ans =0;
        for (int i =0;i<grid.length;i++){
            for (int j =0;j<grid[0].length;j++){
                if (grid[i][j] ==1){
                    rowCount[i]++;
                    colCount[j]++;
                }
            }
        }

        for (int i =0;i<grid.length;i++){
            if (rowCount[i] >1){
                for (int j =0;j<grid[0].length;j++){
                    if (grid[i][j] ==1){
                        grid[i][j]=-1;
                    }
                }
            }
        }

        for (int i = 0;i<colCount.length;i++){
            if (colCount[i]>1){
                for (int j =0;j<grid.length;j++){
                    if (grid[j][i] ==1){
                        grid[j][i]=-1;
                    }
                }
            }
        }

        for (int i =0;i<grid.length;i++){
            for (int j =0;j<grid[0].length;j++){
                if (grid[i][j] ==-1){
                    ans++;
                }
            }
        }

        return ans;

    }

    public int[][] highestPeak(int[][] isWater) {

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] isVisited = new boolean[isWater.length][isWater[0].length];
        for (int i =0;i<isWater.length;i++){
            for (int j =0;j<isWater[0].length;j++){
                if (isWater[i][j] ==1){
                    int[] cell ={i,j};
                    queue.offer(cell);
                }
            }
        }

        int height =0;

        while (!queue.isEmpty()){
            int size = queue.size();
            System.out.println("size is "+size);
            while(size>0){
                int[] cell = queue.poll();
                int row = cell[0];
                int col = cell[1];
                if (isVisited[row][col]){
                    size--;
                    continue;
                }
                isVisited[row][col]=true;
                isWater[row][col] = height;

                // add up
                if (row-1>=0 && !isVisited[row-1][col]){
                    int[] upCell ={row-1,col};
                    queue.offer(upCell);
                }

                // add left
                if (col-1>=0 && !isVisited[row][col-1]){
                    int[] leftCell ={row,col-1};
                    queue.offer(leftCell);
                }

                //add right
                if (col+1<isWater[0].length && !isVisited[row][col+1]){
                    int[] rightCell ={row,col+1};
                    queue.offer(rightCell);
                }

                // add down
                if (row+1<isWater.length  && !isVisited[row+1][col]){
                    int[] downCell ={row+1,col};
                    queue.offer(downCell);
                }
                size--;
            }

            height++;
        }


        return isWater;
    }


    public int firstCompleteIndex(int[] arr, int[][] mat) {
        Map<Integer,Integer> indexMap= new HashMap<>();
        Map<Integer,Integer> colVsMax = new HashMap<>();

        int minRow = Integer.MAX_VALUE;
        for (int i =0; i<arr.length;i++){
            indexMap.put(arr[i],i);
        }

        for (int i =0;i<mat.length;i++){
            int currentRowMax=Integer.MIN_VALUE;
            for (int j =0;j<mat[0].length;j++){
                currentRowMax= Math.max(currentRowMax,indexMap.get(mat[i][j]));
                if (colVsMax.get(j) == null){
                    colVsMax.put(j,indexMap.get(mat[i][j]));
                }else {
                    colVsMax.put(j,Math.max(indexMap.get(mat[i][j]),colVsMax.get(j)));
                }

            }

            minRow= Math.min(currentRowMax,minRow);
        }


        for (int col :colVsMax.keySet()){
            minRow= Math.min(colVsMax.get(col),minRow);
        }


        return minRow;
    }

//    public boolean canBeValid(String s, String locked) {
//        Stack<Character> stack = new Stack<>();
//
//        int length = s.length();
//
//        for (int i =0;i<s.length();i++){
//            if (s.charAt(i) =='('){
//                stack.push('(');
//            }
//        }
//
//    }



    public int countPrefixSuffixPairs(String[] words) {
        int totalPairs =0;

        for (int i =0;i<words.length;i++){
            String firstStr  = words[i];
            for (int j =i+1;j< words.length;i++){
                String secondStr = words[j];
                if (secondStr.startsWith(firstStr) && secondStr.endsWith(firstStr)){
                    totalPairs++;
                }
            }
        }

        return totalPairs;
    }

    public String longestPalindrome(String s) {
        String LongestString ="";
        if (s.length()<=1){
            return s;
        }

        for (int i =1;i<s.length();i++){

            int high =i;
            int low =i;
            // checking for odd length
            while (s.charAt(high) == s.charAt(low) ){
                low--;
                high++;

                if (high==s.length() || low==-1){
                    break;
                }
            }
            String oddPalindrome = s.substring(low+1,high);
            if (oddPalindrome.length()>LongestString.length()){
                LongestString=oddPalindrome;
            }

            high=i;
            low=i-1;
            //checking for even length
            while (s.charAt(high) == s.charAt(low) ){
                low--;
                high++;

                if (high==s.length() || low==-1){
                    break;
                }
            }
            String evenPalindrome = s.substring(low+1,high);
            if (evenPalindrome.length()>LongestString.length()){
                LongestString=evenPalindrome;
            }

        }

        return LongestString;
    }

    public String shiftingLetters(String s, int[][] shifts) {

        int length = s.length();
        int[] forwardChar = new int[26];
        int[] backwardChar = new int[26];

        for (int i =0;i<25;i++){
            forwardChar[i]=i+1;
        }
        forwardChar[25]=0;

        backwardChar[0]=25;
        for (int i =1;i<26;i++){
            backwardChar[i]=i-1;
        }

       for(int[] arr :shifts){
           int start = arr[0];
           int end = arr[1];
           int dir = arr[2];
           StringBuffer sb = new StringBuffer();
           for (int i=start;i<=end;i++){
               char c = s.charAt(i);
               int asciKey = c;
               asciKey=asciKey-97;

               if (dir==1){
                   char newChar = (char)(97 +forwardChar[asciKey]);
                   sb.append(newChar);
               }else {
                   char newChar = (char)(97 + backwardChar[asciKey]);
                   sb.append(newChar);
               }
           }

           String newString ="";
           if (start!=0){
               newString= s.substring(0,start)+sb.toString();
           }else {
               newString=sb.toString();
           }


           if (end !=length-1){
               System.out.println(end+1);
               newString=newString+s.substring(end+1);
           }
           s = newString;

       }

        return s;
    }

    public int waysToSplitArray(int[] nums) {
        int ans =0;
        long[] prefixSum =new long[nums.length];
        prefixSum[0]=nums[0];

        for (int i =1;i<nums.length;i++){
            prefixSum[i]=prefixSum[i-1]+nums[i];
        }
        System.out.println(Arrays.toString(prefixSum));

        for (int i =0;i<nums.length-1;i++){
            if (prefixSum[i]>=prefixSum[nums.length-1]-prefixSum[i]){
                ans++;
            }
        }

        return ans;
    }


    public int countGoodStrings(int low, int high, int zero, int one) {
        int MOD = 1000000007;
        int ans =0;
        Map<Integer,Integer> numVsFreq = new HashMap<>();
        numVsFreq.put(0,1);

        for (int i = 0;i<=high;i++){
            if (numVsFreq.get(i) ==null){
                continue;
            }

            int count = numVsFreq.get(i);

            if(i+one<=high){
                int oneString = (numVsFreq.getOrDefault(i+one,0)+count) % MOD;
                numVsFreq.put(i+one,oneString);
            }

            if(i+zero<=high){
                int zeroString = (numVsFreq.getOrDefault(i+zero,0)+count) % MOD;;
                numVsFreq.put(i+zero,zeroString);
            }

            if (i>=low){
                ans=(ans + count) % MOD;
            }
        }



        return ans;
    }

    public int findTargetSumWays(int[] nums, int target) {
        int ans =0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(nums[0]);
        for (int i =1 ;i<nums.length;i++){
            int size =queue.size();
            while (size>0){
                int number = queue.poll();
                queue.offer(number+(nums[i]*(-1)));
                queue.offer(number+(nums[i]*(1)));
                size--;
            }

        }

        while (!queue.isEmpty()){
            if (queue.peek()== target){
                ans++;
            }
            queue.poll();
        }
        return ans;

    }

    public int minimumOperations(TreeNode root) {
        int ans =0;
        if (root == null){
            return ans;
        }

        Queue<TreeNode> queue= new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            int size = queue.size();
            int[] originalArray = new int[size];
            int[] sortedArray = new int[size];
            HashMap<Integer,Integer> nodeVsIndex = new HashMap<>();
            for (int i =0;i<size;i++){
                TreeNode node = queue.poll();
                originalArray[i]= node.val;
                sortedArray[i]= node.val;


                if (node.left!=null){
                    queue.offer(node.left);
                }

                if (node.right!=null){
                    queue.offer(node.right);
                }

                nodeVsIndex.put(node.val,i);

            }

            Arrays.sort(sortedArray);

            for (int i = 0;i<sortedArray.length;i++){
                if(sortedArray[i] != originalArray[i]){
                    int oldIndex= nodeVsIndex.get(sortedArray[i]);
                    int oldValue=originalArray[i];

                    originalArray[i]=sortedArray[i];
                    originalArray[oldIndex]=oldValue;
                    nodeVsIndex.put(oldValue,oldIndex);
                    nodeVsIndex.put(sortedArray[i],i);

                    ans++;
                }



            }
            System.out.println(Arrays.toString(originalArray));
            System.out.println(Arrays.toString(sortedArray));

        }




        return ans;
    }

}
