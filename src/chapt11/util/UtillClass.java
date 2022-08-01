package chapt11.util;

import chapt11.shopAPI.DiscountQuote;
import chapt11.shopAPI.ShopQuote;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class UtillClass {
    private static final Random RANDOM = new Random();
    private static final int MIN_DELAY_MILLIS = 1000;
    private static final int MAX_DELAY_MILLIS = 2000;

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static void delay() {
        long delayTimeMillis = (long) (RANDOM.nextDouble() * (MAX_DELAY_MILLIS - MIN_DELAY_MILLIS) + MIN_DELAY_MILLIS);
        try {
            Thread.sleep(delayTimeMillis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<ShopQuote> generateRandomShopeQuotes(int size, String productName) {
        return Stream.generate(() -> new ShopQuote(RandomStringUtils.randomAlphabetic(5), productName)).limit(size).toList();
    }


    public static List<DiscountQuote> generateRandomDiscountQuotes(List<ShopQuote> shopQuoteList) {
        return shopQuoteList.stream().map(DiscountQuote::new).toList();
    }

    public static void getAvailableThreadsInPool() {
        System.out.println("Available threads in pool:" + Runtime.getRuntime().availableProcessors());
    }
}
