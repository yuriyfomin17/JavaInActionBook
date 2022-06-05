package chapter5;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PythagoreanTriple {
    public static void getPythagoreanTriples(){
        Stream<int[]> pythagoreanTriples = IntStream
                .rangeClosed(1, 100).boxed().flatMap(
                        a -> IntStream.rangeClosed(a, 100).filter(b -> Math.sqrt(a * a  +  b * b) % 1 == 0 )
                                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a  +  b * b)}));
        pythagoreanTriples.limit(5).forEach(x -> System.out.println(x[0] + "," + x[1] + "," + x[2]));
    }
    public static void main(String[] args) {
        getPythagoreanTriples();

    }
}
