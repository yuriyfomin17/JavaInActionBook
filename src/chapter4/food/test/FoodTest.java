package chapter4.food.test;
import chapter4.food.dish.Dish;
import chapter4.food.menu.Menu;
import chapter4.food.util.DishDataGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FoodTest {
    static List<Dish> dishList = DishDataGenerator.getDishList(20);

    @Test
    public void generateRandomsDishes(){
        dishList.forEach(System.out::println);
    }

    @Test
    public void getThreeHighCaloriesDishes(){
        Menu menu = new Menu(dishList);
        menu.getThreeHighCaloriesDishNames().forEach(System.out::println);

    }

    @Test
    public void testMenuIsHealthy(){
        Menu menu = new Menu(dishList);
        String isHealthy =  menu.isMenuHealthy() ? "healthy" : "not healthy";
        System.out.println("Menu is " + isHealthy);
    }

    @Test
    public void findAnyVegeterianDish(){
        Menu menu = new Menu(dishList);
        menu.findAnyVegeterianDish().ifPresent(System.out::println);
    }
}
