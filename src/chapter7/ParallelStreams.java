package chapter7;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelStreams {
    public static long parallelSum(long n) {
        // here it is long because we perform boxing and autoboxing
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);

    }
    public static long parallelSumFaster(long n){
        // LongStream works on primitive long numbers directly so there is no unboxing overhead
        // LongStream.rangeClosed produces ranges of numbers, which can be easily split into independent chunks.
        // For example, the range 1-20 can be split into 1-5, 6-10
        return LongStream.rangeClosed(1, n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    public static long iterativeSum(long n) {
        return LongStream.rangeClosed(1, n).reduce(0L, Long::sum);
    }

    public static long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            System.out.println("Result:" + sum);
            long duration = (System.nanoTime() - start) / 1000000;
            fastest = Math.min(fastest, duration);
        }
        return fastest;
    }

    public static void main(String[] args) {
        System.out.println("Sequential sum done in:" + measureSumPerf(ParallelStreams::iterativeSum, 10000000) + "msecs");
        System.out.println("Parallel sum done in:" + measureSumPerf(ParallelStreams::parallelSum, 10000000) + "msecs");
        System.out.println("Parallel sum faster done in:" + measureSumPerf(ParallelStreams::parallelSumFaster, 10000000) + "msecs");
    }
}
