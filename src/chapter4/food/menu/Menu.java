package chapter4.food.menu;

import chapter4.food.dish.Dish;

import java.util.List;
import java.util.stream.Collectors;

public class Menu implements MenuImpl {
    private final List<Dish> menuList;
    public Menu(List<Dish> dishList){
        this.menuList = dishList;
    }

    public List<Dish> getMenuList() {
        return menuList;
    }

    @Override
    public List<String> getThreeHighCaloriesDishNames() {
        return this.menuList.stream()
                .filter(Dish::isHighCalories)
                .map(Dish::toString)
                .limit(3)
                .collect(Collectors.toList());
    }
}
