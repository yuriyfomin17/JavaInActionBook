package chapter4.food.menu;

import chapter4.food.dish.Dish;
import chapter4.food.util.CaloricLevel;
import chapter4.food.util.Type;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface MenuImpl {

    /**
     * <h3>getThreeHighCaloriesDishNames</h3>  finds three dishes with highe calories
     */
    List<String> getThreeHighCaloriesDishNames();

    /**
     * <h3>isMenuHealthy</h3>  finds whether menu is healthy
     */
    boolean isMenuHealthy();

    /**
     * <h3>findAnyVegeterianDish</h3> find any vegeterian dish
     */
    Optional<Dish> findAnyVegeterianDish();

    /**
     * <h3>getAverageMenuCalories</h3> get average calories for menu
     */
    double getAverageMenuCalories();


    /**
     * <h3>getTotalMenuCalories</h3> get total calories for menu
     */
    int getTotalMenuCalories();

    /**
     * <h3>getNamesOfAllDishes<h3/>
     * returns string for names of all dishes
     */

    String getNamesOfAllDishes();


    /**
     * <h3>groupDishesByType<h3/>
     * groups dishes by type
     */
    Map<Type, List<Dish>> groupDishesByType();

    /**
     * <h3>gorupDishesByCaloicLevel</h3>
     * groups dishes by caloric level
     */

    Map<CaloricLevel, List<Dish>> gorupDishesByCaloicLevel();

    /**
     * <h3>groupByTypeAndCaloricLevel</h3>
     * groups dishes by type and calories level
     */
    Map<Type, Map<CaloricLevel, List<Dish>>> groupByTypeAndCaloricLevel();

    /**
     * <h3>getCountByType<h3/>
     *  count number of dishes for each type
     */
    Map<Type, Long> getCountByType();

    /**
     * <h3>getByTypeMostCaloric</h3>
     * get by type most caloric dish
     */
    Map<Type, Optional<Dish>> getByTypeMostCaloric();


    /**
     * <h3>getByTypeMostCaloricWithoutOptional</h3>
     * get dish by type and most caloric but this time without optional
     */
    Map<Type, Dish> getByTypeMostCaloricWithoutOptional();

    /**
     * <h3>getByTypeToSet</h3>
     * get caloric levels for each type
     */
    Map<Type, Set<CaloricLevel>> getByTypeToSet();

    /**
     * <h3>partitionByVegeterianDish<h3/>
     * partitions dishes by vegeterian type
     */
    Map<Boolean, List<Dish>> partitionByVegeterianDish();


    /**
     * <h3>partitionByVegeterianGroupByType<h3/>
     * partitions dishes by vegeterian and gets them by type
     */
    Map<Boolean, Map<Type, List<Dish>>> partitioanByVegeterianGroupByType();

    /**
     * <h3>partitionByVegeterianPartitionByCaloricLevel<h3/>
     * partitions menu by vegeterian and by caloric level
     */
    Map<Boolean, Map<Boolean, List<Dish>>> partitionByVegeterianPartitionByCaloricLevel();

    /**
     * <h3>partitionaByVegeterianGetTotalCount<h3/>
     * partitions by vegeterian and gets total count
     */
    Map<Boolean, Long> partitionaByVegeterianGetTotalCount();
}
