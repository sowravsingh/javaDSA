
import java.math.BigInteger;
import java.util.*;

public class Leetcodeproblem4 {


    // * Definition for singly-linked list.
//      public class ListNode {
//          int val;
//          ListNode next;
//          ListNode() {}
//          ListNode(int val) { this.val = val; }
//          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
//      }
     //*/

     //* Definition for a binary tree node.
      public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode() {}
          TreeNode(int val) { this.val = val; }
          TreeNode(int val, TreeNode left, TreeNode right) {
              this.val = val;
              this.left = left;
              this.right = right;
          }
      }


    public static void main(String[] args) {
        //System.out.println(getLucky("za",1));
//        ListNode ll = new ListNode(1);
//        ll.next= new ListNode(2);
//        ll.next.next= new ListNode(3);
//        ll.next.next.next= new ListNode(4);
//        ll.next.next.next.next= new ListNode(5);
//        ll.next.next.next.next.next= new ListNode(6);
//        ll.next.next.next.next.next.next= new ListNode(7);
//        ll.next.next.next.next.next.next.next= new ListNode(8);
//        ll.next.next.next.next.next.next.next.next= new ListNode(9);
//        ll.next.next.next.next.next.next.next.next.next= new ListNode(10);

        String[] str1={"abc","abcd"};
        Leetcodeproblem4 ll  = new Leetcodeproblem4();
        ll.sumPrefixScores(str1);

    }



    public int[] sumPrefixScores(String[] words) {

          int[] resultArr = new int[words.length];
          Map<String,Integer> subStringVSCount =new HashMap<>();
          Map<String,List<String>> wordVsSubString=new LinkedHashMap<>();

          for (String word :words){
              if (wordVsSubString.get(word)==null){
                  List<String> subStringList = new ArrayList<>();
                  StringBuilder sb = new StringBuilder();
                  for (int i =0;i<word.length();i++){
                      sb.append(word.charAt(i));
                      subStringVSCount.put(sb.toString(),0);
                      subStringList.add(sb.toString());
                  }

                  wordVsSubString.put(word,subStringList);
              }


          }

          for (String word : words){
              StringBuilder sb = new StringBuilder();
              for (int i =0;i<word.length();i++){
                  sb.append(word.charAt(i));
                  if(subStringVSCount.containsKey(sb.toString())){
                      int value = subStringVSCount.get(sb.toString());
                      subStringVSCount.put(sb.toString(),value+1);
                  }
              }

          }

          int index =0;
        for (String key : words){
            int count =0;

           List<String> subStringList = wordVsSubString.get(key);
           for (String subString :subStringList){
               count=count+subStringVSCount.get(subString);
           }
            resultArr[index]=count;
            index++;
        }
        System.out.println(wordVsSubString);
        System.out.println(Arrays.toString(resultArr));
          return resultArr;

    }




    public List<Integer> lexicalOrder(int n) {
          List<Integer> resultList = new ArrayList<>();

          for (int i =1;i<=9;i++){
              if (i<=n){
                  resultList.add(i);
                  lexicalRecurssion(i,resultList,n);
              }else{
                  return resultList;
              }

          }

          return resultList;
    }


    int kthElement =0;
      int count =0;

    public int findKthNumber(int n, int k) {

        count=k;
        for (int i =1;i<=9;i++){
            if (i<=n){
                count--;
                if(count==0){
                    return i;
                }else {
                    lexicalKthRecurssion(i,n);
                }
            }

        }

        return kthElement;
    }

    int[] dp;
    public int minExtraChar(String s, String[] dictionary) {

        HashSet<String> hs = new HashSet<>();
        for (String dict :dictionary){
            hs.add(dict);
        }
        dp = new int[s.length()];
        Arrays.fill(dp,-1);

        return minExtraCharRecur(0,s,hs);
    }


    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        Set<Integer> prefixNumbers = new HashSet<>();
        int highestNumber =0;
        for (int num :arr1){
            prefixNumbers.add(num);
            while (num>=10){
                prefixNumbers.add(num/10);
                num=num/10;
            }
        }

        for (int num :arr2){
            if (prefixNumbers.contains(num)){
                highestNumber= Math.max(highestNumber,num);
            }else{
                while (num>=10){
                    if(prefixNumbers.contains(num/10)){
                        highestNumber= Math.max(highestNumber,num/10);
                    }
                    num=num/10;

                }
            }
        }

        if(highestNumber==0){
            return 0;
        }else {
            return String.valueOf(highestNumber).length();
        }



    }

    public int minExtraCharRecur(int index,String s,HashSet<String> dict){
        if (index ==s.length()){
            return 0;
        }

        if (dp[index]!=-1){
            return dp[index];
        }

        StringBuilder sb = new StringBuilder();
        int minCharacters = Integer.MAX_VALUE;
        for (int i = index;i<s.length();i++){
            sb.append(s.charAt(i));
            int missCharacter =0;
            if (!dict.contains(s.toString())){
                missCharacter++;
            }
            minCharacters= Math.min(minCharacters,(missCharacter+ minExtraCharRecur(index+1,s,dict)));
        }

        dp[index]= minCharacters;
        return minCharacters;
    }


    public  void lexicalKthRecurssion(int num,int n){
        if (num>n || count==0){
            return;
        }

        for (int i =0;i<=9;i++){
            int newNum =num*10+i;
            if (newNum<=n){
                System.out.println("enetred here");
                count--;
                if(count==0){
                    kthElement= newNum;
                    return;
                }else {
                    lexicalKthRecurssion(newNum,n);
                }

            }else {
                return;
            }
        }

    }

    public  void lexicalRecurssion(int num, List<Integer> resultList,int n){
          if (num>n){
              return;
          }

          for (int i =0;i<=9;i++){
              int newNum =num*10+i;
              if (newNum<=n){
                  resultList.add(newNum);
                  lexicalRecurssion(newNum,resultList,n);
              }else {
                  return;
              }
          }

    }




    public  String largestNumber(int[] nums) {
        StringBuilder sb =new StringBuilder();

        for (int i =0;i<nums.length;i++){
            for (int j =i+1;j<nums.length;j++){
                StringBuilder sb1 =new StringBuilder();
                StringBuilder sb2 =new StringBuilder();
                sb1.append(nums[i]);
                sb1.append(nums[j]);


                sb2.append(nums[j]);
                sb2.append(nums[i]);

                BigInteger num1 =new BigInteger(sb1.toString());
                BigInteger num2 =new BigInteger(sb2.toString());
                if (num2.compareTo(num1)>0){
                    nums[i]= nums[j]+nums[i];
                    nums[j]= nums[i]-nums[j];
                    nums[i]= nums[i]-nums[j];
                }
            }
        }

        int index=0;
        while (index <nums.length && nums[index]==0){
            index++;
        }

        for (int i  =index; i<nums.length;i++){
            sb.append(nums[i]);
        }


        if (sb.length()==0){
            sb.append("0");
        }
        return sb.toString();

    }



    public  int findMinDifference(List<String> timePoints) {

          int minDiefference =Integer.MAX_VALUE;
          int[]  minutesArray = new int[timePoints.size()];

          int index =0;
          for (String time : timePoints){
              int hr = Integer.parseInt(time.substring(0,2));
              int minutes = Integer.parseInt(time.substring(3,5));
              minutesArray[index]= (hr*60)+minutes;
              index++;
          }

          Arrays.sort(minutesArray);
          int prevMinutes =-1;
          for (int minutes :minutesArray ){
              if (prevMinutes ==-1){
                  prevMinutes = minutes;
              }else {
                  minDiefference= Math.min(minDiefference,(minutes- prevMinutes));
                  prevMinutes= minutes;
              }

          }

          if(minutesArray.length>=2){
              minDiefference= Math.min(minDiefference,((1440+minutesArray[0])-minutesArray[minutesArray.length-1]));
          }

          return minDiefference;
    }

    public int[][] spiralMatrix(int m, int n, ListNode head) {
          int[][] resultArr = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(resultArr[i], -1);
        }


          int firstRow =0;
          int firstCol =0;
          int lastRow = m-1;
          int lastCol =n-1;

          while (head!=null){

              for (int i=firstCol;i<=lastCol;i++){
                  if (head!=null){
                      resultArr[firstRow][i]=head.val;
                      head=head.next;
                  }else {
                      return resultArr;
                  }

              }

              firstRow++;

              for (int i =firstRow;i<=lastRow;i++){
                  if (head!=null){
                      resultArr[i][lastCol]=head.val;
                      head=head.next;
                  }else {
                      return resultArr;
                  }
              }

              lastCol--;

              for (int i = lastCol;i>=firstCol;i--){
                  if (head!=null){
                      resultArr[lastRow][i]=head.val;
                      head=head.next;
                  }else {
                      return resultArr;
                  }
              }

              lastRow--;


              for (int i =lastRow;i>=firstRow;i--){
                  if (head!=null){
                      resultArr[i][firstCol]=head.val;
                      head=head.next;
                  }else {
                      return resultArr;
                  }
              }
              firstCol++;


          }

          return resultArr;
    }

    public static String[] uncommonFromSentences(String s1, String s2) {
          String[] str1Array = s1.split(" ");
          String[] str2Array = s2.split(" ");

        Map<String,Integer> countMap = new HashMap<>();
        for (String str : str1Array){
            countMap.put(str,countMap.getOrDefault(str,0)+1);
        }
        for (String str :str2Array){
            countMap.put(str,countMap.getOrDefault(str,0)+1);
        }

        int sizeCount =0;
        for (String str : countMap.keySet()){
            if(countMap.get(str)==1){
                sizeCount++;
            }
        }

        String[] resultString = new String[sizeCount];
        int index =0;
        for (String str : countMap.keySet()){
            if(countMap.get(str)==1){
                resultString[index]=str;
                index++;
            }
        }

        return resultString;

    }

    public ListNode insertGreatestCommonDivisors(ListNode head) {
          if (head==null){
              return head ;
          }

          ListNode resultHead = head;
          while (head.next!=null){
              int value1 = head.val;
              int value2 = head.next.val;
              int gcdValue =returnGcd(value1,value2);
              ListNode nextNode = head.next;
              ListNode newNode= new ListNode(gcdValue);
              head.next= newNode;
              newNode.next=nextNode;

              head=nextNode;
          }

          return resultHead;
    }

    public int returnGcd(int x, int y ){
          int result =1;
          int num =1;
          int minValue = x<y?x:y;
          while (num<=minValue){
              if(x%num==1 && y%num==1){
                  result=num;
              }
              num++;
          }
          return result;
    }



    public static ListNode[] splitListToParts(ListNode head, int k) {
        ListNode[] resultArr= new ListNode[k];
        int currentIndex =0;
        int size =0;
        ListNode currentNode =head;
        while(currentNode!=null){
            size++;
            currentNode=currentNode.next;
        }

        int div= size/k;
        int rem = size%k;
        ListNode resultHead  =null;
        ListNode nextNode = null;
//        System.out.println(div);
//        System.out.println(rem);
        while (head!=null){
            resultHead=head;
            nextNode= head.next;

            for(int i =0;i<(div-1);i++){
                head=head.next;
                nextNode= head.next;

            }

          //  System.out.println(head.val);
            if (rem!=0){
                if(div!=0){
                    head=head.next;
                    nextNode= head.next;
                }
//                head=head.next;
//                nextNode= head.next;
                rem--;

            }

            resultArr[currentIndex]=resultHead;
            head.next=null;
            head=nextNode;
            currentIndex++;
        }


        return resultArr;
    }



    public ListNode modifiedList(int[] nums, ListNode head) {
        Set<Integer> hashset = new HashSet<>();
        for (int num :nums){
            hashset.add(num);
        }
        ListNode resultHead = null;
        ListNode nextNode =null;
        ListNode prevNode =null;

        while (head!=null){
            nextNode= head.next;
            head.next=null;
            if (!hashset.contains(head.val)){
                //System.out.println("enetered here for "+head.val);
                if (prevNode!=null){
                    prevNode.next= head;
                    prevNode= head;
                }else {
                    resultHead=head;
                    prevNode= head;
                }
            }

            head=nextNode;
        }


        return resultHead;

    }


    public boolean isSubPath(ListNode head, TreeNode root) {
        if (root==null){
            return false;
        }

        if (isSubPathRecursion(root,head)){
            return true;
        }

        return isSubPath(head,root.left)||isSubPath(head,root.right);
    }

    public boolean isSubPathRecursion(TreeNode root,ListNode head){
        if(root==null && head!=null){

            return false;
        }

        if(head==null){
            return true;
        }

        if(head.val==root.val){
            return isSubPathRecursion(root.left,head.next) || isSubPathRecursion(root.right,head.next);
        }else {
            return false;
        }
    }



    public int[] missingRolls(int[] rolls, int mean, int n) {
        int m = rolls.length;
        int[] resultArr = new int[n];
        int sum = Arrays.stream(rolls).sum();
        System.out.println(sum);
        int targetSum = (mean*(m+n))-sum;
        System.out.println("target sum "+targetSum);

        int rem = targetSum%n;
        int div = targetSum/n;

        if(targetSum>(n*6) || targetSum<n){
            return new int[0];
        }

        Arrays.fill(resultArr,div);

        for (int i =0;i<resultArr.length;i++){
            if(rem!=0){
                resultArr[i]++;
                rem--;
            }
        }



        return resultArr;

    }

    public static int getLucky(String s, int k) {
        if(s.length()==0){
            return 0;
        }

        String str ="";
        for(char c : s.toCharArray()){
            int num =c-96;
            str=str+num;
        }
        if(k ==0){
            return Integer.parseInt(str);
        }


        while (k>0){
            int sum =0;
            for(int i =0;i<str.length();i++){
                sum=sum+Integer.parseInt(String.valueOf(str.charAt(i)));
            }
            str= String.valueOf(sum);
            k--;
        }


        return Integer.parseInt(str);
    }

    public int chalkReplacer(int[] chalk, int k) {

        long prefixSum[] = new long[chalk.length];
        prefixSum[0] = chalk[0];
        for(int i=1;i<chalk.length;i++){
            prefixSum[i] = prefixSum[i-1] + chalk[i];
        }
        long sum = prefixSum[chalk.length-1];
        int remainingChalks = (int)(k % sum);

        int start =0;
        int end =chalk.length-1;


        int ans=0;
        while(start<=end){
            int mid = start + (end-start)/2;
            if(prefixSum[mid] == remainingChalks){
                return mid+1;
            }else if(prefixSum[mid] < remainingChalks){
                start = mid+1;
            }else{
                ans = mid;
                end = mid-1;
            }
        }
        return ans;
    }

    public static int[] xorQueries(int[] arr, int[][] queries) {
          int[] prefixSum = new int[arr.length];
          int[] resultArr = new int[queries.length];

          prefixSum[0]= arr[0];
          for (int i =1;i< arr.length;i++){
              int result = prefixSum[i-1]^arr[i];
              prefixSum[i]= result;
          }

          for (int num :prefixSum){
              System.out.println(num);
          }

          int index =0;
          for (int[] indexArr : queries ){
              int startIndex = indexArr[0];
              int endIndex = indexArr[1];

              if (startIndex==0){
                  resultArr[index]= prefixSum[endIndex];
              }else{
                  resultArr[index]=prefixSum[endIndex]^prefixSum[startIndex-1];
              }

              index++;
          }

          return resultArr;
    }
}
