import java.util.*;

public class LeetCode3 {


    int bottom_i,bottom_j;
    public int[][] findFarmland(int[][] land) {
        List<List<Integer>> lists = new ArrayList<>();

        for(int i=0;i<land.length;i++){
            for (int j=0 ;j<land[0].length;j++){
                List<Integer> list = new ArrayList<>();
                bottom_i=i;
                bottom_j=j;
                list.add(i);
                list.add(j);
                dfs(land,i,j);
                list.add(bottom_i);
                list.add(bottom_j);
            }
        }

        int[][] finalArr = new int[lists.size()][4];
        int inner =0;
        for(List<Integer> ll :lists){
            finalArr[inner][0]= ll.get(0);
            finalArr[inner][1]= ll.get(1);
            finalArr[inner][2]= ll.get(2);
            finalArr[inner][3]= ll.get(3);
            inner++;
        }
        return finalArr;
    }


    public void  dfs(int[][] land,int i ,int j){
        if(i==land.length || j==land[0].length || land[i][j]==0){
            return ;
        }
        land[i][j]=0;
        bottom_i=Math.max(i,bottom_i);
        bottom_j=Math.max(j,bottom_j);
        dfs(land,i+1,j);
        dfs(land,i,j+1);

    }



    static  int count=0;

    public static int totalNQueens(int n) {
        boolean[][] board = new boolean[n][n];
        NQueensPossibility(0,0,board);
        return count;

    }

    public static void NQueensPossibility(int r,int c,boolean[][] board){
        if(r== board.length){
            count++;
            return;
        }

        for(int i =0;i<board.length;i++){
            if(isValidCell(r,i,board)){
                board[r][i]=true;
                NQueensPossibility(r+1,c,board);
                board[r][i]=false;
            }
        }
        return;


    }

    public static boolean isValidCell(int r, int c, boolean[][] board){


        //checking downwards
        for(int i =r;i>=0;i--){
            if(board[i][c]==true){
                return false;
            }
        }

        //checking left diagnol
        int maxLeft = Math.min(r,c);
        for(int i=0;i<=maxLeft;i++){
            if(board[r-i][c-i]==true){
                return false;
            }
        }

        //checking right diagnol
        int maxRight = Math.min(r,board.length-c-1);
        for(int i=0;i<=maxRight;i++){
            if(board[r-i][c+i]==true){
                return false;
            }
        }

        return true;
    }


    public int findMaxK(int[] nums) {
        List<Integer> positiveList = new ArrayList<>();
        List<Integer> negativeList = new ArrayList<>();


        for(int num : nums){
            if(num >0){
                positiveList.add(num);
            }else{
                negativeList.add(num*(-1));
            }
        }
        positiveList.stream().sorted();
        for(int i =positiveList.size()-1;i>=0;i--){
            if(negativeList.contains(positiveList.get(i))){
                return positiveList.get(i);
            }
        }


        return -1;
    }


    public static int numRescueBoats(int[] people, int limit) {
        int totalBoats =0;
        Arrays.sort(people);
        int i =0;
        int j=people.length-1;
        while (i<=j){
            if(people[i]+people[j]==limit){
                totalBoats++;
                i++;
                j--;
            }else{
                totalBoats++;
                j--;
            }
        }
        return totalBoats;
    }


     public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }


    public static ListNode removeNodes(ListNode head) {

        ListNode prevNode =null;
        ListNode currentNode =head;
        ListNode nextNode = null;

        while (currentNode.next!=null){
            nextNode=currentNode.next;
            currentNode.next=prevNode;
            prevNode=currentNode;
            currentNode=nextNode;
        }
        currentNode.next=prevNode;


        nextNode=null;
        prevNode=currentNode;
        currentNode=currentNode.next;
        while (currentNode!=null){
            if(currentNode.val<prevNode.val){
                currentNode=currentNode.next;
            }else{
                nextNode = currentNode.next;
                currentNode.next=prevNode;
                prevNode=currentNode;
                currentNode=nextNode;
            }
        }

        return prevNode;
    }



    public static ListNode doubleIt(ListNode head) {

        ListNode prevNode =null;
        ListNode currentNode = head;
        ListNode nextNode =null;

        while(currentNode.next !=null){
            nextNode = currentNode.next;
            currentNode.next = prevNode;
            prevNode= currentNode;
            currentNode= nextNode;
        }

        currentNode.next=prevNode;
        prevNode=null;
        int carry =0;
        while (currentNode.next!=null){
            nextNode=currentNode.next;
            int value = (currentNode.val*2)+carry;
            if(value>9){
                carry = value/10;
                value=value%10;
            }

            currentNode.val= value;
            currentNode.next=prevNode;
            prevNode=currentNode;
            currentNode=nextNode;
        }

        //System.out.println(carry);
        int value = (currentNode.val*2)+carry;
        if(value>9){
            carry = value/10;
            value=value%10;
        }else{
            carry=0;
        }
        currentNode.val = value;
        currentNode.next=prevNode;


        System.out.println(carry);
        if(carry==0){
            return currentNode;
        }
        else{
            nextNode = new ListNode(carry,currentNode);
            return nextNode;
        }

    }


    public static String[] findRelativeRanks(int[] score) {
        Map<Integer,Integer> positionMap = new HashMap<>();
        for(int i =0 ;i<score.length;i++){
            positionMap.put(score[i],i);
        }
        Arrays.sort(score);
        String[] str = new String[score.length];
        for(int i =0 ;i<score.length;i++){
            int original  = positionMap.get(score[i]);
            int value = score.length-i;
            if(value ==1){
                str[original] = "Gold Medal";
            }else if(value ==2){
                str[original] = "Silver Medal";
            }else if (value ==3){
                str[original] = "Bronze Medal";
            }else{
                str[original] = String.valueOf(score.length-i);
            }
        }
        return str;

    }


    public static int compareVersion(String version1, String version2) {
        String[] firstVersion= version1.split("\\.");
        String[] secondVersion = version2.split("\\.");
        int minLen = Math.min(firstVersion.length,secondVersion.length);
        String[] bigArray = firstVersion.length>secondVersion.length? firstVersion :secondVersion;
;
        int value =0;
       // System.out.println(Arrays.toString(firstVersion));
        int i=0;
        int j=0;
        while(i<firstVersion.length || j<secondVersion.length){
            int num1=0;
            int num2 =0;
            if(i< firstVersion.length){
                num1= Integer.parseInt(firstVersion[i]);
            }

            if(j<secondVersion.length){
                num2= Integer.parseInt(secondVersion[j]);
            }

            if(num1>num2){
                return 1;
            }

            if(num2>num1){
                return -1;
            }
            i++;
            j++;


        }

        return 0;

    }

    public int longestPalindrome(String s) {
        Map<Character,Integer> letterVsCount = new HashMap<>();
        for(int i =0;i<s.length();i++){
            char c = s.charAt(i);
            int count = letterVsCount.getOrDefault(c,0);
            letterVsCount.put(c,count+1);
        }
        boolean oneIsPresent=false;
        boolean oddConsidered =false;
        int totalLength =0;
        System.out.println(letterVsCount);
        for(Map.Entry<Character,Integer> entry : letterVsCount.entrySet()){
            int count = entry.getValue();
            if(count%2 ==0){
                totalLength=totalLength+count;
            }else if (count ==1){
                oneIsPresent =true;
            }else if(count%2 !=0 && !oddConsidered){
                totalLength=totalLength+count;
            }
        }

        if(!oddConsidered && oneIsPresent){
            totalLength++;
        }
        return totalLength;

    }



    public static List<String> commonChars(String[] words) {
        int[] refArr = new int[26];
        String firstWord = words[0];
        List<String> resultList = new ArrayList<>();

        for(int i=0;i<firstWord.length();i++){
            char c = firstWord.charAt(i);
            refArr[c-'a']++;
        }

        //  System.out.println(Arrays.toString(refArr));
        for(int i =1;i<words.length;i++){
            String str = words[i];
            int[] strArr = new int[26];
            for(int j=0;j<str.length();j++){
                char c = str.charAt(j);
                strArr[c-'a']++;
            }

            for(int j =0;j<refArr.length;j++){
                refArr[j]= Math.min(refArr[j],strArr[j]);
            }
        }

        //System.out.println(Arrays.toString(refArr));
        for(int i =0;i<refArr.length;i++){
            if(refArr[i]!=0){
                for(int j=0;j<refArr[i];j++){
                    char a = (char) ('a' + i);
                    resultList.add(String.valueOf(a));
                }
            }
        }

        return resultList;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(9);
        ListNode node2 = new ListNode(0);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(8);
        node1.next=node2;
        node2.next=node3;


        ListNode finalNode = doubleIt(node1);

        ListNode tmp = finalNode;
        while(tmp !=null){
            System.out.print(tmp.val +"---> ");
            tmp =tmp.next;
        }
        System.out.println("END");


    }


}
