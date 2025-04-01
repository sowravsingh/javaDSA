import javax.xml.soap.Node;
import java.util.ArrayList;
import java.util.List;

public class CircularLinkedList {
    Node head;

    int size ;

    public static class Node{
        int value;
        Node next ;
        Node(){
            this.value = 0;
            this.next = null;
        }
        Node(int n){
            this.value=n;
        }
    }

    public void insertNode(int n){
        Node newNode = new Node(n);
        Node currentNode = head;
        if(head ==null){
            head=newNode;
            newNode.next=newNode;
        }else{
            while(currentNode.next != head){
                currentNode=currentNode.next;
            }
            currentNode.next =newNode;
            newNode.next=head;
        }

    }

    public  void displayNodes(){
        Node currentNode = head;
        while(currentNode.next !=head){
            System.out.print(currentNode.value +" -> ");
            currentNode=currentNode.next;
        }
        System.out.println(currentNode.value+ " -> head");
    }

    public List<Node> halfCircularlist(){
        List<Node> ll = new ArrayList<>();
        Node head1 = new Node();
        Node head2 = new Node();

        int size =1;
        Node currentNode =head;
        while(currentNode.next != head){
            size =size+1;
            currentNode=currentNode.next;
        }

        int half = (size%2 ==0 )? (size/2):(size/2+1);

        currentNode =head;
        head1 =head;
        int count =1;
        while(currentNode.next != head){
            if(count == half){
                head2 =currentNode.next;
                currentNode.next =head;
                currentNode=head2;
                count =count+1;
                continue;
            }
            currentNode=currentNode.next;
            count =count+1;
        }
        currentNode.next=head2;

        ll.add(head1);
        ll.add(head2);
//        System.out.println(head1.value);
//        System.out.println(head2.value);
        return ll;
    }

    public  void displayNodes(Node head){
        Node currentNode = head;
        while(currentNode.next !=head){
            System.out.print(currentNode.value +" -> ");
            currentNode=currentNode.next;
        }
        System.out.println(currentNode.value+ " -> head");
    }

//    public static void main(String args[]){
//        Node head = null;
//        head= insertNode(head,5);
//        head =insertNode(head,9);
//        //insertNode(head,2);
//        //insertNode(head,5);
//       displayNodes(head);
//
//
//    }


}
