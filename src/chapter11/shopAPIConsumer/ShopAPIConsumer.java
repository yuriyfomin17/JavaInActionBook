package chapter11.shopAPIConsumer;

import chapter11.shopAPI.CurrencyConverter;
import chapter11.shopAPI.DiscountQuote;
import chapter11.shopAPI.ShopQuote;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Stream;
/**
 * {@link CompletableFuture} is more effective then just {@link Stream}.parallel
 * because you can supply an executor to the {@link CompletableFuture} with the defined number of threads available for the task.
 * At the same time {@link Stream}.parallel will only use {@link Runtime}.getAvailableThreads (16 threads). Hence, if  number of tasks are
 * 16 then {@link CompletableFuture} and {@link Stream}.parallel will finish job almost at the same time.
 * However, when number of tasks will start to increase then this is when {@link CompletableFuture} will start to win
 * <br></br>
 * <br></br>
 * Also, when using {@link CompletableFuture} make sure that you use two streams. Otherwise, tasks will be executed sequentially
 * <br></br>
 * <br></br>
 * Alsom then using thenCompose, there exist counterpart with the <h2>Async</h2>suffix. This means that so each of the task can be handled by different threads.
 * However, in our case result of the second task depends on the first. Hence, no point in using thenComposeAsync
 */

public class ShopAPIConsumer {
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
    public static void getShopQuotesPricesInEurUsingCompletableFutures(int shopQuoteSize, String productName){
        Executor executor = createExecutor(shopQuoteSize);
        List<CompletableFuture<Double>> completableFutures = Stream
                .generate(() -> CompletableFuture.supplyAsync(() -> new ShopQuote(RandomStringUtils.randomAlphabetic(5 ), productName), executor))
                .limit(shopQuoteSize)
                .map(future -> future.thenCombine( CompletableFuture.supplyAsync(CurrencyConverter::getCurrencyUsdEurRate, executor), (shopQuote, rate) -> shopQuote.getProductPrice() * rate))
                .toList();
        measurePerfomanceSupplier(() -> completableFutures.stream().map(CompletableFuture::join).toList());
    }

    public static void getDiscountQutoesFromShopQuotes(int shopQuoteSize, String productName) {
        measurePerfomnaceBiFunc((size, product) -> Stream.generate(() -> new ShopQuote(RandomStringUtils.randomAlphabetic(5), productName))
                .limit(shopQuoteSize)
                .parallel()
                .map(DiscountQuote::new)
                .toList(), shopQuoteSize, productName);
    }

    public static void getDiscountQuotesFromShopQuotesUsingCompletableFutures(int shopQutoeSize, String productName) {
        List<CompletableFuture<DiscountQuote>> completableFutures = Stream
                .generate(() ->
                        CompletableFuture.supplyAsync(() -> new ShopQuote(RandomStringUtils.randomAlphabetic(5), productName), createExecutor(shopQutoeSize)))
                .map(future -> future.thenCompose(shopQuote -> CompletableFuture.supplyAsync(() -> new DiscountQuote(shopQuote), createExecutor(shopQutoeSize))))
                .limit(shopQutoeSize).toList();
        measurePerfomanceSupplier(() -> completableFutures.stream().map(CompletableFuture::join).toList());
    }

    public static void getAnyDiscountQuotesFromShopQuotesUsingCompletableFutures(int shopQuoteSize, String productName) {
        long start = System.nanoTime();
        CompletableFuture[] completableFutures = Stream
                .generate(() ->
                        CompletableFuture.supplyAsync(() -> new ShopQuote(RandomStringUtils.randomAlphabetic(5), productName), createExecutor(shopQuoteSize)))
                .map(future -> future.thenCompose(shopQuote -> CompletableFuture.supplyAsync(() -> new DiscountQuote(shopQuote), createExecutor(shopQuoteSize))))
                .map(future -> future.thenAccept(discountQuote -> System.out.println(discountQuote + " currentDelay:(" + (System.nanoTime() - start) / 1_000_000 + ")")))
                .limit(shopQuoteSize).toArray(CompletableFuture[]::new);
        CompletableFuture.anyOf(completableFutures).join();
    }

    public static void getAllDiscountQuotesFromShopQuotesUsingCompletableFutures(int shopeQuoteSize, String productName) {
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

    public static <T> void measurePerfomanceSupplier(Supplier<List<T>> supplier) {
        long start = System.nanoTime();
        List<T> list = supplier.get();
        list.forEach(System.out::println);
        long elapsedTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Total Elapsed Time:(" + elapsedTime + " msecs)");
    }

    private static Executor createExecutor(int size) {
        return Executors.newFixedThreadPool(Math.min(THREAD_LIMIT, size), r -> {
            Thread thread = new Thread(r);
            /*
              using setDaemon which doesn't prevent termination of program
             */
            thread.setDaemon(true);
            return thread;
        });
    }

}
