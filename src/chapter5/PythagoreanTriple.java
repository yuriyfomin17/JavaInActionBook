package chapter5;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.UnaryOperator;
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
    public static void streamOfMethod(){
        Stream<String> stream = Stream.of("Java 8", "Lambdas", "In", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);
    }
    public static void streamFromArrays(){
        int[] numbers = {2, 3, 5, 7, 11, 13};
        int sum = Arrays.stream(numbers).sum();
        System.out.println(sum);
    }
    public static void streamFromFiles(){
        long uniqueWords = 0;
        try(Stream<String> lines = Files.lines(Paths.get("src/chapter5/data.txt"), Charset.defaultCharset())) {
            uniqueWords = lines.flatMap(line  -> Stream.of(line.split(" "))).distinct().count();
            System.out.println("Number of unique words:" + uniqueWords);
        } catch (IOException e){

        }
    }
    public static void streamFromFunctions(){
        UnaryOperator<Integer> unaryOperator  = new UnaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer + 2;
            }
        };

        Stream.iterate(0, unaryOperator).limit(10).forEach(System.out::println);
    }
    public static void main(String[] args) {
        getPythagoreanTriples();
        streamOfMethod();
        streamFromArrays();
        streamFromFiles();
        streamFromFunctions();

    }
}
