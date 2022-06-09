package chapter7;
import java.util.concurrent.RecursiveTask;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class ForkJoinSumCalculator extends RecursiveTask<Long> implements ComputeInterface {
    private final long[] numbers;
    private final int start;
    private final int end;

    public static final long THRESHOLD = 10000;
    public ForkJoinSumCalculator(long[] numbers){
        this(numbers, 0, numbers.length);
    }
    private ForkJoinSumCalculator(long[] numbers, int start, int end){
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }
    @Override
    public Long compute() {
        int length = end - start;
        if (length <= THRESHOLD){
            return computeSequentially();
        }
        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length/ 2);
        // Asynchronously execute the newly created subtask using another thread
        leftTask.fork();
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length /2, end);
        // Execute this second subtask synchronously, potentially allowing further recursive splits
        Long rightResult = rightTask.compute();
        // Read the result of the first subtask or wait for it if it isn't ready
        Long leftResult = leftTask.join();
        // the result of this task is the combination of the results of the two subtasks
        return leftResult + rightResult;
    }

    private long computeSequentially(){
        long sum = 0;
        for (int i = start; i < end ; i++) {
            sum += numbers[i];
        }
        return sum;
    }

    public static void measureTime(ComputeInterface forkJoinSumCalculator){
        long start = System.nanoTime();
        System.out.println(forkJoinSumCalculator.compute());
        long duration = (System.nanoTime() - start) / 1000000;
        System.out.println("Duration:" + duration + "msecs");
    }

    public static void measureForJoinParallel(){
        System.out.println("Parallel execution!");
        ForkJoinSumCalculator forkJoinSumCalculator = new ForkJoinSumCalculator(LongStream.rangeClosed(0, 10000000).toArray());
        measureTime(forkJoinSumCalculator);
    }
    public static void measureSequentialExecution(long[] arr){
        System.out.println("Sequential execution!");
        ComputeInterface computeInterface = () -> {
            long sum = 0;
            for (long l : arr) {
                sum += l;
            }
            return sum;
        };
        measureTime(computeInterface);
    }

    public static void main(String[] args) {
//        measureForJoinParallel();
        measureSequentialExecution(LongStream.rangeClosed(0, 10000000).toArray());
        // Using Java Library
        System.out.println("Java Util Library");
        ComputeInterface computeInterface = () -> LongStream.rangeClosed(0, 10000000).parallel().reduce(0, Long::sum);
        measureTime(computeInterface);
    }
}
