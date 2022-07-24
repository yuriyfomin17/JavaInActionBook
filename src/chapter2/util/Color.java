package chapter2.util;


import java.util.List;
import java.util.Random;

public enum Color {
    RED, BLUE, YELLOW, GREEN, ORANGE, VIOLET, PINK, DARK, INDIGO;
    private static final List<Color> VALUES = List.of(values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();
    public static Color getRandomColor(){
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

}
