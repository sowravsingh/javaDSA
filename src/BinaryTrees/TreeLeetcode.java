package BinaryTrees;

import java.util.*;
import java.util.stream.Collectors;

 class Node{
    Node left;
    Node right;
    int val;

    public  Node(){

    }

    public Node(int value){
        this.val=value;
    }
}
public class TreeLeetcode {

//    public class Node{
//        Node left;
//        Node right;
//        int val;
//
//        public  Node(){
//
//        }
//
//        public Node(int value){
//            this.val=value;
//        }
//    }

    public static void main(String[] args) {
        Node node = new Node(2);
    }

    public boolean flipEquiv(Node root1, Node root2) {
        if(root1==null && root2 ==null){
            return true;
        }

        if(root1==null || root2==null){
            return false;
        }

        if(root1.val != root2.val){
            return false;
        }




        if((root1.left==null && root2.left!=null) || (root1.left!=null && root2.left==null) || (root1.left!=null && root2.left!=null && root1.left.val != root2.left.val)){
            Node leftNode = root1.left;
            root1.left=root1.right;
            root1.right=leftNode;
        }


        return  flipEquiv(root1.left,root2.left) && flipEquiv(root1.right , root2.right);


    }




    public void replaceValueInTree(){
        replaceValueInTree(rootNode);
    }
    public Node replaceValueInTree(Node root) {
        Map<Integer,Map<Integer,Integer>> levelVsParentVsSum = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int level =1;
        while (!queue.isEmpty()){
            int size = queue.size();
            levelVsParentVsSum.putIfAbsent(level,new HashMap<>());
            for (int i =0;i<size;i++){
                Node node = queue.poll();
                int sum =0;
                if (node.left!=null){
                    queue.offer(node.left);
                    sum=sum+node.left.val;
                }
                if (node.right!=null){
                    queue.offer(node.right);
                    sum=sum+node.right.val;
                }

                levelVsParentVsSum.get(level).putIfAbsent(node.val,sum);

            }

            level++;
        }

        System.out.println(levelVsParentVsSum);
        root = replaceValueInTreeRecurrsion(root,null,levelVsParentVsSum,0);
        return root;
    }


    public Node replaceValueInTreeRecurrsion(Node root, Node parentRoot ,Map<Integer,Map<Integer,Integer>> levelVsParentVsSum, int currentLevel){
        if (root==null){
            return null;
        }


        root.left= replaceValueInTreeRecurrsion( root.left,  root , levelVsParentVsSum,  currentLevel+1);
        root.right= replaceValueInTreeRecurrsion( root.right,  root , levelVsParentVsSum,  currentLevel+1);

        Map<Integer,Integer> rootVsChildSum = levelVsParentVsSum.get(currentLevel);

        System.out.println(rootVsChildSum);

        int sum =0;
        if (rootVsChildSum!=null){
            for (Integer rootVal :rootVsChildSum.keySet()){
                if (parentRoot !=null && parentRoot.val!=rootVal){
                    System.out.println(parentRoot.val);
                    System.out.println("*************************");
                    sum=sum+rootVsChildSum.get(rootVal);
                }
            }
        }

        root.val=sum;
        return root;
    }


    public long kthLargestLevelSum(Node root, int k) {
        if (root == null) {
            return -1;
        }

        List<Long> levelSumList = new ArrayList<>();
        Queue<Node> queue =new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            Long levelSum =0L;
            int size = queue.size();
            for (int i =0;i<size;i++){
                Node node = queue.poll();
                levelSum=levelSum+node.val;
                if (node.left!=null){
                    queue.offer(node.left);
                }
                if (node.right!=null){
                    queue.offer(node.right);
                }

            }
            levelSumList.add(levelSum);

        }

        Collections.sort(levelSumList);
        if(levelSumList.size()<k){
            return -1;
        }else {
            return levelSumList.get(levelSumList.size()-k);
        }
    }


//    public void printPreorder(Node rootNode){
//
//        Stack<Node> st = new Stack<>();
//        Node leftNode = rootNode.left;
//        Node rightNode = rootNode.right;
//        while (leftNode!=null){
//            st.add(leftNode);
//            leftNode = leftNode.left;
//        }
//
////        while (st.isEmpty()){
////            System.out.print(st.);
////        }
//
//    }


     Map<Integer,Map<Integer,List<Integer>>> colVsRowVsMap = new HashMap<>();
    public List<List<Integer>> verticalTraversal(Node root) {
        List<List<Integer>> resultList = new ArrayList<>();
        // System.out.println(indexVsMap);
        veritcalRecursion(root,0,0);
        System.out.println(colVsRowVsMap);
        int min= colVsRowVsMap.keySet().stream().min((x,y) ->x.compareTo(y)).get();
        int max= colVsRowVsMap.keySet().stream().max((x,y) ->x.compareTo(y)).get();
        System.out.println(max);
        System.out.println(min);
        for(int i =min;i<=max;i++){
            List<Integer> colList = new ArrayList<>();
            Map<Integer,List<Integer>> rowVsMap = colVsRowVsMap.get(i);
            int minRow= rowVsMap.keySet().stream().min((x,y) ->x.compareTo(y)).get();
            int maxCol= rowVsMap.keySet().stream().max((x,y) ->x.compareTo(y)).get();
            for (int j =minRow;j<maxCol;j++){
                List<Integer> rowWiseData = rowVsMap.getOrDefault(j, new ArrayList<>());
                List<Integer> sortedNumbers= rowWiseData.stream().sorted().collect(Collectors.toList());
                colList.addAll(sortedNumbers);
            }
            resultList.add(colList);

        }

        return resultList;
    }


    public void veritcalRecursion(Node node,int row, int column){
        if (node==null){
            return;
        }

        Map<Integer,List<Integer>> rowMap=colVsRowVsMap.getOrDefault(column,new HashMap<>());
        List<Integer> list = rowMap.getOrDefault(row,new ArrayList<>());
        list.add(node.val);
        rowMap.put(row,list);
        colVsRowVsMap.put(column,rowMap);

        veritcalRecursion(node.left,row+1,column-1);
        veritcalRecursion(node.right,row+1,column+1);

    }

    Node rootNode;
    Scanner sc = new Scanner(System.in);
    public void populate(){
        System.out.println(" enter the rootNode Value :: ");
        int value = sc.nextInt();
        rootNode = new Node(value);
        populate(rootNode);
    }

    List<Integer> resultList = new ArrayList<>();
    public List<Integer> postorder(Node root){
       // postOrderRecurssion(root);
       return resultList;
    }


//    public void postOrderRecurssion(Node root ){
//        if(root ==null){
//            return ;
//        }
//        if(root.children !=null){
//            List<Node> childList = root.children;
//            for (Node node : childList){
//                postOrderRecurssion(node);
//            }
//        }
//
//        resultList.add(root.val);
//        return ;
//
//    }

    public void populate(Node node){
        System.out.println(" do you want to enter left Value of Node :: "+node.val);
        boolean left = sc.nextBoolean();
        if(left){
            System.out.println(" enter the left Value of Node :: "+node.val);
            int value = sc.nextInt();
            node.left=new Node(value);
            populate(node.left );
        }

        System.out.println(" do you want to enter right value of node :: "+node.val);
        boolean right = sc.nextBoolean();
        if(right){
            System.out.println(" enter the right Value of Node :: "+node.val);
            int value = sc.nextInt();
            node.right=new Node(value);
            populate(node.right);
        }
    }

    public void display(){
        display(0,rootNode);
    }

    public void display(int level, Node node){
        if(node == null){
            return;
        }
        display(level+1,node.right);
        for(int i=0;i<level;i++){
            System.out.print("|\t\t");
        }
        System.out.println("|------->"+node.val);
        display(level+1,node.left);
    }



    public int countPaths(int sum){
        List<Integer> list = new ArrayList<>();
        return countPathHelper(rootNode,sum,list);
    }

    public int countPathHelper(Node root, int sum,List<Integer> pathList){
        if (root == null) {
            return 0;
        }


        pathList.add(root.val);
        int s =0;
        int count=0;
        ListIterator<Integer> itr = pathList.listIterator(pathList.size());

        while (itr.hasPrevious()){
            s=s+itr.previous();
            if(s==sum){
                count++;
            }
        }

        count=count+countPathHelper(root.left,sum,pathList)+countPathHelper(root.right,sum,pathList);
       // pathList.remove(pathList.size()-1);
        return count;
    }


    String str="";
    String[] stringArray;
    public String serialize(Node root) {
        if(root==null){
            str= str+",N";
            return str;
        }


        str=str+","+root.val;
        serialize(root.left);
        serialize(root.right);

        return str;
    }

    public Node deserialize(String data) {
        String commaRemoved = data.substring(1);
        stringArray = commaRemoved.split(",");
        System.out.println(Arrays.toString(stringArray));
        return helper();
    }


    int maxSum =Integer.MIN_VALUE;
    public int maxPathSum(Node root) {

        if(root.left ==null && root.right ==null){
            return root.val;
        }

        helperMaxPathSum(root);
        return maxSum;
    }

    public  int helperMaxPathSum(Node root){
       if(root==null){
           return 0;
       }

        int leftValue = helperMaxPathSum(root.left);
        int rightValue = helperMaxPathSum(root.right);

        if(leftValue <0){
            leftValue=0;
        }

        if(rightValue<0){
            rightValue=0;
        }


        maxSum= Math.max(maxSum,(leftValue+rightValue+root.val));


        return root.val+(leftValue>rightValue?leftValue:rightValue);
    }

    public boolean hasPathSum(Node root, int targetSum) {
        if(root ==null){
            return false;
        }

        int value = root.val;
        targetSum =targetSum-value;
        if(targetSum==0 && (root.left==null && root.right==null)){
            return true;
        }


        boolean rightBoolean = hasPathSum(root.left,targetSum);
        boolean leftBoolean = hasPathSum(root.right,targetSum);

        return (rightBoolean || leftBoolean);
    }

    int num =0;
    public int sumNumbers(Node root) {
        return num;
    }

    public void helper2(Node root,int initialValue){
        if(root.right==null && root.right ==null){
            initialValue= initialValue*10+(root.val);
            num=num+initialValue;
            return;
        }

        initialValue= initialValue*10+(root.val);
        helper2(root.left,initialValue);
        helper2(root.right,initialValue);
        return;
    }
    public Node helper(){
        if(stringArray.length==0){
            return null;
        }

        String str = stringArray[0];
        if(stringArray.length!=1){
            stringArray =Arrays.copyOfRange(stringArray,1,stringArray.length);
        }
        if(str.equalsIgnoreCase("N")){
            return null;
        }

        Node node = new Node(Integer.parseInt(str));
        node.left=helper();
        node.right=helper();

        return node;
    }

   public Node buildTree(int[] preorder,int[] inorder){
        if(preorder.length==0 || inorder.length==0){
            return null;
        }
        if(inorder.length==1){
            Node newNode = new Node(inorder[0]);
            return newNode;
        }

        int rootValue = preorder[0];
       Node rootNode = new Node(rootValue);
        for (int i =0;i<inorder.length;i++){
            if(inorder[i]==rootValue){
                rootNode.left=buildTree(Arrays.copyOfRange(preorder,1,i+1),Arrays.copyOfRange(inorder,0,i));
                rootNode.right=buildTree(Arrays.copyOfRange(preorder,i+1,preorder.length),Arrays.copyOfRange(inorder,i+1,inorder.length));
                break;
            }
        }

        return rootNode;
   }

    public Node lowestCommonAncestor(Node root, Node p, Node q) {

        if(root==null){
            return null;
        }

        if(root == p || root ==q){
            return root;
        }

        Node leftNode = lowestCommonAncestor(root.left,p,q);
        Node rightNode = lowestCommonAncestor(root.right,p,q);


        if(leftNode!=null && rightNode!=null){
            return root;
        }
        Node finalNode = leftNode==null?rightNode:leftNode;
        return finalNode;

    }
    public List<Double> averageOfLevels() {
        return averageOfLevels(rootNode);
    }

    public List<Double> averageOfLevels(Node root) {
        List<Double> resultList = new ArrayList<>();

        if(root==null){
            return resultList;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int queueSize= queue.size();
            Double value =0.0;
            for(int i =0;i<queueSize;i++){
                Node node = queue.remove();
                value= value+node.val;
                Node leftNode = node.left;
                Node rightNode= node.right;
                if(leftNode!= null){
                    queue.add(leftNode);
                }

                if(rightNode !=null){
                    queue.add(rightNode);
                }
            }

            Double averageValue = value/queueSize;
            resultList.add(averageValue);
        }

        return resultList;
    }

    public int getSuccesor(int value){
        return getSuccesor(rootNode,value);
    }

    public int getSuccesor(Node root,int value){
        if(root ==null){
            return -1;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int queueSize = queue.size();
            for(int i =0;i<queueSize;i++){
                Node node = queue.remove();
                Node leftNode= node.left;
                Node rightNode= node.right;
                if (leftNode !=null){
                    queue.add(leftNode);
                }

                if(rightNode !=null){
                    queue.add(rightNode);
                }

                if(node.val==value && !queue.isEmpty()){
                    return queue.remove().val;
                }
            }
        }

        return -1;

    }

    public List<List<Integer>> zigzagLevelOrder() {
        return zigzagLevelOrder(rootNode);
    }

    public List<List<Integer>> zigzagLevelOrder(Node root) {
        List<List<Integer>> resultList = new ArrayList<>();
        if(root==null){
            return resultList;
        }

        Deque<Node> deque = new ArrayDeque<>();
        deque.addFirst(root);
        boolean reverse =false;

        while (!deque.isEmpty()){
            List<Integer> list = new ArrayList<>();
            int deqSize= deque.size();
            for(int i = 0;i<deqSize;i++){
                if(!reverse){
                    Node node = deque.removeFirst();
                    list.add(node.val);
                    Node left= node.left;
                    Node right = node.right;
                    if(left!=null){
                        deque.addLast(left);
                    }

                    if(right !=null){
                        deque.addLast(right);
                    }
                }
                else {
                    Node node=deque.removeLast();
                    list.add(node.val);
                    Node left = node.left;
                    Node rigt= node.right;
                    if(rigt !=null){
                        deque.addFirst(rigt);
                    }

                    if(left !=null){
                        deque.addFirst(left);
                    }
                }
            }
            reverse=!reverse;
            resultList.add(list);
        }

        return resultList;

    }

    public String findPath(int value){
        String str="";
        return findPath(str,value,rootNode);
    }

    public String getDirections(int start,int end){
        return getDirections(rootNode,start,end);
    }

    public String getDirections(Node root, int startValue, int destValue) {
        String startString ="";
        String endString="";
        startString=findPath(startString,startValue,root);
        endString=findPath(endString,destValue,root);


        int startIndex =0;
        int endIndex=0;
        while (startIndex <startString.length() && endIndex <endString.length()){
            if(startString.charAt(startIndex) == endString.charAt(endIndex)){
                startIndex++;
                endIndex++;
            }else{
                break;
            }
        }



        if(startIndex ==startString.length()){
            return endString.substring(endIndex);
        }else if (endIndex ==endString.length()){
            String finalString ="";
            for(int i = startIndex;i<startString.length();i++){
                finalString=finalString+"U";
            }
            return finalString;

        }else{
            String finalString ="";
            for(int i = startIndex;i<startString.length();i++){
                finalString=finalString+"U";
            }
            for(int j = endIndex;j<endString.length();j++){
                finalString=finalString+endString.charAt(j);
            }
            return finalString;
        }

    }


    public String findPath(String str,int value,Node rootNode){
        if(rootNode==null){
            return null;
        }

        if(rootNode.val==value){
            return str;
        }

        String leftstr=findPath(str+"L",value,rootNode.left);

        if(leftstr ==null){
            return findPath(str+"R",value,rootNode.right);
        }else {
            return leftstr;
        }

    }



    public Node createBinaryTree(int[][] descriptions) {
        Map<Integer,Node> valueVsNode = new HashMap<>();
        Map<Integer,Boolean> isParentMap = new HashMap<>();
        for(int[] nodeArray : descriptions){
            int isLeft = nodeArray[2];
            Node parentNode = valueVsNode.getOrDefault(nodeArray[0],new Node(nodeArray[0]));

            Node childNode = valueVsNode.getOrDefault(nodeArray[1],new Node(nodeArray[1]));
            if(isLeft==1){
                parentNode.left=childNode;
            }else{
                parentNode.right=childNode;
            }

            valueVsNode.put(nodeArray[0],parentNode);
            valueVsNode.put(nodeArray[1],childNode);
            isParentMap.put(nodeArray[1],false);

            if((isParentMap.containsKey(nodeArray[0]) && isParentMap.get(nodeArray[0])) || !isParentMap.containsKey(nodeArray[0])){
                isParentMap.put(nodeArray[0],true);
            }


        }

        for(Map.Entry<Integer,Boolean> entry : isParentMap.entrySet()){
            if(entry.getValue()){
                return valueVsNode.get(entry.getKey());
            }
        }
       return null;
    }


    public List<List<Integer>> levelOrderBottom(Node root) {
        List<List<Integer>> resultList= new ArrayList<>();

        if(root ==null){
            return resultList;
        }


        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while (!nodeQueue.isEmpty()){
            List<Integer> list = new ArrayList<>();
            int size = nodeQueue.size();
            for (int i =0;i<size;i++){
                Node node = nodeQueue.remove();
                Node leftNode = node.left;
                Node rightNode= node.right;
                list.add(node.val);

                if(leftNode!=null){
                    nodeQueue.add(leftNode);
                }

                if(rightNode!=null){
                    nodeQueue.add(rightNode);
                }
            }
            resultList.add(0,list);

        }

        return resultList;

    }

    public List<Integer> rightSideView(Node root) {
        List<Integer> resultList= new ArrayList<>();

        if(root ==null){
            return resultList;
        }


        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while (!nodeQueue.isEmpty()){

            int size = nodeQueue.size();
            for (int i =0;i<size;i++){
                Node node = nodeQueue.remove();
                Node leftNode = node.left;
                Node rightNode= node.right;
                if(i == size-1){
                    resultList.add(node.val);
                }

                if(leftNode!=null){
                    nodeQueue.add(leftNode);
                }

                if(rightNode!=null){
                    nodeQueue.add(rightNode);
                }
            }


        }

        return resultList;
    }



    public  boolean isSibblings(Node root, Node x ,Node y){
        if(root==null){
            return false;
        }

        if((root.left==x && root.right ==y) || (root.left==y && root.right ==x)){
            return true;
        }

        boolean leftSibb= isSibblings(root.left,x,y);
        if(leftSibb){
            return true;
        }else{
            return isSibblings(root.right,x,y);
        }

    }

    public void flatten(Node root) {

        if(root==null){
            return;
        }

        Node currentNode = root;
        while (currentNode!=null){
            Node leftNode= currentNode.left;
            Node rightNode = currentNode.right;
            if (leftNode!=null){
                Node cn=leftNode;
                while (cn.right!=null){
                    cn=cn.right;
                }
                cn.right=rightNode;
                currentNode.right=leftNode;
            }
            currentNode.left=null;
            currentNode=currentNode.right;
        }

    }



    public Node sortedArrayToBST(int[] nums) {
        return helperSortedArray(0,nums.length-1,nums);
    }

    public Node helperSortedArray(int start,int end,int[] nums){
        if(nums.length ==0){
            return null;
        }
        if(nums.length ==1){
            Node node = new Node(nums[0]);
            return node;
        }

        int mid = (start+end/2);
        Node node = new Node(nums[mid]);
        node.left=helperSortedArray(start,mid-1,nums);
        node.right =helperSortedArray(mid+1,end,nums);
        return node;
    }


    public Node invertTree(Node root) {
        if(root==null){
            return null;
        }
         Node leftNode =invertTree(root.left);
        Node rightNode = invertTree(root.right);

        root.left=rightNode;
        root.right=leftNode;
        return root;

    }

    public int maxDepth(Node root) {
       return helpingDepth(root,0);
    }

    public int helpingDepth(Node root , int depth){
        if(root==null){
            return depth;
        }

        int leftDepth = helpingDepth(root,depth+1);
        int rightDepth = helpingDepth(root,depth+1);
        return Math.max(leftDepth,rightDepth);
    }

    public boolean isCousins(Node root, int x, int y) {

        Node xx = findNode(root,x);
        Node YY = findNode(root,y);
        int xlevel = findLevel(root,x,0);
        int ylevel = findLevel(root,y,0);

        System.out.println(xlevel);
        System.out.println(ylevel);
        return (xlevel== ylevel && !isSibblings(root,xx,YY));
    }
    public Node findNode(Node root, int x){
        if(root ==null){
            return null;
        }

        if(root.val==x){
            return root;
        }

        Node leftNode = findNode(root.left,x);
        if(leftNode==null){
            return findNode(root.right,x);
        }else{
            return leftNode;
        }

    }


    public int findLevel(Node node, int x, int level) {
        if (node == null) {
            return -1;
        }

        if (node.val == x) {
            return level;
        }

        int leftLevel =findLevel(node.left,x,level+1);
        if(leftLevel==-1){
            return findLevel(node.right,x,level+1);
        }else {
            return leftLevel;
        }
    }


    List<Node> arrList = new ArrayList<>();
    public List<Node> delNodes(Node root, int[] to_delete) {
        List<Integer> to_deleteList =new ArrayList<>();
        for(int i =0;i<to_delete.length;i++){
            to_deleteList.add(to_delete[i]);
        }
        if(!to_deleteList.contains(root.val)){
            arrList.add(root);
        }
        deleteNodes(root,to_deleteList);
        return arrList;
    }


    public Node  deleteNodes(Node root ,List<Integer> to_del){
        if(root==null){
            return null;
        }

        if (to_del.contains(root.val)){
            Node leftNode = root.left;
            Node rightNode = root.right;
            if(leftNode!=null){
                if(!to_del.contains(leftNode.val)){
                    arrList.add(leftNode);
                }
                deleteNodes(leftNode,to_del);
            }

            if(rightNode!=null){
                if(!to_del.contains(rightNode.val)){
                    arrList.add(rightNode);
                }
                deleteNodes(rightNode,to_del);
            }
            return null;
        }else {
            root.left=deleteNodes(root.left,to_del);
            root.right=deleteNodes(root.right,to_del);
            return root;
        }

    }


    int diameter =0;
    public  int diamtr(){
        calculateBinaryTree(rootNode);
        return diameter+1;
    }
    public int diameterOfBinaryTree(Node root) {
        calculateBinaryTree(root);
        return diameter+1;

    }

    public int calculateBinaryTree(Node root){
        if(root ==null){
            return 0;
        }

        int leftHeight = calculateBinaryTree(root.left);
        int rightHeight = calculateBinaryTree(root.right);
        int height= Math.max(leftHeight,rightHeight)+1;
        diameter= Math.max(diameter,(leftHeight+rightHeight+1));
        return height;
    }

    public boolean isSymmetric(Node root) {
        return checkSymmetric(root,root);
    }

    public boolean checkSymmetric(Node node1,Node node2){

        if(node1 ==null && node2 ==null){
            return true;
        }

        if((node1 !=null) && (node2!=null) && (node1.val == node2.val) && checkSymmetric(node1.left,node2.right)  && checkSymmetric(node1.right,node2.left) ){
            return true;
        }

        return false;
    }



}
