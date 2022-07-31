package chapter11.asyncAPI;

import java.util.Random;

public class Quote {
    private final String shopName;
    private final Integer price;
    private final Discount.Code discountCode;


    public Quote(String shopName, Integer price, Discount.Code code) {
        this.shopName = shopName;
        this.price = price;
        this.discountCode = code;
    }

    public static Quote parse(String s) {
        String[] split = s.split(":");
        String shopName = split[0];
        Discount.Code discountCode = Discount.Code.valueOf(split[2]);
        return new Quote(shopName, getRandomPrice() , discountCode);
    }

    public String getShopName() {
        return shopName;
    }

    public double getPrice() {
        return price;
    }

    public Discount.Code getDiscountCode() {
        return discountCode;
    }

    private static int getRandomPrice(){
        return new Random().nextInt(250);
    }

}
