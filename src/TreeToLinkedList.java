import java.util.*;

public class TreeToLinkedList {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val){
            this.val=val;
        }

    }

    public  class ListNode{
        int val;
        ListNode prev;
        ListNode next;

        public ListNode(int val){
            this.val=val;
        }
    }


    TreeNode root;
    ListNode head;
    ListNode tail;
    Scanner sc = new Scanner(System.in);

    public void insert(){
        System.out.println("Please enter the value of root");
        int value = sc.nextInt();
        root = new TreeNode(value);
        insert(root);
    }

    public TreeNode insert(TreeNode node){

        System.out.println(" Do You  Want to insert left to "+node.val);
        boolean isLeft = sc.nextBoolean();
        if(isLeft){
            System.out.println("enter the value for left of " +node.val);
            int left = sc.nextInt();
            TreeNode leftNode= new TreeNode(left);
            node.left = leftNode;
            insert(leftNode);
        }

        System.out.println(" Do You  Want to insert right to "+node.val);
        boolean isrRight = sc.nextBoolean();
        if(isrRight){
            System.out.println("enter the value for right of " +node.val);
            int right = sc.nextInt();
            TreeNode rightNode= new TreeNode(right);
            node.right = rightNode;
            insert(rightNode);
        }

        return node;
    }


    public void display(){
        display(root,0);
    }

    public void  display(TreeNode node, int level){
        if(node==null){
            return;
        }

        display(node.right,level+1);
       // String spaces ="";
        for (int i =0;i<level;i++){
            System.out.print("|\t\t");
        }
        System.out.println("|---->"+node.val);
        display(node.left,level+1);
    }


    public  void ConvertTreeToLinkedList(){
        ConvertTreeToLinkedList(root);
    }

    public void ConvertTreeToLinkedList(TreeNode node){
        if (node==null){
            return;
        }

        ConvertTreeToLinkedList(node.left);

        ListNode newNode = new ListNode(node.val);
        if(head ==null){
            head= newNode;
            tail=head;
        }else {
            tail.next=newNode;
            newNode.prev=tail;
            tail= newNode;
        }


        ConvertTreeToLinkedList(node.right);

    }


    public int getOddEVenDifference(){
        return getOddEVenDifference(root);
    }


    public int getOddEVenDifference(TreeNode head){
        int index=1;

        int evenSum =0;
        int oddSum =0;
        Queue<TreeNode> queue  = new LinkedList<>();
        oddSum= oddSum+head.val;
        index++;
        queue.offer(head);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i =0;i<size;i++){
                TreeNode node = queue.poll();
                if (node.left!=null){
                    queue.offer(node.left);
                }
                if (node.right!=null){
                    queue.offer(node.right);
                }

                if (index%2==0){
                    evenSum=evenSum+node.val;
                }else {
                    oddSum=oddSum+node.val;
                }
            }


            index++;
        }

        return Math.abs(oddSum-evenSum);
    }

    public void  displayLinkedList(){
        displayLinkedList(head);
    }

    public void displayLinkedList(ListNode node){

        while (node!=null){
            System.out.print(node.val +"--->");
            node= node.next;
        }
        System.out.println("END");
    }


}
