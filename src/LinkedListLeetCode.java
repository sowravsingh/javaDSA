import java.util.List;

public class LinkedListLeetCode {


    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
    public static void main(String[] args) {

    }


    public ListNode mergeNodes(ListNode head) {

       ListNode newHeadNode=null;
       ListNode node= null;

       ListNode currentNode = head.next;
       int sum=0;
       while (currentNode !=null){
         if(currentNode.val!=0){
           sum=sum+currentNode.val;
         }else{
           ListNode newNode = new ListNode(sum);
           if(newHeadNode ==null){
             newHeadNode= newNode;
             node= newNode;
           }else{
             node.next= newNode;
             node=newNode;
           }
           sum=0;
         }

         currentNode =currentNode.next;
       }

        return newHeadNode;
    }


    public int[] nodesBetweenCriticalPoints(ListNode head) {

        int[] arr ={-1,-1};
        ListNode currentNode = head.next;
        int previousValue= head.val;
        int count =1;
        int firstCritcialPoint =0;
        int latestCriticalPoint =0;
        while(currentNode.next !=null){
            int currentValue = currentNode.val;
            int nextValue = currentNode.next.val;
            count++;
            if((currentValue >previousValue && currentValue >nextValue) || (currentValue<previousValue && currentValue<nextValue)){
                //System.out.println(" found critical point at count "+count);
                if(firstCritcialPoint ==0 && latestCriticalPoint ==0){
                    firstCritcialPoint =count;
                    latestCriticalPoint=count;
                }else{
                    if(arr[0] ==-1){
                        arr[0]=Math.abs(latestCriticalPoint-count);
                    }else{
                        arr[0]=Math.min(Math.abs(latestCriticalPoint-count),arr[0]);
                    }
                    arr[1] = Math.max(Math.abs(firstCritcialPoint-count),arr[1]);
                    latestCriticalPoint =count;
                }

            }


            previousValue=currentValue;
            currentNode=currentNode.next;
        }

        return arr;
        }


}
