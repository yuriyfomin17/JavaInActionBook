package chapter11.asyncAPI;

import chapter11.util.UtilClass;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Shop {

    private final String name;

    public Shop(String shopName) {
        this.name = shopName;
    }

    public String getName() {
        return name;
    }

    private final static Random RANDOM = new Random();

    public Future<Double> createFutureForPrice(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            try {
                double price = calculatePrice(product);
                futurePrice.complete(price);
            } catch (Exception ex) {
                futurePrice.completeExceptionally(ex);
            }
        }).start();
        return futurePrice;
    }

    public Future<Double> getPriceAsyncLessVerbose(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    public double getPrice(String product) {
        return calculatePrice(product);
    }

    public double calculatePriceUsingDouble(Future<Double> doubleFuture) {
        try {
            return doubleFuture.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static double calculatePrice(String product) {
        UtilClass.delay();
        return RANDOM.nextDouble() * product.charAt(0) + product.charAt(1);
    }

}
