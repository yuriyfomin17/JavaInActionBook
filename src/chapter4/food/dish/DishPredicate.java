package chapter4.food.dish;

public interface DishPredicate {
    /**
     * <h3>isVegeterian</h3>  whether dish is vegeterian or not
     */
    boolean isVegeterian();
    /**
     * <h3>isLowCalories</h3>  whether dish is low in calories or not
     */
    boolean isLowCalories();

    /**
     * <h3>isHighCalores</h3>  whether dish is high in calories or not
     */
    boolean isHighCalories();
}
