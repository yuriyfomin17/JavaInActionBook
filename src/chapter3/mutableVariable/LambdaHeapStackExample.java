package chapter3.mutableVariable;

import java.util.function.IntSupplier;

public class LambdaHeapStackExample {

    private int value1 = 0;
// Here value1 will be stored in heap
    public IntSupplier getValue1(){
        return () -> value1++;
    }
// Won't compile as value2 is stored in stack and each thread can get its own stack
//    public IntSupplier getValue2(){
//        int value2 = 0;
//        return () -> value2++;
//    }

    public static void main(String[] args) {
        LambdaHeapStackExample lambdaHeapStackExample = new LambdaHeapStackExample();
        IntSupplier intSupplier = lambdaHeapStackExample.getValue1();
        System.out.println(intSupplier.getAsInt());
    }
}
