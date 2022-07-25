package chapter4.food.menu;

import chapter4.food.dish.Dish;

import java.util.List;
import java.util.Optional;
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

    @Override
    public boolean isMenuHealthy() {
        return this.menuList.stream().allMatch(Dish::isLowCalories);
    }

    @Override
    public Optional<Dish> findAnyVegeterianDish() {
        return this.menuList.stream().filter(Dish::isVegeterian).findAny();
    }
}
