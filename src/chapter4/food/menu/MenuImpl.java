package chapter4.food.menu;

import chapter4.food.dish.Dish;

import java.util.List;
import java.util.Optional;

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


}
