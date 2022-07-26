package chapter4.food.test;
import chapter4.food.dish.Dish;
import chapter4.food.menu.Menu;
import chapter4.food.util.DishDataGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FoodTest {
    private static final List<Dish> DISHLIST = DishDataGenerator.getDishList(20);

    @Test
    public void generateRandomsDishes(){
        DISHLIST.forEach(System.out::println);
    }

    @Test
    public void getThreeHighCaloriesDishes(){
        Menu menu = new Menu(DISHLIST);
        menu.getThreeHighCaloriesDishNames().forEach(System.out::println);

    }

    @Test
    public void testMenuIsHealthy(){
        Menu menu = new Menu(DISHLIST);
        String isHealthy =  menu.isMenuHealthy() ? "healthy" : "not healthy";
        System.out.println("Menu is " + isHealthy);
    }

    @Test
    public void findAnyVegeterianDish(){
        Menu menu = new Menu(DISHLIST);
        menu.findAnyVegeterianDish().ifPresent(System.out::println);
    }

    @Test
    public void testGetAverageMenuCalories(){
        Menu menu = new Menu(DISHLIST);
        System.out.println("Average calories:" + menu.getAverageMenuCalories());
    }
}
