public class SingleLinkedList {
    int size =0;
    Node head;

    Node tail;

    static class Node{
        int value;
        Node next;
        boolean visited;

        Node(int value){
            this.value=value;
            this.next=null;
            this.visited =false;
        }

    }

    public void insertNodes(int value){
        Node newNode = new Node(value);

        if(head ==null){
            head =newNode;
            head.next = null;
            tail =newNode;
        }
        else{
            Node currentNode = head;
            while(currentNode.next !=null){
                currentNode= currentNode.next;
            }
            currentNode.next=newNode;
            tail =newNode;
        }
        size =size+1;
    }

    public void displayNodes(){
        Node tmp = head;
        while(tmp !=null){
            System.out.print(tmp.value +"---> ");
            tmp =tmp.next;
        }
        System.out.println("END");
    }


    public void insertFirst(int val){
        Node newNode = new Node(val);

        if(head ==null){
            head=newNode;
            tail=newNode;
        }else{
            Node currentNode =head;
            head = newNode;
            newNode.next=currentNode;
        }
    }

    public void insertAtIndex(int index, int value){
        Node newNode = new Node(value);
        Node currentNode =head;
        if(head ==null || index ==0){
            insertFirst(value);
        }else {
            for(int i=0;i<(index-1);i++){
                currentNode=currentNode.next;
            }
            Node tmpNode = currentNode.next;
            currentNode.next= newNode;
            newNode.next=tmpNode;
        }

    }

    public void reverseList(){
        Node prev = null;
        Node current = head;
        Node next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }


    public void removeLast(){
        Node currentNode = head;
        while (currentNode!=null){
            if(currentNode.next.next ==null){
                currentNode.next=null;
            }
            currentNode=currentNode.next;
        }
    }

    public void removeFirst(){
        Node headNode =head.next;
        head =headNode;
    }

    public void removeAtIndex(int index){
        Node currentNode =head;
        if(index==0){
            removeFirst();
        }else{
            for(int i=0;i<(index-1);i++){
                currentNode=currentNode.next;
            }
            Node nextNode =currentNode.next.next;
            currentNode.next=nextNode;
        }
    }

    public void rotateNodes(int n ){
        for(int i=0;i<n;i++){
            Node headNode =head;
            Node tailNode = tail;
            Node nextNode = head.next;

            tailNode.next=headNode;
            headNode.next=null;
            tail =headNode;
            head = nextNode;
         }
    }

    public void displayNthNode(int n){
        Node temp =head;
        System.out.println(size);
        for (int i=0;i<(size-n);i++){
            temp =temp.next;
        }

        System.out.println(temp.value);
    }


    public void removeLastOccurence(int num){
        Node dupNode =null;
        Node tempNode =head;
        while(tempNode.next !=null){
            if(tempNode.next.value == num)
                dupNode =tempNode;
            tempNode=tempNode.next;
        }
        if(dupNode !=null){
            dupNode.next=dupNode.next.next;
        }


    }


    public static int findInterserctionNode(Node head1, Node head2){
       Node curNode = head1;

       while(curNode.next !=null){
           curNode.visited =true;
           curNode=curNode.next;
       }

       Node secNode =head2;

       while(secNode.next !=null){
           if(secNode.visited)
               return secNode.value;
           secNode=secNode.next;
       }


        return -1;
    }

    public static Node rotateNodes(Node head, int k){
        Node headNode =null;
        int len =0;
        Node tempNode =head;
        while(tempNode !=null){
            len++;
            tempNode=tempNode.next;
        }

        if(len ==1){
            return head;
        }

        Node dummy =null;
        Node prevFirstNode =null;
        Node prevNode =null;
        Node nextNode = null;
        Node currentNode = head;
        while(len>=k){
            for(int i=0;i<k;i++){
                if(currentNode !=null){
                    if(i ==0){
                        prevFirstNode= currentNode;
                    }
                    if(i ==(k-1) && dummy !=null){
                        dummy.next=currentNode;
                    }
                    nextNode=currentNode.next;
                    currentNode.next=prevNode;
                    prevNode=currentNode;
                    currentNode=nextNode;
                }
            }
            if(prevFirstNode==head){
                headNode=prevNode;
            }
            dummy=prevFirstNode;
            prevNode=null;
            len =len-k;
        }
        if(currentNode !=null){
            prevFirstNode.next=currentNode;
        }

        return headNode;
    }



    public static void displayNodes(Node head1){
        Node tmp = head1;
        while(tmp !=null){
            System.out.print(tmp.value +"---> ");
            tmp =tmp.next;
        }
        System.out.println("END");
    }


    public static void main(String args[]){
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        Node head = rotateNodes(head1,2);
//        System.out.println(head.value);
//        displayNodes(head);
        // list 2
//        Node head2 = new Node(10);
//        head2.next = new Node(9);
//        head2.next.next = new Node(8);
//        head2.next.next.next = head1.next.next.next;
//        System.out.println(" nodes are intersected at :: "+findInterserctionNode(head1,head2));
    }



}
