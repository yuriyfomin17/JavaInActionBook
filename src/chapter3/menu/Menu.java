package chapter3.menu;

import java.util.*;
import java.util.stream.Collectors;


public class Menu {

    private final ArrayList<Dish> menuList = new ArrayList<>();
    private static final int MENU_SIZE = 10;
    public ArrayList<Dish> getMenuList() {
        return menuList;
    }


    public AddSpecificDishes getMenuAdder(){
        return () ->{
            for (int i = 0; i < MENU_SIZE; i++) {
                this.getMenuList().add(Dish.returnRandomDish());
            }
        };
    }
    public List<String> getLowCaloriesSortedDishes(){
        return this.menuList.parallelStream()
                .filter((Dish dish) -> dish.getCalories() < 400.0)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
    }
    public List<Dish> getFirstTwoMeatDishes(){
        return this.menuList.parallelStream()
                .filter(Dish::isMeatDish)
                .limit(2)
                .collect(Collectors.toList());
    }
    public List<Integer> getDishNameLengths(){
        return this.menuList.parallelStream()
                .map((Dish dish) -> dish.getName().length())
                .collect(Collectors.toList());
    }
    public boolean allMealsAreVegetarian(){
        return this.menuList.parallelStream().allMatch(Dish::isVegetarian);
    }
    public boolean anyMealIsHealthy(){
        return this.menuList.parallelStream().anyMatch(dish -> dish.getCalories() < 1000);
    }

    public void findAnyVegetarianDish(){
        Optional<Dish> dish = this.menuList.parallelStream().filter(Dish::isVegetarian).findAny( );
        dish.ifPresent(value -> System.out.println("Vegetarian: " + value));

        this.menuList.parallelStream().filter(Dish::isVegetarian).findAny().ifPresent(System.out::println);
    }
    public int numberOfDishes(){
        return this.menuList.stream().map(dish -> 1).reduce( 0, Integer::sum);
    }


    public static void main(String[] args) {

        Menu menu = new Menu();
        AddSpecificDishes addSpecificDishes = menu.getMenuAdder();
        addSpecificDishes.addTenRandomDishes();
        System.out.println(menu);
        System.out.println(menu.getLowCaloriesSortedDishes());

        List<Dish> dishes = menu.getFirstTwoMeatDishes();
        dishes.forEach(System.out::println);
        List<Integer> dishesNameLengths = menu.getDishNameLengths();
        dishesNameLengths.forEach(System.out::println);
        menu.findAnyVegetarianDish();
        System.out.println("sum of all calories:" + menu.numberOfDishes());


    }
}
