package chapter5.transactionTrader.util;

import java.util.List;
import java.util.Random;

public enum City {
    CAMBRIDGE, MILAN, LONDON, MADRID, NEW_YORK, KIEV, DONETSK, SHANGHAI;

    private static final List<City> VALUES = List.of(values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static City getRandomCity() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
