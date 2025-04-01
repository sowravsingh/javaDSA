package CustomArrays;

import java.util.*;

public class Main {

    public static void main(String[] args) {
//        CustomArrayList arrayList = new CustomArrayList();
//        //CustomArrayList arrayList1= new CustomArrayList();
//        arrayList.add(1);
//        arrayList.add(2);
//        arrayList.add(3);
//        arrayList.add(4);
//        arrayList.add(5);
//        arrayList.add(6);
//        System.out.println(arrayList.get(0));
//        System.out.println("size is "+arrayList.size());
//        System.out.println(arrayList);
//        arrayList.remove(3);
//        System.out.println(arrayList);
//        System.out.println(arrayList.size());
        //arrayList1.add(22);
       // System.out.println(arrayList);
        //System.out.println(arrayList1);
//        CustomStack stack = new CustomStack();
//        stack.push(23);
//        stack.push(33);
//        stack.push(44);
//       System.out.println(stack.pop());
//        System.out.println(stack);
//        Queue<Integer> queue = new LinkedList<>();
//        queue.add(12);
//        queue.add(45);
//        queue.add(31);
//        queue.remove();
//        System.out.println(queue);
//        Deque<Integer> deque = new ArrayDeque<>();
//        deque.addFirst(12);
//        deque.add(13);
//        deque.addFirst(33);
//        deque.addLast(45);
//        System.out.println(deque);


        CustomQueue queue = new CustomQueue();
        queue.insert(23);
        queue.insert(99);
        queue.insert(45);
        queue.display();
        queue.remove();
        queue.insert(101);
        queue.display();

    }
}
