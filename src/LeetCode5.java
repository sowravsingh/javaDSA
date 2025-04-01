import java.util.*;

public class LeetCode5 {


    public static void main(String[] args) {
//        String source="aaaa";
//        String target ="bbbb";
//        char[] original={'a','c'};
//        char[] changed ={'c','b'};
//        int[] cost ={2,5,3,4,1};
//        System.out.println(Arrays.toString(sortArray(cost)));
        int[][] grid1={{1,1,1,0,0},{0,1,1,1,1},{0,0,0,0,0},{1,0,0,0,0},{1,1,0,1,1}};
        int[][] grid2 ={{1,1,1,0,0},{0,0,1,1,1},{0,1,0,0,0},{1,0,1,1,0},{0,1,0,1,0}};
        countSubIslands(grid1,grid2);
    }



    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        if (!wordList.contains(endWord)){
            return 0;
        }
        int count =0;
        Set<String> visited =new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.add(beginWord);

        while (!q.isEmpty()){
            int size = q.size();
            count++;
            while (size>0){
                String element =q.remove();
                for (int i =0;i<element.length();i++){
                    char[] chars= element.toCharArray();
                    for(char c ='a';c<='z';c++){
                        chars[i]=c;
                        String tempString = new String(chars);
                        if(wordList.contains(tempString) && !visited.contains(tempString)){
                            if(tempString.equalsIgnoreCase(endWord)){
                                return count+1;
                            }

                            q.add(tempString);
                            visited.add(tempString);
                        }
                    }
                }


                size--;
            }
        }

        return 0;
    }

    static int[][] grd;
    public static int countSubIslands(int[][] grid1, int[][] grid2) {
        int count =0;
        grd= grid2;
        for (int i =0;i<grd.length;i++){
            for (int j =0;j<grd[0].length;j++){
                if(grd[i][j] ==0){
                    continue;
                }

                if(countRecursion(grid1, i,j)){
                    count++;
                }
            }
        }

        return count;
    }


    public static boolean countRecursion(int[][] grid1 ,int row , int col){
        if(row== grd.length || row<0 || col == grd[0].length|| col<0 ||grd[row][col] ==0){
            return  true;
        }


        System.out.println(row);
        System.out.println(col);
        if(grd[row][col] ==1  && grid1[row][col]==1){
            grd[row][col] =0;
            boolean up = countRecursion(grid1 ,row-1 ,  col);
            boolean down = countRecursion(grid1 , row+1 ,  col);
            boolean right = countRecursion(grid1 ,row ,  col+1);
            boolean left = countRecursion(grid1 ,row ,  col-1);

            return up && down && right && left;
        }else{
            return false;
        }


    }



    public static int[] sortArray(int[] nums) {
        if(nums.length==1){
            return nums;
        }


        int mid = nums.length/2;

        int[] leftArray = sortArray(Arrays.copyOfRange(nums,0,mid));
        int[] rightArray = sortArray(Arrays.copyOfRange(nums,mid,nums.length));
        return mergeArrays(leftArray,rightArray);
    }

    public static int[] mergeArrays(int[] left, int[] right){
        int[] finalArray = new int[left.length+right.length];
        int leftStart =0;
        int rightStart =0;
        int count =0;
        while(leftStart <left.length && rightStart<right.length){
            if(left[leftStart]<right[rightStart]){
                finalArray[count]=left[leftStart];
                count++;
                leftStart++;
            }else{
                finalArray[count]=right[rightStart];
                count++;
                rightStart++;
            }
        }

        while(leftStart<left.length){
            finalArray[count]=left[leftStart];
            count++;
            leftStart++;
        }

        while(rightStart<right.length){
            finalArray[count]=right[rightStart];
            count++;
            rightStart++;
        }

        return finalArray;
    }

   static int totalTeams =0;
    public static int numTeams(int[] rating) {
        List<Integer> arrList = new ArrayList<>();
        for(int i =0;i<rating.length;i++){
            arrList.add(rating[i]);
            numTeamsHelper(rating,arrList,i+1,rating[i]);
            numTeamsHelper2(rating,arrList,i+1,rating[i]);
            arrList.remove(arrList.size()-1);
        }
        return totalTeams;
    }

    public static void numTeamsHelper(int[] rating , List<Integer> arrList, int start ,int element){
        if(start ==rating.length){
            if(arrList.size()==3){
                totalTeams++;
            }
            return ;
        }

        if(arrList.size() ==3){
            totalTeams++;
            return;
        }

        for(int i =start;i<rating.length;i++){
            if(rating[i]>element){
                arrList.add(rating[i]);
                numTeamsHelper(rating,arrList,i+1,rating[i]);
                arrList.remove(arrList.size()-1);
            }
        }
    }


    public static void numTeamsHelper2(int[] rating , List<Integer> arrList, int start ,int element){
        if(start ==rating.length){
            if(arrList.size()==3){
               // System.out.println(" first return "+arrList);
                totalTeams++;
            }
            return ;
        }

        if(arrList.size() ==3){
            //System.out.println(" second return "+arrList);
            totalTeams++;
            return;
        }

        for(int i =start;i<rating.length;i++){
            if(rating[i]<element){
                arrList.add(rating[i]);
                numTeamsHelper2(rating,arrList,i+1,rating[i]);
                arrList.remove(arrList.size()-1);
            }
        }
    }


    public static long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        long minimumCost =0;
        for(int i =0;i<source.length();i++){
            char src = source.charAt(i);
            char tgt = target.charAt(i);
            long min =minimumCostRecur(src,tgt,original,changed,cost,0,false);
            System.out.println(" min : "+min+" for src : "+src+ " target "+tgt);
            if(min==0 && (src !=tgt)){
                return -1;
            }else{
                minimumCost= minimumCost+min;
            }
        }


        return minimumCost;
    }


    public static long minimumCostRecur(char src, char tgt, char[] original,char[] changed, int[] cost, long totalCost,boolean hasfound){
        if(src==tgt){
            return totalCost;
        }
         long Number =0;
        long findNumber =0;
        for(int i =0;i<original.length;i++){
            if(original[i]==src && changed[i]==tgt){
                findNumber=totalCost+cost[i];
                hasfound=true;
            }else  if(original[i]==src && changed[i]!=tgt){
                Number =minimumCostRecur(changed[i],tgt,original,changed,cost,totalCost+cost[i],false);
                hasfound=true;
            }
        }

//        if(Number ==0 ){
//            if(hasfound){
//                return totalCost;
//            }
//            else{
//                return 0;
//            }
//        }else {
//            return Math.min(Number,totalCost);
//        }
        if(hasfound){
            if(findNumber==0 || Number==0){
                return findNumber==0?Number:findNumber;
            }else{
                return Math.min(findNumber,Number);
            }
        }else {
            return 0;
        }

    }




    public static int[] sortJumbled(int[] mapping, int[] nums) {
        Map<Integer,List<Integer>> resultMap =new HashMap<>();
        List<Integer> list= new ArrayList<>();
        int[] arr = new int[nums.length];
        for (int i =0;i<nums.length;i++){
            int number = nums[i];
            int newMappedValue = getNewMappedValues(number,mapping);
            List<Integer> indexList = resultMap.getOrDefault(newMappedValue,new ArrayList<>());
            indexList.add(i);
            resultMap.put(newMappedValue,indexList);
            if(!list.contains(newMappedValue)){
                list.add(newMappedValue);
            }
        }

       Collections.sort(list);
        System.out.println(resultMap);
        System.out.println(list);
        int count=0;
        for(int num :list){
            List<Integer> indexList= resultMap.getOrDefault(num,new ArrayList<>());
            indexList.stream().sorted();
          //  System.out.println(indexList);
            for(int index : indexList){
                arr[count]=nums[index];
                count++;
            }
        }

        System.out.println(Arrays.toString(arr));
        return arr;
    }

    public static int getNewMappedValues(int number,int[] mapping){
        int newNumber = 0;
        while(number>0){
            int rem = number%10;
            newNumber= newNumber*10+rem;

            number=number/10;

        }
        int finalNumber =0;
        while(newNumber>0){
            int rem = newNumber%10;
            finalNumber= finalNumber*10+mapping[rem];

            newNumber=newNumber/10;

        }

        return finalNumber;
    }

    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int[][]resultMatrix= new int[rowSum.length][colSum.length];

        for(int i =0;i<rowSum.length;i++){
            for(int j =0;i<colSum.length;j++){
                resultMatrix[i][j]=Math.min(rowSum[i],colSum[j]);
                rowSum[i]=rowSum[i]-resultMatrix[i][j];
                colSum[j]=colSum[j]-resultMatrix[i][j];
            }
        }
        return resultMatrix;
    }

    public String[] sortPeople(String[] names, int[] heights) {
        Map<Integer,String> heightVSNameMap = new HashMap<>();
        for(int i =0;i<heights.length;i++){
            heightVSNameMap.put(heights[i],names[i]);
        }
        Arrays.sort(heights);

        //   System.out.println(Arrays.toString(heights));
        String[] resultArr = new String[names.length];
        int count=0;
        for(int i = heights.length-1;i>=0;i--){
            //System.out.println(heights[i]);
            resultArr[count]=heightVSNameMap.get(heights[i]);
            count++;
        }
        return resultArr;
    }

    public List<Integer> luckyNumbers (int[][] matrix) {
        List<Integer> arrlist = new ArrayList<>();

        List<Integer> rowList= new ArrayList<>();
        int[] colList = new int[matrix[0].length];

        int count =0;
        for (int[] arr : matrix){
            int minRow = Arrays.stream(arr).min().getAsInt();
            rowList.add(count,minRow);

            for(int i =0;i<arr.length;i++){
                int maxCol = colList[i];
                maxCol=Math.max(maxCol,arr[i]);
               colList[i]= maxCol;
            }
            count++;
        }

        for(int num :colList){
            if(rowList.contains(num)){
                arrlist.add(num);
            }
        }
        System.out.println(rowList);
        System.out.println(Arrays.toString(colList));
        return arrlist;

    }



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
    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> arrList = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()){
            int levelSize = queue.size();
            List<Integer> list = new ArrayList<>();
            for(int i =0;i<levelSize;i++){
                TreeNode node = queue.remove();
                list.add(node.val);
                if(node.left!=null){
                    queue.add(node.left);
                }

                if(node.right!=null){
                    queue.add(node.right);
                }
            }
            arrList.add(list);
        }

        return arrList;

    }
    public static int minOperations(String[] logs) {
        Stack<String> stack = new Stack<>();
        for(String operation : logs){
            if(operation.equalsIgnoreCase("../") && !stack.isEmpty()){
                stack.pop();
            }else if (operation.equalsIgnoreCase("../") && stack.isEmpty()){
                continue;
            }else if (operation.equalsIgnoreCase("./")){
                continue;
            }else{
                stack.push(operation);
            }
        }

        System.out.println(stack);
        return stack.size();

    }

    public double averageWaitingTime(int[][] customers) {
        double waitingTime = 0;
        int totaltime = customers[0][0];
        int count =0;
        for(int[] nums : customers){
            if(totaltime<nums[0]){
                totaltime= nums[0]+nums[1];
            }else{
                totaltime= totaltime+nums[1];
            }

            waitingTime=waitingTime+(totaltime-nums[0]);
            count++;
        }
        System.out.println(waitingTime);
        System.out.println(count);
        return waitingTime/count;

    }

    public boolean threeConsecutiveOdds(int[] arr) {
        int count =0;
        for(int num :arr){
            if(num%2!=0){
                count++;
            }else{
                count=0;
            }

            if(count ==3){
                return true;
            }
        }
        return false;
    }



    public static int findTheWinner(int n, int k) {
        int count =1;
        int index =0;
        List<Integer> arrList  = new ArrayList<>();
        for(int i =1;i<=n;i++){
            arrList.add(i);
        }

        while(arrList.size()>1){
            if(count==k){
                System.out.println("came for index "+index+ "with count"+count);
                arrList.remove(index);
                count=1;
               // index++;
            }else{
                index++;
                count++;
            }


            if(index>=arrList.size()){
                index=0;
            }
        }

        return arrList.get(0);
    }



    public static int numWaterBottles(int numBottles, int numExchange) {
        int totalcount =0;
        totalcount=totalcount+numBottles;
        int availableBottles =numBottles;

        while(availableBottles>= numExchange){
            totalcount=totalcount+(availableBottles/numExchange);
            availableBottles=(availableBottles/numExchange)+(availableBottles%numExchange);
        }

        return totalcount;
    }

    public static int minInsertions(String s) {
        int required =0;
        for(char c :s.toCharArray()){
            if(required ==0 && c ==')'){
                required=required+2;
            }else if(c=='('){
                required=required+2;
            }else if (c ==')'){
                required =required-1;
            }
        }
        return required;
    }




    public int minAddToMakeValid(String s) {
        int count =0;
        Stack<Character> stack = new Stack<>();
        for(char c : s.toCharArray()){
            if(c==')'){
                if(stack.isEmpty()){
                    count++;
                }else{
                    stack.pop();
                }
            }else{
                stack.push(c);
            }
        }

        return count+stack.size();
    }


    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(char c :s.toCharArray()){
            if(c =='{' || c=='[' || c=='('){
                stack.push(c);
            }else {
                if(c == '}'){
                    if(!stack.isEmpty() && stack.peek()=='{'){
                        stack.pop();
                    }else{
                        return false;
                    }
                }
                else if(c == ')'){
                    if(stack.peek()=='('){
                        stack.pop();
                    }else{
                        return false;
                    }
                }
                else if(c == ']'){
                    if(stack.peek()=='['){
                        stack.pop();
                    }else{
                        return false;
                    }
                }
            }
        }

        if(stack.isEmpty()){
            return true;
        }else{
            return false;
        }
    }


    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };

    public Node connect(Node root) {
        if(root==null){
            return null;
        }

        Node firstNode= root;
        while(root.left !=null){

            Node node = root;
            while (node !=null){
                node.left.next= node.right;
                if(node.next!=null){
                    node.right.next= node.next.left;
                }
                node=node.next;
            }


            root=root.left;
        }

        return firstNode;
    }
}
