package chapter4.food.menu;

import chapter4.food.dish.Dish;
import chapter4.food.util.CaloricLevel;
import chapter4.food.util.Type;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

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

    @Override
    public String getNamesOfAllDishes() {
        return this.menuList.stream().map(Dish::getName).collect(Collectors.joining(","));
    }

    @Override
    public Map<Type, List<Dish>> groupDishesByType() {
        return this.menuList.stream().collect(groupingBy(Dish::getType));
    }

    @Override
    public Map<CaloricLevel, List<Dish>> gorupDishesByCaloicLevel() {
        return this.menuList.stream().collect(groupingBy(Dish::getCaloricLevel));
    }

    @Override
    public Map<Type, Map<CaloricLevel, List<Dish>>> groupByTypeAndCaloricLevel() {
        return this.menuList.stream().collect(groupingBy(Dish::getType, groupingBy(Dish::getCaloricLevel)));
    }

    @Override
    public Map<Type, Long> getCountByType() {
        return this.menuList.stream().collect(groupingBy(Dish::getType, counting()));
    }

    @Override
    public Map<Type, Optional<Dish>> getByTypeMostCaloric() {
        return this.menuList.stream().collect(groupingBy(Dish::getType, maxBy(Comparator.comparingInt(Dish::getCalories))));
    }

    @Override
    public Map<Type, Dish> getByTypeMostCaloricWithoutOptional() {
        return this.menuList.stream().collect(groupingBy(Dish::getType, collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
    }

    @Override
    public Map<Type, Set<CaloricLevel>> getByTypeToSet() {
        return this.menuList.stream().collect(groupingBy(Dish::getType, mapping(Dish::getCaloricLevel, toSet())));
    }

    @Override
    public Map<Boolean, List<Dish>> partitionByVegeterianDish() {
        return this.menuList.stream().collect(partitioningBy(Dish::isVegeterian));
    }

    @Override
    public Map<Boolean, Map<Type, List<Dish>>> partitioanByVegeterianGroupByType() {
        return this.menuList.stream().collect(partitioningBy(Dish::isVegeterian, groupingBy(Dish::getType)));
    }

    @Override
    public Map<Boolean, Map<Boolean, List<Dish>>> partitionByVegeterianPartitionByCaloricLevel() {
        return this.menuList.stream().collect(partitioningBy(Dish::isVegeterian,  partitioningBy(Dish::isLowCalories)));
    }

    @Override
    public Map<Boolean, Long> partitionaByVegeterianGetTotalCount() {
        return this.menuList.stream().collect(partitioningBy(Dish::isVegeterian, counting()));
    }
}

