package chapter11.asyncAPI;

import chapter11.util.UtilClass;
import chapter5.utilityClass.UtilityClass;

import java.util.List;
import java.util.Random;

public class Discount {
    private final int percentage;

    public int getPercentage() {
        return percentage;
    }

    enum Code {NONE, SILVER, GOLD, PLATINUM, DIAMOND}

    static final List<Code> codes = List.of(Code.values());

    static final List<Integer> discountPercentages = List.of(0, 5, 10, 15, 20);
    static final Random RANDOM = new Random();
    private static final int SIZE = Code.values().length;

    public static Code getRandomCode() {
        return codes.get(RANDOM.nextInt(SIZE));
    }


    public Discount(int percentage) {
        this.percentage = percentage;
    }

    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " + Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }

    private static double apply(double price, Code code) {
        UtilClass.delay();
        return price * (100 - discountPercentages.get(code.ordinal())) / 100;
    }
}
