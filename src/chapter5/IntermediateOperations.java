package chapter5;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IntermediateOperations {
    public static void squareElements(){
        Integer[] list = {1, 2, 3, 4, 5};
        List<Integer> squaredList = Arrays.stream(list).map(x -> x * x).collect(Collectors.toList());
        squaredList.forEach(System.out::println);
    }
    public static void returnAllPairs(){
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer >list2 = Arrays.asList(3, 4);
        List<int[]> list3 = list1.stream()
                .flatMap(x -> list2.stream().map( j -> new int[]{x, j}))
                .collect(Collectors.toList());
        list3.forEach((int[] el) -> Arrays.stream(el).forEach(System.out::println));
    }
    public static void returnAllPairsDivisibleByThree(){
        System.out.println();
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer >list2 = Arrays.asList(3, 4);
        List<int[]> list3 = list1.stream().flatMap(x -> list2.stream().filter(j -> (x + j) % 3 == 0).map( j -> new int[]{x, j})).collect(Collectors.toList());
        list3.forEach((int[] el) -> Arrays.stream(el).forEach(System.out::println));
    }
    public static void main(String[] args) {
        squareElements();
        returnAllPairs();
        returnAllPairsDivisibleByThree();
    }
}
