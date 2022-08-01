package chapt11.shopAPI;

import chapt11.util.UtillClass;

public class DiscountQuote {
    private final String shopName;
    private final String productName;
    private final double productPrice;
    private final Discount discount;
    private final double productPriceWithDiscount;
    private final long shopDelay;
    private final long discountDelay;

    private final long totalDealy;

    public DiscountQuote(ShopQuote shopQuote) {
        this.shopName = shopQuote.getShopName();
        this.productPrice = shopQuote.getProductPrice();
        this.productName = shopQuote.getProductName();
        this.shopDelay = shopQuote.getShopDelay();

        long start = System.nanoTime();
        UtillClass.delay();
        this.discount = Discount.getDiscount();
        productPriceWithDiscount = UtillClass.round(productPrice - Discount.applyDiscount(discount) * productPrice, 2);
        long end = System.nanoTime();
        discountDelay = (end - start) / 1_000_000;

        totalDealy = this.shopDelay + discountDelay;

    }

    public String getShopName() {
        return shopName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public double getProductPriceWithDiscount() {
        return productPriceWithDiscount;
    }

    public String getProductName() {
        return productName;
    }

    public long getShopDelay() {
        return shopDelay;
    }

    public long getDiscountDelay() {
        return discountDelay;
    }

    @Override
    public String toString() {
        return "DiscountQuote{" +
                "shopName='" + shopName + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", discount=" + discount +
                ", productPriceWithDiscount=" + productPriceWithDiscount +
                ", shopDelay=" + shopDelay +
                ", discountDelay=" + discountDelay +
                ", totalDealy=" + totalDealy +
                '}';
    }
}
