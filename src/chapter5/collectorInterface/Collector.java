package chapter5.collectorInterface;


import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector.Characteristics;

public interface Collector<T,A, R> {
    Supplier<A> supplier();
    BiConsumer<A, T> accumulator();
    Function<A, R> finisher();
    BinaryOperator<A> combiner();
    Set<Characteristics> characteristics();
}
// T is the generic type of the items in the stream to be collected

// A is the type of the accumulator, the object on which the partial result will be accumulated
// during the collection process

//R is the type of the object(typically, but not always , the collection) resulting from collect operation

