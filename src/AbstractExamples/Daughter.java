package AbstractExamples;

public class Daughter extends Parent{
    Daughter(int salary) {
        super(salary);
    }

    @Override
    void carrer() {
        System.out.println("I want to become actor");
    }


    public void age(int age){
        System.out.println(" my age is "+age);
    }
}
