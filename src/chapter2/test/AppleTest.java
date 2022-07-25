package chapter2.test;
import chapter2.Apple;
import chapter2.util.AppleDataGenerator;
import chapter2.util.Color;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class AppleTest {

    @Test
    public void testAppleIsHeavyMethod(){
        List<Apple> apples = AppleDataGenerator.getListOfRandomApples(20);
        List<Apple> filteredApples = Apple.filterApples(apples, Apple::isAppleHeavy);

        System.out.println("HEAVY APPLES");
        filteredApples.forEach(System.out::println);
        System.out.println();
    }
    @Test
    public void testAppleIsGreenMethod(){
        List<Apple> apples = AppleDataGenerator.getListOfRandomApples(20);
        List<Apple> filteredApples = Apple.filterApples(apples, Apple::isAppleGreen);

        System.out.println("GREEN APPLES");
        filteredApples.forEach(System.out::println);
        System.out.println();
    }

    @Test
    public void testAppleIsHeavyAndRed(){
        List<Apple> apples = AppleDataGenerator.getListOfRandomApples(20);
        List<Apple> filteredApples = Apple.filterApples(apples, Apple::isAppleRedAndHeavy);

        System.out.println("HEAVY AND RED APPLES");
        filteredApples.forEach(System.out::println);
        System.out.println();
    }
    @Test
    public void sortApples(){
        List<Apple> apples = AppleDataGenerator.getListOfRandomApples(20);
        Comparator<Apple> appleComparator = Comparator.comparingInt(Apple::getWeight);
        apples.sort(appleComparator);
        apples.forEach(System.out::println);
    }
    @Test
    public void sortApplesByMethodReferences(){
        List<Apple> apples = AppleDataGenerator.getListOfRandomApples(20);
        apples.sort(Comparator.comparing(Apple::getWeight));
        apples.forEach(System.out::println);
    }

    @Test
    public void sortApplesByWeightAndColor(){
        List<Apple> apples = AppleDataGenerator.getListOfRandomApples(20);
        apples.sort(Comparator.comparing(Apple::getWeight).thenComparing(Apple::getColor).reversed());
        apples.forEach(System.out::println);
    }

    @Test
    public void createNewApples(){
        List<Integer> weights = List.of(100, 110, 120, 130, 140, 150);
        List<Color> colors = List.of(Color.RED, Color.BLUE, Color.ORANGE, Color.GREEN, Color.INDIGO, Color.PINK);

        List<Apple> apples = Apple.createApples(weights, colors, Apple::new);
        apples.forEach(System.out::println);
    }

    @Test
    public void negatePredicate(){
        Predicate<Apple> redApple = Apple::isAppleRed;
        Predicate<Apple> notRedApple = redApple.negate();
        Predicate<Apple> redAndHeavyApple = redApple.and(Apple::isAppleHeavy);
        Predicate<Apple> greenOrRedAndHeavyApple = redAndHeavyApple.or(Apple::isAppleGreen);
        List<Apple> apples = AppleDataGenerator.getListOfRandomApples(30);

        List<Apple> redAndHeavyApples = Apple.filterApples(apples, redAndHeavyApple);
        System.out.println("RED AND HEAVY APPLES");
        redAndHeavyApples.forEach(System.out::println);

        List<Apple> notReadApples = Apple.filterApples(apples, notRedApple);
        System.out.println("NOT RED APPLES");
        notReadApples.forEach(System.out::println);


        List<Apple> greenOrRedAndHeavyApples = Apple.filterApples(apples, greenOrRedAndHeavyApple);
        System.out.println("GREEN OR RED AND HEAVY APPLES");
        greenOrRedAndHeavyApples.forEach(System.out::println);


    }

}
