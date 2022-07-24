package chapter3.menu;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class Menu {

    private final ArrayList<Dish> menuList = new ArrayList<>();
    private static final int MENU_SIZE = 10;
    public ArrayList<Dish> getMenuList() {
        return menuList;
    }
    public enum CALORIC_LEVEL{DIET, NORMAL, FAT}

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
    public void getMostCalorieDish(){
        System.out.println("getMostCalorieDish");
        Optional<Dish> dish = this.menuList.stream().max(Comparator.comparingInt(Dish::getCalories));
        dish.ifPresent(aDish -> System.out.println(aDish.getName()));
    }
    public void getTotalCalories(){
        System.out.println("getTotalCalories");
        int totalCalories = this.menuList.stream().map(Dish::getCalories).reduce(0, Integer::sum);
        System.out.println(totalCalories);
    }
    public void getAverageCalories(){
        System.out.println("Average Calories");
        double averageCalories = this.menuList.stream().collect(Collectors.averagingInt(Dish::getCalories));
        System.out.println(averageCalories);
    }

    public void getMenuStatistics(){
        System.out.println("Menu Statistics");
        IntSummaryStatistics intSummaryStatistics = this.menuList.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        System.out.println("Max:" + intSummaryStatistics.getMax());
        System.out.println("Mean:" + intSummaryStatistics.getAverage());
        System.out.println("Min:" + intSummaryStatistics.getMin());
    }
    public void getMenuString(){
        System.out.println("Menu String");
        String menuName = this.menuList.stream().map(Dish::getName).collect(Collectors.joining(","));
        System.out.println("MenuName:" + menuName);

        String menuName1 = this.menuList.stream().map(Dish::getName).reduce("", (s1, s2) -> s1 + s2);
    }

    public void findAnyVegetarianDish(){
        Optional<Dish> dish = this.menuList.parallelStream().filter(Dish::isVegetarian).findAny( );
        dish.ifPresent(value -> System.out.println("Vegetarian: " + value));
        this.menuList.parallelStream().filter(Dish::isVegetarian).findAny().ifPresent(System.out::println);
    }
    public void getDishMap(){
        Map<Dish.Type, List<Dish>> map = this.menuList.stream().collect(groupingBy(Dish::getType));
        for (var entry : map.entrySet()){
            System.out.println("Key:" + entry.getKey());
            entry.getValue().forEach(dish -> System.out.println(dish.getName()));
        }
    }
    public void groupByCalorieLevel(){
        Map<CALORIC_LEVEL, List<Dish>> map = this.menuList.stream().collect(groupingBy(dish -> {
            if (dish.getCalories() <= 400){
                return CALORIC_LEVEL.DIET;
            } else if (dish.getCalories() <= 700) {
                return CALORIC_LEVEL.NORMAL;
            }
            return CALORIC_LEVEL.FAT;
        }));
        for (var entry: map.entrySet()){
            System.out.println("Key:" + entry.getKey());
            entry.getValue().forEach(dish -> System.out.println(dish.getName()));
        }
    }
    public void performNLevelGrouping(){
        Map<Dish.Type, Map<CALORIC_LEVEL, List<Dish>>> groupByTypeAndCalorie = this.menuList.stream()
                .collect(groupingBy(Dish::getType, groupingBy(dish -> {
                    if (dish.getCalories() <= 400){
                        return CALORIC_LEVEL.DIET;
                    } else if (dish.getCalories() <= 700){
                        return CALORIC_LEVEL.NORMAL;
                    }
                    return CALORIC_LEVEL.FAT;
                })));
        for (Map.Entry<Dish.Type, Map<CALORIC_LEVEL, List<Dish>>> level1 : groupByTypeAndCalorie.entrySet()){
            System.out.println("Key:" + level1.getKey());
            for (Map.Entry<CALORIC_LEVEL, List<Dish>> level2: level1.getValue().entrySet()){
                System.out.println("Key2:" + level2.getKey());
                level2.getValue().forEach(dish -> System.out.println(dish.getName()));
            }
        }
    }

    public void countNumberOfDishesForEachType(){
        Map<Dish.Type, Long> groupByTypeAndCount = this.menuList.stream().collect(groupingBy(Dish::getType, Collectors.counting()));
    }
    public void calculateTotalCaloriesForEachType(){
        Map<Dish.Type, Long> groupByTypeAndTotalCalories = this.menuList.stream().collect(groupingBy(Dish::getType, Collectors.summingLong( Dish::getCalories)));
        groupByTypeAndTotalCalories.forEach((key, value) -> {
            System.out.println("Key:" + key);
            System.out.println("Value:" + value);
        });
    }
    public void getMostCaloriesByType(){
        Map<Dish.Type, Optional<Dish>> getMostCaloriesByType = this.menuList.stream().collect(groupingBy(Dish::getType, maxBy(Comparator.comparingInt(Dish::getCalories))));
        getMostCaloriesByType.forEach((key, value) ->{
            System.out.println("Key:" + key);
            value.ifPresent(dish -> System.out.println("Value:"+ dish.getName()));
        });
        Map<Dish.Type, Dish> getMostCaloriesByType2 = this.menuList.stream().collect(groupingBy(Dish::getType, collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
        getMostCaloriesByType2.forEach((key, value) ->{
            System.out.println("Key:" + key);
            System.out.println("Value:" + value.getName());
        });

    }
    public void getTypeAndCalorieLevel(){
        System.out.println("getTypeAndCalorieLevel");
        Map<Dish.Type, Set<CALORIC_LEVEL>> typeAndCalorieLevel = this.menuList.stream().collect(groupingBy(Dish::getType, mapping(dish ->{
            if (dish.getCalories() <= 400){
                return CALORIC_LEVEL.DIET;
            } else if (dish.getCalories() <= 700){
                return CALORIC_LEVEL.NORMAL;
            }
            return CALORIC_LEVEL.FAT;

        }, toSet())));
        typeAndCalorieLevel.forEach((key, setValue)->{
            System.out.println("Key:" + key);
            setValue.forEach(System.out::println);
        } );
    }
    public void partitionMenu(){
        Map<Boolean, List<Dish>> mapDish = this.menuList.stream().collect(partitioningBy(Dish::isVegetarian));
        List<Dish> vegetarianList = mapDish.get(true);
        vegetarianList.forEach(dish -> System.out.println("Vegetarian:" + dish.isVegetarian() + ", Dish Name:" + dish.getName()));
    }
    public void partitionMenuByType(){
        Map<Boolean, Map<Dish.Type,List<Dish>>> mapDish = this.menuList.stream().collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
        Map<Dish.Type,List<Dish>> vegetarianMenu = mapDish.get(true);
        vegetarianMenu.forEach((key, value) ->{
            System.out.println("Key:" + key);
            value.forEach( dish -> System.out.println("Name:" + dish.getName()));
        });
    }
    public void mostCaloricPartitionedByVegetarian(){
        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian = this.menuList.stream().collect(
                partitioningBy(Dish::isVegetarian,
                collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
        System.out.println("VegetarianHighestCalories:" + mostCaloricPartitionedByVegetarian.get(true) );
        System.out.println("NonVegetarianHighestCalories:" + mostCaloricPartitionedByVegetarian.get(false));
    }
    public int numberOfDishes(){
        return this.menuList.stream().map(dish -> 1).reduce( 0, Integer::sum);
    }
    public static boolean isPrime(int n){
        int rootN = (int) Math.sqrt( n);
        return IntStream.rangeClosed(2, rootN).boxed().noneMatch(x -> n % x == 0);
    }
    public static void partitionPrimes(int endRange){
        Map<Boolean, List<Integer>> list = IntStream.rangeClosed(2, endRange).boxed().collect(partitioningBy(Menu::isPrime));
        list.forEach((key, value) ->{
            System.out.println("Prime Numbers:" + key);
            value.forEach(System.out::println);
        });
    }


//    public static void main(String[] args) {
//        partitionPrimes(9);
//        Menu menu = new Menu();
//        AddSpecificDishes addSpecificDishes = menu.getMenuAdder();
//        addSpecificDishes.addTenRandomDishes();
//        System.out.println(menu);
//        System.out.println(menu.getLowCaloriesSortedDishes());
//
//        List<Dish> dishes = menu.getFirstTwoMeatDishes();
//        dishes.forEach(System.out::println);
//        List<Integer> dishesNameLengths = menu.getDishNameLengths();
//        dishesNameLengths.forEach(System.out::println);
//        menu.findAnyVegetarianDish();
//        System.out.println("sum of all calories:" + menu.numberOfDishes());
//
//        menu.getMostCalorieDish();
//        menu.getTotalCalories();
//        menu.getAverageCalories();
//        menu.getMenuStatistics();
//        menu.getMenuString();
//        menu.getDishMap();
//        menu.groupByCalorieLevel();
//        menu.performNLevelGrouping();
//        menu.calculateTotalCaloriesForEachType();
//        menu.getMostCaloriesByType();
//        menu.getTypeAndCalorieLevel();
//        menu.partitionMenu();
//        menu.partitionMenuByType();
//        menu.mostCaloricPartitionedByVegetarian();
//
//    }
public static void main(String[] args) {
        
}
}
