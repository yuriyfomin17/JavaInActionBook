package chapter11.asyncAPI;

import java.util.List;
import java.util.Random;

public class Discount {
    private final int percentage;

    public int getPercentage() {
        return percentage;
    }

    enum Code {NONE, SILVER, GOLD, PLATINUM, DIAMOND}

    static final List<Code> codes = List.of(Code.values());
    static final Random RANDOM = new Random();
    private static final int SIZE = Code.values().length;

    public static Code getRandomCode() {
        return codes.get(RANDOM.nextInt(SIZE));
    }


    public Discount(int percentage) {
        this.percentage = percentage;
    }
}
