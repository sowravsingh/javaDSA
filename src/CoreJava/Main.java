package CoreJava;

public class Main {
    public static void main(String[] args) {
        CustomHashMap<String,Integer> cm = new CustomHashMap<>();
        cm.put("sowrav",23);
        cm.put("teja",24);
        cm.put(null,45);
        cm.put("sowrav",74);
        cm.put("raji",24);
        cm.put("teja",83);
        System.out.println(cm.size());
    }
}
