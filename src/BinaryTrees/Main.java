package BinaryTrees;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int[] arr ={1,3,5};
//        BinarySearchTree bst = new BinarySearchTree();
//        bst.populate(arr);
//        bst.print();
//        System.out.println(bst.isBalanced());
//        bst.printPrOrder();
//        bst.printInOrder();
//        bst.postOrder();

//        SegmentTrees seg= new SegmentTrees();
//        seg.insert(arr);
//        seg.print();
//        System.out.println(seg.updateIndex(1,5));
        //seg.print();
       // avl.print();
       // System.out.println(avl.height());
        TreeLeetcode lt =new TreeLeetcode();
        lt.populate();
        lt.display();
        lt.replaceValueInTree();
        lt.display();


//
//        BigInteger bg = new BigInteger("1");
//        for(int i =1;i<100;i++){
//            bg=bg.multiply(BigInteger.valueOf(i));
//        }
        //System.out.println(bg);

//        BigDecimal bd = BigDecimal.valueOf(0.04);
//        BigDecimal bd2= new BigDecimal("0.02");
//        System.out.println(bd.subtract(bd2));
//        DecimalFormat df = new DecimalFormat("#.00");
//        System.out.println(df.format(2*4));


    }
}
