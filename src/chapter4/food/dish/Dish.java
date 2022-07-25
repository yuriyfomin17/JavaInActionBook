package chapter4.food.dish;

import chapter4.food.util.Type;

public class Dish implements DishPredicate {
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    private static final int LOW_CALORIES = 400;

    public Dish(String name, int calories, Type type) {
        this.name = name;
        this.calories = calories;
        this.type = type;
        this.vegetarian = this.type.equals(Type.VEGETERIAN);
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }

    @Override
    public boolean isVegeterian() {
        return this.type.equals(Type.VEGETERIAN);
    }

    @Override
    public boolean isLowCalories() {
        return this.calories < LOW_CALORIES;
    }

    @Override
    public boolean isHighCalories() {
        return this.calories > LOW_CALORIES;
    }


    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", vegetarian=" + vegetarian +
                ", calories=" + calories +
                ", type=" + type +
                '}';
    }
}
