package CoreJava;

public class CustomHashMap<K,V> {
    ListNode arr[];
    final int DEFAULT_SIZE=10;
    int size=0;
    public float LOAD_FACTOR=0.3f;

    public CustomHashMap(){
        arr = new ListNode[DEFAULT_SIZE];
    }


    public void put(K key, V value){
        int index=0;
        if (key!=null){
            index= Math.abs(key.hashCode()%arr.length);
        }

        ListNode node =arr[index];

        if (node==null){
            node =new ListNode(key,value);
            arr[index]=node;
            size++;
            if((float) size/ arr.length>= LOAD_FACTOR){
                System.out.println("load factor "+size/arr.length +" so rehashing here");
                reHash();
            }
            return;
        }else {
            while (node.nert!=null){
                if (node.key==key){
                    node.value=value;
                    return;
                }
                node=node.nert;
            }

            if (node.key==key){
                node.value=value;
                return;
            }

            ListNode newNode = new ListNode(key,value);
            node.nert= newNode;
            size++;
            if((float) size/ arr.length>= LOAD_FACTOR){
                System.out.println("load factor "+ (float) size/arr.length +" so rehashing");
                reHash();
            }

            return;
        }

    }

    public V get(K key){
        int index=0;
        if (key!=null){
            index= Math.abs(key.hashCode()%arr.length);
        }

        ListNode node =arr[index];
        while (node!=null){
            if (node.key==key){
                return (V) node.value;
            }
            node=node.nert;
        }

        return null;
    }

    public int size(){
        return size;
    }


    public void reHash(){
        ListNode oldarr[] = arr;
        ListNode newArr[] = new ListNode[arr.length*2];
        arr= newArr;

         size =0;
        for (ListNode node : oldarr){
            while (node!=null){
                put((K) node.key, (V) node.value);
                node=node.nert;
            }
        }


    }

}



class ListNode<K,V>{

    K key;
    V value;
    ListNode nert;

    public ListNode(){

    }

    public ListNode(K key,V value){
        this.key= key;
        this.value=value;
    }



}
//class Entity<K,V>{
//    K key;
//    V value;
//
//    public Entity(K key,V value){
//        this.key=key;
//        this.value=value;
//    }
//}
