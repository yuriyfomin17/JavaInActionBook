package chapt7.UtilityClass.test;

import chapt7.UtilityClass.UtilityClass;
import org.junit.jupiter.api.Test;

public class TestUtilityClass {
    private final UtilityClass UTILITY = new UtilityClass();

    @Test
    public void testSequnitalParallelSum(){
        System.out.println("Sequential Sum:" + UTILITY.sequentialSum(100));
        System.out.println("Non optimized Parallel Sum:" + UTILITY.notOptimizedParallelSum(100));
        System.out.println("Optmized Parallel Sum:" + UTILITY.optimizedPrallelSum(100));
    }


    @Test
    public void measurePerfomanceOfSequentialAndParallelSum(){
        System.out.println("Sequential sum duration:" + UTILITY.measurePerfomance(UTILITY::sequentialSum));
        System.out.println("Not Optimized Parallel sum duration:" + UTILITY.measurePerfomance(UTILITY::notOptimizedParallelSum));
        System.out.println("Optimized Parallel sum duration:" + UTILITY.measurePerfomance(UTILITY::optimizedPrallelSum));
    }
}
