package chapt11.shopAPI;

import chapt11.util.UtillClass;

import java.util.Random;

public class Shop {


    private final String shopName;
    private static final Random RANDOM = new Random();

    public Shop(String shopName) {
        this.shopName = shopName;
    }

    public String getProductPrice(String product) {
        long start = System.nanoTime();
        double productPrice = this.calculateProductPrice(product);
        long end = System.nanoTime();
        return String.format("%s:%.2f:%s (delay: %d millis)", this.getShopName(), productPrice, product, (end - start) / 1_000_000);
    }

    public String getShopName() {
        return shopName;
    }

    public double calculateProductPrice(String product) {
        UtillClass.delay();
        return RANDOM.nextDouble() * product.charAt(0) + product.charAt(1);
    }


    @Override
    public String toString() {
        return "Shop{" +
                "shopName='" + shopName + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Shop shop = new Shop("My best shop");
        String productPrice = shop.getProductPrice("Iphone SUper duper cool");
        System.out.println(productPrice);
    }
}
