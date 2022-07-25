package chapter4.food.util;

import java.util.List;
import java.util.Random;

public enum Type {
    MEAT, FISH, VEGETERIAN;

    private static final List<Type> VALUES = List.of(values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();
    public static Type getRandomType(){
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
