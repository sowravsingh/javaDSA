package Interfaces;

@java.lang.FunctionalInterface
public interface FunctionalInterface {

    public void displayName();

    default void displayAge(){
        System.out.println("my age is 24");
    }
}
