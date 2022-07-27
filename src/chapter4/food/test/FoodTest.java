package chapter4.food.test;
import chapter4.food.dish.Dish;
import chapter4.food.menu.Menu;
import chapter4.food.util.CaloricLevel;
import chapter4.food.util.DishDataGenerator;
import chapter4.food.util.Type;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

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

    @Test
    public void TestGetNamesOfAllDishes(){
        Menu menu = new Menu(DISHLIST);
        System.out.println(menu.getNamesOfAllDishes());
    }

    @Test
    public void TestGroupDishesByType(){
        Menu menu = new Menu(DISHLIST);
        Map<Type, List<Dish>> dishesType = menu.groupDishesByType();

        dishesType.forEach((type, dishList) -> System.out.println(type + "[" + dishList+"]"));
    }

    @Test
    public void TestGroupDishesByCaloricLevel(){
        Menu menu = new Menu(DISHLIST);
        Map<CaloricLevel, List<Dish>> dishTypes = menu.gorupDishesByCaloicLevel();

        dishTypes.forEach((caloricLevel, dishList) -> System.out.println(caloricLevel + ":" + dishList  ));
    }

    @Test
    public void TestGroupByTypeAndCaloricLevel(){
        Menu menu = new Menu(DISHLIST);
        Map<Type, Map<CaloricLevel, List<Dish>>> dishesByTypeAndCaloricLevel = menu.groupByTypeAndCaloricLevel();

        dishesByTypeAndCaloricLevel.forEach((type, caloricLevelListMap) -> {
            System.out.println(type);
            caloricLevelListMap.forEach((caloricLevel, dishList) -> {
                System.out.println(caloricLevel);
                System.out.println(dishList);
            });
        });
    }

    @Test
    public void TestGetCountByType(){
        Menu menu = new Menu(DISHLIST);
        Map<Type, Long> dishByTypeCount = menu.getCountByType();
        dishByTypeCount.forEach((type, count) -> System.out.println(type + ":" + count));
    }

    @Test
    public void TestGetByTypeMostCaloric(){
        Menu menu = new Menu(DISHLIST);
        Map<Type, Optional<Dish>> dishByTypeMostCaloric = menu.getByTypeMostCaloric();
        dishByTypeMostCaloric.forEach((type, dish) -> dish.ifPresent(d ->{
            System.out.println(type);
            System.out.println(d);
        }));
    }

    @Test
    public void TestGetByTypeMostCaloricWithoutOptional(){
        Menu menu = new Menu(DISHLIST);
        Map<Type, Dish> dishByTypeMostcaloricWithoutOptional = menu.getByTypeMostCaloricWithoutOptional();

        dishByTypeMostcaloricWithoutOptional.forEach((type, dish) -> {
            System.out.println(type);
            System.out.println(dish);
        });
    }

    @Test
    public void TestGetByTypeToSet(){
        Menu menu = new Menu(DISHLIST);
        Map<Type, Set<CaloricLevel>> typeCaloricLevelSet = menu.getByTypeToSet();
        typeCaloricLevelSet.forEach((type, caloricLevels) -> {
            System.out.println(type);
            caloricLevels.forEach(System.out::println);
            System.out.println();
        });
    }

    @Test
    public void TestParitionByVegeterian(){
        Menu menu = new Menu(DISHLIST);
        Map<Boolean, List<Dish>> vegeterianNonVegeterianDishes = menu.partitionByVegeterianDish();

        List<Dish> vegeterian = vegeterianNonVegeterianDishes.get(true);
        List<Dish> nonVegeterian = vegeterianNonVegeterianDishes.get(false);

        System.out.println("VEGETERIAN DISHES");
        vegeterian.forEach(System.out::println);

        System.out.println("NON VEGETERIAN DISHES");
        nonVegeterian.forEach(System.out::println);
    }

    @Test
    public void TestPartitionByVegeterianGroupByType(){
        Menu menu = new Menu(DISHLIST);

        Map<Boolean, Map<Type, List<Dish>>> veganNonVeganGroupedByType = menu.partitioanByVegeterianGroupByType();

        veganNonVeganGroupedByType.forEach((isVegan, typeListMap) -> {
            System.out.println(isVegan ? "VEGAN DISHES" : "NON VEGAN DISHES");
            typeListMap.forEach((type, dishList) -> {
                System.out.println(type);
                System.out.println(dishList);
            });
        });
    }

    @Test
    public void TestPartitionByVegeterianPartitionByCaloricLevel(){
        Menu menu = new Menu(DISHLIST);

        Map<Boolean, Map<Boolean, List<Dish>>> veganNonVeganLowCaloriesNonLowCalories = menu.partitionByVegeterianPartitionByCaloricLevel();

        veganNonVeganLowCaloriesNonLowCalories.forEach((isVegan, caloricListMap) ->{
            System.out.println(isVegan ? "VEGAN" : "NON VEGAN");
            caloricListMap.forEach((isLowCalories, dishList) -> {
                System.out.println(isLowCalories ? "LOW CALORIES" : "MEDIUM OR HIGH CLAORIES");
                dishList.forEach(System.out::println);
            });
        } );
    }

    @Test
    public void TestPartitionsByVegeterianAndGetsTotalCount(){
        Menu menu = new Menu(DISHLIST);
        Map<Boolean, Long> veganNonVeganTotalCount = menu.partitionaByVegeterianGetTotalCount();

        veganNonVeganTotalCount.forEach((isVegan, totalCount) -> {
            System.out.println(isVegan ? "VEGAN" : "NON VEGAN");
            System.out.println(totalCount);
        });
    }

}
