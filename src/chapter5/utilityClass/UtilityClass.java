package chapter5.utilityClass;

import chapter5.utilityClass.primeNumberCustomCollector.PrimeNumbersCollector;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class UtilityClass {
    public static List<String> getUniqueCharactersFromStringList(List<String> stringList) {
        return stringList.stream().map(s -> s.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
    }

    public static List<Integer> getSquareNumbers(List<Integer> list) {
        return list.stream().map(el -> el * el).collect(Collectors.toList());
    }

    public static List<List<Integer>> getAllPairs(List<Integer> list1, List<Integer> list2) {
        return list1.stream()
                .flatMap(el1 -> list2.stream().map(el2 -> List.of(el1, el2)))
                .collect(Collectors.toList());
    }

    public static List<List<Integer>> getAllPairsDivisibleByThree(List<Integer> list1, List<Integer> list2) {
        return list1.stream()
                .flatMap(el1 -> list2.stream().map(el2 -> List.of(el1, el2)))
                .filter(el -> (el.get(0) + el.get(1)) % 3 == 0)
                .collect(Collectors.toList());
    }

    public static Integer getSum(List<Integer> list) {
        return list.stream().reduce(0, Integer::sum);
    }

    public static List<List<Integer>> getPythagoreanTriples() {
        return IntStream.rangeClosed(1, 100).boxed().flatMap(a -> IntStream.rangeClosed(a, 100).boxed().filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                .map(b -> List.of(a, b, (int) Math.sqrt(a * a + b * b)))).collect(Collectors.toList());
    }

    public static long countNumberOfUniqueWords(String fileName) {
        long uniqueWords;
        try (Stream<String> lines = Files.lines(Path.of(fileName), Charset.defaultCharset())) {
            uniqueWords = lines.distinct().count();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return uniqueWords;
    }

    public static List<String> listUniqueNumberOfWords(String fileName) {
        try (Stream<String> lines = Files.lines(Path.of(fileName), Charset.defaultCharset())) {
            return lines.distinct().toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Integer> getFibonacciSequence(int size) {
        return Stream.iterate(new int[]{0, 1}, n -> {
            n[1] = n[0] + n[1];
            n[0] = n[1] - n[0];
            return n;
        }).limit(size).map(n -> n[1]).collect(Collectors.toList());
    }

    public static boolean isNumberPrime(int n) {
        for (int i = 2; i < Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    /**
     * <h3>isNumberPrimeFromCurrentCandidates</h3>
     * if we have list of prime numbers then we can find whether number is prime or not by
     * simply dividing it by them
     */
    public static boolean isNumberPrimeFromCurrentCandidates(List<Integer> primes, int currentNumber) {
        int candidateSquareRoot = (int) Math.sqrt(currentNumber);
        for (Integer prime : primes) {
            if (currentNumber % prime == 0) return false;
            else if (prime > candidateSquareRoot) return true;
        }
        return true;
    }

    public static Map<Boolean, List<Integer>> getPrimeNumbersList(int limit) {
        return IntStream.rangeClosed(2, limit).boxed().collect(partitioningBy(UtilityClass::isNumberPrime));
    }

    public static Map<Boolean, List<Integer>> getPrimeNumbersListWithCustomCollector(int limit) {
        return IntStream.rangeClosed(2, limit).boxed().collect(new PrimeNumbersCollector());
    }
    public static Map<Boolean, List<Integer>> getPrimeNumbersWithoutStream(int limit){
        Map<Boolean, List<Integer>> result = new HashMap<>();
        result.put(true, new ArrayList<>());
        result.put(false, new ArrayList<>());
        for (int i = 2; i <= limit ; i++) {
            boolean isPrime = UtilityClass.isNumberPrimeFromCurrentCandidates(result.get(true), i);
            List<Integer> list = result.get(isPrime);
            list.add(i);
            result.put(isPrime, list);
        }
        return result;
    }

    public static void testPerfomanceofWithAndWithoutCusomtCollector(Function<Integer, Map<Boolean, List<Integer>>> function, String description) {
        long fastest = Long.MAX_VALUE;
        int LIMIT = 1000000;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            function.apply(LIMIT);
            long duration = (System.nanoTime() - start) / 1000000;
            fastest = Math.min(fastest, duration);
        }
        System.out.println("Fastest execution done in " + fastest + " msecs" + " for " + description);
    }
}
