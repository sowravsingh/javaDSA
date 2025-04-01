package singleton;

public class Singleton {
    private int age = 20;

    private Singleton(){

    }

    private static  Singleton singletonInstance =null;

    public static Singleton getSingletonInstance(){
        if (singletonInstance==null){
            singletonInstance= new Singleton();
        }

        return singletonInstance;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
