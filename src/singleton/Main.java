package singleton;

public class Main {
    public static void main(String[] args) {
        Singleton instance = Singleton.getSingletonInstance();
        System.out.println(instance.getAge());

        instance.setAge(45);

        System.out.println(instance.getAge());

        Singleton instance2 = Singleton.getSingletonInstance();
        System.out.println(instance2.getAge());
    }
}
