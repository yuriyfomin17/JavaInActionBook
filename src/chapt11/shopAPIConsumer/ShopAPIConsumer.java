package chapt11.shopAPIConsumer;

import chapt11.shopAPI.DiscountQuote;
import chapt11.shopAPI.ShopQuote;
import org.apache.commons.lang3.RandomStringUtils;

import java.lang.reflect.Array;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class ShopAPIConsumer<T> {
    private static final int THREAD_LIMIT = 100;

    public static void getShopQuoteList(int shopQuoteSize, String productName) {
        measurePerfomnaceBiFunc((size, product) -> Stream.generate(() -> new ShopQuote(RandomStringUtils.randomAlphabetic(5), productName))
                .limit(shopQuoteSize)
                .parallel()
                .toList(), shopQuoteSize, productName);
    }

    public static void getShopQuoteListUsingCompletableFutures(int shopQuoteSize, String productName) {
        List<CompletableFuture<ShopQuote>> completableFutureStream = Stream
                .generate(() ->
                        CompletableFuture.supplyAsync(() -> new ShopQuote(RandomStringUtils.randomAlphabetic(5), productName), createExecutor(shopQuoteSize)))
                .limit(shopQuoteSize).toList();
        measurePerfomanceSupplier(() -> completableFutureStream.stream().map(CompletableFuture::join).toList());
    }

    public static void getDiscountQutoesFromShopQuotes(int shopQuoteSize, String productName) {
        measurePerfomnaceBiFunc((size, product) -> Stream.generate(() -> new ShopQuote(RandomStringUtils.randomAlphabetic(5), productName))
                .limit(shopQuoteSize)
                .parallel()
                .map(DiscountQuote::new)
                .toList(), shopQuoteSize, productName);
    }

    public static void getDiscountQuotesFromShopQuotesUsingCompletableFutures(int shopQutoeSize, String productName){
        List<CompletableFuture<DiscountQuote>> completableFutures = Stream
                .generate(() ->
                        CompletableFuture.supplyAsync(() -> new ShopQuote(RandomStringUtils.randomAlphabetic(5), productName), createExecutor(shopQutoeSize)))
                .map(future -> future.thenCompose(shopQuote -> CompletableFuture.supplyAsync(() -> new DiscountQuote(shopQuote), createExecutor(shopQutoeSize))))
                .limit(shopQutoeSize).toList();
        measurePerfomanceSupplier(() -> completableFutures.stream().map(CompletableFuture::join).toList());
    }

    public static void getAnyDiscountQuotesFromShopQuotesUsingCompletableFutures(int shopQuoteSize, String productName){
        long start = System.nanoTime();
        CompletableFuture[] completableFutures = Stream
                .generate(() ->
                        CompletableFuture.supplyAsync(() -> new ShopQuote(RandomStringUtils.randomAlphabetic(5), productName), createExecutor(shopQuoteSize)))
                .map(future -> future.thenCompose(shopQuote -> CompletableFuture.supplyAsync(() -> new DiscountQuote(shopQuote), createExecutor(shopQuoteSize))))
                .map(future -> future.thenAccept(discountQuote -> System.out.println(discountQuote + " currentDelay:(" + (System.nanoTime() - start) / 1_000_000 + ")")))
                .limit(shopQuoteSize).toArray(CompletableFuture[]::new);
        CompletableFuture.anyOf(completableFutures).join();
    }
    public static void getAllDiscountQuotesFromShopQuotesUsingCompletableFutures(int shopeQuoteSize, String productName){
        long start = System.nanoTime();
        CompletableFuture[] completableFutures = Stream
                .generate(() -> CompletableFuture.supplyAsync(() -> new ShopQuote(RandomStringUtils.randomAlphabetic(5), productName), createExecutor(shopeQuoteSize)))
                .map(future -> future.thenCompose(shopQuote -> CompletableFuture.supplyAsync(() -> new DiscountQuote(shopQuote), createExecutor(shopeQuoteSize))))
                .map(future -> future.thenAccept(discountQuote -> System.out.println(discountQuote + " currentDelay:(" + (System.nanoTime() - start) / 1_000_000 + ")")))
                .limit(shopeQuoteSize)
                .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(completableFutures).join();
    }


    public static <T> void measurePerfomnaceBiFunc(BiFunction<Integer, String, List<T>> function, int shopQuoteSize, String productName) {
        long start = System.nanoTime();
        List<T> list = function.apply(shopQuoteSize, productName);
        list.forEach(System.out::println);
        long elapsedTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Total Elapsed Time:(" + elapsedTime + " msecs)");
    }

    public static <T> void measurePerfomanceSupplier(Supplier<List<T>> supplier){
        long start = System.nanoTime();
        List<T> list = supplier.get();
        list.forEach(System.out::println);
        long elapsedTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Total Elapsed Time:(" + elapsedTime + " msecs)");
    }

    private static Executor createExecutor(int size) {
        return Executors.newFixedThreadPool(Math.min(THREAD_LIMIT, size), r -> {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            return thread;
        });
    }

}
