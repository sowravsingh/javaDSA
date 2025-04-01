import java.util.*;

public class LeetcodeProblems {

    public static List<List<Integer>> findMatrix(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();
        int maxRepeat =0;
        Map<Integer,Integer> numberRepeatetion = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            int value = numberRepeatetion.getOrDefault(nums[i],0)+1;
            maxRepeat=Math.max(maxRepeat,value);
            numberRepeatetion.put(nums[i],value);
        }

        for(int i =0;i<maxRepeat;i++){
            List<Integer> rowList = new ArrayList<>();
            for(Map.Entry<Integer,Integer> entry:numberRepeatetion.entrySet()){
                if(entry.getValue() !=0){
                    rowList.add(entry.getKey());
                    numberRepeatetion.put(entry.getKey(), entry.getValue()-1);
                }
            }
            resultList.add(rowList);
        }

        return resultList;
    }




    public static boolean canJump(int[] nums) {

      //  int lastStep =0;
        int index =0;
        while(index<nums.length-1){
            if(nums[index] ==0 ){
                return false;
            }else{
                index= nums[index]+index;
            }
        }

        return true;

    }


    public static int numberOfBeams(String[] bank) {
        int totalBeams =0;
        int lastStep =0;
        char c2 ='1';
        for(int i=0;i<bank.length;i++){
            String pattern = bank[i];
            int currentStep=0;
            for(int j=0;j<pattern.length();j++){
                char c= pattern.charAt(j);
                if(c == c2){
                    currentStep=currentStep+1;
                }
            }
            //System.out.println(currentStep);
            if(currentStep !=0){
                totalBeams= totalBeams+(currentStep*lastStep);
                lastStep=currentStep;
            }
        }

        return totalBeams;
    }



    public static int minOperations(int[] nums) {
        int minOperations =0;
        Map<Integer,Integer> repetetionCount = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            int value= repetetionCount.getOrDefault(nums[i],0);
            repetetionCount.put(nums[i],value+1);
        }

        System.out.println(repetetionCount);
        for(Map.Entry<Integer,Integer> entry : repetetionCount.entrySet()){
            int value= entry.getValue();
            if(value ==1){
                return -1;
            }
            minOperations=minOperations+value/2;
        }


        return  minOperations;

    }


    public static  int lengthOfLIS(int[] nums) {
        int len = nums.length;
        if(len ==0)
            return 0;
        int [] ascCount = new int[len];
        Arrays.fill(ascCount,1);

        for(int i=1;i<ascCount.length;i++){
            for(int j=0;j<i;j++){
                if(nums[i] >nums[j]){
                    ascCount[i] = Math.max(ascCount[i],ascCount[j]+1);
                }
            }
        }


        int maxLength = Arrays.stream(ascCount).max().orElse(0);

        return maxLength;
    }


    public static int returnFactorial(int n){
        if(n ==1)
            return 1;
        else{
            return n * returnFactorial(n-1);
        }
    }

    public static void recurrsive(int n){
        if(n ==0)
            return;
        else {
            System.out.println(" printing number  --> "+n);
            recurrsive(n-1);
        }
    }

    public static boolean checkPalindorome(String s){
        if(s.length() ==0 || s.length() ==1){
            return true;
        }

        if(s.charAt(0)==s.charAt(s.length()-1)){
            return checkPalindorome(s.substring(1,s.length()-1));
        }else{
            return false;
        }
    }


    public static int jump(int[] nums) {
        int jumps = 0, currEnd = 0, currFarthest = 0;
        for(int i = 0; i < nums.length - 1; i ++) {
            currFarthest = Math.max(currFarthest, i + nums[i]);
            if(i == currEnd) {
                currEnd = currFarthest;
                jumps ++;
            }
        }
        return jumps;
    }

    public static int hIndex(int[] citations) {
        int totalPapers = citations.length;
        while(totalPapers >0){
           // System.out.println(totalPapers);
            int cnt =0;
            for(int i=0;i<citations.length;i++){
                if(citations[i] >= totalPapers)
                    cnt++;
            }
            System.out.println("total count for "+totalPapers +" is "+cnt);
            if(totalPapers <= cnt){
                return totalPapers;
            }
            totalPapers--;
        }
        return 0;
    }

    public static int removeDuplicates(int[] nums) {
        int number =nums[0];
        int arrayLength=1;
        int numCnt =1;
       int presentIndex =0;
        for(int i=1;i<nums.length;i++){
            if(nums[i]==number ){
                if(numCnt<2){
                    nums[presentIndex+1]= nums[i];
                    numCnt++;
                    presentIndex=presentIndex+1;
                    System.out.println("incrementing array for same number of i"+i);
                    arrayLength++;
                }
                else{
                    numCnt++;
                }
            }else{
                number=nums[i];
                nums[presentIndex+1]=nums[i];
                numCnt=1;
                presentIndex=presentIndex+1;
                System.out.println("incrementing array for new number of i"+i);
                arrayLength++;
            }
        }
        System.out.println(Arrays.toString(nums));
      return arrayLength;
    }

    public static int trap(int[] height) {
        int totalEmptyBlocks =0;
        int startIndex =0;
        for(int i =0;i<height.length-1;i++){
            if(height[i]!=0){
                int startIndexNumber = height[i];
                startIndex=i;
                int lastIndex = nextbigIndex(height,startIndex,startIndexNumber);
                while(startIndex == lastIndex){
                    startIndexNumber--;
                    lastIndex = nextbigIndex(height,startIndex,startIndexNumber);
                }
                System.out.println(startIndex);
                System.out.println(lastIndex);
                totalEmptyBlocks=totalEmptyBlocks+returnEmptyBlocks(height,startIndex,lastIndex);
                i =lastIndex-1;
            }
        }
        return totalEmptyBlocks;
    }

    public static int nextbigIndex(int[] height,int startIndex,int startIndexNumber){
        int lastIndex =startIndex;
        for(int i = startIndex+1;i<height.length;i++){
            if(height[i]>=startIndexNumber){
                return i;
            }
        }
        return lastIndex;

    }

    public static int returnEmptyBlocks(int[] height,int startIndex,int lastIndex){
        int emptyBlocks =0;
        for(int i=startIndex+1;i<lastIndex;i++){
            if(height[startIndex]<=height[lastIndex]){
                int blocks = height[startIndex]-height[i];
                emptyBlocks=emptyBlocks+blocks;
            }else{
                int blocks = height[lastIndex]-height[i];
                emptyBlocks=emptyBlocks+blocks;
            }
//            int blocks = height[startIndex]-height[i];

        }
        return emptyBlocks;
    }


    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        Arrays.fill(result,1);

        for(int i =1;i<result.length;i++){
            result[i] =result[i-1]*nums[i-1];
        }
        int prevProdcut =nums[nums.length-1];
        for(int i =result.length-2;i<=0;i--){
            System.out.println("entered here for "+i);
            result[i] = prevProdcut*result[i];
            prevProdcut=prevProdcut*nums[i];
        }

        return result;
    }

    public boolean halvesAreAlike(String s) {
        s.toLowerCase();
        int len =s.length();
        List<Character> voweList = new ArrayList<>();
        voweList.add('a');
        voweList.add('e');
        voweList.add('i');
        voweList.add('o');
        voweList.add('u');
        int leftCount =0;
        int rightCount=0;
        for(int i=0;i<(len/2);i++){
            if(voweList.contains(s.charAt(i))){
                leftCount++;
            }
        }
        for(int i =(len/2);i<len;i++){
            if(voweList.contains(s.charAt(i))){
                rightCount++;
            }
        }
        if(leftCount==rightCount)
            return true;
        return false;
    }



    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer,Integer> countMap = new HashMap<>();
        for(int i=0;i<arr.length;i++){
            int value = countMap.getOrDefault(arr[i],0);
            countMap.put(arr[i],value+1);
        }
        System.out.println(countMap);
        List<Integer> resultList = new ArrayList<>();
        for(Map.Entry<Integer,Integer> entry : countMap.entrySet()){
            int value = entry.getValue();
            if(resultList.contains(value)){
                return false;
            }
            resultList.add(value);
        }
        return true;
    }

    public static int hammingWeight(int n) {
        int count =0;
        while(n>0){
            if((n & 1)==1){
                count++;
            }
            n=n>>1;
        }
        return count;
    }

    public static long Factorial(int n){
        if(n==1){
            return 1;

        }
        long f = n*Factorial(n-1);
        return f;

    }


    public static int sumOfDigits(int n){
        if(n<10){
            return n;
        }
        int sum =(n%10)+sumOfDigits(n/10);
        return sum;
    }

    static int sum =0;
    public static void reverseNumber(int n){
        if(n==0){
            return;
        }
        sum =sum*10+n%10;
        reverseNumber(n/10);
    }

    public static int FindZeros(int n,int count){
        if(n==0){
            return count;
        }
        if(n%10 ==0){
            return FindZeros(n/10,count+1);
        }else{
            return FindZeros(n/10,count);
        }

    }

    public int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0]=nums[0];
        dp[1]=Math.max(nums[0],nums[1]);
        for(int i=2;i<nums.length;i++){
            dp[i]= Math.max(dp[i-2]+nums[i],dp[i-1]);
        }
        return dp[n-1];
    }

    public int numberOfSteps(int num) {
        int count=0;
        return count;
    }

    public int totalSteps(int num,int count){

        if(num==0){
            return count;
        }

        if(num%2==0){
            return totalSteps(num/2,count+1);
        }else{
            return totalSteps(num-1,count+1);
        }
    }



    public int trailingZeroes(int n) {
        int c=0;
        while(n!=0){
            c=c+n/5;
            n=n/5;
        }
        return c;
    }


    public static boolean checkSorted(int[] nums, int index){
        if(index == nums.length-1){
            return true;
        }

        if(nums[index] >nums[index+1]){
            return false;
        }

        return (nums[index]<nums[index+1] && checkSorted(nums,index+1));
    }

    public static List<Integer> returnIndex(int[] arr, int index,List<Integer> resultList,int number){
        if(index==arr.length){
            return resultList;
        }

        if(arr[index]==number){
            resultList.add(index);
        }
        return returnIndex(arr,index+1,resultList,number);
    }


    public int maxLength(List<String> arr) {

        int[] resultarr= new int[26];
        int count = concatinate(arr,0,resultarr);
        return count;
    }


    public int concatinate(List<String> arr,int index ,int[] resultArray){
        if(index==arr.size()){
            int count=0;
            for(int i=0;i<resultArray.length;i++){
                if (resultArray[i]==1)
                    count++;
            }
            return count;
        }
        int notPickLength =0;
        int pickLength=0;
        String s1= arr.get(index);
        boolean canAppend= selectElement(s1,resultArray);
        if(canAppend){
            for(int i=0;i<s1.length();i++){
                resultArray[s1.charAt(i)-'a']=1;
            }
            pickLength=concatinate(arr,index+1,resultArray);
            for(int i=0;i<s1.length();i++){
                resultArray[s1.charAt(i)-'a']=0;
            }
        }
        notPickLength= concatinate(arr,index+1,resultArray);
        return Math.max(pickLength,notPickLength);

    }

    public boolean selectElement(String str,int[] arr){
        List<Character> currentStringChar = new ArrayList<>();
        for(int i=0;i<str.length();i++){
            int value = arr[str.charAt(i)-'a'];
            if(value ==1 || currentStringChar.contains(str.charAt(i)))
                return false;
            currentStringChar.add(str.charAt(i));
        }
        return true;
    }



    public static int[] bubbleSort(int[] arr,int c,int lastIndex){
        if(lastIndex==0){
            return arr;
        }
        if(c==lastIndex){
            return bubbleSort(arr,1,lastIndex-1);
        }
       if(arr[c]<arr[c-1]){
           int temp = arr[c];
           arr[c]=arr[c-1];
           arr[c-1]=temp;
       }
       return bubbleSort(arr,c+1,lastIndex);
    }


    public static int[] mergeSort(int[] arr){
        if(arr.length==1){
            return arr;
        }

        int mid= arr.length/2;
        int[] leftArr= mergeSort(Arrays.copyOfRange(arr,0,mid));
        int[] rightArr= mergeSort(Arrays.copyOfRange(arr,mid,arr.length));

        return mergeArrays(leftArr,rightArr);

    }


    public static int[] mergeArrays(int[] left,int[] right){
        int l1= left.length;
        int l2= right.length;
        int[] resultArray = new int[l1+l2];
        int i=0;
        int j=0;
        int k=0;
        while(i<l1  && j<l2){
            if(left[i]<right[j]){
                resultArray[k] =left[i];
                k++;
                i++;
            }else{
                resultArray[k] =right[j];
                j++;
                k++;
            }
        }


        while(i<l1){
            resultArray[k]=left[i];
            i++;
            k++;
        }
        while(j<l2){
            resultArray[k]=right[j];
            j++;
            k++;
        }


        return resultArray;
    }

    public void megreSortInLine(int[] arr,int s,int e){

        if(s==e){
            return;
        }
        int mid= (s+e)/2;

    }



    public static String longestPalindrome(String s) {

        int start=0;
        int end =0;
        int maxLen =0;
        for(int i=0;i<s.length();i++){
            int[] oddList = largestString(i,i,s);
            int[] evenList = largestString(i,i+1,s);
            if(oddList[2]>evenList[2] && oddList[2]>maxLen) {
                maxLen=oddList[2];
                start=oddList[0];
                end=oddList[1];
            }
            else if(evenList[2]>oddList[2] && evenList[2]>maxLen){
                maxLen=evenList[2];
                start=evenList[0];
                end=evenList[1];
            }
        }


        return (s.substring(start,end+1));


    }


    public static int[] largestString(int left,int right ,String s ) {
        int[] nums = new int[3];
        while(left>=0 && right<s.length() && (s.charAt(left)==s.charAt(right))){
            left--;
            right++;
        }
        nums[0]= left+1;
        nums[1]=right-1;
        nums[2]=s.substring(left+1,right).length();
        return nums;
    }

   public static List<String>  printSubsequence(String p,String up){

       List<String> rs = new ArrayList<>();
        if(up.isEmpty()){
            rs.add(p);
            return rs;
        }

        List<String> pickList =printSubsequence(p+up.charAt(0),up.substring(1));
        List<String> NotPickList =printSubsequence(p,up.substring(1));
        rs.addAll(pickList);
        rs.addAll(NotPickList);

        return rs;

   }


    public static int sumSubarrayMins(int[] arr) {
        int total =0;
        for(int i=0;i<arr.length;i++){
            int min = arr[i];
            for(int j=i;j<arr.length;j++){
                if(arr[j]<min){
                    min =arr[j];
                }
                total=total+min;
            }
            System.out.println(total);
        }
        return total;
    }

    public static void addSubsequences(int[] arr, int index, List<List<Integer>> ls,List<Integer> p){
        if(index == arr.length){
            System.out.println(ls);
            return;
        }

        p.add(arr[index]);
        addSubsequences(arr,index+1,ls,p);
        p.remove(p.size()-1);
        addSubsequences(arr,index+1,ls,p);
    }


    public static int coinChange(int[] coins, int amount) {
        int temp[][] = new int[coins.length][amount+1];
        for(int i=0; i < coins.length; i++){
            for(int j=0; j <= amount ; j++){
                if(coins[i] > j){
                    temp[i][j] = temp[i-1][j];
                }
                else{
                    temp[i][j] =  Math.min( temp[i][j-coins[i]] +1 ,  temp[i-1][j]) ;
                }
            }
        }
        return temp[coins.length-1][amount];

    }

   public static List<List<Integer>> returnSubsequences(int[] arr){
        List<List<Integer>> rs = new ArrayList<>();
        rs.add(new ArrayList<>());
        for(int num :arr){
            int n = rs.size();
            for(int i=0;i<n;i++){
                List<Integer> newList = new ArrayList<>(rs.get(i));
                newList.add(num);
                rs.add(newList);
            }
        }
        return rs;
   }


    public static List<List<Integer>> returnUniqueSubsequences(int[] arr){
        Arrays.sort(arr);
        List<List<Integer>> rs = new ArrayList<>();
        rs.add(new ArrayList<>());
        int start =0;
        int end = 0;
        for(int i =0;i<arr.length;i++){
            start=0;
            if(i>0 && arr[i] ==arr[i-1]){
                start=end+1;
            }
            end = rs.size();
            for(int j=start;j<end;j++){
                List<Integer> newList = new ArrayList<>(rs.get(j));
                newList.add(arr[i]);
                rs.add(newList);
            }
        }

        return rs;
    }


    public int[][] divideArray(int[] nums, int k) {
        if(nums.length<3){
            return new int[0][0];
        }
        int n = nums.length/3;
        int[][] resultArray = new int[n][3];
        Arrays.sort(nums);
        int outerIndex=0;
        int innerIndex =0;
        int minNumber= nums[0];
        resultArray[outerIndex][innerIndex]=nums[0];
        innerIndex++;

        for(int i=1;i<nums.length;i++){
            if(innerIndex==3){
                outerIndex++;
                resultArray[outerIndex][0]=nums[i];
                minNumber=nums[i];
                innerIndex=1;
                continue;

            }
            if(nums[i]-minNumber<=k ) {
                resultArray[outerIndex][innerIndex] = nums[i];
                minNumber=Math.min(nums[i],minNumber);
                innerIndex++;
            }else{
               return new int[0][0];
            }
        }

        return resultArray;
    }


    public static List<String> permutations(String p,String up){
       if(up.length() ==0){
           List<String> ls = new ArrayList<>();
           ls.add(p);
           return ls;
       }

       char ch =up.charAt(0);
       List<String> fianl = new ArrayList<>();
       for(int i =0;i<=p.length();i++){
           String firstString =p.substring(0,i);
           String SecondString=p.substring(i,p.length());
           List<String> ls=permutations(firstString+ch+SecondString,up.substring(1));
           fianl.addAll(ls);
       }
       return fianl;
    }


    public  static List<String> letterCombinations(String digits) {
        String p ="";
        if (digits.isEmpty()){
            return new ArrayList<>();
        }
        List<String> rs = recursionPhoneNumbers(p,digits);
        return rs;
    }

    public  static List<String> recursionPhoneNumbers (String p,String up){
        if(up.isEmpty()){
            System.out.println(p);
            List<String> ls = new ArrayList<>();
            ls.add(p);
            return  ls;
        }

        List<String> outerList = new ArrayList<>();
        char digit = up.charAt(0);
        int myInt = (int) digit - '0';

        int start  = (myInt-2)*3;
        int end = (myInt-1)*3;

        if(myInt ==7 || myInt==9){
            end=end+1;
        }

        if(myInt ==8){
            start=start+1;
            end= end+1;
        }


        for(int i =start;i<end;i++){
            char c = (char) ('a'+(int)i);
            List<String> innerList= recursionPhoneNumbers(p+c,up.substring(1));
            outerList.addAll(innerList);
        }

        return outerList;

    }

    public static List<Integer> sequentialDigits(int low, int high) {
        List<Integer> rs = new ArrayList<>();
        for(int i =1;i<9;i++){
            List<Integer> recurList = returnRecurNum(low,high,i,i);
           // System.out.println(recurList);
            rs.addAll(recurList);
        }
        Collections.sort(rs);
        return rs;
    }

    public static List<Integer> returnRecurNum(int low,int high,int num,int total){

        if(total>high || num ==10){
            return new ArrayList<>();
        }
        List<Integer> numList= new ArrayList<>();
        if(total>=low){
           // System.out.println(total +" "+low);
            numList.add(total);
        }


        num =num+1;
        total=(total*10)+num;

        List<Integer> innerList =returnRecurNum(low,high,num,total);
        numList.addAll(innerList);


        return numList;
    }


    public static  List<String> returnMaizeRecur(int r,int c,String  s){
        if(r==1 && c==1){
            List<String> ls = new ArrayList<>();
            ls.add(s);
            return ls;
        }

        List<String> resultString = new ArrayList<>();
        if(c !=1){
            resultString.addAll(returnMaizeRecur(r,c-1,s+"R"));
        }
        if(r!=1){
            resultString.addAll(returnMaizeRecur(r-1,c,s+"D"));
        }
        if(r!=1 && c!=1){
            resultString.addAll(returnMaizeRecur(r-1,c-1,s+"C"));
        }

        return resultString;
    }

    public int firstUniqChar(String s) {
       Map<Character,Integer> ls = new HashMap<>();
       for(int i =0;i<s.length();i++){
           char ch =s.charAt(i);
           int value = ls.getOrDefault(ch,0);
           ls.put(ch,value+1);
       }

        for(int i =0;i<s.length();i++){
            char ch =s.charAt(i);
            if(ls.get(ch)==1){
                return i;
            }
        }
        return -1;
    }



    public static void printallPaths(int r,int c ,boolean[][] steps,String s,int count, int [][] countArr){
        if(r==steps.length-1 && c == steps[0].length-1){
            countArr[r][c]=count;
            for(int i=0;i<countArr.length;i++){
                System.out.println(Arrays.toString(countArr[i]));
            }
            System.out.println(s);
            System.out.println("#####3");
            return;
        }
        if(!steps[r][c]){
            return;
        }

        steps[r][c] =false;
        countArr[r][c] =count;
        if(r != steps.length-1){
            printallPaths(r+1,c,steps,s+"D",count+1,countArr);
        }
        if(c!= steps[0].length-1){
            printallPaths(r,c+1,steps,s+"R",count+1,countArr);
        }
        if(r!=0){
            printallPaths(r-1,c,steps,s+"U",count+1,countArr);
        }
        if(c!=0){
            printallPaths(r,c-1,steps,s+"L",count+1,countArr);
        }
        steps[r][c] =true;
        countArr[r][c] =0;

    }

    public static void possibleDice(String p,int up){
        if(up ==0){
            System.out.println(p);
            return;
        }


       for(int i =1;i<=up;i++){
           possibleDice(p+i,up-i);
       }
    }


    public   List<Integer> resultList = new ArrayList<>();
    public static void main(String[] args) {
         String p ="";
        possibleDice(p,6);
    }

}














