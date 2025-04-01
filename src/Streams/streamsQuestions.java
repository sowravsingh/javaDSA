package Streams;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class streamsQuestions {
    public static void main(String[] args) {
        List<Integer> numbers= Arrays.asList(1,2,3,4,56,64,23,45);

        //filter

        List<Integer> result= numbers.stream().filter(n->n>5).collect(Collectors.toList());
        System.out.println(result);

        //map

        List<String> words=Arrays.asList("vamsi","sowrav","nithin","jagadish");
        List<String> result2= words.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(result2);

        //flatmap
        List<List<Integer>> ll=Arrays.asList(Arrays.asList(1,2,3,45),Arrays.asList(23,45,67));
        result=ll.stream().flatMap(Collection::stream).collect(Collectors.toList());

        System.out.println(result);

        //distinct
        result=result.stream().distinct().collect(Collectors.toList());
        System.out.println(result);

        //sorted
        result=result.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println(result);

        System.out.println(result.stream().reduce(0,Integer::sum));
        System.out.println(result.stream().reduce(Integer.MIN_VALUE,Integer::max));

        System.out.println(result.stream().skip(2).limit(1).findFirst().get());

        Student s1=new Student("vamsi",24);
        Student s2=new Student("sowrav",30);
        Student s3=new Student("Bhargav", 30);
        Student s4=new Student("Nithin" ,24);

        List<Student> students=Arrays.asList(s1,s2,s3,s4);
        List<Student> resultStudent= students.stream().sorted(Comparator.comparing(Student::getAge)).collect(Collectors.toList());
        System.out.println(resultStudent);

        resultStudent=students.stream().sorted(Comparator.comparing(Student::getName).thenComparing(Student::getAge)).collect(Collectors.toList());
        System.out.println(resultStudent);

        System.out.println(resultStudent.stream().limit(1).collect(Collectors.toList()));

        System.out.println(resultStudent.stream().findFirst().get());


        System.out.println(result.stream().allMatch(n->n>0));

        System.out.println(students.stream().filter(student -> student.getName().startsWith("v")).findFirst());


        Map<Integer,List<Student>>  mapresult= students.stream().collect(Collectors.groupingBy(Student::getAge));
        System.out.println(mapresult);

        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& ");
        String name = "sowrav singh";
        Map<Character, Long> collect = name.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(collect);

        HashSet<Student> studentsSet = new HashSet<>();
        Student student = new Student("sowrav",23);
        Student student1 = new Student("sowrav",23);
        studentsSet.add(student1);
        studentsSet.add(student);
        studentsSet.add(student1);
        System.out.println(" set size is "+studentsSet.size());


//
//        // generating supplier function
//        Supplier<List<Student>> getStudentList = () ->{
//            Student student1 = new Student(23,"teja");
//            Student student2 = new Student(34,"sowrav");
//            return Arrays.asList(student1,student2);
//        } ;
//
//
//        // generating consumer function
//        Consumer<Student> printStudents= (Student student) ->{
//            System.out.println(student.getName());
//        };
//
//        // generating predicates function
//        Predicate<Student> studentPredicate = (Student student) ->{
//            return student.getId()>10;
//        };
//
//
//        // generating  function function
//
//        Function<Student,Student> UpperCaseFunction= (Student student) ->{
//
//            student.setName(student.getName().toUpperCase());
//            return student;
//        };
//
//
//        // using all those functions
//
//        List<Student> studentList= getStudentList.get();
//
//        studentList.stream().filter(studentPredicate).map(UpperCaseFunction).forEach(printStudents);
//
//        System.out.println("**************");
//        // without using any functions
//
//        Student student1 = new Student(23,"satya");
//        Student student2 = new Student(34,"kumari");
//        List<Student> studentList1 = Arrays.asList(student1,student2);
//
//        studentList1.stream().filter(x ->x.getId()>10).map(x ->x.getName().toUpperCase()).forEach(System.out::println);





    }
}


class Student{

    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Age: " + this.age + " Name: " + this.name;
    }
}
