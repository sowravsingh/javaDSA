package LeetcodeProblems;

import java.util.*;

public class LeetCodeProblem6 {

    public static void main(String[] args) {
     LeetCodeProblem6 ll = new LeetCodeProblem6();
//     char a ='9';
//     char b ='a';
//     if (a>=97 && a<=122){
//         System.out.println(true);
//     }
//
////        if (b>=97 && b<=122){
////            System.out.println(true);
////        }


     int[] nums ={1000000,1,1000000};
     int[][] arr ={{0,1},{1,4},{1,1},{1,4},{1,1}};
        System.out.println(ll.maximumTripletValue(nums));
    }

    public long maximumTripletValue(int[] nums) {
        Long maxTriplet = 0L;

        for (int  i =0;i<nums.length;i++){
            for (int j =i+1;j<nums.length;j++){
                for (int k =j+1;k<nums.length;k++){
                    Long result = (Long.valueOf(nums[i])-Long.valueOf(nums[j]))*Long.valueOf(nums[k]);
                    maxTriplet= Math.max(maxTriplet,result);
                }
            }
        }

        return maxTriplet;
    }

    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> charSet = new HashSet<>();

        int maxLen =1;
        int left =0;

        for (int i =0;i<s.length();i++){
            if (charSet.add(s.charAt(i))){
               maxLen= Math.max(maxLen,i-left+1);
            }else {
                boolean isAdded =false;
                while (!isAdded){
                    charSet.remove(s.charAt(left));
                    left++;
                    isAdded= charSet.add(s.charAt(i));
                }
            }
        }


        return maxLen;
    }


    public int maximumSum(int[] nums) {

        int maxSum = -1;
        Map<Integer,PriorityQueue<Integer>> digitSumVsNumber = new HashMap<>();

        for (int num : nums){
            int temp = num;
            int sum =0;
            while (num>0){
                sum = sum+num%10;
                num=num/10;

            }
            digitSumVsNumber.computeIfAbsent(sum, k-> new PriorityQueue<>(Collections.reverseOrder())).add(temp);

        }

        for (int digitSum : digitSumVsNumber.keySet()){
            PriorityQueue<Integer> priorityQueue = digitSumVsNumber.get(digitSum);
            int sum = 0;
            if (priorityQueue.size() >=2){
                sum= sum+priorityQueue.poll();
                sum=sum+priorityQueue.poll();

            }

            if (sum !=0){
                maxSum= Math.max(sum,maxSum);
            }

        }

        return maxSum;
    }

    public String removeOccurrences(String s, String part) {

        Character lastChar = part.charAt(part.length()-1);


        Stack<Character> stack = new Stack<>();
        for (Character ch :s.toCharArray()){
            stack.push(ch);
            if (ch ==lastChar) {

                for (int i =part.length()-1;i>=0;i--){
                    Stack<Character> innserStack = new Stack<>();
                    if (!stack.isEmpty() && stack.peek() == part.charAt(i)){
                        innserStack.push(stack.pop());
                    }else {
                        while (!innserStack.isEmpty()){
                            stack.push(innserStack.pop());
                        }
                        break;
                    }

                }

                System.out.println(stack.size());

            }

        }

        StringBuffer sb = new StringBuffer();
        while (!stack.isEmpty()){
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

    public String clearDigits(String s) {
        StringBuffer sb = new StringBuffer();

        for (Character ch : s.toCharArray()){
            if (ch>=97 && ch<=122){
                sb.append(ch);

            }else {
                sb.deleteCharAt(sb.length()-1);
                continue;
            }

        }

        return sb.toString();
    }

    public int[] queryResults(int limit, int[][] queries) {
        int[] resultArr = new int[queries.length];
        Map<Integer,List<Integer>> colourVsCountMap = new HashMap<>();
        int[] ballArr = new int[limit+1];

        Arrays.fill(ballArr,-1);

        int index=0;
        for (int[] query : queries){
            int ball = query[0];
            int colour = query[1];


            if (ballArr[ball] == -1){
                ballArr[ball] =colour;
                List<Integer> ballList  = colourVsCountMap.getOrDefault(colour, new ArrayList<>());
                 ballList.add(ball);
                 colourVsCountMap.put(colour,ballList);
            }else {
                int oldColour = ballArr[ball];
                if(colourVsCountMap.get(oldColour).size()==1){
                    colourVsCountMap.remove(oldColour);
                }else {
                    List<Integer> ballList = colourVsCountMap.get(oldColour);
                    ballList.remove( Integer.valueOf(ball));
                    colourVsCountMap.put(oldColour,ballList);
                }


                ballArr[ball] =colour;
                List<Integer> ballList  = colourVsCountMap.getOrDefault(colour, new ArrayList<>());
                ballList.add(ball);
                colourVsCountMap.put(colour,ballList);
            }


            System.out.println(Arrays.toString(ballArr));
            System.out.println(colourVsCountMap);

            System.out.println("***************88");
            resultArr[index]= colourVsCountMap.size();
            index++;

        }


        return resultArr;
    }


    public int tupleSameProduct(int[] nums) {

        int totalTuples =0;
        Map<Integer,Integer> productVsCount = new HashMap<>();

        for (int i =0;i<nums.length;i++){
            for (int j = i+1;j<nums.length;j++){
                int product = nums[i]*nums[j];
                productVsCount.put(product,productVsCount.getOrDefault(product,0)+1);
            }
        }

        for (int product :productVsCount.keySet()){
            if (productVsCount.get(product)>=2){
                int n =productVsCount.get(product);
                int possibleCombinations = (n * (n-1))/2;
                totalTuples= totalTuples+(8*possibleCombinations);
            }
        }

        return totalTuples;
    }

    public boolean areAlmostEqual(String s1, String s2) {

        List<Integer> missingIndexList = new ArrayList<>();
        for (int i =0;i<s2.length();i++){
            if (s2.charAt(i) != s1.charAt(i)){
                missingIndexList.add(i);
                if (missingIndexList.size()>2){
                    return false;
                }
            }
        }

        if (missingIndexList.size() ==1){
            return false;
        }

        if (!missingIndexList.isEmpty()){
            int firstIndex = missingIndexList.get(0);
            int secondIndex =missingIndexList.get(1);

            if (s1.charAt(firstIndex) == s2.charAt(secondIndex) && s1.charAt(secondIndex) == s2.charAt(firstIndex)){
                return true;
            }else {
                return false;
            }

        }



        return true;
    }



    public int maxAscendingSum(int[] nums) {
        int maxSum =Integer.MIN_VALUE;
        if (nums.length ==1){
            return nums[0];
        }

        int prevElement = nums[0];
        int currentSum = nums[0];
        for (int i =1;i<nums.length;i++){
            if (nums[i]<=prevElement){
                maxSum=Math.max(currentSum,maxSum);
                currentSum=nums[i];
            }else {
                currentSum=currentSum+nums[i];
            }
            prevElement= nums[i];
        }

        maxSum=Math.max(currentSum,maxSum);

        return maxSum;
    }
}
