package BinaryTrees;

public class AVLTree {

    public class Node {
        private int value;
        private Node left;
        private Node right;
        private int height;

        public Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    private Node root;

    public AVLTree() {

    }

    public int height() {
        return height(root);
    }
    private int height(Node node) {
        if (node == null) {
            return -1;
        }
        return node.height;
    }

    public void insert(int value) {
        root = insert(root,value);
    }

    private Node insert(Node node ,int value) {
        if(node == null){
            node =new Node(value);
            return node;
        }

        if(node.value>value){
            node.left = insert(node.left,value);
        }else{
            node.right= insert(node.right,value);
        }
        node.height = Math.max(height(node.right),height(node.left))+1;
        return rotate(node);
    }

    private Node rotate(Node node) {
        if(height(node.left)-height(node.right)>1){

            if(height(node.left.left) - height(node.left.right) < 0) {
                // left right case
                node.left = leftRotate(node.left);
            }
            return rightRotate(node);
        }

        if(height(node.right)-height(node.left)>1){

            if(height(node.right.right)-height(node.right.left) < 0) {
                // right left case
                node.right = rightRotate(node.right);
            }
            return leftRotate(node);
        }
        return node;
    }

    public Node rightRotate(Node node) {
        Node c  = node.left;
        Node  t2 = c.right;

        c.right= node;
        node.left = t2;

        c.height = Math.max(height(c.right),height(c.left))+1;
        node.height = Math.max(height(node.right),height(node.left))+1;
        return c;
    }

    public Node leftRotate(Node node) {
        Node c = node.right;
        Node t2 = c.left;

        c.left = node;
        node.right = t2;

        c.height = Math.max(height(c.left), height(c.right) + 1);
        node.height = Math.max(height(node.left), height(node.right) + 1);

        return c;
    }

    public void populate(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            this.insert(nums[i]);
        }
    }

    public void populatedSorted(int[] nums) {
        populatedSorted(nums, 0, nums.length);
    }

    private void populatedSorted(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }

        int mid = (start + end) / 2;

        this.insert(nums[mid]);
        populatedSorted(nums, start, mid);
        populatedSorted(nums, mid + 1, end);
    }

    public void display() {
        display(this.root, "Root Node: ");
    }

    private void display(Node node, String details) {
        if (node == null) {
            return;
        }
        System.out.println(details + node.value);
        display(node.left, "Left child of " + node.value + " : ");
        display(node.right, "Right child of " + node.value + " : ");
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean balanced() {
        return balanced(root);
    }

    private boolean balanced(Node node) {
        if (node == null) {
            return true;
        }
        return Math.abs(height(node.left) - height(node.right)) <= 1 && balanced(node.left) && balanced(node.right);
    }

}
