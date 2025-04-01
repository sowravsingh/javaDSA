package BinaryTrees;

import java.util.*;

public class BinaryTree {

    public  class Node{
        int value;
        Node left;
        Node right;

        public Node(int value){
            this.value = value;
        }

    }

    Node rootNode;

    Scanner sc = new Scanner(System.in);
    public void populate(){
        System.out.println(" enter the rootNode Value :: ");
        int value = sc.nextInt();
        rootNode = new Node(value);
        populate(rootNode);
    }

    public void populate(Node node){
        System.out.println(" do you want to enter left Value of Node :: "+node.value);
        boolean left = sc.nextBoolean();
        if(left){
            System.out.println(" enter the left Value of Node :: "+node.value);
            int value = sc.nextInt();
            node.left=new Node(value);
            populate(node.left );
        }

        System.out.println(" do you want to enter right value of node :: "+node.value);
        boolean right = sc.nextBoolean();
        if(right){
            System.out.println(" enter the right Value of Node :: "+node.value);
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
        System.out.println("|------->"+node.value);
        display(level+1,node.left);
    }

    public List<List<Integer>> levelOrder() {
        return levelOrder(rootNode);
    }
    public List<List<Integer>> levelOrder(Node root) {

        List<List<Integer>> arrList = new ArrayList<>();
        if(root==null){
            return arrList;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()){
            int levelSize = queue.size();
            List<Integer> list = new ArrayList<>();
            for(int i =0;i<levelSize;i++){
                Node node = queue.remove();
                list.add(node.value);
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
}

