package Interfaces;

public class ImplementationClass  implements InterfaceA, InterfaceB{
    @Override
    public void displayName() {
        System.out.println("prrinting name in child class");
    }

    @Override
    public void displayAge() {
        System.out.println(" printing age in child classs");
    }

    @Override
    public void displaySalary() {
        System.out.println("printing salary in child class");
    }

    public void displayDesignationChild(){
        System.out.println(" printing designation in child class");
    }
}
