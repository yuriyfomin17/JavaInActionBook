package chapter7.UtilityClass;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class UtilityClass implements UtilityImpl {
    @Override
    public long sequentialSum(long limit) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(limit)
                .reduce(0L, Long::sum);
    }

    /**
     * Even considering the fact that <h3>notOptomizedParallelSum</h3>
     * is executed in parallel it is not optimized. Hence, <h3>sequentialSum</h3>
     * will always be much faster
     * <ol>
     *     <li>iterate generates boxed values which have to be unboxed</li>
     *     <li>iterate is difficutl to divide into independant parallel chunks</li>
     * </ol>
     */
    @Override
    public long notOptimizedParallelSum(long limit) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(limit)
                .parallel()
                .reduce(0L, Long::sum);
    }

    @Override
    public long optimizedPrallelSum(long limit) {
        return LongStream.rangeClosed(1, limit).parallel().reduce(0L, Long::sum);
    }

    @Override
    public long measurePerfomance(Function<Long, Long> function) {
        long FASTEST = Long.MAX_VALUE;
        long limit = 100_000_000;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            function.apply(limit);
            long duration = (System.nanoTime() - start) / 1_000_000;
            FASTEST = Math.min(duration, FASTEST );
        }
        return FASTEST;
    }
}
