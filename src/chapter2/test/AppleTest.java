package chapter2.test;
import chapter2.Apple;
import chapter2.util.AppleDataGenerator;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

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

}
