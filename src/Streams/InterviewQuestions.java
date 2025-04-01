package Streams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class InterviewQuestions {



    public static void main(String[] args) {
        List<Employee> list = new ArrayList<>();
        list.add(new Employee(1,24,"MALE","KAKINADA",21,"TEJA","EMPLOYEE",Arrays.asList("342344444444")));
        list.add(new Employee(2,25,"MALE","VIZAG",20,"SOWRAV","EMPLOYEE",Arrays.asList("652344444444")));
        list.add(new Employee(3,21,"FEMALE","KAKINADA",18,"RAJI","DOCTOR",Arrays.asList("442344444444")));
        list.add(new Employee(4,24,"MALE","PITHAPURAM",17,"BOB","EMPLOYEE",Arrays.asList("842344444444")));
        list.add(new Employee(5,20,"FEMALE","KAKINADA",10,"SATYA","EMPLOYEE",Arrays.asList("142344444444")));
        list.add(new Employee(6,21,"FEMALE","CHENNAI",9,"ALICE","HR",Arrays.asList("342344444444")));


        //sorting using comparable
        Collections.sort(list);
        System.out.println(list);


        list.sort(Comparator.comparing(Employee::getName));
        System.out.println(list);

        // to get employee names whose rank is between 11 to 20 ;
        List<String> collect = list.stream().filter(x -> x.getRank() >= 11 && x.getRank() <= 20).map(Employee::getName).collect(Collectors.toList());

        System.out.println(collect);


        //to get sorted employee names who are staying in kakinada
        List<String> collect2 = list.stream().filter(x ->x.getCity().equalsIgnoreCase("KAKINADA")).map(Employee::getName).sorted().collect(Collectors.toList());
        System.out.println(collect2);

        // find all the unique designations ::
        Set<String> collect1 = list.stream().map(Employee::getDesignation).collect(Collectors.toSet());
        System.out.println(collect1);


        //get all the contactNumbers into a single list  sorted in reverse order::
        List<String> collect3 = list.stream().flatMap(x -> x.getContacts().stream()).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println(collect3);


        //get all employee count grouping by departmentname
        Map<String, Long> collect4 = list.stream().collect(Collectors.groupingBy(Employee::getDesignation,Collectors.counting()));
        System.out.println(collect4);



        //fetching max employee count in all designations
        Optional<Map.Entry<String, Long>> max = list.stream().collect(Collectors.groupingBy(Employee::getDesignation, Collectors.counting())).entrySet().stream().max(Map.Entry.comparingByValue());

        System.out.println(max.get());

        //finding average age of male and female gender ::
        Map<String, Double> collect5 = list.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));
        System.out.println(collect5);


        //finding highest rank in each department ::
        Map<String, Optional<Employee>> collect6 = list.stream().collect((Collectors.groupingBy(Employee::getDesignation, Collectors.minBy(Comparator.comparing(Employee::getRank)))));
        System.out.println(collect6);


        // find name of a employee having second rank
        Employee employee = list.stream().sorted(Comparator.comparing(Employee::getRank)).skip(1).findFirst().get();
        System.out.println(employee);

        // get third most elder person
        Employee employee1 = list.stream().sorted(Comparator.comparing(Employee::getAge)).skip(2).findFirst().get();
        System.out.println("third elder person "+employee1);

        //give age wise count of employees
        Map<Integer, Long> collect7 = list.stream().collect(Collectors.groupingBy(Employee::getAge, Collectors.counting()));
        System.out.println(collect7);


        //give age wise list of employee names
        Map<Integer, List<String>> collect8 = list.stream().collect(Collectors.groupingBy(Employee::getAge, Collectors.mapping(Employee::getName, Collectors.toList())));
        System.out.println(collect8);
        //Optional

       // collect8.entrySet().stream().forEach(x -> System.out.println(x.getKey()));

        // get age wise list of employes
        Map<Integer, List<Employee>> collect12 = list.stream().collect(Collectors.groupingBy(Employee::getAge, Collectors.mapping(Function.identity(), Collectors.toList())));

        //give age wise employee object list
        Map<Integer, List<Employee>> collect9 = list.stream().collect(Collectors.groupingBy(Employee::getAge));




        // find first non repeating element in a list
        List<Integer> ll = Arrays.asList(1,2,1,1,3,4,3);
        Map.Entry<Integer, Long> integerLongEntry = ll.stream().collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting())).entrySet().stream().filter(entry -> entry.getValue()==1).findFirst().get();
        System.out.println(integerLongEntry.getKey());


        // find top 3 elements in a list
        List<Integer> ll1 = Arrays.asList(70,45,66 ,24 ,33, 81);
        List<Integer> collect10 = ll1.stream().sorted(Comparator.reverseOrder()).limit(3).collect(Collectors.toList());
        System.out.println(collect10);

        // concatinating two lists and filter even list
        List<Integer> lst1 = Arrays.asList(2,3,4,6,8);
        List<Integer> lst2 = Arrays.asList(1,12,5,7,9);
        List<Integer> collect11 = Stream.concat(lst1.stream(), lst2.stream()).filter(x -> x % 2 == 0).collect(Collectors.toList());
        System.out.println(collect11);

        //System.out.println(limit);

//        list.add(new Employee(2,8000,"sowrav","CEO"));
//        list.add(new Employee(3,6000,"amar","HR"));
//        list.add(new Employee(4,4000,"Bod","EMPLOYEE"));
//        list.add(new Employee(5,7000,"daya","EMPLOYEE"));
       // int sum =0;
//        double sum1 = list.stream().mapToDouble(Employee::getSalary).sum();
//        System.out.println(sum1);
//        Map<String, Double> collect = list.stream().collect(Collectors.groupingBy(Employee::getDesignation, Collectors.summingDouble(Employee::getSalary)));
//        System.out.println(collect);
        //list.stream().

//        List<Employee> collect = list.stream().sorted(Comparator.comparing(x->x.getName())).collect(Collectors.toList());
//
//        for (Employee emp :collect){
//            System.out.println(emp.name);
//        }
//        System.out.println("****************************");
//        List<Employee> collect2 = list.stream().sorted((x,y) -> (y.salary-x.salary)).limit(3).collect(Collectors.toList());
//
//        for (Employee emp :collect2){
//            System.out.println(emp.salary);
//        }
//
//        System.out.println("****************************");
//        List<Employee> collect3 = list.stream().sorted((x,y) -> (y.salary-x.salary)).skip(3).collect(Collectors.toList());
//
//        for (Employee emp :collect3){
//            System.out.println(emp.salary);
//        }
    }


}
