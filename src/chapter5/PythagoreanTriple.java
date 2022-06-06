package chapter5;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
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
        Stream.iterate(0, x -> x + 2).limit(10).forEach(System.out::println);

    }
    public static void generateFibonacci(){
        System.out.println("Fibonacci\n");
        // expects previous results
        // we generate new arr each time as it is unsafe to run mutable variable in lambda in concurrent programming
        Stream.iterate(new int[]{0, 1}, arr -> new int[]{arr[1], arr[0] + arr[1]}).limit(10).forEach(arr -> System.out.println("(" + arr[0] + ","  + arr[1] + ")"));
    }
    public static void generateMathRandom(){
        System.out.println("Random\n");
        Stream.generate(Math::random).limit(5).forEach(System.out::println);
    }
    public static void intStreamsExample(){
        IntStream.generate(() -> 1).limit(5).forEach(System.out::println);
        System.out.println("Not safe operation");
        IntSupplier intSupplier = new IntSupplier() {
            private int previous = 0;
            private int next = 1;
            @Override
            public int getAsInt() {
                int previous = this.previous;
                this.next = this.next + this.previous;
                this.previous = this.next - this.previous;
                return previous;
            }
        };
        IntStream.generate(intSupplier).limit(10).forEach(System.out::println);
    }

    public static void main(String[] args) {
        getPythagoreanTriples();
        streamOfMethod();
        streamFromArrays();
        streamFromFiles();
        streamFromFunctions();
        generateFibonacci();
        generateMathRandom();
        intStreamsExample();

    }
}
