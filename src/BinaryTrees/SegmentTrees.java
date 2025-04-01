package BinaryTrees;

public class SegmentTrees {

    public class Node{
        int startIndex;
        int endIndex;
        int value;

        Node left;
        Node right;

        Node(){
        }

        Node(int startIndex,int endIndex){
            this.startIndex =startIndex;
            this.endIndex=endIndex;
        }
    }


    Node root= new Node();
    public void insert(int[] arr){
        root = insert(root,0,arr.length-1,arr);
    }

    private Node insert(Node node,int startIndex,int endIndex,int[] arr){
        if(startIndex==endIndex){
            Node newNode = new Node(startIndex,endIndex);
            newNode.value=arr[startIndex];
            return newNode;
        }

        node.startIndex =startIndex;
        node.endIndex =endIndex;
        int mid = (startIndex+endIndex)/2;


        node.left= insert(new Node(),startIndex,mid,arr);
        node.right = insert(new Node(),mid+1,endIndex,arr);

        node.value = node.left.value+node.right.value;
        return node;
    }

    public int getSumOfRange(int s,int e){
        return getSumOfRange(s, e,root);
    }


    public void print(){
        print(root,0);
    }
    private void print(Node node, int level) {
        if(node ==null){
            return;
        }
        print(node.right,level+1);
        for(int i =0;i<level;i++){
            System.out.print("|\t\t");
        }
        System.out.println("|------>"+node.value+"("+node.startIndex+","+node.endIndex+" )");
        print(node.left,level+1);
    }

    private int getSumOfRange(int s, int e, Node node){
        if(node.startIndex>=s && node.endIndex<=e){
            return node.value;
        }else if (node.startIndex>e || node.endIndex<s){
            return 0;
        }else{
            int leftValue = getSumOfRange(s,e,node.left);
            int rightValue = getSumOfRange(s,e,node.right);
            return (leftValue+rightValue);
        }

    }


    public int updateIndex(int index, int value){
        root = updateIndex(root,index,value);
        return root.value;
    }

    private Node updateIndex(Node node, int index,int value){
        if(node.startIndex== index  && node.endIndex == index){
            node.value =value;
            return node;
        } else if (node.startIndex>index || node.endIndex<index) {
            return node;
        }else{
            node.left= updateIndex(node.left,index,value);
            node.right = updateIndex(node.right,index,value);
            node.value = node.left.value+node.right.value;
            return node;
        }
    }
}
