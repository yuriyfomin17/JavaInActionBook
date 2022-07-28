package chapt7.ForkJoinFramework;

public class ForkJoinSumCalculator extends java.util.concurrent.RecursiveTask<Long> {
    private final long[] numbers;
    private final int start;
    private final int end;

    public static final long THRESHOLD = 10_000;

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        if (length <= THRESHOLD) return computeSequentially();
        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);
        /*
          Asynchronously execute the newly created subtask using another thread
          of the ForkJoinPool
         */
        leftTask.fork();

        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length/ 2, end);
        Long rightResult = rightTask.compute();
        /*
            Read the result of the first subtask or wait for it if it isn't ready
         */

        Long leftResult = leftTask.join();
        return rightResult + leftResult;
    }

    private long computeSequentially(){
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }
}
