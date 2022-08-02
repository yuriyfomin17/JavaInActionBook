package chapter11.shopAPI;

import chapter11.util.UtillClass;

import java.util.Random;

public class ShopQuote {


    private final String shopName;
    private final String productName;
    private final double productPrice;
    private final long shopDelay;
    private static final Random RANDOM = new Random();

    public ShopQuote(String shopName, String product) {
        this.shopName = shopName;
        this.productName = product;
        long start = System.nanoTime();
        productPrice = UtillClass.round(calculateProductPrice(product), 2);
        long end = System.nanoTime();
        shopDelay = (end - start) / 1_000_000;
    }

    public String getShopName() {
        return shopName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public long getShopDelay() {
        return shopDelay;
    }

    private static double calculateProductPrice(String product) {
        UtillClass.delay();
        return RANDOM.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public String getProductName() {
        return productName;
    }

    @Override
    public String toString() {
        return "ShopQuote{" +
                "shopName='" + shopName + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", shopDelay=" + shopDelay +
                '}';
    }
}
