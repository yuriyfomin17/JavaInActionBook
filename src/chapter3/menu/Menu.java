package chapter3.menu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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

    public static void main(String[] args) {

        Menu menu = new Menu();
        AddSpecificDishes addSpecificDishes = menu.getMenuAdder();
        addSpecificDishes.addTenRandomDishes();
        System.out.println(menu);
        System.out.println(menu.getLowCaloriesSortedDishes());
    }
}
