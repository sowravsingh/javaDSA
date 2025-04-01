package BinaryTrees;

import org.omg.CORBA.MARSHAL;

public class BinarySearchTree {

    public class Node{
        Node node;
        int value;
        Node left;
        Node right;
        int height;

        Node(int value ){
            this.value=value;
            this.height=0;
        }
    }

    Node root;
    void populate(int[] arr){
        //System.out.println(arr[0]);
         root = new Node(arr[0]);
        for (int i =1 ;i<arr.length;i++){
            Node node = populate(root,arr[i]);
        }
    }


    Node populate(Node node,int value){
        if(node==null){
            Node newNode = new Node(value);
            return newNode;
        }

        if(node.value>value){
            node.left=populate(node.left,value);
        }else{
            node.right=populate(node.right,value);
        }

        node.height= Math.max(height(node.left),height(node.right))+1;
        return node;
    }

    int height(Node node){
        if(node ==null){
            return -1;
        }
        else{
            return node.height;
        }
    }
    void print(){
        print(root,0);
    }


    boolean isBalanced(){
        return isBalanced(root);
    }

    public boolean isBalanced(Node node){
        if(node==null){
            return true;
        }

        return (Math.abs(height(node.left)-height(node.right))<=1 && isBalanced(node.left) && isBalanced(node.right));
    }

    void print(Node node,int level) {
        if(node ==null){
            return;
        }
        print(node.right,level+1);
        for(int i =0;i<level;i++){
            System.out.print("|\t\t");
        }
        System.out.println("|------>"+node.value+"("+node.height+")");
        print(node.left,level+1);
    }



    public void printPrOrder(){
        printPreOrder(root);
        System.out.println();
    }

    private void printPreOrder(Node node){

        if(node==null){
            return;
        }
        System.out.print(node.value+"----->");
        printPreOrder(node.left);
        printPreOrder(node.right);
    }


    public void printInOrder(){
        printInOrder(root);
        System.out.println();
    }

    private void printInOrder(Node node){

        if(node==null){
            return;
        }

        printInOrder(node.left);
        System.out.print(node.value+"----->");
        printInOrder(node.right);
    }

    public void postOrder(){
        postOrder(root);
        System.out.println();
    }

    private void postOrder(Node node){
        if(node==null){
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.value+"----->");
    }



}
