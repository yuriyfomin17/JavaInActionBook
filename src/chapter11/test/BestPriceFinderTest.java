package chapter11.test;

import chapter11.asyncAPI.Shop;
import chapter11.bestPriceFinder.BestPriceFinder;
import chapter11.util.UtilClass;
import org.junit.jupiter.api.Test;

public class BestPriceFinderTest {
    private static final BestPriceFinder SHOP_API = new BestPriceFinder();
    private static final String PRODUCT_NAME = "Iphone Pro Max Super Duper Cool 100500s";
    private static final Shop RANDOM_SHOP = new Shop("Random Shop");

    @Test
    public void testFindPrices() {
        System.out.println("Measure API executed sequentially");
        UtilClass.measurePerfomance(SHOP_API::findPrices, PRODUCT_NAME, BestPriceFinder.generateShops(5));

        System.out.println();
        System.out.println("Measure API executed in parallel");
        UtilClass.measurePerfomance(SHOP_API::findPricesInParallel, PRODUCT_NAME, BestPriceFinder.generateShops(100));

        System.out.println();
        System.out.println("Measure API executed using CompletableFutures");
        UtilClass.measurePerfomance(SHOP_API::findPricesInCompletableFutures, PRODUCT_NAME, BestPriceFinder.generateShops(100));
    }

    @Test
    public void testInvocationTime() {
        System.out.println("Invocation time using Future");
        UtilClass.measreInvocationPerfomance(RANDOM_SHOP::createFutureForPrice, PRODUCT_NAME);
        System.out.println("Invocation time without Future");
        UtilClass.measreInvocationPerfomance(RANDOM_SHOP::getPrice, PRODUCT_NAME);
    }

    @Test
    public void getAvailableThreadsInPool(){
        UtilClass.getAvailableThreadsInPool();
    }

    @Test
    public void testFindPriceWithDiscount(){
        RANDOM_SHOP.createFutureForPriceLessVerbose(PRODUCT_NAME);
    }
}
