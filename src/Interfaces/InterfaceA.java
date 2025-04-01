package Interfaces;

public interface InterfaceA extends FunctionalInterface {

    public void displaySalary();

    default void displayDesignation(){
        System.out.println(" printing desgination in parent classs");
    }
}
