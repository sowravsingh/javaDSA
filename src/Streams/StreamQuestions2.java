package Streams;


import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamQuestions2 {
    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(33,51,78,5,45,33);
        List<Integer> collect = list.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());
        System.out.println(collect);


        int maxNumber = list.stream().max(Integer::compare).get();
        System.out.println(maxNumber);


        System.out.println(list.stream().max((x,y) -> x.compareTo(y)).get());
        System.out.println(list.stream().min((x,y) -> x.compareTo(y)).get());

        int sumValue = list.stream().mapToInt(x ->x).sum();

        System.out.println(sumValue);

        List<String> namesList = Arrays.asList("sowrav singh","jakka teja");

        List<String> collect1 = namesList.stream().map(String::toUpperCase).collect(Collectors.toList());

        System.out.println(collect1);

        List<Integer> sorted = list.stream().sorted().collect(Collectors.toList());
        System.out.println(sorted);


        long count =list.stream().filter(n -> n>5).count();
        System.out.println(count);

        List<Integer> collect2 = list.stream().distinct().collect(Collectors.toList());
        System.out.println(collect2);

        Integer sum = list.stream().reduce((x, y) -> x + y).get();
        System.out.println(sum);

        System.out.println(list.stream().findAny().get());

        List<String> collect3 = namesList.stream().map(x -> x.split(" ")[0]).collect(Collectors.toList());
        System.out.println(collect3);

        System.out.println(list.stream().allMatch(n -> n >= 0));

        System.out.println(list.stream().noneMatch(n ->n<0));

        System.out.println(list.stream().findFirst().get());

        List<List<Integer>> nestedList = Arrays.asList(Arrays.asList(2,3,4),Arrays.asList(5,6,7));
        List<Integer> collect4 = nestedList.stream().flatMap(x -> x.stream()).collect(Collectors.toList());
        System.out.println(collect4);

        List<Integer> limit = collect4.stream().limit(3).collect(Collectors.toList());
        System.out.println(limit);

        List<Integer> skip = collect4.stream().skip(2).collect(Collectors.toList());
        System.out.println(skip);

        IntSummaryStatistics intSummaryStatistics = collect4.stream().mapToInt(Integer::intValue).summaryStatistics();

        System.out.println(intSummaryStatistics);

    }
}
