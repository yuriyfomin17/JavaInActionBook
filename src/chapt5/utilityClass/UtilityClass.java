package chapt5.utilityClass;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UtilityClass {
    public static List<String> getUniqueCharactersFromStringList(List<String> stringList){
        return stringList.stream().map(s -> s.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
    }
    public static List<Integer> getSquareNumbers(List<Integer> list){
        return list.stream().map(el -> el * el).collect(Collectors.toList());
    }

    public static List<List<Integer>> getAllPairs(List<Integer> list1, List<Integer> list2){
        return list1.stream()
                .flatMap(el1 -> list2.stream().map(el2 -> List.of(el1, el2)))
                .collect(Collectors.toList());
    }

    public static List<List<Integer>> getAllPairsDivisibleByThree(List<Integer> list1, List<Integer> list2){
        return list1.stream()
                .flatMap(el1 -> list2.stream().map(el2 -> List.of(el1, el2)))
                .filter(el -> (el.get(0) + el.get(1)) % 3 == 0)
                .collect(Collectors.toList());
    }

    public static Integer getSum(List<Integer> list){
        return list.stream().reduce(0, Integer::sum);
    }

    public static List<List<Integer>> getPythagoreanTriples(){
        return IntStream.rangeClosed(1, 100).boxed().flatMap(a -> IntStream.rangeClosed(a, 100).boxed().filter(b-> Math.sqrt(a * a + b * b) % 1 == 0)
                .map(b -> List.of(a, b , (int) Math.sqrt(a * a + b * b)))).collect(Collectors.toList());
    }
}
