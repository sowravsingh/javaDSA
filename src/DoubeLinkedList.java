import javax.xml.soap.Node;

public class DoubeLinkedList {
    Node head;
    Node tail;
    class Node{
        int value;
        Node next;
        Node prev;
        Node (int value){
            this.value =value;
            this.next=null;
            this.prev=null;
        }

        Node (){
            this.next=null;
            this.prev=null;
        }

    }

    public void insertNodes(int value){
        Node currentNode = head;
        Node newNode = new Node(value);
        if (head ==null){
            head=newNode;
            tail=newNode;
        }
        else {
            while (currentNode.next !=null){
                currentNode=currentNode.next;
            }
            currentNode.next=newNode;
            newNode.prev=currentNode;
            tail=newNode;
        }

    }

    public void displayNodes(){
        Node currentNode =head;
        while (currentNode!=null){
            System.out.print(currentNode.value+" --> ");
            currentNode=currentNode.next;
        }
        System.out.println("END");
    }

    public void insertFirst(int value){
        Node newNode = new Node(value);
        Node currentNode =head;
        if(head ==null){
            head=newNode;
            tail=newNode;
        }
        else{
           currentNode.prev=newNode;
           newNode.next=currentNode;
           head=newNode;
        }
    }



    public void insertAtIndex(int index,int value){
        Node newNode = new Node(value);
        Node currentNode =head;
        if(index ==0){
            insertFirst(value);
        }else{
            for(int i=0;i<(index-1);i++){
                 currentNode=currentNode.next;
            }
            Node tmpNode =currentNode.next;
            currentNode.next=newNode;
            newNode.prev=currentNode;
            newNode.next=tmpNode;
            //System.out.println("entered here");
            tmpNode.prev=newNode;
        }
    }

    public void reverseLinkedList(){
        Node current =head;
        Node next =null;
        tail=current;
        Node prev =null;
        while(current.next !=null){
            next =current.next;
            prev =current.prev;
            current.prev =next;
            current.next=prev;
            current=next;
        }

        head =current;
        head.next=current.prev;

    }


    public void removeFirst(){
        Node tmpNode =head.next;
        tmpNode.prev =null;
        head =tmpNode;
    }

    public void removeLast(){
       Node prevNode = tail.prev;
       prevNode.next=null;
       tail=prevNode;
    }

    public void removeAtIndex(int index){
        Node currentNode =head;
        if(index ==0 ){
            removeFirst();
        }else{
            for(int i=0;i<(index-1);i++){
                currentNode=currentNode.next;
            }
            if (tail ==currentNode.next){
                removeLast();
            }else{
                Node NextNode = currentNode.next.next;
                currentNode.next=NextNode;
                NextNode.prev=currentNode;
            }

        }
    }




}
