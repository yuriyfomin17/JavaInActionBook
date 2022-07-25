package chapter4.food.util;

import chapter4.food.dish.Dish;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DishDataGenerator {
    private static final Random RANDOM = new Random();

    public static Dish createRandomDish(){
        return new Dish(RandomStringUtils.randomAlphabetic(5), RANDOM.nextInt(1000), Type.getRandomType() );
    }

    public static List<Dish> getDishList(int size){
        return Stream.generate(DishDataGenerator::createRandomDish).limit(size).collect(Collectors.toList());
    }
}
