package Streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class streams {

    public static void main(String[] args) {

//
//        List<Integer> lst1 = Arrays.asList(1,2,3);
//        List<Integer> lst2 = Arrays.asList(4,5,6);
//        List<Integer> lst3 = Arrays.asList(7,8,9);
//        List<List<Integer>> mainList = Arrays.asList(lst1,lst2,lst3);
//         mainList.stream().flatMap(lst -> lst.stream()).collect(Collectors.toList());
//        List<Integer> collect =mainList.stream().flatMap(lst -> lst.stream()).map(n -> n*10).collect(Collectors.toList());
//        System.out.println(collect);


        List<Integer> lst = Arrays.asList(1,2,1,3,4,4,5,6,4);
       lst = lst.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println(lst);
      //  System.out.println(lst.stream().reduce((x,y) -> x+y).get());


//        List<Integer> list = new ArrayList<>();
//        list.add(12);
//        list.add(113);
//        list.add(34);
//        list.add(17);
//        int sum =0;
//        List<Integer> collect = list.stream().filter(i -> i % 2 == 0).map(i -> i - 3).collect(Collectors.toList());
//        List<String> names = Arrays.asList("sowrav","teja","ganesh");
//        names.stream().map(n-> n.toUpperCase()).forEach(n-> System.out.println(n));
//        names.stream().map(n-> n.length()).forEach(n-> System.out.println(n));
//        List<Integer> filteredList = list.stream().filter(i -> i==113).collect(Collectors.toList());
//        System.out.println(filteredList);
//        List<Integer> minussList = list.stream().map(i-> i -3).collect(Collectors.toList());
//        System.out.println(minussList);

//        List<Integer> filteredList = list.stream().filter(i->i%2==0).collect(Collectors.toList());
//        System.out.println(filteredList);
//        List<Integer> forEachList = new ArrayList<>();
//        list.stream().forEach(i->
//                System.out.println(i));
//
//        System.out.println("**********************************");
//        int[] arr = new int[]{1,5,3,8,3};
        //Arrays.stream(arr).filter(i-> i%2==0).collect(Collectors.toList());
       // Stream.of(list).filter(i->i %2==0).collect(Collectors.toList());

      //  System.out.println(list);

    }
}
