package AbstractExamples;

public class Son extends Parent{

    @Override
    void carrer() {
        System.out.println("I want to become doctor");
    }

    int age;

    Son(int salary, int age){
        super(salary);
        this.age=age;
    }

}
