package Streams;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class interviewQuestions2 {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(5,8,2,4,22,11);

        //get the min element
        int min = list.stream().min((x,y) -> x.compareTo(y)).get();
        System.out.println(min);


        //get the max element
        int max =list.stream().max(Integer::compareTo).get();
        System.out.println(max);

        //sort in ascending order
        List<Integer> sortedList = list.stream().sorted().collect(Collectors.toList());
        System.out.println(sortedList);

        //sort in descending order
        List<Integer> descendingList = list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println(descendingList);

        // tell me it has duplicate elements or not
        int[] arr ={1,3,4,1,2};
        if(Arrays.stream(arr).distinct().count()!=arr.length){
            System.out.println("contains duplicate ");
        }else {
            System.out.println(" no duplicate");
        }

        // print  elements list whose square value is greater than 50
        list.stream().map(x ->x*x).filter(x ->x>50).forEach(System.out::println);

        List<String> stringList = Arrays.asList("teja","SOWRAV","sATYA","queen");

        //convert all elements into upper case
        List<String> upperCaseList = stringList.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(upperCaseList);

        //concat 2 streams and sort them
        Stream<Integer> s1 =Stream.of(1,5,6);
        Stream<Integer> s2 = Stream.of(2,3,4);
        List<Integer> concatnatedList = Stream.concat(s1, s2).sorted().collect(Collectors.toList());
        System.out.println(concatnatedList);

        // generate 10 random numbers
        Random random = new Random();
       // Stream.generate(random::nextInt).limit(10).forEach(System.out::println);


        //print current date time
        LocalDateTime ld = LocalDateTime.now();
        System.out.println(ld);

      // give me count of each value how many times repeated
        int[] arr1 ={1,3,3,4,1,2,1};
        Map<Integer, Long> collect = Arrays.stream(arr1).boxed().collect(Collectors.groupingBy(x -> x, Collectors.counting()));
        System.out.println(collect);

        //give elements map which have occured more than once
        List<String> stringList1 = Arrays.asList("teja","teja","sai","queen","sai");
        Map<String, Long> collect1 = stringList1.stream().collect(Collectors.groupingBy(x -> x, Collectors.counting())).entrySet().stream().filter(entry ->entry.getValue()>1).collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
        System.out.println(collect1);

        //print count of each charecter in a string ::
        String s ="satya";
        Map<Character, Long> collect2 = s.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        System.out.println(collect2);

        // find the first non repeatng character
        Character key = s.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream().filter(x -> x.getValue() == 1).findFirst().get().getKey();
        System.out.println(key);
    }
}
