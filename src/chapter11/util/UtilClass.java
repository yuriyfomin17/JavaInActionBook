package chapter11.util;

import chapter11.asyncAPI.Shop;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class UtilClass<T> {
    public static void delay(){
        try{
            Thread.sleep(1000L);
        } catch (InterruptedException interruptedException){
            throw new RuntimeException(interruptedException);
        }
    }

    public static void measurePerfomance(BiFunction<List<Shop>,String,  List<String>> function, String productName, List<Shop> shops){
        long start = System.nanoTime();
        function.apply( shops, productName);
        long retrieveTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Prices returned after " + retrieveTime + " msecs");
    }

    public static void  measreInvocationPerfomance(Consumer<String> consumer, String productName){
        long start = System.nanoTime();
        consumer.accept(productName);
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime + " msces");
    }

    public static void getAvailableThreadsInPool(){
        System.out.println("Available threads in pool:" + Runtime.getRuntime().availableProcessors());
    }
}
