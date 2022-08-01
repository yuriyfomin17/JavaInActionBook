package chapt11.shopAPI;

import chapt11.util.UtillClass;

public class Quote {
    private final String shopName;
    private final double productPrice;
    private final String productName;

    public Quote(String shopProductPrice) {
        String[] infoArr = shopProductPrice.split(":");
        this.shopName = infoArr[0];
        this.productPrice = Double.parseDouble(infoArr[1].split(",")[0]);
        this.productName = infoArr[2];
    }

    public String applyDiscount() {
        long start = System.nanoTime();
        UtillClass.delay();
        Discount discount = Discount.getDiscount();
        double priceWithDiscount = productPrice - Discount.applyDiscount(discount) * productPrice;
        long end = System.nanoTime();
        return String.format("%s:%.2f:%s:%s (delay: %d millis)", shopName, priceWithDiscount, productName, discount, (end - start) / 1_000_000);
    }

    @Override
    public String toString() {
        return "Quote{" +
                "shopName='" + shopName + '\'' +
                ", productPrice=" + productPrice +
                ", productName='" + productName + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Shop shop = new Shop("My best Shop");
        Quote quote = new Quote(shop.getProductPrice("Iphone Super Duper Cool:"));
        System.out.println(quote.applyDiscount());
    }
}
