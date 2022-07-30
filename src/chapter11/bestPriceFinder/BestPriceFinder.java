package chapter11.bestPriceFinder;

import chapter11.asyncAPI.Shop;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BestPriceFinder {
    private final List<Shop> shops = Arrays.asList(
            new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"));


    public static List<Shop> generateShops(int limit){
        return Stream.generate(() -> new Shop(RandomStringUtils.randomAlphabetic(6))).limit(limit).collect(Collectors.toList());
    }

    public List<String> findPrices(List<Shop> shopList, String product){
        return shopList.stream().map(shop -> String.format("%s price is %2f", shop.getName(), shop.getPrice(product)) ).collect(Collectors.toList());
    }

    public List<String> findPricesInParallel(List<Shop> shopList, String product){
        return shopList.parallelStream().map(shop -> String.format("%s price is %2f", shop.getName(), shop.getPrice(product)) ).collect(Collectors.toList());
    }

    /**
     * <h3>findPricesInCompletableFutures</h3>
     *
     * this works much faster then
     * <br><br/>
     * <h3>findPricesInParalle</h3>
     *
     * as we only have 16 available threads in thread pool (found from {@link chapter5.utilityClass.UtilityClass}).
     *
     * Hence, if we have only 16 shops then <h3>findPricesInParallel</h3> will work with same speed as
     * <br><br/>
     * <h3>findPricesInCompletableFutures</h3>
     *
     * The key difference is in {@link Executor} as we can now specify number of threads available
     * that we can use
     *
     */
    public  List<String> findPricesInCompletableFutures(List<Shop> shopList, String product){
        List<CompletableFuture<String>> priceFutures = shopList
                .stream().map(shop -> CompletableFuture.supplyAsync(() -> shop.getName() + " price is " + shop.getPrice(product), createExecutor(shopList.size()) )).toList();

        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }
    /**
     * <h3>findPricesInCompletableFutureList</h3>
     * doesn't work effectively as it waits for each future to be executed before
     * calling next
     */

    public List<String> findPricesInCompletableFutureList(List<Shop> shopList, String product){
        return  shopList
                .stream().map(shop -> CompletableFuture.supplyAsync(() -> shop.getName() + " price is " + shop.getPrice(product), createExecutor(shopList.size()) ))
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }
    public List<Shop> getShops() {
        return shops;
    }

    static private Executor createExecutor(int shopsSize){
        return Executors.newFixedThreadPool(Math.min(shopsSize, 100), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                return t;
            }
        });
    }

}
