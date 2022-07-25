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
}
