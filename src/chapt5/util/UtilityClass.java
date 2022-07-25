package chapt5.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
}
