import java.util.*;

public class leetCodePRoblems2 {

    public static List<List<String>> groupAnagrams(String[] strs) {

        List<List<String>> resultList = new ArrayList<>();
        //int[][] dp =new int[][];
        String[] dp = new String[strs.length];
        for(int i =0;i<strs.length;i++){
            String s = strs[i];
            char[] charArray = s.toCharArray();
            Arrays.sort(charArray);
            String sortedStr = new String(charArray);
            dp[i]=sortedStr;

        }
        Map<String,List<Integer>> indexMAp = new HashMap<>();
        for(int i =0;i<dp.length;i++){
            if(indexMAp.containsKey(dp[i])){
                List<Integer> ls = indexMAp.get(dp[i]);
                ls.add(i);
                indexMAp.put(dp[i],ls);
            }else{
               List<Integer> ls = new ArrayList<>();
               ls.add(i);
                indexMAp.put(dp[i],ls);
            }
        }

        for(Map.Entry<String,List<Integer>> EntrySet :indexMAp.entrySet()){
            List<String> indexString = new ArrayList<>();
            List<Integer> indexValues = EntrySet.getValue();
            for(int index :indexValues){
                indexString.add(strs[index]);
            }
            resultList.add(indexString);
        }

        System.out.println(Arrays.toString(dp));
        System.out.println(indexMAp);

        return resultList;
    }

    public static List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> processed= new ArrayList<>();
        List<Integer> rs= returnLargestDivisbleSubest(nums,processed,0);
        return rs;
    }

    public static List<Integer> returnLargestDivisbleSubest(int[] nums,List<Integer> processed,int index){
        if(index== nums.length){
            //System.out.println(processed);
            List<Integer> rs= new ArrayList<>();
            rs.addAll(processed);
            return rs;
        }

        int processsedNum = processed.size()==0?1:processed.get(processed.size()-1);
        int currentNum= nums[index];
        List<Integer> consideredList = new ArrayList<>();
        List<Integer> notConSideredList = new ArrayList<>();
        if( currentNum%processsedNum==0){
            System.out.println("entere here");
            processed.add(currentNum);
            consideredList = returnLargestDivisbleSubest(nums,processed,index+1);
            System.out.println(consideredList);
            processed.remove(processed.size()-1);
        }
        notConSideredList= returnLargestDivisbleSubest(nums,processed,index+1);
        return consideredList.size()>notConSideredList.size()?consideredList:notConSideredList;
    }
//
//    public List<List<Integer>> combine(int n, int k) {
//    }



    public static int countSubstrings(String s) {
        int ans =0;
        for(int i =0;i<s.length();i++){
            int evenCount=returnPalinCount(i,i+1,s);
            int oddCount = returnPalinCount(i,i,s);
            System.out.println("&&&&&&&&&&&&&&&&&&");
            ans = ans+oddCount+evenCount;
        }
        return ans;
    }


    public static int returnPalinCount(int left,int right,String s){
        int count =0;
        while (left>=0 && right<s.length()){
            if(s.charAt(left)==s.charAt(right)){
                System.out.println("matching for "+left+right);
                count++;
                left--;
                right++;
            }else {
                return count;
            }
        }
        return count;
    }

    public static List<List<Integer>> returnPossible(int n ,int start,int k,List<Integer> list){
        if(list.size()==k){
            List<List<Integer>> ls = new ArrayList<>();
            List<Integer> ll = new ArrayList<>();
            ll.addAll(list);
            System.out.println(list);
            ls.add(ll);
            return ls;
        }
        if(start>n && list.size()!=k ){
            return new ArrayList<>();
        }

        List<List<Integer>> resultSet = new ArrayList<>();
        for(int i = start;i<=n;i++){
            list.add(i);
            List<List<Integer>> rs = returnPossible(n,i+1,k,list);
            resultSet.addAll(rs);
            list.remove(list.size()-1);
        }

        return resultSet;

    }


    public  static void returnUniquePossible(int end ,int start,int k,List<Integer> list,int[] nums){
        if(list.size()==k){
            List<Integer> ll = new ArrayList<>();
            ll.addAll(list);
            rs.add(ll);
            return;
        }
        if(start==end && list.size()!=k ){
            returnUniquePossible(nums.length,0,k,list,nums);
            return ;
        }

        for(int i = start;i<end;i++){
            if(!list.contains(nums[i])){
                list.add(nums[i]);
                returnUniquePossible(end,i+1,k,list,nums);
                list.remove(list.size()-1);
            }
        }

    }


    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> rs = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        returnPermutations(nums,list,rs);
        return rs;
    }

    public void returnPermutations(int[] nums,List<Integer> list, List<List<Integer>> rs){
        if(list.size()==nums.length){
            List<Integer> ll = new ArrayList<>();
            ll.addAll(list);
            rs.add(ll);
            return;
         }

        for (int num:nums){
            if(list.contains(num)){
                continue;
            }
            list.add(num);
            returnPermutations(nums,list,rs);
            list.remove(list.size()-1);
        }

    }



    public String firstPalindrome(String[] words) {

        for(String s:words){
            if(checkPalindrome(s)){
                return s;
            }
        }

        return "";

    }


    public boolean checkPalindrome(String s){
        int start =0;
        int end =s.length()-1;
        while(start<end){
            if(!(s.charAt(start)==s.charAt(end))){
                return false;
            }

            start++;
            end--;
        }


        return true;
    }


    public static void returnCombinationsOfTarget(int target, int[] nums, List<Integer> list,int start){
        if(target==0){
            List<Integer> ll = new ArrayList<>();
            ll.addAll(list);
            rs.add(ll);
        }

        for(int i =start;i<nums.length;i++){
            if(nums[i]<= target){
                list.add(nums[i]);
                returnCombinationsOfTarget(target-nums[i],nums,list,i);
                list.remove(list.size()-1);
            }
        }

    }

    public int[] rearrangeArray(int[] nums) {
        int[] finalArray = new int[nums.length];
        int pos =0;int neg =1;
        for(int i =0;i<nums.length;i++){
            if(nums[i]>=0){
                finalArray[pos]= nums[i];
                pos=pos+2;
            }else{
                finalArray[neg]=nums[i];
                neg =neg+2;
            }
        }
        return finalArray;
    }


    public static void returnParanthesis( int value ,int k,String s){
        if(value==k){
            if(!ls.contains(s)){
               // String s= String.valueOf(sb);
                ls.add(s);
            }
            return;
        }

        String leftString ="()"+s;
        returnParanthesis(value+1,k,leftString);
        String  rightString=s+"()";
        returnParanthesis(value+1,k,rightString);
        String wholeString="("+s+")";
        returnParanthesis(value+1,k,wholeString);


    }


//    public boolean exist(char[][] board, String word) {
//
//    }

    public boolean checkForString(int index,String word, char[][] board, boolean[][] steps,int r, int c){
        if(index ==word.length()){
            return true;
        }

        if(steps[r][c]){
            return false;
        }

        if(board[r][c] == word.charAt(index)){
            steps[r][c] =true;
            boolean isStep = checkForString(index+1,word,board,steps,r,c+1);
            boolean isStep1 = checkForString(index+1,word,board,steps,r+1,c);
            steps[r][c]=false;
        }


        return true;
    }



    static List<String> ls = new ArrayList<>();
    static List<List<Integer>> rs = new ArrayList<>();
    public static void main(String[] args) {

        returnParanthesis(0,3,"");
        System.out.println(ls);
    }



}




























