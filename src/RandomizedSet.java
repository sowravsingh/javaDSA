import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomizedSet {
    Set<Integer> rs = new HashSet<>();
    public RandomizedSet() {

    }

    public boolean insert(int val) {
        if(rs.contains(val)){
            return false;
        }else{
            rs.add(val);
            return true;
        }
    }

    public boolean remove(int val) {
        if(rs.contains(val)){
            rs.remove(val);
            return true;
        }else{
            return false;
        }
    }

    public int getRandom() {
        Integer[] array = rs.toArray(new Integer[0]);

        // Use the Random class to generate a random index
        Random random = new Random();
        int randomIndex = random.nextInt(array.length);

        // Return the element at the random index
        return array[randomIndex];
    }
}
