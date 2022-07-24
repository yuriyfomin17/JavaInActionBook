package chapt2.util;

import chapt2.Apple;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AppleDataGenerator {
    private static final Random RANDOM = new Random();
    public static Apple createRandomApple() {
        Apple apple = new Apple();
        apple.setColor(Color.getRandomColor());
        apple.setWeight(RANDOM.nextInt(200));
        return apple;
    }

    public static List<Apple> getListOfRandomApples(int size){
        return Stream.generate(AppleDataGenerator::createRandomApple).limit(size).collect(Collectors.toList());
    }
}
