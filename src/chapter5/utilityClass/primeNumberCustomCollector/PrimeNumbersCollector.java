package chapter5.utilityClass.primeNumberCustomCollector;

import chapter5.utilityClass.UtilityClass;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class PrimeNumbersCollector implements Collector<Integer,
        Map<Boolean, List<Integer>>,
        Map<Boolean, List<Integer>>> {
    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> new HashMap<>() {{
            put(true, new ArrayList<>());
            put(false, new ArrayList<>());
        }};
    }

    /**
     * <h3>accumulator<h3/>
     * is why {@link PrimeNumbersCollector} perfoms faster then normal accumulator
     * Accumulator has access to intermidiate results hence it while {@link Collector} doesn't have this access
     */

    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (Map<Boolean, List<Integer>> acc, Integer candidate) -> acc.get(UtilityClass.isNumberPrimeFromCurrentCandidates(acc.get(true), candidate)).add(candidate);
    }

    /**
     * <h3>combiner</h3>
     * is used here for parallel execution. In case of parallel execution combiner should just combine to maps into one.
     * However, we can't do that in case of Prime Number Finder as it has mutual state. Hence, it will produce incorrect result.
     * Combiner here was added just for completeness.
     */
    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (Map<Boolean, List<Integer>> map1,
                Map<Boolean, List<Integer>> map2) -> {
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
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }
}
