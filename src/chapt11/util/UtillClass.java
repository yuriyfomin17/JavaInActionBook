package chapt11.util;

import chapt11.shopAPI.Quote;
import chapt11.shopAPI.Shop;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UtillClass {
    private static final Random RANDOM = new Random();
    private static final int MIN_DELAY_MILLIS = 1000;
    private static final int MAX_DELAY_MILLIS = 3000;

    public static void delay()  {
        long delayTimeMillis = (long) (RANDOM.nextDouble() * (MAX_DELAY_MILLIS - MIN_DELAY_MILLIS) + MIN_DELAY_MILLIS);
        try {
            Thread.sleep(delayTimeMillis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Shop> generateRandomShopsList(int size){
        return Stream.generate(() -> new Shop(RandomStringUtils.randomAlphabetic(5))).limit(size).toList();
    }
    public static List<String> getPriceFromRandomShopsList(List<Shop> shopList, String product){
        return shopList.stream().map(shop -> shop.getProductPrice(product)).toList();
    }
    public static List<String> geneateRandomQuote(List<Shop> shopList, String product){
        return shopList.stream().map(shop -> shop.getProductPrice(product)).map(Quote::new).map(Quote::applyDiscount).toList();
    }
}
