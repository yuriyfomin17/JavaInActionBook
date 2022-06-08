package chapter5.collectorInterface;

import java.util.*;
import java.util.function.*;
import java.util.stream.IntStream;

import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;
import java.util.stream.Collector.Characteristics;

public class PrimeNumbersCollector implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {
    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> new HashMap<Boolean, List<Integer>>(){{
            put(true, new ArrayList<Integer>());
            put(false, new ArrayList<Integer>());
        }};
    }

    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (Map<Boolean, List<Integer>> acc, Integer candidate) -> {
            acc.get( isPrime(acc.get(true), candidate) )
                    .add(candidate);
        };
    }
    public static List<Integer> takeAWhile(List<Integer> list, Predicate<Integer> predicate){
        int i = 0;
        for(Integer a : list){
            if (predicate.test(a)) return list.subList(0, i);
            i++;
        }
        return list;
    }

    public static boolean isPrime(List<Integer> primes, Integer candidate){
        int sqrtCandidate = (int) Math.sqrt(candidate);
        return takeAWhile(primes, (x) -> x > sqrtCandidate).stream().noneMatch(i -> candidate % i == 0);
    }

    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (Map<Boolean, List<Integer>> map1, Map<Boolean, List<Integer>> map2) -> {
            map1.get(true).addAll(map2.get(true));
            map1.get(false).addAll(map2.get(false));
            return map1;
        };
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH));
    }
    public static Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector(int n){
        return IntStream.rangeClosed(2, n).boxed()
                .collect(() -> new HashMap<Boolean, List<Integer>>(){{
                    put(true, new ArrayList<>());
                    put(false, new ArrayList<>());
                }},
                        (acc, candidate) ->{
                    acc.get(isPrime(acc.get(true), candidate)).add(candidate);
                        },
                        (map1, map2) ->{
                    map1.get(true).addAll(map2.get(true));
                    map1.get(false).addAll(map2.get(false));
                        }
                );
    }
//    public static Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector2(int n){
//        return IntStream.rangeClosed(2, n).boxed().collect(new PrimeNumbersCollector());
//    }
    public static void main(String[] args) {
        Map<Boolean, List<Integer>> map = partitionPrimesWithCustomCollector(1000000);
        map.get(true).forEach(System.out::println);
//        long fastest = Long.MAX_VALUE;
//        for (int i = 0; i < 10; i++) {
//            long start = System.nanoTime();
//            Map<Boolean, List<Integer>> map = partitionPrimesWithCustomCollector(1000000);
//
//            long duration = (System.nanoTime() - start)  / 1000000;
//            fastest = Math.min(fastest, duration);
//        }
//        System.out.println("Fastest execution done in " + fastest + " msecs");
    }
}
