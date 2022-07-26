package chapter4.food.menu;

import chapter4.food.dish.Dish;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.summingInt;

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

    @Override
    public double getAverageMenuCalories() {
        return this.menuList.stream().collect(averagingInt(Dish::getCalories));
    }

    @Override
    public int getTotalMenuCalories() {
//        return this.menuList.stream().map(Dish::getCalories).reduce(0, Integer::sum);
        return this.menuList.stream().mapToInt(Dish::getCalories).sum();
    }
}
