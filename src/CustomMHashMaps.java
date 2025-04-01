import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CustomMHashMaps <K,V>  {
    ArrayList<LinkedList<Entity>> list;
    int size =0;
    float loadFactor =0.5f;

    CustomMHashMaps(){
        list =new ArrayList<>();
        for (int i =0;i<10;i++){
            LinkedList<Entity> linkedList = new LinkedList<>();
            list.add(i, linkedList);
        }
    }

    public void put(K key,V value){
        int hash = Math.abs(key.hashCode()%list.size());
      //  System.out.println(hash);
        LinkedList<Entity> entityLinkedList = list.get(hash);
        for (Entity entity: entityLinkedList){
            if(entity.key.equals(key)){
                entity.value = value;
                return;
            }
        }

        if((float)size/list.size() >loadFactor){
            reHash();
        }
        entityLinkedList.add(new Entity(key,value));
      //  list.set(hash,entityLinkedList);
        size++;

    }

    public void reHash(){
        ArrayList<LinkedList<Entity>> oldList= list;
        for(int i =0;i<(2* list.size());i++){
            list.add(new LinkedList<>());
        }

        size=0;
        for (LinkedList<Entity> entityLinkedList:oldList){
            for(Entity entity: entityLinkedList){
                put(entity.key,entity.value);
            }
        }
    }


    public V get(K key){
        int hash = Math.abs(key.hashCode()%list.size());
        LinkedList<Entity> entityLinkedList = list.get(hash);
        for (Entity entity: entityLinkedList){
            if(entity.key.equals(key)){
                return entity.value;
            }
        }

        return null;
    }

    public int size(){
        return size;
    }


    public void printMap(){
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        for(LinkedList<Entity> EntityList : list){
            for(Entity entity: EntityList ){
                sb.append(entity.key);
                sb.append("=");
                sb.append(entity.value);
                sb.append(",");
            }
        }
        sb.append("}");
        System.out.println(sb);
    }

    public  void remove(K key){
        int hash = Math.abs(key.hashCode()%list.size());
        LinkedList<Entity> entityLinkedList = list.get(hash);
        Entity target = null;
        for (Entity entity: entityLinkedList){
            if(entity.key.equals(key)){
                target=entity;
                size--;
            }
        }
        entityLinkedList.remove(target);
        list.set(hash,entityLinkedList);
    }



    public class Entity{
        K key;
        V value;
        public Entity(K key,V value){
            this.key=key;
            this.value =value;
        }
    }
}
