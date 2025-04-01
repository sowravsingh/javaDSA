package AbstractExamples;

public class Main {


    public static void main(String[] args) {

        int salary =35000;
        Son son = new Son(salary,24);
        son.carrer();
        System.out.println("son age is "+son.age);
        System.out.println(son.salary);

        Daughter daughter = new Daughter(salary);
        daughter.carrer();
        daughter.age(23);
    }
}
