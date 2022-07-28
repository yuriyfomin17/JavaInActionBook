package chapt7.UtilityClass;

import java.util.function.Function;

public interface UtilityImpl {

    /**
     * <h3>sequentialSum<h3/>
     * generates sum for natural numbers
     */
    long sequentialSum(long limit);

    /**
     * <h3>notOptimizedParallelSum<h3/>
     * generates sum of natural numbers in parallel
     */
    long notOptimizedParallelSum(long limit);

    /**
     * <h3>optimizedParallelSum</h3>
     * generated sum of natural numbers in optimized and parallel fashion
     */

    long optimizedPrallelSum(long limit);

    /**
     * <h3>measurePerfomance<h3/>
     * measure perfomance of the given function
     */
    long measurePerfomance(Function<Long, Long> function);
}
