package Interfaces;

import java.util.Optional;

public class main {

    public static void main(String[] args) {
//        InterfaceA impl = new ImplementationClass();
//        impl.displayName();
//        impl.displayAge();
//        impl.displayDesignation();
        Optional<String> name =printName(2);
        name.ifPresent(System.out::println);

    }


    public static Optional<String> printName(int id){
        String name =null;
        return Optional.ofNullable(name);
    }
}
