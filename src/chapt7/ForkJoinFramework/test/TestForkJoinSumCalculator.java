package chapt7.ForkJoinFramework.test;

import chapt7.ForkJoinFramework.ForkJoinSumCalculator;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class TestForkJoinSumCalculator {

    @Test
    public void testForkJoinSumCalculator(){
        long[] numbers = LongStream.rangeClosed(1, 10_000_000).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        System.out.println("Sum:" + new ForkJoinPool().invoke(task));
    }
}
