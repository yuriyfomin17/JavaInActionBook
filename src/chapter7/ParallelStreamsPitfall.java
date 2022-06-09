package chapter7;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

//
public class ParallelStreamsPitfall {

    public long total = 0;
    public  static List<Long> list = LongStream.rangeClosed(2, 100000000).boxed().collect(Collectors.toList());
    public static long sideEffectSum(long n) {
        ParallelStreamsPitfall accumulator = new ParallelStreamsPitfall();
        LongStream.rangeClosed(1, n).forEach(accumulator::add);
        return accumulator.total;
    }

    public static long sideEffectParallelSum(long n) {
        ParallelStreamsPitfall accumulator = new ParallelStreamsPitfall();
        LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add);
        return accumulator.total;
    }

    public static void findFirstPrimeSequential(){
        System.out.println("findFirstPrimeSequential");
        long start = System.nanoTime();
        Optional<Long> result = list.stream().filter(ParallelStreamsPitfall::isPrime).findFirst();
        long duration = (System.nanoTime() - start) / 1000000;
        System.out.println("Duration:" + duration + "msecs");
        result.ifPresent(System.out::println);
    }
    public static boolean isPrime( Long n){
        long sqrtRoot = (long) Math.sqrt(n);
        return LongStream.rangeClosed(2, sqrtRoot).noneMatch(x -> n % x == 0);
    }
    public static void findFirstPrimeParallel(){
        // Note that exchanging data between cores also takes some time. Hence, should be careful
        // on when to utilize parallel stream of sequential
        System.out.println("findFirstPrimeParallel");
        long start = System.nanoTime();
        Optional<Long> result = list.stream().filter(ParallelStreamsPitfall::isPrime).unordered().parallel().findAny();
        long duration = (System.nanoTime() - start) / 1000000;
        System.out.println("Duration:" + duration + "msecs");
        result.ifPresent(System.out::println);
    }


    public void add(long value) {
        total += value;
    }




    public static void main(String[] args) {
        // different result is returned each time which is caused by the fact that multiple threads are concurrently
        // accessing the accumulator and in particular executing total += value
        System.out.println("SideEffect parallel sum done in: " + ParallelStreams.measureSumPerf(ParallelStreamsPitfall::sideEffectParallelSum, 10_000_000L) + " msecs" );
        findFirstPrimeSequential();
        findFirstPrimeParallel();

    }

}
