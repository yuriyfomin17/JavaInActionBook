package chapter11.shopAPI;

import java.util.List;
import java.util.Random;

public enum Discount {
    BRONZE, SILVER, GOLD, PLATINUM, DIAMOND;
    private static final List<Double> DISCOUNT_PERCENTAGES = List.of(0.1, 0.15, 0.2, 0.25, 0.3);
    private static final Random RANDOM = new Random();
    private static final List<Discount> DISCOUNT_LIST = List.of(values());
    private static final int SIZE = DISCOUNT_LIST.size();

    public static Double applyDiscount(Discount discount) {
        return DISCOUNT_PERCENTAGES.get(DISCOUNT_LIST.indexOf(discount));
    }

    public static Discount getDiscount() {
        return DISCOUNT_LIST.get(RANDOM.nextInt(SIZE));
    }

}
